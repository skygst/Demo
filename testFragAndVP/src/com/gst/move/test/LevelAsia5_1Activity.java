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
import com.gst.move.utils.BaseCommon;

/**
 * 日本 - 复习1
 * @author
 */
public class LevelAsia5_1Activity extends BaseActivity implements
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
	private RelativeLayout rlLayout;

	/*-----------------不共用----------------------*/
	private int endIndex = 28;

	private int offset_time = 0; // 用于步步高平板延迟时间修正
	/*-----------------视图----------------------*/
	private ImageView bt_home, ivWordBg, ivWord1, ivWord2, ivBigPic, ivSel1, ivSel2, ivSel3,
			ivSmallPic, ivRead;
	private RelativeLayout rlNum;
	private TextView tvNum;
	private int tempClicknum = 0;
	private String[][] picArray = new String[][] {
			{"japan_word_bg", "japan_ob", "japan_op", "japan_ot"},
			{"japan_word_c", "japan_word_s", "japan_word_m", "japan_word_j", "japan_word_t", "japan_word_t", "japan_word_m", "japan_word_h"},
			{"japan_word_ob", "japan_word_ob", "japan_word_ob", "japan_word_ob", "japan_word_op", "japan_word_ot", "japan_word_op", "japan_word_ot"},
			{"japan_pic_cob", "japan_pic_sob", "japan_pic_mob", "japan_pic_job", "japan_pic_top", "japan_pic_tot", "japan_pic_mop", "japan_pic_hot"},
			{"0", "0", "0", "0", "1", "2", "1", "2"},
			{"japan_ill", "japan_ell"},
			{"japan_word_p2", "japan_word_m2", "japan_word_s2", "japan_word_h2", "japan_word_w2", "japan_word_t2", "japan_word_f2", "japan_word_b2"},
			{"japan_word_ill", "japan_word_ill", "japan_word_ill", "japan_word_ill", "japan_word_ell", "japan_word_ell", "japan_word_ell", "japan_word_ell"},
			{"japan_pic_pill", "japan_pic_mill", "japan_pic_sill", "japan_pic_hill", "japan_pic_well", "japan_pic_tell", "japan_pic_fell", "japan_pic_bell"},
			{"0", "0", "0", "0", "1", "1", "1", "1"},
	};
	private String[][] mp3NameArray = new String[][] {
			{"flash_cob", "flash_sob", "flash_mob", "flash_job", "flash_top", "flash_tot", "flash_mop", "flash_hot"},
			{"flash_pill", "flash_mill", "flash_sill", "flash_hill", "flash_well", "flash_tell", "flash_fell", "flash_bell"}
	};
	private String[][] mp3NameSoundArray = new String[][] {
			{"flash_c_cob", "flash_s_sob", "flash_m_mob", "flash_j_job", "flash_t_top", "flash_t_tot", "flash_m_mop", "flash_h_hot"},
			{"flash_p_pill", "flash_m_mill", "flash_s_sill", "flash_h_hill", "flash_w_well", "flash_t_tell", "flash_f_fell", "flash_b_bell"}
	};
	private boolean isInitialization = false;
	private boolean isFinishPlayMp3 = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.asia_level5_1);

		initializationData();
		setView();
	}

	/*-----------------初始化----------------------*/
	/* 数据初始化 */
	private void initializationData() {
		mContext = LevelAsia5_1Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		isInitialization = false;
		isFinishPlayMp3 = false;
		path = ConstantEp.path_reading03 + "asia_level5_1.mp4/";
		pathImg = ConstantEp.path_reading03_images;
		currentIndex = 0;// 5
		startTime = ConstantAsia.timeAsia5_1[currentIndex][0];
		endTime = ConstantAsia.timeAsia5_1[currentIndex][1];
		haveClicked = false;
		clickNum = 0;
		first = 0;
		offset_time = Tools.delayTime();
		endIndex = ConstantAsia.timeAsia5_1.length - 1;
		
	}

	/* 视图初始化 */
	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		bt_home = (ImageView) findViewById(R.id.bt_home);
		biz.setViewPositionNoSuoxiao(bt_home, 0, FixedPosition.common_position,
				scaleQPW, scaleQPH);
		bt_home.setOnClickListener(this);
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivWordBg = (ImageView) findViewById(R.id.iv_word_bg);
		ivWord1 = (ImageView) findViewById(R.id.iv_word_1);
		ivWord2 = (ImageView) findViewById(R.id.iv_word_2);
		ivBigPic = (ImageView) findViewById(R.id.iv_big_pic);
		ivSel1 = (ImageView) findViewById(R.id.iv_sel_1);
		ivSel2 = (ImageView) findViewById(R.id.iv_sel_2);
		ivSel3 = (ImageView) findViewById(R.id.iv_sel_3);
		ivRead = (ImageView) findViewById(R.id.iv_read);
		
		ivSmallPic = (ImageView) findViewById(R.id.iv_small_pic);
		rlNum = (RelativeLayout) findViewById(R.id.rl_num);
		tvNum = (TextView) findViewById(R.id.tv_num);

		setViewPosition(rlNum, 10);
		setViewPosition(ivSmallPic, 11);
		setViewPosition(ivRead, 13);
		ivRead.setOnClickListener(this);
		/*---------不共用--------播放开场提示音*/
		playMusic(MediaCommon.getAsia5_0Mp3(11));

		videoCycle(clickNum);
		biz.playVideo(video, path);
		processLayout();
	}
	
	private void processLayout() {
		setViewPosition(ivWordBg, 0);
		setViewPosition(ivWord1, 0);
		setViewPosition(ivWord2, 0);
		
		setViewPosition(ivBigPic, 2);
		setViewPosition(ivSel1, 3);
		setViewPosition(ivSel2, 4);
		setViewPosition(ivSel3, 5);
		
		ivWordBg.setBackground(BaseCommon.drawableChange(pathImg, picArray[0][0]));
		ivSel1.setImageDrawable(BaseCommon.drawableChange(pathImg, picArray[0][1]));
		ivSel2.setImageDrawable(BaseCommon.drawableChange(pathImg, picArray[0][2]));
		ivSel3.setImageDrawable(BaseCommon.drawableChange(pathImg, picArray[0][3]));
		ivSmallPic.setImageDrawable(BaseCommon.drawableChange(pathImg, "small_bamboo"));
		ivSel1.setOnClickListener(this);
		ivSel2.setOnClickListener(this);
		ivSel3.setOnClickListener(this);
		processView();
	}
	
	private void processView() {
		ivSel1.setVisibility(View.VISIBLE);
		ivSel2.setVisibility(View.VISIBLE);
		if(tempClicknum >= 8) {
			ivSel3.setVisibility(View.GONE);
			if(tempClicknum == 8) {
//				ivWordBg.setVisibility(View.GONE);
				ivWordBg.setBackground(BaseCommon.drawableChange(pathImg, "japan_word_bg2"));
				setViewPosition(ivSel1, 8);
				setViewPosition(ivSel2, 9);
				setViewPosition(ivWord1, 1);
				setViewPosition(ivWord2, 1);
				setViewPosition(ivSmallPic, 12);
				ivSel1.setImageDrawable(BaseCommon.drawableChange(pathImg, picArray[5][0]));
				ivSel2.setImageDrawable(BaseCommon.drawableChange(pathImg, picArray[5][1]));
				ivSmallPic.setImageDrawable(BaseCommon.drawableChange(pathImg, "small_darts"));
			}
			if(tempClicknum > 8) {
				isFinishPlayMp3 = false;
				playMusicOnly(ConstantEp.path_reading03 + mp3NameArray[1][tempClicknum - 8] + ".mp3");
			}
		} else {
			ivSel3.setVisibility(View.VISIBLE);
			if(tempClicknum > 0) {
				isFinishPlayMp3 = false;
				playMusicOnly(ConstantEp.path_reading03 + mp3NameArray[0][tempClicknum] + ".mp3");
			}
		}
		int add = 0;
		if(tempClicknum >= 8) {
			add = 5;
		}
		ivWord1.setImageDrawable(BaseCommon.drawableChange(pathImg, picArray[1 + add][tempClicknum>=8?(tempClicknum-8):tempClicknum]));
		if(tempClicknum >= 8) {
			ivWord2.setImageDrawable(BaseCommon.drawableChange(pathImg, "japan_underline2"));
		} else {
			ivWord2.setImageDrawable(BaseCommon.drawableChange(pathImg, "japan_underline"));
		}
		ivBigPic.setImageDrawable(BaseCommon.drawableChange(pathImg, picArray[3 + add][tempClicknum>=8?(tempClicknum-8):tempClicknum]));
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAsia.asia_level5_1_position,
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
				if (ConstantAsia.timeAsia5_1[currentIndex][4] == 1 && isClicked) {// &&
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
				if (ConstantAsia.timeAsia5_1[currentIndex][4] == 0) {
					currentIndex = ConstantAsia.timeAsia5_1[currentIndex][3];
					if(currentIndex == 1) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(1));
					} else if(currentIndex == 3) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(2));
					} else if((currentIndex == 5 && tempClicknum == 0) || (currentIndex == 14 && tempClicknum == 8)) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(3));
					} else if(currentIndex == 12) {
						playMusicOnly(MediaCommon.getAsia5_0Mp3(4));
					}
					isInitialization = true;
					if (currentIndex == 9) {
						if (tempClicknum < 8) {
							currentIndex = 5;
						}
					} if(currentIndex == 19) {
						if(tempClicknum < 16) {
							currentIndex = 14;
						}
					}
				}
				if (currentIndex == endIndex || currentIndex >= 20) {
					handler.sendMessage(handler.obtainMessage(3));
					return;
				}
				startTime = ConstantAsia.timeAsia5_1[currentIndex][0]
						- offset_time;
				endTime = ConstantAsia.timeAsia5_1[currentIndex][1]
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
							if(currentIndex == 5 && tempClicknum == 0 && !isFinishPlayMp3) {
								isFinishPlayMp3 = true;
								playMusicOnly(ConstantEp.path_reading03 + mp3NameArray[0][0] + ".mp3");
							} else if (currentIndex == 14 && tempClicknum == 8 && !isFinishPlayMp3) {
								isFinishPlayMp3 = true;
								playMusicOnly(ConstantEp.path_reading03 + mp3NameArray[1][0] + ".mp3");
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
		if(tempClicknum >= 8) {
			playMusicOnly(ConstantEp.path_reading03 + mp3NameSoundArray[1][tempClicknum - 8] + ".mp3");
		} else {
			playMusicOnly(ConstantEp.path_reading03 + mp3NameSoundArray[0][tempClicknum] + ".mp3");
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
			startTime = ConstantAsia.timeAsia5_1[seekTo][0] - offset_time;
			endTime = ConstantAsia.timeAsia5_1[seekTo][1] - offset_time;

			video.seekTo(ConstantAsia.timeAsia5_1[seekTo][0] - offset_time);
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
				if (currentIndex == 5) {
					// 初始化View
					if(isInitialization) {
						isInitialization = false;
						processView();
						rlLayout.setVisibility(View.VISIBLE);
					}
				}
				if (currentIndex == 14) {
					if (tempClicknum < 16) {
						if(isInitialization) {
							isInitialization = false;
							processView();
							if(tempClicknum == 8) {
								tvNum.setText(0 + " / 8");
							}
							rlLayout.setVisibility(View.VISIBLE);
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
			case 5:
				tempClicknum++;
				if (tempClicknum < 8) {
					currentIndex = 5;
				} else if (tempClicknum == 8) {
					currentIndex = 7;
				} else if (tempClicknum > 8 && tempClicknum < 16) {
					currentIndex = 14;
				} else if (tempClicknum == 16) {
					currentIndex = 16;
				}
				delayProcessClick(300, 6);
				break;
			case 6:
				rlLayout.setVisibility(View.GONE);
				clickExecute(ConstantAsia.timeAsia5_1[currentIndex][3]);
				break;
			case 7:
				CommonAnimation.startShouAnimation22(ivWord1);
				break;
			case 8:
				CommonAnimation.startShouAnimation22(ivWord2);
				break;
			case 9:
				CommonAnimation.startShouAnimation22(ivWord1);
				CommonAnimation.startShouAnimation22(ivWord2);
				break;
			default:
				break;
			}
		}

	};
	
	private void ivClick(View view, int index) {
		String answer = picArray[tempClicknum >= 8 ? 9 : 4][tempClicknum >= 8 ? (tempClicknum - 8)
				: tempClicknum];
		if (index == Integer.valueOf(answer).intValue()) {
			view.setVisibility(View.GONE);
			if (tempClicknum >= 8) {
				ivWord2.setImageDrawable(BaseCommon.drawableChange(pathImg,
						picArray[7][tempClicknum >= 8 ? (tempClicknum - 8)
								: tempClicknum]));
			} else {
				ivWord2.setImageDrawable(BaseCommon.drawableChange(pathImg,
						picArray[2][tempClicknum >= 8 ? (tempClicknum - 8)
								: tempClicknum]));
			}
			delayProcessClick(2600, 5);
			delayProcessAnimation();
		} else {
			// sorry
			MediaCommon.playFuxiError(mContext);
			//动画 晃动
			CommonAnimation.shakeAnimation(mContext, view);
			return;
		}
		view.setVisibility(View.INVISIBLE);
		int num = tempClicknum;
		num = num + 1;
		playword();
		if (num >= 8) {
			if(num == 8) {
				tvNum.setText(num + " / 8");
			} else {
				tvNum.setText((num - 8) + " / 8");
			}
		} else {
			tvNum.setText(num + " / 8");
		}
	}
	
	private void delayProcessClick(final long time, final int index) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(time);
					handler.sendMessage(handler.obtainMessage(index));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void delayProcessAnimation() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(100);
					handler.sendMessage(handler.obtainMessage(7));
					Thread.sleep(800);
					handler.sendMessage(handler.obtainMessage(8));
					Thread.sleep(800);
					handler.sendMessage(handler.obtainMessage(9));
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
		if (v == ivSel1) {
			ivClick(ivSel1, 0);
		} else if (v == ivSel2) { 
			ivClick(ivSel2, 1);
		} else if (v == ivSel3) {
			ivClick(ivSel3, 2);
		} else if(v == ivRead) { // 重读
			if(tempClicknum >= 8) {
				playMusicOnly(ConstantEp.path_reading03 + mp3NameArray[1][tempClicknum - 8] + ".mp3");
			} else {
				playMusicOnly(ConstantEp.path_reading03 + mp3NameArray[0][tempClicknum] + ".mp3");
			}
		}
	}
	
	/* 显示手动画 */
	private void showBtnAndShou(ImageView iv) {
//		iv.setVisibility(View.VISIBLE);
//		if(tempClicknum == 0){
//			biz.setViewPositionNoSuoxiao(ivShou, 18, FixedPositionAsia.asia_level5_0_position, scaleQPW ,scaleQPH);
//		}else if(tempClicknum == 8){
//			biz.setViewPositionNoSuoxiao(ivShou, 19, FixedPositionAsia.asia_level5_0_position, scaleQPW ,scaleQPH);
//		}
		CommonAnimation.startShouAnimation(iv);
	}

	/* 消失手动画 */
	private void stopBtnAndShou(ImageView iv) {
//		ivShou.setVisibility(View.GONE);
		CommonAnimation.stopShouAnimation(iv);
	}

}
