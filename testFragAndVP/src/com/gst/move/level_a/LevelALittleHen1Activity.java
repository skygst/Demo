package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.MediaPlayerBiz;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPositionLevelA;
import com.ebodoo.raz.utils.CommonAnimation;
import com.ebodoo.raz.utils.CommonBitmap;
import com.ebodoo.raz.utils.ConstantEp;
import com.ebodoo.raz.utils.MediaCommon;
import com.ebodoo.raz.utils.MyToast;
import com.gst.move.R;
import com.gst.move.custom.AnimationImageView;
import com.gst.move.utils.BaseCommon;

/**
 * I had a little hen 1
 *
 * @author
 */
public class LevelALittleHen1Activity extends BaseActivity implements OnClickListener {

    private RelativeLayout rlLayout;
    private AnimationImageView ivAnimation1, ivAnimation2, ivAnimation3;
    private ImageView ivCard1, ivCard2, ivCard3, ivShou;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String imgPath;
    private VideoBiz biz;
    private String[] picArray = new String[]{
            "little_hen_bg_1", "little_hen_stone_1", "little_hen_wheat", "little_hen_pocket_1"
    };
    private AnimationDrawable animStone, animWheat, animPocket;
    private boolean[] status = new boolean[]{
            false, false, false
    };
    private int clickNum = 0;
    private boolean isCanClick = true;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.little_hen_1);

        init();
        setView();
    }

    private void init() {
        mContext = LevelALittleHen1Activity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);

        biz = new VideoBiz();
        imgPath = ConstantEp.path_level_a_game + "images/";
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivCard1 = (ImageView) findViewById(R.id.iv_card_1);
        ivCard2 = (ImageView) findViewById(R.id.iv_card_2);
        ivCard3 = (ImageView) findViewById(R.id.iv_card_3);
        ivAnimation1 = (AnimationImageView) findViewById(R.id.iv_animation_1);
        ivAnimation2 = (AnimationImageView) findViewById(R.id.iv_animation_2);
        ivAnimation3 = (AnimationImageView) findViewById(R.id.iv_animation_3);
        ivShou = (ImageView) findViewById(R.id.iv_shou);

        rlLayout.setBackground(BaseCommon.drawableChange(imgPath, picArray[0]));
        ivCard1.setBackground(BaseCommon.drawableChange(imgPath, picArray[1]));
        ivCard2.setBackground(BaseCommon.drawableChange(imgPath, picArray[2]));
        ivCard3.setBackground(BaseCommon.drawableChange(imgPath, picArray[3]));

        setViewPosition(ivCard1, 0);
        setViewPosition(ivCard2, 1);
        setViewPosition(ivCard3, 2);
        setViewPosition(ivShou, 1);

        ivShou.setVisibility(View.VISIBLE);
        CommonAnimation.startShouAnimation(ivShou);

        threadCycleAnimation();
        ivCard1.setOnClickListener(this);
        ivCard2.setOnClickListener(this);
//		ivCard3.setOnClickListener(this);
        // 播放题目读音
        delayPlay();
    }

    private void delayPlay() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    handler.sendMessage(handler.obtainMessage(0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPositionLevelA.little_hen_1_position,
                scaleQPW, scaleQPH, 0, 0, 1.0f);
    }

    private boolean isLoadFinish = false;

    @Override
    public void onClick(View v) {
        if (!isLoadFinish) {
            new MyToast().showTextToast(mContext, "数据暂未加载完，请稍后...");
            return;
        }
        System.out.println("--------------onClick------isCanClick---------" + isCanClick + "---clickNum---" + clickNum);
        if(!isCanClick && clickNum != 0) {
            return;
        }
        isCanClick = false;
        if (v == ivCard1) { // 石磨
            if (clickNum == 1) {
                clickNum = 2;
                click(v, 0, 0, animStone, ivAnimation1);
                click(v, 2, 2, animPocket, ivAnimation3);
                MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "little_hen_stone_voice.mp3", null);
            }
        } else if (v == ivCard2) { // 麦穗
            if (clickNum == 0) {
                clickNum = 1;
                click(v, 1, 3, animWheat, ivAnimation2);
            }
        }
    }

    private void click(final View v, final int index, int posIndex, AnimationDrawable animation, final AnimationImageView anImage) {
        if (ivShou.getVisibility() == View.VISIBLE) {
            CommonAnimation.stopAnimation(ivShou);
            ivShou.setVisibility(View.GONE);
        }
        v.setVisibility(View.GONE);
        anImage.setVisibility(View.VISIBLE);
        setViewPosition(anImage, posIndex);
        anImage.setBackground(animation);
        animation.start();
        if (index != 2) {
            // 监听动画是否播放完
            anImage.loadAnimation(new com.gst.move.custom.AnimationImageView.OnFrameAnimationListener() {

                @Override
                public void onStart() {
                    if (index != 1) {
                        if (ivAnimation2.getVisibility() == View.VISIBLE) {
                            ivAnimation2.setVisibility(View.GONE);
                            ivCard2.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onEnd() {
                    if (index != 1) {
                        anImage.setVisibility(View.GONE);
                        v.setVisibility(View.VISIBLE);
                    }
                    isCanClick = true;
                    if (clickNum == 2) {
                        delayFinish(2);
                    } else if (clickNum == 1) {
                        ivShou.setVisibility(View.VISIBLE);
                        setViewPosition(ivShou, 0);
                        CommonAnimation.startShouAnimation(ivShou);
                    }
                }
            }, animation);
        }
//		status[index] = true;
//		judgeIsFinish(index);
    }

    // 判断是否完成
    private void judgeIsFinish(int index) {
        boolean isFinish = true;
        for (int i = 0; i < status.length; i++) {
            if (!status[i]) {
                isFinish = false;
                break;
            }
        }
        if (isFinish)
            delayFinish(index);
    }

    private void delayFinish(final int index) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
//                    if (index == 0 || index == 1) {
//                        Thread.sleep(2800);
//                    } else {
//                        Thread.sleep(200);
//                    }
                    Thread.sleep(200);
                    handler.sendMessage(handler.obtainMessage(1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void threadCycleAnimation() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                setWheatPic();
                setStonePic();
                setPocketPic();
            }
        }).start();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    // 播放题目读音
                    MediaPlayerBiz.playSoundMusic(ConstantEp.path_level_a_game + "little_hen_problem_1.mp3", null);
                    break;
                case 1:
                    delayFinish();
                    break;
                case 2:
                    isLoadFinish = true;
                    break;
                case 3:
                    MediaCommon.playEgGood(mContext);
                    break;
                case 4:
                    finish();
                    break;
                default:
                    break;
            }
        }

        ;
    };

    private void setStonePic() {
        animStone = new AnimationDrawable();
        for (int i = 1; i <= 21; i++) {
            if (i > 11) {
                animStone.addFrame(CommonBitmap.drawableBmp(imgPath, "little_hen_stone_" + (i - 10)),
                        200);
            } else {
                animStone.addFrame(CommonBitmap.drawableBmp(imgPath, "little_hen_stone_" + i),
                        200);
            }
        }
        // 设置为循环播放
        animStone.setOneShot(true);
    }

    private void setWheatPic() {
        animWheat = new AnimationDrawable();
        for (int i = 1; i <= 10; i++) {
            animWheat.addFrame(CommonBitmap.drawableBmp(imgPath, "little_hen_wheat_" + i),
                    200);
        }
        // 设置为循环播放
        animWheat.setOneShot(true);
        handler.sendMessage(handler.obtainMessage(2));
    }

    private void setPocketPic() {
        animPocket = new AnimationDrawable();
        for (int i = 1; i <= 18; i++) {
            if (i > 9) {
                animPocket.addFrame(CommonBitmap.drawableBmp(imgPath, "little_hen_pocket_" + (i - 9)),
                        200);
            } else {
                animPocket.addFrame(CommonBitmap.drawableBmp(imgPath, "little_hen_pocket_" + i),
                        200);
            }
        }
        // 设置为循环播放
        animPocket.setOneShot(true);
    }

    private void delayFinish() {
        startActivity(new Intent(mContext, CommonGoodActivity.class));
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    handler.sendMessage(handler.obtainMessage(3));
                    Thread.sleep(2200);
                    handler.sendMessage(handler.obtainMessage(4));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
