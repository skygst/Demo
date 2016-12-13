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
import android.view.View.OnClickListener;
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
 *  My Room
 * @author
 *
 */
public class FragmentGame8 extends Fragment implements OnTouchListener,
		OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6, ivUnderlay,
			ivBottom1, ivBottom2, ivBottom3, ivBottom4, ivBottom5, ivBottom6;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardName = {"desk", "book", "toys", "clothes", "shoe", "bed"};
	private int[] arrayList = new int[] { 0, 1, 2, 3, 4, 5};
	private ImageView[] imgCard;
	private ImageView[] bottomWord;
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

	public FragmentGame8() {
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
		view = inflater.inflate(R.layout.af_game_8, container, false);
		init();
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "my_room_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivCard5 = (ImageView) view.findViewById(R.id.iv_card_5);
		ivCard6 = (ImageView) view.findViewById(R.id.iv_card_6);
		ivBottom1 = (ImageView) view.findViewById(R.id.iv_bottom_1);
		ivBottom2 = (ImageView) view.findViewById(R.id.iv_bottom_2);
		ivBottom3 = (ImageView) view.findViewById(R.id.iv_bottom_3);
		ivBottom4 = (ImageView) view.findViewById(R.id.iv_bottom_4);
		ivBottom5 = (ImageView) view.findViewById(R.id.iv_bottom_5);
		ivBottom6 = (ImageView) view.findViewById(R.id.iv_bottom_6);
		ivUnderlay = (ImageView) view.findViewById(R.id.iv_underlay);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "my_room_bg"));
		showCard();
	}
	
	private void showCard() {
		imgCard = new ImageView[6];
		bottomWord = new ImageView[6];
		imgCard[0] = ivCard1;
		imgCard[1] = ivCard2;
		imgCard[2] = ivCard3;
		imgCard[3] = ivCard4;
		imgCard[4] = ivCard5;
		imgCard[5] = ivCard6;
		bottomWord[0] = ivBottom1;
		bottomWord[1] = ivBottom2;
		bottomWord[2] = ivBottom3;
		bottomWord[3] = ivBottom4;
		bottomWord[4] = ivBottom5;
		bottomWord[5] = ivBottom6;
		initCard();
	}
	
	private void initCard() {
		commonPosition();
		randB = BaseCommon.getList(arrayList);
		for(int i=0; i<imgCard.length; i++) {
			imgCard[i].setImageDrawable(BaseCommon.drawableChange(path, cardName[arrayList[i]] + "_del"));
			bottomWord[i].setImageDrawable(BaseCommon.drawableChange(path, "bottom_" + cardName[randB[i]]));
			bottomWord[i].setEnabled(true);
			bottomWord[i].setOnTouchListener(this);
		}
		ivUnderlay.setImageDrawable(BaseCommon.drawableChange(path, "my_room_underlay"));
	}
	
	private void commonPosition() {
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 2);
		setViewPosition(ivCard4, 3);
		setViewPosition(ivCard5, 4);
		setViewPosition(ivCard6, 5);
		setViewPosition(ivUnderlay, 6);
		setViewPosition(ivBottom1, 7);
		setViewPosition(ivBottom2, 8);
		setViewPosition(ivBottom3, 9);
		setViewPosition(ivBottom4, 10);
		setViewPosition(ivBottom5, 11);
		setViewPosition(ivBottom6, 12);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.my_room_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
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
			if (indexSelect == 0) {//
			// 计算该视图在全局坐标系中的x，y值，（注意这个值是要从屏幕顶端算起，也就是索包括了通知栏的高度）//获取在当前屏幕内的绝对坐标
				ivCard1.getLocationOnScreen(location);
				ivCard1.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
				// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			} else if (indexSelect == 1) {
				ivCard2.getLocationOnScreen(location);
				ivCard2.getGlobalVisibleRect(viewRect);
			} else if(indexSelect == 2){
				ivCard3.getLocationOnScreen(location);
				ivCard3.getGlobalVisibleRect(viewRect);
			} else if (indexSelect == 3) {
				ivCard4.getLocationOnScreen(location);
				ivCard4.getGlobalVisibleRect(viewRect);
			} else if (indexSelect == 4) {
				ivCard5.getLocationOnScreen(location);
				ivCard5.getGlobalVisibleRect(viewRect);
			} else if (indexSelect == 5) {
				ivCard6.getLocationOnScreen(location);
				ivCard6.getGlobalVisibleRect(viewRect);
			}
			
			// View otherView = v;
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect != -1)
					&& Rect.intersects(viewRect, otherViewRect)) {
				setBestPosition(v, mx, my);
			} else {
				if (v == ivBottom1) {
					setViewPosition(v, 7);
				} else if (v == ivBottom2) {
					setViewPosition(v, 8);
				} else if (v == ivBottom3) {
					setViewPosition(v, 9);
				} else if (v == ivBottom4) {
					setViewPosition(v, 10);
				} else if(v == ivBottom5) {
					setViewPosition(v, 11);
				} else if(v == ivBottom6) {
					setViewPosition(v, 12);
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
		if (v == ivBottom1) {
			frontView(v, 0);
		} else if (v == ivBottom2) {
			frontView(v, 1);
		} else if (v == ivBottom3) {
			frontView(v, 2);
		} else if (v == ivBottom4) {
			frontView(v, 3);
		} else if (v == ivBottom5) {
			frontView(v, 4);
		} else if (v == ivBottom6) {
			frontView(v, 5);
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
		if (indexSelect >= 0 && indexSelect < 6) {
			imgCard[indexSelect].getLocationOnScreen(location);
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
				ActivityBiz.finishAfBookGame();
				break;
			case 2:
				// 初始化卡片
				initCard();
				break;
			default:
				break;
			}
		}

	};

	private void showSelectedWord(View v) {
		statusImg((ImageView)v);
		selNum++;
		if (selNum >= 6) {
			delayTime(2000, 1);
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
		ivImg.clearAnimation();
		ivImg.setVisibility(View.GONE);
		ivImg.setEnabled(false);
		if(indexSelect != -1) {
			imgCard[indexSelect].setImageDrawable(BaseCommon.drawableChange(path, cardName[indexSelect] + "_sel"));
		}
		playSoundMusic(BaseCommon.path_game + "my_" + cardName[indexSelect] + ".mp3");
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