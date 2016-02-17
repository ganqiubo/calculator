package com.emple.calculatorqb;

import java.util.HashMap;

public class SkinManage {
	
	public static int SKINMODE=1;
	public static String SKINNAME="Default0";
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
		//normal
	}
	
	
}
