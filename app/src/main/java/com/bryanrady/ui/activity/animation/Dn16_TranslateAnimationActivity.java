package com.bryanrady.ui.activity.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * https://www.jianshu.com/p/733532041f46
 * Android 动画：手把手教你使用 补间动画
 *
 * https://www.jianshu.com/p/37e94f8b6f59
 * 酷炫的Activity切换动画，打造更好的用户体验
 * Created by wqb on 2018/7/17.
 */

public class Dn16_TranslateAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TranslateAnimation translateAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn16_tween_animation_example);
        button = findViewById(R.id.btn_tween_animation);
        button.setOnClickListener(this);


//        应用场景
//        7.1 标准的动画效果
//        补间动画常用于视图View的一些标准动画效果：平移、旋转、缩放 & 透明度；
//        除了常规的动画使用，补间动画还有一些特殊的应用场景。

//        7.2 特殊的应用场景
//        Activity 的切换效果
//        Fragement 的切换效果
//        视图组（ViewGroup）中子元素的出场效果
    }

    private void startXmlTranslateAnimation(){
        Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.dn16_tween_translate);
        button.startAnimation(translateAnimation);
    }

    private void startJavaTranslateAnimation(){
        translateAnimation = new TranslateAnimation(0,500,0,500);
        translateAnimation.setDuration(3000);
        //其他属性不设置都是取的默认值
        button.startAnimation(translateAnimation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_tween_animation:
                //startXmlTranslateAnimation();
                startJavaTranslateAnimation();

                //监听动画
                //Animation类通过监听动画开始 / 结束 / 重复时刻来进行一系列操作，如跳转页面等等
                //通过在 Java 代码里setAnimationListener()方法设置
                translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // 动画开始时回调
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // 动画结束时回调
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //动画重复执行的时候回调
                    }

                });

                break;
        }
    }
}
