package com.bryanrady.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.activity.measure_layout.FlowLayoutActivity;
import com.bryanrady.ui.activity.paint.PaintActivity;
import com.bryanrady.ui.activity.canvas.CanvasActivity;
import com.bryanrady.ui.activity.animation.AnimationActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_flowlayout;
    private Button btn_paint;
    private Button btn_canvas;
    private Button btn_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn_flowlayout = (Button) findViewById(R.id.btn_flowlayout);
        btn_flowlayout.setOnClickListener(this);

        btn_paint = (Button) findViewById(R.id.btn_paint);
        btn_paint.setOnClickListener(this);

        btn_canvas = (Button) findViewById(R.id.btn_canvas);
        btn_canvas.setOnClickListener(this);

        btn_animation = (Button) findViewById(R.id.btn_animation);
        btn_animation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_flowlayout:
                intent.setClass(this, FlowLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint:
                intent.setClass(this, PaintActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_canvas:
                intent.setClass(this, CanvasActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_animation:
                intent.setClass(this, AnimationActivity.class);
                startActivity(intent);
                break;
        }
    }
}