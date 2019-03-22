package com.bryanrady.ui.activity.paint;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.BasePaintCanvasActivity;

/**
 * Created by wqb on 2018/7/1.
 */

public class PaintActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_paint_canvas;
    private Button btn_paint_shader;
    private Button btn_paint_xfermode;
    private Button btn_paint_filter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        initView();
    }

    private void initView() {

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
            case R.id.btn_paint_canvas:
                intent.setClass(this, BasePaintCanvasActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint_shader:
                intent.setClass(this, PaintShadersActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint_xfermode:
                intent.setClass(this, PaintXfermodeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_paint_filter:
                intent.setClass(this, PaintFilterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
