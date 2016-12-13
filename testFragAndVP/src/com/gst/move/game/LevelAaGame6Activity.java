package com.gst.move.game;

import android.content.Context;
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
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.ConstantLetters;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 * The Coast --- what is the color of the ocean？
 * 
 * @author
 * 
 */
public class LevelAaGame6Activity extends BaseActivity implements
		OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard, ivColor1, ivColor2, ivEncourage;
	private Context mContext;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private static String path;
	private AnimationDrawable frameAnim1;
	private int answerIndex = 1;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private AnimationDrawable frameAnimEncourage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_6);

		init();
		setView();
	}

	private void init() {
		mContext = LevelAaGame6Activity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		path = BaseCommon.path_raz_image;
		answerIndex = 1;
		// 提示语
		playSoundMusic(BaseCommon.path_raz_mp3 +  "coast_1.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard = (ImageView) findViewById(R.id.iv_card);
		ivColor1 = (ImageView) findViewById(R.id.iv_color_1);
		ivColor2 = (ImageView) findViewById(R.id.iv_color_2);
		
		setViewPosition(ivCard, 0);
		setViewPosition(ivColor1, 1);
		setViewPosition(ivColor2, 2);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "coast_bg"));
		ivCard.setBackground(BaseCommon.drawableChange(path, "coast_spider_1"));
		ivColor1.setBackground(BaseCommon.drawableChange(path, "coast_color_1"));
		ivColor2.setBackground(BaseCommon.drawableChange(path, "coast_color_2"));
		ivColor1.setOnClickListener(this);
		ivColor2.setOnClickListener(this);
		ivColor1.setVisibility(View.GONE);
		ivColor2.setVisibility(View.GONE);
		
		threadData();
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
					frameAnim1 = ConstantLetters.setData(path, "coast_spider_", 
							7, 150);
					handler.sendMessage(handler.obtainMessage(0, 7*150));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedGameAaPosition.coast_1_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivColor1) {
			processClick(0, ivColor1);
		} else if(v == ivColor2) {
			processClick(1, ivColor2);
		}
	}
	
	private void processClick(int indexValue, ImageView iv) {
		if(indexValue == answerIndex) {
			// 提示 good
			MediaCommon.playFuxiGood(mContext);
//			delayFinish();
			ivEncourage.setVisibility(View.VISIBLE);
			processAnimation(frameAnimEncourage, ivEncourage);
			delayFinish(3500);
		} else {
			MediaCommon.playFuxiError(mContext);
			CommonAnimation.shakeAnimation(mContext, iv);
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
						ivColor1.setVisibility(View.VISIBLE);
						ivColor2.setVisibility(View.VISIBLE);
					}
				}, time);
				break;
			case 1:
				finish();
				break;
			default:
				break;
			}
		}

	};

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
	
	private void delayFinish(final long time) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(time);
					handler.sendMessage(handler.obtainMessage(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
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
