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
 *  太空视频界面
 */
public class Level2Activity extends BaseActivity implements OnClickListener {
	
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
	private boolean isShowFirstQipao = false;
	private boolean isClickNext = false;

	private int offset_time = 0;		//用于步步高平板延迟时间修正
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_02);
		
		initializationData();
				
		offset_time = Tools.delayTime();
		if(offset_time > 0){
			offset_time = offset_time-100;
		}
		setView();
	}
	
	private void initializationData() {
		mContext = Level2Activity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1920.0f);
		scaleQPH = (height / 1080.0f);
		isEg = BaseCommon.getIsEg(mContext);
		path = Constant.path_raz01 + "level03_taikong.mp4/";
		currentIndex = 0;
		startTime = Constant.timeLevel02[currentIndex][0];
		endTime = Constant.timeLevel02[currentIndex][1];
//		mMediaPlayer = MediaPlayer.create(this, R.raw.bgmusic_level02);
		// 播放背景音乐
		mMediaPlayer = biz.playBgMusic(mMediaPlayer, Constant.path_raz01 + "bgmusic_level02.mp3");
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
//		CommonUtil.downLoadRes(mContext, 0, 3, Constant.download_level_03, commonPath, "raz01");
	}

	@Override
	public void onClick(View v) {
		stopBtnAndShou();
		if(v == ivBack) {
			returnHome();
		} else if(v == ivShou) {
			if(isShowFirstQipao) {
				isShowFirstQipao = false;
				clickNum = 4;
				currentIndex = 14;
			}
			if(!isClickNext) {
				setClick();
			} else {
				gotoSuc();
			}
		} else if(v == iv11) {
			clickQiPao(4, 14);
			setSelectedPaopao(0);
		} else if(v == iv21) {
			clickQiPao(10, 32);
			setSelectedPaopao(1);
		} else if(v == iv31) {
			clickQiPao(15, 47);
			setSelectedPaopao(2);
		} else if(v == iv41) {
			clickQiPao(21, 65);
			setSelectedPaopao(3);
		} else if(v == btnNext) {
			gotoSuc();
		}
		isClickNext = false;
	}
	
	// 点击气泡，并且计数
	private void clickQiPao(int num, int index) {
		currentIndex = index;
		clickNum = num;
		setClick();
	}
	
	private void gotoSuc() {
//		startActivity(new Intent(mContext, SuccessActivity.class).putExtra(
//				"level", 2));
		clearMediaPlayer();
		finish();
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
		iv1.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao"));
		iv2.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao"));
		iv3.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao"));
		iv4.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao"));
		paopao_selected.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "paopao_selected"));
		iv11.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "ic_apple"));
		iv21.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "ic_ant"));
		iv31.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "ic_angry"));
		iv41.setImageDrawable(BaseCommon.drawableChange(Constant.path_raz01_images, "ic_ax"));
	}
	
	/*设置泡泡view——————0、1、2、3*/	
	private void setSelectedPaopao(int i){
//		indexNum = 0;
		paopao_selected.setVisibility(View.VISIBLE);
		setQiPaoPosition(paopao_selected,(i+4));		
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
					if (Constant.timeLevel02[currentIndex][4] == 1  && isClicked) {// && currentIndex != clickIndex
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
					System.out.println("currentIndex 1 :" + currentIndex);
					if(Constant.timeLevel02[currentIndex][4] == 0){
						currentIndex = Constant.timeLevel02[currentIndex][3];
					}
					System.out.println("currentIndex 2 :" + currentIndex);
					startTime = Constant.timeLevel02[currentIndex][0] - offset_time;
					endTime = Constant.timeLevel02[currentIndex][1] - offset_time;
					handler.sendMessage(handler.obtainMessage(2,tem_num));
				}
				System.out.println("currentIndex 3 :" + currentIndex);
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
					break;
				case 1:
					int mClickNum = (Integer) msg.obj;
					if (mClickNum == 10) {
						setResId("exllent_girl");
						iv11.setVisibility(View.VISIBLE);
					} else if (mClickNum == 15) {
						setResId("awesome_girl");
						iv21.setVisibility(View.VISIBLE);
					} else if (mClickNum == 21) {
						setResId("welldone_girl");
						iv31.setVisibility(View.VISIBLE);
					} else if (mClickNum == 27) {
						setResId("all_shi");
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
				default:
					break;
				}
			}

		};
		
		private void setClick() {
			isCycle = false;
			clickNum++;
			if(isStartClickQipao && (clickNum == 5 || clickNum == 11 
					|| clickNum == 16 || clickNum == 22)) {
				qiPaoClickNum++;
				if(clickNum == 5) {
					setSelectedPaopao(0);
				} else if(clickNum == 11) {
					setSelectedPaopao(1);
				} else if(clickNum == 16) {
					setSelectedPaopao(2);
				} else if(clickNum == 22) {
					setSelectedPaopao(3);
				}
			}
			int seekTo = Constant.timeLevel02[currentIndex][3];
			clickExecute(seekTo);
		}

		// 点击执行的功能
		private void clickExecute(int seekTo) {
			try{
				startTime = Constant.timeLevel02[seekTo][0] - offset_time;
				endTime = Constant.timeLevel02[seekTo][1] - offset_time;
				if(video == null) return;
				video.seekTo(startTime);
				isClicked = true;
				currentIndex = seekTo;
				showPhysical(seekTo);
				videoCycle(clickNum);
				isCycle = true;
			}catch(Exception e){}
		}
		
		// 显示气泡实物
		private void showPhysical(int seekTo) {
			if(clickNum == 5) {
				rlQipao.setVisibility(View.VISIBLE);
			}
			if (clickNum == 10 || clickNum == 15 || clickNum == 21 || clickNum == 27) {
				long ttime = Constant.timeLevel02[seekTo][1]
						- Constant.timeLevel02[seekTo][0];
				if (clickNum == 10) {
					ttime = ttime - 600;
				} else if (clickNum == 15) {
					ttime = ttime - 660;
				} else if (clickNum == 21) {
					ttime = ttime - 1500;
				} else if (clickNum == 27) {
					ttime = ttime - 2000;
				}
				visibleScripte(ttime, clickNum);
			}
		}
		
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
		
		/* 消失手动画 */
		private void stopBtnAndShou() {
			ivShou.setVisibility(View.GONE);
			CommonAnimation.stopShouAnimation(ivShou);
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
				e.printStackTrace();
			}
			try{
				if (null != video) {
					video.stopPlayback();
					video = null;
				}
			}catch(Exception e){
				e.printStackTrace();
			}
//			 startActivity(new Intent(mContext, SelectLevelActivity.class));
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
			biz.setViewPosition(iv, i, FixedPosition.level02_position, scaleQPW, scaleQPH, xoffset, yoffset, suoxiao);
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
		if (clickNum == 26) {
			iv11.setOnClickListener(this);
			iv21.setOnClickListener(this);
			iv31.setOnClickListener(this);
			iv41.setOnClickListener(this);
		}
		i = FixedPosition.getLevel07PositionIndex(
				FixedPosition.level02_index_to_position, clickNum);
		if (i != -1) {
			ivShou.setVisibility(View.VISIBLE);
			clickHere();
			if (clickNum <= 26) {
				if(!isStartClickQipao) { // 第一遍顺序执行
					setViewPosition(ivShou, i);
				} else { // 判断气泡是否被点击过4次
					if(i == 4) { // 一个单词完整执行了一遍
						if(qiPaoClickNum >= 4) { // 4遍结束
							isClickNext = true;
							setViewPosition(ivShou, 3);
						} else { // 判断当前被执行的气泡
							int num = judgeQipaoNum();
							setQiPaoPosition(ivShou, num);
						}
					} else { // 正在执行中
						setViewPosition(ivShou, i);
					}
				}
				
			} else if (clickNum == 27) {
				if(!isStartClickQipao) {
					isStartClickQipao = true;
					btnNext.setVisibility(View.VISIBLE);
					VideoBiz.startAfterAnimation(btnNext);
				}
				if(qiPaoClickNum >= 4) { // 4遍结束
					isClickNext = true;
				} 
				if(qiPaoClickNum >= 4) { // 指向下一步
					setViewPosition(ivShou, 3);
				} else {
					isShowFirstQipao = true;
					setQiPaoPosition(ivShou, 0);
				}
			}
			CommonAnimation.startShouAnimation(ivShou);
		}
	}
	
	private void clickHere() {
		if(clickNum == 0) {
			setResId("clickhere");
		} else if(clickNum == 5 || clickNum == 11 || clickNum == 16 || clickNum == 22) {
			setResId("click_magic_wand");
		}
	}
	
	private void setResId(String mp3Name) {
		int resId = BaseCommon.getMp3Id3(mContext, mp3Name);
		if (resId != 0) {
			playSound(resId);
		}
	}
	
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
	
	//判断当前是第几个气泡被选中
	private int judgeQipaoNum() {
		int num = 0;
		int clickNumValue = 4;
		currentIndex = 14;
		if(clickNum < 11) {
			currentIndex = 32;
			clickNumValue = 10;
			num = 1;
		} else if(clickNum < 16) {
			currentIndex = 47;
			clickNumValue = 15;
			num = 2;
		} else if(clickNum < 22) {
			currentIndex = 65;
			clickNumValue = 21;
			num = 3;
		}
		clickNum = clickNumValue;
		return num;
	}
	
	// 清除数据
		private void clearMediaPlayer() {
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
		}

		
		
}
