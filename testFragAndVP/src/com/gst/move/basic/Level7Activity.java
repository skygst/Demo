package com.gst.move.basic;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.LayoutParameters;
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
import android.widget.RelativeLayout;

public class Level7Activity extends BaseActivity implements OnClickListener {

	private MyVideoView video;
	private ImageView ivBack, ivShou;
	private ImageView iv1, iv2, iv3, iv4, iv11, iv21, iv31, iv41, paopao_selected;
	private Button btnNext;
	private RelativeLayout rlQipao;
	private String path;
	private VideoBiz biz;
	private MediaPlayer mMediaPlayer = null;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private String thisChar = "/o/"; // 判断是否说的是这个字母
	private int clickNum = 0;
	private boolean isCycle = false;
	private boolean isClicked = true;
	private int startTime = 0;
	private int endTime = 0;
	private boolean isEg = false;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private int xoffset = 0, yoffset = 0;
	private float suoxiao = 1.0f;
	private float scaleQP = 1.0f;
	private Context mContext;
	private int secondClick = 0;
	private boolean isSecondClick = false;
	private int currentIndex = 0;
	private int preservation=0;   //按home键保存播放帧数
	private boolean mInitialized = false;
	private final String TAG = "Level4Activity";
	Thread recThread;
	private String mSelectedPhonics;
	private boolean isCanSayClickHere = true;
	private int offset_time = 0;		//用于步步高平板延迟时间修正
	private int indexNum = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_07);
		initializationData();
		
		offset_time = Tools.delayTime();
		setView();
	}

	private void initializationData() {
		mContext = Level7Activity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1920.0f);
		scaleQPH = (height / 1080.0f);
		isEg = BaseCommon.getIsEg(mContext);
		scaleQP = (width / 800.0f);
		path = Constant.path_raz03 + "level07.mp4";
		currentIndex = 0;
		startTime = Constant.timeLevel07[currentIndex][0];
		endTime = Constant.timeLevel07[currentIndex][1];
//		String bgMusicPath = MediaCommon.getLevel07Mp3(isEg, 0);
//		playBgMusic(bgMusicPath);
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz03 + "bgmusic_level07.mp3");
		playSound(1200, MediaCommon.getLevel07Mp3(isEg, 1)); // 第一次说O
	}

	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		ivShou = (ImageView) findViewById(R.id.iv_shou);
		btnNext = (Button) findViewById(R.id.btn_next);
		rlQipao = (RelativeLayout) findViewById(R.id.rl_qipao);
		iv1 = (ImageView) findViewById(R.id.iv1);
		iv2 = (ImageView) findViewById(R.id.iv2);
		iv3 = (ImageView) findViewById(R.id.iv3);
		iv4 = (ImageView) findViewById(R.id.iv4);
		iv11 = (ImageView) findViewById(R.id.iv11);
		iv21 = (ImageView) findViewById(R.id.iv21);
		iv31 = (ImageView) findViewById(R.id.iv31);
		iv41 = (ImageView) findViewById(R.id.iv41);
		paopao_selected = (ImageView) findViewById(R.id.paopao_selected);
		paopao_selected.setVisibility(View.INVISIBLE);
//		ivParams();
		setPaopao();
		setCommonViewPosition(ivBack, 6);
		setCommonViewPosition(btnNext, 4);
		ivShou.setVisibility(View.INVISIBLE);
		btnNext.setVisibility(View.INVISIBLE);
		videoCycle(0);
		biz.playVideo(video, path);
		setQiPaoVisible(false);
		ivBack.setOnClickListener(this);
		ivShou.setOnClickListener(this);
		iv11.setOnClickListener(this);
		iv21.setOnClickListener(this);
		iv31.setOnClickListener(this);
		iv41.setOnClickListener(this);
		btnNext.setOnClickListener(this);
//		CommonUtil.downLoadRes(mContext, 2, 2, Constant.download_level_08, commonPath, "raz03");
	}

	private void setClick() {
		isCycle = false;
		clickNum++;
		if(isSecondClick) { // 二次循环使用
			int index = new VideoBiz().secondaryRecycled(clickNum);
			if(index != 0) {
				currentIndex = index;
			}
		}
		/*if(clickNum == 25) { //跟我读
			disShowBtnNext();
			MediaCommon.playSayit(mContext);
			readWithMe(4000, 0);
			rlQipao.setVisibility(View.GONE);
		} else if (clickNum == 26) {
			readWithMe(3500, 1);
		} else if (clickNum == 27) {
			readWithMe(3500, 2);
		} else if (clickNum == 28) {
			readWithMe(2000, 3);
		}
		if(clickNum >= 26) { // 欢呼
			MediaCommon.playGuzhang(Level7Activity.this);
		}*/
		int seekTo = Constant.timeLevel07[currentIndex][3];
		
		if(seekTo > 118){	//sayit
			gotoSuccessActivity();
			return;
		}
		clickExecute(seekTo);
	}
	//隐藏btnNext
	private void disShowBtnNext(){
		btnNext.setVisibility(View.GONE);
		VideoBiz.stopAfterAnimation(btnNext);
	}
	// 点击执行的功能
	private void clickExecute(int seekTo) {
		startTime = Constant.timeLevel07[seekTo][0] - offset_time;
		endTime = Constant.timeLevel07[seekTo][1] - offset_time;
		if(video == null) return;
		video.seekTo(Constant.timeLevel07[seekTo][0] - offset_time);
		isClicked = true;
		currentIndex = seekTo;
		int num = clickNum;
		if (num == 17 || num == 19 || num == 21 || num == 23) {
			int time = Constant.timeLevel07[seekTo][1]
					- Constant.timeLevel07[seekTo][0];
			if (num == 17) {
				time = time - 500;
				setQiPaoVisible(true);
			} else if (num == 19) {
				time = time + 500;
			} 
			playSound(time, new VideoBiz().delayReadWordLevel07(isEg, num)); // 延迟读取
		}

		if (num == 18 || num == 20 || num == 22 || num == 24) {
			int ttime = Constant.timeLevel07[seekTo][1]
					- Constant.timeLevel07[seekTo][0];
			if (num == 18) {
				ttime = ttime - 666;
			} else if (num == 20) {
				ttime = ttime - 1042;
			} else if (num == 22) {
				ttime = ttime - 1167;
			} else if (num == 24) {
				ttime = ttime - 858;
			}
			playSound(0, new VideoBiz().delayReadWordLevel07(isEg, num)); // 延迟读取
			visibleScripte(ttime, clickNum);
		}
		videoCycle(clickNum);
		isCycle = true;
	}

	@Override
	public void onClick(View v) {
		if (clickNum == 1 || clickNum == 2 || clickNum == 3 || clickNum == 5
				|| clickNum == 6 || clickNum == 7 || clickNum == 9
				|| clickNum == 10 || clickNum == 11) {
			playSoundMusic(MediaCommon.getLevel07Mp3(isEg, 1));
		}
		stopBtnAndShou();
		if (v == ivBack) {
			returnHome();
		}
		if (v == ivShou) {
			if (!isSecondClick) {
				setClick();
			} else {
				if(indexNum == 18) {
					setSelectedPaopao(0);
				} else if(indexNum == 19) {
					setSelectedPaopao(1);
				} else if(indexNum == 20) {
					setSelectedPaopao(2);
				} else if(indexNum == 21) {
					setSelectedPaopao(3);
				}
				if(clickNum == 24) {
					if(secondClick == 0) {
						clickNum = 16;
					}
				}
				if (clickNum == 16 || clickNum == 18 || clickNum == 20
						|| clickNum == 22) {
					secondClick++;
				}
				if(clickNum < 29) {
					setClick();
				} else {
					gotoSuccessActivity();
				}
			}
		} else if (v == iv11) {
			if (isSecondClick) {
				secondClick++;
				clickNum = 16;
				setClick();
				setSelectedPaopao(0);
			}
		} else if (v == iv21) {
			if (isSecondClick) {
				secondClick++;
				clickNum = 18;
				setClick();
				setSelectedPaopao(1);
			}
		} else if (v == iv31) {
			if (isSecondClick) {
				secondClick++;
				clickNum = 20;
				setClick();
				setSelectedPaopao(2);
			}
		} else if (v == iv41) {
			if (isSecondClick) {
				secondClick++;
				clickNum = 22;
				setClick();
				setSelectedPaopao(3);
			}
		} else if (v == btnNext) {
			if (clickNum > 13 && clickNum < 25) {
				clickNum = 24;
				btnNext.setVisibility(View.GONE);
				setClick();
			} else if(clickNum >= 25) {
				// 跳转到ShowTime
				gotoSuccessActivity();
			}
		}
		if(clickNum >= 25) {
			/*btnNext.setVisibility(View.VISIBLE);
			VideoBiz.startAfterAnimation(btnNext);*/
		}
	}
	
	private void gotoSuccessActivity() {
//		startActivity(new Intent(mContext, SuccessActivity.class).putExtra("level", 9));		//4
		biz.clearMediaPlayer(mMediaPlayer, mMediaPlayerSound, video);
		finish();
	}
	
	/*-----------------sayit 3秒控制----------------------*/
	private void sayit3s1(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
//					handler.sendMessage(handler.obtainMessage(3));	
					Thread.sleep(3000);
					handler.sendMessage(handler.obtainMessage(3));		
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	private void playSound(final int time, final String path) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					if(time < 0) return;
					Thread.sleep(time);
					playSoundMusic(path);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
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

	private void setCirclePlay(int num) {
		int currentPos = video.getCurrentPosition();
		int tem_num = num;
		do {
			try {
				if (currentIndex <= 260) {
					Thread.sleep(50);
				}
				if(tem_num != clickNum) break;
				
				if (!isCycle || video == null)
					break;
				currentPos = video.getCurrentPosition(); // 空指针null
				if (Constant.timeLevel07[currentIndex][4] == 1  && isClicked) {// && currentIndex != clickIndex
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
			if(tem_num == clickNum){
				if(Constant.timeLevel07[currentIndex][4] == 0){
					currentIndex = Constant.timeLevel07[currentIndex][3];
					if(!isSecondClick) {
						if(currentIndex == 79) {
							currentIndex = 82;
						} else if(currentIndex == 92) {
							currentIndex = 95;
						} else if(currentIndex == 105) {
							currentIndex = 108;
						}
					}
				}
				startTime = Constant.timeLevel07[currentIndex][0] - offset_time;
				endTime = Constant.timeLevel07[currentIndex][1] - offset_time;
				handler.sendMessage(handler.obtainMessage(2,tem_num));
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
				if(clickNum < 25) {
					showBtnAndShou();
					if((clickNum >16 && clickNum<25 ))
						VideoBiz.stopAfterAnimation(btnNext);
				} else if(clickNum < 29){
//					startVR();
//					sayit3s();
				} else {
					btnNext.setVisibility(View.VISIBLE);				
					VideoBiz.startAfterAnimation(btnNext);
					ivShou.setVisibility(View.VISIBLE);
					setViewPosition(ivShou, 6);
					CommonAnimation.startShouAnimation(ivShou);
				}
				break;
			case 1:
				int mClickNum = (Integer) msg.obj;
				if (mClickNum == 18) {
					iv11.setVisibility(View.VISIBLE);
				} else if (mClickNum == 20) {
					iv21.setVisibility(View.VISIBLE);
				} else if (mClickNum == 22) {
					iv31.setVisibility(View.VISIBLE);
				} else if (mClickNum == 24) {
					iv41.setVisibility(View.VISIBLE);
				}
				break;
			case 2:
				int numValue = (Integer)msg.obj;
				if (video != null && clickNum == numValue) {
					video.seekTo(startTime);
					videoCycle(numValue);
				}
				break;
			case 3:
//				stopVR();
				if (clickNum < 50) {
					isClicked = true;
					setClick();
				}
				break;
			default:
				break;
			}
		}

	};
	
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

	/* 设置视图的位置 */
	private void setViewPosition(ImageView iv, int i) {
		iv.setLayoutParams(LayoutParameters.setViewPositionParams(
				FixedPosition.level07_position[i][0],
				FixedPosition.level07_position[i][1],
				FixedPosition.level07_position[i][2],
				FixedPosition.level07_position[i][3], scaleQPW, scaleQPH,
				xoffset, yoffset, suoxiao));
	}

	/* 显示手动画 */
	private void showBtnAndShou() {
		int i = -1;
		if (!isSecondClick) {
			int index = clickNum;
			if (clickNum == 24) {
				isSecondClick = true;
				index = 100;
			}
			i = FixedPosition.getLevel07PositionIndex(
					FixedPosition.level07_index_to_position, index);
		} else {
			int index = new VideoBiz().secondClickQipaoLevel07(clickNum);
			if (secondClick >= 4 && clickNum != 17 && clickNum != 19
					&& clickNum != 21 && clickNum != 23) {
				index = 24;
			}
			i = FixedPosition.getLevel07PositionIndex(
					FixedPosition.level07_index_to_position, index);
		}
		if (i != -1) {
			indexNum = i;
			ivShou.setVisibility(View.VISIBLE);
			if((clickNum == 1 || clickNum == 13) && isCanSayClickHere) {
				MediaCommon.playClickHere(mContext);
			}
			if (!isSecondClick) {
				if (clickNum == 12 || clickNum == 24) {
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
			setViewPosition(ivShou, i);
			CommonAnimation.startShouAnimation(ivShou);
			if (clickNum == 0 && isCanSayClickHere) {
				playSoundMusic(MediaCommon.getLevel07Mp3(isEg, 6));
			}
		}
	}

	/* 消失手动画 */
	private void stopBtnAndShou() {
		ivShou.setVisibility(View.GONE);
		CommonAnimation.stopShouAnimation(ivShou);
	}

	private void playBgMusic(String path) {
		try {
			if (mMediaPlayer != null) { // 一定要清空播放器资源
				mMediaPlayer.stop();
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
	}

	private void playSoundMusic(String path) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			
		}catch(Exception e){
			
		}
		try{
			if (null != video) {
				video.stopPlayback();
				video = null;
			}
		}catch(Exception e){
			
		}
//		 startActivity(new Intent(mContext, SelectLevelActivity.class));
		this.finish();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setKeepScreenOn(true);
		isCanSayClickHere = true;
		if (null != video) {
			video.start();
			//跳到指定的帧数
			video.seekTo(preservation);
			//显示云上面的手指
			/*if(cycleIndex==5&&!isCanClick){
				ivShou.setVisibility(View.INVISIBLE);
			}*/
		}
		if (null != mMediaPlayer && !mMediaPlayer.isPlaying()) {
			mMediaPlayer.start();
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
		isCanSayClickHere = false;
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

	// 延迟显示气泡数据
	private void visibleScripte(final long timeSleep, final int clickNum) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(timeSleep);
					handler.sendMessage(handler.obtainMessage(1, clickNum));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

	private void setQiPaoVisible(boolean isvisible) {
		if (isvisible) {
			iv1.setVisibility(View.VISIBLE);
			iv2.setVisibility(View.VISIBLE);
			iv3.setVisibility(View.VISIBLE);
			iv4.setVisibility(View.VISIBLE);

			iv1 = biz.setQipaoAnimation(iv1, scaleQP);
			iv2 = biz.setQipaoAnimation(iv2, scaleQP);
			iv3 = biz.setQipaoAnimation(iv3, scaleQP);
			iv4 = biz.setQipaoAnimation(iv4, scaleQP);

		} else {
			iv1.setVisibility(View.INVISIBLE);
			iv2.setVisibility(View.INVISIBLE);
			iv3.setVisibility(View.INVISIBLE);
			iv4.setVisibility(View.INVISIBLE);
			paopao_selected.setVisibility(View.INVISIBLE);
		}
	}
	
	/*设置泡泡view——————0、1、2、3*/	
	private void setSelectedPaopao(int i){
		indexNum = 0;
		paopao_selected.setVisibility(View.VISIBLE);
		setCommonPosition(paopao_selected,(i+4));		
	}
	
	/*设置泡泡view*/	
	private void setPaopao(){
		setCommonPosition(iv11,0);
		setCommonPosition(iv21,1);
		setCommonPosition(iv31,2);
		setCommonPosition(iv41,3);
		
		setCommonPosition(iv1,0);
		setCommonPosition(iv2,1);
		setCommonPosition(iv3,2);
		setCommonPosition(iv4,3);
		
		iv1.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao"));
		iv2.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao"));
		iv3.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao"));
		iv4.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao"));
		paopao_selected.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "paopao_selected"));
		iv11.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "ic_ox"));
		iv21.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "ic_otter"));
		iv31.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "ic_ostrich"));
		iv41.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz03_images, "ic_officer"));
	}
	
	/*设置泡泡的位置*/	
	private void setCommonPosition(ImageView iv, int i) {
		biz.setViewPositionNoSuoxiao(iv, i, FixedPosition.paopao_position,
				(width / 1280.0f), (height / 720.0f));
	}
	
	private void readWithMe(final int time, final int index) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					if (index == 0) {
						mMediaPlayer.pause();
					}
					String path = new VideoBiz().readWithMeLevel07(isEg, index);
					Thread.sleep(time);
					setMediaStatus();
					MediaCommon.playReadAfterMe(mContext); // 跟我读
					Thread.sleep(2000);
					playSoundMusic(path);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void setCommonViewPosition(View view, int i) {
		biz.setViewPosition(view, i, FixedPosition.level_common_position, scaleQPW, scaleQPH, xoffset, yoffset, suoxiao);
	}

}
