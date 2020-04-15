package com.bryanrady.ui.activity.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class CustomButton extends Button {

    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //请求父控件是否要拦截事件 true拦截、false不拦截
        //getParent().requestDisallowInterceptTouchEvent(true);
    }


//    /**
//     * 源码分析：View.dispatchTouchEvent（）
//     */
//    public boolean dispatchTouchEvent(MotionEvent event) {
//
//        if (mOnTouchListener != null && (mViewFlags & ENABLED_MASK) == ENABLED &&
//                mOnTouchListener.onTouch(this, event)) {
//            return true;
//        }
//        return onTouchEvent(event);
//    }
//    // 说明：只有以下3个条件都为真，dispatchTouchEvent()才返回true；否则执行onTouchEvent()
//    //     1. mOnTouchListener != null
//    //     2. (mViewFlags & ENABLED_MASK) == ENABLED
//    //     3. mOnTouchListener.onTouch(this, event)
//    // 下面对这3个条件逐个分析
//
//
//    /**
//     * 条件1：mOnTouchListener != null
//     * 说明：mOnTouchListener变量在View.setOnTouchListener（）方法里赋值
//     */
//    public void setOnTouchListener(OnTouchListener l) {
//
//        mOnTouchListener = l;
//        // 即只要我们给控件注册了Touch事件，mOnTouchListener就一定被赋值（不为空）
//
//    }
//
///**
// * 条件2：(mViewFlags & ENABLED_MASK) == ENABLED
// * 说明：
// *     a. 该条件是判断当前点击的控件是否enable
// *     b. 由于很多View默认enable，故该条件恒定为true
// */
//
///**
// * 条件3：mOnTouchListener.onTouch(this, event)
// * 说明：即 回调控件注册Touch事件时的onTouch（）；需手动复写设置，具体如下（以按钮Button为例）
// */
//    button.setOnTouchListener(new OnTouchListener() {
//        @Override
//        public boolean onTouch(View v, MotionEvent event) {
//
//            return false;
//        }
//    });
//    // 若在onTouch（）返回true，就会让上述三个条件全部成立，从而使得View.dispatchTouchEvent（）直接返回true，事件分发结束
//    // 若在onTouch（）返回false，就会使得上述三个条件不全部成立，从而使得View.d

}
