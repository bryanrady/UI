package com.bryanrady.ui.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.view.View;

/**
 * Created by Administrator on 2019/4/3.
 */

public class CanvasClipRectView extends View {

    private Paint mPaint;
    private Path mPath;

    public CanvasClipRectView(Context context) {
        super(context);

        setFocusable(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setTextSize(16);
        mPaint.setTextAlign(Paint.Align.RIGHT);

        mPath = new Path();
    }


    private void drawScene(Canvas canvas) {
        canvas.clipRect(0, 0, 100, 100);
        canvas.drawColor(Color.WHITE);

        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, mPaint);

        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(30, 70, 30, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.drawText("Clipping", 100, 30, mPaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        clipRect(canvas);
    }

    /**
     *  clipRect 用指定的 rect 区域修改当前的剪裁
     *
     *     对op参数的理解:以剪裁两次的区域分别为A，B来区别
     *
     *          Region.Op.INTERSECT:            剪裁出相交的部分，类似 A交B 部分
     *
     *          Region.Op.DIFFERENCE:           剪裁出差异的部分，类似 A-B 部分
     *
     *          Region.Op.REPLACE:              后剪裁B的覆盖剪裁的A
     *
     *          Region.Op.REVERSE_DIFFERENCE:   剪裁出差异的部分，类似 B-A 部分
     *
     *          Region.Op.UNION:                剪裁出AB合并的部分，类似** AUB**
     *
     *          Region.Op.XOR：                 是** (AUB)-(A交B)** 刚好与** A交B** 相对
     *
     *
     *       clipRect 方法剪裁模式默认为Region.Op.INTERSECT
     *
     *
     */

    private void clipRect(Canvas canvas){
        canvas.drawColor(Color.GRAY);
        canvas.save();

        canvas.translate(10, 10);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(160, 10);
        canvas.clipRect(10, 10, 90, 90);
        canvas.clipRect(30, 30, 70, 70, Region.Op.DIFFERENCE);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(10, 160);
        mPath.reset();
        canvas.clipPath(mPath); // makes the clip empty
        mPath.addCircle(50, 50, 50, Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.REPLACE);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(160, 160);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.UNION);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(10, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.XOR);
        drawScene(canvas);
        canvas.restore();

        canvas.save();
        canvas.translate(160, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.REVERSE_DIFFERENCE);
        drawScene(canvas);
        canvas.restore();

    }


}
