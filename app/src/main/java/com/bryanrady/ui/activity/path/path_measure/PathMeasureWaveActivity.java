package com.bryanrady.ui.activity.path.path_measure;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.path.path_measure.PathMeasureWaveView;

/**
 * Created by Administrator on 2019/4/12.
 */

public class PathMeasureWaveActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PathMeasureWaveView pathMeasureWaveView = new PathMeasureWaveView(this);
        setContentView(pathMeasureWaveView);
        pathMeasureWaveView.startAnimation();
    }

}