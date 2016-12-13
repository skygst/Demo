package com.gst.move.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils.TruncateAt;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.base.WearEarMusic;
import com.ebodoo.raz.biz.ActivityBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.data.FixedPositionRevision;
import com.ebodoo.raz.service.NetBroadcastReceiver;
import com.ebodoo.raz.service.NetBroadcastReceiver.netEventHandler;
import com.ebodoo.raz.service.NetUtil;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.MyToast;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.R.drawable;
import com.gst.move.R.id;
import com.gst.move.R.layout;

/**
 * 磨耳朵
 * 
 * @author
 * 
 */
public class WearEarActivity extends BaseActivity implements OnClickListener, netEventHandler {

	private ImageView ivBack, ivPan, ivPlay, ivNext, ivCollection, 
		ivPlayState, ivDel, ivPrimaryClick, ivAdvanceClick, ivCollectionClick;
	private TextView tvTitle;
	private RelativeLayout rlCollectionBg, rlList;
	private ListView mListView;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private MediaPlayer mMediaPlayer = null;
	private List<List<WearEarMusic>> musicList;
	private List<WearEarMusic> wearPrimaryEarList; // 初级列表
	private List<WearEarMusic> wearAdvancedEarList; // 高级列表
	private List<WearEarMusic> wearCollectionEarList; // 收藏列表
	private List<WearEarMusic> wearPlayCurrentList; // 当前播放列表
	private List<WearEarMusic> wearShowCurrentList; // 当前显示列表
	private Integer[] randomNumber;
	private int pos = 0;
	private int currentPos = 0;
	private NetBroadcastReceiver receiver;
	private Context mContext;
	private WearAdapter adapter;
	private int status = 1; // 1:循环播放  2:单一播放  3:随机播放
	private int currentGrade = 1; // 1:初级  2:高级  3:收藏  
	private boolean isDel = false;
	private boolean isLogin = false;
	private boolean isFirstEnter = true;
	private List<String> collectionId;
	private int currentPlayStatus = 1; // 1:初级  2:高级  3:收藏  
	private String currentPlayId = "", currentTitle, currentPath;
	private boolean delIsCurrent = false;
	private boolean isPlay = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wear_ear);

		init();
		registerReceiver();
		setView();
		threadData();
	}

	private void init() {
		mContext = WearEarActivity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		musicList = new ArrayList<List<WearEarMusic>>();
		wearPrimaryEarList = new ArrayList<WearEarMusic>();
		wearAdvancedEarList = new ArrayList<WearEarMusic>();
		wearCollectionEarList = new ArrayList<WearEarMusic>();
		wearShowCurrentList = new ArrayList<WearEarMusic>();
		wearPlayCurrentList = new ArrayList<WearEarMusic>();
		collectionId = new ArrayList<String>();
//		isLogin = User.isLogin(mContext);
		isLogin = false;
		isFirstEnter = true;
	}
	
	private void registerReceiver() {
		NetBroadcastReceiver.mListeners.add(this);
		IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");  
		receiver = new NetBroadcastReceiver();  
		registerReceiver(receiver, filter);
	}

	private void setView() {
		ivBack = (ImageView) findViewById(R.id.iv_back);
		ivPan = (ImageView) findViewById(R.id.iv_pan);
		ivPlay = (ImageView) findViewById(R.id.iv_play);
		ivNext = (ImageView) findViewById(R.id.iv_next);
		ivPrimaryClick = (ImageView) findViewById(R.id.iv_primary_click);
		ivAdvanceClick = (ImageView) findViewById(R.id.iv_advanced_click);
		ivCollectionClick = (ImageView) findViewById(R.id.iv_collection_click);
		ivCollection = (ImageView) findViewById(R.id.iv_collection);
		ivPlayState = (ImageView) findViewById(R.id.iv_play_state);
		ivDel = (ImageView) findViewById(R.id.iv_del);
		
		rlCollectionBg = (RelativeLayout) findViewById(R.id.rl_collection_bg);
		rlList = (RelativeLayout) findViewById(R.id.rl_list);
		mListView = (ListView) findViewById(R.id.list_view);
		
		tvTitle = (TextView) findViewById(R.id.tv_title);
		setViewLocation();
		ivBack.setOnClickListener(this);
		ivDel.setOnClickListener(this);
		ivPlay.setOnClickListener(this);
		ivNext.setOnClickListener(this);
		ivPlayState.setOnClickListener(this);
		ivCollection.setOnClickListener(this);
		ivPrimaryClick.setOnClickListener(this);
		ivAdvanceClick.setOnClickListener(this);
		ivCollectionClick.setOnClickListener(this);
		ivrotate();
		setTitleParams();
		
		// 获取电话服务
		manager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
				// 手动注册对PhoneStateListener中的listen_call_state状态进行监听
		manager.listen(new MyPhoneStateListener(),
						PhoneStateListener.LISTEN_CALL_STATE);
		listOnItem();
	}
	
	private void listOnItem() {
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(!new MyToast().hasInternetConnection(mContext)) {
					new MyToast().showTextToast(mContext, "网络异常，请先检查网络是否连接");
					return;
				}
				ivPlay.setImageResource(R.drawable.wear_ear_stop);
				if(!isDel) {
					pos = arg2;
					currentPos = pos;
					if(currentGrade == 1) {
						clickItem(wearPrimaryEarList);
					} else if(currentGrade == 2) {
						clickItem(wearAdvancedEarList);
					} else if(currentGrade == 3) {
						clickItem(wearCollectionEarList);
					}
				}
			}
		});
	}
	
	private void clickItem(List<WearEarMusic> wearList) {
		if(wearList != null && wearList.size() > 0) {
			currentPlayStatus = currentGrade;
			wearPlayCurrentList.clear();
			wearPlayCurrentList.addAll(wearList);
			showPlayStatus(true);
		}
	}
	
	// 图片旋转
	private void ivrotate(){
//		Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.iv_rotate);  
//		LinearInterpolator lin = new LinearInterpolator();  
//		operatingAnim.setInterpolator(lin);  
//		ivPan.startAnimation(operatingAnim);
	}

	private void setViewLocation() {
		setViewPosition(ivPan, 0);
		setViewPosition(ivCollection, 1);
		setViewPosition(ivPlay, 2);
		setViewPosition(ivNext, 3);
		setViewPosition(ivPlayState, 4);
		setViewPosition(rlCollectionBg, 5);
		setViewPosition(ivDel, 6);
		setViewPosition(rlList, 7);
		setViewPosition(ivPrimaryClick, 8);
		setViewPosition(ivAdvanceClick, 9);
		setViewPosition(ivCollectionClick, 10);
		
		biz.setViewPosition(ivBack, 0, FixedPosition.common_position, scaleQPW,
				scaleQPH, 0, 0, 1.0f);
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionRevision.wear_ear_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if (v == ivBack) {
			clearMedia();
		}
		if (!new MyToast().hasInternetConnection(mContext)) {
			new MyToast().showTextToast(mContext, "网络异常，请先检查网络是否连接");
			return;
		}
		if (v == ivPlay) {
			if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
				mMediaPlayer.pause();
				ivPlay.setImageResource(R.drawable.wear_ear_play);
			} else {
				if (mMediaPlayer != null) {
					mMediaPlayer.start();
					ivPlay.setImageResource(R.drawable.wear_ear_stop);
				}
			}
		} else if (v == ivNext) {
			startPlay(true);
			ivPlay.setImageResource(R.drawable.wear_ear_stop);
		} else if (v == ivCollection) { // 收藏
			if (!isLogin) {
				new MyToast().showTextToast(mContext, "请先登录");
				return;
			}
			// 判断当前是收藏还是取消收藏
			boolean isAdd = true;
			ConstantState currentConstantState = ivCollection.getBackground()
					.getConstantState();
			ConstantState resourceConstantState = getResources().getDrawable(
					R.drawable.wear_collection_del).getConstantState();
			if (currentConstantState.equals(resourceConstantState)) {
				// 当image1的src为R.drawable.A时，设置image1的src为R.drawable.B
				if (currentPlayStatus == 3) {
					delIsCurrent = true;
				}
				isAdd = true;
			} else {
				isAdd = false;
			}
			// 收藏状态---收藏、取消收藏
			if (!StringBiz.isEmpty(currentPlayId)) {
				collection(isAdd, true, currentPlayId);
			}
		} else if (v == ivPlayState) { // 播放状态
			status++;
			if (status > 3)
				status = 1;
			showPlayStatus(false);
		} else if (v == ivPrimaryClick) { // 初级
			if (currentGrade == 1) {
				return;
			}
			isDel = false;
			ivDel.setVisibility(View.GONE);
			currentGrade = 1;
			threadProcessData();
			rlCollectionBg.setBackgroundResource(R.drawable.wear_primary_bg);
		} else if (v == ivAdvanceClick) { // 高级
			if (currentGrade == 2) {
				return;
			}
			isDel = false;
			ivDel.setVisibility(View.GONE);
			currentGrade = 2;
			threadProcessData();
			rlCollectionBg.setBackgroundResource(R.drawable.wear_advance_bg);
		} else if (v == ivCollectionClick) { // 收藏
			// 获得我的收藏列表
			if (currentGrade == 3) {
				return;
			}
			isDel = false;
			ivDel.setVisibility(View.VISIBLE);
			currentGrade = 3;
			rlCollectionBg.setBackgroundResource(R.drawable.wear_collection_bg);
			// 切换我的收藏列表
			handler.sendMessage(handler.obtainMessage(1, wearCollectionEarList));
		} else if (v == ivDel) { // 删除收藏列表数据
			isDel = !isDel;
			if (isDel) {
				adapter = new WearAdapter(mContext, wearShowCurrentList, isDel,
						-1);
			} else {
				if (currentPlayStatus == 3) {
					int collectionIdIndex = getCollectionId();
					if (collectionIdIndex != -1) {
						pos = currentPos = collectionIdIndex;
						adapter = new WearAdapter(mContext,
								wearShowCurrentList, isDel, collectionIdIndex);
					} else {
						//  判断之前播放的位置
						adapter = new WearAdapter(mContext, wearShowCurrentList, isDel, -1);
					}
				} else {
					adapter = new WearAdapter(mContext, wearShowCurrentList,
							isDel, -1);
				}
			}
			mListView.setAdapter(adapter);
		}
	}
	
	private void showPlayStatus(boolean isPlay) {
		if(status == 1) { // 循环
			ivPlayState.setImageResource(R.drawable.wear_state_cycle);
			if(isPlay) {
				playWearMusic(pos);
			}
		} else if(status == 2) { // 单一
			ivPlayState.setImageResource(R.drawable.wear_state_only_once);
			if(isPlay) {
				playWearMusic(pos);
			}
		} else if(status == 3) { // 随机
			ivPlayState.setImageResource(R.drawable.wear_state_random);
			if(isPlay) {
				playWearMusic(pos);
			}
			randomNumber = BaseCommon.getRandomId(wearPlayCurrentList.size());
			pos = -1;
		}
	}
 	
	private void playWearMusic(int pos) {
		if(pos < 0)
			pos = 0;
		
		//增加统计
//		if(currentGrade == 1) {
//			Statistics.CommonClickCount(mContext, "Listen", "Basic");
//		} else if(currentGrade == 2) {
//			Statistics.CommonClickCount(mContext, "Listen", "Advance");
//		} else if(currentGrade == 3) {
//			Statistics.CommonClickCount(mContext, "Listen", "Favorite");
//		}
		
		showView(wearPlayCurrentList.get(pos).getId(), wearPlayCurrentList.get(pos).getName(),
				wearPlayCurrentList.get(pos).getUrl());
		currentPlayId = wearPlayCurrentList.get(pos).getId();
		currentTitle = wearPlayCurrentList.get(pos).getName();
		currentPath = wearPlayCurrentList.get(pos).getUrl();
		if(currentGrade == currentPlayStatus) {
			adapter = new WearAdapter(mContext, wearShowCurrentList, isDel, pos);
			mListView.setAdapter(adapter);
			mListView.setSelection(pos);
		}
	}
	
	private void showView(String currentId, String currentTitle, String currentUrl) {
		showCollectionStatus(false, currentId, false);
		tvTitle.setText(currentTitle);
		playBgMusic(currentUrl);
	}
	
	// 获得初级、高级列表
	private void threadData() {
		if(!new MyToast().hasInternetConnection(mContext)) {
			new MyToast().showTextToast(mContext, "网络异常，请先检查网络是否连接");
			return;
		}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				musicList = WearEarMusic.earMusicData();
				handler.sendMessage(handler.obtainMessage(0));
			}
		}).start();
		if (isLogin) {
			threadCollectionData();
		}
	}
	
	// 获得收藏列表
	private void threadCollectionData() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				wearCollectionEarList.clear();
//				Object[] obj = WearEarMusic.collectionData(mContext);
//				handler.sendMessage(handler.obtainMessage(2, obj));
			}
		}).start();
	}
	
	// 添加收藏 --- 取消收藏
	private void collection(final boolean isAdd, final boolean isCurrentPos, final String id) {
		if(!isLogin) {
			new MyToast().showTextToast(mContext, "请先登录");
			return;
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				wearCollectionEarList.clear();
//				wearCollectionEarList = WearEarMusic.collectionData(mContext, isAdd, id);
//				Object[] obj = WearEarMusic.collectionData(mContext, isAdd, id);
//				Object[] objCol = new Object[4];
//				objCol[0] = obj[0];
//				objCol[1] = obj[1];
//				objCol[2] = obj[2];
//				objCol[3] = isCurrentPos;
//				handler.sendMessage(handler.obtainMessage(3, objCol));
			}
		}).start();
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				if(musicList != null && musicList.size() > 0) {
					threadProcessData();
				}
				break;
			case 1:
				@SuppressWarnings("unchecked")
				List<WearEarMusic> wearEarList = (List<WearEarMusic>) msg.obj;
				if (wearEarList != null && wearEarList.size() > 0) {
					if(isFirstEnter) {
						isFirstEnter = false;
						wearPlayCurrentList.clear();
						wearPlayCurrentList.addAll(wearEarList);
						tvTitle.setText(wearEarList.get(pos).getName());
						playBgMusic(wearEarList.get(pos).getUrl());
						currentPlayId = wearEarList.get(pos).getId();
						currentTitle = wearEarList.get(pos).getName();
						currentPath = wearEarList.get(pos).getUrl();
						showCollectionStatus(false, wearPlayCurrentList.get(0).getId(), false);
					}
					wearShowCurrentList.clear();
					wearShowCurrentList.addAll(wearEarList);
					if(status == 3) {
						fillAdapter();
					} else {
						fillAdapter();
					}
				} else {
					wearShowCurrentList.clear();
					fillAdapter();
				}
				break;
			case 2:
				Object[] objCol = (Object[]) msg.obj;
				if(objCol != null && objCol.length > 0) {
					String errorCodeCol = (String) objCol[0];
					if(!StringBiz.isEmpty(errorCodeCol)) {
						if(errorCodeCol.equals("401")) { // token过期 --- 去登录
							isPlay = true;
							if (mMediaPlayer != null) {
								if(mMediaPlayer.isPlaying()) {
									isPlay = true;
								}
								mMediaPlayer.pause();
							}
//							ActivityBiz.clearData(mContext);
//							startActivity(new Intent(mContext, SettingsActivity.class)
//							.putExtra("index", 3).putExtra("basics", false));
						} else {
							String errorMsg = (String) objCol[1];
							new MyToast().showTextToast(mContext, errorMsg);
						}
					} else {
						wearCollectionEarList = (List<WearEarMusic>) objCol[2];
						getCollectionId();
					}
				}
				break;
			case 3:
				Object[] objValue = (Object[]) msg.obj;
				if(objValue != null && objValue.length > 0) {
					String errorCode = (String) objValue[0];
					if(!StringBiz.isEmpty(errorCode)) {
						if(errorCode.equals("401")) { // token过期 --- 去登录
							isPlay = true;
							if (mMediaPlayer != null) {
								if(mMediaPlayer.isPlaying()) {
									isPlay = true;
								}
								mMediaPlayer.pause();
							}
//							ActivityBiz.clearData(mContext);
//							startActivity(new Intent(mContext, SettingsActivity.class)
//							.putExtra("index", 3).putExtra("basics", false));
						} else {
							String errorMsg = (String) objValue[1];
							new MyToast().showTextToast(mContext, errorMsg);
						}
					} else {
						wearCollectionEarList = (List<WearEarMusic>) objValue[2];
						String id = currentPlayId;
						int collectionIdIndex = getCollectionId();
						boolean isCurrentPos = (Boolean) objValue[3];
						if(currentGrade == 3) {
							wearShowCurrentList.clear();
							wearShowCurrentList.addAll(wearCollectionEarList);
							if(currentPlayStatus == 3) {
								wearPlayCurrentList.clear();
								wearPlayCurrentList.addAll(wearCollectionEarList);
							}
							if(delIsCurrent) {
								currentPos = collectionIdIndex;
								adapter = new WearAdapter(mContext, wearShowCurrentList, isDel, currentPos);
								mListView.setAdapter(adapter);
							} else {
								if(isDel) {
									if(adapter != null)
										adapter.notifyDataSetChanged();
								} else {
									adapter = new WearAdapter(mContext, wearShowCurrentList, isDel, -1);
									mListView.setAdapter(adapter);
								}
							}
						}
						showCollectionStatus(isCurrentPos, id, delIsCurrent);
						if(delIsCurrent)
							delIsCurrent = false;
					}
				}
				break;
			default:
				break;
			}
		}
		
	};
	
	private void fillAdapter() {
		if(currentGrade == currentPlayStatus) {
			if(currentPlayStatus == 3) {
				if(collectionId.contains(currentPlayId)) {
					adapter = new WearAdapter(mContext, wearShowCurrentList, isDel, currentPos);
				} else {
					adapter = new WearAdapter(mContext, wearShowCurrentList, isDel, -1);
				}
			} else {
				adapter = new WearAdapter(mContext, wearShowCurrentList, isDel, currentPos);
			}
		} else {
			adapter = new WearAdapter(mContext, wearShowCurrentList, isDel, -1);
		}
		mListView.setAdapter(adapter);
	}
	
	private int getCollectionId() {
		int index = -1;
		collectionId.clear();
		if(wearCollectionEarList != null && wearCollectionEarList.size() > 0) {
			for(int i=0; i<wearCollectionEarList.size(); i++) {
				String id = wearCollectionEarList.get(i).getId();
				collectionId.add(id);
				if(!StringBiz.isEmpty(id) && id.equals(currentPlayId)) {
					index = i;
				}
			}
		}
		return index;
	}
	
	// 收藏状态
	private void showCollectionStatus(boolean currentStatus, String id, boolean isAdd) {
		if(collectionId.contains(id)) {
			Drawable drawable = getResources().getDrawable(R.drawable.wear_collection_sel); 
			ivCollection.setBackground(drawable);
		} else {
			Drawable drawable = getResources().getDrawable(R.drawable.wear_collection_del); 
			ivCollection.setBackground(drawable);
		}
		if (currentGrade == 3) {
			if (currentPlayStatus == 3) {
				if (currentStatus) {
					if(!isAdd) {
						if (pos > 0)
							pos = pos - 1;
					}
					currentPos = pos;
				}
			}
		}
	}
	
	private void threadProcessData() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				if(musicList != null && musicList.size() > 1) {
					if(currentGrade == 2) {
						if(wearAdvancedEarList == null || wearAdvancedEarList.size() == 0) {
							wearAdvancedEarList.addAll(musicList.get(1));
						}
						handler.sendMessage(handler.obtainMessage(1, wearAdvancedEarList));
					} else if(currentGrade == 1){
						if (wearPrimaryEarList == null || wearPrimaryEarList.size() == 0) {
							wearPrimaryEarList.addAll(musicList.get(0));
						}
						handler.sendMessage(handler.obtainMessage(1, wearPrimaryEarList));
					}
				}
			}
		}).start();
	}

	public MediaPlayer playBgMusic(String path) {
		try {
			if(!new MyToast().hasInternetConnection(mContext)) {
				new MyToast().showTextToast(mContext, "网络异常，请检查网络是否连接");
				return null;
			}
			if (mMediaPlayer != null) { // 一定要清空播放器资源
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
			try {
				mMediaPlayer = new MediaPlayer();
				/* 重置MediaPlayer */
				mMediaPlayer.reset();
				/* 设置要播放的文件的路径 */
				mMediaPlayer.setDataSource(path);
				/* 准备播放 */
				mMediaPlayer.setLooping(false);
				mMediaPlayer.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/* 开始播放 */
			mMediaPlayer.start();
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer arg0) {
					startPlay(false);
				}
			});
//			if(isPlay) {
//				if(mMediaPlayer != null) {
//					mMediaPlayer.pause();
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mMediaPlayer;
	}

	private void startPlay(boolean isNext) {
		try {
			int size = 0;
			size = wearPlayCurrentList.size();
			if(status != 2 || isNext) {
				if(status == 3) {
					pos++;
					if (pos >= size) {
						pos = 0;
					}
					currentPos = pos;
					currentPos = randomNumber[pos];
				} else {
					currentPos++;
					if (currentPos >= size) {
						currentPos = 0;
					}
					pos = currentPos;
				}
				playWearMusic(currentPos);
			} else {
				showView(currentPlayId, currentTitle, currentPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			System.out.println("-------- onKeyDown -----------");
			clearMedia();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	private void clearMedia() {
		try {
			if (mMediaPlayer != null) {
				mMediaPlayer.stop();
				mMediaPlayer.release();
				mMediaPlayer = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finish();
	}
	
	private void setTitleParams() {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		int intentTopValue = Float.valueOf(
				(25 * (width/1280.0f))).intValue();
		params.topMargin = intentTopValue;
		int leftValue = Float.valueOf(
				(100 * (width/1280.0f))).intValue() + 10;
		params.leftMargin = leftValue;
		int rightValue = Float.valueOf(
				(353 * (width/1280.0f))).intValue() + 20;
		params.rightMargin = rightValue;
		tvTitle.setLayoutParams(params);
		tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
		tvTitle.setSingleLine(true);
		tvTitle.setEllipsize(TruncateAt.END);
		tvTitle.setTextColor(Color.WHITE);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(!new MyToast().hasInternetConnection(mContext)) {
			new MyToast().showTextToast(mContext, "网络异常，请先检查网络是否连接");
			return;
		}
		if (null != mMediaPlayer && !mMediaPlayer.isPlaying()) {
			if(isPlay) {
				isPlay = false;
				mMediaPlayer.start();
			}
		} else {
			isPlay = false;
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (mMediaPlayer != null) {
			if(mMediaPlayer.isPlaying()) {
				isPlay = true;
			}
//			mMediaPlayer.pause();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		NetBroadcastReceiver.mListeners.clear();
		this.unregisterReceiver(receiver);
		System.out.println("-------- onDestroy -----------");
		clearMedia();
	}

	@Override
	public void onNetChange() {
		if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE) {
			new MyToast().showTextToast(mContext, "网络异常，请检查网络是否连接");
			if (null != mMediaPlayer) {
				ivPlay.setImageResource(R.drawable.wear_ear_play);
				mMediaPlayer.pause();
				ivPan.clearAnimation();
			}
		} else {
			if (null != mMediaPlayer) {
				mMediaPlayer.start();
				ivrotate();
				ivPlay.setImageResource(R.drawable.wear_ear_stop);
			}
		}
	}
	
	private TelephonyManager manager;
	
	class MyPhoneStateListener extends PhoneStateListener {
		private boolean isCall = false;

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:// 挂断---没有电话
				try {
					if (isCall) {
						if(mMediaPlayer != null) {
							mMediaPlayer.start();
						}
						isCall = false;
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING:// 来电
				if (mMediaPlayer != null) {
					if (mMediaPlayer.isPlaying()) {
						isCall = true;
					}
					mMediaPlayer.pause();
				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:// 接听
				try {
					if (mMediaPlayer.isPlaying()) {
						isCall = true;
					}
					mMediaPlayer.pause();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}
	}
	
	public class WearAdapter extends BaseAdapter {

		private List<WearEarMusic> mListWear;
		private Context mContext;
		private LayoutInflater mInflater;
		private boolean mIsDel;
		private int mIndex;

		public WearAdapter(Context context, List<WearEarMusic> listWear, boolean isDel, int index) {
			this.mContext = context;
			this.mListWear = listWear;
			this.mIsDel = isDel;
			mInflater = LayoutInflater.from(mContext);
			mIndex = index;
		}

		@Override
		public int getCount() {
			return mListWear != null ? mListWear.size() : 0;
		}

		@Override
		public Object getItem(int arg0) {
			return mListWear.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			if (null == convertView) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.wear_item, null);
				holder.rlWearItem = (RelativeLayout) convertView.findViewById(R.id.rl_wear_item);
				holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
				holder.ivDel = (ImageView) convertView.findViewById(R.id.iv_del);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			
			final WearEarMusic wearEarMusic = mListWear.get(position);
			holder.tvTitle.setText(wearEarMusic.getName());
			if(mIsDel) {
				holder.ivDel.setVisibility(View.VISIBLE);
			} else {
				holder.ivDel.setVisibility(View.GONE);
			}
			holder.ivDel.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) { // 删除收藏
					String id = wearEarMusic.getId();
					if(!StringBiz.isEmpty(id)) {
						// currentPos判断当前是否播放收藏列表
						if(currentGrade == 3 && currentPlayStatus == 3) {
							if(status == 1) { // 循环
								if(currentPos < position) {
									collection(false, false, id);
								} else {
									currentPos = pos = position;
									collection(false, true, id);
								}
							} else if(status == 2){ // 单一
								if(randomNumber[pos] == position) {
									// 暂停播放
									collection(false, false, id);
									ivPlay.setImageResource(R.drawable.wear_ear_play);
								} else {
									collection(false, false, id);
								}
							} else if(status == 3) { // 随机
								if(currentPos < position) {
									collection(false, false, id);
								} else {
									collection(false, true, id);
								}
							}
						} else {
							collection(false, false, id);
						}
					}
				}
			});
			if(mIndex != position) {
				holder.tvTitle.setTextColor(Color.parseColor("#d37d31"));
			} else {
				holder.tvTitle.setTextColor(Color.RED);
			}
			return convertView;
		}
		
		class ViewHolder {
			RelativeLayout rlWearItem;
			TextView tvTitle;
			ImageView ivDel;
		}
	}
	
}
