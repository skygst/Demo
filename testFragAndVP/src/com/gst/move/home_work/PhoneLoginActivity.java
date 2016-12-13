package com.gst.move.home_work;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.data.FixedPosition;
import com.gst.move.R;

/**
 *  登录页面
 */
public class PhoneLoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBg;
    private ClickImageView ivClose, ivGetCode, ivClickLogin;
    private RelativeLayout rlPhone, rlCode;
    private EditText etPhone, etCode;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_login);

        init();
        setView();
    }

    private void init() {
        mContext = PhoneLoginActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
    }

    private void setView() {
        ivBg = (ImageView) findViewById(R.id.iv_bg);
        ivClose = (ClickImageView) findViewById(R.id.iv_close);
        ivGetCode = (ClickImageView) findViewById(R.id.iv_get_code);
        ivClickLogin = (ClickImageView) findViewById(R.id.iv_one_click_login);
        rlPhone = (RelativeLayout) findViewById(R.id.rl_phone);
        rlCode = (RelativeLayout) findViewById(R.id.rl_code);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etCode = (EditText) findViewById(R.id.et_code);

        setViewPosition(ivBg, 0);
        setViewPosition(ivClose, 1);
        setViewPosition(rlPhone, 2);
        setViewPosition(rlCode, 3);
        setViewPosition(ivGetCode, 4);
        setViewPosition(ivClickLogin, 5);

        ivClose.setOnClickListener(this);
        ivGetCode.setOnClickListener(this);
        ivClickLogin.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.phone_login_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivClose) { // 关闭
            finish();
        } else if(v == ivGetCode) { // 获得验证码

        } else if(v == ivClickLogin) { // 一键登录

        }
    }
}
