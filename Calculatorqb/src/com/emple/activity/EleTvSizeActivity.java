package com.emple.activity;

import com.emple.calculatorqb.R;
import com.emple.entity.ElesTextSize;
import com.emple.utils.ElesTxtSizeUtil;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EleTvSizeActivity extends StatusSetActivity {

	private TextView mhead_title;
	private ListView list_tvsize;
	private String[] size_note=new String[]{"元素(小)大小","元素(大)大小"};
	private ElesTextSize elesSize=new ElesTextSize();
	private ElesTxtSizeUtil elesSizeUtil=new ElesTxtSizeUtil();
	private Context mcontext;
	private TextView tvsize_default;
	private MyListAdapter myListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ele_tv_size);
		
		init();
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		mcontext=this;
		mhead_title=(TextView) findViewById(R.id.mhead_title);
		mhead_title.setText("字体大小");
		
		elesSize=elesSizeUtil.getTextSizes();
		list_tvsize=(ListView) findViewById(R.id.tvsize_list);
		tvsize_default=(TextView) findViewById(R.id.tvsize_default);
		tvsize_default.setOnClickListener(new btclick());
		myListAdapter=new MyListAdapter();
		list_tvsize.setAdapter(myListAdapter);
	}
	
	class btclick implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (elesSizeUtil.setToDefaultSize()) {
				elesSize=elesSizeUtil.getDefaultSizes();
				//elesSize
				Log.e("", "--->click");
				myListAdapter.notifyDataSetChanged();
			}else {
				Toast.makeText(mcontext, "修改失败", Toast.LENGTH_SHORT).show();
			}
			
		}
	}
	
	class MyListAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return size_note.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return size_note[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Log.e("", "--->");
			View view;
			final ViewHolder holder;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				Log.e("", "tvsize_item_size----->");
				view = LayoutInflater.from(mcontext).inflate(R.layout.tvsize_list_item, null);
				holder = new ViewHolder();
				holder.tvsize_item_note=(TextView) view.findViewById(R.id.tvsize_item_note);
				holder.tvsize_item_size=(TextView) view.findViewById(R.id.tvsize_item_size);
				holder.tvsize_item_add=(ImageButton) view.findViewById(R.id.tvsize_item_add);
				holder.tvsize_item_sub=(ImageButton) view.findViewById(R.id.tvsize_item_sub);
				
				holder.tvsize_item_add.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ElesTextSize ets=elesSize.clone();
						if ("元素(小)大小".equals(size_note[position])) {
							ets.addTinyOne();
						}else{
							ets.addBigOne();
						}
						setTvSize(ets,holder.tvsize_item_size,position);
					}
				});
				
				holder.tvsize_item_sub.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ElesTextSize ets=elesSize.clone();
						if ("元素(小)大小".equals(size_note[position])) {
							ets.subTinyOne();
						}else{
							ets.subBigOne();
						}
						setTvSize(ets,holder.tvsize_item_size,position);
					}
				});
				view.setTag(holder);
			}
			holder.tvsize_item_note.setText(size_note[position]);
			if ("元素(小)大小".equals(size_note[position])) {
				holder.tvsize_item_size.setText(""+elesSize.getEle_size4());
			}else{
				holder.tvsize_item_size.setText(""+elesSize.getEle1_size4());
			}
			return view;
		}
		
		public void setTvSize(ElesTextSize ets, TextView tvsize_item_size, int position){
			if (elesSizeUtil.setTextSizes(ets)) {
				elesSize=ets;
				if ("元素(小)大小".equals(size_note[position])) {
					tvsize_item_size.setText(""+elesSize.getEle_size4());
				}else{
					tvsize_item_size.setText(""+elesSize.getEle1_size4());
				}
			}else{
				Toast.makeText(mcontext, "修改失败", Toast.LENGTH_SHORT).show();
			}
		}

	}
	
	class ViewHolder {
		TextView tvsize_item_note;
		TextView tvsize_item_size;
		ImageButton tvsize_item_add;
		ImageButton tvsize_item_sub;
	}

}
