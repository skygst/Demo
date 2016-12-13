package com.example.location.biz;

import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class LayoutParameters {

	/*-----------------------视图位置及缩放--------------------------*/

	public static LayoutParams setViewPositionParams(int width, int height,
			int x, int y, float wBeisu, float hBeisu, int xoffset, int yoffset,
			float suoxiao) {

		RelativeLayout.LayoutParams params;

		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * wBeisu / suoxiao).intValue(), Float.valueOf(
				height * hBeisu / suoxiao).intValue());

		int intentTopValue = Float.valueOf(
				(y * hBeisu / suoxiao + yoffset * hBeisu / suoxiao)).intValue();
		params.topMargin = intentTopValue;

		int intentLeftValue = Float.valueOf(
				(x * wBeisu / suoxiao + xoffset * wBeisu / suoxiao)).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}

	public static LayoutParams setViewPositionParams(int width, int height,
			int x, int y) {

		RelativeLayout.LayoutParams params;

		params = new RelativeLayout.LayoutParams(Float.valueOf(width)
				.intValue(), Float.valueOf(height).intValue());

		int intentTopValue = Float.valueOf(y).intValue();
		params.topMargin = intentTopValue;

		int intentLeftValue = Float.valueOf(x).intValue();
		params.leftMargin = intentLeftValue;
		params.rightMargin = -width + width / 2;
		params.bottomMargin = -height + height / 2;
		return params;
	}

	public static LinearLayout.LayoutParams shangchengParams(int width,
															   int height, int y, float wBeisu, float hBeisu) {
		LinearLayout.LayoutParams params;
		params = new LinearLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());

		int intentTopValue = Float.valueOf((y * hBeisu + hBeisu)).intValue();
		params.topMargin = intentTopValue;
		int x = 100;
		int intentLeftValue = Float.valueOf((x * wBeisu + wBeisu)).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}

	public static RelativeLayout.LayoutParams shangchengParams22(int width,
															   int height, int y, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());

		int intentTopValue = Float.valueOf((y * hBeisu + hBeisu)).intValue();
		params.topMargin = intentTopValue;
		int x = 100;
		int intentLeftValue = Float.valueOf((x * wBeisu + wBeisu)).intValue();
		params.leftMargin = intentLeftValue;
		int intentRightValue = Float.valueOf((20 * wBeisu + wBeisu)).intValue();
		params.rightMargin = intentRightValue;
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		return params;
	}

	public static FrameLayout.LayoutParams shangchengParams1(int width, int height, int y, float wBeisu, float hBeisu) {
		FrameLayout.LayoutParams params;
		params = new FrameLayout.LayoutParams(Float.valueOf(
				width * wBeisu).intValue(), Float.valueOf(
				height * hBeisu).intValue());

		int intentTopValue = Float.valueOf(
				(y * hBeisu + hBeisu )).intValue();
		params.topMargin = intentTopValue;
		int x = 20;
		int intentLeftValue = Float.valueOf(
				(x * wBeisu + wBeisu)).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}
	
	public static FrameLayout.LayoutParams shangchengScaleParams(int width, int height, int y, float wBeisu, float hBeisu) {
		FrameLayout.LayoutParams params;
		params = new FrameLayout.LayoutParams(Float.valueOf(
				width * wBeisu).intValue(), Float.valueOf(
				height * hBeisu).intValue());

		int intentTopValue = Float.valueOf(
				(y * hBeisu + hBeisu )).intValue();
		params.topMargin = intentTopValue;
		int x = 20;
		/*int intentLeftValue = Float.valueOf(
				(x * wBeisu + wBeisu)).intValue();
		params.leftMargin = intentLeftValue;*/
		return params;
	}

	public static RelativeLayout.LayoutParams widthParams(int width, int height, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * wBeisu).intValue(), Float.valueOf(
				height * hBeisu).intValue());
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		return params;
	}


	// 权重为1
	public static LinearLayout.LayoutParams weightParams(int width, int height, float wBeisu, float hBeisu) {
		LinearLayout.LayoutParams params;
		params = new LinearLayout.LayoutParams(Float.valueOf(
				width * wBeisu).intValue(), Float.valueOf(
				height * hBeisu).intValue());
		params.weight = 1;
		return params;
	}

	// 设置Top值
	public static RelativeLayout.LayoutParams topParams(int width, int height, int topValue, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * wBeisu).intValue(), Float.valueOf(
				height * hBeisu).intValue());
		int intentTopValue = Float.valueOf(
				(topValue * hBeisu + hBeisu )).intValue();
		params.topMargin = intentTopValue;
		return params;
	}

	// 设置below
	public static RelativeLayout.LayoutParams belowParams(int width, int height, int belowId, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * wBeisu).intValue(), Float.valueOf(
				height * hBeisu).intValue());
		params.addRule(RelativeLayout.BELOW, belowId);
		return params;
	}

	// 在某一个控件里居中
	public static RelativeLayout.LayoutParams centerParams(int width, int height, int alignId, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * wBeisu).intValue(), Float.valueOf(
				height * hBeisu).intValue());
		params.addRule(RelativeLayout.ALIGN_TOP, alignId);
		params.addRule(RelativeLayout.ALIGN_BOTTOM, alignId);
		params.addRule(RelativeLayout.ALIGN_LEFT, alignId);
		params.addRule(RelativeLayout.ALIGN_RIGHT, alignId);
//		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		return params;
	}

}
