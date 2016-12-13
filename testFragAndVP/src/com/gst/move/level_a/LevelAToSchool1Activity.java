package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPositionLevelA;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 *  carlos goes to school 1
 * @author
 *
 */
public class LevelAToSchool1Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6, ivCard7, ivCard8;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private AnimationDrawable animBall;
	private boolean[] status = new boolean[] {
			false, false, false, false, false
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.to_school_1);

		init();
		setView();
	}

	private void init() {
		mContext = LevelAToSchool1Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		imgPath = ConstantEp.path_level_a_game + "images/";
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) findViewById(R.id.iv_card_4);
		ivCard5 = (ImageView) findViewById(R.id.iv_card_5);
		ivCard6 = (ImageView) findViewById(R.id.iv_card_6);
		ivCard7 = (ImageView) findViewById(R.id.iv_card_7);
		ivCard8 = (ImageView) findViewById(R.id.iv_card_8);

		rlLayout.setBackground(CommonBitmap.drawableChange(imgPath, "to_school_bg"));
		ivCard1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "to_school_bag_1"));
		ivCard2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "to_school_book"));
		ivCard3.setImageDrawable(CommonBitmap.drawableChange(imgPath, "to_school_pen"));
		ivCard4.setImageDrawable(CommonBitmap.drawableChange(imgPath, "to_school_ruler"));
		ivCard5.setImageDrawable(CommonBitmap.drawableChange(imgPath, "to_school_rubber"));
		ivCard6.setImageDrawable(CommonBitmap.drawableChange(imgPath, "to_school_bag_2"));
		ivCard7.setBackground(CommonBitmap.drawableChange(imgPath, "to_school_ball"));

		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 2);
		setViewPosition(ivCard4, 3);
		setViewPosition(ivCard5, 4);
		setViewPosition(ivCard6, 0);
		setViewPosition(ivCard7, 5);
		setViewPosition(ivCard8, 6);

		ivCard2.setOnClickListener(this);
		ivCard3.setOnClickListener(this);
		ivCard4.setOnClickListener(this);
		ivCard5.setOnClickListener(this);
		ivCard7.setOnClickListener(this);
		setRecylePic();
		// 播放题目读音
		mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game +
				"to_school_problem_1.mp3", mMediaPlayer);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.to_school_1_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivCard2) { // 书
			click(v, 0);
		} else if(v == ivCard3) { // 笔
			click(v, 1);
		} else if(v == ivCard4) { // 尺子
			click(v, 2);
		} else if(v == ivCard5) { // 橡皮
			click(v, 3);
		} else if(v == ivCard7) { // 球
			click(v, 4);
			v.setVisibility(View.GONE);
			ivCard8.setBackground(animBall);
			animBall.start();
		}
	}

	private void click(View v, int index) {
		if(index != 4) {
			// 添加音效
			mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "to_school_voice.mp3", mMediaPlayer);
			setViewPosition(v, index + 7);
		} else {
			delayFinish(index);
		}
	}

	private void delayFinish(final int index) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					if(index == 4) {
						Thread.sleep(3300);
					} else {
						Thread.sleep(500);
					}
					handler.sendMessage(handler.obtainMessage(0));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 0:
//					finish();
					delayFinish();
					break;
				case 1:
					MediaCommon.playEgGood(mContext);
					break;
				case 2:
					finish();
					break;
				default:
					break;
			}
		};
	};

	private void setRecylePic() {
		animBall = new AnimationDrawable();
		for (int i = 1; i <= 11; i++) {
			animBall.addFrame(CommonBitmap.drawableBmp(imgPath, "to_school_ball_" + i),
					200);
		}
		// 设置为循环播放
		animBall.setOneShot(true);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		MediaPlayerBiz.clearMediaPlayer(mMediaPlayer);
	}

	private void delayFinish() {
		startActivity(new Intent(mContext, CommonGoodActivity.class));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					handler.sendMessage(handler.obtainMessage(1));
					Thread.sleep(2200);
					handler.sendMessage(handler.obtainMessage(2));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
}
