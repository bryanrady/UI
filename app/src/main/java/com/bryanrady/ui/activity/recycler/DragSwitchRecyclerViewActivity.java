package com.bryanrady.ui.activity.recycler;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.recycler.adapter.DragSwitchRecyclerViewAdapter;
import com.bryanrady.ui.activity.recycler.adapter.SlideDeleteRecyclerViewAdapter;
import com.bryanrady.ui.activity.recycler.decoration.LinearRecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/26.
 */

public class DragSwitchRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private DragSwitchRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelper.Callback mCallBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_drag_switch);
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

        mRecyclerView = findViewById(R.id.drag_switch_recycler_view);
        mAdapter = new DragSwitchRecyclerViewAdapter(this, getData());
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new GridLayoutManager(this,3, GridLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

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
                 *      第一个参数是拖拽的标记，    在这里我设置可从左上右下四个方向进行拖拽Item
                 *      第二个参数是滑动的标记。    在这里我设置不可滑动Item（传入0）。
                 */
                return makeMovementFlags(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP, 0);
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
                mAdapter.dragMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                return true;
            }

            /**
             * 滑动删除Item的操作
             * @param viewHolder
             * @param direction     Item滑动的方向
             *
             */
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }

            /**
             * 设置Item是否支持长按拖动
             * @return  true  支持长按操作    false 不支持长按操作
             */
            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            /**
             * 设置Item是否支持滑动
             * @return  true  支持滑动操作    false 不支持滑动操作
             */
            @Override
            public boolean isItemViewSwipeEnabled() {
                return false;
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
                    viewHolder.itemView.setBackgroundResource(R.color.blue);
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
                // 操作完毕后恢复颜色
                viewHolder.itemView.setBackgroundResource(R.drawable.md_common_bg);
                super.clearView(recyclerView, viewHolder);
            }
        };
    }

}
