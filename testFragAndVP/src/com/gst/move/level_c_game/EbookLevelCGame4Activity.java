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
 * What Is at the Zoo
 */
public class EbookLevelCGame4Activity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ImageView ivBack, ivCard, ivPic1, ivPic2, ivAnswer;
    private TextView tvQuestion;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[] {
            "polar_bear", "monkey", "elephant", "giraffe", "lion", "hippo", "sea_lions", "dinosaur"
    };
    private String[] sentence = new String[] {
            "bears", "monkeys", "elephants", "giraffes", "lions", "hippos", "sea_lions", "dinosaurs"
    };
    private String[] questionArray = new String[]{
            "Are there bears at the zoo?",
            "Are there monkeys at the zoo?",
            "Are there elephants at the zoo?",
            "Are there giraffes at the zoo?",
            "Are there lions and tigers at the zoo?",
            "Are there hippos at the zoo? ",
            "Are there sea loins at the zoo?",
            "Are there dinosaurs at the zoo?", // No,there are not.
    };
    private int[] randArray;
    private int clickNum = 0;
    private String path, imgPath;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_4);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame4Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        clickNum = 0;
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        clickNum = 0;
        randArray = BaseCommon.getList(8);
        // TODO 播放引导语
        playSentence(path + "level_c_4_guide_language.mp3", true);
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivCard = (ImageView) findViewById(R.id.iv_card);
        ivPic1 = (ImageView) findViewById(R.id.iv_pic_1);
        ivPic2 = (ImageView) findViewById(R.id.iv_pic_2);
        tvQuestion = (TextView) findViewById(R.id.tv_question);
        ivAnswer = (ImageView) findViewById(R.id.iv_answer);

        setViewPosition(ivCard, 0);
        setViewPosition(ivPic1, 1);
        setViewPosition(ivPic2, 2);
        ivPic1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "zoo_there_are"));
        ivPic2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "zoo_there_are_not"));
        showPic();
        ivBack.setOnClickListener(this);
        ivPic1.setOnClickListener(this);
        ivPic2.setOnClickListener(this);
    }

    private void showPic() {
        if(randArray[clickNum] == 7) {
            rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "zoo_bg_2"));
        } else {
            rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "zoo_bg_1"));
        }
        ivAnswer.setVisibility(View.GONE);
        ivCard.setImageDrawable(CommonBitmap.drawableChange(imgPath, "zoo_" + cardName[randArray[clickNum]]));
        tvQuestion.setText(questionArray[randArray[clickNum]]);
        if(clickNum != 0)
            playSentence(path + sentence[randArray[clickNum]] + "_zoo.mp3", false);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_4_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        } else if(v == ivPic1) {
            clickPic(v, 0);
        } else if(v == ivPic2) {
            clickPic(v, 1);
        }
    }

    private void clickPic(View view, int index) {
        if(randArray[clickNum] == 7) {
            processResult(view, index, 1);
        } else {
            processResult(view, index, 0);
        }
    }

    private void processResult(View view, int index, int num) {
        if(index == num) {
            // TODO 句子读一遍
            if(index == 1) {
                setViewPosition(ivAnswer, 4);
                playSentence(path + "there_are_not.mp3", false);
            } else {
                setViewPosition(ivAnswer, 3);
                playSentence(path + "there_are.mp3", false);
            }
            ivAnswer.setVisibility(View.VISIBLE);
            delayToNext();
        } else {
            MediaCommon.playEgError(mContext);
            CommonAnimation.shakeAnimation(mContext, view);
        }
    }

    private void delayToNext() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
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
                    if (clickNum < 7) {
                        clickNum++;
                        showPic();
                    } else {
                        finish();
                    }
                    break;
            }
        }
    };

    private void playSentence(String sentencePath, boolean isMonitor) {
        try {
            mMediaPlayer = MediaPlayerBiz.playSoundMusic(sentencePath, mMediaPlayer);
            if(isMonitor) {
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        playSentence(path + sentence[randArray[clickNum]] + "_zoo.mp3", false);
                    }
                });
            }
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
