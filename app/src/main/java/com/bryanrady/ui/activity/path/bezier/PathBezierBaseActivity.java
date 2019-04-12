package com.bryanrady.ui.activity.path.bezier;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bryanrady.ui.view.path.bezier.PathBezierBaseView;

/**
 * Created by wangqingbin on 2019/4/4.
 */

public class PathBezierBaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new PathBezierBaseView(this));
    //    setContentView(new PathBezierBaseView2(this));
    //    setContentView(new BezierViewStep1(this));
    //    setContentView(new PathBezierViewStep2(this));
    }

}
