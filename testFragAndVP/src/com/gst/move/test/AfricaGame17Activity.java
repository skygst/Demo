package com.gst.move.test;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.data.FixedPositionAfrica;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.R.drawable;
import com.gst.move.R.id;
import com.gst.move.R.layout;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 *   I Had a Little Hen
 * @author
 *
 */
public class AfricaGame17Activity extends Activity implements OnClickListener {

	private Context mContext;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, 
				ivAnswer1, ivAnswer2, ivAnswer3, ivAnswer4, ivBack, ivH;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] hName = {"hand", "hat", "hen", "horse", "house"};
	private String[] nName = {"cat", "cow", "dog", "egg"};
	private String[] picName;
	private int[] arrayList = new int[] { 0, 1, 2, 3};
	private int[] randB;
	private ImageView[] ivCard;
	private ImageView[] ivAnswer;
	private String path;
	private int width, height;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int selNum = 0;
	private int timesNum = 0;
	private boolean isCanClick = true;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_game_17);
		
		init();
		setView();
	}

	private void init() {
		biz = new VideoBiz();
		mContext = AfricaGame17Activity.this;
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "little_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) findViewById(R.id.iv_card_4);
		ivAnswer1 = (ImageView) findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) findViewById(R.id.iv_answer_2);
		ivAnswer3 = (ImageView) findViewById(R.id.iv_answer_3);
		ivAnswer4 = (ImageView) findViewById(R.id.iv_answer_4);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		ivH = (ImageView) findViewById(R.id.iv_h);
		
		rlLayout.setBackground(BaseCommon.drawableChange(path, "little_bg"));
		initCard();
		setViewPosition(ivH, 8);
		setViewPosition(ivBack, 9);
		ivBack.setOnClickListener(this);
	}
	
	private void initCard() {
		selNum = 0;
		timesNum = 0;
		randB = BaseCommon.getList(arrayList);
		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		for(int i=0; i<ivCard.length; i++) {
			setViewPosition(ivCard[i], i);
		}
		ivAnswer = new ImageView[4];
		ivAnswer[0] = ivAnswer1;
		ivAnswer[1] = ivAnswer2;
		ivAnswer[2] = ivAnswer3;
		ivAnswer[3] = ivAnswer4;
		for(int i=0; i<ivAnswer.length; i++) {
			setViewPosition(ivAnswer[i], i + 4);
		}
		initCardValue();
		ivH.setImageDrawable(BaseCommon.drawableChange(path, "little_h"));
	}
	
	private void initCardValue() {
		if(timesNum < 5) {
			selNum = 0;
			picName = new String[4];
			picName[0] = hName[timesNum];
			int hIndex = BaseCommon.randData(4, timesNum);
			picName[1] = hName[hIndex];
			randB = BaseCommon.getList(arrayList);
			picName[2] = nName[randB[0]];
			picName[3] = nName[randB[1]];
			randB = BaseCommon.getList(arrayList);
			for(int i=0; i<ivCard.length; i++) {
				ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, "little_" + picName[randB[i]]));
				ivCard[i].setOnClickListener(this);
				ivCard[i].setEnabled(true);
			}
			for(int i=0; i<ivAnswer.length; i++) {
				ivAnswer[i].setVisibility(View.GONE);
			}
		}
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.little_hen_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivCard1 || v == ivCard2 || v == ivCard3 || v == ivCard4) {
			if(isCanClick) {
				isCanClick = false;
				int index = ActivityBiz.clickNum(v, ivCard);
				v.setEnabled(false);
				ivAnswer[index].setVisibility(View.VISIBLE);
				if(randB[index] == 0 || randB[index] == 1) {
					playSoundMusic(BaseCommon.path_game + "little_" + picName[randB[index]] + ".mp3");
					selNum++;
					ivAnswer[index].setImageResource(R.drawable.yes_same_size);
					if(selNum >= 2) {
						for(int i=0; i<ivCard.length; i++) {
							ivCard[i].setEnabled(false);
						}
						delayChangeCard(1000, 0);
					} else {
						delayChangeCard(500, 2);
					}
				} else {
					ivAnswer[index].setImageResource(R.drawable.no_same_size);
					MediaCommon.playFuxiError(mContext);
					delayChangeCard(500, 2);
				}
			}
		} else if(v == ivBack) {
			clearMediaPlayer();
		}
	}
	
	private void delayChangeCard(final long time, final int caseId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(time);
					handler.sendMessage(handler.obtainMessage(caseId));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				isCanClick = true;
				timesNum++;
				if(timesNum >= 5) {
					// TODO H放大
					startShouAnimation(ivH);
					delayFinish();
					
				} else {
					initCardValue();
				}
				break;
			case 1:
				finish();
				break;
			case 2:
				isCanClick = true;
				break;

			default:
				break;
			}
		}
		
	};
	
	private void delayFinish() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					handler.sendMessage(handler.obtainMessage(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	public static void startShouAnimation(ImageView iv){
		ScaleAnimation mScaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f,1.2f, 
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
	    mScaleAnimation.setDuration(300);			//执行一次的时间1s
	    mScaleAnimation.setFillAfter(true);
	    mScaleAnimation.setRepeatCount(-1);			//播放次数，-1为重复播放
	    mScaleAnimation.setStartOffset(100);		//停顿200ms
	    mScaleAnimation.setRepeatMode(Animation.REVERSE);  //动画反向执行一遍
	    	    
	    AnimationSet mShouAnimationSet=new AnimationSet(false);
	    mShouAnimationSet.addAnimation(mScaleAnimation);
	    mShouAnimationSet.setFillAfter(true);
	    mShouAnimationSet.setRepeatCount(-1);
	    iv.startAnimation(mShouAnimationSet);
	}
	
	private void playSoundMusic(String path) {
		mMediaPlayerSound = ActivityBiz.playSoundMusic(mMediaPlayerSound, path);
	}
	
	private void clearMediaPlayer() {
		try {
			selNum = 0;
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
			finish();
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