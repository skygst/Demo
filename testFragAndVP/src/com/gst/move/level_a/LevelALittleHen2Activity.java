package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Rect;
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
 * I had a little hen 2
 * @author
 */
public class LevelALittleHen2Activity extends BaseActivity implements
		OnTouchListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivSeasoning1, ivSeasoning2, ivSeasoning3, ivSeasoning4;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private ImageView[] ivSeasoning;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] { 
			"little_hen_bg_2", "little_hen_pizza", "carrot", "lettuce", "sausage", "potato"
	};
	private boolean[] status = new boolean[] { false, false, false, false };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFormat(PixelFormat.TRANSLUCENT); 
		setContentView(R.layout.little_hen_2);

		init();
		setView();
	}

	private void init() {
		mContext = LevelALittleHen2Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		imgPath = ConstantEp.path_level_a_game + "images/";
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivSeasoning1 = (ImageView) findViewById(R.id.iv_seasoning_1);
		ivSeasoning2 = (ImageView) findViewById(R.id.iv_seasoning_2);
		ivSeasoning3 = (ImageView) findViewById(R.id.iv_seasoning_3);
		ivSeasoning4 = (ImageView) findViewById(R.id.iv_seasoning_4);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, picArray[1]));
		ivSeasoning1.setImageDrawable(BaseCommon.drawableChange(imgPath, "little_hen_" + picArray[2]));
		ivSeasoning2.setImageDrawable(BaseCommon.drawableChange(imgPath, "little_hen_" + picArray[3]));
		ivSeasoning3.setImageDrawable(BaseCommon.drawableChange(imgPath, "little_hen_" + picArray[4]));
		ivSeasoning4.setImageDrawable(BaseCommon.drawableChange(imgPath, "little_hen_" + picArray[5]));
		
		ivSeasoning = new ImageView[4];
		ivSeasoning[0] = ivSeasoning1;
		ivSeasoning[1] = ivSeasoning2;
		ivSeasoning[2] = ivSeasoning3;
		ivSeasoning[3] = ivSeasoning4;

		setViewPosition(ivCard1, 0);
		setViewPosition(ivSeasoning1, 1);
		setViewPosition(ivSeasoning2, 2);
		setViewPosition(ivSeasoning3, 3);
		setViewPosition(ivSeasoning4, 4);
		
		ivSeasoning1.setOnTouchListener(this);
		ivSeasoning2.setOnTouchListener(this);
		ivSeasoning3.setOnTouchListener(this);
		ivSeasoning4.setOnTouchListener(this);
		
		// 播放题目读音
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "little_hen_problem_2.mp3", null);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.little_hen_2_position,
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
			ivCard1.getLocationOnScreen(location);
			ivCard1.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect == 0 || indexSelect == 1 || indexSelect == 2 || indexSelect == 3)
					&& Rect.intersects(viewRect, otherViewRect)) {
				setBestPosition(v, mx, my);
			} else {
				int id = v.getId();
				if (id == R.id.iv_seasoning_1) {
					setViewPosition(ivSeasoning1, 1);
				} else if(id == R.id.iv_seasoning_2) {
					setViewPosition(ivSeasoning2, 2);
				} else if(id == R.id.iv_seasoning_3) {
					setViewPosition(ivSeasoning3, 3);
				} else if(id == R.id.iv_seasoning_4) {
					setViewPosition(ivSeasoning4, 4);
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
		if (indexSelect == 0 || indexSelect == 1 || indexSelect == 2 || indexSelect == 3) {
			ivCard1.getLocationOnScreen(location);
		}
		if (indexSelect == 0 || indexSelect == 1 || indexSelect == 2 || indexSelect == 3) {
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
				finish();
				break;
			case 2:
				MediaCommon.playEgGood(mContext);
				break;
				case 3:
					startActivity(new Intent(mContext, CommonGoodActivity.class));
					break;
			default:
				break;
			}
		}

	};

	private void showSelectedWord() {
		if (selectedId == R.id.iv_seasoning_1) {
			statusImg(ivSeasoning1, 0);
		} else if(selectedId == R.id.iv_seasoning_2) {
			statusImg(ivSeasoning2, 1);
		} else if(selectedId == R.id.iv_seasoning_3) {
			statusImg(ivSeasoning3, 2);
		} else if(selectedId == R.id.iv_seasoning_4) {
			statusImg(ivSeasoning4, 3);
		}
		// 保护的用途
		selectedId = 0;
	}

	private void statusImg(ImageView ivImg, int index) {
		setViewPosition(ivImg, 0);
		ivImg.setImageDrawable(BaseCommon.drawableChange(imgPath, "little_hen_pizza_" + picArray[index + 2]));
		ivImg.clearAnimation();
		ivImg.setEnabled(false);
		status[index] = true;
		judgeIsFinish();
		MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "little_hen_" + picArray[index + 2] + ".mp3", null);
	}

	// 把点击的图片置顶到顶层
	private void ivToFront(View v) {
		selectedId = v.getId();
		indexSelect = -1;
		if (v == ivSeasoning1) {
			ivSeasoning1.bringToFront();
			indexSelect = 0;
		} else if(v == ivSeasoning2) {
			ivSeasoning2.bringToFront();
			indexSelect = 1;
		} else if(v == ivSeasoning3) {
			ivSeasoning3.bringToFront();
			indexSelect = 2;
		} else if(v == ivSeasoning4) {
			ivSeasoning4.bringToFront();
			indexSelect = 3;
		}
	}

	// 判断是否完成
	private void judgeIsFinish() {
		boolean isFinish = true;
		for (int i = 0; i < status.length; i++) {
			if (!status[i]) {
				isFinish = false;
				break;
			}
		}
		if (isFinish) {
			delayFinish();
		}
	}

	private void delayFinish() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(600);
					handler.sendMessage(handler.obtainMessage(2));
					Thread.sleep(1000);
					handler.sendMessage(handler.obtainMessage(3));
					Thread.sleep(2500);
					handler.sendMessage(handler.obtainMessage(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
