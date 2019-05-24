package com.bryanrady.ui.activity.path.bezier;

import android.os.Bundle;
import android.view.View;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.path.bezier.PathBezierGarbageView;

public class PathBezierGarbageActivity extends StatusBarBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_bezier_garbage);
        final PathBezierGarbageView garbageView = (PathBezierGarbageView) findViewById(R.id.garbageView);
        garbageView.startRotate();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                garbageView.startRotate();
            }
        });

    }
}