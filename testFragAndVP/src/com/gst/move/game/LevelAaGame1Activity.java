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
import com.ebodoo.raz.utils.MediaCommon;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;

/**
 * @author
 *
 */
public class LevelAaGame1Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView iv1, iv2, iv3, iv4, iv5, ivEncourage;
	private ImageView[] ivCard;
	private Context mContext;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private static String path;
	private int index, size, answerIndex;
	private int[][] locationPos;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private String mp3Name = "";
	private AnimationDrawable frameAnimEncourage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_1);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelAaGame1Activity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		path = BaseCommon.path_raz_image;
		
		index = getIntent().getExtras().getInt("index");
//		index = 10;
		size = ConstantGame.picClickName[index].length - 2;
		answerIndex = Integer.valueOf(ConstantGame.picClickName[index][size + 1]).intValue();
		locationPos = ConstantGame.locationPosition[index];
		mp3Name = ConstantGame.clickMp3Name[index];
		// 提示语
		playSoundMusic(BaseCommon.path_raz_mp3 +  mp3Name);
	}
	
	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		iv1 = (ImageView) findViewById(R.id.iv_1);
		iv2 = (ImageView) findViewById(R.id.iv_2);
		iv3 = (ImageView) findViewById(R.id.iv_3);
		iv4 = (ImageView) findViewById(R.id.iv_4);
		iv5 = (ImageView) findViewById(R.id.iv_5);
		
		ivCard = new ImageView[5];
		ivCard[0] = iv1;
		ivCard[1] = iv2;
		ivCard[2] = iv3;
		ivCard[3] = iv4;
		ivCard[4] = iv5;
		
		rlLayout.setBackground(BaseCommon.drawableChange(path, ConstantGame.picClickName[index][0]));
		for(int i=0; i<ivCard.length; i++) {
			if(i < size) {
				setViewPosition(ivCard[i], i);
				ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, ConstantGame.picClickName[index][i + 1]));
				ivCard[i].setOnClickListener(this);
			} else {
				ivCard[i].setVisibility(View.GONE);
			}
		}
		
		ivEncourage = (ImageView) findViewById(R.id.iv_encourage);
		frameAnimEncourage = ConstantLetters.setData(path, "encourage_", 
				20, 150);
		biz.setViewPosition(ivEncourage, 0, FixedGameAaPosition.encourage_position, scaleQPW, scaleQPH, 0, 0, 1.0f);
		ivEncourage.setBackground(BaseCommon.drawableChange(path, "encourage_" + "1"));
		ivEncourage.setVisibility(View.GONE);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, locationPos, scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == iv1) {
			processClick(0);
		} else if(v == iv2) {
			processClick(1);
		} else if(v == iv3) {
			processClick(2);
		} else if(v == iv4) {
			processClick(3);
		} else if(v == iv5) {
			processClick(4);
		}
	}
	
	private void processClick(int indexValue) {
		if(indexValue == answerIndex) {
			// 提示 good
			if(index == 5) {
				playSoundMusic(BaseCommon.path_raz_mp3 +  "colorful_egg2.mp3");
//				delayFinish(1500);
				ivEncourage.setVisibility(View.VISIBLE);
				processAnimation(frameAnimEncourage, ivEncourage);
				delayFinish(3500);
			} else {
				MediaCommon.playFuxiGood(mContext);
//				delayFinish(1000);
				ivEncourage.setVisibility(View.VISIBLE);
				processAnimation(frameAnimEncourage, ivEncourage);
				delayFinish(3500);
			}
		} else {
			MediaCommon.playFuxiError(mContext);
			CommonAnimation.shakeAnimation(mContext, ivCard[indexValue]);
		}
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

	@Override
	public void onDestroy() {
		super.onDestroy();

		clearMediaPlayer();
	}
	
}
