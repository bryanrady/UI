package com.bryanrady.ui.view.custom.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bryanrady.ui.view.custom.BehaviorCoordinatorLayout;

/**
 *  Behavior作为一个中间者，目的是协调 子控件之间的触摸事件，全局视图改变事件, 滑动事件
 *  这里模拟了 CoordinatorLayout.Behavior
 * Created by Administrator on 2019/5/15.
 */

public abstract class Behavior {

    public Behavior(Context context, AttributeSet attrs){
    }

    public boolean onMeasureChild(BehaviorCoordinatorLayout parent, View child,
                                  int parentWidthMeasureSpec, int widthUsed,
                                  int parentHeightMeasureSpec, int heightUsed) {
        return false;
    }

    public boolean onLayoutChild(BehaviorCoordinatorLayout parent, View child, int layoutDirection) {
        return false;
    }

    public void onLayoutFinish(BehaviorCoordinatorLayout parent, View child) {
    }

    public boolean onInterceptTouchEvent(BehaviorCoordinatorLayout parent, View child, MotionEvent ev) {
        return false;
    }

    public boolean onTouchEvent(BehaviorCoordinatorLayout parent, View child, MotionEvent ev) {
        return false;
    }

    public boolean onStartNestedScroll(BehaviorCoordinatorLayout parent, View child, View target, int nestedScrollAxes) {
        return true;
    }

    public void onStopNestedScroll(BehaviorCoordinatorLayout parent, View child, View target) {
    }

    public void onNestedScroll(BehaviorCoordinatorLayout parent, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d("wangqingbin","Behavior onNestedScroll .............");
    }

    public void onNestedScrollAccepted(BehaviorCoordinatorLayout parent, View child, View target, int axes) {
    }

    public void onNestedPreScroll(BehaviorCoordinatorLayout parent, View child, View target, int dx, int dy, int[] consumed) {
    }

    public boolean onNestedPreFling(BehaviorCoordinatorLayout parent, View child, View target, float velocityX, float velocityY) {
        return false;
    }

    public boolean onNestedFling(BehaviorCoordinatorLayout parent, View child, View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

}
