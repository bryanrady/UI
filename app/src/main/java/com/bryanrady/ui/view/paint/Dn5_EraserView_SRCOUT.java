package com.bryanrady.ui.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  Xfermode实现橡皮擦效果
 * Created by wqb on 2018/6/26.
 */

public class Dn5_EraserView_SRCOUT extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap;
    private Bitmap mDestBitmap;
    private Path mPath;
    private float mX,mY;

    public Dn5_EraserView_SRCOUT(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
        mPaint.setAntiAlias(true);

        mPath = new Path();

        mSrcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.xyjy6,null);
        mDestBitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        /**
         * 关于ARGB_8888、ALPHA_8、ARGB_4444、RGB_565的理解

         A：透明度

         R：红色

         G：绿

         B：蓝

         Bitmap.Config ARGB_4444：每个像素占四位，即A=4，R=4，G=4，B=4，那么一个像素点占4+4+4+4=16位

         Bitmap.Config ARGB_8888：每个像素占四位，即A=8，R=8，G=8，B=8，那么一个像素点占8+8+8+8=32位

         Bitmap.Config RGB_565：每个像素占四位，即R=5，G=6，B=5，没有透明度，那么一个像素点占5+6+5=16位

         Bitmap.Config ALPHA_8：每个像素占四位，只有透明度，没有颜色。

         一般情况下我们都是使用的ARGB_8888，由此可知它是最占内存的，因为一个像素占32位，8位=1字节，所以
         一个像素占4字节的内存。假设有一张480x800的图片，如果格式为ARGB_8888，那么将会占用1500KB的内存
         */
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        // 1、先把手指轨迹画到目标图像上
        Canvas c = new Canvas(mDestBitmap);
        c.drawPath(mPath,mPaint);

        // 2、然后把目标图像画到画布上
        canvas.drawBitmap(mDestBitmap,0,0,mPaint);

        // 3、设置SRC_OUT模式，画原图
        //因为目标图片是一个不透明ARGB格式的路径图片，所以SRC_OUT模式会利用目标图形的像素来影响原图片，
        //所以最后原图片的像素就是透明的了
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(mSrcBitmap, 0 , 0 , mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                mX = event.getX();
                mY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mX + event.getX())/2;
                float endY = (mY + event.getY())/2;
                mPath.quadTo(mX , mY, endX, endY);
                mX = event.getX();
                mY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
