package com.gst.move.home_work;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.ebodoo.raz.BaseActivity;
import com.ebodoo.raz.biz.VideoBiz;
import com.ebodoo.raz.custom.ClickImageView;
import com.ebodoo.raz.custom.MyGridView;
import com.ebodoo.raz.data.FixedPosition;
import com.ebodoo.raz.utils.MyToast;
import com.example.location.biz.StringBiz;
import com.gst.move.R;
import com.gst.move.adapter.ShopAdapter;

/**
 * 商店
 */
public class ShopActivity extends BaseActivity implements View.OnClickListener {

    private ScrollView slView;
    private ImageView ivBg;
    private MyGridView myGridView;
    private RelativeLayout rlExchange, rlInputPwd, rlSucess;
    private ImageView ivExchangeBg, ivSuccessBg, ivMaterialObject;
    private ClickImageView ivClose, ivExchangeClose, ivExchangeConfirm, ivSuccessConfirm;
    private EditText etInputPwd;
    private ShopAdapter adapter;
    private VideoBiz biz;
    private float scaleQPW = 1.0f, scaleQPH = 1.0f;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop);

        init();
        setView();
    }

    private void init() {
        mContext = ShopActivity.this;
        scaleQPW = (width / 1280.0f);
        scaleQPH = (height / 720.0f);
        biz = new VideoBiz();
    }

    private void setView() {
        ivBg = (ImageView) findViewById(R.id.iv_bg);
        ivClose = (ClickImageView) findViewById(R.id.iv_close);
        slView = (ScrollView) findViewById(R.id.sl_view);
        myGridView = (MyGridView) findViewById(R.id.myGridView);
        adapter = new ShopAdapter(mContext, width, -1, scaleQPW, scaleQPH);
        myGridView.setAdapter(adapter);

        rlExchange = (RelativeLayout) findViewById(R.id.rl_exchange);
        rlInputPwd = (RelativeLayout) findViewById(R.id.rl_input_password);
        ivExchangeBg = (ImageView) findViewById(R.id.iv_exchange_bg);
        ivExchangeClose = (ClickImageView) findViewById(R.id.iv_exchange_close);
        ivExchangeConfirm = (ClickImageView) findViewById(R.id.iv_exchange_confirm);
        etInputPwd = (EditText) findViewById(R.id.et_password);

        rlSucess = (RelativeLayout) findViewById(R.id.rl_success);
        ivSuccessBg = (ImageView) findViewById(R.id.iv_success_bg);
        ivMaterialObject = (ImageView) findViewById(R.id.iv_success_material_object);
        ivSuccessConfirm = (ClickImageView) findViewById(R.id.iv_success_confirm);

        setViewPosition(ivBg, 0);
        setViewPosition(ivClose, 1);
        setViewPosition(slView, 2);
        setViewPosition(ivExchangeBg, 3);
        setViewPosition(ivExchangeClose, 4);
        setViewPosition(rlInputPwd, 5);
        setViewPosition(ivExchangeConfirm, 6);
        setViewPosition(ivSuccessBg, 7);
        setViewPosition(ivMaterialObject, 8);
        setViewPosition(ivSuccessConfirm, 9);


        onItemClick();
        ivClose.setOnClickListener(this);
        ivExchangeClose.setOnClickListener(this);
        ivExchangeConfirm.setOnClickListener(this);
        ivSuccessConfirm.setOnClickListener(this);
    }

    private void setViewPosition(View iv, int i) {
        biz.setViewPosition(iv, i, FixedPosition.shop_position, scaleQPW,
                scaleQPH, 0, 0, 1.0f);
    }

    private void onItemClick() {
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,
                                    long arg3) {
                adapter.setIndex(position);
                adapter.notifyDataSetChanged();
                // TODO 弹出框
                slView.setEnabled(false);
                myGridView.setEnabled(false);
                ivClose.setEnabled(false);
                rlExchange.setVisibility(View.VISIBLE);
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (v == ivClose) {
            finish();
        } else if(v == ivExchangeClose) { // 兑换弹框关闭
            etInputPwd.setText("");
            slView.setEnabled(true);
            myGridView.setEnabled(true);
            ivClose.setEnabled(true);
            rlExchange.setVisibility(View.GONE);
        } else if(v == ivExchangeConfirm) { // 兑换弹框 确认
            // TODO 提交兑换请求
            String pwd = etInputPwd.getText().toString();
            if(!StringBiz.isEmpty(pwd)) {
                threadSubmitExchangeRequest();
            } else {
                new MyToast().showTextToast(mContext, "密码不能为空哦");
            }
        } else if(v == ivSuccessConfirm) { // 兑换成功界面
            slView.setEnabled(true);
            myGridView.setEnabled(true);
            ivClose.setEnabled(true);
            rlSucess.setVisibility(View.GONE);
        }
    }

    // 提交兑换请求
    private void threadSubmitExchangeRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    handler.sendMessage(handler.obtainMessage(0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    System.out.println("--------case 0---------------");
                    etInputPwd.setText("");
                    rlExchange.setVisibility(View.GONE);
                    rlSucess.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

}
