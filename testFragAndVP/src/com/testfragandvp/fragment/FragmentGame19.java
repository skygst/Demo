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
 *  The Big Cat
 * @author
 *
 */
public class FragmentGame19 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivAnswer1, ivAnswer2, ivPic, ivSentence;
	private XButton btnWord1, btnWord2;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardName = {"clean", "climb", "eat", "hide", "look", "play", "run", "sleep"};
	private int[] arrayList = new int[] { 0, 1, 2, 3, 4, 5, 6, 7 };
	private int[] randB, rand;
	private String path;
	private int width, height;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int selNum = 0;

	public FragmentGame19() {
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
		view = inflater.inflate(R.layout.af_game_19, container, false);
		init();
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		path = BaseCommon.path_gameImages;
		selNum = 0;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "the_big_cat_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		btnWord1 = (XButton) view.findViewById(R.id.btn_word_1);
		btnWord2 = (XButton) view.findViewById(R.id.btn_word_2);
		ivAnswer1 = (ImageView) view.findViewById(R.id.iv_answer_1);
		ivAnswer2 = (ImageView) view.findViewById(R.id.iv_answer_2);
		ivPic = (ImageView) view.findViewById(R.id.iv_pic);
		ivSentence = (ImageView) view.findViewById(R.id.iv_sentence);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "big_cat_bg"));
		commonPosition();
		randB = BaseCommon.getList(arrayList);
		initCard();
		btnWord1.setOnClickListener(this);
		btnWord2.setOnClickListener(this);
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "cat_sentence_del"));
	}
	
	private void initCard() {
		int index = BaseCommon.randData(8, randB[selNum]);
		String[] wordName = new String[2];
		wordName[0] = cardName[randB[selNum]];
		wordName[1] = cardName[index];
		rand = BaseCommon.getList(new int[] { 0, 1});
		btnWord1.setBackground(BaseCommon.drawableChange(path, "cat_word_" + wordName[rand[0]]));
		btnWord2.setBackground(BaseCommon.drawableChange(path, "cat_word_" + wordName[rand[1]]));
		ivPic.setImageDrawable(BaseCommon.drawableChange(path, "cat_pic_" + cardName[randB[selNum]]));
		btnWord1.setEnabled(true);
		btnWord2.setEnabled(true);
		ivAnswer1.setVisibility(View.GONE);
		ivAnswer2.setVisibility(View.GONE);
	}
	
	private void commonPosition() {
		setViewPosition(btnWord1, 0);
		setViewPosition(btnWord2, 1);
		setViewPosition(ivAnswer1, 2);
		setViewPosition(ivAnswer2, 3);
		setViewPosition(ivPic, 4);
		setViewPosition(ivSentence, 5);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.the_big_cat_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		ivAnswer1.setVisibility(View.GONE);
		ivAnswer2.setVisibility(View.GONE);
		if(v == btnWord1) {
			ivAnswer1.setVisibility(View.VISIBLE);
			if(rand[0] == 0) {
				clickOk();
				ivAnswer1.setImageResource(R.drawable.yes_same_size);
			} else {
				MediaCommon.playFuxiError(mContext);
				ivAnswer1.setImageResource(R.drawable.no_same_size);
			}
		} else if(v == btnWord2) {
			ivAnswer2.setVisibility(View.VISIBLE);
			if (rand[0] == 1) {
				clickOk();
				ivAnswer2.setImageResource(R.drawable.yes_same_size);
			} else {
				MediaCommon.playFuxiError(mContext);
				ivAnswer2.setImageResource(R.drawable.no_same_size);
			}
		}
	}
	
	private void clickOk() {
		playSoundMusic(BaseCommon.path_game + "thebigcat_" + cardName[randB[selNum]] + ".mp3");
		ivSentence.setImageDrawable(BaseCommon.drawableChange(path, "cat_sentence_" + cardName[randB[selNum]]));
		btnWord1.setEnabled(false);
		btnWord2.setEnabled(false);
		delayChangeCard(4000);
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
				if(selNum >= 8) {
					ActivityBiz.finishAfBookGame();
				} else {
					initCard();
//					playSoundMusic(BaseCommon.path_game + "theseshoes_" + cardName[randB[selNum]] + ".mp3");
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