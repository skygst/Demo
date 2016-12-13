package com.gst.move.basic;

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

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.ConstantRaz4;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;

public class Level12Activity extends BaseActivity implements OnClickListener {
	
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
	private Button btnNext;
	private ImageView ivShou;
	private MediaPlayer mMediaPlayer = null;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private Context mContext;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private int xoffset = 0, yoffset = 0;
	private float suoxiao = 1.0f;
	private boolean isEg = false;
	private boolean isSecondClick = false;
	private int preservation=0;   //按home键保存播放帧数
	
	private int offset_time = 0;		//用于步步高平板延迟时间修正
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_12);

		initializationData();
		
		offset_time = Tools.delayTime();
		setView();
	}

	private void initializationData() {
		mContext = Level12Activity.this;
		scaleQPW = (width / 1920.0f);
		scaleQPH = (height / 1080.0f);
		biz = new VideoBiz();
		isEg = BaseCommon.getIsEg(mContext);
		path = Constant.path_raz04 + "level12.mp4";
		cycleIndex = 0;
		startTime = ConstantRaz4.timeLevel12[cycleIndex][0];
		endTime = ConstantRaz4.timeLevel12[cycleIndex][1];
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz04 + "bgmusic_level02.mp3");
		playDelayMusic(5000, MediaCommon.getLeve12Mp3(isEg, 1));
	}

	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		btnNext = (Button) findViewById(R.id.btn_next);
		ivShou = (ImageView) findViewById(R.id.iv_shou);
		ivShou.setVisibility(View.INVISIBLE);
		btnNext.setVisibility(View.INVISIBLE);
		videoCycle(0);
		biz.playVideo(video, path);
		setCommonViewPosition(ivBack, 6);
		setCommonViewPosition(btnNext, 4);
		
		ivBack.setOnClickListener(this);
		btnNext.setOnClickListener(this);

		ivShou.setOnClickListener(this);
//		CommonUtil.downLoadRes(mContext, 3, 3, Constant.download_level_13, commonPath, "raz04");
	}


	@Override
	public void onClick(View v) {
		stopBtnAndShou();
		if(v == ivShou) {
			btnNext.setVisibility(View.GONE);
			VideoBiz.stopAfterAnimation(btnNext);
			if(clickNum >= 34) {
				gotoSuccessActivity();
			} else {
				setClick();
			}
		} else if(v == ivBack) {
			 returnHome();
		} else if(v == btnNext) {
			if(clickNum == 5) {
				setClick();
			} else {
				gotoSuccessActivity();
			}
		}
	}
	
	// 跳转到ShowTime
	private void gotoSuccessActivity() {
//		startActivity(new Intent(mContext, SuccessActivity.class).putExtra(
//				"level", 15)); // 4
		clearMediaPlayer();
		finish();
	}
	
	private void setClick() {
		isCycle = false;
		clickNum++;
		System.out.println("clickNum :" + clickNum);
//		sayItStatus();
		readWord();
		int seekTo = ConstantRaz4.timeLevel12[cycleIndex][3];		

		if(seekTo > 127){	//sayit
			gotoSuccessActivity();
			return;
		}
		clickExecute(seekTo);
	}

	// 点击执行的功能
	private void clickExecute(int seekTo) {
		try{
			startTime = ConstantRaz4.timeLevel12[seekTo][0] - offset_time;
			endTime = ConstantRaz4.timeLevel12[seekTo][1] - offset_time;
			if (video == null)
				return;
			video.seekTo(ConstantRaz4.timeLevel12[seekTo][0] - offset_time);
			isClicked = true;
			cycleIndex = seekTo;
			videoCycle(clickNum);
			isCycle = true;
		}catch(Exception e){}
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
					if (ConstantRaz4.timeLevel12[cycleIndex][4] == 1 && isClicked) {
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
					if (ConstantRaz4.timeLevel12[cycleIndex][4] == 0) {
						cycleIndex = ConstantRaz4.timeLevel12[cycleIndex][3];
					}
					startTime = ConstantRaz4.timeLevel12[cycleIndex][0] - offset_time;
					endTime = ConstantRaz4.timeLevel12[cycleIndex][1] - offset_time;
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
					showBtnAndShou();
					if (clickNum <= 25) {
						
					} else if (clickNum < 34) {
						sayit3s();
					} else {
						btnNext.setVisibility(View.VISIBLE);
						VideoBiz.startAfterAnimation(btnNext);
						ivShou.setVisibility(View.VISIBLE);
						setViewPosition(ivShou, 3);
						CommonAnimation.startShouAnimation(ivShou);
					}
					if (clickNum == 0) {
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
						if(clickNum<34){
							btnNext.setVisibility(View.GONE);
							VideoBiz.stopAfterAnimation(btnNext);
						}else{
							btnNext.setVisibility(View.VISIBLE);
							VideoBiz.startAfterAnimation(btnNext);
						}
						setClick();
					}
					break;
				case 4:
				default:
					break;
				}
			}

		};
	
		/* 显示手动画 */
		private void showBtnAndShou() {
			int i = -1;
			if (!isSecondClick) {
				if (clickNum == 25) {
					isSecondClick = true;
				}
			}
			i = FixedPosition.getLevel07PositionIndex(
					FixedPosition.level12_index_to_position, clickNum);
			System.out.println("i :"+i);
			System.out.println("showBtnAndShou clickNum :"+clickNum);
			if (i != -1) {
				ivShou.setVisibility(View.VISIBLE);
				if (!isSecondClick) {
					if (clickNum == 6 || clickNum == 25) {
						btnNext.setVisibility(View.VISIBLE);
						VideoBiz.startAfterAnimation(btnNext);
					} else {
						btnNext.setVisibility(View.GONE);
						VideoBiz.stopAfterAnimation(btnNext);
					}
				} else {
					if(clickNum == 25){
						btnNext.setVisibility(View.VISIBLE);
						VideoBiz.startAfterAnimation(btnNext);
					}
					
				}
				if (clickNum <= 26 || clickNum >= 34) {
					if(clickNum >= 34){
						btnNext.setVisibility(View.VISIBLE);
						VideoBiz.startAfterAnimation(btnNext);
					}else{
						btnNext.setVisibility(View.GONE);
					}
					setViewPosition(ivShou, i);
					CommonAnimation.startShouAnimation(ivShou);
				}
			}
		}
		
	/*-----------------sayit 3秒控制----------------------*/
	private void sayit3s() {
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
	
	// 读单词
	private void readWord() {
		String path = "";
		if(clickNum == 1 || clickNum == 2 || clickNum == 3) {
			path = MediaCommon.getLeve12Mp3(isEg, 11);
		} else if(clickNum == 4 || clickNum == 5 || clickNum == 6) {
			path = MediaCommon.getLeve12Mp3(isEg, 4);
		} else if (clickNum == 9) { //hop
			path = MediaCommon.getLeve12Mp3(isEg, 5);
		} else if (clickNum == 11) { //top
			path = MediaCommon.getLeve12Mp3(isEg, 6);
		} else if (clickNum == 13) { //cop
			path = MediaCommon.getLeve12Mp3(isEg, 7);
		} else if (clickNum == 15) { //pop
			path = MediaCommon.getLeve12Mp3(isEg, 8);
		} else if(clickNum == 19) { //hot
			path = MediaCommon.getLeve12Mp3(isEg, 12);
		} else if(clickNum == 21) { //tot
			path = MediaCommon.getLeve12Mp3(isEg, 13);
		} else if(clickNum == 23) { //dot
			path = MediaCommon.getLeve12Mp3(isEg, 14);
		} else if(clickNum == 25) { //pot
			path = MediaCommon.getLeve12Mp3(isEg, 15);
		}
		if(path != null && !path.equals("")) {
			playOnlineMusic(path);
		}
	}
	
	//say it 跟我读和欢呼
	private void sayItStatus() {
		if (clickNum == 26) {
//			MediaCommon.playSayit(mContext);
			readWithMe(4500, 0, MediaCommon.getLeve12Mp3(isEg, 5)); //hop
		} else if (clickNum == 27) {
			readWithMe(4500, 1, MediaCommon.getLeve12Mp3(isEg, 6)); //top
		} else if (clickNum == 28) {
			readWithMe(4500, 2, MediaCommon.getLeve12Mp3(isEg, 7)); //cop
		} else if (clickNum == 29) {
			readWithMe(4500, 3, MediaCommon.getLeve12Mp3(isEg, 8)); //pop
		} else if (clickNum == 30) {
			readWithMe(4500, 3, MediaCommon.getLeve12Mp3(isEg, 12)); //hot
		} else if (clickNum == 31) {
			readWithMe(4500, 3, MediaCommon.getLeve12Mp3(isEg, 13)); //tot
		} else if (clickNum == 32) {
			readWithMe(4500, 3, MediaCommon.getLeve12Mp3(isEg, 14)); //dot
		} else if (clickNum == 33) {
			readWithMe(4500, 3, MediaCommon.getLeve12Mp3(isEg, 15)); //pot
		}
		if (clickNum >= 27) { // 欢呼
			MediaCommon.playGuzhang(mContext);
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
	
	private void playDelayMusic(final long time, final String path) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(time);
					playOnlineMusic(path);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void playOnlineMusic(String path) {
		try {
			mMediaPlayerSound = playMusic(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void playSay(final int resId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					if(mMediaPlayerSound != null) {
						mMediaPlayerSound.stop();
						mMediaPlayerSound.reset();
						mMediaPlayerSound = null;
					}
					mMediaPlayerSound = MediaPlayer.create(mContext, resId);
					if (mMediaPlayerSound != null) {
						mMediaPlayerSound.start();
						mMediaPlayerSound.setLooping(false);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void returnHome() {
		isCycle = false;
		clearMediaPlayer();
//		startActivity(new Intent(mContext, SelectLevelActivity.class));
		this.finish();
	}
	
	private void playMusic() {
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
	
	/* 消失手动画 */
	private void stopBtnAndShou() {
		ivShou.setVisibility(View.GONE);
		CommonAnimation.stopShouAnimation(ivShou);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPosition.level12_position, scaleQPW, scaleQPH, xoffset, yoffset, suoxiao);
	}
	
	private void setCommonViewPosition(View view, int i) {
		biz.setViewPosition(view, i, FixedPosition.level_common_position, scaleQPW, scaleQPH, xoffset, yoffset, suoxiao);
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
		clearMediaPlayer();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			returnHome();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	// 清除数据
	public void clearMediaPlayer() {
		try {
			if (mMediaPlayer != null) {
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
			if (null != video) {
				video.stopPlayback();
				video = null;
			}
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MediaPlayer playMusic(String path) {
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
                    /*if(mMediaPlayerSound != null) {
                    	mMediaPlayerSound.release();  
                    }*/
                }   
            });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mMediaPlayerSound;
	}
	
}
