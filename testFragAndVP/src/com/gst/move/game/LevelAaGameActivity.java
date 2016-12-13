package com.gst.move.game;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ebodoo.raz.BaseActivity;
import com.gst.move.R;

/**
 * 
 * @author
 * 
 */
public class LevelAaGameActivity extends BaseActivity implements
		OnClickListener {

	private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
			btn11, btn12, btn13, btn14, btn15, btn16, btn17, btn18, btn19, btn20, btn21,
			btn22, btn23;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aa_0);

		init();
		setView();
	}

	private void init() {
		mContext = LevelAaGameActivity.this;

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
		btn20 = (Button) findViewById(R.id.btn_20);
		btn21 = (Button) findViewById(R.id.btn_21);
		btn22 = (Button) findViewById(R.id.btn_22);
		btn23 = (Button) findViewById(R.id.btn_23);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
		btn6.setOnClickListener(this);
		btn7.setOnClickListener(this);
		btn8.setOnClickListener(this);
		btn9.setOnClickListener(this);
		btn10.setOnClickListener(this);
		btn11.setOnClickListener(this);
		btn12.setOnClickListener(this);
		btn13.setOnClickListener(this);
		btn14.setOnClickListener(this);
		btn15.setOnClickListener(this);
		btn16.setOnClickListener(this);
		btn17.setOnClickListener(this);
		btn18.setOnClickListener(this);
		btn19.setOnClickListener(this);
		btn20.setOnClickListener(this);
		btn21.setOnClickListener(this);
		btn22.setOnClickListener(this);
		btn23 .setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btn1) { // go 1  Where is the cow？
			gotoActivity(LevelAaGame1Activity.class, 0);
		} else if (v == btn2) { // go 2  Where is the dog？
			gotoActivity(LevelAaGame1Activity.class, 1);
		} else if (v == btn3) { // little 1  where is the bug？
			gotoActivity(LevelAaGame2Activity.class, 0);
		} else if (v == btn4) { // little 2  Can you find the little dog?
			gotoActivity(LevelAaGame2Activity.class, 1);
		} else if (v == btn5) { // summer 1  I feel very hot in summer!    How do you feel in summer?
			gotoActivity(LevelAaGame2Activity.class, 2);
		} else if (v == btn6) { //big 1  which one is big？ Can you find the big car?
			gotoActivity(LevelAaGame1Activity.class, 2);
		} else if (v == btn7) { // winter  what is it？/ It is a carrot.
			gotoActivity(LevelAaGame3Activity.class);
		} else if (v == btn8) { // winter  I feel very cold in winter!   How do you feel in winter?
			gotoActivity(LevelAaGame2Activity.class, 3);
		} else if (v == btn9) { //  supermarket  which one is not a vegetable？
			gotoActivity(LevelAaGame1Activity.class, 3);
		} else if (v == btn10) { // supermarket  How many eggs do you see?
			gotoActivity(LevelAaGame4Activity.class);
		} else if (v == btn11) { // ocean  what color is the seaweed? Yes，the color of seaweed is green
			gotoActivity(LevelAaGame5Activity.class, 0);
		} else if (v == btn12) { // garden What can't you grow in the garden?
			gotoActivity(LevelAaGame1Activity.class, 4);
		} else if (v == btn13) { //  garden  Which one is a melon?
			gotoActivity(LevelAaGame2Activity.class, 4);
		} else if (v == btn14) { // colorful eggs  Which is the green egg?/  (This) is a green egg.
			gotoActivity(LevelAaGame1Activity.class, 5);
		} else if (v == btn15) { // fall what color is the pumpkin?
			gotoActivity(LevelAaGame5Activity.class, 1);
		} else if (v == btn16) { // spring  The bunny can hop，which animal below can hop too？
			gotoActivity(LevelAaGame1Activity.class, 6);
		} else if (v == btn17) { // coast  what is the color of the ocean
			gotoActivity(LevelAaGame6Activity.class);
		} else if (v == btn18) { // trip  open the suitcase,close the suitcase
			gotoActivity(LevelAaGame7Activity.class);
		} else if (v == btn19) { // trip  find the word begins with a D sound
			gotoActivity(LevelAaGame1Activity.class, 7);
		} else if (v == btn20) { // bulid  Diy time! paint your own house.
			gotoActivity(LevelAaGame8Activity.class);
		} else if (v == btn21) { // in Which fish is in the fish tank?
			gotoActivity(LevelAaGame1Activity.class, 8);
		} else if (v == btn22) { // in  Do you see my sister? She is in the room.
			gotoActivity(LevelAaGame1Activity.class, 9);
		} else if (v == btn23) { // out  Can you find Lailai? It's out of the basket.
			gotoActivity(LevelAaGame1Activity.class, 10);
		}
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				finish();
				break;
			default:
				break;
			}
		}

	};
	
	private void gotoActivity(Class<?> classes, int index) {
		if(index != -1) {
			startActivity(new Intent(mContext, classes).putExtra("index", index));
		} else {
			startActivity(new Intent(mContext, classes));
		}
	}
	
	private void gotoActivity(Class<?> classes) {
		startActivity(new Intent(mContext, classes));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
