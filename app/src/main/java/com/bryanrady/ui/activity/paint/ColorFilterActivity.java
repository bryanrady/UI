package com.bryanrady.ui.activity.paint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wqb on 2018/6/27.
 */

public class ColorFilterActivity extends StatusBarBaseActivity implements View.OnClickListener {

    private Button btn_filter_color_alpha;
    private Button btn_filter_color_translate;
    private Button btn_filter_color_multiple;
    private Button btn_filter_color_toushe;
    private Button btn_filter_color_rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_filter_color_matrix);

        initView();
    }

    private void initView() {
        btn_filter_color_alpha = (Button) findViewById(R.id.btn_filter_color_alpha);
        btn_filter_color_alpha.setOnClickListener(this);

        btn_filter_color_translate = (Button) findViewById(R.id.btn_filter_color_translate);
        btn_filter_color_translate.setOnClickListener(this);

        btn_filter_color_multiple = (Button) findViewById(R.id.btn_filter_color_multiple);
        btn_filter_color_multiple.setOnClickListener(this);

        btn_filter_color_toushe = (Button) findViewById(R.id.btn_filter_color_toushe);
        btn_filter_color_toushe.setOnClickListener(this);

        btn_filter_color_rotation = (Button) findViewById(R.id.btn_filter_color_rotation);
        btn_filter_color_rotation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_filter_color_alpha:
                intent.setClass(this, ColorFilterAlphaActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_translate:
                intent.setClass(this, ColorFilterTranslateActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_multiple:
                intent.setClass(this, ColorFilterMuliptyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_toushe:
                intent.setClass(this, ColorFilterTousheActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_rotation:
                intent.setClass(this, ColorFilterRotationActivity.class);
                startActivity(intent);
                break;
        }
    }

}
