package com.testfragandvp.fragment;

import android.support.v4.app.Fragment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class FragmentNull extends Fragment implements OnTouchListener {

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	}

}
