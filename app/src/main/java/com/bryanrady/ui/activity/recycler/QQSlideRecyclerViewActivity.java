package com.bryanrady.ui.activity.recycler;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;
import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.recycler.adapter.QQSlideRecyclerViewAdapter;
import com.bryanrady.ui.view.recycler.decoration.LinearRecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿qq侧滑菜单
 * https://blog.csdn.net/weixin_41454168/article/details/79608594
 */

public class QQSlideRecyclerViewActivity extends AppCompatActivity
        implements QQSlideRecyclerViewAdapter.OnSlidingViewClickListener {

    private RecyclerView mRecyclerView;
    private QQSlideRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_qq_slide_layout);
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

        mRecyclerView = findViewById(R.id.qq_slide_recycler_view);
        if(mAdapter == null){
            mAdapter = new QQSlideRecyclerViewAdapter(this, getData());
            mRecyclerView.setAdapter(mAdapter);

            mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            mRecyclerView.addItemDecoration(new LinearRecyclerItemDecoration(this));
        }else{
            mAdapter.notifyDataSetChanged();
        }

        mAdapter.setOnSlidindViewClickListener(this);

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(QQSlideRecyclerViewActivity.this,"click item ：" + position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        Toast.makeText(QQSlideRecyclerViewActivity.this,"long click item ：" + position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSettingClick(View view, int position) {
        Toast.makeText(QQSlideRecyclerViewActivity.this,"click setting ：" + position,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemDeleteClick(View view, int position) {
        mAdapter.removeData(position);
    }

    @Override
    public void onItemReadClick(View view, int position) {
        Toast.makeText(QQSlideRecyclerViewActivity.this,"click read ：" + position,Toast.LENGTH_SHORT).show();
    }
}
