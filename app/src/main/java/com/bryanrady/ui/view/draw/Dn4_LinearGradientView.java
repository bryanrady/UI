package com.bryanrady.ui.view.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *  LinearGradient extends Shader
 *      线性渲染 ，用Bitmap对绘制的图形进行渲染着色，简单来说是用图片对图形进行贴图
 *
 *      场景：霓虹灯文字，倒影图片
 *
 */

public class Dn4_LinearGradientView extends View {

    private Paint mPaint;
    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public Dn4_LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        /**线性渐变
         *  x0, y0, 起始点
         *  x1, y1, 结束点
         * int[]  mColors, 中间依次要出现的几个颜色
         * float[] positions,数组大小跟colors数组一样大，中间依次摆放的几个颜色分别放置在那个位置上(参考比例从左往右)
         *    tile
         */

        LinearGradient linearGradient = new LinearGradient(
                0,0,600,400,mColors,null, Shader.TileMode.REPEAT
        );
        mPaint.setShader(linearGradient);

        canvas.drawRect(0,0,800,800,mPaint);
    }
}
