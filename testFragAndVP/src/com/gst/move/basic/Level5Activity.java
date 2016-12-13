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
import android.widget.RelativeLayout;

/**
 * @author gst
 *  
 */
public class Level5Activity extends BaseActivity implements OnClickListener {
	
	private MyVideoView video;
	private ImageView ivBack, ivShou;
	private ImageView iv1, iv2, iv3, iv4, iv11, iv21, iv31, iv41, paopao_selected;
	private Button btnNext;
	private RelativeLayout rlQipao;
	private String path;
	private VideoBiz biz;
	private MediaPlayer mMediaPlayer = null;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int clickNum = 0;
	private boolean isCycle = false;
	private boolean isClicked = true;
	private int startTime = 0;
	private int endTime = 0;
	private boolean isEg = false;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private int xoffset = 0, yoffset = 0;
	private float suoxiao = 1.0f;
	private Context mContext;
	private int currentIndex = 0;
	private int preservation=0;   //按home键保存播放帧数
	private boolean isStartClickQipao = false;
	private int qiPaoClickNum = 0;
	private boolean isFirstClickQipao = false;
	private boolean fingerPointingNext = false;

	private int offset_time = 0;		//用于步步高平板延迟时间修正
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_05);
		
		initializationData();
		
		offset_time = Tools.delayTime();
		setView();
	}
	
	private void initializationData() {
		mContext = Level5Activity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1920.0f);
		scaleQPH = (height / 1080.0f);
		isEg = BaseCommon.getIsEg(mContext);
		path = Constant.path_raz02 + "level05.mp4/";
		currentIndex = 0;
		startTime = Constant.timeLevel05[currentIndex][0];
		endTime = Constant.timeLevel05[currentIndex][1];
//		String bgMusicPath = MediaCommon.getLevel05Mp3(6);
//		playBgMusic(bgMusicPath);
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz02 + "bg_music_level5.mp3");
		readSingleWord(1200, MediaCommon.getLevel05Mp3(0), 0);
		readSingleWord(2700, "", 9);
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
		iv11 = (ImageView) findViewById(R.id.iv1_11);
		iv21 = (ImageView) findViewById(R.id.iv1_21);
		iv31 = (ImageView) findViewById(R.id.iv1_31);
		iv41 = (ImageView) findViewById(R.id.iv1_41);
		paopao_selected = (ImageView) findViewById(R.id.paopao_selected);
		paopao_selected.setVisibility(View.INVISIBLE);
		ivShou.setVisibility(View.INVISIBLE);
		btnNext.setVisibility(View.INVISIBLE);
		rlQipao.setVisibility(View.INVISIBLE);
		
		setCommonViewPosition(ivBack, 6);
		setCommonViewPosition(btnNext, 4);
		
		videoCycle(0);
		biz.playVideo(video, path);
		setQiPaoVisible(true);
		setPaopao();
		
		ivBack.setOnClickListener(this);
		ivShou.setOnClickListener(this);
		btnNext.setOnClickListener(this);
//		CommonUtil.downLoadRes(mContext, 1, 3, Constant.download_level_06, commonPath, "raz02");
	}

	@Override
	public void onClick(View v) {
		stopBtnAndShou();
		if(v == ivBack) {
			returnHome();
		} else if(v == ivShou) {
			
			if(clickNum <= 12) {
				if(!fingerPointingNext) {
					if(isFirstClickQipao) {
						clickNum = 4;
						currentIndex = 30;
					}
					setClick();
				} else {
					disShowBtnNext();
					gotoSayIt();
				}
			} else {
				gotoSuc();
			}
		} else if(v == iv11) {
			clickQiPao(4, 30);
			setSelectedPaopao(0);
		} else if(v == iv21) {
			clickQiPao(6, 46);
			setSelectedPaopao(1);
		} else if(v == iv31) {
			clickQiPao(8, 60);
			setSelectedPaopao(2);
		} else if(v == iv41) {
			clickQiPao(10, 74);
			setSelectedPaopao(3);
		} else if(v == btnNext) {
			if(clickNum <= 12) {
				disShowBtnNext();
				gotoSayIt();
			} else {
				gotoSuc();
			}
		}
		isFirstClickQipao = false;
		fingerPointingNext = false;
	}
	//隐藏btnNext
	private void disShowBtnNext(){
		btnNext.setVisibility(View.GONE);
		VideoBiz.stopAfterAnimation(btnNext);
	}
	// 跳转到sayit
	private void gotoSayIt() {
		clickNum = 12;
		currentIndex = 86;
		setClick();
	}
	
	// 点击气泡，并且计数
	private void clickQiPao(int num, int index) {
		currentIndex = index;
		clickNum = num;
		setClick();
	}
	
	private void gotoSuc() {
//		startActivity(new Intent(mContext, SuccessActivity.class).putExtra("level", 6));		//4
		clearMediaPlayer();
		finish();
	}
	
	/*设置泡泡view——————0、1、2、3*/	
	private void setSelectedPaopao(int i){
		paopao_selected.setVisibility(View.VISIBLE);
		setQiPaoPosition(paopao_selected,(i+4));		
	}
	
	/*设置泡泡view*/	
	private void setPaopao(){
		setQiPaoPosition(iv11,0);
		setQiPaoPosition(iv21,1);
		setQiPaoPosition(iv31,2);
		setQiPaoPosition(iv41,3);
		setQiPaoPosition(iv1,0);
		setQiPaoPosition(iv2,1);
		setQiPaoPosition(iv3,2);
		setQiPaoPosition(iv4,3);
		
		iv1.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao"));
		iv2.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao"));
		iv3.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao"));
		iv4.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao"));
		paopao_selected.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "paopao_selected"));
		iv11.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "iv_lv5_1"));
		iv21.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "iv_lv5_2"));
		iv31.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "iv_lv5_3"));
		iv41.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz02_images, "iv_lv5_4"));
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
					if (Constant.timeLevel05[currentIndex][4] == 1  && isClicked) {// && currentIndex != clickIndex
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
					if(Constant.timeLevel05[currentIndex][4] == 0){
						currentIndex = Constant.timeLevel05[currentIndex][3];
					}
					startTime = Constant.timeLevel05[currentIndex][0] - offset_time;
					endTime = Constant.timeLevel05[currentIndex][1] - offset_time;
					handler.sendMessage(handler.obtainMessage(2,tem_num));
					if(currentIndex == 34 || currentIndex == 36 || currentIndex == 50 || currentIndex == 52 || currentIndex == 64 || currentIndex == 66 || currentIndex == 78 || currentIndex == 80){
						handler.sendMessage(handler.obtainMessage(4));
					}
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
					if(clickNum <= 12) {
						showBtnAndShou();
					} else if(clickNum < 17) {
						sayit3s();
					} else {
						btnNext.setVisibility(View.VISIBLE);
						VideoBiz.startAfterAnimation(btnNext);
						ivShou.setVisibility(View.VISIBLE);
						setViewPosition(ivShou, 10);
						CommonAnimation.startShouAnimation(ivShou);
					}
					break;
				case 1:
					int mClickNum = (Integer) msg.obj;
					if (mClickNum == 6) {
						iv11.setVisibility(View.VISIBLE);
					} else if (mClickNum == 8) {
						iv21.setVisibility(View.VISIBLE);
					} else if (mClickNum == 10) {
						iv31.setVisibility(View.VISIBLE);
					} else if (mClickNum == 12) {
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
					if (clickNum < 50) {
						isClicked = true;
						setClick();
					}
					break;
				case 4:
				if (currentIndex == 34 || currentIndex == 50
						|| currentIndex == 64 || currentIndex == 78
						|| currentIndex == 15 || currentIndex == 19
						|| currentIndex == 23 || currentIndex == 0
						|| currentIndex == 1) {
					new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								playOnlineMusic(MediaCommon.getLevel05Mp3(1));
								Thread.sleep(1000);
								playWord();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}).start();
				}
					break;
				case 5:
					playWord();
					break;
				default:
					break;
				}
			}

		};
		
		private void playWord(){
			if(currentIndex == 34 || currentIndex == 36 || currentIndex == 90){
				playOnlineMusic(MediaCommon.getLevel05Mp3(2));
			}else if(currentIndex == 50 || currentIndex == 52 || currentIndex == 96){
				playOnlineMusic(MediaCommon.getLevel05Mp3(3));
			}else if(currentIndex == 64 || currentIndex == 66 || currentIndex == 102){
				playOnlineMusic(MediaCommon.getLevel05Mp3(4));
			}else if(currentIndex == 78 || currentIndex == 80 || currentIndex == 108){
				playOnlineMusic(MediaCommon.getLevel05Mp3(5));
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
		
		private void setClick() {
			isCycle = false;
			clickNum++;
			if(isStartClickQipao && (clickNum == 5 || clickNum == 7 
					|| clickNum == 9 || clickNum == 11)) {
				qiPaoClickNum++;
				if(clickNum == 5) {
					setSelectedPaopao(0);
				} else if(clickNum == 7) {
					setSelectedPaopao(1);
				} else if(clickNum == 9) {
					setSelectedPaopao(2);
				} else if(clickNum == 11) {
					setSelectedPaopao(3);
				}
			}
			//TODO测试
//			if(clickNum == 1) {
//				iv11.setVisibility(View.VISIBLE);
//				iv21.setVisibility(View.VISIBLE);
//				iv31.setVisibility(View.VISIBLE);
//				clickNum = 11;
//				currentIndex = 74;
//			}
			
			if(clickNum > 12) {
				rlQipao.setVisibility(View.GONE);
			}
			System.out.println("clickNum :"+clickNum);
			System.out.println("currentIndex :"+currentIndex);
//			sayItStatus();
			readWord();
			showPhysical();
			int seekTo = Constant.timeLevel05[currentIndex][3];

			if(seekTo > 86){	//sayit
				gotoSuc();
				return;
			}
			clickExecute(seekTo);
		}

		// 点击执行的功能
		private void clickExecute(int seekTo) {
			startTime = Constant.timeLevel05[seekTo][0] - offset_time;
			endTime = Constant.timeLevel05[seekTo][1] - offset_time;
			if(video == null) return;
			video.seekTo(Constant.timeLevel05[seekTo][0] - offset_time);
			isClicked = true;
			currentIndex = seekTo;
			videoCycle(clickNum);
			isCycle = true;
		}
		
		// 读单词
		private void readWord() {
			if(clickNum == 2 || clickNum == 3 || clickNum == 4) {
				playOnlineMusic(MediaCommon.getLevel05Mp3(0));
			}else if(clickNum == 6) {
				readWord(0, MediaCommon.getLevel05Mp3(1), MediaCommon.getLevel05Mp3(2));
			} else if(clickNum == 8) {
				readWord(840, MediaCommon.getLevel05Mp3(1), MediaCommon.getLevel05Mp3(3));
			} else if(clickNum == 10) {
				readWord(840, MediaCommon.getLevel05Mp3(1), MediaCommon.getLevel05Mp3(4));
			} else if(clickNum == 12) {
				readWord(1000, MediaCommon.getLevel05Mp3(1), MediaCommon.getLevel05Mp3(5));
			} else if(clickNum == 5 || clickNum == 7 || clickNum == 9 || clickNum == 11) {
				playOnlineMusic(MediaCommon.getLevel05Mp3(1));
			}
		}
		
		//say it 跟我读和欢呼
		private void sayItStatus() {
			if (clickNum == 13) {
//				MediaCommon.playSayit(mContext);
				readWithMe(3500, MediaCommon.getLevel05Mp3(2));
			} else if (clickNum == 14) {
				readWithMe(3500, MediaCommon.getLevel05Mp3(3));
			} else if (clickNum == 15) {
				readWithMe(3500, MediaCommon.getLevel05Mp3(4));
			} else if (clickNum == 16) {
				readWithMe(3500, MediaCommon.getLevel05Mp3(5));
			}
			if (clickNum >= 14) { // 欢呼
				MediaCommon.playGuzhang(mContext);
			}
		}
		
		private void readWithMe(final int time, final String path) {
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
		
		// 读单词
		private void readWord(final int time, final String path1, final String path2) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(time);
						playOnlineMusic(path1);
						Thread.sleep(1000);
						playOnlineMusic(path2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		private void setQiPaoVisible(boolean isvisible){
			if(isvisible){
				iv1.setVisibility(View.VISIBLE);
				iv2.setVisibility(View.VISIBLE);
				iv3.setVisibility(View.VISIBLE);
				iv4.setVisibility(View.VISIBLE);
				CommonAnimation.setQipaoAnimation(iv1);
				CommonAnimation.setQipaoAnimation(iv2);
				CommonAnimation.setQipaoAnimation(iv3);
				CommonAnimation.setQipaoAnimation(iv4);
			}else{
				iv1.setVisibility(View.INVISIBLE);
				iv2.setVisibility(View.INVISIBLE);
				iv3.setVisibility(View.INVISIBLE);
				iv4.setVisibility(View.INVISIBLE);
				CommonAnimation.stopQipiaoAnimation(iv1);
				CommonAnimation.stopQipiaoAnimation(iv2);
				CommonAnimation.stopQipiaoAnimation(iv3);
				CommonAnimation.stopQipiaoAnimation(iv4);
				iv11.setVisibility(View.GONE);
				iv21.setVisibility(View.GONE);
				iv31.setVisibility(View.GONE);
				iv41.setVisibility(View.GONE);
			}
		}
		
		// 显示气泡实物
		private void showPhysical() {
			if(clickNum == 5) {
				rlQipao.setVisibility(View.VISIBLE);
			}
		}
		
		// 显示实物
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
		
		// 读单个单词---sd卡路径图片
		private void readSingleWord(final int time, final String path, final int index) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(time);
						if(path != null && !path.equals("")) {
							playOnlineMusic(path);
						} else {
							setResId(index);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		private void setResId(int index) {
			String pathVoiceover = MediaCommon.getLevel05Mp3(index);
			playOnlineMusic(pathVoiceover);
		}
		
		/* 消失手动画 */
		private void stopBtnAndShou() {
			ivShou.setVisibility(View.GONE);
			CommonAnimation.stopShouAnimation(ivShou);
		}

		private void returnHome() {
			isCycle = false;
			clearMediaPlayer();
//			startActivity(new Intent(mContext, SelectLevelActivity.class));
			this.finish();
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
		
		private void setViewPosition(View iv, int i) {
			biz.setViewPosition(iv, i, FixedPosition.level05_position, scaleQPW, scaleQPH, xoffset, yoffset, suoxiao);
		}
		
		private void setQiPaoPosition(View iv, int i) {
			biz.setViewPosition(iv, i, FixedPosition.paopao_position, (width/1280.0f), (height/720.0f), xoffset, yoffset, suoxiao);
		}
		
		private void setCommonViewPosition(View view, int i) {
			biz.setViewPosition(view, i, FixedPosition.level_common_position, scaleQPW, scaleQPH, xoffset, yoffset, suoxiao);
		}
		
		/* 显示手动画 */
	private void showBtnAndShou() {
		int i = -1;
		if (clickNum == 11) {
			iv11.setOnClickListener(this);
			iv21.setOnClickListener(this);
			iv31.setOnClickListener(this);
			iv41.setOnClickListener(this);
		}
		i = FixedPosition.getLevel07PositionIndex(
				FixedPosition.level05_index_to_position, clickNum);
		System.out.println("showBtnAndShou clickNum :"+clickNum);
		System.out.println("showBtnAndShou i :"+i);
		System.out.println("showBtnAndShou qiPaoClickNum :"+qiPaoClickNum);
		if (i != -1) {
			ivShou.setVisibility(View.VISIBLE);
			physicalVisible();
			clickHere();
			if (clickNum <= 11) {
				if(!isStartClickQipao) { // 第一遍顺序执行
					setViewPosition(ivShou, i);
				} else { // 判断气泡是否被点击过4次
					if(i == 4 || i == 6 || i == 0) { // 一个单词完整执行了一遍
						if(qiPaoClickNum >= 4) { // 4遍结束
							fingerPointingNext = true;
							setViewPosition(ivShou, 10);
						} else { // 判断当前被执行的气泡
							int num = judgeQipaoNum();
							setQiPaoPosition(ivShou, num);
						}
					} else { // 正在执行中
						setViewPosition(ivShou, i);
					}
				}
			} else if (clickNum == 12) {
				if(!isStartClickQipao) {
					isStartClickQipao = true;
					btnNext.setVisibility(View.VISIBLE);
					VideoBiz.startAfterAnimation(btnNext);
				}
				if(qiPaoClickNum >= 4) { // 指向下一步
					fingerPointingNext = true;
					setViewPosition(ivShou, 10);
				} else {
					isFirstClickQipao = true;
					setQiPaoPosition(ivShou, 0);
				}
			}
			CommonAnimation.startShouAnimation(ivShou);
		}
	}
	
	private void physicalVisible() {
		if (clickNum == 6 || clickNum == 8 || clickNum == 10 || clickNum == 12) {
			visibleScripte(0, clickNum);
		}
	}
	
	private void clickHere() {
		if(clickNum == 0) {
			playOnlineMusic(MediaCommon.getLevel05Mp3(10));
		} else if(clickNum == 1) {
			int resId = BaseCommon.getMp3Id3(mContext, "clickhere");
			if (resId != 0) {
				playSound(resId);
			}
		} else if(clickNum == 4 || clickNum == 6 || clickNum == 8 || clickNum == 10) {
			if(!isStartClickQipao) {
				playOnlineMusic(MediaCommon.getLevel05Mp3(11));
			}
		}
	}
	
	//判断当前是第几个气泡被选中
	private int judgeQipaoNum() {
		int num = 0;
		int clickNumValue = 4;
		currentIndex = 30;
		if(clickNum < 7) {
			currentIndex = 46;
			clickNumValue = 6;
			num = 1;
		} else if(clickNum < 9) {
			currentIndex = 60;
			clickNumValue = 8;
			num = 2;
		} else if(clickNum < 11) {
			currentIndex = 74;
			clickNumValue = 10;
			num = 3;
		}
		clickNum = clickNumValue;
		System.out.println("judgeQipaoNum num :"+num);
		return num;
	}
	
	// 清除数据
		private void clearMediaPlayer() {
			try{
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
			}catch(Exception e){
				
			}
			
		}

		// 播放sd卡路径音频
		private void playOnlineMusic(String path) {
			try {
				mMediaPlayerSound = new BaseCommon().playMusic(mMediaPlayerSound, path, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 播放sd卡路径音频
		private void playBgMusic(String path) {
			try {
				mMediaPlayer = new BaseCommon().playMusic(mMediaPlayer, path, true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 播放res路径音频
		private void playSound(int resId) {
			mMediaPlayerSound = MediaPlayer.create(this, resId);
			new Thread(new Runnable() {

				@Override
				public void run() {
					if (mMediaPlayerSound != null) {
						mMediaPlayerSound.start();
						mMediaPlayerSound.setLooping(false);
					}
				}
			}).start();
		}
		
}
