package com.ebodoo.raz.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.Button;

	/**
	 * 自定义Button
	 *   
	 */

public class XButton extends Button {
	
	public XButton(Context context) {
		super(context);
	}

	public XButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}
	
	@Override
	public void setBackgroundDrawable(Drawable d) {
		// TODO Auto-generated method stub
		StateListDrawable mStateListDrawable = getStateListDrawable(d);
		super.setBackgroundDrawable(mStateListDrawable);
	}

	
	public XButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	
	public StateListDrawable getStateListDrawable(Drawable mDefaultBackgroud){
		if(mDefaultBackgroud != null){
			Drawable mHighlightBackgroud = getHighlightDrawable(mDefaultBackgroud);
			StateListDrawable mStateListDrawable = new StateListDrawable();
			mStateListDrawable.addState(PRESSED_ENABLED_STATE_SET, mHighlightBackgroud);
			mStateListDrawable.addState(EMPTY_STATE_SET, mDefaultBackgroud);
			return mStateListDrawable;
		}
		return null;
	}
	
	
	public Drawable getHighlightDrawable(Drawable drawable) {
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		drawable.draw(canvas);
		int pixels[] = new int [w * h];
		bitmap.getPixels(pixels, 0, w, 0, 0, w, h);
		for (int i = 0; i != pixels.length; ++i) {
			pixels[i] = addGrayMask(pixels[i]);
		}
		bitmap.recycle();
		Bitmap mHighlightBitmap = Bitmap.createBitmap(pixels, w, h,Bitmap.Config.ARGB_8888);
		return new BitmapDrawable(mHighlightBitmap);
	}
	
	private int addGrayMask(int color) {
		int b = color & 0xff;
		int g = (color & 0xff00) >> 8;
		int r = (color & 0xff0000) >> 16;
		int a = color >> 24;
		int gray = 50;
		float radio = 0.5f;
		b = hold(b * radio + gray);
		g = hold(g * radio + gray);
		r = hold(r * radio + gray);
		return (a << 24) | (r << 16) | (g << 8) | b;
	}

	private int hold(float v) {
		int r = (int) v;
		return r > 255 ? 255 : r;
	}

}
