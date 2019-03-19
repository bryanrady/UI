package com.bryanrady.ui.activity.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bryanrady.ui.R;

/**
 * 传统2D动画之-补间动画
 *
 *      1.作用对象
 *            视图控件    Button、TextView等
 *      2. 原理
 *            通过确定开始的视图样式 & 结束的视图样式、中间动画变化过程由系统补全来确定一个动画
 *                (1)  结束的视图样式：平移、缩放、旋转 & 透明度样式
 *                (2)  即补间动画的动画效果就是：平移、缩放、旋转 & 透明度动画
 *
 *      3. 分类
 *            根据不同的动画效果，补间动画分为4种动画：
 *           平移动画（Translate）    ---  TranslateAnimation   移动视图控件的位置
 *           缩放动画（scale）        ---  ScaleAnimation     放大、缩小视图控件
 *           旋转动画（rotate）       ---  RotateAnimation    旋转视图的角度
 *           透明度动画（alpha）      ---  AlphaAnimation     改变视图控件的透明度
 *
 *      4. 具体使用
 *          补间动画的使用方式分为两种：
 *               (1)   在XMLres/anim的文件夹里创建动画效果.xml文件
 *               (2)   Java 代码里设置
 *              前者优点：动画描述的可读性更好
 *              后者优点：动画效果可动态创建
 */

public class Dn16_TweenAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_translate_animation;
    private Button btn_scale_animation;
    private Button btn_rotate_animation;
    private Button btn_alpha_animation;
    private Button btn_set_animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn16_tween_animation_example);

        initView();
    }

    private void initView() {
        btn_translate_animation = (Button) findViewById(R.id.btn_translate_animation);
        btn_translate_animation.setOnClickListener(this);

        btn_scale_animation = (Button) findViewById(R.id.btn_scale_animation);
        btn_scale_animation.setOnClickListener(this);

        btn_rotate_animation = (Button) findViewById(R.id.btn_rotate_animation);
        btn_rotate_animation.setOnClickListener(this);

        btn_alpha_animation = (Button) findViewById(R.id.btn_alpha_animation);
        btn_alpha_animation.setOnClickListener(this);

        btn_set_animation = (Button) findViewById(R.id.btn_set_animation);
        btn_set_animation.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_translate_animation:
                intent.setClass(this, Dn16_TranslateAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_scale_animation:
                intent.setClass(this, Dn16_ScaleAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_rotate_animation:
                intent.setClass(this, Dn16_RotateAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_alpha_animation:
                intent.setClass(this, Dn16_AlphaAnimationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_set_animation:
                intent.setClass(this, Dn16_SetAnimationActivity.class);
                startActivity(intent);
                break;
        }
    }
}