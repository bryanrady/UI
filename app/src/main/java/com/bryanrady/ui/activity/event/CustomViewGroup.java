package com.bryanrady.ui.activity.event;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class CustomViewGroup extends LinearLayout {

    public CustomViewGroup(Context context) {
        this(context,null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context,attrs, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //是否禁用父控件拦截事件功能    true禁用父控件拦截事件功能、false不禁用父控件拦截事件功能
        //主要的效果就是让控件的父控件不要调用onInterceptTouchEvent()方法,不要拦截事件,
        // 这样子控件就能拿到所有的事件,然后根据自己的逻辑进行处理
        //requestDisallowInterceptTouchEvent(true);
        /**
         *
         * requestDisallowInterceptTouchEvent注意：
         *
         * 1.在ViewGroup的事件分发中，每次只要DOWN事件发生的时候,都会通过resetTouchState()会做一次清除设置的处理,
         *  即mGroupFlags被设置为不等于FLAG_DISALLOW_INTERCEPT，可以理解成把disallowIntercept重置为false。
         *  所以在DOWN事件之前调用requestDisallowInterceptTouchEvent()是没有意义的,一般我们都是在子View里面收到DOWN
         *  事件后请求父控件不要拦截接下来的事件。
         *
         * 2.就算是调用了requestDisallowInterceptTouchEvent()，父控件的DOWN事件也是一定会走onInterceptTouchEvent()的,
         *  所以想要父控件不要拦截，那么父控件的onInterceptTouchEvent()在DOWN事件的时候一定要返回false,表示不要
         *  拦截DOWN事件
         *
         */
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 平时开发过程中可能会有这种需求：在触碰的时候，按下那下（ACTION_DOWN）需要子View进行事件消费，但在滑
     *  动（ACTION_MOVE）或者抬起（ACTION_UP）的时候需要父布局进行拦截操作。
     *  requestDisallowInterceptTouchEvent()
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /**
         * 这里只拦截 滑动和抬起事件
         */
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                return false;
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_UP:
//                return true;
//        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                Log.d("wangqingbin", "You down layout");
//                return true;
//            case MotionEvent.ACTION_UP:
//                Log.d("wangqingbin", "You up layout");
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                Log.d("wangqingbin", "You move layout");
//                return true;
//        }
        return super.onTouchEvent(event);
    }
}
