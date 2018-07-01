package com.bryanrady.ui.view.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  Xfermode实现圆角图片
 * Created by wqb on 2018/6/26.
 */

public class Dn6_LightingColorFilter extends View {

    private Paint mPaint;
    private Bitmap mBitmap;
    private int progress;

    public Dn6_LightingColorFilter(Context context) {
        super(context);
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

        // LightingColorFilter只是修改RGB值
        //paint.setColorFilter(new LightingColorFilter(0x00ff00,0xff0000));
        //paint.setColorFilter(new LightingColorFilter(0xffffff,progress));

        //可修改颜色
        RectF rectF1 = new RectF(100+mBitmap.getWidth(),100,mBitmap.getWidth()*2,mBitmap.getHeight());
        mPaint.setColorFilter(new LightingColorFilter(0x00ff00,0xff0000));
        canvas.drawBitmap(mBitmap, null , rectF1 , mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                progress = 0xff0000;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                progress = 0x000000;
                invalidate();
                break;
        }
        return true;
    }

}
