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
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.ConstantAsia;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.Tools;
import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;

/**
 * 印度 - 学习
 * @author
 */
public class LevelAsia3_0Activity extends BaseActivity implements
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
	private ImageView bt_home, iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, ivCard1,
			ivCard2, ivCard3, ivCard4, ivShou, ivVacuum;
	private RelativeLayout rlNum;
	private TextView tvNum;
	private ImageView[] ivCleaner;
	private ImageView[] ivCard;
	private int tempClicknum = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asia_level3_0);

		initializationData();
		setView();
	}

	/*-----------------初始化----------------------*/
	/* 数据初始化 */
	private void initializationData() {
		mContext = LevelAsia3_0Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		path = ConstantEp.path_reading03 + "asia_level3_0.mp4/";

		currentIndex = 0;// 5
		startTime = ConstantAsia.timeAsia3_0[currentIndex][0];
		endTime = ConstantAsia.timeAsia3_0[currentIndex][1];
		haveClicked = false;
		clickNum = 0;
		first = 0;
		offset_time = Tools.delayTime();
		endIndex = ConstantAsia.timeAsia3_0.length - 1;
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
		iv7 = (ImageView) findViewById(R.id.iv7);
		iv8 = (ImageView) findViewById(R.id.iv8);
		ivVacuum = (ImageView) findViewById(R.id.iv_vacuum);
		rlNum = (RelativeLayout) findViewById(R.id.rl_num);
		tvNum = (TextView) findViewById(R.id.tv_num);
		rlCard = (RelativeLayout) findViewById(R.id.rl_card);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) findViewById(R.id.iv_card_4);

		ivCleaner = new ImageView[8];
		ivCleaner[0] = iv1;
		ivCleaner[1] = iv2;
		ivCleaner[2] = iv3;
		ivCleaner[3] = iv4;
		ivCleaner[4] = iv5;
		ivCleaner[5] = iv6;
		ivCleaner[6] = iv7;
		ivCleaner[7] = iv8;
		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		for (int i = 0; i < ivCleaner.length; i++) {
			setViewPosition(ivCleaner[i], i);
			ivCleaner[i].setVisibility(View.VISIBLE);
			ivCleaner[i].setOnClickListener(this);
		}
		for (int i = 0; i < ivCard.length; i++) {
			setViewPosition(ivCard[i], (i + 8));
			ivCard[i].setOnClickListener(this);
		}
		setViewPosition(rlNum, 14);
		setViewPosition(ivVacuum, 15);
		playMusic(MediaCommon.getAsia3_0Mp3(0));   //背景音乐播放
		/*---------不共用--------播放开场提示音*/
//		playMusicOnly(MediaCommon.getAsia3_0Mp3(1));

		videoCycle(clickNum);
		biz.playVideo(video, path);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAsia.asia_level3_0_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	/*-----------------视频播放及节点事件处理----------------------*/
	// 循环播放------------------------------------
	private void videoCycle(final int num) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				isCycle = true;
				System.out.println("videoCycle currentIndex :" + currentIndex);
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
				if (ConstantAsia.timeAsia3_0[currentIndex][4] == 1 && isClicked) {// &&
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
				System.out.println("try 1 currentIndex :" + currentIndex);
				if (ConstantAsia.timeAsia3_0[currentIndex][4] == 0) {
					currentIndex = ConstantAsia.timeAsia3_0[currentIndex][3];
					if(currentIndex == 1) {
						playMusicOnly(MediaCommon.getAsia3_0Mp3(1));
					}
					if (currentIndex == 9 || currentIndex == 11
							|| currentIndex == 13 || currentIndex == 15
							|| currentIndex == 17) {
						if (tempClicknum < 12) {
							currentIndex = 7;
						} else {
							currentIndex = 17;
						}
					}
				}
				System.out.println("try 2 currentIndex :" + currentIndex);

				if (currentIndex == endIndex || currentIndex >= 18) {
					handler.sendMessage(handler.obtainMessage(3));
					return;
				}
				startTime = ConstantAsia.timeAsia3_0[currentIndex][0]
						- offset_time;
				endTime = ConstantAsia.timeAsia3_0[currentIndex][1]
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
					// TODO Auto-generated method stub
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
		if (tempClicknum <= 8) {
			playMusicOnly(ConstantEp.path_reading03 + "flash_x.mp3");
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
			startTime = ConstantAsia.timeAsia3_0[seekTo][0] - offset_time;
			endTime = ConstantAsia.timeAsia3_0[seekTo][1] - offset_time;

			video.seekTo(ConstantAsia.timeAsia3_0[seekTo][0] - offset_time);
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
				if (currentIndex == 7) {
					rlCard.setVisibility(View.VISIBLE);
					if(tempClicknum == 8) {
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
		System.out.println("------- showBtnAndShou ------tempClicknum----" + tempClicknum);
		ivShou.setVisibility(View.VISIBLE);
		if(tempClicknum == 0){
			biz.setViewPositionNoSuoxiao(ivShou, 12, FixedPositionAsia.asia_level3_0_position, scaleQPW ,scaleQPH);
		}else if(tempClicknum == 8){
			biz.setViewPositionNoSuoxiao(ivShou, 13, FixedPositionAsia.asia_level3_0_position, scaleQPW ,scaleQPH);
		}
		CommonAnimation.startShouAnimation2(ivShou);
	}

	/* 消失手动画 */
	private void stopBtnAndShou() {
		ivShou.setVisibility(View.GONE);
		CommonAnimation.stopShouAnimation(ivShou);
	}

	private void ivClick(View view, int index) {
		tempClicknum++;
		view.setVisibility(View.INVISIBLE);
		System.out.println("tempClicknum :" + tempClicknum);
		playword();
		if (tempClicknum >= 8) {
			if(tempClicknum == 8) {
				tvNum.setText(tempClicknum + " / 8");
				delayHideIv();
			}
			currentIndex = index;
			rlCard.setVisibility(View.GONE);
			clickExecute(ConstantAsia.timeAsia3_0[currentIndex][3]);
		} else {
			tvNum.setText(tempClicknum + " / 8");
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
			if (tempClicknum == 0) {
				ivClick(iv1, 4);
			} else if(tempClicknum == 8) {
				ivClick(ivCard1, 8);
			}
			return;
		}
		if (v == iv1) {
			ivClick(iv1, 4);
		} else if (v == iv2) {
			ivClick(iv2, 4);
		} else if (v == iv3) {
			ivClick(iv3, 4);
		} else if (v == iv4) {
			ivClick(iv4, 4);
		} else if (v == iv5) {
			ivClick(iv5, 4);
		} else if (v == iv6) {
			ivClick(iv6, 4);
		} else if (v == iv7) {
			ivClick(iv7, 4);
		} else if (v == iv8) {
			ivClick(iv8, 4);
		} else if (v == ivCard1) { // fox
			ivClick(ivCard1, 8);
		} else if (v == ivCard2) { // box
			ivClick(ivCard2, 10);
		} else if (v == ivCard3) { // mix
			ivClick(ivCard3, 12);
		} else if (v == ivCard4) { // six
			ivClick(ivCard4, 14);
		}
	}

}
