package com.bryanrady.ui.activity.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * Created by wqb on 2018/7/17.
 */

public class Dn16_ScaleAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn16_tween_animation_example);
        button = findViewById(R.id.btn_tween_animation);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tween_animation:
            //    startXmlScaleAnimation();
                startJavaScaleAnimation();
                break;
        }
    }

    private void startXmlScaleAnimation() {
        Animation scaleAnimation = AnimationUtils.loadAnimation(this,R.anim.dn16_tween_scale);
        button.startAnimation(scaleAnimation);
    }

    private void startJavaScaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,2,0,1,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(3000);
        button.startAnimation(scaleAnimation);
    }
}
