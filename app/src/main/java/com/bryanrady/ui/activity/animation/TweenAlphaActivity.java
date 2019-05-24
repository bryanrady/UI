package com.bryanrady.ui.activity.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.bryanrady.ui.R;
import com.bryanrady.ui.activity.status_bar.StatusBarBaseActivity;

/**
 * Created by wqb on 2018/7/17.
 */

public class TweenAlphaActivity extends StatusBarBaseActivity implements View.OnClickListener {

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
                //startXmlSetAnimation();
                startJavaSetAnimation();
                break;
        }
    }

    private void startXmlSetAnimation() {
        Animation alpahAnimation = AnimationUtils.loadAnimation(this,R.anim.tween_animation_alpha);
        button.startAnimation(alpahAnimation);
    }

    private void startJavaSetAnimation() {
        AlphaAnimation alpahAnimation = new AlphaAnimation(1,0);
        alpahAnimation.setDuration(3000);
        button.startAnimation(alpahAnimation);
    }
}
