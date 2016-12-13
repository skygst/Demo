package com.ebodoo.raz.utils;

import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class LayoutParameters {

	public static LayoutParams setViewBuyDialogParams1(int width, int height, float wBeisu, float hBeisu,
													  boolean isCenter, boolean isLeft, int viewId) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());
//		int intentLeftValue = Float.valueOf((25 * wBeisu + wBeisu)).intValue();
//		params.leftMargin = intentLeftValue;
//		if(isCenter) {
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
//		} else {
//			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//			if(isLeft) {
//				params.addRule(RelativeLayout.LEFT_OF, viewId);
//			} else {
//				params.addRule(RelativeLayout.RIGHT_OF, viewId);
//			}
//			params.bottomMargin = Float.valueOf((10 * wBeisu + wBeisu)).intValue();
//		}
		return params;
	}

	public static LayoutParams setViewBuyDialogParams(int width, int height,
													  float wBeisu, float hBeisu, boolean isCenter, boolean isLeft,
													  int viewId) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());
//		int intentLeftValue = Float.valueOf((25 * wBeisu + wBeisu)).intValue();
//		params.leftMargin = intentLeftValue;
		if (isCenter) {
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
		} else {
			params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			if (isLeft) {
				params.addRule(RelativeLayout.LEFT_OF, viewId);
			} else {
				params.addRule(RelativeLayout.RIGHT_OF, viewId);
			}
		}
		return params;
	}

	// 水平居中，在空间上面, 离地面高
	public static LayoutParams setViewBuyDialogParams2(int width, int height, float wBeisu, float hBeisu,
													  int aboveId, int bottomValue) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());
//		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		if(bottomValue > 0) {
			params.bottomMargin = Float.valueOf(bottomValue).intValue();
		}
		if(aboveId != 0) {
			params.addRule(RelativeLayout.ABOVE, aboveId);
		} else {
			params.addRule(RelativeLayout.CENTER_IN_PARENT);
		}
		return params;
	}

	// 水平居中，在空间下面
	public static LayoutParams setViewBuyDialogParams3(int width, int height, float wBeisu, float hBeisu,
													  int belowId) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());
		params.addRule(RelativeLayout.BELOW, belowId);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		return params;
	}

	// 水平居中，在空间下面, 在空间左面或者右面，距离15dp
	public static LayoutParams setViewBuyDialogParams4(int width, int height, float wBeisu, float hBeisu,
													  int belowId, boolean isLeft, int id) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());
		params.addRule(RelativeLayout.BELOW, belowId);
		if(isLeft) {
			params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			params.addRule(RelativeLayout.LEFT_OF, id);
			params.leftMargin = Float.valueOf(10).intValue();
		} else {
			params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
			params.addRule(RelativeLayout.RIGHT_OF, id);
			params.rightMargin = Float.valueOf(10).intValue();
		}
		return params;
	}

	// 水平居中，在空间下面, 在空间左面或者右面，距离15dp
	public static LayoutParams setViewBuyDialogParams5(int width, int height, float wBeisu, float hBeisu,
													   int belowId, boolean isLeft, int id, int value) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());
		params.addRule(RelativeLayout.BELOW, belowId);
		if(isLeft) {
			params.addRule(RelativeLayout.LEFT_OF, id);
			params.rightMargin = Float.valueOf(value).intValue();
		} else {
			params.addRule(RelativeLayout.RIGHT_OF, id);
			params.leftMargin = Float.valueOf(value).intValue();
		}
		return params;
	}

	public static LayoutParams getParamsCenter(int width, Button btn) {
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) btn
				.getLayoutParams();
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				btn.getLayoutParams().width).intValue(), Float.valueOf(
				btn.getLayoutParams().height).intValue());

		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		return params;
	}

	public LayoutParams setViewPositionParams2(int width, int height, int x, float scaleQPW, float scaleQPH, int belowId, int topValue) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * scaleQPW).intValue(), height);
		int intentLeftValue = Float.valueOf(
				(x * scaleQPW)).intValue();
		params.leftMargin = intentLeftValue;
		if(belowId != 0) {
			params.addRule(RelativeLayout.BELOW, belowId);
			params.topMargin = Float.valueOf((topValue * scaleQPH)).intValue();
		}
		return params;
	}

	/**
	 * 对第一个气球和pen定位
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getImgParams1(int height, float density,
			ImageView iv, int id, float beisu, boolean isAnimation) {
		int wIv = iv.getLayoutParams().width;
		int hIv = iv.getLayoutParams().height;
		RelativeLayout.LayoutParams params;
		if (isAnimation) {
			// params = new RelativeLayout.LayoutParams(Float.valueOf(hIv /
			// density).intValue(), Float.valueOf(hIv / density).intValue());
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					1.1f * beisu * wIv / density).intValue(), Float.valueOf(
					1.1f * beisu * hIv / density).intValue());
		} else {
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					beisu * wIv / density).intValue(), Float.valueOf(
					beisu * hIv / density).intValue());

		}
		params.addRule(RelativeLayout.ALIGN_LEFT);
		float ddd = (height / 480.0f);
		float topValue = beisu * 72.0f;
		int intentValue = Float.valueOf(topValue).intValue();
		params.topMargin = intentValue;

		float leftValue = ddd * (80.0f) * 1.5f;
		int intentLeftValue = Float.valueOf(leftValue).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}

	/**
	 * 对第一个气球之外的气球和实物定位
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getImgParams(int height, float density,
			ImageView iv, int id, float beisu, boolean isAnimation) {
		int wIv = iv.getLayoutParams().width;
		int hIv = iv.getLayoutParams().height;
		RelativeLayout.LayoutParams params;
		if (isAnimation) {
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					1.1f * beisu * wIv / density).intValue(), Float.valueOf(
					1.1f * beisu * hIv / density).intValue());

		} else {
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					beisu * wIv / density).intValue(), Float.valueOf(
					beisu * hIv / density).intValue());

		}
		params.addRule(RelativeLayout.ALIGN_LEFT);
		params.addRule(RelativeLayout.BELOW, id);
		float ddd = (height / 480.0f);
		float topValue = beisu * 13.333f;// 1.5f*beisu * 18.0f / density;
		int intentValue = Float.valueOf(topValue).intValue();
		params.topMargin = intentValue;

		float leftValue = ddd * (80.0f) * 1.5f;
		int intentLeftValue = Float.valueOf(leftValue).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}

	/**
	 * 对第一个气球和pen定位
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getSelectedPaopaoParams1(int height,
			float density, ImageView iv, int id, float beisu,
			boolean isAnimation) {
		int wIv = Float.valueOf(88 * density).intValue();
		int hIv = Float.valueOf(88 * density).intValue();
		RelativeLayout.LayoutParams params;
		if (isAnimation) {
			// params = new RelativeLayout.LayoutParams(Float.valueOf(hIv /
			// density).intValue(), Float.valueOf(hIv / density).intValue());
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					1.1f * beisu * wIv / density).intValue(), Float.valueOf(
					1.1f * beisu * hIv / density).intValue());
		} else {
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					beisu * wIv / density).intValue(), Float.valueOf(
					beisu * hIv / density).intValue());

		}
		params.addRule(RelativeLayout.ALIGN_LEFT);
		float ddd = (height / 480.0f);
		float topValue = beisu * 72.0f;
		int intentValue = Float.valueOf(topValue).intValue();
		params.topMargin = intentValue;

		float leftValue = ddd * (80.0f) * 1.5f;
		int intentLeftValue = Float.valueOf(leftValue).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}

	/**
	 * 对第一个气球之外的气球和实物定位
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getSelectedPaopaoParams(int height,
			float density, ImageView iv, int id, float beisu,
			boolean isAnimation) {
		int wIv = Float.valueOf(88 * density).intValue();
		int hIv = Float.valueOf(88 * density).intValue();
		RelativeLayout.LayoutParams params;
		if (isAnimation) {
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					1.1f * beisu * wIv / density).intValue(), Float.valueOf(
					1.1f * beisu * hIv / density).intValue());

		} else {
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					beisu * wIv / density).intValue(), Float.valueOf(
					beisu * hIv / density).intValue());

		}
		params.addRule(RelativeLayout.ALIGN_LEFT);
		params.addRule(RelativeLayout.BELOW, id);
		float ddd = (height / 480.0f);
		float topValue = beisu * 13.333f;// 1.5f*beisu * 18.0f / density;
		int intentValue = Float.valueOf(topValue).intValue();
		params.topMargin = intentValue;

		float leftValue = ddd * (80.0f) * 1.5f;
		int intentLeftValue = Float.valueOf(leftValue).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}

	/**
	 * 关卡 kuangao缩放
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getGuankaParams1(int height, float density,
			ImageView iv, int id, float beisu) {
		int wIv = iv.getLayoutParams().width;
		int hIv = iv.getLayoutParams().height;

		if (Constant.languageType == Constant.LANGUAGE_CHINESE) {
			wIv = (int) (400 * density);
			hIv = (int) (186.667 * density);
		} else {
			wIv = (int) (533.33 * density);
			hIv = (int) (200 * density);
		}
		RelativeLayout.LayoutParams params;

		params = new RelativeLayout.LayoutParams(Float.valueOf(
				wIv / density * beisu * 0.9f).intValue(), Float.valueOf(
				hIv / density * beisu * 0.9f).intValue());

		params.addRule(RelativeLayout.BELOW, id);
		// params.addRule(RelativeLayout.Left_OF, R.id.view1);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		float ddd = (height / 480.0f);
		float topValue = 10.0f * beisu;
		int intentValue = Float.valueOf(topValue).intValue();
		params.topMargin = intentValue;

		/*
		 * float leftValue = ddd * (80.0f) * 1.5f; int intentLeftValue =
		 * Float.valueOf(leftValue).intValue(); params.leftMargin =
		 * intentLeftValue;
		 */
		return params;
	}

	/**
	 * 关卡 kuangao缩放
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getGuankaParams2(int height, float density,
			ImageView iv, int id, float beisu) {
		int wIv = iv.getLayoutParams().width;
		int hIv = iv.getLayoutParams().height;
		RelativeLayout.LayoutParams params;

		params = new RelativeLayout.LayoutParams(Float.valueOf(
				wIv / density * beisu).intValue(), Float.valueOf(
				hIv / density * beisu).intValue());

		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		// params.addRule(RelativeLayout.Left_OF, R.id.view1);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		float ddd = (height / 480.0f);
		float topValue = 30.0f * beisu;
		int intentValue = Float.valueOf(topValue).intValue();
		params.bottomMargin = intentValue;

		/*
		 * float leftValue = ddd * (80.0f) * 1.5f; int intentLeftValue =
		 * Float.valueOf(leftValue).intValue(); params.leftMargin =
		 * intentLeftValue;
		 */
		return params;
	}

	/**
	 * replay缩放
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getReplayParams(int height, float density,
			ImageView iv, int id, float beisu) {
		int wIv = iv.getLayoutParams().width;
		int hIv = iv.getLayoutParams().height;
		RelativeLayout.LayoutParams params;
		/*
		 * if(Constant.languageType == Constant.LANGUAGE_CHINESE){ wIv = (int)
		 * (120*density); hIv = (int) (126*density);; }else{ wIv = (int)
		 * (133.33*density); hIv = (int) (126*density);; }
		 */
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				wIv / density * beisu).intValue(), Float.valueOf(
				hIv / density * beisu).intValue());

		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.LEFT_OF, id);
		// params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		float ddd = (height / 480.0f);
		float topValue = 30.0f * beisu;
		int intentValue = Float.valueOf(topValue).intValue();
		params.bottomMargin = intentValue;

		float leftValue = ddd * (50.0f) * 1.5f;
		int intentLeftValue = Float.valueOf(leftValue).intValue();
		params.rightMargin = intentLeftValue;
		return params;
	}

	/**
	 * 关卡 kuangao缩放
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getNextParams(int height, float density,
			ImageView iv, int id, float beisu) {
		int wIv = iv.getLayoutParams().width;
		int hIv = iv.getLayoutParams().height;
		RelativeLayout.LayoutParams params;
		/*
		 * if(Constant.languageType == Constant.LANGUAGE_CHINESE){ wIv = (int)
		 * (120*density); hIv = (int) (126*density);; }else{ wIv = (int)
		 * (133.33*density); hIv = (int) (126*density);; }
		 */
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				wIv / density * beisu).intValue(), Float.valueOf(
				hIv / density * beisu).intValue());

		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.RIGHT_OF, id);
		// params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		float ddd = (height / 480.0f);
		float topValue = 25.0f * beisu;
		int intentValue = Float.valueOf(topValue).intValue();
		params.bottomMargin = intentValue;

		float rightValue = ddd * (50.0f) * 1.5f;
		int intentRightValue = Float.valueOf(rightValue).intValue();
		params.leftMargin = intentRightValue;
		return params;
	}

	/**
	 * 关卡 kuangao缩放
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getAfterParams(int height, float density,
			ImageView iv, float beisu, boolean isAfter) {
		int wIv = iv.getLayoutParams().width;
		int hIv = iv.getLayoutParams().height;
		RelativeLayout.LayoutParams params;
		if (isAfter) {
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					wIv / density * beisu / 1.3f).intValue(), Float.valueOf(
					hIv / density * beisu / 1.3f).intValue());

		} else {
			params = new RelativeLayout.LayoutParams(Float.valueOf(
					wIv / density * beisu * 2.0f).intValue(), Float.valueOf(
					hIv / density * beisu * 2.0f).intValue());

		}

		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		// params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		float ddd = (height / 480.0f);

		float rightValue = (8.0f) * 1.0f * density * beisu;
		int intentRightValue = Float.valueOf(rightValue).intValue();
		params.rightMargin = intentRightValue;

		float bottomValue = (8.0f) * 1.0f * density * beisu;
		int intentbottomValue = Float.valueOf(bottomValue).intValue();

		params.bottomMargin = intentbottomValue;
		if (!isAfter) {
			params.rightMargin = Float.valueOf(-10 * density * beisu)
					.intValue();
			params.bottomMargin = Float.valueOf(-8 * density * beisu)
					.intValue();
		}
		return params;
	}

	/**
	 * 关卡 kuangao缩放
	 * 
	 * @param height
	 * @param density
	 * @param iv
	 * @param id
	 * @param beisu
	 * @param isAnimation
	 * @return
	 */
	public static LayoutParams getHomeParams(int height, float density,
			ImageView iv, float beisu) {
		int wIv = iv.getLayoutParams().width;
		int hIv = iv.getLayoutParams().height;
		RelativeLayout.LayoutParams params;

		params = new RelativeLayout.LayoutParams(Float.valueOf(
				wIv / density * beisu * 1.2f).intValue(), Float.valueOf(
				hIv / density * beisu * 1.2f).intValue());

		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		// params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		float ddd = (height / 480.0f);
		float topValue = 25.0f;
		int intentValue = Float.valueOf(topValue).intValue();
		params.topMargin = intentValue;

		float leftValue = (25.0f);
		int intentRightValue = Float.valueOf(leftValue).intValue();
		params.leftMargin = intentRightValue;
		return params;
	}

	public static LayoutParams getBaowuParams(int height, float density,
			ImageView img, float beisu) {
		/*
		 * int wIv = img.getLayoutParams().width; int hIv =
		 * img.getLayoutParams().height;
		 */
		float wIv = 100 * density;
		float hIv = 100 * density;
		RelativeLayout.LayoutParams params;

		params = new RelativeLayout.LayoutParams(Float.valueOf(
				wIv / density * beisu / 1f).intValue(), Float.valueOf(
				hIv / density * beisu / 1f).intValue());

		float ddd = (height / 480.0f);
		float value = ddd * 5.0f;
		int intentTopValue = Float.valueOf(value).intValue();
		params.topMargin = intentTopValue;
		params.bottomMargin = intentTopValue;

		float leftValue = ddd * (20.0f);
		int intentLeftValue = Float.valueOf(leftValue).intValue();
		params.leftMargin = intentLeftValue;
		params.rightMargin = intentLeftValue;
		return params;
	}

	// 试衣间衣服位置及缩放

	public static LayoutParams getClothesParams(int width, int height, int x,
			int y, float density, float beisu, int xoffset, int yoffset,
			float suoxiao) {

		RelativeLayout.LayoutParams params;

		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * beisu / suoxiao).intValue(), Float.valueOf(
				height * beisu / suoxiao).intValue());

		int intentTopValue = Float.valueOf(
				(y * beisu / suoxiao + yoffset * beisu / suoxiao)).intValue();
		params.topMargin = intentTopValue;

		int intentLeftValue = Float.valueOf(
				(x * beisu / suoxiao + xoffset * beisu / suoxiao)).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}

	// 地图页面缩小的人物专用——试衣间衣服位置及缩放
	public static LayoutParams getClothesParams2(int width, int height, int x,
			int y, float ybeisu, float beisu, int xoffset, int yoffset,
			float suoxiao) {
		RelativeLayout.LayoutParams params;

		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * beisu / suoxiao).intValue(), Float.valueOf(
				height * beisu / suoxiao).intValue());

		int intentTopValue = Float.valueOf((y * ybeisu)).intValue();
		params.topMargin = intentTopValue;

		int intentLeftValue = Float.valueOf((x * beisu)).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}

	/*-----------------------视图位置及缩放--------------------------*/

	public static RelativeLayout.LayoutParams setViewProgressPositionParams(int width, int height,
																			int belowId, int aliginLeftId, int leftValue, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(
				width * wBeisu).intValue(), Float.valueOf(
				height * hBeisu).intValue());
		params.addRule(RelativeLayout.BELOW, belowId);
		params.addRule(RelativeLayout.RIGHT_OF, aliginLeftId);
		params.topMargin = Float.valueOf((0 * wBeisu)).intValue();
		params.leftMargin = Float.valueOf((leftValue * wBeisu)).intValue();
		return params;
	}

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

	// 距离顶部和左边距离
	public static RelativeLayout.LayoutParams topAndLeftValueParams(int width,
																 int height, int topValue, int leftValue, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());
		int intentTopValue = Float.valueOf((topValue * hBeisu + hBeisu)).intValue();
		params.topMargin = intentTopValue;
		params.leftMargin = Float.valueOf((leftValue * wBeisu + wBeisu)).intValue();
		return params;
	}
	public static RelativeLayout.LayoutParams topParams(int width,
			int height, int y, int leftValue, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());

		int intentTopValue = Float.valueOf((y * hBeisu + hBeisu)).intValue();
		params.topMargin = intentTopValue;
		params.leftMargin = Float.valueOf((leftValue * wBeisu + wBeisu)).intValue();
		return params;
	}
	public static RelativeLayout.LayoutParams topAndRightParams(int width,
			int height, int y, int leftId, int rightValue, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());

		int intentTopValue = Float.valueOf((y * hBeisu + hBeisu)).intValue();
		params.topMargin = intentTopValue;

		params.rightMargin = Float.valueOf((rightValue * wBeisu + wBeisu)).intValue();
		if(leftId != 0) {
			params.addRule(RelativeLayout.LEFT_OF, leftId);
		}

		return params;
	}
	public static RelativeLayout.LayoutParams topAndLeftParams(int width,
			int height, int y, int rightId, int leftValue, float wBeisu, float hBeisu) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());

		int intentTopValue = Float.valueOf((y * hBeisu + hBeisu)).intValue();
		params.topMargin = intentTopValue;
		params.leftMargin = Float.valueOf((leftValue * wBeisu + wBeisu)).intValue();
		if(rightId != 0) {
			params.addRule(RelativeLayout.RIGHT_OF, rightId);
		}

		return params;
	}

	public static LayoutParams setViewPositionParams(float density, int width,
			int height, int x, int y, float wBeisu, float hBeisu) {

		RelativeLayout.LayoutParams params;

		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());

		int intentTopValue = Float.valueOf((y * hBeisu + hBeisu)).intValue();
		params.topMargin = intentTopValue;

		// wBeisu = (1920 / 945.0f);
		// wBeisu = (screenWidth / 945.0f); //
		wBeisu = density / 1.48f;
		System.out.println("wBeisu :" + wBeisu);
		int intentLeftValue = Float.valueOf((x * wBeisu + wBeisu)).intValue();
		params.leftMargin = intentLeftValue;
		return params;
	}

	public static LayoutParams setViewLocationParams(int width, int height,
			int top, float wBeisu, float hBeisu, int id) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * wBeisu)
				.intValue(), Float.valueOf(height * hBeisu).intValue());
		int intentLeftValue = Float.valueOf((25 * wBeisu + wBeisu)).intValue();
		params.leftMargin = intentLeftValue;
		if (id != 0) {
			params.addRule(RelativeLayout.RIGHT_OF, id);
		}
		int intentTopValue = Float.valueOf((top * hBeisu + hBeisu)).intValue();
		params.topMargin = intentTopValue;
		return params;
	}

	public static void setViewPosition(View view, int i,
			int level_position[][], float scaleQPW, float scaleQPH,
			float density) {
		view.setLayoutParams(LayoutParameters.setViewPositionParams(density,
				level_position[i][0], level_position[i][1],
				level_position[i][2], level_position[i][3], scaleQPW, scaleQPH));
	}

	public static LayoutParams setViewWidthAndHeightParams(int width, int height,
			int x, int paramsId, boolean isLeft, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * scaleQPW)
				.intValue(), Float.valueOf(height * scaleQPH).intValue());
		if (isLeft) {
			params.addRule(RelativeLayout.LEFT_OF, paramsId);
			params.rightMargin = Float.valueOf((x * scaleQPW)).intValue();
		} else {
			params.addRule(RelativeLayout.RIGHT_OF, paramsId);
			params.leftMargin = Float.valueOf((x * scaleQPW)).intValue();
		}
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		return params;
	}

	public static LayoutParams setViewWidthAndHeightParams2(int width, int height,
														   int x, int paramsId, boolean isLeft, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * scaleQPW)
				.intValue(), Float.valueOf(height * scaleQPH).intValue());
		if (isLeft) {
			params.addRule(RelativeLayout.LEFT_OF, paramsId);
			params.rightMargin = Float.valueOf((x * scaleQPW)).intValue();
		} else {
			params.addRule(RelativeLayout.RIGHT_OF, paramsId);
			params.leftMargin = Float.valueOf((x * scaleQPW)).intValue();
		}
		int intentTopValue = Float.valueOf((25 * scaleQPH)).intValue();
		params.topMargin = intentTopValue;
		return params;
	}

	public static LayoutParams setViewWidthAndHeightParams3(int width, int height,
				int paramsId, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float.valueOf(width * scaleQPW)
				.intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.leftMargin = Float.valueOf((10 * scaleQPW)).intValue();
		params.addRule(RelativeLayout.ALIGN_TOP, paramsId);
		params.addRule(RelativeLayout.RIGHT_OF, paramsId);
		params.addRule(RelativeLayout.ALIGN_BOTTOM, paramsId);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		return params;
	}
	public static LayoutParams setViewWidthAndHeightParams4(int width,
															int height, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(
				height * scaleQPH).intValue());
		params.rightMargin = Float.valueOf((15 * scaleQPW)).intValue();
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		params.addRule(RelativeLayout.CENTER_VERTICAL);
		return params;
	}

	// 居右
	public static LayoutParams setViewParentRightParams(int width,
															int height, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		return params;
	}

	// 居中
	public static LayoutParams setViewCenterParams(int width,
														int height, int topValue, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = Float.valueOf((topValue * scaleQPH)).intValue();
		return params;
	}
	public static LayoutParams setViewCenterParams(int width,
														int height, int topValue, int belowId, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.topMargin = Float.valueOf((topValue * scaleQPH)).intValue();
		if(belowId != 0) {
			params.addRule(RelativeLayout.BELOW, belowId);
		}
		return params;
	}
	// 父类居中
	public static LayoutParams setViewCenterParentParams(int width,
														int height, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		return params;
	}

	// 在一个控件中间
	public static LayoutParams setViewCenterParentParams(int width,
														int height, int centerId, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		params.addRule(RelativeLayout.ALIGN_TOP, centerId);
		params.addRule(RelativeLayout.ALIGN_BOTTOM, centerId);
		return params;
	}

	public static LayoutParams setViewHorizontalCenterAndBelowParams(int width,
														 int height, int belowId, int topValue, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.addRule(RelativeLayout.BELOW, belowId);
		params.topMargin = Float.valueOf((topValue * scaleQPH)).intValue();
		return params;
	}

	public static LayoutParams setViewTopParams(int width,
														int height, int topValue, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.topMargin = Float.valueOf((topValue * scaleQPH)).intValue();
		return params;
	}

	// 在控件的左边
	public static LayoutParams setViewToLeftParams(int width,
												   int height, int topValue, int toLeftId, int rightValue, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.topMargin = Float.valueOf((topValue * scaleQPH)).intValue();
		if(toLeftId != 0) {
			params.addRule(RelativeLayout.LEFT_OF, toLeftId);
		}
		params.rightMargin = Float.valueOf((rightValue * scaleQPW)).intValue();
		return params;
	}

//	// 居中
//	public static LayoutParams setViewCenterParams(int width,
//												   int height, int topValue, int aliginId, float scaleQPW, float scaleQPH) {
//		RelativeLayout.LayoutParams params;
//		params = new RelativeLayout.LayoutParams(Float
//				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
//		params.topMargin = Float.valueOf((topValue * scaleQPH)).intValue();
//		if(aliginId != 0) {
//			params.addRule(RelativeLayout.ALIGN_LEFT, aliginId);
//			params.addRule(RelativeLayout.ALIGN_TOP, aliginId);
//		}
//		return params;
//	}

	// 居中
	public static LayoutParams setViewCenterParams(int width,
												   int height, int topValue, int rightValue, int leftValue, int aliginId, float scaleQPW, float scaleQPH) {
		RelativeLayout.LayoutParams params;
		params = new RelativeLayout.LayoutParams(Float
				.valueOf(width * scaleQPW).intValue(), Float.valueOf(height * scaleQPH).intValue());
		params.topMargin = Float.valueOf((topValue * scaleQPH)).intValue();
		if(aliginId != 0) {
			params.addRule(RelativeLayout.ALIGN_LEFT, aliginId);
			params.addRule(RelativeLayout.ALIGN_TOP, aliginId);
		}
		if(rightValue != 0) {
			params.rightMargin = Float.valueOf((rightValue * scaleQPW)).intValue();
		}
		if(leftValue != 0) {
			params.leftMargin = Float.valueOf((leftValue * scaleQPW)).intValue();
		}
		return params;
	}

}
