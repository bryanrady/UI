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

public class Dn6_MaskFilterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_maskfilter_blur;
    private Button btn_maskfilter_emboss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn6_filter_mask);

        initView();
    }

    private void initView() {
        btn_maskfilter_blur = (Button) findViewById(R.id.btn_maskfilter_blur);
        btn_maskfilter_blur.setOnClickListener(this);

        btn_maskfilter_emboss = (Button) findViewById(R.id.btn_maskfilter_emboss);
        btn_maskfilter_emboss.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_maskfilter_blur:
                intent.setClass(this, Dn6_BlurMaskFilterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_maskfilter_emboss:
                intent.setClass(this, Dn6_EmbossMaskFilterActivity.class);
                startActivity(intent);
                break;
        }
    }

}
