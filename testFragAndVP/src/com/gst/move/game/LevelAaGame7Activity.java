package com.gst.move.game;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.data.FixedGameAaPosition;
import com.ebodoo.raz.utils.ConstantLetters;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;

/**
 * The Trip --- open the suitcase,close the suitcase
 * 
 * @author
 * 
 */
public class LevelAaGame7Activity extends BaseActivity implements
		OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard, ivEncourage;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private static String path;
	private AnimationDrawable frameAnim1, frameAnim2;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int playNum = 0;
	private AnimationDrawable frameAnimEncourage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_7);

		init();
		setView();
	}

	private void init() {
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		path = BaseCommon.path_raz_image;
		// 提示语
		playSoundMusic(BaseCommon.path_raz_mp3 +  "trip_1.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard = (ImageView) findViewById(R.id.iv_card);
		
		setViewPosition(ivCard, 0);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "trip_1_bg"));
		ivCard.setBackground(BaseCommon.drawableChange(path, "trip_dog_open_1"));
		
		ivCard.setOnClickListener(this);
//		ivCard.setEnabled(false);
//		threadData();
		ivEncourage = (ImageView) findViewById(R.id.iv_encourage);
		frameAnimEncourage = ConstantLetters.setData(path, "encourage_", 
				20, 150);
		biz.setViewPosition(ivEncourage, 0, FixedGameAaPosition.encourage_position, scaleQPW, scaleQPH, 0, 0, 1.0f);
		ivEncourage.setBackground(BaseCommon.drawableChange(path, "encourage_" + "1"));
		ivEncourage.setVisibility(View.GONE);
	}

	private void threadData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					long time = 500;
					Thread.sleep(time);
					frameAnim1 = ConstantLetters.setData(path, "trip_dog_open_", 
							11, 150);
					frameAnim2 = ConstantLetters.setData(path, "trip_dog_close_", 
							8, 150);
					handler.sendMessage(handler.obtainMessage(0, 7*150));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedGameAaPosition.trip_2_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivCard) {
			playNum++;
			ivCard.setEnabled(false);
			if(playNum == 1) {
				threadData();
			} else if(playNum == 2) {
				processAnimation(frameAnim2, ivCard);
				int time = 150*8 + 200;
				Handler handler0 = new Handler();
				handler0.postDelayed(new Runnable() {
					public void run() {
						// 此处调用第二个动画播放方法
						handler.sendMessage(handler.obtainMessage(1));
					}
				}, time);
			}
		}
	}
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				int time = (Integer) msg.obj;
				processAnimation(frameAnim1, ivCard);
				Handler handler0 = new Handler();
				handler0.postDelayed(new Runnable() {
					public void run() {
						// 此处调用第二个动画播放方法
						playSoundMusic(BaseCommon.path_raz_mp3 +  "trip_2.mp3");
						handler.sendMessage(handler.obtainMessage(2));
					}
				}, time);
				break;
			case 1:
//				finish();
				ivEncourage.setVisibility(View.VISIBLE);
				processAnimation(frameAnimEncourage, ivEncourage);
				delayFinish(3500);
				break;
			case 2:
				// TODO 关闭箱子 语音提示
				ivCard.setEnabled(true);
				break;
			case 3:
				finish();
				break;
			default:
				break;
			}
		}

	};
	
	private void delayFinish(final long time) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(time);
					handler.sendMessage(handler.obtainMessage(3));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void processAnimation(AnimationDrawable animation, ImageView iv) {
		iv.setBackground(animation);
		animation.start();
		animation.setOneShot(true);// true则只运行一次，false可以循环
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (frameAnim1 != null && !frameAnim1.isRunning()) {
			frameAnim1.stop();
		}
		clearMediaPlayer();
	}
	
	private void playSoundMusic(String path) {
		mMediaPlayerSound = ActivityBiz.playSoundMusic(mMediaPlayerSound, path);
	}
	
	private void clearMediaPlayer() {
		try {
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		try {
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.pause();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
