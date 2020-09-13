package com.bryanrady.ui.view.paint.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  组合渲染        发挥想象自由组合
 *          ComposeShader extends Shader
 *
 *      实现心形图片颜色渐变效果
 *
 * Created by wqb on 2018/6/26.
 */

public class ComposeShaderView extends View {

    private Bitmap mBitmap;
    private int mWidth;
    private int mHeight;
    private Paint mPaint;

    public ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Drawable drawable = getResources().getDrawable(R.drawable.heart);
        BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
        mBitmap = bitmapDrawable.getBitmap();
        mWidth = mBitmap.getWidth();
        mHeight = mBitmap.getHeight();
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         *  ComposeShader(@NonNull Shader shaderA, @NonNull Shader shaderB,@NonNull PorterDuff.Mode mode)
         */
        /**
         * ComposeShader(@NonNull Shader shaderA, @NonNull Shader shaderB, @NonNull Xfermode mode)
         */

        //创建BitmapShader，用以绘制心
        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //创建LinearGradient，用以产生从左上角到右下角的颜色渐变效果
        LinearGradient linearGradient = new LinearGradient(0,0,mWidth,mHeight, Color.RED, Color.GREEN, Shader.TileMode.REPEAT);
        //bitmapShader对应目标像素，linearGradient对应源像素，像素颜色混合采用MULTIPLY模式
        ComposeShader composeShader = new ComposeShader(bitmapShader,linearGradient, PorterDuff.Mode.MULTIPLY);
        mPaint.setShader(composeShader);

        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
    }
}
