package com.bryanrady.ui.view.canvas;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2019/3/22.
 */

public class CanvasTranslateView extends View {

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
    public CanvasTranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
