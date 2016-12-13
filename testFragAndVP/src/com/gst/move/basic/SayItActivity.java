package com.gst.move.basic;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.base.Music;
import com.ebodoo.raz.biz.SayItMedia;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;

public class SayItActivity extends BaseActivity implements OnClickListener,
		OnFocusChangeListener {

	private MyVideoView video;
	private String path;
	private VideoBiz biz;
	private ImageView ivBack;
	private boolean isCycle = false;
	private int startTime = 0;
	private int endTime = 0;
	private int clickNum = 0;
	private boolean isClicked = true;
	private int cycleIndex = 0;
	private Context mContext;
	private int preservation = 0; // 按home键保存播放帧数
	private int offset_time = 0; // 用于步步高平板延迟时间修正
	private SoundPool soundPool;
	private int soundId;
	private ArrayList<Music> listMusic;
	private boolean isFirst = true;
	private int timeLevel[][];
	private int level = 1;
	private int mediaSize = 4;
	private boolean isPause = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.say_it);

		initializationData();
		offset_time = Tools.delayTime();
		offset_time = 800;
		setView();
	}

	private void initializationData() {
		mContext = SayItActivity.this;
		level = getIntent().getExtras().getInt("level");
		listMusic = new ArrayList<Music>();
		mediaSize = SayItMedia.mediaSize(level);
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		initMusicSoundPool();
		biz = new VideoBiz();
		path = SayItMedia.commonMediaPath(level, 0, true);
		cycleIndex = 0;
		timeLevel = SayItMedia.selTimeLevel(level);
		startTime = timeLevel[cycleIndex][0];
		endTime = timeLevel[cycleIndex][1];
	}

	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, (width/1280.0f) ,(height/720.0f));
		videoCycle(0);
		biz.playVideo(video, path);
		ivBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == ivBack) {
			returnHome();
		}
	}

	// 循环播放
	private void videoCycle(final int num) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				isCycle = true;
				setCirclePlay(num);
			}
		}).start();
	}

	private void setCirclePlay(final int num) {
		int currentPos = video.getCurrentPosition();
		int tem_num = num;
		do {
			try {
				if (cycleIndex <= 260) {
					Thread.sleep(20);
				}
				if (tem_num != clickNum)
					break;
				if (!isCycle || video == null)
					break;
				currentPos = video.getCurrentPosition(); // 空指针null
				if (timeLevel[cycleIndex][4] == 1 && isClicked) {
					isClicked = false;
					handler.sendMessage(handler.obtainMessage(0, num));
				}
				if (currentPos >= endTime) {
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (currentPos < endTime);
		try {
			if (!video.isPlaying()) {// 如果不在播放状态，则停止更新
				return;
			}
			if (tem_num == clickNum) {
				if (timeLevel[cycleIndex][4] == 0) {
					cycleIndex = timeLevel[cycleIndex][3];
				}
				startTime = timeLevel[cycleIndex][0] - offset_time;
				endTime = timeLevel[cycleIndex][1] - offset_time;
				handler.sendMessage(handler.obtainMessage(2, tem_num));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				isClicked = false;
				int num = (Integer) msg.obj;
				if (num != clickNum) {
					isClicked = true;
					break;
				}
				if (clickNum < (mediaSize+1)) {
					sayit3s();
				} else {
					startActivity(new Intent(mContext, SuccessSayitActivity.class).putExtra("level", level));
					finish();
				}
				break;
			case 1:
				isClicked = true;
				setClick();
				break;
			case 2:
				int numValue = (Integer) msg.obj;
				if (video != null && clickNum == numValue) {
					video.seekTo(startTime);
					videoCycle(numValue);
				}
				break;
			default:
				break;
			}
		}

	};

	/*-----------------sayit 3秒控制----------------------*/
	private void sayit3s() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					handler.sendMessage(handler.obtainMessage(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void setClick() {
		isCycle = false;
		clickNum++;
		sayItStatus();
		int seekTo = timeLevel[cycleIndex][3];
		clickExecute(seekTo);
	}

	// 点击执行的功能
	private void clickExecute(int seekTo) {
		startTime = timeLevel[seekTo][0] - offset_time;
		endTime = timeLevel[seekTo][1] - offset_time;
		if (video == null)
			return;
		video.seekTo(timeLevel[seekTo][0] - offset_time);
		isClicked = true;
		cycleIndex = seekTo;
		videoCycle(clickNum);
		isCycle = true;
	}

	// say it 跟我读和欢呼
	private void sayItStatus() {
		System.out.println("sayItStatus isPause :" + isPause);
		if(isPause)
			return;
		if (clickNum == 1) {
			MediaCommon.playSayit(mContext);
			readWithMe(1500, 0);
		} else if (clickNum == 2) {
			readWithMe(4500, 1);
		} else if (clickNum == 3) {
			readWithMe(4500, 2);
		} else if (clickNum == 4) {
			readWithMe(4500, 3);
		} else if (clickNum == 5) {
			readWithMe(4500, 4);
		} else if (clickNum == 6) {
			readWithMe(4500, 5);
		} else if (clickNum == 7) {
			readWithMe(4500, 6);
		} else if (clickNum == 8) {
			readWithMe(4500, 7);
		}
		if (clickNum >= 2 && !isPause) { // 欢呼
			MediaCommon.playGuzhang(SayItActivity.this);
		}
	}

	private void readWithMe(final int time, final int index) {
		if(clickNum > mediaSize)
			return;
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(time);
					if(!isPause) {
						playSoundPool(listMusic.size() - 1);
					}
					Thread.sleep(2000);
					if (index >= 0 && index < listMusic.size() && !isPause) {
						playSoundPool(index);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setKeepScreenOn(true);
		if (null != video) {
			video.start();
			// 跳到指定的帧数
			video.seekTo(preservation);
		}
		isPause = false;
		if (isFirst) {
			isFirst = false;
			if (null != video) {
				video.start();
				if(level > 0 && level <= SayItMedia.cycleIndexValue.length) {
					cycleIndex = SayItMedia.cycleIndexValue[level-1];
					video.seekTo(timeLevel[cycleIndex][0]);
					setClick();
				}
			}
		} 
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		try {
			// 按Home键或者电源键或者页面崩溃时保存视频播放的帧数
			if (video != null) {
				int currentPos = video.getCurrentPosition();
				outState.putInt("preservation", currentPos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		try {
			// 取的保存的帧数
			preservation = savedInstanceState.getInt("preservation");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		isPause = true;
		isClicked = true;
		isFirst = true;
		clickNum = 0;
		getWindow().getDecorView().setKeepScreenOn(false);
		try {
			if (null != video) {
				video.pause();
				// 暂停时保存播放帧数
				if (video != null) {
					int currentPos = video.getCurrentPosition();
					preservation = currentPos;
				}
			}
			if(listMusic != null && listMusic.size() > 0) {
				soundPool.pause(soundId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		MediaCommon.pauseMediaplay();
	}

	@Override
	protected void onDestroy() {
		isCycle = false;
		clearPoolMusic();
		super.onDestroy();
		// 帧数清零
		preservation = 0;
		// biz.clearMediaPlayer(mMediaPlayer, mMediaPlayerSound, video);
	}

	private void clearPoolMusic(){
		try{
			if (soundPool != null) {
				soundPool.unload(soundId);  
				soundPool.release();
				soundPool = null;
			}
			if(listMusic != null && listMusic.size() > 0) {
				listMusic.clear();
			}
		}catch (Exception e) {
		}
	}
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			returnHome();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	private void returnHome() {
		clearPoolMusic();
		try {
			if (null != video) {
				video.stopPlayback();
				video = null;
			}
		} catch (Exception e) {
		}
		this.finish();
	}

	@Override
	public void onFocusChange(View v, boolean arg1) {
	}

	/*-----tv 初始化soundpool资源----*/
	private void initMusicSoundPool() {
		for (int i = 0; i < mediaSize; i++) {
			Music music = new Music();
			music.id = i;
			music.soundId = soundPool.load(
					SayItMedia.commonMediaPath(level, i, false), 1);
			listMusic.add(music);
		}
		// clickhere
		Music musicSayit = new Music();
		musicSayit.id = mediaSize;
		musicSayit.soundId = soundPool.load(mContext, R.raw.en_sayit, 1);
		listMusic.add(musicSayit);

		Music musicPeiyin = new Music();
		musicPeiyin.id = mediaSize + 1;
		musicPeiyin.soundId = soundPool.load(mContext,
				R.raw.ppg_read_after_me_ch, 1);
		listMusic.add(musicPeiyin);
	}

	/*-----tv 播放soundpool声音----*/
	private void playSoundPool(int id) {
		int size = listMusic.size();
		if (id >= 0 && id < size) {
			soundId = listMusic.get(id).soundId;
			soundPool.play(soundId, 100.0f, 100.0f, 1, 0, 1.0f);
		}
	}

}
