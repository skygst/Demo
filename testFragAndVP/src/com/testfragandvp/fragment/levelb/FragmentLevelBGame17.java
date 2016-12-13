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

/**
 *  The Hungry Goat
 * @author
 *
 */
public class FragmentLevelBGame17 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivBigCard, ivAnswer1, ivAnswer2, ivAnswer3, ivAnswer4;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"gate", "garden", "gift", "goose" };
	private ImageView[] ivCard;
	private static String path;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private boolean isFinish = false;
	
	public FragmentLevelBGame17() {
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
		view = inflater.inflate(R.layout.level_b_game_17, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		isClick = true;
		clickNum = 0;
		isFinish = false;
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
		ivBigCard = (ImageView) view.findViewById(R.id.iv_big_card);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		ivAnswer3 = (ImageView) view.findViewById(R.id.iv_answer_3);
		ivAnswer4 = (ImageView) view.findViewById(R.id.iv_answer_4);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "hungry_bg"));
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
					playSoundMusic(BaseCommon.pathLevelBGame +  "the_hungry_goat.mp3");
					Thread.sleep(4500);
					handler.sendMessage(handler.obtainMessage(2));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	private void commonPosition() {
		setViewPosition(ivBigCard, 0);
		ivBigCard.setImageDrawable(BaseCommon.drawableChange(path, "hungry_goat"));
		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		for(int i=0; i<ivCard.length; i++) {
			setViewPosition(ivCard[i], (i + 1));
			ivCard[i].setOnClickListener(this);
			ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, "hungry_" + nameId[i]));
		}
		setViewPosition(ivAnswer1, 5);
		setViewPosition(ivAnswer2, 6);
		setViewPosition(ivAnswer3, 7);
		setViewPosition(ivAnswer4, 8);
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_17_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivCard1) {
			showClick(0, ivAnswer1);
		} else if(v == ivCard2){
			showClick(1, ivAnswer2);
		} else if(v == ivCard3){
			showClick(2, ivAnswer3);
		} else if(v == ivCard4){
			showClick(3, ivAnswer4);
		}
	}

	private int clickNum = 0;
	
	private void showClick(int pos, ImageView ivAnswer) {
		if(isClick || clickNum >= 4) {
			return;
		}
		isClick = true;
		clickNum++;
		ivCard[pos].setEnabled(false);
		ivAnswer.setImageResource(R.drawable.iv_good_small);
		playSoundMusic(BaseCommon.pathLevelBGame +  "the_hungry_" + nameId[pos] + ".mp3");
		if(clickNum >= 4) {
			delayTime(false);
		} else {
			// 延迟一秒执行
			delayTime(true);
		}
	}
	
	private void delayTime(final boolean isClick) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					if(isClick) {
						handler.sendMessage(handler.obtainMessage(1));
					} else {
						handler.sendMessage(handler.obtainMessage(0));
					}
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
				ActivityBiz.finishBfBookGame();
				break;
			case 1:
				isClick = false;
				break;
			case 2:
				playSoundMusic(BaseCommon.pathLevelBGame +  "the_hungry_goat_g.mp3");
				isClick = false;
				break;
			default:
				break;
			}
		}
		
	};
	
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