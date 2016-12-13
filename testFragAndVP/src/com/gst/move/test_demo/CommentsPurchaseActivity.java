package com.gst.move.test_demo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.server.CommonAdressConstant;
import com.ebodoo.raz.server.CommonInterfaces;
import com.ebodoo.raz.utils.MyToast;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.base.PayMent;
import com.gst.move.base.Profile;
import com.payment.PayResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 *  点评购买 界面
 *
 */
public class CommentsPurchaseActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivYdzd, ivSddp, ivYydp, ivXjpj, iv1th, iv3th, ivSel1, ivSel3;
    private RelativeLayout rl1th, rl3th;
    private TextView tv1Content, tv3Content, tvLeftMoney, tvRightMoney;
    private Button btnBuy;
    private Context mContext;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private boolean isSel1th = true;
    private String productId = "19";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comments_purchase);

        init();
        setView();
    }

    private void init() {
        mContext = CommentsPurchaseActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
    }

    private void setView() {
        ivYdzd = (ImageView) findViewById(R.id.iv_ydzd);
        ivSddp = (ImageView) findViewById(R.id.iv_sddp);
        ivYydp = (ImageView) findViewById(R.id.iv_yydp);
        ivXjpj = (ImageView) findViewById(R.id.iv_xjpj);
        iv1th = (ImageView) findViewById(R.id.iv_1th);
        iv3th = (ImageView) findViewById(R.id.iv_3th);
        ivSel1 = (ImageView) findViewById(R.id.iv_sel_1);
        ivSel3 = (ImageView) findViewById(R.id.iv_sel_3);
        rl1th = (RelativeLayout) findViewById(R.id.rl_1th);
        rl3th = (RelativeLayout) findViewById(R.id.rl_3th);

        tvLeftMoney = (TextView) findViewById(R.id.tv_left_money);
        tvRightMoney = (TextView) findViewById(R.id.tv_right_money);

        tv1Content = (TextView) findViewById(R.id.tv_1_content);
        tv3Content = (TextView) findViewById(R.id.tv_3_content);

        btnBuy = (Button) findViewById(R.id.btn_buy);

        setViewPosition(ivYdzd, 0);
        setViewPosition(ivSddp, 1);
        setViewPosition(ivYydp, 2);
        setViewPosition(ivXjpj, 3);

        setViewPosition(rl1th, 4);
        setViewPosition(rl3th, 5);
        setViewPosition(iv1th, 6);
        setViewPosition(iv3th, 7);

        processSelBg(isSel1th);

        showTextView(tv1Content, "1", getString(R.string.tv_1thContent), 0xff333333);
        showTextView(tv3Content, "3", getString(R.string.tv_3thContent), 0xff999999);

        rl1th.setOnClickListener(this);
        rl3th.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.comments_purchase_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private void showTextView(TextView tvContent, String content1, String content2, int color) {
        tvContent.setText(StringBiz.mosaicString(mContext, content1, content2, color, color));
    }

    @Override
    public void onClick(View view) {
        if(view == rl1th) { // 选择1个月 -- productId 19
            productId = "19";
            processSelBg(true);
        } else if(view == rl3th) { // 选择3个月 -- productId 20
            productId = "20";
            processSelBg(false);
        } else if(view == btnBuy) { // 确认并购买
            // TODO 去购买
            createOrder(productId);
        }
    }

    private void processSelBg(boolean status) {
        isSel1th = status;
        if(isSel1th) {
            showView(R.drawable.corner_box_green_5, R.drawable.corner_box_white_5,
                    View.VISIBLE, View.GONE, 0xff333333, 0xff999999);
        } else {
            showView(R.drawable.corner_box_white_5, R.drawable.corner_box_green_5,
                    View.GONE, View.VISIBLE, 0xff999999, 0xff333333);
        }
    }

    private void showView(int resId1, int resId2, int status1, int status2, int colcr1, int color2) {
        rl1th.setBackgroundResource(resId1);
        rl3th.setBackgroundResource(resId2);
        ivSel1.setVisibility(status1);
        ivSel3.setVisibility(status2);
        showTextView(tv1Content, "1", getString(R.string.tv_1thContent), colcr1);
        showTextView(tv3Content, "3", getString(R.string.tv_3thContent), color2);
    }

    private PayMent payment;

    private void createOrder(final String productId) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                payment = new PayMent().paymentCreateOrder(mContext, productId, "1", "");
                handler.sendMessage(handler.obtainMessage(0));
            }
        }).start();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    if(payment != null) {
                        String msgValue = payment.getMsg();
                        if(StringBiz.isEmpty(msgValue)) {
                            toPayment();
                        } else {
                            // TODO 显示错误提示
                            // 跳转到登陆界面
                            String errCode = payment.getErrCode();
                            if(!StringBiz.isEmpty(errCode) && errCode.equals("401")) { // 去登录
//                                new DialogBiz().gotoLogin(mContext);
                            } else {
                                new MyToast().showTextToast(mContext, msgValue);
                            }
                        }
                    }
                    break;

                default:
                    break;
            }
        };
    };

    // 去支付
    private void toPayment() {
        // String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");
        String orderInfo = new PayMent().getOrderInfo(payment.getPartner(),
                payment.getSeller_email(), payment.getOut_trade_no(),
                payment.getNotify_url(), payment.getIt_b_pay(),
                payment.getSubject(), payment.getBody(), payment.getPrice());

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = new PayMent().sign(orderInfo, payment.getPrivate_key());
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + new PayMent().getSignType();
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(CommentsPurchaseActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);
                mHandler.sendMessage(mHandler.obtainMessage(0, result));
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
// 				System.out.println("payResult.getResult() :" + payResult.getResult());

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(mContext, "支付成功",
                                Toast.LENGTH_SHORT).show();

                        getProfile();
                        // 统计
// 					if(!StringBiz.isEmpty(label)) {
// 						StatisticsBiz.statisticsPayment(mContext, label);
// 						Statistics.talkingDataAppCpaCount(8);
// 					}
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(mContext, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(mContext, "支付失败",
                                    Toast.LENGTH_SHORT).show();
                        }
                        setResult(1, new Intent());
                        finish();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };


    // TDOD ===================================== 测试 ===================================
    /*----------获取等级和金币-----------*/
    public static String getRazProfile(Context mContext){
        String result = null;
        result = new CommonInterfaces().getDataFromUrlByGet(mContext, CommonAdressConstant.raz_profile,"");

        return result;
    }

    private void getProfile(){
        new AsyncTask<Object, Object, Profile>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Profile doInBackground(Object... params) {
                String resultjson;
//				mArticleCommentAll.mArticleCommentList.clear();
//                level = new BaseCommon().getSelectLevel(mContext);
                //新增新的随机闯关的岛的level
//                island_level = CommonSharePreferences.getIslandAllLevel(mContext);

                resultjson = getRazProfile(mContext);
                if(resultjson == null || resultjson.contains("error_code"))return null;

                Profile mProfile = new Profile();
                mProfile = getProfile(resultjson);
                return mProfile;
            }

            @Override
            protected void onPostExecute(Profile result) {
                super.onPostExecute(result);


                setResult(1, new Intent());
                finish();
            }

        }.execute();
    }


    /*-------------获得level 和 金币-----------------*/
    public Profile getProfile(String result){
        Profile mProfile = new Profile();
        try {
            if(result == null || result.contains("errCode"))
                return null;
            JSONObject resultJson = new JSONObject(result);
            mProfile.coin = resultJson.optInt("coin");
            mProfile.level = resultJson.optInt("level");
            mProfile.island_level = resultJson.optInt("island_level");
            mProfile.updated_at = resultJson.optString("updated_at");
            String levelV2 = resultJson.optString("level_v2");
            mProfile.level_v2 = levelV2;

            System.out.println("------profile---result-----------" + result);

            String audioReviewExpire = resultJson.optString("audio_review_expire");
            if(!StringBiz.isEmpty(audioReviewExpire)) {
                // TODO
                String audioReviewTimes = resultJson.optString("audio_review_times");
                if(!StringBiz.isEmpty(audioReviewTimes)) {

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mProfile;

    }

}
