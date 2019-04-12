package com.bryanrady.ui.view.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * Path基础知识
 * Created by Administrator on 2019/3/22.
 */

public class PathFillTypeView extends View {

    private Path mPath;
    private Paint mPaint;

    public PathFillTypeView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         *
         *  Path.FillType一共定义了WINDING, EVEN_ODD, INVERSE_WINDING, INVERSE_EVEN_ODD 四种模式
         *
         *      WINDING 模式 --- 取Path所有所在的区域 -- 默认的模式
         *
         *      EVEN_ODD 模式 --- 取Path所在不相交的区域
         *
         *      INVERSE_WINDING 模式 -- 取path所有未占的区域
         *
         *      INVERSE_EVEN_ODD 模式 --- 取path所有未占和相交的区域
         */


        drawWindingType(canvas);

        drawEvenOddType(canvas);

        drawInverseWinding(canvas);

        drawInverseEvenOdd(canvas);

    }

    private void drawWindingType(Canvas canvas) {
        mPath = new Path();
        mPath.offset(100,100);
        mPath.addCircle(200, 200, 100, Path.Direction.CW);
        mPath.addCircle(300, 300, 100, Path.Direction.CW);
        mPath.setFillType(Path.FillType.WINDING);
        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath,mPaint);

        canvas.drawText("FillType.WINDING",50,500, mPaint);
    }

    private void drawEvenOddType(Canvas canvas) {
        mPath = new Path();
        mPath.offset(600,100);
        mPath.addCircle(700, 200, 100, Path.Direction.CW);
        mPath.addCircle(800, 300, 100, Path.Direction.CW);
        mPath.setFillType(Path.FillType.EVEN_ODD);
        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath,mPaint);

        canvas.drawText("FillType.EVEN_ODD",600,1000, mPaint);
    }

    private void drawInverseWinding(Canvas canvas) {
        mPath = new Path();
        mPath.offset(100,600);
        mPath.addCircle(200, 700, 100, Path.Direction.CW);
        mPath.addCircle(300, 800, 100, Path.Direction.CW);
        mPath.setFillType(Path.FillType.INVERSE_WINDING);
        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath,mPaint);

        canvas.drawText("FillType.INVERSE_WINDING",50,1000, mPaint);
    }

    private void drawInverseEvenOdd(Canvas canvas) {
        mPath = new Path();
        mPath.offset(600,600);
        mPath.addCircle(700, 700, 100, Path.Direction.CW);
        mPath.addCircle(800, 800, 100, Path.Direction.CW);
        mPath.setFillType(Path.FillType.EVEN_ODD);
        mPaint.setColor(Color.RED);
        canvas.drawPath(mPath,mPaint);

        canvas.drawText("FillType.EVEN_ODD",600,500, mPaint);
    }

}
