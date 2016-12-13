package com.gst.move.utils;

import java.util.Random;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaCommon {
	static MediaPlayer mMediaPlayer;
	public static void pauseMediaplay(){
		try{
			if(mMediaPlayer != null){
				mMediaPlayer.pause();
			}
		}catch(Exception e){
			
		}
		
	}
	
	//五大洲 选对了
	public static void playFuxiGood(Context context) {
		// MediaCommon.PlayMusic(getCommonMedia(context,15));
		mMediaPlayer = getCommonMedia(context, 15);
		PlayMusic2();
	}
	
	//五大洲 选错了
	public static void playFuxiError(Context context) {
		// MediaCommon.PlayMusic(getCommonMedia(context,16));
		mMediaPlayer = getCommonMedia(context, 16);
		PlayMusic2();
	}
	
	private static int randData1_4() {
		Random random = new Random();
		return (random.nextInt(4) + 1);
	}
	
	public static MediaPlayer getCommonMedia(Context mContext, int index) {
		MediaPlayer mMediaPlayerGj = null;
		if (index == 0) {//跟我读 提示音
		} else if (index == 1) {// 闯关点击泡泡提示

		} else if (index == 3) { // 答对了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "good_" + randData1_4()));
		} else if (index == 4) { // 答错了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "error_sorry"));
		} else if (index == 5) { // 再试一次
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "error_try_again"));
		}  else if (index == 6) {
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "ppg_click_the_bubble"));
		}else if(index == 7){	//点点这里
//			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
//				mMediaPlayerGj = MediaPlayer.create(mContext,
//						R.raw.en_clickhere);
//			} else if (Constant.languageType == Constant.LANGUAGE_CHINESE) {
//				mMediaPlayerGj = MediaPlayer.create(mContext,
//						R.raw.ch_clickhere);
//			}
		}else if(index == 8){    //读看到的单词
//			if (Constant.languageType == Constant.LANGUAGE_ENGLISH) {
//				mMediaPlayerGj = MediaPlayer.create(mContext,
//						R.raw.read_look_word_en);
//			} else if (Constant.languageType == Constant.LANGUAGE_CHINESE) {
//				mMediaPlayerGj = MediaPlayer.create(mContext,
//						R.raw.read_look_word_ch);
//			}
		} 
		else if (index == 9) { // 错误太多，重学习——继续努力哦的提示
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "music_fail_note"));
		}else if(index == 10){
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "click_hear"));
			
		}else if (index == 11) { // 答错了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id4(mContext, "error_sorry"));
		}else if (index == 12) { // sayit
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id(mContext, "en_sayit"));
		}
		
		else if (index == 13) { // 选择你听到的字母
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getMp3Id2(mContext, "select_hear_zm"));
		}else if (index == 14) { 
			
		} else if (index == 15) { // 答对了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getFuxiGoodMp3Id(mContext));
		} else if (index == 16) { // 答错了
			mMediaPlayerGj = MediaPlayer.create(mContext,
					BaseCommon.getFuxiErrorMp3Id(mContext));
		}

		return mMediaPlayerGj;
	}
	
	public static void PlayMusic2() {  		
		try { 
			mMediaPlayer.setLooping(false);
        } catch (Exception ex) {  
        } 
//		try { 
//			mMMediaPlayer3.prepare();
//        } catch (Exception ex) {  
//        }  
		try {
			mMediaPlayer.start();// 开始播放 
			mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					// 播放结束后的动作
					if(mp != null){
						mp.release();
						mp = null;
					}
				}
			});
			
			mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO Auto-generated method stub
					if(mp != null) {
						mp.release();  
						mp = null;
                    }
					return false;
				}
			});
		}catch (Exception ex) {  
        } 
    } 

}
