package com.emple.calculatorqb;

import com.emple.annotation.Inject;

import android.app.Activity;
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
    		if(SkinManage.SKINNAME.equals("Default2")){
    			onReMeasure(0);
    		}else{
    			onReMeasure(1);
    		}
    		Inject.inject(this);
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("", ""+e);
		}
    }
    
    public void onReMeasure(int ds){
    	
    }
    
    
}
