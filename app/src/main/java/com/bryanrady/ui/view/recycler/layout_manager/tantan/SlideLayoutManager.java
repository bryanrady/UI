package com.bryanrady.ui.view.recycler.layout_manager.tantan;

import android.support.annotation.NonNull;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * 图片滑动布局 类似探探
 * Created by Administrator on 2019/5/23.
 */

public class SlideLayoutManager extends RecyclerView.LayoutManager {

    private RecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;

    public SlideLayoutManager(RecyclerView recyclerView, ItemTouchHelper itemTouchHelper) {
        this.mRecyclerView = recyclerView;
        this.mItemTouchHelper = itemTouchHelper;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(final RecyclerView.Recycler recycler, RecyclerView.State state) {
        if(getItemCount() <= 0){
            return;
        }
        if(state.isPreLayout()){
            return;
        }

        detachAndScrapAttachedViews(recycler);

        if (getItemCount() > ItemConfig.DEFAULT_SHOW_ITEM) {
            for (int position = ItemConfig.DEFAULT_SHOW_ITEM; position >= 0; position--) {
                final View view = recycler.getViewForPosition(position);
                addView(view);
                measureChildWithMargins(view, 0, 0);

                int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
                int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
                layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 5,
                        widthSpace / 2 + getDecoratedMeasuredWidth(view),
                        heightSpace / 5 + getDecoratedMeasuredHeight(view));

                if (position == ItemConfig.DEFAULT_SHOW_ITEM) {
                    view.setScaleX(1 - (position - 1) * ItemConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - (position - 1) * ItemConfig.DEFAULT_SCALE);
                    view.setTranslationY((position - 1) * view.getMeasuredHeight() / ItemConfig.DEFAULT_TRANSLATE_Y);
                } else if (position > 0) {
                    view.setScaleX(1 - position * ItemConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - position * ItemConfig.DEFAULT_SCALE);
                    view.setTranslationY(position * view.getMeasuredHeight() / ItemConfig.DEFAULT_TRANSLATE_Y);
                } else {
                    view.setOnTouchListener(mOnTouchListener);
                }
            }
        } else {
            for (int position = getItemCount() - 1; position >= 0; position--) {
                final View view = recycler.getViewForPosition(position);
                addView(view);
                measureChildWithMargins(view, 0, 0);

                int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
                int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);
                layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 5,
                        widthSpace / 2 + getDecoratedMeasuredWidth(view),
                        heightSpace / 5 + getDecoratedMeasuredHeight(view));

                if (position > 0) {
                    view.setScaleX(1 - position * ItemConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - position * ItemConfig.DEFAULT_SCALE);
                    view.setTranslationY(position * view.getMeasuredHeight() / ItemConfig.DEFAULT_TRANSLATE_Y);
                } else {
                    view.setOnTouchListener(mOnTouchListener);
                }
            }
        }
    }

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(v);
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                mItemTouchHelper.startSwipe(childViewHolder);
            }
            return false;
        }
    };

}
