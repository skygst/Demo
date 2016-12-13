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
 *  What I Want
 */
public class EbookLevelCGame8Activity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ClickImageView ivPic1, ivPic2, ivPic3;
    private ImageView ivBack, ivAnswer;
    private TextView tvSentnce;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[] {
            "hug", "feed", "play_with", "teach", "dream_with", "read", "walk", "love_dog"
    };
    private String[] questionArray = new String[]{
            "I want something to hug.",
            "I want something to feed.",
            "I want something to play with.",
            "I want something to teach.",
            "I want something to dream with.",
            "I want something to read to.",
            "I want something to walk.",
            "I want something to love. I want a dog !",
    };
    private int clickNum = 0;
    private String path, imgPath;
    private MediaPlayer mMediaPlayer;
    private int[] randPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_8);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame8Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        clickNum = 0;
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        clickNum = 0;
        // TODO 播放引导语
        playSentence(path + "level_c_8_guide_language.mp3", true);
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivAnswer = (ImageView) findViewById(R.id.iv_answer);
        ivPic1 = (ClickImageView) findViewById(R.id.iv_pic_1);
        ivPic2 = (ClickImageView) findViewById(R.id.iv_pic_2);
        ivPic3 = (ClickImageView) findViewById(R.id.iv_pic_3);
        tvSentnce = (TextView) findViewById(R.id.tv_sentence);

        setViewPosition(tvSentnce, 0);
        setViewPosition(ivPic1, 1);
        setViewPosition(ivPic2, 2);
        setViewPosition(ivPic3, 3);

        showPic();
        rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "want_bg"));
        ivBack.setOnClickListener(this);
        ivPic1.setOnClickListener(this);
        ivPic2.setOnClickListener(this);
        ivPic3.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_8_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private int[] picShowIndex;
    private void showPic() {
        if(clickNum >= 8)
            return;
        ivAnswer.setVisibility(View.GONE);
        randPic = BaseCommon.getList(8, clickNum);
        int[] picIndex = new int[] {
                clickNum, randPic[0], randPic[1]
        };
        int[] selPic = BaseCommon.getList(3);
        picShowIndex = new int[] {
                picIndex[selPic[0]], picIndex[selPic[1]], picIndex[selPic[2]]
        };
        for(int i=0; i<picShowIndex.length; i++) {
            System.out.println("----------picShowIndex----------" + picShowIndex[i]);
        }
        tvSentnce.setText(questionArray[clickNum]);
        ivPic1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "want_" + cardName[picShowIndex[0]]));
        ivPic2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "want_" + cardName[picShowIndex[1]]));
        ivPic3.setImageDrawable(CommonBitmap.drawableChange(imgPath, "want_" + cardName[picShowIndex[2]]));

        // TODO 读音
        if(clickNum > 0)
            playSentence(path + "want_" + cardName[clickNum] +".mp3", false);
    }

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
        }
    }

    private boolean isCanClick = true;
    private void clickPic(View view, int index) {
        if(isCanClick) {
            if(picShowIndex[index] == clickNum) { // 答对了
                ivAnswer.setVisibility(View.VISIBLE);
                setViewPosition(ivAnswer, (index+4));
                MediaCommon.playEgGood(mContext);
                delayToNext();
            } else { // 答错了
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

    private void playSentence(String sentencePath, boolean isMonitor) {
        try {
            mMediaPlayer = MediaPlayerBiz.playSoundMusic(sentencePath, mMediaPlayer);
            if(isMonitor) {
                mMediaPlayer.setOnCompletionListener(
                        new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                playSentence(path + "want_" + cardName[clickNum] + ".mp3", false);
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
