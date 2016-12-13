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
 * 日本 - 复习2
 * @author
 */
public class LevelAsia5_2Activity extends BaseActivity implements
		OnClickListener {
	/*-----------------基础----------------------*/
	private Context mContext;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer = null, mMediaPlayer2 = null;
	private boolean haveClicked = false;
	private MyVideoView video;
	private String path, pathImg;
	private VideoBiz biz;
	private boolean isCycle = false;
	private int startTime = 0;
	private int endTime = 0;
	private int clickNum = 0;// 用于判断是否是新的视频
	private boolean isClicked = true;
	private int currentIndex = 0;
	private int preservation = 0; // 按home键保存播放帧数
	private int first = 0;
	private RelativeLayout rlIv;

	/*-----------------不共用----------------------*/
	private int endIndex = 28;

	private int offset_time = 0; // 用于步步高平板延迟时间修正
	/*-----------------视图----------------------*/
	private ImageView bt_home, iv1, iv2, iv3, iv4, iv5, iv6, ivRead,
			ivWord1, ivWord2, ivWord3, ivWord4, ivWord5, ivWord6, ivShou, ivSmallPetal;
	private RelativeLayout rlNum;
	private TextView tvNum;
	private ImageView[] ivSuShi;
	private ImageView[] ivWord;
	private int tempClicknum = 0;
	private int multiple = 1;
	private boolean isInitialization = false;
	private String[] wordSelArray = new String[] {
			"ad", "an", "ell", "en", "et", "ill", "ob", "op"
	};
	private int[] indexArray = new int[] { 0, 1, 2, 3, 4, 5 };
	private int[] randA, randB;
	private String[] randSelArray;
	private int randValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asia_level5_2);

		initializationData();
		setView();
	}

	/*-----------------初始化----------------------*/
	/* 数据初始化 */
	private void initializationData() {
		mContext = LevelAsia5_2Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		isInitialization = false;
		path = ConstantEp.path_reading03 + "asia_level5_2.mp4/";
		pathImg = ConstantEp.path_reading03_images;
		multiple = 1;
		currentIndex = 0;// 5
		startTime = ConstantAsia.timeAsia5_2[currentIndex][0];
		endTime = ConstantAsia.timeAsia5_2[currentIndex][1];
		haveClicked = false;
		clickNum = 0;
		first = 0;
		offset_time = Tools.delayTime();
		endIndex = ConstantAsia.timeAsia5_2.length - 1;
	}
	
	private void initData() {
		// 0-6随机数
		randA = BaseCommon.getList(indexArray);
		// 随机获得8个字母里的4个
		randB = BaseCommon.interceptionArray(8, 4);
		// 获得随机数0-3
		randValue = BaseCommon.randData(4);
		// 6个元素
		String[] selArray = new String[6];
		selArray[0] = wordSelArray[randB[0]];
		selArray[1] = wordSelArray[randB[1]];
		selArray[2] = wordSelArray[randB[2]];
		selArray[3] = wordSelArray[randB[3]];
		selArray[4] = wordSelArray[randB[randValue]];
		selArray[5] = wordSelArray[randB[randValue]];
		randSelArray = new String[6];
		for (int i = 0; i < randSelArray.length; i++) {
			randSelArray[i] = selArray[randA[i]];
		}
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
		ivWord1 = (ImageView) findViewById(R.id.iv_word_1);
		ivWord2 = (ImageView) findViewById(R.id.iv_word_2);
		ivWord3 = (ImageView) findViewById(R.id.iv_word_3);
		ivWord4 = (ImageView) findViewById(R.id.iv_word_4);
		ivWord5 = (ImageView) findViewById(R.id.iv_word_5);
		ivWord6 = (ImageView) findViewById(R.id.iv_word_6);
		ivSmallPetal = (ImageView) findViewById(R.id.iv_small_petal);
		rlNum = (RelativeLayout) findViewById(R.id.rl_num);
		tvNum = (TextView) findViewById(R.id.tv_num);
		ivRead = (ImageView) findViewById(R.id.iv_read);

		ivSuShi = new ImageView[6];
		ivSuShi[0] = iv1;
		ivSuShi[1] = iv2;
		ivSuShi[2] = iv3;
		ivSuShi[3] = iv4;
		ivSuShi[4] = iv5;
		ivSuShi[5] = iv6;
		ivWord = new ImageView[6];
		ivWord[0] = ivWord1;
		ivWord[1] = ivWord2;
		ivWord[2] = ivWord3;
		ivWord[3] = ivWord4;
		ivWord[4] = ivWord5;
		ivWord[5] = ivWord6;
		for (int i = 0; i < ivSuShi.length; i++) {
			setViewPosition(ivSuShi[i], i);
			setViewPosition(ivWord[i], i);
			ivWord[i].setOnClickListener(this);
		}
		setViewPosition(rlNum, 6);
		setViewPosition(ivSmallPetal, 7);
		setViewPosition(ivRead, 8);
		ivRead.setOnClickListener(this);
		ivSmallPetal.setImageDrawable(BaseCommon.drawableChange(pathImg, "small_gift"));
		/*---------不共用--------播放开场提示音*/
		playMusic(MediaCommon.getAsia5_0Mp3(12));

		videoCycle(clickNum);
		biz.playVideo(video, path);
	}
	
	private void processCard() {
		initData();
		for(int i=0; i<ivWord.length; i++) {
			ivSuShi[i].setVisibility(View.VISIBLE);
			ivWord[i].setVisibility(View.VISIBLE);
			ivSuShi[i].setImageDrawable(BaseCommon.drawableChange(pathImg, "sushi_" + (i + 1)));
			ivWord[i].setImageDrawable(BaseCommon.drawableChange(pathImg, "sushi_" + randSelArray[i]));
		}
		playMusicOnly(ConstantEp.path_reading03 + "sound_" + wordSelArray[randB[randValue]] + ".mp3");
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAsia.asia_level5_2_position,
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
				if (ConstantAsia.timeAsia5_2[currentIndex][4] == 1 && isClicked) {// &&
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
				if (ConstantAsia.timeAsia5_2[currentIndex][4] == 0) {
					currentIndex = ConstantAsia.timeAsia5_2[currentIndex][3];
					if(currentIndex == 1) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(10));
					}
					isInitialization = true;
					if (currentIndex == 7) {
						if (tempClicknum < 3) {
							currentIndex = 3;
						}
						if(multiple == 3 && tempClicknum == 3) {
							currentIndex = 7;
						}
					}
				}
				if (currentIndex == endIndex || currentIndex >= 11) {
					handler.sendMessage(handler.obtainMessage(3));
					return;
				}
				startTime = ConstantAsia.timeAsia5_2[currentIndex][0]
						- offset_time;
				endTime = ConstantAsia.timeAsia5_2[currentIndex][1]
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
		playMusicOnly(ConstantEp.path_reading03 + "sound_" + wordSelArray[randB[randValue]] + ".mp3");
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
			startTime = ConstantAsia.timeAsia5_2[seekTo][0] - offset_time;
			endTime = ConstantAsia.timeAsia5_2[seekTo][1] - offset_time;

			video.seekTo(ConstantAsia.timeAsia5_2[seekTo][0] - offset_time);
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
					if(isInitialization) {
						isInitialization = false;
						processCard();
						rlIv.setVisibility(View.VISIBLE);
						tvNum.setText("0" + " / 3");
						if(tempClicknum == 0 && multiple == 1) {
							showBtnAndShou();
						}
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

	private void ivClick(int index) {
		// 判断是否正确
		if(!randSelArray[index].equals(wordSelArray[randB[randValue]])) { // 不对
			MediaCommon.playFuxiError(mContext);
			//动画 晃动
			CommonAnimation.shakeAnimation(mContext, ivWord[index]);
			CommonAnimation.shakeAnimation(mContext, ivSuShi[index]);
			return;
		}
		tempClicknum++;
		ivWord[index].setVisibility(View.INVISIBLE);
		ivSuShi[index].setVisibility(View.INVISIBLE);
		playword();
		if (tempClicknum >= 3) {
			if(tempClicknum == 3 && multiple != 3) {
				multiple++;
				tvNum.setText(tempClicknum + " / 3");
				tempClicknum = 0;
				rlIv.setVisibility(View.GONE);
			}
			if(multiple == 3 && tempClicknum ==3) {
				tvNum.setText((tempClicknum - 10) + " / 3");
				rlIv.setVisibility(View.GONE);
				currentIndex = 5;
			}
			clickExecute(ConstantAsia.timeAsia5_2[currentIndex][3]);
		} else {
			tvNum.setText(tempClicknum + " / 3");
		}
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
				ivClick(0);
			}
			return;
		}
		if (v == ivWord1) {
			ivClick(0);
		} else if (v == ivWord2) { 
			ivClick(1);
		} else if (v == ivWord3) {
			ivClick(2);
		} else if (v == ivWord4) {
			ivClick(3);
		} else if (v == ivWord5) {
			ivClick(4);
		} else if (v == ivWord6) {
			ivClick(5);
		} else if(v == ivRead) { // 重读
			playMusicOnly(ConstantEp.path_reading03 + "sound_" + wordSelArray[randB[randValue]] + ".mp3");
		}
	}

}
