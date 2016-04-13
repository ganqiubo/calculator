package com.emple.utils;

import android.content.res.ColorStateList;

public class StateClolr {

	public static ColorStateList getListSelected(int normal,int selected){
		int[] colors = new int[] { selected,normal};  
		int[][] states = new int[2][]; 
		states[0] = new int[] {android.R.attr.state_selected};  
        states[1] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
	}
	
	public static ColorStateList getListpressed(int normal,int pressed){
		int[] colors = new int[] { pressed,normal};  
		int[][] states = new int[2][]; 
		states[0] = new int[] {android.R.attr.state_pressed};  
        states[1] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
	}
	
}
