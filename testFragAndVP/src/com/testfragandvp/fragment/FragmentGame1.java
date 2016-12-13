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
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.data.FixedPositionAfrica;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 *  Bird Goes Home
 * @author
 *
 */
public class FragmentGame1 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivAnswer1, ivAnswer2,
			ivAnswer3, ivAnswer4;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardId1 = { "bird_goes_01", "bird_goes_02",
			"bird_goes_03", "bird_goes_04" };
	private String[] cardId2 = { "bird_goes_05", "bird_goes_06",
			"bird_goes_07", "bird_goes_08" };
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private int[] rand;
	private static String path;
	private ImageView[] imgAnswers;
	private int index = 1;
	private int countNum = 0;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	
	public FragmentGame1() {
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
		view = inflater.inflate(R.layout.af_game_1, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		index = 1;
		countNum = 0;
		isClick = false;
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		// 提示语
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "bird_goes_home_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		ivAnswer3 = (ImageView) view.findViewById(R.id.iv_answer_3);
		ivAnswer4 = (ImageView) view.findViewById(R.id.iv_answer_4);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "bird_goes_home_bg"));
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		ivCard3.setOnClickListener(this);
		ivCard4.setOnClickListener(this);
		commonPosition();
		resetOrder();
		setImages();
	}

	private void setImages() {
		imgAnswers = new ImageView[4];
		imgAnswers[0] = ivAnswer1;
		imgAnswers[1] = ivAnswer2;
		imgAnswers[2] = ivAnswer3;
		imgAnswers[3] = ivAnswer4;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void resetOrder() {
		rand = BaseCommon.getList(arrayList);
		ivAnswer1.setVisibility(View.GONE);
		ivAnswer2.setVisibility(View.GONE);
		ivAnswer3.setVisibility(View.GONE);
		ivAnswer4.setVisibility(View.GONE);
		if (index == 1) {
			showCard(cardId1);
		} else {
			showCard(cardId2);
		}
	}

	private void showCard(String[] cardId) {
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path,
				cardId[rand[0]]));
		ivCard2.setImageDrawable(BaseCommon.drawableChange(path,
				cardId[rand[1]]));
		ivCard3.setImageDrawable(BaseCommon.drawableChange(path,
				cardId[rand[2]]));
		ivCard4.setImageDrawable(BaseCommon.drawableChange(path,
				cardId[rand[3]]));
	}

	private void commonPosition() {
		setViewPosition(ivCard1, 0);
		setViewPosition(ivCard2, 1);
		setViewPosition(ivCard3, 2);
		setViewPosition(ivCard4, 3);
		setViewPosition(ivAnswer1, 4);
		setViewPosition(ivAnswer2, 5);
		setViewPosition(ivAnswer3, 6);
		setViewPosition(ivAnswer4, 7);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.bird_goes_home_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivCard1) {
			showAnswer(0);
		} else if (v == ivCard2) {
			showAnswer(1);
		} else if (v == ivCard3) {
			showAnswer(2);
		} else if (v == ivCard4) {
			showAnswer(3);
		}
	}

	private void showAnswer(int pos) {
		if(isClick) {
			return;
		}
		imgAnswers[pos].setVisibility(View.VISIBLE);
		if ((index == 1 && rand[pos] == 3) || (index == 2 && rand[pos] == 1)) { // 答对啦
			imgAnswers[pos].setImageDrawable(BaseCommon.drawableChange(path,
					"answer_wright"));
			// TODO 延迟执行
			delayTime();
		} else { // 答错啦
			MediaCommon.playFuxiError(mContext);
			imgAnswers[pos].setImageDrawable(BaseCommon.drawableChange(path,
					"answer_wrong"));
			isClick = false;
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
				countNum++;
				if(countNum == 1) {
					isClick = false;
					index = 2;
					resetOrder();
				} else {
					isClick = true;
					ActivityBiz.finishAfBookGame();
				}
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