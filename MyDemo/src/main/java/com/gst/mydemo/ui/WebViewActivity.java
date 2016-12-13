package com.gst.mydemo.ui;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.gst.mydemo.R;

/**
 * Created by 善同 on 2015/11/3.
 */
public class WebViewActivity extends TopActivity {

    private WebView mWebView;
    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);

        init();
        setView();
    }

    private void init() {
    }

    private void setView() {
        setTopView();
        tvContent = (TextView) findViewById(R.id.tv_content);
        mWebView = (WebView) findViewById(R.id.webview);
        String content = "<p>&nbsp;&nbsp;&nbsp;&nbsp;原料：鳕鱼一小块、鸡蛋一只、香菇一朵、洋葱一小瓣、面粉适量<br /> <br />&nbsp; &nbsp;&nbsp; &nbsp;做法：<br />1、鳕鱼洗净蒸熟，捻碎；<br />2、洋葱和香菇洗净，切碎；<br />3、鸡蛋打碎，去蛋黄打散，加面粉和清水搅匀调成面糊，加入鳕鱼碎、香菇碎和洋葱碎，搅匀；<br />4、平底锅烧热，舀入一大勺面糊，摊成圆饼即可。<br /> <br />营养提示：<br />&nbsp; &nbsp;&nbsp; &nbsp;鳕鱼鱼脂中含有球蛋白、白蛋白及磷的核蛋白，还含有儿童发育所必需的各种氨基酸，其比值和儿童的需要量非常相近，又容易被人消化吸收，还含有不饱和脂肪酸和钙、磷、铁、B族维生素等。 </p>";
        mWebView.loadDataWithBaseURL("fake://not/needed", content,
                "text/html", "utf-8", "");

        tvContent.setText(content);
    }
}
