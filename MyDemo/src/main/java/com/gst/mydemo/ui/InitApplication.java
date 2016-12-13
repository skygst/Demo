package com.gst.mydemo.ui;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

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
		.memoryCache(new UsingFreqLimitedMemoryCache(2* 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
        .memoryCacheSize(2 * 1024 * 1024)     
        .discCacheFileCount(100) //缓存的文件数量   
		.build();
		mImageLoader.init(config);

	}

}
