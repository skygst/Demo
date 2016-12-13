package com.gst.move.test;

import com.gst.move.R;
import com.gst.move.R.id;
import com.gst.move.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 *  自定义Dialog
 * @author 善同
 *
 */
public class DefineDialogActivity extends Activity {

	private Context mContext;
	private Button btnClick;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.define_dialog);
		
		init();
		setView();
	}

	private void init() {
		mContext = DefineDialogActivity.this;
	}

	private void setView() {
		btnClick = (Button) findViewById(R.id.btn_click);
		
		btnClick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showDefineDialog();
			}
		});
	}
	
	// 显示自定义Dialog
	private void showDefineDialog() {
		final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
		dialog.show();
		Window window = dialog.getWindow();
		// *** 主要就是在这里实现这种效果的.
		window.setContentView(R.layout.define_dialog_2);

//		RelativeLayout rlCancel = (RelativeLayout) window
//				.findViewById(R.id.rl_left);
//		RelativeLayout rlBoundPhone = (RelativeLayout) window
//				.findViewById(R.id.rl_right);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_dialog_title);
		TextView tvContent = (TextView) window
				.findViewById(R.id.tv_dialog_content);
//		TextView tvLeft = (TextView) window.findViewById(R.id.tv_left);
//		TextView tvRight = (TextView) window.findViewById(R.id.tv_right);

		tvTitle.setText("绑定手机");
		tvTitle.setVisibility(View.VISIBLE);
//		tvContent.setText("绑定手机");
		tvContent.setTextSize(16.0f);
//		tvLeft.setText("取消");
//		tvRight.setText("绑定手机");
//		rlCancel.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});
//
//		rlBoundPhone.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});
	}
	
}
