package com.bryanrady.ui.view.canvas;

import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

/**
 * Created by user on 2017/5/20.
 */

public class CustomSearchViewController implements CustomSearchView.OnDetachedViewListener {

    private CustomSearchView mCustomSearchView;

    // 动画默认执行时间
    public final static int DEFAULT_DURATION = 600;

    public float mAnimatedValue;

    public void setSearchView(CustomSearchView view) {
        // 将CustomSearchView和Controller互相绑定
        mCustomSearchView = view;
        mCustomSearchView.addController(this);
        mCustomSearchView.setOnDetachedViewListener(this);
    }

    public void open() {
        // 开始执行动画
        startAnimation(CustomSearchView.ANIMA_STATE_OPEN);
    }

    public void close() {
        startAnimation(CustomSearchView.ANIMA_STATE_CLOSE);
    }

    private void startAnimation(@CustomSearchView.AnimaState int state) {
        // 开始执行动画
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1.0f);
        animator.setDuration(DEFAULT_DURATION);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatedValue = (float) animation.getAnimatedValue();
                if (mCustomSearchView != null) {
                    mCustomSearchView.invalidate();
                }
            }
        });
        if (mCustomSearchView != null) {
            mCustomSearchView.setAnimaState(state);
        }
        animator.start();
    }

    @Override
    public void onDetachedView() {
        if (mCustomSearchView != null) {
            mCustomSearchView = null;
        }
    }
}
