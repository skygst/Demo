package com.gst.move.home_work;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.AnimationBiz;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.CommonAnimation;
import com.gst.move.R;

/**
 *  句型 A 游戏
 */
public class SentencePatternGameAActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack, ivTree, ivCard1, ivCard2, ivCard3, ivNum1, ivNum2, ivNum3, ivShou;
    private RelativeLayout rlBg;
    private ImageView[] ivCard;
    private TextView tvSentence;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;
    private int refreshNum = 0, clickCount = 0;
    private String[] sentence = new String[] {
            "Apple apple, on a tree!",
            "Ant ant, one two three!",
            "Alligator alligator, go away!"
    };
    private boolean isCanClick = true;
    private AnimationDrawable animationAnt1, animationAnt2, animationAnt3, animationAlligator1, animationAlligator2, animationAlligator3;
    private MediaPlayer mMediaPlayer;
    private boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sentence_a);

        init();
        setView();
    }

    private void init() {
        isFinish = false;
        mContext = SentencePatternGameAActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        isCanClick = true;
        animationAnt1 = new AnimationBiz().setAnimation(mContext, 1, 12, "sentence_ant_");
        animationAnt2 = new AnimationBiz().setAnimation(mContext, 1, 12, "sentence_ant_");
        animationAnt3 = new AnimationBiz().setAnimation(mContext, 1, 12, "sentence_ant_");
        animationAlligator1 = new AnimationBiz().setAnimation(mContext, 1, 15, "sentence_alligator_");
        animationAlligator2 = new AnimationBiz().setAnimation(mContext, 1, 15, "sentence_alligator_");
        animationAlligator3 = new AnimationBiz().setAnimation(mContext, 1, 15, "sentence_alligator_");
    }

    private void setView() {
        tvSentence = (TextView) findViewById(R.id.tv_sentence);
        rlBg = (RelativeLayout) findViewById(R.id.rl_bg);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivTree = (ImageView) findViewById(R.id.iv_tree);
        ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
        ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
        ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
        ivNum1 = (ImageView) findViewById(R.id.iv_num_1);
        ivNum2 = (ImageView) findViewById(R.id.iv_num_2);
        ivNum3 = (ImageView) findViewById(R.id.iv_num_3);
        ivShou = (ImageView) findViewById(R.id.iv_shou);

        ivCard = new ImageView[3];
        ivCard[0] = ivCard1;
        ivCard[1] = ivCard2;
        ivCard[2] = ivCard3;

        setViewPosition(ivTree, 0);
        setViewPosition(ivCard1, 1);
        setViewPosition(ivCard2, 2);
        setViewPosition(ivCard3, 3);
        setViewPosition(ivNum1, 7);
        setViewPosition(ivNum2, 8);
        setViewPosition(ivNum3, 9);

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
        startAnimationNum = 0;
        if(refreshNum == 0) { // apple
            setViewPosition(ivShou, 1);
        } else if(refreshNum == 1) { // ant
            setViewPosition(ivShou, 4);
            ivTree.setVisibility(View.GONE);
            rlBg.setBackgroundResource(R.drawable.sentence_a_2_bg);
            initAnt();
        } else if(refreshNum == 2) { // alligator
            ivNum1.setVisibility(View.GONE);
            ivNum2.setVisibility(View.GONE);
            ivNum3.setVisibility(View.GONE);
            setViewPosition(ivShou, 10);
            ivTree.setVisibility(View.GONE);
            rlBg.setBackgroundResource(R.drawable.sentence_a_3_bg);
            for (int i=0; i<ivCard.length; i++) {
                ivCard[i].clearAnimation();
                ivCard[i].setVisibility(View.VISIBLE);
                setViewPosition(ivCard[i], (i + 10));
                ivCard[i].setImageResource(0);
                ivCard[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.sentence_alligator_1));
            }
        }
    }

    private void initAnt() {
        startAnimationNum = 0;
        ivNum1.setVisibility(View.GONE);
        ivNum2.setVisibility(View.GONE);
        ivNum3.setVisibility(View.GONE);
        for (int i=0; i<ivCard.length; i++) {
            ivCard[i].clearAnimation();
            ivCard[i].setVisibility(View.VISIBLE);
            ivCard[i].setEnabled(true);
            setViewPosition(ivCard[i], (i + 4));
            ivCard[i].setImageResource(0);
            ivCard[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.sentence_ant_1));
        }
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.sentence_pattern_a_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        } else if (v == ivCard1) {
            processClickApple(ivCard1);
        } else if(v == ivCard2) {
            processClickApple(ivCard2);
        } else if(v == ivCard3) {
            processClickApple(ivCard3);
        }
    }

    private void processClickApple(ImageView iv) {
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
            iv.setEnabled(false);
//            iv.setFocusable(false);
            CommonAnimation.enlargeAndGradientAnimation(iv);
            delayMove(1500, 0, iv);
        } else if(refreshNum == 1) {

            ivCard1.setBackgroundDrawable(animationAnt1);
            startAnimation(animationAnt1);

        } else if(refreshNum == 2) {
            if(startAnimationNum == 0) {
                startAnimation(iv, animationAlligator1);
            } else if(startAnimationNum == 1) {
                startAnimation(iv, animationAlligator2);
            } else {
                startAnimation(iv, animationAlligator3);
            }
            playSentence(R.raw.alligator_sink_down, false);
        }
    }

    private void startAnimation(ImageView iv, AnimationDrawable animationDrawable) {
        iv.setBackgroundDrawable(animationDrawable);
        startAnimation(animationDrawable);
    }

    private int startAnimationNum = 0;

    private void startAnimation(AnimationDrawable animationDrawable) {
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            animationDrawable.clearColorFilter();
            animationDrawable.start();
        } else {
            animationDrawable.start();
        }
        startAnimationNum++;
        int duration = 0;
        for (int i = 0; i < animationDrawable.getNumberOfFrames(); i++) {
            duration += animationDrawable.getDuration(i);
        }
        System.out.println("----------duration--------:" + duration);
        handler.postDelayed(new Runnable() {
            public void run() {
                //此处调用第二个动画播放方法
                if(refreshNum == 1) {
                    if(startAnimationNum == 1) {
                        handler.sendMessage(handler.obtainMessage(1, ivCard2));
                    } else if(startAnimationNum == 2){
                        handler.sendMessage(handler.obtainMessage(1, ivCard3));
                    } else if(startAnimationNum == 3) {
                        handler.sendMessage(handler.obtainMessage(1, ivCard3));
                    }
                } else if(refreshNum == 2) {
                    delayMove(1000, 2, null);
                }
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
                    tvSentence.setVisibility(View.GONE);
                    delayMove(0, 2, null);
                    break;
                case 1: //
                    System.out.println("---------- case 1-------startAnimationNum---------" + startAnimationNum);
                    ImageView ivAnt = (ImageView) msg.obj;
                    if(startAnimationNum == 1) {
                        ivCard1.clearAnimation();
                        ivNum1.setVisibility(View.VISIBLE);
                        ivAnt.setBackgroundDrawable(animationAnt2);
                        startAnimation(animationAnt2);
                    } else if(startAnimationNum == 2){
                        ivCard2.clearAnimation();
                        ivNum2.setVisibility(View.VISIBLE);
                        ivAnt.setBackgroundDrawable(animationAnt3);
                        startAnimation(animationAnt3);

                        animationAnt1 = new AnimationBiz().setAnimation(mContext, 1, 12, "sentence_ant_");
                        animationAnt2 = new AnimationBiz().setAnimation(mContext, 1, 12, "sentence_ant_");
                        animationAnt3 = new AnimationBiz().setAnimation(mContext, 1, 12, "sentence_ant_");
                    } else {
                        ivCard3.clearAnimation();
                        ivNum3.setVisibility(View.VISIBLE);
                        delayMove(1000, 2, null);
                    }
                    break;
                case 2:
                    tvSentence.setVisibility(View.GONE);
                    clickCount++;
                    isCanClick = true;
                    System.out.println("---------- case 2------clickCount---------" + clickCount + "-----refreshNum-" + refreshNum);
                    if(clickCount >= 3) {
                        refreshNum++;
                        if(refreshNum >= 3) {
                            // TODO 跳转到成功界面

                            finish();
                        } else {
                            refreshView();
                        }
                    } else {
                        if(refreshNum == 1) { // ant
                            // 初始化
                            initAnt();
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

}
