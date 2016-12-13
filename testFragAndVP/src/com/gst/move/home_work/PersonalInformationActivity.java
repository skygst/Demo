package com.gst.move.home_work;

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
 *  个人信息 界面
 */
public class PersonalInformationActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBg, ivHeadBg, ivHead;
    private TextView tvName, tvStarNum, tvClass, tvXuehao, tvRanking, tvExitLogin;
    private RelativeLayout rlInfoBg;
    private ClickImageView ivClose;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);

        init();
        setView();
    }

    private void init() {
        mContext = PersonalInformationActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
    }

    private void setView() {
        ivBg = (ImageView) findViewById(R.id.iv_bg);
        ivHeadBg = (ImageView) findViewById(R.id.iv_head_bg);
        ivHead = (ImageView) findViewById(R.id.iv_head);
        ivClose = (ClickImageView) findViewById(R.id.iv_close);
        tvName = (TextView) findViewById(R.id.tv_name);
        tvStarNum = (TextView) findViewById(R.id.tv_star_num);
        rlInfoBg = (RelativeLayout) findViewById(R.id.rl_info_bg);
        tvClass = (TextView) findViewById(R.id.tv_class);
        tvXuehao = (TextView) findViewById(R.id.tv_xuehao);
        tvRanking = (TextView) findViewById(R.id.tv_ranking);
        tvExitLogin = (TextView) findViewById(R.id.tv_exit_login);

        setViewPosition(ivBg, 0);
        setViewPosition(ivClose, 1);
        setViewPosition(ivHeadBg, 2);
        setViewPosition(ivHead, 3);
        setViewPosition(tvStarNum, 4);
        setViewPosition(rlInfoBg, 5);
        StringBiz.setTextPaint(tvName);
        StringBiz.setTextPaint(tvStarNum);
        ivClose.setOnClickListener(this);
        tvExitLogin.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.personal_information_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if (v == ivClose) {
            finish();
        } else if(v == tvExitLogin) { // 退出登录

        }
    }
}
