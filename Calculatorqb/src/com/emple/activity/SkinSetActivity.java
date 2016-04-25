package com.emple.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.emple.calculatorqb.Globe;
import cn.gqb.calculator.R;
import com.emple.calculatorqb.SkinManage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SkinSetActivity extends StatusSetActivity {

	private TextView mhead_title;
	private SQLiteDatabase db;
	private ListView skin_set_lv;
	private List<String> skins;
	private Context mContext;
	private MyListAdapter myListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_skin_set);
		
		init();
		
	}

	private void init() {
		// TODO Auto-generated method stub
		
		mContext=this;
		db = SQLiteDatabase.openOrCreateDatabase(Globe.dbFile.getPath(), null);  
		mhead_title=(TextView) findViewById(R.id.mhead_title);
		mhead_title.setText("∆§∑Ù…Ë÷√");
		
		skins=new ArrayList<String>();
		try {
			skins = Arrays.asList(getAssets().list("skin"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		skin_set_lv=(ListView) findViewById(R.id.skin_set_lv);
		myListAdapter = new MyListAdapter();
		skin_set_lv.setAdapter(myListAdapter);
	}

	class MyListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return skins.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return skins.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			View view;
			final ViewHolder holder;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			}else {
				view = LayoutInflater.from(mContext).inflate(R.layout.skin_list_item, null);
				holder = new ViewHolder();
				holder.skin_item_note=(TextView) view.findViewById(R.id.skin_item_note);
				holder.skin_item_ok=(ImageView) view.findViewById(R.id.skin_item_ok);
				view.setTag(holder);
			}
			if (position!=skins.indexOf(SkinManage.SKINNAME)) {
				holder.skin_item_ok.setVisibility(View.GONE);
			}else {
				holder.skin_item_ok.setVisibility(View.VISIBLE);
			}
			holder.skin_item_note.setText(skins.get(position));
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					TextView tv=(TextView) v.findViewById(R.id.skin_item_note);
					if (!tv.getText().toString().equals(SkinManage.SKINNAME)) {
						String newskin=tv.getText().toString();
						SkinManage.SKINNAME=newskin;
						SkinManage.SKINPATH="skin/"+newskin;
						SkinManage.MODECHANGE=true;
						updateSkin();
					}
				}
			});
			return view;
		}
	}
	
	class ViewHolder {
		TextView skin_item_note;
		ImageView skin_item_ok;
	}
	
	public void updateSkin() {
		// TODO Auto-generated method stub
		ContentValues values=new ContentValues();
		values.put("name", SkinManage.SKINNAME);
		values.put("path", SkinManage.SKINPATH);
		db.update("skin", values, "id=0", null);
		myListAdapter.notifyDataSetChanged();
	}
	
}
