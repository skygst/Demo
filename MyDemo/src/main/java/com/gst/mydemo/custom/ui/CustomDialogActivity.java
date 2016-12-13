package com.gst.mydemo.custom.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.mydemo.R;
import com.gst.mydemo.biz.ActivityBiz;
import com.gst.mydemo.biz.DialogBiz;
import com.gst.mydemo.ui.TopActivity;

/**
 * Created by 善同 on 2015/11/3.
 */
public class CustomDialogActivity extends TopActivity implements View.OnClickListener {

    private Context mContext;
    private Button btn1, btn2, btn3, btn4, btn5, btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        init();
        setView();
    }

    private void init() {
        mContext = CustomDialogActivity.this;
    }

    private void setView() {
        setTopView();
        tvTitle.setText("自定义Dialog显示");
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_dialog_activity);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btn1) { // 单按钮
            DialogBiz.loginDialog(mContext);
        } else if(v == btn2) { // 双按钮
            DialogBiz.boundPhoneDialog(mContext);
        } else if(v == btn3) { // 文本列表

        } else if(v == btn4) { //自定义输入框
            DialogBiz.editContentDialog(mContext);
        } else if(v == btn5) { // 自定义
            DialogBiz.showDefineDialog(mContext);
        } else if(v == btn6) { // Activity作为Dialog
            ActivityBiz.gotoActivity(mContext, DialogActivity.class);
        }
    }
}
