package com.gst.move.test;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.data.FixedPositionAsia;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.ConstantAsia;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;

/**
 * 日本 - 学习
 * @author
 */
public class LevelAsia5_0Activity extends BaseActivity implements
		OnClickListener {
	/*-----------------基础----------------------*/
	private Context mContext;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer = null, mMediaPlayer2 = null;
	private boolean haveClicked = false;
	private MyVideoView video;
	private String path;
	private VideoBiz biz;
	private boolean isCycle = false;
	private int startTime = 0;
	private int endTime = 0;
	private int clickNum = 0;// 用于判断是否是新的视频
	private boolean isClicked = true;
	private int currentIndex = 0;
	private int preservation = 0; // 按home键保存播放帧数
	private int first = 0;
	private RelativeLayout rlIv, rlCard;

	/*-----------------不共用----------------------*/
	private int endIndex = 28;

	private int offset_time = 0; // 用于步步高平板延迟时间修正
	/*-----------------视图----------------------*/
	private ImageView bt_home, iv1, iv2, iv3, iv4, iv5, iv6, ivCard1,
			ivCard2, ivCard3, ivCard4, ivShou, ivSmallPetal;
	private RelativeLayout rlNum;
	private TextView tvNum;
	private ImageView[] ivPetal;
	private ImageView[] ivCard;
	private int tempClicknum = 0;
	private int multiple = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asia_level5_0);

		initializationData();
		setView();
	}

	/*-----------------初始化----------------------*/
	/* 数据初始化 */
	private void initializationData() {
		mContext = LevelAsia5_0Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		path = ConstantEp.path_reading03 + "asia_level5_0.mp4/";
		multiple = 1;
		currentIndex = 0;// 5
		startTime = ConstantAsia.timeAsia5_0[currentIndex][0];
		endTime = ConstantAsia.timeAsia5_0[currentIndex][1];
		haveClicked = false;
		clickNum = 0;
		first = 0;
		offset_time = Tools.delayTime();
		endIndex = ConstantAsia.timeAsia5_0.length - 1;
		
	}

	/* 视图初始化 */
	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		bt_home = (ImageView) findViewById(R.id.bt_home);
		biz.setViewPositionNoSuoxiao(bt_home, 0, FixedPosition.common_position,
				scaleQPW, scaleQPH);
		bt_home.setOnClickListener(this);
		ivShou = (ImageView) findViewById(R.id.iv_shou);
		ivShou.setOnClickListener(this);
		rlIv = (RelativeLayout) findViewById(R.id.rl_iv);
		iv1 = (ImageView) findViewById(R.id.iv1);
		iv2 = (ImageView) findViewById(R.id.iv2);
		iv3 = (ImageView) findViewById(R.id.iv3);
		iv4 = (ImageView) findViewById(R.id.iv4);
		iv5 = (ImageView) findViewById(R.id.iv5);
		iv6 = (ImageView) findViewById(R.id.iv6);
		ivSmallPetal = (ImageView) findViewById(R.id.iv_small_petal);
		rlNum = (RelativeLayout) findViewById(R.id.rl_num);
		tvNum = (TextView) findViewById(R.id.tv_num);
		rlCard = (RelativeLayout) findViewById(R.id.rl_card);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) findViewById(R.id.iv_card_4);

		ivPetal = new ImageView[6];
		ivPetal[0] = iv1;
		ivPetal[1] = iv2;
		ivPetal[2] = iv3;
		ivPetal[3] = iv4;
		ivPetal[4] = iv5;
		ivPetal[5] = iv6;
		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		for (int i = 0; i < ivPetal.length; i++) {
			setViewPosition(ivPetal[i], i);
			ivPetal[i].setVisibility(View.VISIBLE);
			ivPetal[i].setOnClickListener(this);
		}
		for (int i = 0; i < ivCard.length; i++) {
			setViewPosition(ivCard[i], (i + 6));
			ivCard[i].setOnClickListener(this);
		}
		setViewPosition(rlNum, 14);
		setViewPosition(ivSmallPetal, 15);
		/*---------不共用--------播放开场提示音*/
		playMusic(MediaCommon.getAsia5_0Mp3(0));
		ivSmallPetal.setImageDrawable(BaseCommon.drawableChange(ConstantEp.path_reading03_images, "petal"));
		videoCycle(clickNum);
		biz.playVideo(video, path);
		processIv("petal_ob");
		processCard("cup");
	}
	
	private void processIv(String resValue) {
		for(int i=0; i<ivPetal.length; i++) {
			ivPetal[i].setVisibility(View.VISIBLE);
			ivPetal[i].setImageDrawable(BaseCommon.drawableChange(ConstantEp.path_reading03_images, resValue));
		}
	}
	
	private void processCard(String resValue) {
		for(int i=0; i<ivCard.length; i++) {
			ivCard[i].setVisibility(View.VISIBLE);
			ivCard[i].setImageDrawable(BaseCommon.drawableChange(ConstantEp.path_reading03_images, resValue));
			if(multiple == 2) {
				setViewPosition(ivCard[i], (i + 10));
			}
		}
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAsia.asia_level5_0_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	/*-----------------视频播放及节点事件处理----------------------*/
	// 循环播放------------------------------------
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
				Thread.sleep(150);
				if (tem_num != clickNum)
					break;
				if (!isCycle || video == null)
					break;
				currentPos = video.getCurrentPosition(); // 空指针null
				if (ConstantAsia.timeAsia5_0[currentIndex][4] == 1 && isClicked) {// &&
																					// currentIndex
																					// !=
																					// clickIndex
					isClicked = false;
					handler.sendMessage(handler.obtainMessage(0, currentIndex));
				}
				if (currentPos >= endTime) {
					break;
				}
			} catch (Exception e) {

			}
		} while (currentPos < endTime);
		try {
			if (!video.isPlaying()) {// 如果不在播放状态，则停止更新
				return;
			}
			if (tem_num == clickNum) {
				if (ConstantAsia.timeAsia5_0[currentIndex][4] == 0) {
					currentIndex = ConstantAsia.timeAsia5_0[currentIndex][3];
					if(currentIndex == 1) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(5));
					} else if(currentIndex == 7 && tempClicknum == 6) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(6));
					} else if(currentIndex == 9 && tempClicknum == 6) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(7));
					} else if(currentIndex == 33 && tempClicknum == 16) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(8));
					} else if(currentIndex == 35 && tempClicknum == 16) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(9));
					}
					if (currentIndex == 7) {
						if (tempClicknum < 6) {
							currentIndex = 3;
						}
					} else if(currentIndex == 9 || currentIndex == 15 || 
							currentIndex == 19 || currentIndex == 23 || currentIndex == 27) {
						if(tempClicknum < 10) {
							currentIndex = 9;
						} else {
							currentIndex = 27;
						}
					} else if(currentIndex == 33) {
						if(tempClicknum < 16) {
							currentIndex = 29;
						}
					} else if(currentIndex == 35 || currentIndex == 41 || 
							currentIndex == 45 || currentIndex == 49 || currentIndex == 53) {
						if(tempClicknum < 20) {
							currentIndex = 35;
						} else {
							currentIndex = 53;
						}
					}
				}
				if (currentIndex == endIndex || currentIndex >= 54) {
					handler.sendMessage(handler.obtainMessage(3));
					return;
				}
				startTime = ConstantAsia.timeAsia5_0[currentIndex][0]
						- offset_time;
				endTime = ConstantAsia.timeAsia5_0[currentIndex][1]
						- offset_time;
				handler.sendMessage(handler.obtainMessage(2, tem_num));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void clearMediaVideo() {
		isCycle = false;
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
		if (null != video) {
			video.stopPlayback();
			video = null;
		}
		if (mMediaPlayer2 != null) {
			mMediaPlayer2.release();
			mMediaPlayer2 = null;
		}
	}

	private void pauseMediaVideo() {
		try {
			if (null != video) {
				video.pause();
			}
			if (mMediaPlayer != null) {
				mMediaPlayer.pause();
			}
			if (mMediaPlayer2 != null) {
				mMediaPlayer2.pause();
			}

		} catch (Exception e) {

		}

	}

	private void returnHome() {
		clearMediaVideo();
		this.finish();
	}

	/* 声音播放 */
	public void playMusic(String path) {
		try {
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
			mMediaPlayer
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

						public void onCompletion(MediaPlayer arg0) {
							// mMediaPlayer.release();
						}
					});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void playMusicOnly(String path) {
		try {
			if (mMediaPlayer2 != null) {
				mMediaPlayer2.release();
				mMediaPlayer2 = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			mMediaPlayer2 = new MediaPlayer();
			/* 重置MediaPlayer */
			mMediaPlayer2.reset();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			/* 设置要播放的文件的路径 */
			mMediaPlayer2.setDataSource(path);
			mMediaPlayer2.setLooping(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			/* 准备播放 */
			mMediaPlayer2.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			/* 开始播放 */
			mMediaPlayer2.start();
			mMediaPlayer2
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
						public void onCompletion(MediaPlayer arg0) {
							if (mMediaPlayer2 != null) {
								mMediaPlayer2.release();
								mMediaPlayer2 = null;
							}
						}
					});
			mMediaPlayer2.setOnErrorListener(new MediaPlayer.OnErrorListener() {

				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					if (mMediaPlayer2 != null) {
						mMediaPlayer2.release();
						mMediaPlayer2 = null;
					}
					return false;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void playword() {
		if (tempClicknum <= 6) {
			playMusicOnly(ConstantEp.path_reading03 + "sound_ob.mp3");
		} else if(tempClicknum >= 11 && tempClicknum <= 16) {
			playMusicOnly(ConstantEp.path_reading03 + "sound_ell.mp3");
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setKeepScreenOn(true);

		if (first > 0 && null != video) {
			video.start();
			// 跳到指定的帧数
			video.seekTo(preservation);
			// 播放循环
			videoCycle(clickNum);
		}
		if (null != mMediaPlayer && !mMediaPlayer.isPlaying()) {
			mMediaPlayer.start();
		}
		first++;
	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
			// 暂停时保存播放帧数
			if (video != null) {
				int currentPos = video.getCurrentPosition();
				preservation = currentPos;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getWindow().getDecorView().setKeepScreenOn(false);
		pauseMediaVideo();
		MediaCommon.pauseMediaplay();
	}

	@Override
	protected void onDestroy() {
		isCycle = false;
		super.onDestroy();
		// 帧数清零
		preservation = 0;
		clearMediaVideo();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			returnHome();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_HOME) {
			// isClicked = true;
			pauseMediaVideo();
		}

		return super.onKeyDown(keyCode, event);
	}

	// 点击执行的功能---------------------------------------------------------------------------------
	private void clickExecute(int seekTo) {
		try {
			isCycle = false;
			clickNum++;
			startTime = ConstantAsia.timeAsia5_0[seekTo][0] - offset_time;
			endTime = ConstantAsia.timeAsia5_0[seekTo][1] - offset_time;

			video.seekTo(ConstantAsia.timeAsia5_0[seekTo][0] - offset_time);
			isClicked = true;
			currentIndex = seekTo;

			videoCycle(clickNum);
			isCycle = true;
		} catch (Exception e) {
		}
	}

	/*-----------------Handler onClick----------------------*/
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				isClicked = false;
				haveClicked = false;
				break;
			case 2:
				haveClicked = false;
				int num = (Integer) msg.obj;
				if (currentIndex == 3) {
					rlIv.setVisibility(View.VISIBLE);
					if(tempClicknum == 0) {
						showBtnAndShou();
					}
				}
				if (currentIndex == 9) {
					rlCard.setVisibility(View.VISIBLE);
					if(tempClicknum == 6) {
						showBtnAndShou();
					}
				}
				if(currentIndex == 29) {
					multiple = 2;
					rlIv.setVisibility(View.VISIBLE);
					if(tempClicknum == 10) {
						processIv("petal_ell");
						tvNum.setText("0" + " / 6");
						showBtnAndShou();
					}
				}
				if(currentIndex == 35) {
					rlCard.setVisibility(View.VISIBLE);
					if(tempClicknum == 16) {
						processCard("baskets");
						showBtnAndShou();
					}
				}
				if (video != null && clickNum == num) {
					video.seekTo(startTime);
					videoCycle(num);
				}
				break;
			case 3:
				// 只有国家学完才会进这个界面，所以飞行棋不计算在内
				// startActivity(new Intent(mContext,
				// CountryPassOverActivity.class).putExtra("level",
				// 1).putExtra("countryIndex", 12));
				finish();
				break;
			case 4:
				rlIv.setVisibility(View.GONE);
				break;
			default:
				break;
			}
		}

	};
	
	/* 显示手动画 */
	private void showBtnAndShou() {
		ivShou.setVisibility(View.VISIBLE);
		if(tempClicknum == 0){
			biz.setViewPositionNoSuoxiao(ivShou, 18, FixedPositionAsia.asia_level5_0_position, scaleQPW ,scaleQPH);
		}else if(tempClicknum == 8){
			biz.setViewPositionNoSuoxiao(ivShou, 19, FixedPositionAsia.asia_level5_0_position, scaleQPW ,scaleQPH);
		}
		CommonAnimation.startShouAnimation(ivShou);
	}

	/* 消失手动画 */
	private void stopBtnAndShou() {
		ivShou.setVisibility(View.GONE);
		CommonAnimation.stopShouAnimation(ivShou);
	}

	private void ivClick(View view, int index) {
		tempClicknum++;
		view.setVisibility(View.INVISIBLE);
		rlIv.setVisibility(View.GONE);
		playword();
		clickExecute(ConstantAsia.timeAsia5_0[currentIndex][3]);
		if (tempClicknum >= 6) {
			if(tempClicknum == 6) {
				tvNum.setText(tempClicknum + " / 6");
				delayHideIv();
			}
			if(multiple == 2 && tempClicknum > 10 && tempClicknum <= 16) {
				tvNum.setText((tempClicknum - 10) + " / 6");
				delayHideIv();
			}
			currentIndex = index;
			rlCard.setVisibility(View.GONE);
			clickExecute(ConstantAsia.timeAsia5_0[currentIndex][3]);
		} else {
			tvNum.setText(tempClicknum + " / 6");
		}
	}
	
	private void delayHideIv() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(500);
					handler.sendMessage(handler.obtainMessage(4));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		if (v == bt_home) {
			returnHome();
		}
		if (haveClicked)
			return;
		stopBtnAndShou();
		haveClicked = true;
		if (v == ivShou) {
			if (tempClicknum == 0 || tempClicknum == 10) {
				ivClick(iv1, multiple == 1 ? 4 : 30);
			} else if(tempClicknum == 6 || tempClicknum == 16) {
				ivClick(ivCard1, multiple == 1 ? 10 : 36);
			}
			return;
		}
		if (v == iv1) {
			ivClick(iv1, multiple == 1 ? 4 : 30);
		} else if (v == iv2) { 
			ivClick(iv2, multiple == 1 ? 4 : 30);
		} else if (v == iv3) {
			ivClick(iv3, multiple == 1 ? 4 : 30);
		} else if (v == iv4) {
			ivClick(iv4, multiple == 1 ? 4 : 30);
		} else if (v == iv5) {
			ivClick(iv5, multiple == 1 ? 4 : 30);
		} else if (v == iv6) {
			ivClick(iv6, multiple == 1 ? 4 : 30);
		} else if (v == ivCard1) { // cob  well
			ivClick(ivCard1, multiple == 1 ? 10 : 36);
		} else if (v == ivCard2) { // sob  tell
			ivClick(ivCard2, multiple == 1 ? 14 : 40);
		} else if (v == ivCard3) { // mob  fell
			ivClick(ivCard3, multiple == 1 ? 18 : 44);
		} else if (v == ivCard4) { // job  bell
			ivClick(ivCard4, multiple == 1 ? 22 : 48);
		}
	}

}
