package com.bryanrady.ui.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;

import com.bryanrady.ui.R;

/**
 * Created by Administrator on 2019/5/14.
 */

public class QQHeaderSrollListView extends ListView {

    private ImageView mImageView;
    private int mImageHeight;

    public QQHeaderSrollListView(Context context) {
        super(context);
    }

    public QQHeaderSrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mImageHeight = context.getResources().getDimensionPixelSize(R.dimen.dp_160);
        Log.d("wangqingbin","mImageHeight=="+mImageHeight);
    }

    public void setZoomImageView(ImageView imageView) {
        this.mImageView = imageView;
    }

    class ResetAnimation extends Animation {

        private int extraHeight;
        private int currentHeight;

        public ResetAnimation(int targetHeight) {
            currentHeight = mImageView.getHeight();
            extraHeight = mImageView.getHeight() - targetHeight;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            mImageView.getLayoutParams().height = (int) (currentHeight - extraHeight * interpolatedTime);
            mImageView.requestLayout();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if(action == MotionEvent.ACTION_UP){    //当手指离开的时候要恢复原来的样子
            ResetAnimation resetAnimation = new ResetAnimation(mImageHeight);
            resetAnimation.setInterpolator(new OvershootInterpolator());
            resetAnimation.setDuration(1000);
            mImageView.startAnimation(resetAnimation);
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.d("wangqingbin","overScrollBy..........");
        if(deltaY < 0){
            //            -  下拉

            //改变image的高度
            mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
            mImageView.requestLayout();
        }else{
            //            + 上拉

            mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
            mImageView.requestLayout();
        }


        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        View header = (View) mImageView.getParent();
        Log.i("wangqingbin", "onSizeChanged: header.getTop() == " + header.getTop());

        //ListView会滑出去的高度（负数）
        int deltaY = header.getTop();

//        只有image的高度大于 图片原始的高度   那我们就缩小 相当于隐藏了
        if (mImageView.getHeight() > mImageHeight) {
            mImageView.getLayoutParams().height = mImageView.getHeight() + deltaY;
            //header的距离屏幕顶部位置永远是0
            header.layout(header.getLeft(), 0, header.getRight(), header.getHeight());
            mImageView.requestLayout();
        }
    }

}
