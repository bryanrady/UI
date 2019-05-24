package com.bryanrady.ui.activity.path.path_measure;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.path.bezier.PathBezierBaseView;
import com.bryanrady.ui.view.path.path_measure.PathMeasureBaseView;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class PathMeasureBaseActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathMeasureBaseView(this));
    }

}
