package com.gst.move.level_c_game;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.gst.move.R;

/**
 * Going Away
 */
public class EbookLevelCGame3Activity extends BaseActivity implements View.OnClickListener {

    private Context mContext;
    private RelativeLayout rlLayout;
    private ImageView ivBack, ivCard, ivShou;
    private TextView tvQuestion;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String[] cardName = new String[] {
            "car", "ship", "aircraft", "bike", "balloon", "train", "rocket", "shoes"
    };
    private String[] firstSentence = new String[] {
            "car", "boat", "plane", "bike", "balloon", "train", "rocket", "shoes"
    };
    private String[] sencondSentence = new String[] {
            "drive", "sail", "fly", "pedal", "float", "chug", "zoom", "run"
    };
    private String[][] questionArray = new String[][]{
            {"We can get in a car.", "We can drive away."},
            {"We can get in a boat.", "We can sail away."},
            {"We can get on a plane.", "We can fly away."},
            {"We can get on a bike.", "We can pedal away."},
            {"We can get in a balloon.", "We can float away."},
            {"We can get on a train.", "We can chug away."},
            {"We can get in a rocket.", "We can zoom away."},
            {"We can put on our shoes.", "We can run away."}
    };
    private int clickNum = 0;
    private String path, imgPath;
    private boolean isFirst = true;
    private MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelc_game_3);

        init();
        setView();
    }

    private void init() {
        mContext = EbookLevelCGame3Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        path = ConstantEp.path_ebook_levelc;
        imgPath = ConstantEp.path_ebook_levelc + "images/";
        clickNum = 0;
        // TODO 播放引导语
        playSentence(path + "level_c_3_guide_language.mp3");
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBack = (ImageView) findViewById(R.id.iv_back);
        biz.setViewPositionNoSuoxiao(ivBack, 0, FixedPosition.common_position, scaleQPW ,scaleQPH);
        ivCard = (ImageView) findViewById(R.id.iv_card);
        ivShou = (ImageView) findViewById(R.id.iv_shou);
        tvQuestion = (TextView) findViewById(R.id.tv_question);

        setViewPosition(ivCard, 0);
        setViewPosition(ivShou, 1);
        rlLayout.setBackgroundDrawable(CommonBitmap.drawableChange(imgPath, "away_bg"));
        showPic();
        ivBack.setOnClickListener(this);
        ivShou.setOnClickListener(this);
    }

    private void showPic() {
        ivShou.setVisibility(View.VISIBLE);
        CommonAnimation.startShouAnimation(ivShou);
        if(isFirst) {
            setViewPosition(ivShou, 1);
            ivCard.setImageDrawable(CommonBitmap.drawableChange(imgPath, "away_" + cardName[clickNum] + "_people"));
            tvQuestion.setText(questionArray[clickNum][0]);
        } else {
            setViewPosition(ivShou, 2);
            ivCard.setImageDrawable(CommonBitmap.drawableChange(imgPath, "away_" + cardName[clickNum]));
            tvQuestion.setText(questionArray[clickNum][1]);
        }
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.level_c_game_3_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == ivBack) {
            finish();
        } else if(v == ivShou) {
            clickPic();
        }
    }

    private void clickPic() {
        ivShou.clearAnimation();
        ivShou.setVisibility(View.GONE);
        isFirst = !isFirst;
        if (!isFirst) {
            playSentence(path + "in_a_" + firstSentence[clickNum] + ".mp3");
            delayRefresh();
        } else {
            playSentence(path + sencondSentence[clickNum] + "_away.mp3");
            ivShou.clearAnimation();
            ivShou.setVisibility(View.GONE);
            startAnimationView();
        }
    }

    private void delayRefresh() {
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
                    showPic();
                    break;
            }
        }
    };

    public void startAnimationView() {
        // 移动
        Animation anim = null;
        if(clickNum == 0) { // 汽车
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,-1500,Animation.RELATIVE_TO_SELF,1000);
        } else if(clickNum == 1) { // 船
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,1500,Animation.RELATIVE_TO_SELF,-200);
        } else if(clickNum == 2){ // 飞机
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,1500,Animation.RELATIVE_TO_SELF,-1000);
        } else if(clickNum == 3) { // 自行车
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,1500,Animation.RELATIVE_TO_SELF, 0);
        } else if(clickNum == 4) { // 气球
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,1500,Animation.RELATIVE_TO_SELF, -1000);
        } else if(clickNum == 5) { // 火车
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,-1500,Animation.RELATIVE_TO_SELF, 0);
        } else if(clickNum == 6) { // 火箭
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0,Animation.RELATIVE_TO_SELF, -2000);
        } else if(clickNum == 7) { // 鞋
            anim = new TranslateAnimation(Animation.RELATIVE_TO_SELF,1500,Animation.RELATIVE_TO_SELF, 1000);
        }
        anim.setDuration(2000);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                clickNum++;
                if(clickNum <= 7) {
                    ivShou.setVisibility(View.VISIBLE);
//                    showPic();
                } else {
                    finish();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        // 缩放
        ScaleAnimation animation = null;
        if(clickNum == 0 || clickNum == 2 || clickNum == 7) {
            animation = new ScaleAnimation(1.0f, 2.5f, 1.0f, 2.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1000);//设置动画持续时间
        }
        AnimationSet as = new AnimationSet(true);
        as.addAnimation(anim);
        if(animation != null) {
            as.addAnimation(animation);
        }
        as.setDuration(2000);
        ivCard.setAnimation(as);
    }

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
