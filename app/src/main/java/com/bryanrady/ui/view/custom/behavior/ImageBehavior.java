package com.bryanrady.ui.view.custom.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bryanrady.ui.view.custom.BehaviorCoordinatorLayout;

/**
 * Created by Administrator on 2019/5/15.
 */

public class ImageBehavior extends Behavior {

    private final int mMaxHeight = 600;
    private int mOriginHeight;

    public ImageBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onLayoutFinish(BehaviorCoordinatorLayout parent, View child) {
        super.onLayoutFinish(parent, child);
        if (mOriginHeight == 0) {
            mOriginHeight = child.getHeight();
        }
    }

    /**
     * 图片动态的改变高度
     * @param parent
     * @param child ImageView
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     */
    @Override
    public void onNestedScroll(BehaviorCoordinatorLayout parent, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(parent, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d("wangqingbin", "ImageBehavior onNestedScroll: dyConsumed == "+ dyConsumed);
        Log.d("wangqingbin", "ImageBehavior target.getScrollY() == "+ target.getScrollY());
        BehaviorCoordinatorLayout.LayoutParams layoutParams = (BehaviorCoordinatorLayout.LayoutParams) child.getLayoutParams();
        if(target.getScrollY() > 0) {           //上滑
            layoutParams.height = layoutParams.height - Math.abs(dyConsumed);
            if (layoutParams.height < mOriginHeight) {
                layoutParams.height = mOriginHeight;
            }
        }else if(target.getScrollY() == 0) {    //下滑
            layoutParams.height = layoutParams.height + Math.abs(dyUnconsumed);
            if(layoutParams.height >= mMaxHeight){
                layoutParams.height = mMaxHeight;
            }
        }
        child.setLayoutParams(layoutParams);
    }
}
