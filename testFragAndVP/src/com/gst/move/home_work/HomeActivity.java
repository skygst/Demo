package com.gst.move.home_work;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.custom.SpringProgressView;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.LayoutParameters;
import com.example.location.biz.StringBiz;
import com.gst.move.R;

/**
 * 回家作业  首页
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout llLayout;
    private RelativeLayout rlGold, rlHeadPortrait;
    private SpringProgressView spProgress;
    private TextView tvGold, tvName, tvLevel;
    private ClickImageView ivBarn, ivShop;
    private ImageView ivWindmill, ivBottomBg, ivHeadPortrait;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;
    private String[] signArray = new String[] {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        setView();
    }

    private void init() {
        mContext = HomeActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
    }

    private void setView() {
        rlGold = (RelativeLayout) findViewById(R.id.rl_gold);
        spProgress = (SpringProgressView) findViewById(R.id.sp_progress);
        rlHeadPortrait = (RelativeLayout) findViewById(R.id.rl_head_portrait);
        llLayout = (LinearLayout) findViewById(R.id.ll_layout);
        ivWindmill = (ImageView) findViewById(R.id.iv_windmill);
        ivBottomBg = (ImageView) findViewById(R.id.iv_bottom_bg);
        ivBarn = (ClickImageView) findViewById(R.id.iv_barn);
        ivShop = (ClickImageView) findViewById(R.id.iv_shop);
        tvGold = (TextView) findViewById(R.id.tv_gold);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvLevel = (TextView) findViewById(R.id.tv_level);
        ivHeadPortrait = (ImageView) findViewById(R.id.iv_head_portrait);
        addView();
        ivrotate();
        setViewPosition(ivWindmill, 0);
        setViewPosition(ivBottomBg, 1);
        setViewPosition(ivBarn, 2);
        setViewPosition(ivShop, 3);
        setViewPosition(rlGold, 4);
        setViewPosition(rlHeadPortrait, 5);
        setViewPosition(ivHeadPortrait, 6);

        RelativeLayout.LayoutParams params = LayoutParameters.setViewProgressPositionParams(120, 20, R.id.tv_name, R.id.iv_head_portrait, 12, scaleQPW,
                scaleQPH);
        spProgress.setLayoutParams(params);
        spProgress.setMaxCount(100.0f);
        spProgress.setCurrentCount(100.0f);
        StringBiz.setTextPaint(tvLevel);

        ivBarn.setOnClickListener(this);
        ivShop.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.home_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private int lineEnd = 1;
    private int[] seedId = new int[] {
            R.drawable.apple_stage_3, R.drawable.banana_stage_3
    };

    private void addView() {
        for (int i = 0; i < 26; i++) {
            View itemView = View.inflate(mContext, R.layout.home_item, null);
            ImageView ivPit = (ImageView) itemView.findViewById(R.id.iv_pit);
            ImageView ivSeed = (ImageView) itemView.findViewById(R.id.iv_seed);
            ImageView ivRoad = (ImageView) itemView.findViewById(R.id.iv_road);
            ImageView ivExtraRoad = (ImageView) itemView.findViewById(R.id.iv_extra_road);
            ImageView ivRoadSigns = (ImageView) itemView.findViewById(R.id.iv_road_signs);
            int extral = (i == 0 ? 100 : 0);
            int extralRoadValue = (i % 2 == 0 ? 20 : -20);
            int heiPit = (i % 2 == 0 ? 325 : 285);
            int heiSign = (i % 2 == 0 ? 303 : 286);
            int heiRoad = (i % 2 == 0 ? 400 : 410);
            int heiSeed = (i % 2 == 0 ? 116 : 86);
            if(i < seedId.length) {
                ivSeed.setImageResource(seedId[i]);
                ivSeed.setVisibility(View.VISIBLE);
            } else {
                ivSeed.setVisibility(View.GONE);
            }
            if (i % 2 == 0) {
                ivRoad.setImageResource(R.drawable.road_left_1);
                ivExtraRoad.setImageResource(R.drawable.road_left_2);
            } else {
                ivRoad.setImageResource(R.drawable.road_left_2);
                ivExtraRoad.setImageResource(R.drawable.road_left_1);
            }
            if(i == 0) {
                ivExtraRoad.setVisibility(View.GONE);
            } else {
                ivExtraRoad.setVisibility(View.VISIBLE);
            }
            ivSeed.setLayoutParams(setParams(370, 350, heiSeed, R.id.iv_road_signs, -45));
            ivPit.setLayoutParams(setParams(343, 213, heiPit, R.id.iv_road_signs, -35));
            ivRoadSigns.setLayoutParams(setParams(94, 107, heiSign, 0, (0 + extral)));
            ivRoad.setLayoutParams(setParams(129, 40, heiRoad, 0, (10 + 343 + extral)));
            ivExtraRoad.setLayoutParams(setParams(129, 40, (heiRoad + extralRoadValue), 0, 0));
            if(i == 25) {
                ivRoad.setVisibility(View.INVISIBLE);
            } else {
                ivRoad.setVisibility(View.VISIBLE);
            }
            int resId =  new BaseCommon().getImageId(mContext, "signs_" + signArray[i]);
            if(resId != 0) {
                ivRoadSigns.setImageResource(resId);
            }
            if(i >= 1) {
                if(i > (lineEnd - 1)) {
                    ivRoad.setVisibility(View.INVISIBLE);
                } else {
                    ivRoad.setVisibility(View.VISIBLE);
                }

                if(i > lineEnd) {
                    ivExtraRoad.setVisibility(View.INVISIBLE);
                } else {
                    ivExtraRoad.setVisibility(View.VISIBLE);
                }
            }
            llLayout.addView(itemView);
        }
    }

    private RelativeLayout.LayoutParams setParams(int width, int height, int hei, int leftId, int leftValue) {
        RelativeLayout.LayoutParams params = LayoutParameters.topAndLeftParams(
                width, height, hei, leftId, leftValue, scaleQPW, scaleQPH);
        return params;
    }

    @Override
    public void onClick(View v) {

    }

    // 风车旋转
    private void ivrotate(){
        Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.iv_rotate);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        ivWindmill.startAnimation(operatingAnim);
    }

}
