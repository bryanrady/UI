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

public class Dn6_PaintFilterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_filter_alpha;
    private Button btn_filter_rgb;
    private Button btn_filter_argb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn6_paint_filter);

        initView();
    }

    private void initView() {
        btn_filter_alpha = (Button) findViewById(R.id.btn_filter_alpha);
        btn_filter_alpha.setOnClickListener(this);

        btn_filter_rgb = (Button) findViewById(R.id.btn_filter_rgb);
        btn_filter_rgb.setOnClickListener(this);

        btn_filter_argb = (Button) findViewById(R.id.btn_filter_argb);
        btn_filter_argb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_filter_alpha:
                intent.setClass(this, Dn6_MaskFilterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_rgb:
                intent.setClass(this, Dn6_ColorFilterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_argb:
                intent.setClass(this, Dn6_ManyFilterActivity.class);
                startActivity(intent);
                break;
        }
    }

}
