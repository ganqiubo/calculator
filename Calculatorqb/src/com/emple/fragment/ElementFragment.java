package com.emple.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.gqb.calculator.R;

public class ElementFragment extends Fragment{

   private TextView fragment_element_about;
   private String fontStart="<font color='#0080FF'>",fontEnd="</font>";

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	   
	View rootView = inflater.inflate(R.layout.fragment_element, container, false);
	
	fragment_element_about= (TextView) rootView.findViewById(R.id.fragment_element_about);
	fragment_element_about.setText(Html.fromHtml("\u3000\u3000该元素周期表是按照高中化学经典的元素表来制作的,"
			+ "其中的有些参数可能并没有精确的数值,对那些参数采用中位数。"));
	
	
	return rootView;
}
	
}
