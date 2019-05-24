package com.bryanrady.ui.activity.custom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.custom.typeArray.TypeArrayActivity;
import com.bryanrady.ui.activity.path.bezier.PathBezierActivity;
import com.bryanrady.ui.activity.path.path_measure.PathMeasureActivity;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by Administrator on 2019/5/14.
 */

public class CustomViewActivity extends StatusBarBaseActivity implements View.OnClickListener {

    private Button btn_type_array;
    private Button btn_material_design;
    private Button btn_qq_zoom_header;
    private Button btn_coordinator_nested;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        initView();
    }

    private void initView() {

        btn_type_array = (Button) findViewById(R.id.btn_type_array);
        btn_type_array.setOnClickListener(this);

        btn_material_design = (Button) findViewById(R.id.btn_material_design);
        btn_material_design.setOnClickListener(this);

        btn_qq_zoom_header = (Button) findViewById(R.id.btn_qq_zoom_header);
        btn_qq_zoom_header.setOnClickListener(this);

        btn_coordinator_nested = (Button) findViewById(R.id.btn_coordinator_nested);
        btn_coordinator_nested.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_type_array:
                intent.setClass(this, TypeArrayActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_material_design:
                intent.setClass(this, MaterialDesignButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_qq_zoom_header:
                intent.setClass(this, QQZoomHeaderActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_coordinator_nested:
                intent.setClass(this, BehaviorCoordinatorLayoutActivity.class);
                startActivity(intent);
                break;
        }
    }
}
