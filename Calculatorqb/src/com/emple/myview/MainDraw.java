package com.emple.myview;

import com.emple.calculatorqb.Globe;
import com.emple.calculatorqb.R;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
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
import android.widget.TableLayout;
import android.widget.TextView;

public class MainDraw extends Activity {

	private Button skin_modify;
	private RelativeLayout eles_rl;
	private float dsx,dsy,touchx,touchy;
	private boolean pressed=false;
	private Context mcontext;
	private int pwidth;
	private int pheight;

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
		mcontext = this;
		eles_rl=(RelativeLayout) findViewById(R.id.eles_rl);
		/*eles_rl.setScaleX((float) 1.2);
		eles_rl.setScaleY((float) 1.2);*/
		//Log.e("", "onCreate---->");
		
		
	}
	
}
