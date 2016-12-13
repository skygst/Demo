package com.gst.move.level_c_game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.BaseCommon;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.custom.MyTextView;

/**
 *  勋章详情 界面
 */
public class MedalDetailActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout rlLayout;
    private ImageView ivBg, ivClose, ivLine, ivSparkling, ivMedal, ivMedalSignificance;
    private TextView tvMedalSignificance;
    private MyTextView tvDes;
    private Context mContext;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private String picName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medal_detail);

        init();
        setView();
    }

    private void init() {
        mContext = MedalDetailActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
        picName = "medal_big_"+0+"_" + 0;
//        picName = getIntent().getExtras().getString("picName");
    }

    private void setView() {
        rlLayout = (RelativeLayout) findViewById(R.id.rl_layout);
        ivBg = (ImageView) findViewById(R.id.iv_bg);
        ivClose = (ImageView) findViewById(R.id.iv_close);
        ivLine = (ImageView) findViewById(R.id.iv_line);
        ivSparkling = (ImageView) findViewById(R.id.iv_sparkling);
        ivMedal = (ImageView) findViewById(R.id.iv_medal);
        ivMedalSignificance = (ImageView) findViewById(R.id.iv_medal_significance);

        tvDes = (MyTextView) findViewById(R.id.tv_des);
        tvMedalSignificance = (TextView) findViewById(R.id.tv_medal_significance);

        setViewPosition(ivBg, 0);
        setViewPosition(ivClose, 1);
        setViewPosition(ivLine, 2);
        setViewPosition(ivSparkling, 3);
        setViewPosition(ivMedal, 4);
        setViewPosition(ivMedalSignificance, 5);

        StringBiz.setTextPaint(tvDes);

        if(!StringBiz.isEmpty(picName)) {
            int resId = new BaseCommon().getImageId(mContext, picName);
            if(resId != 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
//                ivMedal.setImageResource(resId);
                ivMedal.setImageBitmap(grey(bitmap));
            }
        }
        rlLayout.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        ivBg.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.medal_detail_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    @Override
    public void onClick(View v) {
        if(v == rlLayout || v == ivClose) { // 关闭
            finish();
        }
    }

    public static final Bitmap grey(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap faceIconGreyBitmap = Bitmap
                .createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(faceIconGreyBitmap);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
                colorMatrix);
        paint.setColorFilter(colorMatrixFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return faceIconGreyBitmap;
    }
}
