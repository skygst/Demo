package com.gst.mydemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gst.mydemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.List;

/**
 * Created by 善同 on 2016/1/14.
 */
public class HorizonAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private List<String> mListString;
    private final Context mContext;
    private ImageLoader mImageLoader;
    private int mWidth;

    public HorizonAdapter(Context context, List<String> threadList, int width) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mListString = threadList;
        mWidth = width;
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
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.horizon_item, null, false);
            holder.rlLayout = (RelativeLayout) convertView.findViewById(R.id.rl_layout);
            holder.ivPic = (ImageView) convertView.findViewById(R.id.iv_pic);
            if (mWidth <= 480) {
                RelativeLayout.LayoutParams params;
                params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = 7;
                params.topMargin = 0;
                params.addRule(RelativeLayout.CENTER_VERTICAL);
                holder.rlLayout.setLayoutParams(params);
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String attachUrl = mListString.get(position);
        try {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true).cacheInMemory(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
//                    .showImageOnLoading(R.drawable.default_image)
                    .displayer(new SimpleBitmapDisplayer()).build();
            mImageLoader.displayImage(attachUrl, holder.ivPic, options);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    class ViewHolder {
        RelativeLayout rlLayout;
        ImageView ivPic;
    }
}