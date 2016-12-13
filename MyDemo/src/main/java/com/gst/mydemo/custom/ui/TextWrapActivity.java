package com.gst.mydemo.custom.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.gst.mydemo.R;
import com.gst.mydemo.custom.widget.XRTextView;
import com.gst.mydemo.ui.TopActivity;

/**
 * Created by 善同 on 2015/11/3.
 * 文本顶格换行
 */
public class TextWrapActivity extends TopActivity {

    private XRTextView tvDefine; // 定制
    private TextView tvGeneral; // 普通

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_text);

        init();
        setView();
    }

    private void init() {
    }

    private void setView() {
        setTopView();
        tvTitle.setText("文本定制");
        tvDefine = (XRTextView) findViewById(R.id.tv_define);
        tvGeneral = (TextView) findViewById(R.id.tv_general);

        String content = getResources().getString(R.string.textContent);
        content = "The author’s purpose for this story is to inform. What is a step the author gives for making applesauce?";
        tvDefine.setText(content);
        tvGeneral.setText(toDBC(content));
    }


    /**
     * 针对TextView显示中文中出现的排版错乱问题，通过调用此方法得以解决
     *
     * @param str
     * @return 返回全部为全角字符的字符串
     */
    public static String toDBC(String str) {
        char[] c = str.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375) {
                c[i] = (char) (c[i] - 65248);
            }

        }
        return new String(c);
    }


}
