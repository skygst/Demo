package com.gst.move.level_a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ebodoo.raz.BaseActivity;
import com.gst.move.R;

public class LevelAMainActivity extends BaseActivity {
	
	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, 
			btn9, btn10, btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19;
	private Context mContext;
	private Button[] btn;
	private Class<?>[] classes = new Class<?>[] {
			LevelABirdGoesHomeActivity.class, LevelAHamsterHome1Activity.class, LevelAHamsterHome2Activity.class,
			LevelAHeRuns2Activity.class, LevelAMyDog2Activity.class, LevelAMyHair1Activity.class,
			LevelAMyRoom2Activity.class, LevelAPondAnimals2Activity.class, LevelATheseShoes1Activity.class,
			LevelATheseShoes2Activity.class, LevelAWhatILike1Activity.class, LevelAMyColors1Activity.class,
			LevelABaaSheep1Activity.class, LevelAToSchool1Activity.class, LevelAHumptyDumpty2Activity.class,
			LevelALittleHen1Activity.class, LevelALittleHen2Activity.class, LevelAInAndOut2Activity.class,
			LevelABigCat1Activity.class
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_a_main);
		
		init();
		setView();
	}

	private void init() {
		mContext = LevelAMainActivity.this;
	}

	private void setView() {
		btn1 = (Button) findViewById(R.id.btn_1);
		btn2 = (Button) findViewById(R.id.btn_2);
		btn3 = (Button) findViewById(R.id.btn_3);
		btn4 = (Button) findViewById(R.id.btn_4);
		btn5 = (Button) findViewById(R.id.btn_5);
		btn6 = (Button) findViewById(R.id.btn_6);
		btn7 = (Button) findViewById(R.id.btn_7);
		btn8 = (Button) findViewById(R.id.btn_8);
		btn9 = (Button) findViewById(R.id.btn_9);
		btn10 = (Button) findViewById(R.id.btn_10);
		btn11 = (Button) findViewById(R.id.btn_11);
		btn12 = (Button) findViewById(R.id.btn_12);
		btn13 = (Button) findViewById(R.id.btn_13);
		btn14 = (Button) findViewById(R.id.btn_14);
		btn15 = (Button) findViewById(R.id.btn_15);
		btn16 = (Button) findViewById(R.id.btn_16);
		btn17 = (Button) findViewById(R.id.btn_17);
		btn18 = (Button) findViewById(R.id.btn_18);
		btn19 = (Button) findViewById(R.id.btn_19);
		
		processBtn();
	}
	
	private void processBtn() {
		btn = new Button[19];
		btn[0] = btn1;
		btn[1] = btn2;
		btn[2] = btn3;
		btn[3] = btn4;
		btn[4] = btn5;
		btn[5] = btn6;
		btn[6] = btn7;
		btn[7] = btn8;
		btn[8] = btn9;
		btn[9] = btn10;
		btn[10] = btn11;
		btn[11] = btn12;
		btn[12] = btn13;
		btn[13] = btn14;
		btn[14] = btn15;
		btn[15] = btn16;
		btn[16] = btn17;
		btn[17] = btn18;
		btn[18] = btn19;
		
		for(int i=0; i<btn.length; i++) {
			final int index = i;
			btn[i].setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					gotoActivity(classes[index]);
				}
			});
		}
	}
	
	private void gotoActivity(Class<?> classes) {
		startActivity(new Intent(mContext, classes));
	}
	
}
