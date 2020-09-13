package com.bryanrady.ui.activity.progress;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.progress.LinearProgressBar;


public class LinearProgressActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    private LinearProgressBar mProgressBar2;
    private LinearProgressBar mProgressBar5;
    private LinearProgressBar mProgressBar6;
    private LinearProgressBar mProgressBar7;
    private SeekBar mSeekBar;
    private SeekBar mSeekBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_linear);

        mProgressBar2 = findViewById(R.id.progressBar2);

        mProgressBar5 = findViewById(R.id.progressBar5);
        mProgressBar6 = findViewById(R.id.progressBar6);
        mProgressBar7 = findViewById(R.id.progressBar7);

        mSeekBar = findViewById(R.id.seekBar);
        mSeekBar2 = findViewById(R.id.seekBar2);

        mProgressBar2.setOutGradient(false, Color.RED, Color.YELLOW);
        mProgressBar5.setOutGradient(Color.RED, Color.YELLOW);
        mProgressBar6.setOutGradient(false, Color.parseColor("#571100"),
                Color.RED,
                Color.RED,
                Color.parseColor("#571100"));

        mSeekBar.setMax(20);
        mSeekBar.setOnSeekBarChangeListener(this);

        mSeekBar2.setMax(20);
        mSeekBar2.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progress = mProgressBar7.sp2px(progress);
        switch (seekBar.getId()){
            case R.id.seekBar:
                mProgressBar7.setRadius(progress);
                mProgressBar7.invalidate();
                break;
            case R.id.seekBar2:
                mProgressBar7.setProgressRadius(progress);
                mProgressBar7.invalidate();
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
