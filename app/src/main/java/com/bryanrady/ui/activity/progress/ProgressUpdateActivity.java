package com.bryanrady.ui.activity.progress;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.bryanrady.ui.R;
import com.bryanrady.ui.view.progress.LinearProgressBar;

public class ProgressUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearProgressBar mLinearProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_update);

        findViewById(R.id.btn_start).setOnClickListener(this);

        mLinearProgressBar = findViewById(R.id.linearProgressBar);
        mLinearProgressBar.setOutGradient(true, Color.parseColor("#FF9900"),
                Color.parseColor("#EE6911"),
                Color.parseColor("#DD4822"),
                Color.parseColor("#571100"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                ValueAnimator animator = ValueAnimator.ofFloat(0f, 100f);
                animator.setDuration(5000);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        mLinearProgressBar.setProgress((float) animation.getAnimatedValue());
                    }
                });
                animator.start();
                break;
        }
    }

}
