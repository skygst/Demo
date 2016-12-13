package com.gst.move.level_c_game;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.gst.move.R;

/**
 *  When Is Nighttime
 */
public class EbookLevelCGame5Activity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ImageView ivBack, ivCard1, ivCard2, ivCard3, ivCard4, ivCard5, ivCard6,
            ivPic1, ivPic2, ivPic3, ivPic4, ivPic5, ivPic6;
    private TextView tvQuestion;
    private ImageView[] ivCard, ivPic;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[] {
            "moon", "bat", "sleep", "little_bee", "stars", "read"
    };
    private String[] sentence = new String[] {
            "moon", "bats", "sleep", "fireflies", "stars", "read"
    };
    private String[] questionArray = new String[]{
         "Nighttime is when the moon rises.",
         "It is nighttime when the bats fly.",
         "Nighttime is when I go to sleep.",
         "Nighttime is when the fireflies blink.",
         "When the stars shine,it is nighttime.",
         "Nighttime is when Dad reads to me.",
    };
    private int[] randArray;
    private int clickNum = 0;
    private String path, imgPath;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_5);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame5Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        clickNum = 0;
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        clickNum = 0;
        randArray = BaseCommon.getList(8);
        // TODO 播放引导语
        playSentence(path + "level_c_5_guide_language.mp3");
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
        ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
        ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
        ivCard4 = (ImageView) findViewById(R.id.iv_card_4);
        ivCard5 = (ImageView) findViewById(R.id.iv_card_5);
        ivCard6 = (ImageView) findViewById(R.id.iv_card_6);

        ivPic1 = (ImageView) findViewById(R.id.iv_pic_1);
        ivPic2 = (ImageView) findViewById(R.id.iv_pic_2);
        ivPic3 = (ImageView) findViewById(R.id.iv_pic_3);
        ivPic4 = (ImageView) findViewById(R.id.iv_pic_4);
        ivPic5 = (ImageView) findViewById(R.id.iv_pic_5);
        ivPic6 = (ImageView) findViewById(R.id.iv_pic_6);
        tvQuestion = (TextView) findViewById(R.id.tv_question);

        ivCard = new ImageView[6];
        ivCard[0] = ivCard1;
        ivCard[1] = ivCard2;
        ivCard[2] = ivCard3;
        ivCard[3] = ivCard4;
        ivCard[4] = ivCard5;
        ivCard[5] = ivCard6;
        for (int i=0; i<ivCard.length; i++) {
            ivCard[i].setOnClickListener(this);
            setViewPosition(ivCard[i], i);
            ivCard[i].setImageDrawable(CommonBitmap.drawableChange(imgPath, "nighttime_" + cardName[i]));
        }
        ivPic = new ImageView[6];
        ivPic[0] = ivPic1;
        ivPic[1] = ivPic2;
        ivPic[2] = ivPic3;
        ivPic[3] = ivPic4;
        ivPic[4] = ivPic5;
        ivPic[5] = ivPic6;
        for (int i=0; i<ivPic.length; i++) {
            setViewPosition(ivPic[i], (i+6));
            ivPic[i].setImageDrawable(CommonBitmap.drawableChange(imgPath, "nighttime_" + cardName[i] + "_pic"));
        }
        rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "nighttime_bg"));
        ivBack.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_5_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        } else if(v == ivCard1) {
            clickPic(v, 0);
        } else if(v == ivCard2) {
            clickPic(v, 1);
        } else if(v == ivCard3) {
            clickPic(v, 2);
        } else if(v == ivCard4) {
            clickPic(v, 3);
        } else if(v == ivCard5) {
            clickPic(v, 4);
        } else if(v == ivCard6) {
            clickPic(v, 5);
        }
    }
    private boolean isCanClick = true;
    private void clickPic(View view, int index) {
        if(isCanClick) {
            isCanClick = false;
            ((ImageView)view).setImageDrawable(CommonBitmap.drawableChange(imgPath, "nighttime_" + cardName[index] + "_sel"));
            ivPic[index].setVisibility(View.VISIBLE);
            tvQuestion.setText(questionArray[index]);
            view.setFocusable(false);
            view.setEnabled(false);
            playSentence(path + "nighttime_" + sentence[index] + ".mp3");
            delayToNext();
        }
    }

    private void delayToNext() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3500);
                    handler.sendMessage(handler.obtainMessage(0));
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
                    if (clickNum < 5) {
                        clickNum++;
                        isCanClick = true;
                    } else {
                        finish();
                    }
                    break;
            }
        }
    };

    private void playSentence(String sentencePath) {
        try {
            mMediaPlayer = MediaPlayerBiz.playSoundMusic(sentencePath, mMediaPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearMedia() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearMedia();
    }

}
