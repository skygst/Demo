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
 * Open and Close
 */
public class EbookLevelCGame2Activity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ImageView ivBack, ivCard, ivPic1, ivPic2;
    private TextView tvQuestion1, tvQuestion2, tvQuestion3, tvQuestion4;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[] {
            "door", "window", "drawer", "box", "book", "jar", "cage"
    };
    private String[] questionArray = new String[] {
            "the door.",
            "the window.",
            "the drawer.",
            "the box.",
            "the book.",
            "the jar.",
            "the cage."
    };
    private String[] questionArray1 = new String[7];
    private String[] questionArray4 = new String[7];
    private String[] cardArray = new String[7];
    private int[] answerId = new int[7];
    private int[] randArray;
    private int clickNum = 0;
    private String path, imgPath;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_2);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame2Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        clickNum = 0;
        for(int i=0; i<7; i++) {
            randArray = BaseCommon.getList(2);
            answerId[i] = randArray[0];
            if(randArray[0] == 0) {
                cardArray[i] = "open_" + cardName[i];
                questionArray1[i] = "I";
                questionArray1[i] = "I";
                questionArray4[i] = "open";
            } else {
                cardArray[i] = "close_" + cardName[i];
                if(i == 6) {
                    questionArray4[i] = "Close";
                    questionArray1[i] = "Quick,quick!";
                } else {
                    questionArray4[i] = "close";
                    questionArray1[i] = "You";
                }
            }
        }
        // TODO 播放引导语
        playSentence(path + "level_c_2_guide_language.mp3");
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivCard = (ImageView) findViewById(R.id.iv_card);
        ivPic1 = (ImageView) findViewById(R.id.iv_pic_1);
        ivPic2 = (ImageView) findViewById(R.id.iv_pic_2);
        tvQuestion1 = (TextView) findViewById(R.id.tv_question_1);
        tvQuestion2 = (TextView) findViewById(R.id.tv_question_2);
        tvQuestion3 = (TextView) findViewById(R.id.tv_question_3);
        tvQuestion4 = (TextView) findViewById(R.id.tv_question_4);

        setViewPosition(ivCard, 0);
        setViewPosition(ivPic1, 1);
        setViewPosition(ivPic2, 2);
        ivPic1.setOnClickListener(this);
        ivPic2.setOnClickListener(this);
        rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "open_and_close_bg"));
        ivPic1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "open_sel"));
        ivPic2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "close_sel"));
        showPic();
        ivBack.setOnClickListener(this);
        ivPic1.setOnClickListener(this);
        ivPic2.setOnClickListener(this);
    }

    private void showPic() {
        isCanClick = true;
        ivCard.setImageDrawable(CommonBitmap.drawableChange(imgPath, cardArray[clickNum]));
        tvQuestion1.setText(questionArray1[clickNum]);
        tvQuestion2.setText(" ______ ");
        tvQuestion3.setText(questionArray[clickNum]);
        tvQuestion4.setText("");
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_2_position, scaleQPW,
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
        }
    }

    private void clickPic(View view, int index) {
        if(!isCanClick) {
            return;
        }
        isCanClick = false;
        if(answerId[clickNum] == index) {
            tvQuestion4.setText(questionArray4[clickNum]);
            playSentence(path + cardArray[clickNum] + ".mp3");
            delayToNext();
        } else {
            isCanClick = true;
            MediaCommon.playEgError(mContext);
            CommonAnimation.shakeAnimation(mContext, view);
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
