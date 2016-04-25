package com.emple.activity;

import com.emple.calculatorqb.Globe;
import cn.gqb.calculator.R;
import com.emple.calculatorqb.SkinManage;
import com.kyleduo.switchbutton.SwitchButton;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class OtherActivity extends StatusSetActivity {

	private OtherActivity mContext;
	private SQLiteDatabase db;
	private TextView mhead_title;
	private SwitchButton soundSb;
	public static boolean PLAYSOUND=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		mContext=this;
		db = SQLiteDatabase.openOrCreateDatabase(Globe.dbFile.getPath(), null); 
		mhead_title=(TextView) findViewById(R.id.mhead_title);
		mhead_title.setText("∆‰À˚…Ë÷√");
		soundSb=(SwitchButton) findViewById(R.id.other_detail_soundsb);
		soundSb.setChecked(PLAYSOUND);
		soundSb.setOnCheckedChangeListener(new sbCheckChanged());
	}
	
	class sbCheckChanged implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			updateStatus(isChecked);
		}
		
	}

	public void updateStatus(boolean isChecked) {
		// TODO Auto-generated method stub
		ContentValues values=new ContentValues();
		values.put("play_sound", isChecked?1:0);
		db.update("other_sets", values, "id=1", null);
		PLAYSOUND=isChecked;
	}

}
