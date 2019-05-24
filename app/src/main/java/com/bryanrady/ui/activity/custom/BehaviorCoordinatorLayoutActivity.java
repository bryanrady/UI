package com.bryanrady.ui.activity.custom;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by Administrator on 2019/5/15.
 */

public class BehaviorCoordinatorLayoutActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_coordinator_layout);
    }
}
