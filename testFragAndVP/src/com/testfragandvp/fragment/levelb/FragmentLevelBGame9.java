package com.testfragandvp.fragment.levelb;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.data.FixedLevelBPosition;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 *  I Love Art Class
 * @author
 *
 */
public class FragmentLevelBGame9 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivRead, ivAnswer1, ivAnswer2, ivSentence;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"flower", "airplane", "hat", "painting"};
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private int[] rand, randB, randC;
	private int[][] random;
	private ImageView[] ivAnswer;
	private static String path;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private boolean isClickRead = false;
	
	public FragmentLevelBGame9() {
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
		view = inflater.inflate(R.layout.level_b_game_9, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		isClick = false;
		clickNum = 0;
		path = BaseCommon.pathLevelBGameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivRead = (ImageView) view.findViewById(R.id.iv_read);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "howmany_bg"));
		ivRead.setOnClickListener(this);
		ivAnswer1.setOnClickListener(this);
		ivAnswer2.setOnClickListener(this);
		commonPosition();
	}

	@Override
	public void onResume() {
		super.onResume();
		// 提示语
		delayPlaySound();
	}
	
	private void delayPlaySound() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(300);
					playSoundMusic(BaseCommon.pathLevelBGame +  "i_love_art_class.mp3");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}

	private void commonPosition() {
		rand = BaseCommon.getList(arrayList);
		setViewPosition(ivRead, 0);
		setViewPosition(ivCard1, 1);
		ivAnswer = new ImageView[2];
		ivAnswer[0] = ivAnswer1;
		ivAnswer[1] = ivAnswer2;
		setViewPosition(ivAnswer[0], 2);
		setViewPosition(ivAnswer[1], 3);
		setViewPosition(ivSentence, 4);
		ivRead.setImageResource(R.drawable.reread);
		random = BaseCommon.customRand(rand[0]);
		randB = random[0];
		randC = random[1];
		refreshData();
	}
	
	private void refreshData() {
		if(clickNum >= 4) return;
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "art_" + nameId[randB[clickNum]]));
		ivAnswer1.setImageDrawable(BaseCommon.drawableChange(path, "art_yes_del"));
		ivAnswer2.setImageDrawable(BaseCommon.drawableChange(path, "art_not_del"));
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "art_sentence_" + nameId[randC[clickNum]]));
		isClickRead = false;
		playQuestion();
	}
	
	private void playQuestion() {
		// 延迟三秒后读问题
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					if (clickNum == 0 && !isClickRead) {
						Thread.sleep(7500);
					}
					if(clickNum >= 4) return;
					System.out.println("playQuestion clickNum :" + clickNum);
					playSoundMusic(BaseCommon.pathLevelBGame + "art_class_" + nameId[randC[clickNum]] + ".mp3");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_9_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivRead) { // 再读一遍
			if(clickNum >= 4) return;
			isClickRead = true;
			playQuestion();
		} else if (v == ivAnswer1) { // 选项1
			showClick(0, ivAnswer1);
		} else if (v == ivAnswer2) { // 选项2
			showClick(1, ivAnswer2);
		}
	}

	private int clickNum = 0;
	
	private void showClick(int pos, ImageView ivAnswer) {
		if(isClick || clickNum >= 4) {
			return;
		}
		isClick = true;
		System.out.println("randB[clickNum] :" + randB[clickNum]);
		System.out.println("randC[clickNum] :" + randC[clickNum]);
		if(randB[clickNum] == randC[clickNum]) {
			if(pos == 0) {
				ivAnswer.setImageDrawable(BaseCommon.drawableChange(path, "art_yes_sel"));
				delayTime();
			} else {
				isClick = false;
				MediaCommon.playFuxiError(mContext);
			}
		} else {
			if(pos != 0) {
				ivAnswer.setImageDrawable(BaseCommon.drawableChange(path, "art_not_sel"));
				delayTime();
			} else {
				isClick = false;
				MediaCommon.playFuxiError(mContext);
			}
		}
	}
	
	private void delayTime() {
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
				isClick = false;
				clickNum++;
				if(clickNum >= 4) {
					// TODO 游戏结束
					ActivityBiz.finishBfBookGame();
				} else {
					refreshData();
				}
				break;
			case 1:
				break;
			default:
				break;
			}
		}
		
	};
	
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