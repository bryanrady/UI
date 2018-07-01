package com.bryanrady.ui.view.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bryanrady.ui.R;


/**
 *  Shader
 *      着色器，Canvas的drawXXXX这个方法是画具体的形状，
 *      Paint的shader定义的就是图形的着色和外观
 *
 *  BitmapShader extends Shader
 *      位图渲染，用Bitmap对绘制的图形进行渲染着色，简单来说是用图片对图形进行贴图
 *
 *      场景：圆形头像，放大镜效果
 *
 *      TileMode 拉伸形式
 *          CLAMP   拉伸最后一个像素铺满屏幕
 *
 *          MIRROR  横向纵向不足处不断翻转镜像平铺
 *
 *          REPEAT  类似电脑壁纸，横向纵向不足的重复放置
 */

public class Dn4_BitmapShaderView extends View {

    private Bitmap mBitmap;
    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public Dn4_BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy2,null);
        mPaint = new Paint();
        mWidth = mBitmap.getWidth()/2;
        mHeight = mBitmap.getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        //白色背景
        canvas.drawColor(Color.WHITE);

        /**
         * TileMode.CLAMP 拉伸最后一个像素去铺满剩下的地方
         * TileMode.MIRROR 通过镜像翻转铺满剩下的地方。
         * TileMode.REPEAT 重复图片平铺整个画面（电脑设置壁纸）
         */

    //    BitmapShader(@NonNull Bitmap bitmap, @NonNull TileMode tileX, @NonNull TileMode tileY)

        BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.MIRROR);
        mPaint.setShader(bitmapShader);

        //可以多试一下很多宽高不一样的图片
        //可以设置像素矩阵设置图片缩放来解决图片宽高不一致的问题
        float scale = Math.max(mWidth,mHeight) / Math.min(mWidth,mHeight);
        Matrix matrix = new Matrix();
        matrix.setScale(scale,scale);
        bitmapShader.setLocalMatrix(matrix);

        //画圆形头像
        canvas.drawCircle(400,400, 400,mPaint);
    //    canvas.drawOval(new RectF(0 , 0, 600, 800),mPaint);
        //canvas.drawRect(new Rect(0,0 , 1000, 1600),mPaint);


        //2. 圆形头像还可以用ShapeDrawable来实现
//        ShapeDrawable shapeDrawable = new ShapeDrawable();
//        shapeDrawable.getPaint().setShader(bitmapShader);
//        //设置一个矩形区域，然后会在矩形里面画内切椭圆
//        shapeDrawable.setBounds(0,0,mWidth,mHeight);
//        shapeDrawable.draw(canvas);


    }
}
