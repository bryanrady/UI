package com.bryanrady.ui.view.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Path基础知识
 * Created by Administrator on 2019/3/22.
 */

public class PathOpView extends View {

    private Path mPath;
    private Paint mPaint;

    public PathOpView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         *  op(Path path, Op op) 布尔运算
         *      Path.Op一共定义了INTERSECT, DIFFERENCE, REVERSE_DIFFERENCE, UNION , XOR 五种模式
         *
         *      INTERSECT --- 保留Path2 和 Path1 共同的部分
         *
         *      DIFFERENCE -- 减去Path2后Path1区域剩下的部分
         *
         *      REVERSE_DIFFERENCE --- 减去Path1后Path2区域剩下的部分
         *
         *      UNION -- 保留Path1 和 Path 2
         *
         *      XOR --- 保留Path1 和 Path2 还有共同的部分
         *
         */



        drawIntersectOp(canvas);

        drawDifferenceOp(canvas);

        drawReverseDifferenceOp(canvas);

        drawUnionOp(canvas);

        drawXorOp(canvas);

    }

    private void drawIntersectOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(200, 200, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(300, 300, 100, Path.Direction.CW);

        path1.op(path2, Path.Op.INTERSECT);
        mPaint.setColor(Color.GREEN);
        canvas.drawPath(path1, mPaint);

        canvas.drawText("Path.Op.INTERSECT",50,450, mPaint);

    }

    private void drawDifferenceOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(700, 200, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(800, 300, 100, Path.Direction.CW);

        path1.op(path2, Path.Op.DIFFERENCE);
        mPaint.setColor(Color.GREEN);
        canvas.drawPath(path1, mPaint);

        canvas.drawText("Path.Op.DIFFERENCE",600,450, mPaint);
    }

    private void drawReverseDifferenceOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(200, 600, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(300, 700, 100, Path.Direction.CW);

        path1.op(path2, Path.Op.REVERSE_DIFFERENCE);
        mPaint.setColor(Color.GREEN);
        canvas.drawPath(path1, mPaint);

        canvas.drawText("Path.Op.REVERSE_DIFFERENCE",50,900, mPaint);
    }


    private void drawUnionOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(700, 600, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(800, 700, 100, Path.Direction.CW);

        path1.op(path2, Path.Op.UNION);
        mPaint.setColor(Color.GREEN);
        canvas.drawPath(path1, mPaint);

        canvas.drawText("Path.Op.UNION",700,900, mPaint);
    }

    private void drawXorOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(200, 1100, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(300, 1200, 100, Path.Direction.CW);

        path1.op(path2, Path.Op.XOR);
        mPaint.setColor(Color.GREEN);
        canvas.drawPath(path1, mPaint);

        canvas.drawText("Path.Op.XOR",50,1400, mPaint);
    }



}
