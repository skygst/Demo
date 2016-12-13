package com.gst.mydemo.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class UserPhoto {
	public static void doPhotoCrop(Activity activity, Intent data) {
		if (data == null || data.getData() == null)
			return;
		Uri originalUri = data.getData();
		startPhotoCrop(activity, originalUri);
	}

	public static void startPhotoCrop(Activity activity, Uri uri) {
		// 裁剪图片意图
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		// 裁剪框的比例，1：1
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// 裁剪后输出图片的尺寸大小
		intent.putExtra("outputX", 350);
		intent.putExtra("outputY", 350);
		// 图片格式
		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", true);
		activity.startActivityForResult(intent, 3);
	}

	public static final String SDCARD_ROOT_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath();// 路径
	public static final String IMAGE_UNSPECIFIED = "image/*";
	public static final String SAVE_PATH_IN_SDCARD = "/bodoo/babyplan/head/"; // 图片及其他数据保存文件夹

	public static String doPickedPhoto(Intent data) {
		Bundle extras = data.getExtras();

		if ((Bitmap) extras.get("data") == null)
			return null;

		Bitmap bitmap = (Bitmap) extras.get("data");
		FileOutputStream b = null;
		File file = new File(SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD);
		String photoFilePath = SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD
				+ getGeneratedPhotoFileName();
		file.mkdirs();
		try {
			b = new FileOutputStream(photoFilePath);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (b != null) {
					b.flush();
					b.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return photoFilePath;
	}

	public String doPickedPhoto(Bitmap bitmap) {
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
	
	public boolean hasSdcard() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	public static String doPickedPhoto(String pic_path) {
		BitmapFactory.Options option = new BitmapFactory.Options();
		option.inSampleSize = 1;
		Bitmap bitmap = BitmapFactory.decodeFile(pic_path, option);

		FileOutputStream b = null;
		File file = new File(SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD);
		String photoFilePath = SDCARD_ROOT_PATH + SAVE_PATH_IN_SDCARD
				+ getGeneratedPhotoFileName();
		file.mkdirs();
		try {
			b = new FileOutputStream(photoFilePath);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (b != null) {
					b.flush();
					b.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return photoFilePath;
	}

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

	public static void doCropPhoto(Activity activity, File f) {
		try {
			final Intent intent = getCropImageIntent(Uri.fromFile(f));
			activity.startActivityForResult(intent, 4);

		} catch (Exception e) {
			e.getMessage();
			Toast.makeText(activity, "失败", Toast.LENGTH_LONG).show();
		}
	}

	public static Intent getCropImageIntent(Uri photoUri) {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		intent.setDataAndType(photoUri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 250);
		intent.putExtra("outputY", 250);
		intent.putExtra("return-data", true);
		return intent;
	}
}
