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
 * We Make Cookies
 * 
 * @author
 * 
 */
public class FragmentLevelBGame5 extends Fragment implements OnTouchListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivStep1, ivStep2, ivStep3, ivStep4;
	private ImageView ivBottomBg1, ivBottomBg2, ivBottomBg3, ivBottomBg4,
			ivBottomCard1, ivBottomCard2, ivBottomCard3,
			ivBottomCard4, ivName1, ivName2, ivName3, ivName4;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"flour", "sugar", "salt", "egg", "milk", "butter", "mix", "over"};
	private String[] steps = {"step1", "step2", "step3", "step4", "step5", "step6", "step7", "step8"};
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private int[] rand;
	private int countNum = 0;
	private static String path;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private ImageView[] ivStep;
	private ImageView[] ivCard;
	private ImageView[] ivBottomCard;
	private ImageView[] ivBottomBg;
	private ImageView[] ivName;
	private int width, height;
	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个

	public FragmentLevelBGame5() {
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
		view = inflater.inflate(R.layout.level_b_game_5, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		countNum = 0;
		path = BaseCommon.pathLevelBGameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		// 提示语
		playSoundMusic(BaseCommon.pathLevelBGame +  "we_make_cookies.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivStep1 = (ImageView) view.findViewById(R.id.iv_step_1);
		ivStep2 = (ImageView) view.findViewById(R.id.iv_step_2);
		ivStep3 = (ImageView) view.findViewById(R.id.iv_step_3);
		ivStep4 = (ImageView) view.findViewById(R.id.iv_step_4);
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
		ivName1 = (ImageView) view.findViewById(R.id.iv_name_1);
		ivName2 = (ImageView) view.findViewById(R.id.iv_name_2);
		ivName3 = (ImageView) view.findViewById(R.id.iv_name_3);
		ivName4 = (ImageView) view.findViewById(R.id.iv_name_4);
		
		rlLayout.setBackground(BaseCommon.drawableChange(path,
				"cookies_bg"));
		commonPosition();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void commonPosition() {
		countNum = 0;
		ivStep = new ImageView[4];
		ivStep[0] = ivStep1;
		ivStep[1] = ivStep2;
		ivStep[2] = ivStep3;
		ivStep[3] = ivStep4;
		for(int i=0; i<ivStep.length; i++) {
			setViewPosition(ivStep[i], i);
		}
		
		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		for(int i=0; i<ivCard.length; i++) {
			setViewPosition(ivCard[i], i + 4);
		}
		ivBottomBg = new ImageView[4];
		ivBottomBg[0] = ivBottomBg1;
		ivBottomBg[1] = ivBottomBg2;
		ivBottomBg[2] = ivBottomBg3;
		ivBottomBg[3] = ivBottomBg4;
		for(int i=0; i<ivBottomBg.length; i++) {
			setViewPosition(ivBottomBg[i], (i + 8));
		}
		ivBottomCard = new ImageView[4];
		ivBottomCard[0] = ivBottomCard1;
		ivBottomCard[1] = ivBottomCard2;
		ivBottomCard[2] = ivBottomCard3;
		ivBottomCard[3] = ivBottomCard4;
//		for(int i=0; i<ivBottomCard.length; i++) {
//			setViewPosition(ivBottomCard[i], (i + 8));
//			ivBottomCard[i].setOnTouchListener(this);
//		}
		ivName = new ImageView[4];
		ivName[0] = ivName1;
		ivName[1] = ivName2;
		ivName[2] = ivName3;
		ivName[3] = ivName4;
		for(int i=0; i<ivName.length; i++) {
			setViewPosition(ivName[i], (i + 12));
		}
		refreshData();
	}
	
	private void refreshData() {
		selectNum = 0;
//		indexSelect = -1;
		rand = BaseCommon.getList(arrayList);
		int index = indexValue();
		for(int i=0; i<ivStep.length; i++) {
			setViewPosition(ivStep[i], i);
			ivStep[i].setImageDrawable(BaseCommon.drawableChange(path, "cookies_" + steps[i + index]));
		}
		for(int i=0; i<ivCard.length; i++) {
			ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, "cookies_board"));
		}
		for(int i=0; i<ivBottomBg.length; i++) {
			ivBottomBg[i].setImageDrawable(BaseCommon.drawableChange(path, "cookies_bottom_bg_" + nameId[rand[i] + index]));
		}
		for(int i=0; i<ivBottomCard.length; i++) {
			ivBottomCard[i].setVisibility(View.VISIBLE);
			setViewPosition(ivBottomCard[i], (i + 8));
			ivBottomCard[i].setOnTouchListener(this);
			ivBottomCard[i].setEnabled(true);
			ivBottomCard[i].setImageDrawable(BaseCommon.drawableChange(path, "cookies_bottom_" + nameId[rand[i] + index]));
		}
		for(int i=0; i<ivName.length; i++) {
			ivName[i].setImageDrawable(BaseCommon.drawableChange(path, "cookies_name_" + nameId[rand[i] + index]));
		}
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_5_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
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
					setViewPosition(ivBottomCard1, 8);
				} else if (v == ivBottomCard2) {
					setViewPosition(ivBottomCard2, 9);
				} else if (v == ivBottomCard3) {
					setViewPosition(ivBottomCard3, 10);
				} else if (v == ivBottomCard4) {
					setViewPosition(ivBottomCard4, 11);
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
			if (i == rand[index]) {
				return i;
			}
		}
		return -1;
	}

	// 获得最佳停留位置
	private void setBestPosition(View v, float x, float y) {
		final int[] location = new int[2];
		if (indexSelect >= 0 && indexSelect < 4) {
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
				Object[] object1 = (Object[]) msg.obj;
				int index1 = indexValue();
				ImageView iv1 = (ImageView) object1[0];
				int pos = (Integer) object1[1];
				iv1.setImageDrawable(BaseCommon.drawableChange(path, "cookies_board2"));
				playSoundMusic(BaseCommon.pathLevelBGame +  "make_cookies_" + nameId[pos + index1] + ".mp3");
				break;
			case 2:
				Object[] object = (Object[]) msg.obj;
				int index = indexValue();
				ImageView iv2 = (ImageView) object[0];
				int selPos = (Integer) object[1];
				iv2.setImageDrawable(BaseCommon.drawableChange(path, "cookies_" + nameId[selPos + index]));
				if (countNum == 0 && selectNum >= 4) {
					selectNum = 0;
					countNum++;
					delayChange();
				} else if(countNum == 1 && selectNum >= 4) {
					// 延迟1s --- 跳转到选择题界面
					delayFinish();
				}
				break;
			case 3:
				refreshData();
				break;
			case 4:
				ActivityBiz.finishBfBookGame();
				break;
			default:
				break;
			}
		}

	};
	
	private int indexValue() {
		int index = 0;
		if(countNum != 0) {
			index = 4;
		}
		return index;
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
		delayShowPic(ivCard[rand[index]], indexSelect);
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
				try {
					Object[] obj = new Object[2];
					obj[0] = iv;
					obj[1] = index;
					handler.sendMessage(handler.obtainMessage(1, obj));
					Thread.sleep(1000);
					handler.sendMessage(handler.obtainMessage(2, obj));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	private void delayChange() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1500);
					handler.sendMessage(handler.obtainMessage(3));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void delayFinish() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					handler.sendMessage(handler.obtainMessage(4));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}