package com.bryanrady.ui.activity.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * Created by wqb on 2018/7/17.
 */

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_tween_animation;
    private Button btn_frame_animation;
    private Button btn_property_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        initView();
    }

    private void initView() {
        btn_tween_animation = (Button) findViewById(R.id.btn_tween_animation);
        btn_tween_animation.setOnClickListener(this);

        btn_frame_animation = (Button) findViewById(R.id.btn_frame_animation);
        btn_frame_animation.setOnClickListener(this);

        btn_property_animation = (Button) findViewById(R.id.btn_property_animation);
        btn_property_animation.setOnClickListener(this);
        }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_tween_animation:
                intent.setClass(this, TweenAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_frame_animation:
                intent.setClass(this, FrameAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_property_animation:
                intent.setClass(this, ObjectorAnimatorActivity.class);
                startActivity(intent);
                break;
        }
    }
}
