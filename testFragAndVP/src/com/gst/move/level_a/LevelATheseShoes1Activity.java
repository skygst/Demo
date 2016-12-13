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
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 *  These shoes 1
 * @author
 *
 */
public class LevelATheseShoes1Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivEffect;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] {
			"these_shoes_bg", "these_shoes_red", "these_shoes_yellow", "these_shoes_blue"
	};
	private boolean[] status = new boolean[] {false, false, false};
	private AnimationDrawable animEffect;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.these_shoes_1);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelATheseShoes1Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		imgPath = ConstantEp.path_level_a_game + "images/";
		threadEffectAnimation();
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivEffect = (ImageView) findViewById(R.id.iv_effect);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[1]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[2]));
		ivCard3.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[3]));
		
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 2);
		
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		ivCard3.setOnClickListener(this);
		// 播放题目读音
		mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game +
				"these_shoes_problem_1.mp3", mMediaPlayer);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.these_shoes_1_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}
	
	@Override
	public void onClick(View v) {
		if(v == ivCard1) {
			click(0);
		} else if(v == ivCard2) {
			click(1);
		} else if(v == ivCard3) {
			click(2);
		}
	}
	
	private void click(int index) {
		ivEffect.clearAnimation();
		setViewPosition(ivEffect, index + 3);
		ivEffect.setBackground(animEffect);
		animEffect.start();
		
		status[index] = true;
		mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game +
				picArray[index + 1] + ".mp3", mMediaPlayer);
		judgeIsFinish();
	}
	
	// 判断是否完成
	private void judgeIsFinish() {
		boolean isFinish = true;
		for(int i=0; i<status.length; i++) {
			if(!status[i]) {
				isFinish = false;
				break;
			}
		}
		if(isFinish)
			delayFinish();
	}
	
	private void delayFinish() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2500);
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
//				finish();
				MediaCommon.playEgGood(mContext);
				delayFinish2();
				break;
				case 1:
				finish();
				break;
			default:
				break;
			}
		};
	};
	
	private void threadEffectAnimation() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				setEffectPic();
			}
		}).start();
	}
	
	private void setEffectPic() {
		animEffect = new AnimationDrawable();
		for (int i = 1; i <= 10; i++) {
			animEffect.addFrame(CommonBitmap.drawableBmp(imgPath, "flash_effect_" + i),
					200);
		}
		// 设置为循环播放
		animEffect.setOneShot(false);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ivEffect.clearAnimation();
		MediaPlayerBiz.clearMediaPlayer(mMediaPlayer);
	}

	private void delayFinish2() {
		startActivity(new Intent(mContext, CommonGoodActivity.class));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2200);
					handler.sendMessage(handler.obtainMessage(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
