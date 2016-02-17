package com.emple.calculatorqb;

import com.emple.activity.ElementListActivity;
import com.emple.activity.NoteActivity;
import com.emple.activity.SkinSetActivity;
import com.emple.activity.StatusSetActivity;
import com.emple.activity.UnitTablesActivity;
import com.emple.activity.VersionInfoActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class SettingActivity extends StatusSetActivity{

	private static String[] SETS=new String[]{"皮肤管理","功能详细说明","单位转换表","元素周期表","软件版本号"};
	private static int[] SETICONS=new int[]{R.drawable.skin,R.drawable.note,R.drawable.unit_tables,R.drawable.element_list,R.drawable.version_info};
	private ListView list_sets;
	private Context mcontext=this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		
		initview();
		
	}

	private void initview() {
		// TODO Auto-generated method stub
		list_sets=(ListView) findViewById(R.id.setting_list);
		
		list_sets.setAdapter(new MyListAdapter());
		list_sets.setOnItemClickListener(new itemclick());
	}

	class itemclick implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			if (position==0) {
				Intent intent=new Intent(SettingActivity.this, SkinSetActivity.class);
				startActivity(intent);
			}if (position==1) {
				Intent intent=new Intent(SettingActivity.this, NoteActivity.class);
				startActivity(intent);
			}if (position==2) {
				Intent intent=new Intent(SettingActivity.this, UnitTablesActivity.class);
				startActivity(intent);
			}if (position==3) {
				Intent intent=new Intent(SettingActivity.this, ElementListActivity.class);
				startActivity(intent);
			}if (position==4) {
				Intent intent=new Intent(SettingActivity.this, VersionInfoActivity.class);
				startActivity(intent);
			}
			overridePendingTransition(R.anim.slide_in_right,R.anim.slide_no); 
		}
	}
	
	class MyListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return SETS.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return SETS[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			View view=null;
			ViewHolder holder=null;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(mcontext, R.layout.set_list_item, null);
				holder = new ViewHolder();
				holder.set_tv=(TextView) view.findViewById(R.id.setting_list_itemtv);
				holder.set_icon=(ImageView) view.findViewById(R.id.setting_list_itemiv);
				
				holder.set_tv.setText(SETS[position]);
				holder.set_icon.setImageResource(SETICONS[position]);
			}
			//Log.e("", "===>"+position);
			//holder.set_tv.setText(SETS[position]);
			//holder.set_icon.setImageResource(SETICONS[position]);
			return view;
			
		}

	}
	
	class ViewHolder {
		TextView set_tv;
		ImageView set_icon;
	}
	
}
