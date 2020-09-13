package com.bryanrady.ui.view.paint.xfermode;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.bryanrady.ui.R;

/**
 *  Xfermode实现倒影图片
 * Created by wqb on 2018/6/26.
 */

public class IrregularWaveView_DST_IN extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap;
    private Bitmap mDestBitmap;
    private int mItemWaveLength = 0;
    private int mDx = 0;

    public IrregularWaveView_DST_IN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.circle_shape,null);
        mDestBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wave_bg,null);

        mItemWaveLength = mDestBitmap.getWidth();
        startAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //先画上圆形
        canvas.drawBitmap(mSrcBitmap,0,0,mPaint);

        //再画上结果
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mDestBitmap,new Rect(mDx,0,mDx+mSrcBitmap.getWidth(), mSrcBitmap.getHeight()),
                new Rect(0,0,mSrcBitmap.getWidth(),mSrcBitmap.getHeight()),mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mSrcBitmap,0,0,mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofInt(0,mItemWaveLength);
        animator.setDuration(4000);
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
