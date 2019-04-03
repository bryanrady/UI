package com.bryanrady.ui.view.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Canvas变化操作
 * Created by Administrator on 2019/3/22.
 */

public class CanvasTransferView extends View {

    /**
     * Canvas里面牵扯两种坐标系：Canvas自己的坐标系、绘图坐标系

     Canvas的坐标系，

            它就在View的左上角，做坐标原点往右是X轴正半轴，往下是Y轴的正半轴，有且只有一个，唯一不变

     绘图坐标系

            它不是唯一不变的，它与Canvas的Matrix有关系，当Matrix发生改变的时候，绘图坐标系对应的进行改变，
            同时这个过程是不可逆的（save和restore方法来保存和还原变化操作）
            Matrix又是通过我们设置translate、rotate、scale、skew来进行改变的

     */

    //https://www.jianshu.com/p/afa06f716ca6
    public CanvasTransferView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        transfer(canvas);
    }

    private void transfer(Canvas canvas){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);

        //1.先画一个原
        paint.setColor(Color.RED);
        canvas.drawCircle(200,200,200,paint);
        canvas.save();

        //2.canvas.translate(float dx, float dy) 对canvas dx、dy 方向进行canvas平移
        canvas.translate(200,200);
        paint.setColor(Color.BLUE);
        canvas.drawCircle(200,200,200,paint);
        canvas.save();

        //3.canvas.scale(float sx, float sy)
        // 是 x、y 方向上缩放的倍数,画布缩放后，再画出的图片相应的坐标都会进行缩放
        canvas.translate(200,200);
        canvas.scale(0.5f,0.5f);    //cx,cy,半径均缩小一半
        paint.setColor(Color.GREEN);
        canvas.drawCircle(200,200,200,paint);
        canvas.save();

        //canvas.scale (float sx, float sy, float px, float py)
        // 缩放画布并平移画布到基准点 (px,py)    px,py 为缩放后画布新的坐标原点(也叫缩放基准点)
        canvas.scale(0.5f,0.5f);    //x,y均缩小一半
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(0,0,400,paint);
        canvas.save();

        //4.canvas.rotate(float degrees)  顺时针旋转当前画布一定角度，也可加入基准点坐标
        canvas.restoreToCount(2);
        canvas.rotate(45);
        paint.setColor(Color.BLACK);
        canvas.drawRect(0,0,400,200,paint);

        //canvas.skew(float sx, float sy)  画布的错切
        //  sx:将画布在 x 方向上倾斜相应的角度，sx 为倾斜角度的 tan 值；
        //  sy:将画布在 y 轴方向上倾斜相应的角度，sy 为倾斜角度的 tan 值；
        //  比如在 X 轴方向上倾斜45度，tan45=1;
        canvas.skew(1,0);//画布X轴倾斜45度
        paint.setColor(Color.GRAY);
        canvas.drawRect(new RectF(0,0,180,180),paint);
        canvas.save();


    }
}
