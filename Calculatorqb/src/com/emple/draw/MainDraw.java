package com.emple.draw;

import com.emple.calculatorqb.R;
import android.os.Bundle;
import android.util.Log;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainDraw extends Activity {

	private Button skin_modify;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main_draw);
		
		skin_modify=(Button) findViewById(R.id.button1);
		GradientDrawable gra=new GradientDrawable();
		gra.setColor(Color.rgb(0, 100, 0));
		skin_modify.setBackground(gra);
		gra.setCornerRadius(20);
		gra.setCornerRadii(new float[]{10,10,20,20,30,30,40,60});
		Log.e("", gra.getGradientRadius()+"<<<<<<<<<getBackground-----¡·");
		
	}

	
	
	
}
