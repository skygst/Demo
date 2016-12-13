package com.gst.mydemo.custom.ui;

import android.os.Bundle;

import com.gst.mydemo.R;
import com.gst.mydemo.ui.BaseActivity;

/**
 *  自定义线、虚线、圆角框
 * @author gst
 *
 */
public class LineStyleActivity extends BaseActivity {

//	private RelativeLayout rlReceive, rlView, rlTvView;
//	private ImageView ivPic;
//	private ImageLoader mImageLoader;
//	private final String TAG = "LineStyleActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_line_style);
//		setContentView(R.layout.ad_view);

		init();
		setView();
	}

	private void init() {
	}

	private void setView() {
	}

}
