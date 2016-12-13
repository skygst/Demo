package com.gst.move.test_demo;

import android.content.Context;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.data.FixedPosition;
import com.gst.move.R;

/**
 * Created by gst-pc on 2016/9/30.
 */

public class RecorderFinishActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBg, ivMoney, ivWord;
    private ClickImageView ivComments, ivShare, ivList;
    private TextView tvMoney;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_record_finish);

        init();
        setView();
    }

    private void init() {
        mContext = RecorderFinishActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
    }

    private void setView() {
        ivBg = (ImageView) findViewById(R.id.iv_bg);
        tvMoney = (TextView) findViewById(R.id.tv_money);
        ivMoney = (ImageView) findViewById(R.id.iv_money);
        ivWord = (ImageView) findViewById(R.id.iv_good);

        ivComments = (ClickImageView) findViewById(R.id.iv_comments);
        ivShare = (ClickImageView) findViewById(R.id.iv_share);
        ivList = (ClickImageView) findViewById(R.id.iv_list);

        setViewPosition(ivBg, 0);
        setViewPosition(ivMoney, 1);
        setViewPosition(ivWord, 2);
        setViewPosition(ivComments, 3);
        setViewPosition(ivShare, 4);
        setViewPosition(ivList, 5);
        setViewPosition(tvMoney, 6);

        tvMoney.setText("+ " + 20);
        TextPaint paint = tvMoney.getPaint();
        paint.setFakeBoldText(true);

        ivComments.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        ivList.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.record_finish_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivComments) { // 求点评

        } else if(v == ivShare) { // 分享

        } else if(v == ivList) { // 录音列表

        }
    }
}
