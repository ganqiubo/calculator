package com.emple.calculatorqb;

import com.emple.annotation.Inject;

import android.app.Activity;
import android.view.View;

/**
 * Created by 11649 on 2016/2/16.
 */
public class FrameActivity extends Activity {

    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	
    	try {
    		Inject.inject(this);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    }

}
