package com.gst.move.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ebodoo.raz.base.VideoHistory;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.utils.BaseCommon;

public class WearVideoAlbumsHistoryAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext;
	private List<VideoHistory> mListVideo;

	public WearVideoAlbumsHistoryAdapter(List<VideoHistory> listVideo, Context context) {
		this.mListVideo = listVideo;
		this.mContext = context;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mListVideo.size();
	}

	@Override
	public Object getItem(int position) {
		return mListVideo != null ? mListVideo.get(position) : 0;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_wear_history, null);
			holder = new ViewHolder();
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		VideoHistory videoHistory = mListVideo.get(position);
		String albumName = videoHistory.getAlbumsName();
		String name = videoHistory.getName();
		String currentPosition = videoHistory.getPlayTime();
		if(!StringBiz.isEmpty(currentPosition) && new BaseCommon().isNumeric(currentPosition) && !currentPosition.equals("0")) {
			String playTime = BaseCommon.minutesAndSeconds(Integer.valueOf(currentPosition).intValue());
			holder.tvTime.setText("观看到 " + playTime);
		} else {
			holder.tvTime.setText("");
		}
		holder.tvTitle.setText(albumName + name);
		return convertView;
	}

	class ViewHolder {
		TextView tvTitle, tvTime;
	}

}

