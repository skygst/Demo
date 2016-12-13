package com.gst.mydemo.custom.ui;

import android.graphics.Color;
import android.os.Bundle;

import com.gst.mydemo.R;
import com.gst.mydemo.custom.widget.BadgeView;
import com.gst.mydemo.ui.TopActivity;

/**
 * Created by 善同 on 2015/11/2.
 *
 *  数字 --- 红色背景
 */
public class BadgeViewActivity extends TopActivity {

    private BadgeView tvBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_badge);

        init();
        setView();
    }

    private void init() {
    }

    private void setView() {
        setTopView();
        tvBadge = (BadgeView) findViewById(R.id.tv_badge);
        tvBadge.setBadgePosition(BadgeView.POSITION_CENTER); // 显示的位置.中间
        tvBadge.setBadgeMargin(0, 0); // 水平和竖直方向的间距
        tvBadge.setBadgeBackgroundColor(Color.RED);  //背景颜色
//        tvBadge.setVisibility(View.GONE);

    }

}
