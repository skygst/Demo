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
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;

/**
 *  Get In
 */
public class EbookLevelCGame7Activity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ClickImageView ivPic1, ivPic2;
    private ImageView ivBack, ivCard;
    private TextView tvSentnce;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[] {
            "boy", "girl", "man", "woman", "duck", "dog", "pig", "elephant"
    };
    private String[][] selPicName = new String[][] {
            {"he_can", "he_cannot"},
            {"she_can", "she_cannot"},
            {"he_can", "he_cannot"},
            {"she_can", "she_cannot"},
            {"it_can", "it_cannot"},
            {"it_can", "it_cannot"},
            {"it_can", "it_cannot"},
            {"it_can", "it_cannot"},
    };
    private String[][] questionArray = new String[][]{
            {"Can the boy get in?", "Yes, he can."},
            {"Can the girl get in?", "Yes, she can."},
            {"Can the man get in?", "Yes, he can."},
            {"Can the woman get in?", "Yes, she can."},
            {"Can the duck get in?", "Yes, it can."},
            {"Can the dog get in ?", "Yes, it can."},
            {"Can the pig get in ?", "Yes, it can."},
            {"Can the elephant get in ?", "No, it cannot."},
    };
    private int clickNum = 0;
    private String path, imgPath;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_7);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame7Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        clickNum = 0;
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        clickNum = 0;
        // TODO 播放引导语
        playSentence(path + "level_c_7_guide_language.mp3", true);
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivCard = (ImageView) findViewById(R.id.iv_card);
        ivPic1 = (ClickImageView) findViewById(R.id.iv_pic_1);
        ivPic2 = (ClickImageView) findViewById(R.id.iv_pic_2);
        tvSentnce = (TextView) findViewById(R.id.tv_sentence);

        setViewPosition(ivCard, 0);
        setViewPosition(ivPic1, 1);
        setViewPosition(ivPic2, 2);
        showPic();
        rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "get_in_bg"));
        ivBack.setOnClickListener(this);
        ivPic1.setOnClickListener(this);
        ivPic2.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_7_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private void showPic() {
        if(clickNum >= 8)
            return;
        ivPic1.setVisibility(View.VISIBLE);
        ivPic2.setVisibility(View.VISIBLE);
        tvSentnce.setText(questionArray[clickNum][0]);
        ivCard.setImageDrawable(CommonBitmap.drawableChange(imgPath, "get_in_" + cardName[clickNum] + "_1"));
        ivPic1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "get_in_" + selPicName[clickNum][0]));
        ivPic2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "get_in_" + selPicName[clickNum][1]));
        if(clickNum > 0) {
            playSentence(path + "get_in_" + cardName[clickNum] + ".mp3", false);
        }
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
            if((clickNum != 7 && index == 0) || (clickNum == 7 && index == 1)) { // 答对了
                view.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                tvSentnce.setText(questionArray[clickNum][1]);
                ivCard.setImageDrawable(CommonBitmap.drawableChange(imgPath, "get_in_" + cardName[clickNum] + "_2"));
                // TODO 读音
                System.out.println("------ name ---" + "get_in_" + selPicName[clickNum][1] + ".mp3");
                if(clickNum == 7 && index == 1) {
                    playSentence(path + "get_in_" + selPicName[clickNum][1] + ".mp3", false);
                } else {
                    playSentence(path + "get_in_" + selPicName[clickNum][0] + ".mp3", false);
                }
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
                        playSentence(path + "get_in_" + cardName[clickNum] + ".mp3", false);
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
