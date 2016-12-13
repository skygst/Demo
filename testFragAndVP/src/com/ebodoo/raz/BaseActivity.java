package com.ebodoo.raz;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;

import com.ebodoo.raz.utils.Constant;
import com.ebodoo.raz.utils.ConstantAfricaEbook;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.ConstantLetters;

import java.io.File;

public class BaseActivity extends Activity {

	public int width, height;
	public float density;
	public String commonPath;
	public boolean isTable = false; // false:手机布局；true: 平板布局
	public String ext_path="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		width = metric.widthPixels; // 屏幕宽度（像素）
		height = metric.heightPixels; // 屏幕高度（像素）
		density = metric.density; // 屏幕密度（0.75 / 1.0 / 1.5）

		if(Constant.sdcard_path.equals("")){
			Constant.sdcard_path = Constant.getExtSdcard();
			ext_path = Environment.getExternalStorageDirectory() + "";
			commonPath = Environment.getExternalStorageDirectory()
					+ "/raz_english/";

			try {
				File destDir = new File(commonPath);

				if(!Constant.sdcard_path.equals(ext_path)){
					if (!destDir.exists()) {
						commonPath = Constant.sdcard_path
								+ "/raz_english/";
						File destDir2 = new File(commonPath);
						if (!destDir2.exists()) {
							destDir2.mkdirs();
						}
					}else{
						Constant.sdcard_path = ext_path;
					}
				}else{
					if (!destDir.exists()) {
						destDir.mkdirs();
					}
				}

				File destDir3 = new File(commonPath+"/.nomedia");
				if(!destDir3.exists())
					new File(destDir, ".nomedia").createNewFile();

			} catch (Exception e) {
				e.printStackTrace();
			}

			com.ebodoo.raz.samples.SampleApplication.utils.Constant.path_pinyipin = com.ebodoo.raz.utils.Constant.sdcard_path
					+ "/raz_english/pinyipin/";
			com.ebodoo.raz.samples.SampleApplication.utils.Constant.path_raz = com.ebodoo.raz.utils.Constant.sdcard_path
					+ "/raz_english/raz_card_video/";
			com.ebodoo.raz.samples.SampleApplication.utils.Constant.path_raz_image = com.ebodoo.raz.utils.Constant.sdcard_path
					+ "/raz_english/raz_card_video/images/";
			com.ebodoo.raz.samples.SampleApplication.utils.Constant.commonPath = com.ebodoo.raz.utils.Constant.sdcard_path + "/raz_english/";


			Constant.path_raz = Constant.sdcard_path
					+ "/raz_english/";
			Constant.path_star = Constant.sdcard_path
					+ "/raz_english/raz_star/";

			Constant.path_raz01 = Constant.sdcard_path
					+ "/raz_english/raz01/";
			Constant.path_raz01_images = Constant.sdcard_path
					+ "/raz_english/raz01/images/";
			Constant.path_raz02 = Constant.sdcard_path
					+ "/raz_english/raz02/";
			Constant.path_raz02_images = Constant.sdcard_path
					+ "/raz_english/raz02/images/";
			Constant.path_raz03 = Constant.sdcard_path
					+ "/raz_english/raz03/";
			Constant.path_raz03_images = Constant.sdcard_path
					+ "/raz_english/raz03/images/";
			Constant.path_raz04 = Constant.sdcard_path
					+ "/raz_english/raz04/";
			Constant.path_raz04_images = Constant.sdcard_path
					+ "/raz_english/raz04/images/";

			ConstantAfricaEbook.path_reaEbook02 = Constant.sdcard_path
					+ "/raz_english/reaEbook02/";

			ConstantEp.path_pinyipin = Constant.sdcard_path
			+ "/raz_english/pinyipin/";

			ConstantEp.path_reading01 = Constant.sdcard_path
					+ "/raz_english/reading01/"; // 欧洲

			ConstantEp.path_reading02 = Constant.sdcard_path
					+ "/raz_english/reading02/"; // 非洲

			ConstantEp.path_reading03 = Constant.sdcard_path
					+ "/raz_english/reading03/"; // 亚洲

			ConstantEp.path_reading04 = Constant.sdcard_path
					+ "/raz_english/reading04/"; // 美洲


			ConstantEp.path_reading01_images = Constant.sdcard_path
					+ "/raz_english/reading01/images/";
			ConstantEp.path_reading02_images = Constant.sdcard_path
					+ "/raz_english/reading02/images/";

			ConstantEp.path_ebook_africa_images = Constant.sdcard_path
					+ "/raz_english/reaEbook02/images/";

			ConstantEp.path_ebook_images = Constant.sdcard_path
					+ "/raz_english/reaEbook01/images/";

			ConstantLetters.path_letters = Constant.sdcard_path + "/raz_english/letters/";


		}else{
			commonPath = Constant.sdcard_path
					+ "/raz_english/";
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setKeepScreenOn(true);
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	/**
     * 判断当前设备是手机还是平板，代码来自 Google I/O App for Android
     * @param context
     * @return 平板返回 True，手机返回 False
     */
	public static boolean isTablet(Context context) {
		return (context.getResources().getConfiguration().screenLayout &
				Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
	}

}
