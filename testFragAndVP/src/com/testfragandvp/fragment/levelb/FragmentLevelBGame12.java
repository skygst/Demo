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
 *  Game We Play
 * @author
 *
 */
public class FragmentLevelBGame12 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout, rlSentence;
	private ImageView ivCard1, ivCard2,ivCard3,ivCard4, 
		ivClips1, ivClips2, ivClips3, ivClips4, ivBottom1, ivBottom2, ivBottom3, ivBottom4, ivError;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] picId = {"tag", "baseball", "soccer", "dress_up", "house", "hide_and_seek", "video_games", "with_fire"};
	private String[] nameId = {"play_card_1", "play_card_2", "play_card_3", "play_card_4", "play_card_5", "play_card_6", "play_card_7", "play_card_8"};
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private int[] rand;
	private ImageView[] ivBottomCard, ivClips, ivCard;
	private static String path;
	private boolean isClick = false;
	private int countNum = 0;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	
	public FragmentLevelBGame12() {
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
		view = inflater.inflate(R.layout.level_b_game_12, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		isClick = false;
		clickNum = 0;
		countNum = 0;
		path = BaseCommon.pathLevelBGameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		// 提示语
		playSoundMusic(BaseCommon.pathLevelBGame +  "games_we_play.mp3");
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		rlSentence = (RelativeLayout) view.findViewById(R.id.rl_sentence_bg);
		ivCard1 = (ImageView) view.findViewById(R.id.iv_card_1);
		ivCard2 = (ImageView) view.findViewById(R.id.iv_card_2);
		ivCard3 = (ImageView) view.findViewById(R.id.iv_card_3);
		ivCard4 = (ImageView) view.findViewById(R.id.iv_card_4);
		ivClips1 = (ImageView) view.findViewById(R.id.iv_clips_1);
		ivClips2 = (ImageView) view.findViewById(R.id.iv_clips_2);
		ivClips3 = (ImageView) view.findViewById(R.id.iv_clips_3);
		ivClips4 = (ImageView) view.findViewById(R.id.iv_clips_4);
		ivBottom1 = (ImageView) view.findViewById(R.id.iv_bottom_1);
		ivBottom2 = (ImageView) view.findViewById(R.id.iv_bottom_2);
		ivBottom3 = (ImageView) view.findViewById(R.id.iv_bottom_3);
		ivBottom4 = (ImageView) view.findViewById(R.id.iv_bottom_4);
		ivError = (ImageView) view.findViewById(R.id.iv_error);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "play_bg"));
		commonPosition();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void commonPosition() {
		setViewPosition(ivCard1, 1);
		ivCard = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		ivClips = new ImageView[4];
		ivClips[0] = ivClips1;
		ivClips[1] = ivClips2;
		ivClips[2] = ivClips3;
		ivClips[3] = ivClips4;
		
		ivBottomCard = new ImageView[4];
		ivBottomCard[0] = ivBottom1;
		ivBottomCard[1] = ivBottom2;
		ivBottomCard[2] = ivBottom3;
		ivBottomCard[3] = ivBottom4;
		for(int i=0; i<ivBottomCard.length; i++) {
			ivBottomCard[i].setOnClickListener(this);
			setViewPosition(ivBottomCard[i], (i + 8));
			setViewPosition(ivClips[i], i);
			ivClips[i].setImageDrawable(BaseCommon.drawableChange(path, "play_clips_" + (i + 1)));
		}
		refreshData();
	}
	
	private void refreshData() {
		if(clickNum >= 4 && countNum > 1) return;
		clickNum = 0;
		rand = BaseCommon.getList(arrayList);
		int index = countNum * 4;
		for(int i=0; i<ivBottomCard.length; i++) {
			setViewPosition(ivCard[i], (i + 4));
			ivBottomCard[i].setVisibility(View.VISIBLE);
			ivCard[i].setVisibility(View.GONE);
			ivBottomCard[i].setEnabled(true);
			ivBottomCard[i].setImageDrawable(BaseCommon.drawableChange(path, "play_card_" + (rand[i] + 1 + index)));
		}
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedLevelBPosition.lebel_b_12_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivBottom1) {
			showClick(0);
		} else if (v == ivBottom2) {
			showClick(1);
		} else if (v == ivBottom3) {
			showClick(2);
		} else if (v == ivBottom4) {
			showClick(3);
		}
	}

	private int clickNum = 0;
	
	private void showClick(int pos) {
		if(isClick || clickNum >= 4) {
			return;
		}
		isClick = true;
		int index = countNum * 4;
		clickNum++;
		if((rand[pos] + index) != 7) {
			ivCard[pos].setVisibility(View.VISIBLE);
			ivCard[pos].setImageDrawable(BaseCommon.drawableChange(path, nameId[rand[pos] + index]));
			ivBottomCard[pos].setVisibility(View.GONE);
		} else {
			setViewPosition(ivError, (pos + 12));
			ivError.setImageResource(R.drawable.iv_error_small);
		}
		ivBottomCard[pos].setEnabled(false);
		playSoundMusic(BaseCommon.pathLevelBGame +  "play_" + picId[rand[pos] + index] + ".mp3");
		delayTime(false);
	}
	
	private void delayTime(final boolean isFinish) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					if(isFinish) {
						Thread.sleep(1200);
						handler.sendMessage(handler.obtainMessage(1));
					} else {
						Thread.sleep(2200);
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
				isClick = false;
				System.out.println("clickNum :" + clickNum);
				System.out.println("countNum :" + countNum);
				if(clickNum >= 4) {
					if(countNum == 0) {
						countNum++;
						refreshData();
					} else {
						countNum++;
						// 游戏结束
						delayTime(true);
					}
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