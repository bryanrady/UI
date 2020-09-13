package com.bryanrady.ui.activity.progress;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.SeekBar;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.progress.LinearCenterProgressBar;

public class LinearCenterProgressActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearCenterProgressBar mLinearCenterProgressBar;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_line_centre);

        mLinearCenterProgressBar = findViewById(R.id.linearCenterProgressBar);
        mSeekBar = findViewById(R.id.seekBar);
        findViewById(R.id.btn_start).setOnClickListener(this);

        mSeekBar.setMax(mLinearCenterProgressBar.dp2px(10));
        mSeekBar.setProgress(mSeekBar.getMax());
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mLinearCenterProgressBar.setBoxRadius(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 80f);
                animator.setDuration(5000);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mLinearCenterProgressBar.setProgress((float) animation.getAnimatedValue());
                    }
                });
                animator.start();
                break;
        }
    }
}
