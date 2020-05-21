package com.ttd.androidlearning.animations;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import com.ttd.androidlearning.R;

/**
 * 动画
 * @author wt
 * @time 2020/4/23
 *
 *  {@link Animation#setInterpolator(Interpolator)}
 *  {@link Interpolator }~~~定义了动画的变化速度~~~
 *  {@link AccelerateDecelerateInterpolator }先加速后减速
 *  {@link AccelerateInterpolator} 逐渐加速
 *  {@link LinearInterpolator} 平稳不变
 *  {@link DecelerateInterpolator} 逐渐减速
 *  {@link CycleInterpolator} 曲线运动特效
 */
public class ViewAnimationsActivity extends ComponentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_animations);

        initAlphaAnimation();
        initRotateAnimation();
        initScaleAnimation();
        initTranslateAnimation();

        initAnimationSet();
        initFrameAnimation();

    }

    /**
     * 透明度变化
     */
    private void initAlphaAnimation() {
        View v1 = findViewById(R.id.v_1);

        Animation fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setInterpolator(new DecelerateInterpolator());//变化速度递减
        fadeIn.setDuration(1000);
        fadeIn.setFillAfter(true);//动画停留在结束时的状态

        Animation fadeOut = new AlphaAnimation(1, 0);
//        fadeOut.setInterpolator(new AccelerateInterpolator());//变化速度递增
//        fadeOut.setStartOffset(1000);//延时启动
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);

        v1.setOnClickListener(view -> {
            if (null == v1.getAnimation() || v1.getAnimation() == fadeIn) {
                v1.startAnimation(fadeOut);
            } else {
                v1.startAnimation(fadeIn);
            }
        });
    }

    /**
     * 旋转动画
     */
    private void initRotateAnimation() {
        View v2 = findViewById(R.id.v_2);

        Animation mRotateUpAnim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateUpAnim.setInterpolator(new LinearInterpolator());
//        mRotateUpAnim.setRepeatCount(Integer.MAX_VALUE);//重复次数
        mRotateUpAnim.setDuration(600);
        mRotateUpAnim.setFillAfter(true);

        v2.setOnClickListener(view -> {
            v2.startAnimation(mRotateUpAnim);
        });
    }

    /**
     * 缩放动画
     */
    private void initScaleAnimation() {
        View v3 = findViewById(R.id.v_3);

        Animation scaleAnimation = new ScaleAnimation(1.0f, 1f, 0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        scaleAnimation.setFillAfter(true);

        v3.setOnClickListener(v -> {
            v3.startAnimation(scaleAnimation);
        });
    }

    /**
     * 位移动画
     */
    private void initTranslateAnimation() {
        View v4 = findViewById(R.id.v_4);
        Animation animation = new TranslateAnimation(0, 0, 0, 150);
        animation.setFillAfter(true);
        animation.setDuration(300);

        v4.setOnClickListener(v -> {
            v4.startAnimation(animation);
        });
    }

    /**
     * 动画组
     */
    private void initAnimationSet() {
        View v5 = findViewById(R.id.v_5);

        Animation a1 = new TranslateAnimation(-90, 150, 0, 0);
        a1.setFillAfter(true);
        a1.setDuration(1000);

        Animation a2 = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        a2.setInterpolator(new LinearInterpolator());
        a2.setDuration(600);
        a2.setFillAfter(true);

        Animation a3 = new ScaleAnimation(1.0f, 0.5f, 1.0f, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        a3.setDuration(500);
        a3.setFillAfter(true);

        Animation a4 = new AlphaAnimation(1, 0);
        a4.setDuration(1000);
        a4.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(a1);
        set.addAnimation(a2);
        set.addAnimation(a3);
        set.addAnimation(a4);

        v5.setOnClickListener(v -> {
            v5.startAnimation(set);
        });
    }

    private void initFrameAnimation(){
        ImageView v6 = findViewById(R.id.v_6);
        v6.setBackgroundResource(R.drawable.anim_battery);
        AnimationDrawable animation = (AnimationDrawable) v6.getBackground();

        v6.setOnClickListener(v -> {
            if(!animation.isRunning()){
                animation.start();
            }
        });
    }
}
