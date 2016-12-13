package com.ebodoo.raz.biz;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.TextView;

public class ImageBiz {

	public static Bitmap drawableBmp(String path) {
		Bitmap bmp = readSdCardBitmapByPath(path);
		System.out.println("bmp :" + bmp);
//		Drawable drawable = new BitmapDrawable(bmp);
		return bmp;
	}

	// 读取SD卡上的图片
	public static Bitmap readSdCardBitmapByPath(String path) {
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
	
	// 读取网络上的图片
	public static Bitmap readBitmapByInternet(String url) {
		BitmapFactory.Options bfOptions = new BitmapFactory.Options();
		bfOptions.inDither = false;
		bfOptions.inPurgeable = true;
		bfOptions.inInputShareable = true;
		bfOptions.inTempStorage = new byte[64 * 1024];
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is, null, bfOptions);
//			 Bitmap img= BitmapFactory.decodeStream(new ByteArrayInputStream(baos.toByteArray()));
//			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	/**
	 * 把uri 转换为 bitmap          注意要关闭硬件加速！！否则会因为图片超大而不显示
	 * @param mContext
	 * @param mUri
	 * @return
	 */
	public static  Bitmap readBitmapUri(Context mContext,Uri mUri) {
	    ContentResolver contentProvider = mContext.getContentResolver();
	    Bitmap bmp = null;
	    try {
	        bmp = BitmapFactory.decodeStream(contentProvider.openInputStream(mUri));
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return bmp;
	}
	
	 /** 
     * Bitmap to Drawable 
     * @param bitmap 
     * @param mcontext 
     * @return 
     */  
    public static Drawable bitmapToDrawble(Bitmap bitmap,Context mcontext){  
        Drawable drawable = new BitmapDrawable(mcontext.getResources(), bitmap);  
        return drawable;  
    } 
    
 // drawable=====>>>bitmap
 	public static Drawable drawableToBitmap(Drawable drawable, Context context) {
 		// // 取 drawable 的长宽
 		int w = drawable.getIntrinsicWidth();
 		int h = drawable.getIntrinsicHeight();
 		// 取 drawable 的颜色格式
 		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
 				: Bitmap.Config.RGB_565;
 		// 建立对应 bitmap
 		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
 		// 建立对应 bitmap 的画布
 		Canvas canvas = new Canvas(bitmap);
 		drawable.setBounds(0, 0, w, h);
 		// 把 drawable 内容画到画布中
 		drawable.draw(canvas);
 		
 		Drawable drawableValue = bitmapToDrawble(bitmap, context);
 		return drawableValue;
 	}
 	
 	public static Bitmap toBitmap(Drawable drawable, Context context) {
 		// // 取 drawable 的长宽
 		int w = drawable.getIntrinsicWidth();
 		int h = drawable.getIntrinsicHeight();
 		// 取 drawable 的颜色格式
 		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
 				: Bitmap.Config.RGB_565;
 		// 建立对应 bitmap
 		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
 		// 建立对应 bitmap 的画布
 		Canvas canvas = new Canvas(bitmap);
 		drawable.setBounds(0, 0, w, h);
 		// 把 drawable 内容画到画布中
 		drawable.draw(canvas);
 		return bitmap;
 	}
 	
 	// 图片options
 	public static DisplayImageOptions imgOptions(int emptyUri) {
 		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.resetViewBeforeLoading(true).cacheOnDisk(true)
		.cacheInMemory(true)
		.imageScaleType(ImageScaleType.EXACTLY)
		.showImageForEmptyUri(emptyUri) // resource or
		.showImageOnFail(emptyUri) // resource or
		.bitmapConfig(Bitmap.Config.RGB_565)
		.considerExifParams(true)
		.displayer(new SimpleBitmapDisplayer()).build();
 		return options;
 	}
 	
 	public Drawable showSelDrawable(Context context, int drawableId) {
		Drawable drawable = context.getResources().getDrawable(drawableId);
		// / 这一步必须要做,否则不会显示.
		drawable.setBounds(0, 0, drawable.getMinimumWidth(),
				drawable.getMinimumHeight());
		return drawable;
	}
 	
 	public void showLeftDrawable(Context context, TextView tvView, int drawableId) {
 		tvView.setCompoundDrawables(
				showSelDrawable(context, drawableId), null,
				null, null);
	}
	
}
