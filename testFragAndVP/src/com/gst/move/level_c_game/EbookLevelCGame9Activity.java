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
import com.example.location.biz.FileBiz;
import com.gst.move.R;

/**
 * What I Want
 */
public class EbookLevelCGame9Activity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ClickImageView ivPic1, ivPic2, ivPic3;
    private ImageView ivBack, ivAnswer, ivShou, ivBody, ivPicHead, ivPicArm, ivPicEye, ivPicNose, ivCardHead, ivCardArm, ivCardEye, ivCardNose;
    private ImageView[] ivCard;
    private TextView tvSentnce;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[]{
            "play", "out_play", "play_snow", "snowman", "house"
    };
    private String[] sentenceName = new String[]{
            "play", "out_play", "play_snow", "make", "arms", "eyes", "nose", "snowman", "house"
    };
    private String[] snowmanPicArray = new String[]{
            "pic_head", "pic_arm", "pic_eye", "pic_nose", "card_head", "card_arm", "card_eye", "card_nose"
    };
    private String[] questionArray = new String[]{
            "I go out to play.",
            "My friends go out to play.",
            "We play in the snow.",
            "We make a snowman.",
            "We make arms for the snowman.",
            "We make eyes for the snowman.",
            "We make a nose and a mouth.",
            "We play with the snowman.",
            "We go in the house.Dad makes hot soup. Yum!",
    };
    private int clickNum = 0;
    private String path, imgPath;
    private MediaPlayer mMediaPlayer;
    private int[] randPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_9);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame9Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        clickNum = 0;
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        clickNum = 0;
        // TODO 播放引导语
        playSentence(path + "level_c_9_guide_language.mp3", true);
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW, scaleQPH);
        ivAnswer = (ImageView) findViewById(R.id.iv_answer);
        ivShou = (ImageView) findViewById(R.id.iv_shou);
        ivPic1 = (ClickImageView) findViewById(R.id.iv_pic_1);
        ivPic2 = (ClickImageView) findViewById(R.id.iv_pic_2);
        ivPic3 = (ClickImageView) findViewById(R.id.iv_pic_3);
        tvSentnce = (TextView) findViewById(R.id.tv_sentence);

        ivShou = (ImageView) findViewById(R.id.iv_shou);
        ivBody = (ImageView) findViewById(R.id.iv_body);
        ivPicHead = (ImageView) findViewById(R.id.iv_pic_head);
        ivPicArm = (ImageView) findViewById(R.id.iv_pic_arm);
        ivPicEye = (ImageView) findViewById(R.id.iv_pic_eye);
        ivPicNose = (ImageView) findViewById(R.id.iv_pic_nose);
        ivCardHead = (ImageView) findViewById(R.id.iv_card_head);
        ivCardArm = (ImageView) findViewById(R.id.iv_card_arm);
        ivCardEye = (ImageView) findViewById(R.id.iv_card_eye);
        ivCardNose = (ImageView) findViewById(R.id.iv_card_nose);
        ivCard = new ImageView[8];
        ivCard[0] = ivPicHead;
        ivCard[1] = ivPicArm;
        ivCard[2] = ivPicEye;
        ivCard[3] = ivPicNose;
        ivCard[4] = ivCardHead;
        ivCard[5] = ivCardArm;
        ivCard[6] = ivCardEye;
        ivCard[7] = ivCardNose;

        setViewPosition(ivBody, 7);
        ivBody.setImageDrawable(CommonBitmap.drawableChange(imgPath, "snowman_card_body"));
        for(int i=0; i<ivCard.length; i++) {
            ivCard[i].setImageDrawable(CommonBitmap.drawableChange(imgPath, "snowman_" + snowmanPicArray[i]));
            setViewPosition(ivCard[i], (8 + i));
            ivCard[i].setVisibility(View.GONE);
        }

        setViewPosition(tvSentnce, 0);
        setViewPosition(ivPic1, 1);
        setViewPosition(ivPic2, 2);
        setViewPosition(ivPic3, 3);

        showPic();
        rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "snowman_bg_1"));
        ivBack.setOnClickListener(this);
        ivPic1.setOnClickListener(this);
        ivPic2.setOnClickListener(this);
        ivPic3.setOnClickListener(this);
        ivShou.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_9_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private int[] picShowIndex;

    private void showPic() {
        if (clickNum >= 9)
            return;
        if(clickNum < 3) {
            ivAnswer.setVisibility(View.GONE);
            randPic = BaseCommon.getList(3, clickNum);
            int[] picIndex = new int[]{
                    clickNum, randPic[0], randPic[1]
            };
            int[] selPic = BaseCommon.getList(3);
            picShowIndex = new int[]{
                    picIndex[selPic[0]], picIndex[selPic[1]], picIndex[selPic[2]]
            };
            for (int i = 0; i < picShowIndex.length; i++) {
                System.out.println("----------picShowIndex----------" + picShowIndex[i]);
            }
            tvSentnce.setText(questionArray[clickNum]);
            ivPic1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "snowman_" + cardName[picShowIndex[0]]));
            ivPic2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "snowman_" + cardName[picShowIndex[1]]));
            ivPic3.setImageDrawable(CommonBitmap.drawableChange(imgPath, "snowman_" + cardName[picShowIndex[2]]));
            // TODO 读音
            if(clickNum > 0)
                playSentence(path + "snowman_" + cardName[clickNum] + ".mp3", false);
        } else if (clickNum == 3) {
            tvSentnce.setText(questionArray[clickNum]);
            rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "snowman_bg_2"));
            ivPic1.setVisibility(View.GONE);
            ivPic2.setVisibility(View.GONE);
            ivPic3.setVisibility(View.GONE);
            ivAnswer.setVisibility(View.GONE);
            ivBody.setVisibility(View.VISIBLE);
            for(int i=0; i<4; i++) {
                ivCard[i].setVisibility(View.VISIBLE);
            }
            ivShou.setVisibility(View.VISIBLE);
            CommonAnimation.startShouAnimation(ivShou);
            setViewPosition(ivShou, 8);
            if(clickNum == 3) {
                // TODO  堆雪人引导语
                playSentence(path + "level_c_9_2_guide_language.mp3", true);
                System.out.println("---------- clickNum == 3 --------------");
            }
        } else {
            ivAnswer.setVisibility(View.GONE);
            tvSentnce.setText(questionArray[clickNum]);
            randPic = BaseCommon.getList(2);
            int[] picIndex = new int[]{
                    randPic[0] + 3, randPic[1] + 3
            };
            picShowIndex = new int[]{
                    picIndex[randPic[0]], picIndex[randPic[1]]
            };
            for (int i = 0; i < picShowIndex.length; i++) {
                System.out.println("----------picShowIndex----------" + picShowIndex[i]);
            }
            ivPic1.setImageDrawable(CommonBitmap.drawableChange(imgPath, "snowman_" + cardName[picShowIndex[0]]));
            ivPic2.setImageDrawable(CommonBitmap.drawableChange(imgPath, "snowman_" + cardName[picShowIndex[1]]));
            // TODO 读音
            playSentence(path + "snowman_" + sentenceName[clickNum] + ".mp3", false);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ivBack) {
            finish();
        } else if (v == ivPic1) {
            clickPic(v, 0);
        } else if (v == ivPic2) {
            clickPic(v, 1);
        } else if (v == ivPic3) {
            clickPic(v, 2);
        } else if(v == ivShou) {
            clickNum++;
            tvSentnce.setText(questionArray[clickNum]);
            if(clickNum > 3 && clickNum < 7) {
                String name = path + "snowman_" + sentenceName[clickNum] + ".mp3";
                if(FileBiz.isExists(name)) {
                    System.out.println("----------true----------");
                } else {
                    System.out.println("----------false----------");
                }
                playSentence(path + "snowman_" + sentenceName[clickNum] + ".mp3", false);
            }
            if(clickNum == 4) {
                cardStatus(0, 4);
                setViewPosition(ivShou, 9);
            } else if(clickNum == 5) {
                cardStatus(1, 5);
                setViewPosition(ivShou, 10);
            } else if(clickNum == 6) {
                cardStatus(2, 6);
                setViewPosition(ivShou, 11);
            } else if(clickNum == 7) {
                clickNum--;
                tvSentnce.setText("");
                ivShou.clearAnimation();
                ivShou.setVisibility(View.GONE);
                cardStatus(3, 7);
                delayToNext();
            }
        }
    }

    private void cardStatus(int goneIndex, int visibleIndex) {
        ivCard[goneIndex].setVisibility(View.GONE);
        ivCard[visibleIndex].setVisibility(View.VISIBLE);
    }

    private boolean isCanClick = true;

    private void clickPic(View view, int index) {
        if (isCanClick) {
            System.out.println("-----picShowIndex[index]----" + picShowIndex[index]);
            System.out.println("-----clickNum----" + clickNum);
            if (picShowIndex[index] == (clickNum >= 7 ? (clickNum - 4) : clickNum)) { // 答对了
                ivAnswer.setVisibility(View.VISIBLE);
                if(clickNum > 4) {
                    setViewPosition(ivAnswer, (index + 18));
                } else {
                    setViewPosition(ivAnswer, (index + 4));
                }
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
                    if(clickNum > 3 && clickNum < 7) {
                        Thread.sleep(1500);
                    } else {
                        Thread.sleep(3500);
                    }
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
                    if (clickNum < 8) {
                        clickNum++;
                        processInfo();
                        isCanClick = true;
                        showPic();
                    } else {
                        finish();
                    }
                    break;
            }
        }
    };

    private void processInfo() {
        if(clickNum == 7) {
            rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "snowman_bg_1"));
            tvSentnce.setVisibility(View.VISIBLE);
            ivBody.setVisibility(View.GONE);
            ivPic1.setVisibility(View.VISIBLE);
            ivPic2.setVisibility(View.VISIBLE);
            for(int i=0; i<8; i++) {
                ivCard[i].setVisibility(View.GONE);
            }
            setViewPosition(ivPic1, 16);
            setViewPosition(ivPic2, 17);
        }
    }

    private void playSentence(String sentencePath, boolean isMonitor) {
        try {
            mMediaPlayer = MediaPlayerBiz.playSoundMusic(sentencePath, mMediaPlayer);
            if(isMonitor) {
                mMediaPlayer.setOnCompletionListener(
                        new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                if(clickNum == 0) {
                                    playSentence(path + "snowman_" + sentenceName[clickNum] + ".mp3", false);
                                } else if(clickNum == 3) {
                                    playSentence(path + "snowman_" + sentenceName[clickNum] + ".mp3", false);
                                }
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
