package com.ebodoo.raz.biz;

import android.content.Context;
import android.media.MediaPlayer;

public class MediaPlayerBiz {
	
	public static MediaPlayer playMusicFromResId(Context context, MediaPlayer mMediaPlayer, int resId, boolean isLooping) {
		mMediaPlayer = MediaPlayer.create(context, resId);
		if (mMediaPlayer != null) {
			mMediaPlayer.start();
			mMediaPlayer.setLooping(isLooping);
		}
		return mMediaPlayer;
	}

	public static MediaPlayer playBgMusic(String path, MediaPlayer mMediaPlayer) {
		try {
			if (mMediaPlayer != null) { // 一定要清空播放器资源
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
			mMediaPlayer = new MediaPlayer();
			/* 重置MediaPlayer */
			mMediaPlayer.reset();
			/* 设置要播放的文件的路径 */
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.setLooping(true);
			/* 准备播放 */
			mMediaPlayer.prepare();
			/* 开始播放 */
			mMediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mMediaPlayer;
	}

	public static MediaPlayer playSoundMusic(String path, MediaPlayer mMediaPlayerSound) {
		try {
			if (mMediaPlayerSound != null) { // 一定要清空播放器资源
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
                public void onCompletion(MediaPlayer mp)   
                {   
                    if(mp != null) {
                    	mp.release();
                    	mp = null;
                    }
                }   
            });
			mMediaPlayerSound.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					if(mp != null) {
						mp.release();
						mp = null;
                    }
					return false;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mMediaPlayerSound;
	}
	public static MediaPlayer playMusic(String path, MediaPlayer mMediaPlayerSound) {
		try {
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
				public void onCompletion(MediaPlayer mp)   
				{   
					if(mp != null) {
						mp.release();
						mp = null;
					}
				}   
			});
			mMediaPlayerSound.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					if(mp != null) {
						mp.release();
						mp = null;
					}
					return false;
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mMediaPlayerSound;
	}
	
	public static MediaPlayer clearMediaPlayer(MediaPlayer mMediaPlayer) {
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
		return mMediaPlayer;
	}
	
}
