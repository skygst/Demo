package com.gst.mydemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gst.mydemo.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MyApapter extends BaseAdapter {

	private List<String> mListString;
	private LayoutInflater mInflater;
	private Context mContext;
	private ImageLoader mImageLoader;
	int wh;

	public MyApapter(List<String> listString, Context context, int wh) {
		this.mListString = listString;
		this.mContext = context;
//		this.wh = (wh - (3 * Dp2Px(context, 15)) - (4 * Dp2Px(context, 7))) / 4;
		this.wh = (wh - (2 * Dp2Px(context, 15)) - (3 * Dp2Px(context, 7))) / 3;
		mInflater = LayoutInflater.from(mContext);
		mImageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return mListString.size();
	}

	@Override
	public Object getItem(int position) {
		return mListString.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.photo_layout, null);
			holder = new ViewHolder();
			holder.headphoto = (ImageView) convertView.findViewById(R.id.photo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		String path = mListString.get(position);
		Log.e("jj", path);
		mImageLoader.displayImage(path, holder.headphoto);

		AbsListView.LayoutParams param = new AbsListView.LayoutParams(wh, wh);
		convertView.setLayoutParams(param);

		return convertView;
	}

	class ViewHolder {
		ImageView headphoto;
	}

	public static int Dp2Px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

}
