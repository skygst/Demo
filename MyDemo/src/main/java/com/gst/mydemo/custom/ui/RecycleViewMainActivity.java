package com.gst.mydemo.custom.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.gst.mydemo.R;
import com.gst.mydemo.custom.ui.recycleView.RecycleViewActivity;
import com.gst.mydemo.custom.ui.recycleView.RecycleViewRefreshActivity;
import com.gst.mydemo.ui.BaseActivity;

/**
 * Created by gst-pc on 2016/9/5.
 */
public class RecycleViewMainActivity extends BaseActivity implements OnClickListener {

    private Button btnSelfAdaption, btnRefreshMore;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recylerview_main);

        init();
        setView();
    }

    private void init() {
        mContext = RecycleViewMainActivity.this;
    }

    private void setView() {
        btnSelfAdaption = (Button) findViewById(R.id.btn_self_adaption);
        btnRefreshMore = (Button) findViewById(R.id.btn_refresh);

        btnSelfAdaption.setOnClickListener(this);
        btnRefreshMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnSelfAdaption) { // 自适应
            gotoActivity(RecycleViewActivity.class);
        } else if(view == btnRefreshMore) { // 上拉刷新 下拉加载更多
            gotoActivity(RecycleViewRefreshActivity.class);
        }
    }

    private void gotoActivity(Class<?> classes) {
        startActivity(new Intent(mContext, classes));
    }
}
