package com.bryanrady.ui.activity.recycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.path.bezier.PathBezierActivity;
import com.bryanrady.ui.activity.path.path_measure.PathMeasureActivity;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_recycler_linear_layout;
    private Button btn_recycler_grid_layout;
    private Button btn_recycler_staggered_grid;
    private Button btn_recycler_slide_delete;
    private Button btn_recycler_drag_switch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_base);
        initView();
    }

    private void initView() {

        btn_recycler_linear_layout = (Button) findViewById(R.id.btn_recycler_linear_layout);
        btn_recycler_linear_layout.setOnClickListener(this);

        btn_recycler_grid_layout = (Button) findViewById(R.id.btn_recycler_grid_layout);
        btn_recycler_grid_layout.setOnClickListener(this);

        btn_recycler_staggered_grid = (Button) findViewById(R.id.btn_recycler_staggered_grid);
        btn_recycler_staggered_grid.setOnClickListener(this);

        btn_recycler_slide_delete = (Button) findViewById(R.id.btn_recycler_slide_delete);
        btn_recycler_slide_delete.setOnClickListener(this);

        btn_recycler_drag_switch = (Button) findViewById(R.id.btn_recycler_drag_switch);
        btn_recycler_drag_switch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_recycler_linear_layout:
                intent.setClass(this, LinearLayoutRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_grid_layout:
                intent.setClass(this, GridLayoutRecyclerViewActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_recycler_staggered_grid:
                intent.setClass(this, StaggeredGridRecyclerViewActivity.class);
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
        }
    }
}