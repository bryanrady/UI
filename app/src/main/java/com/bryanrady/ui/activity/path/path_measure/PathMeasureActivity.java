package com.bryanrady.ui.activity.path.path_measure;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * Path辅助类 PathMeasure
 * Created by Administrator on 2019/4/12.
 */

public class PathMeasureActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_path_measure_base;
    private Button btn_path_measure_circle_rotate;
    private Button btn_path_measure_face_loading;
    private Button btn_path_measure_loading;
    private Button btn_path_measure_wave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_measure);
        initView();
    }

    private void initView() {

        btn_path_measure_base = (Button) findViewById(R.id.btn_path_measure_base);
        btn_path_measure_base.setOnClickListener(this);

        btn_path_measure_circle_rotate = (Button) findViewById(R.id.btn_path_measure_circle_rotate);
        btn_path_measure_circle_rotate.setOnClickListener(this);

        btn_path_measure_face_loading = (Button) findViewById(R.id.btn_path_measure_face_loading);
        btn_path_measure_face_loading.setOnClickListener(this);

        btn_path_measure_loading = (Button) findViewById(R.id.btn_path_measure_loading);
        btn_path_measure_loading.setOnClickListener(this);

        btn_path_measure_wave = (Button) findViewById(R.id.btn_path_measure_wave);
        btn_path_measure_wave.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_path_measure_base:
                intent.setClass(this, PathMeasureBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_measure_circle_rotate:
                intent.setClass(this, PathMeasureCircleRotateActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_measure_face_loading:
                intent.setClass(this, PathMeasureFaceLoadingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_measure_loading:
                intent.setClass(this, PathMeasureLoadingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_measure_wave:
                intent.setClass(this, PathMeasureWaveActivity.class);
                startActivity(intent);
                break;
        }
    }
}