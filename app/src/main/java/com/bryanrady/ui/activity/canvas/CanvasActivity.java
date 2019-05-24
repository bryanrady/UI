package com.bryanrady.ui.activity.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wqb on 2018/7/1.
 *
 * 直面意思是画布，其实是分装的一个工具类

 一个Canvas类对象有四大基本要素

 1、一个是用来保存像素的bitmap  -----  画板

 2、一个Canvas在Bitmap上进行绘制操作 ---- 画布或者画纸（Layer---saveLayer操作时，新建一个透明的画布图层）

 3、绘制的东西

 4、绘制的画笔Paint
 *
 */

public class CanvasActivity extends StatusBarBaseActivity implements View.OnClickListener {

    private Button btn_canvas_transfer;
    private Button btn_canvas_clip;
    private Button btn_canvas_status_save;
    private Button btn_canvas_reveal_drawable;
    private Button btn_canvas_search_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        initView();
    }

    private void initView() {

        btn_canvas_transfer = (Button) findViewById(R.id.btn_canvas_transfer);
        btn_canvas_transfer.setOnClickListener(this);

        btn_canvas_clip = (Button) findViewById(R.id.btn_canvas_clip);
        btn_canvas_clip.setOnClickListener(this);

        btn_canvas_status_save = (Button) findViewById(R.id.btn_canvas_status_save);
        btn_canvas_status_save.setOnClickListener(this);

        btn_canvas_reveal_drawable = (Button) findViewById(R.id.btn_canvas_reveal_drawable);
        btn_canvas_reveal_drawable.setOnClickListener(this);

        btn_canvas_search_view = (Button) findViewById(R.id.btn_canvas_search_view);
        btn_canvas_search_view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_canvas_transfer:
                intent.setClass(this, CanvasTransferActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_canvas_clip:
                intent.setClass(this, CanvasClipActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_canvas_status_save:
                intent.setClass(this, CanvasSaveRestoreActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_canvas_reveal_drawable:
                intent.setClass(this, CanvasRevealDrawableActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_canvas_search_view:
                intent.setClass(this, CanvasSearchViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
