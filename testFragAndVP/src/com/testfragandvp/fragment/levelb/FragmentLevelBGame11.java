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
 *  Go Animals Go
 *  
 * @author
 *
 */
public class FragmentLevelBGame11 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivRead, ivChoose1, ivChoose2, ivAnswer1, ivAnswer2, ivSentence;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"cow", "bird", "cat", "dog", "duck", "goat", "pig"};
	private int[] answerId = {0, 1, 1, 1, 0, 0, 1};
	private int[] arrayList = new int[] { 0, 1, 2, 3, 4, 5, 6 };
	private int[] rand;
	private static String path;
	private boolean isClick = false;
	private boolean isClickRead = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	
	public FragmentLevelBGame11() {
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
		view = inflater.inflate(R.layout.level_b_game_11, container, false);
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
		playSoundMusic(BaseCommon.pathLevelBGame +  "go_animals_go.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivRead = (ImageView) view.findViewById(R.id.iv_read);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivChoose1 = (ImageView) view.findViewById(R.id.iv_choose_1);
		ivChoose2 = (ImageView) view.findViewById(R.id.iv_choose_2);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "animals_bg"));
		ivRead.setOnClickListener(this);
		ivChoose1.setOnClickListener(this);
		ivChoose2.setOnClickListener(this);
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
		setViewPosition(ivChoose1, 2);
		setViewPosition(ivChoose2, 3);
		setViewPosition(ivAnswer1, 4);
		setViewPosition(ivAnswer2, 5);
		setViewPosition(ivSentence, 6);
		ivRead.setImageResource(R.drawable.reread);
		refreshData();
	}
	
	private void refreshData() {
		System.out.println("refreshData clickNum :" + clickNum);
		if(clickNum >= 7) return;
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "animals_" + nameId[rand[clickNum]]));
		ivChoose1.setImageDrawable(BaseCommon.drawableChange(path, "animals_in"));
		ivChoose2.setImageDrawable(BaseCommon.drawableChange(path, "animals_on"));
		ivAnswer1.setImageResource(0);
		ivAnswer2.setImageResource(0);
		ivSentence.setImageDrawable(null);
		isClickRead = false;
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_11_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivRead) { // 再读一遍
			if(clickNum >= 7) return;
			isClickRead = true;
			playQuestion();
		} else if (v == ivChoose1) { // 选项1
			showClick(0, ivAnswer1);
		} else if (v == ivChoose2) { // 选项2
			showClick(1, ivAnswer2);
		}
	}
	
	private void playQuestion() {
		// 延迟三秒后读问题
		new Thread(new Runnable() {

			@Override
			public void run() {
				playSoundMusic(BaseCommon.pathLevelBGame + "animals_" + nameId[rand[clickNum]] + ".mp3");
			}
		}).start();
	}

	private int clickNum = 0;
	
	private void showClick(int pos, ImageView ivAnswer) {
		if(isClick || clickNum >= 7) {
			return;
		}
		isClick = true;
		if(answerId[rand[clickNum]] == pos) {
			ivAnswer.setImageResource(R.drawable.iv_good_small);
			ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "animals_sentence_" + nameId[rand[clickNum]]));
			playQuestion();
			delayTime();
		} else {
			ivAnswer.setImageResource(R.drawable.iv_error_small);
			isClick = false;
			MediaCommon.playFuxiError(mContext);
		}
	}
	
	private void delayTime() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
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
				clickNum++;
				isClick = false;
				if(clickNum >= 7) {
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