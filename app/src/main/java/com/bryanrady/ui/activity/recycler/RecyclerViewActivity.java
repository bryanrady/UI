package com.bryanrady.ui.activity.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class RecyclerViewActivity extends StatusBarBaseActivity implements View.OnClickListener {

    private Button btn_recycler_recyclerView;
    private Button btn_recycler_staggered_grid;
    private Button btn_recycler_linear_layout;
    private Button btn_recycler_grid_layout;
    private Button btn_recycler_custom_layout_manager;
    private Button btn_recycler_slide_delete;
    private Button btn_recycler_drag_switch;
    private Button btn_recycler_qq_slide_delete;
    private Button btn_recycler_pull_refresh;
    private Button btn_recycler_nesting_slide_conflict;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_base);
        initView();
    }

    private void initView() {

        btn_recycler_recyclerView = (Button) findViewById(R.id.btn_recycler_recyclerView);
        btn_recycler_recyclerView.setOnClickListener(this);

        btn_recycler_staggered_grid = (Button) findViewById(R.id.btn_recycler_staggered_grid);
        btn_recycler_staggered_grid.setOnClickListener(this);

        btn_recycler_linear_layout = (Button) findViewById(R.id.btn_recycler_linear_layout);
        btn_recycler_linear_layout.setOnClickListener(this);

        btn_recycler_grid_layout = (Button) findViewById(R.id.btn_recycler_grid_layout);
        btn_recycler_grid_layout.setOnClickListener(this);

        btn_recycler_custom_layout_manager = (Button) findViewById(R.id.btn_recycler_custom_layout_manager);
        btn_recycler_custom_layout_manager.setOnClickListener(this);

        btn_recycler_slide_delete = (Button) findViewById(R.id.btn_recycler_slide_delete);
        btn_recycler_slide_delete.setOnClickListener(this);

        btn_recycler_drag_switch = (Button) findViewById(R.id.btn_recycler_drag_switch);
        btn_recycler_drag_switch.setOnClickListener(this);

        btn_recycler_qq_slide_delete = (Button) findViewById(R.id.btn_recycler_qq_slide_delete);
        btn_recycler_qq_slide_delete.setOnClickListener(this);

        btn_recycler_pull_refresh = (Button) findViewById(R.id.btn_recycler_pull_refresh);
        btn_recycler_pull_refresh.setOnClickListener(this);

        btn_recycler_nesting_slide_conflict = (Button) findViewById(R.id.btn_recycler_nesting_slide_conflict);
        btn_recycler_nesting_slide_conflict.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_recycler_recyclerView:
                intent.setClass(this, CustomRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_staggered_grid:
                intent.setClass(this, StaggeredGridRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_linear_layout:
                intent.setClass(this, ItemAnimatorRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_grid_layout:
                intent.setClass(this, ItemDecorationRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_custom_layout_manager:
                intent.setClass(this, LayoutManagerRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_slide_delete:
                intent.setClass(this, SlideDeleteRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_drag_switch:
                intent.setClass(this, DragSwitchRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_qq_slide_delete:
                intent.setClass(this, QQSlideRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_pull_refresh:
                intent.setClass(this, PullRefreshRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_nesting_slide_conflict:
                intent.setClass(this, SlideConflictRecyclerViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}