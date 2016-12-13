package com.gst.move.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.base.Items;
import com.example.location.biz.VideoBiz;
import com.gst.move.R;

public class WearItemDetailAdapter extends BaseAdapter {

	private List<Items> mListItems;
	private LayoutInflater mInflater;
	private Context mContext;
	private float scaleQPW, scaleQPH;
	int wh;
	public int mSelPos;

	public WearItemDetailAdapter(List<Items> listItems, Context context, int wh, float scaleW, float scaleH, int selPos) {
		this.mListItems = listItems;
		this.mContext = context;
		this.wh = (wh - (4 * Dp2Px(context, 15)) - (5 * Dp2Px(context, 7))) / 5;
//		this.wh = (wh - (2 * Dp2Px(context, 15)) - (3 * Dp2Px(context, 7))) / 3;
		mSelPos = selPos;
		mInflater = LayoutInflater.from(mContext);
		scaleQPW = scaleW;
		scaleQPH = scaleH;
	}

	@Override
	public int getCount() {
		return mListItems != null ? mListItems.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return mListItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_wear_video_albums_detail, null);
			holder = new ViewHolder();
			holder.rlNum = (RelativeLayout) convertView.findViewById(R.id.rl_num);
			holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
			holder.rlNum.setLayoutParams(new VideoBiz().
					setAlbumsSelParams(104, 81, 7, 0, 0, scaleQPW, scaleQPH));
			AbsListView.LayoutParams param = new AbsListView.LayoutParams(wh, wh);
			convertView.setLayoutParams(param);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvNum.setText("" + (position + 1));
		if(mSelPos == position) {
			holder.rlNum.setBackgroundResource(R.drawable.wear_sel_play_video_bg_sel);
		} else {
			holder.rlNum.setBackgroundResource(R.drawable.wear_sel_play_video_bg);
		}
		return convertView;
	}

	class ViewHolder {
		TextView tvNum;
		RelativeLayout rlNum;
	}

	public static int Dp2Px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

}

