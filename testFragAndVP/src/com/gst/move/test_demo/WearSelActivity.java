package com.gst.move.test_demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.data.FixedPositionAsia;
import com.ebodoo.raz.utils.CommonBitmap;
import com.gst.move.R;

/**
 *  磨耳朵音频、视频选择界面
 */
public class WearSelActivity extends BaseActivity implements OnClickListener {

	private Context mContext;
	private RelativeLayout rlSpeakLoudly, rlMusic, rlVideo;
	private ImageView ivBack;
	private ClickImageView ivMusic, ivVideo, ivListenMusic, ivLookVideo, ivSpeakLoudly;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wear_sel);
		
		init();
		setView();
	}
	
	private void init() {
		mContext = WearSelActivity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
	}

	private void setView() {
		ivBack = (ImageView) findViewById(R.id.iv_back);
		rlSpeakLoudly = (RelativeLayout) findViewById(R.id.rl_speak_loudly);
		rlMusic = (RelativeLayout) findViewById(R.id.rl_music);
		rlVideo = (RelativeLayout) findViewById(R.id.rl_video);
		CommonBitmap.changeDrawable(mContext, rlMusic, R.drawable.music_bg);
		CommonBitmap.changeDrawable(mContext, rlVideo, R.drawable.video_bg);
		
		ivMusic = (ClickImageView) findViewById(R.id.iv_music);
		ivListenMusic = (ClickImageView) findViewById(R.id.iv_listen_music);
		ivVideo = (ClickImageView) findViewById(R.id.iv_video);
		ivLookVideo = (ClickImageView) findViewById(R.id.iv_look_video);
		ivSpeakLoudly = (ClickImageView) findViewById(R.id.iv_speak_loudly);

		setViewPosition(rlSpeakLoudly, 0);
		setViewPosition(rlMusic, 1);
		setViewPosition(rlVideo, 2);
		setViewPosition(ivSpeakLoudly, 3);
		setViewPosition(ivMusic, 4);
		setViewPosition(ivListenMusic, 5);
		setViewPosition(ivVideo, 6);
		setViewPosition(ivLookVideo, 7);


		CommonBitmap.changeDrawable(mContext, ivMusic, R.drawable.icon_music);
		CommonBitmap.changeDrawable(mContext, ivListenMusic, R.drawable.listen_music);
		CommonBitmap.changeDrawable(mContext, ivVideo, R.drawable.icon_video);
		CommonBitmap.changeDrawable(mContext, ivLookVideo, R.drawable.look_video);
		
		biz.setViewPosition(ivBack, 0, FixedPosition.common_position, scaleQPW,
				scaleQPH, 0, 0, 1.0f);

		rlMusic.setBackgroundColor(getResources().getColor(R.color.yellow4));
		rlVideo.setBackgroundColor(getResources().getColor(R.color.green3));
		ivBack.setOnClickListener(this);
		rlSpeakLoudly.setOnClickListener(this);
		ivSpeakLoudly.setOnClickListener(this);
		ivMusic.setOnClickListener(this);
		ivListenMusic.setOnClickListener(this);
		ivVideo.setOnClickListener(this);
		ivLookVideo.setOnClickListener(this);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAsia.wear_sel_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivBack) { // 返回
			finish();
		} else if(v == ivMusic || v == ivListenMusic) { // 听儿歌
//			biz.gotoActivity(mContext, WearEarActivity.class);
		} else if(v == ivVideo || v == ivLookVideo) { // 看动画
//			biz.gotoActivity(mContext, WearVideoAlbumsActivity.class);
		} else if(v == rlSpeakLoudly || v == ivSpeakLoudly) { // 磨耳大声说
			startActivity(new Intent(mContext, SpeakLoudlyActivity.class));
		}
	}
	
}
