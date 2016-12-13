package com.gst.mydemo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gst.mydemo.R;
import com.gst.mydemo.custom.widget.CircleProgress;

/**
 * 动画实现界面
 * 
 * @author gst
 * 
 */
public class AnimationActivity extends BaseActivity implements OnClickListener {

	private ImageView ivPic1, ivPic2, ivPic3, ivMove;
	private Button btnMore;
	private CircleProgress pb;
	private Context mContext;
	private int pbCount = 68;
	private int currentNum = 0;

	private EditText edit;
	private Button btn;
	private ImageView image;
	private boolean isStart = true;
	private  int x, y;
	private Animation shakeAnim;
	private Animation showAction, hideAction;
	LinearLayout linearLayout;
	Button button;
	boolean menuShowed = false;
	protected Animation animation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation);

		mContext = AnimationActivity.this;
		setView();
		showAnimation();
	}

	private void showAnimation() {
		linearLayout = (LinearLayout) findViewById(R.id.menu);
		button = (Button) findViewById(R.id.button);
		// 这里是TranslateAnimation动画
//		showAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//				0.0f, Animation.RELATIVE_TO_SELF, -1.0f);
		// 这里是ScaleAnimation动画 //
		showAction = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				1.0f);
		showAction.setDuration(500);
		// 这里是TranslateAnimation动画
//		hideAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//				1.0f, Animation.RELATIVE_TO_SELF, 0.0f);

		// 这里是ScaleAnimation动画
		hideAction = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				1.0f);
		hideAction.setDuration(500);
		menuShowed = false;
		linearLayout.setVisibility(View.GONE);

		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (menuShowed) {
					menuShowed = false;
					linearLayout.startAnimation(hideAction);
					linearLayout.setVisibility(View.GONE);
				} else {
					menuShowed = true;
					linearLayout.startAnimation(showAction);
					linearLayout.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	private void setView() {
		ivPic1 = (ImageView) findViewById(R.id.iv_pic1);
		ivPic2 = (ImageView) findViewById(R.id.iv_pic2);
		ivPic3 = (ImageView) findViewById(R.id.iv_pic3);
		ivMove = (ImageView) findViewById(R.id.iv_move);
		btnMore = (Button) findViewById(R.id.btn_more);
		pb = (CircleProgress) findViewById(R.id.progress);
		ivrotate();
		showProgressBar();

		ivPic1.setOnClickListener(this);
		ivPic2.setOnClickListener(this);
		btnMore.setOnClickListener(this);
//		ivPic1.setImageResource(R.drawable.ic_launcher);
//		slideview(ivPic1, 658, 448, 684, 782, R.anim.shake_y);
//		setMoveAnimation1();
//		setMoveAnimation2();
		
		/*int[] location = new  int[2] ;
		ivPic1.getLocationInWindow(location); //获取在当前窗口内的绝对坐标
		ivPic1.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
	    System.out.println("view--->x坐标:"+location [0]+"view--->y坐标:"+location [1]);*/
	    
		edit = (EditText) findViewById(R.id.edit);
		image = (ImageView) findViewById(R.id.main_iv);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Animation animation = AnimationUtils.loadAnimation(
						mContext, R.anim.myanim);
				edit.startAnimation(animation);
				image.startAnimation(animation);
			}
		});
		setShakeDownAnimation(ivPic1);// 钱袋上下抖动动画
		setShakeDownAnimation(ivPic2);// 钱袋上下抖动动画
		ivMove.setOnClickListener(this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("======= onResume() ========");
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		System.out.println("======= onWindowFocusChanged() ========");
		int[] location = new int[2];  
		 ivPic1.getLocationOnScreen(location);  
        x = location[0];  
        y = location[1];  
        System.out.println("x:" + x + "y:" + y);
	}
	
	public void slideview(final ImageView view, final float xFrom, final float xTo, float yFrom, float yTo, int shake) {
	    TranslateAnimation animation = new TranslateAnimation(xFrom, xTo, yFrom, yTo);
//	    TranslateAnimation animation = (TranslateAnimation) AnimationUtils.loadAnimation(mContext, R.anim.shake_y);
//	    animation.setInterpolator(new OvershootInterpolator());
	    animation.setDuration(3000);
//	    animation.setStartOffset(1000);
	    animation.setFillAfter(true);
	    animation.setAnimationListener(new AnimationListener() {
	        @Override
	        public void onAnimationStart(Animation animation) {
	        }
	        
	        @Override
	        public void onAnimationRepeat(Animation animation) {
	        }
	        
	        @Override
	        public void onAnimationEnd(Animation animation) {
	            /*int left = view.getLeft()+(int)(p2-p1);
	            int top = view.getTop();
	            int width = view.getWidth();
	            int height = view.getHeight();
	            view.clearAnimation();
	            view.layout(left, top, left+width, top+height);*/
//	        	view.clearAnimation();
	        	isStart = !isStart;
	        	if(isStart) {
//	        		slideview(ivPic1, 0, 300, R.anim.shake_y);
	        		slideview(ivPic1, 658, 448, 684, 782, R.anim.shake_y);
	        	} else {
//	        		slideview(ivPic1, 300, 0, R.anim.shake_x);
	        		slideview(ivPic1, 448, 658, 782, 684, R.anim.shake_x);
	        	}
	        }
	    });
	    view.startAnimation(animation);
	}
	
	// 移动
	private void setMoveAnimation1() {
		Animation animationMove = AnimationUtils.loadAnimation(this, R.anim.my_translate_action);
		ivPic1.startAnimation(animationMove);
	}
	
	// 抖动
	private void setMoveAnimation2() {
		final Animation shakeAnim = AnimationUtils.loadAnimation(mContext, R.anim.shake_y);
		ivPic1.startAnimation(shakeAnim); 
		shakeAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				ivPic1.startAnimation(shakeAnim);
			}
		});
	}
	
	//CycleTimes动画重复的次数
    public Animation shakeAnimation(int CycleTimes) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 10);
        translateAnimation.setInterpolator(new CycleInterpolator(CycleTimes));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }

	@Override
	public void onClick(View v) {
		if (v == ivPic1) {
//			new ImageUtils().rotateAnim("10", ivPic1);
//			ivPic1.setFocusable(false);
			ivPic1.clearAnimation();
			ViewGroup parent = (ViewGroup) ivPic1.getParent();  
            if (parent != null)  
                parent.removeView(ivPic1); 
			ivPic1.setVisibility(View.GONE);
			
		} else if (v == ivPic2) {
//			new ImageUtils().flopAnimation(mContext, ivPic2);
//			ivPic2.setFocusable(false);
			ivPic2.clearAnimation();
			ViewGroup parent = (ViewGroup) ivPic2.getParent();  
            if (parent != null)  
                parent.removeView(ivPic2); 
			ivPic2.setVisibility(View.GONE);
		} else if (v == btnMore) {
			startActivity(new Intent(mContext, AllAnimationActivity.class));
		} else if(v == ivMove) { // 移动
//			int moveId = R.anim.translate_rotate;
//			animation = AnimationUtils.loadAnimation(this, moveId);

//			android:fromDegrees="0"
//			android:toDegrees="+360"
//			android:pivotX="50%"
//			android:pivotY="50%"
//			android:startOffset="800"
//			android:duration="5000"

//			Animation roteAnimation = new RotateAnimation(0.0f, 360.0f, 5.5f, 5.5f);
//			roteAnimation.setDuration(2000); // 设置动画持续时间
//			roteAnimation.setStartOffset(400); // 设置启动时间
//			roteAnimation.setFillAfter(true); // ture表示动画结束后停留在动画的最后位置，false表示动画结束后回到初始位置，默认为false。
//			roteAnimation.setZAdjustment(Animation.ZORDER_TOP);
//			roteAnimation.setRepeatMode(Animation.REVERSE);
//			AnimationDrawable animationDrawable = (AnimationDrawable) ivMove.getDrawable();
//			animationDrawable.start();
//			ivMove.startAnimation(roteAnimation);

			count = 0;
//			roundMove(0.0f, 400.0f, 400.0f, 0.0f);
			int moveId = R.anim.alpha_translate_rotate;
			animation = AnimationUtils.loadAnimation(this, moveId);
			animation.setRepeatCount(Integer.MAX_VALUE);
			animation.setRepeatMode(Animation.REVERSE);
			ivMove.startAnimation(animation);
//			animationDrawable = (AnimationDrawable) ivMove.getDrawable();
//			animationDrawable.start();
//			moveInfo(0.1f, 200.0f,0.1f,200.0f);
		}
		 int[] location = new int[2];  
		 ivPic1.getLocationOnScreen(location);  
         int x = location[0];  
         int y = location[1];  
         System.out.println("x:" + x + "y:" + y);
         System.out.println("图片各个角Left：" + ivPic1.getLeft() + "Right：" + ivPic1.getRight() + "Top：" + ivPic1.getTop() + "Bottom：" + ivPic1.getBottom());
	}

	// 圆形移动(中心点移动)
	private void roundMove(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
		RotateAnimation roteAnimation = new RotateAnimation(0.0f, 360.0f, 0.5f, 0.5f);
		Animation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
//		translateAnimation.setDuration(3000);

		AnimationSet animation = new AnimationSet(true);
		animation.addAnimation(roteAnimation);
		animation.addAnimation(translateAnimation);
		animation.setRepeatCount(100);
		animation.setDuration(3000);
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				count++;
//				if (count == 1) {
//					roundMove(400.0f, 0.0f, 200.0f, 200.0f);
////					ivMove.clearAnimation();
//				} else if (count == 2) {
//					count = 0;
//					roundMove(0.0f, 400.0f, 200.0f, 200.0f);
//				} else if (count == 3) {
////					count = 0;
////					roundMove(100.0f, 400.0f, 150.0f, 150.0f);
//				}

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
		});
//		animation.setFillAfter(true);
		animation.setStartOffset(100);
		ivMove.startAnimation(animation);
	}

	private int count = 0;
	AnimationDrawable animationDrawable;
	// 移动
	private void moveInfo(float fromX, float toX, float fromY, float toY) {

		Animation translateAnimation = new TranslateAnimation(fromX, toX, fromY, toY);
		translateAnimation.setDuration(4000);
		translateAnimation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				count++;
				if (count == 1) {
					moveInfo(200.0f, 400.0f, 200.0f, 0.1f);
				} else if (count == 2) {
					moveInfo(400.0f, 600.0f, 0.1f, 200.0f);
				} else if (count == 3) {
					moveInfo(600.0f, 800.0f, 200.0f, 0.1f);
				} else if (count == 4) {
					moveInfo(800.0f, 0.1f, 0.1f, 0.1f);
				} else {
//					animationDrawable.stop();
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}
		});
		ivMove.startAnimation(translateAnimation);
	}

	private void ivrotate() {
		Animation operatingAnim = AnimationUtils.loadAnimation(this,
				R.anim.iv_rotate);
		LinearInterpolator lin = new LinearInterpolator();
		operatingAnim.setInterpolator(lin);
		ivPic3.startAnimation(operatingAnim);
	}

	private void showProgressBar() {
		if (currentNum <= pbCount) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(100);
						handler.sendMessage(handler
								.obtainMessage(0, currentNum));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				int count = (Integer) msg.obj;
				// System.out.println("handler count :"+count);
				pb.setMainProgress(count);
				currentNum++;
				showProgressBar();
				break;

			default:
				break;
			}
		}

	};
	
	public void setShakeDownAnimation(final View view) {
		shakeAnim = AnimationUtils.loadAnimation(mContext,
				R.anim.shake_y);
		view.startAnimation(shakeAnim);
		shakeAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				view.startAnimation(shakeAnim);
			}
		});
	}

}
