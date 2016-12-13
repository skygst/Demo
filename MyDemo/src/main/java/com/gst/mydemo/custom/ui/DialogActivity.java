package com.gst.mydemo.custom.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.gst.mydemo.R;
import com.gst.mydemo.linstener.ShowBigPicListener;
import com.gst.mydemo.ui.BaseActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *  Activity作为Dialog使用, 并且实现apk的下载、安装、打开
 *
 * Created by 善同 on 2016/1/19.
 */
public class DialogActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rlReceive, rlView, rlTvView;
    private ImageView ivPic, ivClose;
    private Button btnLoad;
    private ImageLoader mImageLoader;
    private static final String URL_STRING = "http://gdown.baidu.com/data/wisegame/b7d7e4efd8199dea/tianyiyuedu_310.apk";
    private static int down = 0;
    private File file;
    private Context mContext;
    private final String TAG = "LineStyleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ad_view);

        init();
        setView();
    }

    private void init() {
        mContext = DialogActivity.this;
        mImageLoader = ImageLoader.getInstance();
    }

    private void setView() {
        rlView = (RelativeLayout) findViewById(R.id.rl_view);
        rlTvView = (RelativeLayout) findViewById(R.id.rl_tv_view);
        rlReceive = (RelativeLayout) findViewById(R.id.rl_receive);
        ivPic = (ImageView) findViewById(R.id.iv_pic);
        ivClose = (ImageView) findViewById(R.id.iv_close);
        btnLoad = (Button) findViewById(R.id.btn_down_load);
        GradientDrawable drawable = (GradientDrawable) rlReceive.getBackground();
        drawable.setColor(Color.parseColor("#FFA036"));
        GradientDrawable drawable2 = (GradientDrawable) rlTvView.getBackground();
        drawable2.setColor(Color.parseColor("#ff7376"));

        String path = "http://img.bbpapp.com/uploads/stories/2015_11_27/9591522708ef71e6c2f77c8fd89aac3c.jpg";
//		path = "drawable://" + R.mipmap.xxxx;
        int width = getViewWidth(rlView);
        mImageLoader.displayImage(path, ivPic, new ShowBigPicListener(width));

        btnLoad.setOnClickListener(this);
        ivClose.setOnClickListener(this);
    }

    // 获取控件的宽高
    private int getViewWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();
        System.out.println("measure width=" + width + " height=" + height);
        return width;
    }

    private void getWidth(final View view) {

        ViewTreeObserver vto = view.getViewTreeObserver();

        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            @Override
            public boolean onPreDraw() {
                int height = view.getMeasuredHeight();
                int width = view.getMeasuredWidth();
                Log.d(TAG, "----width---" + width);
                Log.d(TAG, "----height---" + height);
                return false;
            }

        });

    }

    @Override
    public void onClick(View v) {
        if(v == ivClose) {
            this.finish();
        } else if(v == btnLoad) { // 下载
                // 下载apk
            if (down == 0) {
                downFile(URL_STRING);
                btnLoad.setText("正在下载");
                // 安装APK
            } else if (down == 1) {
                installApk();
                // 打开apk
            } else if (down == 2) {
                openApk(mContext, URL_STRING);
            }
        }
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    btnLoad.setText("点击安装");
                    down = 1;
                    break;
                case 2:
                    down = 2;
                    btnLoad.setText("打开");
                    break;
            }
        }

    };

    // 接收到安装完成apk的广播
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            System.out.println("接收到安装完成apk的广播");

            Message message = handler.obtainMessage();
            message.what = 2;
            handler.sendMessage(message);
        }
    };

    /**
     * 后台在下面一个Apk 下载完成后返回下载好的文件
     *
     * @param httpUrl
     * @return
     */
    private File downFile(final String httpUrl) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    URL url = new URL(httpUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    FileOutputStream fileOutputStream = null;
                    InputStream inputStream;
                    if (connection.getResponseCode() == 200) {
                        inputStream = connection.getInputStream();

                        if (inputStream != null) {
                            file = getFile(httpUrl);
                            fileOutputStream = new FileOutputStream(file);
                            byte[] buffer = new byte[1024];
                            int length = 0;

                            while ((length = inputStream.read(buffer)) != -1) {
                                fileOutputStream.write(buffer, 0, length);
                            }
                            fileOutputStream.close();
                            fileOutputStream.flush();
                        }
                        inputStream.close();
                    }

                    System.out.println("已经下载完成");
                    // 往handler发送一条消息 更改button的text属性
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    handler.sendMessage(message);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return file;
    }

    /**
     * 安装APK
     */
    private void installApk() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive"); // .apk的MIME类型application/vnd.android.package-archive
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addDataScheme("package");

        // 注册一个广播
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 解除广播
        unregisterReceiver(broadcastReceiver);
    }

    /**
     * 打开已经安装好的apk
     */
    private void openApk(Context context, String url) {
        PackageManager manager = context.getPackageManager();
        // 这里的是你下载好的文件路径
        PackageInfo info = manager.getPackageArchiveInfo(Environment.getExternalStorageDirectory().getAbsolutePath()
                + getFilePath(url), PackageManager.GET_ACTIVITIES);
        if (info != null) {
            Intent intent = manager.getLaunchIntentForPackage(info.applicationInfo.packageName);
            startActivity(intent);
        }
    }

    /**
     * 根据传过来url创建文件
     *
     */
    private File getFile(String url) {
        File files = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), getFilePath(url));
        return files;
    }

    /**
     * 截取出url后面的apk的文件名
     *
     * @param url
     * @return
     */
    private String getFilePath(String url) {
        return url.substring(url.lastIndexOf("/"), url.length());
    }

}
