package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPositionLevelA;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 *  the big cat
 * @author
 *
 */
public class LevelABigCat1Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] {
			"big_cat_bg", "big_cat_small", "big_cat_big"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.big_cat_1);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelABigCat1Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		imgPath = ConstantEp.path_level_a_game + "images/";
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[1]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[2]));
		
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		// 播放题目读音
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "big_cat_problem_1.mp3", null);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.big_cat_1_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivCard1) { // 鸟窝 
			click(v, 0);
		} else if(v == ivCard2) { // 床
			click(v, 1);
		}
	}
	
	private boolean isCanClick = true;
	
	private void click(View v, int index) {
		if(isCanClick) {
			if(index == 1) {
				isCanClick = false;
				MediaCommon.playEgGood(mContext);
				ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[2] + "_open"));
				delayProcess(2400, 1);
			} else {
				MediaCommon.playEgError(mContext);
				ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[1] + "_open"));
				delayProcess(800, 0);
				CommonAnimation.shakeAnimation(mContext, v);
			} 
		}
	}
	
	// 延迟处理
	private void delayProcess(final long time, final int caseId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					if(caseId == 1) {
						Thread.sleep(500);
						handler.sendMessage(handler.obtainMessage(2));
					}
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
				ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[1]));
				break;
			case 1:
				finish();
				break;
				case 2:
					startActivity(new Intent(mContext, CommonGoodActivity.class));
					break;
			default:
				break;
			}
		}

	};
	
}
