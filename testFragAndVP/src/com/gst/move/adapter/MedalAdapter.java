package com.gst.move.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.custom.InnerGridView;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.LayoutParameters;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.base.GridViewData;
import com.gst.move.base.ListviewData;
import com.gst.move.level_c_game.MedalDetailActivity;

import java.util.List;

/**
 * Created by gst-pc on 2016/11/2.
 */

public class MedalAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private float mScaleQPW, mScaleQPH;
    private List<ListviewData> mListData;
    private int wh;
    private String mMedalId;
    private int mItemIndex = -1, mChildIndex = -1;

    public MedalAdapter(Context context, List<ListviewData> listData, float scaleQPW, float scaleQPH, int wh, String medalId) {
        mContext = context;
        mScaleQPW = scaleQPW;
        mScaleQPH = scaleQPH;
        mListData = listData;
        this.wh = wh;
        mMedalId = medalId;
        if(!StringBiz.isEmpty(mMedalId) && StringBiz.isNumeric(mMedalId)) {
            String fValue = mMedalId.substring(0, 1);
            String eValue = mMedalId.substring(mMedalId.length()-2, mMedalId.length());
            mItemIndex = Integer.valueOf(fValue).intValue();
            mChildIndex = Integer.valueOf(eValue).intValue();
        }
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.medal_item, null);
            holder.gridView = (InnerGridView) convertView.findViewById(R.id.gridview);
            holder.ivType = (ImageView) convertView.findViewById(R.id.iv_type);
            holder.rlGridview = (RelativeLayout) convertView.findViewById(R.id.rl_gridview);
            setViewPosition(holder.rlGridview);
            setViewPosition(holder.ivType, R.id.rl_gridview);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position == 0) {
            holder.rlGridview.setBackgroundResource(R.drawable.medal_0_bg);
            holder.ivType.setImageResource(0);
        } else if(position == 1) {
            holder.rlGridview.setBackgroundResource(R.drawable.medal_1_bg);
            holder.ivType.setImageResource(R.drawable.type_1);
        } else if(position == 2) {
            holder.rlGridview.setBackgroundResource(R.drawable.medal_2_bg);
            holder.ivType.setImageResource(R.drawable.type_2);
        } else if(position == 3) {
            holder.rlGridview.setBackgroundResource(R.drawable.medal_3_bg);
            holder.ivType.setImageResource(R.drawable.type_3);
        } else if(position == 4) {
            holder.rlGridview.setBackgroundResource(R.drawable.medal_4_bg);
            holder.ivType.setImageResource(R.drawable.type_4);
        } else if(position == 5) {
            holder.rlGridview.setBackgroundResource(R.drawable.medal_5_bg);
            holder.ivType.setImageResource(R.drawable.type_5);
        } else if(position == 6) {
            holder.rlGridview.setBackgroundResource(R.drawable.medal_6_bg);
            holder.ivType.setImageResource(R.drawable.type_6);
        } else {
            holder.rlGridview.setBackgroundResource(R.drawable.medal_0_bg);
            holder.ivType.setImageResource(0);
        }
//        int index = mListData.get(position).getLen();
        List<GridViewData> listGrid = mListData.get(position).getGridData();
        holder.gridView.setAdapter(new GridViewAdapter(position, listGrid));
        return convertView;
    }

    class ViewHolder {
        RelativeLayout rlGridview;
        private ImageView ivType;
        InnerGridView gridView;
    }

    private void setViewPosition(View view) {
        view.setLayoutParams(LayoutParameters
                .setViewCenterParams(408, RelativeLayout.LayoutParams.WRAP_CONTENT, 20, mScaleQPW, mScaleQPH));
    }

    private void setViewPosition(View view, int aliginId) {
        view.setLayoutParams(LayoutParameters
                .setViewCenterParams(130, 36, -5, 0, 4, aliginId, mScaleQPW, mScaleQPH));
    }

    private void setViewPosition(View view, int width, int height) {
        view.setLayoutParams(LayoutParameters
                .setViewCenterParams(width, height, 0, mScaleQPW, mScaleQPH));
    }

    public class GridViewAdapter extends BaseAdapter{
        private List<GridViewData> mGridData;
        private int mIndex;
        public GridViewAdapter(int index, List<GridViewData>  gridData) {
            mIndex = index;
            mGridData = gridData;
        }

        @Override
        public int getCount() {
            return mGridData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            GridHolder holder;
            if(null == convertView){
                holder=new GridHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, null);
                holder.child_item = (RelativeLayout) convertView.findViewById(R.id.child_item);
                holder.ivMedalItem = (ClickImageView) convertView.findViewById(R.id.iv_medal_item);
//                setViewPosition(holder.ivMedalItem, 87, 103);
                if(wh > 1280) {
                    setViewPosition(holder.ivMedalItem, 150, 177);
                } else {
                    setViewPosition(holder.ivMedalItem, 125, 148);
                }
                convertView.setTag(holder);
            } else {
                holder=(GridHolder) convertView.getTag();
            }
            int len = mGridData.get(position).getLen();
            String name = "medal_" + mIndex + "_" + len;
            int resId = new BaseCommon().getImageId(mContext, "medal_" + mIndex + "_" + len);
            if(resId != 0) {
                holder.ivMedalItem.setImageResource(resId);
            }
            if(mItemIndex != -1 && mChildIndex != -1 && mChildIndex < mGridData.size() && mItemIndex == mIndex && (mChildIndex - 1) == position) {
                CommonAnimation.startShouAnimation(holder.ivMedalItem, 0.8f, 400);
            } else {
                holder.ivMedalItem.clearAnimation();
            }
            holder.ivMedalItem.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO 显示详情
//                    Toast.makeText(mContext, "---------mIndex : "+ mIndex + "  ----pos------" + position, Toast.LENGTH_LONG).show();
                    mContext.startActivity(new Intent(mContext, MedalDetailActivity.class).putExtra("picName",
                            "medal_big_"+mIndex+"_" + position));

                }
            });
            return convertView;
        }
    }

    public static class GridHolder{
        RelativeLayout child_item;
        ClickImageView ivMedalItem;
    }

}
