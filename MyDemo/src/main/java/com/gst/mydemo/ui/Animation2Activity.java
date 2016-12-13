package com.gst.mydemo.ui;

/**
 * Created by 善同 on 2016/1/29.
 */

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.gst.mydemo.R;

public class Animation2Activity extends Activity
{
    private ImageView animationIV;
    private Button buttonA, buttonB, buttonC;
    private AnimationDrawable animationDrawable;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.animation_2);


        animationIV = (ImageView) findViewById(R.id.animationIV);
        buttonA = (Button) findViewById(R.id.buttonA);
        buttonB = (Button) findViewById(R.id.buttonB);
        buttonC = (Button) findViewById(R.id.buttonC);

        animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.little_car_1), 120);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.little_car_2), 120);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.little_car_3), 120);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.little_car_4), 120);
        animationDrawable.addFrame(getResources().getDrawable(R.mipmap.little_car_5), 120);
        animationDrawable.setOneShot(false);//true则只运行一次，false可以循环

        animationIV.setBackgroundDrawable(animationDrawable);
        animationIV.setOnClickListener(new View.OnClickListener()//按钮点击的时候运行，再次点击停止
        {

            @Override
            public void onClick(View v) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                } else {
                    animationDrawable.start();
                }
            }
        });

        buttonA.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationIV.setImageResource(R.drawable.animation1);
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.start();
            }

        });

        buttonB.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.stop();
            }

        });

        buttonC.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                animationIV.setImageResource(R.drawable.animation2);
                animationDrawable = (AnimationDrawable) animationIV.getDrawable();
                animationDrawable.start();
            }
        });
    }
}