package com.gst.mydemo.ui.controls;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gst.mydemo.R;
import com.gst.mydemo.biz.DialogBiz;
import com.gst.mydemo.ui.TopActivity;
import com.gst.mydemo.utils.BaseCommonUtils;
import com.gst.mydemo.utils.PhotoUtil;
import com.gst.mydemo.utils.UserPhoto;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.io.File;

/**
 *  ImageView的使用
 *  1. 获得相册图片和拍照图片(裁剪 或者 不裁剪)
 *
 * Created by 善同 on 2016/1/14.
 */
public class ImageViewActivity extends TopActivity implements View.OnClickListener {

    private Button btnGalleryPic;
    private ImageView ivGalleryPic;
    private Context mContext;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);

        init();
        setView();
    }

    private void init() {
        mContext = ImageViewActivity.this;
        mImageLoader = ImageLoader.getInstance();
    }

    private void setView() {
        btnGalleryPic = (Button) findViewById(R.id.btn_gallery);
        ivGalleryPic = (ImageView) findViewById(R.id.iv_gallery_pic);

        btnGalleryPic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == btnGalleryPic) { // 获取相册图片
            DialogBiz.showGalleryDialog(mContext);
        } /*else if(v == btnListView) { // ListView部分功能

        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("tmpPhotoFilePath :" + picPth);
        if(picPth != null) {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .resetViewBeforeLoading(true)
                    .cacheOnDisk(true)
                    .cacheInMemory(true)
                    .imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .considerExifParams(true)
                    .displayer(new SimpleBitmapDisplayer()).build();
            if (picPth.contains("content://media")) {
                mImageLoader.displayImage(picPth, ivGalleryPic, options);
            } else {
                mImageLoader.displayImage("file://" + picPth + "?" + new BaseCommonUtils().randomNumber(), ivGalleryPic, options);
            }
        }
    }

    private String picPth;
    private File mCurrentPhotoFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case PhotoUtil.PHOTO_PICKED_WITH_DATA: {
                picPth = UserPhoto.doPickedPhoto(data);
                break;
            }
            case PhotoUtil.CAMERA_WITH_DATA: {
                mCurrentPhotoFile = new File(Environment
                        .getExternalStorageDirectory().getAbsolutePath()
                        + java.io.File.separator
                        + "bodoo"
                        + java.io.File.separator
                        + "zhaopian.jpg");
                System.out.println("mCurrentPhotoFile.toString() :" + mCurrentPhotoFile.toString());
                // 不裁剪
                picPth = mCurrentPhotoFile.toString();
                // 裁剪
//			UserPhoto.startPhotoCrop((Activity)mContext,
//					Uri.fromFile(mCurrentPhotoFile));
                break;
            }
            case PhotoUtil.PHOTOZOOM:
                if (data == null)
                    return;
                Uri image_Uri = data.getData();
                if (image_Uri != null) { // 个别手机返回的是图片的路径
                    String str_uri = image_Uri.toString();
                    System.out.println("str_uri :" + str_uri);
                    // 不裁剪
                    picPth = str_uri;
                    // 裁剪
//				if (str_uri.contains("content://media")) {
//					String m_strImgUri_01 = getRealPathFromURI(
//							mContext, image_Uri);
//
//					String m_strImgUri_02 = m_strImgUri_01.replace("file://",
//							"");
//					System.out.println("----------------------------------------");
//					File file = new File(m_strImgUri_02);
//					UserPhoto.startPhotoCrop(this, Uri.fromFile(file));
//				} else {
//					System.out.println("+++++++++++++++++++++++++");
//					String m_strImgUri_02 = str_uri.replace("file://", "");
//
//					File file = new File(m_strImgUri_02);
//					UserPhoto.startPhotoCrop(this, Uri.fromFile(file));
//				}
                } else { // 大部分手机返回时bitmap
                    if (data != null) {
                        picPth = UserPhoto.doPickedPhoto(data);
                    }
                }
                break;

            case 3:
                if (data != null) {
                    picPth = UserPhoto.doPickedPhoto(data);
                }
                break;
        }
    }
}
