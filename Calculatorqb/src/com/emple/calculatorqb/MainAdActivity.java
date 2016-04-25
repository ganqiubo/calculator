package com.emple.calculatorqb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;
import cn.gqb.calculator.R;

public class MainAdActivity extends Activity {

	private ImageView ad_iv1; 
	private Button ad_bt1;
	private Context ct;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_ad);
		
		ad_iv1=(ImageView) findViewById(R.id.ad_iv1);
		ad_bt1=(Button) findViewById(R.id.ad_bt1);
		ct=this;
		new Thread(new Runnable() {
			public void run() {
				try {
					for (int i = 5; i >= 0; i--) {
						Thread.sleep(1000);
						handler.sendEmptyMessage(i);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
	}

	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			ad_bt1.setText(""+msg.what);
			if (msg.what==0) {
				Intent intent=new Intent(ct, MainActivity.class);
				startActivity(intent);
				finish();
			}
		}
		
		
		
	};
	
}
