package com.gst.move.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ebodoo.raz.custom.ClickImageView;
import com.example.location.biz.LayoutParameters;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.base.WearProducts;
import com.gst.move.test_demo.BuySpeakLoudlyDialogActivity;
import com.gst.move.test_demo.VideoPlayActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class SpeakLoudlyAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private float mWbeishu, mHbeishu;
    private ImageLoader mImageLoader;
    private List<WearProducts> mListWear;

    public SpeakLoudlyAdapter(Context context, List<WearProducts> listWear, float wBeishu, float hBeishu) {
        this.mContext = context;
        mListWear = listWear;
        mInflater = LayoutInflater.from(mContext);
        mWbeishu = wBeishu;
        mHbeishu = hBeishu;
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public int getCount() {
        return mListWear.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.speak_loudly_item, null);
            holder.ivTitle = (ImageView) convertView.findViewById(R.id.iv_title);
            holder.ivPic1 = (ClickImageView) convertView.findViewById(R.id.iv_pic_1);
            holder.ivPic2 = (ClickImageView) convertView.findViewById(R.id.iv_pic_2);
            holder.ivPic3 = (ClickImageView) convertView.findViewById(R.id.iv_pic_3);
            holder.ivPic4 = (ClickImageView) convertView.findViewById(R.id.iv_pic_4);
            holder.ivLock2 = (ImageView) convertView.findViewById(R.id.iv_lock_2);
            holder.ivLock3 = (ImageView) convertView.findViewById(R.id.iv_lock_3);
            holder.ivLock4 = (ImageView) convertView.findViewById(R.id.iv_lock_4);

            holder.ivPic1.setLayoutParams(LayoutParameters.widthParams(223, 246, mWbeishu, mHbeishu));
            holder.ivPic2.setLayoutParams(LayoutParameters.widthParams(223, 246, mWbeishu, mHbeishu));
            holder.ivPic3.setLayoutParams(LayoutParameters.widthParams(223, 246, mWbeishu, mHbeishu));
            holder.ivPic4.setLayoutParams(LayoutParameters.widthParams(223, 246, mWbeishu, mHbeishu));

            holder.ivTitle.setLayoutParams(LayoutParameters.belowParams(296, 76, R.id.view_top, mWbeishu, mHbeishu));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final List<WearProducts.Items> mListItems = mListWear.get(position).getListItems();
        String titlePicUrl = mListWear.get(position).getPic_url();
        if(!StringBiz.isEmpty(titlePicUrl)) {
            mImageLoader.displayImage(titlePicUrl, holder.ivTitle);
        }
        if(mListItems != null && mListItems.size() >0 && mListItems.size() >= 4) {
            final String purchased = mListWear.get(position).getPurchased();
            if(!StringBiz.isEmpty(purchased) && purchased.equals("1")) {
                holder.ivLock2.setVisibility(View.GONE);
                holder.ivLock3.setVisibility(View.GONE);
                holder.ivLock4.setVisibility(View.GONE);
            } else {
                holder.ivLock2.setVisibility(View.VISIBLE);
                holder.ivLock3.setVisibility(View.VISIBLE);
                holder.ivLock4.setVisibility(View.VISIBLE);
            }
            String picUrl1 = mListItems.get(0).getPic_url();
            String picUrl2 = mListItems.get(1).getPic_url();
            String picUrl3 = mListItems.get(2).getPic_url();
            String picUrl4 = mListItems.get(3).getPic_url();

            /*DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .showImageOnFail(R.drawable.speak_loudly_learn_together)
                    .showImageForEmptyUri(R.drawable.speak_loudly_1_story)
                    .cacheOnDisk(true).cacheInMemory(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .displayer(new SimpleBitmapDisplayer()).build();*/

//            mImageLoader.displayImage("drawable://" + R.drawable.speak_loudly_learn_together, holder.ivPic1);
//            mImageLoader.displayImage("drawable://" + R.drawable.speak_loudly_1_story, holder.ivPic2);
//            mImageLoader.displayImage("drawable://" + R.drawable.speak_loudly_1_script, holder.ivPic3);
//            mImageLoader.displayImage("drawable://" + R.drawable.speak_loudly_1_parenting, holder.ivPic4);
            mImageLoader.displayImage(picUrl1, holder.ivPic1);
            mImageLoader.displayImage(picUrl2, holder.ivPic2);
            mImageLoader.displayImage(picUrl3, holder.ivPic3);
            mImageLoader.displayImage(picUrl4, holder.ivPic4);

            holder.ivPic1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickPlayVideo(true, mListItems.get(0).getVideo_url(), purchased, mListWear.get(position).getListProducts());
                }
            });
            holder.ivPic2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickPlayVideo(false, mListItems.get(1).getVideo_url(), purchased, mListWear.get(position).getListProducts());
                }
            });
            holder.ivPic3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickPlayVideo(false, mListItems.get(2).getVideo_url(), purchased, mListWear.get(position).getListProducts());
                }
            });
            holder.ivPic4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickPlayVideo(false, mListItems.get(3).getVideo_url(), purchased, mListWear.get(position).getListProducts());
                }
            });
        }
        return convertView;
    }

    class ViewHolder {
        ClickImageView ivPic1, ivPic2, ivPic3, ivPic4;
        ImageView ivTitle, ivLock2, ivLock3, ivLock4;
    }

    private void clickPlayVideo(boolean isPlay, String path, String purchased, List<WearProducts.Products> listProducts) {
        if(isPlay || (!StringBiz.isEmpty(purchased)
                && purchased.equals("1") && !StringBiz.isEmpty(path))) {
            mContext.startActivity(new Intent(mContext, VideoPlayActivity.class).putExtra("path", path));
        } else {
            // TODO 去购买
            if(listProducts != null && listProducts.size() >= 2) {
                mContext.startActivity(new Intent(mContext, BuySpeakLoudlyDialogActivity.class)
                        .putExtra("onceId", listProducts.get(0).getId())
                        .putExtra("onceName", listProducts.get(0).getName())
                        .putExtra("oncePrice", listProducts.get(0).getPrice())
                        .putExtra("allId", listProducts.get(1).getId())
                        .putExtra("allName", listProducts.get(1).getName())
                        .putExtra("allPrice", listProducts.get(1).getPrice()));
            }
        }
    }

}
