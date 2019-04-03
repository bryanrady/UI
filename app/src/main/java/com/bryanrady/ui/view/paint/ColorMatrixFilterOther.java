package com.bryanrady.ui.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  颜色矩阵的一些其他效果
 * Created by wqb on 2018/6/26.
 */
public class ColorMatrixFilterOther extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    private int progress1;
    private int progress2;

    public ColorMatrixFilterOther(Context context) {
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

        //1.设置矩阵缩放 setScale
        RectF rectF1 = new RectF(100+mBitmap.getWidth(),100,mBitmap.getWidth()*2,mBitmap.getHeight());
        ColorMatrix colorMatrix = new ColorMatrix();
        //图片变亮了
        colorMatrix.setScale(1.2f,1.2f,1.2f,1);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, null , rectF1 , mPaint);
        mPaint.setColorFilter(null);

       // 2.设置饱和度 setSaturation 图片能变深沉
        RectF rectF2 = new RectF(100,mBitmap.getHeight()+100,mBitmap.getWidth(),mBitmap.getHeight()*2);
        ColorMatrix colorMatrix2 = new ColorMatrix();
        colorMatrix2.setSaturation(progress1);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix2));
        canvas.drawBitmap(mBitmap,null,rectF2,mPaint);
        mPaint.setColorFilter(null);


        //3.设置旋转 setRotate  颜色变换
        RectF rectF3 = new RectF(100+mBitmap.getWidth(),mBitmap.getHeight()+100,mBitmap.getWidth()*2,mBitmap.getHeight()*2);
        ColorMatrix colorMatrix3 = new ColorMatrix();
        //aixs-- 0 红色轴，1，绿色，2，蓝色
        // degrees -- 旋转的角度
        colorMatrix3.setRotate(0,progress2);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix3));
        canvas.drawBitmap(mBitmap,null, rectF3, mPaint);
        mPaint.setColorFilter(null);


        //4.两个矩阵组合 setConcat
        RectF rectF4 = new RectF(100,mBitmap.getHeight()*2+100,mBitmap.getWidth(),mBitmap.getHeight()*3);
        ColorMatrix colorMatrix4 = new ColorMatrix();
        colorMatrix4.setConcat(colorMatrix2,colorMatrix3);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix4));
        canvas.drawBitmap(mBitmap,null, rectF4, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //progress1 += 1f;
                progress1 += 20f;
                progress2 = 0xff0000;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                progress2 = 0x000000;
                invalidate();
                break;
        }
        return true;
    }
}
