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
 * He Runs
 * 
 * @author
 * 
 */
public class FragmentGame3 extends Fragment implements OnTouchListener,
		OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivLeft1, ivLeft2, ivRight1, ivRight2,
			ivBottompic1, ivBottompic2, ivBottompic3, ivBottompic4;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private String[] cardVehicles = { "he_runs_bus", "he_runs_train",
			"he_runs_plan", "he_runs_boat" };
	private String[] cardPlace = { "he_runs_store", "he_runs_school",
			"he_runs_pool", "he_runs_home" };
	private String[] upVehicles = { "he_runs_up_bus", "he_runs_up_train",
			"he_runsup_up_plan", "he_runs_up_boat" };
	private String[] upPlace = { "he_runs_up_store", "he_runs_up_school",
			"he_runs_up_pool", "he_runs_up_home" };
	private String[] card1;
	private String[] card2;
	private int[] upIndex = { 6, 8, 10, 12 };
	private int[] downIndex = { 14, 16, 18, 20 };
	private String[] currentCard;
	private String[] upCurrentCard;
	private int[] upLocation;
	private int[] rand;
	private int[] randB;
	private String path;
	private int width, height;
	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个
	private int updateTimes = 1;
	private boolean isLeftFirst = true;
	private boolean isRightFirst = true;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效

	public FragmentGame3() {
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
		view = inflater.inflate(R.layout.af_game_3, container, false);
		init();
//		processData();
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		selectNum = 0;
		updateTimes = 1;
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		// rand = BaseCommon.getList(arrayList);
		// 提示语
		playSoundMusic(BaseCommon.path_game
				+ BaseCommon.mp3Path(mContext, "he_runs_prologue.mp3"));
	}

	private void processData() {
		currentCard = new String[4];
		upCurrentCard = new String[4];
		upLocation = new int[4];
		int index = 0;
		String[] cardSound1 = { "bus", "train", "store", "school" };
		String[] cardSound2 = { "plan", "boat", "pool", "home" };
		randB = BaseCommon.getList(arrayList);
		rand = BaseCommon.getList(new int[] { 0, 1 });
		card1 = new String[4];
		card2 = new String[4];
		String[] card11 = new String[] { cardSound1[rand[0]], cardSound1[rand[1]],
				cardSound1[rand[0] + 2], cardSound1[rand[1] + 2] };
		card1[0] = card11[randB[0]];
		card1[1] = card11[randB[1]];
		card1[2] = card11[randB[2]];
		card1[3] = card11[randB[3]];
		if (updateTimes == 2) {
			index = 2;
			String[] card22 = new String[] { cardSound2[rand[0]],
					cardSound2[rand[1]], cardSound2[rand[0] + index],
					cardSound2[rand[1] + index] };
			card2[0] = card22[randB[0]];
			card2[1] = card22[randB[1]];
			card2[2] = card22[randB[2]];
			card2[3] = card22[randB[3]];
		}
		String[] bottomPic = { cardVehicles[rand[0] + index],
				cardVehicles[rand[1] + index], cardPlace[rand[0] + index],
				cardPlace[rand[1] + index] };
		currentCard[0] = bottomPic[randB[0]];
		currentCard[1] = bottomPic[randB[1]];
		currentCard[2] = bottomPic[randB[2]];
		currentCard[3] = bottomPic[randB[3]];
		String[] upPic = { upVehicles[rand[0] + index],
				upVehicles[rand[1] + index], upPlace[rand[0] + index],
				upPlace[rand[1] + index] };
		upCurrentCard[0] = upPic[randB[0]];
		upCurrentCard[1] = upPic[randB[1]];
		upCurrentCard[2] = upPic[randB[2]];
		upCurrentCard[3] = upPic[randB[3]];
		int[] locationIndex = { upIndex[rand[0] + index],
				upIndex[rand[1] + index], downIndex[rand[0] + index],
				downIndex[rand[1] + index] };
		upLocation[0] = locationIndex[randB[0]];
		upLocation[1] = locationIndex[randB[1]];
		upLocation[2] = locationIndex[randB[2]];
		upLocation[3] = locationIndex[randB[3]];
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivLeft1 = (ImageView) view.findViewById(R.id.iv_left_1);
		ivLeft2 = (ImageView) view.findViewById(R.id.iv_left_2);
		ivRight1 = (ImageView) view.findViewById(R.id.iv_right_1);
		ivRight2 = (ImageView) view.findViewById(R.id.iv_right_2);
		ivBottompic1 = (ImageView) view.findViewById(R.id.iv_bottom_pic_1);
		ivBottompic2 = (ImageView) view.findViewById(R.id.iv_bottom_pic_2);
		ivBottompic3 = (ImageView) view.findViewById(R.id.iv_bottom_pic_3);
		ivBottompic4 = (ImageView) view.findViewById(R.id.iv_bottom_pic_4);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "he_runs_bg"));
		ivBottompic1.setOnTouchListener(this);
		ivBottompic2.setOnTouchListener(this);
		ivBottompic3.setOnTouchListener(this);
		ivBottompic4.setOnTouchListener(this);
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		commonPosition();
		showCard();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void showCard() {
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "vehicles_bg"));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(path, "places_bg"));
		refreshData();
	}

	private void refreshData() {
		processData();
		String[] card = currentCard;
		selectNum = 0;
		isLeftFirst = true;
		isRightFirst = true;
		ivBottompic1.setImageDrawable(BaseCommon.drawableChange(path, card[0]));
		ivBottompic2.setImageDrawable(BaseCommon.drawableChange(path, card[1]));
		ivBottompic3.setImageDrawable(BaseCommon.drawableChange(path, card[2]));
		ivBottompic4.setImageDrawable(BaseCommon.drawableChange(path, card[3]));
		ivLeft1.setImageDrawable(null);
		ivLeft2.setImageDrawable(null);
		ivRight1.setImageDrawable(null);
		ivRight2.setImageDrawable(null);
		commonPosition();
		ivBottompic1.setVisibility(View.VISIBLE);
		ivBottompic2.setVisibility(View.VISIBLE);
		ivBottompic3.setVisibility(View.VISIBLE);
		ivBottompic4.setVisibility(View.VISIBLE);
		ivBottompic1.setEnabled(true);
		ivBottompic2.setEnabled(true);
		ivBottompic3.setEnabled(true);
		ivBottompic4.setEnabled(true);
	}

	private void commonPosition() {
		setViewPosition(ivBottompic1, 2);
		setViewPosition(ivBottompic2, 3);
		setViewPosition(ivBottompic3, 4);
		setViewPosition(ivBottompic4, 5);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.he_runs_position,
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
			} else {
				ivCard1.getLocationOnScreen(location);
				ivCard1.getGlobalVisibleRect(viewRect);
			}

			// View otherView = v;
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect == 0 || indexSelect == 1)
					&& Rect.intersects(viewRect, otherViewRect)) {
				setBestPosition(v, mx, my);
			} else {
				MediaCommon.playFuxiError(mContext);
				if (v == ivBottompic1) {
					setViewPosition(ivBottompic1, 2);
				} else if (v == ivBottompic2) {
					setViewPosition(ivBottompic2, 3);
				} else if (v == ivBottompic3) {
					setViewPosition(ivBottompic3, 4);
				} else if (v == ivBottompic4) {
					setViewPosition(ivBottompic4, 5);
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
		if (v == ivBottompic1) {
			frontView(ivBottompic1, 0);
		} else if (v == ivBottompic2) {
			frontView(ivBottompic2, 1);
		} else if (v == ivBottompic3) {
			frontView(ivBottompic3, 2);
		} else if (v == ivBottompic4) {
			frontView(ivBottompic4, 3);
		}
	}

	private void frontView(ImageView ivPic, int index) {
		ivPic.bringToFront();
		indexSelect = getRand(index);
	}

	private int getRand(int index) {
		for (int i = 0; i < arrayList.length; i++) {
			if (randB[index] == 0 || randB[index] == 1) {
				return 0;
			} else if (randB[index] == 2 || randB[index] == 3) {
				return 1;
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
		}
		if (indexSelect == 0 || indexSelect == 1) {
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
				updateTimes = 2;
				refreshData();
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
		if (selectedId == R.id.iv_bottom_pic_1) {
			statusImg(ivBottompic1, 0);
		} else if (selectedId == R.id.iv_bottom_pic_2) {
			statusImg(ivBottompic2, 1);
		} else if (selectedId == R.id.iv_bottom_pic_3) {
			statusImg(ivBottompic3, 2);
		} else if (selectedId == R.id.iv_bottom_pic_4) {
			statusImg(ivBottompic4, 3);
		}
		selectNum++;
		if (selectNum == 4 && updateTimes == 1) {
			delayCarriedOut(1);
		} else if (selectNum == 4 && updateTimes == 2) {
			delayCarriedOut(2);
		}
		// 保护的用途
		selectedId = 0;
	}

	private void upViewStatus(ImageView iv, int num, String picName) {
		iv.setImageDrawable(BaseCommon.drawableChange(path, picName));
		iv.setEnabled(false);
		setViewPosition(iv, num);
	}

	private void delayCarriedOut(final int index) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1500);
					if (index == 1) {
						handler.sendMessage(handler.obtainMessage(1));
					} else {
						handler.sendMessage(handler.obtainMessage(2));
					}
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
		if (updateTimes == 1) {
			playSoundMusic(BaseCommon.path_game + "he_runs_"
					+ card1[index] + ".mp3");
		} else {
			playSoundMusic(BaseCommon.path_game + "he_runs_"
					+ card2[index] + ".mp3");
		}
	}

	private void locationIndex(int index) {
		String[] card;
		int num = 0;
		card = upCurrentCard;
		num = upLocation[index];
		ImageView iv = null;
		if (randB[index] == 0 || randB[index] == 1) {
			if (isLeftFirst) {
				iv = ivLeft1;
				isLeftFirst = false;
			} else {
				iv = ivLeft2;
				num += 1;
			}
		} else {
			if (isRightFirst) {
				iv = ivRight1;
				isRightFirst = false;
			} else {
				iv = ivRight2;
				num += 1;
			}
		}
		upViewStatus(iv, num, card[index]);
		// playSoundMusic(ConstantEbook.path_reaEbook01 +
		// drawableId[randB[index]] + ".mp3");
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