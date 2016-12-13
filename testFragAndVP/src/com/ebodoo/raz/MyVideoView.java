package com.ebodoo.raz;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class MyVideoView extends VideoView {
	public static int WIDTH;
	public static int HEIGHT;

	public MyVideoView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = getDefaultSize(WIDTH, widthMeasureSpec);
		int height = getDefaultSize(HEIGHT, heightMeasureSpec);
		if(width < height) {
			int transit = width;
			width = height;
			height = transit;
		}
		setMeasuredDimension(width, height);
	}
}
