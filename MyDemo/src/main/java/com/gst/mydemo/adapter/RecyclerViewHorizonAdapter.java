package com.gst.mydemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gst.mydemo.R;

import java.util.List;

/**
 * Created by gst-pc on 2016/9/9.
 */
public class RecyclerViewHorizonAdapter extends RecyclerView.Adapter {

    private List<Integer> mDatas;
    private Context mContext;

    public static interface OnRecyclerViewListener {
        void onItemClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    public RecyclerViewHorizonAdapter(Context context, List<Integer> datas) {
        mContext = context;
        mDatas = datas;
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        return null;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_horizon_item, viewGroup, false);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new MyViewHolder(view);
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        MyViewHolder holder = (MyViewHolder) viewHolder;
        int resId = mDatas.get(i);
        holder.mImg.setImageResource(resId);
        holder.mTxt.setText("第" + i + "个item");

        if(onRecyclerViewListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("------------ onClick --------vvv---------");
                    if (null != onRecyclerViewListener) {
                        System.out.println("------------ onClick -----------------");
                        onRecyclerViewListener.onItemClick(i);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        TextView mTxt;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImg = (ImageView) itemView.findViewById(R.id.id_index_gallery_item_image);
            mTxt = (TextView) itemView.findViewById(R.id.id_index_gallery_item_text);
        }
    }

}
