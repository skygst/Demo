package com.gst.move.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.data.FixedPositionAfrica;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.LayoutParameters;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;
import com.gst.move.utils.MultipleChoiceContent;

/**
 *  Humpty Dumpty
 * @author
 *
 */
public class AfricaGame16Activity extends Activity implements OnTouchListener,
    OnClickListener{

	private Context mContext;
	private RelativeLayout rlLayout;
	private ImageView ivHumpty, ivHouse, ivCard0, ivCard1, ivCard2, ivCard3, ivCard4,
			ivCard5, ivBottomBg, ivBottomBg1, ivBottomBg2, ivBottomBg3,
			ivBottomBg4, ivBottomBg5, ivBottomPic1, ivBottomPic2, ivBottomPic3,
			ivBottomPic4, ivBottomPic5;
	private TextView tvTitle;
	private ImageView ivBack;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] picName = {"arm", "face", "face2", "hat", "leg"};
	private int[] arrayList = new int[] { 0, 1, 2, 3, 4};
	private int[] randB;
	private ImageView[] bottomPic, bottomBg, ivCard;
	private String path;
	private int width, height;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int selNum = 0;
	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个
	private int currentIndex = -1;

	public AfricaGame16Activity() {
		super();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_game_16);
		
		init();
		setView();
	}

	private void init() {
		biz = new VideoBiz();
		mContext = AfricaGame16Activity.this;
		selNum = 0;
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "humpty_dumpty_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivHumpty = (ImageView) findViewById(R.id.iv_humpty);
		ivHouse = (ImageView) findViewById(R.id.iv_horse);
		ivCard0 = (ImageView) findViewById(R.id.iv_card_0);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) findViewById(R.id.iv_card_4);
		ivCard5 = (ImageView) findViewById(R.id.iv_card_5);
		ivBottomBg = (ImageView) findViewById(R.id.iv_bottom_bg);
		ivBottomBg1 = (ImageView) findViewById(R.id.iv_bottom_bg_1);
		ivBottomBg2 = (ImageView) findViewById(R.id.iv_bottom_bg_2);
		ivBottomBg3 = (ImageView) findViewById(R.id.iv_bottom_bg_3);
		ivBottomBg4 = (ImageView) findViewById(R.id.iv_bottom_bg_4);
		ivBottomBg5 = (ImageView) findViewById(R.id.iv_bottom_bg_5);
		ivBottomPic1 = (ImageView) findViewById(R.id.iv_bottom_pic_1);
		ivBottomPic2 = (ImageView) findViewById(R.id.iv_bottom_pic_2);
		ivBottomPic3 = (ImageView) findViewById(R.id.iv_bottom_pic_3);
		ivBottomPic4 = (ImageView) findViewById(R.id.iv_bottom_pic_4);
		ivBottomPic5 = (ImageView) findViewById(R.id.iv_bottom_pic_5);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "humpty_dumpty_bg"));
		tvTitle = (TextView) findViewById(R.id.tv_title);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		tvTitle.setText(MultipleChoiceContent.titles[12]);
		setViewPosition(tvTitle, 14);
		setViewPosition(ivBack, 15);
		commonPosition();
		initCard();
		ivBottomBg.setImageDrawable(BaseCommon.drawableChange(path, "humpty_dumpty_bottom_bg"));
		ivBack.setOnClickListener(this);
	}
	
	private void initCard() {
		randB = BaseCommon.getList(arrayList);
		ivCard = new ImageView[5];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		ivCard[4] = ivCard5;
		for(int i=0; i<ivCard.length; i++) {
			setViewPosition(ivCard[i], 1);
		}
		
		bottomBg = new ImageView[5];
		bottomBg[0] = ivBottomBg1;
		bottomBg[1] = ivBottomBg2;
		bottomBg[2] = ivBottomBg3;
		bottomBg[3] = ivBottomBg4;
		bottomBg[4] = ivBottomBg5;
		for(int i=0; i<bottomBg.length; i++) {
			bottomBg[i].setImageDrawable(BaseCommon.drawableChange(path, "humpty_dumpty_bottom_pic_bg_del"));
			setViewPosition(bottomBg[i], i + 3);
		}
		
		bottomPic = new ImageView[5];
		bottomPic[0] = ivBottomPic1;
		bottomPic[1] = ivBottomPic2;
		bottomPic[2] = ivBottomPic3;
		bottomPic[3] = ivBottomPic4;
		bottomPic[4] = ivBottomPic5;
		for(int i=0; i<bottomPic.length; i++) {
			bottomPic[i].setImageDrawable(BaseCommon.drawableChange(path, "humpty_dumpty_bottom_pic_" + picName[randB[i]]));
			setViewPosition(bottomPic[i], i + 8);
			bottomPic[i].setOnTouchListener(this);
		}
		ivCard0.setImageDrawable(BaseCommon.drawableChange(path, "humpty_bg"));
		ivHumpty.setImageDrawable(BaseCommon.drawableChange(path, "humpty_dumpty_humpty_scry"));
		ivHouse.setImageDrawable(BaseCommon.drawableChange(path, "humpty_dumpty_horse_sad"));
	}
	
	private void commonPosition() {
		setViewPosition(ivHumpty, 0);
		setViewPosition(ivCard0, 1);
		setViewPosition(ivHouse, 2);
		setViewPosition(ivBottomBg, 13);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.humpty_dumpty_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	@Override
	public void onClick(View v) {
		if (v == ivBack) {
			clearMediaPlayer();
		}
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
			if (indexSelect != -1) {//
			// 计算该视图在全局坐标系中的x，y值，（注意这个值是要从屏幕顶端算起，也就是索包括了通知栏的高度）//获取在当前屏幕内的绝对坐标
				ivCard0.getLocationOnScreen(location);
				ivCard0.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
				// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
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
				if (v == ivBottomPic1) {
					setViewPosition(v, 8);
				} else if (v == ivBottomPic2) {
					setViewPosition(v, 9);
				} else if (v == ivBottomPic3) {
					setViewPosition(v, 10);
				} else if (v == ivBottomPic4) {
					setViewPosition(v, 11);
				} else if (v == ivBottomPic5) {
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
		if (v == ivBottomPic1) {
			frontView(v, 0);
		} else if (v == ivBottomPic2) {
			frontView(v, 1);
		} else if (v == ivBottomPic3) {
			frontView(v, 2);
		} else if (v == ivBottomPic4) {
			frontView(v, 3);
		} else if (v == ivBottomPic5) {
			frontView(v, 4);
		}
	}
	
	private void frontView(View ivPic, int index) {
		ivPic.bringToFront();
		indexSelect = getRand(index);
		currentIndex = index;
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
			ivCard[indexSelect].getLocationOnScreen(location);
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
				finish();
				break;
			default:
				break;
			}
		}

	};

	private void showSelectedWord(View v) {
		statusImg((ImageView)v);
		selNum++;
		if (selNum >= 5) {
			ivHumpty.setImageDrawable(BaseCommon.drawableChange(path, "humpty_dumpty_humpty_smile"));
			ivHouse.setImageDrawable(BaseCommon.drawableChange(path, "humpty_dumpty_horse_smile"));
			playSoundMusic(BaseCommon.path_game + "eg_humpty_dumpty_prologue_2.mp3");
			delayTime(6000, 1);
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
			if(selNum < 5) {
				ivCard[selNum].setImageDrawable(BaseCommon.drawableChange(path, "humpty_dumpty_" + picName[indexSelect]));
				bottomBg[currentIndex].setImageDrawable(BaseCommon.drawableChange(path, "humpty_dumpty_bottom_pic_bg_sel"));
			}
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
			finish();
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