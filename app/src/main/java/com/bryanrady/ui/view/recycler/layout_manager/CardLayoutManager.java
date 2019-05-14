package com.bryanrady.ui.view.recycler.layout_manager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 自定义布局管理器
 * https://blog.csdn.net/qibin0506/article/details/52676670
 *
 * 自定义LayoutManager主要要求我们完成三件事情：
 *          计算每个ItemView的位置；
 *          处理滑动事件；
 *          缓存并重用ItemView；
 *
 *          而我们比较重要的工作是在onLayoutChildern() 这个回调方法中完成的。
 */

public class CardLayoutManager extends RecyclerView.LayoutManager {

    private static final int DEFAULT_GROUP_SIZE = 5;
    private boolean mGravityCenter; //当我们item布局的宽度小于总宽度时，是否要居中显示
    private int mGroupSize;

    private int mHorizontalOffset;
    private int mVerticalOffset;
    private int mTotalWidth;
    private int mTotalHeight;
    private int mGravityOffset;

    private Pool<Rect> mItemFrames;

    public CardLayoutManager(boolean gravityCenter){
        this(gravityCenter,DEFAULT_GROUP_SIZE);
    }

    public CardLayoutManager(boolean gravityCenter,int groupSize){
        mGravityCenter = gravityCenter;
        mGroupSize = groupSize;

        mItemFrames = new Pool<>(new Pool.New<Rect>() {
            @Override
            public Rect get() { return new Rect();}
        });
    }

    /**
     * 获取我们的每个ItemView提供默认的LayoutParams
     * @return
     */
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        return layoutParams;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) { return;}

        detachAndScrapAttachedViews(recycler);
        View first = recycler.getViewForPosition(0);
        measureChildWithMargins(first, 0, 0);
        int itemWidth = getDecoratedMeasuredWidth(first);
        int itemHeight = getDecoratedMeasuredHeight(first);

        int firstLineSize = mGroupSize / 2 + 1;
        int secondLineSize = firstLineSize + mGroupSize / 2;
        if (mGravityCenter && firstLineSize * itemWidth < getHorizontalSpace()) {
            mGravityOffset = (getHorizontalSpace() - firstLineSize * itemWidth) / 2;
        } else {
            mGravityOffset = 0;
        }

        for (int i = 0; i < getItemCount(); i++) {
            Rect item = mItemFrames.get(i);
            float coefficient = isFirstGroup(i) ? 1.5f : 1.f;
            int offsetHeight = (int) ((i / mGroupSize) * itemHeight * coefficient);

            // 每一组的第一行
            if (isItemInFirstLine(i)) {
                int offsetInLine = i < firstLineSize ? i : i % mGroupSize;
                item.set(mGravityOffset + offsetInLine * itemWidth, offsetHeight, mGravityOffset + offsetInLine * itemWidth + itemWidth,
                        itemHeight + offsetHeight);
            }else {
                int lineOffset = itemHeight / 2;
                int offsetInLine = (i < secondLineSize ? i : i % mGroupSize) - firstLineSize;
                item.set(mGravityOffset + offsetInLine * itemWidth + itemWidth / 2,
                        offsetHeight + lineOffset, mGravityOffset + offsetInLine * itemWidth + itemWidth  + itemWidth / 2,
                        itemHeight + offsetHeight + lineOffset);
            }
        }

        mTotalWidth = Math.max(firstLineSize * itemWidth, getHorizontalSpace());
        int totalHeight = getGroupSize() * itemHeight;
        if (!isItemInFirstLine(getItemCount() - 1)) { totalHeight += itemHeight / 2;}
        mTotalHeight = Math.max(totalHeight, getVerticalSpace());
        fill(recycler, state);
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() <= 0 || state.isPreLayout()) { return;}
        Rect displayRect = new Rect(mHorizontalOffset, mVerticalOffset,
                getHorizontalSpace() + mHorizontalOffset,
                getVerticalSpace() + mVerticalOffset);

        // Rect rect = new Rect();
        // for (int i = 0; i < getChildCount(); i++) {
        // View item = getChildAt(i);
        // rect.left = getDecoratedLeft(item);
        // rect.top = getDecoratedTop(item);
        // rect.right = getDecoratedRight(item);
        // rect.bottom = getDecoratedBottom(item);
        // if (!Rect.intersects(displayRect, rect)) {
        // removeAndRecycleView(item, recycler);
        // }
        // }

        for (int i = 0; i < getItemCount(); i++) {
            Rect frame = mItemFrames.get(i);
            if (Rect.intersects(displayRect, frame)) {
                View scrap = recycler.getViewForPosition(i);
                addView(scrap);
                measureChildWithMargins(scrap, 0, 0);
                layoutDecorated(scrap, frame.left - mHorizontalOffset, frame.top - mVerticalOffset,
                        frame.right - mHorizontalOffset, frame.bottom - mVerticalOffset);
            }
        }
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        if (mVerticalOffset + dy < 0) {
            dy = -mVerticalOffset;
        } else if (mVerticalOffset + dy > mTotalHeight - getVerticalSpace()) {
            dy = mTotalHeight - getVerticalSpace() - mVerticalOffset;
        }

        offsetChildrenVertical(-dy);
        fill(recycler, state);
        mVerticalOffset += dy;
        return dy;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        if (mHorizontalOffset + dx < 0) {
            dx = -mHorizontalOffset;
        } else if (mHorizontalOffset + dx > mTotalWidth - getHorizontalSpace()) {
            dx = mTotalWidth - getHorizontalSpace() - mHorizontalOffset;
        }

        offsetChildrenHorizontal(-dx);
        fill(recycler, state);
        mHorizontalOffset += dx;
        return dx;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    private boolean isItemInFirstLine(int index) {
        int firstLineSize = mGroupSize / 2 + 1;
        return index < firstLineSize || (index >= mGroupSize && index % mGroupSize < firstLineSize);
    }

    private int getGroupSize() {
        return (int) Math.ceil(getItemCount() / (float)mGroupSize);
    }

    private boolean isFirstGroup(int index) {
        return index < mGroupSize;
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }


}
