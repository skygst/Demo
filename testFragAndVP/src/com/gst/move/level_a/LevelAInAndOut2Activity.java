package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.media.MediaPlayer;
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
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPositionLevelA;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.DisplayUtil;

/**
 * In and out
 * @author
 */
public class LevelAInAndOut2Activity extends BaseActivity implements
		OnTouchListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2Bg, ivCard2, ivBottom1Bg, ivBottom1, ivBottom2Bg;
	private TextView tvProblem;
	private MediaPlayer mMediaPlayer;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] { "in_and_out_bg", "in_and_out_book_bg", "in_and_out_apple_bg",
			"in_and_out_apple", "in_and_out_basket_bg", "in_and_out_bottom_book" };
	private boolean isBookOut = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT); 
		setContentView(R.layout.in_and_out_2);

		init();
		setView();
	}

	private void init() {
		mContext = LevelAInAndOut2Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		imgPath = ConstantEp.path_level_a_game + "images/";
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard2Bg = (ImageView) findViewById(R.id.iv_card_2_bg);
		ivBottom1 = (ImageView) findViewById(R.id.iv_bottom_1);
		ivBottom1Bg = (ImageView) findViewById(R.id.iv_bottom_1_bg_1);
		tvProblem = (TextView) findViewById(R.id.tv_problem);
		ivBottom2Bg = (ImageView) findViewById(R.id.iv_bottom_1_bg_2);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[1]));
		ivCard2Bg.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[2]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[3]));
		ivBottom1Bg.setImageDrawable(BaseCommon.drawableChange(imgPath, "in_and_out_basket_bg_1"));
		ivBottom1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[5]));
		ivBottom2Bg.setImageDrawable(BaseCommon.drawableChange(imgPath, "in_and_out_basket_bg_2"));

		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard2Bg, 1);
		setViewPosition(ivBottom1, 3);
		setViewPosition(ivBottom1Bg, 2);
		setViewPosition(ivBottom2Bg, 2);
		
		ivBottom1.setOnTouchListener(this);
		
		// 播放题目读音
		mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "in_and_out_problem_2_1.mp3", mMediaPlayer);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.in_and_out_2_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
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
			final int[] location = new int[2];
			Rect viewRect = new Rect(); // 定义一个矩形区域
			if (indexSelect == 0) {//
				if(isBookOut) {
					ivCard1.getLocationOnScreen(location);
					ivCard1.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
				} else {
					ivBottom1Bg.getLocationOnScreen(location);
					ivBottom1Bg.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
				}
			}

			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect == 0)
					&& Rect.intersects(viewRect, otherViewRect)) {
				setBestPosition(v, mx, my);
			} else {
				MediaCommon.playEgError(mContext);
				int id = v.getId();
				if(isBookOut) {
					if (id == R.id.iv_bottom_1) {
						System.out.println("------------ ivBottom1 -----222------");
						ivBottom1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[5]));
						setViewPosition(ivBottom1, 3);
						ivBottom2Bg.bringToFront();
						indexSelect = -1;
					}
				} else {
					if (id == R.id.iv_card_2) {
						setViewPosition(ivCard2, 1);
					}
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
		if(isBookOut) {
			if (indexSelect == 0) {
				ivCard1.getLocationOnScreen(location);
			}
		} else {
			if (indexSelect == 0) {
				ivBottom1Bg.getLocationOnScreen(location);
			}
		}
		if (indexSelect == 0) {
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
				mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "in_and_out_problem_2_2.mp3", mMediaPlayer);
				break;
			case 3:
				MediaCommon.playEgGood(mContext);
				break;
				case 4:
					finish();
				break;
			default:
				break;
			}
		}

	};

	private void showSelectedWord() {
		if(isBookOut) {
			if (selectedId == R.id.iv_bottom_1) {
				statusImg(ivBottom1, 0);
			}
		} else {
			if (selectedId == R.id.iv_card_2) {
				statusImg(ivCard2, 0);
			}
		}
		// 保护的用途
		selectedId = 0;
	}

	private void statusImg(ImageView ivImg, int index) {
		ivImg.clearAnimation();
		ivImg.setVisibility(View.GONE);
		if(isBookOut) {
//			mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "jump_out.mp3", mMediaPlayer);
			MediaCommon.playEgGood(mContext);
			ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, "in_and_out_book"));
			isBookOut = false;
			ivCard2.setOnTouchListener(this);
			tvProblem.setText("Put the apples into the basket.");
			delayFinish(3300, 2);
		} else {
//			mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "jump_in.mp3", mMediaPlayer);
			ivBottom1.setVisibility(View.VISIBLE);
			setViewPosition(ivBottom1, 3);
			ivBottom1.setImageDrawable(BaseCommon.drawableChange(imgPath, "in_and_out_bottom_apple"));
			delayFinish();
			ivBottom2Bg.bringToFront();
			indexSelect = -1;
		}
		ivImg.setEnabled(false);
	}
	
	private void delayFinish(final long time, final int caseId) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(time);
					handler.sendMessage(handler.obtainMessage(caseId));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 把点击的图片置顶到顶层
	private void ivToFront(View v) {
		selectedId = v.getId();
		indexSelect = -1;
		if(isBookOut) {
			if (v == ivBottom1) {
				ivBottom1.bringToFront();
				indexSelect = 0;
			}
		} else {
			if (v == ivCard2) {
				ivCard2.bringToFront();
				indexSelect = 0;
			}
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		MediaPlayerBiz.clearMediaPlayer(mMediaPlayer);
	}

	private void delayFinish() {
		startActivity(new Intent(mContext, CommonGoodActivity.class));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					handler.sendMessage(handler.obtainMessage(3));
					Thread.sleep(2200);
					handler.sendMessage(handler.obtainMessage(4));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
