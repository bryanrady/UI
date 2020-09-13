package com.bryanrady.ui.view.paint.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 环形渲染
 *      RadialGradient extends Shader
 *      水波纹效果
 */

public class RadialGradientView extends View {

    private Paint mPaint;
    private int[] mColors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
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
         * RadialGradient(float centerX, float centerY, float radius,
         *      @NonNull @ColorInt int colors[], @Nullable float stops[],
         *      @NonNull TileMode tileMode)
         */
        RadialGradient radialGradient = new RadialGradient(getWidth()/2, getHeight()/2, 100, mColors,null, Shader.TileMode.REPEAT);
        mPaint.setShader(radialGradient);

        canvas.drawCircle(getWidth()/2, getHeight()/2, 300, mPaint);

    }
}
