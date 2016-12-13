package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPositionLevelA;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;

/**
 * Bird goes home
 * @author
 */
public class LevelABirdGoesHomeActivity extends BaseActivity implements
		View.OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MyVideoView video;
	private String path, imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] { "bird_gose_home_bg",
			"bird_gose_homenest", "bird_gose_homebed", "bird_gose_homebird" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT); 
		setContentView(R.layout.bird_goes_home);

		init();
		setView();
	}

	private void init() {
		mContext = LevelABirdGoesHomeActivity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		path = ConstantEp.path_level_a_game + "bird_goes_home.mp4/";
		imgPath = ConstantEp.path_level_a_game + "images/";

	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);

		video = (MyVideoView) findViewById(R.id.video_play);

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
		video = biz.prepareVideo(video, path);
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		// 播放题目读音
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "bird_goes_home_problem_2.mp3", null);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.bird_goes_home_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	private void playVideo() {
		video.setVisibility(View.VISIBLE);
		video.start();
		video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mediaPlayer) {
			}
		});
		video.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer arg0) {
				// 处理播放完后的情况
//				finish();
				delayFinish();
			}
		});
	}

	@Override
	public void onClick(View view) {
		if(view == ivCard1) {
			playVideo();
		} else if(view == ivCard2){
			MediaCommon.playEgError(mContext);
			CommonAnimation.shakeAnimation(mContext, view);
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
