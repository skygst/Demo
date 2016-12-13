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

/**
 *   Playful Puppy
 * @author
 *
 */
public class FragmentLevelBGame14 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout, rlSentence;
	private ImageView ivCard1, ivChoose1, ivChoose2, ivChoose3, ivChoose4;
	private TextView tvSentence;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"mouse", "frog", "tortoise", "ball"};
	private String[] sentenceId = {
			"Puppy sees a little mouse.",
			"Puppy sees a little frog.",
			"Puppy sees a little turtle.",
			"Puppy sees a little ball."
	};
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private int[] rand;
	private ImageView[] ivChoose;
	private static String path;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	
	public FragmentLevelBGame14() {
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
		view = inflater.inflate(R.layout.level_b_game_14, container, false);
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
		playSoundMusic(BaseCommon.pathLevelBGame +  "playful_puppy.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		rlSentence = (RelativeLayout) view.findViewById(R.id.rl_sentence);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivChoose1 = (ImageView) view.findViewById(R.id.iv_choose_1);
		ivChoose2 = (ImageView) view.findViewById(R.id.iv_choose_2);
		ivChoose3 = (ImageView) view.findViewById(R.id.iv_choose_3);
		ivChoose4 = (ImageView) view.findViewById(R.id.iv_choose_4);
		tvSentence = (TextView) view.findViewById(R.id.tv_sentence);
		
		rlLayout.setBackground(BaseCommon.drawableChange(path, "puppy_bg"));
		ivChoose1.setOnClickListener(this);
		ivChoose2.setOnClickListener(this);
		ivChoose3.setOnClickListener(this);
		ivChoose4.setOnClickListener(this);
		commonPosition();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void commonPosition() {
		rand = BaseCommon.getList(arrayList);
		setViewPosition(ivCard1, 0);
		setViewPosition(rlSentence, 5);
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "puppy_dog"));
		rlSentence.setBackgroundDrawable(BaseCommon.drawableChange(path, "puppy_sentence_bg"));
		ivChoose = new ImageView[4];
		ivChoose[0] = ivChoose1;
		ivChoose[1] = ivChoose2;
		ivChoose[2] = ivChoose3;
		ivChoose[3] = ivChoose4;
		for(int i=0; i<ivChoose.length; i++) {
			setViewPosition(ivChoose[i], (i + 1));
			ivChoose[i].setImageDrawable(BaseCommon.drawableChange(path, "puppy_card_" + nameId[rand[i]]));
		}
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_14_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivChoose1) { // 选项1
			showClick(0, ivChoose1);
		} else if (v == ivChoose2) { // 选项2
			showClick(1, ivChoose2);
		} else if (v == ivChoose3) { // 选项3
			showClick(2, ivChoose3);
		} else if (v == ivChoose4) { // 选项4
			showClick(3, ivChoose4);
		}
	}

	private int clickNum = 0;
	
	private void showClick(int pos, ImageView ivChoose) {
		if(isClick || clickNum >= 4) {
			return;
		}
		isClick = true;
		clickNum++;
		tvSentence.setText(sentenceId[rand[pos]]);
		ivChoose.setImageDrawable(BaseCommon.drawableChange(path, "puppy_card_" + nameId[rand[pos]] + "_bg"));
		ivChoose.setEnabled(false);
		ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "puppy_" + nameId[rand[pos]]));
		playSoundMusic(BaseCommon.pathLevelBGame +  "puppy_" + nameId[rand[pos]] + ".mp3");
		delayTime();
	}
	
	private void delayTime() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2200);
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
				if(clickNum >= 4) {
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