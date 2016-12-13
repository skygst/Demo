package com.gst.move.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebodoo.raz.base.Volumes;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.utils.CacheSp;
import com.nostra13.universalimageloader.core.ImageLoader;

public class WearVideoAlbumsAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private Context mContext;
	private ImageLoader mImageLoader;
	private int wh;
	private List<Volumes> mListVolumes;

	public WearVideoAlbumsAdapter(List<Volumes> listVolumes, Context context, int wh) {
		this.mListVolumes = listVolumes;
		this.mContext = context;
		this.wh = (wh - (3 * Dp2Px(context, 15)) - (4 * Dp2Px(context, 7))) / 4; // 4列
//		this.wh = (wh - (2 * Dp2Px(context, 15)) - (3 * Dp2Px(context, 7))) / 3; // 3列
		mInflater = LayoutInflater.from(mContext);
		mImageLoader = ImageLoader.getInstance();
	}

	@Override
	public int getCount() {
		return mListVolumes != null ? mListVolumes.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return mListVolumes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_wear_video_albums, null);
			holder = new ViewHolder();
			holder.headphoto = (ImageView) convertView.findViewById(R.id.photo);
			holder.ivStatus = (ImageView) convertView.findViewById(R.id.iv_status);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_video_name);
			AbsListView.LayoutParams param = new AbsListView.LayoutParams(wh, wh);
			convertView.setLayoutParams(param);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Volumes volumes = mListVolumes.get(position);
		String path = volumes.getPic_url();
		mImageLoader.displayImage(path, holder.headphoto);
		
		// 显示标签
		// 服务端时间与记录时间进行比较，不等则表示New
		// 显示状态 NEW
		String id = volumes.getId();
		String updateTime = volumes.getUpdated_at();
		String oldTime = new CacheSp().spGetVideoStatus(mContext, id);
		String status = volumes.getStatus();
		holder.ivStatus.setImageDrawable(null);
		System.out.println("apater oldTime :" + oldTime);
		if(StringBiz.isEmpty(oldTime)) {
			new CacheSp().spSetVideoStatus(mContext, id, updateTime);
		} else {
			if(!StringBiz.isEmpty(updateTime) && !StringBiz.isEmpty(updateTime)) {
				if (!oldTime.equals(updateTime)) { // NEW
					status = "-1";
					holder.ivStatus.setImageResource(R.drawable.tag_new);
				}
			}
		}
	    //0普通 1推荐 2:全局推荐
		if(!StringBiz.isEmpty(status) && !status.equals("-1")) {
			System.out.println("status :" + status);
			if(status.equals("0")) {
				holder.ivStatus.setImageDrawable(null);
			} else if(status.equals("1") || status.equals("2")) {
				holder.ivStatus.setImageResource(R.drawable.tag_recommend);
			} else {
				holder.ivStatus.setImageDrawable(null);
			}
		}
		// 显示名字
		holder.tvName.setText(volumes.getName());
		return convertView;
	}

	class ViewHolder {
		ImageView headphoto, ivStatus;
		TextView tvName;
	}

	public static int Dp2Px(Context context, float dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

}
