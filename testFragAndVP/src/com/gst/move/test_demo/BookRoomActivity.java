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
import com.gst.move.R;

/**
 *  图书馆 页面
 */
public class BookRoomActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack, ivBookRoomTitle, iv1, iv2, ivLine1, ivLine2, ivLine3, ivLine4, ivHonor;
    private ClickImageView ivLevel1, ivLevel2, ivLevel3, ivLevel4, ivLevel5, ivLevel6, ivLevel7, ivLevel8, ivMyRecorder;
   private RelativeLayout rlCommentsTimesBg;
    private TextView tvTimes, tvDate;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_room);

        init();
        setView();
    }

    private void init() {
        mContext = BookRoomActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
    }

    private void setView() {
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0,
                FixedPosition.common_position, scaleQPW, scaleQPH);
        ivBookRoomTitle = (ImageView) findViewById(R.id.iv_book_room_title);
        iv1 = (ImageView) findViewById(R.id.iv_1);
        iv2 = (ImageView) findViewById(R.id.iv_2);
        ivLine1 = (ImageView) findViewById(R.id.iv_line_1);
        ivLine2 = (ImageView) findViewById(R.id.iv_line_2);
        ivLine3 = (ImageView) findViewById(R.id.iv_line_3);
        ivLine4 = (ImageView) findViewById(R.id.iv_line_4);
        ivHonor = (ImageView) findViewById(R.id.iv_honor);

        rlCommentsTimesBg = (RelativeLayout) findViewById(R.id.rl_comments_times_bg);
        tvTimes = (TextView) findViewById(R.id.tv_times);
        tvDate = (TextView) findViewById(R.id.tv_date);

        ivLevel1 = (ClickImageView) findViewById(R.id.iv_level_1);
        ivLevel2 = (ClickImageView) findViewById(R.id.iv_level_2);
        ivLevel3 = (ClickImageView) findViewById(R.id.iv_level_3);
        ivLevel4 = (ClickImageView) findViewById(R.id.iv_level_4);
        ivLevel5 = (ClickImageView) findViewById(R.id.iv_level_5);
        ivLevel6 = (ClickImageView) findViewById(R.id.iv_level_6);
        ivLevel7 = (ClickImageView) findViewById(R.id.iv_level_7);
        ivLevel8 = (ClickImageView) findViewById(R.id.iv_level_8);
        ivMyRecorder = (ClickImageView) findViewById(R.id.iv_my_recorder);

        setViewPosition(ivBookRoomTitle, 0);
        setViewPosition(ivLevel1, 1);
        setViewPosition(ivLevel2, 2);
        setViewPosition(ivLevel3, 3);
        setViewPosition(ivLevel4, 4);
        setViewPosition(ivLevel5, 5);
        setViewPosition(ivLevel6, 6);
        setViewPosition(ivLevel7, 7);
        setViewPosition(ivLevel8, 8);
        setViewPosition(iv1, 9);
        setViewPosition(iv2, 10);
        setViewPosition(ivMyRecorder, 11);
        setViewPosition(rlCommentsTimesBg, 12);
        setViewPosition(ivLine1, 13);
        setViewPosition(ivLine2, 14);
        setViewPosition(ivLine3, 15);
        setViewPosition(ivLine4, 16);
        setViewPosition(ivHonor, 17);

        ivLevel1.setOnClickListener(this);
        ivLevel2.setOnClickListener(this);
        ivLevel3.setOnClickListener(this);
        ivLevel4.setOnClickListener(this);
        ivLevel5.setOnClickListener(this);
        ivLevel6.setOnClickListener(this);
        ivLevel7.setOnClickListener(this);
        ivLevel8.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.book_room_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {

    }
}
