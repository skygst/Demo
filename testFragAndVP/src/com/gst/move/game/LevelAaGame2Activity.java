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
import com.example.location.biz.StringBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 * Little --- where is the bug？
 * 
 * @author
 * 
 */
public class LevelAaGame2Activity extends BaseActivity implements
		OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView iv1, iv2, iv3, iv4, iv5, ivEncourage;
	private ImageView[] ivCard;
	private Context mContext;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private static String path;
	private AnimationDrawable frameAnim1, frameAnim2, frameAnim3, frameAnim4, frameAnim5;
	private AnimationDrawable[] animation;
	private int index, size, answerIndex;
	private String blValue;
	private int[][] locationPos;
	private boolean isNotCycle = true;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private String mp3Name = "";
	private int playNum = 0;
	private AnimationDrawable frameAnimEncourage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_2);

		init();
		setView();
	}

	private void init() {
		mContext = LevelAaGame2Activity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		path = BaseCommon.path_raz_image;
		
		index = getIntent().getExtras().getInt("index");
//		index = 4;
		size = ConstantGame.picAnimationName[index].length - 3;
		answerIndex = Integer.valueOf(ConstantGame.picAnimationName[index][size + 1]).intValue();
		blValue = ConstantGame.picAnimationName[index][ConstantGame.picAnimationName[index].length - 1];
		locationPos = ConstantGame.locationAnimationPosition[index];
		animation = new AnimationDrawable[size];
		if(!StringBiz.isEmpty(blValue) && blValue.equals("false")) {
			isNotCycle = false; 
		} else {
			isNotCycle = true;
		}
		mp3Name = ConstantGame.animationMp3Name[index];
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
		
		rlLayout.setBackground(BaseCommon.drawableChange(path, ConstantGame.picAnimationName[index][0]));
		for(int i=0; i<ivCard.length; i++) {
			if(i < size) {
				setViewPosition(ivCard[i], i);
				ivCard[i].setBackground(BaseCommon.drawableChange(path, ConstantGame.picAnimationName[index][i + 1] + "1"));
				ivCard[i].setOnClickListener(this);
			} else {
				ivCard[i].setVisibility(View.GONE);
			}
		}
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
					long time = 0;
					if(isNotCycle) {
						time = 500;
					}
					Thread.sleep(time);
					for(int i=0; i<size; i++) {
						AnimationDrawable frameAnim = ConstantLetters.setData(path, ConstantGame.picAnimationName[index][i + 1], 
								ConstantGame.picNum[index][i], 150);
						animation[i] = frameAnim;
					}
					handler.sendMessage(handler.obtainMessage(0));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, locationPos,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
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
			MediaCommon.playFuxiGood(mContext);
//			delayFinish();
			isNotCycle = true;
			ivEncourage.setVisibility(View.VISIBLE);
			processAnimation(frameAnimEncourage, ivEncourage);
			delayFinish(3500);
		} else {
			MediaCommon.playFuxiError(mContext);
			CommonAnimation.shakeAnimation(mContext, ivCard[indexValue]);
		}
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				for(int i=0; i<size; i++) {
					processAnimation(animation[i], ivCard[i]);
				}
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
		animation.setOneShot(isNotCycle);// true则只运行一次，false可以循环
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
		if (frameAnim3 != null && !frameAnim3.isRunning()) {
			frameAnim3.stop();
		}
		if (frameAnim4 != null && !frameAnim4.isRunning()) {
			frameAnim4.stop();
		}
		if (frameAnim5 != null && !frameAnim5.isRunning()) {
			frameAnim5.stop();
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
		mMediaPlayerSound.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				playNum++;
				if(index == 2 && playNum == 1) {
					playSoundMusic(BaseCommon.path_raz_mp3 +  "summer_2.mp3");
				} else if(index == 3 && playNum == 1) {
					playSoundMusic(BaseCommon.path_raz_mp3 +  "winter_4.mp3");
				}
			}
		});
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
