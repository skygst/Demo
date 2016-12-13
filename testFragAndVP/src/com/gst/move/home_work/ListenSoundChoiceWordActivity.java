package com.gst.move.home_work;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.AnimationBiz;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.LayoutParameters;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

import java.util.ArrayList;
import java.util.List;

/**
 *  听音选单词
 */
public class ListenSoundChoiceWordActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivCard1, ivCard2, ivCard3, ivStar, ivRake, ivLailai;
    private HorizontalScrollView hlScrollView;
    private ImageView[] ivCard;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;
    private String[] cardName = new String[] {
            "apple", "ant", "alligator"
    };
    private int[] soundId = new int[] {
            R.raw.apple, R.raw.ant, R.raw.alligator
    };
    private int refreshNum = 0;
    private int[] randArray;
    private MediaPlayer mMediaPlayer;
    private int readCount = 0;
    private boolean isFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listen_sound_choice_word);

        init();
        setView();
    }

    private void init() {
        isFinish = false;
        mContext = ListenSoundChoiceWordActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        System.out.println("----------width--------:" + width);
        System.out.println("----------height--------:" + height);
        biz = new VideoBiz();
    }

    private void setView() {
        hlScrollView = (HorizontalScrollView) findViewById(R.id.horizon_scrollview);
        ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
        ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
        ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
        ivStar = (ImageView) findViewById(R.id.iv_star);
        ivRake = (ImageView) findViewById(R.id.iv_rake);
        ivLailai = (ImageView) findViewById(R.id.iv_lailai);

        ivCard = new ImageView[3];
        ivCard[0] = ivCard1;
        ivCard[1] = ivCard2;
        ivCard[2] = ivCard3;

        setViewPosition(ivLailai, 6);
        ivRake.setImageResource(R.drawable.listen_rake);
        refreshView();
        hlScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        for(int i=0; i<ivCard.length; i++) {
            ivCard[i].setOnClickListener(this);
        }
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.listen_sound_choice_word_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private void refreshView() {
        readCount = 0;
        ivStar.clearAnimation();
        ivStar.setVisibility(View.GONE);
        hlScrollView.bringToFront();
        randArray = BaseCommon.getList(3);
        for(int i=0; i<ivCard.length; i++) {
            ivCard[i].clearAnimation();
            ivCard[i].setVisibility(View.VISIBLE);
            int resId = new BaseCommon().getImageId(mContext, "listen_card_" + cardName[randArray[i]]);
            ivCard[i].setImageResource(resId);
        }
        ivRake.setVisibility(View.VISIBLE);
        ivCard1.setLayoutParams(LayoutParameters.topAndLeftValueParams(228, 291, 221, 223, scaleQPW, scaleQPH));
        ivCard2.setLayoutParams(LayoutParameters.topAndLeftValueParams(228, 291, 221, 243, scaleQPW, scaleQPH));
        ivCard3.setLayoutParams(LayoutParameters.topAndLeftValueParams(228, 291, 221, 263, scaleQPW, scaleQPH));
        ivRake.setLayoutParams(LayoutParameters.topAndLeftValueParams(555, 267, 231, 205, scaleQPW, scaleQPH));
        delayMove(1000, 0, -1, null);
    }

    @Override
    public void onClick(View v) {
        if(v == ivCard1) {
            processClick(v, 0);
        } else if(v == ivCard2) {
            processClick(v, 1);
        } else if(v == ivCard3) {
            processClick(v, 2);
        }
    }

    private void processClick(View view, int index) {
        if(randArray[index] == (refreshNum % 3)) {
            MediaCommon.playEgGood(mContext);
            ivStar.setVisibility(View.VISIBLE);
            setViewPosition(ivStar, (index+2));
            new AnimationBiz().starAnimation(ivStar, true);
            delayMove(1000, 2, index, null);
        } else {
            MediaCommon.playEgError(mContext);
            CommonAnimation.shakeAnimation(mContext, view);
        }
    }

    private void delayMove(final long time, final int caseId, final int index, final ImageView ivCard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    if(index >= 0) {
                        handler.sendMessage(handler.obtainMessage(caseId, index));
                    } else if(ivCard != null){
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
                    new AnimationBiz().startAnimationView3(243, 270, 0, 0, 600, 221, 250, 0, true, scaleQPH, ivCard2, false);
                    new AnimationBiz().startAnimationView3(263, 550, 0, 0, 1000, 221, 270, 0, true, scaleQPH, ivCard3, false);
                    new AnimationBiz().startAnimationView3(205, 906, 0, 0, 1200, 231, 210, 0, true, scaleQPH, ivRake, false);
//                    new AnimationBiz().startAnimationView3(243, 420, 0, 0, 600, 221, 370, 0, true, scaleQPH, ivCard2, false);
//                    new AnimationBiz().startAnimationView3(263, 850, 0, 0, 1000, 221, 400, 0, true, scaleQPH, ivCard3, false);
//                    new AnimationBiz().startAnimationView3(205, 1300, 0, 0, 1200, 231, 310, 0, true, scaleQPH, ivRake, false);
                    playSentence(R.raw.drag_card, false);
                    delayMove(1500, 1, -1, null);
                    break;
                case 1:
                    for(int i=0; i<ivCard.length; i++) {
                        ivCard[i].bringToFront();
                    }
                    ivStar.bringToFront();
                    break;
                case 2:
                    int index = (Integer) msg.obj;
                    hlScrollView.bringToFront();
                    // TODO 测试
                        index = 2;
                    if(index == 0) { // 从第二个开始拖动
                        ivRake.setLayoutParams(LayoutParameters.topAndLeftValueParams(555, 267, 231, 420, scaleQPW, scaleQPH));
                        delayMoveToEnd(0, 3, 1420, 1800, 221, 370, ivCard2);
                        delayMoveToEnd(600, 3, 1420, 450, 221, 400, ivCard3);
                        delayMoveToEnd(0, 3, 1850, 1800, 231, 310, ivRake);

                        playSentence(R.raw.drag_card, false);
                        delayMove(1800, 4, -1, ivCard1);
                    } else { // 从第一个开始拖动
                        ivRake.setLayoutParams(LayoutParameters.topAndLeftValueParams(555, 267, 231, 205, scaleQPW, scaleQPH));
                        delayMoveToEnd(0, 3, 1420, 1650, 221, 370, ivCard1);
                        ImageView ivCard = null;
                        if(index == 1) {
                            ivCard = ivCard2;
                            delayMoveToEnd(600, 3, 1420, 1350, 221, 400, ivCard3);
                        } else {
                            ivCard = ivCard3;
                            delayMoveToEnd(400, 3, 1420, 1300, 221, 370, ivCard2);
                        }
                        delayMoveToEnd(0, 3, 1850, 2000, 231, 310, ivRake);
                        playSentence(R.raw.drag_card, false);
                        delayMove(1800, 4, -1, ivCard);
                    }
                    break;
                case 3:
                    List<Object> list = new ArrayList<Object>();
                    list = (List<Object>) msg.obj;
                    ImageView ivAnimation = (ImageView) list.get(0);
                    ivAnimation.setVisibility(View.VISIBLE);
                    int toX = (int) list.get(1);
                    int duration = (int) list.get(2);
                    int topValue = (int) list.get(3);
                    int extraValue = (int) list.get(4);
                    new AnimationBiz().startAnimationView3(0, toX, 0, 0, duration, topValue, extraValue, 0, true, scaleQPH, ivAnimation, true);
                    break;
                case 4:
                    ImageView ivCard = (ImageView) msg.obj;
                    setViewPosition(ivCard, 5);
                    // TODO 跟我读画外音
//                    playSentence();

                    // TODO 读三遍，每遍隔4秒
                    delayFangda(ivCard);
                    break;
                case 5:
                    readCount++;
                    ImageView ivBigCard = (ImageView) msg.obj;
                    // TODO 读单词
                    playSentence(soundId[refreshNum % 3], false);
                    // TODO 放大
                    CommonAnimation.startShouAnimation22(ivBigCard);
                    if(readCount >= 3 && !isFinish) {
                        // TODO 弹出来来
                        delayMove(4000, 6, -1, null);
                    }
                    break;
                case 6:
                    ivLailai.bringToFront();
                    ivLailai.setVisibility(View.VISIBLE);
                    MediaCommon.playEgGood(mContext);
                    delayMove(4000, 7, -1, null);
                    break;
                case 7:
                    ivLailai.setVisibility(View.GONE);
                    refreshNum++;
                    if(refreshNum >= 6) {
                        // TODO 跳转到成功界面
                        finish();
                    } else {
                        refreshView();
                    }
                    break;

            }
        }
    };

    private void delayFangda(final ImageView ivCard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    handler.sendMessage(handler.obtainMessage(5, ivCard));
                    Thread.sleep(4000);
                    handler.sendMessage(handler.obtainMessage(5, ivCard));
                    Thread.sleep(4000);
                    handler.sendMessage(handler.obtainMessage(5, ivCard));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void delayMoveToEnd(final long time, final int caseId, final int toX, final int duration, final int topValue, final int extraValue, final ImageView ivCard) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
                    handler.sendMessage(handler.obtainMessage(caseId, getList(ivCard, toX, duration, topValue, extraValue)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private List<Object> getList(ImageView ivAnimation, int toX, int duration, int topValue, int extraValue) {
        List<Object> list = new ArrayList<Object>();
        list.add(ivAnimation);
        list.add(toX);
        list.add(duration);
        list.add(topValue);
        list.add(extraValue);
        return list;
    }

    private void playSentence(int rawId, boolean isMonitor) {
        try {
            if(isFinish)
                return;
//            mMediaPlayer = MediaPlayerBiz.playSoundMusic(sentencePath, mMediaPlayer);
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
        MediaPlayerBiz.clearMediaPlayer(mMediaPlayer);
        isFinish = true;
    }

}
