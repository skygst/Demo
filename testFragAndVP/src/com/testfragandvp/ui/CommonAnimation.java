package com.testfragandvp.ui;

import java.lang.reflect.Field;

import com.gst.move.utils.BaseCommon;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;

public class CommonAnimation {
	
	
	public static void setQipaoAnimation(ImageView iv){
		float scale = 1.0f;
		Animation mScaleAnimation = new ScaleAnimation(scale, scale/1.1f, scale, scale/1.1f,// 整个屏幕就 到 的大小//缩放
	            Animation.INFINITE, scale,
	            Animation.INFINITE, scale);
		/*Animation mScaleAnimation = new ScaleAnimation(scaleQP, scaleQP*1.1f, scaleQP, scaleQP*1.1f,// 整个屏幕就 到 的大小//缩放
	            Animation.INFINITE, scaleQP,
	            Animation.INFINITE, scaleQP);*/
	    mScaleAnimation.setDuration(1000);			//执行一次的时间1s
	    mScaleAnimation.setFillAfter(true);
	    mScaleAnimation.setRepeatCount(-1);			//播放次数，-1为重复播放
	    mScaleAnimation.setStartOffset(200);		//停顿200ms
	    mScaleAnimation.setRepeatMode(Animation.REVERSE);  //动画反向执行一遍
	    	    
	    AnimationSet mAnimationSet=new AnimationSet(false);
	    mAnimationSet.addAnimation(mScaleAnimation);
	    mAnimationSet.setFillAfter(true);
	    mAnimationSet.setRepeatCount(-1);
	    iv.startAnimation(mAnimationSet);
	}

	public static void stopQipiaoAnimation(ImageView iv){
		iv.clearAnimation();
	}
	
	public static void startShouAnimation(ImageView iv){
		float scale = 0.7f;
		Animation mScaleAnimation = new ScaleAnimation(scale, scale*1.3f, scale, scale*1.3f,// 整个屏幕就 到 的大小//缩放
	            Animation.INFINITE, scale,
	            Animation.INFINITE, scale);
	    mScaleAnimation.setDuration(500);			//执行一次的时间1s
	    mScaleAnimation.setFillAfter(true);
	    mScaleAnimation.setRepeatCount(-1);			//播放次数，-1为重复播放
	    mScaleAnimation.setStartOffset(200);		//停顿200ms
	    mScaleAnimation.setRepeatMode(Animation.REVERSE);  //动画反向执行一遍
	    	    
	    AnimationSet mShouAnimationSet=new AnimationSet(false);
	    mShouAnimationSet.addAnimation(mScaleAnimation);
	    mShouAnimationSet.setFillAfter(true);
	    mShouAnimationSet.setRepeatCount(-1);
	    iv.startAnimation(mShouAnimationSet);
	}
	
	public static void startChangeBigAnimation(Button iv,long time){
		float scale = 1.0f;
		Animation mScaleAnimation = new ScaleAnimation(scale*0.9f, scale, scale*0.9f, scale,// 整个屏幕就 到 的大小//缩放
	            Animation.INFINITE, scale,
	            Animation.INFINITE, scale);
	    mScaleAnimation.setDuration(time);			//执行一次的时间1s
	    mScaleAnimation.setFillAfter(true);
	    mScaleAnimation.setRepeatCount(0);			//播放次数，-1为重复播放
	    mScaleAnimation.setStartOffset(200);		//停顿200ms
	    mScaleAnimation.setRepeatMode(Animation.REVERSE);  //动画反向执行一遍
	    	    
	    AnimationSet mShouAnimationSet=new AnimationSet(false);
	    mShouAnimationSet.addAnimation(mScaleAnimation);
	    mShouAnimationSet.setFillAfter(true);
	    mShouAnimationSet.setRepeatCount(-1);
	    iv.startAnimation(mShouAnimationSet);
	}
	
	public static void stopShouAnimation(ImageView iv){
		iv.clearAnimation();
	}
	
	//抖动的动画
	public static void shakeAnimation(Context mContext,View v){
		Animation shake = AnimationUtils.loadAnimation(mContext, BaseCommon.getAnimViewId(mContext,"shake"));//加载动画资源文件
		v.startAnimation(shake);
	}
	
	//翻牌的动画
	public static void flopAnimation(Context mContext,final View view){
		
		  Animation animation = AnimationUtils.loadAnimation(mContext, getAnimViewId(mContext,"flop"));
		    animation.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					view.setVisibility(View.INVISIBLE);
				}
			});
		   
		    view.startAnimation(animation);
	}
	
	/**
	 * 通过反射获得资源文件的ID
	 * 
	 * @param animName
	 * @return
	 */
	public static int getAnimViewId(Context context, String animName) {
		try {
			Field field;
			field = Class.forName(context.getPackageName() + ".R$anim")
					.getField(animName);

			return field.getInt(field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
