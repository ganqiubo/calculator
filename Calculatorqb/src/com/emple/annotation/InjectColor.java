package com.emple.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

public class InjectColor {

	public static void inject(Activity activity){
		Class<?> clazz=activity.getClass();
		Field[] fields=clazz.getDeclaredFields();
		for (Field field : fields) {
			InjectTextColor injectTextColor=field.getAnnotation(InjectTextColor.class);
			int color=Color.argb(255, 255, 255, 255);
			if (injectTextColor!=null) {	
				try {
					color=injectTextColor.color();
					field.setAccessible(true);
					View fieldv=(View)field.get(activity);
					if (fieldv!=null) {
						Class<?> clazzf=fieldv.getClass();
						Method methodf=clazzf.getMethod("setTextColor", int.class);
						if (methodf!= null) {
							methodf.setAccessible(true);
							methodf.invoke(fieldv, color);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					
				}
			}
		}	
	}
	
}
