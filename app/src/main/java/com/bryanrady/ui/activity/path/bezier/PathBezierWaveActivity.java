package com.bryanrady.ui.activity.path.bezier;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.path.bezier.PathBezierWaveView;

public class PathBezierWaveActivity extends AppCompatActivity {
    private Button mBtnReset;
    private Button mBtnStart;
    private PathBezierWaveView pathBezierWaveView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_bezier_wave);
        pathBezierWaveView = (PathBezierWaveView) findViewById(R.id.waveView);
        mBtnReset= (Button) findViewById(R.id.btn_reset);
        mBtnStart= (Button) findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathBezierWaveView.startAnim();
            }
        });
        mBtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathBezierWaveView.reset();
            }
        });
    }
}
