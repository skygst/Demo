package com.testfragandvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.data.FixedPositionAfrica;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.testfragandvp.ui.CommonAnimation;

/**
 *  I Can
 * @author 
 *
 */
public class FragmentGame4 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6,
			ivCard7, ivCard8, ivAnswer1, ivAnswer2, ivAnswer3, ivAnswer4,
			ivAnswer5, ivAnswer6, ivAnswer7, ivAnswer8;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private int[] arrayList = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
	private String[] picName = {"climb", "crawl", "hop", "jump", 
			"play", "ride", "run", "swing"};
	private String[] currentPicName;
	private ImageView[] imgCard;
	private ImageView[] imgAnswer;
	private int[] rand;
	private int[] randB;
	private String path;
	private int width, height;
	private int updateTimes = 1;
	private int firstIndex = -1, sencondIndex = -1;
	private Boolean[] blCardStatus;
	private boolean isCanClick = true;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	
	public FragmentGame4() {
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
		view = inflater.inflate(R.layout.af_game_4, container, false);
		init();
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		updateTimes = 1;
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		// 提示语
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "i_can_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivCard5 = (ImageView) view.findViewById(R.id.iv_card_5);
		ivCard6 = (ImageView) view.findViewById(R.id.iv_card_6);
		ivCard7 = (ImageView) view.findViewById(R.id.iv_card_7);
		ivCard8 = (ImageView) view.findViewById(R.id.iv_card_8);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		ivAnswer3 = (ImageView) view.findViewById(R.id.iv_answer_3);
		ivAnswer4 = (ImageView) view.findViewById(R.id.iv_answer_4);
		ivAnswer5 = (ImageView) view.findViewById(R.id.iv_answer_5);
		ivAnswer6 = (ImageView) view.findViewById(R.id.iv_answer_6);
		ivAnswer7 = (ImageView) view.findViewById(R.id.iv_answer_7);
		ivAnswer8 = (ImageView) view.findViewById(R.id.iv_answer_8);
		rlLayout.setBackground(BaseCommon.drawableChange(path,
				"bird_goes_home_bg"));
		showCard();
	}

	private void showCard() {
		imgCard = new ImageView[8];
		imgAnswer = new ImageView[8];
		blCardStatus = new Boolean[8];
		imgCard[0] = ivCard1;
		imgCard[1] = ivCard2;
		imgCard[2] = ivCard3;
		imgCard[3] = ivCard4;
		imgCard[4] = ivCard5;
		imgCard[5] = ivCard6;
		imgCard[6] = ivCard7;
		imgCard[7] = ivCard8;
		imgAnswer[0] = ivAnswer1;
		imgAnswer[1] = ivAnswer2;
		imgAnswer[2] = ivAnswer3;
		imgAnswer[3] = ivAnswer4;
		imgAnswer[4] = ivAnswer5;
		imgAnswer[5] = ivAnswer6;
		imgAnswer[6] = ivAnswer7;
		imgAnswer[7] = ivAnswer8;
		for (int i = 0; i < imgCard.length; i++) {
			imgCard[i].setImageDrawable(BaseCommon.drawableChange(path,
					"i_can_card_bg"));
			imgCard[i].setOnClickListener(this);
			setViewPosition(imgCard[i], i);
			imgAnswer[i].setImageDrawable(BaseCommon.drawableChange(path,
					"answer_wright"));
			imgAnswer[i].setVisibility(View.GONE);
			setViewPosition(imgAnswer[i], 8 + i);
		}
		for(int i=0; i<8; i++) {
			blCardStatus[i] = false;
		}
		showPicStatus();
	}
	
	private void showPicStatus() {
		currentPicName = new String[8];
		int index = 0;
		if(updateTimes == 2) {
			rand = BaseCommon.getList(new int[] {  4, 5, 6, 7 });
		} else {
			rand = BaseCommon.getList(new int[] { 0, 1, 2, 3});
		}
		String[] matchPic = { "card_" + picName[rand[index]],
				"card_" + picName[rand[index + 1]],
				"card_" + picName[rand[index + 2]],
				"card_" + picName[rand[index + 3]],
				"card_" + picName[rand[index]] + "2",
				"card_" + picName[rand[index + 1]] + "2",
				"card_" + picName[rand[index + 2]] + "2",
				"card_" + picName[rand[index + 3]] + "2" };
		randB = BaseCommon.getList(arrayList);
		currentPicName[0] = matchPic[randB[0]];
		currentPicName[1] = matchPic[randB[1]];
		currentPicName[2] = matchPic[randB[2]];
		currentPicName[3] = matchPic[randB[3]];
		currentPicName[4] = matchPic[randB[4]];
		currentPicName[5] = matchPic[randB[5]];
		currentPicName[6] = matchPic[randB[6]];
		currentPicName[7] = matchPic[randB[7]];
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.i_can_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivCard1) {
			clickCard(0);
		} else if (v == ivCard2) {
			clickCard(1);
		} else if (v == ivCard3) {
			clickCard(2);
		} else if (v == ivCard4) {
			clickCard(3);
		} else if (v == ivCard5) {
			clickCard(4);
		} else if (v == ivCard6) {
			clickCard(5);
		} else if (v == ivCard7) {
			clickCard(6);
		} else if (v == ivCard8) {
			clickCard(7);
		}
	}

	private void clickCard(int index) {
		if(!isCanClick) {
			return;
		}
		isCanClick = false;
		System.out.println("------------ clickCard ---------------");
		if(!blCardStatus[index]) {
			System.out.println("------------ clickCard ----111111111111-----------");
			blCardStatus[index] = true;
			if(firstIndex == -1) {
				firstIndex = index;
			} else if(sencondIndex == -1) {
				sencondIndex = index;
//				isCanClick = false;
			} else {
				/*sencondIndex = -1;
				firstIndex = randB[index];*/
			}
			System.out.println("------------------------");
			if(firstIndex == -1 || sencondIndex == -1) {
				delayTime(500, 3);
			}
			CommonAnimation.flopAnimation(getActivity(), imgCard[index]);
			fanPai(imgCard[index], currentPicName[index]);
		} else {
			System.out.println("+++++++++++++++++++=====");
			delayTime(500, 3);
		}
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	private void fanPai(final ImageView imgView, final String picName) {
		Animation animation = AnimationUtils.loadAnimation(mContext,
				R.anim.back_scale);
		animation.setAnimationListener(new Animation.AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				imgView.setImageDrawable(BaseCommon.drawableChange(path, picName));
				// 通过AnimationUtils得到动画配置文件(/res/anim/front_scale.xml),然后在把动画交给ImageView
				imgView.startAnimation(AnimationUtils.loadAnimation(mContext,
						R.anim.front_scale));
				if(firstIndex != -1 && sencondIndex != -1) {
					delayPost();
				}
			}
		});
		imgView.startAnimation(animation);
	}
	
	private void delayPost() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
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
				int value = Math.abs(randB[firstIndex]-randB[sencondIndex]);
				if(value == 4) {
					int index = -1;
					if(updateTimes == 1) {
						if(randB[firstIndex] < 4) {
							index = randB[firstIndex];
						} else {
							index = randB[sencondIndex];
						}
					} else {
						if(randB[sencondIndex] > 4) {
							index = randB[firstIndex];
						} else {
							index = randB[sencondIndex];
						}
					}
					if(index >= 4) {
						index = index - 4;
					}
					playSoundMusic(BaseCommon.path_game + "ican_" + picName[rand[index]] + ".mp3");
					imgAnswer[firstIndex].setVisibility(View.VISIBLE);
					imgAnswer[sencondIndex].setVisibility(View.VISIBLE);
				} else {
					blCardStatus[firstIndex] = false;
					blCardStatus[sencondIndex] = false;
					fanPai(imgCard[firstIndex], "i_can_card_bg");
					fanPai(imgCard[sencondIndex], "i_can_card_bg");
				}
				firstIndex = -1;
				sencondIndex = -1;
				boolean blStatus = true;
				for(int i=0; i<blCardStatus.length; i++) {
					if(!blCardStatus[i]) {
						blStatus = false;
					}
				}
				isCanClick = true;
				if(blStatus && updateTimes == 1) {
					updateTimes = 2;
					// 下一组
					delayTime(1500, 1);
				} else if(blStatus && updateTimes == 2) {
					delayTime(2000, 2);
				}
				break;
			case 1:
				showCard();
				break;
			case 2:
				ActivityBiz.finishAfBookGame();
				break;
			case 3:
				isCanClick = true;
				break;
			default:
				break;
			}
		}
		
	};
	
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