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
 *  My dog
 * @author
 *
 */
public class FragmentGame6 extends Fragment implements OnTouchListener,
		OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4,
			ivBottom1, ivBottom2, ivBottom3, ivBottom4, ivSentence, ivPic;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardName = {"sit", "climb", "dig", "hug", "jump", "roll_over", "run", "swim"};
	private int[] arrayList = new int[] { 0, 1, 2, 3};
	private ImageView[] imgCard;
	private ImageView[] bottomWord;
	private int[] rand;
	private int[] randB;
	private String path;
	private int width, height;
	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个
	private MediaPlayer mMediaPlayerSound = null; // 播放音效

	public FragmentGame6() {
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
		view = inflater.inflate(R.layout.af_game_6, container, false);
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
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "my_dog_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivBottom1 = (ImageView) view.findViewById(R.id.iv_bottom_1);
		ivBottom2 = (ImageView) view.findViewById(R.id.iv_bottom_2);
		ivBottom3 = (ImageView) view.findViewById(R.id.iv_bottom_3);
		ivBottom4 = (ImageView) view.findViewById(R.id.iv_bottom_4);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		ivPic = (ImageView) view.findViewById(R.id.iv_pic);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "my_dog_bg"));
		showCard();
		commonPosition();
	}
	
	private void showCard() {
		imgCard = new ImageView[4];
		bottomWord = new ImageView[4];
		imgCard[0] = ivCard1;
		imgCard[1] = ivCard2;
		imgCard[2] = ivCard3;
		imgCard[3] = ivCard4;
		bottomWord[0] = ivBottom1;
		bottomWord[1] = ivBottom2;
		bottomWord[2] = ivBottom3;
		bottomWord[3] = ivBottom4;
		initCard();
		initPic();
	}
	
	private void initCard() {
		rand = BaseCommon.getList(arrayList);
		randB = BaseCommon.getList(arrayList);
		int index = 0;
		if(!isFirst) {
			index = 4;
		}
		for(int i=0; i<imgCard.length; i++) {
			imgCard[i].setImageDrawable(BaseCommon.drawableChange(path, "dog_" + cardName[rand[i] + index] + "_default"));
			bottomWord[i].setImageDrawable(BaseCommon.drawableChange(path, cardName[randB[i] + index] + "_del"));
			bottomWord[i].setEnabled(true);
			bottomWord[i].setOnTouchListener(this);
		}
	}
	
	private void initPic() {
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "dog_sentence_del"));
		ivPic.setImageDrawable(BaseCommon.drawableChange(path, "dog_pic_del"));
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void commonPosition() {
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 2);
		setViewPosition(ivCard4, 3);
		setViewPosition(ivBottom1, 4);
		setViewPosition(ivBottom2, 5);
		setViewPosition(ivBottom3, 6);
		setViewPosition(ivBottom4, 7);
		setViewPosition(ivSentence, 8);
		setViewPosition(ivPic, 9);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.my_dog_position,
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
				initPic();
				if (v == ivBottom1) {
					setViewPosition(v, 4);
				} else if (v == ivBottom2) {
					setViewPosition(v, 5);
				} else if (v == ivBottom3) {
					setViewPosition(v, 6);
				} else if (v == ivBottom4) {
					setViewPosition(v, 7);
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
		}
	}
	
	private void frontView(View ivPic, int index) {
		ivPic.bringToFront();
		indexSelect = getRand(index);
	}
	
	private int getRand(int index) {
		for(int i=0; i<arrayList.length; i++) {
			if(rand[i] == randB[index]) {
				return i;
			}
		}
		return -1;
	}
	
	// 获得最佳停留位置
	private void setBestPosition(View v, float x, float y) {
		final int[] location = new int[2];
		if (indexSelect >= 0 && indexSelect < 8) {
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
			default:
				break;
			}
		}

	};

	private int selNum = 0;
	private boolean isFirst = true;
	
	private void showSelectedWord(View v) {
		statusImg((ImageView)v);
		selNum++;
		if(selNum >= 4) {
			if(!isFirst) {
				delayCarriedOut();
			} else {
				selNum = 0;
				isFirst = false;
				//  初始化卡片
				initCard();
			}
		}
		// 保护的用途
		selectedId = 0;
	}
	
	private void delayCarriedOut() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					handler.sendMessage(handler.obtainMessage(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void statusImg(ImageView ivImg) {
		int index = 0;
		if(!isFirst) {
			index = 4;
		}
		ivImg.clearAnimation();
		ivImg.setImageDrawable(BaseCommon.drawableChange(path, cardName[rand[indexSelect] + index] + "_sel"));
		ivImg.setEnabled(false);
		if (ivImg == ivBottom1) {
			setViewPosition(ivImg, 4);
		} else if (ivImg == ivBottom2) {
			setViewPosition(ivImg, 5);
		} else if (ivImg == ivBottom3) {
			setViewPosition(ivImg, 6);
		} else if (ivImg == ivBottom4) {
			setViewPosition(ivImg, 7);
		}
		if(indexSelect != -1) {
			imgCard[indexSelect].setImageDrawable(BaseCommon.drawableChange(path, "dog_" + cardName[rand[indexSelect] + index] + "_sel"));
		}
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "dog_sentence_" + cardName[rand[indexSelect] + index]));
		ivPic.setImageDrawable(BaseCommon.drawableChange(path, "dog_pic_" + cardName[rand[indexSelect] + index]));
		
		playSoundMusic(BaseCommon.path_game + "mydog_" + cardName[rand[indexSelect] + index] + ".mp3");
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