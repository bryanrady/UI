package com.bryanrady.ui.activity.event;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_event);
        final CustomViewGroup customViewGroup = findViewById(R.id.customViewGroup);
        CustomButton customButton = findViewById(R.id.customButton);
        Button btn_dispatch_event = findViewById(R.id.btn_dispatch_event);

        customViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DispatchEventActivity.this,"CustomViewGroup 被点击了..",Toast.LENGTH_SHORT).show();
                Log.d("wangqingbin","CustomViewGroup 被点击了..");
            }
        });
        customButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DispatchEventActivity.this,"CustomButton 被点击了..",Toast.LENGTH_SHORT).show();
                Log.d("wangqingbin","CustomButton 被点击了..");
            }
        });

        btn_dispatch_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewGroup.setInterceptTouchEvent(true);
                Toast.makeText(DispatchEventActivity.this,"在CustomViewGroup中设置了拦截事件..",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Activity.dispatchTouchEvent（） 中的第一个方法 空方法
     * 作用：实现屏保功能
     * 当此activity在栈顶时，触屏点击按home，back，menu键等都会触发此方法
     */
    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.d("wangqingbin","onUserInteraction().............");
    }


//    /**
//     * 源码分析：Activity.dispatchTouchEvent（）
//     */
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        // 一般事件列开始都是DOWN事件 = 按下事件，故此处基本是true
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//
//            onUserInteraction();
//            // ->>分析1
//
//        }
//
//        // ->>分析2
//        if (getWindow().superDispatchTouchEvent(ev)) {
//
//            return true;
//            // 若getWindow().superDispatchTouchEvent(ev)的返回true
//            // 则Activity.dispatchTouchEvent（）就返回true，则方法结束。即 ：该点击事件停止往下传递 & 事件传递过程结束
//            // 否则：继续往下调用Activity.onTouchEvent
//
//        }
//        // ->>分析4
//        return onTouchEvent(ev);
//    }
//
//
//    /**
//     * 分析1：onUserInteraction()
//     * 作用：实现屏保功能
//     * 注：
//     *    a. 该方法为空方法
//     *    b. 当此activity在栈顶时，触屏点击按home，back，menu键等都会触发此方法
//     */
//    public void onUserInteraction() {
//
//    }
//    // 回到最初的调用原处
//
//    /**
//     * 分析2：getWindow().superDispatchTouchEvent(ev)
//     * 说明：
//     *     a. getWindow() = 获取Window类的对象
//     *     b. Window类是抽象类，其唯一实现类 = PhoneWindow类；即此处的Window类对象 = PhoneWindow类对象
//     *     c. Window类的superDispatchTouchEvent() = 1个抽象方法，由子类PhoneWindow类实现
//     */
//    @Override
//    public boolean superDispatchTouchEvent(MotionEvent event) {
//
//        return mDecor.superDispatchTouchEvent(event);
//        // mDecor = 顶层View（DecorView）的实例对象
//        // ->> 分析3
//    }
//
//    /**
//     * 分析3：mDecor.superDispatchTouchEvent(event)
//     * 定义：属于顶层View（DecorView）
//     * 说明：
//     *     a. DecorView类是PhoneWindow类的一个内部类
//     *     b. DecorView继承自FrameLayout，是所有界面的父类
//     *     c. FrameLayout是ViewGroup的子类，故DecorView的间接父类 = ViewGroup
//     */
//    public boolean superDispatchTouchEvent(MotionEvent event) {
//
//        return super.dispatchTouchEvent(event);
//        // 调用父类的方法 = ViewGroup的dispatchTouchEvent()
//        // 即 将事件传递到ViewGroup去处理，详细请看ViewGroup的事件分发机制
//
//    }
//    // 回到最初的调用原处
//
//    /**
//     * 分析4：Activity.onTouchEvent（）
//     * 定义：属于顶层View（DecorView）
//     * 说明：
//     *     a. DecorView类是PhoneWindow类的一个内部类
//     *     b. DecorView继承自FrameLayout，是所有界面的父类
//     *     c. FrameLayout是ViewGroup的子类，故DecorView的间接父类 = ViewGroup
//     */
//    public boolean onTouchEvent(MotionEvent event) {
//
//        // 当一个点击事件未被Activity下任何一个View接收 / 处理时
//        // 应用场景：处理发生在Window边界外的触摸事件
//        // ->> 分析5
//        if (mWindow.shouldCloseOnTouch(this, event)) {
//            finish();
//            return true;
//        }
//
//        return false;
//        // 即 只有在点击事件在Window边界外才会返回true，一般情况都返回false，分析完毕
//    }
//
//    /**
//     * 分析5：mWindow.shouldCloseOnTouch(this, event)
//     */
//    public boolean shouldCloseOnTouch(Context context, MotionEvent event) {
//        // 主要是对于处理边界外点击事件的判断：是否是DOWN事件，event的坐标是否在边界内等
//        if (mCloseOnTouchOutside && event.getAction() == MotionEvent.ACTION_DOWN
//                && isOutOfBounds(context, event) && peekDecorView() != null) {
//            return true;
//        }
//        return false;
//        // 返回true：说明事件在边界外，即 消费事件
//        // 返回false：未消费（默认）
//    }
//// 回到分析4调用原处


}
