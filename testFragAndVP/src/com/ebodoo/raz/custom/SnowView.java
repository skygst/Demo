package com.ebodoo.raz.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;

import com.gst.move.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SnowView extends View {
	// 用于驱动所有单独的片状动画,而不是潜在的数以百计的独立的画师,我们只使用一个,然后更新所有片每一帧的动画。
	private ValueAnimator mAnimator = ValueAnimator.ofFloat(0, 1);
	// 上个时间
	private long mPrevTime;
	private Matrix mMatrix; // 矩阵用于移动每片呈现
	private Bitmap mSnowBitmapOne, mSnowBitmapTwo, mSnowBitmapThree,
			mSnowBitmapFour, mSnowBitmapFive;
	private ArrayList<Bitmap> mSnowBitmaps;
	// 雪花的个数
	private int mSnowNumber = 0;
	// 最多显示雪花的个数 （75）
//	private static final int MAX_SNOW_NUMBER = 75;
	private static final int MAX_SNOW_NUMBER = 5;

	private ArrayList<Snow> mSnows;// 雪花集合

	public SnowView(Context context) {
		this(context, null);
	}

	public SnowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SnowView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initData();
	}

	private void initData() {
		mMatrix = new Matrix();
		mSnows = new ArrayList<Snow>();

		mSnowBitmapOne = BitmapFactory.decodeResource(getResources(),
				R.drawable.color_apple);
//		mSnowBitmapTwo = BitmapFactory.decodeResource(getResources(),
//				R.drawable.color_apple);
//		mSnowBitmapThree = BitmapFactory.decodeResource(getResources(),
//				R.drawable.color_apple);
//		mSnowBitmapFour = BitmapFactory.decodeResource(getResources(),
//				R.drawable.color_apple);
//		mSnowBitmapFive = BitmapFactory.decodeResource(getResources(),
//				R.drawable.color_apple);

		mSnowBitmaps = new ArrayList<Bitmap>();
		mSnowBitmaps.add(mSnowBitmapOne);
//		mSnowBitmaps.add(mSnowBitmapTwo);
//		mSnowBitmaps.add(mSnowBitmapThree);
//		mSnowBitmaps.add(mSnowBitmapFour);
//		mSnowBitmaps.add(mSnowBitmapFive);

		mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator arg0) {
				// 每一帧的动画,我们计算运行时间和更新每一片的位置和旋转它的速度。
				long nowTime = System.currentTimeMillis();
				float secs = (nowTime - mPrevTime) / 1000f;
				mPrevTime = nowTime;

				for (Snow snow : mSnows) {
					// 新的位置
					snow.y += snow.speed * secs;

					if (snow.y > getHeight()) {
						// 如果已经到达底部，从上面从新再来一次,重置snow y 的位置
						snow.y = 0 - snow.height;
					}

					// 雪花旋转
					snow.rotation = snow.rotation + (snow.rotationSpeed * secs);
				}
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
		for (Snow snow : mSnows) {
			// 利用矩阵去实现
			mMatrix.setTranslate(-snow.width / 2, -snow.height / 2);
			mMatrix.postRotate(snow.rotation);
			mMatrix.postTranslate(snow.width / 2 + snow.x, snow.height
					/ 2 + snow.y);
			canvas.drawBitmap(snow.bitmap, mMatrix, null);
		}
		// TODO 下面计算帧速率
	}

	/**
	 * 开始下雪
	 */
	public void startSnow(int rangeWidth) {
		mPrevTime = System.currentTimeMillis();
		// 没有添加直接开始下雪时，默认添加15个
		addSnows(15, rangeWidth);
		mAnimator.start();
	}

	public int getSnowNumber() {
		return this.mSnowNumber >= MAX_SNOW_NUMBER ? MAX_SNOW_NUMBER
				: mSnowNumber;
	}

	/**
	 * 添加雪花
	 */
	public void addSnows(int snowNumber, int rangeWidth) {
		System.out.println("-----------snowNumber-------------" + snowNumber);
		if (mSnowNumber < MAX_SNOW_NUMBER) {
			for (int i = 0; i < snowNumber; i++) {
				// 随机挑选 (也可以根据位置求%,等比例)
				int index = (int) (Math.random() * 1);
				Bitmap snowBitmap = mSnowBitmaps.get(index);
				Snow snow = Snow.createSnow(snowBitmap, rangeWidth);
				mSnows.add(snow);
			}
			// 当前雪花数量叠加
			mSnowNumber += snowNumber;
		}
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
			// 根据比例算出高度
			snow.height = (int) (snow.width * hwRatio);

			// x位置在[snow.width,所传递的宽度] 任意位置
			snow.x = (float) Math.random() * (xRange - snow.width);

			// 定位雪花垂直稍微偏离顶部显示
			snow.y = 0 - (snow.height + (float) Math.random() * snow.height);

			// 每秒的下落速度在 50~300之间 + snow.width
			snow.speed = 50 + (float) Math.random() * 250 + snow.width;

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
					+ "speed:" + speed + "  " + "x:" + x + "  " + "y:" + y
					+ "rotation:" + rotation + "rotationSpeed:" + rotationSpeed;
		}
	}
}
