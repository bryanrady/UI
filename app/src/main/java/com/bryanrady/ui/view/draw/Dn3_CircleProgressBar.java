package com.bryanrady.ui.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wqb on 2018/6/25.
 */

public class Dn3_CircleProgressBar extends View {

    private Paint mPaint;
    private int mMax = 100;
    private int mProgress;

    public Dn3_CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircleProgressBar(canvas);
    }

    private void drawCircleProgressBar(Canvas canvas) {
        //1.画背景圆环
        int center = getWidth()/2;
        int radius = 200;
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(15);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(center,center,radius,mPaint);

        //2.画进度百分比
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(20);
        mPaint.setStrokeWidth(0);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        int percent = mProgress/mMax * 100;
        String strPercent = percent + "%";
        Paint.FontMetricsInt fm = new Paint.FontMetricsInt();
        if(percent != 0){
            canvas.drawText(strPercent, getWidth() / 2 - mPaint.measureText(strPercent) / 2 ,
                    getWidth() / 2  +(fm.bottom - fm.top)/2 - fm.bottom, mPaint);
        }

        //3.画圆弧
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(15);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        RectF rectF = new RectF(
                center - radius,
                center - radius,
                center + radius,
                center + radius);

        canvas.drawArc(rectF,0,mProgress/mMax * 360,false, mPaint);
    }

    public void setProgress(int progress){
        if(progress < 0){
            throw new IllegalArgumentException("进度Progress不能小于0");
        }
        if(progress > mMax){
            mProgress = mMax;
        }else{
            mProgress = progress;
            postInvalidate();
        }
    }

}
