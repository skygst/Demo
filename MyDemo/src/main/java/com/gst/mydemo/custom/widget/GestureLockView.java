package com.gst.mydemo.custom.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.view.Display;
import android.view.View;

public class GestureLockView extends View
{
	private static final String TAG = "GestureLockView";
	/**
	 * GestureLockView的三种状态
	 */
	enum Mode
	{
		STATUS_NO_FINGER, STATUS_FINGER_ON, STATUS_FINGER_UP;
	}

	/**
	 * GestureLockView的当前状态
	 */
	private Mode mCurrentStatus = Mode.STATUS_NO_FINGER;
	
	/**
	 * 宽度
	 */
	private int mWidth;
	/**
	 * 高度
	 */
	private int mHeight;
	/**
	 * 外圆半径
	 */
	private int mRadius;
	/**
	 * 画笔的宽度
	 */
	private int mStrokeWidth = 2;

	/**
	 * 圆心坐标
	 */
	private int mCenterX;
	private int mCenterY;
	private Paint mPaint;

	/**
	 * 箭头（小三角最长边的一半长度 = mArrawRate * mWidth / 2 ）
	 */
	private float mArrowRate = 0.333f;
	private int mArrowDegree = -1;
	private Path mArrowPath;
	/**
	 * 内圆的半径 = mInnerCircleRadiusRate * mRadus
	 * 
	 */
	private float mInnerCircleRadiusRate = 0.3F;

	/**
	 * 四个颜色，可由用户自定义，初始化时由GestureLockViewGroup传入
	 */
	private int mColorNoFingerInner;
	private int mColorNoFingerOutter;
	private int mColorFingerOn;
	private int mColorFingerUp;
	private String mContent;
	private int screenWidth;

	public GestureLockView(Context context , int colorNoFingerInner , int colorNoFingerOutter,
			int colorFingerOn , int colorFingerUp, String content)
	{
		super(context);
		this.mColorNoFingerInner = colorNoFingerInner;
		this.mColorNoFingerOutter = colorNoFingerOutter;
		this.mColorFingerOn = colorFingerOn;
		this.mColorFingerUp = colorFingerUp;
		this.mContent = content;
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mArrowPath = new Path();
		Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
		screenWidth = display.getWidth();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);
System.out.println("get--mWidth :" + widthMeasureSpec);
		// 取长和宽中的小值
		mWidth = mWidth < mHeight ? mWidth : mHeight;
		mRadius = mCenterX = mCenterY = mWidth / 2;
		mRadius -= mStrokeWidth / 2;

		// 绘制三角形，初始时是个默认箭头朝上的一个等腰三角形，用户绘制结束后，根据由两个GestureLockView决定需要旋转多少度
		float mArrowLength = mWidth / 2 * mArrowRate;
		mArrowPath.moveTo(mWidth / 2, mStrokeWidth + 2);
		mArrowPath.lineTo(mWidth / 2 - mArrowLength, mStrokeWidth + 2
				+ mArrowLength);
		mArrowPath.lineTo(mWidth / 2 + mArrowLength, mStrokeWidth + 2
				+ mArrowLength);
		mArrowPath.close();
		mArrowPath.setFillType(Path.FillType.WINDING);

	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		System.out.println("onDraw mCurrentStatus :" + mCurrentStatus);
		switch (mCurrentStatus)
		{
		
		case STATUS_FINGER_ON:
			// 绘制外圆
			mPaint.setStyle(Style.STROKE);
			mPaint.setColor(mColorFingerOn);
			mPaint.setStrokeWidth(2);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			// 绘制内圆
			mPaint.setStyle(Style.FILL);
//			canvas.drawCircle(mCenterX, mCenterY, mRadius
//					* mInnerCircleRadiusRate, mPaint);

			addTextView(canvas);
			break;
		case STATUS_FINGER_UP:
			// 绘制外圆
			mPaint.setColor(mColorFingerUp);
			mPaint.setStyle(Style.STROKE);
			mPaint.setStrokeWidth(2);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			// 绘制内圆
			mPaint.setStyle(Style.FILL);
//			canvas.drawCircle(mCenterX, mCenterY, mRadius
//					* mInnerCircleRadiusRate, mPaint);
			drawArrow(canvas);

			addTextView(canvas);
			break;

		case STATUS_NO_FINGER:

			// 绘制外圆
			mPaint.setColor(mColorNoFingerInner);
			mPaint.setColor(mColorNoFingerOutter);
			canvas.drawCircle(mCenterX, mCenterY, mRadius, mPaint);
			// 绘制内圆
			mPaint.setStyle(Style.FILL);
//			canvas.drawCircle(mCenterX, mCenterY, mRadius
//					* mInnerCircleRadiusRate, mPaint);

			addTextView(canvas);
			break;

		}

	}

	/**
	 * 添加文本
	 * @param canvas
	 */
	private void addTextView(Canvas canvas) {
		mPaint.setColor(Color.BLACK);
		System.out.println("getWidth() :" + getWidth());
		System.out.println("getHeight() :" + getHeight());
		System.out.println("get screenWidth :" + screenWidth);
		// TODO 竖屏显示使用 screenWidth；横屏显示使用 screenHeight
		float rate = (float) screenWidth/320;
		System.out.println("get rate :" + rate);
		System.out.println("get rate :" + (40*rate));
		int txtSize = (int)(40*rate);
		mPaint.setTextSize(txtSize);
		canvas.drawText(mContent, getWidth() / 3.0f, getHeight() / 1.50f, mPaint);
	}

	/**
	 * 绘制箭头
	 * @param canvas
	 */
	private void drawArrow(Canvas canvas)
	{
		if (mArrowDegree != -1)
		{
			mPaint.setStyle(Style.FILL);

			canvas.save();
			canvas.rotate(mArrowDegree, mCenterX, mCenterY);
			canvas.drawPath(mArrowPath, mPaint);

			canvas.restore();
		}

	}

	/**
	 * 设置当前模式并重绘界面
	 * 
	 * @param mode
	 */
	public void setMode(Mode mode)
	{
		this.mCurrentStatus = mode;
		invalidate();
	}

	public void setArrowDegree(int degree)
	{
		this.mArrowDegree = degree;
	}

	public int getArrowDegree()
	{
		return this.mArrowDegree;
	}
}
