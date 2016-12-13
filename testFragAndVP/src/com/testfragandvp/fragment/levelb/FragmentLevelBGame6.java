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
 *  Birthday Party
 * @author
 *
 */
public class FragmentLevelBGame6 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivLaiLai, ivAnswer1, ivAnswer2;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"toy", "icecream", "balloon", "cake"};
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private int[] rand, randB;
	private static String path;
	private ImageView[] ivCard;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	
	public FragmentLevelBGame6() {
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
		view = inflater.inflate(R.layout.level_b_game_6, container, false);
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
		playSoundMusic(BaseCommon.pathLevelBGame +  "birthday_party.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		ivLaiLai = (ImageView) view.findViewById(R.id.iv_lailai);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "pick_up_bg"));
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
		setViewPosition(ivLaiLai, 0);
		ivCard = new ImageView[2];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		for(int i=0; i<ivCard.length; i++) {
			ivCard[i].setOnClickListener(this);
			setViewPosition(ivCard[i], (i + 1));
		}
		setViewPosition(ivAnswer1, 3);
		setViewPosition(ivAnswer2, 4);
		refreshData();
	}
	
	private void refreshData() {
		randB = BaseCommon.getList(new int[]{0, 1});
		ivLaiLai.setImageDrawable(BaseCommon.drawableChange(path, "lailai_" + nameId[rand[clickNum]]));
		int index = BaseCommon.randData(4, clickNum);
		System.out.println("index :" + index);
		System.out.println("clickNum :" + clickNum);
		ivAnswer1.setImageResource(0);
		ivAnswer2.setImageResource(0);
		if(randB[0] == 0) {
			ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "birthday_" + nameId[rand[clickNum]]));
			ivCard2.setImageDrawable(BaseCommon.drawableChange(path, "birthday_" + nameId[rand[index]]));
		} else {
			ivCard1.setImageDrawable(BaseCommon.drawableChange(path, "birthday_" + nameId[rand[index]]));
			ivCard2.setImageDrawable(BaseCommon.drawableChange(path, "birthday_" + nameId[rand[clickNum]]));
		}
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_6_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivCard1) {
			showClick(0, ivAnswer1);
		} else if (v == ivCard2) {
			showClick(1, ivAnswer2);
		}
	}

	private int clickNum = 0;
	
	private void showClick(int pos, ImageView ivAnswer) {
		if(isClick || clickNum >= 4) {
			return;
		}
		isClick = true;
		if(randB[pos] == 0) {
			ivAnswer.setImageResource(R.drawable.iv_good);
			playSoundMusic(BaseCommon.pathLevelBGame +  "birthday_party_" + nameId[rand[clickNum]] + ".mp3");
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
					Thread.sleep(4000);
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
					// 游戏结束
					ActivityBiz.finishBfBookGame();
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
	
//	private void delayFinish() {
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(4000);
//					handler.sendMessage(handler.obtainMessage(2));
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
//	}

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