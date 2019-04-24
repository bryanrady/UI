package com.bryanrady.ui.view.svg;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;

/**
 * Created by Administrator on 2019/4/23.
 */

public class ProvinceItem {
    /**
     * 省份的path路径
     */
    private Path path;
    /**
     * 省份的绘制颜色
     */
    private int drawColor;

    public void setPath(Path path) {
        this.path = path;
    }

    public void setDrawColor(int drawColor) {
        this.drawColor = drawColor;
    }

    public void drawProvinceItem(Canvas canvas, Paint paint, boolean isSelect){
        //先画边界
        paint.setStrokeWidth(2);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(8,0,0,0xffffff);
        canvas.drawPath(path,paint);

        //然后填充
        paint.clearShadowLayer();
        paint.setColor(drawColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        canvas.drawPath(path, paint);

        //如果选中，就直接绘制填充就行了
        if(isSelect){
            paint.clearShadowLayer();
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.FILL);
            paint.setStrokeWidth(2);
            canvas.drawPath(path, paint);
        }
    }

    /**
     * 判断当前是不是在省份的区域内
     * @param x
     * @param y
     * @return
     */
    public boolean isTouch(float x, float y) {
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);    //计算Path的边界
//        rectF   矩形  包含了Path
        Region region = new Region();
        //在path和矩形之间取交集，所以这个交集就是整个省份的区域
        region.setPath(path, new Region((int)rectF.left, (int)rectF.top,(int)rectF.right, (int)rectF.bottom));
        return region.contains((int)x,(int)y);
    }

}
