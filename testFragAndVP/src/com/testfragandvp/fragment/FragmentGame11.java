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
import com.gst.move.custom.XButton;
import com.gst.move.utils.BaseCommon;
import com.gst.move.utils.MediaCommon;

/**
 *  These shoes
 * @author
 *
 */
public class FragmentGame11 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivCard, ivSentence;
	private XButton btnRead, btnWright, btnWrong;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardName = {"tall", "wet", "old", "blue"};
	private boolean[] answerValue = {true, false, true, false};
	private int[] arrayList = new int[] { 0, 1, 2, 3};
	private int[] randB;
	private String path;
	private int width, height;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int selNum = 0;
	private boolean isPlayPrologue = true;

	public FragmentGame11() {
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
		view = inflater.inflate(R.layout.af_game_11, container, false);
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
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "these_shoes_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		ivCard = (ImageView) view.findViewById(R.id.iv_card);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		btnRead = (XButton) view.findViewById(R.id.btn_read);
		btnWright = (XButton) view.findViewById(R.id.btn_wright);
		btnWrong = (XButton) view.findViewById(R.id.btn_wrong);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "these_shoes_bg"));
		commonPosition();
		randB = BaseCommon.getList(arrayList);
		initCard();
		btnRead.setOnClickListener(this);
		btnWright.setOnClickListener(this);
		btnWrong.setOnClickListener(this);
	}
	
	private void initCard() {
		ivCard.setImageDrawable(BaseCommon.drawableChange(path, "shoes_" + cardName[randB[selNum]]));
		btnWright.setBackground(BaseCommon.drawableChange(path, "shoes_wright_del"));
		btnWrong.setBackground(BaseCommon.drawableChange(path, "shoes_wrong_del"));
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "shoes_sentence_" + cardName[randB[selNum]]));
	}
	
	private void commonPosition() {
		setViewPosition(btnRead, 0);
		setViewPosition(ivCard, 1);
		setViewPosition(btnWright, 2);
		setViewPosition(btnWrong, 3);
		setViewPosition(ivSentence, 4);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.these_shoes_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == btnRead) { // 重读
			if(selNum < 4) {
				playSoundMusic(BaseCommon.path_game + "theseshoes_" + cardName[randB[selNum]] + ".mp3");
			}
		} else if(v == btnWright) { // 对
			if(answerValue[randB[selNum]]) {
				clickAnswer(v, "shoes_wright_sel");
			} else { // sorry
				MediaCommon.playFuxiError(mContext);
			}
		} else if(v == btnWrong) { // 错
			if(!answerValue[randB[selNum]]) {
				clickAnswer(v, "shoes_wrong_sel");
			} else { // sorry
				MediaCommon.playFuxiError(mContext);
			}
		}
	}
	
	private void clickAnswer(View v, String bgValue) {
		v.setBackground(BaseCommon.drawableChange(path, bgValue));
		delayChangeCard(1000);
		MediaCommon.playFuxiGood(mContext);
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
				selNum++;
				if(selNum >= 4) {
					btnWright.setEnabled(false);
					btnWrong.setEnabled(false);
					ActivityBiz.finishAfBookGame();
				} else {
					initCard();
					playSoundMusic(BaseCommon.path_game + "theseshoes_" + cardName[randB[selNum]] + ".mp3");
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
	            	if(isPlayPrologue && selNum == 0) {
	            		isPlayPrologue = false;
	            		playSoundMusic(BaseCommon.path_game + "theseshoes_" + cardName[randB[selNum]] + ".mp3");
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