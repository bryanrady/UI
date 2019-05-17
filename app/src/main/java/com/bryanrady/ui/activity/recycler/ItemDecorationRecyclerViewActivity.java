package com.bryanrady.ui.activity.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.recycler.adapter.GridRecyclerViewAdapter;
import com.bryanrady.ui.view.recycler.decoration.GridRecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class ItemDecorationRecyclerViewActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private GridRecyclerViewAdapter mAdapter;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid_layout);
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
        mRecyclerView = findViewById(R.id.grid_recycler_view);
        mAdapter = new GridRecyclerViewAdapter(this, getData());
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new GridLayoutManager(this,3);
    //    mLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.addItemDecoration(new GridRecyclerItemDecoration(this));

        mAdapter.setOnItemClickListener(new GridRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ItemDecorationRecyclerViewActivity.this,"click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(ItemDecorationRecyclerViewActivity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });
    }

}