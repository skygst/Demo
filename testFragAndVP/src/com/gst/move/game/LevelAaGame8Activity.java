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
 * We Bulid --- Diy time! paint your own house.
 * 
 * @author
 * 
 */
public class LevelAaGame8Activity extends BaseActivity implements
		OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivColor1, ivColor2, ivHouse, ivEncourage;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private static String path;
	private int clickNum = 0;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private AnimationDrawable frameAnimEncourage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_8);

		init();
		setView();
	}

	private void init() {
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		path = BaseCommon.path_raz_image;
		clickNum = 0;
		// 提示语
		playSoundMusic(BaseCommon.path_raz_mp3 +  "build_3.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivColor1 = (ImageView) findViewById(R.id.iv_color_1);
		ivColor2 = (ImageView) findViewById(R.id.iv_color_2);
		ivHouse = (ImageView) findViewById(R.id.iv_house);
		
		setViewPosition(ivColor1, 0);
		setViewPosition(ivColor2, 1);
		setViewPosition(ivHouse, 2);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "bulid_bg"));
		ivColor1.setBackground(BaseCommon.drawableChange(path, "bulid_color_1"));
		ivColor2.setBackground(BaseCommon.drawableChange(path, "bulid_color_2"));
		ivHouse.setBackground(BaseCommon.drawableChange(path, "bulid_house_empty"));
		
		ivColor1.setOnClickListener(this);
		ivColor2.setOnClickListener(this);
		ivEncourage = (ImageView) findViewById(R.id.iv_encourage);
		frameAnimEncourage = ConstantLetters.setData(path, "encourage_", 
				20, 150);
		biz.setViewPosition(ivEncourage, 0, FixedGameAaPosition.encourage_position, scaleQPW, scaleQPH, 0, 0, 1.0f);
		ivEncourage.setBackground(BaseCommon.drawableChange(path, "encourage_" + "1"));
		ivEncourage.setVisibility(View.GONE);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedGameAaPosition.bulid_1_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivColor1) {
			clickColor(ivColor1, false);
		} else if(v == ivColor2) {
			clickColor(ivColor2, true);
		}
	}
	
	private void clickColor(ImageView iv, boolean isClickRoofColor) {
		iv.setEnabled(false);
		clickNum++;
		if(clickNum == 1) {
			if(isClickRoofColor) {
				ivHouse.setBackground(BaseCommon.drawableChange(path, "bulid_house_roof"));
			} else {
				ivHouse.setBackground(BaseCommon.drawableChange(path, "bulid_house_wall"));
			}
		} else {
			ivHouse.setBackground(BaseCommon.drawableChange(path, "bulid_house_finish"));
//			delayFinish();
			ivEncourage.setVisibility(View.VISIBLE);
			processAnimation(frameAnimEncourage, ivEncourage);
			delayFinish(3500);
		}
	}
	
	private void processAnimation(AnimationDrawable animation, ImageView iv) {
		iv.setBackground(animation);
		animation.start();
		animation.setOneShot(true);// true则只运行一次，false可以循环
	}
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				finish();
				break;
			default:
				break;
			}
		}

	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
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
