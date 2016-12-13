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
 *  Up and Down
 * @author
 *
 */
public class FragmentGame20 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivPic, ivSentence;
	private XButton btnRead, btnRight, btnWrong;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardName = {"eyes_down", "eyes_up", "hands_down", "hands_up"};
	private String[][] soundName = {
			{"eyes_down", "eyes_up"},
			{"eyes_down", "eyes_up"},
			{"hands_down", "hands_up"},
			{"hands_down", "hands_up"}
	};
	private int[] arrayList = new int[] { 0, 1, 2, 3 };
	private int[] randB;
	private String path;
	private int width, height;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int selNum = 0, currentNum = 0;
	private boolean isPlayprologue = true;

	public FragmentGame20() {
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
		view = inflater.inflate(R.layout.af_game_20, container, false);
		init();
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		path = BaseCommon.path_gameImages;
		selNum = 0;
		currentNum = 0;
		isPlayprologue = true;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "up_and_down_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		btnRead = (XButton) view.findViewById(R.id.btn_read);
		btnRight = (XButton) view.findViewById(R.id.btn_right);
		btnWrong = (XButton) view.findViewById(R.id.btn_wrong);
		ivPic = (ImageView) view.findViewById(R.id.iv_pic);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "up_and_down_bg"));
		commonPosition();
		randB = BaseCommon.getList(arrayList);
		initCard();
		btnRight.setOnClickListener(this);
		btnWrong.setOnClickListener(this);
		btnRead.setOnClickListener(this);
	}
	
	private void initCard() {
		btnRight.setBackground(BaseCommon.drawableChange(path, "up_and_down_right_del"));
		btnWrong.setBackground(BaseCommon.drawableChange(path, "up_and_down_wrong_del"));
		ivPic.setImageDrawable(BaseCommon.drawableChange(path, "up_and_down_pic_" + cardName[randB[currentNum]]));
		btnRight.setEnabled(true);
		btnWrong.setEnabled(true);
		if(!isPlayprologue) {
			playSoundMusic(BaseCommon.path_game + "upanddown_" + soundName[randB[currentNum]][selNum] + ".mp3");
		}
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "up_and_down_" + soundName[randB[currentNum]][0]));
	}
	
	private void commonPosition() {
		setViewPosition(btnRead, 0);
		setViewPosition(btnRight, 1);
		setViewPosition(btnWrong, 2);
		setViewPosition(ivPic, 3);
		setViewPosition(ivSentence, 4);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.up_and_down_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == btnRead) {
			playSoundMusic(BaseCommon.path_game + "upanddown_" + soundName[randB[currentNum]][selNum] + ".mp3");
		} else if(v == btnRight) {
			if(randB[currentNum] == 0 || randB[currentNum] == 2) { // selNum = 0为对
				commonClick(v, 0);
			} else { // selNum = 1为错
				commonClick(v, 1);
			}
		} else if(v == btnWrong) {
			if (randB[currentNum] == 1 || randB[currentNum] == 3) {
				commonClick(v, 0);
			} else {
				commonClick(v, 1);
			}
		}
	}
	
	private void commonClick(View v, int index) {
		if(selNum == index) {
			if(v == btnRight) {
				v.setBackground(BaseCommon.drawableChange(path, "up_and_down_right_sel"));
			} else {
				v.setBackground(BaseCommon.drawableChange(path, "up_and_down_wrong_sel"));
			}
			MediaCommon.playFuxiGood(mContext);
			clickOk();
		} else { // sorry
			MediaCommon.playFuxiError(mContext);
		}
	}
	
	private void clickOk() {
		btnRight.setEnabled(false);
		btnWrong.setEnabled(false);
		delayChangeCard(1000);
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
				if(selNum >= 2) {
					currentNum++;
					if(currentNum >= 4) {
						ActivityBiz.finishAfBookGame();
					} else {
						selNum = 0;
						initCard();
					}
				} else {
					btnRight.setEnabled(true);
					btnWrong.setEnabled(true);
					btnRight.setBackground(BaseCommon.drawableChange(path, "up_and_down_right_del"));
					btnWrong.setBackground(BaseCommon.drawableChange(path, "up_and_down_wrong_del"));
					playSoundMusic(BaseCommon.path_game + "upanddown_" + soundName[randB[currentNum]][1] + ".mp3");
					ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "up_and_down_" + soundName[randB[currentNum]][1]));
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
                	if(isPlayprologue && currentNum == 0 && selNum == 0) {
                		isPlayprologue = false;
                		playSoundMusic(BaseCommon.path_game + "upanddown_" + soundName[randB[currentNum]][0] + ".mp3");
                	} else {
                		isPlayprologue = false;
                	}
                }   
            });
			mMediaPlayerSound.setOnErrorListener(new MediaPlayer.OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
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