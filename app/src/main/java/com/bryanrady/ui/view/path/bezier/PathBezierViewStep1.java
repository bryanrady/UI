package com.bryanrady.ui.view.path.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;
/**
 * 控制点绘制
 * @author barry
 * @version V1.0
 * @time 2018-6-27 01:15:32
 */
public class PathBezierViewStep1 extends View {


    //曲线开始点
    private float mStartX, mStartY;
    //结束点
    private float mEndX, mEndY;
    //控制点
    private float mContorlX = 200, mContorlY = 60;//默认值
    private Paint mPaint,mLinePointPaint;
    private Path mPath;

    //多控制点
    private ArrayList<PointF> mControlPoints = null;    // 控制点集



    public PathBezierViewStep1(Context context) {
        this(context,null);
    }

    public PathBezierViewStep1(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);



        mLinePointPaint = new Paint();
        mLinePointPaint.setAntiAlias(true);
        mLinePointPaint.setStrokeWidth(4);
        mLinePointPaint.setStyle(Paint.Style.STROKE);
        mLinePointPaint.setColor(Color.RED);


        mStartX = 60;
        mStartY = 350;
        mEndX = 450;
        mEndY = 350;
        mPath = new Path();
        mControlPoints = new ArrayList<>();
        init();
    }



    private void init(){
        Random random = new Random();
        for (int i = 0;i < 5;i++){
            int x = random.nextInt(600);
            int y = random.nextInt(600);
            Log.i("barry","x:"+x+"--y:"+y);
            PointF pointF = new PointF(x,y);
            mControlPoints.add(pointF);
        }
    }

    public PathBezierViewStep1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }








    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 控制点和控制点连线
        int size = mControlPoints.size();
        PointF point;
        for (int i = 0; i < size; i++) {
            point = mControlPoints.get(i);
            if (i > 0) {
                // 控制点连线
                canvas.drawLine(mControlPoints.get(i - 1).x, mControlPoints.get(i - 1).y, point.x, point.y,
                        mLinePointPaint);
            }
            // 控制点
            canvas.drawCircle(point.x, point.y, 12, mLinePointPaint);
        }

    }








    /**
     * deCasteljau算法
     *
     * @param i 阶数
     * @param j 点
     * @param t 时间
     * @return
     */
    private float deCasteljauX(int i, int j, float t) {
        if (i == 1) {
            return (1 - t) * mControlPoints.get(j).x + t * mControlPoints.get(j + 1).x;
        }
        return (1 - t) * deCasteljauX(i - 1, j, t) + t * deCasteljauX(i - 1, j + 1, t);
    }

    /**
     * deCasteljau算法
     *
     * @param i 阶数
     * @param j 点
     * @param t 时间
     * @return
     */
    private float deCasteljauY(int i, int j, float t) {
        if (i == 1) {
            return (1 - t) * mControlPoints.get(j).y + t * mControlPoints.get(j + 1).y;
        }
        return (1 - t) * deCasteljauY(i - 1, j, t) + t * deCasteljauY(i - 1, j + 1, t);
    }
}