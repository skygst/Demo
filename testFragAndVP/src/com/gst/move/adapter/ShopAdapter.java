package com.gst.move.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.utils.LayoutParameters;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by gst-pc on 2016/11/27.
 */

public class ShopAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private Context mContext;
    private ImageLoader mImageLoader;
    private float mScalePW, mScalePH;
    private int wh, mSelIndex;

    public ShopAdapter(Context context, int wh, int selIndex, float scaleQPW, float scaleQPH) {
        this.mContext = context;
        this.wh = (wh - (3 * Dp2Px(context, 15)) - (4 * Dp2Px(context, 7))) / 4; // 4列
//		this.wh = (wh - (2 * Dp2Px(context, 15)) - (3 * Dp2Px(context, 7))) / 3; // 3列
        System.out.println("--------wh---------" + this.wh);
        mSelIndex = selIndex;
        mInflater = LayoutInflater.from(mContext);
        mImageLoader = ImageLoader.getInstance();
        mScalePW = scaleQPW;
        mScalePH = scaleQPH;
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.shop_item, null);
            holder = new ViewHolder();
            holder.rlBg = (RelativeLayout) convertView.findViewById(R.id.rl_bg);
            holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            holder.ivLight = (ImageView) convertView.findViewById(R.id.iv_light);
            holder.ivPrize = (ImageView) convertView.findViewById(R.id.iv_prize);
            holder.ivStar = (ImageView) convertView.findViewById(R.id.iv_star);
            holder.ivExchange = (ImageView) convertView.findViewById(R.id.iv_exchange);

            holder.rlBg.setLayoutParams(LayoutParameters.setViewTopParams(201, 261, 5, mScalePW, mScalePH));
            holder.tvTitle.setLayoutParams(LayoutParameters.setViewCenterParams(156, 31, 20, 0, mScalePW, mScalePH));
            holder.ivLight.setLayoutParams(LayoutParameters.setViewCenterParams(154, 155, -15, R.id.tv_title, mScalePW, mScalePH));
            holder.ivPrize.setLayoutParams(LayoutParameters.setViewCenterParentParams(160, 110, R.id.iv_light, mScalePW, mScalePH));
            holder.ivStar.setLayoutParams(LayoutParameters.setViewHorizontalCenterAndBelowParams(93, 34, R.id.iv_prize, -30, mScalePW, mScalePH));
            holder.ivExchange.setLayoutParams(LayoutParameters.setViewHorizontalCenterAndBelowParams(115, 46, R.id.iv_star, 3, mScalePW, mScalePH));

            AbsListView.LayoutParams param = new AbsListView.LayoutParams(wh, wh);
            convertView.setLayoutParams(param);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == mSelIndex) {
            holder.rlBg.setBackgroundResource(R.drawable.shop_item_bg_sel);
        } else {
            holder.rlBg.setBackgroundResource(R.drawable.shop_item_bg);
        }
        StringBiz.setTextPaint(holder.tvTitle);
        return convertView;
    }

    class ViewHolder {
        RelativeLayout rlBg;
        TextView tvTitle, tvStarNum;
        ImageView ivPrize, ivStar, ivExchange, ivLight;
    }

    public void setIndex(int index) {
        mSelIndex = index;
    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
