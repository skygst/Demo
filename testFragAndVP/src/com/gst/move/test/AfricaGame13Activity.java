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
 *  Pond Animals
 * @author
 *
 */
public class AfricaGame13Activity extends Activity implements OnTouchListener,
		OnClickListener {

	private Context mContext;
	private RelativeLayout rlLayout;
	private ImageView ivSentence, ivCard1, ivCard2, ivCard3, ivCard4, ivGift1, ivGift2, ivGift3, ivGift4;
	private TextView tvTitle;
	private ImageView ivBack;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardName = {"grandpa", "grandma", "mum", "dad"};
	private ImageView[] imgGift, ivCard;
	private String path;
	private int width, height;
	private int selectedId = 0;
	private float x, y;
	private int mx, my;
	private int mWidth, mHeight;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int selNum = 0;
	private boolean[] status = {false, false, false, false};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.af_game_13);
		
		init();
		setView();
	}

	private void init() {
		mContext = AfricaGame13Activity.this;
		biz = new VideoBiz();
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "black_sheep_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) findViewById(R.id.iv_card_4);
		ivGift1 = (ImageView) findViewById(R.id.iv_gift_1);
		ivGift2 = (ImageView) findViewById(R.id.iv_gift_2);
		ivGift3 = (ImageView) findViewById(R.id.iv_gift_3);
		ivGift4 = (ImageView) findViewById(R.id.iv_gift_4);
		ivSentence = (ImageView) findViewById(R.id.iv_sentence);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		tvTitle.setText(MultipleChoiceContent.titles[12]);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "black_sheep_bg"));
		setViewPosition(tvTitle, 9);
		setViewPosition(ivBack, 10);
		showCard();
		ivBack.setOnClickListener(this);
	}
	
	private void showCard() {
		imgGift = new ImageView[4];
		ivCard = new ImageView[4];
		imgGift[0] = ivGift1;
		imgGift[1] = ivGift2;
		imgGift[2] = ivGift3;
		imgGift[3] = ivGift4;
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		initCard();
	}
	
	private void initCard() {
		setViewPosition(ivSentence, 0);
		for(int i=0; i<imgGift.length; i++) {
			imgGift[i].setImageDrawable(BaseCommon.drawableChange(path, "black_sheep_box"));
			setViewPosition(imgGift[i], 1 + i);
			imgGift[i].setEnabled(true);
			imgGift[i].setOnTouchListener(this);
		}
		for(int i=0; i<ivCard.length; i++) {
			ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, "black_sheep_" + cardName[i] + "_del"));
			setViewPosition(ivCard[i], 5 + i);
		}
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "black_sheep_sentence_del"));
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.black_sheep_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivBack) {
			clearMediaPlayer();
		}
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
			int[] location = new int[2];
			Rect viewRect = new Rect(); // 定义一个矩形区域
			int[] location2 = new int[2];
			Rect viewRect2 = new Rect(); // 定义一个矩形区域
			int[] location3 = new int[2];
			Rect viewRect3 = new Rect(); // 定义一个矩形区域
			int[] location4 = new int[2];
			Rect viewRect4 = new Rect(); // 定义一个矩形区域
			ivCard1.getLocationOnScreen(location);
			ivCard1.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
			ivCard2.getLocationOnScreen(location2);
			ivCard2.getGlobalVisibleRect(viewRect2);
			ivCard3.getLocationOnScreen(location3);
			ivCard3.getGlobalVisibleRect(viewRect3);
			ivCard4.getLocationOnScreen(location4);
			ivCard4.getGlobalVisibleRect(viewRect4);
			
			Rect otherViewRect = new Rect();
			v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
													// 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
			// 判断这两个坐标是否重叠 判断这两个对象是否有重叠
			indexSelect = -1;
			if(Rect.intersects(viewRect, otherViewRect) && !status[0]) {
				indexSelect = 0;
				status[0] = true;
			} else if(Rect.intersects(viewRect2, otherViewRect) && !status[1]) {
				indexSelect = 1;
				status[1] = true;
			} else if(Rect.intersects(viewRect3, otherViewRect) && !status[2]) {
				indexSelect = 2;
				status[2] = true;
			} else if(Rect.intersects(viewRect4, otherViewRect) && !status[3]) {
				indexSelect = 3;
				status[3] = true;
			}
			if ((indexSelect != -1)
					&& (Rect.intersects(viewRect, otherViewRect) || 
							Rect.intersects(viewRect2, otherViewRect) || 
							Rect.intersects(viewRect3, otherViewRect) || 
							Rect.intersects(viewRect4, otherViewRect))) {
				setBestPosition(v, mx, my);
			} else {
				if (v == ivGift1) {
					setViewPosition(v, 1);
				} else if (v == ivGift2) {
					setViewPosition(v, 2);
				} else if (v == ivGift3) {
					setViewPosition(v, 3);
				} else if (v == ivGift4) {
					setViewPosition(v, 4);
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
		v.bringToFront();
	}
	
	// 获得最佳停留位置
	private void setBestPosition(View v, float x, float y) {
		final int[] location = new int[2];
		if (indexSelect >= 0 && indexSelect < 4) {
			imgGift[indexSelect].getLocationOnScreen(location);
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
		if (selNum >= 4) {
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
			ivCard[indexSelect].setImageDrawable(BaseCommon.drawableChange(path, "black_sheep_" + cardName[indexSelect] + "_sel"));
			ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "black_sheep_sentence_" + cardName[indexSelect]));
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