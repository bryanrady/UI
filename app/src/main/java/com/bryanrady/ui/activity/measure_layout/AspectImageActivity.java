package com.bryanrady.ui.activity.measure_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class AspectImageActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect_image_layout);
    }

}
