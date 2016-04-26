package com.emple.activity;

import java.math.BigDecimal;

import com.emple.calculatorqb.Globe;
import cn.gqb.calculator.R;
import com.emple.entity.ElesTextSize;
import com.emple.utils.ElesTxtSizeUtil;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.SuperscriptSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
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
	private ImageView eles_iv1;
	private RecyclerView recy;
	private ElesTextSize elesSize=new ElesTextSize();
	private ElesTxtSizeUtil elesSizeUtil=new ElesTxtSizeUtil();
	private int typedSizeValue=TypedValue.COMPLEX_UNIT_SP;
	private SQLiteDatabase db;
	private int rawPressIndex=-1,rawPressIndexX,rawPressIndexY;
	private float TouchedX,TouchedY,rawTouchedX,rawTouchedY;
	private LinearLayout touchedEle,frontTouchedEle;
	private int EleWidth,EleHeight;
	private LayoutInflater inflater;
	private RelativeLayout rl_ele1,ele1_rlbg;
	
	private TextView ele1_electronic_structure,ele1_num,ele1_symble,ele1_name_zh,
	ele1_name_us,relative_atomic_mass1,ele1_name_margin;
	private TextView[] tv_configs;
	private ImageView ele1_radioactive;
	private TextView ele1_density,ele1_melting,ele1_boil,ele1_volume,
	ele1_radius,covalent_radius,ionic_radius,ele1_density_note,
	specific_heat,heat_vaporization,heat_fusion,electrical_resistivity,
	electronegativity,first_ionization_energy,first_electron_affinity,ele1_melting_note,ele1_volume_note,
	ele1_radius_note,covalent_radius_note,ionic_radius_note,specific_heat_note,heat_vaporization_note,
	heat_fusion_note,electrical_resistivity_note,electronegativity_note,first_ionization_energy_note,
	first_electron_affinity_note,ele1_boil_note,ele1_margin_shell6,ele1_margin_shell,ele1_margin_shell3,
	ele1_margin_shell4,ele1_margin_shell5;
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
		mcontext = this;
		db = SQLiteDatabase.openOrCreateDatabase(Globe.dbFile.getPath(), null);  
		eles_rl=(RelativeLayout) findViewById(R.id.eles_rl);
		eles_iv1=new ImageView(mcontext);
		eles_iv1.setBackgroundResource(R.drawable.atomic);
		eles_rl.addView(eles_iv1,RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		inflater = LayoutInflater.from(mcontext);
		elesSize=elesSizeUtil.getTextSizes();
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
		
		//recy=(RecyclerView) findViewById(R.id.eles_recy);
		
		rl_ele1=(RelativeLayout) inflater.inflate(R.layout.ele_layout1, null);
		recy=new RecyclerView(mcontext);
		recy.setOverScrollMode(View.OVER_SCROLL_NEVER);
		RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE); 
		eles_rl.addView(recy,params);
		eles_rl.addView(rl_ele1,RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		GridLayoutManager mgr=new GridLayoutManager(this,18);  
		recy.setLayoutManager(mgr);
		recy.setAdapter(new MyAdapter());
		
		ele1_rlbg=(RelativeLayout) rl_ele1.findViewById(R.id.ele1_rlbg);
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
		ele1_melting_note=(TextView)rl_ele1.findViewById(R.id.ele1_melting_note);
		ele1_boil_note=(TextView)rl_ele1.findViewById(R.id.ele1_boil_note);
		ele1_volume_note=(TextView)rl_ele1.findViewById(R.id.ele1_volume_note);
		ele1_radius_note=(TextView)rl_ele1.findViewById(R.id.ele1_radius_note);
		covalent_radius_note=(TextView)rl_ele1.findViewById(R.id.covalent_radius_note);
		ionic_radius_note=(TextView)rl_ele1.findViewById(R.id.ionic_radius_note);
		specific_heat_note=(TextView)rl_ele1.findViewById(R.id.specific_heat_note);
		heat_vaporization_note=(TextView)rl_ele1.findViewById(R.id.heat_vaporization_note);
		heat_fusion_note=(TextView)rl_ele1.findViewById(R.id.heat_fusion_note);
		electrical_resistivity_note=(TextView)rl_ele1.findViewById(R.id.electrical_resistivity_note);
		electronegativity_note=(TextView)rl_ele1.findViewById(R.id.electronegativity_note);
		first_ionization_energy_note=(TextView)rl_ele1.findViewById(R.id.first_ionization_energy_note);
		first_electron_affinity_note=(TextView)rl_ele1.findViewById(R.id.first_electron_affinity_note);
		ele1_margin_shell6=(TextView)rl_ele1.findViewById(R.id.ele1_margin_shell6);
		ele1_margin_shell=(TextView)rl_ele1.findViewById(R.id.ele1_margin_shell);
		ele1_margin_shell3=(TextView)rl_ele1.findViewById(R.id.ele1_margin_shell3);
		ele1_margin_shell4=(TextView)rl_ele1.findViewById(R.id.ele1_margin_shell4);
		ele1_margin_shell5=(TextView)rl_ele1.findViewById(R.id.ele1_margin_shell5);
		
		ele1_name_margin.setTextSize(typedSizeValue, elesSize.getEle1_size3());
		ele1_electronic_structure.setTextSize(typedSizeValue, elesSize.getEle1_size4());
		ele1_num.setTextSize(typedSizeValue, elesSize.getEle1_size1());
		ele1_symble.setTextSize(typedSizeValue, elesSize.getEle1_size1());
		ele1_name_zh.setTextSize(typedSizeValue, elesSize.getEle1_size3());
		ele1_name_us.setTextSize(typedSizeValue, elesSize.getEle1_size4());
		relative_atomic_mass1.setTextSize(typedSizeValue, elesSize.getEle1_size4());
		tv_configs[0].setTextSize(typedSizeValue, elesSize.getEle1_size2());
		tv_configs[1].setTextSize(typedSizeValue, elesSize.getEle1_size2());
		tv_configs[2].setTextSize(typedSizeValue, elesSize.getEle1_size2());
		tv_configs[3].setTextSize(typedSizeValue, elesSize.getEle1_size2());
		tv_configs[4].setTextSize(typedSizeValue, elesSize.getEle1_size2());
		tv_configs[5].setTextSize(typedSizeValue, elesSize.getEle1_size2());
		tv_configs[6].setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_density.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_melting.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_boil.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_volume.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_radius.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		covalent_radius.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ionic_radius.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		specific_heat.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		heat_vaporization.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		heat_fusion.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		electrical_resistivity.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		electronegativity.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		first_ionization_energy.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		first_electron_affinity.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_density_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_melting_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_volume_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_radius_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		covalent_radius_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ionic_radius_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		specific_heat_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		heat_vaporization_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		heat_fusion_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		electrical_resistivity_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		electronegativity_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		first_ionization_energy_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		first_electron_affinity_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_boil_note.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_margin_shell.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_margin_shell3.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_margin_shell4.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_margin_shell5.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		ele1_margin_shell6.setTextSize(typedSizeValue, elesSize.getEle1_size2());
		
	}
	
	
	 class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

		@Override
		public int getItemCount() {
			// TODO Auto-generated method stub
			return 9*18;
		}

		@Override
		public void onBindViewHolder(ViewHolder arg0, final int arg1) {
			// TODO Auto-generated method stub
			
			final LinearLayout rl= (LinearLayout) arg0.itemView;
			arg0.ele_name_zh.setTextSize(typedSizeValue, elesSize.getEle_size3());
			arg0.ele_name_us.setTextSize(typedSizeValue, elesSize.getEle_size4());
			arg0.ele_num.setTextSize(typedSizeValue, elesSize.getEle_size1());
			arg0.ele_symble.setTextSize(typedSizeValue, elesSize.getEle_size1());
			arg0.relative_atomic_mass.setTextSize(typedSizeValue, elesSize.getEle_size4());
			
			arg0.ele_name_zh.setTextSize(typedSizeValue, elesSize.getEle_size3());
			arg0.ele_name_us.setTextSize(typedSizeValue, elesSize.getEle_size4());
			arg0.ele_num.setTextSize(typedSizeValue, elesSize.getEle_size1());
			arg0.ele_symble.setTextSize(typedSizeValue, elesSize.getEle_size1());
			arg0.relative_atomic_mass.setTextSize(typedSizeValue, elesSize.getEle_size4());
			
			int i,j;
			i=arg1/18+1;
			j=arg1%18+1;
			//Log.e("", arg1+"<+++>"+i+"::"+j);
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
				
				arg0.ele_name_zh.setText(c.getString(c.getColumnIndexOrThrow("ele_name_zh")));
				arg0.ele_name_us.setText(c.getString(c.getColumnIndexOrThrow("ele_name_us")));
				arg0.ele_num.setText(c.getString(c.getColumnIndexOrThrow("ele_num")));
				arg0.ele_symble.setText(c.getString(c.getColumnIndexOrThrow("ele_symble_us")));
				arg0.relative_atomic_mass.setText(c.getString(c.getColumnIndexOrThrow("relative_atomic_mass")));
				
				if ("false".equals(c.getString(c.getColumnIndexOrThrow("is_radioactive")))) {
					arg0.ele_radioactive.setVisibility(View.GONE);
				}if ("true".equals(c.getString(c.getColumnIndexOrThrow("is_man-made")))) {
					arg0.ele_symble.setTextColor(Color.rgb(255, 60, 60));
				}if ("气态".equals(c.getString(c.getColumnIndexOrThrow("state_matter_normal")))) {
					arg0.ele_name_zh.setTextColor(Color.rgb(255, 255, 255));
				}if ("液态".equals(c.getString(c.getColumnIndexOrThrow("state_matter_normal")))) {
					arg0.ele_name_zh.setTextColor(Color.rgb(54, 121, 220));
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
			
			rl.setOnTouchListener(new OnTouchListener(){

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					if (event.getAction()==MotionEvent.ACTION_DOWN) {
						rawPressIndex=arg1;
						TouchedX=event.getX();
						TouchedY=event.getY();
						rawTouchedX=event.getRawX();
						rawTouchedY=event.getRawY();
						rawPressIndexX=arg1%18+1;
						rawPressIndexY=arg1/18+1;
						touchedEle=rl;
						touchedEle.setSelected(true);
						SetEle1(rawPressIndexX,rawPressIndexY);
						touchtime=System.currentTimeMillis();
					}
					return true;
				}
			});
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			View v = LayoutInflater.from(mcontext).inflate(R.layout.ele_layout,null);  
            return new ViewHolder(v);
		}
		
		class ViewHolder extends RecyclerView.ViewHolder {
			
			TextView ele_name_zh;
			TextView ele_name_us;
			TextView ele_num;
			TextView ele_symble;
			ImageView ele_radioactive;
			TextView relative_atomic_mass;
			
			public ViewHolder(View itemView) {
				super(itemView);
				// TODO Auto-generated constructor stub
				ele_name_zh=(TextView) itemView.findViewById(R.id.ele_name_zh);
				ele_name_us=(TextView) itemView.findViewById(R.id.ele_name_us);
				ele_num=(TextView) itemView.findViewById(R.id.ele_num);
				ele_symble=(TextView) itemView.findViewById(R.id.ele_symble);
				ele_radioactive=(ImageView) itemView.findViewById(R.id.ele_radioactive);
				relative_atomic_mass=(TextView) itemView.findViewById(R.id.relative_atomic_mass);
				
				int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
				int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED); 
				ele_name_zh.measure(w, h); 
				int height =ele_name_zh.getMeasuredHeight(); 
				int width =ele_name_zh.getMeasuredWidth();
				LinearLayout.LayoutParams params=(android.widget.LinearLayout.LayoutParams) ele_radioactive.getLayoutParams();
				params.height=height;
				params.width=width;
				ele_radioactive.setLayoutParams(params);
			}
		}
		 
	 }
	
	 public void setEleBg(int color_press,int color_normal,LinearLayout ll){
			LayerDrawable ld=(LayerDrawable) ll.getBackground();
			int id=ld.getId(1);
			StateListDrawable sld=new StateListDrawable();
			sld.addState(new int[]{android.R.attr.state_selected},
					new ColorDrawable(color_press));
			sld.addState(new int[]{}, new ColorDrawable(color_normal));
			ld.setDrawableByLayerId(id, sld);
			ll.setBackgroundDrawable(ld);
		}
	 
	 
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what==1) {
				addViews();
				eles_iv1.setVisibility(View.INVISIBLE);
				RelativeLayout.LayoutParams params=(LayoutParams) rl_ele1.getLayoutParams();
				params.width=pwidth*10/18+pwidth*1/140;
				rl_ele1.setLayoutParams(params);
				rl_ele1.setX((float) ((pwidth*2.0/18)-pwidth*8.0/(280*4)));
				SetEle1(6,6);
			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (rawPressIndex!=-1) {
			if (ev.getAction()==ev.ACTION_MOVE) {
				reJudegTouchEle(ev.getX(),ev.getY());
			}if (ev.getAction()==ev.ACTION_UP) {
				long dstime = System.currentTimeMillis()-touchtime;
				if (dstime<400) {
					onClick(null);
				}
				touchedEle.setSelected(false);
				rawPressIndex=-1;
			}
		}
		return super.dispatchTouchEvent(ev);
	}
	
	private void reJudegTouchEle(float evx, float evy) {
		EleWidth=touchedEle.getWidth();
		EleHeight=touchedEle.getHeight();
		int newx=0,newy=0;
		
		float dsx=evx-rawTouchedX;
		float dsy=evy-rawTouchedY;
		if (dsx>0) {
			if (dsx>((EleWidth-TouchedX))) {
				newx=(int) ((dsx-EleWidth+TouchedX)/EleWidth)+1;
			}else {
				newx=0;
			}
		}if (dsx<0) {
			if (Math.abs(dsx)>(TouchedX)) {
				newx=(int) ((dsx+TouchedX)/EleWidth)-1;
			}else{
				newx=0;
			}
		}if (dsy>0) {
			if (dsy>((EleHeight-TouchedY))) {
				newy=(int) ((dsy-EleHeight+TouchedY)/EleHeight)+1;
			}else {
				newy=0;
			}
		}if (dsy<0) {
			if (Math.abs(dsy)>(TouchedY)) {
				newy=(int) ((dsy+TouchedY)/EleHeight)-1;
			}else{
				newy=0;
			}
		}
		int IndexX=rawPressIndexX+newx,IndexY=rawPressIndexY+newy;
		if (IndexX<1 || IndexX>18 || IndexY<1 || IndexY>9) {
			frontTouchedEle.setSelected(false);
			touchedEle.setSelected(false);
		}if(IndexX>=1 && IndexX<=18 && IndexY>=1 && IndexY<=9){
			LinearLayout newTouchedEle =(LinearLayout) recy.getChildAt((IndexX+18*(IndexY-1)-1));
			if (newTouchedEle!=touchedEle) {
				frontTouchedEle=touchedEle;
				touchedEle.setSelected(false);
				touchedEle=newTouchedEle;
				touchedEle.setSelected(true);
				SetEle1(IndexX,IndexY);
			}
		}
		//PressIndex=0;
		//newx=rawPressIndexX+
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
				/*if ("1".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
					ele1_rlbg.setBackgroundColor(Color.rgb(43, 204, 92));
				}if ("2".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
					ele1_rlbg.setBackgroundColor(Color.rgb(198, 201, 204));
				}if ("3".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
					ele1_rlbg.setBackgroundColor(Color.rgb(250, 252, 80));
				}if ("4".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
					ele1_rlbg.setBackgroundColor(Color.rgb(244, 214, 82));
				}if ("5".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
					ele1_rlbg.setBackgroundColor(Color.rgb(252, 157, 73));
				}if ("6".equals(c.getString(c.getColumnIndexOrThrow("ele_classify")))) {
					ele1_rlbg.setBackgroundColor(Color.rgb(205, 252, 88));
				}*/
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
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		rawPressIndex=-1;
	}
	
}
