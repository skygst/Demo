package com.ebodoo.raz.video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.database.ContactInjfoDao;
import com.ebodoo.raz.database.MyVideoHistoryHelper;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;

public class WearVideoPlayActivity extends BaseActivity {

	private VideoView video;
	private MediaController mController;
	private Context mContext;
	private ContactInjfoDao mDao;
	private String path, albumsName, name, videoId;
	private Cursor cursor;

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
		mContext = WearVideoPlayActivity.this;
		mDao = new ContactInjfoDao(mContext);
//		path = getIntent().getExtras().getString("path");
//		albumsName = getIntent().getExtras().getString("albumName");
//		name = getIntent().getExtras().getString("name");
//		videoId = getIntent().getExtras().getString("videoId");
		path = "http://my.tv.sohu.com/us/63368717/31767110.shtml";
		MyVideoHistoryHelper helper = new MyVideoHistoryHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
		cursor = db.rawQuery("select * from videoHistory", null);
	}

	private void setView() {
		video = (VideoView) findViewById(R.id.video_play);
		if (path != null && !path.equals("")) {
			mController = new MediaController(this);
			video.setVideoURI(Uri.parse(path));
			video.setMediaController(mController);
			video.requestFocus();
			video.start();
			video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				@Override
				public void onCompletion(MediaPlayer mp) {
					processDateUtils(0);
					// 播放结束后的动作
					finish();
				}
			});

			video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

				@Override
				public void onPrepared(MediaPlayer arg0) {
					if (cursor != null) {
						String albumNameValue = mDao.findAlbumsFromVideoId(videoId);
						if (!StringBiz.isEmpty(albumNameValue)) {
							String time = mDao.findPlayTimeFromVideoId(videoId);
							if (!StringBiz.isEmpty(time)
									&& new BaseCommon().isNumeric(time)) {
								int seekToPos = Integer.valueOf(time)
										.intValue();
								if (seekToPos != 0) {
									video.seekTo(seekToPos);
								}
							}
						}
					}
//					cursor.close();
				}
			});
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			int seekToPosition = video.getCurrentPosition();
			processDateUtils(seekToPosition);
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
		cursor.close();
	}
	
	private void processDateUtils(int currentPosition) {
		String albumNameValue = mDao.findAlbumsFromVideoId(videoId);
		if(!StringBiz.isEmpty(albumNameValue)) {
			mDao.deleteDate(videoId);
		}
		mDao.addDate(albumsName, videoId, name, "", path, "" + currentPosition);
		if(cursor != null) {
			int count = cursor.getCount();
			if(count >= 9) { // 删除第一个
				int num = 0;
				while (cursor.moveToNext() && num == 0) {
					num++;
	                String videoId = cursor.getString(cursor.getColumnIndex("videoId"));
	                mDao.deleteDate(videoId);
	            }
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
