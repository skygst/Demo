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
 *  I Pick Up
 * @author
 *
 */
public class FragmentLevelBGame2 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, ivStarCard1, ivStarCard2,
			ivStarCard3, ivStarCard4, ivLaiLai;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] nameId = {"ball", "blanket", "book", "cars", "hat", "shoes", "teddy_bear"};
	private int[] arrayList = new int[] { 0, 1, 2, 3, 4, 5, 6 };
	private int[] rand;
	private static String path;
	private ImageView[] ivCard;
	private ImageView[] ivStarCard;
	private int countNum = 0;
	private boolean isClick = false;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	
	public FragmentLevelBGame2() {
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
		view = inflater.inflate(R.layout.level_b_game_2, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		countNum = 0;
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
		playSoundMusic(BaseCommon.pathLevelBGame +  "i_pick_up.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivStarCard1 = (ImageView) view.findViewById(R.id.iv_star_card_1);
		ivStarCard2 = (ImageView) view.findViewById(R.id.iv_star_card_2);
		ivStarCard3 = (ImageView) view.findViewById(R.id.iv_star_card_3);
		ivStarCard4 = (ImageView) view.findViewById(R.id.iv_star_card_4);
		ivLaiLai = (ImageView) view.findViewById(R.id.iv_big_pic);
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

//	private void showCard(String[] cardId) {
//		ivCard1.setImageDrawable(BaseCommon.drawableChange(path,
//				cardId[rand[0]]));
//		ivCard2.setImageDrawable(BaseCommon.drawableChange(path,
//				cardId[rand[1]]));
//		ivCard3.setImageDrawable(BaseCommon.drawableChange(path,
//				cardId[rand[2]]));
//		ivCard4.setImageDrawable(BaseCommon.drawableChange(path,
//				cardId[rand[3]]));
//	}

	private void commonPosition() {
		rand = BaseCommon.getList(arrayList);
		setViewPosition(ivLaiLai, 0);
		ivLaiLai.setImageDrawable(BaseCommon.drawableChange(path, "pick_up_lailai"));
		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		ivStarCard = new ImageView[4];
		ivStarCard[0] = ivStarCard1;
		ivStarCard[1] = ivStarCard2;
		ivStarCard[2] = ivStarCard3;
		ivStarCard[3] = ivStarCard4;
		for(int i=0; i<ivCard.length; i++) {
			setViewPosition(ivCard[i], (i + 1));
			setViewPosition(ivStarCard[i], (i + 1));
			ivCard[i].setOnClickListener(this);
		}
		refreshData();
	}
	
	private void refreshData() {
		clickNum = 0;
		if(countNum == 0) {
			for(int i=0; i<ivCard.length; i++) {
				ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, "pick_up_" + nameId[rand[i]]));
				ivStarCard[i].setImageDrawable(null);
			}
		} else {
			for(int i=0; i<ivCard.length; i++) {
				if(i == 3) {
					ivCard[i].setVisibility(View.GONE);
					ivStarCard[i].setVisibility(View.GONE);
					ivStarCard[i].setImageDrawable(null);
				} else {
					ivCard[i].setEnabled(true);
					ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, "pick_up_" + nameId[rand[i + 4]]));
					ivStarCard[i].setImageDrawable(null);
				}
			}
			
		}
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_2_position,
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
		ivStarCard[pos].setImageDrawable(BaseCommon.drawableChange(path, "pick_up_star"));
		int index = countNum * 4 + pos;
		ivLaiLai.setImageDrawable(BaseCommon.drawableChange(path, "pick_up_lailai_" + nameId[rand[index]]));
		// TODO 延迟执行
		delayTime();
		playSoundMusic(BaseCommon.pathLevelBGame + "pick_up_" + nameId[rand[index]] + ".mp3");
	}
	
	private void delayTime() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					if(countNum == 0) {
						// TODO 延迟执行
						if(clickNum >= 4) {
							handler.sendMessage(handler.obtainMessage(0));
						} else {
							handler.sendMessage(handler.obtainMessage(1));
						}
					} else {
						if(clickNum >= 3) {
							handler.sendMessage(handler.obtainMessage(0));
						} else {
							handler.sendMessage(handler.obtainMessage(1));
						}
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
				countNum++;
				if(countNum == 1) {
					isClick = false;
					refreshData();
				} else {
					isClick = true;
					delayFinish();
				}
				break;
			case 1:
				isClick = false;
				break;
			case 2:
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
					Thread.sleep(1000);
					handler.sendMessage(handler.obtainMessage(2));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
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