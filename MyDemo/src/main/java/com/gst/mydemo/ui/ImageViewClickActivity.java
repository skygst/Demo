package com.gst.mydemo.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gst.mydemo.R;

/**
 * Created by 善同 on 2015/11/6.
 */
public class ImageViewClickActivity extends TopActivity implements View.OnClickListener {

    private ImageView ivPic1, ivPic2;
//    private

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_pic);

        init();
        setView();
    }

    private void init() {

    }

    private void setView() {
        ivPic1 = (ImageView) findViewById(R.id.iv_pic1);
        ivPic2 = (ImageView) findViewById(R.id.iv_pic2);

        ivPic1.setOnClickListener(this);
        ivPic2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

    }
}
