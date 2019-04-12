package com.bryanrady.ui.activity.path.bezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class PathBezierActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_path_bezier_base;
    private Button btn_path_bezier_advanced;
    private Button btn_path_bezier_drag_bubble;
    private Button btn_path_bezier_wave;
    private Button btn_path_bezier_rubbsh;
    private Button btn_path_bezier_garbage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_bezier);
        initView();
    }

    private void initView() {

        btn_path_bezier_base = (Button) findViewById(R.id.btn_path_bezier_base);
        btn_path_bezier_base.setOnClickListener(this);

        btn_path_bezier_advanced = (Button) findViewById(R.id.btn_path_bezier_advanced);
        btn_path_bezier_advanced.setOnClickListener(this);

        btn_path_bezier_drag_bubble = (Button) findViewById(R.id.btn_path_bezier_drag_bubble);
        btn_path_bezier_drag_bubble.setOnClickListener(this);

        btn_path_bezier_wave = (Button) findViewById(R.id.btn_path_bezier_wave);
        btn_path_bezier_wave.setOnClickListener(this);

        btn_path_bezier_rubbsh = (Button) findViewById(R.id.btn_path_bezier_rubbsh);
        btn_path_bezier_rubbsh.setOnClickListener(this);

        btn_path_bezier_garbage = (Button) findViewById(R.id.btn_path_bezier_garbage);
        btn_path_bezier_garbage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_path_bezier_base:
                intent.setClass(this, PathBezierBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_bezier_advanced:
                intent.setClass(this, PathBezierAdvancedActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_bezier_drag_bubble:
                intent.setClass(this, PathBezierDragBubbleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_bezier_wave:
                intent.setClass(this, PathBezierWaveActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_bezier_rubbsh:
                intent.setClass(this, PathBezierRubbshActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_bezier_garbage:
                intent.setClass(this, PathBezierGarbageActivity.class);
                startActivity(intent);
                break;
        }
    }
}