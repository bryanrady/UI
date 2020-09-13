package com.bryanrady.ui.view.progress;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.bryanrady.ui.R;

/**
 * 水波纹进度条
 */
public class WaterWaveProgressBar extends SquareProgressBar {

    private Path mPath = new Path();

    private int mWaveWidth; //水波长
    private int mWaveHeight; //水波高度
    private int mWaveSpeed;//水波速度

    private float mStartX = 0; //

    public WaterWaveProgressBar(Context context) {
        this(context, null);
    }

    public WaterWaveProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaterWaveProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);//关闭硬件加速

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WaterWaveProgressBar);
        mWaveWidth = typedArray.getDimensionPixelOffset(R.styleable.WaterWaveProgressBar_water_wave_width, -1);
        mWaveHeight = typedArray.getDimensionPixelOffset(R.styleable.WaterWaveProgressBar_water_wave_height, -1);
        mWaveSpeed = typedArray.getInteger(R.styleable.WaterWaveProgressBar_water_wave_speed, 5);
        if(mWaveSpeed <= 0 || mWaveSpeed > 10){
            mWaveSpeed = 5;
        }
    }

    @Override
    public void init() {
        if(lightShow){
            blankSpace = dp2px(10);
            refreshLight();
            progressPaint.setMaskFilter(null);
        }
        //设置模式
        progressPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        //5. 确定波长和波高
        if(mWaveWidth == -1){
            mWaveWidth = width/4;
        }
        if(mWaveHeight == -1){
            mWaveHeight = sp2px(5); //默认水波高度 单位sp
        }
        startAnimator();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {

        //1. 绘制贝塞尔曲线
        drawBessel(width, mStartX, (int)(height*(1-progress/maxProgress)), mWaveWidth, mWaveHeight, mPath);
        canvas.drawPath(mPath, progressPaint);
        //2. 绘制圆形bitmap
        canvas.drawBitmap(createCircleBitmap(width/2-blankSpace-strokeWidth), null, new Rect(0,0,width,height), progressPaint);

        //3.发光效果
        if(lightShow){
            canvas.drawCircle(width/2, width/2, width/2-blankSpace-strokeWidth, lightPaint);
        }
        //4.边框
        if(strokeShow){
            canvas.drawCircle(width/2, width/2, width/2-blankSpace-strokeWidth, strokePaint);
        }

        //3. 绘制文字
        drawText(canvas);
    }

    /**
     * 绘制贝塞尔曲线
     * @param width 总共需要绘制的长度
     * @param startX 开始X点坐标(-2*startX 到 0 之间) 左右预留一个波长
     * @param startY 开始Y坐标
     * @param waveWidth 波长(半个周期)
     * @param waveHeight 波高
     * @param path
     */
    private void drawBessel(float width, float startX, float startY, float waveWidth, float waveHeight, Path path){
        //Android贝塞尔曲线
        // 二阶写法：rQuadTo(float dx1, float dy1, float dx2, float dy2) 相对上一个起点的坐标
        path.reset();
        int currentWidth = 0; //当前已经绘制的宽度
        path.moveTo(startX,startY); //画笔位置
        while (currentWidth <= width + 4*waveWidth && waveWidth>0){
            path.rQuadTo(waveWidth, -waveHeight, 2*waveWidth, 0);
            path.rQuadTo(waveWidth, waveHeight, 2*waveWidth, 0);
            currentWidth += 2*waveWidth;
        }
        //封闭的区域
        path.lineTo(getWidth()+4*waveWidth,getHeight());
        path.lineTo(0,getHeight());
        path.close();
    }

    private Bitmap createCircleBitmap(int radius){
        Bitmap canvasBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(canvasBmp);
        canvas.drawColor(Color.TRANSPARENT);
        canvas.drawCircle(width/2, height/2, radius, progressBgPaint); //确定位置
        return canvasBmp;
    }

    ValueAnimator animator;
    private void startAnimator(){
        animator = ValueAnimator.ofFloat(0 - 4 * mWaveWidth, 0);
        animator.setInterpolator(new LinearInterpolator());//匀速插值器 解决卡顿问题
        int time = 200;
        if(mWaveSpeed > 0){
            time = 12000/mWaveSpeed;
            if(time>12000){
                time = 12000;
            }else if(time<1000){
                time = 1000;
            }
        }
        animator.setDuration(time);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setStartX((float) animation.getAnimatedValue());
                invalidate();
            }
        });
        animator.start();
    }

    /**
     * Activity的onDestroy中调用，结束动画. 以免造成内存泄漏
     */
    public void replace(){
        if(animator!=null && animator.isRunning()){
            animator.cancel();
        }
    }

    public void invalidates() {
        replace();
        startAnimator();
        invalidate();
    }

    public int getWaveWidth() {
        return mWaveWidth;
    }

    public void setWaveWidth(int waveWidth) {
        this.mWaveWidth = waveWidth;
        invalidates();
    }

    public int getWaveHeight() {
        return mWaveHeight;
    }

    public void setWaveHeight(int waveHeight) {
        this.mWaveHeight = waveHeight;
        invalidates();
    }

    public int getWaveSpeed() {
        return mWaveSpeed;
    }

    public void setWaveSpeed(int waveSpeed) {
        this.mWaveSpeed = waveSpeed;
        invalidates();
    }

    public float getStartX() {
        return mStartX;
    }

    public void setStartX(float startX) {
        this.mStartX = startX;
    }
}
