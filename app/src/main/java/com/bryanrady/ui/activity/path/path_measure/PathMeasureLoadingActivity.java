package com.bryanrady.ui.activity.path.path_measure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.path.path_measure.PathMeasureLoadingView;

/**
 * Created by Administrator on 2019/4/12.
 */

public class PathMeasureLoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathMeasureLoadingView(this));
    }

}