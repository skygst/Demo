package com.example.location.biz;

import com.gst.move.test.WearEarActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class VideoBiz {

//	public void playVideo(MyVideoView video, String path) {
//		try {
//			if (path != null && !path.equals("")) {
//				System.out.println("path :" + path);
//				video.setVideoURI(Uri.parse(path));
//				video.requestFocus();
//				video.start();
//				video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//					@Override
//					public void onCompletion(MediaPlayer mp) {
//						
//					}
//				});
//
//				video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//
//					@Override
//					public void onPrepared(MediaPlayer arg0) {
//
//					}
//				});
//				video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
//					
//					@Override
//					public boolean onError(MediaPlayer mp, int what, int extra) {
//						// TODO Auto-generated method stub
//						return true;
//					}
//				});
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public ImageView setQipaoAnimation(ImageView iv, float scaleQP) {
		/*
		 * Animation mScaleAnimation = new ScaleAnimation(scaleQP, scaleQP *
		 * 1.1f, scaleQP, scaleQP * 1.1f,// 整个屏幕就 到 的大小//缩放 Animation.INFINITE,
		 * scaleQP, Animation.INFINITE, scaleQP);
		 */

		float scale = 1.0f;
		Animation mScaleAnimation = new ScaleAnimation(scale, scale / 1.1f,
				scale, scale / 1.1f,// 整个屏幕就 到 的大小//缩放
				Animation.INFINITE, scale, Animation.INFINITE, scale);
		mScaleAnimation.setDuration(1000); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(-1); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mAnimationSet = new AnimationSet(false);
		mAnimationSet.addAnimation(mScaleAnimation);
		mAnimationSet.setFillAfter(true);
		mAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mAnimationSet);
		return iv;
	}

	public View startShouAnimation(View iv) {
		iv = startShouAnimation(iv, 0.7f);
		return iv;
	}

	public View startShouAnimation(View iv, float scale) {
		Animation mScaleAnimation = new ScaleAnimation(scale, scale * 1.3f,
				scale, scale * 1.3f,// 整个屏幕就 到 的大小//缩放
				Animation.INFINITE, scale, Animation.INFINITE, scale);
		mScaleAnimation.setDuration(500); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(-1); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(true);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
		return iv;
	}
	
	public View startShouAnimation2(View iv, float scale) {
		Animation mScaleAnimation = new ScaleAnimation(scale, scale * 1.1f,
				scale, scale * 1.1f,// 整个屏幕就 到 的大小//缩放
				Animation.INFINITE, scale, Animation.INFINITE, scale);
		mScaleAnimation.setDuration(500); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(-1); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mShouAnimationSet = new AnimationSet(false);
		mShouAnimationSet.addAnimation(mScaleAnimation);
		mShouAnimationSet.setFillAfter(true);
		mShouAnimationSet.setRepeatCount(-1);
		iv.startAnimation(mShouAnimationSet);
		return iv;
	}

	public static void startAfterAnimation(View iv) {
		float scale = 0.8f;
		Animation mScaleAnimation = new ScaleAnimation(scale, scale * 1.3f,
				scale, scale * 1.3f,// 整个屏幕就 到 的大小//缩放
				Animation.INFINITE, scale, Animation.INFINITE, scale);
		mScaleAnimation.setDuration(800); // 执行一次的时间1s
		mScaleAnimation.setFillAfter(true);
		mScaleAnimation.setRepeatCount(8); // 播放次数，-1为重复播放
		mScaleAnimation.setStartOffset(200); // 停顿200ms
		mScaleAnimation.setRepeatMode(Animation.REVERSE); // 动画反向执行一遍

		AnimationSet mAfterAnimationSet = new AnimationSet(false);
		mAfterAnimationSet.addAnimation(mScaleAnimation);
		mAfterAnimationSet.setFillAfter(true);
		mAfterAnimationSet.setRepeatCount(8);
		iv.startAnimation(mAfterAnimationSet);
	}
	public static void stopAfterAnimation(View iv){
		iv.clearAnimation();
	}
	// 清除数据
//	public void clearMediaPlayer(MediaPlayer mMediaPlayer,
//			MediaPlayer mMediaPlayerSound, MyVideoView video) {
//		try {
//			if (mMediaPlayer != null) {
//				mMediaPlayer.stop();
//				mMediaPlayer.release();
//				mMediaPlayer = null;
//			}
//			if (null != video) {
//				video.stopPlayback();
//				video = null;
//			}
//			if (mMediaPlayerSound != null) {
//				mMediaPlayerSound.stop();
//				mMediaPlayerSound.release();
//				mMediaPlayerSound = null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void playBgMusic(final MediaPlayer mMediaPlayer) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (mMediaPlayer != null) {
					mMediaPlayer.start();
					mMediaPlayer.setLooping(true);
				}
			}
		}).start();
	}

	public void playSoundMusic(final MediaPlayer mMediaPlayer) {
		if (mMediaPlayer != null) {
			mMediaPlayer.start();
			mMediaPlayer.setLooping(false);
		}
		/*
		 * new Thread(new Runnable() {
		 * 
		 * @Override public void run() {
		 * 
		 * } }).start();
		 */
	}

	// 太空视频界面SpaceVideoActivity循环点切换
	public Object[] transformationNum(int clickNum) {
		Object[] obj = new Object[2];
		int seekTo = 0, nextTime = 0;
		if (clickNum == 1) {
			seekTo = 4;
			nextTime = 5;
		} else if (clickNum == 2) {
			seekTo = 7;
			nextTime = 8;
		} else if (clickNum == 3) {
			seekTo = 10;
			nextTime = 11;
		} else if (clickNum == 4) {
			seekTo = 13;
			nextTime = 14;
		} else if (clickNum == 5) {
			seekTo = 16;
			nextTime = 17;
		} else if (clickNum == 6) {
			seekTo = 19;
			nextTime = 20;
		} else if (clickNum == 7) {
			seekTo = 22;
			nextTime = 23;
		} else if (clickNum == 8) {
			seekTo = 25;
			nextTime = 26;
		} else if (clickNum == 9) {
			seekTo = 28;
			nextTime = 29;
		} else if (clickNum == 10) {
			seekTo = 31;
			nextTime = 32;
		} else if (clickNum == 11) {
			seekTo = 34;
			nextTime = 35;
		} else if (clickNum == 12) {
			seekTo = 37;
			nextTime = 38;
		} else if (clickNum == 13) {
			seekTo = 40;
			nextTime = 41;
		} else if (clickNum == 14) {
			seekTo = 43;
			nextTime = 44;
		} else if (clickNum == 15) {
			seekTo = 46;
			nextTime = 47;
		} else if (clickNum == 16) {
			seekTo = 49;
			nextTime = 50;
		} else if (clickNum == 17) {
			seekTo = 52;
			nextTime = 53;
		} else if (clickNum == 18) {
			seekTo = 55;
			nextTime = 56;
		} else if (clickNum == 19) {
			seekTo = 58;
			nextTime = 59;
		} else if (clickNum == 20) {
			seekTo = 61;
			nextTime = 62;
		} else if (clickNum == 21) {
			seekTo = 64;
			nextTime = 65;
		} else if (clickNum == 22) {
			seekTo = 67;
			nextTime = 68;
		} else if (clickNum == 23) {
			seekTo = 70;
			nextTime = 71;
		} else if (clickNum == 24) {
			seekTo = 73;
			nextTime = 74;
		} else if (clickNum == 25) {
			seekTo = 76;
			nextTime = 77;
		} else if (clickNum == 26) {
			seekTo = 79;
			nextTime = 80;
		} else if (clickNum == 27) {
			seekTo = 82;
			nextTime = 83;
		}

		obj[0] = seekTo;
		obj[1] = nextTime;
		return obj;
	}

	/**
	 * 
	 * @param clickNum
	 * @param isEg
	 * @param isFromLeftToRight
	 *            :从左边到右边
	 * @param isStop
	 *            ： 停止在4个不动的界面
	 * @return
	 */
	public Object[] selectLevelNum(Context context, int clickNum) {
		Object[] obj = new Object[2];
		int seekTo = 0, nextTime = 0;
		if (clickNum == 0) {
			seekTo = 0;
			nextTime = 1;
		} else if (clickNum == 1) {
			seekTo = 2;
			nextTime = 3;
		} else if (clickNum == 2) {
			seekTo = 4;
			nextTime = 5;
		} else if (clickNum == 3) {
			seekTo = 6;
			nextTime = 7;
		} else if(clickNum == 4){
			seekTo = 8;
			nextTime = 9; // 停留在4个都高亮的界面
		} else if(clickNum == 5){
			seekTo = 10;
			nextTime = 11; 
		} else if(clickNum == 6) {
			seekTo = 12;
			nextTime = 13; 
		}
		obj[0] = seekTo;
		obj[1] = nextTime;
		return obj;
	}

	public Object[] selectLevelMove(Context context, boolean isEg,
			boolean isFromLeftToRight, boolean isStop) {
		Object[] obj = new Object[2];
		int seekTo = 0, nextTime = 0;
		if (isStop) {
			seekTo = 8;
			nextTime = 9; // 停留在4个都高亮的界面
		} else {
			if (isEg) {
				if (isFromLeftToRight) {
					seekTo = 14;
					nextTime = 15; // 从4个高亮界面移动到yingwen的"即将上线"界面
				} else {
					seekTo = 16;
					nextTime = 17; // 从yingwen的"即将上线"界面跳转到4个都高亮的界面
				}
			} else {
				if (isFromLeftToRight) {
					seekTo = 10;
					nextTime = 11; // 从4个高亮界面移动到"即将上线"界面
				} else {
					seekTo = 12;
					nextTime = 13; // 从中文的"即将上线"界面跳转到4个都高亮的界面
				}
			}
		}
		obj[0] = seekTo;
		obj[1] = nextTime;
		return obj;
	}

	public Object[] selectLevel04(int clickNum) {
		Object[] obj = new Object[2];
		int seekTo = 0, nextTime = 0;
		if (clickNum == 0) {
			seekTo = 3;
			nextTime = 5;
		} else if (clickNum == 1) {
			seekTo = 7;
			nextTime = 9;
		} else if (clickNum == 2) {
			seekTo = 11;
			nextTime = 13;
		} else if (clickNum == 3) {
			seekTo = 15;
			nextTime = 17;
		} else if (clickNum == 4) {
			seekTo = 19;
			nextTime = 21;
		} else if (clickNum == 5) {
			seekTo = 23;
			nextTime = 25;
		} else if (clickNum == 6) {
			seekTo = 27;
			nextTime = 29;
		} else if (clickNum == 7) {
			seekTo = 31;
			nextTime = 33;
		} else if (clickNum == 8) {
			seekTo = 35;
			nextTime = 37;
		} else if (clickNum == 9) {
			seekTo = 39;
			nextTime = 41;
		} else if (clickNum == 10) {
			seekTo = 43;
			nextTime = 45;
		} else if (clickNum == 11) {
			seekTo = 47;
			nextTime = 49;
		} else if (clickNum == 12) {
			seekTo = 51;
			nextTime = 53;
		} else if (clickNum == 13) {
			seekTo = 55;
			nextTime = 57;
		} else if (clickNum == 14) {
			seekTo = 59;
			nextTime = 61;
		} else if (clickNum == 15) {
			seekTo = 63;
			nextTime = 65;
		} else if (clickNum == 16) {
			seekTo = 67;
			nextTime = 69;
		} else if (clickNum == 17) {
			seekTo = 71;
			nextTime = 73;
		} else if (clickNum == 18) {
			seekTo = 75;
			nextTime = 77;
		} else if (clickNum == 19) {
			seekTo = 79;
			nextTime = 81;
		} else if (clickNum == 20) {
			seekTo = 83;
			nextTime = 85;
		} else if (clickNum == 21) {
			seekTo = 87;
			nextTime = 89;
		} else if (clickNum == 22) {
			seekTo = 91;
			nextTime = 93;
		} else if (clickNum == 23) {
			seekTo = 95;
			nextTime = 97;
		} else if (clickNum == 24) {
			seekTo = 99;
			nextTime = 101;
		} else if (clickNum == 25) {
			seekTo = 103;
			nextTime = 105;
		} else if (clickNum == 26) {
			seekTo = 107;
			nextTime = 111;
		} else if (clickNum == 27) {
			seekTo = 113;
			nextTime = 117;
		} else if (clickNum == 28) {
			seekTo = 119;
			nextTime = 123;
		} else if (clickNum == 29) {
			seekTo = 125;
			nextTime = 129;
		} else if (clickNum == 30) {
			seekTo = 131;
			nextTime = 132;
		}
		obj[0] = seekTo;
		obj[1] = nextTime;
		return obj;
	}

	public Object[] selectLevel05(int clickNum) {
		Object[] obj = new Object[2];
		int seekTo = 0, nextTime = 0;
		if (clickNum == 1) {
			seekTo = 7;
			nextTime = 9;
		} else if (clickNum == 2) {
			seekTo = 11;
			nextTime = 13;
		} else if (clickNum == 3) {
			seekTo = 15;
			nextTime = 17;
		} else if (clickNum == 4) {
			seekTo = 19;
			nextTime = 21;
		} else if (clickNum == 5) {
			seekTo = 23;
			nextTime = 27;
		} else if (clickNum == 6) {
			seekTo = 32;
			nextTime = 38;
		} else if (clickNum == 7) {
			seekTo = 40;
			nextTime = 42;
		} else if (clickNum == 8) {
			seekTo = 44;
			nextTime = 46;
		} else if (clickNum == 9) {
			seekTo = 48;
			nextTime = 54;
		} else if (clickNum == 10) {
			seekTo = 56;
			nextTime = 58;
		} else if (clickNum == 11) {
			seekTo = 62;
			nextTime = 68;
		} else if (clickNum == 12) {
			seekTo = 70;
			nextTime = 72;
		} else if (clickNum == 13) {
			seekTo = 76;
			nextTime = 82;
		} else if (clickNum == 14) {
			seekTo = 84;
			nextTime = 90;
		} else if (clickNum == 15) {
			seekTo = 92;
			nextTime = 96;
		} else if (clickNum == 16) {
			seekTo = 98;
			nextTime = 102;
		} else if (clickNum == 17) {
			seekTo = 104;
			nextTime = 108;
		}
		obj[0] = seekTo;
		obj[1] = nextTime;
		return obj;
	}
	
	// 二次循环使用
	public int secondaryRecycled(int clickNum) {
		int currentIndex = 0;
		if(clickNum == 17) {
			currentIndex = 69;
		} else if(clickNum == 19) {
			currentIndex = 82;
		} else if(clickNum == 21) {
			currentIndex = 95;
		} else if(clickNum == 23) {
			currentIndex = 108;
		} else if(clickNum == 25) {
			currentIndex = 118;
		}
		return currentIndex;
	}
	
	
	public int secondClickQipaoLevel07(int clickNum) {
		int index = 0;
		if (clickNum == 16) {
			index = 100;
		} else if (clickNum == 18) {
			index = 101;
		} else if (clickNum == 20) {
			index = 102;
		} else if (clickNum == 22) {
			index = 103;
		} else {
			index = clickNum;
		}
		return index;
	}
	
	/* 设置视图的位置 */
	public void setViewPosition(View view, int i, int level_position[][], float scaleQPW, float scaleQPH, int xoffset, int yoffset, float suoxiao) {
		view.setLayoutParams(LayoutParameters.setViewPositionParams(
				level_position[i][0],
				level_position[i][1],
				level_position[i][2],
				level_position[i][3], scaleQPW, scaleQPH,
				xoffset, yoffset, suoxiao));
	}
	
	public void setViewPositionNoSuoxiao(View view, int i, int level_position[][], float scaleQPW, float scaleQPH) {
		setViewPosition(view,i,level_position,scaleQPW,scaleQPH,0,0,1.0f);
	}
	
	
	public LayoutParams setViewTopPositionParams(int y, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(LayoutParams.WRAP_CONTENT*wBeisu).intValue(), Float.valueOf(242*hBeisu).intValue());
		int intentTopValue = Float.valueOf(y * hBeisu).intValue();
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = intentTopValue;
		return params;
	}
	
	public void gotoActivity(Context context, Class<?> classes) {
		context.startActivity(new Intent(context, classes));
	}
	
	public RelativeLayout.LayoutParams setAlbumsSelParams(int width, int height, int leftValue, int rightId, int heiValue, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * scaleQPW).intValue(), Float.valueOf(
						(height + heiValue) * scaleQPH).intValue());
		if(leftValue != 0) {
			int intentLeftValue = Float.valueOf(
					(leftValue * scaleQPW + scaleQPW)).intValue();
			params.leftMargin = intentLeftValue;
		}
		if(rightId != 0) {
			params.addRule(RelativeLayout.RIGHT_OF, rightId);
		}
		return params;
	}

}
