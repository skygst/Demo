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
 * 印度 - 复习2
 * 
 * @author
 */
public class LevelAsia3_2Activity extends BaseActivity implements
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
	private RelativeLayout rlNum, rlCard;
	private int offset_time = 0; // 用于步步高平板延迟时间修正
	/*-----------------视图----------------------*/
	private ImageView bt_home, ivDrum1,
			ivDrum2, ivDrum3, ivDrum4, ivDrum5, ivDrum6, ivDrum7, ivDrum8,
			ivDrum9, ivDrumNum;
	private TextView tvNum;
	private ImageView[] ivDrum;
	private int tempClicknum = 0;
	private int[] randDrum;
	private boolean isFirstPlayFinish = false;
	private String[] drumId = new String[] {
	"box", "fox", "mix", "six", "bus", "nut", "fat"};
	private String[] extraId = new String[] {
			"bus", "nut", "fat"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asia_level3_2);

		initializationData();
		setView();
	}

	/*-----------------初始化----------------------*/
	/* 数据初始化 */
	private void initializationData() {
		mContext = LevelAsia3_2Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		path = ConstantEp.path_reading03 + "asia_level3_2.mp4/";
		isFirstPlayFinish = false;
		currentIndex = 0;// 5
		startTime = ConstantAsia.timeAsia3_2[currentIndex][0];
		endTime = ConstantAsia.timeAsia3_2[currentIndex][1];
		haveClicked = false;
		clickNum = 0;
		first = 0;
		offset_time = Tools.delayTime();
//		randDrum = BaseCommon.getList(9);
//		for (int i = 0; i < randSound.length; i++) {
//			System.out.println("randSound[" + i + "] :" + randSound[i]);
//		}
	}

	/* 视图初始化 */
	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		bt_home = (ImageView) findViewById(R.id.bt_home);
		biz.setViewPositionNoSuoxiao(bt_home, 0, FixedPosition.common_position,
				scaleQPW, scaleQPH);
		bt_home.setOnClickListener(this);
		rlNum = (RelativeLayout) findViewById(R.id.rl_num);
		tvNum = (TextView) findViewById(R.id.tv_num);

		rlCard = (RelativeLayout) findViewById(R.id.rl_card);
		ivDrum1 = (ImageView) findViewById(R.id.iv_drum_1);
		ivDrum2 = (ImageView) findViewById(R.id.iv_drum_2);
		ivDrum3 = (ImageView) findViewById(R.id.iv_drum_3);
		ivDrum4 = (ImageView) findViewById(R.id.iv_drum_4);
		ivDrum5 = (ImageView) findViewById(R.id.iv_drum_5);
		ivDrum6 = (ImageView) findViewById(R.id.iv_drum_6);
		ivDrum7 = (ImageView) findViewById(R.id.iv_drum_7);
		ivDrum8 = (ImageView) findViewById(R.id.iv_drum_8);
		ivDrum9 = (ImageView) findViewById(R.id.iv_drum_9);

		ivDrumNum = (ImageView) findViewById(R.id.iv_drum_num);

		ivDrum = new ImageView[9];
		ivDrum[0] = ivDrum1;
		ivDrum[1] = ivDrum2;
		ivDrum[2] = ivDrum3;
		ivDrum[3] = ivDrum4;
		ivDrum[4] = ivDrum5;
		ivDrum[5] = ivDrum6;
		ivDrum[6] = ivDrum7;
		ivDrum[7] = ivDrum8;
		ivDrum[8] = ivDrum9;

		for (int i = 0; i < ivDrum.length; i++) {
			setViewPosition(ivDrum[i], i);
			ivDrum[i].setOnClickListener(this);
		}
		setViewPosition(rlNum, 18);
		setViewPosition(ivDrumNum, 19);

		/*---------不共用--------播放开场提示音*/
//		playMusicOnly(MediaCommon.getAsia3_0Mp3(4));
		playMusic(MediaCommon.getAsia3_0Mp3(7));   //背景音乐播放
		
		videoCycle(clickNum);
		biz.playVideo(video, path);
		refreshCard();
	}

	// 显示点击的卡片
	private void refreshCard() {
		randDrum = BaseCommon.getList(9);
		int[] rand = BaseCommon.getList(3);
		String[] cardId = new String[9];
		for(int i=0; i<drumId.length; i++) {
			cardId[i] = drumId[i];
		}
		cardId[7] = extraId[rand[0]];
		cardId[8] = extraId[rand[1]];
		for (int i = 0; i < ivDrum.length; i++) {
			int resId = new BaseCommon().getImageId(mContext,
					"drum_" + cardId[randDrum[i]]);
			ivDrum[i].setImageResource(resId);
			ivDrum[i].setVisibility(View.VISIBLE);
		}
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAsia.asia_level3_2_position,
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
				if (ConstantAsia.timeAsia3_2[currentIndex][4] == 1 && isClicked) {// &&
																					// currentIndex
																					// !=
																					// clickIndex
					isClicked = false;
					handler.sendMessage(handler.obtainMessage(0, num));
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
				if (ConstantAsia.timeAsia3_2[currentIndex][4] == 0) {
					currentIndex = ConstantAsia.timeAsia3_2[currentIndex][3];
					if(currentIndex == 1) {
						playMusicOnly(MediaCommon.getAsia3_0Mp3(4));
					}
				}
				if (currentIndex >= 6) {
					handler.sendMessage(handler.obtainMessage(3));
					return;
				}
				startTime = ConstantAsia.timeAsia3_2[currentIndex][0]
						- offset_time;
				endTime = ConstantAsia.timeAsia3_2[currentIndex][1]
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
			e.printStackTrace();
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
							if (tempClicknum == 0 && !isFirstPlayFinish) {
								isFirstPlayFinish = true;
								playMusicOnly(MediaCommon.getAsia3_0Mp3(5));
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
			startTime = ConstantAsia.timeAsia3_2[seekTo][0] - offset_time;
			endTime = ConstantAsia.timeAsia3_2[seekTo][1] - offset_time;

			video.seekTo(ConstantAsia.timeAsia3_2[seekTo][0] - offset_time);
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
				System.out.println("++++++++++++ case 0 +++++++++++");
				int num2 = (Integer) msg.obj;
				if (num2 != clickNum) {
					isClicked = true;
					break;
				}
				System.out.println("handler currentIndex :" + currentIndex);
				System.out.println("----------- case 0 ------------------");
				if (currentIndex == 3 || currentIndex == 7
						|| currentIndex == 11 || currentIndex == 15
						|| currentIndex == 19 || currentIndex == 23
						|| currentIndex == 27 || currentIndex == 31) {
					showView(View.VISIBLE, View.VISIBLE, View.GONE);
					refreshCard();
				}
				break;
			case 2:
				haveClicked = false;
				int num = (Integer) msg.obj;
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
				rlCard.setVisibility(View.GONE);
				clickExecute(ConstantAsia.timeAsia3_2[currentIndex][3]);
				break;
			case 5:
				refreshCard();
				break;
			default:
				break;
			}
		}

	};

	private void showView(int cardView, int lailaiView, int sisterView) {
		rlCard.setVisibility(cardView);
	}

	private void ivClick(View view, int index) {
		System.out.println("tempClicknum :" + tempClicknum);
		// 判断是否答对了
		if (tempClicknum < 12) {
			int clickId = randDrum[index];
			System.out.println("ivClick clickId :" + clickId);
			boolean isCorrect = false;
			if (clickId < 4) {
				isCorrect = true;
			}
			if (isCorrect) { // 答对了
				tempClicknum++;
				//动画 翻牌
				CommonAnimation.flopAnimation(mContext,view);
				// rlCard.setVisibility(View.GONE);
				playMusicOnly(ConstantEp.path_reading03 + "flash_" + drumId[randDrum[index]] +".mp3");
				tvNum.setText(tempClicknum + " / 12");
				if(tempClicknum == 4 || tempClicknum == 8 || tempClicknum == 12) {
					if(tempClicknum == 12) {
						delayGone(true);
					} else {
						delayGone(false);
					}
				}
			} else { // 答错了
				MediaCommon.playFuxiError(mContext);
				//动画 晃动
				CommonAnimation.shakeAnimation(mContext,view);
				
			}
		}
	}

	private void delayGone(final boolean isFinish) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					if(isFinish) {
						handler.sendMessage(handler.obtainMessage(4));
					} else {
						handler.sendMessage(handler.obtainMessage(5));
					}
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
		haveClicked = true;
		if (v == ivDrum1) {
			ivClick(ivDrum1, 0);
		} else if (v == ivDrum2) {
			ivClick(ivDrum2, 1);
		} else if (v == ivDrum3) {
			ivClick(ivDrum3, 2);
		} else if (v == ivDrum4) {
			ivClick(ivDrum4, 3);
		}  else if (v == ivDrum5) {
			ivClick(ivDrum5, 4);
		} else if (v == ivDrum6) {
			ivClick(ivDrum6, 5);
		} else if (v == ivDrum7) {
			ivClick(ivDrum7, 6);
		}  else if (v == ivDrum8) {
			ivClick(ivDrum8, 7);
		} else if (v == ivDrum9) {
			ivClick(ivDrum9, 8);
		}
	}

}
