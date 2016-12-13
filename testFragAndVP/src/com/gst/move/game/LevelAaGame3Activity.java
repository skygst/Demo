package com.gst.move.game;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
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
import com.ebodoo.raz.utils.MediaCommon;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;

/**
 * 
 * @author
 * 
 */
public class LevelAaGame3Activity extends BaseActivity implements
		OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView iv1, ivEncourage;
	private Context mContext;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private static String path;
	private AnimationDrawable frameAnim1, frameAnim2;
	private boolean isCanClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int playNum = 0;
	private AnimationDrawable frameAnimEncourage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_3);

		init();
		setView();
	}

	private void init() {
		mContext = LevelAaGame3Activity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		path = BaseCommon.path_raz_image;
		isCanClick = false;
		// 提示语
		playSoundMusic(BaseCommon.path_raz_mp3 +  "winter_1.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		iv1 = (ImageView) findViewById(R.id.iv_1);
		
		rlLayout.setBackground(BaseCommon.drawableChange(path, "winter_bg"));
		setViewPosition(iv1, 0);
		threadData("winter_snowman_", 15);
		iv1.setOnClickListener(this);
		ivEncourage = (ImageView) findViewById(R.id.iv_encourage);
		frameAnimEncourage = ConstantLetters.setData(path, "encourage_", 
				20, 150);
		biz.setViewPosition(ivEncourage, 0, FixedGameAaPosition.encourage_position, scaleQPW, scaleQPH, 0, 0, 1.0f);
		ivEncourage.setBackground(BaseCommon.drawableChange(path, "encourage_" + "1"));
		ivEncourage.setVisibility(View.GONE);
	}

	private void threadData(final String picName, final int num) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				frameAnim1 = ConstantLetters.setData(path,
						picName, num, 150);
				frameAnim2 = ConstantLetters.setData(path,
						"winter_carrot_", 3, 150);
				handler.sendMessage(handler.obtainMessage(0));
			}
		}).start();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedGameAaPosition.winter_1_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == iv1) {
			if(isCanClick) {
				isCanClick = false;
				processClick();
			}
		}
	}
	
	private void processClick() {
			// 提示 good
//			MediaCommon.playFuxiGood(mContext);
			playSoundMusic(BaseCommon.path_raz_mp3 + "winter_2.mp3");
//			delayFinish();
			ivEncourage.setVisibility(View.VISIBLE);
			processAnimation(frameAnimEncourage, ivEncourage);
			delayFinish(3500);
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				processAnimation(frameAnim1, iv1);
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
//		animation.set
		int duration = 0; 
        for(int i=0;i<animation.getNumberOfFrames();i++){ 
            duration += animation.getDuration(i); 
        } 
        
        Handler handler = new Handler(); 
        handler.postDelayed(new Runnable() { 

            public void run() { 
               //此处调用第二个动画播放方法   
            	playSencondAnimation();
            } 
        }, duration); 
	}
	
	private void playSencondAnimation() {
		isCanClick = true;
		iv1.setBackground(frameAnim2);
		frameAnim2.start();
		frameAnim2.setOneShot(false);// true则只运行一次，false可以循环
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (frameAnim1 != null && !frameAnim1.isRunning()) {
			frameAnim1.stop();
		}
		if (frameAnim2 != null && !frameAnim2.isRunning()) {
			frameAnim2.stop();
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
