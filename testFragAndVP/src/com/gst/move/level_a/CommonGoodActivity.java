package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPositionLevelA;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.gst.move.R;
import com.gst.move.custom.AnimationImageView;

/**
 * Created by gst-pc on 2016/9/6.
 */
public class CommonGoodActivity extends BaseActivity {

    private ImageView ivGood;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String imgPath;
    private VideoBiz biz;
    private Context mContext;
    private AnimationDrawable animGood;
    private AnimationImageView animationIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_good);

        init();
        setView();
    }

    private void init() {
        mContext = CommonGoodActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        imgPath = ConstantEp.path_level_a_game + "images/";
    }

    private void setView() {
        ivGood = (ImageView) findViewById(R.id.iv_good);
        animationIv = (AnimationImageView) findViewById(R.id.iv_animation_1);
        setViewPosition(ivGood, 0);
        threadAnimation();
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPositionLevelA.common_good_position,
                scaleQPW, scaleQPH, 0, 0, 1.0f);
    }

    private void threadAnimation() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                setGoodPic();
            }
        }).start();
    }

    private void setGoodPic() {
        animGood = new AnimationDrawable();
        for (int i = 1; i <= 11; i++) {
            animGood.addFrame(CommonBitmap.drawableBmp(imgPath, "good_" + i),
                    110);
        }
        // 设置为循环播放
        animGood.setOneShot(true);
        handler.sendMessage(handler.obtainMessage(0));
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    ivGood.setVisibility(View.GONE);
                    setViewPosition(animationIv, 0);
                    animationIv.setBackground(animGood);
                    animGood.start();
                    // 监听动画是否播放完
                    animationIv.loadAnimation(new com.gst.move.custom.AnimationImageView.OnFrameAnimationListener() {

                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onEnd() {
                            delayFinish();
                        }
                    }, animGood);
                    break;
                case 1:
                    finish();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    private void delayFinish() {
        startActivity(new Intent(mContext, CommonGoodActivity.class));
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    handler.sendMessage(handler.obtainMessage(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
