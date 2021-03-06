package com.emple.calculatorqb;

import com.emple.annotation.InjectColor;
import com.emple.annotation.InjectDra;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import cn.gqb.calculator.R;

/**
 * Created by 11649 on 2016/2/16.
 */
public class FrameActivity extends Activity {

    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	Log.e("", "====>onStart");
    	try {
    		if(SkinManage.SKINNAME.equals("Default0") || SkinManage.SKINNAME.equals("Default4") || SkinManage.SKINNAME.equals("Default1")){
    			onReMeasure(1);
    		}else{
    			onReMeasure(0);
    		}
    		if ("Default4".equals(SkinManage.SKINNAME) || "Default1".equals(SkinManage.SKINNAME)) {
    			if (this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
    				InjectColor.inject(this,0xff676767,R.layout.unitpopstyle3);
    			}else {
    				InjectColor.inject(this,0xff676767,R.layout.unitpopstyle1);
				}
    		}if ("Default2".equals(SkinManage.SKINNAME)) {
    			if (this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
    				InjectColor.inject(this,0xff565656,R.layout.unitpopstyle3);
    			}else {
    				InjectColor.inject(this,0xff565656,R.layout.unitpopstyle1);
				}
			}if (!"Default4".equals(SkinManage.SKINNAME) && !"Default1".equals(SkinManage.SKINNAME) 
    				&& !"Default2".equals(SkinManage.SKINNAME)) {
    			if (this.getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
    				InjectColor.inject(this,0xffffffff,R.layout.unitpopstyle2);
    			}else {
    				InjectColor.inject(this,0xffffffff,R.layout.unitpopstyle);
    			}
			}
    		InjectDra.inject(this);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("", ""+e);
		}
    }
    
    /**
     *@param ds driver width
     */
    public void onReMeasure(int ds){
    	
    }
    
    
}
