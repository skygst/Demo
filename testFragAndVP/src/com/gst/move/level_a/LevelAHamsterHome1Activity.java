package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
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
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;
import com.gst.move.custom.AnimationImageView;
import com.gst.move.utils.BaseCommon;

/**
 *  Hamster home 1
 * @author
 *
 */
public class LevelAHamsterHome1Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private AnimationImageView ivCard2;
	private ImageView ivCard1, ivShou;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private String imgPath;
	private VideoBiz biz;
	private String[] picArray = new String[] {
			"hamster_home1_bg"
	};
	private Context mContext;
	private AnimationDrawable animHamsterCycle, animHamsterImplement;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hamster_home_1);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelAHamsterHome1Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		imgPath = ConstantEp.path_level_a_game + "images/";
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (AnimationImageView) findViewById(R.id.iv_card_2);
		ivShou = (ImageView) findViewById(R.id.iv_shou);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setBackground(BaseCommon.drawableChange(imgPath, "hamster_cycle_1"));
		
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 0);
		setViewPosition(ivShou, 0);
		threadCycleAnimation();
		threadImplementAnimation();
		ivCard1.setOnClickListener(this);
		// 播放题目读音
		delayPlay("hamster_home_problem_1.mp3");
	}
	
	private void delayPlay(final String mp3Name) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(500);
					handler.sendMessage(handler.obtainMessage(2, mp3Name));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.hamster_home1_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivCard1) { // 鸟窝 
			CommonAnimation.stopAnimation(ivShou);
			ivShou.setVisibility(View.GONE);
			ivCard1.setVisibility(View.GONE);
			ivCard2.setVisibility(View.VISIBLE);
			// 监听动画是否播放完
			ivCard2.loadAnimation(new com.gst.move.custom.AnimationImageView.OnFrameAnimationListener() {
				
				@Override
				public void onStart() {
					delayPlay("hamster_home_hi.mp3");
				}
				
				@Override
				public void onEnd() {
//					finish();
					delayFinish();
				}
			}, animHamsterImplement);
		}
	}
	
	private void threadCycleAnimation() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				setRecylePic();
				handler.sendMessage(handler.obtainMessage(0));
			}
		}).start();
	}
	private void threadImplementAnimation() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				setImplementPic();
				handler.sendMessage(handler.obtainMessage(1));
			}
		}).start();
	}
	
	Handler handler = new Handler() {
		
		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				ivCard1.setBackground(animHamsterCycle);
				animHamsterCycle.start();
				ivShou.setVisibility(View.VISIBLE);
				CommonAnimation.startShouAnimation(ivShou);
				break;
			case 1:
				ivCard2.setBackground(animHamsterImplement);
				break;
			case 2:
				// 播放题目读音
				String mp3Name = (String) msg.obj;
				MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + mp3Name, null);
				break;
				case 3:
					MediaCommon.playEgGood(mContext);
					break;
				case 4:
					finish();
					break;
			default:
				break;
			}
		};
	};
	
	private void setRecylePic() {
		animHamsterCycle = new AnimationDrawable();
		for (int i = 1; i <= 7; i++) {
			animHamsterCycle.addFrame(CommonBitmap.drawableBmp(imgPath, "hamster_cycle_" + i),
					200);
		}
		// 设置为循环播放
		animHamsterCycle.setOneShot(false);
	}
	
	private void setImplementPic() {
		animHamsterImplement = new AnimationDrawable();
		for (int i = 1; i <= 19; i++) {
			animHamsterImplement.addFrame(CommonBitmap.drawableBmp(imgPath, "hamster_implement_" + i),
					200);
		}
		// 设置为循环播放
		animHamsterImplement.setOneShot(true);
	}

	private void delayFinish() {
		startActivity(new Intent(mContext, CommonGoodActivity.class));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					handler.sendMessage(handler.obtainMessage(3));
					Thread.sleep(2200);
					handler.sendMessage(handler.obtainMessage(4));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
}
