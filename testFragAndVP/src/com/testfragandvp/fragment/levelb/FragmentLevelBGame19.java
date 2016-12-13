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

import com.ebodoo.raz.data.FixedLevelBPosition;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;

/**
 *  It Is Spring
 * @author
 *
 */
public class FragmentLevelBGame19 extends Fragment {

	private Context mContext;
	private View view;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6,
			ivCard7, ivCard8, ivCard9, ivCard10, ivBg1, ivBg2, ivBottomBg, ivSentence;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"deer", "leaves", "birds", "songshu", "rabbit", "fox", "flower", "worms", "plants", "sun"};
	private String[] sentenceId = {"animals", "leaves", "birds", "animals", "animals", "animals", "flowers", "worms", "plants", "sun"};
	private ImageView[] ivCard;
	private static String path;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int clickNum = 0;
	
	public FragmentLevelBGame19() {
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
		view = inflater.inflate(R.layout.level_b_game_19, container, false);
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
		// 提示语
		playSoundMusic(BaseCommon.pathLevelBGame +  "it_is_spring.mp3");
	}

	private void setView() {
		ivBg1 = (ImageView) view.findViewById(R.id.iv_bg_1);
		ivBg2 = (ImageView) view.findViewById(R.id.iv_bg_2);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivCard5 = (ImageView) view.findViewById(R.id.iv_card_5);
		ivCard6 = (ImageView) view.findViewById(R.id.iv_card_6);
		ivCard7 = (ImageView) view.findViewById(R.id.iv_card_7);
		ivCard8 = (ImageView) view.findViewById(R.id.iv_card_8);
		ivCard9 = (ImageView) view.findViewById(R.id.iv_card_9);
		ivCard10 = (ImageView) view.findViewById(R.id.iv_card_10);
		ivBottomBg = (ImageView) view.findViewById(R.id.iv_bottom_bg);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		commonPosition();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void commonPosition() {
		setViewPosition(ivBg1, 0);
		setViewPosition(ivBg2, 0);
		ivCard = new ImageView[10];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		ivCard[4] = ivCard5;
		ivCard[5] = ivCard6;
		ivCard[6] = ivCard7;
		ivCard[7] = ivCard8;
		ivCard[8] = ivCard9;
		ivCard[9] = ivCard10;
		for(int i=0; i<ivCard.length; i++) {
			setViewPosition(ivCard[i], (i + 1));
			ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, "spring_" + nameId[i]));
			final int index = i;
			ivCard[i].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					showClick(index);
				}
			});
		}
		ivBg1.setImageDrawable(BaseCommon.drawableChange(path, "spring_bg1"));
		ivBg2.setImageDrawable(BaseCommon.drawableChange(path, "spring_bg2"));
		setViewPosition(ivBottomBg, 11);
		setViewPosition(ivSentence, 12);
		ivBottomBg.setImageDrawable(BaseCommon.drawableChange(path, "spring_bottom_bg"));
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_19_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	private void showClick(int pos) {
		if(isClick) {
			return;
		}
		ivCard[pos].setEnabled(false);
		isClick = true;
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "spring_sentence_" + sentenceId[pos]));
		ivCard[pos].setImageDrawable(BaseCommon.drawableChange(path, "spring_" + nameId[pos] + "_sel"));
		playSoundMusic(BaseCommon.pathLevelBGame +  "it_is_spring_" + sentenceId[pos] + ".mp3");
		delayTime();
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
				if(clickNum >= 10) {
					// 游戏结束
					ActivityBiz.finishBfBookGame();
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