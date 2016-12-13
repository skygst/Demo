package com.gst.move.level_c_game;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ebodoo.raz.BaseActivity;
import com.gst.move.R;

/**
 * Created by gst-pc on 2016/10/10.
 */

public class AnimationMoveActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_move);

        init();
        setView();
    }

    private void init() {
        String commonPath = Environment.getExternalStorageDirectory()
                + "/raz_english/";
        System.out.println("----------commonPath------------------" + commonPath);
//        if(FileBiz.isExists(commonPath+"homePic")) {
//            System.out.println("---------- 存在 -----------");
//        } else {
//            System.out.println("----------  不存在 -----------");
//        }
//
//        if(FileBiz.isExists(commonPath+"homePic/planet_star_1.png")) {
//            System.out.println("---------- planet_star_1存在 -----------");
//        } else {
//            System.out.println("----------  planet_star_1不存在 -----------");
//        }
//        if(FileBiz.isExists(commonPath+"homePic/planet_star_10.png")) {
//            System.out.println("---------- planet_star_10存在 -----------");
//        } else {
//            System.out.println("----------  planet_star_10不存在 -----------");
//        }
    }

    private void setView() {
        ivPic = (ImageView) findViewById(R.id.iv_pic);
        // 添加帧动画
        ivPic.setImageResource(R.drawable.lailai_move);
//        ivPic.setImageResource(R.drawable.away_ride_a_bike);
//        Translate();
        ivPic.setOnClickListener(this);
    }

    public void startAnimationView() {
        // 循环
        AnimationDrawable animationDrawable = (AnimationDrawable) ivPic.getDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.start();


//        Animation anim = AnimationUtils.loadAnimation(this,R.anim.translate_thank_you_tyrant);
//        ivPic.startAnimation(anim);
        // 移动
        Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,1800,Animation.RELATIVE_TO_SELF,0);
        anim.setDuration(5000);
        anim.setFillAfter(true);
        anim.setRepeatMode(Animation.REVERSE);    //反方向执行
        ivPic.startAnimation(anim);
//        anim.start();

        // 缩放
        final ScaleAnimation animation =new ScaleAnimation(1.0f, 2.5f, 1.0f, 2.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(1000);//设置动画持续时间
        ivPic.startAnimation(animation);

        AnimationSet as = new AnimationSet(true);
        as.addAnimation(anim);
        as.addAnimation(animation);
        as.setDuration(3000);
        as.setFillAfter(true);//设置动画结束之后是否保持动画的目标状态
//        as.setFillBefore(false);//设置动画结束之后是否保持动画开始时的状态
//        as.start();
        ivPic.setAnimation(as);
    }

    public void Translate() {
        AnimationSet animationSet=new AnimationSet(true);
        TranslateAnimation translateAnimation=new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,  //X轴的开始位置
                Animation.RELATIVE_TO_SELF, 1.5f,  //X轴的结束位置
                Animation.RELATIVE_TO_SELF, 0f,  //Y轴的开始位置
                Animation.RELATIVE_TO_SELF, 5.0f);  //Y轴的结束位置
        translateAnimation.setDuration(2000);
        animationSet.addAnimation(translateAnimation);

        /*
         * 第一行的设置如果为true，则动画执行完之后效果定格在执行完之后的状态
         * 第二行的设置如果为false，则动画执行完之后效果定格在执行完之后的状态
         * 第三行设置的是一个long类型的值，是指动画延迟多少毫秒之后执行
         * 第四行定义的是动画重复几次执行
         */
        animationSet.setFillAfter(true);
        animationSet.setFillBefore(false);
        animationSet.setStartOffset(2000);
        animationSet.setRepeatCount(3);

        ivPic.startAnimation(animationSet);
    }

    @Override
    public void onClick(View v) {
        if(v == ivPic) {
            startAnimationView();
        }
    }
}
