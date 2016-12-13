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
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.LayoutParameters;
import com.gst.move.R;
import com.gst.move.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

import static com.ebodoo.raz.utils.MyEbookRecorderService.mMediaPlayer;
import static com.gst.move.test.MainActivity.isFinish;

/**
 *  B填色游戏
 */
public class ColoringBGameActivity extends BaseActivity implements View.OnTouchListener, View.OnClickListener {

    private RelativeLayout rlBg;
    private ImageView ivBack, ivBg, ivDotted, ivRightWord, ivStar, ivBottom1, ivBottom2, ivBottom3;
    private ImageView ivAnimation1, ivAnimation2, ivAnimation3, ivAnimation4, ivAnimation5, ivAnimation6, ivAnimation7, ivAnimation8;
    private ImageView[] ivAnimationView;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;
    private int refreshNum = 0, clickNum = 0;
    private String[] colorBg = new String[] {
            "brown", "green", "blue"
    };
    private String[] rightWord = new String[] {
            "us", "ag", "anana"
    };
    private int[] soundId = new int[] {
            R.raw.bus, R.raw.bag, R.raw.banana
    };
    private boolean isCanMove = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coloring_b_game);

        init();
        setView();
    }

    private void init() {
        mContext = ColoringBGameActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        isCanMove = true;
        // TODO test
        refreshNum = 0;
    }

    private void setView() {
        rlBg = (RelativeLayout) findViewById(R.id.rl_bg);
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

        ivDotted.setImageResource(R.drawable.color_b_dotted);
        ivBottom1.setOnTouchListener(this);
        ivBottom2.setOnTouchListener(this);
        ivBottom3.setOnTouchListener(this);

        ivBack.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.color_b_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private void refreshView() {
        clickNum = 0;
        isCanMove = true;
        if(refreshNum == 2) {
            ivAnimation1.clearAnimation();
            ivAnimation2.clearAnimation();
            ivAnimation3.clearAnimation();
            ivAnimation1.setVisibility(View.GONE);
            ivAnimation2.setVisibility(View.GONE);
            ivAnimation3.setVisibility(View.GONE);
        }
        setViewPosition(ivStar, refreshNum + 17);
        setViewPosition(ivBg, 0 + refreshNum * 3);
        setViewPosition(ivDotted, 1 + refreshNum * 3);
        setViewPosition(ivRightWord, 2 + refreshNum * 3);
        ivBg.setImageResource(R.drawable.color_b_white);
        int resId = new BaseCommon().getImageId(mContext, "color_b_" + rightWord[refreshNum]);
        ivRightWord.setImageResource(resId);

        ivBottom1.setImageResource(R.drawable.color_b_brown_bottom);
        ivBottom2.setImageResource(R.drawable.color_b_green_bottom);
        ivBottom3.setImageResource(R.drawable.color_b_blue_bottom);
        setViewPosition(ivBottom1, 14);
        setViewPosition(ivBottom2, 15);
        setViewPosition(ivBottom3, 16);

        ivBottom1.setVisibility(View.VISIBLE);
        ivBottom2.setVisibility(View.VISIBLE);
        ivBottom3.setVisibility(View.VISIBLE);
        if(refreshNum == 0) { // bus
            ivAnimation1.setImageResource(R.drawable.color_pic_bus_1);
            ivAnimation2.setImageResource(R.drawable.color_pic_bus_2);
            ivAnimation1.setLayoutParams(LayoutParameters.setViewTopParams(272, 170, 552, scaleQPW, scaleQPH));
            ivAnimation2.setLayoutParams(LayoutParameters.setViewTopParams(272, 170, 12, scaleQPW, scaleQPH));
        } else if(refreshNum == 1) { // bug
            for(int i=0 ;i<3; i++) {
                setViewPosition(ivAnimationView[i], (i + 11));
                ivAnimationView[i].setImageResource(R.drawable.color_pic_bag);
            }
        } else if(refreshNum == 2) { // banana
            for(int i=0 ;i<5; i++) {
                ivAnimationView[i].setLayoutParams(LayoutParameters.setViewTopParams(184, 157, -150, scaleQPW, scaleQPH));
                ivAnimationView[i].setImageResource(R.drawable.color_pic_banana);
            }
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
                        setViewPosition(ivBottom1, 14);
                    } else if(id == R.id.iv_bottom_2) {
                        setViewPosition(ivBottom2, 15);
                    } else if(id == R.id.iv_bottom_3) {
                        setViewPosition(ivBottom3, 16);
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
                    CommonAnimation.startShouAnimation22(rlBg);
                    mMediaPlayer = MediaPlayerBiz.playMusicFromResId(mContext, mMediaPlayer, soundId[refreshNum], false);
                    if(clickNum >= 3) {
                        refreshNum++;
                        if(refreshNum == 1) {
                            new AnimationBiz().startAnimationView2(-200, 1300, 0, 0, 3000, 552, 400, clickNum, true, scaleQPH, ivAnimation1);
                            new AnimationBiz().startAnimationView2(2000, -1400, 0, 0, 3000, 12, 400, clickNum, true, scaleQPH, ivAnimation2);
                            playSentence(R.raw.bus_walk, false);
                        }
                        if(refreshNum == 2) {
                            ivAnimation3.setVisibility(View.VISIBLE);
                        }
                        if(refreshNum == 3) {
                            delayToDrop();
                        }
                        if(refreshNum < 3) {
                            // TODO 延迟跳转
                            if(refreshNum == 1) {
                                delayToRefresh(2500, 2);
                            } else if(refreshNum == 2){
                                delayToRefresh(1000, 2);
                            } else {
                                delayToRefresh(3500, 2);

                            }
                        } else {
                            delayToRefresh(3000, 3);
                        }
                    } else {
                        System.out.println("-------------refreshNum---------" + refreshNum + "---------clickNum------" + clickNum);
                        if(refreshNum == 0 && clickNum == 1) {
                            ivAnimation1.setVisibility(View.VISIBLE);
                            ivAnimation2.setVisibility(View.VISIBLE);
                            new AnimationBiz().startAnimationView2(-300, 200, 0, 0, 3000, 552, 400, clickNum, false, scaleQPH, ivAnimation1);
                            new AnimationBiz().startAnimationView2(1300, 800, 0, 0, 3000, 12, 400, clickNum, false, scaleQPH, ivAnimation2);
                            playSentence(R.raw.bus_walk, false);
                        } else if(refreshNum == 0 && clickNum == 2) {
                            new AnimationBiz().startAnimationView3(0, 550, 0, 0, 3000, 552, 200, clickNum, true, scaleQPH, ivAnimation1, false);
                            new AnimationBiz().startAnimationView2(0, -450, 0, 0, 3000, 12, 100, clickNum, true, scaleQPH, ivAnimation2);
                            playSentence(R.raw.bus_walk, false);
                        }

                        if(refreshNum == 1 && clickNum == 1) {
                            ivAnimation1.setVisibility(View.VISIBLE);
                        } else if(refreshNum == 1 && clickNum == 2) {
                            ivAnimation2.setVisibility(View.VISIBLE);
                        }
                        if(refreshNum == 2) {
                            delayToDrop();
                        }
                        delayToRefresh(2000, 5);
                    }
                    break;
                case 2:
                    refreshView();
                    break;
                case 3:
                    finish();
                    break;
                case 4:
                    List<Object> listBanana = new ArrayList<Object>();
                    listBanana = (List<Object>) msg.obj;
                    ImageView ivBanana = (ImageView) listBanana.get(0);
                    int index = (Integer) listBanana.get(1);
                    ivBanana.setVisibility(View.VISIBLE);
                    int toY = (Integer) listBanana.get(2);
                    if(index == 0) {
                        playSentence(R.raw.apple_drop, false);
                    }
                    new AnimationBiz().startAnimationView((0 + index * 350), (0 + index * 350), -200, toY, 2000, 400, clickNum, false, scaleQPH, ivBanana);
                    break;
                case 5:
                    isCanMove = true;
                    break;
                default:
                    break;
            }
        }

    };

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
        int resId = new BaseCommon().getImageId(mContext, "color_b_" + colorBg[index]);
        ivBg.setImageResource(resId);
        ivImg.clearAnimation();
        playSentence(R.raw.star_blast, false);
        ivStar.clearAnimation();
        startAnimationView(ivStar);
    }

    public void startAnimationView(ImageView iv) {
        iv.setVisibility(View.VISIBLE);
        // 星星动画
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

    // 延迟香蕉掉落的时间
    private void delayToDrop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(0);
                    handler.sendMessage(handler.obtainMessage(4, getList(ivAnimation5, 4, 800)));
                    Thread.sleep(100);
                    handler.sendMessage(handler.obtainMessage(4, getList(ivAnimation1, 0, 800)));
                    Thread.sleep(50);
                    handler.sendMessage(handler.obtainMessage(4, getList(ivAnimation4, 3, 800)));
                    Thread.sleep(200);
                    handler.sendMessage(handler.obtainMessage(4, getList(ivAnimation2, 1, 800)));
                    Thread.sleep(200);
                    handler.sendMessage(handler.obtainMessage(4, getList(ivAnimation3, 2, 800)));
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

}
