package com.bryanrady.ui.activity.path;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class PathBaseActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_path_base_xxx_to;
    private Button btn_path_base_add_xxx;
    private Button btn_path_base_fill_type;
    private Button btn_path_base_op;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_base);
        initView();
    }

    private void initView() {

        btn_path_base_xxx_to = (Button) findViewById(R.id.btn_path_base_xxx_to);
        btn_path_base_xxx_to.setOnClickListener(this);

        btn_path_base_add_xxx = (Button) findViewById(R.id.btn_path_base_add_xxx);
        btn_path_base_add_xxx.setOnClickListener(this);

        btn_path_base_fill_type = (Button) findViewById(R.id.btn_path_base_fill_type);
        btn_path_base_fill_type.setOnClickListener(this);

        btn_path_base_op = (Button) findViewById(R.id.btn_path_base_op);
        btn_path_base_op.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_path_base_xxx_to:
                intent.setClass(this, PathXxxToActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_base_add_xxx:
                intent.setClass(this, PathAddXxxActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_base_fill_type:
                intent.setClass(this, PathFillTypeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_path_base_op:
                intent.setClass(this, PathOpActivity.class);
                startActivity(intent);
                break;
        }
    }
}