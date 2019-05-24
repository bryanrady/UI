package com.bryanrady.ui.activity.paint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wqb on 2018/6/26.
 */

public class PaintShadersActivity extends StatusBarBaseActivity implements View.OnClickListener {

    private Button btn_bitmap_shaders;
    private Button btn_linear_shaders;
    private Button btn_gradient_shaders;
    private Button btn_sweep_shaders;
    private Button btn_compose_shaders;
    private Button btn_zoom_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_shaders);

        initView();
    }

    private void initView() {
        btn_bitmap_shaders = (Button) findViewById(R.id.btn_bitmap_shaders);
        btn_bitmap_shaders.setOnClickListener(this);

        btn_linear_shaders = (Button) findViewById(R.id.btn_linear_shaders);
        btn_linear_shaders.setOnClickListener(this);

        btn_gradient_shaders = (Button) findViewById(R.id.btn_gradient_shaders);
        btn_gradient_shaders.setOnClickListener(this);

        btn_sweep_shaders = (Button) findViewById(R.id.btn_sweep_shaders);
        btn_sweep_shaders.setOnClickListener(this);

        btn_compose_shaders = (Button) findViewById(R.id.btn_compose_shaders);
        btn_compose_shaders.setOnClickListener(this);

        btn_zoom_image = (Button) findViewById(R.id.btn_zoom_image);
        btn_zoom_image.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_bitmap_shaders:
                intent.setClass(this, BitmapShadersActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_linear_shaders:
                intent.setClass(this, LinerGradientActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_gradient_shaders:
                intent.setClass(this, SweepGradientActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sweep_shaders:
                intent.setClass(this, RadialGradientActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_compose_shaders:
                intent.setClass(this, ComposeShadersActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_zoom_image:
                intent.setClass(this, ZoomImageViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
