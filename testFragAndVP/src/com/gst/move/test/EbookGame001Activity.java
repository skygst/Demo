package com.gst.move.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.data.FixedPositionEp;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;

/**
 *  Go Go Go
 * @author 
 *
 */
public class EbookGame001Activity extends BaseActivity implements OnTouchListener, OnClickListener {

	private Context mContext;
	private RelativeLayout rlLayout;
	private ImageView ivBack, ivCardName1, ivCardName2, ivCardName3, ivCardName4;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4;
	private ImageView ivBottomBg, ivBottomImg1, ivBottomImg2, ivBottomImg3,
			ivBottomImg4;
	private float x, y;
	private int mx, my;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private int selectedId = 0;
	private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个
	private int[] randA = new int[] { 0, 1, 2, 3, 4, 5, 6};
	private int[] randB;
	private int[] randC;
	private int[] currentOrder;
	private String[] cardId = {"eg001_board_bird", "eg001_board_duck", "eg001_board_cat", 
			"eg001_board_dog", "eg001_board_cow", "eg001_board_goat", "eg001_board_pig"};
	private String[] fillCardId = {"eg001_card_bird", "eg001_card_duck", "eg001_card_cat", 
			"eg001_card_dog", "eg001_card_cow", "eg001_card_goat", "eg001_card_pig"};
	private String[] nameId = {"eg001_bird_word", "eg001_duck_word", "eg001_cat_word",
			"eg001_dog_word", "eg001_cow_word", "eg001_goat_word", "eg001_pig_word"};
	private String[] drawableId = {"eg001_bird", "eg001_duck", "eg001_cat", 
			"eg001_dog", "eg001_cow", "eg001_goat", "eg001_pig"};
	private int indexTimes = 0;
	private int selectNum = 0;
	private static String path;
	private VideoBiz biz;
	private int mWidth, mHeight;
	private MediaPlayer mMediaPlayer = null;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_gogo);

		initializationData();
		setView();
	}

	private void initializationData() {
		mContext = EbookGame001Activity.this;
//		path = ConstantEbook.path_gameImages;
		path = Constant.sdcard_path + "/raz_english/reaEbook01/images/";
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
//		playSoundMusic(EbookPath.asidePath(1));
	}
	
	private void setView() {
		rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		ivCardName1 = (ImageView) findViewById(R.id.iv_card_name_1);
		ivCardName2 = (ImageView) findViewById(R.id.iv_card_name_2);
		ivCardName3 = (ImageView) findViewById(R.id.iv_card_name_3);
		ivCardName4 = (ImageView) findViewById(R.id.iv_card_name_4);
		ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) findViewById(R.id.iv_card_4);

		ivBottomBg = (ImageView) findViewById(R.id.iv_bottom_bg);
		ivBottomImg1 = (ImageView) findViewById(R.id.iv_bottom_img1);
		ivBottomImg2 = (ImageView) findViewById(R.id.iv_bottom_img2);
		ivBottomImg3 = (ImageView) findViewById(R.id.iv_bottom_img3);
		ivBottomImg4 = (ImageView) findViewById(R.id.iv_bottom_img4);
		
		rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(path,"eg001_go_bg"));
		ivBottomBg.setImageDrawable(CommonBitmap.drawableChange(path,"eg001_go_bottom_bg"));
		resetParams();
		setCommonViewPosition(ivBack, 0);
		setViewPosition(ivBottomBg, 0);
		setViewPosition(ivCardName1, 1);
		setViewPosition(ivCardName2, 2);
		setViewPosition(ivCardName3, 3);
		setViewPosition(ivCardName4, 4);
		setViewPosition(ivCard1, 5);
		setViewPosition(ivCard2, 6);
		setViewPosition(ivCard3, 7);
		setViewPosition(ivCard4, 8);
		commonPosition();

		ivBottomImg1.setOnTouchListener(this);
		ivBottomImg2.setOnTouchListener(this);
		ivBottomImg3.setOnTouchListener(this);
		ivBottomImg4.setOnTouchListener(this);
		
		ivBack.setOnClickListener(this);
	}
	
	private void commonPosition() {
		setViewPosition(ivBottomImg1, 9);
		setViewPosition(ivBottomImg2, 10);
		setViewPosition(ivBottomImg3, 11);
		setViewPosition(ivBottomImg4, 12);
	}
	
	private void resetParams() {
		indexTimes++;
		if(indexTimes > 3) {
			videoPlayComplete();
			return;
		}
		commonPosition();
		selectNum = 0;
		if(indexTimes == 1) {
			randA = new int[] { 0, 1, 2, 3};
		} else if(indexTimes == 2) {
			randA = new int[] {3, 4, 5, 6};
		} else if(indexTimes == 3) {
			randA = new int[] { 0, 1, 2, 3, 4, 5, 6};
		}
		randC = BaseCommon.getList(randA);
		currentOrder = new int[4];
		currentOrder[0] = randC[0]; 
		currentOrder[1] = randC[1]; 
		currentOrder[2] = randC[2]; 
		currentOrder[3] = randC[3]; 
		randB = BaseCommon.getList(currentOrder);
		
		ivCardName1.setImageDrawable(CommonBitmap.drawableChange(path, nameId[currentOrder[0]]));
		ivCardName2.setImageDrawable(CommonBitmap.drawableChange(path,nameId[currentOrder[1]]));
		ivCardName3.setImageDrawable(CommonBitmap.drawableChange(path,nameId[currentOrder[2]]));
		ivCardName4.setImageDrawable(CommonBitmap.drawableChange(path,nameId[currentOrder[3]]));
		ivCard1.setImageDrawable(CommonBitmap.drawableChange(path,cardId[currentOrder[0]]));
		ivCard2.setImageDrawable(CommonBitmap.drawableChange(path,cardId[currentOrder[1]]));
		ivCard3.setImageDrawable(CommonBitmap.drawableChange(path,cardId[currentOrder[2]]));
		ivCard4.setImageDrawable(CommonBitmap.drawableChange(path,cardId[currentOrder[3]]));
		ivBottomImg1.setImageDrawable(CommonBitmap.drawableChange(path,drawableId[randB[0]]));
		ivBottomImg2.setImageDrawable(CommonBitmap.drawableChange(path,drawableId[randB[1]]));
		ivBottomImg3.setImageDrawable(CommonBitmap.drawableChange(path,drawableId[randB[2]]));
		ivBottomImg4.setImageDrawable(CommonBitmap.drawableChange(path,drawableId[randB[3]]));
		showBottomView();
	}
	
	private void showBottomView() {
		ivBottomImg1.setVisibility(View.VISIBLE);
		ivBottomImg2.setVisibility(View.VISIBLE);
		ivBottomImg3.setVisibility(View.VISIBLE);
		ivBottomImg4.setVisibility(View.VISIBLE);
		ivBottomImg1.setEnabled(true);
		ivBottomImg2.setEnabled(true);
		ivBottomImg3.setEnabled(true);
		ivBottomImg4.setEnabled(true);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionEp.gogo_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}
	
	private void setCommonViewPosition(View view, int i) {
		biz.setViewPosition(view, i, FixedPosition.common_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
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
//			v.layout(mx, my, mx + v.getWidth(), my + v.getHeight());
			if(mx +mWidth>width)mx = width - mWidth;
            if(my +mHeight>height)my = height - mHeight;
            
            v.setLayoutParams(LayoutParameters.setViewPositionParams(mWidth,mHeight, mx, my));

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
			} else if(indexSelect == 3) {
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
			currentId = v.getId();
			if ((indexSelect == 0 || indexSelect == 1 || indexSelect == 2 || indexSelect == 3)
					&& Rect.intersects(viewRect, otherViewRect)) {
				setBestPosition(v, mx, my);
			} else {
				MediaCommon.playFuxiError(mContext);
				int id = v.getId();
				if (id == R.id.iv_bottom_img1) {
					setViewPosition(ivBottomImg1, 9);
				} else if (id == R.id.iv_bottom_img2) {
					setViewPosition(ivBottomImg2, 10);
				} else if (id == R.id.iv_bottom_img3) {
					setViewPosition(ivBottomImg3, 11);
				} else if (id == R.id.iv_bottom_img4) {
					setViewPosition(ivBottomImg4, 12);
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
	
	private int currentId;

	// 获得最佳停留位置
	private void setBestPosition(View v, float x, float y) {
		final int[] location = new int[2];
		if (indexSelect == 0) {
			ivCard1.getLocationOnScreen(location);
		} else if (indexSelect == 1) {
			ivCard2.getLocationOnScreen(location);
		} else if(indexSelect == 2) {
			ivCard3.getLocationOnScreen(location);
		} else if(indexSelect == 3) {
			ivCard4.getLocationOnScreen(location);
		}
		System.out.println("*****--------------------indexSelect :" + indexSelect);
		if (indexSelect == 0 || indexSelect == 1 || indexSelect == 2 || indexSelect == 3) {
			showGoodWord();
			TranslateAnimation animation = new TranslateAnimation(0.0f,
					location[0] + v.getWidth() / 2 - x, 0.0f, location[1]
							+ v.getHeight() / 2 - y);
			animation.setInterpolator(new LinearInterpolator()); // 设置动画的速率
			animation.setDuration(400);
			v.startAnimation(animation);
			animation.setFillAfter(true); // 执行终止填充效果
		} else {
			System.out.println("*****--------------------&&&&&&&&&&&&");
		}
		// v.invalidate();
	}

	private void showGoodWord() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(400);
					handler.sendMessage(handler.obtainMessage(0));
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
				System.out.println("*****&&&&&&&&&&&&& case 0&&&&&&&&&&&&&");
				showSelectedWord();
				break;
			case 1:
				resetParams();
				break;
			default:
				break;
			}
		}

	};

	private void showSelectedWord() {
		System.out.println("*****^^^^^^^^^^ showSelectedWord ^^^^^^^selectedId ^^^" + selectedId);
		if (selectedId == R.id.iv_bottom_img1) {
			statusImg(ivBottomImg1, 0);
		} else if (selectedId == R.id.iv_bottom_img2) {
			statusImg(ivBottomImg2, 1);
		} else if (selectedId == R.id.iv_bottom_img3) {
			statusImg(ivBottomImg3, 2);
		} else if (selectedId == R.id.iv_bottom_img4) {
			statusImg(ivBottomImg4, 3);
		} else {
			System.out.println("*****KKKKKKKKKK currentId KKKKKKK" + currentId);
			if (currentId == R.id.iv_bottom_img1) {
				System.out.println("1111111111111111");
				ivBottomImg1.clearFocus();
				ivBottomImg1.setImageDrawable(null);
				ivBottomImg1.setImageDrawable(CommonBitmap.drawableChange(path,drawableId[randB[0]]));
				setViewPosition(ivBottomImg1, 9);
			} else if (currentId == R.id.iv_bottom_img2) {
				System.out.println("222222222222222");
				setViewPosition(ivBottomImg2, 10);
			} else if (currentId == R.id.iv_bottom_img3) {
				System.out.println("333333333333333");
				setViewPosition(ivBottomImg3, 11);
			} else if (currentId == R.id.iv_bottom_img4) {
				System.out.println("44444444444444");
				setViewPosition(ivBottomImg4, 12);
			}
			// 保护的用途
			selectedId = 0;
		}
	}
	
	private void statusImg(ImageView ivImg, int index) {
		System.out.println("*****++++++++++++++++++++++");
		locationIndex(index);
		ivImg.clearAnimation();
		ivImg.setVisibility(View.GONE);
		ivImg.setEnabled(false);
		
		selectNum++;
		if(selectNum >= 4) {
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
					Thread.sleep(1000);
					handler.sendMessage(handler.obtainMessage(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void locationIndex(int index) {
		System.out.println("*********************************" );
		if (randB[index] == currentOrder[0]) {
			ivCard1.setImageDrawable(CommonBitmap.drawableChange(path, fillCardId[currentOrder[0]]));
			ivCard1.setEnabled(false);
		} else if (randB[index] == currentOrder[1]) {
			ivCard2.setImageDrawable(CommonBitmap.drawableChange(path, fillCardId[currentOrder[1]]));
			ivCard2.setEnabled(false);
		} else if (randB[index] == currentOrder[2]) {
			ivCard3.setImageDrawable(CommonBitmap.drawableChange(path, fillCardId[currentOrder[2]]));
			ivCard3.setEnabled(false);
		} else if (randB[index] == currentOrder[3]) {
			ivCard4.setImageDrawable(CommonBitmap.drawableChange(path, fillCardId[currentOrder[3]]));
			ivCard4.setEnabled(false);
		}
//		playSoundMusic(ConstantEbook.path_reaEbook01 + drawableId[randB[index]] + ".mp3");
	}

	// 把点击的图片置顶到顶层
	private void ivToFront(View v) {
		selectedId = v.getId();
		indexSelect = -1;
		if (v == ivBottomImg1) {
			ivBottomImg1.bringToFront();
			indexSelect = getRandB(0);
		} else if (v == ivBottomImg2) {
			ivBottomImg2.bringToFront();
			indexSelect = getRandB(1);
		} else if (v == ivBottomImg3) {
			ivBottomImg3.bringToFront();
			indexSelect = getRandB(2);
		} else if (v == ivBottomImg4) {
			ivBottomImg4.bringToFront();
			indexSelect = getRandB(3);
		}
	}

	private int getRandB(int index) {
		if(randB[index] == currentOrder[0]) {
			return 0;
		} else if(randB[index] == currentOrder[1]) {
			return 1;
		} else if(randB[index] == currentOrder[2]) {
			return 2;
		} else if(randB[index] == currentOrder[3]) {
			return 3;
		} else {
			return -1;
		}
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivBack) {
			returnHome();
		}
	}
	
	private void playBgMusic(String path) {
		try {
			if (mMediaPlayer != null) { // 一定要清空播放器资源
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
			mMediaPlayer = new MediaPlayer();
			/* 重置MediaPlayer */
			mMediaPlayer.reset();
			/* 设置要播放的文件的路径 */
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.setLooping(true);
			/* 准备播放 */
			mMediaPlayer.prepare();
			/* 开始播放 */
			mMediaPlayer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void playSoundMusic(String path) {
		try {
			if (mMediaPlayerSound != null) { // 一定要清空播放器资源
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
			mMediaPlayerSound = new MediaPlayer();
			/* 重置MediaPlayer */
			mMediaPlayerSound.reset();
			/* 设置要播放的文件的路径 */
			mMediaPlayerSound.setDataSource(path);
			mMediaPlayerSound.setLooping(false);
			/* 准备播放 */
			mMediaPlayerSound.prepare();
			/* 开始播放 */
			mMediaPlayerSound.start();
			mMediaPlayerSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener()    
            {
                public void onCompletion(MediaPlayer arg0)   
                {   
                    if(mMediaPlayerSound != null) {
                    	mMediaPlayerSound.release();
        				mMediaPlayerSound = null; 
                    }
                }   
            });
			mMediaPlayerSound.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					// TODO Auto-generated method stub
					if(mMediaPlayerSound != null) {
						mMediaPlayerSound.release();
						mMediaPlayerSound = null;
                    }
					return false;
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			returnHome();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void returnHome() {
		clearMediaPlayer();
		this.finish();
	}
	
	private void clearMediaPlayer() {
		try{
			if (mMediaPlayer != null) {
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		clearDrawable();
	}
	
	private void videoPlayComplete() {
		clearMediaPlayer();
//		startActivity(new Intent(mContext, EbookFinishActivity.class).putExtra("level", 12));
		finish();
	}
	
	private void clearDrawable() {
		rlLayout.setBackgroundDrawable(null);
		ivBack.setBackgroundResource(0);
		ivBottomBg.setImageDrawable(null);
		ivCardName1.setImageDrawable(null);
		ivCardName2.setImageDrawable(null);
		ivCardName3.setImageDrawable(null);
		ivCardName4.setImageDrawable(null);
		ivCard1.setImageDrawable(null);
		ivCard2.setImageDrawable(null);
		ivCard3.setImageDrawable(null);
		ivCard4.setImageDrawable(null);
		ivBottomImg1.setImageDrawable(null);
		ivBottomImg2.setImageDrawable(null);
		ivBottomImg3.setImageDrawable(null);
		ivBottomImg4.setImageDrawable(null);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
//		playBgMusic(EbookPath.ebookGameBgPath());
		try{
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.start();
			}
			
		}catch(Exception e){
			
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		getWindow().getDecorView().setKeepScreenOn(false);
		try{
			if (mMediaPlayer != null) {
				mMediaPlayer.pause();
			}
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.pause();
			}
		}catch(Exception e){
			
		}
		MediaCommon.pauseMediaplay();
	}

}
