package com.gst.move.test;

import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
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
import com.example.location.biz.LayoutParameters;
import com.gst.move.R;
import com.gst.move.R.drawable;
import com.gst.move.R.id;
import com.gst.move.R.layout;

/**
 * 印度 - 复习1
 * 
 * @author
 */
public class LevelAsia3_1Activity extends BaseActivity implements
		OnClickListener, OnTouchListener {
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
	private RelativeLayout rlNum, rlLaiLai, rlSister, rlCard;
	private int offset_time = 0; // 用于步步高平板延迟时间修正
	/*-----------------视图----------------------*/
	private ImageView bt_home, ivAnswer1, ivAnswer2, ivAnswer3, ivAnswer4,
			ivCard1, ivCard2, ivCard3, ivCard4, ivReader, ivCloud, ivWordBg, ivWord;
	private ImageView ivSisterCard, ivSisterWhiteBox1, ivSisterWhiteBox2,
			ivSisterWhiteBox3, ivSisterCard1, ivSisterCard2, ivSisterCard3,
			ivSisterBottomWord1, ivSisterBottomWord2, ivSisterBottomWord3;
	private TextView tvNum;
	private ImageView[] ivCard, ivAnswer, ivSisterWord, ivSisterBoxCard;
	private int tempClicknum = 0;
	private int[] imgId = new int[] { R.drawable.card_1_box,
			R.drawable.card_1_fox, R.drawable.card_1_mix, R.drawable.card_1_six };
	private int[] rand, randCard, randSound;
	private boolean isFirstPlayFinish = false;
	private String[] cardSound = new String[] { // 随机读的卡片读音
	"box", "fox", "mix", "six", "box", "fox", "mix", "six" };
	private String[] sisterCardId = new String[] { // 随机读的卡片读音
	"box", "fox", "mix", "six" };
	private String[] wordArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asia_level3_1);

		initializationData();
		setView();
	}

	/*-----------------初始化----------------------*/
	/* 数据初始化 */
	private void initializationData() {
		mContext = LevelAsia3_1Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		tempClicknum = 0;
		biz = new VideoBiz();
		path = ConstantEp.path_reading03 + "asia_level3_1.mp4/";
		isFirstPlayFinish = false;
		currentIndex = 0;// 5
		selectNum = 0;
		startTime = ConstantAsia.timeAsia3_1[currentIndex][0];
		endTime = ConstantAsia.timeAsia3_1[currentIndex][1];
		haveClicked = false;
		clickNum = 0;
		first = 0;
		offset_time = Tools.delayTime();
		randSound = BaseCommon.getList(8);
	}

	/* 视图初始化 */
	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		bt_home = (ImageView) findViewById(R.id.bt_home);
		biz.setViewPositionNoSuoxiao(bt_home, 0, FixedPosition.common_position,
				scaleQPW, scaleQPH);
		bt_home.setOnClickListener(this);
		rlLaiLai = (RelativeLayout) findViewById(R.id.rl_lailai);
		rlSister = (RelativeLayout) findViewById(R.id.rl_sister);
		rlNum = (RelativeLayout) findViewById(R.id.rl_num);
		tvNum = (TextView) findViewById(R.id.tv_num);

		rlCard = (RelativeLayout) findViewById(R.id.rl_card);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) findViewById(R.id.iv_card_4);

		ivAnswer1 = (ImageView) findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) findViewById(R.id.iv_answer_2);
		ivAnswer3 = (ImageView) findViewById(R.id.iv_answer_3);
		ivAnswer4 = (ImageView) findViewById(R.id.iv_answer_4);
		ivWordBg = (ImageView) findViewById(R.id.iv_word_bg);
		ivWord = (ImageView) findViewById(R.id.iv_word);

		ivReader = (ImageView) findViewById(R.id.iv_reader);
		ivCloud = (ImageView) findViewById(R.id.iv_cloud);

		ivSisterCard = (ImageView) findViewById(R.id.iv_sister_card);
		ivSisterWhiteBox1 = (ImageView) findViewById(R.id.iv_sister_white_box_1);
		ivSisterWhiteBox2 = (ImageView) findViewById(R.id.iv_sister_white_box_2);
		ivSisterWhiteBox3 = (ImageView) findViewById(R.id.iv_sister_white_box_3);
		ivSisterCard1 = (ImageView) findViewById(R.id.iv_sister_white_box_card_1);
		ivSisterCard2 = (ImageView) findViewById(R.id.iv_sister_white_box_card_2);
		ivSisterCard3 = (ImageView) findViewById(R.id.iv_sister_white_box_card_3);
		ivSisterBottomWord1 = (ImageView) findViewById(R.id.iv_sister_white_box_bottom_card_1);
		ivSisterBottomWord2 = (ImageView) findViewById(R.id.iv_sister_white_box_bottom_card_2);
		ivSisterBottomWord3 = (ImageView) findViewById(R.id.iv_sister_white_box_bottom_card_3);

		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		ivAnswer = new ImageView[4];
		ivAnswer[0] = ivAnswer1;
		ivAnswer[1] = ivAnswer2;
		ivAnswer[2] = ivAnswer3;
		ivAnswer[3] = ivAnswer4;
		ivSisterWord = new ImageView[3];
		ivSisterWord[0] = ivSisterBottomWord1;
		ivSisterWord[1] = ivSisterBottomWord2;
		ivSisterWord[2] = ivSisterBottomWord3;
		ivSisterBoxCard = new ImageView[3];
		ivSisterBoxCard[0] = ivSisterCard1;
		ivSisterBoxCard[1] = ivSisterCard2;
		ivSisterBoxCard[2] = ivSisterCard3;

		setViewPosition(ivReader, 0);
		for (int i = 0; i < ivCard.length; i++) {
			setViewPosition(ivCard[i], (i + 1));
			ivCard[i].setOnClickListener(this);
//			setViewPosition(ivAnswer[i], (i + 5));
			setViewPosition(ivAnswer[i], (i + 1));
		}
		ivReader.setOnClickListener(this);
		setViewPosition(rlNum, 9);
		setViewPosition(ivCloud, 10);
		setViewPosition(ivWordBg, 11);
		setViewPosition(ivWord, 11);

		setViewPosition(ivSisterCard, 12);
		setViewPosition(ivSisterWhiteBox1, 13);
		setViewPosition(ivSisterWhiteBox2, 14);
		setViewPosition(ivSisterWhiteBox3, 15);
		setViewPosition(ivSisterCard1, 16);
		setViewPosition(ivSisterCard2, 17);
		setViewPosition(ivSisterCard3, 18);

		for (int i = 0; i < ivSisterWord.length; i++) {
			ivSisterWord[i].setOnTouchListener(this);
			setViewPosition(ivSisterWord[i], (19 + i));
		}
		playMusic(MediaCommon.getAsia3_0Mp3(6));   //背景音乐播放

		videoCycle(clickNum);
		biz.playVideo(video, path);
		refreshCard();
	}

	// 显示点击的卡片
	private void refreshCard() {
		randCard = BaseCommon.getList(4);
		for (int i = 0; i < ivCard.length; i++) {
			ivCard[i].setImageResource(imgId[randCard[i]]);
			ivAnswer[i].setImageResource(0);
		}
	}

	// 显示拖动的卡片
	private void refreshSisterCard() {
		rand = BaseCommon.getList(3);
		// tempClicknum = 8;
		int resId = new BaseCommon().getImageId(mContext, "sister_"
				+ sisterCardId[randCard[(tempClicknum - 8)]]);
		ivSisterCard.setImageResource(resId);
		tvNum.setText((tempClicknum - 8) + " / 4");
		wordArray = new String[3];
		for (int i = 0; i < ivSisterWord.length; i++) {
			ivSisterBoxCard[i].setImageResource(0);
			ivSisterWord[i].setEnabled(true);
			ivSisterWord[i].setVisibility(View.VISIBLE);
			setViewPosition(ivSisterWord[i], (19 + i));
		}

		if (tempClicknum < 12) {
			String word = sisterCardId[randCard[(tempClicknum - 8)]];
			wordArray[0] = word.substring(0, 1);
			wordArray[1] = word.substring(1, 2);
			wordArray[2] = word.substring(2, 3);
			for (int i = 0; i < ivSisterWord.length; i++) {
				ivSisterWord[i].setImageResource(new BaseCommon().getImageId(
						mContext, "word_" + wordArray[rand[i]]));
			}
		}
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAsia.asia_level3_1_position,
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
				if (ConstantAsia.timeAsia3_1[currentIndex][4] == 1 && isClicked) {// &&
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
				if (ConstantAsia.timeAsia3_1[currentIndex][4] == 0) {
					currentIndex = ConstantAsia.timeAsia3_1[currentIndex][3];
					if(currentIndex == 1) {
						playMusicOnly(MediaCommon.getAsia3_0Mp3(2));
					}
				}
				if (currentIndex >= 50) {
					handler.sendMessage(handler.obtainMessage(3));
					return;
				}
				startTime = ConstantAsia.timeAsia3_1[currentIndex][0]
						- offset_time;
				endTime = ConstantAsia.timeAsia3_1[currentIndex][1]
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
								playMusicOnly(ConstantEp.path_reading03
										+ "flash_" + cardSound[randSound[0]]
										+ ".mp3");
							}
							if (tempClicknum == 8 && !isFirstPlayFinish) {
								isFirstPlayFinish = true;
								playMusicOnly(ConstantEp.path_reading03
										+ "flash_" + sisterCardId[randCard[0]]
										+ ".mp3");
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
			startTime = ConstantAsia.timeAsia3_1[seekTo][0] - offset_time;
			endTime = ConstantAsia.timeAsia3_1[seekTo][1] - offset_time;

			video.seekTo(ConstantAsia.timeAsia3_1[seekTo][0] - offset_time);
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
				int num2 = (Integer) msg.obj;
				if (num2 != clickNum) {
					isClicked = true;
					break;
				}
				// TODO 测试
				// ----------------------
//				if(tempClicknum == 0) {
//					tempClicknum = 8;
//					currentIndex = 35;
//				}
				// ----------------------
				if (currentIndex == 3 || currentIndex == 7
						|| currentIndex == 11 || currentIndex == 15
						|| currentIndex == 19 || currentIndex == 23
						|| currentIndex == 27 || currentIndex == 31) {
					showView(View.VISIBLE, View.VISIBLE, View.GONE);
					if (tempClicknum < 8) {
						int resId = new BaseCommon().getImageId(mContext,
								"card_bottom_"
										+ cardSound[randSound[tempClicknum]]);
						ivWord.setImageResource(resId);
						CommonAnimation.startShouAnimation2(ivWord);
					}
					refreshCard();
					if (tempClicknum > 0 && tempClicknum < 8) {
						playMusicOnly(ConstantEp.path_reading03 + "flash_"
								+ cardSound[randSound[tempClicknum]] + ".mp3");
					}
				}
				if (currentIndex == 35 || currentIndex == 39
						|| currentIndex == 43 || currentIndex == 47) {
					// TODO 显示狮小妹吃飞饼
					showView(View.VISIBLE, View.GONE, View.VISIBLE);
					if (tempClicknum == 8) {
						isFirstPlayFinish = false;
						playMusicOnly(MediaCommon.getAsia3_0Mp3(3));
					} else {
						playMusicOnly(ConstantEp.path_reading03 + "flash_"
								+ sisterCardId[randCard[(tempClicknum - 8)]]
								+ ".mp3");
					}
					refreshSisterCard();
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
				clickExecute(ConstantAsia.timeAsia3_1[currentIndex][3]);
				break;
			case 5:
				String w1 = (String) msg.obj;
				playMusicOnly(ConstantEp.path_reading03 + "flash_" + w1 + "_sound.mp3");
				CommonAnimation.enlargeAnimation2(ivSisterCard1, 600);
				break;
			case 6:
				String w2 = (String) msg.obj;
				playMusicOnly(ConstantEp.path_reading03 + "flash_" + w2 + "_sound.mp3");
				CommonAnimation.enlargeAnimation2(ivSisterCard2, 600);
				break;
			case 7:
				String w3 = (String) msg.obj;
				playMusicOnly(ConstantEp.path_reading03 + "flash_" + w3 + "_sound.mp3");
				CommonAnimation.enlargeAnimation2(ivSisterCard3, 600);
				break;
			case 8:
				String word = (String) msg.obj;
				CommonAnimation.enlargeAnimation2(ivSisterCard1, 600);
				CommonAnimation.enlargeAnimation2(ivSisterCard2, 600);
				CommonAnimation.enlargeAnimation2(ivSisterCard3, 600);
				playMusicOnly(ConstantEp.path_reading03 + "flash_" + word + ".mp3");
				break;
			default:
				break;
			}
		}

	};

	private void showView(int cardView, int lailaiView, int sisterView) {
		rlCard.setVisibility(cardView);
		rlLaiLai.setVisibility(lailaiView);
		rlSister.setVisibility(sisterView);
	}

	private void ivClick(View view, int index) {
		// 判断是否答对了
		if (tempClicknum < 8) {
			int soundId = randSound[tempClicknum];
			int clickId = randCard[index];
			boolean isCorrect = false;
			if (soundId == clickId || soundId == (clickId + 4)) {
				isCorrect = true;
			}
			if (isCorrect) { // 答对了
				playMusicOnly(ConstantEp.path_reading03 + "flash_" + cardSound[randSound[tempClicknum]] + ".mp3");
				tempClicknum++;
//				ivAnswer[index].setImageResource(R.drawable.yes_same_size);
				ivAnswer[index].setImageResource(R.drawable.big_right);
				tvNum.setText(tempClicknum + " / 8");
				delayGone();
			} else { // 答错了
				MediaCommon.playFuxiError(mContext);
//				ivAnswer[index].setImageResource(R.drawable.no_same_size);
				ivAnswer[index].setImageResource(R.drawable.big_error);
			}
		}
	}

	private void delayGone() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1500);
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
		haveClicked = true;
		if (v == ivCard1) { // fox
			ivClick(ivCard1, 0);
		} else if (v == ivCard2) { // box
			ivClick(ivCard2, 1);
		} else if (v == ivCard3) { // mix
			ivClick(ivCard3, 2);
		} else if (v == ivCard4) { // six
			ivClick(ivCard4, 3);
		} else if (v == ivReader) {
			if (tempClicknum < 8) {
				playMusicOnly(ConstantEp.path_reading03 + "flash_"
						+ cardSound[randSound[tempClicknum]] + ".mp3");
			} else if (tempClicknum < 12) {
				playMusicOnly(ConstantEp.path_reading03 + "flash_"
						+ cardSound[randCard[(tempClicknum - 8)]] + ".mp3");
			}
		}
	}

	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1;
	private int[] arrayList = new int[] { 0, 1, 2 };

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (selectedId != 0 && selectedId != v.getId())
			return true;
		ivToFront(v);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x = event.getX();
			y = event.getY();
			mWidth = v.getWidth();
			mHeight = v.getHeight();
			break;
		case MotionEvent.ACTION_MOVE:
			mx = (int) (event.getRawX() - x);
			my = (int) (event.getRawY() - dip2px(mContext, 25) - y);
			if (mx + mWidth > width)
				mx = width - mWidth;
			if (my + mHeight > height)
				my = height - mHeight;
			v.setLayoutParams(LayoutParameters.setViewPositionParams(mWidth,
					mHeight, mx, my));
			break;

		case MotionEvent.ACTION_UP:
			// 拿到红色框框在屏幕的Y X 坐标（dp）
			final int[] location = new int[2];
			Rect viewRect = new Rect(); // 定义一个矩形区域
			if (indexSelect == 0) {//
				// 计算该视图在全局坐标系中的x，y值，（注意这个值是要从屏幕顶端算起，也就是索包括了通知栏的高度）//获取在当前屏幕内的绝对坐标
				ivSisterCard1.getLocationOnScreen(location);
				ivSisterCard1.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
				// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			} else if (indexSelect == 1) {
				ivSisterCard2.getLocationOnScreen(location);
				ivSisterCard2.getGlobalVisibleRect(viewRect);
			} else if (indexSelect == 2) {
				ivSisterCard3.getLocationOnScreen(location);
				ivSisterCard3.getGlobalVisibleRect(viewRect);
			} else {
				ivSisterCard1.getLocationOnScreen(location);
				ivSisterCard1.getGlobalVisibleRect(viewRect);
			}

			// View otherView = v;
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect >= 0) && Rect.intersects(viewRect, otherViewRect)) {
				setBestPosition(v, mx, my);
			} else {
				MediaCommon.playFuxiError(mContext);
				if (v == ivSisterBottomWord1) {
					setViewPosition(ivSisterBottomWord1, 19);
				} else if (v == ivSisterBottomWord2) {
					setViewPosition(ivSisterBottomWord2, 20);
				} else if (v == ivSisterBottomWord3) {
					setViewPosition(ivSisterBottomWord3, 21);
				}
				// 保护的用途
				selectedId = 0;
			}
			break;
		default:
			break;
		}
		return true;
	}

	// 把点击的图片置顶到顶层
	private void ivToFront(View v) {
		selectedId = v.getId();
		indexSelect = -1;
		if (v == ivSisterBottomWord1) {
			frontView(ivSisterBottomWord1, 0);
		} else if (v == ivSisterBottomWord2) {
			frontView(ivSisterBottomWord2, 1);
		} else if (v == ivSisterBottomWord3) {
			frontView(ivSisterBottomWord3, 2);
		}
	}

	private void frontView(ImageView ivPic, int index) {
		ivPic.bringToFront();
		indexSelect = getRand(index);
	}

	private int getRand(int index) {
		for (int i = 0; i < arrayList.length; i++) {
			if (i == rand[index]) {
				return i;
			}
		}
		return -1;
	}

	// 获得最佳停留位置
	private void setBestPosition(View v, float x, float y) {
		final int[] location = new int[2];
		if (indexSelect >= 0 && indexSelect < 5) {
			ivSisterBoxCard[indexSelect].getLocationOnScreen(location);
		}
		if (indexSelect >= 0) {
			showGoodWord(v);
		}
	}

	private void showGoodWord(final View v) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				handlerTouch.sendMessage(handlerTouch.obtainMessage(0, v));
			}
		}).start();
	}

	Handler handlerTouch = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				View v = (View) msg.obj;
				showSelectedWord(v);
				break;
			default:
				break;
			}
		}

	};

	private int selectNum = 0;
	private int sisterFinishNum = 0;

	private void showSelectedWord(View v) {
		if (selectedId == R.id.iv_sister_white_box_bottom_card_1) {
			statusImg((ImageView) v, 0);
		} else if (selectedId == R.id.iv_sister_white_box_bottom_card_2) {
			statusImg((ImageView) v, 1);
		} else if (selectedId == R.id.iv_sister_white_box_bottom_card_3) {
			statusImg((ImageView) v, 2);
		}
		selectNum++;
		if (selectNum == 3) {
			selectNum = 0;
			sisterFinishNum++;
			if (sisterFinishNum <= 4) {
//				playMusicOnly(ConstantEp.path_reading03 + "flash_" + sisterCardId[randCard[(tempClicknum - 8)]] + ".mp3");
				String word = sisterCardId[randCard[(tempClicknum - 8)]];
				tempClicknum++;
				tvNum.setText((tempClicknum - 8) + " / 4");
				String w1 = word.substring(0, 1);
				String w2 = word.substring(1, 2);
				String w3 = word.substring(2, 3);
				delayFinish(w1, w2, w3, word);
			}
		}
		// 保护的用途
		selectedId = 0;
	}

	private void delayFinish(final String w1, final String w2, final String w3, final String word) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					// TODO 单词字母放大
					handler.sendMessage(handler.obtainMessage(5, w1));
					Thread.sleep(800);
					handler.sendMessage(handler.obtainMessage(6, w2));
					Thread.sleep(800);
					handler.sendMessage(handler.obtainMessage(7, w3));
					Thread.sleep(800);
					handler.sendMessage(handler.obtainMessage(8, word));
					Thread.sleep(1300);
					handler.sendMessage(handler.obtainMessage(4));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void statusImg(ImageView ivImg, int index) {
		ivSisterBoxCard[rand[index]].setImageResource(new BaseCommon()
				.getImageId(mContext, "word_" + wordArray[rand[index]]));
		ivImg.clearAnimation();
		ivImg.setVisibility(View.GONE);
		ivImg.setEnabled(false);
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

}
