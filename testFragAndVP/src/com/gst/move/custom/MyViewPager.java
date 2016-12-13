package com.gst.move.custom;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager {
	
	public boolean result = true;

	public MyViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (result)
			return super.onInterceptTouchEvent(arg0);
		else
			return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (result)
			return super.onTouchEvent(arg0);
		else
			return false;
	}
}
