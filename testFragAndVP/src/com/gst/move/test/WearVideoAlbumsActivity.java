package com.gst.move.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.base.VideoBase;
import com.ebodoo.raz.base.Volumes;
import com.ebodoo.raz.custom.MyGridView;
import com.example.location.biz.StringBiz;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;
import com.gst.move.adapter.WearVideoAlbumsAdapter;
import com.gst.move.utils.CacheSp;

/**
 *  专辑列表
 * @author 
 *
 */
public class WearVideoAlbumsActivity extends BaseActivity implements OnClickListener {
	
	private Context mContext;
	private ScrollView slView;
	private RelativeLayout rlTitleBg, rlAlbums1, rlAlbums2, rlAlbums3, rlAlbums4, rlAlbums5, rlAlbums6;
	private TextView tvAlbums1, tvAlbums2, tvAlbums3, tvAlbums4, tvAlbums5, tvAlbums6;
	private View vPoint1, vPoint2, vPoint3, vPoint4, vPoint5, vPoint6;
	private RelativeLayout[] rlAlbums;
	private TextView[] tvAlbums;
	private View[] vPoint;
	private TextView tvTitle;
	private ImageView ivBack, ivHistory;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private int previousOne = 1;
	private MyGridView gridView;
	private WearVideoAlbumsAdapter adapter;
	private List<VideoBase> mListVideo;
	private List<Volumes> mListVolumes;
	private List<Volumes> mListCommon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wear_video_album);
		
		init();
		setView();
		new MyAsyncTask().execute();
	}
	
	private void init() {
		mContext = WearVideoAlbumsActivity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		mListVideo = new ArrayList<VideoBase>();
		mListVolumes = new ArrayList<Volumes>();
		mListCommon = new ArrayList<Volumes>();
	}

	private void setView() {
		slView = (ScrollView) findViewById(R.id.sl_view);
		rlTitleBg = (RelativeLayout) findViewById(R.id.rl_title_bg);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		TextPaint tp = tvTitle.getPaint();
		tp.setFakeBoldText(true); 
		ivBack = (ImageView) findViewById(R.id.iv_back);
		ivHistory = (ImageView) findViewById(R.id.iv_history);
		gridView = (MyGridView) findViewById(R.id.myGridView);
		
		rlAlbums1 = (RelativeLayout) findViewById(R.id.rl_albums_1);
		rlAlbums2 = (RelativeLayout) findViewById(R.id.rl_albums_2);
		rlAlbums3 = (RelativeLayout) findViewById(R.id.rl_albums_3);
		rlAlbums4 = (RelativeLayout) findViewById(R.id.rl_albums_4);
		rlAlbums5 = (RelativeLayout) findViewById(R.id.rl_albums_5);
		rlAlbums6 = (RelativeLayout) findViewById(R.id.rl_albums_6);
		tvAlbums1 = (TextView) findViewById(R.id.tv_albums_1);
		tvAlbums2 = (TextView) findViewById(R.id.tv_albums_2);
		tvAlbums3 = (TextView) findViewById(R.id.tv_albums_3);
		tvAlbums4 = (TextView) findViewById(R.id.tv_albums_4);
		tvAlbums5 = (TextView) findViewById(R.id.tv_albums_5);
		tvAlbums6 = (TextView) findViewById(R.id.tv_albums_6);
		vPoint1 = (View) findViewById(R.id.view_round_1);
		vPoint2 = (View) findViewById(R.id.view_round_2);
		vPoint3 = (View) findViewById(R.id.view_round_3);
		vPoint4 = (View) findViewById(R.id.view_round_4);
		vPoint5 = (View) findViewById(R.id.view_round_5);
		vPoint6 = (View) findViewById(R.id.view_round_6);
		
		rlTitleBg.setLayoutParams(biz.setAlbumsSelParams(1280, 92, 0, 0, 0, scaleQPW, scaleQPH));
		
		rlAlbums1.setLayoutParams(biz.setAlbumsSelParams(188, 68, 0, 0, 0, scaleQPW, scaleQPH));
		
		rlAlbums = new RelativeLayout[6];
		rlAlbums[0] = rlAlbums1;
		rlAlbums[1] = rlAlbums2;
		rlAlbums[2] = rlAlbums3;
		rlAlbums[3] = rlAlbums4;
		rlAlbums[4] = rlAlbums5;
		rlAlbums[5] = rlAlbums6;
		tvAlbums = new TextView[6];
		tvAlbums[0] = tvAlbums1;
		tvAlbums[1] = tvAlbums2;
		tvAlbums[2] = tvAlbums3;
		tvAlbums[3] = tvAlbums4;
		tvAlbums[4] = tvAlbums5;
		tvAlbums[5] = tvAlbums6;
		vPoint = new View[6];
		vPoint[0] = vPoint1;
		vPoint[1] = vPoint2;
		vPoint[2] = vPoint3;
		vPoint[3] = vPoint4;
		vPoint[4] = vPoint5;
		vPoint[5] = vPoint6;
		
		ivBack.setOnClickListener(this);
		ivHistory.setOnClickListener(this);
		slView.smoothScrollTo(0, 0);
		onItemClick();
	}
	
	private void onItemClick() {
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				Volumes volumes = mListVolumes.get(position);
				String id = volumes.getId();
				String updateTime = volumes.getUpdated_at();
				String oldTime = new CacheSp().spGetVideoStatus(mContext, id);
				System.out.println("onItemClick oldTime :" + oldTime);
				ImageView ivStatus = (ImageView) view.findViewById(R.id.iv_status);
				if(!StringBiz.isEmpty(oldTime) && !StringBiz.isEmpty(updateTime)) {
					if(!oldTime.equals(updateTime)) { // NEW
						ivStatus.setImageDrawable(null);
						new CacheSp().spSetVideoStatus(mContext, id, updateTime);
					}
				}
				startActivity(new Intent(mContext, WearItemDetailActivity.class)
				.putExtra("name", volumes.getName()).putExtra("volumeId", volumes.getId()));
			}
		});
	}

	@Override
	public void onClick(View v) {
		if(v == ivBack) {
			finish();
		} else if(v == ivHistory) { // 历史记录
			biz.gotoActivity(mContext, WearVideoAlbumsHistoryRecordActivity.class);
		} else if(v == rlAlbums1) {
			processClick(1);
		} else if(v == rlAlbums2) {
			processClick(2);
		} else if(v == rlAlbums3) {
			processClick(3);
		} else if(v == rlAlbums4) {
			processClick(4);
		} else if(v == rlAlbums5) {
			processClick(5);
		} else if(v == rlAlbums6) {
			processClick(6);
		}
	}
	
	private void processClick(int index) {
		changeSelStatus(previousOne, 0);
		System.out.println("previousOne :" + previousOne);
		changeSelStatus(index, 3);
		previousOne = index;
		// 显示切换的列表数据
		processData(index - 1);
		// 记录标签的时间
		String name = mListVideo.get(index-1).getName();
		String updateTime = mListVideo.get(index-1).getUpdated_at();
		String oldTime = new CacheSp().spGetVideoStatus(mContext, name);
		if(!StringBiz.isEmpty(oldTime) && !StringBiz.isEmpty(updateTime)) {
			if(!oldTime.equals(updateTime)) { // NEW
				new CacheSp().spSetVideoStatus(mContext, name, updateTime);
				vPoint[index-1].setVisibility(View.GONE);
			}
		}
	}
	
	private void changeSelStatus(int pos, int heiValue) {
		if(pos == 1) {
			rlAlbums1.setLayoutParams(biz.setAlbumsSelParams(188, 68, 0, 0, 0 + heiValue, scaleQPW, scaleQPH));
		} else if(pos == 2) {
			rlAlbums2.setLayoutParams(biz.setAlbumsSelParams(188, 68, 14, R.id.rl_albums_1, 0 + heiValue, scaleQPW, scaleQPH));
		} else if(pos == 3) {
			rlAlbums3.setLayoutParams(biz.setAlbumsSelParams(188, 68, 14, R.id.rl_albums_2, 0 + heiValue, scaleQPW, scaleQPH));
		} else if(pos == 4) {
			rlAlbums4.setLayoutParams(biz.setAlbumsSelParams(188, 68, 14, R.id.rl_albums_3, 0 + heiValue, scaleQPW, scaleQPH));
		} else if(pos == 5) {
			rlAlbums5.setLayoutParams(biz.setAlbumsSelParams(188, 68, 14, R.id.rl_albums_4, 0 + heiValue, scaleQPW, scaleQPH));
		} else if(pos == 6) {
			rlAlbums6.setLayoutParams(biz.setAlbumsSelParams(188, 68, 14, R.id.rl_albums_5, 0 + heiValue, scaleQPW, scaleQPH));
		}
		
	}
	
	public class MyAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			mListVideo = VideoBase.getVideoList(mContext);
			return "";
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			if(mListVideo != null && mListVideo.size() > 0) {
				processType();
				VideoBase videoBaseCommon = new VideoBase();
				videoBaseCommon = mListVideo.get(mListVideo.size() - 1);
				String name = videoBaseCommon.getName();
				if(videoBaseCommon != null && !StringBiz.isEmpty(name) && name.equals("全局推荐")) {
					mListCommon = videoBaseCommon.getVolumes();
				}
				processData(0);
			}
		}
	}
	
	// 处理分类标签
	private void processType() {
		int len = mListVideo.size() - 1;
		for(int i=0; i<len; i++) {
			rlAlbums[i].setVisibility(View.VISIBLE);
			if(i == 0) {
				changeSelStatus((i+1), 3);
			} else {
				changeSelStatus((i+1), 0);
			}
			String name = mListVideo.get(i).getName();
			String updateTime = mListVideo.get(i).getUpdated_at();
			String oldTime = new CacheSp().spGetVideoStatus(mContext, name);
			if(StringBiz.isEmpty(oldTime)) {
				vPoint[i].setVisibility(View.GONE);
				new CacheSp().spSetVideoStatus(mContext, name, updateTime);
			} else {
				if(!StringBiz.isEmpty(updateTime)) {
					if(!oldTime.equals(updateTime)) { // NEW
						vPoint[i].setVisibility(View.VISIBLE);
					} else {
						vPoint[i].setVisibility(View.GONE);
					}
				}
			}
			tvAlbums[i].setText(mListVideo.get(i).getName());
			rlAlbums[i].setOnClickListener(this);
		}
	}
	
	// 填充每一个分类的数据
	private void processData(int index) {
		VideoBase videoBase = new VideoBase();
		videoBase = mListVideo.get(index);
		mListVolumes.clear();
		List<Volumes> volmesList = new ArrayList<Volumes>();
		if(mListCommon != null && mListCommon.size() > 0) {
			mListVolumes.addAll(mListCommon);
		}
		volmesList = videoBase.getVolumes();
		if(videoBase != null && volmesList != null && volmesList.size() > 0) {
			mListVolumes.addAll(volmesList);
		}
		adapter = new WearVideoAlbumsAdapter(mListVolumes, mContext, width);
		gridView.setAdapter(adapter);
	}
	
}
