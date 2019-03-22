package com.bryanrady.ui.view.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.bryanrady.ui.R;

/**
 *  实现图片放大镜效果
 * Created by wqb on 2018/6/26.
 */

public class Dn4_ZoomImageView extends View {

    //放大倍数
    private static final int mScaledFactor = 2;
    //放大镜的半径
    private static final int RADIUS  = 200;

    private Matrix mMatrix;

    // 制作的圆形的图片（放大的局部），盖在Canvas上面
    private ShapeDrawable mShapeDrawable;

    private Bitmap mBitmap;
    private Bitmap mScaledBitmap;    //放大后的Bitmap

    public Dn4_ZoomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.xyjy3,null);

        mMatrix = new Matrix();

        //图片缩放
        mScaledBitmap = Bitmap.createScaledBitmap(mBitmap,
                mBitmap.getWidth() * mScaledFactor,
                mBitmap.getHeight() * mScaledFactor,
                true);

        BitmapShader bitmapShader = new BitmapShader(mScaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mShapeDrawable = new ShapeDrawable(new OvalShape());
        mShapeDrawable.getPaint().setShader(bitmapShader);
        // 切出矩形区域，用来画圆（内切圆）
        mShapeDrawable.setBounds(0,0,RADIUS * 2,RADIUS * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 1、画原图
        canvas.drawBitmap(mBitmap, 0 , 0 , null);

        // 2、画放大镜的图 放大镜上的图就是一个圆形图形，只不过把图片放大了而已
        mShapeDrawable.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) event.getX();
        int y = (int) event.getY();

        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();

        //让放大镜不跑到外面去
        if( (x>RADIUS && x+RADIUS<width) && (y>RADIUS && y+RADIUS<height) ){
            // 将放大的图片往相反的方向挪动
            mMatrix.setTranslate(RADIUS - x * mScaledFactor, RADIUS - y * mScaledFactor);
            mShapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);

            // 切出手势区域点位置的圆
            mShapeDrawable.setBounds(x-RADIUS,y - RADIUS, x + RADIUS, y + RADIUS);

            invalidate();
            return true;
        }

        return false;
    }
}
