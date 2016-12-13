package com.gst.move.test_demo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.utils.LayoutParameters;
import com.example.location.biz.StringBiz;
import com.gst.move.R;

/**
 * Created by gst-pc on 2016/9/13.
 */
public class ProgressDialogActivity extends BaseActivity implements View.OnClickListener {

    private Button btnClick;
    private Context mContext;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private TextView tvTimes1, tvTimes2;
    private boolean isShowTimes = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);

        init();
        setView();
    }

    private void init() {
        mContext = ProgressDialogActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        System.out.println("------width------" + width);
        System.out.println("------height------" + height);
    }

    private void setView() {
        btnClick = (Button) findViewById(R.id.btn_click);
        tvTimes1 = (TextView) findViewById(R.id.tv_times_1);
        tvTimes2 = (TextView) findViewById(R.id.tv_times_2);

        showTextView();
        btnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnClick) {
            showDefineDialog(true);
        }
    }

    private void showTextView() {
        int times= 0;
        if(isShowTimes) {
            // TODO 显示次数
            times = 12;
            tvTimes1.setBackgroundResource(R.drawable.reviews_number_bg_1);
            tvTimes1.setText(StringBiz.commentsTimesString(mContext, " 剩余点评次数: ", times + "", "   (有效期至2016.10.16)", 0xffffffff, 0xfffbdd32));
            tvTimes2.setVisibility(View.GONE);
        } else {
            // TODO 显示次数
            tvTimes1.setBackgroundResource(R.drawable.reviews_number_bg_1);
            tvTimes1.setText(StringBiz.commentsTimesString(mContext, " 请老师为宝贝的阅读打分 ", "", "", 0xffffffff, 0xffffffff));
            tvTimes2.setVisibility(View.GONE);
        }
    }

    private void showDefineDialog(boolean status) {
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        // *** 主要就是在这里实现这种效果的.
        window.setContentView(R.layout.define_dialog_3);
        RelativeLayout rlBg = (RelativeLayout) window.findViewById(R.id.rl_bg);
        setViewPosition(rlBg, 619, 487, true, false, 0);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_title);
        ImageView ivPlayBg = (ImageView) window.findViewById(R.id.iv_play_bg);
        ClickImageView ivPlay = (ClickImageView) window.findViewById(R.id.iv_play);
        ClickImageView ivSave = (ClickImageView) window.findViewById(R.id.iv_save);
        ClickImageView ivContinue = (ClickImageView) window.findViewById(R.id.iv_continue);
        ClickImageView ivGiveUp = (ClickImageView) window.findViewById(R.id.iv_give_up);

        if(!status) {
            tvTitle.setText("录音尚未完成...");
            ivContinue.setVisibility(View.VISIBLE);
            setViewBelowPosition(ivContinue, 114, 137, R.id.tv_plan_name, true, R.id.iv_save);
        } else {
            tvTitle.setText("录音完成");
            ivContinue.setVisibility(View.GONE);
        }

        setViewPosition(ivPlayBg, 296, 138, R.id.view_2, 0);
        setViewPosition(ivPlay, 77, 76, R.id.view_2, 10);
        if(!status) {
            setViewBelowPosition(ivSave, 114, 137, R.id.tv_plan_name);
            setViewBelowPosition(ivGiveUp, 114, 137, R.id.tv_plan_name, false, R.id.iv_save);
        } else {
            setViewBelowPosition(ivSave, 114, 137, R.id.tv_plan_name, true, R.id.view_below, 10);
            setViewBelowPosition(ivGiveUp, 114, 137, R.id.tv_plan_name, false, R.id.view_below, 10);
        }

        ivPlay.setOnClickListener(new View.OnClickListener() { // 播放
            @Override
            public void onClick(View v) {

            }
        });
        ivSave.setOnClickListener(new View.OnClickListener() { // 保存
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ivContinue.setOnClickListener(new View.OnClickListener() { // 继续
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ivGiveUp.setOnClickListener(new View.OnClickListener() { // 放弃
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false); // 禁止使用返回键
    }

//    private void showDefineDialog1() {
//        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
//        dialog.setCancelable(false);
//        dialog.show();
//        Window window = dialog.getWindow();
//        // *** 主要就是在这里实现这种效果的.
//        window.setContentView(R.layout.define_comments_to_buy_dialog);
//
//        RelativeLayout rlBg = (RelativeLayout) window.findViewById(R.id.rl_bg);
//        setViewPosition(rlBg, 585, 659, true, false, 0);
//        TextView tvContent = (TextView) window.findViewById(R.id.tv_dialog_content_1);
//
//        ClickImageView ivBuy = (ClickImageView) window.findViewById(R.id.iv_to_buy);
//        setViewPosition(ivBuy, 152, 152, false, true, R.id.view_center);
//        ClickImageView ivCancel = (ClickImageView) window.findViewById(R.id.iv_cancel);
//        setViewPosition(ivCancel, 152, 152, false, false, R.id.view_center);
//
//        tvContent.setText(StringBiz.buyCommentsTimesString(mContext, "2016-10-30", "3", 0xff444444, 0xffff8486, 0xff999999));
//
//        ivCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        ivBuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 去购买
//                dialog.dismiss();
//            }
//        });
//    }

    private void setViewPosition(View iv, int width, int height, boolean isCenter, boolean isLeft, int viewId) {
        RelativeLayout.LayoutParams params = LayoutParameters
                .setViewBuyDialogParams(width, height, scaleQPW, scaleQPH, isCenter, isLeft, viewId);
        iv.setLayoutParams(params);
    }
    private void setViewPosition(View iv, int width, int height, int aboveId, int bottomValue) {
        RelativeLayout.LayoutParams params = LayoutParameters
                .setViewBuyDialogParams2(width, height, scaleQPW, scaleQPH, aboveId, bottomValue);
        iv.setLayoutParams(params);
    }
    private void setViewBelowPosition(View iv, int width, int height, int belowId) {
        RelativeLayout.LayoutParams params = LayoutParameters
                .setViewBuyDialogParams3(width, height, scaleQPW, scaleQPH, belowId);
        iv.setLayoutParams(params);
    }
    private void setViewBelowPosition(View iv, int width, int height, int belowId, boolean isLeft, int id) {
        RelativeLayout.LayoutParams params = LayoutParameters
                .setViewBuyDialogParams4(width, height, scaleQPW, scaleQPH, belowId, isLeft, id);
        iv.setLayoutParams(params);
    }
    private void setViewBelowPosition(View iv, int width, int height, int belowId, boolean isLeft, int id, int value) {
        RelativeLayout.LayoutParams params = LayoutParameters
                .setViewBuyDialogParams5(width, height, scaleQPW, scaleQPH, belowId, isLeft, id, value);
        iv.setLayoutParams(params);
    }

}
