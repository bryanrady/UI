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

public class Dn5_PaintXfermodeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_xfermode_16;
    private Button btn_xfermode_src;
    private Button btn_xfermode_dst;
    private Button btn_xfermode_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn5_paint_xfermode);

        initView();
    }

    private void initView() {
        btn_xfermode_16 = (Button) findViewById(R.id.btn_xfermode_16);
        btn_xfermode_16.setOnClickListener(this);

        btn_xfermode_src = (Button) findViewById(R.id.btn_xfermode_src);
        btn_xfermode_src.setOnClickListener(this);

        btn_xfermode_dst = (Button) findViewById(R.id.btn_xfermode_dst);
        btn_xfermode_dst.setOnClickListener(this);

        btn_xfermode_other = (Button) findViewById(R.id.btn_xfermode_other);
        btn_xfermode_other.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_xfermode_16:
                intent.setClass(this, Dn5_Xfermode16Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_xfermode_src:
                intent.setClass(this, Dn5_Xfermode_SRCActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_xfermode_dst:
                intent.setClass(this, Dn5_Xfermode_DSTActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_xfermode_other:
                intent.setClass(this, Dn5_Xfermode_OtherActivity.class);
                startActivity(intent);
                break;
        }
    }

}
