package com.gst.move.mall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.LayoutParameters;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.utils.ImageUtil;

import java.io.File;

/**
 * 联系客服 界面
 */
public class ContactServiceActivity extends ProgressLailaiBaseActivity implements ReWebChomeClient.OpenFileChooserCallBack {

    private RelativeLayout rlLayout;
    private WebView webView;
    private Context mContext;
    private String url;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;

    private static final int REQUEST_CODE_PICK_IMAGE = 0;
    private static final int REQUEST_CODE_IMAGE_CAPTURE = 1;
    private Intent mSourceIntent;
    private ValueCallback<Uri> mUploadMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_service);

        init();
        setView();
    }

    private void init() {
        mContext = ContactServiceActivity.this;
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）
        int height = metric.heightPixels; // 屏幕高度（像素）
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        url = "http://oauth.bbpapp.com/other/customer_service_web?access_token=" + "bfef6a46b7ba7da66280a56f5ba62411ed46ee55";
    }

    private void setView() {
    	setTopView("正在加载中...");
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        webView = (WebView) findViewById(R.id.web_view);

        webViewInitialization();
        setRelayoutPosition(rlLayout, 0);
    }

    private void setRelayoutPosition(View view, int belowId) {
        view.setLayoutParams(new LayoutParameters().setViewPositionParams2(
                FixedPosition.mall_position[0][0], RelativeLayout.LayoutParams.WRAP_CONTENT,
                FixedPosition.mall_position[0][2], scaleQPW, scaleQPH, belowId,
                0));
    }

    private void webViewInitialization() {
        // 设置webView属性，能够执行JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        // //调用重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
        webView.setHorizontalScrollBarEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setUseWideViewPort(false);
        webView.getSettings().setLoadWithOverviewMode(true);
        // webView.setDownloadListener(new MywebViewDownLoadListener());
        // 设置web视图客户端
        webView.setWebChromeClient(new ReWebChomeClient(this));
//        webView.setWebViewClient(new ReWebViewClient());
        webView.setWebViewClient(new MywebViewClient());

        webView.requestFocusFromTouch();
        // 加载URL内容"http://weibo.com/babyproject"
//        url = "http://wwww.baidu.com";
        webView.loadUrl(url);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            System.out.println("-----NO----> RESULT_OK :");
            if(mUploadMsg != null) {
                System.out.println("-----NO----> RESULT_OK --------------:");
                mUploadMsg.onReceiveValue(null);
            }
            return;
        }
        switch (requestCode) {
            case REQUEST_CODE_IMAGE_CAPTURE:
            case REQUEST_CODE_PICK_IMAGE: {
                try {
                    System.out.println("---------> 1111 :");
                    if (mUploadMsg == null) {
                        return;
                    }
                    System.out.println("---------> 222 :");
                    String sourcePath = ImageUtil.retrievePath(this, mSourceIntent, data);
                    System.out.println("--------->sourcePath :" + sourcePath);
                    if (TextUtils.isEmpty(sourcePath) || !new File(sourcePath).exists()) {
//                        Log.w(TAG, "sourcePath empty or not exists.");
                        break;
                    }
                    Uri uri = Uri.fromFile(new File(sourcePath));
                    System.out.println("--------->uri 1 :" + uri);
//                    Bitmap bitmap = ImageUtil.getBitmapFormUri(mContext, uri);
                    Bitmap bitmap = ImageUtil.getSmallBitmap(sourcePath);
                    System.out.println("--------->bitmap :" + bitmap);
                    uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null,null));
                    System.out.println("--------->uri 2 :" + uri);
                    mUploadMsg.onReceiveValue(uri);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    @Override
    public void openFileChooserCallBack(ValueCallback<Uri> uploadMsg, String acceptType) {
        mUploadMsg = uploadMsg;
        showOptions();
    }

    public void showOptions() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setOnCancelListener(new ReOnCancelListener());
        alertDialog.setTitle("操作");
        alertDialog.setItems(R.array.options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            mSourceIntent = ImageUtil.choosePicture();
                            startActivityForResult(mSourceIntent, REQUEST_CODE_PICK_IMAGE);
                        } else {
                            mSourceIntent = ImageUtil.takeBigPicture();
                            System.out.println("-----------mSourceIntent--------" + mSourceIntent);
                            startActivityForResult(mSourceIntent, REQUEST_CODE_IMAGE_CAPTURE);
                        }
                    }
                }
        );
        alertDialog.show();
    }

    private class ReOnCancelListener implements DialogInterface.OnCancelListener {

        @Override
        public void onCancel(DialogInterface dialogInterface) {
            if (mUploadMsg != null) {
                mUploadMsg.onReceiveValue(null);
                mUploadMsg = null;
            }
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message,
                                 final JsResult result) {

            // 弹框提示
            if (!isFinishing()) {
                new AlertDialog.Builder(mContext)
                        .setTitle("说明")
                        .setMessage(message)
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        result.confirm();
                                    }
                                }).create().show();
            }

            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                                   final JsResult result) {

            Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    // 设置回退
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // web视图客户端
    public class MywebViewClient extends WebViewClient {

        /**
         * 这个事件，将在用户点击链接时触发。
         * 通过判断url，可确定如何操作，
         * 如果返回true，表示我们已经处理了这个request，
         * 如果返回false，表 示没有处理，
         * 那么浏览器将会根据url获取网页
         *
         * */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(!StringBiz.isEmpty(url) && url.equals("http://m.kuaidi100.com/null")) {
                finish();
            }
            return true; // 表 示已经处理了这次URL的请求
        }

        public boolean shouldOverviewUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
        
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
			rlDefineProgress.setVisibility(View.VISIBLE);
			startAnimationView();
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			rlDefineProgress.setVisibility(View.GONE);
		}
        
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.removeAllViews();
        webView.clearHistory();
        webView.destroy();
    }

}
