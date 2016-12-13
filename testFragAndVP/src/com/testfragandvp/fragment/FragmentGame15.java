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
 *  Carlos Goes To School
 * @author
 *
 */
public class FragmentGame15 extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private ImageView ivBigBag, ivSmallBag, ivPencil, ivEraser, ivRuler;
	private XButton btnCard1, btnCard2, btnCard3;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private String[] cardName = {"dog", "eraser", "parents", "pencil", "sister", "ruler"};
	private int[] arrayList1 = new int[] { 0, 1, 2};
	private int[] arrayList2 = new int[] { 3, 4, 5};
	private int[] randB;
	private XButton[] btnCard;
	private String path;
	private int width, height;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private int selNum = 0;
	private boolean isFirstQuestion = true;

	public FragmentGame15() {
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
		view = inflater.inflate(R.layout.af_game_15, container, false);
		init();
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		selNum = 0;
		isFirstQuestion = true;
		path = BaseCommon.path_gameImages;
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		playSoundMusic(BaseCommon.path_game + BaseCommon.mp3Path(mContext, "to_school_prologue.mp3"));
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		btnCard1 = (XButton) view.findViewById(R.id.btn_card_1);
		btnCard2 = (XButton) view.findViewById(R.id.btn_card_2);
		btnCard3 = (XButton) view.findViewById(R.id.btn_card_3);
		ivPencil = (ImageView) view.findViewById(R.id.iv_pencil);
		ivEraser = (ImageView) view.findViewById(R.id.iv_eraser);
		ivRuler = (ImageView) view.findViewById(R.id.iv_ruler);
		ivBigBag = (ImageView) view.findViewById(R.id.iv_big_bag);
		ivSmallBag = (ImageView) view.findViewById(R.id.iv_small_bag);
		rlLayout.setBackground(BaseCommon.drawableChange(path, "to_school_bg"));
		commonPosition();
		ivBigBag.setImageDrawable(BaseCommon.drawableChange(path, "to_school_big_bag"));
		ivSmallBag.setImageDrawable(BaseCommon.drawableChange(path, "to_school_small_bag"));
		ivPencil.setImageDrawable(BaseCommon.drawableChange(path, "pencil"));
		ivEraser.setImageDrawable(BaseCommon.drawableChange(path, "eraser"));
		ivRuler.setImageDrawable(BaseCommon.drawableChange(path, "ruler"));
		initCard();
	}
	
	private void initCard() {
		if(isFirstQuestion) {
			randB = BaseCommon.getList(arrayList1);
		} else {
			randB = BaseCommon.getList(arrayList2);
		}
		btnCard = new XButton[3];
		btnCard[0] = btnCard1;
		btnCard[1] = btnCard2;
		btnCard[2] = btnCard3;
		for(int i=0; i<btnCard.length; i++) {
			btnCard[i].setBackground(BaseCommon.drawableChange(path, "to_school_" + cardName[randB[i]] + "_del"));
			btnCard[i].setOnClickListener(this);
			btnCard[i].setEnabled(true);
		}
	}
	
	private void commonPosition() {
		setViewPosition(btnCard1, 0);
		setViewPosition(btnCard2, 1);
		setViewPosition(btnCard3, 2);
		
		setViewPosition(ivBigBag, 3);
		setViewPosition(ivPencil, 4);
		setViewPosition(ivEraser, 5);
		setViewPosition(ivRuler, 6);
		setViewPosition(ivSmallBag, 7);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.to_school_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == btnCard1 || v == btnCard2 || v == btnCard3) {
			int index = ActivityBiz.clickNum(v, btnCard);
			if(isFirstQuestion) {
				if(randB[index] == 1) {
					ivEraser.setVisibility(View.VISIBLE);
					clickShow(v, "carlosgoesschool_eraser.mp3", 1);
				} else {
					MediaCommon.playFuxiError(mContext);
					if(randB[index] == 0) {
						v.setBackground(BaseCommon.drawableChange(path, "to_school_" + cardName[0] + "_sel"));
					} else {
						v.setBackground(BaseCommon.drawableChange(path, "to_school_" + cardName[2] + "_sel"));
					}
				}
				v.setEnabled(false);
				if(selNum >= 1) {
					for(int i=0; i<btnCard.length; i++) {
						btnCard[i].setEnabled(false);
					}
					delayChangeCard(1500);
				}
			} else {
				if (randB[index] == 3) {
					ivPencil.setVisibility(View.VISIBLE);
					clickShow(v, "carlosgoesschool_pencil.mp3", 3);
				} else if(randB[index] == 5) {
					ivRuler.setVisibility(View.VISIBLE);
					clickShow(v, "carlosgoesschool_ruler.mp3", 5);
				} else {
					MediaCommon.playFuxiError(mContext);
					v.setBackground(BaseCommon.drawableChange(path, "to_school_" + cardName[4] + "_sel"));
				}
				v.setEnabled(false);
				if(selNum >= 2) {
					delayChangeCard(1700);
				}
			}
		}
	}
	
	private void clickShow(View v, String name, int index) {
		playSoundMusic(BaseCommon.path_game + name);
		selNum++;
		v.setBackground(BaseCommon.drawableChange(path, "to_school_" + cardName[index] + "_sel"));
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
					selNum = 0;
					isFirstQuestion = false;
					initCard();
				} else {
					selNum = 0;
					ActivityBiz.finishAfBookGame();
					for(int i=0; i<btnCard.length; i++) {
						btnCard[i].setEnabled(false);
					}
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
			isFirstQuestion = true;
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