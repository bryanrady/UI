package com.bryanrady.ui.activity.path.path_measure;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.path.path_measure.PathMeasureFaceLoadingView;

/**
 * Created by Administrator on 2019/4/12.
 */

public class PathMeasureFaceLoadingActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathMeasureFaceLoadingView(this));
    }

}