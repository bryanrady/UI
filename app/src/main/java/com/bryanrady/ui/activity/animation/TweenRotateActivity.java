package com.bryanrady.ui.activity.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wqb on 2018/7/17.
 */

public class TweenRotateActivity extends StatusBarBaseActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_animation_example);
        button = findViewById(R.id.btn_tween_animation);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tween_animation:
                //startXmlRotateAnimation();
                startJavaRotateAnimation();
                break;
        }
    }

    private void startXmlRotateAnimation() {
        Animation rotateAnimation = AnimationUtils.loadAnimation(this,R.anim.tween_animation_rotate);
        button.startAnimation(rotateAnimation);
    }

    private void startJavaRotateAnimation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0,270,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(3000);
        button.startAnimation(rotateAnimation);
    }
}
