package com.bryanrady.ui.view.path.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PathBezierBaseView extends View {

    private float mStartX, mStartY;
    private float mEndX, mEndY;
    private float mContorlX = 200, mContorlY = 60;//默认值
    private Paint mPaint;
    private Path mPath;


    public PathBezierBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mStartX = 60;
        mStartY = 350;
        mEndX = 450;
        mEndY = 350;
        mPath = new Path();
    }

    public PathBezierBaseView(Context context) {
        this(context,null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartX, mStartY);
        mPath.quadTo(mContorlX, mContorlY, mEndX, mEndY);
        //mPath.addCircle(); 里面有一个方向值 --顺时针和逆时针
        // canvas.drawTextOnPath();---可以去看一下文字的绘制方向
        canvas.drawPath(mPath, mPaint);
        canvas.drawPoint(mContorlX,mContorlY,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            mContorlX = event.getX();
            mContorlY = event.getY();
            invalidate();
        }
        return true;
    }
}
