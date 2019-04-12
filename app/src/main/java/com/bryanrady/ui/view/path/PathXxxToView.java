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

public class PathXxxToView extends View {

    private Paint mPaint;
    private Path mPath;

    public PathXxxToView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPathXxxTo(canvas);
    }

    public void drawPathXxxTo(Canvas canvas){

        /**
         *  xxxTo方法，其作用是从起点到终点移动path画笔并绘制线（moveTo方法只移动path画笔不绘制线），线有直线和曲线
         *
         *      moveTo(float x, float y)
         *          移动画笔，x：终点x坐标值，y：终点y坐标值
         *
         *      lineTo(float x, float y)
         *          绘制直线，x：终点x坐标值，y：终点y坐标值
         *
         *      arcTo(RectF oval, float startAngle, float sweepAngle)       默认false
         *          绘制圆弧，oval：圆弧矩形区域，startAngle：起始角度，顺时针旋转绘制圆弧 sweepAngle：圆弧旋转的角度 （X轴正方向为0°）  （sweepAngle为负时则逆时针旋转）
         *
         *      arcTo(RectF oval, float startAngle, float sweepAngle,boolean forceMoveTo)
         *          绘制圆弧，oval：圆弧矩形区域，startAngle：起始角度，sweepAngle：圆弧旋转的角度，
         *          forceMoveTo：是否在绘制圆弧前移动（moveTo）path画笔位置
         *
         *      arcTo(float left, float top, float right, float bottom, float startAngle,float sweepAngle, boolean forceMoveTo)
         *          绘制圆弧，left、top、right、bottom组成圆弧矩形区域，startAngle：起始角度，sweepAngle：圆弧旋转的角度，
         *          forceMoveTo：是否在绘制圆弧前移动（moveTo）path画笔位置
         *
         *      quadTo(float x1, float y1, float x2, float y2)
         *          绘制二阶贝塞尔曲线，控制点坐标：(x1,y1)，终点坐标：(x2,y2)
         *
         *      cubicTo(float x1, float y1, float x2, float y2,float x3, float y3)
         *          绘制三阶贝塞尔曲线，其中控制点1坐标为(x1,y1)，控制点2坐标为(x2,y2)，终点坐标为(x3,y3)
         *
         *  rXxxTo方法的r意思是relative，即相对的意思，方法有四个，其功能与对应的xxxTo方法一样，区别在于rXxxTo方法在绘制Path时
         *  是以当前path画笔位置为坐标原点，即相对于path画笔位置进行绘制，而xxxTo方法的坐标原点则与当前canvas坐标原点一致
         *
         *     rMoveTo(float dx, float dy)
         *
         *     rLineTo(float dx, float dy)
         *
         *     rCubicTo(float x1, float y1, float x2, float y2,float x3, float y3)
         *
         *     rQuadTo(float dx1, float dy1, float dx2, float dy2)
         *
         */

        mPath.moveTo(50,50);    //先将画笔移动到canvas坐标系中的(50,50)处

        mPath.lineTo(200,200);  //绘制直线

        /**
         * 绘制圆弧，若forceMoveTo为false，则用法和arcTo(RectF oval, float startAngle, float sweepAngle)一样，
         * 绘制圆弧之前不会移动（moveTo）path画笔位置。若为true，先强制调用moveTo移动path画笔至圆弧起点，
         * 再绘制圆弧。
         * 注意：如果调用arcTo(RectF oval, float startAngle, float sweepAngle,boolean forceMoveTo)方法之前没有对path进行任何操作，
         * 则forceMoveTo设置true或false效果都和设置true一样
         */

        RectF rectF = new RectF(400,200,700,500);
        mPath.arcTo(rectF,0,180,false); //绘制弧线

        mPath.quadTo(100,600,300,800);  //绘制二阶贝赛尔曲线

        mPath.cubicTo(500,600,600,1000,900,800);   //绘制三阶贝赛尔曲线

        canvas.drawPath(mPath,mPaint);
    }



}
