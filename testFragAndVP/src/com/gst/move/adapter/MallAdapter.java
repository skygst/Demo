package com.gst.move.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.location.biz.LayoutParameters;
import com.gst.move.R;
import com.gst.move.model.MallBase;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class MallAdapter extends RecyclerView.Adapter<MallAdapter.ViewHolder> {

	public interface OnItemClickLitener {
		void onItemClick(View view, int position);
	}

	private OnItemClickLitener mOnItemClickLitener;

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
		this.mOnItemClickLitener = mOnItemClickLitener;
	}

	private List<MallBase> mListMall;
	private Context mContext;
	private LayoutInflater mInflater;
	private int mWidth = 410, mHeight = 475, mY = 120;
	private float mWbeishu, mHbeishu;
	private ImageLoader mImageLoader;

	public MallAdapter(Context context, List<MallBase> listMall, float wBeishu,
					   float hBeishu) {
		this.mContext = context;
		this.mListMall = listMall;
		mInflater = LayoutInflater.from(mContext);
		mWbeishu = wBeishu;
		mHbeishu = hBeishu;
		mImageLoader = ImageLoader.getInstance();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ViewHolder(View arg0) {
			super(arg0);
		}

		LinearLayout rlLayout;
		TextView tvTitle, tvOriginalPrice, tvPrice;
		ImageView ivPic;
	}

	@Override
	public int getItemCount() {
		return mListMall.size();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View view = mInflater.inflate(R.layout.mall_item, viewGroup, false);
		ViewHolder viewHolder = new ViewHolder(view);

		viewHolder.rlLayout = (LinearLayout) view
				.findViewById(R.id.rl_layout);
		viewHolder.tvTitle = (TextView) view.findViewById(R.id.tv_title);
		viewHolder.tvPrice = (TextView) view.findViewById(R.id.tv_price);
		viewHolder.tvOriginalPrice = (TextView) view
				.findViewById(R.id.tv_original_price);
		viewHolder.ivPic = (ImageView) view.findViewById(R.id.iv_pic);

		LinearLayout.LayoutParams params = LayoutParameters.shangchengParams(
				mWidth, mHeight, mY, mWbeishu, mHbeishu);
		viewHolder.rlLayout.setLayoutParams(params);
//		RelativeLayout.LayoutParams params2 = LayoutParameters.shangchengParams22(
//				300, 40, mY + 33, mWbeishu, mHbeishu);
//		viewHolder.tvTitle.setLayoutParams(params2);
//		viewHolder.tvTitle.setGravity(Gravity.CENTER_HORIZONTAL);
		return viewHolder;
	}

	@Override
	public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
		MallBase mallBase = mListMall.get(i);
		if(i == 0) {
			viewHolder.tvTitle.setText(mallBase.getName() + "---------------------------" + mallBase.getName());
		} else {
			viewHolder.tvTitle.setText(mallBase.getName());
		}
		viewHolder.tvPrice.setText("￥" + mallBase.getPrice());
		viewHolder.tvOriginalPrice.setText("￥" + mallBase.getOriginal_price());
		viewHolder.tvOriginalPrice.getPaint().setFlags(
				Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);// 文件中间加下划线
		String url = mallBase.getPic();
		System.out.println("url :" + url);
		mImageLoader.displayImage(url, viewHolder.ivPic);

		if (mOnItemClickLitener != null) {
			viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
				}
			});

		}

	}

}
