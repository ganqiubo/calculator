package com.emple.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

public class InjectColor {

	public static void inject(Activity activity,int color){
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
	}
	
}
