package com.bryanrady.ui.view.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * 正方形进度条
 */
public abstract class SquareProgressBar extends BaseProgressBar {

    protected int defaultWidth = 100; //默认宽高，单位sp

    public SquareProgressBar(Context context) {
        super(context);
    }

    public SquareProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //取默认值
        width = sp2px(defaultWidth);
        height = sp2px(defaultWidth);
        //1. 获取宽
        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) { //具体值
            width = MeasureSpec.getSize(widthMeasureSpec);
        }
        //2.获取高
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) { //具体值
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        //3. 确定宽高(保持宽高一致)
        width = height = (width > height ? height : width);
        setMeasuredDimension(width, height);
    }

    /**
     * 文字居中
     * @param canvas
     */
    protected void drawText(Canvas canvas) {
        canvas.drawText(text, width/2 - getTextRect(text).width()/2, getBaseline(textPaint), textPaint);
    }
}
