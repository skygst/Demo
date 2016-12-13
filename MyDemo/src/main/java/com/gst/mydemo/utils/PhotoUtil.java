package com.gst.mydemo.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoUtil {
	public static final String SDCARD_ROOT_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();// 路径
	public static final String IMAGE_UNSPECIFIED = "image/*";
	public static final String SAVE_PATH_IN_SDCARD = "/bodoo/babyplan/head/"; // 图片及其他数据保存文件夹
	public static final int CAMERA_WITH_DATA = 101; // 拍照
	public static final int CAMERA_WITH_DATA_ROTING = 102; // 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果
	public static final int PHOTO_PICKED_WITH_DATA = 4;
	public static final File PHOTO_DIR = new File(
			Environment.getExternalStorageDirectory() + "/bodoo/babyplan/temp");// 图片的存储目录
	public static final File PHOTO_DIR2 = new File(
			Environment.getExternalStorageDirectory() + "/DCIM/Camera");// 图片的存储目录

	public static void doCropPhoto(Activity activity, File f) {
		try {
			activity.startActivityForResult(
					cropImage(500, 500, PHOTO_PICKED_WITH_DATA),
					PHOTO_PICKED_WITH_DATA);
		} catch (Exception e) {
			e.getMessage();
			Toast.makeText(activity, "失败", Toast.LENGTH_LONG).show();
		}
	}

	public static Intent getCropImageIntent(Uri photoUri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(photoUri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 250);
		intent.putExtra("outputY", 250);
		intent.putExtra("return-data", true);
		return intent;
	}

	public static void startPhotoCrop(Activity activity, Uri uri,
			int screenWidth) {
		int outputx;
		if (screenWidth > 350) {
			outputx = (Integer) screenWidth * 350 / 480;
		} else {
			outputx = 350;
		}

		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", outputx);
		intent.putExtra("outputY", outputx);
		// 图片格式
		intent.putExtra("scale", true);
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", true);
		activity.startActivityForResult(intent, PHOTORESOULT);
	}

	/*
	 * public static void startPhotoCrop(Activity activity, Uri uri){
	 * 
	 * //裁剪图片意图 Intent intent = new Intent("com.android.camera.action.CROP");
	 * intent.setDataAndType(uri, "image/*"); intent.putExtra("crop", "true");
	 * //裁剪框的比例，1：1 intent.putExtra("aspectX", 1); intent.putExtra("aspectY",
	 * 1); //裁剪后输出图片的尺寸大小 intent.putExtra("outputX", 600);
	 * intent.putExtra("outputY", 600); //图片格式 intent.putExtra("scale", true);
	 * intent.putExtra("outputFormat", "JPEG");
	 * intent.putExtra("noFaceDetection", true); intent.putExtra("return-data",
	 * true); activity.startActivityForResult(intent, PHOTORESOULT); }
	 */

	/**
	 * 用当前时间给取得的图片命名
	 * 
	 */
	public static String getGeneratedPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy_MM_dd_HH_mm_ss");
		return dateFormat.format(date) + ".jpg";
	}

	/**
	 * 检查存储卡是否插入
	 * 
	 * @return
	 */
	public static boolean hasSDCard(Activity activity) {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			Toast.makeText(activity, "SD卡无法识别，没有插入？", Toast.LENGTH_LONG).show();
			return false;
		}
	}

	public static String doPickedPhoto(Intent data) {
		if (data == null || data.equals("")) {
			return null;
		}
		Bundle extras = data.getExtras();
		Bitmap bitmap = (Bitmap) extras.get("data");
		FileOutputStream b = null;
		File file = new File(SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD);
		String photoFilePath = SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD
				+ getGeneratedPhotoFileName();
		file.mkdirs();
		if (hasSdcard() && photoFilePath != null
				&& !photoFilePath.equals("")) {
			try {
				b = new FileOutputStream(photoFilePath);
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					b.flush();
					b.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return photoFilePath;
	}
	
	public static boolean hasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static void doPhotoCrop(Activity activity, Intent data) {
		if (data == null || data.getData() == null)
			return;
		Uri originalUri = data.getData();
		int screenWidth = getWidth(activity);
		startPhotoCrop(activity, originalUri, screenWidth);
	}

	private static int getWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		// 获取屏幕信息
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

		return dm.widthPixels;
	}

	public static Intent cropImage(int outputX, int outputY, int requestCode) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		// Intent intent = new Intent("com.android.camera.action.CROP");
		// 实现对图片的裁剪,必须要设置图片的属性和大小
		intent.setType("image/*"); // 设置属性，表示获取任意类型的图片
//		intent.putExtra("crop", "true");// 设置可以滑动选选择区域的属性,注意这里是字符串"true"
//		intent.putExtra("aspectX", 1);// 设置剪切框1:1比例的效果
//		intent.putExtra("aspectY", 1);
//		intent.putExtra("outputX", 250);
//		intent.putExtra("outputY", 250);
//		intent.putExtra("outputFormat", "JPEG");
//		intent.putExtra("noFaceDetection", true);
//		intent.putExtra("return-data", true);
		return intent;
	}

}
