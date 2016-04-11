package com.emple.activity;

import java.math.BigDecimal;

import com.emple.calculatorqb.Globe;
import com.emple.calculatorqb.R;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.SuperscriptSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ElementListActivity extends Activity implements OnClickListener{

	private RelativeLayout eles_rl;
	private Context mcontext;
	private int pwidth;
	private int pheight;
	private LinearLayout[] rows=new LinearLayout[9];
	private SQLiteDatabase db;
	private ImageView eles_iv1;
	private LayoutInflater inflater;
	private RelativeLayout rl_main,rl_ele1;
	private int touchedEleX,touchedEleY,rawtouchedEleX,rawtouchedEleY;
	private float touchDownX,touchDownY;
	private RelativeLayout frontTouchedEle,touchedEle;
	private int EleWidth,EleHeight;
	private SpannableString[] subSpan;
	
	private TextView ele1_electronic_structure,ele1_num,ele1_symble,ele1_name_zh,
		ele1_name_us,relative_atomic_mass1,ele1_name_margin;
	private TextView[] tv_configs;
	private ImageView ele1_radioactive;
	private TextView ele1_density,ele1_melting,ele1_boil,ele1_volume,
		ele1_radius,covalent_radius,ionic_radius,ele1_density_note,
		specific_heat,heat_vaporization,heat_fusion,electrical_resistivity,
		electronegativity,first_ionization_energy,first_electron_affinity;
	
	private long touchtime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics  dm = new DisplayMetrics();    
	 	getWindowManager().getDefaultDisplay().getMetrics(dm);    
	 	pwidth = dm.widthPixels;              
	 	pheight = dm.heightPixels;
	 	Globe.pwidth=pwidth;
	 	Globe.pheight=pheight;
	 	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_elements);
		
		db = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory()+"/gqb/gqb.db", null);   	
		mcontext = this;
		eles_rl=(RelativeLayout) findViewById(R.id.eles_rl);
		eles_iv1=new ImageView(mcontext);
		eles_iv1.setBackgroundResource(R.drawable.atomic);
		eles_rl.addView(eles_iv1,RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		inflater = LayoutInflater.from(mcontext);
		
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(500);
					handler.sendEmptyMessage(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	private void addViews() {
		rl_main=(RelativeLayout) inflater.inflate(R.layout.activity_elements_main, null);
		rl_ele1=(RelativeLayout) inflater.inflate(R.layout.ele_layout1, null);
		
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE); 
		eles_rl.addView(rl_main,params);
		eles_rl.addView(rl_ele1,RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		tv_configs=new TextView[7];
		ele1_name_margin=(TextView)rl_ele1.findViewById(R.id.ele1_name_margin);
		ele1_electronic_structure=(TextView)rl_ele1.findViewById(R.id.ele1_electronic_structure);
		ele1_num=(TextView)rl_ele1.findViewById(R.id.ele1_num);
		ele1_symble=(TextView)rl_ele1.findViewById(R.id.ele1_symble);
		ele1_name_zh=(TextView)rl_ele1.findViewById(R.id.ele1_name_zh);
		ele1_name_us=(TextView)rl_ele1.findViewById(R.id.ele1_name_us);
		relative_atomic_mass1=(TextView)rl_ele1.findViewById(R.id.relative_atomic_mass1);
		tv_configs[0]=(TextView)rl_ele1.findViewById(R.id.ele1_first);
		tv_configs[1]=(TextView)rl_ele1.findViewById(R.id.ele1_second);
		tv_configs[2]=(TextView)rl_ele1.findViewById(R.id.ele1_third);
		tv_configs[3]=(TextView)rl_ele1.findViewById(R.id.ele1_forth);
		tv_configs[4]=(TextView)rl_ele1.findViewById(R.id.ele1_fifth);
		tv_configs[5]=(TextView)rl_ele1.findViewById(R.id.ele1_sixth);
		tv_configs[6]=(TextView)rl_ele1.findViewById(R.id.ele1_seventh);
		ele1_radioactive=(ImageView)rl_ele1.findViewById(R.id.ele1_radioactive);
		ele1_density=(TextView)rl_ele1.findViewById(R.id.ele1_density);
		ele1_melting=(TextView)rl_ele1.findViewById(R.id.ele1_melting);
		ele1_boil=(TextView)rl_ele1.findViewById(R.id.ele1_boil);
		ele1_volume=(TextView)rl_ele1.findViewById(R.id.ele1_volume);
		ele1_radius=(TextView)rl_ele1.findViewById(R.id.ele1_radius);
		covalent_radius=(TextView)rl_ele1.findViewById(R.id.covalent_radius);
		ionic_radius=(TextView)rl_ele1.findViewById(R.id.ionic_radius);
		ele1_density_note=(TextView)rl_ele1.findViewById(R.id.ele1_density_note);
		specific_heat=(TextView)rl_ele1.findViewById(R.id.specific_heat);
		heat_vaporization=(TextView)rl_ele1.findViewById(R.id.heat_vaporization);
		heat_fusion=(TextView)rl_ele1.findViewById(R.id.heat_fusion);
		electrical_resistivity=(TextView)rl_ele1.findViewById(R.id.electrical_resistivity);
		electronegativity=(TextView)rl_ele1.findViewById(R.id.electronegativity);
		first_ionization_energy=(TextView)rl_ele1.findViewById(R.id.first_ionization_energy);
		first_electron_affinity=(TextView)rl_ele1.findViewById(R.id.first_electron_affinity);
		
	}
	
	private void initViews() {
		// TODO Auto-generated method stub
		
		//gifview.showAnimation();
		//gifview.showCover();
		Log.e("", "ELES----->"+rl_main.getChildCount());
		for(int i=0;i<(rl_main.getChildCount());i++){
			rows[i]=(LinearLayout) rl_main.getChildAt(i);
			for(int j=0;j<rows[i].getChildCount();j++){
				addEleData((RelativeLayout) rows[i].getChildAt(j), (i+1), (j+1));
			}
		}
		
	}

	private void addEleData(RelativeLayout rl,int i,int j) {
		// TODO Auto-generated method stub
		TextView ele_name_zh=(TextView) rl.findViewById(R.id.ele_name_zh);
		TextView ele_name_us=(TextView) rl.findViewById(R.id.ele_name_us);
		TextView ele_num=(TextView) rl.findViewById(R.id.ele_num);
		TextView ele_symble=(TextView) rl.findViewById(R.id.ele_symble);
		ImageView ele_radioactive=(ImageView) rl.findViewById(R.id.ele_radioactive);
		TextView ele_name_margin=(TextView) rl.findViewById(R.id.ele_name_margin);
		TextView relative_atomic_mass=(TextView) rl.findViewById(R.id.relative_atomic_mass);
		
		Cursor c = db.rawQuery("select * from elements_table where ele_x=? and ele_y=?", new String[]{(""+i),(""+j)});
		if(c.getCount()==0){
			rl.setVisibility(View.INVISIBLE);
		}if (c.moveToNext()) {
			//Log.e("", c.getCount()+"--->"+i+">>>>>"+j);
			int boder=c.getInt(c.getColumnIndexOrThrow("ele_border"));
			rl.setBackgroundResource(R.drawable.selector_ele1);
			if (boder==1) {
				rl.setBackgroundResource(R.drawable.border1);
			}if (boder==2) {
				rl.setBackgroundResource(R.drawable.border2);
			}if (boder==3) {
				rl.setBackgroundResource(R.drawable.border3);
			}if (boder==4) {
				rl.setBackgroundResource(R.drawable.border4);
			}
			
			ele_name_zh.setText(c.getString(c.getColumnIndexOrThrow("ele_name_zh")));
			ele_name_us.setText(c.getString(c.getColumnIndexOrThrow("ele_name_us")));
			ele_num.setText(c.getString(c.getColumnIndexOrThrow("ele_num")));
			ele_symble.setText(c.getString(c.getColumnIndexOrThrow("ele_symble_us")));
			relative_atomic_mass.setText(c.getString(c.getColumnIndexOrThrow("relative_atomic_mass")));
			
			if ("false".equals(c.getString(c.getColumnIndexOrThrow("is_radioactive")))) {
				ele_radioactive.setVisibility(View.GONE);
				ele_name_margin.setVisibility(View.GONE);
			}if ("true".equals(c.getString(c.getColumnIndexOrThrow("is_man-made")))) {
				ele_symble.setTextColor(Color.rgb(255, 60, 60));
			}if ("气态".equals(c.getString(c.getColumnIndexOrThrow("state_matter_normal")))) {
				ele_name_zh.setTextColor(Color.rgb(255, 255, 255));
			}if ("液态".equals(c.getString(c.getColumnIndexOrThrow("state_matter_normal")))) {
				ele_name_zh.setTextColor(Color.rgb(54, 121, 220));
			}
			int color_selected=Color.rgb(154, 194, 253);
			//int color_selected=Color.rgb(102, 153, 255);
			//int color_press=Color.rgb(255, 255, 255);
			if ("1".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
				setEleBg(color_selected, Color.rgb(43, 204, 92), rl);
				//rl.setBackgroundColor(Color.rgb(43, 204, 92));
			}if ("2".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
				setEleBg(color_selected, Color.rgb(198, 201, 204), rl);
				//rl.setBackgroundColor(Color.rgb(198, 201, 204));
			}if ("3".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
				setEleBg(color_selected, Color.rgb(250, 252, 80), rl);
				//rl.setBackgroundColor(Color.rgb(250, 252, 80));
			}if ("4".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
				setEleBg(color_selected, Color.rgb(244, 214, 82), rl);
				//rl.setBackgroundColor(Color.rgb(244, 214, 82));
			}if ("5".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
				setEleBg(color_selected, Color.rgb(252, 157, 73), rl);
				//rl.setBackgroundColor(Color.rgb(252, 157, 73));
			}if ("6".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
				setEleBg(color_selected, Color.rgb(205, 252, 88), rl);
				//rl.setBackgroundColor(Color.rgb(205, 252, 88));
			}
			
		}
		c.close();
		rl.setOnTouchListener(new eleTouch());	
	}
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what==1) {
				addViews();
				initViews();
				eles_iv1.setVisibility(View.INVISIBLE);
				RelativeLayout.LayoutParams params=(LayoutParams) rl_ele1.getLayoutParams();
				params.width=pwidth*10/18+pwidth*1/140;
				rl_ele1.setLayoutParams(params);
				rl_ele1.setX((pwidth*2/18)-pwidth*1/280);
				SetEle1(6,6);
			}
		}
	};
	
	class eleTouch implements OnTouchListener{

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			 
			
			if (event.getAction()==MotionEvent.ACTION_DOWN) {
				//Log.e("", v+"--->ACTION_DOWN");
				touchDownX=event.getX();
				touchDownY=event.getY();
				frontTouchedEle=null;
				touchtime=System.currentTimeMillis();
				//Log.e("", event.getX()+"--->ACTION_DOWN"+event.getY());
				getTouchEle(v);
				Log.e("", "ACTION_DOWN---->"+(System.currentTimeMillis()-touchtime));
			}if (event.getAction()==MotionEvent.ACTION_UP) {
				if (touchedEle!=null) {
					touchedEle.setSelected(false);
					long dstime = System.currentTimeMillis()-touchtime;
					Log.e("", "ACTION_UP---->"+(System.currentTimeMillis()-touchtime));
					if (dstime<200) {
						onClick(null);
					}
					Log.e("", "::setEle1---->"+(System.currentTimeMillis()-touchtime));
				}
			}if (event.getAction()==MotionEvent.ACTION_MOVE) {
				reJudegTouchEle(v, event);
			}
			return true;
		}
	}
	
	public void setEleBg(int color_press,int color_normal,RelativeLayout rl){
		LayerDrawable ld=(LayerDrawable) rl.getBackground();
		int id=ld.getId(1);
		StateListDrawable sld=new StateListDrawable();
		sld.addState(new int[]{android.R.attr.state_selected},
				new ColorDrawable(color_press));
		sld.addState(new int[]{}, new ColorDrawable(color_normal));
		ld.setDrawableByLayerId(id, sld);
		rl.setBackgroundDrawable(ld);
	}

	private void getTouchEle(View v) {
		// TODO Auto-generated method stub
		
		for (int i = 0; i < rows.length; i++) {
			int indexX=rows[i].indexOfChild(v);
			if (indexX!=-1) {
				touchedEleX=indexX+1;
				touchedEleY=i+1;
			}
		}
		rawtouchedEleX=touchedEleX;
		rawtouchedEleY=touchedEleY;
		RelativeLayout newtouchedEle=(RelativeLayout) v;
		if (touchedEle!=null) {
			touchedEle.setSelected(false);
		}
		touchedEle=newtouchedEle;
		touchedEle.setSelected(true);
		
	}
	
	private void reJudegTouchEle(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		EleWidth=v.getWidth();
		EleHeight=v.getHeight();
		int dsIndexX=0,dsIndexY=0;
		if (event.getX()<0) {
			dsIndexX=(int) (event.getX()/EleWidth)-1;
		}if (event.getY()<0) {
			dsIndexY=(int) (event.getY()/EleHeight)-1;
		}if (event.getX()>EleWidth) {
			dsIndexX=(int) (event.getX()/EleWidth);
		}if (event.getY()>EleHeight) {
			dsIndexY=(int) (event.getY()/EleHeight);
		}
		int IndexX=rawtouchedEleX+dsIndexX,IndexY=rawtouchedEleY+dsIndexY;
		if (IndexX<1 || IndexX>18 || IndexY<1 || IndexY>9) {
			if (frontTouchedEle!=null) {
				frontTouchedEle.setSelected(false);
				frontTouchedEle=null;
			}if (touchedEle!=null) {
				touchedEle.setSelected(false);
				touchedEle=null;
			}
		}if(IndexX>=1 && IndexX<=18 && IndexY>=1 && IndexY<=9){
			if (touchedEle!=null) {
				RelativeLayout newTouchedEle = (RelativeLayout) rows[(IndexY-1)].getChildAt((IndexX-1));
				if (newTouchedEle!=touchedEle) {
					frontTouchedEle=touchedEle;
					touchedEle.setSelected(false);
					touchedEle=newTouchedEle;
					touchedEle.setSelected(true);
				}
			}
		}
	}
	
	public void SetEle1(int x,int y){
		Cursor c = db.rawQuery("select * from elements_table where ele_x=? and ele_y=?", new String[]{(""+y),(""+x)});
		while(c.moveToNext()){
			if (!"".equals(c.getString(c.getColumnIndexOrThrow("ele_num")))) {
				ele1_num.setText(c.getString(c.getColumnIndexOrThrow("ele_num")));
				ele1_symble.setText(c.getString(c.getColumnIndexOrThrow("ele_symble_us")));
				ele1_name_zh.setText(c.getString(c.getColumnIndexOrThrow("ele_name_zh")));
				ele1_name_us.setText(c.getString(c.getColumnIndexOrThrow("ele_name_us")));
				relative_atomic_mass1.setText(c.getString(c.getColumnIndexOrThrow("relative_atomic_mass")));
				String[] structs=c.getString(c.getColumnIndexOrThrow("electronic_structure")).split(" ");
				String struct = structs[structs.length-1];
				ele1_electronic_structure.setText(subFormat(struct));
				if ("false".equals(c.getString(c.getColumnIndexOrThrow("is_radioactive")))) {
					ele1_radioactive.setVisibility(View.GONE);
					ele1_name_margin.setVisibility(View.GONE);
				}if ("true".equals(c.getString(c.getColumnIndexOrThrow("is_radioactive")))) {
					ele1_radioactive.setVisibility(View.VISIBLE);
					ele1_name_margin.setVisibility(View.VISIBLE);
				}if ("true".equals(c.getString(c.getColumnIndexOrThrow("is_man-made")))) {
					ele1_symble.setTextColor(Color.rgb(180, 0, 0));
				}if ("false".equals(c.getString(c.getColumnIndexOrThrow("is_man-made")))) {
					ele1_symble.setTextColor(Color.rgb(0, 0, 0));
				}if ("气态".equals(c.getString(c.getColumnIndexOrThrow("state_matter_normal")))) {
					ele1_name_zh.setTextColor(Color.rgb(255, 255, 255));
				}if ("液态".equals(c.getString(c.getColumnIndexOrThrow("state_matter_normal")))) {
					ele1_name_zh.setTextColor(Color.rgb(54, 121, 220));
				}if ("固态".equals(c.getString(c.getColumnIndexOrThrow("state_matter_normal")))) {
					ele1_name_zh.setTextColor(Color.rgb(0, 0, 0));
				}
				String[] confs=c.getString(c.getColumnIndexOrThrow("electronic_confi")).split(",");
				for (int i = 0; i < 7; i++) {
					if (i<confs.length) {
						tv_configs[i].setText(confs[i]);
						tv_configs[i].setVisibility(View.VISIBLE);
					}else{
						tv_configs[i].setVisibility(View.GONE);
					}
				}
				ele1_density.setText(doubFormat(c.getString(c.getColumnIndexOrThrow("ele_density"))));
				ele1_density_note.setText("("+c.getString(c.getColumnIndexOrThrow("ele_density_unity"))+")密度 ");
				ele1_melting.setText(doubFormat(c.getString(c.getColumnIndexOrThrow("ele_melting"))));
				ele1_boil.setText(doubFormat(c.getString(c.getColumnIndexOrThrow("ele_boil"))));
				ele1_volume.setText(doubFormat(c.getString(c.getColumnIndexOrThrow("ele_volume"))));
				ele1_radius.setText(doubFormat(c.getString(c.getColumnIndexOrThrow("ele_radius"))));
				covalent_radius.setText(doubFormat(c.getString(c.getColumnIndexOrThrow("covalent_radius"))));
				ionic_radius.setText(doubFormat(c.getString(c.getColumnIndexOrThrow("ionic_radius"))));
				specific_heat.setText(" "+doubFormat(c.getString(c.getColumnIndexOrThrow("specific_heat"))));
				heat_vaporization.setText(" "+doubFormat(c.getString(c.getColumnIndexOrThrow("heat_vaporization"))));
				heat_fusion.setText(" "+doubFormat(c.getString(c.getColumnIndexOrThrow("heat_fusion"))));
				electrical_resistivity.setText(" "+doubFormat(c.getString(c.getColumnIndexOrThrow("electrical_resistivity"))));
				electronegativity.setText(" "+doubFormat(c.getString(c.getColumnIndexOrThrow("electronegativity"))));
				first_ionization_energy.setText(" "+doubFormat(c.getString(c.getColumnIndexOrThrow("first_ionization_energy"))));
				first_electron_affinity.setText(" "+doubFormat(c.getString(c.getColumnIndexOrThrow("first_electron_affinity"))));
			}
		}
		c.close();
	}

	
	
	private SpannableString subFormat(String struct) {
		// TODO Auto-generated method stub
		SpannableString sub=new SpannableString(struct);
		for (int i = 1; i < struct.length(); i++) {
			String str=struct.charAt(i)+"";
			if (str.matches("[0123456789]")) {
				sub.setSpan(new SuperscriptSpan(), i, (i+1), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); 
			}
		}
		return sub;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.e("", "onClick");
		SetEle1(rawtouchedEleX,rawtouchedEleY);
	}
	
	public String doubFormat(String doubStr){
		String str=doubStr;
		try {
			if (doubStr.indexOf(".")!=-1) {
				int dotIndex=doubStr.indexOf(".");
				if (dotIndex>=4) {
					BigDecimal b=new BigDecimal(doubStr);     
					int f1=b.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
					str=f1+"";
				}else{
					BigDecimal b=new BigDecimal(doubStr);     
					double f1=b.setScale((4-dotIndex),BigDecimal.ROUND_HALF_UP).doubleValue();
					str=f1+"";
				}
			}if (doubStr.indexOf(".")==-1) {
				if (doubStr.length()>4) {
					BigDecimal b=new BigDecimal(doubStr);     
					int f1=b.setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
					str=f1+"";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			str="/";
		}
		return str;
	}
	
}
