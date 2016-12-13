package com.gst.mydemo.ui.controls;

import android.os.Bundle;
import android.view.View;

import com.gst.mydemo.R;
import com.gst.mydemo.ui.BaseActivity;

/**
 * Created by 善同 on 2016/2/15.
 */
public class ButtonFunctionActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.btn_function);
        
        init();
        setView();
    }

    private void init() {
    }

    private void setView() {

    }

    @Override
    public void onClick(View view) {
        
    }
}
