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

public class Dn6_ManyFilterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_color_matrix_filter_other;
    private Button btn_filter_color_lighting;
    private Button btn_filter_color_porterDuff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn6_filter_many);

        initView();
    }

    private void initView() {
        btn_color_matrix_filter_other = (Button) findViewById(R.id.btn_color_matrix_filter_other);
        btn_color_matrix_filter_other.setOnClickListener(this);

        btn_filter_color_lighting = (Button) findViewById(R.id.btn_filter_color_lighting);
        btn_filter_color_lighting.setOnClickListener(this);

        btn_filter_color_porterDuff = (Button) findViewById(R.id.btn_filter_color_porterDuff);
        btn_filter_color_porterDuff.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_color_matrix_filter_other:
                intent.setClass(this, Dn6_ColorMatrixFilterOtherActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_lighting:
                intent.setClass(this, Dn6_LightingColorFilterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_filter_color_porterDuff:
                intent.setClass(this, Dn6_PorterDuffColorFilterActivity.class);
                startActivity(intent);
                break;
        }
    }

}
