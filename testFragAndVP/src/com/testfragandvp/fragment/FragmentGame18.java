package com.testfragandvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.data.FixedPositionAfrica;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.LayoutParameters;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 *  In and Out
 * @author
 *
 */
public class FragmentGame18 extends Fragment implements OnTouchListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard0, ivCard1, ivCard2, ivBottomBg1, ivBottomBg2, ivBottomBg3, ivBottomBg4,
			ivBottomBg, ivBottom1, ivBottom2, ivBottom3, ivBottom4;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardName = {"cat", "cow", "dog", "pig"};
	private int[] arrayList = new int[] { 0, 1, 2, 3};
	private ImageView[] bottomBg, bottomWord;
	private int[] randB;
	private String path;
	private int width, height;
	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int selNum = 0;
	private boolean isIn = true;
	private boolean isStartTouch = true;

	public FragmentGame18() {
		super();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.af_game_18, container, false);
		init();
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		isIn = true;
//		isIn = false;
		isStartTouch = true;
		selNum = 0;
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "in_and_out_prologue_in.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard0 = (ImageView) view.findViewById(R.id.iv_card_0);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivBottomBg = (ImageView) view.findViewById(R.id.iv_bottom_bg);
		ivBottomBg1 = (ImageView) view.findViewById(R.id.iv_bottom_bg_1);
		ivBottomBg2 = (ImageView) view.findViewById(R.id.iv_bottom_bg_2);
		ivBottomBg3 = (ImageView) view.findViewById(R.id.iv_bottom_bg_3);
		ivBottomBg4 = (ImageView) view.findViewById(R.id.iv_bottom_bg_4);
		ivBottom1 = (ImageView) view.findViewById(R.id.iv_bottom_1);
		ivBottom2 = (ImageView) view.findViewById(R.id.iv_bottom_2);
		ivBottom3 = (ImageView) view.findViewById(R.id.iv_bottom_3);
		ivBottom4 = (ImageView) view.findViewById(R.id.iv_bottom_4);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "in_and_out_bg"));
		showCard();
		ivBottomBg.setImageDrawable(BaseCommon.drawableChange(path, "in_and_out_bottom_bg"));
	}
	
	private void showCard() {
		bottomBg = new ImageView[4];
		bottomBg[0] = ivBottomBg1;
		bottomBg[1] = ivBottomBg2;
		bottomBg[2] = ivBottomBg3;
		bottomBg[3] = ivBottomBg4;
		
		bottomWord = new ImageView[4];
		bottomWord[0] = ivBottom1;
		bottomWord[1] = ivBottom2;
		bottomWord[2] = ivBottom3;
		bottomWord[3] = ivBottom4;
		initCard();
		ivCard0.setImageDrawable(BaseCommon.drawableChange(path, "in_and_out_mud"));
	}
	
	private void initCard() {
		commonPosition();
		randB = BaseCommon.getList(arrayList);
		for(int i=0; i<bottomWord.length; i++) {
			bottomBg[i].setImageDrawable(BaseCommon.drawableChange(path, "in_and_out_" + cardName[randB[i]] + "_bg"));
			if(isIn) {
				bottomWord[i].setImageDrawable(BaseCommon.drawableChange(path, "in_and_out_" + cardName[randB[i]]));
				bottomWord[i].setEnabled(true);
				bottomWord[i].setOnTouchListener(this);
			} else {
				bottomWord[i].setEnabled(false);
				bottomWord[i].setImageDrawable(null);
				bottomWord[i].setVisibility(View.VISIBLE);
			}
			setViewPosition(bottomWord[i], i+1);
		}
		if(!isIn) {
			ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "in_and_out_mud_" + cardName[randB[selNum]]));
			ivCard1.setEnabled(true);
			ivCard2.setOnTouchListener(this);
		}
	}
	
	private void commonPosition() {
		setViewPosition(ivCard0, 0);
		setViewPosition(ivCard1, 0);
		setViewPosition(ivBottomBg1, 1);
		setViewPosition(ivBottomBg2, 2);
		setViewPosition(ivBottomBg3, 3);
		setViewPosition(ivBottomBg4, 4);
		setViewPosition(ivBottomBg, 5);
		setViewPosition(ivCard2, 6);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.in_and_out_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
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
			my = (int) (event.getRawY() - dip2px(mContext, 25) - y);
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
			if (indexSelect != -1) {
				if(isIn) {
					ivCard0.getLocationOnScreen(location);
					ivCard0.getGlobalVisibleRect(viewRect);
				} else {
					if (indexSelect == 0) {//
						// 计算该视图在全局坐标系中的x，y值，（注意这个值是要从屏幕顶端算起，也就是索包括了通知栏的高度）//获取在当前屏幕内的绝对坐标
						ivBottom1.getLocationOnScreen(location);
						ivBottom1.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
						// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
					} else if (indexSelect == 1) {
						ivBottom2.getLocationOnScreen(location);
						ivBottom2.getGlobalVisibleRect(viewRect);
					} else if (indexSelect == 2) {
						ivBottom3.getLocationOnScreen(location);
						ivBottom3.getGlobalVisibleRect(viewRect);
					} else if (indexSelect == 3) {
						ivBottom4.getLocationOnScreen(location);
						ivBottom4.getGlobalVisibleRect(viewRect);
					}
				}
			} 
			
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect != -1)
					&& Rect.intersects(viewRect, otherViewRect)) {
				setBestPosition(v, mx, my);
			} else {
				if(isIn) {
					if (v == ivBottom1) {
						setViewPosition(v, 1);
					} else if (v == ivBottom2) {
						setViewPosition(v, 2);
					} else if (v == ivBottom3) {
						setViewPosition(v, 3);
					} else if (v == ivBottom4) {
						setViewPosition(v, 4);
					}
				} else {
					isStartTouch = true;
					setViewPosition(v, 6);
					ivCard1.setVisibility(View.VISIBLE);
					ivCard2.setImageDrawable(null);
				}
				MediaCommon.playFuxiError(mContext);
				// 保护的用途
				selectedId = 0;
			}
			break;
		default:
			break;
		}
		return true;
	}
	
	// 把点击的图片置顶到顶层
	private void ivToFront(View v) {
		selectedId = v.getId();
		indexSelect = -1;
		if(isIn) {
			if (v == ivBottom1) {
				frontView(v, 0);
			} else if (v == ivBottom2) {
				frontView(v, 1);
			} else if (v == ivBottom3) {
				frontView(v, 2);
			} else if (v == ivBottom4) {
				frontView(v, 3);
			}
		} else {
			indexSelect = selNum;
		}
		if(!isIn && isStartTouch) {
			isStartTouch = false;
			ivCard1.setVisibility(View.GONE);
			ivCard2.setImageDrawable(BaseCommon.drawableChange(path, "in_and_out_big_" + cardName[randB[selNum]]));
		}
	}
	
	private void frontView(View ivPic, int index) {
		ivPic.bringToFront();
		indexSelect = getRand(index);
	}
	
	private int getRand(int index) {
		for(int i=0; i<arrayList.length; i++) {
			if(randB[index] == i) {
				return i;
			}
		}
		return -1;
	}
	
	// 获得最佳停留位置
	private void setBestPosition(View v, float x, float y) {
		final int[] location = new int[2];
		if (indexSelect >= 0 && indexSelect < 4) {
			ivCard1.getLocationOnScreen(location);
		}
		if (indexSelect != -1) {
			showGoodWord(v);
		}
	}
	
	private void showGoodWord(final View v) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				handler.sendMessage(handler.obtainMessage(0, v));
			}
		}).start();
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				View v = (View) msg.obj;
				showSelectedWord(v);
				break;
			case 1:
				isIn = false;
				selNum = 0;
				playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "in_and_out_prologue_out.mp3"));
				initCard();
				break;
			case 2:
				ActivityBiz.finishAfBookGame();
				break;
			default:
				break;
			}
		}

	};

	private void showSelectedWord(View v) {
		statusImg((ImageView)v);
		selNum++;
		if (selNum >= 4) {
			if (isIn) {
				delayTime(2000, 1);
			} else {
				delayTime(2000, 2);
			}
		}
		// 保护的用途
		selectedId = 0;
	}
	
	private void delayTime(final long time, final int caseId) {
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
	
	private void statusImg(ImageView ivImg) {
		if(isIn) {
			ivImg.clearAnimation();
			ivImg.setVisibility(View.GONE);
			ivImg.setEnabled(false);
			if(indexSelect != -1) {
				ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "in_and_out_mud_" + cardName[indexSelect]));
			}
			playSoundMusic(BaseCommon.path_game + "razinandout_" + cardName[indexSelect] + "_in" + ".mp3");
		} else {
			setViewPosition(ivImg, 6);
			isStartTouch = true;
			if(selNum >= 0 && selNum < 3) {
				ivCard1.setVisibility(View.VISIBLE);
				ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "in_and_out_mud_" + cardName[randB[selNum + 1]]));
				ivCard2.setImageDrawable(null);
			} else {
				ivImg.clearAnimation();
				ivImg.setVisibility(View.GONE);
				ivImg.setEnabled(false);
			}
			playSoundMusic(BaseCommon.path_game + "razinandout_" + cardName[randB[selNum]] + "_out" + ".mp3");
			bottomWord[indexSelect].setImageDrawable(BaseCommon.drawableChange(path, "in_and_out_" + cardName[randB[selNum]]));
		}
	}
	
	private void playSoundMusic(String path) {
		mMediaPlayerSound = ActivityBiz.playSoundMusic(mMediaPlayerSound, path);
	}
	
	private void clearMediaPlayer() {
		try {
			selNum = 0;
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		try {
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.pause();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		clearMediaPlayer();
	}
	
}