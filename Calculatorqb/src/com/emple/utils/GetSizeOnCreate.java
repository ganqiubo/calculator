package com.emple.utils;

import android.view.View;

public class GetSizeOnCreate {

	public static int[] getSize(View v) {
		// TODO Auto-generated constructor stub
		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
		v.measure(w, h); 
		int[] size=new int[2];
		size[0] =v.getMeasuredWidth(); 
		size[1] =v.getMeasuredHeight(); 
		return size;
	}
	
}
