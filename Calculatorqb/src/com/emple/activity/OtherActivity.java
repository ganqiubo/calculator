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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class OtherActivity extends StatusSetActivity {

	private OtherActivity mContext;
	private SQLiteDatabase db;
	private TextView mhead_title,tv_demic,tv_note_demic;
	private SwitchButton soundSb;
	public static boolean PLAYSOUND=false;
	private ImageButton add_demic,sub_demic;

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
		mhead_title.setText("其他设置");
		soundSb=(SwitchButton) findViewById(R.id.other_detail_soundsb);
		soundSb.setChecked(PLAYSOUND);
		soundSb.setOnCheckedChangeListener(new sbCheckChanged());
		
		tv_demic=(TextView) findViewById(R.id.tvsize_item_size);
		tv_note_demic=(TextView) findViewById(R.id.tvsize_item_note);
		add_demic=(ImageButton) findViewById(R.id.tvsize_item_add);
		sub_demic=(ImageButton) findViewById(R.id.tvsize_item_sub);
		tv_note_demic.setText("保留小数位");tv_demic.setText(""+Globe.demic);
		add_demic.setOnClickListener(new click());
		sub_demic.setOnClickListener(new click());
	}
	
	class click implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v==add_demic) {
				updateDemic((Globe.demic+1));
			}if (v==sub_demic) {
				if (Globe.demic>=1) {
					updateDemic((Globe.demic-1));
				}
			}
		}
	}
	
	private void updateDemic(int demic) {
		// TODO Auto-generated method stub
		ContentValues values=new ContentValues();
		values.put("digital", demic);
		int result=db.update("other_sets", values, "id=1", null);
		if (result>0) {
			Globe.demic=demic;
			tv_demic.setText(""+Globe.demic);
		}
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
