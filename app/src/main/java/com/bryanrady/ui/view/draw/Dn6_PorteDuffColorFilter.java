package com.bryanrady.ui.view.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  利用Xfermode模式实现滤镜效果
 * Created by wqb on 2018/6/26.
 */

public class Dn6_PorteDuffColorFilter extends View {

    private Paint mPaint;
    private Bitmap mBitmap;

    public Dn6_PorteDuffColorFilter(Context context) {
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

        //PorterDuffColorFilter 图片颜色能进行过滤 使用PorterDuff.Mode
        RectF rectF1 = new RectF(100+mBitmap.getWidth(),100,mBitmap.getWidth()*2,mBitmap.getHeight());
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mBitmap, null , rectF1 , mPaint);
        mPaint.setColorFilter(null);

        RectF rectF2 = new RectF(100,mBitmap.getHeight()+200,mBitmap.getWidth(),mBitmap.getHeight()*2+100);
        mPaint.setColorFilter(new PorterDuffColorFilter(Color.argb(255,140,90,200), PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(mBitmap,null,rectF2,mPaint);

    }

}
