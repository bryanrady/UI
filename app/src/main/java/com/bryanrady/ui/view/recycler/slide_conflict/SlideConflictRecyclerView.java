package com.bryanrady.ui.view.recycler.slide_conflict;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 解决RecyclerView嵌套滑动冲突的问题
 * Created by Administrator on 2019/5/14.
 */

public class SlideConflictRecyclerView extends RecyclerView {

    private final int INVALID_POINTER = -1;
    private int mScrollPointerId = INVALID_POINTER;

    private int mTouchSlop;
    private int mInitialTouchX;
    private int mInitialTouchY;

    public SlideConflictRecyclerView(@NonNull Context context) {
        this(context,null);
    }

    public SlideConflictRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SlideConflictRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //ViewConfiguration 是系统中关于视图的各种特性的常量记录对象。其中包含各种基础数据
        // https://blog.csdn.net/jjwwmlp456/article/details/68065838
        //getScaledEdgeSlop()   当用户touch在屏幕边缘时，插入一定像素值，以寻找出可触摸内容

        ViewConfiguration vc = ViewConfiguration.get(context);
        mTouchSlop = vc.getScaledEdgeSlop();
    }

    //这段代码是从源码中来的 只需要改一下下面的判断条件就行了
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (e == null) {
            return false;
        }
        int action = MotionEventCompat.getActionMasked(e);
        int actionIndex = MotionEventCompat.getActionIndex(e);
        switch (action) {
            case MotionEvent.ACTION_DOWN :
                mScrollPointerId = MotionEventCompat.getPointerId(e, 0);
                mInitialTouchX = Math.round(e.getX() + 0.5f);
                mInitialTouchY = Math.round(e.getY() + 0.5f);
                return super.onInterceptTouchEvent(e);
            case MotionEvent.ACTION_POINTER_DOWN:
                mScrollPointerId = MotionEventCompat.getPointerId(e, actionIndex);
                mInitialTouchX = Math.round(MotionEventCompat.getX(e, actionIndex) + 0.5f);
                mInitialTouchY = Math.round(MotionEventCompat.getY(e, actionIndex) + 0.5f);
                return super.onInterceptTouchEvent(e);
            case MotionEvent.ACTION_MOVE:
                int index = MotionEventCompat.findPointerIndex(e, mScrollPointerId);
                if (index < 0) {
                    return false;
                }
                int x = Math.round(MotionEventCompat.getX(e, index) + 0.5f);
                int y = Math.round(MotionEventCompat.getY(e, index) + 0.5f);
                if (getScrollState() != SCROLL_STATE_DRAGGING ) {
                    int dx = x - mInitialTouchX;
                    int dy = y - mInitialTouchY;
                    boolean startScroll = false;

                    if(getLayoutManager().canScrollHorizontally() && Math.abs(dy) > mTouchSlop
                            && ( Math.abs(dx) > Math.abs(dy))) {
                        startScroll = true;
                    }
                    if(getLayoutManager().canScrollVertically() && Math.abs(dy) > mTouchSlop
                            && ( Math.abs(dy) > Math.abs(dx))) {
                        startScroll = true;
                    }

                    //如下条件，结合成一个条件， 前者条件已经是判断未纵向移动了，那么后面补上横向移动就行
//                    if(getLayoutManager().canScrollVertically()
//                            && Math.abs(dy) > mTouchSlop
//                            && (getLayoutManager().canScrollHorizontally() || Math.abs(dy) > Math.abs(dx))) {
//                        startScroll = true;
//                    }
                    return startScroll && super.onInterceptTouchEvent(e);
                }
                return super.onInterceptTouchEvent(e);
            default:
                return super.onInterceptTouchEvent(e);
        }
    }
}
