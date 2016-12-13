package com.gst.mydemo.ui.controls;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.mydemo.R;
import com.gst.mydemo.ui.BaseActivity;
import com.gst.mydemo.view.LineChartView;

/**
 * Created by 善同 on 2016/2/17.
 */
public class LineChartActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_chat_view);
        LineChartView myView = (LineChartView) this.findViewById(R.id.myView);
        System.out.println("1");
        myView.SetInfo(new String[] { "7-11", "7-12", "7-13", "7-14", "7-15",
                        "7-16", "7-17", "7-18", "7-19", "7-20", "7-21" }, // X轴刻度
                new String[] { "", "50", "100", "150", "200", "250", "300", "350", "400", "450" }, // Y轴刻度
                new int[] { 85, 43, 100, 26, 45, 10, 120, 80, 50, 70, 10 }, // 数据
                "图标的标题");

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() { // 为返回按钮添加监听

            public void onClick(View v) {
                finish();
            }
        });
    }
}
