package com.bryanrady.ui.view.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bryanrady.ui.R;

/**
 * 3、Canvas的状态保存---状态栈、Layer栈
 *
 *       状态栈--save、 restore方法来保存和还原变换操作Matrix以及Clip剪裁,也可以通过restoretoCount直接还原到对应栈的保存状态
 *
 *
 *       Layer栈--- saveLayer的时候都会新建一个透明的图层（离屏Bitmap-离屏缓冲），并且会将saveLayer之前的一些Canvas操作延续过来
 *                    后续的绘图操作都在新建的layer上面进行,当我们调用restore 或者 restoreToCount 时 更新到对应的图层和画布上
 *
 *      canvas中 save（）和saveLayer（）区别
 *
 *            相同点
 *
 *                    saveLayer可以实现save所能实现的功能
 *
 *           不同点
 *                    1，saveLaye生成一个独立的图层而save只是保存了一下当时画布的状态类似于一个还原点（本来就是）。
 *                    2，saveLaye因为多了一个图层的原因更加耗费内存慎用。
 *                    3，saveLaye可指定保存相应区域，尽量避免2中所指的情况。
 *                    4，在使用混合模式setXfermode时会产生不同的影响。
 *
 */
public class CanvasSaveRestoreView extends View {

    private static final String TAG = "CanvasSaveRestoreView";

    private Paint mPaint = null;
    private Bitmap mBitmap = null;

    public CanvasSaveRestoreView(Context context) {
        this(context, null);
    }

    public CanvasSaveRestoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lsj);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        Log.d(TAG, "Current SaveCount = " + canvas.getSaveCount());

        canvas.translate(400, 400);
        RectF rectF = new RectF(0,0,600,600);

        //canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);

        canvas.save();
        Log.d(TAG, "Current SaveCount = " + canvas.getSaveCount());

        canvas.rotate(45);
        //canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);

        canvas.save();
        Log.d(TAG, "Current SaveCount = " + canvas.getSaveCount());

        canvas.rotate(45);
        //canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);

        canvas.save();
        Log.d(TAG, "Current SaveCount = " + canvas.getSaveCount());

        canvas.restoreToCount(1);
        Log.d(TAG, "Current SaveCount = " + canvas.getSaveCount());

        canvas.translate(0, 200);
        //canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        //rectF = new RectF(0,0,600,600);
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
        canvas.save();
        Log.d(TAG, "Current SaveCount = " + canvas.getSaveCount());

        canvas.restoreToCount(1);
        Log.d(TAG, "Current SaveCount = " + canvas.getSaveCount());
        canvas.drawBitmap(mBitmap, null, rectF, mPaint);
    }
}
