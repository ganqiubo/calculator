package com.emple.calculatorqb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SettingActivity extends Activity {

	private static String[] SETS=new String[]{"皮肤管理","功能详细说明","单位转换表","元素周期表","软件版本号"};
	private static int[] SETICONS=new int[]{R.drawable.skin,R.drawable.note,R.drawable.unit_tables,R.drawable.element_list,R.drawable.version_info};
	private ListView list_sets;
	private ImageButton back;
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
		back=(ImageButton) findViewById(R.id.mhead_back);
		
		back.setOnClickListener(new backclick());
		list_sets.setAdapter(new MyListAdapter());
	}
	
	class backclick implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
			overridePendingTransition(R.anim.slide_no,R.anim.slide_out_right);    
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		if (event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
			finish();
			overridePendingTransition(R.anim.slide_no,R.anim.slide_out_right);    
		}
		return false;
	}

	
	
}
