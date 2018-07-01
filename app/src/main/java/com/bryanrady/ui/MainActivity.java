package com.bryanrady.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.activity.Dn2_FlowLayoutActivity;
import com.bryanrady.ui.activity.Dn3_BasePaintCanvasActivity;
import com.bryanrady.ui.activity.Dn4_PaintShadersActivity;
import com.bryanrady.ui.activity.Dn5_PaintXfermodeActivity;
import com.bryanrady.ui.activity.Dn6_PaintFilterActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_flowlayout;
    private Button btn_paint_canvas;
    private Button btn_paint_shader;
    private Button btn_paint_xfermode;
    private Button btn_paint_filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn_flowlayout = (Button) findViewById(R.id.btn_flowlayout);
        btn_flowlayout.setOnClickListener(this);

        btn_paint_canvas = (Button) findViewById(R.id.btn_paint_canvas);
        btn_paint_canvas.setOnClickListener(this);

        btn_paint_shader = (Button) findViewById(R.id.btn_paint_shader);
        btn_paint_shader.setOnClickListener(this);

        btn_paint_xfermode = (Button) findViewById(R.id.btn_paint_xfermode);
        btn_paint_xfermode.setOnClickListener(this);

        btn_paint_filter = (Button) findViewById(R.id.btn_paint_filter);
        btn_paint_filter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_flowlayout:
                intent.setClass(this, Dn2_FlowLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint_canvas:
                intent.setClass(this, Dn3_BasePaintCanvasActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint_shader:
                intent.setClass(this, Dn4_PaintShadersActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint_xfermode:
                intent.setClass(this, Dn5_PaintXfermodeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint_filter:
                intent.setClass(this, Dn6_PaintFilterActivity.class);
                startActivity(intent);
                break;
        }
    }
}