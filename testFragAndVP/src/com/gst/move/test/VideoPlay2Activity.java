package com.gst.move.test;

import java.io.File;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ebodoo.raz.BaseActivity;
import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;

public class VideoPlay2Activity extends BaseActivity {

	private VideoView video;
	private MediaController mController;
	private Context mContext;
	private String path, albumsName, name, videoId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		final IntentFilter homeFilter = new IntentFilter(
				Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		registerReceiver(homePressReceiver, homeFilter);

		setContentView(R.layout.activity_video_play);
		
		init();
		setView();
	}

	private void init() {
		mContext = VideoPlay2Activity.this;
		
//		path = getIntent().getExtras().getString("path");
//		albumsName = getIntent().getExtras().getString("albumName");
//		name = getIntent().getExtras().getString("name");
//		videoId = getIntent().getExtras().getString("videoId");
		
		File sdcardDir = Environment.getExternalStorageDirectory();
		path = "file://" + sdcardDir.getPath() + "/asia_level5_0.mp4";
		path = "http://7xopla.com2.z0.glb.qiniucdn.com/01aitangxiangdeduola.mp4";
		System.out.println("VideoPlay2Activity path :" + path);
	}

	private void setView() {
		video = (VideoView) findViewById(R.id.video_play);
		
		/*video.setVideoPath(path);
		  MediaController mediaController = new MediaController(this);
		  video.setMediaController(mediaController);
		  video.start();*/
//		path = "http://7xo65p.media1.z0.glb.clouddn.com/basic_level01.mp4";
//		 Uri uri = Uri.parse(path);  
//		 video.setMediaController(new MediaController(this));  
//		 video.setVideoURI(uri);  
//	        //videoView.start();  
//		 video.requestFocus();  
		
		if (path != null && !path.equals("")) {
			mController = new MediaController(this);
			video.setVideoURI(Uri.parse(path));
			video.setMediaController(mController);
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
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setKeepScreenOn(true);
		if (null != video) {
			video.resume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		getWindow().getDecorView().setKeepScreenOn(false);
		if (null != video) {
			video.pause();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (null != video) {
			video.stopPlayback();
			video = null;
		}
		if (homePressReceiver != null) {
			try {
				unregisterReceiver(homePressReceiver);
			} catch (Exception e) {

			}
		}
	}
	

	private final BroadcastReceiver homePressReceiver = new BroadcastReceiver() {
		final String SYSTEM_DIALOG_REASON_KEY = "reason";
		final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
				String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
				if (reason != null
						&& reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
					// 自己随意控制程序，关闭...
//					SharePreferCommon.setVideoPath(mContext, path);
//					if (video != null) {
//						int seekToPosition = video.getCurrentPosition();
//						if (seekToPosition != 0) {
//							SharePreferCommon.setVideoPosition(mContext,
//									seekToPosition);
//						}
//					}

				}
			}
		}
	};

}
