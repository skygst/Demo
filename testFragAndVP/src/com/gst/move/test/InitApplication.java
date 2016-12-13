package com.gst.move.test;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

public class InitApplication extends Application {
	Context mContext;

	@Override
	public void onCreate() {
		super.onCreate();
		
		mContext = getApplicationContext();
		ImageLoader mImageLoader = ImageLoader.getInstance();

		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				// .showImageOnLoading(R.drawable.load_icon) // resource or
				// drawable
				// .showImageForEmptyUri(R.drawable.ic_empty) // resource
				// ordrawable
				// .showImageOnFail(R.drawable.ic_error) // resource or
				// .resetViewBeforeLoading(true)
				.cacheOnDisc(true).imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.ARGB_8888).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions).threadPoolSize(5)
				.build();
		mImageLoader.init(config);

	}

}
