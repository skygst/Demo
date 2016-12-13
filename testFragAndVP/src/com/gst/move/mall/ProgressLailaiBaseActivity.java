package com.gst.move.mall;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.gst.move.R;

public class ProgressLailaiBaseActivity extends BaseActivity {

    public RelativeLayout rlDefineProgress;
    private TextView tvProcessDes;
    private ImageView ivLailai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setTopView(String processDes) {
        rlDefineProgress = (RelativeLayout) findViewById(R.id.rl_define_progress);
        ivLailai = (ImageView) findViewById(R.id.iv_progress_lailai);
        tvProcessDes = (TextView) rlDefineProgress.findViewById(R.id.tv_progress_des);
        tvProcessDes.setText(processDes);
        ivLailai.setImageResource(R.drawable.lailai_move);
    }

    public void startAnimationView() {
        AnimationDrawable animationDrawable = (AnimationDrawable) ivLailai.getDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.start();
    }

}
