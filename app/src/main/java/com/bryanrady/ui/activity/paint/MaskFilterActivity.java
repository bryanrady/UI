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

public class MaskFilterActivity extends StatusBarBaseActivity implements View.OnClickListener {

    private Button btn_maskfilter_blur;
    private Button btn_maskfilter_emboss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_filter_mask);

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
                intent.setClass(this, BlurMaskFilterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_maskfilter_emboss:
                intent.setClass(this, EmbossMaskFilterActivity.class);
                startActivity(intent);
                break;
        }
    }

}
