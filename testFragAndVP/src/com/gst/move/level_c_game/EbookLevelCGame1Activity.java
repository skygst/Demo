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
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 * What Animals Eat
 */
public class EbookLevelCGame1Activity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ImageView ivBack, ivCard, ivPic1, ivPic2, ivPic3, ivPic4, ivPic5, ivPic6, ivPic7;
    private ImageView[] ivPic;
    private TextView tvQuestion;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[] {
            "cow", "birds", "pandas", "frogs", "monkeys", "seals", "goats"
    };
    private int[] delayTime = new int[] {
           6000, 6000, 6000, 7000, 6000, 6000, 9000
    };
    private String[] cardSelName = new String[] {
            "_eat_green_grass", "_eat_pine_nut", "_eat_bamboo",
            "_eat_fly", "_eat_banana", "_eat_fish", "_eat_everything"
    };
    private String[] picName = new String[] {// animals_eat_
            "pine_nut", "book_everything", "banana", "fly", "bamboo", "fish", "green_grass"
    };
    private String[] questionArray = new String[]{// animals_eat_
            "Cows like to eat ______.",
            "Birds like to eat ______.",
            "Pandas like to eat ______.",
            "Frogs like to eat ______.",
            "Monkeys like to eat ______.",
            "Seals like to eat ______.",
            "Goats like to eat ______.",
    };

    private String[] answerArray = new String[]{// animals_eat_
            "Cows like to eat grass.Munch,munch.",
            "Birds like to eat seeds.Crunch,crunch.",
            "Pandas like to eat leaves.Munch,munch.",
            "Frogs like to eat bugs.Zap,zap.",
            "Monkeys like to eat fruit.Slurp,slurp.",
            "Seals like to eat fish.Gulp,gulp.",
            "Goats like to eat everything.Hey,stop! That's my sock.",
    };
    private int[] clickAnswer = new int[] {
            6, 0, 4, 3, 2, 5, 1
    };
    private int[] randArray;
    private int clickNum = 0;
    private String path, imgPath;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_1);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame1Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        clickNum = 0;
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        randArray = BaseCommon.getList(7);
        for(int i=0; i<randArray.length; i++) {
            System.out.println("---------------" + randArray[i]);
        }
        // TODO 播放引导语
        playSentence(path + "level_c_1_guide_language.mp3");
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivCard = (ImageView) findViewById(R.id.iv_card);
        ivPic1 = (ImageView) findViewById(R.id.iv_pic_1);
        ivPic2 = (ImageView) findViewById(R.id.iv_pic_2);
        ivPic3 = (ImageView) findViewById(R.id.iv_pic_3);
        ivPic4 = (ImageView) findViewById(R.id.iv_pic_4);
        ivPic5 = (ImageView) findViewById(R.id.iv_pic_5);
        ivPic6 = (ImageView) findViewById(R.id.iv_pic_6);
        ivPic7 = (ImageView) findViewById(R.id.iv_pic_7);
        tvQuestion = (TextView) findViewById(R.id.tv_question);

        setViewPosition(ivCard, 0);

        ivPic = new ImageView[7];
        ivPic[0] = ivPic1;
        ivPic[1] = ivPic2;
        ivPic[2] = ivPic3;
        ivPic[3] = ivPic4;
        ivPic[4] = ivPic5;
        ivPic[5] = ivPic6;
        ivPic[6] = ivPic7;
        for(int i=0; i<ivPic.length; i++) {
            setViewPosition(ivPic[i], (i + 1));
            ivPic[i].setOnClickListener(this);
        }
        rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "animals_eat_bg"));
        showPic();
        ivBack.setOnClickListener(this);
    }

    private void showPic() {
        isCanClick = true;
        ivCard.setImageDrawable(CommonBitmap.drawableChange(imgPath, "animals_eat_" + cardName[randArray[clickNum]]));
        tvQuestion.setText(questionArray[randArray[clickNum]]);
        for(int i=0; i<ivPic.length; i++) {
            ivPic[i].setImageDrawable(CommonBitmap.drawableChange(imgPath, picName[i]));
        }
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_1_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private boolean isCanClick = true;

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        } else if(v == ivPic1) {
            clickPic(v, 0);
        } else if(v == ivPic2) {
            clickPic(v, 1);
        } else if(v == ivPic3) {
            clickPic(v, 2);
        } else if(v == ivPic4) {
            clickPic(v, 3);
        } else if(v == ivPic5) {
            clickPic(v, 4);
        } else if(v == ivPic6) {
            clickPic(v, 5);
        } else if(v == ivPic7) {
            clickPic(v, 6);
        }
    }

    private void clickPic(View view, int index) {
        if(!isCanClick) {
            return;
        }
        isCanClick = false;
        if(clickAnswer[randArray[clickNum]] == index) {
            ((ImageView)view).setImageDrawable(CommonBitmap.drawableChange(imgPath, picName[index] + "_sel"));
            ivCard.setImageDrawable(CommonBitmap.drawableChange(imgPath, cardName[randArray[clickNum]]+ cardSelName[randArray[clickNum]]));
            tvQuestion.setText(answerArray[randArray[clickNum]]);
            playSentence(path + "eat_" + cardName[randArray[clickNum]] + ".mp3");
            delayToNext(delayTime[randArray[clickNum]]);
        } else {
            isCanClick = true;
            MediaCommon.playEgError(mContext);
            CommonAnimation.shakeAnimation(mContext, view);
        }
    }

    private void delayToNext(final int time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(time);
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
                    if (clickNum < 6) {
                        clickNum++;
                        showPic();
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
