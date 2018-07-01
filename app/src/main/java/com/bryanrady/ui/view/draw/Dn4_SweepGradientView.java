package com.bryanrady.ui.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *  渐变渲染/梯度渲染
 *      SweepGradient extends Shader
 *
 *               雷达扫描效果, 钉钉签到图片初始化定位雷达效果
 *
 */

public class Dn4_SweepGradientView extends View {

    private Paint mPaint;
    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public Dn4_SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        /**
         *  SweepGradient(float cx, float cy,@NonNull @ColorInt int colors[], @Nullable float positions[])
         */
        SweepGradient sweepGradient = new SweepGradient(getWidth()/2,getHeight()/2,mColors,null);
        mPaint.setShader(sweepGradient);
        canvas.drawCircle(getWidth()/2,getHeight()/2,300,mPaint);
    }
}
