package com.emple.activity;

import com.emple.calculatorqb.Globe;
import com.emple.calculatorqb.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

@SuppressLint("NewApi")
public class StatusSetActivity extends Activity{

	public boolean hide_status=false;
	public RelativeLayout rlhead;
	public ImageButton back;
	
	@Override
	public void setContentView(int layoutResID) {
		// TODO Auto-generated method stub
		if(VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {  
            Window window = getWindow();  
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS  
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
            window.getDecorView().setSystemUiVisibility(
            				View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN  
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);  
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);  
            window.setStatusBarColor(Color.TRANSPARENT);
            hide_status=true;
        }
		super.setContentView(layoutResID);
		
		rlhead=(RelativeLayout) findViewById(R.id.settinhg_head);
		back=(ImageButton) findViewById(R.id.mhead_back);
		
		back.setOnClickListener(new backclick());
		
		if (hide_status) {
			LayoutParams params=(LayoutParams) rlhead.getLayoutParams();
			params.topMargin=(Globe.statusHeight1-5);
			rlhead.setLayoutParams(params);
			//Toast.makeText(this, "----->"+Globe.statusHeight1, Toast.LENGTH_SHORT).show();
		}
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
	
	class backclick implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			finish();
			overridePendingTransition(R.anim.slide_no,R.anim.slide_out_right);    
		}
	}
	
}
