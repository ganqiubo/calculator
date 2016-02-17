package com.emple.calculatorqb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;

import com.emple.annotation.InjectStateDraw;
import com.emple.calculatorqb.Globe;
import com.emple.calculatorqb.Judge;
import com.emple.calculatorqb.Main1Inputtext;
import com.emple.calculatorqb.R;
import com.emple.calculatorqb.SettingActivity;
import com.emple.calculatorqb.SkinManage;
import com.emple.calculatorqb.R.anim;
import com.emple.calculatorqb.R.color;
import com.emple.calculatorqb.R.drawable;
import com.emple.calculatorqb.R.id;
import com.emple.calculatorqb.R.layout;

import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FrameActivity {
	
	String unitnameshow="all";//,funnote="";
	BigDecimal π;
	BigDecimal e;
	boolean soundinput=false;
	Context ct;
	String[] strarray=new String[]{"n",".","0","=","+","√","1","2","3","-","^","4","5","6","×",
			                    "( )","7","8","9","÷","sin","cos","tan","log","ln","L","R","∑","C","D"};			 	
	int pwidth,pheight,btw1,bth1,btspace/*,statusBarHeight*/,ds,funid=1,maxfunid=0,etsize=0,btsize=0;
	@InjectStateDraw(draw_name="main1bg")
	RelativeLayout rln1;
	@InjectStateDraw(draw_name="main1rl2")
	RelativeLayout rln2;
	@InjectStateDraw(draw_name="main1et1")
	RelativeLayout rln3;
	EditText main1et1,funet1;
	
	TextView main1tv1;
	TextView main1tv4;
	@InjectStateDraw(draw_name="main1tv2")
	TextView main1tv2;
	@InjectStateDraw(draw_name="main1tv3")
	TextView main1tv3;
	ArrayList<String> function, units=new ArrayList<String>();
	Button[] funbt=new Button[5];
	LinearLayout main1ll1;
	HorizontalScrollView main1hsv1;
	ArrayList<TextView> tvarray=new ArrayList<TextView>();	
	PopupWindow unitpop;
	View unitview;
	LayoutInflater flater;
	@InjectStateDraw(draw_name="unitpopbg")
	ListView unitpoplv1;
	SQLiteDatabase db;
	//float TextSize=0;
	Judge judge;
	Main1Inputtext main1inputtext=new Main1Inputtext();			
	Globe globe=new Globe();	
	TextView pbCal;
	@InjectStateDraw(draw_name="main1rl2")
	TextView pbCal1;	
	//SkinManage skinManage;
	long systime=0;
	
	@InjectStateDraw(draw_name="btbg2")
	Button funbt1;
	@InjectStateDraw(draw_name="btbg2")
	Button funbt2;
	@InjectStateDraw(draw_name="btbg2")
	Button funbt3;
	@InjectStateDraw(draw_name="btbg2")
	Button funbt4;
	@InjectStateDraw(draw_name="btbg2")
	Button funbt5;
	
	@InjectStateDraw(draw_name="btbg1")
	Button bts1;
	@InjectStateDraw(draw_name="btbg1")
	Button bts2;
	@InjectStateDraw(draw_name="btbg")
	Button bts3;
	@InjectStateDraw(draw_name="btbg1")
	Button bts4;
	@InjectStateDraw(draw_name="btbg1")
	Button bts5;
	@InjectStateDraw(draw_name="btbg1")
	Button bts6;
	@InjectStateDraw(draw_name="btbg")
	Button bts7;
	@InjectStateDraw(draw_name="btbg")
	Button bts8;
	@InjectStateDraw(draw_name="btbg")
	Button bts9;
	@InjectStateDraw(draw_name="btbg1")
	Button bts10;
	@InjectStateDraw(draw_name="btbg1")
	Button bts11;
	@InjectStateDraw(draw_name="btbg")
	Button bts12;
	@InjectStateDraw(draw_name="btbg")
	Button bts13;
	@InjectStateDraw(draw_name="btbg")
	Button bts14;
	@InjectStateDraw(draw_name="btbg1")
	Button bts15;
	@InjectStateDraw(draw_name="btbg1")
	Button bts16;
	@InjectStateDraw(draw_name="btbg")
	Button bts17;
	@InjectStateDraw(draw_name="btbg")
	Button bts18;
	@InjectStateDraw(draw_name="btbg")
	Button bts19;
	@InjectStateDraw(draw_name="btbg1")
	Button bts20;
	@InjectStateDraw(draw_name="btbg2")
	Button bts21;
	@InjectStateDraw(draw_name="btbg2")
	Button bts22;
	@InjectStateDraw(draw_name="btbg2")
	Button bts23;
	@InjectStateDraw(draw_name="btbg2")
	Button bts24;
	@InjectStateDraw(draw_name="btbg2")
	Button bts25;
	@InjectStateDraw(draw_name="btbg2")
	Button bts26;
	@InjectStateDraw(draw_name="btbg2")
	Button bts27;
	@InjectStateDraw(draw_name="btbg2")
	Button bts28;
	@InjectStateDraw(draw_name="btbg2")
	Button bts29;
	@InjectStateDraw(draw_name="btbg2")
	Button bts30;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
  	   //statusBarHeight=getStatusHeight(this); 	   
       DisplayMetrics  dm = new DisplayMetrics();    
 	   getWindowManager().getDefaultDisplay().getMetrics(dm);    
 	   pwidth = dm.widthPixels;              
 	   pheight = dm.heightPixels/*-statusBarHeight*/;
 	   SkinManage.MODECHANGE=true;
 	  
 	   addButtons();
 	   
 	   /*int ds1=pwidth-pwidth*50/254*5-pwidth/254*4;
 	   ds=ds1-ds1/5;
 	   btw1=pwidth*50/254+ds1/5;bth1=pwidth*10/54;btspace=pwidth/254;*/
 	   
 	   ds=1;
 	   btw1=(pwidth-4*ds)/5;
 	   bth1=pheight*15/(24*6);
 	   //bth1=btw1*5/6;	
 	   btspace=ds;
 	   ct=this;	   
 	   flater=LayoutInflater.from(ct);
 	   for(int i=0;i<6;i++){
 		  globe.unitseleid.add(-1);
 		  globe.unitseleidStr.add("");
 	   }
 	  units.add("长    度");units.add("质    量");units.add("面    积");units.add("体    积");units.add("压    力");
 	  units.add("流    量");
 	  funet1=new EditText(this);funet1.setFocusableInTouchMode(false);funet1.setFocusable(false);
 	  funet1.setTextColor(Color.WHITE);funet1.setBackgroundDrawable(null);
 	 
 	  createSkin();
      init();
      getset();
       /*π=customvalues.π;
       e=customvalues.e;*/     
    }
    
    private void addButtons() {
		// TODO Auto-generated method stub
    	funbt1=new Button(this);funbt2=new Button(this);funbt3=new Button(this);funbt4=new Button(this);funbt5=new Button(this);
    	funbt[0]=funbt1;funbt[1]=funbt2;funbt[2]=funbt3;funbt[3]=funbt4;funbt[4]=funbt5;
    	
    	bts1=new Button(this);bts2=new Button(this);bts3=new Button(this);bts4=new Button(this);bts5=new Button(this);
    	bts6=new Button(this);bts7=new Button(this);bts8=new Button(this);bts9=new Button(this);bts10=new Button(this);
    	bts11=new Button(this);bts12=new Button(this);bts13=new Button(this);bts14=new Button(this);bts15=new Button(this);
    	bts16=new Button(this);bts17=new Button(this);bts18=new Button(this);bts19=new Button(this);bts20=new Button(this);
    	bts21=new Button(this);bts22=new Button(this);bts23=new Button(this);bts24=new Button(this);bts25=new Button(this);
    	bts26=new Button(this);bts27=new Button(this);bts28=new Button(this);bts29=new Button(this);bts30=new Button(this);
    	globe.btarray.add(bts1);globe.btarray.add(bts2);globe.btarray.add(bts3);globe.btarray.add(bts4);globe.btarray.add(bts5);
    	globe.btarray.add(bts6);globe.btarray.add(bts7);globe.btarray.add(bts8);globe.btarray.add(bts9);globe.btarray.add(bts10);
		globe.btarray.add(bts11);globe.btarray.add(bts12);globe.btarray.add(bts13);globe.btarray.add(bts14);globe.btarray.add(bts15);
		globe.btarray.add(bts16);globe.btarray.add(bts17);globe.btarray.add(bts18);globe.btarray.add(bts19);globe.btarray.add(bts20);
		globe.btarray.add(bts21);globe.btarray.add(bts22);globe.btarray.add(bts23);globe.btarray.add(bts24);globe.btarray.add(bts25);
		globe.btarray.add(bts26);globe.btarray.add(bts27);globe.btarray.add(bts28);globe.btarray.add(bts29);globe.btarray.add(bts30);
	}

	private void createSkin() {
		// TODO Auto-generated method stub
    	//Log.e("", "createSkin---------->"+SkinManage.loadAssetsSkin("", ct));
    	//skinManage=new SkinManage("Default0", ct);
	}

	private void getset() {
		// TODO Auto-generated method stub
    	main1inputtext.ct=ct;
    	main1inputtext.funet1=funet1;   	    	
    	main1inputtext.main1et1=main1et1;
    	main1inputtext.globe=globe;
    	main1inputtext.judge=judge;
    	main1inputtext.main1hsv1=main1hsv1;
    	main1inputtext.main1ll1=main1ll1;
    	main1inputtext.main1tv4=main1tv4;
    	
    	globe.calculate.globe=globe;
    	globe.calculate.ct=ct;
    	globe.calculate.db=db;
    	globe.calculate.unitseleid=globe.unitseleid;
    	globe.calculate.demic=globe.demic;
    	globe.calculate.degrees=globe.degrees;  
    	
    	
    	globe.rln1=rln1;
    	globe.pbCal=pbCal;
    	globe.pbCal1=pbCal1;
    	globe.main1et1=main1et1;
    	globe.main1inputtext=main1inputtext;
    	globe.main1tv4=main1tv4;
    	
	}

	public static int getStatusHeight(Activity activity){  
        int statusHeight1 = 0;  
        Rect localRect = new Rect();  
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);  
        statusHeight1 = localRect.top;  
        if (0 == statusHeight1){  
            Class<?> localClass;  
            try {  
                localClass = Class.forName("com.android.internal.R$dimen");  
                Object localObject = localClass.newInstance();  
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());  
                statusHeight1 = activity.getResources().getDimensionPixelSize(i5);  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return statusHeight1;  
    }  
    
    private void init(){
    	   	
    	File f=new File(Environment.getExternalStorageDirectory()+"/gqb");
    	File f1=new File(Environment.getExternalStorageDirectory()+"/gqb/gqb.db");
    	if(!f.exists()){
    		f.mkdir();
    		dboutput();
    	}if(f.exists()){   		
    		if(!f1.exists()){
    			dboutput();
    		}
    	}
    	
    	db = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory()+"/gqb/gqb.db", null);   	
    	getfunname();
    	maxfunid=function.size()/5+1;
    	judge=new Judge();
    	
    	rln3=new RelativeLayout(this);
    	rln1=(RelativeLayout) findViewById(R.id.main1rln1);	
    	main1et1=(EditText) findViewById(R.id.main1et1);
    	
    	//rln1.setBackgroundDrawable(skinManage.loadMain1BgSkin());
    	rln1.removeView(main1et1);
    	rln1.addView(rln3,pwidth,pheight-6*bth1-5*btspace-bth1*4/7);
    	rln1.addView(main1et1,pwidth-12,pheight-6*bth1-5*btspace-bth1*4/7);
    	main1et1.setX(6);
    	//rln3.setBackgroundDrawable(skinManage.loadMain1Et1Skin());
    	etsize=(int) (main1et1.getTextSize()+4);
    	btsize=(int) (main1et1.getTextSize());
    	main1et1.setTextSize(TypedValue.COMPLEX_UNIT_PX, etsize);
    	
    	for(int i=0;i<30;i++){
    		if(i==2 || i==6 || i==7 || i==8 || i==11 || i==12 || i==13 || i==16 || i==17 || i==18){
    			//bt.setBackgroundResource(R.drawable.btbg);
    			//globe.btarray.get(i).setBackgroundDrawable(skinManage.loadBtbgSkin());
    		}if(i==0 || i==1 || i==3 || i==4 || i==9 || i==14 || i==19 || i==5 || i==10 || i==15){
    			//bt.setBackgroundResource(R.drawable.btbg2);
    			//globe.btarray.get(i).setBackgroundDrawable(skinManage.loadBtbg1Skin());
    		}if(i==20  || i==21 || i==22 || i==23 || i==24 || i==25 || i==26 || i==27 || i==28 || i==29){
    			//bt.setBackgroundResource(R.drawable.btbg1);
    			//globe.btarray.get(i).setBackgroundDrawable(skinManage.loadBtbg2Skin());
    		}
    		
    		if(i<=19 || i>=25){
    			globe.btarray.get(i).setText(strarray[i]);
    		}else{
    			globe.btarray.get(i).setText(function.get(i-20));
    		}
    		globe.btarray.get(i).setTextColor(Color.WHITE);///bt.setTextAppearance(context, resid)
    		globe.btarray.get(i).setTypeface(Typeface.SERIF);globe.btarray.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, btsize);   		
    		//globe.btarray.add(bt); 
            if(i%5==4){
            	rln1.addView(globe.btarray.get(i),btw1+ds,bth1);  
    		}else{
    			rln1.addView(globe.btarray.get(i),btw1,bth1);
    		}
            globe.btarray.get(i).setX((i%5*(btw1+btspace)));globe.btarray.get(i).setY(pheight-(i/5+1)*bth1-i/5*btspace);
            globe.btarray.get(i).setOnClickListener(new main1btclick());
    	}
    	
    	for(int i=0;i<5;i++){
    		funbt[i].setTextColor(Color.WHITE);///bt.setTextAppearance(context, resid)
    		funbt[i].setTypeface(Typeface.SERIF);funbt[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, btsize); 
    		//funbt[i].setBackgroundDrawable(skinManage.loadBtbg2Skin());
    		if(i%5==4){
            	rln1.addView(funbt[i],btw1+ds,bth1);  
    		}else{
    			rln1.addView(funbt[i],btw1,bth1);
    		}
    		funbt[i].setX((i%5*(btw1+btspace)));funbt[i].setY(pheight-5*bth1-4*btspace);
    		funbt[i].setVisibility(View.INVISIBLE);
    	}
    	
    	for(int i=25;i<=29;i++){
    		rln1.removeView(globe.btarray.get(i));
    		rln1.addView(globe.btarray.get(i));
    	}for(int i=15;i<=19;i++){
    		rln1.removeView(globe.btarray.get(i));
    		rln1.addView(globe.btarray.get(i));
    	}
    	globe.btarray.get(29).setOnLongClickListener(new btlong());
    	rln2=new RelativeLayout(this);
    	rln1.addView(rln2,RelativeLayout.LayoutParams.FILL_PARENT,bth1*4/7);
    	//rln2.setBackgroundResource(R.drawable.main1bgrl2);
    	//rln2.setBackgroundDrawable(skinManage.loadMain1Rl2Skin());
    	rln2.setY(globe.btarray.get(25).getY()-bth1*4/7);
    	main1tv1=new TextView(this);main1tv1.setBackgroundResource(R.drawable.main1tv1a);
    	main1tv2=new TextView(this);//main1tv2.setBackgroundDrawable(skinManage.loadMain1Tv2Skin());
    	main1tv3=new TextView(this);//main1tv3.setBackgroundDrawable(skinManage.loadMain1Tv3Skin());
    	main1tv4=new TextView(this);main1tv4.setGravity(Gravity.CENTER);main1tv4.setText("长度");
    	main1tv1.setGravity(Gravity.CENTER);
    	rln2.addView(main1tv1, bth1*3/5,RelativeLayout.LayoutParams.FILL_PARENT);main1tv1.setClickable(true);
    	rln2.addView(main1tv2, bth1*3/5,RelativeLayout.LayoutParams.FILL_PARENT);main1tv2.setX(bth1*3/5);
    	rln2.addView(main1tv3, bth1*3/5,RelativeLayout.LayoutParams.FILL_PARENT);main1tv3.setClickable(true);main1tv3.setX(pwidth-bth1*6/5);
    	rln2.addView(main1tv4, bth1*3/5,RelativeLayout.LayoutParams.FILL_PARENT);main1tv4.setClickable(true);main1tv4.setX(pwidth-bth1*3/5);   	
    	main1tv1.setOnClickListener(new tvclick());main1tv4.setOnClickListener(new tvclick());
    	main1tv2.setOnClickListener(new tvclick());main1tv3.setOnClickListener(new tvclick());
    	main1tv2.setFocusable(false);main1tv2.setFocusableInTouchMode(false);main1tv2.setClickable(false);main1tv2.setActivated(false);
    	main1tv3.setActivated(true);  	
    	
    	int[] colors = new int[] { Color.rgb(255, 102, 0),Color.rgb(255, 255, 255)};  
        int[][] states = new int[2][]; 
        states[0] = new int[] {android.R.attr.state_pressed};  
        states[1] = new int[] {};         
		ColorStateList colorList = new ColorStateList(states, colors);
		main1tv4.setTextColor(colorList);
				
		main1ll1=(LinearLayout) findViewById(R.id.main1ll1);
		main1hsv1=(HorizontalScrollView) findViewById(R.id.main1hsv1);
		rln1.removeView(main1hsv1);rln2.addView(main1hsv1,pwidth-bth1*12/5,bth1*4/7);
		main1hsv1.setX(bth1*6/5);		
		anim1(0,30,700);
		
		unitview=flater.inflate(R.layout.unitpop, null);	
		unitpoplv1=(ListView) unitview.findViewById(R.id.unitpoplv1);
		unitpoplv1.setAdapter((ListAdapter) new ArrayAdapter<String>(this,  
                R.layout.unitpopstyle,units));
		unitpoplv1.setOnItemClickListener(new unititem());
		//unitpoplv1.setBackgroundDrawable(skinManage.loadPopLv1BgSkin());
			
		getunitname();
		/*Cursor c = db.rawQuery("select * from unit_table", null);
		//stripTrailingZeros()科学记数		
		 *bb2.toPlainString()全部显示	
		while(c.moveToNext()){
			BigDecimal bb2=new BigDecimal(c.getString(2));	//bb2.p	
			Log.e("", c.getString(0)+"<==>"+c.getString(1)+"<==>"+bb2.toPlainString()+"<==>"+c.getString(3));
		}*/		
		Class<EditText> cls=EditText.class;
		try {
			Method seinputsoft=cls.getMethod("setShowSoftInputOnFocus", boolean.class);
			seinputsoft.setAccessible(false);
			seinputsoft.invoke(main1et1, false);	
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		pbCal=new TextView(ct);pbCal.setBackgroundResource(R.drawable.calpb);
		rln2.addView(pbCal,pwidth-bth1*12/5,bth1*4/7);
		pbCal.setX(bth1*6/5);pbCal.setVisibility(View.INVISIBLE);
		pbCal1=new TextView(ct);rln2.addView(pbCal1,bth1*6/5,RelativeLayout.LayoutParams.FILL_PARENT);
		pbCal1.setX(pwidth-bth1*6/5+2); pbCal1.setText("取消操作");pbCal1.setGravity(Gravity.CENTER);
		pbCal1.setTextColor(colorList);pbCal1.setClickable(true);
		//pbCal1.setBackgroundResource(R.drawable.main1bgrl2);
		//pbCal1.setBackgroundDrawable(skinManage.loadMain1Rl2Skin());
		pbCal1.setOnClickListener(new pbcal1click());pbCal1.setVisibility(View.INVISIBLE);
		
		/*btCal=new Button(ct);btCal.setText("取消");//btCal.setBackgroundResource(R.drawable.)
		rln2.addView(btCal, bth1*3/5,RelativeLayout.LayoutParams.FILL_PARENT);btCal.setX(pwidth-bth1*3/5); 
		btCal.setVisibility(View.INVISIBLE);*/
		
    }
    
    class pbcal1click implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (globe.cdTask!=null) {
				globe.cdTask.cancel(true);
				globe.cdTask.cancer=true;
				globe.cdTask=null;
			}
			Log.e("", "pbcal1click====>");
		}
    	
    }
    
    class btlong implements OnLongClickListener{

		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			
			if(main1et1.getSelectionStart()!=-1 && !globe.isfuning && !globe.isfun1save && globe.input==1){
				main1et1.getText().delete(0, main1et1.getSelectionStart());
			}							
			Vibrator vibrator = (Vibrator)ct.getSystemService(Service.VIBRATOR_SERVICE);
			long[] pattern = {0, 1, 20, 21}; 
			vibrator.vibrate(pattern,-1);
			main1inputtext.etcursor();
			return true;
		}    	
    }
    
    private void getfunname(){
    	function=new ArrayList<String>();
    	Cursor c = db.rawQuery("select * from fun_table", null);
    	while(c.moveToNext()){
    		function.add(c.getString(c.getColumnIndexOrThrow("funname")));
    	}
    }
    
    private void getunitname(){
    	main1ll1.removeAllViews();
    	Cursor c = db.rawQuery("select * from unit_table where unittype=?", new String[]{main1tv4.getText().toString()});
    	while(c.moveToNext()){
    		String s="";
    		if(unitnameshow.equals("all")){
    			 s=c.getString(c.getColumnIndexOrThrow("unitname"))+"("+
    	    	         c.getString(c.getColumnIndexOrThrow("unitsimple"))+")";
    		}if(unitnameshow.equals("zh")){
    			 s=c.getString(c.getColumnIndexOrThrow("unitname"));
    		}if(unitnameshow.equals("uk")){
    			s=c.getString(c.getColumnIndexOrThrow("unitsimple"));
    		}
    		TextView unit=new TextView(this);unit.setGravity(Gravity.CENTER);
    		unit.setTextSize(TypedValue.COMPLEX_UNIT_PX,(unit.getTextSize()*19/20));
    		unit.setText(" "+s+" ");unit.setTextColor(Color.WHITE);unit.setClickable(true);
			main1ll1.addView(unit,RelativeLayout.LayoutParams.WRAP_CONTENT,bth1*4/7);
			unit.setOnClickListener(new unitclick());
    	}
    	c.close();
    }
    
    private void dboutput(){
    	try {
			InputStream is = getBaseContext().getAssets().open("gqb.db");
			OutputStream os = new FileOutputStream(Environment.getExternalStorageDirectory()+"/gqb/gqb.db");
			byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            // 关闭文件流
            os.flush();
            os.close();
            is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    class unititem implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			if (globe.input==1) {
				if(!main1tv4.getText().equals(units.get(arg2).replace(" ", "")) && !globe.isfuning){
					main1tv4.setText(units.get(arg2).replace(" ", ""));
					getunitname();
					if(globe.unitseleid.get(arg2)!=-1){
						TextView tv=(TextView) main1ll1.getChildAt(globe.unitseleid.get(arg2));
						tv.setTextColor(Color.rgb(105, 251, 254));
					}				
				}		
				if(unitpop!=null){
					unitpop.dismiss();
				}		
			}
				
		}
    }
    
     class unitclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub	
			
			if (globe.input==1) {
				String s=main1tv4.getText().charAt(0)+"    "+main1tv4.getText().charAt(1);
				int a=units.indexOf(s);	
				TextView tv=(TextView) v;
				if(globe.unitseleid.get(a)!=main1ll1.indexOfChild(v)){				
					tv.setTextColor(Color.rgb(105, 251, 254));
					if(globe.unitseleid.get(a)!=-1){
						TextView tv1=(TextView) main1ll1.getChildAt(globe.unitseleid.get(a));
						tv1.setTextColor(Color.WHITE);
					}				
					globe.unitseleid.set(a, main1ll1.indexOfChild(v));
					globe.unitseleidStr.set(a,tv.getText().toString());
				}				
				addunit(tv.getText().toString());	
			}
					
		}  		
    }
    
     private void addunit(String s){
    	 if(!globe.isfun1save && !globe.isfuning){
    		 if(main1et1.getText().length()>0 && main1et1.getText().length()>=main1et1.getSelectionStart()){
    			 if(main1et1.getSelectionStart()>0 && main1et1.getText().length()>(main1et1.getSelectionStart())){
    				 String s1=""+main1et1.getText().charAt((main1et1.getSelectionStart()-1));
    				 String s2=""+main1et1.getText().charAt((main1et1.getSelectionStart()));
    				 if(s1.matches("[0123456789eπ\\)]") && s2.matches("[\\)\\+\\-×÷]")){    					 
    					 s=addunitdeal(s);
    					 main1inputtext.addSpannableStr(main1et1.getSelectionStart(),s,this.getResources().getColor(R.color.strpan1),0); 
    				 }    				 
    			 }if(main1et1.getSelectionStart()>0 && main1et1.getText().length()==main1et1.getSelectionStart()){
    				 Log.e("", main1et1.getSelectionStart()+"<<<<unit3>>>>"+ main1et1.getSelectionStart());
    				 String s1=""+main1et1.getText().charAt((main1et1.getSelectionStart()-1));
    				 if(s1.matches(".*[0123456789eπ\\)].*")){
    					 s=addunitdeal(s);
    					 main1inputtext.addSpannableStr(main1et1.getSelectionStart(),s,this.getResources().getColor(R.color.strpan1),0); 
    				 }
    			 }
    		 }   		
    		 Log.e("", main1et1.getSelectionStart()+"<<<<unit>>>>"+ main1et1.getText().length());}}
    	 
     
     private String addunitdeal(String s){
    	 //s=s.replace(" ", "");
    	 String s1="";
    	 int a1=s.indexOf("(");
    	 int a2=s.indexOf(")");
    	 if(globe.unitshowtype.equals("zh")){
    		 for (int i = 0; i < s.length(); i++) {
				if(i<a1 || i>a2){
					s1=s1+s.charAt(i);
				}
			}
    	 }if(globe.unitshowtype.equals("us")){
    		 for (int i = 0; i < s.length(); i++) {
 				if(i>a1 && i<a2){
 					s1=s1+s.charAt(i);
 				}
 			}
    	 }   	 
    	 return s1;
     }
     
    class tvclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (globe.input==1) {
				if(v==main1tv1){						
					if(!soundinput){
						main1tv1.setBackgroundResource(R.drawable.main1tv1b);
					}if(soundinput){
						main1tv1.setBackgroundResource(R.drawable.main1tv1a);
					}
					soundinput=!soundinput;
				}if(v==main1tv2){
					if(funid==maxfunid){
						main1tv3.setClickable(true);
						//main1tv3.setBackgroundResource(R.drawable.main1tv3);
						main1tv3.setActivated(true);
					}if(funid>1){
						funid=funid-1;
						funchange("down");
					}if(funid==1){
						//main1tv2.setBackgroundResource(R.drawable.main1tv2c);
						Log.e("", "oooo");
						main1tv2.setActivated(false);
						main1tv2.setFocusable(false);main1tv2.setFocusableInTouchMode(false);main1tv2.setClickable(false);
					}			
				}if(v==main1tv3){
					if(funid==1){
					    main1tv2.setClickable(true);main1tv2.setActivated(true);
						//main1tv2.setBackgroundResource(R.drawable.main1tv2);
					}if(funid!=maxfunid){
						funid=funid+1;
						funchange("up");
					}if(funid==maxfunid){
						//main1tv3.setBackgroundResource(R.drawable.main1tv3c); 
						main1tv3.setActivated(false);
						main1tv3.setFocusable(false);main1tv3.setFocusableInTouchMode(false);main1tv3.setClickable(false);
					}	
				}if(v==main1tv4){
					showunitpop();
				}
			}
			
		}}
    
    private void showunitpop(){
    	if(unitpop!=null){
    		unitpop.dismiss();
    	}
    	unitpop=new PopupWindow(unitview,bth1*23/20,RelativeLayout.LayoutParams.WRAP_CONTENT, true);
    	unitpop.setBackgroundDrawable(new BitmapDrawable());		
    	unitpop.showAsDropDown(main1tv4,-bth1*11/20-2,0);
    	unitpop.setOutsideTouchable(true);  		          
    	unitpop.setFocusable(true);		 
    }
    
    private void funchange(String s){ 
    	if(s.equals("up")){
    		for(int i=20;i<25;i++){ 
    			funbt[i-20].setText(globe.btarray.get(i).getText());
    			if(funid<maxfunid){
    				globe.btarray.get(i).setText(function.get(5*(funid-1)+i-20));
    			}if(funid==maxfunid){
    				int a=function.size()%5-1; 
        			if((i-20)<=a){
        				globe.btarray.get(i).setText(function.get(5*(funid-1)+(i-20)));
        			}if((i-20)>a){   
        				globe.btarray.get(i).setText("?");
        			}     		
    			}
    		}
    		btanimup(-bth1);
    	}if(s.equals("down")){   		
    		for(int i=20;i<25;i++){ 
    			funbt[i-20].setText(globe.btarray.get(i).getText());
    			globe.btarray.get(i).setText(function.get(5*(funid-1)+i-20));
    		}    		
    		btanimup(bth1);
    	} 
    	funbt[0].setVisibility(View.VISIBLE);funbt[1].setVisibility(View.VISIBLE);
		funbt[2].setVisibility(View.VISIBLE);funbt[3].setVisibility(View.VISIBLE);funbt[4].setVisibility(View.VISIBLE);
		
		anim1(20,25,500);
    }
    
    private void btanimup(int a){
    	TranslateAnimation ta=new TranslateAnimation(0, 0, 0, a);
    	ta.setDuration(300);
    	ta.setFillAfter(true);
    	for(int i=0;i<=4;i++){
    		funbt[i].startAnimation(ta);
    	}
    	ta.setAnimationListener(new upanim());
    }
    
    class upanim implements AnimationListener{

		@Override
		public void onAnimationEnd(Animation animation) {
			// TODO Auto-generated method stub
			for(int i=0;i<=4;i++){
				funbt[i].setVisibility(View.INVISIBLE);
				funbt[i].clearAnimation();
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    class main1btclick implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub	
			if (globe.input==1) {
				String s="";
		    	   if(main1et1.getSelectionStart()>0){
		    		   s=""+main1et1.getText().charAt(main1et1.getSelectionStart()-1);
		    	   }
				if(globe.btarray.indexOf(arg0)==20 || globe.btarray.indexOf(arg0)==21 || globe.btarray.indexOf(arg0)==22
						 || globe.btarray.indexOf(arg0)==23 || globe.btarray.indexOf(arg0)==24){
					Button b=(Button) arg0;
					if(!s.equals("0") && !s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4") && !s.equals("5")
							 && !s.equals("6") && !s.equals("7") && !s.equals("8") && !s.equals("9") && !s.equals(".")
							 && !s.equals("e") && !s.equals("π") && !s.equals(")") && !s.equals("n")){
						if (b.getText().toString().equals("π") || b.getText().toString().equals("e")) {
							main1inputtext.addSpannableStr(main1et1.getSelectionStart(),b.getText().toString(),
															ct.getResources().getColor(R.color.strpan1),0);
						}if (!b.getText().toString().equals("π") && !b.getText().toString().equals("e") && !globe.isfuning){
							funbtclick(b.getText().toString(),s);
						}
					   
					}				
				}else{
						try {
							main1inputtext.inputtext(globe.btarray.indexOf(arg0),s);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Log.e("", ">>>>>>>>>>>>>>"+globe.btarray.indexOf(arg0));
				}
			}
			  
			/* {"n",".","0","=","+","√","1","2","3","-","^","4","5","6","×",
            "( )","7","8","9","÷","sin","cos","tan","log","ln","×","π","∑","e","/c"}*/
			
			/*1.∑()+
			2.∑()[,,]+
			3.[1/N]∑()+
			4.[1/N]∑()[,,]+
			5.()+*/
			
		}  	
    }
    
    private void funbtclick(String etstr, String s){

    	/*for (int i = 0; i < 100; i++) {
			pbCal.setWidth((pwidth-bth1*12/5)*i/100);
		}*/   	
    	
    	int a=0;
    	if(etstr.equals("sin") || etstr.equals("cos") || etstr.equals("tan") || etstr.equals("ln") || etstr.equals("asin")
    			|| etstr.equals("acos") || etstr.equals("atan") || etstr.equals("log")){  
    		a=1;
    		if(!globe.isfun1save){
    			if(!etstr.equals("log")){
    				main1inputtext.addSpannableStr(main1et1.getSelectionStart(),etstr,this.getResources().getColor(R.color.strpan1),0);  
            		main1inputtext.addSpannableStr(main1et1.getSelectionStart(),"()",this.getResources().getColor(R.color.strpan3),1);
    			}if(etstr.equals("log")){
    				main1inputtext.addSpannableStr(main1et1.getSelectionStart(),etstr,this.getResources().getColor(R.color.strpan1),0);  
    	    		main1inputtext.addSpannableStr(main1et1.getSelectionStart(),"(,)",this.getResources().getColor(R.color.strpan3),2);
    			}    			        		
    		}if(globe.isfun1save){
    			     
    			a=1;
    			int funa=main1et1.getText().toString().indexOf("{");
    			int funb=main1et1.getText().toString().indexOf("}");
    			String funs1=main1et1.getText().toString().substring(funa, funb+1); 
    			int funa1=funs1.indexOf("(")+funa;
    			int funb1=funs1.lastIndexOf(")")+funa;     			
    			  			    			
    			if(((funs1.indexOf("(")!=-1 && funs1.lastIndexOf(")")!=-1 && funa1<funb1 && funb1<funb 
    					&& funa1>funa) || (funs1.indexOf("(")==-1 && funs1.lastIndexOf(")")==-1)) 
    					&& main1et1.getSelectionStart()>funa1 && main1et1.getSelectionStart()<=funb1){
    				
    				if(!etstr.equals("log")){
    					main1inputtext.addSpannableStr(main1et1.getSelectionStart(),etstr,this.getResources().getColor(R.color.strpan1),0);  
                		main1inputtext.addSpannableStr(main1et1.getSelectionStart(),"()",this.getResources().getColor(R.color.strpan3),1);
        			}if(etstr.equals("log")){
        				main1inputtext.addSpannableStr(main1et1.getSelectionStart(),etstr,this.getResources().getColor(R.color.strpan1),0);  
        	    		main1inputtext.addSpannableStr(main1et1.getSelectionStart(),"(,)",this.getResources().getColor(R.color.strpan3),2);
        			}
    			}
    		}
    	}
    	int b=globe.funtype;
    	System.out.println(b);
    	if(etstr.matches("(X|Y|AVG)") && (globe.funtype==1 || globe.funtype==3 || globe.funtype==5)){
    		int left=main1et1.getText().toString().indexOf("(");
    		int right=main1et1.getText().toString().lastIndexOf(")");
    		int middle=main1et1.getSelectionStart();
    		int m=main1et1.getText().toString().indexOf("∑");
    		if(globe.isfun1save && middle>left && middle<=right && etstr.matches("(X|Y)")){
    			main1inputtext.addSpannableStr(main1et1.getSelectionStart(),etstr,this.getResources().getColor(R.color.strpan1),0);  
    		}if(globe.isfun1save && middle>left && middle<=right && etstr.matches("(AVG)") && m!=-1 && globe.funtype!=0){
    			main1inputtext.addSpannableStr(main1et1.getSelectionStart(),etstr,this.getResources().getColor(R.color.strpan1),0);  
    		}
    		a=1;
    	}if(etstr.equals("{+}")){
    		if(!globe.isfun1save){
    		}
    		a=1;
    	}if(a==0 && !globe.isfuning && !globe.isfun1save){
    		   
    		globe.funnote =""; globe.n=1;globe.allfunxy=new ArrayList<Integer>();  		
    		Cursor c=db.rawQuery("select * from fun_table where funname=?", new String[]{etstr});
    		while(c.moveToNext()){
    			globe.funnote =c.getString(c.getColumnIndexOrThrow("funnote"));
    			Log.e("", "otherfun>>>"+c.getString(c.getColumnIndexOrThrow("funnote")));
    		}   		    		
    		//String s1="[1/N]∑(X+12.8×2-Y)[1,1,9]+";
    		/*String s1="∑(X+12.8×2-Y)+";
			funnote=s1;*/   			
    		int funa3=globe.funnote.lastIndexOf("["); 
    		int funa4=globe.funnote.lastIndexOf("∑"); 
    		int funa5=globe.funnote.indexOf("[");   		
    		main1inputtext.funtype(funa3, funa4, funa5);
    		if(globe.funtype==1 || globe.funtype==3){
    			
    			main1inputtext.funing(etstr);
    			 /* {"n",".","0","=","+","√","1","2","3","-","^","4","5","6","×",
                "( )","7","8","9","÷","sin","cos","tan","log","ln","×","π","∑","e","c"}*/
    			
    		}if(globe.funnote .indexOf("∑")==-1){
    			
    		}
    	}
    	main1inputtext.etcursor();
    	
    	/*double d=12345678912345678912345780934374683872189D;
    	BigDecimal bd = new BigDecimal(d); 
    	bd = bd.setScale(0,BigDecimal.ROUND_HALF_UP);
    	Log.e("", s+bd.doubleValue());
    	Log.e("", s+new BigDecimal(Math.sin(33.0*Math.PI/180)).setScale(14, BigDecimal.ROUND_HALF_UP).doubleValue());*/
    	
    	/*BigDecimal bd2=new BigDecimal(matcher1.group().substring(1,matcher1.group().length()));
		MathContext mc=new MathContext(0, RoundingMode.HALF_UP);//bd2.
		bd2=bd2.pow(2, mc);*/
    }

    private void anim1(int b,int c,int time){
    	for(int i=b;i<c;i++){
    		ScaleAnimation sa1 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
   	             Animation.RELATIVE_TO_PARENT, (float) ((globe.btarray.get(i).getX()+btw1/2*1.0)/pwidth), 
   	             Animation.RELATIVE_TO_PARENT, (float) ((globe.btarray.get(i).getY()+bth1*1.0/2)/pheight)); 
    		sa1.setInterpolator(new DecelerateInterpolator());
    		sa1.setDuration(time);
    		globe.btarray.get(i).startAnimation(sa1);		
    	}
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (event.getKeyCode()==KeyEvent.KEYCODE_BACK) {
			if(System.currentTimeMillis()-systime<400){
				finish();
			}else{
				systime=System.currentTimeMillis();
				Toast.makeText(ct, "再按一次退出", Toast.LENGTH_SHORT).show();
			}
			
		}if (event.getKeyCode()==KeyEvent.KEYCODE_MENU) {
			//Log.e("", "===>KEYCODE_MENU");
			Intent intent=new Intent(this, SettingActivity.class);
			startActivity(intent);
			overridePendingTransition(R.anim.slide_in_right,R.anim.slide_no);    
		}
		return false;
	}
    
    
    
    
}
