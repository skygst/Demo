package com.gst.move.basic;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;
import com.gst.move.model.CommonUtil;

public class LevelGameActivity extends BaseActivity implements OnClickListener {

	private Context mContext;
	private MediaPlayer mMediaPlayer = null;
	private ImageButton ibtnCard1, ibtnCard2, ibtnCard3;
	private ImageView ivPic1, ivPic2, ivPic3, ivDashDot1, ivDashDot2, ibtnBack;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private int xoffset_2 = 0, yoffset_2 = 0;
	private float suoxiao_2 = 1.0f;
	private VideoBiz biz;
	private boolean isEg = false;
	private int level = 0, island = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_game);

		initializationData();
		setView();
	}

	private void initializationData() {
		mContext = LevelGameActivity.this;
		isEg = BaseCommon.getIsEg(mContext);
		level = getIntent().getExtras().getInt("level");
		island = getIntent().getExtras().getInt("island");
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
	}

	private void setView() {
		ibtnBack = (ImageView) findViewById(R.id.ibtn_back);
		ibtnCard1 = (ImageButton) findViewById(R.id.ibtn_card_1);
		ibtnCard2 = (ImageButton) findViewById(R.id.ibtn_card_2);
		ibtnCard3 = (ImageButton) findViewById(R.id.ibtn_card_3);
		ivPic1 = (ImageView) findViewById(R.id.pic_1);
		ivPic2 = (ImageView) findViewById(R.id.pic_2);
		ivPic3 = (ImageView) findViewById(R.id.pic_3);
		ivDashDot1 = (ImageView) findViewById(R.id.iv_dash_dot1);
		ivDashDot2 = (ImageView) findViewById(R.id.iv_dash_dot2);
		setLocation();
		showPicView();
		backStatus();
		ibtnBack.setOnClickListener(this);
		ibtnCard1.setOnClickListener(this);
		ibtnCard2.setOnClickListener(this);
		ibtnCard3.setOnClickListener(this);
	}

	private void showPicView() {
		String imgPath = "";
		if(island == 1) {
			imgPath = Constant.path_raz01_images;
		} else if(island == 2) {
			imgPath = Constant.path_raz02_images;
		} else if(island == 3) {
			imgPath = Constant.path_raz03_images;
		} else if(island == 4) {
			imgPath = Constant.path_raz04_images;
		}
		System.out.println("level :" + level);
		System.out.println("island :" + island);
		ivPic1.setImageDrawable(BaseCommon.drawableChange(imgPath, "level" + level + "_1"));
		ivPic2.setImageDrawable(BaseCommon.drawableChange(imgPath, "level" + level + "_2"));
		ivPic3.setImageDrawable(BaseCommon.drawableChange(imgPath, "level" + level + "_3"));
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 重新播放
		playMusic(R.raw.bgmusic_select_level);
	}

	private void setLocation() {
		biz.setViewPositionNoSuoxiao(ibtnBack, 6,
				FixedPosition.level_common_position, (width / 1920.0f),
				(height / 1080.0f));
		setViewPosition(ivPic1, 0);
		setViewPosition(ivPic2, 1);
		setViewPosition(ivPic3, 2);
		setViewPosition(ibtnCard1, 3);
		setViewPosition(ibtnCard2, 4);
		setViewPosition(ibtnCard3, 5);
		setViewPosition(ivDashDot1, 6);
		setViewPosition(ivDashDot2, 7);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPosition.gameLevelPosition, scaleQPW,
				scaleQPH, xoffset_2, yoffset_2, suoxiao_2);
	}

	private void backStatus() {
		biz.setViewPositionNoSuoxiao(ibtnBack, 0,
				FixedPosition.common_position, (width / 1280.0f),
				(height / 720.0f));
	}

	@Override
	protected void onPause() {
		super.onPause();
		getWindow().getDecorView().setKeepScreenOn(false);
		if (mMediaPlayer != null) {
			mMediaPlayer.pause();
		}
		MediaCommon.pauseMediaplay();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		if (v == ibtnCard1 || v == ibtnCard2 || v == ibtnCard3) {
			clearMedia();
		}
		if (v == ibtnBack) {
			clearMedia();
			this.finish();
		} else if (v == ibtnCard1) { // 学习
			gotoLevelActivity();
		} else if (v == ibtnCard2) { // say it
			startActivity(new Intent(mContext, SayItActivity.class).putExtra(
					"level", level));
		} else if (v == ibtnCard3) { // MV
			if(CommonUtil.wifiInfo(mContext)) {
//				startActivity(new Intent(mContext, MvPlayActivity.class).putExtra(
//						"level", level));
			} else {
				if (isEg) {
					Toast.makeText(mContext,
							"Please download in WiFi environment",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(mContext, "请在WIFI环境下进行下载",
							Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	private void gotoLevelActivity() {
		Class<?> classes = CommonUtil.classesView(level);
		if (classes != null) {
			startActivity(new Intent(mContext, classes));
		}
	}

	private void playMusic(int resId) {
		mMediaPlayer = MediaPlayer.create(mContext, resId);
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (mMediaPlayer != null) {
					mMediaPlayer.start();
					mMediaPlayer.setLooping(true);
				}
			}
		}).start();
	}

	private void clearMedia() {
		try {
			if (mMediaPlayer != null) {
				mMediaPlayer.pause();
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
