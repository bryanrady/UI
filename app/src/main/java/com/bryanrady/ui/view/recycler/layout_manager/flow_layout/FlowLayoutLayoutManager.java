package com.bryanrady.ui.view.recycler.layout_manager.flow_layout;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * 自定义LayoutManager实现流式布局
 *
 * LayoutManager作用 ：测量 摆放子控件 滑动 回收池
 *
 *     自定义LayoutManger的步骤:
 *
 *      1.生成子控件的LayoutParams
 *              generateDefaultLayoutParams
 *
 *      2. 计算子控件并进行摆放 （两次 onMeasure onLayout） 初始化 item的摆放
 *              onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state)
 *
 *      3.使RecyclerView能够进行滑动 并处理滑动操作
 *              scrollVerticallyBy(int dx, RecyclerView.Recycler recycler）
 *
 *              canScrollVertically()   return true;
 *
 */
public class FlowLayoutLayoutManager extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        if(getItemCount() <= 0){
            return;
        }
        if(state.isPreLayout()){
            return;
        }



    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }
}
