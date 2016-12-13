package com.gst.move.custom;

import com.gst.move.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * @author KingKong
 * 视差滚动布局
 */
public class MyHorizonScrollView extends FrameLayout{

    public MyHorizonScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizonScrollView(Context context) {
        super(context);
    }


    /**
     * background和foreground滚动的视差参数
     */
    private float mDiffFactor = 0;

    /**
     * child scrollview
     */
    private ParallaxInnerScrollView mForeground;

    /**
     * background
     */
    private View mBackgournd;

    /**
     * content
     */
    private RelativeLayout mContent;

    /**
     * 视差滚动根布局
     */
    private View mParallaxScrollWidget;

    /**
     * 背景显示
     */
    private View mBackgroundContent;

    private Scroller mScroller;

    /**
     * 初始化
     */
    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.my_parallax_scrollview, this);
        mParallaxScrollWidget = findViewById(R.id.parallax_scroll_widget);
        mBackgournd = findViewById(R.id.background);
        mBackgroundContent = findViewById(R.id.background_content);
        mForeground = (ParallaxInnerScrollView) findViewById(R.id.foreground);
        mForeground.setOnScrollListener(onScrollListener);
        mContent = (RelativeLayout) findViewById(R.id.content);
        transferChildren();
    }

    /**
     * 移动子view，将布局文件中设定的子view移动可滚动的view中
     */
    private void transferChildren() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(0);
            if (child != mParallaxScrollWidget) {
                detachFromParent(child);
                mContent.addView(child);
            }
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        init();
    }

    /**
     * 添加child到可滚动的视图当中
     * @param child child
     */
    public void addChildView(View child) {
        detachFromParent(child);
        mContent.addView(child);
    }

    /**
     * 添加child到可滚动的视图当中
     * @param child child
     * @param index index
     */
    public void addChildView(View child, int index) {
        detachFromParent(child);
        mContent.addView(child, index);
    }

    /**
     * 添加child到可滚动的视图当中
     * @param child child
     * @param width width
     * @param height height
     */
    public void addChildView(View child, int width, int height) {
        detachFromParent(child);
        mContent.addView(child, width, height);
    }


    /**
     * detach from parent
     * @param childView
     */
    private void detachFromParent(View childView) {
        if (null != childView) {
            ViewGroup parent = (ViewGroup) childView.getParent();
            if (null != parent) {
                parent.removeView(childView);
            }
        }
    }


    /**
     * 设置背景
     * @param drawableId
     */
    public void setBackgroundDrawableId(int drawableId) {
        if (null != mBackgroundContent && drawableId >= 0) {
            mBackgroundContent.setBackgroundResource(drawableId);
        }
    }

    /* (non-Javadoc)
     * @see android.view.View#setBackgroundDrawable(android.graphics.drawable.Drawable)
     */
    public void setBackgroundDrawable(Drawable drawable) {
        if (null != drawable && null != mBackgroundContent) {
            mBackgroundContent.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置前景
     * @param drawableId
     */
    public void setForegroundDrawableId(int drawableId) {
        if (null != mForeground && drawableId >= 0) {
            mForeground.setBackgroundResource(drawableId);
        }
    }

    /**
     * 设置前景
     * @param drawable
     */
    public void  setForegroundDrawable(Drawable drawable) {
        if (null != mForeground && null != drawable) {
            mForeground.setBackgroundDrawable(drawable);
        }
    }
    public void  setForegroundDrawable2(Drawable drawable) {
        if (null != mForeground && null != drawable) {
            mForeground.setBackgroundDrawable(drawable);
        }
    }

    /**
     * 设置视差滚动参数
     * @param diffFactor
     */
    public void setDiffFactor(float diffFactor) {
        this.mDiffFactor = diffFactor;
        invalidate();
    }


    /**
     * background相对于屏幕可以滚动的距离
     */
    private int mBackgroundDiff;

    /**
     * foreground内容相对于屏幕可以滚动的距离
     */
    private int mForegroundDiff;


    /**
     * 视差因子，用于根据foreground滚动的距离来计算background的滚动
     */
    private float mParallaxFactor;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isInEditMode()) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        //先measure一次，以得到child的尺寸
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int foregroundTotalWidth = getChildScrollViewTotalWidth();
        mWidthControl = foregroundTotalWidth;
        System.out.println("--------=========onMeasure====mWidthControl====" + mWidthControl);
        //计算需要额外布局的宽度
        int scrollDiff = computeScrollDiffDistance(mBackgournd.getMeasuredWidth(), foregroundTotalWidth);
        int newBackgroundWidth = mBackgournd.getMeasuredWidth() + scrollDiff;
        //重新measure一下background
        measureChild(mBackgournd, MeasureSpec.makeMeasureSpec(newBackgroundWidth, MeasureSpec.EXACTLY), heightMeasureSpec);

        mBackgroundDiff = mBackgournd.getMeasuredWidth() - getMeasuredWidth();
        mForegroundDiff = foregroundTotalWidth - getMeasuredWidth();
        mParallaxFactor = ((float) mBackgroundDiff) / ((float) mForegroundDiff);
    }
    /**
     * 计算滚动差
     * @param backgroundHeight
     * @param forgroundHeight
     * @return 背景需要额外增加的高度
     */
    private int computeScrollDiffDistance(int backgroundHeight, int forgroundHeight) {
        if (forgroundHeight <= backgroundHeight) {
            return 0;
        }
        int heightDiff = forgroundHeight - backgroundHeight;
        int scrollDiff = (int) (heightDiff * mDiffFactor);
        return scrollDiff;
    }


    /**
     * 返回scrollview 内容高度（在onMeasure中或者结束后调用）
     * @return child scroll view的总高度
     */
    private int getChildScrollViewTotalHeight() {
        if (null == mContent) {
            return 0;
        }
        return mContent.getMeasuredHeight();
    }

    private int getChildScrollViewTotalWidth() {
        if (null == mContent) {
            return 0;
        }
        return mContent.getMeasuredWidth();
    }

    /**
     * forground滚动监听
     */
    private InnerScrollViewScrollListener onScrollListener = new InnerScrollViewScrollListener() {
        @Override
        public void onScroll() {

            handler.sendMessage(handler.obtainMessage(0));
        }
    };

    public int lastScrollX = 0;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    int backgroundScrollX = (int) (mParallaxFactor * mForeground.getScrollX());
                    if(backgroundScrollX != lastScrollX) {
                        lastScrollX = backgroundScrollX;
                        mBackgournd.scrollTo(backgroundScrollX, 0);
                    }
                    break;

                default:
                    break;
            }
        };
    };

    /**
     * @author yuanxingzhong
     * 监听forground 的滚动
     */
    public static interface InnerScrollViewScrollListener {
        public void onScroll();
    }

    private boolean isScrolling = false;
    private final int SLEEP_EVERY_TIME = 1;
    public void roreydiuAotuScroll(final int moveToX) {
        if (isScrolling == true) {
            // Toast.makeText(getContext(), "正在自动滑动", 0).show();
            return;
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    isScrolling = true;
                    int range = 0;
                    if ((moveToX - mForeground.getScrollX()) >= 0) {
                        /** 这里只是我的视图总高度是10500 因为我的手机像素密度是3.0的 做的只是一个简单的演示 没有写规范 **/
                        // 这里的目的是当输入的值过大时 能够即时判断 不让线程做一些过多的无用工作 因为已经早就滑到了底部
                        // 也避免触摸事件的长久屏蔽 实际运用中不会出现这样的问题 所以不加入此判断也是没有关系的
                        if (moveToX > mWidthControl) {
                            range = mWidthControl - mForeground.getScrollX();
                        } else {
                            range = (int) (moveToX - mForeground.getScrollX());
                        }
                        for (int i = 0; i < range/2; i++) {
                            Thread.sleep(SLEEP_EVERY_TIME);
                            handler1.sendEmptyMessage(POSITIVE_MARK);
                        }
                    } else {
//                        range = (int) (mForeground.getScrollX() - moveToX);
                        range = (int) (mDx - moveToX);
                        for (int i = 0; i < range/2; i++) {
                            Thread.sleep(SLEEP_EVERY_TIME);
                            handler1.sendEmptyMessage(NAGATIVE_MARK);
                        }
                    }
                    isScrolling = false;// 滑动完毕可以再次开启
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /** 正向移动信息标志 **/
    private final int POSITIVE_MARK = 0;
    /** 负向移动信息标志 **/
    private final int NAGATIVE_MARK = 1;

    private final int POSITIVE_MOVE_Y = 2;
    private final int NAGATIVE_MOVE_Y = -2;

    private Handler handler1 = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case POSITIVE_MARK:
                    mForeground.scrollBy(POSITIVE_MOVE_Y, 0);
                    break;
                case NAGATIVE_MARK:
                    mForeground.scrollBy(NAGATIVE_MOVE_Y, 0);
                    break;
                case 101:
                    int location = (Integer) msg.obj;
                    roreydiuAotuScroll(location);
                    //必须调用该方法，否则不一定能看到滚动效果
                    postInvalidate();
                    break;
                case 102:
                    mForeground.scrollTo(mDx, 0);
                    break;
            }
        }
    };

    private int mDx;
    private int mWidthControl;
    private boolean isFinish = false;
    private boolean mIsScrollTo = false;
    private boolean mIsStartMove = true;
    private int screenWidth;

    //调用此方法设置滚动的相对偏移
    public void smoothScrollBy(Context context, int width, boolean isScrollTo, boolean isStartmove) {
        mScroller = new Scroller(context);
//        mIsStartMove = isStartmove;
        mIsScrollTo = isScrollTo;
        screenWidth = width;
        System.out.println("--------=========smoothScrollBy==mWidthControl==" + mWidthControl);
        if(mWidthControl > 0 && mIsStartMove) {
            mIsStartMove = false;
            startMove();
        }
    }

    private void startMove() {
    	if(mScroller != null) {
    		if(mIsScrollTo) {
    			isFinish = true;
    			mDx = screenWidth;
//    			int duration = (mDx/2 + 50);
    			int duration = mDx;
    			mScroller.startScroll(0, 0, mDx, 0, duration);
    		} else {
    			mDx = mWidthControl - screenWidth;
//    			int duration = (mDx/2 + 50);
    			int duration = mDx;
    			mScroller.startScroll(0, 0, mDx, 0, duration);
    		}
    		invalidate();//这里必须调用invalidate()才能保证computeScroll()会被调用，否则不一定会刷新界面，看不到滚动效果
    	}
    }
    
    private boolean isStart = false;

    @Override
    public void computeScroll() {
        //先判断mScroller滚动是否完成
        if (mScroller != null) {
            if(mScroller.computeScrollOffset()) {
                //这里调用View的scrollTo()完成实际的滚动
				try {
					if(!isStart) {
						isStart = true;
						System.out.println("--------=========computeScroll==mWidthControl==");
						Thread.sleep(0);
						handler1.sendMessage(handler1.obtainMessage(101, mDx));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            } else {
                if(!isFinish) {
                    isScrolling = false;
                    isFinish = true;
                    try {
                        Thread.sleep(0);
                        handler1.sendMessage(handler1.obtainMessage(101, 0));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        super.computeScroll();
    }
}
