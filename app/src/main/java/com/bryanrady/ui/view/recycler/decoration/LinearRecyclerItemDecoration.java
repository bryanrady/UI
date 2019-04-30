package com.bryanrady.ui.view.recycler.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bryanrady.ui.R;

/**
 * 线性布局管理器的RecyclerView的分割线
 * Created by Administrator on 2019/4/25.
 */

public class LinearRecyclerItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 分割线高度
     */
    private int mDividerSize;
    /**
     * 线性列表方向(横向、纵向)
     */
    private int mOrientation;
    /**
     * 分割线资源
     */
    private Drawable mDivider;

    public LinearRecyclerItemDecoration(Context context) {
        this(context,LinearLayoutManager.VERTICAL);
    }

    public LinearRecyclerItemDecoration(Context context, int orientation) {
        this(context, orientation, 0);
    }

    public LinearRecyclerItemDecoration(Context context, int orientation, int drawableId) {
        this(context, orientation, drawableId, 0);
    }

    public LinearRecyclerItemDecoration(Context context, int orientation, int drawableId, int dividerSize) {
        if(drawableId == 0){
            drawableId = R.drawable.linear_recycler_item_decoration;
        }
        mDivider = ContextCompat.getDrawable(context, drawableId);
        if (orientation != LinearLayoutManager.HORIZONTAL && orientation != LinearLayoutManager.VERTICAL) {
            throw new IllegalArgumentException("Invalid orientation!");
        }
        mOrientation = orientation;

        if(dividerSize == 0){
            if(mOrientation == LinearLayoutManager.HORIZONTAL){
                mDividerSize = mDivider.getIntrinsicWidth();
            }else if(mOrientation == LinearLayoutManager.VERTICAL){
                mDividerSize = mDivider.getIntrinsicHeight();
            }
        }else{
            mDividerSize = dividerSize;
        }
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
        if(mOrientation == LinearLayoutManager.HORIZONTAL){
            outRect.set(0, 0, mDividerSize, 0);
        }else if(mOrientation == LinearLayoutManager.VERTICAL){
            outRect.set(0, 0, 0, mDividerSize);
        }
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
        if(mOrientation == LinearLayoutManager.HORIZONTAL){
            drawHorizontalDividLine(c,parent);
        }else if(mOrientation == LinearLayoutManager.VERTICAL){
            drawVerticalDividLine(c,parent);
        }
    }

    private void drawVerticalDividLine(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + mDividerSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }

    private void drawHorizontalDividLine(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            final int right = left + mDividerSize;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }
}
