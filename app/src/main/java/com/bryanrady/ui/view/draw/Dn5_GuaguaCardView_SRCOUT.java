package com.bryanrady.ui.view.draw;

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
 *  Xfermode实现  刮刮卡效果 和橡皮擦一样的原理
 * Created by wqb on 2018/6/26.
 */

public class Dn5_GuaguaCardView_SRCOUT extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap;
    private Bitmap mDestBitmap;
    private Bitmap mTextBitmap;
    private Path mPath;
    private float mX,mY;

    public Dn5_GuaguaCardView_SRCOUT(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
        mPaint.setAntiAlias(true);

        mPath = new Path();

        mTextBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.guaguaka_text1,null);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.guaguaka,null);
        mDestBitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1、画中奖文字
        canvas.drawBitmap(mTextBitmap, 0 , 0 , mPaint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        // 2、先把手指轨迹画到目标Bitmap上
        Canvas c = new Canvas(mDestBitmap);
        c.drawPath(mPath,mPaint);
        //然后把目标图像画到画布上
        canvas.drawBitmap(mDestBitmap,0,0,mPaint);

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
