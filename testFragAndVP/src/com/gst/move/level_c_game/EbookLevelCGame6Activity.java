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
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 *  Feelings
 */
public class EbookLevelCGame6Activity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ClickImageView ivPic1, ivPic2;
    private ImageView ivBack, ivCard, ivAnswer;
    private TextView tvSentnce;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[] {
            "happy", "very_happy", "sad", "very_sad", "angry", "very_angry", "scared", "very_scared"
    };
    private int[] answerIndex = new int[] {
            0, 1, 0, 1, 0, 1, 0, 1
    };
    private String[][] selPicName = new String[][] {
            {"happy", "very_happy"},
            {"sad", "very_sad"},
            {"angry", "very_angry"},
            {"scared", "very_scared"},
    };
    private String[] questionArray = new String[]{
            "Sometimes I feel kind of happy.",
            "Sometimes I feel very happy.",
            "Sometimes I feel kind of sad.",
            "Sometimes I feel very sad.",
            "Sometimes I feel kind of angry.",
            "Sometimes I feel very angry.",
            "Sometimes I feel kind of scared.",
            "Sometimes I feel very scared.",
    };
    private int[] randArray, randSel;
    private int clickNum = 0;
    private String path, imgPath;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_6);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame6Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        clickNum = 0;
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        clickNum = 0;
        randArray = BaseCommon.getList(8);
        // TODO 播放引导语
        playSentence(path + "level_c_6_guide_language.mp3");
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivCard = (ImageView) findViewById(R.id.iv_card);
        ivAnswer = (ImageView) findViewById(R.id.iv_answer);
        ivPic1 = (ClickImageView) findViewById(R.id.iv_pic_1);
        ivPic2 = (ClickImageView) findViewById(R.id.iv_pic_2);
        tvSentnce = (TextView) findViewById(R.id.tv_sentence);

        setViewPosition(ivCard, 0);
        setViewPosition(ivPic1, 1);
        setViewPosition(ivPic2, 2);
        showPic();
        rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "feelings_bg"));
        ivBack.setOnClickListener(this);
        ivPic1.setOnClickListener(this);
        ivPic2.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_6_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private void showPic() {
        if(clickNum >= 8)
            return;
        ivAnswer.setVisibility(View.GONE);
        ivPic1.setVisibility(View.VISIBLE);
        ivPic2.setVisibility(View.VISIBLE);
        randSel = BaseCommon.getList(2);
        int index = randArray[clickNum];
        tvSentnce.setText("");
        int selIndex = index / 2;
        System.out.println("----------selIndex-----------" + selIndex);
        ivCard.setImageDrawable(CommonBitmap.drawableChange(imgPath, "feelings_card_" + cardName[index]));
        ivPic1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "feelings_pic_" + selPicName[selIndex][randSel[0]]));
        ivPic2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "feelings_pic_" + selPicName[selIndex][randSel[1]]));
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        } else if(v == ivPic1) {
            clickPic(v, ivPic2, 0);
        } else if(v == ivPic2) {
            clickPic(v, ivPic1, 1);
        }
    }

    private boolean isCanClick = true;
    private void clickPic(View view, View view2, int index) {
        if(isCanClick) {
            System.out.println("------index------------" + index);
            System.out.println("------clickNum------------" + clickNum);
            System.out.println("------randSel[index]------------" + randSel[index]);
            System.out.println("------answerIndex[clickNum]------------" + answerIndex[clickNum]);
            if(answerIndex[randArray[clickNum]] == randSel[index]) { // 答对了
//                view2.setVisibility(View.GONE);
                if(index == 0) {
                    setViewPosition(ivAnswer, 3);
                } else {
                    setViewPosition(ivAnswer, 4);
                }
                ivAnswer.setVisibility(View.VISIBLE);
                tvSentnce.setText(questionArray[randArray[clickNum]]);
                // TODO 读音
                playSentence(path + "feel_" + cardName[randArray[clickNum]] + ".mp3");
                delayToNext();
            } else { // 打错了
                MediaCommon.playEgError(mContext);
                CommonAnimation.shakeAnimation(mContext, view);
            }
        }
    }

    private void delayToNext() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
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
                        isCanClick = true;
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
