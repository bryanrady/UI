package com.bryanrady.ui.activity.progress;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.SeekBar;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.progress.WaterWaveProgressBar;

/**
 * 水波纹样式
 */
public class WaterWaveProgressActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    WaterWaveProgressBar mWaterWaveProgressBar;
    SeekBar mSeekBar;
    SeekBar mSeekBar2;
    SeekBar mSeekBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_water_wave);

        mWaterWaveProgressBar = findViewById(R.id.waterWaveProgressBar);
        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar2 = findViewById(R.id.seekBar2);
        mSeekBar3 = findViewById(R.id.seekBar3);
        findViewById(R.id.btn_start).setOnClickListener(this);

        mSeekBar.setMax(mWaterWaveProgressBar.dp2px(100));
        mSeekBar.setOnSeekBarChangeListener(this);

        mSeekBar2.setMax(mWaterWaveProgressBar.dp2px(30));
        mSeekBar2.setOnSeekBarChangeListener(this);

        mSeekBar3.setMax(10);
        mSeekBar3.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 50f);
                animator.setDuration(5000);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mWaterWaveProgressBar.setProgress((float) animation.getAnimatedValue());
                    }
                });
                animator.start();
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(progress==0){
            return;
        }
        switch (seekBar.getId()){
            case R.id.seekBar:
                mWaterWaveProgressBar.setWaveWidth(progress);
                break;
            case R.id.seekBar2:
                mWaterWaveProgressBar.setWaveHeight(progress);
                break;
            case R.id.seekBar3:
                mWaterWaveProgressBar.setWaveSpeed(progress);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWaterWaveProgressBar.replace();
    }
}
