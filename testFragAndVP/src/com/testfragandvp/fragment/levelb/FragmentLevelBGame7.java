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
 *  How Many
 * @author
 *
 */
public class FragmentLevelBGame7 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivRead, ivChoose1, ivChoose2, ivChoose3, ivAnswer1, ivAnswer2, ivAnswer3;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
	private String[] cardId = {"many_card_2", "many_card_3", "many_card_4", 
			"many_card_5", "many_card_6", "many_card_7", "many_card_8", "many_card_9"};
	private int[] arrayList = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
	private int[] rand, randB;
	private String[] chooseItem;
	private ImageView[] ivChoose;
	private ImageView[] ivAnswer;
	private static String path;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private boolean isClickRead = false;
	
	public FragmentLevelBGame7() {
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
		view = inflater.inflate(R.layout.level_b_game_7, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		isClick = false;
		clickNum = 0;
		isClickRead = false;
		path = BaseCommon.pathLevelBGameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		// 提示语
		playSoundMusic(BaseCommon.pathLevelBGame +  "how_many.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivRead = (ImageView) view.findViewById(R.id.iv_read);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivChoose1 = (ImageView) view.findViewById(R.id.iv_choose_1);
		ivChoose2 = (ImageView) view.findViewById(R.id.iv_choose_2);
		ivChoose3 = (ImageView) view.findViewById(R.id.iv_choose_3);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		ivAnswer3 = (ImageView) view.findViewById(R.id.iv_answer_3);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "howmany_bg"));
		ivRead.setOnClickListener(this);
		ivChoose1.setOnClickListener(this);
		ivChoose2.setOnClickListener(this);
		ivChoose3.setOnClickListener(this);
		commonPosition();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void commonPosition() {
		rand = BaseCommon.getList(arrayList);
		setViewPosition(ivRead, 0);
		setViewPosition(ivCard1, 1);
		ivChoose = new ImageView[3];
		ivChoose[0] = ivChoose1;
		ivChoose[1] = ivChoose2;
		ivChoose[2] = ivChoose3;
		for(int i=0; i<ivChoose.length; i++) {
			setViewPosition(ivChoose[i], (2 + i));
		}
		ivAnswer = new ImageView[3];
		ivAnswer[0] = ivAnswer1;
		ivAnswer[1] = ivAnswer2;
		ivAnswer[2] = ivAnswer3;
		for(int i=0; i<ivAnswer.length; i++) {
			setViewPosition(ivAnswer[i], (5 + i));
		}
		refreshData();
		ivRead.setImageResource(R.drawable.reread);
	}
	
	private void refreshData() {
		randB = BaseCommon.getList(new int[]{0, 1, 2});
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path, cardId[rand[clickNum]]));
		int index0 = rand[clickNum] + 1;
		int index1 = BaseCommon.randData(8, index0);
		int index2 = BaseCommon.randData(8, index0, index1);
		chooseItem = new String[3];
		chooseItem[0] = nameId[index0];
		chooseItem[1] = nameId[index1];
		chooseItem[2] = nameId[index2];
		ivAnswer1.setImageResource(0);
		ivAnswer2.setImageResource(0);
		ivAnswer3.setImageResource(0);
		for(int i=0; i<ivAnswer.length; i++) {
			ivAnswer[i].setImageResource(0);
		}
		for(int i=0; i<ivChoose.length; i++) {
			ivChoose[i].setImageDrawable(BaseCommon.drawableChange(path, "choose_" + chooseItem[randB[i]] + "_del"));
		}
		isClickRead = false;
		playQuestion();
	}
	
	private void playQuestion() {
		// 延迟三秒后读问题
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							if(clickNum == 0 && !isClickRead) {
								Thread.sleep(3500);
							}
							playSoundMusic(BaseCommon.pathLevelBGame +  "how_many_" + nameId[rand[clickNum] + 1] + ".mp3");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_7_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivRead) { // 再读一遍
			isClickRead = true;
			playQuestion();
		} else if (v == ivChoose1) { // 选项1
			showClick(0, ivAnswer1);
		} else if (v == ivChoose2) { // 选项2
			showClick(1, ivAnswer2);
		} else if (v == ivChoose3) { // 选项3
			showClick(2, ivAnswer3);
		}
	}

	private int clickNum = 0;
	
	private void showClick(int pos, ImageView ivAnswer) {
		if(isClick) {
			return;
		}
		isClick = true;
		if(randB[pos] == 0) {
			ivAnswer.setImageResource(R.drawable.iv_good_small);
			playSoundMusic(BaseCommon.pathLevelBGame +  "answer_" + chooseItem[0] + ".mp3");
			clickNum++;
			delayTime();
		} else {
			isClick = false;
			ivAnswer.setImageResource(R.drawable.iv_error_small);
			MediaCommon.playFuxiError(mContext);
		}
		ivChoose[pos].setImageDrawable(BaseCommon.drawableChange(path, "choose_" + chooseItem[randB[pos]] + "_sel"));
	}
	
	private void delayTime() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1500);
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
				if(clickNum >= 8) {
					for(int i=0; i<ivChoose.length; i++) {
						ivChoose[i].setEnabled(false);
					}
					// 游戏结束
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