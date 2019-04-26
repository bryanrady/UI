package com.bryanrady.ui.activity.recycler;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.recycler.adapter.SlideDeleteRecyclerViewAdapter;
import com.bryanrady.ui.activity.recycler.decoration.LinearRecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.jianshu.com/p/2f3813f27236
 *
 * ItemTouchHelper  ItemToucheHelper.CallBack
 *
 *      主要用于对RecyclerView滑动删除和拖拽切换提供支持，它支持所有的类型的布局管理器LayoutManager。
 *      它只有一个构造函数，必须传入ItemToucheHelper.CallBack这个类的实例化对象。ItemToucheHelper.CallBack它可以控制
 *      用户的哪些行为是可以操控列表的Item的，当用户进行了这些操作，它会收到回调
 *
 */

public class SlideDeleteRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SlideDeleteRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelper.Callback mCallBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_slide_delete);
        init();
    }

    private List<String> getData() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item ";
        for(int i = 0; i < 50; i++) {
            data.add(i + temp);
        }
        return data;
    }

    private void init() {

        mRecyclerView = findViewById(R.id.slide_delete_recycler_view);
        mAdapter = new SlideDeleteRecyclerViewAdapter(this, getData());
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.addItemDecoration(new LinearRecyclerItemDecoration(this));

        initCallBack();

        mItemTouchHelper = new ItemTouchHelper(mCallBack);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    private void initCallBack(){
        mCallBack = new ItemTouchHelper.Callback() {

            /**
             *  设置滑动类型标记
             * @param recyclerView
             * @param viewHolder
             * @return  返回一个整数类型的标识，用于判断Item那种移动行为是允许的
             */
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                /**
                 * makeMovementFlags(int dragFlags, int swipeFlags)     它用于创建一个滑动类型标记
                 *      第一个参数是拖拽的标记，    在这里我设置不可拖拽Item（传入0）
                 *      第二个参数是滑动的标记。    可进行左右滑动Item
                 */
                return makeMovementFlags(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
                //试了这两个 没什么区别
            //    return makeMovementFlags(0,ItemTouchHelper.START | ItemTouchHelper.END);
            }

            /**
             * 拖拽切换Item的回调
             * @param recyclerView
             * @param viewHolder
             * @param target
             * @return  如果Item切换了位置，返回true；反之，返回false
             */
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            /**
             * 滑动删除Item的操作
             * @param viewHolder
             * @param direction     Item滑动的方向
             *
             */
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mAdapter.slideDelete(viewHolder.getAdapterPosition());
            }

            /**
             * 设置Item是否支持长按拖动
             * @return  true  支持长按操作    false 不支持长按操作
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return false;
            }

            /**
             * 设置Item是否支持滑动
             * @return  true  支持滑动操作    false 不支持滑动操作
             */
            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            /**
             * Item被选中时候回调 可以在这里做选中的操作
             * @param viewHolder
             * @param actionState   当前Item的状态
             *          ItemTouchHelper.ACTION_STATE_IDLE   闲置状态
             *          ItemTouchHelper.ACTION_STATE_SWIPE  滑动中状态
             *          ItemTouchHelper#ACTION_STATE_DRAG   拖拽中状态
             */
            @Override
            public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
                //  这里我们做当item被选中的时候，给item设置背景颜色
                if(actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundResource(R.color.green);
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 在移动过程中绘制Item
             * @param c
             * @param recyclerView
             * @param viewHolder
             * @param dX            X轴移动的距离
             * @param dY            Y轴移动的距离
             * @param actionState   当前Item的状态
             * @param isCurrentlyActive 如果当前被用户操作为true，反之为false
             */
            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                //随着Item滑动的距离，设置Item的透明度
                float x = Math.abs(dX) + 0.5f;
                float width = viewHolder.itemView.getWidth();
                float alpha = 1f - x / width;
                viewHolder.itemView.setAlpha(alpha);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            /**
             * 在移动过程中绘制Item
             * @param c
             * @param recyclerView
             * @param viewHolder
             * @param dX            X轴移动的距离
             * @param dY            Y轴移动的距离
             * @param actionState   当前Item的状态
             * @param isCurrentlyActive 如果当前被用户操作为true，反之为false
             */
            @Override
            public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                        RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                        int actionState, boolean isCurrentlyActive) {
                super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

            /**
             * 用户操作完毕或者动画完毕后会被调用
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                // 操作完毕后恢复背景颜色和透明度
                viewHolder.itemView.setBackgroundResource(R.color.blue);
                viewHolder.itemView.setAlpha(1.0f);
                super.clearView(recyclerView, viewHolder);
            }
        };
    }

}
