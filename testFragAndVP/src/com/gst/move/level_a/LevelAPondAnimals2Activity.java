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
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPositionLevelA;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 *  Pond animals
 * @author
 *
 */
public class LevelAPondAnimals2Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MyVideoView video;
	private String path, imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] {
			"pond_animals_bg", "pond_animals_four", "pond_animals_five"
	};
	private int[] randArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pond_animals_2);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelAPondAnimals2Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		path = ConstantEp.path_level_a_game + "pond_animals_2.mp4/";
		imgPath = ConstantEp.path_level_a_game + "images/";
		randArray = BaseCommon.getList(2); 
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		
		video = (MyVideoView) findViewById(R.id.video_play);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[0] + 1]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[1] + 1]));
		
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		// 播放题目读音
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "pond_animals_problem_2.mp3", null);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.pond_animals_position,
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
			if(randArray[index] == 1) {
				isCanClick = false;
				// 去播放视频
				playVideo();
			} else {
				MediaCommon.playEgError(mContext);
				CommonAnimation.shakeAnimation(mContext, v);
			}
		}
	}
	
	private void playVideo() {
		rlLayout.setVisibility(View.GONE);
		video.setVisibility(View.VISIBLE);
		biz.playVideo(video, path);
		video.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				// 处理播放完后的情况
				delayFinish();
			}
		});
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
