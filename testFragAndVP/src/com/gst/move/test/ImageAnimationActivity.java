package com.gst.move.test;

import android.os.Bundle;
import android.widget.ImageView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.utils.CommonAnimation;
import com.gst.move.R;
import com.gst.move.R.drawable;
import com.gst.move.R.id;
import com.gst.move.R.layout;

public class ImageAnimationActivity extends BaseActivity {

	private ImageView ivImg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.img_animation);
		
		setView();
	}

	private void setView() {
		ivImg = (ImageView) findViewById(R.id.iv_img);
		ivImg.setImageResource(R.drawable.buy_close);
		CommonAnimation.startShouAnimation22(ivImg);
	}
	
}
