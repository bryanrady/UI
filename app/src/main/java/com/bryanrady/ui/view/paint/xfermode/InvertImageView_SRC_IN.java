package com.bryanrady.ui.view.paint.xfermode;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  Xfermode实现倒影图片
 * Created by wqb on 2018/6/26.
 */

public class InvertImageView_SRC_IN extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap;
    private Bitmap mDestBitmap;
    private Bitmap mInvertBitmap;

    public InvertImageView_SRC_IN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint();
        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy6,null);
        mDestBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.invert_shade,null);

        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);
        // 生成倒影图
        mInvertBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0, mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1、画正图
        canvas.drawBitmap(mSrcBitmap, 0 , 0 , mPaint);

        // 2、再画出倒影
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0,mSrcBitmap.getHeight());

        // 3、画倒影图区域的目标图
        canvas.drawBitmap(mDestBitmap,0,0,mPaint);

        // 4、设置SRC_IN模式，画原图
        //目标图片是一张和正图一样大小的有点低像素的接近透明的矩形图片
        //所以原图会根据目标图的像素进行变化
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mInvertBitmap, 0 , 0 , mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);
    }

}
