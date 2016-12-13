package com.ebodoo.raz.custom;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.gst.move.R;

import java.util.HashMap;

/**
 * 
 * ============================================================
 * 
 * project name : TiantianFangFu
 * 
 * copyright ZENG HUI (c) 2015
 * 
 * author : HUI
 * 
 * QQ ：240336124
 * 
 * version : 1.0
 * 
 * date created : On July, 2015
 * 
 * description : 单个雪花飘落
 * 
 * revision history :
 * 
 * ============================================================
 *
 */
public class SingleSnowView extends View {
	// 用于驱动所有单独的片状动画,而不是潜在的数以百计的独立的动画,我们只使用一个,然后更新所有片每一帧的动画。
	private ValueAnimator mAnimator = ValueAnimator.ofFloat(0, 1);
	// 用于追踪时间为动画和 Frames per second
	private long mPrevTime;
	private Snow mSnow;
	private Matrix mMatrix; // 矩阵用于移动每片呈现
	private Bitmap mSnowBitmap;

	public SingleSnowView(Context context) {
		this(context, null);
	}

	public SingleSnowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SingleSnowView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initData();
	}

	private void initData() {
		mMatrix = new Matrix();

		mSnowBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.color_apple);

		mAnimator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// 每一帧的动画,我们计算运行时间和更新每一片的位置和旋转它的速度。
				long nowTime = System.currentTimeMillis();
				float secs = (nowTime - mPrevTime) / 1000f;
				mPrevTime = nowTime;
				// 新的位置
				mSnow.y += mSnow.speed * secs;

				if (mSnow.y > getHeight()) {
					// 如果已经到达底部，从上面从新再来一次,重置snow y 的位置
					mSnow.y = 0 - mSnow.height;
				}

				// 雪花旋转
				mSnow.rotation = mSnow.rotation + (mSnow.rotationSpeed * secs);

				// 请求重新去刷新绘制界面
				invalidate();
			}
		});
		mAnimator.setRepeatCount(ValueAnimator.INFINITE);
		mAnimator.setDuration(2000);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 利用矩阵去实现
		mMatrix.setTranslate(-mSnow.width / 2, -mSnow.height / 2);
		mMatrix.postRotate(mSnow.rotation);
		mMatrix.postTranslate(mSnow.width / 2 + mSnow.x, mSnow.height
				/ 2 + mSnow.y);
		canvas.drawBitmap(mSnow.bitmap, mMatrix, null);
	}

	/**
	 * 开始下雪
	 */
	public void startSnow(int rangeWidth) {
		if (mSnow == null) {
			mPrevTime = System.currentTimeMillis();
			mSnow = Snow.createSnow(mSnowBitmap, rangeWidth);
		}
		mAnimator.start();
	}

	/**
	 * 停止下雪
	 */
	public void stopSnow() {
		mAnimator.cancel();
	}

	public static class Snow {
		// 旋转角度和速率
		public float rotation;
		public float rotationSpeed;
		// 在view中的x,y点的位置
		public float x, y;
		// 下落的速度
		public float speed;
		// 高度和宽度
		public int width, height;
		// bitmap
		public Bitmap bitmap;

		// 根据宽度存放bitmap，以便同样的宽度可以重集合中获取
		private static HashMap<Integer, Bitmap> bitmapMap = new HashMap<Integer, Bitmap>();

		public static Snow createSnow(Bitmap snowBitmap, int xRange) {
			Snow snow = new Snow();
			// 宽度在8~58之间
			snow.width = (int) (8 + (float) Math.random() * 50);
			// 根据传进来的bitmap宽高求出比例
			float hwRatio = snowBitmap.getHeight() / snowBitmap.getWidth();
			// 算出高度
			snow.height = (int) (snow.width * hwRatio);

			// x位置在[snow.width,所属View中宽度] 任意位置
			snow.x = (float) Math.random() * (xRange - snow.width);

			// 定位雪花垂直稍微偏离的顶部显示
			snow.y = 0 - (snow.height + (float) Math.random() * snow.height);

			// 每秒的下落速度在 50~300之间
			snow.speed = 50 + (float) Math.random() * 250;

			// 片开始在-90 - 90度旋转,旋转速度到-45 - 45之间
			snow.rotation = (float) Math.random() * 180 - 90;
			snow.rotationSpeed = (float) Math.random() * 90 - 45;

			// 先根据宽度从缓存中获取
			snow.bitmap = bitmapMap.get(snow.width);
			if (snow.bitmap == null) {
				// 如果缓存中没有，创建,缓存到集合
				snow.bitmap = Bitmap.createScaledBitmap(snowBitmap, snow.width,
						snow.height, true);
				bitmapMap.put(snow.width, snow.bitmap);
			}
			return snow;
		}

		@Override
		public String toString() {
			return "width:" + width + "  " + "height:" + height + "  "
					+ "speed:" + speed + "  " + "x:" + x + "  " + "y:" + y;
		}
	}
}
