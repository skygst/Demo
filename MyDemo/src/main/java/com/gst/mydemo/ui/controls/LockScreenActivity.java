package com.gst.mydemo.ui.controls;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.gst.mydemo.R;
import com.gst.mydemo.custom.widget.GestureLockViewGroup;

/**
 *  锁屏测试
 * Created by 善同 on 2016/2/15.
 */
public class LockScreenActivity extends Activity {

        private GestureLockViewGroup mGestureLockViewGroup;
    private Context mContext;

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.lock_screen);

            mContext = LockScreenActivity.this;
//		int[] mAnswer = { 1, 2, 3, 4,5 };
            String[] wordValue = new String[] {"s", "e", "s", "s", "e", "s", "s", "s", "e"  , "s", "s", "e", "s", "s", "s", "e"};
            int[] mAnswer = new int[] { 1, 5, 9 };
            mGestureLockViewGroup = (GestureLockViewGroup) findViewById(R.id.id_gestureLockViewGroup);
            mGestureLockViewGroup.setAnswer(mAnswer);
            mGestureLockViewGroup.setWord(wordValue);
            mGestureLockViewGroup
                    .setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener()
                    {

                        @Override
                        public void onUnmatchedExceedBoundary()
                        {
                            Toast.makeText(mContext, "错误5次...",
                                    Toast.LENGTH_SHORT).show();
                            mGestureLockViewGroup.setUnMatchExceedBoundary(5);
                        }

                        @Override
                        public void onGestureEvent(boolean matched)
                        {
                            Toast.makeText(mContext, matched+"",
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onBlockSelected(int cId)
                        {
                        }
                    });
        }

    }

