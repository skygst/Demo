package com.gst.move.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Handler;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.custom.CircleBitmapDisplayer;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.custom.DefineRoundImageView;
import com.ebodoo.raz.utils.DateUtil;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MyToast;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.basic.Recorder;
import com.gst.move.basic.RecorderMusic;
import com.gst.move.basic.Review;
import com.gst.move.test_demo.MyRecordActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyRecordAdapter extends BaseAdapter {

	private Context mContext;
	private List<Recorder> mListRecorder;
	private boolean mIsDel;
	private LayoutInflater mInflater;
	private int selectedId = -1;
	private ImageLoader mImageLoader;
	private int currentClickIndex = -1;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private int height;
	private HashSet<Integer> data = new HashSet<Integer>();
	private final String TAG = "MyRecordAdapter";

	public MyRecordAdapter(Context context, List<Recorder> listAttention,
							  boolean isDel) {
		this.mContext = context;
		this.mListRecorder = listAttention;
		this.mIsDel = isDel;
		this.mInflater = LayoutInflater.from(context);
		mImageLoader = ImageLoader.getInstance();
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mContext).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
	}

	public void setDelete(boolean mIsDel) {
		this.mIsDel = mIsDel;
	}

	public void setSelectedId(int id) {
		this.selectedId = id;
	}

	@Override
	public int getCount() {
		return mListRecorder.size();
	}

	@Override
	public Object getItem(int position) {
		return mListRecorder.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.my_record_item, null);
			holder.rlLayout = (RelativeLayout) convertView
					.findViewById(R.id.rl_layout);
			holder.rlBottom = (RelativeLayout) convertView
					.findViewById(R.id.rl_bottom);
			holder.rlErrorCorrection = (RelativeLayout) convertView
					.findViewById(R.id.rl_error_correction);
			holder.ivPlay = (ClickImageView) convertView
					.findViewById(R.id.iv_play);
			holder.ivShare = (ClickImageView) convertView
					.findViewById(R.id.iv_share);
			holder.ivComments = (ClickImageView) convertView
					.findViewById(R.id.iv_comments);
			holder.ivCommentsRecord = (ClickImageView) convertView
					.findViewById(R.id.iv_comment_record);

			holder.rlCheckBox = (RelativeLayout) convertView
					.findViewById(R.id.rl_check_box);
			holder.checkBox = (CheckBox) convertView
					.findViewById(R.id.checkbox);

			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tvDuration = (TextView) convertView
					.findViewById(R.id.tv_duration);
			holder.tvDate = (TextView) convertView.findViewById(R.id.tv_date);
			holder.pb1 = (ProgressBar) convertView.findViewById(R.id.pb_yz);
			holder.pb2 = (ProgressBar) convertView.findViewById(R.id.pb_lld);
			holder.pb3 = (ProgressBar) convertView.findViewById(R.id.pb_yg);

			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tvCommentsContent = (TextView) convertView
					.findViewById(R.id.tv_content);
			holder.tvErrorWord = (TextView) convertView
					.findViewById(R.id.tv_error_word);
			holder.ivHead = (ImageView) convertView
					.findViewById(R.id.iv_head);

			holder.ivBookPic = (DefineRoundImageView) convertView
					.findViewById(R.id.iv_book_pic);

			holder.pb1.setMax(100);
			holder.pb2.setMax(100);
			holder.pb3.setMax(100);
			holder.pb1.setIndeterminate(false);
			holder.pb2.setIndeterminate(false);
			holder.pb3.setIndeterminate(false);

			TextPaint tpTitle = holder.tvTitle.getPaint();
			tpTitle.setFakeBoldText(true);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final Recorder recorder = mListRecorder.get(position);
		String name = recorder.getBook_name();
		if (!StringBiz.isEmpty(name)) {
			holder.tvTitle.setText(name);
		} else {
			holder.tvTitle.setText("");
		}
		showPic(recorder.getBook_cover(), holder.ivBookPic);
//		try {
//			DisplayImageOptions options = new DisplayImageOptions.Builder()
//					.resetViewBeforeLoading(true).cacheOnDisk(true)
//					.cacheInMemory(true)
//					.imageScaleType(ImageScaleType.EXACTLY)
//					.bitmapConfig(Bitmap.Config.RGB_565)
//					.considerExifParams(true)
//					.displayer(new SimpleBitmapDisplayer()).build();
//			if (!StringBiz.isEmpty(recorder.getBook_cover())) {
//				mImageLoader.displayImage(recorder.getBook_cover(), holder.ivBookPic,
//						options);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		String length = recorder.getAudio_length();
		String lengthValue = new DateUtil().changeTime(length);
		holder.tvDuration.setText(lengthValue);
		String createTime = recorder.getCreated_at();
		if (!StringBiz.isEmpty(createTime)) {
			holder.tvDate.setText(new DateUtil().dateFormat(createTime));
		} else {
			holder.tvDate.setText("");
		}

		if (mIsDel) {
			holder.checkBox.setVisibility(View.VISIBLE);
		} else {
			holder.checkBox.setVisibility(View.GONE);
		}

		if (selectedId == position) {
			holder.tvTitle.setTextColor(mContext.getResources().getColor(
					R.color.yellow));
			holder.ivPlay.setImageResource(R.drawable.record_stop);
		} else {
			holder.tvTitle.setTextColor(mContext.getResources().getColor(
					R.color.blue3));
			holder.ivPlay.setImageResource(R.drawable.record_play);
		}

		holder.ivPlay.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) { // 播放
				if (currentClickIndex != position) {
					currentClickIndex = position;
					((MyRecordActivity) mContext).adapter
							.setSelectedId(position);
					((MyRecordActivity) mContext).adapter
							.notifyDataSetChanged();
					holder.ivPlay.setImageResource(R.drawable.record_stop);
					threadPlay(mListRecorder.get(position).getUrl(),
							holder.ivPlay, holder.tvTitle, true);
				} else {
					if (((MyRecordActivity) mContext).mMediaPlayer != null) {
						try {
							if (((MyRecordActivity) mContext).mMediaPlayer
									.isPlaying()) {
								((MyRecordActivity) mContext).mMediaPlayer
										.pause();
								holder.ivPlay
										.setImageResource(R.drawable.record_play);
								holder.tvTitle
										.setTextColor(mContext.getResources()
												.getColor(R.color.blue3));
							} else {
								((MyRecordActivity) mContext).mMediaPlayer
										.start();
								holder.ivPlay
										.setImageResource(R.drawable.record_stop);
								holder.tvTitle.setTextColor(mContext
										.getResources()
										.getColor(R.color.yellow));
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		holder.ivShare.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { // 分享
//				CommonSharePreferences.setIfShareHuiben(mContext, true);
//				String name = mListRecorder.get(position).getBook_name();
//				String shareUrl = mListRecorder.get(position).getShare_url();
//				ActivityBiz.shareToWxDialog(mContext, name, shareUrl, false);
			}
		});

		// 显示当前点评状态（求点评、等待点评、正在点评、已点评）
		final String reviewStatus = recorder.getReview_status();
		if (!StringBiz.isEmpty(reviewStatus)) {
			if (reviewStatus.equals("0")) {
				holder.ivComments
						.setBackgroundResource(R.drawable.record_comments_on);
			} else if (reviewStatus.equals("1")) {
				holder.ivComments
						.setBackgroundResource(R.drawable.record_wait_comments);
			} else if (reviewStatus.equals("2")) {
				holder.ivComments
						.setBackgroundResource(R.drawable.record_underway_comment);
			} else if (reviewStatus.equals("3")) {
				holder.ivComments
						.setBackgroundResource(R.drawable.record_already_comments);
			} else {
				holder.ivComments
						.setBackgroundResource(R.drawable.record_comments_on);
			}
			if (reviewStatus.equals("3")) { // 已点评
				holder.rlBottom.setVisibility(View.VISIBLE);
				Review review = recorder.getReview();
				holder.pb1.setProgress(review.getAccuracy() * 10); // 音准
				holder.pb2.setProgress(review.getFluency() * 10); // 流利度
				holder.pb3.setProgress(review.getSense() * 10); // 语感
				String keyWord = review.getKeyword();
				if (!StringBiz.isEmpty(keyWord)) {
					holder.rlErrorCorrection.setVisibility(View.VISIBLE);
					holder.tvErrorWord.setText("单词纠错 : " + keyWord);
				} else {
					holder.rlErrorCorrection.setVisibility(View.GONE);
				}
				holder.tvCommentsContent.setText(review.getText_comment());
				holder.tvName.setText(review.getTeacher_name() + "老师的评价:");
				try {
					DisplayImageOptions options = new DisplayImageOptions.Builder()
							.cacheInMemory(true)
							.cacheOnDisk(true)
							.bitmapConfig(Bitmap.Config.RGB_565)
							.displayer(new CircleBitmapDisplayer())
							.build();
					String picUrl = review.getTeacher_avatar();
					if (!StringBiz.isEmpty(picUrl)) {
						Log.d(TAG, "-------picUrl----" + picUrl);
						mImageLoader.displayImage(picUrl, holder.ivHead, options);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				holder.rlBottom.setVisibility(View.GONE);
			}
		}
		holder.ivComments.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { // 求点评
				// 当前状态（求点评、等待点评、正在点评、已点评）
				if (!StringBiz.isEmpty(reviewStatus)
						&& reviewStatus.equals("1")) {
//					showDefineDialog(recorder.getId(), position);
				} else if (!StringBiz.isEmpty(reviewStatus)
						&& reviewStatus.equals("2")) {
//					new DialogBiz().showDialogOnceButton(mContext, "正在点评",
//							"老师正在点评你的录音，请稍后");
				} else if (!StringBiz.isEmpty(reviewStatus)
						&& reviewStatus.equals("0")) {
					// 判断当前是否需要点评
//					String[] status = new CacheSp().spGetReviewStatus(mContext);
//					String times = status[0];
//					String date = status[1];
//					if (!StringBiz.isEmpty(date)) {
//						int timesValue = Integer.valueOf(times).intValue();
//						if (timesValue > 0) {
//							((MyRecordActivity) mContext).refreshProgressStart("正在提交点评中...");
//							// 提交点评
//							applyReview(recorder.getId(), position);
//						} else if (timesValue == 0) {
//							new DialogBiz().showDefineDialog(mContext, date,
//									"0", false, scaleQPW, scaleQPH);
//						}
//					} else {
//						((Activity) mContext).startActivity(new Intent(
//								mContext, CommentsPurchaseActivity.class));
//					}
				}
			}
		});

		holder.ivCommentsRecord.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) { // 老师的录音
				// 播放老师的录音
				threadPlay(recorder.getReview().getAudio_comment(),
						holder.ivCommentsRecord, holder.tvTitle, false);
			}
		});

		if (mIsDel) {
			if (data.contains(position)) {
				holder.checkBox.setBackgroundResource(R.drawable.check_sel);
			} else {
				holder.checkBox.setBackgroundResource(R.drawable.check_del);
			}

			holder.rlCheckBox.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					selItem(holder.checkBox, position, reviewStatus);
				}
			});
			holder.checkBox.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					// 删除Item
					selItem(holder.checkBox, position, reviewStatus);
				}
			});
		}

		return convertView;
	}

	private void showPic(String picUrl, DefineRoundImageView ivPic) {
		try {
			DisplayImageOptions options = new DisplayImageOptions.Builder()
					.resetViewBeforeLoading(true).cacheOnDisk(true)
					.cacheInMemory(true)
					.imageScaleType(ImageScaleType.EXACTLY)
					.bitmapConfig(Bitmap.Config.RGB_565)
					.considerExifParams(false)
					.displayer(new SimpleBitmapDisplayer()).build();
			Log.d(TAG, "-----------" + picUrl);
			if (!StringBiz.isEmpty(picUrl)) {
				mImageLoader.displayImage(picUrl, ivPic,
						options);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void selItem(CheckBox checkBox, int pos, String reviewStatus) {
		if (!StringBiz.isEmpty(reviewStatus)) {
			if ((reviewStatus.equals("1") || reviewStatus.equals("2"))) {
				new MyToast().showTextToast(mContext,
						"等待评论和正在评论不可以删除哦");
			} else {
				if (data.contains(pos)) {
					data.remove(pos);
					checkBox.setBackgroundResource(R.drawable.check_del);
				} else {
					data.add(pos);
					checkBox.setBackgroundResource(R.drawable.check_sel);
				}
			}
		}
	}

	private void setChangeTitleParams(View view, boolean isLeft, int leftId,
									  int width, int height) {
		RelativeLayout.LayoutParams params = LayoutParameters
				.setViewWidthAndHeightParams2(width, height, 10, leftId,
						isLeft, scaleQPW, scaleQPH);
		view.setLayoutParams(params);
	}

	class ViewHolder {
		ClickImageView ivPlay, ivShare, ivComments, ivCommentsRecord;
		CheckBox checkBox;
		ImageView ivHead;
		DefineRoundImageView ivBookPic;
		TextView tvTitle, tvDuration, tvDate, tvName, tvCommentsContent,
				tvErrorWord;
		RelativeLayout rlLayout, rlBottom, rlErrorCorrection, rlCheckBox;
		ProgressBar pb1, pb2, pb3;
	}

	public void setDelStatus(boolean isDel) {
		mIsDel = isDel;
	}

	private void threadPlay(final String pathUrl, final ImageView ivPlay,
							final TextView tvTitle, final boolean isChangePic) {
		if (!new MyToast().hasInternetConnection(mContext)) {
			new MyToast().showTextToast(mContext, "网络异常，请检查网络是否连接");
			return;
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				playBgMusic(pathUrl, ivPlay, tvTitle, isChangePic);
			}
		}).start();
	}

	private void playBgMusic(String path, final ImageView ivPlay,
							 final TextView tvTitle, boolean isChangePic) {
		((MyRecordActivity) mContext).mMediaPlayer = MediaPlayerBiz
				.playSoundMusic(path,
						((MyRecordActivity) mContext).mMediaPlayer);
		if (isChangePic) {
			((MyRecordActivity) mContext).mMediaPlayer
					.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

						@Override
						public void onCompletion(MediaPlayer arg0) {
							Object[] obj = new Object[2];
							obj[0] = ivPlay;
							obj[1] = tvTitle;
							handler.sendMessage(handler.obtainMessage(0, obj));
						}
					});
		}
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case 0:
					Object[] object = (Object[]) msg.obj;
					ImageView ivPlay = (ImageView) object[0];
					ivPlay.setImageResource(R.drawable.record_play);
					TextView tvTitle = (TextView) object[1];
					tvTitle.setTextColor(mContext.getResources().getColor(
							R.color.blue3));
					break;
				case 2:
					String msgValue = (String) msg.obj;
					new MyToast().showTextToast(mContext, msgValue);
					((MyRecordActivity) mContext).rlDefineProgress
							.setVisibility(View.GONE);
					break;
				case 3:
					@SuppressWarnings("unchecked")
					List<Object> listObj = (List<Object>) msg.obj;
					int pos_1 = (Integer) listObj.get(0);
					Recorder recorder = (Recorder) listObj.get(1);
					((MyRecordActivity) mContext).rlDefineProgress
							.setVisibility(View.GONE);
					mListRecorder.set(pos_1, recorder);
					((MyRecordActivity) mContext).adapter.notifyDataSetChanged();
					break;
				default:
					break;
			}
		};
	};

	// 提交点评
	private void applyReview(final String audioId, final int pos) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					RecorderMusic recorderMusic = new RecorderMusic()
							.getAudioApplyReview(mContext, audioId);
					String msgValue = recorderMusic.getMsg();
					if (recorderMusic != null && StringBiz.isEmpty(msgValue)
							&& recorderMusic.getList() != null
							&& recorderMusic.getList().size() > 0) {
						List<Object> listObj = new ArrayList<Object>();
						listObj.add(pos);
						listObj.add(recorderMusic.getList().get(0));
						handler.sendMessage(handler.obtainMessage(3, listObj));
					} else {
						handler.sendMessage(handler.obtainMessage(2, msgValue));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 取消点评
	private void applyCancel(final String audioId, final int pos) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					RecorderMusic recorderMusic = new RecorderMusic()
							.getAudioApplyCancel(mContext, audioId);
					if (recorderMusic != null
							&& StringBiz.isEmpty(recorderMusic.getMsg())
							&& recorderMusic.getList() != null
							&& recorderMusic.getList().size() > 0) {
						List<Object> listObj = new ArrayList<Object>();
						listObj.add(pos);
						listObj.add(recorderMusic.getList().get(0));
						handler.sendMessage(handler.obtainMessage(3, listObj));
					} else {
						handler.sendMessage(handler.obtainMessage(2,
								recorderMusic.getMsg()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 显示自定义Dialog
//	private void showDefineDialog(final String audioId, final int pos) {
//		final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
//		dialog.show();
//		Window window = dialog.getWindow();
//		// *** 主要就是在这里实现这种效果的.
//		window.setContentView(R.layout.define_dialog_2);
//
//		RelativeLayout rlCancel = (RelativeLayout) window
//				.findViewById(R.id.rl_left);
//		RelativeLayout rlBoundPhone = (RelativeLayout) window
//				.findViewById(R.id.rl_right);
//		TextView tvTitle = (TextView) window.findViewById(R.id.tv_dialog_title);
//		TextView tvContent = (TextView) window
//				.findViewById(R.id.tv_dialog_content);
//		TextView tvLeft = (TextView) window.findViewById(R.id.tv_left);
//		TextView tvRight = (TextView) window.findViewById(R.id.tv_right);
//
//		tvTitle.setText("撤销点评");
//		tvTitle.setVisibility(View.VISIBLE);
//		tvContent.setText("您的录音正在等待老师点评，是否要撤销？");
//		tvContent.setTextSize(16.0f);
//		tvLeft.setText("撤销");
//		tvRight.setText("不撤销");
//		rlCancel.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//				((MyRecordActivity) mContext).refreshProgressStart("正在撤销点评中...");
//				applyCancel(audioId, pos);
//			}
//		});
//
//		rlBoundPhone.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});
//	}

	public List<Integer> getPosition() {
		List<Integer> list = new ArrayList<Integer>();
		for (Integer i : data) {
			list.add(i);
		}
		return list;
	}

	public void clearData() {
		data.clear();
	}

}

