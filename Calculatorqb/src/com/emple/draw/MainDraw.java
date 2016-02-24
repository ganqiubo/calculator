package com.emple.draw;

import com.emple.calculatorqb.R;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainDraw extends Activity {

	private Button skin_modify;
	private RelativeLayout rl0;
	private float dsx,dsy,touchx,touchy;
	private boolean pressed=false;
	private GridView gv;
	private Context mcontext;
	private MyGridAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
		mcontext = this;
		gv=(GridView) findViewById(R.id.eles_gv);
		adapter = new MyGridAdapter();
		gv.setAdapter(adapter);
		Log.e("", "====>>>"+gv.getLayoutParams());
	}

	private class MyGridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 6;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			View view;
			ViewHolder holder;
			if (convertView != null) {
				view = convertView;
				holder = (ViewHolder) view.getTag();
			} else {
				view = View.inflate(mcontext, R.layout.ele_layout, null);
				holder = new ViewHolder();
				holder.ele_rl0=(RelativeLayout) view.findViewById(R.id.ele_rl0);
				holder.ele_rlcenter=(RelativeLayout) view.findViewById(R.id.ele_rlcenter);
				view.setTag(holder);
			}
			if (holder.ele_rl0.getLayoutParams()!=null) {
				Log.e("", holder.ele_rl0.getLayoutParams()+"====>"+position);
				//holder.ele_rl0.getLayoutParams().width=400;
			}
			
			//holder.ele_rl0.getLayoutParams().width=100;
			return view;
		}
	
	}
	
	class ViewHolder {
		RelativeLayout ele_rlcenter,ele_rl0;
	}
	
	
	/*@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		//rl0.setPressed(pressed);
		//Log.e("", ""+rl0.isPressed());
		
		if(ev.getAction()==MotionEvent.ACTION_DOWN){
			//ev.getPointerCount();
			//Log.e("", "ACTION_DOWN====>");
		}if (ev.getAction()==MotionEvent.ACTION_UP) {
			//Log.e("", "ACTION_UP====>");
		}if (ev.getAction()==MotionEvent.ACTION_MOVE) {
			//Log.e("", rl0.getX()+"===>"+rl0.isPressed());
			if (!rl0.isPressed()) {
				if (!onmove) {
					onmove=true;
					dsx=0;
					dsy=0;
					touchx=ev.getX();
					touchy=ev.getY();
				}else{
					dsx=ev.getX()-touchx;
					dsy=ev.getY()-touchy;
					//Log.e("", dsx+"<=="+rl0.isPressed()+"==>"+dsy);
					rl0.setX(rl0.getX()+dsx);
					rl0.setY(rl0.getY()+dsy);
					touchx=ev.getX();
					touchy=ev.getY();
				}
			}	
		}
		return super.dispatchTouchEvent(ev);
		
		//return false;
	}*/
	
}
