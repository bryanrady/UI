package com.bryanrady.ui.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;

import com.bryanrady.ui.R;

/**
 * 属性动画
 *
 */

public class Dn16_PropertyAnimatorActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dn16_property_animator);
        iv = (ImageView)findViewById(R.id.button1);
    }

    public void startAnimation(View v) {
        //1.-------------属性动画基础部分 直接作用于控件-------------
//        iv.setTranslationX(200f);
//        iv.setTranslationY(200f);
//        iv.setScaleX(0.5f);
//        iv.setScaleY(0.5f);
//        iv.setRotationX(120f);
//        iv.setRotationY(120f);
//        iv.setAlpha(0.5f);

        //只要view里面有setXXX()方法就可以通过反射达到变化的目的,这种只能执行单个动画
//    //    ObjectAnimator animator = ObjectAnimator.ofFloat(iv,"translationX",0f,200f);
//    //    ObjectAnimator animator = ObjectAnimator.ofFloat(iv,"translationY",0f,200f);
//        ObjectAnimator animator = ObjectAnimator.ofFloat(iv,"scaleX",0.5f);
//        animator.setDuration(500);
//        animator.start();

        //2.-------------多个动画同时执行-------------
        //方法1：设置动画监听，然后在监听里面同步其他动画
//        final ObjectAnimator animator = ObjectAnimator.ofFloat(iv,"translationX",0f,200f);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//            //    animation.getAnimatedFraction();    //这个可以获取到动画执行的百分比  0~1
//                float value = (float) animation.getAnimatedValue();
//                iv.setRotation(value);
//            }
//        });
//        animator.setDuration(500);
//        animator.start();
//		animator.setRepeatCount(2);
//		animator.setRepeatCount(ValueAnimator.INFINITE);
//		animator.setRepeatMode(ValueAnimator.RESTART);  //正序
//		animator.setRepeatMode(ValueAnimator.REVERSE.)  //倒置
        //还可以设置动画状态监听操作
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
//        //还可以只监听单个操作 适配器原理
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//                super.onAnimationStart(animation);
//            }
//        });

        //方法2：ValueAnimator---如果只需要监听值变化就用ValueAnimator
//        ValueAnimator animator = ValueAnimator.ofFloat(0f, 200f);
//        animator.setDuration(300);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();//得到0f~200f当中的这个时间点对应的值
//                iv.setScaleX(0.5f+value/200);
//            //    iv.setScaleY(0.5f+value/200);
//                iv.setTranslationY(value);
//            }
//        });
//        animator.start();

        //方法3：PropertyValuesHolder
//		PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("alpha", 1f,0.5f);
//		PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleX", 1f,0.5f);
//		PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("scaleY", 1f,0.5f);
//		ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(iv, holder1,holder2,holder3);
//		animator.setDuration(300);
//		animator.start();

        //方法4：动画集合
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(iv, "translationX", 0f,100f);
//		ObjectAnimator animator2 = ObjectAnimator.ofFloat(iv, "translationY", 0f,100f);
//		ObjectAnimator animator3 = ObjectAnimator.ofFloat(iv, "scaleX", 0f,2f);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(500);
//		animatorSet.play(animator3).with(animator2).after(animator1);//animator1在前面
////        animatorSet.play(animator3).with(animator2).before(animator1);//animator1在后面
////		  animatorSet.playTogether(animator1,animator2,animator3);
////        animatorSet.playSequentially(animator1,animator2,animator3);
//        animatorSet.start();

        //4.--------案例：实现自由落体抛物线效果---购物车动画、股指数----------
        /**
         * x: 匀速
         * y: 加速度 y = vt = 1/2*g*t*t
         * 估值器 --- 控制坐标PointF(x,y)
         */
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(1000);
        animator.setObjectValues(new PointF(0f,0f));
        final PointF pointF = new PointF();
        //颜色估值器
//		setBackgroundColor((Integer) sArgbEvaluator.evaluate(ratio, mDiscrollveFromBgColor, mDiscrollveToBgColor));
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                // 估值计算方法---可以在执行的过程当中干预改变属性的值---做效果：用自己的算法来控制
                // 不断地去计算修改坐标
                // x方向 匀速运动 x=v*t 为了看起来效果好我让t变成fraction*5
                pointF.x = 100f*(fraction*5);
                //y方向 加速度 y=vt=1/2*g*t*t
//				pointF.y = 0.5f*9.8f*(fraction*5)*(fraction*5);
                pointF.y = 10f*0.5f*9.8f*(fraction*5)*(fraction*5);
                return pointF;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
                iv.setX(point.x);
                iv.setY(point.y);
            }
        });
        animator.start();

        //5.---------插值器（加速器）Interpolater-----------
//        ObjectAnimator animator = ObjectAnimator.ofFloat(iv, "translationY", 0f,1000f);
//        animator.setDuration(800);
////		TimeInterpolator
////		oa.setInterpolator(new AccelerateInterpolator(1));
////		oa.setInterpolator(new AccelerateDecelerateInterpolator());
////		oa.setInterpolator(new BounceInterpolator());
////		oa.setInterpolator(new AnticipateInterpolator());
//        animator.setInterpolator(new CycleInterpolator(5));
//        animator.start();

        //6.-------- 刷鲜花效果-----------
//        1.作业：刷鲜花效果
//        思路：两种。 1.自绘控件，如何让鲜花按照贝塞尔曲线运动----绘制的花(可以是SVG or 很小的图片)按照曲线路径绘制
//        Path+PathMeasure （小船的案例。）
//        2.控制View的动画，每一朵鲜花都是一个ImageView，可以通过属性动画控制曲线运动
//        ObjectAnimator.
//
//                贝塞尔曲线：贝塞尔曲线方程式
//        2.作业：淘宝购物旋转动画
//
//                ================================================
//        ObjectAnimator.ofFloat(view,"translationX",0f,1f);
//        反射Method实现的调用setTranslationX(x)
//        Method.invoke(object, 参数);
//
//
//        属性动画源码---作业：看NineOldAndroid.jar源码实现。
    }
}
