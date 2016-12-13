package com.gst.mydemo.custom.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gst.mydemo.R;
import com.gst.mydemo.custom.widget.PieTestProgress;
import com.gst.mydemo.custom.widget.ProgressWheel;
import com.gst.mydemo.custom.widget.SpringProgressView;
import com.gst.mydemo.ui.BaseActivity;

import static com.gst.mydemo.R.id.sp;
import static com.gst.mydemo.R.id.sp_2;

/**
 * 不同进度条的样式
 * Created by gst-pc on 2016/9/9.
 */
public class ProgressBarActivity extends BaseActivity {

    private SpringProgressView sProgress, sp2;

    private ProgressWheel pwOne, pwTwo;
    private PieTestProgress mPieProgress1, mPieProgress2;
    boolean wheelRunning, pieRunning;
    int wheelProgress = 0, pieProgress = 0;
    private ProgressDialog pbDialog;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);

        init();
        setView();
    }

    private void init() {
        mContext = ProgressBarActivity.this;
        pbDialog = new ProgressDialog(mContext);
        pbDialog.setMessage("手动自定义ProgressDialog");
        pbDialog.show();
    }

    private void setView() {
        sProgress = (SpringProgressView) findViewById(sp);
        sp2 = (SpringProgressView) findViewById(sp_2);
        sProgress.setMaxCount(500.0f);
        sProgress.setCurrentCount(420.0f);
        sp2.setMaxCount(100.0f);
        sp2.setCurrentCount(20.0f);

        pwOne = (ProgressWheel) findViewById(R.id.progress_bar_one);
        pwOne.spin();
        pwTwo = (ProgressWheel) findViewById(R.id.progress_bar_two);
        new Thread(r).start();

        mPieProgress1 = (PieTestProgress) findViewById(R.id.pie_progress1);
        mPieProgress2 = (PieTestProgress) findViewById(R.id.pie_progress2);
        new Thread(indicatorRunnable).start();

        Button startBtn = (Button) findViewById(R.id.btn_start);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!wheelRunning) {
                    wheelProgress = 0;
                    pwTwo.resetCount();
                    new Thread(r).start();
                }
            }
        });

        Button pieStartBtn = (Button) findViewById(R.id.btn_start2);
        pieStartBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!pieRunning) {
                    pieProgress = 0;
                    new Thread(indicatorRunnable).start();
                }
            }
        });
    }

    final Runnable r = new Runnable() {
        public void run() {
            wheelRunning = true;
            while (wheelProgress < 361) {
                pwTwo.incrementProgress();
                wheelProgress++;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            wheelRunning = false;
        }
    };

    final Runnable indicatorRunnable = new Runnable() {
        public void run() {
            pieRunning = true;
            while (pieProgress < 361) {
                mPieProgress1.setProgress(pieProgress);
                mPieProgress2.setProgress(pieProgress);
                pieProgress += 2;;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pieRunning = false;
        }
    };
}
