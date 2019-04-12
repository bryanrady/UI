package com.bryanrady.ui.view.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * Path基础知识
 * Created by Administrator on 2019/3/22.
 */

public class PathAddXxxView extends View {

    private Paint mPaint;

    public PathAddXxxView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPathAddXxx(canvas);
    }

    public void drawPathAddXxx(Canvas canvas){

        /**
         *      addArc(RectF oval, float startAngle, float sweepAngle)
         *              添加圆弧，oval：圆弧矩形区域，startAngle：起始角度，sweepAngle：圆弧旋转的角度
         *
         *      addArc(float left, float top, float right, float bottom, float startAngle,float sweepAngle)
         *              添加圆弧，left、top、right、bottom组成圆弧矩形区域，startAngle：起始角度，
         *              sweepAngle：圆弧旋转的角度。ps：此方法在API 19以上有效
         *
         *      addCircle(float x, float y, float radius, Direction dir)
         *              添加圆形，x：圆形圆心的x坐标，y：圆形圆心的y坐标，radius：圆形半径，dir：线的闭合方向（CW顺时针方向 | CCW逆时针方向）
         *
         *      addOval(RectF oval, Direction dir)
         *              添加椭圆，oval：椭圆内切的矩形区域，dir：线的闭合方向（CW顺时针方向 | CCW逆时针方向）
         *
         *      addOval(float left, float top, float right, float bottom, Direction dir)
         *              添加椭圆，left、top、right、bottom组成椭圆内切的矩形区域，dir：线的闭合方向（CW顺时针方向 | CCW逆时针方向）
         *
         *      addRect(RectF rect, Direction dir)
         *              添加矩形，rect：矩形区域，dir：线的闭合方向（CW顺时针方向 | CCW逆时针方向）
         *
         *      addRect(float left, float top, float right, float bottom, Direction dir)
         *              添加矩形，left、top、right、bottom组成矩形区域，dir：线的闭合方向（CW顺时针方向 | CCW逆时针方向）
         *
         *      addRoundRect(RectF rect, float rx, float ry, Direction dir)
         *              添加统一圆角的圆角矩形，rect：矩形区域，rx：椭圆圆角的横轴半径，
         *              ry：椭圆圆角的纵轴半径，dir：线的闭合方向（CW顺时针方向 | CCW逆时针方向）
         *
         *     addRoundRect(float left, float top, float right, float bottom, float rx, float ry,Direction dir)
         *              添加统一圆角的圆角矩形，left、top、right、bottom组成矩形区域，rx：椭圆圆角的横轴半径，ry：椭圆圆角的纵轴半径，
         *              dir：线的闭合方向（CW顺时针方向 | CCW逆时针方向）
         *
         *     addRoundRect(RectF rect, float[] radii, Direction dir)
         *              添加非统一圆角的圆角矩形，rect：矩形区域，radii：矩形四个椭圆圆角的横轴半径和纵轴半径的数组，一共8个数值，
         *              dir：线的闭合方向（CW顺时针方向 | CCW逆时针方向）
         *
         *     addRoundRect(float left, float top, float right, float bottom, float[] radii,Direction dir)
         *              添加非统一圆角的圆角矩形，left、top、right、bottom组成矩形区域，radii：矩形四个椭圆圆角的横轴半径和纵轴半径的数组，
         *              一共8个数值，dir：线的闭合方向（CW顺时针方向 | CCW逆时针方向）
         *
         *     addPath(Path src)
         *              添加一组Path，src：要添加的Path
         *
         *     addPath(Path src, float dx, float dy)
         *              添加一组平移后的Path，src：要添加的Path，dx：平移的x坐标，dy：平移的y坐标
         *
         *     addPath(Path src, Matrix matrix)
         *              添加一组经过矩阵变换后的Path，src：要添加的Path，matrix：3x3的矩阵
         *
         */

        //1.addArc(RectF oval, float startAngle, float sweepAngle)addArc两个方法使用起来与arcTo(RectF oval,
        // float startAngle, float sweepAngle,boolean forceMoveTo)中forceMoveTo设置为true效果一致

        //1.顺时针方向绘制圆
        Path path1 = new Path();
        path1.addCircle(250,200,150, Path.Direction.CW);
        canvas.drawPath(path1,mPaint);
        mPaint.setTextSize(40);
        //沿着Path绘制一段文字  hOffset : 与路径起始点的水平偏移距离 vOffset : 与路径中心的垂直偏移量
        canvas.drawTextOnPath("顺时针",path1,0,-10,mPaint);

        //2.逆时针方向绘制圆
        Path path2 = new Path();
        path2.addCircle(750,200,150, Path.Direction.CCW);
        canvas.drawPath(path2,mPaint);
        canvas.drawTextOnPath("逆时针",path2,0,-10,mPaint);

        //3.顺时针方向绘制圆角矩形 顺时针方向绘制起点在左下角
        Path path3 = new Path();
        RectF rectF1 = new RectF(100,400,400,600);
        path3.addRoundRect(rectF1, 20,20, Path.Direction.CW);
        canvas.drawPath(path3,mPaint);
        canvas.drawTextOnPath("顺时针",path3,0,-10,mPaint);

        //4.逆时针方向绘制圆角矩形 四个角的圆角的横轴和纵轴半径由radii数组中的8个数值决定  逆时针方向绘制起点在左上角
        Path path4 = new Path();
        RectF rectF2 = new RectF(600,400,900,600);
        // 需要注意的是，如果radii数组中的元素小于8，系统会抛出错误信息 java.lang.ArrayIndexOutOfBoundsException: radii[] needs 8 values
        float[] radii = new float[]{10,20,30,10,50,70,10,20};
        path4.addRoundRect(rectF2, radii, Path.Direction.CCW);
        canvas.drawPath(path4,mPaint);
        canvas.drawTextOnPath("逆时针",path4,0,-10,mPaint);

        //5.顺时针方向绘制矩形
        Path path5 = new Path();
        RectF rectF3 = new RectF(100,700,400,900);
        path5.addRect(rectF3, Path.Direction.CW);
        canvas.drawPath(path5,mPaint);
        canvas.drawTextOnPath("顺时针",path5,0,-10,mPaint);

        //6.顺时针方向绘制椭圆
        Path path6 = new Path();
        RectF rectF4 = new RectF(600,700,900,900);
        path6.addOval(rectF4, Path.Direction.CW);
        canvas.drawPath(path6,mPaint);
        canvas.drawTextOnPath("顺时针",path6,0,-10,mPaint);

        //7.添加一组Path
        Path path7 = new Path();
        Path copyPath = new Path();
        copyPath.moveTo(100,1000);
        copyPath.lineTo(150,1200);
        copyPath.quadTo(300,1000,450,1300);
        copyPath.lineTo(100,1400);
        copyPath.close();

        path7.addPath(copyPath);
        canvas.drawPath(path7,mPaint);

        //7.添加一组Path,然后将其进行矩阵变换
        Path path8 = new Path();

        Matrix matrix = new Matrix();
        //向右平移500
        matrix.setTranslate(500,0);

        path8.addPath(copyPath,matrix);
        canvas.drawPath(path8,mPaint);
    }

}
