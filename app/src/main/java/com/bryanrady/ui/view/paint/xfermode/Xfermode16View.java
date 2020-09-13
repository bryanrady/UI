package com.bryanrady.ui.view.paint.xfermode;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 *  Paint高级部分   图形混合n
 *    一 、 Xfermode
 *          通过使用Xfermode将绘制的图形的像素和Canvas上对应位置的像素按照一定的规则进行混合，
 *          得到新的像素，再将这个像素更新到Canvas上形成新的图形
 *
 *         我们一个像素的颜色都是由四个分量组成，即ARGB，A表示的是我们Alpha值，RGB表示的是颜色
 *
 *         S表示的是源像素，源像素的值表示[Sa,Sc] Sa表示的就是源像素的Alpha值，Sc表示源像素的颜色值
 *
 *         D表示的是目标像素，目标像素的值表示[Da,Dc] Da表示的就是目标像素的Alpha值, Dc表示目标像素的颜色值
 *
 *  二、混合模式分类
 *
 *      1. SRC类     ----   优先显示的是源图片    也就是受影响的是原图片
 *
 *         （1）    SRC   [Sa, Sc]
 *                      ----  处理图片相交区域时，总是显示的是原图片
 *
 *          (2)     SRC_IN   [Sa * Da, Sc * Da]
 *                      ----  处理图片相交区域时，受到目标图片的Alpha值影响,当我们的目标图片为空白像素的时候，
 *                          原图片也会变成空白,简单的来说就是用目标图片的透明度来改变源图片的透明度和饱和度，
 *                          当目标图片的透明度为0时，源图片就不会显示示例：圆角头像 、倒影图片
 *
 *         （3）    SRC_OUT   [Sa * (1 - Da), Sc * (1 - Da)] --- 同SRC_IN类似  (1 - Da)
 *                      ----  用我们目标图片的透明度的补值来改变源图片的透明度和饱和度,当目标图片的透明度
 *                            为不透明时，源图片就不会显示
 *                      示例：橡皮擦效果，   刮刮卡效果
 *                                  目标图片 --- 手势的轨迹
 *                                  源图片 --- 擦除的图片
 *
 *
 *         （4）    SRC_ATOP  [Da, Sc * Da + (1 - Sa) * Dc]
 *                      ----   当目标图片透明度为 100% 和 0% 时，SRC_IN 模式 和 SRC_ATOP是效果是一样的;
 *                            当目标图片透明度不为上述的两个值时，SRC_ATOP  比 SRC_IN  源图像的饱和度会增加，变得更亮一些
 *                      示例：用SRC_ATOP来实现  圆角头像 、倒影图片 对比一下SRC_IN
 *
 *          (5)     SRC_OVER
 *
 *      2、DST类    ----  优先显示的是目标图片      也就是受影响的是目标图片
 *
 *          （1）   DST
 *
 *          （2）    DST_IN   [Sa * Da, Sa * Dc]
 *                      ----    对比一下SRC_IN，正好和我们SRC_IN相反，在相交的时候以源图片的透明度来改变目标图片的透明度和饱和度
 *                              当源图片的透明度为0的时候，目标图片完全不显示
 *                      示例：心电图效果，不规则水波纹效果，当然也可以做SRC_IN 的效果（注意选择谁为源图片，谁为目标图片）
 *                          心电图效果-
 *                              目标图片 ---心电图
 *                              源图片 ---- 不透明的图 就是通过改变透明图片的不透明区域的宽度，来实现心电图的动画效果
 *
 *          （3）   DST_OUT
 *
 *          （4）   DST_ATOP
 *
 *          （5）   DST_OVER
 *
 *      3、其他模式的叠加效果
 *
 *           (1)  CLEAR
 *
 *           (2)  XOR
 *
 *           (3)  DARKEN
 *
 *           (4)  LIGHTEN
 *                  --- 变亮  应用： 书架 头顶灯光变亮效果
 *
 *           (5)  MULTIPLY    [Sa * Da, Sc * Dc]
 *                  ---  应用：可以把图片的轮廓取出来
 *
 *           (6)  SCREEN
 *
 *           //这两个好像是刚加的
 *           (7)  ADD
 *
 *           (8)  OVERLAY
 *
 *           PorterDuff.Mode
 */
public class Xfermode16View extends View {

    private Paint mPaint;
    float mItemSize = 0;
    float mItemHorizontalOffset = 0;
    float mItemVerticalOffset = 0;
    float mCircleRadius = 0;
    float mRectSize = 0;
    float mTextSize = 25;
    int mCircleColor = 0xffffcc44;  //黄色
    int mRectColor = 0xff66aaff;    //蓝色

    private static final Xfermode[] mXferModes = {
            new PorterDuffXfermode(PorterDuff.Mode.CLEAR),
            new PorterDuffXfermode(PorterDuff.Mode.SRC),
            new PorterDuffXfermode(PorterDuff.Mode.DST),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OVER),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_IN),
            new PorterDuffXfermode(PorterDuff.Mode.DST_IN),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT),
            new PorterDuffXfermode(PorterDuff.Mode.DST_OUT),
            new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP),
            new PorterDuffXfermode(PorterDuff.Mode.XOR),
            new PorterDuffXfermode(PorterDuff.Mode.DARKEN),
            new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN),
            new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY),
            new PorterDuffXfermode(PorterDuff.Mode.SCREEN)
    };

    private static final String[] mLabels = {
            "Clear", "Src", "Dst", "SrcOver",
            "DstOver", "SrcIn", "DstIn", "SrcOut",
            "DstOut", "SrcATop", "DstATop", "Xor",
            "Darken", "Lighten", "Multiply", "Screen"
    };

    public Xfermode16View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if(Build.VERSION.SDK_INT >= 11){
            //开启硬件加速
            setLayerType(LAYER_TYPE_SOFTWARE,null);
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        mPaint.setTextSize(mTextSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        for(int row = 0; row < 4; row++){
            for(int column = 0; column < 4; column++){
                canvas.save();
                int layer = canvas.saveLayer(0, 0, canvasWidth, canvasHeight, null, Canvas.ALL_SAVE_FLAG);
                mPaint.setXfermode(null);
                int index = row * 4 + column;
                float translateX = (mItemSize + mItemHorizontalOffset) * column;
                float translateY = (mItemSize + mItemVerticalOffset) * row;
                canvas.translate(translateX, translateY);
                //画文字
                String text = mLabels[index];
                mPaint.setColor(Color.BLACK);
                float textXOffset = mItemSize / 2;
                float textYOffset = mTextSize + (mItemVerticalOffset - mTextSize) / 2;
                canvas.drawText(text, textXOffset, textYOffset, mPaint);
                canvas.translate(0, mItemVerticalOffset);
                //画边框
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(0xff000000);
                canvas.drawRect(2, 2, mItemSize - 2, mItemSize - 2, mPaint);
                mPaint.setStyle(Paint.Style.FILL);

                //蓝色矩形表示的是原图片，黄色圆表示的是目标图片
                //画圆
                mPaint.setColor(mCircleColor);
                float left = mCircleRadius + 3;
                float top = mCircleRadius + 3;
                canvas.drawCircle(left, top, mCircleRadius, mPaint);
                mPaint.setXfermode(mXferModes[index]);
                //画矩形
                mPaint.setColor(mRectColor);
                float rectRight = mCircleRadius + mRectSize;
                float rectBottom = mCircleRadius + mRectSize;
                canvas.drawRect(left, top, rectRight, rectBottom, mPaint);
                mPaint.setXfermode(null);
                //canvas.restore();
                canvas.restoreToCount(layer);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mItemSize = w / 4.5f;
        mItemHorizontalOffset = mItemSize / 6;
        mItemVerticalOffset = mItemSize * 0.426f;
        mCircleRadius = mItemSize / 3;
        mRectSize = mItemSize * 0.6f;
    }
}
