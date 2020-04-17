package com.bryanrady.ui.activity.event;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * https://www.jianshu.com/p/38015afcdb58   Android事件分发机制详解：史上最全面、最易懂
 *
 *      1.1 事件分发的对象是谁？
 *          点击事件（Touch事件）,当用户触摸到屏幕时会产生Touch事件, Touch事件的相关细节（发生触摸的位置、时间等）被封装成MotionEvent事件类型对象
 *
 *          事件类型（4种）                具体动作
 *          MotionEvent.ACTION_DOWN     按下View（所有事件的开始）
 *          MotionEvent.ACTION_UP       抬起View（与DOWN对应）
 *          MotionEvent.ACTION_MOVE     滑动View
 *          MotionEvent.ACTION_CANCEL   结束事件（非人为原因）
 *
 *          特别说明：事件列 从手指接触屏幕 至 手指离开屏幕，这个过程产生的一系列事件
 *                注：一般情况下，事件列都是以DOWN事件开始、UP事件结束，中间有无数的MOVE事件
 *
 *          即当一个点击事件（MotionEvent ）产生后，系统需把这个事件传递给一个具体的 View 去处理。
 *
 *      1.2 事件分发机制的本质
 *          将点击事件（MotionEvent）传递到某个具体的View并且处理这个事件的整个过程     即 事件传递的过程 = 分发过程。
 *
 *      1.3 事件在哪些对象之间进行传递
 *          Activity、ViewGroup、View
 *
 *      1.4 事件分发(传递)的顺序
 *          Activity -> ViewGroup -> View
 *          即：1个点击事件发生后，事件先传到Activity、再传到ViewGroup、最终再传到 View
 *
 *      1.5 事件分发过程由哪些方法协作完成？
 *              dispatchTouchEvent()、onInterceptTouchEvent() 和 onTouchEvent()
 *
 *              方法                          作用                          调用时刻
 *          dispatchTouchEvent()        分发（传递）点击事件              当点击事件能够传递给当前View时，该方法就会被调用
 *
 *          onTouchEvent()              处理点击事件                      在dispatchTouchEvent()方法内部进行调用
 *
 *                                      判断是否拦截了某个事件
 *          onInterceptTouchEvent()    (这个方法只存在于ViewGroup        在ViewGroup中的dispatchTouchEvent() 内部调用
 *                                      中，View里面没有这个方法)
 *
 *
 *
 *
 *       请谨记：   Android事件分发流程 = Activity -> ViewGroup -> View
 *
 *           即： 1个点击事件发生后，事件先传到Activity、再传到ViewGroup、最终再传到 View
 *
 *           从上可知，要想充分理解Android分发机制，本质上是要理解：
 *
 *                  Activity对点击事件的分发机制、ViewGroup对点击事件的分发机制、View对点击事件的分发机制
 *
 *              即：Activity事件分发机制、ViewGroup事件分发机制、View事件分发机制
 *
 *      源码分析是基于 5.0 之前的
 */

public class DispatchEventActivity extends StatusBarBaseActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_event);
        CustomViewGroup customViewGroup = findViewById(R.id.customViewGroup);
        CustomButton customButton = findViewById(R.id.customButton);

        customViewGroup.setClickable(true);
        customButton.setClickable(true);

//        customViewGroup.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.d("wangqingbin","CustomViewGroup onTouch.....");
//                return false;
//            }
//        });
        customViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("wangqingbin","CustomViewGroup onClick.....");
            }
        });
//        customButton.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.d("wangqingbin","CustomButton onTouch.....");
//                return false;
//            }
//        });
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("wangqingbin","CustomButton onClick.....");
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

}
