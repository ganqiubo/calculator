package com.emple.myview;

import com.emple.calculatorqb.Globe;
import com.emple.calculatorqb.R;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

public class MyTabLayout extends TableLayout{

	public LayoutInflater layoutInflater;
	public LinearLayout layout;
	public int rows;
	public int cols;
	public int width;
	public int height;
	public int item_layout;
	public String myNamespace = "http://schemas.android.com/apk/res/com.emple.calculatorqb";
	public int itemwidth,itemheight;

	public MyTabLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.rows=Integer.parseInt(attrs.getAttributeValue(myNamespace, "rows"));
		this.cols=Integer.parseInt(attrs.getAttributeValue(myNamespace, "cols"));
		this.item_layout=Integer.parseInt((attrs.getAttributeValue(myNamespace, "item_layout").replace("@", "")));
		Log.e("", "====>"+attrs.getAttributeValue(myNamespace, "item_layout"));
		addView(context);
	}
	
	public MyTabLayout(Context context, int rows,int cols,int item_layout) {
		super(context);
		// TODO Auto-generated constructor stub
		this.rows=rows;
		this.cols=cols;
		this.item_layout=item_layout;
		addView(context);
	}

	public void addView(Context context){
		layout=new LinearLayout(context);
		layout.setOrientation(LinearLayout.VERTICAL);
		itemwidth=Globe.pwidth/10;
		itemheight=Globe.pwidth/6;
		for(int i=0;i<rows;i++){
			LinearLayout Layoutcolumn = new LinearLayout(context);
			Layoutcolumn.setOrientation(LinearLayout.HORIZONTAL);
			for(int j=0;j<cols;j++){
				layoutInflater=LayoutInflater.from(context);  
				RelativeLayout view=(RelativeLayout) layoutInflater.inflate(item_layout, null);
				view.getChildAt(0).getLayoutParams().width=itemwidth;
				//Log.e("",Globe.pwidth+"----¡·"+view.getChildAt(0).getLayoutParams());
				view.setBackgroundColor(Color.YELLOW);
				Layoutcolumn.addView(view);
			}
			layout.addView(Layoutcolumn);
		}
		this.addView(layout);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	}
	
	public View getitem(int rowindex,int colindex){
		View view=((LinearLayout)layout.getChildAt(rowindex)).getChildAt(colindex);
		return view;
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		//Log.e("", ""+);
	}
	
}
