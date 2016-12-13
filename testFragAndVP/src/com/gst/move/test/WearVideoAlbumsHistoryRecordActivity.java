package com.gst.move.test;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.base.VideoHistory;
import com.ebodoo.raz.biz.ActivityBiz;
import com.ebodoo.raz.data.FixedPositionAsia;
import com.ebodoo.raz.database.MyVideoHistoryHelper;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;
import com.gst.move.adapter.WearVideoAlbumsHistoryAdapter;

/**
 *  视频专辑历史记录
 * @author 
 *
 */
public class WearVideoAlbumsHistoryRecordActivity extends BaseActivity implements OnClickListener {
	
	private Context mContext;
	private RelativeLayout rlBg, rlView;
	private ImageView ivTitle;
	private ListView listView;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private WearVideoAlbumsHistoryAdapter adapter;
	private List<VideoHistory> mListVideo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wear_video_album_history);
		
		init();
		setView();
	}
	
	private void init() {
		mContext = WearVideoAlbumsHistoryRecordActivity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		mListVideo = new ArrayList<VideoHistory>();
		query();
	}

	private void setView() {
		rlBg = (RelativeLayout) findViewById(R.id.rl_bg);
		rlView = (RelativeLayout) findViewById(R.id.rl_view);
		ivTitle = (ImageView) findViewById(R.id.iv_title);
		listView = (ListView) findViewById(R.id.list_view);
		
		adapter = new WearVideoAlbumsHistoryAdapter(mListVideo, mContext);
		listView.setAdapter(adapter);
		
		setViewPosition(rlView, 3);
		setViewPosition(listView, 4);
		setViewPosition(ivTitle, 5);
		rlBg.setOnClickListener(this);
		onItemClick();
	}
	
	private void onItemClick() {
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				System.out.println("点击了第 " + (pos + 1) + " 个");
				VideoHistory history = mListVideo.get(pos);
				ActivityBiz.gotoPlayVideo(mContext, history.getVideoUrl(), 
						history.getYoukeId(), history.getAlbumsName(), history.getName(), history.getVideoId()); 
			}
		});
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i, FixedPositionAsia.wear_video_albums_position,
				scaleQPW, scaleQPH, 0, 0, 1.0f);
	}
	
	@Override
	public void onClick(View v) {
		if(v == rlBg) {
			finish();
		}
	}
	
	// 查询-显示数据库所有内容
    private void query() {
        MyVideoHistoryHelper helper = new MyVideoHistoryHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from videoHistory", null);
        int count = cursor.getCount();
        System.out.println("count :" + count);
        if (cursor != null) {
            while (cursor.moveToNext()) {
            	VideoHistory videoHistory = new VideoHistory();
                String albumsName = cursor.getString(cursor.getColumnIndex("albumsName"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String youkeId = cursor.getString(cursor.getColumnIndex("youkeId"));
                String videoUrl = cursor.getString(cursor.getColumnIndex("videoUrl"));
                String playTime = cursor.getString(cursor.getColumnIndex("playTime"));
                String videoId = cursor.getString(cursor.getColumnIndex("videoId"));
                videoHistory.setAlbumsName(albumsName);
                videoHistory.setName(name);
                videoHistory.setYoukeId(youkeId);
                videoHistory.setVideoUrl(videoUrl);
                videoHistory.setPlayTime(playTime);
                videoHistory.setVideoId(videoId);
                mListVideo.add(videoHistory);
            }
        }
        cursor.close();
        List<VideoHistory> videoList = new ArrayList<VideoHistory>();
        videoList.addAll(mListVideo);
        mListVideo.clear();
        for(int i=videoList.size()-1;i >= 0; i--) {
        	mListVideo.add(videoList.get(i));
        }
    }
	
	
}
