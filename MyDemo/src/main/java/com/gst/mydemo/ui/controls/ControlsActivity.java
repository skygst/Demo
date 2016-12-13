package com.gst.mydemo.ui.controls;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.mydemo.R;
import com.gst.mydemo.biz.ActivityBiz;
import com.gst.mydemo.ui.TopActivity;

/**
 * 不同控件的使用
 * Created by 善同 on 2016/1/14.
 */
public class ControlsActivity extends TopActivity implements View.OnClickListener {

    private Button btnImageView, btnListView1, btnHorizonListView, btnButton, btnListFrame,
            btnBingtu, btnZhexiantu;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);

        init();
        setView();
    }

    private void init() {
        mContext = ControlsActivity.this;
    }

    private void setView() {
        btnImageView = (Button) findViewById(R.id.btn_imageview);
        btnListView1 = (Button) findViewById(R.id.btn_listview_1);
        btnHorizonListView = (Button) findViewById(R.id.btn_horizon_list_view);
        btnButton = (Button) findViewById(R.id.btn_button);
        btnListFrame = (Button) findViewById(R.id.btn_list_frame);
        btnBingtu = (Button) findViewById(R.id.btn_bing_tu);
        btnZhexiantu = (Button) findViewById(R.id.btn_zhexian_tu);


        btnImageView.setOnClickListener(this);
        btnListView1.setOnClickListener(this);
        btnHorizonListView.setOnClickListener(this);
        btnButton.setOnClickListener(this);
        btnListFrame.setOnClickListener(this);
        btnBingtu.setOnClickListener(this);
        btnZhexiantu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnImageView) { // ImageView部分功能
            ActivityBiz.gotoActivity(mContext, ImageViewActivity.class);
        } else if (v == btnListView1) { // ScrollView嵌套ListView竖滑动
            ActivityBiz.gotoActivity(mContext, ScrollViewNestingListViewActivity.class);
        } else if (v == btnHorizonListView) { // 水平ListView
            ActivityBiz.gotoActivity(mContext, HorizonListViewActivity.class);
        } else if (v == btnButton) { // Button的一些特殊功能
            ActivityBiz.gotoActivity(mContext, ButtonFunctionActivity.class);
        } else if (v == btnListFrame) { // ListView图片动态添加边框
            ActivityBiz.gotoActivity(mContext, ListViewFrameActivity.class);
        } else if (v == btnBingtu) { // 并图
//            ActivityBiz.gotoActivity(mContext, PieActivity.class);
            ActivityBiz.gotoActivity(mContext, PieChartBuilder.class);
        } else if (v == btnZhexiantu) { // 折线图
            ActivityBiz.gotoActivity(mContext, LineChartActivity.class);
        }
    }


}
