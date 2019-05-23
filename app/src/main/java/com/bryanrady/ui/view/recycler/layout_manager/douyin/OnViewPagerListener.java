package com.bryanrady.ui.view.recycler.layout_manager.douyin;

public interface OnViewPagerListener {

    /**
     * 布局完成的监听
     */
    void onLayoutComplete();

    /**
     * 释放的监听    暂停播放操作
     * @param position
     */
    void onPageRelease(int position,boolean isNext);

    /**
     * 播放操作
     * @param position  选中的Item的位置
     * @param isBottom 判断手指是否到达底部
     */
    void onPageSelected(int position, boolean isBottom);


}
