package com.emple.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.emple.calculatorqb.Globe;
import com.emple.calculatorqb.MainActivity;
import com.emple.calculatorqb.R;
import com.emple.calculatorqb.R.id;
import com.emple.utils.StateClolr;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class InjectColor {

	public static void inject(Activity activity,int color, int layoutId){
		Log.e("", "@=@4-->methodf");
		Class<?> clazz=activity.getClass();
		Field[] fields=clazz.getDeclaredFields();
		for (Field field : fields) {
			InjectTextColor injectTextColor=field.getAnnotation(InjectTextColor.class);
			if (injectTextColor!=null) {	
				try {
					//color=injectTextColor.color();
					field.setAccessible(true);
					View fieldv=(View)field.get(activity);
					if (fieldv!=null) {
						Class<?> clazzf=fieldv.getClass();
						Method methodf=clazzf.getMethod("setTextColor", int.class);
						if (methodf!= null) {
							methodf.setAccessible(true);
							methodf.invoke(fieldv, color);
							Log.e("", "@=@3-->methodf");
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					
				}
			}
		}
		unityTvColor(activity ,color,layoutId);
	}

	private static void unityTvColor(Activity activity, int color, int layoutId) {
		// TODO Auto-generated method stubs
		ColorStateList colorStateList1=StateClolr.getListSelected(color, 
				0xFF3DEDED);
		MainActivity.unitColorList=colorStateList1;
		for (int i = 0; i < Globe.main1ll1.getChildCount(); i++) {
			((TextView)Globe.main1ll1.getChildAt(i)).setTextColor(colorStateList1);
		}
		
		Globe.main1tv4.setTextColor(StateClolr.getListpressed(color, 
				Color.rgb(255, 102, 0)));
		//int position=MainActivity.unitpoplv1.getSelectedItemPosition();
		
		MainActivity.unitpoplv1.setAdapter((ListAdapter) new ArrayAdapter<String>(activity,  
				layoutId,MainActivity.units));
		MainActivity.pbCal1.setTextColor(StateClolr.getListpressed(color, 
				Color.rgb(255, 102, 0)));
		Globe.funet1.setTextColor(color);
	}
	
}
