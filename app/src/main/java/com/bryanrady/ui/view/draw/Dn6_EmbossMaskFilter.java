package com.bryanrady.ui.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 *  Xfermode实现圆角图片
 * Created by wqb on 2018/6/26.
 */

public class Dn6_EmbossMaskFilter extends View {

    private Paint mPaint;

    public Dn6_EmbossMaskFilter(Context context) {
        super(context);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(100,100,800,400);

        /**
         * Create an emboss maskfilter
         *
         * @param direction  指定光源的位置，长度为xxx的数组标量[x,y,z]
         * @param ambient    环境光的因子 （0~1），越接近0，环境光越暗
         * @param specular   镜面反射系数 越接近0，镜面反射越强
         * @param blurRadius 模糊半径 值越大，模糊效果越明显
         */
        mPaint.setMaskFilter(new EmbossMaskFilter(
                new float[]{1,1,1},
                0.5f,
                0.1f,
                80));

        canvas.drawRect(rectF,mPaint);
    }

}
