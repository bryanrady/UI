package com.bryanrady.ui.view.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.bryanrady.ui.R;

public class LinearCenterProgressBar extends LinearBaseProgressBar {

    private Paint mBoxPaint = new Paint();
    private int mBoxWidth;
    private int mBoxRadius;

    public LinearCenterProgressBar(Context context) {
        this(context, null);
    }

    public LinearCenterProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearCenterProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinearCenterProgressBar);
        mBoxWidth = typedArray.getDimensionPixelOffset(R.styleable.LinearCenterProgressBar_box_width, -1);
        mBoxRadius = typedArray.getDimensionPixelOffset(R.styleable.LinearCenterProgressBar_box_radius, -1);
        typedArray.recycle();
    }

    @Override
    public void init() {
        mBoxPaint.setAntiAlias(true);
        mBoxPaint.setColor(progressPaint.getColor());
        if(mBoxWidth == -1){
            mBoxWidth = height*3/2;
        }
        if(mBoxRadius == -1){
            mBoxRadius = height/2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //1. 获取当前进度
        int outWidth = (int)(progress/maxProgress*width); //计算当前进度距离
        if(outWidth >= width - mBoxWidth){
            outWidth = (width - mBoxWidth);
        }

        int top = (height-progressSize)/2;
        //进度条当前长度
        canvas.drawRoundRect(new RectF(0, top, width, top+progressSize), radius, radius, progressBgPaint);
        canvas.drawRoundRect(new RectF(0, top, outWidth + mBoxWidth/2, top+progressSize), radius, radius, progressPaint);

        drawBox(canvas, outWidth);
        drawText(canvas, outWidth);
    }

    /**
     * @param canvas
     * @param left 左边距离
     */
    public void drawBox(Canvas canvas, int left){
        RectF rectF = new RectF(left, 0, left + mBoxWidth, height); // 设置个新的长方形
        canvas.drawRoundRect(rectF, mBoxRadius, mBoxRadius, mBoxPaint); //第二个参数是x半径，第三个参数是y半径
    }

    public void drawText(Canvas canvas, int left){
        canvas.drawText(text, left + mBoxWidth/2 - getTextRect(text).width()/2, getBaseline(textPaint), textPaint);
    }

    public Paint getBoxPaint() {
        return mBoxPaint;
    }

    public void setBoxPaint(Paint boxPaint) {
        this.mBoxPaint = boxPaint;
    }

    public int getBoxWidth() {
        return mBoxWidth;
    }

    public void setBoxWidth(int boxWidth) {
        this.mBoxWidth = boxWidth;
        invalidate();
    }

    public int getBoxRadius() {
        return mBoxRadius;
    }

    public void setBoxRadius(int boxRadius) {
        this.mBoxRadius = boxRadius;
        invalidate();
    }
}
