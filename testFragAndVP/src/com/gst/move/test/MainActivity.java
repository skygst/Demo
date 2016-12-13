package com.gst.move.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebodoo.raz.data.FixedPositionAfrica;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.R.drawable;
import com.gst.move.R.id;
import com.gst.move.R.layout;
import com.gst.move.custom.MyViewPager;
import com.gst.move.utils.MultipleChoiceContent;
import com.testfragandvp.adapter.FragAdapter;
import com.testfragandvp.fragment.FragmentChoiceFour;
import com.testfragandvp.fragment.FragmentChoiceThree;
import com.testfragandvp.fragment.FragmentDone;
import com.testfragandvp.fragment.FragmentChoiceFive;
import com.testfragandvp.fragment.FragmentChoiceTwo;
import com.testfragandvp.fragment.FragmentChoiceOne;

public class MainActivity extends FragmentActivity implements OnClickListener {

	public static MyViewPager viewPage;
	private Context mContext;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	public static Button[] btnViews;
	public static Button btnGame, btnDown, btn1, btn2, btn3, btn4, btn5;
	private TextView tvTitle;
	private ImageView ivBack;
	private FragAdapter adapter;
	private int[] brownPicId = new int[] { R.drawable.step_game,
			R.drawable.step_1, R.drawable.step_2,
			R.drawable.step_3, R.drawable.step_4,
			R.drawable.step_5, R.drawable.step_done };
	
	private int[] grayPicId = new int[] { R.drawable.step_game,
			R.drawable.step_gray_1, R.drawable.step_gray_2,
			R.drawable.step_gray_3, R.drawable.step_gray_4,
			R.drawable.step_gray_5, R.drawable.step_done };
	
	private int[] finishPicId = new int[] { R.drawable.step_finish_game,
			R.drawable.step_finish_1, R.drawable.step_finish_2,
			R.drawable.step_finish_3, R.drawable.step_finish_4,
			R.drawable.step_finish_5, R.drawable.step_finish_done };
	
	public static String[] issureContent;
	public static String[] answerContent;
	public static String[] choiceSoundContent;
//	private String path;
	public static int index = 1;
	public static boolean isFirstEnter = true;
	public static boolean isFinish = false;
	public static boolean isShowAnswer = false;
	
	// 缓存选择题完成的题号
	public static Boolean[] blStatus = new Boolean[] {false, false, false, false, false};
	public static String[] choiceAnswerStatus;
	public static String[] replyChoiceAnswerStatus = new String[5];
	public static boolean isFinishGame = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
		setView();
		initViewPage();
		commonPosition();
		issureContent = MultipleChoiceContent.issueContent[index];
		answerContent = MultipleChoiceContent.answerContent[index];
		choiceSoundContent = MultipleChoiceContent.choiceSound[index];
		choiceAnswerStatus = MultipleChoiceContent.choiceAnswer[index];
	}

	private void init() {
		mContext = MainActivity.this;
		isFirstEnter = true;
		isFinish = false;
		isShowAnswer = false;
//		path = BaseCommon.path_gameImages;
		biz = new VideoBiz();
		// TODO 动态传递index的值
//		index = getIntent().getExtras().getInt("index_value");
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		int height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		
		System.out.println("width :" + width);
		System.out.println("height :" + height);
		System.out.println("screenWidth() :" + screenWidth());
	}
	public int screenWidth() {
		int mScreenWidth = 0;
		Display display = getWindowManager().getDefaultDisplay();
		mScreenWidth = display.getWidth();
		return mScreenWidth;
	}

	private void setView() {
		viewPage = (MyViewPager) findViewById(R.id.vp_main);
		viewPage.result = false;
		btnGame = (Button) findViewById(R.id.btn_game);
		btn1 = (Button) findViewById(R.id.btn_1);
		btn2 = (Button) findViewById(R.id.btn_2);
		btn3 = (Button) findViewById(R.id.btn_3);
		btn4 = (Button) findViewById(R.id.btn_4);
		btn5 = (Button) findViewById(R.id.btn_5);
		btnDown = (Button) findViewById(R.id.btn_down);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		tvTitle.setText(MultipleChoiceContent.titles[index]);
		btnGame.setOnClickListener(this);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btnDown.setOnClickListener(this);
		ivBack.setOnClickListener(this);

		settextview();
	}

	private void commonPosition() {
		setViewPosition(btnGame, 0);
		setViewPosition(btn1, 1);
		setViewPosition(btn2, 2);
		setViewPosition(btn3, 3);
		setViewPosition(btn4, 4);
		setViewPosition(btn5, 5);
		setViewPosition(btnDown, 6);
		setViewPosition(tvTitle, 7);
		setViewPosition(ivBack, 8);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAfrica.common_sel_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	public void settextview() {
		btnViews = new Button[7];
		btnViews[0] = btnGame;
		btnViews[1] = btn1;
		btnViews[2] = btn2;
		btnViews[3] = btn3;
		btnViews[4] = btn4;
		btnViews[5] = btn5;
		btnViews[6] = btnDown;
	}

	/**
	 * 初始化 view page的相关数据
	 */
	private void initViewPage() {
		List<Fragment> fragments = new ArrayList<Fragment>();
		Fragment fragment = MultipleChoiceContent.fragmentGame[index];
		fragments.add(fragment); // 游戏
		fragments.add(new FragmentChoiceOne()); // 选择题 -- 1
		fragments.add(new FragmentChoiceTwo()); // 选择题 -- 2
		fragments.add(new FragmentChoiceThree()); // 选择题 -- 3
		fragments.add(new FragmentChoiceFour()); // 选择题 -- 4
		fragments.add(new FragmentChoiceFive()); // 选择题 -- 5
		fragments.add(new FragmentDone()); // 完成题目 -- 2
		adapter = new FragAdapter(getSupportFragmentManager(), fragments);
		viewPage.setAdapter(adapter);
		viewPage.setCurrentItem(0);
		changeTextColor(0);
		viewPage.setOffscreenPageLimit(7);
		viewPage.setOnPageChangeListener(new MyVPageChangeListener());
	}

	private class MyVPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int location) {
			changeTextColor(location);
		}

	}

	/**
	 * 设置按钮颜色。
	 */
	private void changeTextColor(int location) {
		resetView();
		showView(location);
	}

	// 当前选中界面按钮的背景--灰色背景
	private void showView(int index) {
		if (index > 0 && index < 6) {
			if (isShowAnswer) {
				if (ActivityBiz.arrayValue(choiceAnswerStatus,
						replyChoiceAnswerStatus, (index - 1))) {
					btnViews[index]
							.setBackgroundResource(R.drawable.step_gray_wright);
				} else {
					btnViews[index]
							.setBackgroundResource(R.drawable.step_gray_wrong);
				}
			} else {
				btnViews[index].setBackgroundResource(grayPicId[index]);
			}
		} else {
			btnViews[index].setBackgroundResource(grayPicId[index]);
		}
	}

	// 重新设置按钮的背景
	private void resetView() {
		for (int i = 0; i < 7; i++) {
			btnViews[i].setBackgroundResource(brownPicId[i]);
			if(i > 0 && i < 6) {
				if(isShowAnswer) {
					if(ActivityBiz.arrayValue(choiceAnswerStatus, replyChoiceAnswerStatus,  (i - 1))) {
						btnViews[i].setBackgroundResource(R.drawable.step_finish_wright);
					} else {
						btnViews[i].setBackgroundResource(R.drawable.step_finish_wrong);
					}
				} else {
					if(blStatus[i-1]) {
						btnViews[i].setBackgroundResource(finishPicId[i]);
					}
				}
			}
			if(i == 6 && ActivityBiz.isFinishAll()) {
				btnViews[i].setBackgroundResource(finishPicId[i]);
			}
		}
		if(viewPage.result) {
			btnViews[0].setBackgroundResource(finishPicId[0]);
		}
	}
	
	// 判断选择题答案是否正确
	private void JudgeAnswer() {
		for (int i = 1; i < 6; i++) {
		}
	}

	@Override
	public void onClick(View v) {
		if (v == ivBack) {
			returnHome();
		}
		if(!viewPage.result) {
			return;
		}
		if (v == btnGame) {
			viewPage.result = true;
			viewPage.setCurrentItem(0);
		} else if (v == btn1) {
			viewPage.setCurrentItem(1);
		} else if (v == btn2) {
			viewPage.setCurrentItem(2);
		} else if (v == btn3) {
			viewPage.setCurrentItem(3);
		} else if (v == btn4) {
			viewPage.setCurrentItem(4);
		} else if (v == btn5) {
			viewPage.setCurrentItem(5);
		} else if (v == btnDown) {
			viewPage.setCurrentItem(6);
			if(viewPage.result) {
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			returnHome();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void returnHome() {
		clearMediaPlayer();
		this.finish();
	}

	private void clearMediaPlayer() {
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		clearDrawable();
	}

	private void clearDrawable() {
		ivBack.setBackgroundResource(0);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		getWindow().getDecorView().setKeepScreenOn(false);
	}

}
