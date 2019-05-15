package com.bryanrady.ui.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.custom.behavior.Behavior;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 现在来模拟 CoordinatorLayout内部实现机制 因为CoordinatorLayout只支持24版本以上的
 *
 * CoordinatorLayout 使用的是NestedScrollingParent2，但是兼容低版本的话 NestedScrollingParent 就够了
 *
 */

public class BehaviorCoordinatorLayout extends RelativeLayout implements NestedScrollingParent,ViewTreeObserver.OnGlobalLayoutListener{

    public BehaviorCoordinatorLayout(Context context) {
        super(context);
    }

    public BehaviorCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BehaviorCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 事件分发
     *  ViewGroup.addView-->ViewGroup.addViewInner->child的LayoutParam是由父类的generateLayoutParams 生成
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(),attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        for(int i=0;i<getChildCount();i++){
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            Behavior behavior = layoutParams.getBehavior();
            if(behavior != null){
                behavior.onLayoutFinish(this,child);
            }
        }
    }

    /**
     * 当子view的调用NestedScrollingChild的方法startNestedScroll时,会调用该方法.一定要返回true，
     * 该方法决定了当前控件是否能接收到其内部View(非并非是直接子View)滑动时的参数；
     * @param child
     * @param target
     * @param nestedScrollAxes
     * @return
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    /**
     * 滑动结束后调用
     * @param child
     */
    @Override
    public void onStopNestedScroll(View child) {

    }

    /**
     * 滑动时调用
     * @param target        触发嵌套滚动的view
     * @param dxConsumed    表示target已经滑动的x方向的距离
     * @param dyConsumed    表示target已经滑动的x方向的距离
     * @param dxUnconsumed  表示x方向剩下的滑动距离
     * @param dyUnconsumed  表示y方向剩下的滑动距离
     */
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d("wangqingbin","BehaviorCoordinatorLayout onNestedScroll ......");
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
            Behavior behavior = layoutParams.getBehavior();
            if (behavior != null) {
                behavior.onNestedScroll(this,child ,target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
            }
        }
    }

    /**
     * 如果onStartNestedScroll方法返回的是true的话,那么紧接着就会调用该方法.
     * 它是让嵌套的滑动子View在开始滚动之前,让布局容器(viewGroup)或者它的父类执行一些配置的初始化的.
     * @param child
     * @param target
     * @param axes
     */
    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {

    }

    /**
     *
     * @param target    触发嵌套滚动的view
     * @param dx        表示target本次滚动产生的x方向的滚动总距离
     * @param dy        表示target本次滚动产生的y方向的滚动总距离
     * @param consumed  表示父布局要消费的滚动距离,consumed[0]和consumed[1]分别表示父布局在x和y方向上消费的距离.
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
    }

    /**
     * 当一个嵌套的滑动子View触发Fling时调用
     * @param target
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    /**
     *  当一个嵌套的滑动子View Fling(惯性滑动)时调用
     * onNestedFling你可以捕获对内部View的fling事件，如果return true则表示拦截掉内部View的事件
     * @param target
     * @param velocityX
     * @param velocityY
     * @param consumed
     * @return
     */
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return  false;
    }


    /**
     * 这里自定义自己的LayoutParams 和 CoordinatorLayout的实现原理是一样的
     */
    public static class LayoutParams extends RelativeLayout.LayoutParams{

        private Behavior mBehavior;

        public Behavior getBehavior() {
            return mBehavior;
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.BehaviorCoordinatorLayout);
            boolean hasLayoutBehaviorValue = a.hasValue(R.styleable.BehaviorCoordinatorLayout_layout_behavior);
            if(hasLayoutBehaviorValue) {
                String className = a.getString(R.styleable.BehaviorCoordinatorLayout_layout_behavior);
                Log.d("wangqingbin","className =="+className);
                mBehavior = parseBehavior(c, attrs,className);
            }
            a.recycle();
        }

        /**
         * 通过反射获取到Behavior的实例
         * @param context
         * @param attrs
         * @param className
         * @return
         */
        private Behavior parseBehavior(Context context, AttributeSet attrs, String className) {
            try {
                Class<?> aClass = Class.forName(className,true,context.getClassLoader());
                //获取到context,attrs的构造函数
                Constructor<?> constructor = aClass.getConstructor(new Class[]{Context.class, AttributeSet.class});
                constructor.setAccessible(true);
                Behavior behavior = (Behavior) constructor.newInstance(context,attrs);
                Log.d("wangqingbin","behavior =="+behavior);
                return behavior;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return null;
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(RelativeLayout.LayoutParams source) {
            super(source);
        }
    }
}
