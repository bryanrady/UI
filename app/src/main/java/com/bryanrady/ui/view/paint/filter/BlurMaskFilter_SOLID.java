package com.bryanrady.ui.view.paint.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  Xfermode实现圆角图片
 * Created by wqb on 2018/6/26.
 */

public class BlurMaskFilter_SOLID extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public BlurMaskFilter_SOLID(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy2,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.restore();
        RectF rectF = new RectF(100,100,mBitmap.getWidth(),mBitmap.getHeight());
        canvas.drawBitmap(mBitmap, null , rectF , mPaint);

        RectF rectF1 = new RectF(100+mBitmap.getWidth(),100,mBitmap.getWidth()*2,mBitmap.getHeight());
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
        canvas.drawBitmap(mBitmap, null , rectF1 , mPaint);
    }

}
