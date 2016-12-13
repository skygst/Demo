package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
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
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 *  My room 2
 * @author
 *
 */
public class LevelAMyRoom2Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] {
			"my_room_bg", "my_room_ball", "my_room_slide", "my_room_kite", 
			"my_room_duck", "my_room_aircraft", "my_room_bear"
	};
	private int[] randArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_room_2);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelAMyRoom2Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		imgPath = ConstantEp.path_level_a_game + "images/";
		randArray = BaseCommon.getList(6); 
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) findViewById(R.id.iv_card_4);
		ivCard5 = (ImageView) findViewById(R.id.iv_card_5);
		ivCard6 = (ImageView) findViewById(R.id.iv_card_6);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[0] + 1]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[1] + 1]));
		ivCard3.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[2] + 1]));
		ivCard4.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[3] + 1]));
		ivCard5.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[4] + 1]));
		ivCard6.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[5] + 1]));
		
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 2);
		setViewPosition(ivCard4, 3);
		setViewPosition(ivCard5, 4);
		setViewPosition(ivCard6, 5);
		
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		ivCard3.setOnClickListener(this);
		ivCard4.setOnClickListener(this);
		ivCard5.setOnClickListener(this);
		ivCard6.setOnClickListener(this);
		// 播放题目读音
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "my_room_problem_2.mp3", null);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.my_room_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivCard1) {
			click(v, 0);
		} else if(v == ivCard2) {
			click(v, 1);
		} else if(v == ivCard3) {
			click(v, 2);
		} else if(v == ivCard4) {
			click(v, 3);
		} else if(v == ivCard5) {
			click(v, 4);
		} else if(v == ivCard6) {
			click(v, 5);
		}
	}
	
	private boolean isCanClick = true;
	
	private void click(View v, int index) {
		if(isCanClick) {
			if(randArray[index] == 0) {
				isCanClick = false;
				// View放大
				CommonAnimation.startShouAnimation22((ImageView)v);
				MediaCommon.playEgGood(mContext);
				// TODO 延迟1s关闭
				delayFinish();
			} else {
				MediaCommon.playEgError(mContext);
				CommonAnimation.shakeAnimation(mContext, v);
			}
		}
	}
	
	private void delayFinish() {
		startActivity(new Intent(mContext, CommonGoodActivity.class));
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2800);
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
				finish();
				break;

			default:
				break;
			}
		};
	};
	
}
