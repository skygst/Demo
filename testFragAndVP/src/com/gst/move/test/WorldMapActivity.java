package com.gst.move.test;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.ImageBiz;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.data.FixedPositionRevision;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.MyToast;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.custom.MyHorizonScrollView;

/**
 * 世界地图
 * 
 * @author
 * 
 */
public class WorldMapActivity extends BaseActivity implements OnClickListener {

	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MyHorizonScrollView hslView;
	private ImageView ivCountry1, ivCountry2, ivCountry3, ivCountry4,
			ivCountry5, ivCountry6, ivCountry7, ivCountry8, ivCountry9,
			ivCountry10, ivCountry11, ivCountry12, ivCountry13, ivCountry14,
			ivCountry15, ivCountry16, ivCountry17, ivCountry18, ivCountry19,
			ivCountry20;
	private ImageView ivLock1, ivLock2, ivLock3, ivLock4, ivLock5, ivLock6, ivLock7, ivLock8, ivLock9, ivLock10, ivLock11,
				ivLock12, ivLock13, ivLock14, ivLock15, ivLock16, ivLock17, ivLock18, ivLock19, 
				ivLock20, ivLock21, ivLock22, ivLocateLock;
	private ImageView ivBack, ivChess1, ivChess2, ivChess3, ivChess4;
	private ImageView ivFlag1, ivFlag2, ivFlag3, ivFlag4, ivFlag5, ivFlag6,
			ivFlag7, ivFlag8, ivFlag9, ivFlag10, ivFlag11, ivFlag12, ivFlag13,
			ivFlag14, ivFlag15, ivFlag16, ivFlag17, ivFlag18, ivFlag19,
			ivFlag20;
	private ImageView[] ivCountrys;
	private ImageView[] ivFlags;
	private ImageView[] ivLock;
	private RelativeLayout rlLayout;
	private VideoBiz biz;
	private Context mContext;
	private MediaPlayer mMediaPlayer = null;
	private MediaPlayer mMediaPlayerSound = null;
	private int currentLevel = 0, clickCurrentLecel = -1;
	private String processValue = "";
	private boolean isCanMove = true;
	//新国家测试
	private int finishMaxCountryIndex = 22; // 最大开发完成国家索引
	private int status = 0; // 0:没有买过；1:可以玩到新加坡；2:购买了整个国家
	private boolean isAddWeinxin = false;
	private String wxhValue = "";
	private HandlerWorld handlerWorld;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.world_map);

		init();
		setView();
	}

	private void init() {
		mContext = WorldMapActivity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		biz = new VideoBiz();
//		isAddWeinxin = 
//		wxhValue = new ConfigBiz().ConfigParamsWeixin(mContext);
		wxhValue = "";
		if(!StringBiz.isEmpty(wxhValue)) {
			isAddWeinxin = true;
		} else {
			isAddWeinxin = false;
		}
		handlerWorld = new HandlerWorld(Looper.myLooper());
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		hslView = (MyHorizonScrollView) findViewById(R.id.hsl_view);
		ivCountry1 = (ImageView) findViewById(R.id.iv_country_1);
		ivCountry2 = (ImageView) findViewById(R.id.iv_country_2);
		ivCountry3 = (ImageView) findViewById(R.id.iv_country_3);
		ivCountry4 = (ImageView) findViewById(R.id.iv_country_4);
		ivCountry5 = (ImageView) findViewById(R.id.iv_country_5);
		ivCountry6 = (ImageView) findViewById(R.id.iv_country_6);
		ivCountry7 = (ImageView) findViewById(R.id.iv_country_7);
		ivCountry8 = (ImageView) findViewById(R.id.iv_country_8);
		ivCountry9 = (ImageView) findViewById(R.id.iv_country_9);
		ivCountry10 = (ImageView) findViewById(R.id.iv_country_10);
		ivCountry11 = (ImageView) findViewById(R.id.iv_country_11);
		ivCountry12 = (ImageView) findViewById(R.id.iv_country_12);
		ivCountry13 = (ImageView) findViewById(R.id.iv_country_13);
		ivCountry14 = (ImageView) findViewById(R.id.iv_country_14);
		ivCountry15 = (ImageView) findViewById(R.id.iv_country_15);
		ivCountry16 = (ImageView) findViewById(R.id.iv_country_16);
		ivCountry17 = (ImageView) findViewById(R.id.iv_country_17);
		ivCountry18 = (ImageView) findViewById(R.id.iv_country_18);
		ivCountry19 = (ImageView) findViewById(R.id.iv_country_19);
		ivCountry20 = (ImageView) findViewById(R.id.iv_country_20);
		ivChess1 = (ImageView) findViewById(R.id.iv_chess_1);
		ivChess2 = (ImageView) findViewById(R.id.iv_chess_2);
		ivChess3 = (ImageView) findViewById(R.id.iv_chess_3);
		ivChess4 = (ImageView) findViewById(R.id.iv_chess_4);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		ivFlag1 = (ImageView) findViewById(R.id.iv_flag_1);
		ivFlag2 = (ImageView) findViewById(R.id.iv_flag_2);
		ivFlag3 = (ImageView) findViewById(R.id.iv_flag_3);
		ivFlag4 = (ImageView) findViewById(R.id.iv_flag_4);
		ivFlag5 = (ImageView) findViewById(R.id.iv_flag_5);
		ivFlag6 = (ImageView) findViewById(R.id.iv_flag_6);
		ivFlag7 = (ImageView) findViewById(R.id.iv_flag_7);
		ivFlag8 = (ImageView) findViewById(R.id.iv_flag_8);
		ivFlag9 = (ImageView) findViewById(R.id.iv_flag_9);
		ivFlag10 = (ImageView) findViewById(R.id.iv_flag_10);
		ivFlag11 = (ImageView) findViewById(R.id.iv_flag_11);
		ivFlag12 = (ImageView) findViewById(R.id.iv_flag_12);
		ivFlag13 = (ImageView) findViewById(R.id.iv_flag_13);
		ivFlag14 = (ImageView) findViewById(R.id.iv_flag_14);
		ivFlag15 = (ImageView) findViewById(R.id.iv_flag_15);
		ivFlag16 = (ImageView) findViewById(R.id.iv_flag_16);
		ivFlag17 = (ImageView) findViewById(R.id.iv_flag_17);
		ivFlag18 = (ImageView) findViewById(R.id.iv_flag_18);
		ivFlag19 = (ImageView) findViewById(R.id.iv_flag_19);
		ivFlag20 = (ImageView) findViewById(R.id.iv_flag_20);
		
		ivLock1 = (ImageView) findViewById(R.id.iv_country_lock_1);
		ivLock2 = (ImageView) findViewById(R.id.iv_country_lock_2);
		ivLock3 = (ImageView) findViewById(R.id.iv_country_lock_3);
		ivLock4 = (ImageView) findViewById(R.id.iv_country_lock_4);
		ivLock5 = (ImageView) findViewById(R.id.iv_country_lock_5);
		ivLock6 = (ImageView) findViewById(R.id.iv_country_lock_6);
		ivLock7 = (ImageView) findViewById(R.id.iv_country_lock_7);
		ivLock8 = (ImageView) findViewById(R.id.iv_country_lock_8);
		ivLock9 = (ImageView) findViewById(R.id.iv_country_lock_9);
		ivLock10 = (ImageView) findViewById(R.id.iv_country_lock_10);
		ivLock11 = (ImageView) findViewById(R.id.iv_country_lock_11);
		ivLock12 = (ImageView) findViewById(R.id.iv_country_lock_12);
		ivLock13 = (ImageView) findViewById(R.id.iv_country_lock_13);
		ivLock14 = (ImageView) findViewById(R.id.iv_country_lock_14);
		ivLock15 = (ImageView) findViewById(R.id.iv_country_lock_15);
		ivLock16 = (ImageView) findViewById(R.id.iv_country_lock_16);
		ivLock17 = (ImageView) findViewById(R.id.iv_country_lock_17);
		ivLock18 = (ImageView) findViewById(R.id.iv_country_lock_18);
		ivLock19 = (ImageView) findViewById(R.id.iv_country_lock_19);
		ivLock20 = (ImageView) findViewById(R.id.iv_country_lock_20);
		ivLock21 = (ImageView) findViewById(R.id.iv_country_lock_21);
		ivLock22 = (ImageView) findViewById(R.id.iv_country_lock_22);
		
		ivLocateLock = (ImageView) findViewById(R.id.iv_locate_lock);
		
		setImgCountrys();
		setImgFlags();
		setImgLocks();
		showBg();
		biz.setViewPosition(ivBack, 0, FixedPosition.common_position, scaleQPW,
				scaleQPH, 0, 0, 1.0f);
		ivBack.setOnClickListener(this);
		
		setViewPosition(ivLocateLock, 24);
		ivLocateLock.setVisibility(View.INVISIBLE);
		
//		hslView.setForegroundDrawable(new ColorDrawable(android.R.color.transparent));  //设置前景
//		hslView.setDiffFactor(1.0f);
		delayMove();
	}
	
	private void showBg() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Looper.prepare();
				handlerWorld.sendMessage(handlerWorld.obtainMessage(1));
				Looper.loop();
			}
		}).start();
	}

	private void setImgLocks() {
		ivLock = new ImageView[22];
		ivLock[0] = ivLock1;
		ivLock[1] = ivLock2;
		ivLock[2] = ivLock3;
		ivLock[3] = ivLock4;
		ivLock[4] = ivLock5;
		ivLock[5] = ivLock6;
		ivLock[6] = ivLock7;
		ivLock[7] = ivLock8;
		ivLock[8] = ivLock9;
		ivLock[9] = ivLock10;
		ivLock[10] = ivLock11;
		ivLock[11] = ivLock12;
		ivLock[12] = ivLock13;
		ivLock[13] = ivLock14;
		ivLock[14] = ivLock15;
		ivLock[15] = ivLock16;
		ivLock[16] = ivLock17;
		ivLock[17] = ivLock18;
		ivLock[18] = ivLock19;
		ivLock[19] = ivLock20;
		ivLock[20] = ivLock21;
		ivLock[21] = ivLock22;
		
		for (int i = 0; i < ivLock.length; i++) {
			setViewLockPosition(ivLock[i], i);
			final int index = i;
		}
	}

	private void setImgFlags() {
		ivFlags = new ImageView[20];
		ivFlags[0] = ivFlag1;
		ivFlags[1] = ivFlag2;
		ivFlags[2] = ivFlag3;
		ivFlags[3] = ivFlag4;
		ivFlags[4] = ivFlag5;
		ivFlags[5] = ivFlag6;
		ivFlags[6] = ivFlag7;
		ivFlags[7] = ivFlag8;
		ivFlags[8] = ivFlag9;
		ivFlags[9] = ivFlag10;
		ivFlags[10] = ivFlag11;
		ivFlags[11] = ivFlag12;
		ivFlags[12] = ivFlag13;
		ivFlags[13] = ivFlag14;
		ivFlags[14] = ivFlag15;
		ivFlags[15] = ivFlag16;
		ivFlags[16] = ivFlag17;
		ivFlags[17] = ivFlag18;
		ivFlags[18] = ivFlag19;
		ivFlags[19] = ivFlag20;
		for (int i = 0; i < ivFlags.length; i++) {
			setViewFlagPosition(ivFlags[i], i);
		}
	}
	
	private void showFlags() {
		int index = 0;
		if (currentLevel == 24) {
			index = currentLevel - 4;
		} else if (currentLevel >= 18) {
			index = currentLevel - 3;
		} else if (currentLevel >= 12) {
			index = currentLevel - 2;
		} else if (currentLevel >= 6) {
			index = currentLevel - 1;
		} else {
			index = currentLevel;
		}
		/*if(currentLevel >= finishMaxCountryIndex) {
			index = finishMaxCountryIndex - 2;
		} else if (currentLevel >= 6) {
			index = currentLevel - 1;
		} else {
			index = currentLevel;
		}*/
		for (int i = 0; i < ivFlags.length; i++) {
			if (i >= index) {
				ivFlags[i].setImageResource(new BaseCommon().getImageId(
						mContext, "flag_" + (i + 1)));
			} else {
				ivFlags[i].setImageResource(new BaseCommon().getImageId(
						mContext, "flag_" + (i + 20 + 1)));
			}
		}
	}

	private void setImgCountrys() {
		ivCountrys = new ImageView[24];
		ivCountrys[0] = ivCountry1;
		ivCountrys[1] = ivCountry2;
		ivCountrys[2] = ivCountry3;
		ivCountrys[3] = ivCountry4;
		ivCountrys[4] = ivCountry5;
		ivCountrys[5] = ivChess1;
		ivCountrys[6] = ivCountry6;
		ivCountrys[7] = ivCountry7;
		ivCountrys[8] = ivCountry8;
		ivCountrys[9] = ivCountry9;
		ivCountrys[10] = ivCountry10;
		ivCountrys[11] = ivChess2;
		ivCountrys[12] = ivCountry11;
		ivCountrys[13] = ivCountry12;
		ivCountrys[14] = ivCountry13;
		ivCountrys[15] = ivCountry14;
		ivCountrys[16] = ivCountry15;
		ivCountrys[17] = ivChess3;
		ivCountrys[18] = ivCountry16;
		ivCountrys[19] = ivCountry17;
		ivCountrys[20] = ivCountry18;
		ivCountrys[21] = ivCountry19;
		ivCountrys[22] = ivCountry20;
		ivCountrys[23] = ivChess4;

		for (int i = 0; i < ivCountrys.length; i++) {
			setViewPosition(ivCountrys[i], i);
			ivCountrys[i].setOnClickListener(this);
		}
	}

	private void setViewPosition(View iv, int i) {
//		if(height == 480) {
//			LayoutParameters.setViewPosition(iv, i,
//					FixedPositionRevision.country_position_480, scaleQPW, scaleQPH,
//					density);
//		} else {
			LayoutParameters.setViewPosition(iv, i,
					FixedPositionRevision.country_position, scaleQPW, scaleQPH,
					density);
//		}
	}

	private void setViewFlagPosition(View iv, int i) {
//		if(height == 480) {
//			LayoutParameters.setViewPosition(iv, i,
//					FixedPositionRevision.flag_position_480, scaleQPW, scaleQPH,
//					density);
//		} else {
			LayoutParameters.setViewPosition(iv, i,
					FixedPositionRevision.flag_position, scaleQPW, scaleQPH,
					density);
//		}
	}
	private void setViewLockPosition(View iv, int i) {
//		if(height == 480) {
//			LayoutParameters.setViewPosition(iv, i,
//					FixedPositionRevision.country_lock_position_480, scaleQPW, scaleQPH,
//					density);
//		} else {
//			LayoutParameters.setViewPosition(iv, i,
//					FixedPositionRevision.country_lock_position, scaleQPW, scaleQPH,
//					density);
//		}
	}

	@Override
	public void onClick(View v) {
		if (v == ivBack) {
			this.finish();
		} else if (v == ivCountry1) {
//			finishCountry();
			gotoActivity(0, 0);
		} else if (v == ivCountry2) {
			gotoActivity(0, 1);
		} else if (v == ivCountry3) {
			gotoActivity(0, 2);
		} else if (v == ivCountry4) {
			gotoActivity(0, 3);
		} else if (v == ivCountry5) {
			gotoActivity(0, 4);
		} else if (v == ivCountry6) {
			gotoActivity(1, 0);
		} else if (v == ivCountry7) {
			gotoActivity(1, 1);
		} else if (v == ivCountry8) {
			gotoActivity(1, 2);
		} else if (v == ivCountry9) {
			gotoActivity(1, 3);
		} else if (v == ivCountry10) {
			gotoActivity(1, 4);
		} else if (v == ivCountry11) {
			gotoActivity(2, 0);
		} else if (v == ivCountry12) {
			gotoActivity(2, 1);
		} else if (v == ivCountry13) {
			gotoActivity(2, 2);
		} else if (v == ivCountry14) {
			gotoActivity(2, 3);
		} else if (v == ivCountry15) {
			gotoActivity(2, 4);
		} else if (v == ivCountry16) {
			gotoActivity(3, 0);
		} else if (v == ivCountry17) {
			gotoActivity(3, 1);
		} else if (v == ivCountry18) {
			gotoActivity(3, 2);
		} else if (v == ivCountry19) {
			//新国家测试
			gotoActivity(3, 3);
//			toastLevel();
		} else if (v == ivCountry20) {
			toastLevel();
		} else if (v == ivChess1) {
		} else if (v == ivChess2) {
		} else if (v == ivChess3) {
		} else if (v == ivChess4) {
		}
	}

	private void toastLevel() {
		new MyToast().showTextToast(mContext, "暂未开启，敬请期待");
	}

	private void gotoActivity(int island, int index) {}
	
	private boolean toPayMent = false;
	
	@Override
	protected void onResume() {
		super.onResume();
//		// 去更新purchasesList数据
//		if(toPayMent) {
//			toPayMent = false;
//			getPurchasesList();
//		}
//		mMediaPlayer = MediaPlayerBiz.playMusicFromResId(mContext, mMediaPlayer, R.raw.map_bg, true);
//		int index = 0;
//		//新国家测试
//		if(currentLevel >= 21) {
//			index = 21;
//		} else {
//			index = currentLevel;
//		}
//		if(ivCountrys != null)
//			ivCountrys[index].clearAnimation();
//		if(clickCurrentLecel == -1 || clickCurrentLecel > currentLevel) {
//			isCanMove = true;
//		} else {
//			isCanMove = false;
//		}
//		// 再次获取当前进度
//		processValue = new CacheSp().spGetCountryLevel(mContext);
//		currentLevel = CommonUtil.countryProgress(mContext, processValue);
		processStatus();
//		// HorizontalScrollView动态移动到某一个位置
//		hslView.post(new Runnable() { 
//	        @Override  
//	        public void run() {  
//	        	if(isCanMove) {
//	        		int left = 0;
//	        		if(currentLevel >= 16) {
//	        			left = Float.valueOf((1800*scaleQPW)).intValue();
//	        		} else if(currentLevel >= 14) {
//	        			left = Float.valueOf((1000*scaleQPW)).intValue();
//	        		} else if(currentLevel >= 10) { 
//	        			left = Float.valueOf((500*scaleQPW)).intValue();
//	        		}
//	        		if(left != 0) {
//	        			hslView.scrollTo(left, 0);
//	        		}
//	        	} 
//	        }   
//	    });  
	}
	
	private void processStatus() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Looper.prepare();
				handlerWorld.sendMessage(handlerWorld.obtainMessage(2));
				Looper.loop();
			}
		}).start();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		getWindow().getDecorView().setKeepScreenOn(false);
		if (mMediaPlayer != null) {
			mMediaPlayer.pause();
		}
		if (mMediaPlayerSound != null) {
			mMediaPlayerSound.pause();
		}
		MediaCommon.pauseMediaplay();
	}

	@Override
	protected void onDestroy() {
		clearMediaPlayer();
		for(int i=0; i<ivCountrys.length; i++) {
			ivCountrys[i].setImageResource(0);
		}
		for(int i=0; i<ivFlags.length; i++) {
			ivFlags[i].setImageResource(0);
		}
		for(int i=0; i<ivLock.length; i++) {
			ivLock[i].setImageResource(0);
		}
		ivChess1.setImageResource(0);
		ivChess2.setImageResource(0);
		ivChess3.setImageResource(0);
		ivChess4.setImageResource(0);
		rlLayout.setBackground(null);
		handlerWorld.removeCallbacksAndMessages(null);
		super.onDestroy();
	}

	private void clearMediaPlayer() {
		try {
			if (mMediaPlayer != null) {
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void playSoundMusic() {
		int resId = R.raw.country_level_prompt;
		if (mMediaPlayerSound != null) {
			mMediaPlayerSound.stop();
			mMediaPlayerSound.release();
			mMediaPlayerSound = null;
		}
		mMediaPlayerSound = MediaPlayerBiz.playMusicFromResId(mContext, mMediaPlayerSound, resId, false);
	}

	// 国家闪烁
	private void countryFlicker() {
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 1:
			toPayMent = true;
			break;

		default:
			break;
		}
	}
	
	private class HandlerWorld extends Handler {

		private HandlerWorld(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				break;
			case 1:
				Resources  r = getResources();
				Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
					    + r.getResourcePackageName(R.drawable.can_kao1) + "/"
					    + r.getResourceTypeName(R.drawable.can_kao1) + "/"
					    + r.getResourceEntryName(R.drawable.can_kao1));
				Drawable drawable = ImageBiz.bitmapToDrawble(ImageBiz.readBitmapUri(mContext, uri), mContext);
				Drawable drawableValue = ImageBiz.drawableToBitmap(drawable, mContext);
				rlLayout.setBackground(drawableValue);
				break;
			case 2:
				showFlags();
				countryFlicker();
				break;
			case 3:
				// TODO 滑动
				int location = 1000;
				hslView.smoothScrollBy(mContext, location, true, true);
				break;
			default:
				break;
			}
		};
	}
	
	private void delayMove() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(250);
					handlerWorld.sendMessage(handlerWorld.obtainMessage(3));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	
	
}
