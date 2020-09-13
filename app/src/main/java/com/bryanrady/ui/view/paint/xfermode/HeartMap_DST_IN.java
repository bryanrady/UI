package com.bryanrady.ui.view.paint.xfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.bryanrady.ui.R;

/**
 *  心电图效果-
 *      目标图片 ---心电图
 *      源图片 ---- 不透明的图 就是通过改变源图片的不透明区域的宽度，来实现心电图的动画效果
 */

public class HeartMap_DST_IN extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap;
    private Bitmap mDestBitmap;
    private int mItemWaveLength = 0;
    private int mDx = 0;

    public HeartMap_DST_IN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        mDestBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.heartmap,null);
        mSrcBitmap = Bitmap.createBitmap(mDestBitmap.getWidth(), mDestBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        mItemWaveLength = mDestBitmap.getWidth();
        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Canvas c = new Canvas(mSrcBitmap);
        //清空bitmap
        c.drawColor(Color.RED, PorterDuff.Mode.CLEAR);
        //画上矩形
        c.drawRect(mDestBitmap.getWidth() - mDx,0,mDestBitmap.getWidth(),mDestBitmap.getHeight(),mPaint);

        //模式合成
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mDestBitmap,0,0,mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mSrcBitmap,0,0,mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofInt(0,mItemWaveLength);
        animator.setDuration(6000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

}
