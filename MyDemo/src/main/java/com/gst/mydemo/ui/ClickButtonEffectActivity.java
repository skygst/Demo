package com.gst.mydemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.mydemo.R;

/**
 *  按钮点击效果
 * Created by 善同 on 2016/2/14.
 */
public class ClickButtonEffectActivity extends Activity implements View.OnClickListener {

    private Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_click_effect);

        init();
        setView();
    }

    private void init() {

    }

    private void setView() {
        btn1 = (Button) findViewById(R.id.btn_1);
        btn1.setBackgroundResource(R.drawable.button_color); // 渐变效果
        btn1.setBackgroundResource(R.drawable.state_selected);

        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);

        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btn3) {

        }
    }
}
