package com.example.location.biz;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.gst.move.utils.DisplayUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringBiz {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		boolean isEmpty = true;
		if (value != null && !value.trim().equals("")) {
			isEmpty = false;
		}
		return isEmpty;
	}

	/**
	 * 判断字符串是否是数字(0.0)
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		char[] p = str.toCharArray();
		for (int i = 0; i < p.length; i++) {
			if (!isNum("" + p[i])) {
				return false;
			}
		}
		return true;
	}

	private static boolean isNum(String str) {
		Pattern pattern = Pattern.compile("[0-9.]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	// 拼接不同颜色及字体的字符串
	public static SpannableStringBuilder mosaicString(Context context, String content1, String content2, int color1, int color2) {
		int len1 = !StringBiz.isEmpty(content1) ? content1.length() : 0;
		int len2 = !StringBiz.isEmpty(content2) ? content2.length() : 0;
		ColorStateList blackColors = ColorStateList.valueOf(color1);
		ColorStateList red2Colors = ColorStateList.valueOf(color2);
		SpannableStringBuilder spanBuilder = new SpannableStringBuilder(content1 + content2);
		//style 为0 即是正常的，还有Typeface.BOLD(粗体) Typeface.ITALIC(斜体)等
		//size  为0 即采用原始的正常的 size大小
		//TextAppearanceSpan 五个参数分别是，字体名称，字体样式，字体大小--单位是px，字体颜色，字体链接颜色
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, 25.0f), blackColors, null), 0, len1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DisplayUtil.sp2px(context, 15.0f), red2Colors, null), len1, (len1 + len2), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		System.out.println("------spanBuilder :" + spanBuilder);
		return spanBuilder;
	}

	// 购买点评次数文本
	public static SpannableStringBuilder buyCommentsTimesString(Context context, String contentTime, String times, int color1, int color2, int color3) {
		DisplayMetrics metric = new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metric);
		int height = metric.heightPixels; // 屏幕高度（像素）

		String content1 = "您的";
		String content2 = " “求点评” ";
		String content3 = "将在 ";
		String content4 = contentTime;
		String content5 = " 到期，如不续费，您将损失剩余 ";
		String content6 = times;
		String content7 = " 次点评机会";
		String content8 = "\n(若续费成功，剩余点评次数将累计到次月继续使用)";
		int len1 = !StringBiz.isEmpty(content1) ? content1.length() : 0;
		int len2 = !StringBiz.isEmpty(content2) ? content2.length() : 0;
		int len3 = !StringBiz.isEmpty(content3) ? content3.length() : 0;
		int len4 = !StringBiz.isEmpty(content4) ? content4.length() : 0;
		int len5 = !StringBiz.isEmpty(content5) ? content5.length() : 0;
		int len6 = !StringBiz.isEmpty(content6) ? content6.length() : 0;
		int len7 = !StringBiz.isEmpty(content7) ? content7.length() : 0;
		int len8 = !StringBiz.isEmpty(content8) ? content8.length() : 0;

		ColorStateList blackColors = ColorStateList.valueOf(color1);
		ColorStateList red2Colors = ColorStateList.valueOf(color2);
		ColorStateList grayColor3 = ColorStateList.valueOf(color3);
		SpannableStringBuilder spanBuilder = new SpannableStringBuilder(content1 + content2 + content3 + content4 + content5 + content6 + content7 + content8);
		float bigTxtSize = 15.0f;
		float smallTxtSize = 11.0f;
		if(height < 720) {
			bigTxtSize = 13.0f;
			smallTxtSize = 10.0f;
		}
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, bigTxtSize), blackColors, null), 0, len1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, bigTxtSize), red2Colors, null), len1, (len1 + len2), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, bigTxtSize), blackColors, null), (len1 + len2), (len1 + len2 + len3), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, bigTxtSize), red2Colors, null), (len1 + len2 + len3), (len1 + len2 + len3 + len4), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, bigTxtSize), blackColors, null), (len1 + len2 + len3 + len4), (len1 + len2 + len3 + len4 + len5), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, bigTxtSize), red2Colors, null), (len1 + len2 + len3 + len4 + len5), (len1 + len2 + len3 + len4 + len5 + len6), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, bigTxtSize), blackColors, null), (len1 + len2 + len3 + len4 + len5 + len6), (len1 + len2 + len3 + len4 + len5 + len6 + len7), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DisplayUtil.sp2px(context, smallTxtSize), grayColor3, null), (len1 + len2 + len3 + len4 + len5 + len6 + len7), (len1 + len2 + len3 + len4 + len5 + len6 + len7 + len8), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		return spanBuilder;
	}


	// 购买点评次数文本
	public static SpannableStringBuilder buyCommentsTimesSorryString(Context context, int color1, int color2) {
		String content1 = "Sorry！\n您本月的 ";
		String content2 = "“求点评”";
		String content3 = " 次数已全部使用完，如果您想继续使用，请另购使用次数哦";
		int len1 = !StringBiz.isEmpty(content1) ? content1.length() : 0;
		int len2 = !StringBiz.isEmpty(content2) ? content2.length() : 0;
		int len3 = !StringBiz.isEmpty(content3) ? content3.length() : 0;

		ColorStateList red2Colors = ColorStateList.valueOf(color1);
		ColorStateList grayColor3 = ColorStateList.valueOf(color2);
		SpannableStringBuilder spanBuilder = new SpannableStringBuilder(content1 + content2 + content3);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, 15.0f), grayColor3, null), 0, len1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, 15.0f), red2Colors, null), len1, (len1 + len2), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, 15.0f), grayColor3, null), (len1 + len2), (len1 + len2 + len3), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		return spanBuilder;
	}

	// 点评次数
	public static SpannableStringBuilder commentsTimesString(Context context, String content1,
															 String content2, String content3, int color1, int color2) {
		int len1 = !StringBiz.isEmpty(content1) ? content1.length() : 0;
		int len2 = !StringBiz.isEmpty(content2) ? content2.length() : 0;
		int len3 = !StringBiz.isEmpty(content3) ? content3.length() : 0;;
		ColorStateList blackColors = ColorStateList.valueOf(color1);
		ColorStateList redColors = ColorStateList.valueOf(color2);
		SpannableStringBuilder spanBuilder = new SpannableStringBuilder(content1 + content2 + content3);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DisplayUtil.sp2px(context, 18.0f), blackColors, null), 0, len1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		if(!StringBiz.isEmpty(content2)) {
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, 22.0f), redColors, null), len1, (len1 + len2), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		}
		if(!StringBiz.isEmpty(content3)) {
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DisplayUtil.sp2px(context, 14.0f), blackColors, null), (len1 + len2), (len1 + len2 + len3), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		}
		return spanBuilder;
	}

	public static SpannableStringBuilder commentsTimesString(Context context, String content1,
															 String content2, String content3, int color1, int color2, float smallSize, float bigSize) {
		int len1 = !StringBiz.isEmpty(content1) ? content1.length() : 0;
		int len2 = !StringBiz.isEmpty(content2) ? content2.length() : 0;
		int len3 = !StringBiz.isEmpty(content3) ? content3.length() : 0;;
		ColorStateList blackColors = ColorStateList.valueOf(color1);
		ColorStateList redColors = ColorStateList.valueOf(color2);
		SpannableStringBuilder spanBuilder = new SpannableStringBuilder(content1 + content2 + content3);
		spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DisplayUtil.sp2px(context, smallSize), blackColors, null), 0, len1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		if(!StringBiz.isEmpty(content2)) {
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.BOLD, DisplayUtil.sp2px(context, bigSize), redColors, null), len1, (len1 + len2), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		}
		if(!StringBiz.isEmpty(content3)) {
			spanBuilder.setSpan(new TextAppearanceSpan(null, Typeface.NORMAL, DisplayUtil.sp2px(context, smallSize), blackColors, null), (len1 + len2), (len1 + len2 + len3), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		}
		return spanBuilder;
	}

	// 拼接Json格式字符串
	public static String spliceJson(String[] idArray, String[] numArray) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (int i = 0; i < idArray.length; i++) {
			if (i != idArray.length - 1) {
				sb.append("\"" + idArray[i] + "\":" + numArray[i] + ",");
			} else {
				sb.append("\"" + idArray[i] + "\":" + numArray[i] + "}");
			}
		}
		return sb.toString();
	}

	// 字体加粗
	public static void setTextPaint(TextView tv) {
		TextPaint tp = tv.getPaint();
		tp.setFakeBoldText(true);
	}

}
