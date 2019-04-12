package com.bryanrady.ui.view.path.bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by zenghui on 2017/5/24.
 */

public class PathBezierGarbageView extends View {

    Path mHeadPath;
    Path mBodyPath;
    Paint mPaint;
    int angle = 0;

    public PathBezierGarbageView(Context context) {
        super(context);
    }


    public PathBezierGarbageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getMeasuredWidth() > 0){
            if (mBodyPath == null){
                mBodyPath = new Path();
                mBodyPath.moveTo(getWidth()/10,getHeight()/3);
                mBodyPath.lineTo(getWidth()/5,getHeight());
                mBodyPath.lineTo(getWidth()*4/5,getHeight());
                mBodyPath.lineTo(getWidth()*9/10,getHeight()/3);
            }
             if (mPaint == null){
                 mPaint = new Paint();
                 mPaint.setAntiAlias(true);
                 mPaint.setStyle(Paint.Style.STROKE);
                 mPaint.setColor(Color.BLACK);
             }

             if (mHeadPath == null){
                 mHeadPath = new Path();
                 mHeadPath.moveTo(0,getHeight()/3);
                 mHeadPath.lineTo(getWidth(),getHeight()/3);
                 Path temp = new Path();
                 temp.moveTo(getWidth()*2/7,getHeight()/3);
                 temp.lineTo(getWidth()/3,getHeight()/4);
                 temp.lineTo(getWidth()*2/3,getHeight()/4);
                 temp.lineTo(getWidth()*5/7,getHeight()/3);
                 mHeadPath.addPath(temp);
             }
        }else {
            return;
        }

        canvas.drawPath(mBodyPath,mPaint);
        canvas.drawLine(getWidth()/3,getHeight(),getWidth()/3,getHeight()/3,mPaint);
        canvas.drawLine(getWidth()*2/3,getHeight(),getWidth()*2/3,getHeight()/3,mPaint);
        canvas.save();
        canvas.rotate(angle,getWidth()*9/10,getHeight()/3);
        canvas.drawPath(mHeadPath,mPaint);
        canvas.restore();

    }


    public void  startRotate(){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(30,0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angle = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.start();
    }
}
