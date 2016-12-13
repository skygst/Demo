package com.gst.move.custom;

import com.gst.move.custom.MyHorizonScrollView.InnerScrollViewScrollListener;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * @author KingKong
 * 主要目的是对于ScrollView和HorizontalScrollView没有现成的接口来监听滚动
 */
public class ParallaxInnerScrollView extends HorizontalScrollView{
    /**
     * 滚动监听
     */
    private InnerScrollViewScrollListener mScrollListener;
    
    /**
     * 设置滚动监听
     * @param listener
     */
    public void setOnScrollListener(InnerScrollViewScrollListener listener) {
        mScrollListener = listener;
    }
    
    
    public ParallaxInnerScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ParallaxInnerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxInnerScrollView(Context context) {
        super(context);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (null != mScrollListener) {
            mScrollListener.onScroll();
        }
    }
}
