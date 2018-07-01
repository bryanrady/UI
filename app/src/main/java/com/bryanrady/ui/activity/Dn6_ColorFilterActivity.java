package com.bryanrady.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * Created by wqb on 2018/6/27.
 */

public class Dn6_ColorFilterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_filter_color_alpha;
    private Button btn_filter_color_translate;
    private Button btn_filter_color_multiple;
    private Button btn_filter_color_toushe;
    private Button btn_filter_color_rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn6_filter_color_matrix);

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
                intent.setClass(this, Dn6_ColorFilterAlphaActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_translate:
                intent.setClass(this, Dn6_ColorFilterTranslateActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_multiple:
                intent.setClass(this, Dn6_ColorFilterMuliptyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_toushe:
                intent.setClass(this, Dn6_ColorFilterTousheActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_rotation:
                intent.setClass(this, Dn6_ColorFilterRotationActivity.class);
                startActivity(intent);
                break;
        }
    }

}
