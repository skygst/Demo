package com.testfragandvp.fragment.levelb;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.data.FixedPositionAfrica;
import com.example.location.biz.ActivityBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.custom.XButton;
import com.gst.move.test.LevelBActivity;
import com.gst.move.utils.BaseCommon;

public class FragmentLevelBChoiceOne extends Fragment implements OnClickListener {

	private Context mContext;
	private View view, viewLines;
	private TextView tvIssure;
	private ListView listView;
	private XButton btnRead;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private VideoBiz biz;
	private List<String> listString;
	public static AnswerAdapter adapter;
	private int[] optionsId = new int[] { R.drawable.choose1,
			R.drawable.choose2, R.drawable.choose3, R.drawable.choose4 };
	private MediaPlayer mMediaPlayerSound = null; // 播放音效
	private String[] choiceSoundContent;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		init();
		view = inflater.inflate(R.layout.choice, container, false);
		setView();
		return view;
	}

	private void init() {
		biz = new VideoBiz();
		listString = new ArrayList<String>();
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
			// 相当于Fragment的onResume
			if (LevelBActivity.isFirstEnter) {
				LevelBActivity.isFirstEnter = false;
				// 提示语
				playSoundMusic(BaseCommon.pathLevelBGame + "question_prologue.mp3");
			}
		} else {
			// 相当于Fragment的onPause
			clearMediaPlayer();
		}
	}

	private void setView() {
		viewLines = (View) view.findViewById(R.id.view_line);
		tvIssure = (TextView) view.findViewById(R.id.tv_issue);
		listView = (ListView) view.findViewById(R.id.list_view);
		btnRead = (XButton) view.findViewById(R.id.btn_read);
		String issureContent = LevelBActivity.issureContent[0];
		tvIssure.setText(issureContent);
		System.out.println("new LevelBActivity().answerContent[0] :"
				+ LevelBActivity.answerContent[0]);
		String[] choiceContent = LevelBActivity.answerContent[0].split(";");
		choiceSoundContent = LevelBActivity.choiceSoundContent[0].split(";");
		if (choiceContent != null && choiceContent.length > 0) {
			for (int i = 0; i < choiceContent.length; i++) {
				listString.add(choiceContent[i]);
			}
		}
		adapter = new AnswerAdapter(mContext, listString);
		listView.setAdapter(adapter);
		setViewPosition(viewLines, 0);
		btnRead.setOnClickListener(this);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i,
				FixedPositionAfrica.multiple_choice_position, scaleQPW,
				scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == btnRead) {
			// 读音
			if (choiceSoundContent != null && choiceSoundContent.length > 0) {
				playSoundMusic(BaseCommon.pathLevelBGame + choiceSoundContent[0]);
			}
		}
	}

	public class AnswerAdapter extends BaseAdapter {

		private List<String> mListContent;
		private Context mContext;
		private LayoutInflater mInflater;
		private int num = -1;

		private int[] selOptionsId = new int[] { R.drawable.sel_choose1,
				R.drawable.sel_choose2, R.drawable.sel_choose3,
				R.drawable.sel_choose4 };
		private Button currentBtn;

		public AnswerAdapter(Context context, List<String> listContent) {
			this.mContext = context;
			this.mListContent = listContent;
			mInflater = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			return mListContent != null ? mListContent.size() : 0;
		}

		@Override
		public Object getItem(int arg0) {
			return mListContent.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final ViewHolder holder;
			if (null == convertView) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.answer_item, null);
				holder.rlLayout = (RelativeLayout) convertView
						.findViewById(R.id.rl_layout);
				holder.btnRead = (XButton) convertView
						.findViewById(R.id.btn_read);
				holder.btnOptions = (Button) convertView
						.findViewById(R.id.btn_options);
				holder.tvContent = (TextView) convertView
						.findViewById(R.id.tv_content);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (position < optionsId.length) {
				if (position == num) {
					holder.btnOptions
							.setBackgroundResource(selOptionsId[position]);
				} else {
					holder.btnOptions
							.setBackgroundResource(optionsId[position]);
				}
			}
			currentBtn = holder.btnOptions;
			String content = mListContent.get(position);
			holder.tvContent.setText(content);
			holder.btnOptions.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					num = position;
					handleImageView((Button) v, position);
					holder.rlLayout.performClick();
					ActivityBiz.changeChoiceLevelBStatus(0, position);
				}
			});

			holder.btnRead.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					if (position < (choiceSoundContent.length - 1)) {
						playSoundMusic(BaseCommon.pathLevelBGame
								+ choiceSoundContent[position + 1]);
					}
				}
			});

			return convertView;
		}

		/**
		 * 给点击选中的ImageView加边框，并将之前的ImageView边框清除
		 * 
		 * @param imageView
		 *            要添加边框的ImageView
		 */
		public void handleImageView(Button btn, int pos) {
			currentBtn.setBackgroundResource(0);
			btn.setBackgroundResource(selOptionsId[pos]);
			currentBtn = btn;
			// 可能存在内存问题， 不能够及时释放
			FragmentLevelBChoiceOne.adapter.notifyDataSetChanged();
		}

		class ViewHolder {
			RelativeLayout rlLayout;
			Button btnRead, btnOptions;
			TextView tvContent;
		}

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
				mMediaPlayerSound.stop();
				mMediaPlayerSound.release();
				mMediaPlayerSound = null;
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