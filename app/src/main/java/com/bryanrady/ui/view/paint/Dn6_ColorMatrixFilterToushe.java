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

public class Dn6_ColorMatrixFilterToushe extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public Dn6_ColorMatrixFilterToushe(Context context) {
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

//        3、色彩的投射运算
//
//                  黑白照片
//                  色彩反色（两个颜色交换）
//                  复古照片

        // 黑白照片 场景：在qq头像下线状态 头像黑白
        // 去色原理：只要把R G B 三通道的色彩信息设置成一样，那么图像就会变成灰色，
        // 同时为了保证图像亮度不变，同一个通道里的R+G+B =1
        //
        float[] floats = new float[]{
                0.213f, 0.715f,0.072f,0,0,
                0.213f, 0.715f,0.072f,0,0,
                0.213f, 0.715f,0.072f,0,0,
                0, 0, 0, 1,0,
        };
        ColorMatrix colorMatrix = new ColorMatrix(floats);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, null , rectF1 , mPaint);

        mPaint.setColorFilter(null);

        RectF rectF2 = new RectF(100,mBitmap.getHeight()+200,mBitmap.getWidth(),mBitmap.getHeight()*2+100);

        // 发色效果---把对应的两个颜色进行调换（比如红色和绿色交换）
        //就是把第二行和第一行进行交换,这样的话 对应的红色会显示成绿色,绿色显示成红色
        float[] floats2 = new float[]{
                0,1,0,0,0,
                1,0,0,0,0,
                0,0,1,0,0,
                0,0,0,1,0,
        };
        ColorMatrix colorMatrix2 = new ColorMatrix(floats2);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix2));
        canvas.drawBitmap(mBitmap,null,rectF2,mPaint);

        mPaint.setColorFilter(null);

        RectF rectF3 = new RectF(100+mBitmap.getWidth(),mBitmap.getHeight()+200,mBitmap.getWidth()*2,mBitmap.getHeight()*2+100);

        // 复古效果 复古照片
        float[] floats3 = new float[]{
                1/2f,1/2f,1/2f,0,0,
                1/3f,1/3f,1/3f,0,0,
                1/4f,1/4f,1/4f,0,0,
                0,0,0,1,0,
        };


        ColorMatrix colorMatrix3 = new ColorMatrix(floats3);
        mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix3));
        canvas.drawBitmap(mBitmap,null, rectF3, mPaint);

    }

}
