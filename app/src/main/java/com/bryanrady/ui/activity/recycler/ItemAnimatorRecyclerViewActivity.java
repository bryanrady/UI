package com.bryanrady.ui.activity.recycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.recycler.adapter.LinearRecyclerViewAdapter;
import com.bryanrady.ui.view.recycler.animator.AlphaItemAnimator;
import com.bryanrady.ui.view.recycler.animator.RotateItemAnimator;
import com.bryanrady.ui.view.recycler.animator.ScaleItemAnimator;
import com.bryanrady.ui.view.recycler.animator.SlideItemAnimator;
import com.bryanrady.ui.view.recycler.decoration.LinearRecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class ItemAnimatorRecyclerViewActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn_add_item,btn_delete_item,btn_update_item;
    private Button btn_alpha_animator_item,btn_rotate_animator_item,
            btn_scale_animator_item,btn_slide_animator_item;
    private RecyclerView mRecyclerView;
    private LinearRecyclerViewAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_linear_layout);
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
        btn_add_item = findViewById(R.id.btn_add_item);
        btn_delete_item = findViewById(R.id.btn_delete_item);
        btn_update_item = findViewById(R.id.btn_update_item);
        btn_alpha_animator_item = findViewById(R.id.btn_alpha_animator_item);
        btn_rotate_animator_item = findViewById(R.id.btn_rotate_animator_item);
        btn_scale_animator_item = findViewById(R.id.btn_scale_animator_item);
        btn_slide_animator_item = findViewById(R.id.btn_slide_animator_item);

        btn_add_item.setOnClickListener(this);
        btn_delete_item.setOnClickListener(this);
        btn_update_item.setOnClickListener(this);
        btn_alpha_animator_item.setOnClickListener(this);
        btn_rotate_animator_item.setOnClickListener(this);
        btn_scale_animator_item.setOnClickListener(this);
        btn_slide_animator_item.setOnClickListener(this);


        mRecyclerView = findViewById(R.id.linear_recycler_view);
        mAdapter = new LinearRecyclerViewAdapter(this,getData(),LinearLayoutManager.VERTICAL);
    //    mAdapter = new LinearRecyclerViewAdapter(this,getData(),LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setAdapter(mAdapter);

        // 设置布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    //    mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // 设置Item添加、移除、更新时的动画   https://www.jianshu.com/p/b9aef3597f2d
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 设置Item之间间隔样式  分割线    https://www.jianshu.com/p/7c3c549a0ec4
        mRecyclerView.addItemDecoration(new LinearRecyclerItemDecoration(this));
     //   mRecyclerView.addItemDecoration(new LinearRecyclerItemDecoration(this,LinearLayoutManager.HORIZONTAL));

        mAdapter.setOnItemClickListener(new LinearRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ItemAnimatorRecyclerViewActivity.this,"click " + position + " item", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(ItemAnimatorRecyclerViewActivity.this,"long click " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_item:
                mAdapter.addItem();
                mLayoutManager.scrollToPosition(0);
                break;
            case R.id.btn_delete_item:
                mAdapter.deleteItem();
                mLayoutManager.scrollToPosition(0);
                break;
            case R.id.btn_update_item:
                mAdapter.updateItem();
                mLayoutManager.scrollToPosition(1);
                break;
            case R.id.btn_alpha_animator_item:
                mRecyclerView.setItemAnimator(new AlphaItemAnimator());
                break;
            case R.id.btn_rotate_animator_item:
                mRecyclerView.setItemAnimator(new RotateItemAnimator());
                break;
            case R.id.btn_scale_animator_item:
                mRecyclerView.setItemAnimator(new ScaleItemAnimator());
                break;
            case R.id.btn_slide_animator_item:
                mRecyclerView.setItemAnimator(new SlideItemAnimator());
                break;
        }
    }

}