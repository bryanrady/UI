package com.bryanrady.ui.view.custom.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bryanrady.ui.view.custom.BehaviorCoordinatorLayout;

/**
 * Created by Administrator on 2019/5/15.
 */

public class ToolBarBehavior extends Behavior {

    private final int mMaxHeight = 600;

    public ToolBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Toolbar在滚动的时候进行透明度变换
     * @param parent
     * @param child Toolbar
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(BehaviorCoordinatorLayout parent, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(parent, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d("wangqingbin", "ToolBarBehavior onNestedScroll: dyConsumed == "+ dyConsumed);
        Log.d("wangqingbin", "ToolBarBehavior target.getScrollY() == "+ target.getScrollY());
        if(target.getScrollY() <= mMaxHeight) {
            child.setAlpha(target.getScrollY() * 1.0f/mMaxHeight);
        }else if(target.getScrollY() == 0) {
            child.setAlpha(0);
        }
    }
}
