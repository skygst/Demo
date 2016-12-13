package com.gst.mydemo.linstener;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ShowBigPicListener implements ImageLoadingListener {

	private int mScreenWidth = 0;

	public ShowBigPicListener(int screenWidth) {
		mScreenWidth = screenWidth;
	}

	@Override
	public void onLoadingStarted(String imageUri, View view) {

	}

	@Override
	public void onLoadingFailed(String imageUri, View view,
			FailReason failReason) {

	}

	@Override
	public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		if (loadedImage != null) {
			// 根据屏幕的宽高来显示图片
			int bmpWidth = loadedImage.getWidth();
			int bmpHeight = loadedImage.getHeight();
			int resultHeight = (Integer) (bmpHeight * mScreenWidth / bmpWidth);
			loadedImage = Bitmap.createScaledBitmap(loadedImage, mScreenWidth,
					resultHeight, false);
			ImageView iv = (ImageView) view;
			iv.setImageBitmap(loadedImage);
//			iv.setBackground(new BitmapDrawable(loadedImage));
		}

	}

	@Override
	public void onLoadingCancelled(String imageUri, View view) {

	}
}
