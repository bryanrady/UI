package com.bryanrady.ui.activity.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.paint.PaintShadersActivity;
import com.bryanrady.ui.activity.paint.PaintXfermodeActivity;

/**
 * Canvas 裁剪    canvas.clipxxx系列
 * Created by wqb on 2018/7/1.
 */

public class CanvasClipActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_canvas_clip_path;
    private Button btn_canvas_clip_rect;
    private Button btn_canvas_clip_round_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_clip);
        initView();
    }

    private void initView() {

        btn_canvas_clip_path = (Button) findViewById(R.id.btn_canvas_clip_path);
        btn_canvas_clip_path.setOnClickListener(this);

        btn_canvas_clip_rect = (Button) findViewById(R.id.btn_canvas_clip_rect);
        btn_canvas_clip_rect.setOnClickListener(this);

        btn_canvas_clip_round_image = (Button) findViewById(R.id.btn_canvas_clip_round_image);
        btn_canvas_clip_round_image.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_canvas_clip_path:
                intent.setClass(this, CanvasClipPathActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_canvas_clip_rect:
                intent.setClass(this, CanvasClipRectActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_canvas_clip_round_image:
                intent.setClass(this, CanvasClipPathRoundImageActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 作业：1、实现ReavlView效果 --- 通过图片剪裁拼接（自定义Drawable实现）

     2、自定义SearchView
     */
}
