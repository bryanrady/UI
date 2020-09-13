package com.bryanrady.ui.view.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;

import com.bryanrady.ui.R;

/**
 * 圆弧进度条
 */
public class ArcProgressBarBar extends SquareProgressBar {

    private int mStartAngle = 0; //开始角度
    private int mDrawAngle = 360; //需要绘制的角度
    private int mCurrentAngle = 0; //当前角度

    public ArcProgressBarBar(Context context) {
        this(context, null);
    }

    public ArcProgressBarBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArcProgressBarBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcProgressBarBar);
        mStartAngle = typedArray.getInteger(R.styleable.ArcProgressBarBar_arc_start_angle, 0);
        mDrawAngle = typedArray.getInteger(R.styleable.ArcProgressBarBar_arc_draw_angle, 360);
        typedArray.recycle();
    }

    @Override
    public void init() {
        if(progressSize==height){
            progressSize = dp2px(10);
        }
        blankSpace = dp2px(4);
        refreshLight();

        progressPaint.setStrokeWidth(progressSize); //大小
        progressPaint.setStrokeCap(Paint.Cap.ROUND); // 结束位置圆角
        progressPaint.setStyle(Paint.Style.STROKE); //空心样式

        progressBgPaint.setStrokeWidth(progressSize); //大小
        progressBgPaint.setStrokeCap(Paint.Cap.ROUND); // 结束位置圆角
        progressBgPaint.setStyle(Paint.Style.STROKE); //空心样式
    }

    @Override
    public void setOutGradient(final boolean isProDirection, @ColorInt final int... colors){
        post(new Runnable() {
            @Override
            public void run() {
                int[] colorResArr = new int[colors.length];
                for (int i = 0; i < colors.length; i++) {
                    colorResArr[i] = colors[i];
                }
                SweepGradient gradient = new SweepGradient(width/2, height/2, colorResArr, null);
                progressPaint.setShader(gradient);
            }
        });
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //计算角度 progress/maxProgress = currentAngle/drawAngle;
        mStartAngle = (int)(progress * mDrawAngle / maxProgress);

        int r = progressSize / 2; //圆弧的一半
        RectF rectF = new RectF(r+ blankSpace, r+ blankSpace, width - r- blankSpace, height - r- blankSpace);
//        drawLightCircle(canvas, rectF);
        drawInCircle(canvas, rectF);
        drawOutCircle(canvas, rectF);
        drawText(canvas);
    }

    //发光区域
    private void drawLightCircle(Canvas canvas, RectF rectF) {
        canvas.drawArc(rectF, mStartAngle, mDrawAngle, false, lightPaint);
    }

    //内圆弧
    protected void drawInCircle(Canvas canvas, RectF rectF) {
        canvas.drawArc(rectF, mStartAngle, mDrawAngle, false, progressBgPaint);
    }

    //外圆弧
    protected void drawOutCircle(Canvas canvas, RectF rectF) {
        if (mCurrentAngle > mDrawAngle) {
            mCurrentAngle = mDrawAngle;
        }
        canvas.drawArc(rectF, mStartAngle, mCurrentAngle, false, progressPaint);
    }

    public int getStartAngle() {
        return mStartAngle;
    }

    public void setStartAngle(int startAngle) {
        this.mStartAngle = startAngle;
    }

    public int getDrawAngle() {
        return mDrawAngle;
    }

    public void setDrawAngle(int drawAngle) {
        this.mDrawAngle = drawAngle;
        invalidate();
    }

    public int getCurrentAngle() {
        return mCurrentAngle;
    }

    public void setCurrentAngle(int currentAngle) {
        this.mCurrentAngle = currentAngle;
    }

    public int getDefaultWidth() {
        return defaultWidth;
    }

    public void setDefaultWidth(int defaultWidth) {
        this.defaultWidth = defaultWidth;
    }
}
