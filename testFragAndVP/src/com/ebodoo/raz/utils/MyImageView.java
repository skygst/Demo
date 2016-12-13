package com.ebodoo.raz.utils;

import android.content.Context;

import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MyImageView extends ImageView {

	
    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
   }

	public MyImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	 

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
            return false;// false 自己不处理此次事件以及后续的事件，那么向上传递给外层view
    }

}
