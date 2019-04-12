package com.bryanrady.ui.activity.path.bezier;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.path.bezier.PathBezierRubbshView;

public class PathBezierRubbshActivity extends AppCompatActivity {

    private Button mBtnSwitch;
    private PathBezierRubbshView pathBezierRubbshView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_bezier_rubbsh);
        pathBezierRubbshView = (PathBezierRubbshView) findViewById(R.id.rubbsh);
        mBtnSwitch = (Button) findViewById(R.id.btn_switch);
        mBtnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathBezierRubbshView.startAnimation();
            }
        });
    }
}
