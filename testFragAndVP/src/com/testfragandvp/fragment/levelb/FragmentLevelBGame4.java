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
 *  The Picnic
 * @author
 *
 */
public class FragmentLevelBGame4 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivSentence, ivCard1, ivCard2, ivCard3, ivCard4, ivLailai, ivFoodBasketBg,
	ivPhysical1, ivPhysical2, ivPhysical3, ivPhysical4, ivFoodBasketFrame;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"chips", "apple", "mouse", "cheese"};
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private int[] rand;
	private static String path;
	private ImageView[] ivCard;
	private ImageView[] ivPhysicalCard;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	
	public FragmentLevelBGame4() {
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
		view = inflater.inflate(R.layout.level_b_game_4, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		isClick = false;
		path = BaseCommon.pathLevelBGameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		// 提示语
		playSoundMusic(BaseCommon.pathLevelBGame +  "the_picnic.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		
		ivFoodBasketBg = (ImageView) view.findViewById(R.id.iv_food_basket_bg);
		ivPhysical1 = (ImageView) view.findViewById(R.id.iv_physical_1);
		ivPhysical2 = (ImageView) view.findViewById(R.id.iv_physical_2);
		ivPhysical3 = (ImageView) view.findViewById(R.id.iv_physical_3);
		ivPhysical4 = (ImageView) view.findViewById(R.id.iv_physical_4);
		ivFoodBasketFrame = (ImageView) view.findViewById(R.id.iv_food_basket_frame);
		
		ivLailai = (ImageView) view.findViewById(R.id.iv_lailai);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "pick_up_bg"));
		ivCard1.setOnClickListener(this);
		ivCard2.setOnClickListener(this);
		ivCard3.setOnClickListener(this);
		ivCard4.setOnClickListener(this);
		commonPosition();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void commonPosition() {
		clickNum = 0;
		rand = BaseCommon.getList(arrayList);
		setViewPosition(ivSentence, 0);
		setViewPosition(ivLailai, 5);
		ivLailai.setImageDrawable(BaseCommon.drawableChange(path, "picnic_lailai"));
		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		ivPhysicalCard = new ImageView[4];
		ivPhysicalCard[0] = ivPhysical1;
		ivPhysicalCard[1] = ivPhysical2;
		ivPhysicalCard[2] = ivPhysical3;
		ivPhysicalCard[3] = ivPhysical4;
		for(int i=0; i<ivCard.length; i++) {
			setViewPosition(ivCard[i], (i + 1));
			setViewPosition(ivPhysicalCard[i], 6);
			ivCard[i].setOnClickListener(this);
		}
		for(int i=0; i<ivCard.length; i++) {
			ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, nameId[rand[i]]));
		}
		setViewPosition(ivFoodBasketBg, 6);
		setViewPosition(ivFoodBasketFrame, 6);
		ivFoodBasketBg.setImageDrawable(BaseCommon.drawableChange(path, "basket_bg"));
		ivFoodBasketFrame.setImageDrawable(BaseCommon.drawableChange(path, "basket_frame"));
	}
	
	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_4_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivCard1) {
			showClick(0);
		} else if (v == ivCard2) {
			showClick(1);
		} else if (v == ivCard3) {
			showClick(2);
		} else if (v == ivCard4) {
			showClick(3);
		}
	}

	private int clickNum = 0;
	
	private void showClick(int pos) {
		System.out.println("showClick isClick :" + isClick);
		if(isClick) {
			return;
		}
		isClick = true;
		clickNum++;
		ivCard[pos].setEnabled(false);
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "sentence_" + nameId[rand[pos]]));
		// TODO 读音
		
		ivCard[pos].setImageDrawable(BaseCommon.drawableChange(path, nameId[rand[pos]] + "_sel"));
		ivPhysicalCard[rand[pos]].setImageDrawable(BaseCommon.drawableChange(path, "basket_" + nameId[rand[pos]]));
		// TODO 延迟执行
		delayTime();
		playSoundMusic(BaseCommon.pathLevelBGame +  "the_picnic_" + nameId[rand[pos]] + ".mp3");
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
				if(clickNum >= 4) {
					// finish
					ActivityBiz.finishBfBookGame();
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