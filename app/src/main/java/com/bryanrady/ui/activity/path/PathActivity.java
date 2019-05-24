package com.bryanrady.ui.activity.path;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.path.bezier.PathBezierActivity;
import com.bryanrady.ui.activity.path.path_measure.PathMeasureActivity;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class PathActivity extends StatusBarBaseActivity implements View.OnClickListener {

    private Button btn_path_base;
    private Button btn_path_bezier;
    private Button btn_path_measure;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        initView();
    }

    private void initView() {

        btn_path_base = (Button) findViewById(R.id.btn_path_base);
        btn_path_base.setOnClickListener(this);

        btn_path_bezier = (Button) findViewById(R.id.btn_path_bezier);
        btn_path_bezier.setOnClickListener(this);

        btn_path_measure = (Button) findViewById(R.id.btn_path_measure);
        btn_path_measure.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_path_base:
                intent.setClass(this, PathBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_bezier:
                intent.setClass(this, PathBezierActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_measure:
                intent.setClass(this, PathMeasureActivity.class);
                startActivity(intent);
                break;
        }
    }
}