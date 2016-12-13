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
 *  My dog
 * @author
 *
 */
public class LevelAMyDog2Activity extends BaseActivity implements OnClickListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard0, ivCard1, ivCard2, ivCard3;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MyVideoView video;
	private String path, imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] {
			"my_dog_bg", "my_dog_yun", "my_dog_bone", "my_dog_fish", "my_dog_dog"
	};
	private int[] randArray;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_dog_2);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelAMyDog2Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		path = ConstantEp.path_level_a_game + "my_dog_2.mp4/";
		imgPath = ConstantEp.path_level_a_game + "images/";
		randArray = BaseCommon.getList(2); 
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard0 = (ImageView) findViewById(R.id.iv_card_0);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		
		video = (MyVideoView) findViewById(R.id.video_play);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard0.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[1]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[0] + 2]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[randArray[1] + 2]));
		ivCard3.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[4]));
		
		setViewPosition(ivCard0, 0);
		setViewPosition(ivCard1, 1);
		setViewPosition(ivCard2, 2);
		setViewPosition(ivCard3, 3);
		
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		// 播放题目读音
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "my_dog_problem_2.mp3", null);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.my_dog_position,
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
			if(randArray[index] == 0) {
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
	Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 0:
//					fl2.setVisibility(View.GONE);
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
