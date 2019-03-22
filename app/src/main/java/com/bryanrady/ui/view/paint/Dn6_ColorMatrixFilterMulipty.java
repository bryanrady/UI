package com.bryanrady.ui.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  Xfermode实现圆角图片
 * Created by wqb on 2018/6/26.
 */

public class Dn6_ColorMatrixFilterMulipty extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public Dn6_ColorMatrixFilterMulipty(Context context) {
        super(context);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.xyjy2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(100,100,mBitmap.getWidth(),mBitmap.getHeight());
        canvas.drawBitmap(mBitmap, null , rectF , mPaint);

        RectF rectF1 = new RectF(100+mBitmap.getWidth(),100,mBitmap.getWidth()*2,mBitmap.getHeight());

//       缩放运算---乘法 -- 颜色增强
//              高亮图片
//              颜色通道过滤（单独红色、绿色、蓝色）

        //变亮
        float[] floats = new float[]{
                1.2f,0,0,0,0,
                0,1.2f,0,0,0,
                0,0,1.2f,0,0,
                0,0,0,1.2f,0,
        };
        ColorMatrix colorMatrix = new ColorMatrix(floats);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, null , rectF1 , mPaint);
        mPaint.setColorFilter(null);

        RectF rectF2 = new RectF(100,mBitmap.getHeight()+200,mBitmap.getWidth(),mBitmap.getHeight()*2+100);

        //变暗
        float[] floats2 = new float[]{
                0.6f,0,0,0,0,
                0,0.6f,0,0,0,
                0,0,0.6f,0,0,
                0,0,0,0.6f,0,
        };
        ColorMatrix colorMatrix2 = new ColorMatrix(floats2);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix2));
        canvas.drawBitmap(mBitmap,null,rectF2,mPaint);

    }

}
