package com.bryanrady.ui.activity.progress;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.progress.BaseProgressBar;

public class ProgressMainActivity extends AppCompatActivity implements View.OnClickListener {

    private final int[] ids = new int[]{
            R.id.linearProgressBar,
            R.id.linearCenterProgressBar,
            R.id.linearBottomProgressBar,
            R.id.arcProgressBar,
            R.id.waterWaveProgressBar,
    };

    private BaseProgressBar[] progressBars = new BaseProgressBar[ids.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_main);

        findViewById(R.id.btn_linear).setOnClickListener(this);
        findViewById(R.id.btn_linear_center).setOnClickListener(this);
        findViewById(R.id.btn_linear_bottom).setOnClickListener(this);
        findViewById(R.id.btn_arc).setOnClickListener(this);
        findViewById(R.id.btn_water_wave).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);

        for (int i = 0; i < progressBars.length; i++) {
            progressBars[i] = findViewById(ids[i]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_linear:
                startActivity(new Intent(this, LinearProgressActivity.class));
                break;
            case R.id.btn_linear_center:
                startActivity(new Intent(this, LinearCenterProgressActivity.class));
                break;
            case R.id.btn_linear_bottom:
                startActivity(new Intent(this, LinearBottomProgressActivity.class));
                break;
            case R.id.btn_arc:
                startActivity(new Intent(this, ArcProgressActivity.class));
                break;
            case R.id.btn_water_wave:
                startActivity(new Intent(this, WaterWaveProgressActivity.class));
                break;
            case R.id.btn_start:
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 60f);
                animator.setDuration(5000);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        for (int i = 0; i < progressBars.length; i++) {
                            progressBars[i].setProgress((float) animation.getAnimatedValue());
                        }
                    }
                });
                animator.start();
                break;
        }
    }
}
