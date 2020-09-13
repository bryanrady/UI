package com.bryanrady.ui.activity.progress;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.SeekBar;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.progress.ArcProgressBarBar;


public class ArcProgressActivity extends AppCompatActivity implements View.OnClickListener {

    private ArcProgressBarBar mArcProgressBar;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_arc);

        mArcProgressBar = findViewById(R.id.arcProgressBarBar);
        mSeekBar = findViewById(R.id.seekBar);
        findViewById(R.id.btn_start).setOnClickListener(this);

        mSeekBar.setMax(360);
        mSeekBar.setProgress(mSeekBar.getMax());
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mArcProgressBar.setDrawAngle(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mArcProgressBar.setOutGradient(Color.RED, Color.YELLOW, Color.GRAY, Color.RED);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 100f);
                animator.setDuration(5000);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mArcProgressBar.setProgress((float) animation.getAnimatedValue());
                    }
                });
                animator.start();
                break;
        }
    }
}
