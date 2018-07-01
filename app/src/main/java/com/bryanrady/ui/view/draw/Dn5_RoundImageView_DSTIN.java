package com.bryanrady.ui.view.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  Xfermode实现圆角图片
 * Created by wqb on 2018/6/26.
 */

public class Dn5_RoundImageView_DSTIN extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap;
    private Bitmap mDestBitmap;

    public Dn5_RoundImageView_DSTIN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shade,null);
        mDestBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1、画目标图
        canvas.drawBitmap(mDestBitmap, 0 , 0 , mPaint);

        // 3、设置SRC_IN模式，画原图
        //对比SRC_IN, 把原图片和目标图片换下位置就行了
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mSrcBitmap, 0 , 0 , mPaint);

        mPaint.setXfermode(null);
    }

}
