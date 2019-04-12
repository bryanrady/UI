package com.bryanrady.ui.activity.path.path_measure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.path.path_measure.PathMeasureWaveView;

/**
 * Created by Administrator on 2019/4/12.
 */

public class PathMeasureWaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PathMeasureWaveView pathMeasureWaveView = new PathMeasureWaveView(this);
        setContentView(pathMeasureWaveView);
        pathMeasureWaveView.startAnimation();
    }

}