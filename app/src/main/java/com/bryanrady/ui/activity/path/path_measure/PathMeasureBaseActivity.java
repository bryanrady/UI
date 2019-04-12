package com.bryanrady.ui.activity.path.path_measure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.path.bezier.PathBezierBaseView;
import com.bryanrady.ui.view.path.path_measure.PathMeasureBaseView;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class PathMeasureBaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathMeasureBaseView(this));
    }

}
