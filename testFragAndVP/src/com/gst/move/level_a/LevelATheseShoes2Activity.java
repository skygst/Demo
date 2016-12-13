package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
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
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 *  These shoes 2
 * @author
 *
 */
public class LevelATheseShoes2Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] {
			"these_shoes_2_bg", "these_shoes_2_snack", "these_shoes_2_lailai", "these_shoes_2_px", "these_shoes_2_shoes"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.these_shoes_2);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelATheseShoes2Activity.this;
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
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[1]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[2]));
		ivCard3.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[3]));
		ivCard4.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[4]));
		
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 2);
		setViewPosition(ivCard4, 3);
		
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		ivCard3.setOnClickListener(this);
		// 播放题目读音
		mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game +
				"these_shoes_problem_2.mp3", mMediaPlayer);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.these_shoes_2_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}
	
	private boolean isCanClick = true;
	
	@Override
	public void onClick(View v) {
		if(v == ivCard1) {
			click(v, 0);
		} else if(v == ivCard2) {
			click(v, 1);
		} else if(v == ivCard3) {
			click(v, 2);
		}
	}
	
	private void click(View v, int index) {
		if(isCanClick) {
			ivCard4.setVisibility(View.GONE);
			if(index == 1) {
				mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game +
						"my_shoes.mp3", mMediaPlayer);
				showView((ImageView) v, index, true);
			} else {
				mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game +
						"not_mine.mp3", mMediaPlayer);
				showView((ImageView) v, index, false);
			}
		}
		
	}
	
	private void showView(ImageView ivCard, int index, boolean isFinish) {
		ivCard.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[index + 1] + "_chuan"));
		if(isFinish) {
			delayFinish(1500, 0, ivCard, index);
		} else {
			delayFinish(1000, 1, ivCard, index);
		}
	}
	
	private void delayFinish(final long time, final int caseId, final ImageView ivCard, final int index) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(time);
					if(caseId == 0) {
						handler.sendMessage(handler.obtainMessage(0));
					} else {
						Object[] obj = new Object[2];
						obj[0] = ivCard;
						obj[1] = index;
						handler.sendMessage(handler.obtainMessage(1, obj));
					}
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
				delayFinish();
				break;
			case 1:
				Object[] obj = (Object[]) msg.obj;
				ImageView ivCard = (ImageView) obj[0];
				int index = (Integer) obj[1];
				ivCard4.setVisibility(View.VISIBLE);
				ivCard.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[index + 1]));
				break;
				case 2:
					finish();
					break;
			default:
				break;
			}
		};
	};
	

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
					Thread.sleep(2200);
					handler.sendMessage(handler.obtainMessage(2));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
