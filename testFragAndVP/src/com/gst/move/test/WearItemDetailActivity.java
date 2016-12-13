package com.gst.move.test;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.base.Items;
import com.ebodoo.raz.base.VideoShow;
import com.ebodoo.raz.biz.ActivityBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.MyGridView;
import com.ebodoo.raz.data.FixedPositionAsia;
import com.ebodoo.raz.database.ContactInjfoDao;
import com.ebodoo.raz.database.MyVideoHistoryHelper;
import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;
import com.gst.move.adapter.WearItemDetailAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * 视频列表
 * 
 * @author
 * 
 */
public class WearItemDetailActivity extends BaseActivity implements OnClickListener {

	private Context mContext;
	private RelativeLayout rlTitleBg, rlPicBg, rlGridView;
	private View viewLine;
	private TextView tvTitle, tvDes;
	private ImageView ivBack, ivPic;
	private MyGridView gridView;
	private ScrollView slView;
	private VideoBiz biz;
	private float scaleQPW = 1.0f, scaleQPH = 1.0f;
	private WearItemDetailAdapter adapter;
	private VideoShow videoShow;
	private ImageLoader mImageLoader;
	private String title, volumeId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wear_video_item_detail);

		init();
		setView();
		new MyAsyncTask().execute();
	}

	private void init() {
		mContext = WearItemDetailActivity.this;
		biz = new VideoBiz();
		scaleQPW = (width / 1280.0f);
		scaleQPH = (height / 720.0f);
		videoShow = new VideoShow();
		title = getIntent().getExtras().getString("name");
		volumeId = getIntent().getExtras().getString("volumeId");
//		title = "粉红小猪";
		mImageLoader = ImageLoader.getInstance();
	}

	private void setView() {
		slView = (ScrollView) findViewById(R.id.sl_view);
		ivBack = (ImageView) findViewById(R.id.iv_back);
		rlTitleBg = (RelativeLayout) findViewById(R.id.rl_title_bg);
		rlPicBg = (RelativeLayout) findViewById(R.id.rl_pic_bg);
		rlGridView = (RelativeLayout) findViewById(R.id.rl_gridview);
		viewLine = (View) findViewById(R.id.view_line);
		gridView = (MyGridView) findViewById(R.id.myGridView);
		ivPic = (ImageView) findViewById(R.id.iv_pic);
		tvTitle = (TextView) findViewById(R.id.tv_title);
		tvDes = (TextView) findViewById(R.id.tv_albums_des);
		TextPaint tp = tvTitle.getPaint();
		tvTitle.setText(title);
		tp.setFakeBoldText(true); 
		
		setViewPosition(rlTitleBg, 0);
		setViewPosition(rlPicBg, 1);
		setViewPosition(viewLine, 2);
		slView.smoothScrollTo(0, 0);
		ivBack.setOnClickListener(this);
		onItemClick();
	}
	
	private void onItemClick() {
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				Items items = videoShow.getItems().get(position);
				String youkuId = items.getYouku_id();
				String videoUrl = items.getVideo_url();
				adapter.mSelPos = position;
				adapter.notifyDataSetChanged();
				ActivityBiz.gotoPlayVideo(mContext, videoUrl, 
						youkuId, title, items.getName(), items.getId());
			}
		});
	}

	private void setViewPosition(View iv, int i) {
		biz.setViewPosition(iv, i,
				FixedPositionAsia.wear_video_albums_position, scaleQPW,
				scaleQPH, 0, 0, 1.0f);
	}

	@Override
	public void onClick(View v) {
		if(v == ivBack) {
			finish();
		}
	}
	
	public class MyAsyncTask extends AsyncTask<String, Integer, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			videoShow = VideoShow.getVideoShow(mContext, volumeId);
			return "";
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			if(videoShow != null) {
				String picUrl = videoShow.getPic_url();
//				picUrl = "http://img.bbpapp.com/Mon_1509/11_435730_a4981530c1d5735.jpg";
				mImageLoader.displayImage(picUrl, ivPic);
				String description = videoShow.getDescription();
				tvDes.setText(description);
				List<Items> listItems = videoShow.getItems();
				int width = ActivityBiz.getViewWidth(rlGridView);
				adapter = new WearItemDetailAdapter(listItems, mContext, width,
						scaleQPW, scaleQPH, -1);
				gridView.setAdapter(adapter);
			}
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// 删除数据库
//		ContactInjfoDao mDao = new ContactInjfoDao(mContext);
//    	mDao.deleteDatabase(mContext, "video_history.db");
		query();
	}
	
	
	// 查询-显示数据库所有内容
    public void query() {
        MyVideoHistoryHelper helper = new MyVideoHistoryHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from videoHistory", null);
        int count = cursor.getCount();
        System.out.println("count :" + count);
        if (cursor != null) {
            while (cursor.moveToNext()) {
            	String sbAlbumsName = cursor.getString(cursor.getColumnIndex("albumsName"));
                String sbName = cursor.getString(cursor.getColumnIndex("name"));
                String youkeId = cursor.getString(cursor.getColumnIndex("youkeId"));
                String videoUrl = cursor.getString(cursor.getColumnIndex("videoUrl"));
                String playTime = cursor.getString(cursor.getColumnIndex("playTime"));
                String videoId = cursor.getString(cursor.getColumnIndex("videoId"));
                System.out.println("name--sbName- " + sbName 
                		+ " --- sbAlbumsName :" + sbAlbumsName
                		+ " --- youkeId :" + youkeId
                		+ " --- videoUrl :" + videoUrl
                		+ " --- videoId :" + videoId
                		+ " --- playTime :" + playTime);
                String sbId = cursor.getString(cursor.getColumnIndex("id"));
                System.out.println("name--sbId- " + sbId);
            }
        }
        cursor.close();
    }

}
