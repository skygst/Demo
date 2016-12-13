package com.gst.move.level_c_game;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.utils.LayoutParameters;
import com.example.location.biz.StringBiz;
import com.gst.move.R;

import static com.gst.move.R.id.btn_1;

/**
 * Created by gst-pc on 2016/10/19.
 */

public class DialogTestActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Button btnClick, btn1, btn2, btn3, btn4, btn5;
    private ImageView ivBg;
    private boolean isGoneBg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_dialog);

        init();
        setView();
    }

    private void init() {
        mContext = DialogTestActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        System.out.println("----------width----------" + width + "----------height-----------" + height);
        biz = new VideoBiz();
    }

    private void setView() {
        btnClick = (Button) findViewById(R.id.btn_click);
        btn1 = (Button) findViewById(btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        ivBg = (ImageView) findViewById(R.id.iv_bg);

        btnClick.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    private boolean isToBuy = false;

    @Override
    public void onClick(View v) {
        if(v == btnClick) {
            isToBuy = !isToBuy;
            showDefineDialog(isToBuy);
//            showDefineDialog22();
        } else if(v == btn1) {
//            ivBg.setVisibility(View.VISIBLE);
//            isGoneBg = true;
//            gotoGame(MedalActivity.class);
//            gotoGame(MedalDetailActivity.class);
            gotoGame(EbookLevelCGame6Activity.class);
        } else if(v == btn2) {
            gotoGame(EbookLevelCGame7Activity.class);
        } else if(v == btn3) {
            gotoGame(EbookLevelCGame8Activity.class);
        } else if(v == btn4) {
            gotoGame(EbookLevelCGame9Activity.class);
        } else if(v == btn5) {
            gotoGame(EbookLevelCGame10Activity.class);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("---------- onResume() ---------------");
        if(isGoneBg) {
            isGoneBg = false;
            ivBg.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("---------- onPause() ---------------");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("---------- onStop() ---------------");
    }

    private void gotoGame(Class<?> classes) {
        startActivity(new Intent(mContext, classes));
    }

    private void showDefineDialog(boolean isToBuy) {
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.show();
        Window window = dialog.getWindow();
        // *** 主要就是在这里实现这种效果的.
        window.setContentView(R.layout.define_comment_buy_dialog);
        RelativeLayout rlBg = (RelativeLayout) window.findViewById(R.id.rl_bg);
        setViewPosition(rlBg, 619, 487, true, false, 0);
        TextView tvContent = (TextView) window.findViewById(R.id.tv_content);
        ClickImageView ivLeft = (ClickImageView) window.findViewById(R.id.iv_left);
        ClickImageView ivRight = (ClickImageView) window.findViewById(R.id.iv_right);

        setViewBelowPosition(ivLeft, 102, 137, R.id.tv_plan_name, true, R.id.view_below, 30);
        setViewBelowPosition(ivRight, 102, 137, R.id.tv_plan_name, false, R.id.view_below, 30);
        String time = "2016.10.19";
        String times = "5";
        if(!isToBuy) {
            ivLeft.setImageResource(R.drawable.icon_renew);
            tvContent.setText(StringBiz.buyCommentsTimesString(mContext,
                    time, times, 0xff444444, 0xfffe4627, 0xff6d4a3c));
        } else {
            ivLeft.setImageResource(R.drawable.icon_buy);
            tvContent.setText(StringBiz.buyCommentsTimesSorryString(mContext,
                    0xfffe4627, 0xff6d4a3c));
        }
        ivLeft.setOnClickListener(new View.OnClickListener() { // 提交点评
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // TODO 提交点评

            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() { // 取消
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.setCanceledOnTouchOutside(true);
    }

    private void showDefineDialog22() {
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.show();
        Window window = dialog.getWindow();
        // *** 主要就是在这里实现这种效果的.
        window.setContentView(R.layout.define_comment_dialog);
        RelativeLayout rlBg = (RelativeLayout) window.findViewById(R.id.rl_bg);
        setViewPosition(rlBg, 619, 487, true, false, 0);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_title);
        TextView tvContent = (TextView) window.findViewById(R.id.tv_content);
        ImageView ivIcon = (ImageView) window.findViewById(R.id.iv_icon);
        ClickImageView ivLeft = (ClickImageView) window.findViewById(R.id.iv_left);
        ClickImageView ivRight = (ClickImageView) window.findViewById(R.id.iv_right);

        tvTitle.setText("是否去点评？");
        setViewPosition(ivIcon, 140, 145, 0, 0);
        setViewBelowPosition(ivLeft, 102, 137, R.id.tv_plan_name, true, R.id.view_below, 30);
        setViewBelowPosition(ivRight, 102, 137, R.id.tv_plan_name, false, R.id.view_below, 30);
        StringBiz.setTextPaint(tvTitle);
        tvTitle.setTextColor(getResources().getColor(R.color.brown1));
        tvContent.setTextColor(Color.parseColor("#6d4a3c"));
        tvContent.setText(StringBiz.commentsTimesString(mContext,
                "剩余点评次数: ", "7", "", getResources().getColor(R.color.brown1), getResources().getColor(R.color.orange1), 14.0f, 18.0f));
        ivLeft.setOnClickListener(new View.OnClickListener() { // 提交点评
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                // TODO 提交点评

            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() { // 取消
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.setCanceledOnTouchOutside(true);
    }

    private void showDefineDialog11(boolean status) {
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.show();
        Window window = dialog.getWindow();
        // *** 主要就是在这里实现这种效果的.
        window.setContentView(R.layout.define_recorder_dialog);
        RelativeLayout rlBg = (RelativeLayout) window.findViewById(R.id.rl_bg);
        setViewPosition(rlBg, 619, 487, true, false, 0);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_title);
        ImageView ivPlayBg = (ImageView) window.findViewById(R.id.iv_play_bg);
        final ClickImageView ivPlay = (ClickImageView) window.findViewById(R.id.iv_play);
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
        dialog.setCanceledOnTouchOutside(true);
    }

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
