package com.bryanrady.ui.view.recycler.layout_manager.douyin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * 时下最火的莫过抖音了，实现这个效果应该很简单嘛，用ViewPager就可以了。
 * 但是等你通过ViewPager来实现的时候，手机内存不够用的情况就会显现出来。
 *
 *
 * Created by Administrator on 2019/5/20.
 */
public class ViewPagerLayoutManager  extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {
    /**
     * 竖直方向的手指滑动增量
     *      手指向下滑 mDy < 0   手指向上滑 mDy > 0
     */
    private int mDrift;

    /**
     * https://www.jianshu.com/p/ef3a3b8d0a77   使用RecyclerView + SnapHelper实现类似ViewPager效果
     * 用RecyclerView 实现一个类似ViewPager的效果
     * PagerSnapHelper的展示效果和LineSnapHelper是一样的，只是PagerSnapHelper 限制一次只能滑动一页，不能快速滑动
     */
    private PagerSnapHelper mPagerSnapHelper;
    private OnViewPagerListener mOnViewPagerListener;

    public void setOnViewPagerListener(OnViewPagerListener mOnViewPagerListener) {
        this.mOnViewPagerListener = mOnViewPagerListener;
    }

    public ViewPagerLayoutManager(Context context) {
        this(context,LinearLayoutManager.VERTICAL,false);

    }

    public ViewPagerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        mPagerSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        view.addOnChildAttachStateChangeListener(this);
        mPagerSnapHelper.attachToRecyclerView(view);
        super.onAttachedToWindow(view);
    }

    @Override
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }


    /**
     * 要监听滑动到哪页 我们需要的就是RecyclerView停止时的状态，我们就可以拿到这个View的Position.
     * @param state
     */
    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE:
                View view = mPagerSnapHelper.findSnapView(this);
                int position = getPosition(view);
                if (mOnViewPagerListener != null) {
                    mOnViewPagerListener.onPageSelected(position, position == getItemCount() - 1);

                }
                break;
        }
        super.onScrollStateChanged(state);
    }

    /**
     * 当item被添加到屏幕中来时 会调用这个方法
     * @param view
     */
    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        Log.d("wangqingbin","onChildViewAttachedToWindow...");
        mToWindowView = view;
        if(mDrift == 0){
            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageSelected(getPosition(mToWindowView), false);
            }
        }
        //播放视频操作 即将要播放的是上一个视频 还是下一个视频
//        if (mDrift > 0) {
//            //向上
//            if (mOnViewPagerListener != null) {
//                mOnViewPagerListener.onPageSelected(getPosition(view), true);
//            }
//
//        }else {
//            if (mOnViewPagerListener != null) {
//                mOnViewPagerListener.onPageSelected(getPosition(view), false);
//            }
//        }
    }

    private View mToWindowView;

    /**
     * 当item从屏幕中移除时 会调用这个方法
     * @param view
     */
    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        Log.d("wangqingbin","onChildViewDetachedFromWindow...");
        if (mDrift >= 0){
            //向上
            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageSelected(getPosition(mToWindowView), true);
            }

            if (mOnViewPagerListener != null) mOnViewPagerListener.onPageRelease(getPosition(view),true);
        }else {

            if (mOnViewPagerListener != null) {
                mOnViewPagerListener.onPageSelected(getPosition(mToWindowView), false);
            }

            if (mOnViewPagerListener != null) mOnViewPagerListener.onPageRelease(getPosition(view),false);
        }
    }

}
