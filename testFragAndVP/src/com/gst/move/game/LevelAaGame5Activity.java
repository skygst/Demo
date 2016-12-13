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
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.ConstantGame;
import com.ebodoo.raz.utils.ConstantLetters;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 * The Ocean ---what color is the seaweed? Yes，the color of seaweed is green---0
 * It Is Fall --- what color is the pumpkin? --- 1
 * 
 * @author
 * 
 */
public class LevelAaGame5Activity extends BaseActivity implements
		OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView iv1, iv2, iv3, iv4, ivEncourage;
	private ImageView[] ivCard;
	private Context mContext;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private static String path;
	private int index, size, answerIndex;
	private int[][] locationPos;
	private String mp3Name = "";
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private AnimationDrawable frameAnimEncourage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_5);

		init();
		setView();
	}

	private void init() {
		mContext = LevelAaGame5Activity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		path = BaseCommon.path_raz_image;
		
		index = getIntent().getExtras().getInt("index");
//		index = 1;
		size = ConstantGame.picChangeName[index].length - 4;
		answerIndex = Integer.valueOf(ConstantGame.picChangeName[index][size + 3]).intValue();
		locationPos = ConstantGame.locationChangePosition[index];
		// 提示语
		mp3Name = ConstantGame.changeMp3Name[index];
		playSoundMusic(BaseCommon.path_raz_mp3 +  mp3Name);
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		iv1 = (ImageView) findViewById(R.id.iv_1);
		iv2 = (ImageView) findViewById(R.id.iv_2);
		iv3 = (ImageView) findViewById(R.id.iv_3);
		iv4 = (ImageView) findViewById(R.id.iv_4);

		rlLayout.setBackground(BaseCommon.drawableChange(path, ConstantGame.picChangeName[index][0]));
		ivCard = new ImageView[4];
		ivCard[0] = iv1;
		ivCard[1] = iv2;
		ivCard[2] = iv3;
		ivCard[3] = iv4;
		for(int i=0; i<ivCard.length; i++) {
			setViewPosition(ivCard[i], i);
			ivCard[i].setBackground(BaseCommon.drawableChange(path, ConstantGame.picChangeName[index][i + 1]));
			if(i != (ivCard.length - 1)) {
				ivCard[i].setOnClickListener(this);
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
		biz.setViewPosition(iv, i, locationPos,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == iv1) { // 点击 冷
			processClick(0);
		} else if (v == iv2) { // 点击 热
			processClick(1);
		} else if(v == iv3) {
			processClick(2);
		}
	}
	
	private void processClick(int indexValue) {
		if(indexValue == answerIndex) {
			// 提示 good
			ivCard[ivCard.length - 1].setBackground(BaseCommon.drawableChange(path, ConstantGame.picChangeName[index][5]));
			if(index == 0) {
				playSoundMusic(BaseCommon.path_raz_mp3 + "ocean_2.mp3");
//				delayFinish(3500);
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
