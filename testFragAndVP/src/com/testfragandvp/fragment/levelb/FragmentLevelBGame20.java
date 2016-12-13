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
import android.widget.TextView;

import com.ebodoo.raz.data.FixedLevelBPosition;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 *  The Big Game
 * @author
 *
 */
public class FragmentLevelBGame20 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout, rlSentence;
	private TextView tvSentence;
	private ImageView ivCard1, ivChoose1, ivChoose2, ivAnswer1, ivAnswer2;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"dress_up", "clean", "paint", "cheer", "eat", "play", "watch"};
	private String[] sentenceId = {
			"We dress up on game day.", 
			"We clean on game day.", 
			"We paint faces on game day.",
			"We cheer on game day.",
			"We eat snacks on game day.",
			"We play football on game day!",
			"We watch football on game day."};
	private int[] randB;
	private static String path;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private boolean isFinish = false;
	
	public FragmentLevelBGame20() {
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
		view = inflater.inflate(R.layout.level_b_game_20, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		isClick = false;
		isFinish = false;
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
		playSoundMusic(BaseCommon.pathLevelBGame +  "the_big_game.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		rlSentence = (RelativeLayout) view.findViewById(R.id.rl_sentence_bg);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivChoose1 = (ImageView) view.findViewById(R.id.iv_choose_1);
		ivChoose2 = (ImageView) view.findViewById(R.id.iv_choose_2);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		tvSentence = (TextView) view.findViewById(R.id.tv_sentence);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "game_bg"));
		ivChoose1.setOnClickListener(this);
		ivChoose2.setOnClickListener(this);
		commonPosition();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void commonPosition() {
		setViewPosition(rlSentence, 0);
		setViewPosition(ivCard1, 1);
		setViewPosition(ivChoose1, 2);
		setViewPosition(ivChoose2, 3);
		setViewPosition(ivAnswer1, 4);
		setViewPosition(ivAnswer2, 5);
		refreshData();
	}
	
	private void refreshData() {
		if(clickNum >= 7) return;
		int index = BaseCommon.randData(7, clickNum);
		randB = BaseCommon.getList(new int[] {clickNum, index});
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "game_card_" + nameId[clickNum]));
		ivChoose1.setImageDrawable(BaseCommon.drawableChange(path, "game_" + nameId[randB[0]]));
		ivChoose2.setImageDrawable(BaseCommon.drawableChange(path, "game_" + nameId[randB[1]]));
		ivAnswer1.setImageResource(0);
		ivAnswer2.setImageResource(0);
		tvSentence.setText("");
	}
	
	private void playQuestion() {
		// 延迟三秒后读问题
		new Thread(new Runnable() {

			@Override
			public void run() {
				playSoundMusic(BaseCommon.pathLevelBGame + "the_big_game_" + nameId[clickNum] + ".mp3");
			}
		}).start();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_20_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivChoose1) { // 选项1
			showClick(0, ivAnswer1);
		} else if (v == ivChoose2) { // 选项2
			showClick(1, ivAnswer2);
		}
	}

	private int clickNum = 0;
	
	private void showClick(int pos, ImageView ivAnswer) {
		if(isClick || clickNum >= 7) {
			return;
		}
		isClick = true;
		if(randB[pos] == clickNum) {
			ivAnswer.setImageResource(R.drawable.iv_good_small);
			playQuestion();
			tvSentence.setText(sentenceId[clickNum]);
			delayTime();
		} else {
			isClick = false;
			ivAnswer.setImageResource(R.drawable.iv_error_small);
			MediaCommon.playFuxiError(mContext);
		}
	}
	
	private void delayTime() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(4200);
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
				if(clickNum >= 7) {
					// 游戏结束
					ivChoose1.setEnabled(false);
					ivChoose2.setEnabled(false);
					delayFinish();
				} else {
					refreshData();
				}
				break;
			case 1:
				ActivityBiz.finishBfBookGame();
				break;
			default:
				break;
			}
		}
		
	};
	
	private void delayFinish() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1500);
					handler.sendMessage(handler.obtainMessage(1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void playSoundMusic(String path) {
		if(isFinish) return;
		mMediaPlayerSound = ActivityBiz.playSoundMusic(mMediaPlayerSound, path);
	}
	
	private void clearMediaPlayer() {
		try {
			if (mMediaPlayerSound != null) {
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
				isFinish = true;
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