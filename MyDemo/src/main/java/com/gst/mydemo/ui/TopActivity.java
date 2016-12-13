package com.gst.mydemo.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gst.mydemo.R;

/**
 * Created by 善同 on 2015/11/2.
 */
public class TopActivity extends BaseActivity {

    public RelativeLayout rlTitleBar;
    public Button btnBack, btnRight;
    public TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setTopView() {
        rlTitleBar = (RelativeLayout) findViewById(R.id.title_styles);
        btnBack = (Button) rlTitleBar.findViewById(R.id.btn_back);
        btnRight = (Button) rlTitleBar.findViewById(R.id.btn_right);
        tvTitle = (TextView) rlTitleBar.findViewById(R.id.tv_title);
        tvTitle.setTextColor(Color.WHITE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
