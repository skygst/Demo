package com.gst.move.test_demo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.data.FixedPosition;
import com.gst.move.R;

/**
 *  点评购买介绍页面
 */
public class CommentsIntroductionActivity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout, rlBlackboard;
    private ClickImageView ivBuyIcon;
    private ImageView ivBack, ivTeacher;
    private TextView tvContent;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private final String TAG = "CommentsIntroductionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_info);

        init();
        setView();
    }

    private void init() {
        mContext = CommentsIntroductionActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        rlBlackboard = (RelativeLayout) findViewById(R.id.rl_blackboard);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0,
                FixedPosition.common_position, scaleQPW, scaleQPH);
        ivBuyIcon = (ClickImageView) findViewById(R.id.iv_buy_icon);
        ivTeacher = (ImageView) findViewById(R.id.iv_teacher);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText("\u3000\u3000" + getString(R.string.comments_info_content));

        setViewPosition(rlBlackboard, 0);
        setViewPosition(ivTeacher, 1);
        setViewPosition(ivBuyIcon, 2);

        ivBack.setOnClickListener(this);
        ivBuyIcon.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.comments_info_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) { // 返回
            finish();
        } else if(v == ivBuyIcon) { // 购买
            showDefineDialog();
        }
    }

    private boolean selOneMth = true;

    // 弹出购买选择框
    private void showDefineDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
        dialog.setCancelable(false);
        dialog.show();
        Window window = dialog.getWindow();
        // *** 主要就是在这里实现这种效果的.
        window.setContentView(R.layout.define_dialog_radiobutton);
        RelativeLayout rlBg = (RelativeLayout) window.findViewById(R.id.rl_bg);
        TextView tvTitle = (TextView) window.findViewById(R.id.tv_dialog_title);
        final ImageView ivSel1 = (ImageView) window.findViewById(R.id.iv_sel_1);
        final ImageView ivSel2 = (ImageView) window.findViewById(R.id.iv_sel_2);
        ClickImageView ivLeft = (ClickImageView) window.findViewById(R.id.iv_left);
        ClickImageView ivRight = (ClickImageView) window.findViewById(R.id.iv_right);
        RelativeLayout rlOnce = (RelativeLayout) window.findViewById(R.id.rl_rd_1);
        RelativeLayout rlThird = (RelativeLayout) window.findViewById(R.id.rl_rd_2);
        TextView tv1Content = (TextView) window.findViewById(R.id.tv_1_content);
        TextView tv3Content = (TextView) window.findViewById(R.id.tv_3_content);

        tvTitle.setText("购买点评次数");
        tvTitle.setVisibility(View.VISIBLE);

        rlOnce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selOneMth = true;
                ivSel1.setImageResource(R.drawable.comments_sel);
                ivSel2.setImageResource(R.drawable.comments_del);
            }
        });
        rlThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selOneMth = false;
                ivSel1.setImageResource(R.drawable.comments_del);
                ivSel2.setImageResource(R.drawable.comments_sel);
            }
        });

        ivLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if(selOneMth) { // 购买1个月

                } else { // 购买3个月

                }
            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
