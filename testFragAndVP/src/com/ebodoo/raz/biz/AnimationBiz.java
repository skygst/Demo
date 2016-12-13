package com.ebodoo.raz.biz;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.utils.BaseCommon;

/**
 * Created by gst-pc on 2016/11/22.
 */

public class AnimationBiz {

    public void startAnimationView(final int fromX, int toX, int fromY, final int toY, int duration, int exValue,
                                   final int clickNum, final boolean isShowSelf, final float scalePh, final ImageView ivAnimation) {
        // 移动
        Animation anim = null;
        final float scaleQPW = (1920 / 1280.0f);
        float  scaleQPH = (1280 / 720.0f);
//        final float scaleQPW = (1280 / 1280.0f);
//        float  scaleQPH = (720 / 720.0f);
        int fromx = Float.valueOf(fromX * scaleQPW).intValue();
        int tox = Float.valueOf(toX * scaleQPW).intValue();
        int fromy = Float.valueOf(fromY * scaleQPH).intValue();
        int toy = Float.valueOf(toY * scaleQPH).intValue();
        if(!isShowSelf) {
            anim = new TranslateAnimation(fromx, tox, fromy, toy);
        } else {
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, tox, Animation.RELATIVE_TO_SELF, toy);
        }
        anim.setDuration(duration);
//        anim.setFillAfter(true);
        ivAnimation.startAnimation(anim);
        AnimationDrawable animationDrawable = null;
        if(toY == 0) {
            animationDrawable = (AnimationDrawable) ivAnimation.getDrawable();
            animationDrawable.setOneShot(false);
            animationDrawable.start();
        }
        final AnimationDrawable anDrawable = animationDrawable;
        int value = toX;
        int mValue = value < 0 ? Math.abs(value + exValue) : value;
        final int proValue = mValue;
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(toY == 0) {
                    anDrawable.stop();
                }
                ivAnimation.clearAnimation();
                if(clickNum >= 3) {
                    ivAnimation.setVisibility(View.GONE);
                } else {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivAnimation.getLayoutParams();
                    if(toY == 0) {
                        params.setMargins(Float.valueOf(proValue * scaleQPW).intValue(), Float.valueOf(21 * scalePh).intValue(),
                                0, 0);// 改变位置,这里是左右不变，上下平移height高度
                    } else {
                        params.setMargins(0, Float.valueOf(-150 * scalePh).intValue(), 0, 0); // left, top, right, bottom
//                        if(isShowSelf)
                        ivAnimation.setVisibility(View.GONE);
                    }
                    ivAnimation.setLayoutParams(params);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void startAnimationView2(final int fromX, int toX, int fromY, final int toY, long duration, final int topValue, int exValue,
              final int clickNum, final boolean isShowSelf, final float scalePh,final ImageView ivAnimation) {
        // 移动
        Animation anim = null;
        final float scaleQPW = (1920 / 1280.0f);
        float  scaleQPH = (1280 / 720.0f);
//        final float scaleQPW = (1280 / 1280.0f);
//        float  scaleQPH = (720 / 720.0f);
        int fromx = Float.valueOf(fromX * scaleQPW).intValue();
        int tox = Float.valueOf(toX * scaleQPW).intValue();
        int fromy = Float.valueOf(fromY * scaleQPH).intValue();
        int toy = Float.valueOf(toY * scaleQPH).intValue();
        if(!isShowSelf) {
            anim = new TranslateAnimation(fromx, tox, fromy, toy);
        } else {
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, tox, Animation.RELATIVE_TO_SELF, toy);
        }
        anim.setDuration(duration);
        ivAnimation.startAnimation(anim);
        int value = toX;
        int mValue = value < 0 ? Math.abs(value + exValue) : value;
        final int proValue = mValue;
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivAnimation.clearAnimation();
                if(clickNum >= 3) {
                    ivAnimation.setVisibility(View.GONE);
                } else {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivAnimation.getLayoutParams();
                    System.out.println("-----------proValue-----------:" + proValue);
                    params.setMargins(Float.valueOf(proValue * scaleQPW).intValue(), Float.valueOf(topValue * scalePh).intValue(),
                            0, 0);// 改变位置,这里是左右不变，上下平移height高度
                    ivAnimation.setLayoutParams(params);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public void startAnimationView3(final int fromX, int toX, int fromY, final int toY, int duration, final int topValue, int exValue,
                                    final int clickNum, final boolean isShowSelf, final float scalePh,final ImageView ivAnimation, final boolean isGone) {
        // 移动
        Animation anim = null;
        final float scaleQPW = (1920 / 1280.0f);
        float  scaleQPH = (1280 / 720.0f);
//        final float scaleQPW = (1280 / 1280.0f);
//        float  scaleQPH = (720 / 720.0f);
        int fromx = Float.valueOf(fromX * scaleQPW).intValue();
        int tox = Float.valueOf(toX * scaleQPW).intValue();
        int fromy = Float.valueOf(fromY * scaleQPH).intValue();
        int toy = Float.valueOf(toY * scaleQPH).intValue();
        System.out.println("---------tox----------:" + tox);
        if (!isShowSelf) {
            anim = new TranslateAnimation(fromx, tox, fromy, toy);
//			anim = new TranslateAnimation(fromX, toX, fromY, toY);
        } else {
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, tox,
                    Animation.RELATIVE_TO_SELF, toy);
//			anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, toX,
//					Animation.RELATIVE_TO_SELF, toY);
        }
        anim.setDuration(duration);
        ivAnimation.startAnimation(anim);
        int value = toX;
        int mValue = value > 0 ? Math.abs(value + exValue) : value;
        final int proValue = mValue;
//        final int proValue = Math.abs(value);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivAnimation.clearAnimation();
                if(clickNum >= 3) {
                    ivAnimation.setVisibility(View.GONE);
                } else {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivAnimation.getLayoutParams();
                    System.out.println("-----------proValue-----------:" + proValue);
                    params.setMargins(Float.valueOf(proValue * scaleQPW).intValue(), Float.valueOf(topValue * scalePh).intValue(),
                            0, 0);// 改变位置,这里是左右不变，上下平移height高度
                    if(isGone)
                        ivAnimation.setVisibility(View.GONE);
                    ivAnimation.setLayoutParams(params);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public AnimationDrawable setAnimation(Context context, int fromIndex, int toIndex, String picName) {
        AnimationDrawable animation = new AnimationDrawable();
            for (int i = fromIndex; i <= toIndex; i++) {
                int resId = new BaseCommon().getImageId(context, picName + i);
                animation.addFrame(context.getResources().getDrawable(resId),
                        80);
            }
            // 设置为循环播放
        animation.setOneShot(true);
        return animation;
    }

    public void starAnimation(ImageView ivAnimation, boolean isOneShot) {
        AnimationDrawable animationDrawable = (AnimationDrawable) ivAnimation.getDrawable();
        animationDrawable.setOneShot(isOneShot);
        animationDrawable.start();
    }

}
