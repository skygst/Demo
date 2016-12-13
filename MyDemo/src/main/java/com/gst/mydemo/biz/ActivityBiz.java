package com.gst.mydemo.biz;

import android.content.Context;
import android.content.Intent;

/**
 * Created by 善同 on 2016/1/14.
 */
public class ActivityBiz {

    public static void gotoActivity(Context context, Class<?> classes) {
        context.startActivity(new Intent(context, classes));
    }
}
