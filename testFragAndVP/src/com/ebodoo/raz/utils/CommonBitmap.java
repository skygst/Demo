package com.ebodoo.raz.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.ebodoo.raz.biz.ImageBiz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CommonBitmap {

	public Bitmap convertDrawable2BitmapByCanvas(Drawable drawable) {
		Bitmap bitmap = Bitmap
				.createBitmap(
						drawable.getIntrinsicWidth(),
						drawable.getIntrinsicHeight(),
						drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_4444
								: Bitmap.Config.RGB_565);
		Canvas canvas = new Canvas(bitmap);
		// canvas.setBitmap(bitmap);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight());
		drawable.draw(canvas);
		return bitmap;
	}

	public Drawable convertBitmap2Drawable(Bitmap bitmap) {
		BitmapDrawable bd = new BitmapDrawable(bitmap);
		// 因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
		return bd;
	}

	public Bitmap getPropThumnail(Context mContext, int id) {
		Drawable d = mContext.getResources().getDrawable(id);
		Bitmap b = convertDrawable2BitmapByCanvas(d);// BitmapUtil.drawableToBitmap(d);
		// Bitmap bb = BitmapUtil.getRoundedCornerBitmap(b, 100);
		/*
		 * int w = mContext.getResources().getDimensionPixelOffset(R.dimen.
		 * thumnail_default_width); int h =
		 * mContext.getResources().getDimensionPixelSize
		 * (R.dimen.thumnail_default_height);
		 */
		int w = b.getWidth();
		int h = b.getHeight();
		Bitmap thumBitmap = ThumbnailUtils.extractThumbnail(b, w, h);

		return thumBitmap;
	}

	public static Drawable drawableChange(String path, String name) {
		Drawable drawable = Drawable.createFromPath(path + name + ".png");
		return drawable;
	}

	public static Drawable drawableBmp(String path, String name) {
		Bitmap bmp = readBitmapByPath(path + name + ".png");
		System.out.println("bmp :" + bmp);
		Drawable drawable = new BitmapDrawable(bmp);
		return drawable;
	}

	public static Bitmap readBitmapByPath(String path) {
		BitmapFactory.Options bfOptions = new BitmapFactory.Options();
		bfOptions.inDither = false;
		bfOptions.inPurgeable = true;
		bfOptions.inInputShareable = true;
		bfOptions.inTempStorage = new byte[64 * 1024];

		File file = new File(path);
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(file);
			if (fs != null)
				return BitmapFactory.decodeFileDescriptor(fs.getFD(), null,
						bfOptions);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	//设置背景图片
	@SuppressWarnings("deprecation")
	public static void changeDrawable(Context mContext, View iv, int id){
		Resources r = mContext.getResources();
		Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
				+ r.getResourcePackageName(id) + "/"
				+ r.getResourceTypeName(id) + "/"
				+ r.getResourceEntryName(id));
		Drawable drawable = ImageBiz.bitmapToDrawble(ImageBiz.readBitmapUri(mContext, uri), mContext);
		Drawable drawableValue = ImageBiz.drawableToBitmap(drawable, mContext);
		iv.setBackgroundDrawable(drawableValue);
	}
	//设置背景图片
	public static void changeDrawableSetImage(Context mContext, ImageView iv, int id){
		Resources  r = mContext.getResources();
		Uri uri =  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
				+ r.getResourcePackageName(id) + "/"
				+ r.getResourceTypeName(id) + "/"
				+ r.getResourceEntryName(id));
		Drawable drawable = ImageBiz.bitmapToDrawble(ImageBiz.readBitmapUri(mContext, uri), mContext);
		Drawable drawableValue = ImageBiz.drawableToBitmap(drawable, mContext);
		iv.setImageDrawable(drawableValue);
	}

}
