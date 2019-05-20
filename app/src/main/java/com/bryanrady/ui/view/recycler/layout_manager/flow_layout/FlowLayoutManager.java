package com.bryanrady.ui.view.recycler.layout_manager.flow_layout;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
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
public class FlowLayoutManager extends RecyclerView.LayoutManager {

    /**
     * 所有Item的总高度
     */
    private int mTotalHeight;

    /**
     * 将每个Item的显示区域信息放在Rect中，然后缓存起来
     */
    private SparseArray<Rect> mAllItemFrames = new SparseArray<>();

    /**
     * 滑动偏移量 始终大于0
     * 如果这里滑到最顶部的时候 mOffsetY == 0  往下面滑动的时候 mOffsetY > 0
     */
    private int mOffsetY;

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
        // RecyclerView有两级缓存 第一级是scrap缓存 第二级是recycler回收池

        //        detach   轻量级的移除操作    remove  重量级
        //step1: 先解绑和回收所有的item,将item分离放入scrap缓存中，以准备重新对view进行排版
        detachAndScrapAttachedViews(recycler);

        //step2: 对Item进行测量摆放
        int curWidth = 0;   //当前已经摆放的宽度
        int curHeight = 0;  //当前已经摆放的的高度
        int itemHeight = 0; //每个Item的高度
        
        for (int i=0; i < getItemCount(); i++){
            //step1:通过recycler得到该位置上的View，Recycler负责是否使用旧的还是生成新的View,
            // 然后再把得到的item添加到RecyclerView中，并对Item进行带Margin的测量
            View item = recycler.getViewForPosition(i);
            addView(item);
            measureChildWithMargins(item,0,0);

            //step2: 对每个item测量后宽高，然后计算每个item的显示区域信息Rect
            int width = getDecoratedMeasuredWidth(item);
            int height = getDecoratedMeasuredHeight(item);
            itemHeight = height;

            Rect frame = mAllItemFrames.get(i);
            if(frame == null){
                frame = new Rect();
            }

            //step3:对每个item进行摆放
            //如果当前已经摆放的的宽度 + 准备要摆放的item的宽度 大于 RecyclerView的宽度
            //说明这个Item只能摆在下一行，所以需要换行
            if(curWidth + width > getWidth()){
                curWidth = width;
                curHeight += height;

                frame.set(0,curHeight,width,curHeight+height);
            } else {    //不需要换行

                frame.set(curWidth,curHeight,curWidth+width,curHeight+height);

                curWidth += width;
            }
            mAllItemFrames.put(i,frame);

        }
        //这个时候的itemHeight 是 最后一个Item的itemHeight
        mTotalHeight = curHeight + itemHeight;

        fillAndRecycler(recycler,state);

    }

    /**
     * 回收不可见的item
     * @param recycler
     * @param state
     */
    private void fillAndRecycler(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) {
            return;
        }
        //先解绑和回收所有的item
        detachAndScrapAttachedViews(recycler);

        //得到当前显示在屏幕上的 也就是可见的RecyclerView的部分item的区域信息
        Rect curVisibleRect = new Rect(0,mOffsetY,getWidth(),mOffsetY + getHeight());

        //将滑出屏幕的(也就是不可见的)Item进行回收
//        for (int i = 0;i < getItemCount(); i++){
//
//            Rect itemFrame = mAllItemFrames.get(i);
//            View item = recycler.getViewForPosition(i);
//
//            if(!Rect.intersects(curVisibleRect,itemFrame)){
//                //如果当前item的Rect不在当前RecyclerView中可见的Rect的时候，那就进行回收
//                removeAndRecycleView(item,recycler);
//
//            }else{
//                //如果当前item在屏幕中进行显示，也就是说是当前可见的,那就重新进行测量摆放
//                measureChildWithMargins(item,0,0);
//                addView(item);
//                layoutDecoratedWithMargins(item,itemFrame.left, itemFrame.top - mOffsetY,
//                        itemFrame.right,itemFrame.bottom - mOffsetY);
//            }
//        }


        //将滑出屏幕的view进行回收
        for (int i=0;i<getChildCount();i++){
            View childView = getChildAt(i);
            Rect child=mAllItemFrames.get(i);
            if (!Rect.intersects(curVisibleRect, child)) {
                removeAndRecycleView(childView, recycler);
            }
        }

        //可见区域出现在屏幕上的子view
        for (int j = 0;j<getItemCount();j++){
            if (Rect.intersects(curVisibleRect,mAllItemFrames.get(j))){
//                scrap回收池里面拿的
                View scrap = recycler.getViewForPosition(j);
                measureChildWithMargins(scrap,0,0);
                addView(scrap);
                Rect frame = mAllItemFrames.get(j);
                layoutDecoratedWithMargins(scrap, frame.left, frame.top - mOffsetY,
                        frame.right, frame.bottom - mOffsetY);
            }

        }

    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        /**
         * 只需要完成下面这三个任务：
         *      1.将所有的子视图移动适当的位置 (对的，你得自己做这个)。
         *      2.决定移动视图后 添加/移除 视图。
         *      3.返回滚动的实际距离。框架会根据它判断你是否触碰到边界。
         *
         *      dy : 竖直方向滚动距离的增量
         *              手指向下滑  dy < 0
         *              手指向上滑  dy > 0
         *
         */

        Log.d("wangqingbin","dy=="+dy);

        //willScroll是实际滑动增量
        int willScroll = dy;

        // 边界值判断
        if (dy < -mOffsetY) {    //dy < 0 手指向下滑动  如果滑动到最顶部
            willScroll = -mOffsetY;
        }else if(dy > mTotalHeight- getHeight() - mOffsetY){    //手指向上滑动 如果滑动到最底部
            willScroll = mTotalHeight - getHeight() - mOffsetY;
        }

        mOffsetY += willScroll;

        //平移容器内的item 这两个方法 可以帮助我们处理匀速移动。如果你不实现它，
        // 你的视图就不会滚动。 移动视图操作完成后，我们触发另一个填充操作， 根据滚动的距离替换视图。
        offsetChildrenVertical(willScroll);

        fillAndRecycler(recycler, state);

        return willScroll;
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }
}
