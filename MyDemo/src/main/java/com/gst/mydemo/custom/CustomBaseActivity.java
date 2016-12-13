package com.gst.mydemo.custom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.mydemo.R;
import com.gst.mydemo.custom.ui.BadgeViewActivity;
import com.gst.mydemo.custom.ui.GridViewActivity;
import com.gst.mydemo.custom.ui.PicShowActivity;
import com.gst.mydemo.custom.ui.ProgressBarActivity;
import com.gst.mydemo.custom.ui.RecycleViewMainActivity;
import com.gst.mydemo.custom.ui.TextWrapActivity;
import com.gst.mydemo.ui.TopActivity;

/**
 * Created by 善同 on 2015/11/2.
 */
public class CustomBaseActivity extends TopActivity implements View.OnClickListener {

    private Button btnBadgeView, btnText, btnGridView, btnPic, btnRecycle, btnProgressBar;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_main);

        init();
        setView();
    }

    private void init() {
        mContext = CustomBaseActivity.this;
    }

    private void setView() {
        setTopView();
        tvTitle.setText("自定义控件");
        btnBadgeView = (Button) findViewById(R.id.btn_badge_view);
        btnText = (Button) findViewById(R.id.btn_custom_text);
        btnGridView = (Button) findViewById(R.id.btn_gridview_view);
        btnPic = (Button) findViewById(R.id.btn_custom_pic);
        btnRecycle = (Button) findViewById(R.id.btn_recycleview_view);
        btnProgressBar = (Button) findViewById(R.id.btn_progress);


        btnBadgeView.setOnClickListener(this);
        btnText.setOnClickListener(this);
        btnGridView.setOnClickListener(this);
        btnPic.setOnClickListener(this);
        btnRecycle.setOnClickListener(this);
        btnProgressBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnBadgeView) { // 数字红背景
            gotoActivity(BadgeViewActivity.class);
        } else if(v == btnText) { // 自定义文本顶格显示
            gotoActivity(TextWrapActivity.class);
        } else if(v == btnGridView) { // GridView自适应
            gotoActivity(GridViewActivity.class);
        } else if(v == btnPic) { // 图片显示
            gotoActivity(PicShowActivity.class);
        } else if(v == btnRecycle) { // 自定义RecycleView
            gotoActivity(RecycleViewMainActivity.class);
        } else if (v == btnProgressBar) { // 进度条
            gotoActivity(ProgressBarActivity.class);
        }
    }

   private void gotoActivity(Class<?> classes) {
        startActivity(new Intent(mContext, classes));
    }

}
