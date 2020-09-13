package com.bryanrady.ui.view.progress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.bryanrady.ui.R;

public class LinearBottomProgressBar extends LinearBaseProgressBar {

    private Paint mBoxPaint = new Paint();
    private int mBoxWidth;
    private int mBoxHeight;
    private int mBoxRadius;

    public LinearBottomProgressBar(Context context) {
        this(context, null);
    }

    public LinearBottomProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LinearBottomProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LinearBottomProgressBar);
        mBoxWidth = typedArray.getDimensionPixelOffset(R.styleable.LinearBottomProgressBar_box_width, -1);
        mBoxHeight = typedArray.getDimensionPixelOffset(R.styleable.LinearBottomProgressBar_box_height, -1);
        mBoxRadius = typedArray.getDimensionPixelOffset(R.styleable.LinearBottomProgressBar_box_radius, -1);
        typedArray.recycle();
    }

    @Override
    public void init() {
        mBoxPaint.setAntiAlias(true);
        mBoxPaint.setColor(progressPaint.getColor());

        if(progressSize == height){
            progressSize = height/5 * 2;
        }

        if(mBoxHeight == -1){
            mBoxHeight = height/5 * 2;
        }

        if(mBoxWidth == -1){
            mBoxWidth = mBoxHeight * 2;
        }
        if(mBoxRadius == -1){
            mBoxRadius = dp2px(5);
        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        pathIn.reset();
        pathOut.reset();

        //1. 获取当前进度
        int outWidth = (int)(progress/maxProgress * (width - mBoxWidth)); //计算当前进度距离
        int top = height-progressSize;
        //进度条当前长度
//        canvas.drawRoundRect(new RectF(boxWidth/2, top, width-boxWidth/2, top+progressSize), radius, radius, progressBgPaint);
//        canvas.drawRoundRect(new RectF(boxWidth/2, top, outWidth+boxWidth/2, top+progressSize), progressRadius, progressRadius, progressPaint);

        refreshRadius();
        pathIn.addRoundRect(new RectF(mBoxWidth/2, top, width - mBoxWidth/2, top + progressSize), floatsIn, Path.Direction.CW);
        pathOut.addRoundRect(new RectF(mBoxWidth/2, top, outWidth + mBoxWidth/2, top + progressSize), floatsOut, Path.Direction.CW);
        pathOut.op(pathIn, Path.Op.INTERSECT); //交集
        canvas.drawPath(pathIn, progressBgPaint);
        canvas.drawPath(pathOut, progressPaint);

        drawBox(canvas, outWidth);
        drawText(canvas, outWidth);
    }

    /**
     * @param canvas
     * @param left 左边距离
     */
    public void drawBox(Canvas canvas, int left){
        //2.1圆角矩形
        RectF rectF = new RectF(left, 0, left + mBoxWidth, mBoxHeight);
        canvas.drawRoundRect(rectF, mBoxRadius, mBoxRadius, mBoxPaint);

        //2.2 画三角形 (绘制这个三角形,你可以绘制任意多边形)
        Path path = new Path();
        path.moveTo(left + mBoxWidth/2 - sp2px(4), mBoxHeight);// 此点为多边形的起点
        path.lineTo(left + mBoxWidth/2 + sp2px(4), mBoxHeight);
        path.lineTo(left + mBoxWidth/2, height-progressSize-2);
        path.close(); // 使这些点构成封闭的多边形
        canvas.drawPath(path, mBoxPaint);
    }

    public void drawText(Canvas canvas, int left){
        canvas.drawText(text, left + mBoxWidth/2 - getTextRect(text).width()/2, getBaseline(textPaint, mBoxHeight), textPaint);
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
