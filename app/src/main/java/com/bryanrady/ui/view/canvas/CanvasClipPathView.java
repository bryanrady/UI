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

public class CanvasClipPathView extends View {


    public CanvasClipPathView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        clipPath(canvas);
    }

    /**
     *  clipPath 用指定的路径 path 修改当前的剪裁
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
     *       clipPath 方法剪裁模式默认为Region.Op.INTERSECT
     *
     *
     */

    private void clipPath(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);

        paint.setColor(Color.RED);
        Path path1 = new Path();
        path1.addRect(100,100,300,300, Path.Direction.CCW);
        canvas.drawPath(path1,paint);
        paint.setColor(Color.BLUE);
        Path path2 = new Path();
        path2.addCircle(300,200,100, Path.Direction.CCW);
        canvas.drawPath(path2,paint);
        canvas.clipPath(path1, Region.Op.INTERSECT);
        canvas.clipPath(path2,Region.Op.INTERSECT);
        canvas.drawColor(Color.GREEN);


        paint.setColor(Color.RED);
        Path path3 = new Path();
        path3.addRect(500,100,700,300, Path.Direction.CCW);
        canvas.drawPath(path3,paint);
        paint.setColor(Color.BLUE);
        Path path4 = new Path();
        path4.addCircle(700,200,100, Path.Direction.CCW);
        canvas.drawPath(path4,paint);
        canvas.clipPath(path3, Region.Op.DIFFERENCE);
        canvas.clipPath(path4,Region.Op.DIFFERENCE);
        canvas.drawColor(Color.GREEN);


        paint.setColor(Color.RED);
        Path path5 = new Path();
        path5.addRect(100,400,300,600, Path.Direction.CCW);
        canvas.drawPath(path5,paint);
        paint.setColor(Color.BLUE);
        Path path6 = new Path();
        path6.addCircle(300,500,100, Path.Direction.CCW);
        canvas.drawPath(path6,paint);
        canvas.clipPath(path5, Region.Op.REPLACE);
        canvas.clipPath(path6,Region.Op.REPLACE);
        canvas.drawColor(Color.GREEN);


        paint.setColor(Color.RED);
        Path path7 = new Path();
        path7.addRect(500,400,700,600, Path.Direction.CCW);
        canvas.drawPath(path7,paint);
        paint.setColor(Color.BLUE);
        Path path8 = new Path();
        path8.addCircle(700,500,100, Path.Direction.CCW);
        canvas.drawPath(path8,paint);
        canvas.clipPath(path7, Region.Op.REVERSE_DIFFERENCE);
        canvas.clipPath(path8, Region.Op.REVERSE_DIFFERENCE);
        canvas.drawColor(Color.GREEN);


        paint.setColor(Color.RED);
        Path path9 = new Path();
        path9.addRect(100,700,300,900, Path.Direction.CCW);
        canvas.drawPath(path9,paint);
        paint.setColor(Color.BLUE);
        Path path10 = new Path();
        path10.addCircle(300,800,100, Path.Direction.CCW);
        canvas.drawPath(path10,paint);
        canvas.clipPath(path9, Region.Op.UNION);
        canvas.clipPath(path10,Region.Op.UNION);
        canvas.drawColor(Color.GREEN);


        paint.setColor(Color.RED);
        Path path11 = new Path();
        path11.addRect(500,700,700,900, Path.Direction.CCW);    // Path.Direction.CCW 逆时针方向
        canvas.drawPath(path11,paint);
        paint.setColor(Color.BLUE);
        Path path12 = new Path();
        path12.addCircle(700,800,100, Path.Direction.CCW);
        canvas.drawPath(path12,paint);
        canvas.clipPath(path11, Region.Op.XOR);
        canvas.clipPath(path12, Region.Op.XOR);
        canvas.drawColor(Color.GREEN);

    }


}
