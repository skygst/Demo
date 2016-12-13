package com.gst.move.home_work;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.AnimationBiz;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.SnowView;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.LayoutParameters;
import com.gst.move.R;
import com.gst.move.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *  填色游戏
 */
public class ColoringAGameActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    private RelativeLayout rlLayout;
    private ImageView ivBack, ivBg, ivDotted, ivRightWord, ivStar, ivBottom1, ivBottom2, ivBottom3;
    private ImageView ivAnimation1, ivAnimation2, ivAnimation3, ivAnimation4, ivAnimation5, ivAnimation6, ivAnimation7, ivAnimation8;
    private ImageView[] ivAnimationView;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;
    private int refreshNum = 0, clickNum = 0;
    private SnowView snowView;
    private Runnable runnableRain;
    private String[] colorBg = new String[] {
            "brown", "green", "blue"
    };
    private String[] rightWord = new String[] {
            "pple", "nt", "lligator"
    };
    private int[] soundId = new int[] {
            R.raw.apple, R.raw.ant, R.raw.alligator
    };
    private AnimationDrawable animation1, animation2, animation3, animation4, animation5, animation6;
    private boolean isCanMove = true;
    private boolean isFillColoring = true;
    private MediaPlayer mMediaPlayer;
    private boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coloring_game);

        init();
        setView();
    }

    private void init() {
        isFinish = false;
        mContext = ColoringAGameActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        isCanMove = true;
        // TODO test
        refreshNum = 1;

        isFillColoring = false;
//        isFillColoring = getIntent().getExtras().getBoolean("is_fill_coloring")
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        snowView = (SnowView) findViewById(R.id.snow_view);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivBg = (ImageView) findViewById(R.id.iv_bg);
        ivDotted = (ImageView) findViewById(R.id.iv_dotted);
        ivRightWord = (ImageView) findViewById(R.id.iv_right_word);
        ivStar = (ImageView) findViewById(R.id.iv_star);
        ivBottom1 = (ImageView) findViewById(R.id.iv_bottom_1);
        ivBottom2 = (ImageView) findViewById(R.id.iv_bottom_2);
        ivBottom3 = (ImageView) findViewById(R.id.iv_bottom_3);
        ivAnimation1 = (ImageView) findViewById(R.id.iv_animation_1);
        ivAnimation2 = (ImageView) findViewById(R.id.iv_animation_2);
        ivAnimation3 = (ImageView) findViewById(R.id.iv_animation_3);
        ivAnimation4 = (ImageView) findViewById(R.id.iv_animation_4);
        ivAnimation5 = (ImageView) findViewById(R.id.iv_animation_5);
        ivAnimation6 = (ImageView) findViewById(R.id.iv_animation_6);
        ivAnimation7 = (ImageView) findViewById(R.id.iv_animation_7);
        ivAnimation8 = (ImageView) findViewById(R.id.iv_animation_8);

        ivAnimationView = new ImageView[6];
        ivAnimationView[0] = ivAnimation1;
        ivAnimationView[1] = ivAnimation2;
        ivAnimationView[2] = ivAnimation3;
        ivAnimationView[3] = ivAnimation4;
        ivAnimationView[4] = ivAnimation5;
        ivAnimationView[5] = ivAnimation6;

        refreshView();

        if(isFillColoring) {
            rlLayout.setBackgroundResource(R.drawable.color_bg);
            ivDotted.setImageResource(R.drawable.color_a_dotted);
        } else {
            rlLayout.setBackgroundResource(R.drawable.fill_letter_bg);
            ivBg.setImageResource(R.drawable.fill_letter_a_bg);
        }
        ivBottom1.setOnTouchListener(this);
        ivBottom2.setOnTouchListener(this);
        ivBottom3.setOnTouchListener(this);

        ivBack.setOnClickListener(this);
        animation1 = new AnimationBiz().setAnimation(mContext, 1, 15, "eyu_");
        animation2 = new AnimationBiz().setAnimation(mContext, 1, 15, "eyu_");
        animation3 = new AnimationBiz().setAnimation(mContext, 16, 30, "eyu_");
        animation4 = new AnimationBiz().setAnimation(mContext, 16, 30, "eyu_");
        animation5 = new AnimationBiz().setAnimation(mContext, 31, 45, "eyu_");
        animation6 = new AnimationBiz().setAnimation(mContext, 31, 45, "eyu_");
    }

    private void setViewPosition(View iv, int i) {
        if(isFillColoring) {
            biz.setViewPosition(iv, i, FixedPosition.color_a_position, scaleQPW,
                    scaleQPH, 0, 0, 1.0f);
        } else {
            biz.setViewPosition(iv, i, FixedPosition.color_a_word_position, scaleQPW,
                    scaleQPH, 0, 0, 1.0f);

        }
    }

    private void refreshView() {
        clickNum = 0;
        isCanMove = true;
        setViewPosition(ivStar, refreshNum + 9);
        setViewPosition(ivBg, 0 + refreshNum * 3);
        setViewPosition(ivDotted, 1 + refreshNum * 3);
        setViewPosition(ivRightWord, 2 + refreshNum * 3);
        if(isFillColoring) {
            ivBg.setImageResource(R.drawable.color_a_white);
        } else {
            ivDotted.setImageResource(R.drawable.fill_letter_a_bg);
        }
        int resId = 0;
        if(isFillColoring) {
            resId = new BaseCommon().getImageId(mContext, "color_a_" + rightWord[refreshNum]);
        } else {
            resId = new BaseCommon().getImageId(mContext, "fill_letter_" + rightWord[refreshNum]);
        }
        ivRightWord.setImageResource(resId);
        if(isFillColoring) {
            ivBottom1.setImageResource(R.drawable.color_a_brown_bottom);
            ivBottom2.setImageResource(R.drawable.color_a_green_bottom);
            ivBottom3.setImageResource(R.drawable.color_a_blue_bottom);
        } else {
            ivBottom1.setImageResource(R.drawable.fill_letter_a);
            ivBottom2.setImageResource(R.drawable.fill_letter_a);
            ivBottom3.setImageResource(R.drawable.fill_letter_a);
        }
        setViewPosition(ivBottom1, 12);
        setViewPosition(ivBottom2, 13);
        setViewPosition(ivBottom3, 14);

        ivBottom1.setVisibility(View.VISIBLE);
        ivBottom2.setVisibility(View.VISIBLE);
        ivBottom3.setVisibility(View.VISIBLE);
        System.out.println("-------refreshView---refreshNum----------:" + refreshNum);
        if(refreshNum == 0) { // apple
            for(int i=0 ;i<5; i++) {
//                ivAnimationView[i].setLayoutParams(LayoutParameters.setViewTopParams(193, 221, -50, scaleQPW, scaleQPH));
                ivAnimationView[i].setImageResource(R.drawable.color_apple);
            }
            ivAnimation1.setLayoutParams(LayoutParameters.setViewTopParams(193, 221, -250, scaleQPW, scaleQPH));
            ivAnimation2.setLayoutParams(LayoutParameters.setViewTopParams(96, 110, -250, scaleQPW, scaleQPH));
            ivAnimation3.setLayoutParams(LayoutParameters.setViewTopParams(116, 132, -250, scaleQPW, scaleQPH));
            ivAnimation4.setLayoutParams(LayoutParameters.setViewTopParams(96, 110, -250, scaleQPW, scaleQPH));
            ivAnimation5.setLayoutParams(LayoutParameters.setViewTopParams(193, 221, -250, scaleQPW, scaleQPH));
        } else if(refreshNum == 1) { // ant
            for(int i=0 ;i<ivAnimationView.length; i++) {
                ivAnimationView[i].setLayoutParams(LayoutParameters.setViewTopParams(116, 130, 21, scaleQPW, scaleQPH));
                ivAnimationView[i].setImageResource(R.drawable.ant_move);
            }
        } else if(refreshNum == 2) { // alligator
            ivAnimation7.setVisibility(View.VISIBLE);
            ivAnimation8.setVisibility(View.VISIBLE);
            setViewPosition(ivAnimation7, 17);
            setViewPosition(ivAnimation8, 18);
            ivAnimation7.setBackgroundDrawable(getResources().getDrawable(R.drawable.eyu_1));
            ivAnimation8.setBackgroundDrawable(getResources().getDrawable(R.drawable.eyu_1));
        }
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        }
    }

    private int selectedId = 0;
    private float x, y;
    private int mx, my;
    private int mWidth, mHeight;
    private int indexSelect = -1; // 拖拽的button对应的是 上面两个图片的哪一个

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        System.out.println("-------onTouch---isCanMove----------------:" + isCanMove);
        if ((selectedId != 0 && selectedId != v.getId()) || !isCanMove)
            return true;
        ivToFront(v);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                mWidth = v.getWidth();
                mHeight = v.getHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                mx = (int) (event.getRawX() - x);
                my = (int) (event.getRawY() - DisplayUtil.dip2px(mContext, 25) - y);
                if (mx + mWidth > width)
                    mx = width - mWidth;
                if (my + mHeight > height)
                    my = height - mHeight;
                v.setLayoutParams(LayoutParameters.setViewPositionParams(mWidth,
                        mHeight, mx, my));
                break;

            case MotionEvent.ACTION_UP:
                // 拿到红色框框在屏幕的Y X 坐标（dp）
                final int[] location = new int[2];
                Rect viewRect = new Rect(); // 定义一个矩形区域
                ivBg.getLocationOnScreen(location);
                ivBg.getGlobalVisibleRect(viewRect); // 获取img3全局坐标系的一个视图区域，
                Rect otherViewRect = new Rect();
                v.getGlobalVisibleRect(otherViewRect);// 获取当前触摸的view全局坐标系的一个视图区域，
                // 返回一个填充的Rect对象；该Rect是基于总整个屏幕的
                // 判断这两个坐标是否重叠 判断这两个对象是否有重叠
                if ((indexSelect == 0 || indexSelect == 1 || indexSelect == 2 || indexSelect == 3)
                        && Rect.intersects(viewRect, otherViewRect)) {
                    isCanMove = false;
                    setBestPosition(v, mx, my);
                } else {
                    isCanMove = true;
                    int id = v.getId();
                    if (id == R.id.iv_bottom_1) {
                        setViewPosition(ivBottom1, 12);
                    } else if(id == R.id.iv_bottom_2) {
                        setViewPosition(ivBottom2, 13);
                    } else if(id == R.id.iv_bottom_3) {
                        setViewPosition(ivBottom3, 14);
                    }
                    // 保护的用途
                    selectedId = 0;
                }
                break;
            default:
                break;
        }
        return true;
    }

    // 获得最佳停留位置
    private void setBestPosition(View v, float x, float y) {
        final int[] location = new int[2];
        if (indexSelect == 0) {
            ivBg.getLocationOnScreen(location);
        }
        if (indexSelect == 0 || indexSelect == 1 || indexSelect == 2 || indexSelect == 3) {
            showView(400, 0);
            TranslateAnimation animation = new TranslateAnimation(0.0f,
                    location[0] + v.getWidth() / 2 - x - 50, 0.0f, location[1]
                    + v.getHeight() / 2 - y - 100);
            animation.setInterpolator(new LinearInterpolator()); // 设置动画的速率
            animation.setDuration(400);
            v.startAnimation(animation);
            animation.setFillAfter(true); // 执行终止填充效果
        }
    }

    private void showView(final long time, final  int caseIndex) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    handler.sendMessage(handler.obtainMessage(caseIndex));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    showSelectedWord();
                    break;
                case 1:
                    ivStar.clearAnimation();
                    ivStar.setVisibility(View.GONE);
                    CommonAnimation.startShouAnimation22(ivBg);
                    CommonAnimation.startShouAnimation22(ivDotted);
                    CommonAnimation.startShouAnimation22(ivRightWord);
//                    CommonAnimation.startShouAnimation22(rlBg);
                    // TODO 读音
                    mMediaPlayer = MediaPlayerBiz.playMusicFromResId(mContext, mMediaPlayer, soundId[refreshNum], false);
                    if(clickNum >= 3) {
                        if(refreshNum == 0 && clickNum == 3) {
                            playSentence(R.raw.apple_drop, false);
                            delayToDrop();
                        }
                        if(refreshNum == 1 && clickNum == 3) { // ant
                            playSentence(R.raw.ant_walk, false);
                            for(int i=0 ;i<ivAnimationView.length; i++) {
                                new AnimationBiz().startAnimationView(400, -2000, 0, 0, 4000, 400, clickNum, true, scaleQPH, ivAnimationView[i]);
                            }
                        } else if(refreshNum == 2 && clickNum == 3) { // alligator
                            playSentence(R.raw.alligator_float_up, false);
                            playAnimation(animation5, animation6);
                        }
                        refreshNum++;
                        if(refreshNum < 3) {
                            // TODO 延迟跳转
                            if(refreshNum == 1) {
                                delayToRefresh(2000, 2);
                            } else {
                                delayToRefresh(3500, 2);
                            }
                        } else {
                            delayToRefresh(1500, 3);
                        }
                    } else {
                        if(refreshNum == 0) {
                            playSentence(R.raw.apple_drop, false);
                            delayToDrop();
                            delayToRefresh(2000, 5);
                        }
                        if(refreshNum == 1 && clickNum == 1) {
                            ivAnimation1.setVisibility(View.VISIBLE);
                            new AnimationBiz().startAnimationView(1300, 700, 0, 0, 4000, 400, clickNum, false, scaleQPH, ivAnimation1);
                            delayProcessAnimation(800, ivAnimation2, 1300, 850, 3200, false);
                            delayProcessAnimation(1600, ivAnimation3, 1300, 1000, 2400, false);
                            delayProcessAnimation(2400, ivAnimation4, 1300, 1150, 1600, false);
                            playSentence(R.raw.ant_walk, false);
                            delayToRefresh(4000, 5);
                        } else if(refreshNum == 1 && clickNum == 2) {
                            for(int i=0 ;i<4; i++) {
                                new AnimationBiz().startAnimationView(1000, -500, 0, 0, 3000, (300 - i * 150), clickNum, true, scaleQPH, ivAnimationView[i]);
                            }
                            delayProcessAnimation(0, ivAnimation5, 1300, 800, 3000, false);
                            delayProcessAnimation(800, ivAnimation6, 1300, 950, 2200, false);
                            playSentence(R.raw.ant_walk, false);
                            delayToRefresh(3000, 5);
                        }

                        if(refreshNum == 2 && clickNum == 1) {
                            playSentence(R.raw.alligator_float_up, false);
                            playAnimation(animation1, animation2);
                        } else if(refreshNum == 2 && clickNum == 2) {
                            playSentence(R.raw.alligator_float_up, false);
                            playAnimation(animation3, animation4);
                        }
                    }
                    break;
                case 2:
                    refreshView();
                    break;
                case 3:
                    finish();
                    break;
                case 4:
                    List<Object> list = new ArrayList<Object>();
                    list = (List<Object>) msg.obj;
                    ImageView ivAnimation = (ImageView) list.get(0);
                    ivAnimation.setVisibility(View.VISIBLE);
                    int fromX = (int) list.get(1);
                    int toX = (int) list.get(2);
                    int duration = (int) list.get(3);
                    boolean isShowSelf = (boolean) list.get(4);
                    new AnimationBiz().startAnimationView(fromX, toX, 0, 0, duration,  400, clickNum, isShowSelf, scaleQPH, ivAnimation);
                    break;
                case 5:
                    isCanMove = true;
                    break;
                case 6:
                    List<Object> listApple = new ArrayList<Object>();
                    listApple = (List<Object>) msg.obj;
                    ImageView ivAnimationApple = (ImageView) listApple.get(0);
                    int index = (int) listApple.get(1);
                    ivAnimationApple.setVisibility(View.VISIBLE);
                    int toY = (int) listApple.get(2);
                    new AnimationBiz().startAnimationView((0 + index * 350), (0 + index * 350), -200, toY, 1000, 400, clickNum, false, scaleQPH, ivAnimationApple);

                    break;
                case 7: // TODO 抛物线
                    List<Object> listApple2 = new ArrayList<Object>();
                    listApple2 = (List<Object>) msg.obj;
                    ImageView ivAnimationApple2 = (ImageView) listApple2.get(0);
                    int toY2 = (int) listApple2.get(1);
                    new AnimationBiz().startAnimationView(0 * 350, -250, toY2, (toY2 - 300), 500, 400, clickNum, false, scaleQPH, ivAnimationApple2);
                    break;
                default:
                    break;
            }
        }

    };

    private void playAnimation(AnimationDrawable an1, AnimationDrawable an2) {
        ivAnimation7.setBackgroundDrawable(an1);
        ivAnimation8.setBackgroundDrawable(an2);
        startAnimation(an1);
        startAnimation(an2);
    }

    private void startAnimation(AnimationDrawable animationDrawable) {
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.clearColorFilter();
            animationDrawable.start();
        } else {
            animationDrawable.start();
        }
        delayToRefresh(2000, 5);
    }

    private void delayProcessAnimation(final long time, final ImageView ivAnimation,
              final int fromX, final int toX, final int duration, final boolean isShowSelf) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    List<Object> list = new ArrayList<Object>();
                    list.add(ivAnimation);
                    list.add(fromX);
                    list.add(toX);
                    list.add(duration);
                    list.add(isShowSelf);
                    handler.sendMessage(handler.obtainMessage(4, list));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void delayToRefresh(final long time, final int caseId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    handler.sendMessage(handler.obtainMessage(caseId));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 延迟苹果掉落的时间
    private void delayToDrop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(0);
                    handler.sendMessage(handler.obtainMessage(6, getList(ivAnimation5, 4, 800)));
                    Thread.sleep(100);
                    handler.sendMessage(handler.obtainMessage(6, getList(ivAnimation1, 0, 800)));
                    Thread.sleep(50);
                    handler.sendMessage(handler.obtainMessage(6, getList(ivAnimation4, 3, 800)));
                    Thread.sleep(200);
                    handler.sendMessage(handler.obtainMessage(6, getList(ivAnimation2, 1, 800)));
                    Thread.sleep(200);
                    handler.sendMessage(handler.obtainMessage(6, getList(ivAnimation3, 2, 800)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private List<Object> getList(ImageView ivAnimation, int index, int toY) {
        List<Object> list = new ArrayList<Object>();
        list.add(ivAnimation);
        if(index >= 0) {
            list.add(index);
        }
        list.add(toY);
        return list;
    }

    private void showSelectedWord() {
        if (selectedId == R.id.iv_bottom_1) {
            statusImg(ivBottom1, 0);
        } else if(selectedId == R.id.iv_bottom_2) {
            statusImg(ivBottom2, 1);
        } else if(selectedId == R.id.iv_bottom_3) {
            statusImg(ivBottom3, 2);
        }
        // 保护的用途
        selectedId = 0;
    }

    private void statusImg(ImageView ivImg, int index) {
        clickNum++;
        System.out.println("-------------index-----------:" + index);
        setViewPosition(ivImg, 0);
        ivImg.setVisibility(View.GONE);
        if(isFillColoring) {
            int resId = new BaseCommon().getImageId(mContext, "color_a_" + colorBg[index]);
            ivBg.setImageResource(resId);
        } else {
            ivDotted.setImageResource(R.drawable.fill_letter_sel_a);
        }
        ivImg.clearAnimation();
        playSentence(R.raw.star_blast, false);
        ivStar.clearAnimation();
        startAnimationView(ivStar);
    }

    public void startAnimationView(ImageView iv) {
        iv.setVisibility(View.VISIBLE);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.setOneShot(true);
        animationDrawable.start();
        int duration = 0;
        for(int i=0;i<animationDrawable.getNumberOfFrames();i++){
            duration += animationDrawable.getDuration(i);
        }
        handler.postDelayed(new Runnable() {
            public void run() {
                //此处调用第二个动画播放方法
                handler.sendMessage(handler.obtainMessage(1));
            }
        }, duration);

    }

    // 把点击的图片置顶到顶层
    private void ivToFront(View v) {
        selectedId = v.getId();
        indexSelect = -1;
        if (v == ivBottom1) {
            ivBottom1.bringToFront();
            indexSelect = 0;
        } else if(v == ivBottom2) {
            ivBottom2.bringToFront();
            indexSelect = 0;
        } else if(v == ivBottom3) {
            ivBottom3.bringToFront();
            indexSelect = 0;
        }
    }

    private void playSentence(int rawId, boolean isMonitor) {
        try {
            if(isFinish)
                return;
            mMediaPlayer = MediaPlayerBiz.playMusicFromResId(mContext, mMediaPlayer, rawId, false);
            if(isMonitor) {
                mMediaPlayer.setOnCompletionListener(
                        new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
//                                playSentence(path + "want_" + cardName[clickNum] + ".mp3", false);
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isFinish = true;
    }
}
