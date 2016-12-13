package com.gst.mydemo.custom.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.gst.mydemo.R;
import com.gst.mydemo.custom.widget.RoundImageView;
import com.gst.mydemo.linstener.ShowBigPicListener;
import com.gst.mydemo.ui.TopActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * Created by 善同 on 2015/11/3.
 */
public class PicShowActivity extends TopActivity {
    
    private RoundImageView pic1, pic2;
    private ImageView ivPic4;
//    private DefineRoundImageView pic3;
    private ImageView ivPic3;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_show);

        init();
        setView();
    }

    private void init() {
        mImageLoader = ImageLoader.getInstance();
    }


//    String imageUri = "http://site.com/image.png"; // from Web
//    String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
//    String imageUri = "content://media/external/audio/albumart/13"; // from content provider
//    String imageUri = "assets://image.png"; // from assets
//    String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
    private void setView() {
        pic1 = (RoundImageView) findViewById(R.id.iv_pic_1);
        pic2 = (RoundImageView) findViewById(R.id.iv_pic_2);
//        pic3 = (DefineRoundImageView) findViewById(R.id.iv_pic_3);
        ivPic3 = (ImageView) findViewById(R.id.iv_pic_0);
        String url1 = "http://api.bbpapp.com/upload/bodoo/system/story/pic/2012-05-03-13-35-49-1336023349.jpg";
        String url2 = "http://story.bbpapp.com/story/pic/2012-04-28-16-07-13-1335600433.jpg";
        String url3 = "http://api.bbpapp.com/upload/bodoo/system/story/pic/2012-05-03-13-35-49-1336023349.jpg";
//        showPic(url3);

        mImageLoader.displayImage(url1, pic1);
        mImageLoader.displayImage(url2, pic2);
        String url0 = "http://img.bbpapp.com/uploads/things/2015_10_23/5373399afb02826c01fd395be4a96947.jpg";
//        mImageLoader.displayImage(url0, ivPic3, new ShowBigPicListener(screenWidth()));


        ivPic4 = (ImageView) findViewById(R.id.iv_pic_4);
        String url = "http://img.bbpapp.com/uploads/advertisement/2016_01_08/f29a4d26830058fe5b6bfdbbe2705ad7.jpg";
        showPic(url);
    }

    private void showPic(String picUrl) {
        try {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true).cacheInMemory(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .displayer(new SimpleBitmapDisplayer()).build();
            mImageLoader.displayImage(picUrl, ivPic4, options, new ShowBigPicListener(screenWidth()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
