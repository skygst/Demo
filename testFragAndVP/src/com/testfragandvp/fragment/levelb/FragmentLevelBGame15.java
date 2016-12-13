package com.testfragandvp.fragment.levelb;

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

import com.ebodoo.raz.data.FixedLevelBPosition;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.LayoutParameters;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 * You And I
 * 
 * @author
 * 
 */
public class FragmentLevelBGame15 extends Fragment implements OnClickListener, OnTouchListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivSentence, ivCard1, ivCard2, ivCard3, ivCard4;
	private ImageView ivBottomBg1, ivBottomBg2, ivBottomBg3, ivBottomBg4,
			ivBottomCard1, ivBottomCard2, ivBottomCard3,
			ivBottomCard4;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = { "bike", "dance", "run", "swing" };
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private int[] rand, randB;
	private static String path;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private ImageView[] ivCard;
	private ImageView[] ivBottomCard;
	private ImageView[] ivBottomBg;
	private int width, height;
	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个

	public FragmentLevelBGame15() {
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
		view = inflater.inflate(R.layout.level_b_game_15, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		path = BaseCommon.pathLevelBGameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		// 提示语
		playSoundMusic(BaseCommon.pathLevelBGame +  "you_and_i.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivBottomBg1 = (ImageView) view.findViewById(R.id.iv_bottom_bg_1);
		ivBottomBg2 = (ImageView) view.findViewById(R.id.iv_bottom_bg_2);
		ivBottomBg3 = (ImageView) view.findViewById(R.id.iv_bottom_bg_3);
		ivBottomBg4 = (ImageView) view.findViewById(R.id.iv_bottom_bg_4);
		ivBottomCard1 = (ImageView) view.findViewById(R.id.iv_bottom_card_1);
		ivBottomCard2 = (ImageView) view.findViewById(R.id.iv_bottom_card_2);
		ivBottomCard3 = (ImageView) view.findViewById(R.id.iv_bottom_card_3);
		ivBottomCard4 = (ImageView) view.findViewById(R.id.iv_bottom_card_4);
		
		rlLayout.setBackground(BaseCommon.drawableChange(path,
				"farm_bg"));
		commonPosition();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void commonPosition() {
		setViewPosition(ivSentence, 0);
		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		for(int i=0; i<ivCard.length; i++) {
			setViewPosition(ivCard[i], (i + 1));
		}
		ivBottomBg = new ImageView[4];
		ivBottomBg[0] = ivBottomBg1;
		ivBottomBg[1] = ivBottomBg2;
		ivBottomBg[2] = ivBottomBg3;
		ivBottomBg[3] = ivBottomBg4;
		for(int i=0; i<ivBottomBg.length; i++) {
			setViewPosition(ivBottomBg[i], (i + 5));
		}
		ivBottomCard = new ImageView[4];
		ivBottomCard[0] = ivBottomCard1;
		ivBottomCard[1] = ivBottomCard2;
		ivBottomCard[2] = ivBottomCard3;
		ivBottomCard[3] = ivBottomCard4;
		refreshData();
	}
	
	private void refreshData() {
		selectNum = 0;
		rand = BaseCommon.getList(arrayList);
		randB = BaseCommon.getList(arrayList);
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "you_and_i_sentence"));
		
		for(int i=0; i<ivBottomCard.length; i++) {
			ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, "you_and_i_card_" + nameId[randB[i]] + "_del"));
			ivBottomBg[i].setImageDrawable(BaseCommon.drawableChange(path, "you_and_i_bottom_" + nameId[rand[i]] + "_sel"));
			ivBottomCard[i].setOnTouchListener(this);
			
			setViewPosition(ivBottomCard[i], (i + 5));
			ivBottomCard[i].setVisibility(View.VISIBLE);
			ivBottomCard[i].setEnabled(true);
			ivBottomCard[i].setImageDrawable(BaseCommon.drawableChange(path, "you_and_i_bottom_" + nameId[rand[i]] + "_del"));
		}
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_15_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
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
			} else if (indexSelect == 2) {
				ivCard3.getLocationOnScreen(location);
				ivCard3.getGlobalVisibleRect(viewRect);
			} else if (indexSelect == 3) {
				ivCard4.getLocationOnScreen(location);
				ivCard4.getGlobalVisibleRect(viewRect);
			} else {
				ivCard1.getLocationOnScreen(location);
				ivCard1.getGlobalVisibleRect(viewRect);
			}

			// View otherView = v;
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			if ((indexSelect >= 0)
					&& Rect.intersects(viewRect, otherViewRect)) {
				setBestPosition(v, mx, my);
			} else {
				MediaCommon.playFuxiError(mContext);
				if (v == ivBottomCard1) {
					setViewPosition(ivBottomCard1, 5);
				} else if (v == ivBottomCard2) {
					setViewPosition(ivBottomCard2, 6);
				} else if (v == ivBottomCard3) {
					setViewPosition(ivBottomCard3, 7);
				} else if (v == ivBottomCard4) {
					setViewPosition(ivBottomCard4, 8);
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
		if (v == ivBottomCard1) {
			frontView(ivBottomCard1, 0);
		} else if (v == ivBottomCard2) {
			frontView(ivBottomCard2, 1);
		} else if (v == ivBottomCard3) {
			frontView(ivBottomCard3, 2);
		} else if (v == ivBottomCard4) {
			frontView(ivBottomCard4, 3);
		}
	}

	private void frontView(ImageView ivPic, int index) {
		ivPic.bringToFront();
		indexSelect = getRand(index);
	}

	private int getRand(int index) {
		for (int i = 0; i < arrayList.length; i++) {
			if (randB[i] == rand[index]) {
				return i;
			}
		}
		return -1;
	}

	// 获得最佳停留位置
	private void setBestPosition(View v, float x, float y) {
		final int[] location = new int[2];
		if (indexSelect >= 0) {
			ivCard[indexSelect].getLocationOnScreen(location);
		}
		if (indexSelect >= 0) {
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
				Object[] object = (Object[]) msg.obj;
				ImageView iv2 = (ImageView) object[0];
				int selPos = (Integer) object[1];
				iv2.setImageDrawable(BaseCommon.drawableChange(path, "you_and_i_card_" + nameId[randB[selPos]] + "_sel"));
				ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "you_and_i_sentence_" + nameId[randB[selPos]]));
				playSoundMusic(BaseCommon.pathLevelBGame +  "you_and_i_" + nameId[randB[selPos]] + ".mp3");
				if (selectNum >= 4) {
					// 延迟1s --- 跳转到选择题界面
					delayFinish(false);
				}
				break;
			case 2:
				ActivityBiz.finishBfBookGame();
				break;
			case 3:
				refreshData();
				break;
			default:
				break;
			}
		}

	};
	
	private void delayFinish(final boolean isRefresh) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					if(isRefresh) {
						handler.sendMessage(handler.obtainMessage(3));
					} else {
						handler.sendMessage(handler.obtainMessage(2));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private int selectNum = 0;

	private void showSelectedWord(View v) {
		if (selectedId == R.id.iv_bottom_card_1) {
			statusImg((ImageView) v, 0);
		} else if (selectedId == R.id.iv_bottom_card_2) {
			statusImg((ImageView) v, 1);
		} else if (selectedId == R.id.iv_bottom_card_3) {
			statusImg((ImageView) v, 2);
		} else if (selectedId == R.id.iv_bottom_card_4) {
			statusImg((ImageView) v, 3);
		}
		selectNum++;
		// 保护的用途
		selectedId = 0;
	}
	
	private void statusImg(ImageView ivImg, int index) {
		delayShowPic(ivCard[indexSelect], indexSelect);
		ivImg.clearAnimation();
		ivImg.setVisibility(View.GONE);
		ivImg.setEnabled(false);
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	private void delayShowPic(final ImageView iv, final int index) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Object[] obj = new Object[2];
				obj[0] = iv;
				obj[1] = index;
				handler.sendMessage(handler.obtainMessage(1, obj));
			}
		}).start();
	}

}