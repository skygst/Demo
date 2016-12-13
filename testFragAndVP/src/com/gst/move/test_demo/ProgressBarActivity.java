package com.gst.move.test_demo;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.ebodoo.raz.BaseActivity;
import com.gst.move.R;
import com.gst.move.custom.SpringProgressView;

/**
 * Created by gst-pc on 2016/9/8.
 */
public class ProgressBarActivity extends BaseActivity {

    private SpringProgressView sp;
    private ProgressBar pbNengli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_1);
        
        init();
        setView();
System.out.println("------------width-----------" + width);
System.out.println("------------height-----------" + height);
    }

    private void init() {
    }

    private void setView() {
        sp = (SpringProgressView) findViewById(R.id.sp);
        pbNengli = (ProgressBar) findViewById(R.id.pb_nengli);
        sp.setMaxCount(1000.0f);
        sp.setCurrentCount(550.0f);

        pbNengli.setProgress(50);
    }
}
