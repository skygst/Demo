package com.gst.move.basic;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class Level4Activity extends BaseActivity implements OnClickListener {

	private MyVideoView video;
	private String path;
	private VideoBiz biz;
	private boolean isCycle = false;
	private int startTime = 0;
	private int endTime = 0;
	private int clickNum = 0;
	private boolean isClicked = true;
	private int cycleIndex = 0;
	private ImageView ivBack;
	private Button btnClouds1, btnClouds2, btnClouds3, btnNext;
	private ImageView ivShou;
	private MediaPlayer mMediaPlayer = null;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private Context mContext;
	private boolean isEg = false;
	private boolean isSecondClick = false;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private int xoffset = 0, yoffset = 0;
	private float suoxiao = 1.0f;
	private int preservation=0;   //按home键保存播放帧数

	private int offset_time = 0;		//用于步步高平板延迟时间修正
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_04);

		initializationData();
		
		offset_time = Tools.delayTime();
		setView();
	}

	private void initializationData() {
		mContext = Level4Activity.this;
		scaleQPW = (width / 1920.0f);
		scaleQPH = (height / 1080.0f);
		biz = new VideoBiz();
		isEg = BaseCommon.getIsEg(mContext);
		path = Constant.path_raz02 + "level04.mp4";
		cycleIndex = 0;
		startTime = Constant.timeLevel04[cycleIndex][0];
		endTime = Constant.timeLevel04[cycleIndex][1];
//		String bgMusicPath = MediaCommon.getLeve04Mp3(isEg, 0);
//		playBgMusic(bgMusicPath);
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz02 + "bgmusic_level04.mp3");
		firstStartSayM();
		delayPlayVoiceover(2700, 1);
	}

	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		btnNext = (Button) findViewById(R.id.btn_next);
		btnClouds1 = (Button) findViewById(R.id.btn_clouds_1);
		btnClouds2 = (Button) findViewById(R.id.btn_clouds_2);
		btnClouds3 = (Button) findViewById(R.id.btn_clouds_3);
		ivShou = (ImageView) findViewById(R.id.iv_shou);
		showClouds(View.GONE);
		ivShou.setVisibility(View.INVISIBLE);
		btnNext.setVisibility(View.INVISIBLE);
		videoCycle(0);
		biz.playVideo(video, path);
		
		setCommonViewPosition(ivBack, 6);
		setViewPosition(btnClouds1, 0);
		setViewPosition(btnClouds2, 1);
		setViewPosition(btnClouds3, 2);
		setCommonViewPosition(btnNext, 4);
		btnClouds1.setBackground(BaseCommon.drawableChange(Constant.path_raz02_images, "m_clouds"));
		btnClouds2.setBackground(BaseCommon.drawableChange(Constant.path_raz02_images, "m_clouds"));
		btnClouds3.setBackground(BaseCommon.drawableChange(Constant.path_raz02_images, "m_clouds"));
		ivBack.setOnClickListener(this);
		btnNext.setOnClickListener(this);

		ivShou.setOnClickListener(this);
//		CommonUtil.downLoadRes(mContext, 1, 2, Constant.download_level_05, commonPath, "raz02");
	}
	
	private void showClouds(int clouds) {
		btnClouds1.setVisibility(clouds);
		btnClouds2.setVisibility(clouds);
		btnClouds3.setVisibility(clouds);
	}

	@Override
	public void onClick(View v) {
		stopBtnAndShou();
		if (v == ivBack) {
			 returnHome();
		} else if (v == ivShou) {
			disShowBtnNext();
			if (!isSecondClick) {
				setClick();
			} else {
				if (clickNum < 33) {
					setClick();
				} else {
					gotoSuccessActivity();
				}
			}
		} else if (v == btnNext) {
			disShowBtnNext();
			if (clickNum > 4 && clickNum < 29) {
				clickNum = 28;
				setClick();
			} else if (clickNum >= 29) {
				gotoSuccessActivity();
			}
		}
		if (clickNum >= 28) {
			/*btnNext.setVisibility(View.VISIBLE);
			VideoBiz.startAfterAnimation(btnNext);*/
		}
	}

	//隐藏btnNext
	private void disShowBtnNext(){
		btnNext.setVisibility(View.GONE);
		VideoBiz.stopAfterAnimation(btnNext);
	}
	// 跳转到ShowTime
	private void gotoSuccessActivity() {
//		startActivity(new Intent(mContext, SuccessActivity.class).putExtra(
//				"level", 5)); // 4
		biz.clearMediaPlayer(mMediaPlayer, mMediaPlayerSound, video);
		finish();
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
				if (Constant.timeLevel04[cycleIndex][4] == 1 && isClicked) {
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
				if (Constant.timeLevel04[cycleIndex][4] == 0) {
					cycleIndex = Constant.timeLevel04[cycleIndex][3];
				}
				startTime = Constant.timeLevel04[cycleIndex][0] - offset_time;
				endTime = Constant.timeLevel04[cycleIndex][1] - offset_time;
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
				if (clickNum <= 28) {
					showBtnAndShou();
				} else if (clickNum < 33) {
//					sayit3s();
				} else {
					btnNext.setVisibility(View.VISIBLE);
					VideoBiz.startAfterAnimation(btnNext);
					ivShou.setVisibility(View.VISIBLE);
					setViewPosition(ivShou, 15);
					CommonAnimation.startShouAnimation(ivShou);
				}
				if (clickNum == 0 || clickNum == 4 || clickNum == 5 || clickNum == 10 || clickNum == 11
						|| clickNum == 16 || clickNum == 17 || clickNum == 22 || clickNum == 23) {
					if(isEg) {
						playSay(R.raw.eg_clickhere);
					} else {
						playSay(R.raw.ch_clickhere);
					}
				}
				break;
			case 2:
				int numValue = (Integer) msg.obj;
				if (video != null && clickNum == numValue) {
					video.seekTo(startTime);
					videoCycle(numValue);
				}
				break;
			case 3:
				if (clickNum < 50) {
					isClicked = true;
					setClick();
				}
				break;
			case 4:
				String path1 = (String) msg.obj;
				playOnlineMusic(path1);
				break;
			case 5:
				String path2 = (String) msg.obj;
				playOnlineMusic(path2);
				break;
			case 6:
				showClouds(View.VISIBLE);
				break;
			default:
				break;
			}
		}

	};

	/*-----------------sayit 3秒控制----------------------*/
	private void sayit3s1() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					handler.sendMessage(handler.obtainMessage(3));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void setClick() {
		isCycle = false;
		clickNum++;
		if (clickNum < 4) {
			cycleIndex = 2;
			showClouds();
		}
		System.out.println("clickNum :" + clickNum);
//		sayItStatus();
		readWord();
		int seekTo = Constant.timeLevel04[cycleIndex][3];
		
		if(seekTo > 105){	//sayit
			gotoSuccessActivity();
			return;
		}
		clickExecute(seekTo);
	}

	// 点击执行的功能
	private void clickExecute(int seekTo) {
		startTime = Constant.timeLevel04[seekTo][0] - offset_time;
		endTime = Constant.timeLevel04[seekTo][1] - offset_time;
		if (video == null)
			return;
		video.seekTo(Constant.timeLevel04[seekTo][0] - offset_time);
		isClicked = true;
		cycleIndex = seekTo;
		videoCycle(clickNum);
		isCycle = true;
	}
	
	// 读单词
	private void readWord() {
		if(clickNum >=5 && clickNum <= 10) {
			playWord(0);
		} else if(clickNum >=11 && clickNum <= 16) {
			if(clickNum == 11) { // 画外音
				delayPlayVoiceover(1500, 2);
			}
			playWord(1);
		} else if(clickNum >=17 && clickNum <= 22) {
			if (clickNum == 17) { // 画外音
				delayPlayVoiceover(1500, 3);
			}
			playWord(2);
		} else if(clickNum >=23 && clickNum <= 28) {
			playWord(3);
		}
	}
	
	//say it 跟我读和欢呼
	private void sayItStatus() {
		if (clickNum == 29) {
//			MediaCommon.playSayit(mContext);
//			readWithMe(4500, 0, MediaCommon.getLeve04Mp3(isEg, 5));
		} else if (clickNum == 30) {
//			readWithMe(4500, 1, MediaCommon.getLeve04Mp3(isEg, 8));
		} else if (clickNum == 31) {
//			readWithMe(4500, 2, MediaCommon.getLeve04Mp3(isEg, 7));
		} else if (clickNum == 32) {
//			readWithMe(4500, 3, MediaCommon.getLeve04Mp3(isEg, 6));
		}
		if (clickNum >= 30) { // 欢呼
			MediaCommon.playGuzhang(Level4Activity.this);
		}
	}

	private void showClouds() {
		if (clickNum == 1) {
			btnClouds1.setVisibility(View.INVISIBLE);
		} else if (clickNum == 2) {
			btnClouds2.setVisibility(View.INVISIBLE);
		} else if (clickNum == 3) {
			btnClouds3.setVisibility(View.INVISIBLE);
		}
		playSound(MediaCommon.getLeve04Mp3(isEg, 9), MediaCommon.getLeve04Mp3(isEg, 4));
	}
	
	private void playWord(int index) {
		String sound = MediaCommon.getLeve04Mp3(isEg, 4);
		if (index == 0) {
			playSound(sound, MediaCommon.getLeve04Mp3(isEg, 8));
		} else if (index == 1) {
			playSound(sound, MediaCommon.getLeve04Mp3(isEg, 5));
		} else if (index == 2) {
			playSound(sound, MediaCommon.getLeve04Mp3(isEg, 6));
		} else if(index == 3) {
			playSound(sound, MediaCommon.getLeve04Mp3(isEg, 7));
		}
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPosition.level04_position, scaleQPW, scaleQPH, xoffset, yoffset, suoxiao);
	}
	
	private void setCommonViewPosition(View view, int i) {
		biz.setViewPosition(view, i, FixedPosition.level_common_position, scaleQPW, scaleQPH, xoffset, yoffset, suoxiao);
	}

	/* 显示手动画 */
	private void showBtnAndShou() {
		int i = -1;
		if (!isSecondClick) {
			if (clickNum == 28) {
				isSecondClick = true;
			}
		}
		i = FixedPosition.getLevel07PositionIndex(
				FixedPosition.level04_index_to_position, clickNum);
		if (i != -1) {
			ivShou.setVisibility(View.VISIBLE);
			if (!isSecondClick) {
				if (clickNum == 3 || clickNum == 28) {
					btnNext.setVisibility(View.VISIBLE);
					VideoBiz.startAfterAnimation(btnNext);
				} else {
					btnNext.setVisibility(View.GONE);
					VideoBiz.stopAfterAnimation(btnNext);
				}
			} else {
				btnNext.setVisibility(View.VISIBLE);
				VideoBiz.startAfterAnimation(btnNext);
			}
			if (clickNum <= 29 || clickNum >= 33) {
				setViewPosition(ivShou, i);
				CommonAnimation.startShouAnimation(ivShou);
			}
		}
	}

	private void readWithMe(final int time, final int index, final String path) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(time);
					mMediaPlayerSound = new MediaCommon().getPeiyinMp3(
							mContext, 0);
					biz.playSoundMusic(mMediaPlayerSound);
					Thread.sleep(2000);
					playOnlineMusic(path);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	/* 消失手动画 */
	private void stopBtnAndShou() {
		ivShou.setVisibility(View.GONE);
		CommonAnimation.stopShouAnimation(ivShou);
	}
	
	private void delayPlayVoiceover(final int time, final int index) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(time);
					setResId(index);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void setResId(int index) {
		String pathVoiceover = MediaCommon.getLeve04Mp3(isEg, index);
		playOnlineMusic(pathVoiceover);
	}

	private void playSound(final String path1, final String path2) {
		handler.sendMessage(handler.obtainMessage(4, path1));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(600);
					handler.sendMessage(handler.obtainMessage(5, path2));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 第一次说M
	private void firstStartSayM() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1200);
					playSound(MediaCommon.getLeve04Mp3(isEg, 9),
							MediaCommon.getLeve04Mp3(isEg, 4));
					Thread.sleep(3000);
					handler.sendMessage(handler.obtainMessage(6));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	private void setMediaStatus() {
		try{
			if(mMediaPlayerSound != null) {
				mMediaPlayerSound.stop();
				mMediaPlayerSound.reset();
				mMediaPlayerSound = null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void playSay(final int resId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				long time = 0;
				if(clickNum == 11) {
					time = 1300;
				} else if(clickNum == 17) {
					time = 2000;
				}
				try {
					Thread.sleep(time);
					setMediaStatus();
					mMediaPlayerSound = MediaPlayer.create(Level4Activity.this, resId);
					biz.playSoundMusic(mMediaPlayerSound);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}

	private void playBgMusic(String path) {
		try {
			mMediaPlayer = new BaseCommon().playMusic(mMediaPlayer, path, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void playOnlineMusic(String path) {
		try {
			mMediaPlayerSound = new BaseCommon().playMusic(mMediaPlayerSound, path, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setKeepScreenOn(true);
		if (null != video) {
			video.start();
			//跳到指定的帧数
			video.seekTo(preservation);
		}
		if (null != mMediaPlayer && !mMediaPlayer.isPlaying()) {
			mMediaPlayer.start();
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		try {
			//按Home键或者电源键或者页面崩溃时保存视频播放的帧数
			if(video!=null){
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
			//取的保存的帧数
			preservation= savedInstanceState.getInt("preservation");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		isClicked = true;
		getWindow().getDecorView().setKeepScreenOn(false);
		if (null != video) {
			video.pause();
			try {
				//暂停时保存播放帧数
				if(video!=null){
					int currentPos = video.getCurrentPosition();
					preservation=currentPos;			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
				mMediaPlayer.pause();
			}
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.pause();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		MediaCommon.pauseMediaplay();
	}

	@Override
	protected void onDestroy() {
		isCycle = false;
		super.onDestroy();
		//帧数清零
        preservation=0;
		biz.clearMediaPlayer(mMediaPlayer, mMediaPlayerSound, video);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			returnHome();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void returnHome() {
		try{
			isCycle = false;
			if (mMediaPlayer != null) {
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
		}catch(Exception e){}
		try{
			if (null != video) {
				video.stopPlayback();
				video = null;
			}
		}catch(Exception e){}
//		 startActivity(new Intent(mContext, SelectLevelActivity.class));
		this.finish();
	}

}
