package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.MyVideoView;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPositionLevelA;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;
import com.gst.move.utils.DisplayUtil;

/**
 *  I see my colors
 * @author
 *
 */
public class LevelAMyColors1Activity extends BaseActivity implements OnTouchListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivFrog;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer;
	private MyVideoView video;
	private String path, imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] {
			"my_colors_bg", "my_colors_green", "my_colors_yellow", "my_colors_frog_del"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_colors_1);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelAMyColors1Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		imgPath = ConstantEp.path_level_a_game + "images/";
		path = ConstantEp.path_level_a_game + "my_colors_1.mp4/";
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivFrog = (ImageView) findViewById(R.id.iv_frog);
		video = (MyVideoView) findViewById(R.id.video_play);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[1]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[2]));
		ivFrog.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[3]));
		
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivFrog, 2);
		
		ivCard1.setOnTouchListener(this);
		ivCard2.setOnTouchListener(this);
		// 播放题目读音
		mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game +
				"my_colors_problem_1.mp3", mMediaPlayer);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.my_colors_1_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		MediaPlayerBiz.clearMediaPlayer(mMediaPlayer);
	}

	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (selectedId != 0 && selectedId != v.getId())
			return true;
		ivToFront(v);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			x = event.getX();
			y = event.getY();
			mWidth = v.getWidth();
			mHeight = v.getHeight();
			break;
		case MotionEvent.ACTION_MOVE:
			mx = (int) (event.getRawX() - x);
			my = (int) (event.getRawY() - DisplayUtil.dip2px(mContext, 25) - y);
			if (mx + mWidth > width)
				mx = width - mWidth;
			if (my + mHeight > height)
				my = height - mHeight;
			v.setLayoutParams(LayoutParameters.setViewPositionParams(mWidth,
					mHeight, mx, my));
			break;

		case MotionEvent.ACTION_UP:
			// 拿到红色框框在屏幕的Y X 坐标（dp）
			int[] location = new int[2];
			Rect viewRect = new Rect(); 
			if(indexSelect == 0) {
				ivFrog.getLocationOnScreen(location);
				ivFrog.getGlobalVisibleRect(viewRect);
			}
			
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect == 0)
					&& (Rect.intersects(viewRect, otherViewRect))) {
				setBestPosition(v, mx, my);
			} else {
				MediaCommon.playEgError(mContext);
				int id = v.getId();
				if (id == R.id.iv_card_1) {
					setViewPosition(ivCard1, 0);
				} else if(id == R.id.iv_card_2) {
					setViewPosition(ivCard2, 1);
				}
				// 保护的用途
				selectedId = 0;
			}
			break;
		default:
			break;
		}
		return true;
	}

	// 获得最佳停留位置
	private void setBestPosition(View v, float x, float y) {
		final int[] location = new int[2];
		if (indexSelect == 0) {
			ivFrog.getLocationOnScreen(location);
		}
		if (indexSelect == 0 || indexSelect == 1) {
			showView(400, 0);
			TranslateAnimation animation = new TranslateAnimation(0.0f,
					location[0] + v.getWidth() / 2 - x, 0.0f, location[1]
							+ v.getHeight() / 2 - y);
			animation.setInterpolator(new LinearInterpolator()); // 设置动画的速率
			animation.setDuration(400);
			v.startAnimation(animation);
			animation.setFillAfter(true); // 执行终止填充效果
		}
	}

	private void showView(final long time, final  int caseIndex) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(time);
					handler.sendMessage(handler.obtainMessage(caseIndex));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				showSelectedWord();
				break;
			case 1:
				rlLayout.setVisibility(View.GONE);
				break;
			case 2:
				playVideo();
				break;
				case 4:
					MediaCommon.playEgGood(mContext);
					break;
				case 5:
					finish();
					break;
			default:
				break;
			}
		}

	};

	private void showSelectedWord() {
		if (selectedId == R.id.iv_card_1) {
			statusImg(ivCard1, 0);
		} else if (selectedId == R.id.iv_card_2) {
			statusImg(ivCard2, 1);
		}
		
		// 保护的用途
		selectedId = 0;
	}

	private void statusImg(ImageView ivImg, int index) {
		ivFrog.setImageDrawable(BaseCommon.drawableChange(imgPath, "my_colors_frog_sel"));
		ivImg.clearAnimation();
		ivImg.setVisibility(View.GONE);
		ivImg.setEnabled(false);
		delayFinish();
	}
	
	private void delayFinish() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(500);
					handler.sendMessage(handler.obtainMessage(2));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void playVideo() {
		video.setVisibility(View.VISIBLE);
		biz.playVideo(video, path);
		video.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer arg0) {
				// 处理播放完后的情况
				delayFinish2();
			}
		});
	}

	// 把点击的图片置顶到顶层
	private void ivToFront(View v) {
		selectedId = v.getId();
		indexSelect = -1;
		if (v == ivCard1) {
			ivCard1.bringToFront();
			indexSelect = 0;
		} else if (v == ivCard2) {
			ivCard2.bringToFront();
			indexSelect = -1;
		}
	}

	private void delayFinish2() {
		startActivity(new Intent(mContext, CommonGoodActivity.class));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					handler.sendMessage(handler.obtainMessage(4));
					Thread.sleep(2200);
					handler.sendMessage(handler.obtainMessage(5));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
}
