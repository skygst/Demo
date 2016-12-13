package com.ebodoo.raz.video;

import java.lang.reflect.InvocationTargetException;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.database.ContactInjfoDao;
import com.ebodoo.raz.database.MyVideoHistoryHelper;
import com.example.location.biz.StringBiz;
import com.gst.move.R;

/**
 *  视频播放界面---WebView播放
 * @author
 * 
 */
public class WearVideoPlayWebViewActivity extends BaseActivity {
	private FrameLayout videoview;// 全屏时视频加载view
	private WebView videowebview;
	private Boolean islandport = true;// true表示此时是竖屏，false表示此时横屏。
	private View xCustomView;
	private xWebChromeClient xwebchromeclient;
	private LinearLayout llLayout;
	private WebChromeClient.CustomViewCallback xCustomViewCallback;
	// 网页加载出错进显示页面
	private TextView videoerror;
	// 网页加载出错正在刷新
	private TextView videorefresh;
	private String url;
	private Context mContext;
	private String youkuId, albumsName, name, videoId;
	private ContactInjfoDao mDao;
	private Cursor cursor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉应用标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.video_play_web_view);
		
		init();
		initwidget();
		initListener();
		url = "http://player.youku.com/embed/XNTE3NDgyNDI4";
		url = "http://my.tv.sohu.com/us/63368717/31767110.shtml";
		videowebview.loadUrl(url);
	}
	
	private void init() {
		mContext = WearVideoPlayWebViewActivity.this;
//		youkuId = getIntent().getExtras().getString("youku_id");
//		albumsName = getIntent().getExtras().getString("albumName");
//		name = getIntent().getExtras().getString("name");
//		videoId = getIntent().getExtras().getString("videoId");
//		url = "http://player.youku.com/embed/" + youkuId;
		mDao = new ContactInjfoDao(mContext);
		MyVideoHistoryHelper helper = new MyVideoHistoryHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();
		cursor = db.rawQuery("select * from videoHistory", null);
	}

	private void initListener() {
		// videolandport.setOnClickListener(new Listener());
	}

	private void initwidget() {
		llLayout = (LinearLayout) findViewById(R.id.ll_layout);
		videoview = (FrameLayout) findViewById(R.id.video_view);
		// videoview.setOnTouchListener(listClick);
		// videolandport = (Button) findViewById(R.id.video_landport);
		videowebview = (WebView) findViewById(R.id.video_webview);
		videoerror = (TextView) findViewById(R.id.video_error);
		videorefresh = (TextView) findViewById(R.id.video_refresh);
		videoerror.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				videowebview.loadUrl("about:blank");
				videowebview.loadUrl(url);
				videoerror.setVisibility(View.GONE);
				videorefresh.setVisibility(View.VISIBLE);
				videowebview.setVisibility(View.GONE);
			}
		});
		WebSettings ws = videowebview.getSettings();
		/**
		 * setAllowFileAccess 启用或禁止WebView访问文件数据 setBlockNetworkImage 是否显示网络图像
		 * setBuiltInZoomControls 设置是否支持缩放 setCacheMode 设置缓冲的模式
		 * setDefaultFontSize 设置默认的字体大小 setDefaultTextEncodingName 设置在解码时使用的默认编码
		 * setFixedFontFamily 设置固定使用的字体 setJavaSciptEnabled 设置是否支持Javascript
		 * setLayoutAlgorithm 设置布局方式 setLightTouchEnabled 设置用鼠标激活被选项
		 * setSupportZoom 设置是否支持变焦
		 * */
		// ws.setBuiltInZoomControls(true);// 隐藏缩放按钮
		ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
		// ws.setUseWideViewPort(true);// 可任意比例缩放
		ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
		ws.setSavePassword(true);
		ws.setSaveFormData(true);// 保存表单数据
		ws.setJavaScriptEnabled(true);
		// ws.setGeolocationEnabled(true);// 启用地理定位
//		ws.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");// 设置定位的数据库路径
		ws.setDomStorageEnabled(true);
		xwebchromeclient = new xWebChromeClient();
		videowebview.setWebChromeClient(xwebchromeclient);
		videowebview.setWebViewClient(new xWebViewClientent());

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (inCustomView()) {
				hideCustomView();
				WearVideoPlayWebViewActivity.this.finish();
				return true;
			} else {
				// 退出时加载空网址防止退出时还在播放视频
				videowebview.loadUrl("about:blank");
				WearVideoPlayWebViewActivity.this.finish();
			}
		}
		return true;
	}

	/**
	 * 判断是否是全屏
	 * 
	 * @return
	 */
	public boolean inCustomView() {
		return (xCustomView != null);
	}

	/**
	 * 全屏时按返加键执行退出全屏方法
	 */
	public void hideCustomView() {
		xwebchromeclient.onHideCustomView();
	}

	/**
	 * 处理Javascript的对话框、网站图标、网站标题以及网页加载进度等
	 * 
	 * @author
	 */
	public class xWebChromeClient extends WebChromeClient {
		private Bitmap xdefaltvideo;
		private View xprogressvideo;

		@Override
		// 播放网络视频时全屏会被调用的方法
		public void onShowCustomView(View view,
				WebChromeClient.CustomViewCallback callback) {
			if (islandport) {

			} else {
				// ii = "1";
				// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			}
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			videowebview.setVisibility(View.GONE);
			// 如果一个视图已经存在，那么立刻终止并新建一个
			if (xCustomView != null) {
				callback.onCustomViewHidden();
				return;
			}
			videoview.addView(view);
			xCustomView = view;
			xCustomViewCallback = callback;
			videoview.setVisibility(View.VISIBLE);
		}

		@Override
		// 视频播放退出全屏会被调用的
		public void onHideCustomView() {

			if (xCustomView == null)// 不是全屏播放状态
				return;
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			xCustomView.setVisibility(View.GONE);
			videoview.removeView(xCustomView);
			xCustomView = null;
			videoview.setVisibility(View.GONE);
			xCustomViewCallback.onCustomViewHidden();
			videowebview.setVisibility(View.VISIBLE);
		}

		// 视频加载添加默认图标
		@Override
		public Bitmap getDefaultVideoPoster() {
			if (xdefaltvideo == null) {
//				xdefaltvideo = BitmapFactory.decodeResource(getResources(),
//						R.drawable.videoicon);
			}
			return xdefaltvideo;
		}

		// 视频加载时进程loading
		@Override
		public View getVideoLoadingProgressView() {
			if (xprogressvideo == null) {
				LayoutInflater inflater = LayoutInflater
						.from(WearVideoPlayWebViewActivity.this);
				xprogressvideo = inflater.inflate(
						R.layout.video_loading_progress, null);
			}
			return xprogressvideo;
		}

		// 网页标题
		@Override
		public void onReceivedTitle(WebView view, String title) {
			(WearVideoPlayWebViewActivity.this).setTitle(title);
		}

		// @Override
		// //当WebView进度改变时更新窗口进度
		// public void onProgressChanged(WebView view, int newProgress) {
		// (MainActivity.this).getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
		// newProgress*100);
		// }

	}

	/**
	 * 处理各种通知、请求等事件
	 * 
	 * @author
	 */
	public class xWebViewClientent extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			return false;
		}

		@Override
		public void onReceivedError(WebView view, int errorCode,
				String description, String failingUrl) {
			super.onReceivedError(view, errorCode, description, failingUrl);
			// 这里进行无网络或错误处理，具体可以根据errorCode的值进行判断，做跟详细的处理。
			// view.loadUrl(file:///android_asset/error.html );
			videowebview.setVisibility(View.GONE);
			videoerror.setVisibility(View.VISIBLE);
			videorefresh.setVisibility(View.GONE);
		}

		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			super.onPageStarted(view, url, favicon);
		}

		@Override
		public void onPageFinished(WebView view, String url) {
			videowebview.setVisibility(View.VISIBLE);
			videoerror.setVisibility(View.GONE);
			videorefresh.setVisibility(View.GONE);
			super.onPageFinished(view, url);
		}
	}
	
	/**
     * 当Activity执行onResume()时让WebView执行resume
     */ 
	@Override
	protected void onResume() {
		super.onResume();
		try {
			if(videoview.getVisibility() == View.VISIBLE) {
				videoview.getClass().getMethod("onResume").invoke(videoview,(Object[])null);
			} else {
				videowebview.getClass().getMethod("onResume").invoke(videowebview,(Object[])null);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 当Activity执行onPause()时让WebView执行pause
     */ 
	@Override
	protected void onPause() {
		super.onPause();
		try {
			if(videoview.getVisibility() == View.VISIBLE) {
				videoview.getClass().getMethod("onPause").invoke(videoview,(Object[])null);
			} else {
				videowebview.getClass().getMethod("onPause").invoke(videowebview,(Object[])null);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		llLayout.removeView(videoview);
		llLayout.removeView(videowebview);
		videowebview.destroy();
		
		processDateUtils();
	}
	
	private void processDateUtils() {
		String albumNameValue = mDao.findAlbumsFromVideoId(videoId);
		if(!StringBiz.isEmpty(albumNameValue)) {
			mDao.deleteDate(videoId);
		}
		mDao.addDate(albumsName, videoId, name, youkuId, "", "0");
		if(cursor != null) {
			int count = cursor.getCount();
			if(count >= 9) { // 删除第一个
				int num = 0;
				while (cursor.moveToNext() && num == 0) {
					num++;
	                String videoId = cursor.getString(cursor.getColumnIndex("videoId"));
	                mDao.deleteDate(videoId);
	            }
			}
		}
	}
	
}
