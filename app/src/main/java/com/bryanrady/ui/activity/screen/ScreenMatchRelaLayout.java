package com.bryanrady.ui.activity.screen;

import android.content.Context;
import android.graphics.Canvas;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2018/6/7.
 */

public class ScreenMatchRelaLayout extends RelativeLayout {

    public ScreenMatchRelaLayout(Context context) {
        super(context);
    }

    public ScreenMatchRelaLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenMatchRelaLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    static boolean isFlag = true;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if(isFlag){
            int count = this.getChildCount();
            float scaleX =  UIUtils.getInstance(this.getContext()).getHorizontalScaleValue();
            float scaleY =  UIUtils.getInstance(this.getContext()).getVerticalScaleValue();

            Log.i("wangqingbin","x系数:"+scaleX);
            Log.i("wangqingbin","y系数:"+scaleY);
            for (int i = 0;i < count;i++){
                View child = this.getChildAt(i);

                //代表的是当前空间的所有属性列表
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                layoutParams.width = (int) (layoutParams.width * scaleX);
                layoutParams.height = (int) (layoutParams.height * scaleY);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * scaleX);
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * scaleX);
                layoutParams.topMargin = (int) (layoutParams.topMargin * scaleY);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * scaleY);
            }
            isFlag = false;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
