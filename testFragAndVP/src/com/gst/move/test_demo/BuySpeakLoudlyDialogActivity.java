package com.gst.move.test_demo;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.data.FixedPosition;
import com.example.location.biz.StringBiz;
import com.gst.move.R;

/**
 * Created by gst-pc on 2016/10/26.
 */

public class BuySpeakLoudlyDialogActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rlLayout, rlBg, rlOnce, rlAll;
    private ClickImageView ivLeft, ivRight;
    private TextView tvOnceTitle, tvAllTitle, tvOncePrice, tvAllPrice;
    private ImageView ivOnceSel, ivAllSel;
//    private boolean
    private Context mContext;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String onceId, onceName, oncePrice, allId, allName, allPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_speak_loudy);

        init();
        setView();
    }

    private void init() {
        mContext = BuySpeakLoudlyDialogActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        onceId = getIntent().getExtras().getString("onceId");
        onceName = getIntent().getExtras().getString("onceName");
        oncePrice = getIntent().getExtras().getString("oncePrice");
        allId = getIntent().getExtras().getString("allId");
        allName = getIntent().getExtras().getString("allName");
        allPrice = getIntent().getExtras().getString("allPrice");
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        rlBg = (RelativeLayout) findViewById(R.id.rl_bg);
        rlOnce = (RelativeLayout) findViewById(R.id.rl_once);
        rlAll = (RelativeLayout) findViewById(R.id.rl_all);

        tvOnceTitle = (TextView) findViewById(R.id.tv_once_title);
        tvAllTitle = (TextView) findViewById(R.id.tv_all_title);
        tvOncePrice = (TextView) findViewById(R.id.tv_once_price);
        tvAllPrice = (TextView) findViewById(R.id.tv_all_price);

        ivOnceSel = (ImageView) findViewById(R.id.iv_once_sel);
        ivAllSel = (ImageView) findViewById(R.id.iv_all_sel);

        ivLeft = (ClickImageView) findViewById(R.id.iv_left);
        ivRight = (ClickImageView) findViewById(R.id.iv_right);

        setViewPosition(rlBg, 0);
        setViewPosition(rlOnce, 1);
        setViewPosition(rlAll, 2);
        setViewPosition(ivLeft, 3);
        setViewPosition(ivRight, 4);

        showView();

        rlLayout.setOnClickListener(this);
        rlBg.setOnClickListener(this);
        rlOnce.setOnClickListener(this);
        rlAll.setOnClickListener(this);
        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.speak_loudly_buy_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private void showView() {
        titleStatus(R.drawable.corner_box_green_5, R.drawable.corner_box_white_5, View.VISIBLE, View.INVISIBLE);
        StringBiz.setTextPaint(tvOncePrice);
        StringBiz.setTextPaint(tvAllPrice);
        tvOnceTitle.setText(onceName);
        tvOncePrice.setText("￥" + oncePrice);
        tvAllTitle.setText(allName);
        tvAllPrice.setText("￥" + allPrice);
    }


    @Override
    public void onClick(View v) {
        if(v == rlLayout) {
            finish();
        } else if(v == ivLeft) { // 取消
            finish();
        } else if(v == ivRight) { // 去购买

        } else if(v == rlOnce) {
            titleStatus(R.drawable.corner_box_green_5, R.drawable.corner_box_white_5, View.VISIBLE, View.INVISIBLE);
        } else if(v == rlAll) {
            titleStatus(R.drawable.corner_box_white_5, R.drawable.corner_box_green_5, View.INVISIBLE, View.VISIBLE);
        }
    }

    private void titleStatus(int onceId, int allId, int onceStatus, int allStatus) {
        tvOnceTitle.setBackgroundResource(onceId);
        tvAllTitle.setBackgroundResource(allId);
        ivOnceSel.setVisibility(onceStatus);
        ivAllSel.setVisibility(allStatus);
    }
}
