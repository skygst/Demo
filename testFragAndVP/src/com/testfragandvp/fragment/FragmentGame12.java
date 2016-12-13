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

/**
 *  What I Like
 * @author
 *
 */
public class FragmentGame12 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard1, ivCard2, ivCard3, ivCard4, 
				ivAnswer1, ivAnswer2, ivAnswer3, ivAnswer4;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardName = {"bee", "flower", "tree", "bird", "dig", "play", "slide", "swing"};
	private int[] arrayList = new int[] { 0, 1, 2, 3};
	private int[] randB;
	private ImageView[] ivCard;
	private ImageView[] ivAnswer;
	private String path;
	private int width, height;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int selNum = 0;
	private boolean isFirstQuestion = true;

	public FragmentGame12() {
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
		view = inflater.inflate(R.layout.af_game_12, container, false);
		init();
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "i_like_prologue_b.mp3"));
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
		rlLayout.setBackground(BaseCommon.drawableChange(path, "what_i_like_bg"));
		commonPosition();
		initCard();
	}
	
	private void initCard() {
		selNum = 0;
		randB = BaseCommon.getList(arrayList);
		ivCard = new ImageView[4];
		ivAnswer = new ImageView[4];
		ivCard[0] = ivCard1;
		ivCard[1] = ivCard2;
		ivCard[2] = ivCard3;
		ivCard[3] = ivCard4;
		int index = 0;
		if(!isFirstQuestion) {
			index = 4;
		}
		for(int i=0; i<ivCard.length; i++) {
			ivCard[i].setImageDrawable(BaseCommon.drawableChange(path, cardName[randB[i] + index]));
			ivCard[i].setOnClickListener(this);
		}
		ivAnswer[0] = ivAnswer1;
		ivAnswer[1] = ivAnswer2;
		ivAnswer[2] = ivAnswer3;
		ivAnswer[3] = ivAnswer4;
		for(int i=0; i<ivAnswer.length; i++) {
			ivAnswer[i].setVisibility(View.GONE);
		}
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
		biz.setViewPosition(iv, i, FixedPositionAfrica.what_i_like_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivCard1 || v == ivCard2 || v == ivCard3 || v == ivCard4) {
			int index = ActivityBiz.clickNum(v, ivCard);
			if(isFirstQuestion) { // b
				playSoundMusic(BaseCommon.path_game + "i_like_" + cardName[randB[index]] + ".mp3");
				ivAnswer[index].setVisibility(View.VISIBLE);
				if(randB[index] == 0 || randB[index] == 3) {
					selNum++;
					ivAnswer[index].setImageResource(R.drawable.yes_same_size);
					if(selNum >= 2) {
						delayChangeCard(5000);
					}
				} else {
					ivAnswer[index].setImageResource(R.drawable.no_same_size);
				}
			} else { // s
				playSoundMusic(BaseCommon.path_game + "i_like_" + cardName[randB[index] + 4] + ".mp3");
				ivAnswer[index].setVisibility(View.VISIBLE);
				if(randB[index] == 2 || randB[index] == 3) {
					selNum++;
					ivAnswer[index].setImageResource(R.drawable.yes_same_size);
					if(selNum >= 2) {
						delayChangeCard(5000);
					}
				} else {
					ivAnswer[index].setImageResource(R.drawable.no_same_size);
				}
			}
		}
	}
	
	private void delayChangeCard(final long time) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(time);
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
				if(isFirstQuestion) {
					isFirstQuestion = false;
					initCard();
					playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "i_like_prologue_s.mp3"));
				} else {
					ActivityBiz.finishAfBookGame();
					for(int i=0; i<ivCard.length; i++) {
						ivCard[i].setEnabled(false);
					}
				}
				break;

			default:
				break;
			}
		}
		
	};
	
	private void playSoundMusic(String path) {
		try {
			if (mMediaPlayerSound != null) { // 一定要清空播放器资源
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
			}
			mMediaPlayerSound = new MediaPlayer();
			/* 重置MediaPlayer */
			mMediaPlayerSound.reset();
			/* 设置要播放的文件的路径 */
			mMediaPlayerSound.setDataSource(path);
			mMediaPlayerSound.setLooping(false);
			/* 准备播放 */
			mMediaPlayerSound.prepare();
			/* 开始播放 */
			mMediaPlayerSound.start();
			mMediaPlayerSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener()    
	        {
	            public void onCompletion(MediaPlayer arg0)   
	            {   
	                if(mMediaPlayerSound != null) {
	                	mMediaPlayerSound.release();
	    				mMediaPlayerSound = null;
	                }
	            }   
	        });
			mMediaPlayerSound.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					if(mMediaPlayerSound != null) {
						mMediaPlayerSound.release();
						mMediaPlayerSound = null;
	                }
					return false;
				}
			});
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	private void clearMediaPlayer() {
		try {
			selNum = 0;
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