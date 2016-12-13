package com.gst.mydemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.mydemo.R;
import com.gst.mydemo.custom.CustomBaseActivity;
import com.gst.mydemo.custom.ui.CustomDialogActivity;
import com.gst.mydemo.custom.ui.LineStyleActivity;
import com.gst.mydemo.ui.controls.ControlsActivity;
import com.gst.mydemo.ui.controls.ListViewActivity;
import com.gst.mydemo.ui.controls.LockScreenActivity;

public class MainActivity extends TopActivity implements View.OnClickListener {

    private Button btnCustom, btnDialog, btnLine, btnWebView, btnAnimation, btnListView,
            btnControls, btnEffect, btnLockScreen, btnDbTest;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setView();
    }

    private void init() {
        mContext = MainActivity.this;
    }

    private void setView() {
        setTopView();
        tvTitle.setText("我的demo");
        btnCustom = (Button) findViewById(R.id.btn_btnCustom);
        btnDialog = (Button) findViewById(R.id.btn_custom_dialog);
        btnLine = (Button) findViewById(R.id.btn_lines);
        btnWebView = (Button) findViewById(R.id.btn_webview);
        btnAnimation = (Button) findViewById(R.id.btn_animation);
        btnListView = (Button) findViewById(R.id.btn_listView_value);
        btnControls = (Button) findViewById(R.id.btn_controls);
        btnEffect = (Button) findViewById(R.id.btn_click_effect);
        btnLockScreen = (Button) findViewById(R.id.btn_lock_screen);
        btnDbTest = (Button) findViewById(R.id.btn_db_test);


        btnCustom.setOnClickListener(this);
        btnDialog.setOnClickListener(this);
        btnLine.setOnClickListener(this);
        btnWebView.setOnClickListener(this);
        btnAnimation.setOnClickListener(this);
        btnListView.setOnClickListener(this);
        btnControls.setOnClickListener(this);
        btnEffect.setOnClickListener(this);
        btnLockScreen.setOnClickListener(this);
        btnDbTest.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btnCustom) { // 自定义控件
            gotoActivity(CustomBaseActivity.class);
        } else if (v == btnDialog) { // 自定义dialog
            gotoActivity(CustomDialogActivity.class);
        } else if(v == btnLine) { // 虚线的使用
            gotoActivity(LineStyleActivity.class);
        } else if(v == btnWebView) { // WebView的使用
            gotoActivity(WebViewActivity.class);
        } else if(v == btnAnimation) { // 动画
//            gotoActivity(AnimationActivity.class);
            gotoActivity(Animation2Activity.class);
        } else if(v == btnListView) { // ListView
            gotoActivity(ListViewActivity.class);
//            gotoActivity(ImageViewClickActivity.class);
        } else if (v == btnControls) { // 不同控件的使用
            gotoActivity(ControlsActivity.class);
        } else if(v == btnEffect) { // 按钮的点击效果
            gotoActivity(ClickButtonEffectActivity.class);
        } else if (v == btnLockScreen) { // 手势解锁
            gotoActivity(LockScreenActivity.class);
        } else if(v == btnDbTest) { // 数库
            gotoActivity(DataBaseActivity.class);
        }
    }

    private void gotoActivity(Class<?> classes) {
        startActivity(new Intent(mContext, classes));
    }
}
