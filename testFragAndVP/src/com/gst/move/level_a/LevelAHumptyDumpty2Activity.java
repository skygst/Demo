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
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 * Humpty Dumpty
 * 
 * @author
 * 
 */
public class LevelAHumptyDumpty2Activity extends BaseActivity implements
		OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] { "humpty_dumpty_bg",
			"humpty_dumpty_man_3", "humpty_dumpty_man_2", "humpty_dumpty_man_1" };
	private boolean[] status = new boolean[] { false, false, false };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.humpty_dumpty_2);

		init();
		setView();
	}

	private void init() {
		mContext = LevelAHumptyDumpty2Activity.this;
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

		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon
				.drawableChange(imgPath, picArray[1]));
		ivCard2.setImageDrawable(BaseCommon
				.drawableChange(imgPath, picArray[2]));
		ivCard3.setImageDrawable(BaseCommon
				.drawableChange(imgPath, picArray[3]));

		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 2);

		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		ivCard3.setOnClickListener(this);
		// 播放题目读音
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game
				+ "humpty_dumpty_problem_2.mp3", null);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i,
				FixedPositionLevelA.humpty_dumpty_2_position, scaleQPW,
				scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivCard1) { // 3
			click(v, 0, "three");
		} else if (v == ivCard2) { // 2
			click(v, 1, "two");
		} else if (v == ivCard3) { // 1
			click(v, 2, "one");
		}
	}

	private void click(View v, int index, String mp3Name) {
		// 播放题目读音
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + mp3Name
				+ "_man.mp3", null);
		status[index] = true;
		judgeIsFinish();
	}

	// 判断是否完成
	private void judgeIsFinish() {
		boolean isFinish = true;
		for (int i = 0; i < status.length; i++) {
			if (!status[i]) {
				isFinish = false;
				break;
			}
		}
		if (isFinish)
			delayFinish();
	}

	private void delayFinish() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
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
				delayFinish2();
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

	private void delayFinish2() {
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
