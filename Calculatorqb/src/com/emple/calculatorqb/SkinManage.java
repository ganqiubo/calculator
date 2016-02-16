package com.emple.calculatorqb;

import java.io.IOException;
import java.util.HashMap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

public class SkinManage {
	
	public static int SKINMODE=1;
	public static String SKINPATH="skin/Default0";
	public static boolean MODECHANGE=true;
	
	public static HashMap<String, Integer> States=new HashMap<String, Integer>();
	
	static{
		States.put("above_anchor", android.R.attr.state_above_anchor);
		States.put("unabove_anchor", -android.R.attr.state_above_anchor);
		States.put("accelerated", android.R.attr.state_accelerated);
		States.put("unaccelerated", -android.R.attr.state_accelerated);
		States.put("activated", android.R.attr.state_activated);
		States.put("unactivated", -android.R.attr.state_activated);
		States.put("active", android.R.attr.state_active);
		States.put("unactive", -android.R.attr.state_active);
		States.put("checkable", android.R.attr.state_checkable);
		States.put("uncheckable", -android.R.attr.state_checkable);
		States.put("drag_can_accept", android.R.attr.state_drag_can_accept);
		States.put("undrag_can_accept", -android.R.attr.state_drag_can_accept);
		States.put("drag_hovered", android.R.attr.state_drag_hovered);
		States.put("undrag_hovered", -android.R.attr.state_drag_hovered);
		States.put("empty", android.R.attr.state_empty);
		States.put("unempty", -android.R.attr.state_empty);
		States.put("enabled", android.R.attr.state_enabled);
		States.put("unenabled", -android.R.attr.state_enabled);
		States.put("expanded", android.R.attr.state_expanded);
		States.put("unexpanded", -android.R.attr.state_expanded);
		States.put("first", android.R.attr.state_first);
		States.put("unfirst", -android.R.attr.state_first);
		States.put("focused", android.R.attr.state_focused);
		States.put("unfocused", -android.R.attr.state_focused);
		States.put("hovered", android.R.attr.state_hovered);
		States.put("unhovered", -android.R.attr.state_hovered);
		States.put("last", android.R.attr.state_last);
		States.put("unlast", -android.R.attr.state_last);
		States.put("long_pressable", android.R.attr.state_long_pressable);
		States.put("unlong_pressable", -android.R.attr.state_long_pressable);
		States.put("middle", android.R.attr.state_middle);
		States.put("unmiddle", -android.R.attr.state_middle);
		States.put("multiline", android.R.attr.state_multiline);
		States.put("unmultiline", -android.R.attr.state_multiline);
		States.put("pressed", android.R.attr.state_pressed);
		States.put("unpressed", -android.R.attr.state_pressed);
		States.put("selected", android.R.attr.state_selected);
		States.put("unselected", -android.R.attr.state_selected);
		States.put("single", android.R.attr.state_single);
		States.put("unsingle", -android.R.attr.state_single);
		States.put("window_focused", android.R.attr.state_window_focused);
		States.put("unwindow_focused", -android.R.attr.state_window_focused);
	}
	
	private int pressed = android.R.attr.state_pressed; 
	private int activated = android.R.attr.state_activated; 
	private String skinName;
	private Context ct;
	private String skinPath;
	private int skinPathMode=1;
	
	public SkinManage(String skinName, Context ct) {
		this.skinName = skinName;
		this.ct = ct;
		this.skinPathMode = 1;
		//ct.getApplicationContext()
	}
	
	public SkinManage(String skinName, String skinPath) {
		this.skinName = skinName;
		this.skinPath = skinPath;
		this.skinPathMode = 2;
	}

	public Drawable loadMain1BgSkin(){
		if (skinPathMode==1) {
			return LoadMode1DrawBg(skinName+"/main1bg.png", "main1bg");
		}else {
			return LoadMode2DrawBg(skinPath+"/"+skinName+"/main1bg-b.png");
		}
	}
	
	public Drawable loadPopLv1BgSkin(){
		if (skinPathMode==1) {
			return LoadMode1DrawBg(skinName+"/unitpopbg.png", "unitpopbg");
		}else {
			return LoadMode2DrawBg(skinPath+"/"+skinName+"/unitpopbg.png");
		}
	}
	
	public Drawable loadMain1Et1Skin(){
		if (skinPathMode==1) {
			return LoadMode1DrawBg(skinName+"/main1et1.png", "main1et1");
		}else {
			return LoadMode2DrawBg(skinPath+"/"+skinName+"/main1et1-b.png");
		}
	}
	
	public Drawable loadMain1Rl2Skin(){
		if (skinPathMode==1) {
			return LoadMode1DrawBg(skinName+"/main1rl2.png", "main1rl2");
		}else {
			return LoadMode2DrawBg(skinPath+"/"+skinName+"/main1rl2.png");
		}
	}
	
	public StateListDrawable loadBtbgSkin(){
		if(skinPathMode==1){
			return LoadMode1Draw(new String[]{skinName+"/btbg-b.png",skinName+"/btbg-a.png"}, 
					new String[]{"btbg-b","btbg-a"});
		}else{
			return LoadMode2Draw(new String[]{skinPath+"/"+skinName+"/btbg-b.png",skinPath+"/"+skinName+"/btbg-a.png"});
		}
	}
	
	public StateListDrawable loadBtbg1Skin(){
		if(skinPathMode==1){
			return LoadMode1Draw(new String[]{skinName+"/btbg1-b.png",skinName+"/btbg1-a.png"}, 
					new String[]{"btbg1-b","btbg1-a"});
		}else{
			return LoadMode2Draw(new String[]{skinPath+"/"+skinName+"/btbg1-b.png",skinPath+"/"+skinName+"/btbg1-a.png"});
		}
	}
	
	public StateListDrawable loadBtbg2Skin(){
		if(skinPathMode==1){
			return LoadMode1Draw(new String[]{skinName+"/btbg2-b.png",skinName+"/btbg2-a.png"}, 
					new String[]{"btbg2-b","btbg2-a"});
		}else{
			return LoadMode2Draw(new String[]{skinPath+"/"+skinName+"/btbg2-b.png",skinPath+"/"+skinName+"/btbg2-a.png"});
		}
	}
	
	public StateListDrawable loadMain1Tv2Skin(){
		if(skinPathMode==1){
			return LoadMode1Draw(new String[]{skinName+"/main1tv2-b.png",skinName+"/main1tv2-a.png",skinName+"/main1tv2-c.png"}, 
					new String[]{"main1tv2-b","main1tv2-a","main1tv2-c"});
		}else{
			return LoadMode2Draw(new String[]{skinPath+"/"+skinName+"/main1tv2-b.png",
									skinPath+"/"+skinName+"/main1tv2-a.png",
									skinPath+"/"+skinName+"/main1tv2-c.png",});
		}
	}
	
	public StateListDrawable loadMain1Tv3Skin(){
		if(skinPathMode==1){
			return LoadMode1Draw(new String[]{skinName+"/main1tv3-b.png",skinName+"/main1tv3-a.png",skinName+"/main1tv3-c.png"}, 
					new String[]{"main1tv3-b","main1tv3-a","main1tv3-c"});
		}else{
			return LoadMode2Draw(new String[]{skinPath+"/"+skinName+"/main1tv3-b.png",
									skinPath+"/"+skinName+"/main1tv3-a.png",
									skinPath+"/"+skinName+"/main1tv3-c.png",});
		}
	}
	
	private StateListDrawable LoadMode1Draw(String[] drawPaths,String[] drawNames){
		StateListDrawable draw = new StateListDrawable();
		try {
			draw.addState(new int[]{pressed}, 
					Drawable.createFromStream(ct.getAssets().open("skin/"+drawPaths[0]), drawNames[0]));
			if (drawPaths.length==3) {
				draw.addState(new int[]{-activated},
					Drawable.createFromStream(ct.getAssets().open("skin/"+drawPaths[2]), drawNames[2]));
			}
			draw.addState(new int[]{}, 
					Drawable.createFromStream(ct.getAssets().open("skin/"+drawPaths[1]), drawNames[1]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			draw=null;
		}
		return draw;
	}
	
	private StateListDrawable LoadMode2Draw(String[] drawPaths){
		StateListDrawable draw = new StateListDrawable();
		try {
			draw.addState(new int[]{pressed}, 
					Drawable.createFromPath(drawPaths[0]));
			if (drawPaths.length==3) {
				draw.addState(new int[]{-activated}, 
						Drawable.createFromPath(drawPaths[2]));
			}
			draw.addState(new int[]{}, 
					Drawable.createFromPath(drawPaths[1]));
		} catch (Exception e) {
			// TODO: handle exception
			draw=null;
		}
		return draw;
	}
	
	private Drawable LoadMode1DrawBg(String drawPath,String drawName){
		Drawable draw=null;
		try {
			draw=Drawable.createFromStream(ct.getAssets().open("skin/"+drawPath), drawName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			draw=null;
		}
		return draw;
	}
	
	private Drawable LoadMode2DrawBg(String drawPath){
		Drawable draw = null;
		try {
			draw=Drawable.createFromPath(drawPath);
		} catch (Exception e) {
			// TODO: handle exception
			draw=null;
		}
		return draw;
	}
	
	/*style:
	 * 	btbg
	 * 	btbg1
	 * 	btbg2
	 * 	rln1
	 * */

	
	
}
