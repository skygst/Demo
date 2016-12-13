package com.gst.mydemo.ui.controls;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.gst.mydemo.R;
import com.gst.mydemo.ui.BaseActivity;
import com.gst.mydemo.view.ChartProp;
import com.gst.mydemo.view.ChartView;

import java.util.List;

/**
 *  饼图
 * Created by 善同 on 2016/2/17.
 */
public class PieActivity extends BaseActivity {
        ChartView mChartView;

        int color[] = new int[]{Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW,Color.WHITE};
        float percent[] = new float[]{0.2f, 0.3f, 0.1f, 0.35f, 0.05f};
        String names[] = new String[]{"Project1","Project2","Project3","Project4","Project5"};
        int fontSize[] = new int[]{40,40,40,40,40};

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pie_view);


            mChartView = (ChartView)this.findViewById(R.id.chartView);
            mChartView.setAntiAlias(true);
            /**
             *  设置饼状图的中心点
              */
            mChartView.setCenter(new Point(300, 200));
            mChartView.setR(150);
            mChartView.setStartAngle(30);
            mChartView.setWizardLineLength(30);

            List<ChartProp> acps = mChartView.createCharts(5);
            int size = acps.size();
            for(int i = 0; i < size; i++)
            {
                ChartProp chartProp = acps.get(i);
                chartProp.setColor(color[i]);
                chartProp.setPercent(percent[i]);
                chartProp.setName(names[i]);
                chartProp.setFontSize(fontSize[i]);
            }
        }
    }
