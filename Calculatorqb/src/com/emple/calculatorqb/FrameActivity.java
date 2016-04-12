package com.emple.calculatorqb;

import com.emple.annotation.InjectColor;
import com.emple.annotation.InjectDra;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

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
    		if(SkinManage.SKINNAME.equals("Default0")){
    			onReMeasure(1);
    		}else{
    			onReMeasure(0);
    		}
    		if ("Default4".equals(SkinManage.SKINNAME)) {
    			InjectColor.inject(this,0xff676767);
    		}if (!"Default4".equals(SkinManage.SKINNAME)) {
    			InjectColor.inject(this,0xffffffff);
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
