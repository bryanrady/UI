package com.bryanrady.ui.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Paint 基本用法
 *   Paint负责样式和颜色 比如 Paint 就像人身上的衣服
 *
 *   Canvas负责样子 比如 Convas 就像一个人
 * Created by wqb on 2018/1/12.
 */

public class Dn3_BasePaintView extends View {

    public Dn3_BasePaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * canvas的常用绘制方法:
     1. 填充
        drawARGB(int a, int r, int g, int b)
        drawColor(int color)
        drawRGB(int r, int g, int b)
        drawColor(int color, PorterDuff.Mode mode)

     2. 几何图形
        canvas.drawArc （扇形）
        canvas.drawCircle（圆）
        canvas.drawOval（椭圆）
        canvas.drawLine（线）
        canvas.drawPoint（点）
        canvas.drawRect（矩形）
        canvas.drawRoundRect（圆角矩形）
        canvas.drawVertices（顶点）
        cnavas.drawPath（路径）

     3.图片
        canvas.drawBitmap （位图）
        canvas.drawPicture （图片）

     4.文本
        canvas.drawText

     */


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    //    drawText(canvas,"我喜欢你");

    //    drawLine(canvas);

        drawStrokeJoin(canvas);

    //    drawArc(canvas);

    //    drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(8);
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);

        canvas.drawCircle(getWidth()/2,getHeight()/2,300,paint);
    }

    private void drawArc(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);

        paint.setStrokeWidth(10);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        //定义圆弧所在的区域
        RectF oval = new RectF(100,100,400,400);
        //useCenter 为true时，绘制出来的时扇形
        canvas.drawArc(oval,0,135,true, paint);

        paint.setStrokeWidth(20);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        RectF oval2 = new RectF(500,500,800,800);
        canvas.drawArc(oval2,-120,75,false, paint);
    }

    /**
     * 区分 paint.setStyle（）模式
     * @param canvas
     * @param text
     */
    private void drawText(Canvas canvas, String text){
        Paint paint = new Paint();
        paint.setStrokeWidth(4);    //设置线宽
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setDither(true);

        //测量文本文字
        Rect textBounds = new Rect();
        paint.getTextBounds(text,0, text.length(), textBounds);
        int height = textBounds.height();

        paint.setTextSize(100);

        /**
         *  paint.setStyle（） 设置画笔样式  有三种模式
         *
         *      Paint.Style.STROKE      只绘制图形轮廓（描边）
         *
         *      Paint.Style.FILL      只绘制图形内容  填充图形内容
         *
         *      Paint.Style.FILL_AND_STROKE      既绘制轮廓也绘制内容
         */

        //默认填充图形内容  FILL
        canvas.drawText(text,100,200, paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawText(text,100,400, paint);

        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text,100,600 , paint);

        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText(text,100,800, paint);
    }

    /**
     * StrokeCap 线帽 示例
     * setStrokeCap 区分线帽样式
     */
    private void drawLine(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStrokeWidth(60);
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);   //设置抗锯齿，这个会让图形看起来尽量平滑，但是会带来一定的性能损耗
    //    paint.setDither(true);  //设置图像是否使用抖动处理,会使得绘制出来的图片更加平滑和饱满，图像更加清晰
        paint.setStyle(Paint.Style.STROKE);

        /**
         * setStrokeCap() 设置线帽样式  有三种模式
         *
         *      BUTT    无线帽
         *
         *      ROUND   圆形线帽
         *
         *      SQUARE  方形线帽
         */
        //默认无线帽 BUTT
        canvas.drawLine(100,200,400,200, paint);

        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(100,400,400,400, paint);

        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(100,600,400,600, paint);

        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(100,800,400,800, paint);

    }

    /**
     * 两条线连接边缘样式的示例
     * @param canvas
     */
    private void drawStrokeJoin(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(40);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);

        Path path  = new Path();
        path.moveTo(100,100);
        path.lineTo(450,100);
        path.lineTo(100,300);
        //设置线与线之间的连接处的边缘是一个锐角 就是一个尖角
        paint.setStrokeJoin(Paint.Join.MITER);
        canvas.drawPath(path,paint);

        path.moveTo(100,400);
        path.lineTo(450,400);
        path.lineTo(100,600);
        //设置线与线之间的连接处的边缘是一个直线
        paint.setStrokeJoin(Paint.Join.BEVEL);
        canvas.drawPath(path,paint);

        path.moveTo(100,700);
        path.lineTo(450,700);
        path.lineTo(100,900);
        //设置线与线之间的连接处的边缘是一个圆弧
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path,paint);
    }



}
