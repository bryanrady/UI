package com.bryanrady.ui.activity.measure_layout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * Created by wqb on 2018/7/1.
 */

public class MeasureLayoutActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_measure_layout_flowLayout;
    private Button btn_measure_layout_aspect_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_layout);
        initView();
    }

    private void initView() {

        btn_measure_layout_flowLayout = (Button) findViewById(R.id.btn_measure_layout_flowLayout);
        btn_measure_layout_flowLayout.setOnClickListener(this);

        btn_measure_layout_aspect_image = (Button) findViewById(R.id.btn_measure_layout_aspect_image);
        btn_measure_layout_aspect_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_measure_layout_flowLayout:
                intent.setClass(this, FlowLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_measure_layout_aspect_image:
                intent.setClass(this, AspectImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
