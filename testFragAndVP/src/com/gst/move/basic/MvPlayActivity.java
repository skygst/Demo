package com.gst.move.basic;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.base.MvVideo;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.MyToast;
import com.gst.move.R;
import com.gst.move.model.CommonUtil;

public class MvPlayActivity extends BaseActivity implements OnClickListener {

	private MyVideoView video;
	private String path;
	private ImageView ivBack;
	private MediaPlayer mMediaPlayer;
	private Context mContext;
	private float scaleQPW = 1.0f,scaleQPH = 1.0f;
	private VideoBiz mVideoBiz;
	private int level = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mv_play);
		
		mContext = MvPlayActivity.this;
		scaleQPW = (width/1280.0f);
		scaleQPH = (height/720.0f);
		level = getIntent().getExtras().getInt("level");
		// level = 5;
		path = CommonUtil.mvPath(mContext, level);
		setView();
	}

	private void setView() {
		video = (MyVideoView) findViewById(R.id.video_play);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		mVideoBiz = new VideoBiz();
		mVideoBiz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
		if (path != null && !path.equals("")) {
			playVideo(path);
		} else {
			// 重新获取数据
			threadMvVideo();
		}
		ivBack.setOnClickListener(this);
	}
	
	public void playVideo(String path) {
		if (path != null && !path.equals("")) {
			video.setVideoURI(Uri.parse(path));
			video.requestFocus();
			video.start();
			video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					// 播放结束后的动作
					 finish();
				}
			});

			video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer arg0) {
				}
			});
			
			video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					return true;
				}
			});
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setKeepScreenOn(true);
		if (null != video) {
			video.start();
		}
		if(null != mMediaPlayer) {
			mMediaPlayer.start();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		getWindow().getDecorView().setKeepScreenOn(false);
		if (null != video) {
			video.pause();
		}
		
		if(null != mMediaPlayer) {
			mMediaPlayer.pause();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		clearVideo();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			clearVideo();
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void clearVideo() {
		try {
			if (null != video) {
				video.stopPlayback();
				video = null;
			}
			if(null != mMediaPlayer) {
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
		}catch(Exception e) {
            
        } 
		
	}

	@Override
	public void onClick(View v) {
		if(v == ivBack) {
			clearVideo();
			this.finish();
		}
	}
	
	private void threadMvVideo() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				new MvVideo().videoData(mContext);
			}
		}).start();
	}
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				String path = CommonUtil.mvPath(mContext, level);
				if (path != null && !path.equals("")) {
					playVideo(path);
				} else {
					new MyToast().showTextToast(mContext, "MV路径获取错误");
				}
				break;

			default:
				break;
			}
		}
		
	};
	
}
