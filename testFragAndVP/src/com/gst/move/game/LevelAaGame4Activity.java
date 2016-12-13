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
import com.ebodoo.raz.utils.ConstantGame;
import com.ebodoo.raz.utils.ConstantLetters;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 * The Supermarket
 *   How many eggs do you see?
 * @author
 * 
 */
public class LevelAaGame4Activity extends BaseActivity implements
		OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivNum1, ivNum2, ivNum3, ivEgg1, ivEgg2, ivEgg3, ivEgg4, ivEgg5, ivEgg6, ivEncourage;
	private Context mContext;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private static String path;
	private AnimationDrawable frameAnim1, frameAnim2, frameAnim3;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private AnimationDrawable frameAnimEncourage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_4);

		init();
		setView();
	}

	private void init() {
		mContext = LevelAaGame4Activity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		path = BaseCommon.path_raz_image;
		// 提示语
		playSoundMusic(BaseCommon.path_raz_mp3 +  "supermarket_2.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivNum1 = (ImageView) findViewById(R.id.iv_num_1);
		ivNum2 = (ImageView) findViewById(R.id.iv_num_2);
		ivNum3 = (ImageView) findViewById(R.id.iv_num_3);
		ivEgg1 = (ImageView) findViewById(R.id.iv_egg_1);
		ivEgg2 = (ImageView) findViewById(R.id.iv_egg_2);
		ivEgg3 = (ImageView) findViewById(R.id.iv_egg_3);
		ivEgg4 = (ImageView) findViewById(R.id.iv_egg_4);
		ivEgg5 = (ImageView) findViewById(R.id.iv_egg_5);
		ivEgg6 = (ImageView) findViewById(R.id.iv_egg_6);

		setViewPosition(ivNum1, 0);
		setViewPosition(ivNum2, 1);
		setViewPosition(ivNum3, 2);
		setViewPosition(ivEgg1, 3);
		setViewPosition(ivEgg2, 4);
		setViewPosition(ivEgg3, 5);
		setViewPosition(ivEgg4, 6);
		setViewPosition(ivEgg5, 7);
		setViewPosition(ivEgg6, 8);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "supermarket_egg_bg"));
		ivNum1.setBackground(BaseCommon.drawableChange(path, "supermarket_num_1"));
		ivNum2.setBackground(BaseCommon.drawableChange(path, "supermarket_num_2"));
		ivNum3.setBackground(BaseCommon.drawableChange(path, "supermarket_num_3"));
		ivEgg1.setBackground(BaseCommon.drawableChange(path, "supermarket_egg_1"));
		ivEgg2.setBackground(BaseCommon.drawableChange(path, "supermarket_egg_1"));
		ivEgg3.setBackground(BaseCommon.drawableChange(path, "supermarket_egg_1"));
		ivEgg4.setBackground(BaseCommon.drawableChange(path, "supermarket_egg_1"));
		ivEgg5.setBackground(BaseCommon.drawableChange(path, "supermarket_egg_1"));
		ivEgg6.setBackground(BaseCommon.drawableChange(path, "supermarket_egg_1"));

		ivNum1.setOnClickListener(this);
		ivNum2.setOnClickListener(this);
		ivNum3.setOnClickListener(this);

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
					Thread.sleep(800);
					frameAnim1 = ConstantLetters.setData(path, "supermarket_egg_", 
							6, 150);
					frameAnim2 = ConstantLetters.setData(path, "supermarket_egg_", 
							6, 150);
					frameAnim3 = ConstantLetters.setData(path, "supermarket_egg_", 
							6, 150);
					handler.sendMessage(handler.obtainMessage(0));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedGameAaPosition.supermarket_2_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivNum1) {
			MediaCommon.playFuxiError(mContext);
			CommonAnimation.shakeAnimation(mContext, ivNum1);
		} else if (v == ivNum2) {
			MediaCommon.playFuxiError(mContext);
			CommonAnimation.shakeAnimation(mContext, ivNum2);
		} else if(v == ivNum3) {
			// 提示 good
			MediaCommon.playFuxiGood(mContext);
//			delayFinish();
			ivEncourage.setVisibility(View.VISIBLE);
			processAnimation(frameAnimEncourage, ivEncourage);
			delayFinish(3500);
		}
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				processAnimation(frameAnim1, ivEgg2);
				processAnimation(frameAnim2, ivEgg3);
				processAnimation(frameAnim3, ivEgg6);
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
