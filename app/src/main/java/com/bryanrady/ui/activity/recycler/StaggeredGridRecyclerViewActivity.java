package com.bryanrady.ui.activity.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.recycler.adapter.StaggeredGridRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * http://www.cnblogs.com/tangs/articles/5914646.html
 * Created by wangqingbin on 2019/4/4.
 */

public class StaggeredGridRecyclerViewActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private StaggeredGridRecyclerAdapter mAdapter;
    private StaggeredGridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_staggered_grid);
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
        mRecyclerView = findViewById(R.id.staggered_grid_recycler_view);
        mAdapter = new StaggeredGridRecyclerAdapter(this,getData());
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter.setOnItemClickListener(new StaggeredGridRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggeredGridRecyclerViewActivity.this,"click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(StaggeredGridRecyclerViewActivity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });
    }

}