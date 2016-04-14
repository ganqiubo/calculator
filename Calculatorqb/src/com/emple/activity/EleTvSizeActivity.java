package com.emple.activity;

import com.emple.calculatorqb.R;
import com.emple.calculatorqb.R.layout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

public class EleTvSizeActivity extends StatusSetActivity {

	private TextView mhead_title;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ele_tv_size);
		
		init();
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		mhead_title=(TextView) findViewById(R.id.mhead_title);
		mhead_title.setText("×ÖÌå´óÐ¡");
	}

}
