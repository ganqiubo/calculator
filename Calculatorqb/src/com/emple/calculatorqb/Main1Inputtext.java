package com.emple.calculatorqb;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.Subject;

import org.junit.Test;

import com.emple.utils.SoundPlayer;

import android.R.integer;
import android.R.string;
import android.app.Service;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.renderscript.Type;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.gqb.calculator.R;

public class Main1Inputtext {

	EditText main1et1,funet1;
	Context ct;
	int ssn1=9;
	Globe globe;
	Judge judge;
	HorizontalScrollView main1hsv1;
	LinearLayout main1ll1;
	TextView main1tv4;
	SoundPlayer soundPlayer;
	
	/*1.∑()
	2.∑()[]
	3.[1/N]∑()
	4.[]1/N∑()[,,]
	5.()*/
	
    public void inputtext(int a, String s) throws Exception{
	   	  
    	int a1=0;
    	if(!globe.isfuning && !globe.isfun1save){
    		
    		inputnormal(a, s);
    		if(a==3){
    			String s1=globe.calculate.calculate(main1et1.getText().toString());
    			//s1=unityScale(s1);
    			Log.e("", "计算结果为："+s1);
    			if(s1==null){
    				
    			}if(s1!=null){
    				if(s1.equals("-e")){
    					s1="-"+CustomValues.e.setScale(globe.demic, RoundingMode.HALF_UP);
    				}if(s1.equals("e")){
    					s1=""+CustomValues.e.setScale(globe.demic, RoundingMode.HALF_UP);
    				}if(s1.equals("-π")){
    					s1="-"+CustomValues.π.setScale(globe.demic, RoundingMode.HALF_UP);
    				}if(s1.equals("π")){
    					s1=""+CustomValues.π.setScale(globe.demic, RoundingMode.HALF_UP);
    				}
    				main1et1.setText("");
    				BigDecimal bigDecimal=new BigDecimal(s1);
    				if (globe.hasUnity) {
    					s1=unityScale(s1);
    					s1=new BigDecimal(s1).setScale(globe.demic, RoundingMode.HALF_UP).toPlainString();
					}if (!globe.hasUnity) {
						s1=bigDecimal.setScale(globe.demic, RoundingMode.HALF_UP).toPlainString();
					}   				
    				s1=removie0(s1);
    				if (globe.hasUnity) {
    					String unitySeletStr="";
    					ArrayList<String> aListStr=new ArrayList<String>();
    					aListStr.add("长度");aListStr.add("质量");aListStr.add("面积");
    					aListStr.add("体积");aListStr.add("压力");aListStr.add("流量");
    					unitySeletStr=globe.unitseleidStr.get(aListStr.indexOf(Globe.main1tv4.getText()));
    					if (unitySeletStr.indexOf("(")!=-1) {
    						unitySeletStr=unitySeletStr.substring((unitySeletStr.indexOf("(")+1), unitySeletStr.indexOf(")"));
    					}
    					s1=s1+unitySeletStr;
					}
    				addSpannableStr(main1et1.getSelectionStart(),s1,ct.getResources().getColor(R.color.strpan1),0);
    				
    				//addSpannableStr(main1et1.getSelectionStart(),"=",ct.getResources().getColor(R.color.strpan5),0);
    			}    			   			    			
    		}if(a==27 && !globe.isfuning && !globe.isfun1save && (s.equals("+") || s.equals("-") || s.equals("×")
      			 || s.equals("÷") || s.equals("√") || s.equals("^") || s.equals("(") || s.equals(""))){
    		 a1=1;
      		 globe.isfun1save=true;
      		 addSpannableStr(main1et1.getSelectionStart(),"{[1/N]∑()[,,]+}",Color.rgb(198,70, 67),5);
      		 globe.btarray.get(27).setText("→");
      	   }
    	}if(globe.isfuning && !globe.isfun1save){
    		
    		if(a==26){
       		   	cursormove(1);
       	    }if(a==25){
       		   cursormove(-1);
       	    }
    		
    		String calStr="";
    		 if (a==3) {
    			 String indStr=funet1.getText().toString();
    			 indStr=getIndexStr(indStr);//除去一个多余的未知数
    			 int ind1=indStr.indexOf("=");
    			
    			 if (globe.funtype==1 || globe.funtype==3) {
					 indStr=indStr.substring((ind1+1),indStr.length());
					 if (globe.funtype==3) {
						 indStr=indStr.replace("[", "");
						 indStr=indStr.replace("]", "");
						 indStr=indStr.replace("/", "÷");
						 indStr=indStr.replace("N", (globe.xy.size()+"×"));
					}
					 Log.e("", "thisfuntype======>1》》》》》"+indStr);
					 BigDecimal sumDecimal=new BigDecimal("0");
					 if (indStr.indexOf("AVG")!=-1) {
						sumDecimal=getAvg(sumDecimal);
						indStr=indStr.replace("AVG", sumDecimal.toPlainString());
						calStr=globe.calculate.calculate(indStr);
					}else {
						calStr=globe.calculate.calculate(indStr);
						//indStr
					}if (calStr!=null) {
						 calStr=new BigDecimal(calStr).setScale(globe.demic, RoundingMode.HALF_UP).toPlainString();
					}if (calStr!=null) {
						 String et1Str=main1et1.getText().toString();
						 calStr=removie0(calStr);
						 
						 int as1=0;int as2=0;
						 if (globe.funtype==1) {
							 as1=et1Str.indexOf("{");as2=et1Str.lastIndexOf("}");						 
							 //et1Str=et1Str.replace(et1Str.substring(et1Str.indexOf("{"), (et1Str.lastIndexOf("}")+1)), "")+"("+calStr+")";
						}if (globe.funtype==3) {
							as1=et1Str.indexOf("[");as2=et1Str.lastIndexOf("}");
							//et1Str=et1Str.replace(et1Str.substring(et1Str.indexOf("["), (et1Str.lastIndexOf("}")+1)), "")+"("+calStr+")";
						}
						 globe.main1et1.getText().delete(as1, (as2+1));
						 globe.main1inputtext.addSpannableStr(as1, ("("+calStr+")"), ct.getResources().getColor(R.color.strpan1), 0);
						 funcancer("noclear");
					}
				}/*if (globe.funtype==2) {
					String str=main1et1.getText().toString();
					str=str.substring((str.indexOf("{")+1), str.lastIndexOf("}"));
					Log.e("", "funtype2====>"+str);
				}if (globe.funtype==4) {
					Log.e("", "thisfuntype======>4");
				}转至isfunsaving下处理*/
    			 
 			}
    		
    		if(globe.funtype==1 || globe.funtype==3){
    			int bigstart=main1et1.getText().toString().indexOf("{");
        		int bigend=main1et1.getText().toString().indexOf("}");
        		int funstart=main1et1.getText().toString().indexOf("f");
    			int funend=main1et1.getText().toString().indexOf(")", funstart);
    			int funinclud1=main1et1.getText().toString().indexOf("(", funstart);   		
        		
        		 if(a==2 || a==6 || a==7 || a==8 || a==11 || a==12 || a==13 || a==16 || a==17 || a==18){
        			 
        			 if(main1et1.getSelectionStart()>funinclud1 && main1et1.getSelectionStart()<=funend){
        				 addSpannableStr(main1et1.getSelectionStart(),globe.btarray.get(a).getText().toString(),Color.RED,0);
        			 }    			     			 
        		 }if(a==1 && !s.equals("(") && !s.equals("√")){
        			 if(main1et1.getSelectionStart()>funinclud1 && main1et1.getSelectionStart()<=funend){
        				 addSpannableStr(main1et1.getSelectionStart(),globe.btarray.get(a).getText().toString(),Color.RED,0);
        			 }  		 
        		 } if(a==9){ 
        			 if((s.equals("(") || s.equals(",")) && main1et1.getSelectionStart()>funinclud1 && main1et1.getSelectionStart()<=funend){
        				 addSpannableStr(main1et1.getSelectionStart(),globe.btarray.get(a).getText().toString(),Color.RED,0);
        			 }if(main1et1.getSelectionStart()>funend){
        				 
        			 }    			 
        		 }if(a==5){
        			 if(s.equals("(") || s.equals("√") || s.equals("-") || s.equals(",")){
        				 addSpannableStr(main1et1.getSelectionStart(),"√",Color.RED,0);
        			 }
        		 }if(a==28){        			 
        			 funcancer("");        			         			 
        		 }if(a==27){
        			 int ssa1=main1et1.getText().toString().indexOf("f");
        			 int ssa2=main1et1.getText().toString().indexOf(")", ssa1);    
        			 int ssa3=main1et1.getText().toString().indexOf("(", ssa1);   
        			 String ss1=main1et1.getText().toString().substring(ssa1, ssa2+1); 

        			 String funnum1="";
        			 String funnum2="";
        			 boolean isnumfun1=false;
        			 boolean isnumfun2=false;
        			 if(globe.fununknow==1){    				 
        				 funnum1=main1et1.getText().toString().substring(ssa3+1, ssa2);  
        				 isnumfun1=judge.isnumberfun(funnum1);
        				 if(isnumfun1){
        					 globe.funinputx=funnum1;
        					 main1et1.getText().delete(ssa1, ssa2+1);
        					 main1et1.getText().insert(main1et1.getText().toString().indexOf("}"), "h("+funnum1+")"+globe.funcharacter);
        					 funreplace();funinput();mainet1fxy();	
        					 main1hsv1.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        					 globe.n=globe.n+1;    					 
        				 }else{
        					 Toast.makeText(ct, "请输入正确参数", Toast.LENGTH_SHORT).show();
        				 }
        			 }if(globe.fununknow==2){
        				 int ssa4=main1et1.getText().toString().lastIndexOf(",");
        				 funnum1=main1et1.getText().toString().substring(ssa3+1, ssa4);    	
        				 funnum2=main1et1.getText().toString().substring(ssa4+1, ssa2);  
        				 if(!funnum1.equals("") && funnum2.equals("")){
        					 main1et1.setSelection(ssa4+1);    					 
        				 }if(!funnum1.equals("") && !funnum2.equals("")){
        					 isnumfun1=judge.isnumberfun(funnum1);
        					 isnumfun2=judge.isnumberfun(funnum2);
        					 if(isnumfun1 && isnumfun2){
        						 globe.funinputx=funnum1;
        						 globe.funinputy=funnum2;
        						 main1et1.getText().delete(ssa1, ssa2+1);
        						 main1et1.getText().insert(main1et1.getText().toString().indexOf("}"), "h("+funnum1+","+funnum2+")"+globe.funcharacter);
        						 funreplace();funinput();mainet1fxy(); 	
        						 main1hsv1.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        						 globe.n=globe.n+1;
        					 }else{
        						 Toast.makeText(ct, "请输入正确参数", Toast.LENGTH_SHORT).show();
        					 }
        				 }
        			 }   			 				
        		 }if(a==29 && main1et1.getSelectionStart()>bigstart && main1et1.getSelectionStart()<=bigend ){
        			 if(main1et1.getSelectionStart()>funinclud1+1 && main1et1.getSelectionStart()<=funend
        					 && !s.equals(",") && main1et1.getSelectionStart()!=-1){
        				 main1et1.getText().delete(main1et1.getSelectionStart()-1, main1et1.getSelectionStart());
        			 }if(s.equals(",") || s.equals(globe.funcharacter) || s.equals("(") || s.equals("f")){
        				 main1et1.setSelection(main1et1.getSelectionStart()-1);
        			 }if(main1et1.getSelectionStart()>bigstart+1 && main1et1.getSelectionStart()<=funstart-1
        					 && !s.equals("{") && !s.equals(globe.funcharacter) && !s.equals(",")){
        				 
        				 int index=1;
        				 for(int i=main1et1.getSelectionStart();i<=funstart;i++){
        					 String sn1=""+main1et1.getText().charAt(i);
        					 if(sn1.equals(globe.funcharacter)){
        						 index=index+1;
        					 }   					 
        				 }           
        				 if (globe.fununknow==1) {
        					 globe.xy.remove((globe.xy.size()-index+1));
						}if (globe.fununknow==2) {
							globe.xy.remove((globe.xy.size()-2*index+2));
							globe.xy.remove((globe.xy.size()-2*index+3));
						}
						for (String iterable_element : globe.xy) {
							Log.e("", "wo==>"+iterable_element);
						}
        				 int an1=main1et1.getSelectionStart();
        				 int an2=bigstart;
        				 for(int i=an1-1;i>=bigstart;i--){
        					 String sn1=""+main1et1.getText().charAt(i);
        					 if(sn1.equals(globe.funcharacter)){
        						 an2=i;
        						 break;
        					 }
        				 }	
        				 for(int i=an1-1;i<=bigend;i++){
        					 String sn1=""+main1et1.getText().charAt(i);
        					 if(sn1.equals(globe.funcharacter)){
        						 an1=i;
        						 break;
        					 }
        				 }	
        				 main1et1.getText().delete(an2+1, an1+1);
        				 
        				 int an1a=funet1.getText().toString().lastIndexOf(")");    				 
        				 int tozero=0;an1=-1;an2=-1;int time=0;
        				 String alls="";int success=-1;int fail=-1;
        				 for(int i=an1a-1;i>=0;i--){
        					 String sn1=""+funet1.getText().charAt(i);	
        					 alls=funet1.getText().charAt(i)+alls;
        					 if(sn1.equals(")")){
        						 tozero=tozero+1;
        						 if(tozero==1){
        							 fail=0; 
        						 }
        					 }if(sn1.equals("(")){
        						 tozero=tozero-1;
        						 if(tozero==0){
        							 success=0;
        						 }
        					 }if(success==0){
        						 time=time+1;
        					 }if(time==index-1 && fail==0){
        						 an1=i;
        					 }if(time==index){
        						 an2=i;
        						 break;
        					 }   
        					 success=-1;fail=-1;
        				 }   				 
        				 if(an1!=-1 && an2!=-1){
        					 funet1.getText().delete(an2, an1+2);
        					 globe.n=globe.n-1;    	
        				 }        				 
        			 }   
        		 }
    		}   		
    	}if(globe.isfun1save && !globe.isfuning){   
    		
    		if(a==26){
       		   	cursormove(1);
       	    }if(a==25){
       		   cursormove(-1);
       	    }
    		
    		int funa1=main1et1.getText().toString().indexOf("{");
    		int funb1=main1et1.getText().toString().lastIndexOf("}");
    		int funa2=main1et1.getText().toString().indexOf("(");
    		int funb2=main1et1.getText().toString().lastIndexOf(")");   		
    	    int funa3=main1et1.getText().toString().lastIndexOf("[");  
    	    int funb3=main1et1.getText().toString().lastIndexOf("]"); 
    	    int funa4=main1et1.getText().toString().lastIndexOf("∑"); 
    	    int funa5=main1et1.getText().toString().indexOf("[");  
    	    int funb5=main1et1.getText().toString().indexOf("]");
    	    String stra=a+"";
    	    
    	    funtype(funa3,funa4,funa5);
    	    
			if (a==3 && a1==0) {//
				Object[] match = funismatch(funa1,funb1,funa2,funb2);
    			boolean matchs=(Boolean) match[0];
    			String matches1=(String) match[1];
    			String copymatches=(String) match[2];
    			if (!matchs) {
    				Toast.makeText(ct, "表达式有错误，请修改", Toast.LENGTH_SHORT).show();
				}if (matchs) {
					String str = main1et1.getText().toString();
					str = str.substring((str.indexOf("{") + 1),
							str.lastIndexOf("}"));
					Log.e("", globe.funtype+"<=@=@=>" + str+"::"+copymatches);
					if (globe.funtype == 2) {
						funtype24Str(str,2,copymatches);
					}if (globe.funtype == 4) {
						funtype24Str(str,4,copymatches);
					}
				}
			}if(a==27 && a1==0){  
    			
    			Object[] match = funismatch(funa1,funb1,funa2,funb2);
    			boolean matchs=(Boolean) match[0];
    			String matches1=(String) match[1];
				Log.e("", "funtype=====>"+globe.funtype);
				if(!matchs){
					Toast.makeText(ct, "表达式有错误，请修改", Toast.LENGTH_SHORT).show();
				}if(matchs){
					if(globe.funtype==1 || globe.funtype==3){//必须有x,y,不能有n
						main1et1.getText().delete(funa1, funb1+1);
						globe.funnote=matches1;globe.isfun1save=false;						
						funing("f");		
						//Toast.makeText(ct, "OK", Toast.LENGTH_SHORT).show();
					}if(globe.funtype==2 || globe.funtype==4){//必须有n,不能有x,y,avg
						//Toast.makeText(ct, "OK", Toast.LENGTH_SHORT).show();
					}if(globe.funtype==5){
						main1et1.getText().delete(funa1, funb1+1);
						Toast.makeText(ct, "不支持此种函数", Toast.LENGTH_SHORT).show();
						globe.isfun1save=false;globe.isfuning=false;
						globe.btarray.get(27).setText("∑");
					}					
				}
			  }if(a==28){
    			  funcancer("");
    		  }if(a==0/* && funa4<funa3 && funa1<funa2 && funa2<funb2 && funb2<funb1*/ && main1et1.getSelectionStart()>(funa4+1) && 
    				  main1et1.getSelectionStart()<=(funa3-1) && (globe.funtype==2 || globe.funtype==4)){
    			  if(!s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4") && !s.equals("5") 
        		   		&& !s.equals("6") && !s.equals("7") && !s.equals("8") && !s.equals("9")&& !s.equals(".") 
        		   		&& !s.equals("0") && !s.equals(")") && !s.equals("π") && !s.equals("e") && !s.equals("n")){
    				  addSpannableStr(main1et1.getSelectionStart(),"n",ct.getResources().getColor(R.color.strpan1),0);
    				  soundPlayer.playSound(1,"n");
    			  }
    			  
    		  }if(main1et1.getSelectionStart()-1>funa1 && main1et1.getSelectionStart()<=funb1 && funa4==-1 || 
    			   (main1et1.getSelectionStart()+1>funa4 && main1et1.getSelectionStart()<=funb1 && funa4!=-1)){
    			      		
    			  //if(!s.equals("]") && !s.equals("∑") && !s.equals("{") && !s.equals("}") && funb2!=main1et1.getSelectionStart()-1 && ){
    			  if(s.matches("[^∑\\]\\{\\}]") && funb2!=main1et1.getSelectionStart()-1 && main1et1.getSelectionStart()!=funb1){
    				  if((funa3==-1 && funb3==-1) || funa3<funa4 || (funa3!=-1 && funb3!=-1 && (main1et1.getSelectionStart()<funa3 || main1et1.getSelectionStart()>funb3))){
    					  inputnormal(a, s);
    				  }else{     					  
    					  if(stra.matches("(1|2|6|7|8|11|12|13|16|17|18)")){
    						  inputnormal(a, s);
    					  }
    				  }   				  
    			  }if(s.matches("[\\]\\)]") && stra.matches("(4|9|14|19)") && main1et1.getSelectionStart()==funb1 
    					  && main1et1.getText().toString().lastIndexOf("∑")!=-1){			  
    				  inputnormal(a, s);
				  }if(a==29){
    				  int u=main1et1.getSelectionStart();
    				  int v=main1et1.getText().length()-1;
    				  if(s.equals("[")){
    					  for(int i=u;i<main1et1.getText().length();i++){
    						  String ssa1=""+main1et1.getText().charAt(i);
    						  if(ssa1.equals("]")){
    							  v=i;
    							  break;
    						  }
    					  }
    					main1et1.getText().delete(u-1, v+1);
    				  }if(s.equals("]")){
    					      v=funa1;
        					  for(int i=u;i>v;i--){
        						  String ssa1=""+main1et1.getText().charAt(i);
        						  if(ssa1.equals("[")){
        							  v=i;
        							  break;
        						  }
        					  }
        				main1et1.getText().delete(v, u);
    				  }if(s.equals("∑")){   					  
    					  /*main1et1.getText().delete(main1et1.getSelectionStart()-1, main1et1.getSelectionStart());
    					  int u1=main1et1.getText().toString().indexOf("[");
    					  int v1=main1et1.getText().toString().indexOf("]");    					  				  
    					  if(u1!=-1 && v1!=-1 && u1<=v1){
    						  main1et1.getText().delete(u1, v1+1);  
    					  }
    					  int u2=main1et1.getText().toString().lastIndexOf("[");
    					  int v2=main1et1.getText().toString().lastIndexOf("]");   	
    					  if(u2!=-1 && v2!=-1 && u2<=v2){    	
    						  main1et1.getText().delete(u2, v2+1); 
    					  }   				*/
    					  if (main1et1.getSelectionStart()>=1) {
    						  main1et1.setSelection(main1et1.getSelectionStart()-1);
    					  }
    				  }
    				  int dsn=0;
    				  if((!s.equals("]") && !s.equals("[") && !s.equals("∑") && !s.equals("{") && !s.equals("}") && !s.equals(",") && 
    						  funa2!=main1et1.getSelectionStart()-1 && funb2!=main1et1.getSelectionStart()-1) || (main1et1.getSelectionStart()-1>funa2 && main1et1.getSelectionStart()<=funb2)){
    					  main1et1.getText().delete(main1et1.getSelectionStart()-1, main1et1.getSelectionStart()); 
    					  dsn=1;
    				  }if((funa2==main1et1.getSelectionStart()-1 || funb2==main1et1.getSelectionStart()-1 || s.equals(",")) && dsn==0){    					   
    					  main1et1.setSelection(main1et1.getSelectionStart()-1);    					     	
    				  }if(main1et1.getText().toString().lastIndexOf("∑")==-1){
    					  int de1=main1et1.getText().toString().lastIndexOf("}");
    			    	  int de2=main1et1.getText().toString().lastIndexOf(")"); 
    					  main1et1.getText().delete(de2+1, de1); 
    				  }
    				   funa3=main1et1.getText().toString().lastIndexOf("["); 
    				   funa4=main1et1.getText().toString().lastIndexOf("∑"); 
    				   funa5=main1et1.getText().toString().indexOf("[");  
    				  funtype(funa3,funa4,funa5);
    				  
    			  }
    		  }
    	}
    	   /*{"n",".","0","=","+","√","1","2","3","-","^","4","5","6","×",
               "( )","7","8","9","÷","sin","cos","tan","log","ln","×","π","∑","e","c"}*/
    	   etcursor();    	  
    }   
    
    //计算结果优化
    public String unityScale(String s1) {
		// TODO Auto-generated method stub
    	
    	int a=s1.indexOf(".");
    	String s2=s1.substring((a+1), s1.length());
    	int isscale=-1;
    	if (a!=-1 && globe.demic>5) {
    		for (int i = 0; i < (s2.length()-4); i++) {
    			String ch=s2.charAt(i)+"";
    			if (ch.equals("0") && (isscale==0 || isscale==-1)) {
    				isscale=0;
				}if (ch.equals("0") && isscale==9) {
					isscale=-1;
					break;
				}if (ch.equals("9") && isscale==0) {
					isscale=-1;
					break;
				}if (ch.equals("9") && (isscale==9 || isscale==-1)) {
					isscale=9;
				}if (!ch.equals("0") && !ch.equals("9")) {
					isscale=-1;
					break;
				}
    		}
    		if (isscale==0 || isscale==9) {
				s1=new BigDecimal(s1).setScale(0, RoundingMode.HALF_UP).toPlainString();
			}
		}
		return s1;
		
	}

	private void funtype24Str(String str, int type, String copymatches) {
		// TODO Auto-generated method stub
		Log.e("", globe.funtype+"<=0=0=>" + str+"::"+copymatches);
		globe.cdTask=new CalDataTask();
    	globe.cdTask.globe=globe;globe.cdTask.ct=ct;
    	globe.cdTask.execute(str,(type+""),copymatches);
 	
	} 


	public Object[] funismatch(int funa1, int funb1, int funa2, int funb2) throws Exception {
		// TODO Auto-generated method stub
    	String matches1=main1et1.getText().toString().substring(funa1+1, funb1);
		String matches2=main1et1.getText().toString().substring(funa2+1, funb2);
		boolean matchs = false; 
		matchs=judge.isfun(matches1,matches2, globe.funtype);
		String copymatches2="("+matches2+")";
		if(matchs==true){
			
			int n=-1;boolean find=true;
			while (find) {
				Pattern pattern=Pattern.compile("[\\+|×|\\-|÷|\\^√\\(]n[\\+|×|\\-|÷|\\^\\)]");
				Matcher matcher=pattern.matcher(copymatches2);find=false;
				while (matcher.find()) {
					String s=matcher.group();
					copymatches2=globe.calculate.inc_subreplace(copymatches2, "1", (matcher.start()+s.indexOf("n")), (matcher.start()+s.indexOf("n")));
					n=1;find=true;
				}
			}
			
			if(globe.funtype==1 || globe.funtype==3){
				if((matches2.indexOf("X")==-1 && matches2.indexOf("Y")==-1) || n!=-1){
					Log.e("", "<<<<<<<nnnnnnnnn>>>>>>>>"+n);
					matchs=false;
				}
			}if(globe.funtype==2 || globe.funtype==4){
				if(n==-1 || matches2.indexOf("X")!=-1 || matches2.indexOf("Y")!=-1 || matches2.indexOf("AVG")!=-1){
					matchs=false;
				}
			}if(globe.funtype==5){
				if(n!=-1 || (matches2.indexOf("X")==-1 && matches2.indexOf("Y")==-1) || matches2.indexOf("AVG")!=-1){
					matchs=false;
				}
			}if (matchs) {
				copymatches2=copymatches2.replace("X", "1");
				copymatches2=copymatches2.replace("Y", "1");
				copymatches2=copymatches2.replace("AVG", "1");
				//copymatches2=globe.calculate.calculate(copymatches2);
				if (copymatches2==null) {
					matchs=false;
				}
			}
		}
		return new Object[]{matchs,matches1,copymatches2};		
	}

	private BigDecimal getAvg(BigDecimal sumDecimal) {
		// TODO Auto-generated method stub
    	for (String xyStr : globe.xy) {
			try {
				 sumDecimal=sumDecimal.add(new BigDecimal(xyStr));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		 if (globe.xy.size()>=1) {
			 sumDecimal=sumDecimal.divide(new BigDecimal((globe.xy.size()+"")), (globe.demic+2), RoundingMode.HALF_UP);
			 /*calculate的demic？*/
		}
		return sumDecimal;
	}

	public String getIndexStr(String indStr) {
		// TODO Auto-generated method stub
		
    	/*indStr=indStr.substring(0,(indStr.length()-1));
    	Log.e("", "getIndexStr====>"+indStr);*/
    	int a1=0;int num=1;
    	for (int i = indStr.length()-3; i >=0; i--) {
    		String str=indStr.charAt(i)+"";
    		if (str.equals(")")) {
    			num=num+1;
			}if (str.equals("(")) {
				num=num-1;
			}if (num==0) {
				a1=i;
				break;
			}
		}
    	indStr=indStr.substring(0,(a1-1))+")";
    	Log.e("", "getIndexStr======>"+indStr);
    	return indStr;
	}

	public void inputnormal(int a, String s){
		String playStr="";
       if(a==2 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("∑") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"0",ct.getResources().getColor(R.color.strpan1),0);
   		   playStr="zero";
       }if(a==6 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("∑") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"1",ct.getResources().getColor(R.color.strpan1),0);
   		   playStr="one";
       }if(a==7 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("∑") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"2",ct.getResources().getColor(R.color.strpan1),0);
   		   playStr="two";
       }if(a==8 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("∑") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"3",ct.getResources().getColor(R.color.strpan1),0);
   		playStr="three";  
       }if(a==11 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("∑") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"4",ct.getResources().getColor(R.color.strpan1),0);
   		playStr="four";  
       }if(a==12 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("∑") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"5",ct.getResources().getColor(R.color.strpan1),0);
   		playStr="five";  
       }if(a==13 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("∑") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"6",ct.getResources().getColor(R.color.strpan1),0);
   		playStr="six";  
       }if(a==16 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("∑") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"7",ct.getResources().getColor(R.color.strpan1),0);
   		playStr="seven";  
       }if(a==17 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("∑") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"8",ct.getResources().getColor(R.color.strpan1),0);
   		playStr="eight";  
       }if(a==18 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"9",ct.getResources().getColor(R.color.strpan1),0);
   		playStr="nine";  
       }if(a==28 && !globe.isfun1save && !globe.isfuning){
   		   main1et1.setText("");      		   
   	   }if(a==29 && main1et1.getSelectionStart()>0 && !globe.isfun1save && !globe.isfuning){
   		   main1et1.getText().delete(main1et1.getSelectionStart()-1, main1et1.getSelectionStart());
   		playStr="D";  
   	   }if(a==1 && !s.equals("π") && !s.equals("e") && !s.equals(")") && !s.equals(".") && 
   			   !s.equals("+") && !s.equals("∑") && !s.equals("-") && !s.equals("×") && !s.equals("÷")
   			   && !s.equals("√") && !s.equals("^") && !s.equals("(") && !s.equals("")){
   		   addSpannableStr(main1et1.getSelectionStart(),".",ct.getResources().getColor(R.color.strpan1),0);
   		playStr="point";  
   	   }if(!s.equals(".") && !s.equals("-") && !s.equals("×") && !s.equals("÷") && !s.equals("√")
   			   && !s.equals("^") && !s.equals("(") && !s.equals("+") && !s.equals("")){
   		   if(a==4){  
   			   addSpannableStr(main1et1.getSelectionStart(),"+",ct.getResources().getColor(R.color.strpan5),0);
   			playStr="plus";  
   		   }if(a==14){
   			   addSpannableStr(main1et1.getSelectionStart(),"×",ct.getResources().getColor(R.color.strpan5),0);
   			playStr="multiply";  
   		   }if(a==19){
   			   addSpannableStr(main1et1.getSelectionStart(),"÷",ct.getResources().getColor(R.color.strpan5),0);
   			playStr="divide";  
   		   }		   
   	   }if(a==9 && !s.equals(".") && !s.equals("-") && !s.equals("×") && !s.equals("÷") && !s.equals("√")
   			    && !s.equals("+")){
			   addSpannableStr(main1et1.getSelectionStart(),"-",ct.getResources().getColor(R.color.strpan5),0);
			   playStr="subtract";  
   	   }if(a==5 && !s.equals(".") && !s.equals("π") && !s.equals("e") && !s.equals("0")
   			   && !s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4") && !s.equals("5")
   			   && !s.equals("6") && !s.equals("7") && !s.equals("8") && !s.equals("9") && !s.equals(")")){
   		   addSpannableStr(main1et1.getSelectionStart(),"√",ct.getResources().getColor(R.color.strpan3),0);
   		playStr="sqr";  
   	   }if(a==10 && !s.equals("^") && !s.equals("(") && !s.equals(".") && !s.equals("+") && !s.equals("-") 
   			   && !s.equals("×") && !s.equals("÷") && !s.equals("√") && !s.equals("")){
   		   addSpannableStr(main1et1.getSelectionStart(),"^",ct.getResources().getColor(R.color.strpan3),0);
   		playStr="power";  
   	   }if(a==15 && !s.equals(")") && !s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4")
   			   && !s.equals("5") && !s.equals("6") && !s.equals("7") && !s.equals("8") && !s.equals("9")
   			   && !s.equals(".") && !s.equals("0") && !s.equals("π") && !s.equals("e") && !s.equals("n")){
   		   addSpannableStr(main1et1.getSelectionStart(),"()",ct.getResources().getColor(R.color.strpan3),1);
   		playStr="bracket";
   	   }if(a==26){
   		   	cursormove(1);
   			//addSpannableStr(main1et1.getSelectionStart(),"π",ct.getResources().getColor(R.color.strpan1),0);
   	   }if(a==25){
   		   cursormove(-1);
   			//addSpannableStr(main1et1.getSelectionStart(),"e",ct.getResources().getColor(R.color.strpan1),0);
   	   }if (a==3) {
   		playStr="equal";
   	   }/*if (a==29) {
   		   Vibrator vibrator = (Vibrator)ct.getSystemService(Service.VIBRATOR_SERVICE);
   		   long[] pattern = {0, 1, 20, 21,30,31}; 
   		   vibrator.vibrate(pattern,-1);
   	   }*/
   		/*if(!s.equals("1") && !s.equals("2") && !s.equals("3") && !s.equals("4") && !s.equals("5") 
   		   		 && !s.equals("6") && !s.equals("7") && !s.equals("8") && !s.equals("9")&& !s.equals(".") 
   		   		 && !s.equals("0") && !s.equals(")") && !s.equals("π") && !s.equals("e") && !s.equals("n")){
   		}*/
	   	if (soundPlayer==null) {
			soundPlayer=new SoundPlayer(ct);
		}if (!"".equals(playStr)) {
			soundPlayer.playSound(1,playStr);
		}
	
    }
    
    private void cursormove(int index) {
		// TODO Auto-generated method stub
		
    	int selectIndex = main1et1.getSelectionStart();
    	selectIndex=selectIndex+index;
    	int len=main1et1.getText().toString().length();
    	if (selectIndex>=0 && selectIndex<=len) {
    		main1et1.setSelection(selectIndex);
		}
    	
	}

	public String removie0(String s1) {
    	
    	if (s1.length()>=2) {
    		String s=s1.charAt(s1.length()-1)+"";
        	while ((s.equals("0") || s.equals(".")) && s1.length()>=2 && s1.indexOf(".")!=-1) {
    			s1=s1.substring(0, (s1.length()-1));
    			if (s.equals(".")) {
					break;
				}
    			s=s1.charAt(s1.length()-1)+"";
    		}
		}
		return s1;
	}
    
	public void addSpannableStr(int a, String s, int b, int c) {
		SpannableString spanText = new SpannableString(s);
		spanText.setSpan(new ForegroundColorSpan(b), 0, spanText.length(),
				Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		main1et1.getText().insert(a, spanText);
		main1et1.setSelection(main1et1.getSelectionStart() - c);
		//addSpannableStr(main1et1.getSelectionStart(),"0",ct.getResources().getColor(R.color.strpan1),0);
	}
	
	public void funreplace(){
    	String s=funet1.getText().toString();
    	
    	if(globe.fununknow==1){    		   		
    	    s=s.replace("X", globe.funinputx);
    		s=s.replace("Y", globe.funinputx);
    		globe.xy.add(globe.funinputx);
    	}if(globe.fununknow==2){
    		s=s.replace("X", globe.funinputx);
    		s=s.replace("Y", globe.funinputy);
    		globe.xy.add(globe.funinputx);
    		globe.xy.add(globe.funinputy);
    	}
    	funet1.setText(s);
    	int a=funet1.getText().toString().lastIndexOf(")");
    	funet1.getText().insert(a, globe.funcharacter);
    }
	
	public void funinput(){
    	
    	int a=funet1.getText().toString().lastIndexOf(")");
    	SpannableString spanText = new SpannableString(globe.funbody1);
    	if(globe.fununknow>=1){   		
    		for(int i=0;i<globe.funxy.size();i++){
    			spanText.setSpan(new BackgroundColorSpan(Color.rgb(222, 159, 16)), globe.funxy.get(i), globe.funxy.get(i)+1,
        			    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
    		}   		 
    	}
    	funet1.getText().insert(a, spanText);   	    	
    } 
	
	public void mainet1fxy(){
    	
    	int a=main1et1.getText().toString().indexOf("}");
    	
    	SpannableString spanText = new SpannableString(globe.xory1);
   	    if(globe.fununknow!=0){   	    	    	
  	    	    spanText.setSpan(new ForegroundColorSpan(Color.rgb(193, 65, 11)),0, spanText.length(),
  	    			    Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
  	    	    if(a==-1){
  	    	    	main1et1.getText().insert(main1et1.getSelectionStart(), spanText);
  	    	    	main1et1.setSelection(main1et1.getSelectionStart()+spanText.length());
  	    	    }if(a!=-1){
  	    	    	main1et1.getText().insert(a, spanText);
  	    	    	a=main1et1.getText().toString().indexOf("}");
  	    	    	main1et1.setSelection(a);
  	    	    }
  	    	    
   	    }if(globe.fununknow==1){
   	    	main1et1.setSelection(main1et1.getSelectionStart()-1);
   	    }if(globe.fununknow==2){
   	    	main1et1.setSelection(main1et1.getSelectionStart()-2);
   	    }
    }
	
	public void etcursor(){
    	if(main1et1.length()>20){
	    	main1et1.setGravity(Gravity.LEFT|Gravity.BOTTOM);
	    } if(main1et1.length()<=20){
	    	main1et1.setGravity(Gravity.RIGHT|Gravity.BOTTOM);
	    }
    }   
	
	public void funcancer(String str) {

		globe.btarray.get(27).setText("∑");
		if (str.equals("")) {
			main1et1.setText("");
		}
		/*if (main1et1.getSelectionStart()!=-1) {
			addSpannableStr(0,str,ct.getResources().getColor(R.color.strpan1),0);
		}*/
		globe.isfun1save = false;
		globe.isfuning = false;
		if (main1hsv1.indexOfChild(funet1) != -1) {
			main1hsv1.removeView(funet1);
			main1hsv1.addView(main1ll1);
		}
	}
	
	public void funtype(int funa3, int funa4, int funa5){
		
		if(funa5 == -1 && funa3 == -1 && funa4 == -1) {
			globe.funtype=5;//()
		}if(funa5 == -1 && funa3 == -1 && funa4 != -1) {
			globe.funtype=1;//∑()
		}if(funa5 != -1 && funa3 != -1 && funa5 == funa3 && funa4 != -1) {										
			if(funa5 < funa4) {
				globe.funtype=3;//[1/N]∑()
			}if(funa5 > funa4) {
				globe.funtype=2;//∑()[]
			}
		}if(funa5 != -1 && funa3 != -1 && funa5 != funa3 && funa4 != -1) {
			globe.funtype=4;//[1/N]∑()[,,]                                                                                           
		}
		
	}
	
	public void funing(String etstr){
		
		int a1,b1,c1,d1,e1; 
		a1=globe.funnote .indexOf("[");               
		b1=globe.funnote .lastIndexOf("[");
		c1=globe.funnote .indexOf("∑");
		d1=globe.funnote .indexOf("(");
		e1=	globe.funnote .lastIndexOf(")");
		
		globe.isfuning=true;		
		globe.TextSize=main1tv4.getTextSize();    			
		main1hsv1.removeView(main1ll1);
		globe.btarray.get(27).setText("→");   			
		globe.fununknow=0;String xory="";globe.xory1=""; 			   	    	
		if(globe.funnote .indexOf("X")!=-1 && globe.funnote .indexOf("Y")==-1){
			globe.fununknow=1;
			xory="(x)";
			globe.xory1="f()";
		}if(globe.funnote .indexOf("Y")!=-1 && globe.funnote .indexOf("X")==-1){
			globe.fununknow=1;
			xory="(y)";
			globe.xory1="f()";
		}if(globe.funnote .indexOf("Y")!=-1 && globe.funnote .indexOf("X")!=-1){
			globe.fununknow=2;
			xory="(x,y)";
			globe.xory1="f(,)";
		}
		if(globe.funnote .indexOf("N")!=-1){
			String sn1=globe.funnote .substring(globe.funnote .indexOf("["), globe.funnote .indexOf("]")+1);
			addSpannableStr(main1et1.getSelectionStart(),sn1+"{}",ct.getResources().getColor(R.color.strpan3),1);
			soundPlayer.playSound(1, etstr);
		}if(globe.funnote .indexOf("N")==-1){
			addSpannableStr(main1et1.getSelectionStart(),"{}",ct.getResources().getColor(R.color.strpan3),1);
			soundPlayer.playSound(1, etstr);
		}
		mainet1fxy();  	    	 
		funet1.setTextSize(TypedValue.COMPLEX_UNIT_PX, globe.TextSize);funet1.setText("");   			   			
		main1hsv1.addView(funet1,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);   
		globe.funcharacter=globe.funnote .charAt(globe.funnote .length()-1)+"";//main1hsv1    			  			
		String body=globe.funnote .substring(0, c1);
		body=body+globe.funnote .substring(d1, d1+1);
		body=body+globe.funnote .substring(e1, globe.funnote .length()-1);
		
		globe.funinputx="";globe.funinputy="";    			
		funet1.setText(etstr+xory+"="+body);
		globe.funbody1="("+globe.funnote .substring(d1+1, e1)+")";   
		globe.xy=new ArrayList<String>();
		arrayfunxy();    			
		funinput();
	}
	
	  private void arrayfunxy(){
	    	
	    	globe.funxy=new ArrayList<Integer>();
	    	for(int i=0;i<globe.funbody1.length();i++){
	    		String s=""+globe.funbody1.charAt(i);
	    		if(s.equals("X") || s.equals("Y")){
	    			globe.funxy.add(i);
	    			globe.allfunxy.add(i);
	    		}
	    	}
	    }   
	
	 public void inputResult(String result) {
		 
		 if(result.equals("-e")){
			 result="-"+CustomValues.e.setScale(globe.demic, RoundingMode.HALF_UP);
		 }if(result.equals("e")){
			 result=""+CustomValues.e.setScale(globe.demic, RoundingMode.HALF_UP);
		 }if(result.equals("-π")){
			 result="-"+CustomValues.π.setScale(globe.demic, RoundingMode.HALF_UP);
		 }if(result.equals("π")){
			 result=""+CustomValues.π.setScale(globe.demic, RoundingMode.HALF_UP);
		 }
		 BigDecimal bigDecimal=new BigDecimal(result);
		 result=bigDecimal.setScale(globe.demic, RoundingMode.HALF_UP).toPlainString();
		 result=removie0(result);
		 String s=globe.main1et1.getText().toString();
		 int a=s.indexOf("{");int b=s.indexOf("}");
		 globe.main1et1.getText().delete(a, (b+1));
		 globe.main1inputtext.addSpannableStr(a, ("("+result+")"), ct.getResources().getColor(R.color.strpan1), 0);
	}
}











