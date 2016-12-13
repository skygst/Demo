package com.gst.mydemo.ui.controls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gst.mydemo.R;
import com.gst.mydemo.ui.MainActivity;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 善同 on 2016/2/17.
 */
public class PieChartBuilder extends Activity {

    private Timer timer = new Timer();// 设计定时器
    private TimerTask task;// 定时任务
    private Handler handler;// 线程通讯
    private String title = "动态饼图示例";// 饼图标题
    private CategorySeries mSeries;// 饼图数据
    private DefaultRenderer mRenderer;// 饼图描绘器
    private GraphicalView mChartView;// 显示PieChart
    private Context context;
    private double data[] = new double[9];
    private LinearLayout mLinear;// 布局方式
    private int[] COLORS = new int[] { Color.RED, Color.GREEN, Color.BLUE,
            Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.DKGRAY };// 颜色
    private double VALUE = 0;// 总数
    private SimpleSeriesRenderer renderer;// 饼图每块描绘器

    public void back(View v) {
        Log.i("qiuzhping", "back onClick");
        Intent intent = new Intent();
        intent.setClass(PieChartBuilder.this, MainActivity.class);
        startActivity(intent);
        PieChartBuilder.this.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();// 获取上下文对象
        setContentView(R.layout.xy_chart);// 设置样式

        mLinear = (LinearLayout) findViewById(R.id.chart);// 获取mLinear布局，下面会把图表画在这个布局里面
        mLinear.setBackgroundColor(Color.BLACK);// 设置背景色

        mRenderer = new DefaultRenderer();// 创建一个描绘器的实例，将被用来创建图表
        mRenderer.setZoomButtonsVisible(true);// 显示放大缩小功能按钮
        mRenderer.setStartAngle(180);// 设置为水平开始
//        mRenderer.setDisplayValues(true);// 显示数据
        // mRenderer.setFitLegend(false);// 设置是否显示图例
        // mRenderer.setLegendTextSize(10);// 设置图例字体大小
        // mRenderer.setLegendHeight(10);// 设置图例高度
        mRenderer.setShowLegend(false);// 默认是显示的下载需要关闭，因为动态更新数据的时候，图例更新慢
        mRenderer.setChartTitle(title);// 设置饼图标题
        mRenderer.setChartTitleTextSize(14);// 设置饼图标题大小

        mSeries = new CategorySeries("");

        for (int i = 0; i < 9; i++) {
            Random random = new Random();
            int R = random.nextInt(255);
            Log.i("qiuzhping", "Random R=" + R);
            data[i] = R;
            VALUE += data[i];// 总的数据大小
        }
        for (int i = 0; i < data.length; i++) {
            mSeries.add("示例 " + (i + 1), data[i] / VALUE);// 设置种类名称和对应的数值，前面是（key,value）键值对
            renderer = new SimpleSeriesRenderer();
            if (i < COLORS.length) {
                renderer.setColor(COLORS[i]);// 设置描绘器的颜色
            } else {
                renderer.setColor(getRandomColor());// 设置描绘器的颜色
            }
            DecimalFormat df1 = new DecimalFormat(".00");
//            renderer.setChartValuesFormat(NumberFormat.getPercentInstance());// 设置百分比
//            renderer.setChartValuesFormat(df1);// 设置百分比
            mRenderer.addSeriesRenderer(renderer);// 将最新的描绘器添加到DefaultRenderer中
        }

        mChartView = ChartFactory.getPieChartView(context, mSeries, mRenderer);// 构建mChartView

        mLinear.addView(mChartView, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,
                RelativeLayout.LayoutParams.FILL_PARENT));

        handler = new Handler() {// 这里的Handler实例将配合下面的Timer实例，完成定时更新图表的功能
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    updateChart();// 刷新图表具体方法 Handler将此并入主线程
                }
                super.handleMessage(msg);
            }
        };
        task = new TimerTask() {
            @Override
            public void run() {// 通过消息更新
                Log.i("task", " task ok ");
                Message message = new Message();
                message.what = 1;// 消息定义标志
                handler.sendMessage(message);
            }
        };

        timer.schedule(task, 500, 1000 * 10);// 执行任务
    }

    @Override
    public void onDestroy() {// 当结束程序时关掉Timer
        if (timer != null) {
            timer.cancel();
            Log.i("qiuzhping", "onDestroy timer cancel ");
        }
        super.onDestroy();
    }

    private void updateChart() {
        Log.i("qiuzhping", "updateChart ok");
        mSeries.clear();
        VALUE = 0;// 初始化
        // mRenderer.removeAllRenderers();
        for (int i = 0; i < 9; i++) {// 产生动态数据，实际项目中可以通过Web Service
            // 获取数据，不过这个内容应该放在线程上搞，太耗时了
            Random random = new Random();
            int R = random.nextInt(255);
            Log.i("qiuzhping", "Random R=" + R);
            data[i] = R;
            VALUE += data[i];// 总的数据大小
        }
        for (int i = 0; i < data.length; i++) {
            mSeries.add("示例 " + (i + 1), data[i] / VALUE);// 设置种类名称和对应的数值，前面是（key,value）键值对
            renderer = new SimpleSeriesRenderer();
            if (i < COLORS.length) {
                renderer.setColor(COLORS[i]);// 设置描绘器的颜色
            } else {
                renderer.setColor(getRandomColor());// 设置描绘器的颜色
            }
//            renderer.setChartValuesFormat(NumberFormat.getPercentInstance());// 设置百分比

            mRenderer.addSeriesRenderer(renderer);// 将最新的描绘器添加到DefaultRenderer中
        }
        mChartView.repaint();
    }

    private int getRandomColor() {// 分别产生RBG数值
        Random random = new Random();
        int R = random.nextInt(255);
        int G = random.nextInt(255);
        int B = random.nextInt(255);
        return Color.rgb(R, G, B);
    }
}

