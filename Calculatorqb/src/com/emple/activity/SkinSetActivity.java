package com.emple.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.emple.calculatorqb.Globe;
import com.emple.calculatorqb.R;
import com.emple.calculatorqb.SkinManage;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class SkinSetActivity extends StatusSetActivity {

	private TextView mhead_title;
	private Spinner skinname;
	private Button skin_modify;
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_skin_set);
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		db = SQLiteDatabase.openOrCreateDatabase(Globe.dbFile.getPath(), null);  
		mhead_title=(TextView) findViewById(R.id.mhead_title);
		mhead_title.setText("皮肤设置");
		
		skinname=(Spinner) findViewById(R.id.myskin_sp);
		List<String> skins=new ArrayList<String>();
		try {
			skins = Arrays.asList(getAssets().list("skin"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinn_style, skins);
		skinname.setAdapter(adapter);
		skinname.setSelection(skins.indexOf(SkinManage.SKINNAME));
		skinname.setOnItemSelectedListener(new itemselected());
		
		skin_modify=(Button) findViewById(R.id.myskin_modify);
		skin_modify.setOnClickListener(new btclick());
	}

	class itemselected implements OnItemSelectedListener{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			Log.e("", skinname.getSelectedItem().toString()+"<<<<<<<<<onItemSelected-----》"+SkinManage.SKINNAME);
			//skin_modify.getBackground().getClass();
			Log.e("", skin_modify.getBackground().getClass()+"<<<<<<<<<getBackground-----》");
			if (skinname.getSelectedItem().toString().equals(SkinManage.SKINNAME)) {
				skin_modify.setEnabled(false);
			}else{
				skin_modify.setEnabled(true);
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
		}
	}
	
	class btclick implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			String newskin=skinname.getSelectedItem().toString();
			SkinManage.SKINNAME=newskin;
			SkinManage.SKINPATH="skin/"+newskin;
			SkinManage.MODECHANGE=true;
			updateSkin();
			Toast.makeText(SkinSetActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
			skin_modify.setEnabled(false);
		}

		private void updateSkin() {
			// TODO Auto-generated method stub
			
			ContentValues values=new ContentValues();
			values.put("name", SkinManage.SKINNAME);
			values.put("path", SkinManage.SKINPATH);
			db.update("skin", values, "id=0", null);
		}
	}
	
}
