package com.gst.mydemo.biz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gst.mydemo.R;
import com.gst.mydemo.utils.MyToast;
import com.gst.mydemo.utils.PhotoUtil;

import java.io.File;

public class DialogBiz {

	// 邮箱登录提示 - Dialog
	public static void loginDialog(final Context context) {
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.show();
		Window window = dialog.getWindow();
		// *** 主要就是在这里实现这种效果的.
		window.setContentView(R.layout.define_dialog_one_btn);
		Button btnBottom = (Button) window.findViewById(R.id.btn_bottom);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_dialog_title);
		TextView tvContent = (TextView) window
				.findViewById(R.id.tv_dialog_content);

		tvTitle.setText(context.getString(R.string.login_tips_title));
		tvTitle.setVisibility(View.VISIBLE);
		tvContent.setText(context.getString(R.string.login_tips_content));
		tvContent.setTextSize(16.0f);
		btnBottom.setText(context.getString(R.string.login_tips_btn));
		btnBottom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				((Activity) context).startActivityForResult(
//						new Intent(context, LoginActivity.class), 0);
				dialog.dismiss();
			}
		});
		dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失  
	}

	// 手机绑定Dialog
	public static void boundPhoneDialog(final Context context) {
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.show();
		Window window = dialog.getWindow();
		// *** 主要就是在这里实现这种效果的.
		window.setContentView(R.layout.define_dialog_2);

		RelativeLayout rlCancel = (RelativeLayout) window
				.findViewById(R.id.rl_left);
		RelativeLayout rlBoundPhone = (RelativeLayout) window
				.findViewById(R.id.rl_right);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_dialog_title);
		TextView tvContent = (TextView) window
				.findViewById(R.id.tv_dialog_content);
		TextView tvLeft = (TextView) window.findViewById(R.id.tv_left);
		TextView tvRight = (TextView) window.findViewById(R.id.tv_right);

		tvTitle.setText(context.getString(R.string.bound_phone));
		tvTitle.setVisibility(View.VISIBLE);
		tvContent.setText(context.getString(R.string.bound_phone_content));
		tvContent.setTextSize(16.0f);
		tvLeft.setText(context.getString(R.string.qu_xiao));
		tvRight.setText(context.getString(R.string.bound_phone));
		rlCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		rlBoundPhone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				context.startActivity(new Intent(context,
//						BoundPhoneActivity.class));
				dialog.dismiss();
			}
		});
	}

	// 可编辑输入框dialog
	public static void editContentDialog(final Context context) {
		final AlertDialog dialog = new AlertDialog.Builder(context).create();
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.define_dialog_edit, null);
		dialog.setView(layout);
		dialog.show();
		Window window = dialog.getWindow();
		// *** 主要就是在这里实现这种效果的.
		window.setContentView(R.layout.define_dialog_edit);

		RelativeLayout rlCancel = (RelativeLayout) window.findViewById(R.id.rl_left);
		RelativeLayout rlRight = (RelativeLayout) window
				.findViewById(R.id.rl_right);
		TextView tvTitle = (TextView) window.findViewById(R.id.tv_dialog_title);
		final EditText etContent = (EditText) window
				.findViewById(R.id.et_content);
		TextView tvLeft = (TextView) window.findViewById(R.id.tv_left);
		TextView tvRight = (TextView) window.findViewById(R.id.tv_right);

		tvTitle.setText("新建列表");
		tvTitle.setVisibility(View.VISIBLE);
		etContent.setTextSize(16.0f);
		tvLeft.setText("取消");
		tvRight.setText("保存");
		rlCancel.setOnClickListener(new android.view.View.OnClickListener() { // 取消

			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		rlRight.setOnClickListener(new android.view.View.OnClickListener() { // 保存

			@Override
			public void onClick(View v) {
				String name = etContent.getText().toString().trim();
				if(StringBiz.isEmpty(name)) {
					new MyToast().showTextToast(context, "列表名不能为空哦");
				} else {
					dialog.dismiss();
				}
			}
		});
		dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失
	}

	// 显示自定义Dialog
	public static void showDefineDialog(Context mContext) {
		final AlertDialog dialog = new AlertDialog.Builder(mContext).create();
		dialog.show();
		Window window = dialog.getWindow();
		// *** 主要就是在这里实现这种效果的.
		window.setContentView(R.layout.define_dialog_3);

		TextView tvContent = (TextView) window
				.findViewById(R.id.tv_dialog_content);
		ImageView ivClose = (ImageView) window.findViewById(R.id.iv_close);
		tvContent.setTextSize(16.0f);
		ivClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				dialog.dismiss();
			}
		});
		tvContent.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO 去购买
				System.out.println("---------- 去购买 -----------");
				dialog.dismiss();
			}
		});
		dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失
	}

	public static void showGalleryDialog(final Context context) {
		final String[] items = { "手机相册", "相机拍摄" };
		AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
		builder2.setTitle("选择头像").setItems(items,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
							case 0:
								if (PhotoUtil.hasSDCard((Activity) context)) {
									// 从相册里选择图片
									((Activity)context).startActivityForResult(PhotoUtil.cropImage(500,
													500, PhotoUtil.PHOTOZOOM),
											PhotoUtil.PHOTOZOOM);
								}
								break;
							case 1:
								// 拍摄
								if (PhotoUtil.hasSDCard((Activity) context)) {
									PhotoUtil.PHOTO_DIR.mkdirs();
								/*
								 * 用当前时间给取得的图片命名
								 */
									Intent i = new Intent(
											"android.media.action.IMAGE_CAPTURE");
									i.putExtra(MediaStore.EXTRA_OUTPUT, Uri
											.fromFile(new File(Environment
													.getExternalStorageDirectory()
													.getAbsolutePath()
													+ java.io.File.separator
													+ "bodoo", "zhaopian.jpg")));

									((Activity)context).startActivityForResult(i,
											PhotoUtil.CAMERA_WITH_DATA);
								}
								break;
						}
					}
				});
		builder2.create().show();
	}
	
}
