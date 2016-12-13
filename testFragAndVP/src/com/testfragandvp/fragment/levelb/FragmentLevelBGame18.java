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
 *  Carlos And his Teacher
 * @author
 *
 */
public class FragmentLevelBGame18 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivRead, ivAnswer1, ivAnswer2, ivSentence;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"add", "clean", "paint", "read", "talk", "write"};
	private int[] arrayList = new int[] { 0, 1, 2, 3, 4, 5 };
	private int[] rand, randB;
	private static String path;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private boolean isClickRead = false;
	
	public FragmentLevelBGame18() {
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
		view = inflater.inflate(R.layout.level_b_game_18, container, false);
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
		playSoundMusic(BaseCommon.pathLevelBGame +  "carlos_and_his_teacher.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivRead = (ImageView) view.findViewById(R.id.iv_read);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "teacher_bg"));
		ivRead.setOnClickListener(this);
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
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
		setViewPosition(ivCard2, 2);
		setViewPosition(ivAnswer1, 3);
		setViewPosition(ivAnswer2, 4);
		setViewPosition(ivSentence, 5);
		ivRead.setImageResource(R.drawable.reread);
		refreshData();
	}
	
	private void refreshData() {
		if(clickNum >= 6) return;
		int index = BaseCommon.randData(6, rand[clickNum]);
		randB = BaseCommon.getList(new int[] {rand[clickNum], index});
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "teacher_card_" + nameId[randB[0]]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(path, "teacher_card_" + nameId[randB[1]]));
		ivAnswer1.setImageResource(0);
		ivAnswer2.setImageResource(0);
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "teacher_sentence_" + nameId[rand[clickNum]]));
		isClickRead = false;
		playQuestion();
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_18_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivRead) { // 再读一遍
			if(clickNum >= 6) return;
			isClickRead = true;
			playQuestion();
		} else if (v == ivCard1) { // 选项1
			showClick(0, ivAnswer1);
		} else if (v == ivCard2) { // 选项2
			showClick(1, ivAnswer2);
		}
	}
	
	private void playQuestion() {
		// 延迟三秒后读问题
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					if (clickNum == 0 && !isClickRead) {
						Thread.sleep(4500);
					}
					playSoundMusic(BaseCommon.pathLevelBGame + "carlos_and_his_teacher_" + nameId[rand[clickNum]] + ".mp3");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private int clickNum = 0;
	
	private void showClick(int pos, ImageView ivAnswer) {
		if(isClick || clickNum >= 6) {
			return;
		}
		isClick = true;
		if(randB[pos] == rand[clickNum]) {
			ivAnswer.setImageResource(R.drawable.iv_good);
			delayTime();
		} else {
			isClick = false;
			ivAnswer.setImageResource(R.drawable.iv_error);
			MediaCommon.playFuxiError(mContext);
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
				System.out.println("clickNum :" + clickNum);
				if(clickNum >= 6) {
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