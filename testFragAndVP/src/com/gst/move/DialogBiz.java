package com.gst.move;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.utils.LayoutParameters;

/**
 * Created by gst-pc on 2016/10/26.
 */

public class DialogBiz {

    // 大声说 购买
    public void showDefineSpeakLoudlyDialog(final Context context, float scaleQPW, float scaleQPH) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        // *** 主要就是在这里实现这种效果的.
        window.setContentView(R.layout.define_comments_to_buy_dialog);
        RelativeLayout rlBg = (RelativeLayout) window.findViewById(R.id.rl_bg);
        setViewPosition(rlBg, 697, 583, true, false, 0, scaleQPW, scaleQPH);

        RelativeLayout rlOnce = (RelativeLayout) window.findViewById(R.id.rl_once);
        RelativeLayout rlAll = (RelativeLayout) window.findViewById(R.id.rl_all);

        ClickImageView ivLeft = (ClickImageView) window.findViewById(R.id.iv_left);
        ClickImageView ivRight = (ClickImageView) window.findViewById(R.id.iv_right);

//        setViewBelowPosition(ivLeft, 230, 70, R.id.tv_plan_name, true, R.id.view_below, 30, scaleQPW, scaleQPH);
//        setViewBelowPosition(ivRight, 230, 70, R.id.tv_plan_name, false, R.id.view_below, 30, scaleQPW, scaleQPH);
//
        ivLeft.setOnClickListener(new View.OnClickListener() { // 取消
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ivRight.setOnClickListener(new View.OnClickListener() { // 去购买
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                ((Activity) context).startActivity(new Intent(context, CommentsIntroductionActivity.class));
            }
        });
        dialog.setCanceledOnTouchOutside(true);
    }

    private void setViewPosition(View iv, int width, int height,
                                 boolean isCenter, boolean isLeft, int viewId, float scaleQPW, float scaleQPH) {
        RelativeLayout.LayoutParams params = LayoutParameters
                .setViewBuyDialogParams(width, height, scaleQPW, scaleQPH, isCenter, isLeft, viewId);
        iv.setLayoutParams(params);
    }

    private void setViewBelowPosition(View iv, int width, int height, int belowId, boolean isLeft, int id, int value, float scaleQPW, float scaleQPH) {
        RelativeLayout.LayoutParams params = LayoutParameters
                .setViewBuyDialogParams5(width, height, scaleQPW, scaleQPH, belowId, isLeft, id, value);
        iv.setLayoutParams(params);
    }

}
