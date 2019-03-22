package com.bryanrady.ui.activity.canvas;

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

public class CanvasActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_canvas_transfer;
    private Button btn_canvas_clip;
    private Button btn_canvas_status_save;

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
                intent.setClass(this, CanvasStatusSaveActivity.class);
                startActivity(intent);
                break;
        }
    }
}
