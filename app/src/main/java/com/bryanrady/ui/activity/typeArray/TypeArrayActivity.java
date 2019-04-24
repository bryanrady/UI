package com.bryanrady.ui.activity.typeArray;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.measure_layout.AspectImageActivity;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class TypeArrayActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_type_array_custom_text;
    private Button btn_type_array_double_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_array);
        initView();
    }

    private void initView() {

        btn_type_array_custom_text = (Button) findViewById(R.id.btn_type_array_custom_text);
        btn_type_array_custom_text.setOnClickListener(this);

        btn_type_array_double_image = (Button) findViewById(R.id.btn_type_array_double_image);
        btn_type_array_double_image.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_type_array_custom_text:
                intent.setClass(this, TypeArrayCustomTextActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_type_array_double_image:
                intent.setClass(this, TypeArrayDoubleImageActivity.class);
                startActivity(intent);
                break;
        }
    }
}