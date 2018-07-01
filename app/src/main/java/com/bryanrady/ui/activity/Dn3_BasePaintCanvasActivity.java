package com.bryanrady.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * Created by wqb on 2018/6/25.
 */

public class Dn3_BasePaintCanvasActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_circle_progress;
    private Button btn_paint_base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn3_paint_canvas);

        initView();
    }

    private void initView() {
        btn_circle_progress = (Button) findViewById(R.id.btn_circle_progress);
        btn_circle_progress.setOnClickListener(this);

        btn_paint_base = (Button) findViewById(R.id.btn_paint_base);
        btn_paint_base.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_circle_progress:
                intent.setClass(this, Dn3_CircleProgressBarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint_base:
                intent.setClass(this, Dn3_BasePaintActivity.class);
                startActivity(intent);
                break;
        }
    }
}