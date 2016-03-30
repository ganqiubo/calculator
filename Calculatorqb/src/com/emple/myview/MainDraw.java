package com.emple.myview;

import com.emple.calculatorqb.Globe;
import com.emple.calculatorqb.R;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainDraw extends Activity {

	private Button skin_modify;
	private RelativeLayout eles_rl;
	private float dsx,dsy,touchx,touchy;
	private boolean pressed=false;
	private Context mcontext;
	private int pwidth;
	private int pheight;
	private LinearLayout[] rows=new LinearLayout[9];
	private GridView gview;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics  dm = new DisplayMetrics();    
	 	getWindowManager().getDefaultDisplay().getMetrics(dm);    
	 	pwidth = dm.widthPixels;              
	 	pheight = dm.heightPixels;
	 	Globe.pwidth=pwidth;
	 	Globe.pheight=pheight;
	 	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_elements);
		
		/*skin_modify=(Button) findViewById(R.id.button1);
		GradientDrawable gra=new GradientDrawable();
		gra.setColor(Color.rgb(0, 100, 0));
		skin_modify.setBackground(gra);
		gra.setCornerRadius(20);
		gra.setCornerRadii(new float[]{10,10,20,20,30,30,40,60});
		Log.e("", gra.getGradientRadius()+"<<<<<<<<<getBackground-----¡·");*/
		/*rl0=(RelativeLayout) findViewById(R.id.ele_rl0);
		
		StateListDrawable sts=new StateListDrawable();
		sts.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(Color.WHITE));
		sts.addState(new int[]{}, new ColorDrawable(Color.YELLOW));
		
		rl0.setBackgroundDrawable(sts);
		rl0.setOnClickListener(new onclick());
		//view.dispatchDragEvent(event)
		//skin_modify.dis
		rl0.setScaleX((float) 1);
		rl0.setScaleY((float) 1);*/
		//gridView.setColumnWidth(20);
		//gridView
		
		
		initViews();
		/*mcontext = this;
		gview = (GridView) findViewById(R.id.eles_grid);
		gview.setAdapter(new ElesAdapter());*/
	}

	private void initViews() {
		// TODO Auto-generated method stub
		db = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory()+"/gqb/gqb.db", null);   	
		mcontext = this;
		eles_rl=(RelativeLayout) findViewById(R.id.eles_rl);
		
		for(int i=0;i<eles_rl.getChildCount();i++){
			rows[i]=(LinearLayout) eles_rl.getChildAt(i);
			for(int j=0;j<rows[i].getChildCount();j++){
				addEleData((RelativeLayout) rows[i].getChildAt(j), (i+1), (j+1));
			}
		}
		
	}
	
	private void addEleData(RelativeLayout rl,int i,int j) {
		// TODO Auto-generated method stub
		
		Cursor c = db.rawQuery("select * from elements_table where ele_x=? and ele_y=?", new String[]{(""+i),(""+j)});
		if(c.getCount()==0){
			rl.setVisibility(View.INVISIBLE);
		}if (c.moveToNext()) {
			//Log.e("", c.getCount()+"--->"+i+">>>>>"+j);
			int boder=c.getInt(c.getColumnIndexOrThrow("ele_border"));
			rl.setBackgroundResource(R.drawable.selector_ele1);
			if (boder==1) {
				rl.setBackgroundResource(R.drawable.border1);
			}if (boder==2) {
				rl.setBackgroundResource(R.drawable.border2);
			}if (boder==3) {
				rl.setBackgroundResource(R.drawable.border3);
			}if (boder==4) {
				rl.setBackgroundResource(R.drawable.border4);
				//StateListDrawable sd=(StateListDrawable) rl.getBackground();
				LayerDrawable ld=(LayerDrawable) rl.getBackground();
				Log.e("", "----->"+ld.getNumberOfLayers());
			}
			//Log.e("", "----->"+rl.getBackground().getClass());
			rl.setOnClickListener(new click());
			//LayerDrawable dr;
			//dr.addLayer(dr);
		}
	}

	class click implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Log.e("", "----->onClick");
		}
		
	}
	
	private class ElesAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return 162;
		}

		@Override
		public Object getItem(int position) {
			return 0;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view;
			ViewHolder holder = null;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(mcontext, R.layout.ele_layout, null);
				view.setTag(holder);
			}
			//Log.e("", "------>"+view.getClass());
			return view;
		}

	}
 
	class ViewHolder {
		
	}
	
}
