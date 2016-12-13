package com.ebodoo.raz.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
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
	
	public static void startShouAnimation2(ImageView iv){
		float scale = 0.8f;
		Animation mScaleAnimation = new ScaleAnimation(scale*1.0f, scale * 1.2f,
				scale*1.0f, scale * 1.2f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mScaleAnimation.setDuration(600); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(-1); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(true);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
	}
	
	public static void startShouAnimation(ImageView iv){
		float scale = 0.7f;
		Animation mScaleAnimation = new ScaleAnimation(scale, scale*1.3f, scale, scale*1.3f,// 整个屏幕就 到 的大小//缩放
	            Animation.RELATIVE_TO_SELF, 0.5f,
	            Animation.RELATIVE_TO_SELF, 0.5f);
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
	
	public static void startShouAnimation22(View iv){
		float scale = 1.0f;
		Animation mScaleAnimation = new ScaleAnimation(1.0f, scale*1.2f, scale, scale*1.2f,// 整个屏幕就 到 的大小//缩放
	            Animation.RELATIVE_TO_SELF, 0.5f,
	            Animation.RELATIVE_TO_SELF, 0.5f);
	    mScaleAnimation.setDuration(200);			//执行一次的时间1s
	    mScaleAnimation.setFillAfter(true);
	    mScaleAnimation.setRepeatCount(1);			//播放次数，-1为重复播放
	    mScaleAnimation.setStartOffset(150);		//停顿200ms
	    mScaleAnimation.setRepeatMode(Animation.REVERSE);  //动画反向执行一遍

		AnimationSet mShouAnimationSet=new AnimationSet(false);
	    mShouAnimationSet.addAnimation(mScaleAnimation);
	    mShouAnimationSet.setFillAfter(true);
	    mShouAnimationSet.setRepeatCount(-1);
	    iv.startAnimation(mShouAnimationSet);
	}

	// 放大和渐变的动画
	public static void enlargeAndGradientAnimation(View iv){
		float scale = 1.0f;
		Animation mScaleAnimation = new ScaleAnimation(scale*1.2f, 0.0f, scale*1.2f, 0.0f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mScaleAnimation.setDuration(800);			//执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(1);			//播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(150);		//停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE);  //动画反向执行一遍

		// 渐变
		Animation animation = new AlphaAnimation(1.0f, 0.0f);
		animation.setDuration(1000);

		AnimationSet mShouAnimationSet=new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.addAnimation(animation);
		mShouAnimationSet.setFillAfter(true);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
	}

	// 无限循环播放
	public static void startShouAnimation(ImageView iv, float scale, long time){
//		float scale = 1.0f;
		Animation mScaleAnimation = new ScaleAnimation(scale, scale*1.2f, scale, scale*1.2f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mScaleAnimation.setDuration(time);			//执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(-1);			//播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(150);		//停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE);  //动画反向执行一遍

		AnimationSet mShouAnimationSet=new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(true);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
	}
	
	public static void startLettersAnimation(View iv, float scale) {
		Animation mScaleAnimation = new ScaleAnimation(scale, scale * 1.1f,
				scale, scale * 1.1f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_SELF, scale, Animation.RELATIVE_TO_SELF, scale);
		mScaleAnimation.setDuration(1800); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(-1); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(true);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
	}
	
	public static void startCountryAnimation(View iv, float scale) {
		Animation mScaleAnimation = new ScaleAnimation(scale, scale * 1.3f,
				scale, scale * 1.3f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_SELF, scale, Animation.RELATIVE_TO_SELF, scale);
		mScaleAnimation.setDuration(800); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(-1); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(true);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
	}
	
	public static void startLettersAnimation(View iv){
		ScaleAnimation mScaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f,1.2f, 
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
	    mScaleAnimation.setDuration(300);			//执行一次的时间1s
	    mScaleAnimation.setFillAfter(true);
	    mScaleAnimation.setRepeatCount(1);			//播放次数，-1为重复播放
	    mScaleAnimation.setStartOffset(100);		//停顿200ms
	    mScaleAnimation.setRepeatMode(Animation.REVERSE);  //动画反向执行一遍
	    	    
	    AnimationSet mShouAnimationSet=new AnimationSet(false);
	    mShouAnimationSet.addAnimation(mScaleAnimation);
	    mShouAnimationSet.setFillAfter(true);
	    mShouAnimationSet.setRepeatCount(-1);
	    iv.startAnimation(mShouAnimationSet);
	}
	
	public static void startAfGameAnimation(View iv){
		ScaleAnimation mScaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f,1.2f, 
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
	    mScaleAnimation.setDuration(300);			//执行一次的时间1s
	    mScaleAnimation.setFillAfter(true);
	    mScaleAnimation.setRepeatCount(-1);			//播放次数，-1为重复播放
	    mScaleAnimation.setStartOffset(100);		//停顿200ms
	    mScaleAnimation.setRepeatMode(Animation.REVERSE);  //动画反向执行一遍
	    	    
	    AnimationSet mShouAnimationSet=new AnimationSet(false);
	    mShouAnimationSet.addAnimation(mScaleAnimation);
	    mShouAnimationSet.setFillAfter(true);
	    mShouAnimationSet.setRepeatCount(-1);
	    iv.startAnimation(mShouAnimationSet);
	}
	
	public static void startLettersViewAnimation(View iv){
		ScaleAnimation mScaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f,1.1f, 
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
	    mScaleAnimation.setDuration(300);			//执行一次的时间1s
	    mScaleAnimation.setFillAfter(true);
	    mScaleAnimation.setRepeatCount(1);			//播放次数，-1为重复播放
	    mScaleAnimation.setStartOffset(100);		//停顿200ms
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
	
	public static void startChangeViewBigAnimation(View iv,long time){
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
//	public static void shakeAnimation(Context mContext,View v){
//		Animation shake = AnimationUtils.loadAnimation(mContext, BaseCommon.getAnimViewId(mContext,"shake"));//加载动画资源文件
//		v.startAnimation(shake);
//	}
	
	//翻牌的动画
//	public static void flopAnimation(Context mContext,final View view){
//		
//		  Animation animation = AnimationUtils.loadAnimation(mContext, BaseCommon.getAnimViewId(mContext,"flop"));
//		    animation.setAnimationListener(new AnimationListener() {
//				
//				@Override
//				public void onAnimationStart(Animation animation) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void onAnimationRepeat(Animation animation) {
//					// TODO Auto-generated method stub
//					
//				}
//				//umeng_socialize_title_back_bt_normal.png
//				@Override
//				public void onAnimationEnd(Animation animation) {
//					// TODO Auto-generated method stub
//					view.setVisibility(View.INVISIBLE);
//				}
//			});
//		   
//		    view.startAnimation(animation);
//	}
	//翻牌的动画
//	public static void flopAnimation2(Context mContext,final View view){
//		
//		  Animation animation = AnimationUtils.loadAnimation(mContext, BaseCommon.getAnimViewId(mContext,"flop"));
//		    animation.setAnimationListener(new AnimationListener() {
//				
//				@Override
//				public void onAnimationStart(Animation animation) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//				@Override
//				public void onAnimationRepeat(Animation animation) {
//					// TODO Auto-generated method stub
//					
//				}
//				//umeng_socialize_title_back_bt_normal.png
//				@Override
//				public void onAnimationEnd(Animation animation) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//		   
//		    view.startAnimation(animation);
//	}
	public static Animation rotateAnimation(){
	     Animation animationR =new RotateAnimation(0f,100f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f); 
	     animationR.setDuration(3000);
	     animationR.setFillAfter(true); 
	     return animationR;
	}

	public static void translateAnimation1(View view,int Duration ,int mx,int x,int ym,int y,float scaleQP){
//		 TranslateAnimation  animation=new TranslateAnimation(0.0f,150, 0, 20f);
		 TranslateAnimation  animation=new TranslateAnimation(mx*scaleQP,x*scaleQP, ym*scaleQP, y*scaleQP);
	     animation.setInterpolator(new LinearInterpolator());      
	     animation.setDuration(Duration);  
	     view.startAnimation(animation);
	     animation.setFillAfter(true); 
	}
	
	public static void stopAnimation(ImageView iv){
		iv.clearAnimation();
	}
	
	public static void enlargeAnimation(View iv, float scale,long time) {
		Animation mScaleAnimation = new ScaleAnimation(scale*0.9f, scale * 1.1f,
				scale*0.9f, scale * 1.1f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mScaleAnimation.setDuration(time); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(false);
		mScaleAnimation.setRepeatCount(0); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(false);
		mShouAnimationSet.setRepeatCount(0);
		iv.startAnimation(mShouAnimationSet);
		
	}
	
	public static void enlargeAnimation3(View iv, float scale,long time) {
		Animation mScaleAnimation = new ScaleAnimation(scale*0.9f, scale * 1.1f,
				scale*0.9f, scale * 1.1f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mScaleAnimation.setDuration(time); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(0); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(true);
		mShouAnimationSet.setRepeatCount(0);
		iv.startAnimation(mShouAnimationSet);
		
	}
	
	public static void enlargeAnimation2(View iv, long time) {
		float scale = 1.0f;
		Animation mScaleAnimation = new ScaleAnimation(scale*1.0f, scale * 1.2f,
				scale*1.0f, scale * 1.2f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		mScaleAnimation.setDuration(time); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(false);
		mScaleAnimation.setRepeatCount(0); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(false);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
	}
	
	public static void enlargeAnimation3(View iv, long time) {
		float scale = 1.0f;
		Animation mScaleAnimation = new ScaleAnimation(scale*1.0f, scale * 1.1f,
				scale*1.0f, scale * 1.1f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
		mScaleAnimation.setDuration(time); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(false);
		mScaleAnimation.setRepeatCount(0); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(false);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
		
	}
	
	public static void enlargeZidanAnimation(View iv) {
		float scale = 1.0f;
		Animation mScaleAnimation = new ScaleAnimation(scale*1.0f, scale * 2.5f,
				scale*1.0f, scale * 2.5f,// 整个屏幕就 到 的大小//缩放
				Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
		mScaleAnimation.setDuration(1000); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(false);
		mScaleAnimation.setRepeatCount(0); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(50); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(false);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
		
	}
	
	//从SD卡获取帧动画
	public static AnimationDrawable setData(String path, String[] picName,
			int index_start,int index_end) {
		AnimationDrawable frameAnim = new AnimationDrawable();
		for (int i = index_start; i <= index_end; i++) {
			frameAnim.addFrame(
					CommonBitmap.drawableBmp(path, picName[i]), 200);
		}
		// 设置只播放一次吗？
		frameAnim.setOneShot(false);
		// 设置ImageView的背景为AnimationDrawable
		return frameAnim;
	}
	//从SD卡获取帧动画
	public static AnimationDrawable setDataOneOnly(String path, String[] picName,
			int index_start,int index_end) {
		AnimationDrawable frameAnim = new AnimationDrawable();
		for (int i = index_start; i <= index_end; i++) {
			frameAnim.addFrame(
					CommonBitmap.drawableBmp(path, picName[i]), 200);
		}
		// 设置只播放一次吗？
		frameAnim.setOneShot(true);
		// 设置ImageView的背景为AnimationDrawable
		return frameAnim;
	}
	//从SD卡获取帧动画——根据时间
	public static AnimationDrawable setData2(String path, String[] picName,
			int index_start,int index_end,int time1,int time2) {
		AnimationDrawable frameAnim = new AnimationDrawable();
		for (int i = index_start; i <= index_end; i++) {
			if(i == index_start)
				frameAnim.addFrame(CommonBitmap.drawableBmp(path, picName[i]), time1);
			else
				frameAnim.addFrame(CommonBitmap.drawableBmp(path, picName[i]), time2);
		}
		// 设置只播放一次吗？
		frameAnim.setOneShot(false);
		// 设置ImageView的背景为AnimationDrawable
		return frameAnim;
	}
	
	public static void translateAnimation(View view,int length ,int time,int mx,int x,int ym,int y,float scaleQP){
		 TranslateAnimation  animation=new TranslateAnimation(mx*scaleQP,x*scaleQP, ym*scaleQP, y*scaleQP);
	     animation.setInterpolator(new LinearInterpolator());      
	     animation.setDuration(length*time);  
	     view.startAnimation(animation);
	     animation.setFillAfter(true); 
	     animation.setAnimationListener(new AnimationListener(){

	    	    public void onAnimationEnd(Animation animation) {
	    	    
	    	    }

	    	    public void onAnimationRepeat(Animation animation) { }
	    	    public void onAnimationStart(Animation animation) { }

	    	    });
	}	
	public static void translateAnimation(final ImageView view,int time,int mx,int x,int ym,int y,float scaleQP){
		 TranslateAnimation  animation=new TranslateAnimation(mx*scaleQP,x*scaleQP, ym*scaleQP, y*scaleQP);
	     animation.setInterpolator(new LinearInterpolator());      
	     animation.setDuration(time);  
	     view.startAnimation(animation);
	     animation.setFillAfter(true); 
	     animation.setAnimationListener(new AnimationListener(){

	    	    public void onAnimationEnd(Animation animation) {
	    	    	view.clearAnimation();
	    	    	view.setVisibility(View.GONE);
	    	    	view.setImageDrawable(null);
	    	    }

	    	    public void onAnimationRepeat(Animation animation) { }
	    	    public void onAnimationStart(Animation animation) { }

	    	    });
	}	
	
	//抖动的动画
		public static void shakeAnimation(Context mContext,View v){
			Animation shake = AnimationUtils.loadAnimation(mContext, BaseCommon.getAnimViewId(mContext,"shake"));//加载动画资源文件
			v.startAnimation(shake);
		}
		
		//翻牌的动画
		public static void flopAnimation(Context mContext,final View view){
			
			  Animation animation = AnimationUtils.loadAnimation(mContext, BaseCommon.getAnimViewId(mContext,"flop"));
			    animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					//umeng_socialize_title_back_bt_normal.png
					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						view.setVisibility(View.INVISIBLE);
					}
				});
			   
			    view.startAnimation(animation);
		}
}
