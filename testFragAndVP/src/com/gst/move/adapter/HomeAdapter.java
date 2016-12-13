package com.gst.move.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.utils.LayoutParameters;
import com.gst.move.R;
import com.gst.move.model.MallBase;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by gst-pc on 2016/11/18.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

//    private List<MallBase> mListMall;
    private Context mContext;
    private LayoutInflater mInflater;
    private int mWidth = 410, mHeight = 475, mY = 120;
    private float mWbeishu, mHbeishu;
    private ImageLoader mImageLoader;

    public HomeAdapter(Context context, List<MallBase> listMall, float wBeishu,
                       float hBeishu) {
        this.mContext = context;
//        this.mListMall = listMall;
        mInflater = LayoutInflater.from(mContext);
        mWbeishu = wBeishu;
        mHbeishu = hBeishu;
        mImageLoader = ImageLoader.getInstance();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        ImageView ivPit, ivSeed, ivRoad, ivRoadSigns;
    }

    @Override
    public int getItemCount() {
//        return mListMall.size();
        return 26;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.home_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
//        ivPit, ivSeed, ivRoad, ivRoadSigns
        viewHolder.ivPit = (ImageView) view.findViewById(R.id.iv_pit);
        viewHolder.ivSeed = (ImageView) view.findViewById(R.id.iv_seed);
        viewHolder.ivRoad = (ImageView) view.findViewById(R.id.iv_road);
        viewHolder.ivRoadSigns = (ImageView) view.findViewById(R.id.iv_road_signs);

        int heiPit, heiSign, hitRoad;
//        if(i % 2 == 0) {
            heiPit = 325;
            heiSign = 303;
            hitRoad = 400;
//        } else {
//            heiPit = 305;
//            heiSign = 286;
//            hitRoad = 410;
//        }
        RelativeLayout.LayoutParams params1 = LayoutParameters.topParams(
                343, 213, heiPit, 0, mWbeishu, mHbeishu);
        viewHolder.ivPit.setLayoutParams(params1);

        RelativeLayout.LayoutParams params2 = LayoutParameters.topAndRightParams(
                94, 107, heiSign, R.id.iv_pit, -20, mWbeishu, mHbeishu);
        viewHolder.ivRoadSigns.setLayoutParams(params2);

        RelativeLayout.LayoutParams params3 = LayoutParameters.topAndLeftParams(
                208, 40, hitRoad, R.id.iv_pit, 0, mWbeishu, mHbeishu);
        viewHolder.ivRoad.setLayoutParams(params3);

//        LinearLayout.LayoutParams params = LayoutParameters.shangchengParams(
//                mWidth, mHeight, mY, mWbeishu, mHbeishu);
//        viewHolder.rlLayout.setLayoutParams(params);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
//        int heiPit, heiSign, hitRoad;
//        if(i % 2 == 0) {
//            heiPit = 325;
//            heiSign = 303;
//            hitRoad = 400;
//        } else {
//            heiPit = 305;
//            heiSign = 286;
//            hitRoad = 410;
//        }
//        RelativeLayout.LayoutParams params1 = LayoutParameters.topParams(
//                343, 213, heiPit, mWbeishu, mHbeishu);
//        viewHolder.ivPit.setLayoutParams(params1);
//
//        RelativeLayout.LayoutParams params2 = LayoutParameters.topAndRightParams(
//                94, 107, heiSign, R.id.iv_pit, -20, mWbeishu, mHbeishu);
//        viewHolder.ivRoadSigns.setLayoutParams(params2);
//
//        RelativeLayout.LayoutParams params3 = LayoutParameters.topAndLeftParams(
//                208, 40, hitRoad, R.id.iv_pit, 0, mWbeishu, mHbeishu);
//        viewHolder.ivRoad.setLayoutParams(params3);

//        if (mOnItemClickLitener != null) {
//            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mOnItemClickLitener.onItemClick(viewHolder.itemView, i);
//                }
//            });
//
//        }

    }


    public final static int home_position[][] = {
            {343, 213, 171, 325}, // pit下
            {343, 213, 639, 305}, // pit 上

            {94, 107, 124, 303}, // signs 下
            {94, 107, 583, 286}, // signs 上

            {208, 40, 478, 400}, // road 下
            {208, 40, 927, 410}, // road 上
    };

}
