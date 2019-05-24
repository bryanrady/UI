package com.bryanrady.ui.activity.custom;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by Administrator on 2019/5/14.
 */

public class MaterialDesignButtonActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design_button);
        Button button = findViewById(R.id.click);

        //MD是5.0之后才出来的
        // 5.0以上 android:theme="@style/MyButton"
        //5.0以下
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            button.setBackground(getResources().getDrawable(R.drawable.ic_btn_selector));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MaterialDesignButtonActivity.this,MaterialDesignActivity.class);
                startActivity(intent);
            }
        });

    }
}
