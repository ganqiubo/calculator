package com.emple.annotation;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.xmlpull.v1.XmlPullParser;

import cn.gqb.calculator.R;
import com.emple.calculatorqb.SkinManage;
import com.emple.utils.NinePatchBitmapFactory;

import android.R.raw;
import android.app.Activity;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ResourceCursorTreeAdapter;
import android.widget.Toast;

public class InjectDra {

	public static void inject(Activity activity){
		if (SkinManage.MODECHANGE) {
			Class<?> clazz=activity.getClass();
			Field[] fields=clazz.getDeclaredFields();
			
			/*资源文件按注解名分类*/
			Map<String, List<String[]>> classifyDraws=new HashMap<String, List<String[]>>();
			try {
				if (SkinManage.SKINMODE==1) {
					classifyDraws=classifyDraws1(activity);
				}else{
					classifyDraws=classifyDraws2();
				}
			} catch (Exception e) {
				// TODO: handle exception
				Log.e("", ""+e);
			}
			
			Log.e("", fields.length+"=======>");
			
			/*注入皮肤背景图片*/
			for (Field field : fields) {		
				
				InjectStateDraw injectStateDraw=field.getAnnotation(InjectStateDraw.class);
				String draw_name="";
				System.out.println("====>INJECT");
				if (injectStateDraw!=null && !"".equals(injectStateDraw.draw_name())) {	
					draw_name=injectStateDraw.draw_name();
					//Log.e("", injectStateDraw+"=======>"+field);
					StateListDrawable draw = null;
					if (SkinManage.SKINMODE==1) {
						draw=getStateDraw1(activity,classifyDraws,draw_name);
					}else{
						draw=getStateDraw2(activity,classifyDraws,draw_name);
					}
					try {
						//Log.e("", draw+"====>"+((View)field.get(activity)));
						field.setAccessible(true);
						if (draw!=null && ((View)field.get(activity))!=null) {
							((View)field.get(activity)).setBackgroundDrawable(draw);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
					} 
				}
			}
			SkinManage.MODECHANGE=false;
		}
	}

	public static StateListDrawable getStateDraw1(Activity activity, Map<String, List<String[]>> classifyDraws, String draw_name) {
		// TODO Auto-generated method stub
		StateListDrawable draw=new StateListDrawable();
		try {
			boolean hasnormal=false;
			String normal_draw_name="";
			String normal_draw_filename="";
			String draType="0";
			for (String[] parts : classifyDraws.get(draw_name)) {
				if ("normal".equals(parts[0])) {
					hasnormal=true;
					normal_draw_name=draw_name;
					normal_draw_filename=parts[1];
					draType=parts[2];
				}else{
					if ("0".equals(parts[2])) {
						draw.addState(new int[]{SkinManage.States.get(parts[0])}, 
								Drawable.createFromStream(activity.getAssets().open(SkinManage.SKINPATH+"/"+parts[1]), draw_name));
					}
				}
			}
			if (hasnormal) {
				if ("0".equals(draType)) {
					draw.addState(new int[]{}, 
							Drawable.createFromStream(activity.getAssets().open(SkinManage.SKINPATH+"/"+normal_draw_filename), draw_name));
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return draw;
	}
	
	/*资源文件按注解名分类*/
	public static Map<String, List<String[]>> classifyDraws1(Activity activity) throws IOException {
		// TODO Auto-generated method stub
		Map<String, List<String[]>> classifyDraws=new HashMap<String, List<String[]>>();
		for (String filename : activity.getAssets().list(SkinManage.SKINPATH)) {
			String[] parts=filename.split("[-\\.]");
			if (parts.length==3) {
				if (classifyDraws.containsKey(parts[0])) {
					classifyDraws.get(parts[0]).add(new String[]{parts[1],filename,"0"});
				}else{
					List<String[]> listDraws=new ArrayList<String[]>();
					listDraws.add(new String[]{parts[1],filename,"0"});
					classifyDraws.put(parts[0], listDraws);
				}
			}
		}		
		Log.e("", "SkinManage.SKINMODE=====>"+classifyDraws.size());
		//Log.e("", classifyDraws.size()+"====>"+classifyDraws+"----->"+"<<<<<<inject--->");
		for (Entry<String, List<String[]>> element : classifyDraws.entrySet()) {
			Log.e("", element.getKey()+"====>"+element.getValue().size()+"----->"+"<<<<<<inject--->");
		}
		return classifyDraws;
	}

	
	/*资源文件按注解名分类*/
	public static Map<String, List<String[]>> classifyDraws2() throws IOException {
		return null;
	}
	
	
	public static StateListDrawable getStateDraw2(Activity activity, Map<String, List<String[]>> classifyDraws, String draw_name) {
		// TODO Auto-generated method stub
		
		return null;
	}
	

	/*android.R.attr.state_above_anchor
	android.R.attr.state_accelerated
	android.R.attr.state_activated
	android.R.attr.state_active
	android.R.attr.state_checkable
	android.R.attr.state_drag_can_accept
	android.R.attr.state_drag_hovered
	android.R.attr.state_empty
	android.R.attr.state_enabled
	android.R.attr.state_expanded
	android.R.attr.state_first
	android.R.attr.state_focused
	android.R.attr.state_hovered
	android.R.attr.state_last
	android.R.attr.state_long_pressable
	android.R.attr.state_middle
	android.R.attr.state_multiline
	android.R.attr.state_pressed
	android.R.attr.state_selected
	android.R.attr.state_single
	android.R.attr.state_window_focused*/
	
	
}
