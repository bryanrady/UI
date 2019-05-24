package com.bryanrady.ui.activity.path.bezier;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.path.bezier.PathBezierBaseView;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class PathBezierBaseActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathBezierBaseView(this));
    //    setContentView(new PathBezierBaseView2(this));
    //    setContentView(new BezierViewStep1(this));
    //    setContentView(new PathBezierViewStep2(this));
    }

}
