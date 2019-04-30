package com.bryanrady.ui.view.recycler.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bryanrady.ui.R;

/**
 * 网格布局管理器的RecyclerView的分割线
 * Created by Administrator on 2019/4/25.
 */

public class GridRecyclerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;

    public GridRecyclerItemDecoration(Context context) {
        mDivider = ContextCompat.getDrawable(context, R.drawable.linear_recycler_item_decoration);
    }


    /**
     * 获取分割线尺寸
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        int orientation = ((GridLayoutManager)parent.getLayoutManager()).getOrientation();
        int position = parent.getChildLayoutPosition(view);
        if(orientation == GridLayoutManager.VERTICAL && (position + 1) % spanCount == 0) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            return;
        }

        if(orientation == GridLayoutManager.HORIZONTAL && (position + 1) % spanCount == 0) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            return;
        }

        outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
    }

    /**
     * 绘制分割线
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        // 绘制间隔，每一个item，绘制右边和下方间隔样式
        int childCount = parent.getChildCount();
        int spanCount = ((GridLayoutManager)parent.getLayoutManager()).getSpanCount();
        int orientation = ((GridLayoutManager)parent.getLayoutManager()).getOrientation();
        boolean isDrawHorizontalDivider = true;
        boolean isDrawVerticalDivider = true;
        int extra = childCount % spanCount;
        extra = extra == 0 ? spanCount : extra;
        for(int i = 0; i < childCount; i++) {
            isDrawVerticalDivider = true;
            isDrawHorizontalDivider = true;
            // 如果是竖直方向，最右边一列不绘制竖直方向的间隔
            if(orientation == GridLayoutManager.VERTICAL && (i + 1) % spanCount == 0) {
                isDrawVerticalDivider = false;
            }

            // 如果是竖直方向，最后一行不绘制水平方向间隔
            if(orientation == GridLayoutManager.VERTICAL && i >= childCount - extra) {
                isDrawHorizontalDivider = false;
            }

            // 如果是水平方向，最下面一行不绘制水平方向的间隔
            if(orientation == GridLayoutManager.HORIZONTAL && (i + 1) % spanCount == 0) {
                isDrawHorizontalDivider = false;
            }

            // 如果是水平方向，最后一列不绘制竖直方向间隔
            if(orientation == GridLayoutManager.HORIZONTAL && i >= childCount - extra) {
                isDrawVerticalDivider = false;
            }

            if(isDrawHorizontalDivider) {
                drawHorizontalDivider(c, parent, i);
            }

            if(isDrawVerticalDivider) {
                drawVerticalDivider(c, parent, i);
            }
        }

    }

    /**
     * 绘制竖直间隔线
     * @param canvas
     * @param parent    父布局，RecyclerView
     * @param position  item在父布局中所在的位置
     *
     */
    private void drawVerticalDivider(Canvas canvas, RecyclerView parent, int position) {
        final View child = parent.getChildAt(position);
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int top = child.getTop() - params.topMargin;
        final int bottom = child.getBottom() + params.bottomMargin + mDivider.getIntrinsicHeight();
        final int left = child.getRight() + params.rightMargin;
        final int right = left + mDivider.getIntrinsicWidth();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(canvas);
    }

    /**
     * 绘制水平间隔线
     * @param canvas
     * @param parent    父布局，RecyclerView
     * @param position  item在父布局中所在的位置
     */
    private void drawHorizontalDivider(Canvas canvas, RecyclerView parent, int position) {
        final View child = parent.getChildAt(position);
        final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
        final int top = child.getBottom() + params.bottomMargin;
        final int bottom = top + mDivider.getIntrinsicHeight();
        final int left = child.getLeft() - params.leftMargin;
        final int right = child.getRight() + params.rightMargin + mDivider.getIntrinsicWidth();
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(canvas);
    }

}
