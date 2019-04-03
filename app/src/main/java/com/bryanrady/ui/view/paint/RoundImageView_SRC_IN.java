package com.bryanrady.ui.view.paint;

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
 *  Xfermode 实现圆角图片
 * Created by wqb on 2018/6/26.
 */

public class RoundImageView_SRC_IN extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap;
    private Bitmap mDestBitmap;

    public RoundImageView_SRC_IN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6,null);
        mDestBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.shade,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1、画目标图
        canvas.drawBitmap(mDestBitmap, 0 , 0 , mPaint);

        // 3、设置SRC_IN模式，画原图
        //因为目标图片是一个边框外面透明的圆形图片，所以边框外面的透明度会影响4原图片的透明度，
        //最后原图片在边框外面的区域也不会显示了
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mSrcBitmap, 0 , 0 , mPaint);

        mPaint.setXfermode(null);
    }

}
