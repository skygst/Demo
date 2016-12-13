package com.example.location.biz;

import com.gst.move.R;
import com.gst.move.test.LevelBActivity;
import com.gst.move.test.MainActivity;

import android.media.MediaPlayer;
import android.view.View;

public class ActivityBiz {

	public static MediaPlayer playSoundMusic(MediaPlayer mMediaPlayerSound, String path) {
		try {
			if (mMediaPlayerSound != null) { // 一定要清空播放器资源
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
			mMediaPlayerSound = new MediaPlayer();
			/* 重置MediaPlayer */
			mMediaPlayerSound.reset();
			/* 设置要播放的文件的路径 */
			mMediaPlayerSound.setDataSource(path);
			mMediaPlayerSound.setLooping(false);
			/* 准备播放 */
			mMediaPlayerSound.prepare();
			/* 开始播放 */
			mMediaPlayerSound.start();
			mMediaPlayerSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener()    
            {
                public void onCompletion(MediaPlayer arg0)   
                {   
                }   
            });
			mMediaPlayerSound.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					return false;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mMediaPlayerSound;
	}
	
	public static int clickNum(View v, View[] viewArray) {
		int index = -1;
		for(int i=0; i<viewArray.length; i++) {
			if(v == viewArray[i]) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static void finishAfBookGame() {
		MainActivity.viewPage.setCurrentItem(1);
		MainActivity.viewPage.result = true;
		MainActivity.btnGame.setBackgroundResource(R.drawable.step_finish_game);
	}
	
	public static void finishBfBookGame() {
		LevelBActivity.viewPage.setCurrentItem(1);
		LevelBActivity.viewPage.result = true;
		LevelBActivity.btnGame.setBackgroundResource(R.drawable.step_finish_game);
	}
	
	public static void changeChoiceStatus(int index, int pos) {
		MainActivity.blStatus[index] = true;
		if(ActivityBiz.isFinishAll()) {
			MainActivity.btnDown.setBackgroundResource(R.drawable.step_finish_done);
		}
		String value = "A";
		if(pos == 0) {
			value = "A";
		} else if(pos == 1) {
			value = "B";
		} else if(pos == 2) {
			value = "C";
		}
		MainActivity.replyChoiceAnswerStatus[index] = value;
	}
	
	public static void changeChoiceLevelBStatus(int index, int pos) {
		LevelBActivity.blStatus[index] = true;
		if(ActivityBiz.isFinishAll()) {
			LevelBActivity.btnDown.setBackgroundResource(R.drawable.step_finish_done);
		}
		String value = "A";
		if(pos == 0) {
			value = "A";
		} else if(pos == 1) {
			value = "B";
		} else if(pos == 2) {
			value = "C";
		}
		LevelBActivity.replyChoiceAnswerStatus[index] = value;
	}
	
	public static boolean isFinishAllLevelB() {
		boolean isFinishAll = true;
		for (int i = 0; i < LevelBActivity.blStatus.length; i++) {
			if (!LevelBActivity.blStatus[i]) {
				isFinishAll = false;
				break;
			}
		}
		return isFinishAll;
	}
	
	public static boolean isFinishAll() {
		boolean isFinishAll = true;
		for (int i = 0; i < MainActivity.blStatus.length; i++) {
			if (!MainActivity.blStatus[i]) {
				isFinishAll = false;
				break;
			}
		}
		return isFinishAll;
	}
	
	// 判断两个数组的index的值是否相等
	public static boolean arrayValue(String[] answer, String[] replyAnswer, int index) {
		boolean isRight = false;
		if(index < answer.length && index < replyAnswer.length) {
			if(answer[index].equals(replyAnswer[index])) {
				isRight = true;
			}
		}
		return isRight;
	}
	
}
