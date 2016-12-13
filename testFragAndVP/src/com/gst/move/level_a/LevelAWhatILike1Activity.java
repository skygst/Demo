package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
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

import com.ebodoo.raz.BaseActivity;
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
 *  What i like 1
 * @author
 *
 */
public class LevelAWhatILike1Activity extends BaseActivity implements OnTouchListener {

	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivPigment1, ivPigment2, ivPigment3;
	private ImageView[] ivCard;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer;
	private String imgPath;
	private VideoBiz biz;
	private Context mContext;
	private String[] picArray = new String[] {
			"what_i_like_bg", "red", "yellow", "blue"
	};
	private boolean[] status = new boolean[] {false, false, false};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.what_i_like_1);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelAWhatILike1Activity.this;
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);

		biz = new VideoBiz();
		imgPath = ConstantEp.path_level_a_game + "images/";
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivPigment1 = (ImageView) findViewById(R.id.iv_pigment_1);
		ivPigment2 = (ImageView) findViewById(R.id.iv_pigment_2);
		ivPigment3 = (ImageView) findViewById(R.id.iv_pigment_3);
		
		rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(imgPath, "what_i_like_flower"));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(imgPath, "what_i_like_flower"));
		ivCard3.setImageDrawable(BaseCommon.drawableChange(imgPath, "what_i_like_flower"));
		ivPigment1.setImageDrawable(BaseCommon.drawableChange(imgPath, "what_i_like_pigment_" + picArray[1]));
		ivPigment2.setImageDrawable(BaseCommon.drawableChange(imgPath, "what_i_like_pigment_" + picArray[2]));
		ivPigment3.setImageDrawable(BaseCommon.drawableChange(imgPath, "what_i_like_pigment_" + picArray[3]));
		
		ivCard = new ImageView[3];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 2);
		setViewPosition(ivPigment1, 3);
		setViewPosition(ivPigment2, 4);
		setViewPosition(ivPigment3, 5);
		
		ivPigment1.setOnTouchListener(this);
		ivPigment2.setOnTouchListener(this);
		ivPigment3.setOnTouchListener(this);
		// 播放题目读音
		mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game +
				"what_i_like_problem_1.mp3", mMediaPlayer);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionLevelA.what_i_like_1_position,
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
	private int currentIndex = 0;

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
			Rect viewRect1 = new Rect(); 
			Rect viewRect2 = new Rect(); 
			Rect viewRect3 = new Rect(); 
			for(int i=0; i<status.length; i++) {
				if(!status[i]) {
					int[] location = new int[2];
					ivCard[i].getLocationOnScreen(location);
					if(i == 0) {
						ivCard[i].getGlobalVisibleRect(viewRect1);
					} else if(i == 1) {
						ivCard[i].getGlobalVisibleRect(viewRect2);
					} else if(i == 2) {
						ivCard[i].getGlobalVisibleRect(viewRect3);
					}
				}
			}
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect == 0 || indexSelect == 1 || indexSelect == 2)
					&& (Rect.intersects(viewRect1, otherViewRect) || Rect.intersects(viewRect2, otherViewRect) || Rect.intersects(viewRect3, otherViewRect))) {
				if(Rect.intersects(viewRect1, otherViewRect)) {
					currentIndex = 0;
				} else if(Rect.intersects(viewRect2, otherViewRect)) {
					currentIndex = 1;
				} else if(Rect.intersects(viewRect3, otherViewRect)) {
					currentIndex = 2;
				}
				setBestPosition(v, mx, my);
			} else {
				int id = v.getId();
				if (id == R.id.iv_pigment_1) {
					setViewPosition(ivPigment1, 3);
				} else if(id == R.id.iv_pigment_2) {
					setViewPosition(ivPigment2, 4);
				} else if(id == R.id.iv_pigment_3) {
					setViewPosition(ivPigment3, 5);
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
		if (currentIndex == 0) {
			ivCard1.getLocationOnScreen(location);
		} else if (currentIndex == 1) {
			ivCard2.getLocationOnScreen(location);
		} else if(currentIndex == 2) {
			ivCard3.getLocationOnScreen(location);
		}
		if (indexSelect == 0 || indexSelect == 1 || indexSelect == 2) {
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
				MediaCommon.playEgGood(mContext);
				delayFinish2();
//				finish();
				break;
				case 3:
					finish();
					break;
			default:
				break;
			}
		}

	};

	private void showSelectedWord() {
		if (selectedId == R.id.iv_pigment_1) {
			statusImg(ivPigment1, 0);
		} else if (selectedId == R.id.iv_pigment_2) {
			statusImg(ivPigment2, 1);
		} else if (selectedId == R.id.iv_pigment_3) {
			statusImg(ivPigment3, 2);
		}
		
		// 保护的用途
		selectedId = 0;
	}

	private void statusImg(ImageView ivImg, int index) {
		status[currentIndex] = true;
		ivCard[currentIndex].setImageDrawable(BaseCommon.drawableChange(imgPath, "what_i_like_flower_" + picArray[index + 1]));
		mMediaPlayer = MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game +
				"what_i_like_" + picArray[index + 1] + ".mp3", mMediaPlayer);
		judgeIsFinish();
		ivImg.clearAnimation();
		ivImg.setVisibility(View.GONE);
		ivImg.setEnabled(false);
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
		if (isFinish)
			delayFinish();
	}
	
	private void delayFinish() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					handler.sendMessage(handler.obtainMessage(2));
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
		if (v == ivPigment1) {
			ivPigment1.bringToFront();
			indexSelect = 0;
		} else if (v == ivPigment2) {
			ivPigment2.bringToFront();
			indexSelect = 1;
		} else if (v == ivPigment3) {
			ivPigment3.bringToFront();
			indexSelect = 2;
		}
	}

	private void delayFinish2() {
		startActivity(new Intent(mContext, CommonGoodActivity.class));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2200);
					handler.sendMessage(handler.obtainMessage(3));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
}
