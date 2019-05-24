package com.bryanrady.ui.activity.measure_layout;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wqb on 2018/7/1.
 */

public class FlowLayoutActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
    }
}
