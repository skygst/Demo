package com.gst.move.test;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.data.FixedPositionAsia;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;
import com.gst.move.utils.CacheSp;

/**
 *  磨耳朵音频、视频选择界面
 * @author
 *
 */
public class WearSelActivity extends BaseActivity implements OnClickListener {

	private Context mContext;
	private ImageView ivBack, ivMusic, ivListenMusic, ivVideo, ivLookVideo;
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
		ivMusic = (ImageView) findViewById(R.id.iv_music);
		ivListenMusic = (ImageView) findViewById(R.id.iv_listen_music);
		ivVideo = (ImageView) findViewById(R.id.iv_video);
		ivLookVideo = (ImageView) findViewById(R.id.iv_look_video);
	
		setViewPosition(ivMusic, 0);
		setViewPosition(ivListenMusic, 1);
		setViewPosition(ivVideo, 2);
		setViewPosition(ivLookVideo, 3);
		biz.setViewPosition(ivBack, 0, FixedPosition.common_position, scaleQPW,
				scaleQPH, 0, 0, 1.0f);
		ivBack.setOnClickListener(this);
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
			Toast.makeText(mContext, "点击---听音乐", Toast.LENGTH_LONG).show();
			biz.gotoActivity(mContext, WearEarActivity.class);
		} else if(v == ivVideo || v == ivLookVideo) { // 看动画
			long time1 = System.currentTimeMillis();
			biz.gotoActivity(mContext, WearVideoAlbumsActivity.class);
			Toast.makeText(mContext, "点击+++看动画", Toast.LENGTH_LONG).show();
			long time2 = System.currentTimeMillis();
			System.out.println("time :" + (time2-time1));
		}  
	}
	
}
