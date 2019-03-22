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
 * 书架照亮
 * Created by wqb on 2018/6/28.
 */

public class Dn5_LightBookView_LIGHTEN extends View {

    private Paint mPaint;
    private Bitmap mSrcBitmap,mDstBitmap;

    public Dn5_LightBookView_LIGHTEN(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mDstBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.book_bg,null);
        mSrcBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.book_light,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        canvas.drawBitmap(mDstBitmap,0,0,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        canvas.drawBitmap(mSrcBitmap,0,0,mPaint);

        mPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }
}
