package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
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
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;

/**
 *  He runs
 * @author
 *
 */
public class LevelAHeRuns2Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] {
			"he_runs_bg", "he_runs_book", "he_runs_apple"
	};
	private boolean isCanClick = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.he_runs_2);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelAHeRuns2Activity.this;
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
		ivCard1.setBackground(BaseCommon.drawableChange(imgPath, picArray[1]));
		ivCard2.setBackground(BaseCommon.drawableChange(imgPath, picArray[2]));
		
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		// 播放题目读音
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "he_runs_problem_2.mp3", null);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.he_runs_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivCard1) { // 书
			if(isCanClick) {
				isCanClick = false;
				//  画外音I can buy  books.
				playVoiceover(ConstantEp.path_level_a_game + "he_runs_2.mp3");
			}
		} else if(v == ivCard2) { // 苹果
			MediaCommon.playEgError(mContext);
			CommonAnimation.shakeAnimation(mContext, v);
		}
	}
	
	// 播放画外音
	private void playVoiceover(String path) {
		try {
			MediaPlayer mMediaPlayer = null;
			mMediaPlayer = MediaPlayerBiz.playSoundMusic(path, mMediaPlayer);
			mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer arg0) {
					// TODO 跳转到 鼓励界面---good
					delayFinish();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void delayFinish() {
		startActivity(new Intent(mContext, CommonGoodActivity.class));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					handler.sendMessage(handler.obtainMessage(0));
					Thread.sleep(2200);
					handler.sendMessage(handler.obtainMessage(1));
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
					MediaCommon.playEgGood(mContext);
					break;
				case 1:
					finish();
					break;
			}
		};
	};

}
