package com.testfragandvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.data.FixedPositionAfrica;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.test.MainActivity;

public class FragmentDone extends Fragment implements OnClickListener {

	private Context mContext;
	private View view;
	private RelativeLayout rlLayout;
	private TextView tvAnswerRightNum, tvAnswerRight, tvGood, tvKeepItUp;
	private ImageView ivPic;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private MediaPlayer mMediaPlayerSound = null; // 播放音效

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		init();
		view = inflater.inflate(R.layout.done, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			if (ActivityBiz.isFinishAll()) { // 全部完成， 显示答案对错
				int answerNum = 0;
				MainActivity.isShowAnswer = true;
				for (int i = 0; i < MainActivity.btnViews.length; i++) {
					if (i > 0 && i < 6) {
						if (ActivityBiz.arrayValue(
								MainActivity.choiceAnswerStatus,
								MainActivity.replyChoiceAnswerStatus, (i - 1))) {
							answerNum++;
							MainActivity.btnViews[i]
									.setBackgroundResource(R.drawable.step_finish_wright);
						} else {
							MainActivity.btnViews[i]
									.setBackgroundResource(R.drawable.step_finish_wrong);
						}
					}
				}
				if(answerNum == 5) {
					tvAnswerRightNum.setVisibility(View.GONE);
					tvAnswerRight.setVisibility(View.GONE);
					tvKeepItUp.setVisibility(View.GONE);
					tvGood.setVisibility(View.VISIBLE);
					tvGood.setBackgroundResource(R.drawable.af_down_answer_good);
					ivPic.setImageResource(R.drawable.af_down_pic_good);
				} else {
					tvAnswerRightNum.setVisibility(View.VISIBLE);
					tvAnswerRight.setVisibility(View.VISIBLE);
					tvKeepItUp.setVisibility(View.GONE);
					tvGood.setVisibility(View.GONE);
					tvAnswerRightNum.setText("" + answerNum);
					tvAnswerRight.setBackgroundResource(R.drawable.af_down_answer_wrong);
					ivPic.setImageResource(R.drawable.af_down_pic_wrong);
				}
			} else {
				tvKeepItUp.setVisibility(View.VISIBLE);
				tvKeepItUp.setText("继续努力，还没有答完哦");
				TextPaint tp = tvKeepItUp.getPaint();
				tp.setFakeBoldText(true); 
				ivPic.setImageResource(R.drawable.af_down_pic_wrong);
			}
		} else {
			clearMediaPlayer();
		}
	}

	private void setView() {
		rlLayout = (RelativeLayout) view.findViewById(R.id.rl_layout);
		tvAnswerRight = (TextView) view.findViewById(R.id.tv_answer_right);
		tvAnswerRightNum = (TextView) view.findViewById(R.id.tv_answer_right_num);
		tvGood = (TextView) view.findViewById(R.id.tv_good);
		tvKeepItUp = (TextView) view.findViewById(R.id.tv_keep_it_up);
		ivPic = (ImageView) view.findViewById(R.id.iv_pic);
		rlLayout.setBackgroundResource(R.drawable.af_down_bg);
		setViewPosition(tvGood, 0);
		setViewPosition(tvAnswerRight, 1);
		setViewPosition(tvAnswerRightNum, 2);
		setViewPosition(tvKeepItUp, 3);
		setViewPosition(ivPic, 4);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i,
				FixedPositionAfrica.done_position, scaleQPW,
				scaleQPH, 0, 0, 1.0f);
	}

//	private void playSoundMusic(String path) {
//		mMediaPlayerSound = ActivityBiz.playSoundMusic(mMediaPlayerSound, path);
//	}

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

	@Override
	public void onClick(View v) {

	}

}