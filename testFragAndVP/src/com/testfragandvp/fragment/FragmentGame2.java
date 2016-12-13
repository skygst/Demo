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
 *  Hamster Home
 * @author 
 *
 */
public class FragmentGame2 extends Fragment implements OnTouchListener,
		OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard, ivCard0, ivCard1, ivCard2, ivCard3, ivCard4, ivCard5,
			ivCard6, ivCard7, ivpic1, ivpic2, ivpic3, ivpic4, ivpic5
			,ivpicBg1, ivpicBg2, ivpicBg3, ivpicBg4, ivpicBg5;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private int[] arrayList = new int[] { 0, 1, 2, 3, 4 };
	private String[] cardId = { "wheel", "tube", "ball", "food", "water" };
	private String[] cardId2 = { "hamster_wheel", "hamster_tube", "hamster_ball", 
			"hamster_food", "hamster_water" };
	private String[] bottomCardId = { "hamster_04_up", "hamster_03_up", "hamster_05_up", 
			"hamster_01_up", "hamster_02_up"};
	private String[] bottomBgCardId = { "hamster_04_down", "hamster_03_down", "hamster_05_down", 
			"hamster_01_down", "hamster_02_down"};
	private int[] rand;
	private String path;
	private int width, height;
	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个
	private int lastIndex = -1;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效

	public FragmentGame2() {
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
		init();
		view = inflater.inflate(R.layout.af_game_2, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		selectNum = 0;
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		// 提示语
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "hamster_home_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard = (ImageView) view.findViewById(R.id.iv_card);
		ivCard0 = (ImageView) view.findViewById(R.id.iv_card_0);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivCard5 = (ImageView) view.findViewById(R.id.iv_card_5);
		ivCard6 = (ImageView) view.findViewById(R.id.iv_card_6);
		ivCard7 = (ImageView) view.findViewById(R.id.iv_card_7);
		ivpicBg1 = (ImageView) view.findViewById(R.id.iv_pic_bg_1);
		ivpicBg2 = (ImageView) view.findViewById(R.id.iv_pic_bg_2);
		ivpicBg3 = (ImageView) view.findViewById(R.id.iv_pic_bg_3);
		ivpicBg4 = (ImageView) view.findViewById(R.id.iv_pic_bg_4);
		ivpicBg5 = (ImageView) view.findViewById(R.id.iv_pic_bg_5);
		ivpic1 = (ImageView) view.findViewById(R.id.iv_pic_1);
		ivpic2 = (ImageView) view.findViewById(R.id.iv_pic_2);
		ivpic3 = (ImageView) view.findViewById(R.id.iv_pic_3);
		ivpic4 = (ImageView) view.findViewById(R.id.iv_pic_4);
		ivpic5 = (ImageView) view.findViewById(R.id.iv_pic_5);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "hamster_home_bg"));
		ivpic1.setOnTouchListener(this);
		ivpic2.setOnTouchListener(this);
		ivpic3.setOnTouchListener(this);
		ivpic4.setOnTouchListener(this);
		ivpic5.setOnTouchListener(this);

		commonPosition();
		resetOrder();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void resetOrder() {
		rand = BaseCommon.getList(arrayList);
		showCard();
	}

	private void showCard() {
		ivCard0.setImageDrawable(BaseCommon.drawableChange(path,
				"hamster_big_bg"));
		ivCard7.setImageDrawable(BaseCommon.drawableChange(path,
				"little_hamster"));
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "hamster_bg"));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(path, cardId[0]));
		ivCard3.setImageDrawable(BaseCommon.drawableChange(path, cardId[1]));
		ivCard4.setImageDrawable(BaseCommon.drawableChange(path, cardId[2]));
		ivCard5.setImageDrawable(BaseCommon.drawableChange(path, cardId[3]));
		ivCard6.setImageDrawable(BaseCommon.drawableChange(path, cardId[4]));

		ivpicBg1.setImageDrawable(BaseCommon.drawableChange(path,
				bottomBgCardId[rand[0]]));
		ivpicBg2.setImageDrawable(BaseCommon.drawableChange(path,
				bottomBgCardId[rand[1]]));
		ivpicBg3.setImageDrawable(BaseCommon.drawableChange(path,
				bottomBgCardId[rand[2]]));
		ivpicBg4.setImageDrawable(BaseCommon.drawableChange(path,
				bottomBgCardId[rand[3]]));
		ivpicBg5.setImageDrawable(BaseCommon.drawableChange(path,
				bottomBgCardId[rand[4]]));
		ivpic1.setImageDrawable(BaseCommon.drawableChange(path,
				bottomCardId[rand[0]]));
		ivpic2.setImageDrawable(BaseCommon.drawableChange(path,
				bottomCardId[rand[1]]));
		ivpic3.setImageDrawable(BaseCommon.drawableChange(path,
				bottomCardId[rand[2]]));
		ivpic4.setImageDrawable(BaseCommon.drawableChange(path,
				bottomCardId[rand[3]]));
		ivpic5.setImageDrawable(BaseCommon.drawableChange(path,
				bottomCardId[rand[4]]));
	}

	private void commonPosition() {
		setViewPosition(ivCard, 1);
		setViewPosition(ivCard0, 0);
		setViewPosition(ivCard1, 1);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 1);
		setViewPosition(ivCard4, 1);
		setViewPosition(ivCard5, 1);
		setViewPosition(ivCard6, 1);
		setViewPosition(ivCard7, 1);
		setViewPosition(ivpicBg1, 2);
		setViewPosition(ivpicBg2, 3);
		setViewPosition(ivpicBg3, 4);
		setViewPosition(ivpicBg4, 5);
		setViewPosition(ivpicBg5, 6);
		setViewPosition(ivpic1, 2);
		setViewPosition(ivpic2, 3);
		setViewPosition(ivpic3, 4);
		setViewPosition(ivpic4, 5);
		setViewPosition(ivpic5, 6);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.hamater_home_position,
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
			ivCard1.getLocationOnScreen(location);
			ivCard1.getGlobalVisibleRect(viewRect);
			
			// View otherView = v;
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect == 0 || indexSelect == 1 || indexSelect == 2 || indexSelect == 3 || indexSelect == 4)
					&& Rect.intersects(viewRect, otherViewRect)) {
				setBestPosition(v, mx, my);
			} else {
				 MediaCommon.playFuxiError(mContext);
				if (v == ivpic1) {
					setViewPosition(ivpic1, 2);
				} else if (v == ivpic2) {
					setViewPosition(ivpic2, 3);
				} else if (v == ivpic3) {
					setViewPosition(ivpic3, 4);
				} else if (v == ivpic4) {
					setViewPosition(ivpic4, 5);
				} else if (v == ivpic5) {
					setViewPosition(ivpic5, 6);
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

	// 把点击的图片置顶到顶层
	private void ivToFront(View v) {
		selectedId = v.getId();
		indexSelect = -1;
		if (v == ivpic1) {
			frontView(ivpic1, 0);
		} else if (v == ivpic2) {
			frontView(ivpic2, 1);
		} else if (v == ivpic3) {
			frontView(ivpic3, 2);
		} else if (v == ivpic4) {
			frontView(ivpic4, 3);
		} else if (v == ivpic5) {
			frontView(ivpic5, 4);
		}
	}

	private void frontView(ImageView ivPic, int index) {
		ivPic.bringToFront();
		indexSelect = getRand(index);
	}

	private int getRand(int index) {
		for(int i=0; i<arrayList.length; i++) {
			if(rand[index] == arrayList[i]) {
				return arrayList[i];
			}
		}
		return -1;
	}

	// 获得最佳停留位置
	private void setBestPosition(View v, float x, float y) {
		final int[] location = new int[2];
		if (indexSelect == 0) {
			ivCard1.getLocationOnScreen(location);
		} else if (indexSelect == 1) {
			ivCard2.getLocationOnScreen(location);
		} else if (indexSelect == 2) {
			ivCard3.getLocationOnScreen(location);
		} else if (indexSelect == 3) {
			ivCard4.getLocationOnScreen(location);
		} else if (indexSelect == 4) {
			ivCard5.getLocationOnScreen(location);
		}
		if (indexSelect == 0 || indexSelect == 1 || indexSelect == 2
				|| indexSelect == 3 || indexSelect == 4) {
			showGoodWord();
		}
	}

	private void showGoodWord() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				handler.sendMessage(handler.obtainMessage(0));
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
				showLastView();
				break;
			case 2:
				ActivityBiz.finishAfBookGame();
				break;
			default:
				break;
			}
		}

	};

	private int selectNum = 0;

	private void showSelectedWord() {
		if (selectedId == R.id.iv_pic_1) {
			statusImg(ivpic1, 0);
		} else if (selectedId == R.id.iv_pic_2) {
			statusImg(ivpic2, 1);
		} else if (selectedId == R.id.iv_pic_3) {
			statusImg(ivpic3, 2);
		} else if (selectedId == R.id.iv_pic_4) {
			statusImg(ivpic4, 3);
		} else if (selectedId == R.id.iv_pic_5) {
			statusImg(ivpic5, 4);
		}
		selectNum++;
		if (selectNum >= 5) {
			 delayCarriedOut();
		}
		// 保护的用途
		selectedId = 0;
	}
	
	private void delayCarriedOut() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(500);
					handler.sendMessage(handler.obtainMessage(1));
					Thread.sleep(2500);
					handler.sendMessage(handler.obtainMessage(2));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void statusImg(ImageView ivImg, int index) {
		locationIndex(index);
		ivImg.clearAnimation();
		ivImg.setVisibility(View.GONE);
		ivImg.setEnabled(false);
		playSoundMusic(BaseCommon.path_game + "hamsterhome_" + cardId[indexSelect] + ".mp3");
	}

	private void locationIndex(int index) {
		ivCard7.setVisibility(View.GONE);
		showLastView();
		if (rand[index] == arrayList[0]) {
			lastIndex = 0;
			ivCard.setImageDrawable(BaseCommon.drawableChange(path, cardId2[0]));
		} else if (rand[index] == arrayList[1]) {
			lastIndex = 1;
			ivCard.setImageDrawable(BaseCommon.drawableChange(path, cardId2[1]));
		} else if (rand[index] == arrayList[2]) {
			lastIndex = 2;
			ivCard.setImageDrawable(BaseCommon.drawableChange(path, cardId2[2]));
		} else if (rand[index] == arrayList[3]) {
			lastIndex = 3;
			ivCard5.setImageDrawable(BaseCommon.drawableChange(path, cardId2[3]));
			ivCard5.setVisibility(View.VISIBLE);
		} else if(rand[index] == arrayList[4]) {
			lastIndex = 4;
			ivCard6.setImageDrawable(BaseCommon.drawableChange(path, cardId2[4]));
			ivCard6.setVisibility(View.VISIBLE);
		}
		// playSoundMusic(ConstantEbook.path_reaEbook01 +
		// drawableId[randB[index]] + ".mp3");
	}
	
	private void showLastView() {
		ivCard.setImageDrawable(null);
		if(lastIndex == 0) {
			ivCard2.setVisibility(View.VISIBLE);
		} else if(lastIndex == 1) {
			ivCard3.setVisibility(View.VISIBLE);
		} else if(lastIndex == 2) {
			ivCard4.setVisibility(View.VISIBLE);
		} else if(lastIndex == 3) {
			ivCard5.setImageDrawable(BaseCommon.drawableChange(path, cardId[3]));
		} else if(lastIndex == 4) {
			ivCard6.setImageDrawable(BaseCommon.drawableChange(path, cardId[4]));
		}
		lastIndex = -1;
	}
	
	private void playSoundMusic(String path) {
		mMediaPlayerSound = ActivityBiz.playSoundMusic(mMediaPlayerSound, path);
	}
	
	private void clearMediaPlayer() {
		try {
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