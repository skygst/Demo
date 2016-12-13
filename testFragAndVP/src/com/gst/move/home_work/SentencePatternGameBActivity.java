package com.gst.move.home_work;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.AnimationBiz;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.CommonAnimation;
import com.gst.move.R;

import static android.R.id.list;
import static android.R.transition.move;

/**
 *  句型 B 游戏
 */
public class SentencePatternGameBActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack, ivCard1, ivCard2, ivCard3, ivPic1, ivPic2, ivShou;
    private RelativeLayout rlBg;
    private ImageView[] ivCard;
    private TextView tvSentence;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;
    private int refreshNum = 0, clickCount = 0;
    private String[] sentence = new String[] {
            "Bus bus, drive away!",
            "Bag bag, on my back!",
            "Banana banana, in a bag!"
    };
    private boolean isCanClick = true;
    private AnimationDrawable animationBag1, animationBag2, animationBag3;
    private MediaPlayer mMediaPlayer;
    private boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sentence_b);

        init();
        setView();
    }

    private void init() {
        isFinish = false;
        mContext = SentencePatternGameBActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        isCanClick = true;
        animationBag1 = new AnimationBiz().setAnimation(mContext, 1, 15, "bag_");
        animationBag2 = new AnimationBiz().setAnimation(mContext, 1, 15, "bag_");
        animationBag3 = new AnimationBiz().setAnimation(mContext, 1, 15, "bag_");
        refreshNum = 0;
    }

    private void setView() {
        tvSentence = (TextView) findViewById(R.id.tv_sentence);
        rlBg = (RelativeLayout) findViewById(R.id.rl_bg);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
        ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
        ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
        ivPic1 = (ImageView) findViewById(R.id.iv_pic_1);
        ivPic2 = (ImageView) findViewById(R.id.iv_pic_2);
        ivShou = (ImageView) findViewById(R.id.iv_shou);

        ivCard = new ImageView[3];
        ivCard[0] = ivCard1;
        ivCard[1] = ivCard2;
        ivCard[2] = ivCard3;

        setViewPosition(ivCard1, 0);
        setViewPosition(ivCard2, 1);
        setViewPosition(ivCard3, 2);
        setViewPosition(ivPic1, 12);
        setViewPosition(ivPic2, 17);

        refreshView();

        ivBack.setOnClickListener(this);
        ivCard1.setOnClickListener(this);
        ivCard2.setOnClickListener(this);
        ivCard3.setOnClickListener(this);
    }

    private void refreshView() {
        clickCount = 0;
        tvSentence.setVisibility(View.GONE);
        ivShou.setVisibility(View.VISIBLE);
        CommonAnimation.startShouAnimation(ivShou);
        playSentence(R.raw.click_here, false);
        if(refreshNum == 0) { // bus
            setViewPosition(ivShou, 0);
        } else if(refreshNum == 1) { // bag
            setViewPosition(ivShou, 3);
            for(int i=0; i<ivCard.length; i++) {
                ivCard[i].setVisibility(View.VISIBLE);
                ivCard[i].setImageResource(R.drawable.sentence_b_bag);
                setViewPosition(ivCard[i], (i + 3));
            }
            rlBg.setBackgroundResource(R.drawable.sentence_b_2_bg);
        } else if(refreshNum == 2) { // banana
            rlBg.setBackgroundResource(R.drawable.sentence_b_3_bg);
            ivPic1.setVisibility(View.VISIBLE);
            setViewPosition(ivShou, 9);
            for(int i=0; i<ivCard.length; i++) {
                ivCard[i].setVisibility(View.VISIBLE);
                ivCard[i].setBackgroundResource(0);
                ivCard[i].setImageResource(R.drawable.sentence_b_banana);
                setViewPosition(ivCard[i], (i + 9));
            }
        }
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.sentence_pattern_b_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        } else if (v == ivCard1) {
            processClick(ivCard1, 0);
        } else if(v == ivCard2) {
            processClick(ivCard2, 1);
        } else if(v == ivCard3) {
            processClick(ivCard3, 2);
        }
    }

    private void processClick(ImageView iv, int index) {
        if(!isCanClick) {
            return;
        }
        if(ivShou.getVisibility() == View.VISIBLE) {
            ivShou.clearAnimation();
            ivShou.setVisibility(View.GONE);
        }
        isCanClick = false;
        // TODO 读句子，显示句子
        tvSentence.setVisibility(View.VISIBLE);
        tvSentence.setText(sentence[refreshNum]);
        if(refreshNum == 0) {
            if(index == 0) {
                new AnimationBiz().startAnimationView2(-200, -1500, 0, 0, 1500, 552, 400, 0, true, scaleQPH, iv);
            } else {
                new AnimationBiz().startAnimationView2(-200, 1500, 0, 0, 1500, 552, 400, 0, true, scaleQPH, iv);
            }
            delayMove(1500, 0, iv);
        } else if(refreshNum == 1) {
            setViewPosition(ivCard[index], (index + 6));
            ivCard[index].setImageResource(0);
            if(clickCount == 0) {
                startAnimation(ivCard[index], animationBag1);
            } else if(clickCount == 1) {
                startAnimation(ivCard[index], animationBag2);
            } else {
                startAnimation(ivCard[index], animationBag3);
            }
        } else if(refreshNum == 2) {
            int[] start_location = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
            ivCard[index].getLocationInWindow(start_location);// 这是获取当前点击的按钮在屏幕的X、Y坐标（这也是动画开始的坐标）
            ImageView ivIcon = new ImageView(this);
            setViewPosition(ivIcon, (index + 14));
            ivCard[index].setVisibility(View.GONE);
            ivIcon.setImageResource(R.drawable.small_banana);

            move(ivIcon, start_location);
            delayMove(1800, 1, null);
        }
    }

    private void startAnimation(ImageView iv, AnimationDrawable animationDrawable) {
        iv.setBackgroundDrawable(animationDrawable);
        startAnimation(animationDrawable, iv);
    }

    private void startAnimation(AnimationDrawable animationDrawable, final ImageView iv) {
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.clearColorFilter();
            animationDrawable.start();
        } else {
            animationDrawable.start();
        }
        int duration = 0;
        for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
            duration += animationDrawable.getDuration(i);
        }
        duration += 1000;
        System.out.println("----------duration--------:" + duration);
        handler.postDelayed(new Runnable() {
            public void run() {
                //此处调用第二个动画播放方法
                handler.sendMessage(handler.obtainMessage(0, iv));
            }
        }, duration);
    }

    private void delayMove(final long time, final int caseId, final ImageView ivCard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    if(ivCard != null) {
                        handler.sendMessage(handler.obtainMessage(caseId, ivCard));
                    } else {
                        handler.sendMessage(handler.obtainMessage(caseId));
                    }
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
                    ImageView ivCard = (ImageView) msg.obj;
                    ivCard.setVisibility(View.GONE);
                    delayMove(100, 1, null);
                    break;
                case 1:
                    tvSentence.setVisibility(View.GONE);
                    clickCount++;
                    isCanClick = true;
                    if(clickCount >= 3) {
                        refreshNum++;
                        if(refreshNum >= 3) {
                            // TODO 跳转到成功界面

                            finish();
                        } else {
                            refreshView();
                        }
                    } else {
                        if(refreshNum == 1) { // bag
                            // 初始化
//                            initAnt();
                        }
                    }
                    break;

            }
        }
    };

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

    private ViewGroup viewGroup;// 动画层

    private void move(final View v, int[] start_location) {
        viewGroup = null;
        viewGroup = createAnimLayout();
        viewGroup.addView(v);// 把要移动的控件添加到动画层
        final View view = addViewToAnimLayout(viewGroup, v, start_location);
        int[] end_location = new int[2];// 这是用来存储动画结束位置的X、Y坐标
        ivPic2.getLocationInWindow(end_location);
            // 计算位移
            int endX = 0 - start_location[0]
                    + ivPic2.getLeft();// 动画位移的X坐标
            int endY = end_location[1] - start_location[1];// 动画位移的y坐标
            TranslateAnimation translateAnimationX = new TranslateAnimation(0,
                    endX, 0, 0);
            translateAnimationX.setInterpolator(new LinearInterpolator());
            translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
            translateAnimationX.setFillAfter(true);

            TranslateAnimation translateAnimationY = new TranslateAnimation(0,
                    0, 0, endY);
            translateAnimationY.setInterpolator(new AccelerateInterpolator());
            translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
            translateAnimationX.setFillAfter(true);

            AnimationSet set = new AnimationSet(false);
            set.setFillAfter(false);
            set.addAnimation(translateAnimationY);
            set.addAnimation(translateAnimationX);
            set.setDuration(800);// 动画的执行时间
            view.startAnimation(set);
            // 动画监听事件
            set.setAnimationListener(new Animation.AnimationListener() {
                // 动画的开始
                @Override
                public void onAnimationStart(Animation animation) {
                    v.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }

                // 动画的结束
                @Override
                public void onAnimationEnd(Animation animation) {
                    v.setVisibility(View.GONE);
                }
            });
    }

    /**
     * @Description: 创建动画层
     * @param
     * @return void
     * @throws
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

}

