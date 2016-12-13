package com.gst.move.basic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ebodoo.raz.BaseActivity;
import com.gst.move.R;

public class SelLevelActivity extends BaseActivity implements OnClickListener {

	private Context mContext;
	private Button btnLevel1, btnLevel2, btnLevel3, btnLevel4, btnLevel5, btnLevel6, btnLevel7, btnLevel8, btnLevel9,
	btnLevel10, btnLevel11, btnLevel12, btnLevel13, btnLevel14, btnLevel15;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sel_level);
		
		init();
		setView();
	}

	private void init() {
		mContext = SelLevelActivity.this;
	}
	
	private void setView() {
		btnLevel1 = (Button) findViewById(R.id.btn_level_1);
		btnLevel2 = (Button) findViewById(R.id.btn_level_2);
		btnLevel3 = (Button) findViewById(R.id.btn_level_3);
		btnLevel4 = (Button) findViewById(R.id.btn_level_4);
		btnLevel5 = (Button) findViewById(R.id.btn_level_5);
		btnLevel6 = (Button) findViewById(R.id.btn_level_6);
		btnLevel7 = (Button) findViewById(R.id.btn_level_7);
		btnLevel8 = (Button) findViewById(R.id.btn_level_8);
		btnLevel9 = (Button) findViewById(R.id.btn_level_9);
		btnLevel10 = (Button) findViewById(R.id.btn_level_10);
		btnLevel11 = (Button) findViewById(R.id.btn_level_11);
		btnLevel12 = (Button) findViewById(R.id.btn_level_12);
		btnLevel13 = (Button) findViewById(R.id.btn_level_13);
		btnLevel14 = (Button) findViewById(R.id.btn_level_14);
		btnLevel15 = (Button) findViewById(R.id.btn_level_15);
		
		btnLevel1.setOnClickListener(this);
		btnLevel2.setOnClickListener(this);
		btnLevel3.setOnClickListener(this);
		btnLevel4.setOnClickListener(this);
		btnLevel5.setOnClickListener(this);
		btnLevel6.setOnClickListener(this);
		btnLevel7.setOnClickListener(this);
		btnLevel8.setOnClickListener(this);
		btnLevel9.setOnClickListener(this);
		btnLevel10.setOnClickListener(this);
		btnLevel11.setOnClickListener(this);
		btnLevel12.setOnClickListener(this);
		btnLevel13.setOnClickListener(this);
		btnLevel14.setOnClickListener(this);
		btnLevel15.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v == btnLevel1) {
			gotoActivity(1, 1);
		} else if(v == btnLevel2) {
			gotoActivity(2, 1);
		} else if(v == btnLevel3) {
			gotoActivity(3, 1);
		} else if(v == btnLevel4) {
			gotoActivity(4, 2);
		} else if(v == btnLevel5) {
			gotoActivity(5, 2);
		} else if(v == btnLevel6) {
			gotoActivity(6, 2);
		} else if(v == btnLevel7) {
			gotoActivity(7, 3);
		} else if(v == btnLevel8) {
			gotoActivity(8, 3);
		} else if(v == btnLevel9) {
			gotoActivity(9, 3);
		} else if(v == btnLevel10) {
			gotoActivity(10, 3);
		} else if(v == btnLevel11) {
			gotoActivity(11, 4);
		} else if(v == btnLevel12) {
			gotoActivity(12, 4);
		} else if(v == btnLevel13) {
			gotoActivity(13, 4);
		} else if(v == btnLevel14) {
			gotoActivity(14, 4);
		} else if(v == btnLevel15) {
			gotoActivity(15, 4);
		}
	}
	
	private void gotoActivity(int level, int island) {
		startActivity(new Intent(mContext, LevelGameActivity.class).putExtra(
				"level", level).putExtra("island", island));
	}

	
}
