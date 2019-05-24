package com.bryanrady.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;
import com.bryanrady.ui.view.CircleProgressBar2;

/**
 * Created by wqb on 2018/6/25.
 */

public class DrawCircleProgressBarActivity extends StatusBarBaseActivity {

    private CircleProgressBar2 mProgressBar;
    private int mProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_circle_progressbar);

        mProgressBar = findViewById(R.id.circleProgressBar);
        mProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mProgress <= 100){
                            mProgress += 2;
                            mProgressBar.setProgress(mProgress);

                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }
}
