package com.emple.calculatorqb;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.R.integer;
import android.R.string;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.GetChars;
/*import android.util.Log;*/
import android.util.Log;

public class Calculate {
	
	 Globe globe;
	 SQLiteDatabase db;
	 Context ct;
	 ArrayList<Integer> unitseleid=new ArrayList<Integer>();	
	 int demic;
	 int subtime=0,subinctime=0;
	 String degrees;

	public String calculate(String s){
				
		String s1=s;globe.hasUnity=false;
		
		if(s1.indexOf("+)")!=-1 || s1.indexOf("-)")!=-1 || s1.indexOf("÷)")!=-1 || s1.indexOf("×)")!=-1 || 
	               s1.indexOf("^)")!=-1 || s1.indexOf("√)")!=-1 || s1.indexOf(".)")!=-1 || s1.indexOf("(^")!=-1 
	                || s1.indexOf("(.")!=-1 || s1.indexOf("(+")!=-1 || s1.indexOf("()")!=-1
	               || s1.indexOf("(÷")!=-1 || s1.indexOf("(×")!=-1 || s1.indexOf(".-")!=-1 || s1.indexOf(".+")!=-1
	               || s1.indexOf(".÷")!=-1 || s1.indexOf(".×")!=-1 || s1.indexOf(".^")!=-1 || s1.indexOf(".√")!=-1
	               || s1.indexOf("-.")!=-1 || s1.indexOf("+.")!=-1 || s1.indexOf("÷.")!=-1 || s1.indexOf("×.")!=-1 
	               || s1.indexOf("^.")!=-1 || s1.indexOf("√.")!=-1){
				s1=null;
		}
		
		s1=eπjudge(s1);
		
		while(s1!=null && (s1.indexOf("sin")!=-1 || s1.indexOf("cos")!=-1 || s1.indexOf("tan")!=-1 || s1.indexOf("log")!=-1
				 || s1.indexOf("ln")!=-1 || s1.indexOf("asin")!=-1 || s1.indexOf("acos")!=-1 || s1.indexOf("atan")!=-1)){
			s1=tonumber(s1);
		}
		if(s1!=null){
			s1=calculinc(s1);
		}String s2Uni="have";
		while (s1!=null && s2Uni.equals("have")) {
			String[] sn=removieunit(s1);
			s1=sn[0];s2Uni=sn[1];
		}
		//Log.e("", "calculate=======>"+s1);
		return s1;
	}

	public String eπjudge(String s1) {
		// TODO Auto-generated method stub
		
		if (s1!=null && (s1.indexOf("e1")!=-1 || s1.indexOf("e2")!=-1  || s1.indexOf("e3")!=-1  || s1.indexOf("e4")!=-1 
				|| s1.indexOf("e5")!=-1 || s1.indexOf("e6")!=-1  || s1.indexOf("e7")!=-1  || s1.indexOf("e8")!=-1 
				|| s1.indexOf("e9")!=-1 || s1.indexOf("e0")!=-1 || s1.indexOf("0e")!=-1 || s1.indexOf("1e")!=-1 || 
				s1.indexOf("2e")!=-1  || s1.indexOf("3e")!=-1  || s1.indexOf("4e")!=-1  || s1.indexOf("5e")!=-1
				 || s1.indexOf("6e")!=-1  || s1.indexOf("7e")!=-1  || s1.indexOf("8e")!=-1 || s1.indexOf("9e")!=-1
				 || s1.indexOf("e.")!=-1 || s1.indexOf(".e")!=-1 || s1.indexOf("e√")!=-1)) {
			s1=null;
		}if (s1!=null && (s1.indexOf("π1")!=-1 || s1.indexOf("π2")!=-1  || s1.indexOf("π3")!=-1  || s1.indexOf("π4")!=-1 
				|| s1.indexOf("π5")!=-1 || s1.indexOf("π6")!=-1  || s1.indexOf("π7")!=-1  || s1.indexOf("π8")!=-1 
				|| s1.indexOf("π9")!=-1 || s1.indexOf("π0")!=-1 || s1.indexOf("0π")!=-1 || s1.indexOf("1π")!=-1 || 
				s1.indexOf("2π")!=-1  || s1.indexOf("3π")!=-1  || s1.indexOf("4π")!=-1  || s1.indexOf("5π")!=-1
				 || s1.indexOf("6π")!=-1  || s1.indexOf("7π")!=-1  || s1.indexOf("8π")!=-1 || s1.indexOf("9π")!=-1
				 || s1.indexOf("π.")!=-1 || s1.indexOf(".π")!=-1 || s1.indexOf("π√")!=-1)) {
			s1=null;
		}if (s1!=null && (s1.indexOf("eπ")!=-1 || s1.indexOf("πe")!=-1)) {
			s1=null;
		}
		
		return s1;
	}

	public String tonumber(String s){
	 
		//Pattern pattern = Pattern.compile("(sin|cos|tan|log|ln|asin|acos|atan)\\([0123456789\\+\\-×÷πe.√\\^,]*([\\(][0123456789\\+\\-×÷πe.√\\^,]*[\\)])*\\)");//先将sin等函数化为数字	
		//Pattern pattern = Pattern.compile("(sin|cos|tan|log|ln|asin|acos|atan)\\([[\\(][0123456789\\+\\-×÷πe.√\\^,]*[\\)]|[0123456789\\+\\-×÷πe.√\\^,]]*\\)");//先将sin等函数化为数字
		Pattern pattern = Pattern.compile("(sin|cos|tan|log|ln|asin|acos|atan)\\([0123456789\\+\\-×÷πe.√\\^\\(\\),]*\\)");//先将sin等函数化为数字
		Matcher matcher = pattern.matcher(s);
		
		boolean boo=false;
		while(matcher.find() && s!=null){//解决后面多括号问题
			//Log.e("", "tonumber<<<"+matcher.group());
			//s=numinsertjudg(s, matcher);
			
			String group="";boo=true;
			int strat=matcher.group().indexOf("(")+1;
			int end=0;int a=0;
			for(int i=strat;i<matcher.group().length();i++){
				String sa=""+matcher.group().charAt(i);
				if(sa.equals("(")){
					a=a+1;
				}if(sa.equals(")")){
					a=a-1;
				}if(a==-1){
					end=i;
					break;
				}
			}			
			group=matcher.group().substring(0, (end+1));
			//Log.e("", "<======tonumber1======>"+group);
			s=numinsertjudg(s,matcher.start(),(matcher.start()+end+1));
			if(s!=null){
				String s2=calculatefun1(group);   
				if(s2==null){
					s=null;
					break;
				}if(s!=null){
					/*s=s.replace(group, s2);	修改*/
					s=inc_subreplace(s,("("+s2+")"),matcher.start(),(matcher.start()+end));					
				}	
			}
			break;
		}if (!boo) {
			s=null;
		}
		return s;
	}
	
	public String calculatefun1(String s){
		
		String s1 = "";String s2 = "";String s0 = "";
		int a = s.indexOf("(");
		int b = s.lastIndexOf(")");
		//Log.e("", s + "<======calculatefun1===>" + s.substring(a + 1, b));
		s1 = s.substring(a + 1, b);
		boolean boo=true;
		for (int i = 0; i < s1.length(); i++) {
			String str=s1.charAt(i)+"";
			if(!str.matches("(0|1|2|3|4|5|6|7|8|9|\\(|\\)|.|\\+|\\-|×|÷|√|\\^|π|e|,)")){
				boo=false;
				s0=null;
				break;
			}
		}
		if(s1.equals("") || !boo){
			//Log.e("", "<======calculatefun1a===>" + s.substring(a + 1, b));
			s0=null;
		}		
		if(s0!=null){
			
			int run=0;
			if(s!=null && s.indexOf("asin")!=-1){
				run=1;s2=calculinc(s1);
				double[] dn=fun1deal(s2);	
				if(dn[0]==-1){
					s=null;
				}if(dn[0]==1){
					if(dn[1]>=-1 && dn[1]<=1){
						String sto=Math.toDegrees(Math.asin(dn[1]))+"";
						s=new BigDecimal(sto).toPlainString();
						//Log.e("", "asin============>"+s);
					}else{
						s=null;
					}				
				}
			}if(s!=null && s.indexOf("acos")!=-1){
				run=1;s2=calculinc(s1);
				double[] dn=fun1deal(s2);		
				if(dn[0]==-1){
					s=null;
				}if(dn[0]==1){
					if(dn[1]>=-1 && dn[1]<=1){
						String sto=Math.toDegrees(Math.acos(dn[1]))+"";
						s=new BigDecimal(sto).toPlainString();
						//Log.e("", "acos============>"+s);
					}else{
						s=null;
					}				
				}
			}if(s!=null && s.indexOf("atan")!=-1){
				run=1;s2=calculinc(s1);
				double[] dn=fun1deal(s2);		
				if(dn[0]==-1){
					s=null;
				}if(dn[0]==1){					
					String sto=Math.toDegrees(Math.atan(dn[1]))+"";
					s=new BigDecimal(sto).toPlainString();
					//Log.e("", "atan============>"+s);			
				}
			}if(s!=null && s.indexOf("sin")!=-1 && run==0){
				s2=calculinc(s1);
				double[] dn=fun1deal(s2);		
				if(dn[0]==-1){
					s=null;
				}if(dn[0]==1){
					dn[1]=todegrees(dn[1]);
					String sto=Math.sin(dn[1])+"";
					s=new BigDecimal(sto).toPlainString();
					//Log.e("", "sin============>"+s);
				}
			}if(s!=null && s.indexOf("cos")!=-1 && run==0){
				s2=calculinc(s1);
				double[] dn=fun1deal(s2);
				if(dn[0]==-1){
					s=null;
				}if(dn[0]==1){
					dn[1]=todegrees(dn[1]);
					String sto=Math.cos(dn[1])+"";
					s=new BigDecimal(sto).toPlainString();
					//Log.e("", "cos============>"+s);
				}
			}if(s!=null && s.indexOf("tan")!=-1 && run==0){
				s2=calculinc(s1);
				double[] dn=fun1deal(s2);
				if(dn[0]==-1){
					s=null;
				}if(dn[0]==1){
					if(Math.cos(dn[1])==0){
						s=null;
					}if(Math.cos(dn[1])!=0){
						dn[1]=todegrees(dn[1]);
						String sto=Math.tan(dn[1])+"";
						s=new BigDecimal(sto).toPlainString();
						//Log.e("", "tan============>"+s);
					}					
				}
			}if(s!=null && s.indexOf("log")!=-1){
				//Math.
				/*s2=calculinc(s1);
				double[] dn=fun1deal(s2);
				if(dn[0]==-1){
					s=null;
				}if(dn[0]==1){	
					
				}*/
				String[] sa=s1.split(",");			
				if(sa.length==2){
					sa[0]=calculinc(sa[0]);
					sa[1]=calculinc(sa[1]);
					
					double[] dn1=fun1deal(sa[0]);
					double[] dn2=fun1deal(sa[1]);
					if (dn1[0]==1 && dn2[0]==1) {
						//BigDecimal bigDecimal1=new BigDecimal(sa[0]);
						//BigDecimal bigDecimal2=new BigDecimal(sa[1]);
						
						if(dn1[1]>0 && dn1[1]!=1 
								&& dn2[1]>0){
							String sto=""+Math.log(dn2[1])/Math.log(dn1[1]);
							s=new BigDecimal(sto).toPlainString();
						}else{
							s=null;
						}
					}else {
						s=null;
					}
					
					//Log.e("", "log==========>"+s);
				}else{
					s=null;
				}
				//Log.e("", "log============>"+Math.log(0));
				
			}if(s!=null && s.indexOf("ln")!=-1){
				s2=calculinc(s1);
				double[] dn=fun1deal(s2);
				if(dn[0]==-1 || dn[1]<=0){
					s=null;
				}if(s!=null && dn[0]==1){					
					//Log.e("", "ln============>"+Math.log(dn[1]));
					String sto=""+Math.log(dn[1]);
					s=new BigDecimal(sto).toPlainString();
				}
			}
		}
		return s;		
	}
	public double[] fun1deal(String s2){
		
		double[] dn=new double[2];dn[0]=-1;dn[1]=-1;
		double d=-1;
		if(s2.matches("-?([0-9]+([.]([0-9]+)|([0-9]*))|π|e)")){
			CustomValues CustomValues=new CustomValues();
			if(s2.indexOf("π")!=-1){
			    BigDecimal sbd=CustomValues.π;
			    d=sbd.doubleValue();
			}if(s2.indexOf("e")!=-1){
				 BigDecimal sbd=CustomValues.e;
				 d=sbd.doubleValue();
			}if(s2.indexOf("π")==-1 && s2.indexOf("e")==-1){
				d=Double.parseDouble(s2);
			}
			dn[0]=1;dn[1]=d;
		}
		return dn;
	}
	
	public double todegrees(double d){
		
		if(degrees.equals("degree")){
			d=Math.toRadians(d);
			//Log.e("", "todegrees============>");
		}
		return d;
	}
	
	public String calculinc(String s1){
		
		String s2Uni="have";
		while (s1!=null && s2Uni.equals("have")) {
			String[] sn=removieunit(s1);
			s1=sn[0];s2Uni=sn[1];
		}if (s1!=null) {
			s1=eπjudge(s1);
			if (s1!=null) {
				s1=espectCal(s1);
				
				Pattern pattern=Pattern.compile("\\([0123456789.\\+\\-×÷πe√\\^]*\\)");
			    Matcher matcher=pattern.matcher(s1);
			    boolean isright=false;
				while(matcher.find() && s1!=null){
					isright=true;
					String s3=matcher.group().replace("(", "");
					s3=s3.replace(")", "");
					String s2=calculnum(s3);
					String sn2=inc_subreplace(s1,s2,matcher.start(),(matcher.end()-1));
					if (sn2.indexOf("√-")!=-1) {
						s1=null;
					}
					if (s1!=null && s2==null) {
						s1=null;
						break;
					}if(s1!=null && s2!=null && s2.indexOf("-")!=-1){
											
						//String sadd=s1.replace(oldChar, newChar);
						String sadd=inc_subreplace(s1,s2,(matcher.start()+1),(matcher.end()-2));
						Pattern pattern1 = Pattern.compile("\\(-([0-9]+([.]([0-9]+)|([0-9]*))|e|π)\\)[\\^][-]?([0-9]+([.]([0-9]+)|([0-9]*))|e|π)");//计算n次方
						Matcher matcher1 = pattern1.matcher(sadd);
						if (matcher1.find()) {
							//s1=inc_subreplace(s1,s2,matcher.start(),(matcher.end()-1));
							s1=espectCal(sadd);
						}else {
							if(matcher.start()==0){
								s1=numinsertjudg(s1, matcher.start(),matcher.end());
			                    if(s1==null){
									break;
								}
								s1=inc_subreplace(s1,s2,matcher.start(),(matcher.end()-1));
							}if(matcher.start()>0){
								s1=numinsertjudg(s1, matcher.start(),matcher.end());
			                    if(s1==null){
									break;
								}
								int len1=s1.length();int a=matcher.start();int b=matcher.end()-1;
								String[] strs=suninc(s1,(matcher.start()-1));
								//s1=suninc(s1,(matcher.start()-1));
								s1=strs[0];
								int len2=s1.length();
								if(len1>len2){
									a=a-1;
									b=b-1;
								}if(len1<len2){
									a=a+1;
									b=b+1;
								}		
								if(strs[1].equals("")) {
									s1=inc_subreplace(s1,s2.replace("-", ""),a,b);
								}if(strs[1].equals("1")) {
									s1=inc_subreplace(s1,s2,a,b);
								}
							}
						}
					}if(s1!=null && s2!=null && s2.indexOf("-")==-1){
						s1=numinsertjudg(s1, matcher.start(),matcher.end());
		                if(s1==null){
							break;
						}
						s1=inc_subreplace(s1,s2,matcher.start(),(matcher.end()-1));
					}
					break;
				}
				//Log.e("", "<=======calculinc2========>"+s1);
				if(s1!=null && (s1.indexOf("+)")!=-1 || s1.indexOf("-)")!=-1 || s1.indexOf("÷)")!=-1 || s1.indexOf("×)")!=-1 || 
		               s1.indexOf("^)")!=-1 || s1.indexOf("√)")!=-1 || s1.indexOf(".)")!=-1 || s1.indexOf("(^")!=-1 
		                || s1.indexOf("(.")!=-1 || s1.indexOf("(+")!=-1 || s1.indexOf("()")!=-1 
		               || s1.indexOf("(÷")!=-1 || s1.indexOf("(×")!=-1 || s1.indexOf(".-")!=-1 || s1.indexOf(".+")!=-1
		               || s1.indexOf(".÷")!=-1 || s1.indexOf(".×")!=-1 || s1.indexOf(".^")!=-1 || s1.indexOf(".√")!=-1
		               || s1.indexOf("-.")!=-1 || s1.indexOf("+.")!=-1 || s1.indexOf("÷.")!=-1 || s1.indexOf("×.")!=-1 
		               || s1.indexOf("^.")!=-1 || s1.indexOf("√.")!=-1)){
					s1=null;
				}if(s1!=null && (s1.indexOf("(")!=-1 || s1.indexOf(")")!=-1) && isright){
					s1=calculinc(s1);
				}if(s1!=null && (s1.indexOf("(")!=-1 || s1.indexOf(")")!=-1) && !isright){
					s1=null;
				}if(s1!=null && s1.indexOf("(")==-1 && s1.indexOf(")")==-1){
					String s3Uni="have";
					while (s1!=null && s3Uni.equals("have")) {
						String[] sn=removieunit(s1);
						s1=sn[0];s3Uni=sn[1];
					}
					 s1=calculnum(s1);
				}
			}
		}
		
		
		return s1;
	}
	
	public String espectCal(String s1) {
		// TODO Auto-generated method stub
		Pattern pattern1 = Pattern.compile("\\(-([0-9]+([.]([0-9]+)|([0-9]*))|e|π)\\)[\\^][-]?([0-9]+([.]([0-9]+)|([0-9]*))|e|π)");//计算n次方
		Matcher matcher1 = pattern1.matcher(s1);
		if (matcher1.find()) {
			String ssa1=matcher1.group();
			s1=numinsertjudg(s1, matcher1.start(),matcher1.end());
			String ssa2=calculpow1(ssa1);
			if (ssa2.indexOf("-")!=-1) {
				if(matcher1.start()==0){
					s1=inc_subreplace(s1,ssa2,matcher1.start(),(matcher1.end()-1));
				}if(matcher1.start()>0){
					int len1=s1.length();int a=matcher1.start();int b=matcher1.end()-1;
					String[] strs=suninc(s1,(matcher1.start()-1));
					//s1=suninc(s1,(matcher.start()-1));
					s1=strs[0];
					int len2=s1.length();
					if(len1>len2){
						a=a-1;
						b=b-1;
					}if(len1<len2){
						a=a+1;
						b=b+1;
					}		
					if(strs[1].equals("")) {
						s1=inc_subreplace(s1,ssa2.replace("-", ""),a,b);
					}if(strs[1].equals("1")) {
						s1=inc_subreplace(s1,ssa2,a,b);
					}
				}
			}if (ssa2.indexOf("-")==-1) {
				/*s1=s1.replace(ssa1, ssa2); 修*/
				s1=inc_subreplace(s1,ssa2,matcher1.start(),(matcher1.end()-1));
			}
			//Log.e("", "<=====calculinc=====>"+matcher1.group());
		}
		return s1;
	}

	public String calculpow1(String ssa1) {
		// TODO Auto-generated method stub
		
		String[] ssb = ssa1.split("\\^");
		ssb[0] = ssb[0].replace("(", "");
		ssb[0] = ssb[0].replace(")", "");
		ssb = detaleorπ(ssb);
		ssa1 = (new BigDecimal((Math.pow(Double.parseDouble(ssb[0]),
				Double.parseDouble(ssb[1])) + "")).setScale((demic + 2),
				RoundingMode.HALF_UP)).toPlainString();
		return ssa1;
	}

	public String inc_subreplace(String s1,String s2,int a,int b){
		
		//Log.e("", a+"<=======inc_subreplace========>"+b);
		String s="";
		for(int i=0;i<s1.length();i++){
			if(i<a || i>b){
				s=s+s1.charAt(i);
			}if(i==a){
				s=s+s2;
			}
		}
		return s;
	}
	
	public String[] suninc(String s1, int a){
		
		String[] strs=new String[2];
		strs[0]="";
		strs[1]="";
		
		boolean isbrow=false;int brow=0;
		for(int i=a;i>=0;i--){
			String str1=s1.charAt(i)+"";
			String str2="";
			if (str1.equals("^")) {//^?????
				strs[1]="1";
				break;
			}if(i>=1){
				str2=s1.charAt((i-1))+"";	
			}
			if(str1.equals("-") && !isbrow && i!=0 && !str2.equals("(") && !str2.equals("^")){
				s1=rep_str(s1,i,"+");
				break;
			}if(str1.equals("-") && !isbrow && !str2.equals("^") && (i==0 || str2.equals("("))){
				s1=rep_str(s1,i,"");
				break;
			}if(str1.equals("+") && !isbrow){
				s1=rep_str(s1,i,"-");
				break;
			}if(str1.equals("(") && !isbrow){
				s1=rep_str(s1,i,"(-");
				break;
			}if(str1.equals("(") && isbrow){
				brow=brow-1;
				if(brow==0){
					isbrow=true;
				}if(brow!=0){
					isbrow=false;
				}
			}if(str1.equals(")")){				
				brow=brow+1;
				if(brow==0){
					isbrow=true;
				}if(brow!=0){
					isbrow=false;
				}
			}if(i==0 && !isbrow && !str1.equals("-")){
				s1=rep_str(s1,i,"-"+str1);
				break;
			}if(i==0 && isbrow){
				s1=null;
				break;
			}			
		}
		strs[0]=s1;
		//Log.e("", a+"suninc======>"+s1);
		return strs;
	}
	
	public String rep_str(String str0,int index,String str1){
		
		String s1="";
		for(int i=0;i<str0.length();i++){
			if(i!=index){
				s1=s1+str0.charAt(i);
			}if(i==index){
				s1=s1+str1;
			}
		}
		
		return s1;
	}
	
	public String calculnum(String s1){
		
		/*BigDecimal bd=new BigDecimal(s1);
		bd.pow(-4);*/
		if(s1!=null && s1.equals("")){
			s1=null;
		}if(s1!=null && !s1.equals("")){
			subtime=0;
			String am0=s1.charAt(0)+"";
			if(am0.equals("-")){
				s1=subtimeset(s1);
			}
		}
		while(s1!=null && s1.indexOf("√")!=-1){  //计算根号
			s1=calculsqrt(s1);
		}while(s1!=null && s1.indexOf("^")!=-1){//计算n次方      
			s1=calculpow(s1);
		}while(s1!=null && (s1.indexOf("×")!=-1 || s1.indexOf("÷")!=-1)){//计算乘除    
			s1=calcmulordiv(s1);
			//Log.e("", "calcmulordiv======>"+s1);
		}while(s1!=null && (s1.indexOf("-")!=-1 || s1.indexOf("+")!=-1)){//计算加减   
			s1=calcmuladdorsub(s1);
			//Log.e("", "calcmuladdorsub======>"+s1);
		}
		
		
		  //计算除法 calculdivide
		//计算乘法 calculmultiply
		//计算加法 calculadd
		//计算减法 calculsubtract
		
		if(subtime%2==1 && s1!=null){
			String am=""+s1.charAt(0);
			if(am.equals("-")){
				s1=s1.substring(1,s1.length());
			}if(!am.equals("-")){
				s1="-"+s1;
			}
		}
		/*if(s1!=null){
			Toast.makeText(ct, s1, Toast.LENGTH_SHORT).show();
		}
*/		//Log.e("", "calculnum=====>"+s1);
		
		
		return s1;
	}
	
	
	public String calcmuladdorsub(String s1){
		
		Pattern pattern1 = Pattern.compile("([0-9]+([.]([0-9]+)|([0-9]*))|e|π)(\\-|\\+)([0-9]+([.]([0-9]+)|([0-9]*))|e|π)");//计算乘除
		Matcher matcher1 = pattern1.matcher(s1);
		boolean find=false;
		while(matcher1.find()){
			String string=matcher1.group();
			MathContext mc=new MathContext(0, RoundingMode.HALF_UP);
			s1=numinsertjudg(s1, matcher1.start(),matcher1.end());
			if(s1==null){
				break;
			}
			find=true;
			String[] sn=null;
			if(matcher1.group().indexOf("+")!=-1){
				sn=matcher1.group().split("\\+");
				
				sn=detaleorπ(sn);
							
				BigDecimal bd=new BigDecimal(sn[1]);
				BigDecimal bd1=new BigDecimal(sn[0]);
				bd=bd.add(bd1, mc);
				/*s1=s1.replace(matcher1.group(),bd.toPlainString()); 修*/
				s1=inc_subreplace(s1,bd.toPlainString(),matcher1.start(),(matcher1.end()-1));
			}if(matcher1.group().indexOf("-")!=-1){
				sn=matcher1.group().split("\\-");
				
				sn=detaleorπ(sn);
				
				BigDecimal bd=new BigDecimal(sn[0]);
				BigDecimal bd1=new BigDecimal(sn[1]);
				bd=bd.subtract(bd1, mc);			
				/*s1=s1.replace(matcher1.group(),bd.toPlainString()); 修*/
				s1=inc_subreplace(s1,bd.toPlainString(),matcher1.start(),(matcher1.end()-1));
                if(bd.compareTo(new BigDecimal("0"))==-1){
                	String am0=s1.charAt(0)+"";
            		if(am0.equals("-")){
            			s1=subtimeset(s1);
            		}
				}				
			}
			break;
		}
		if(s1!=null && !find && (s1.indexOf("-")!=-1 || s1.indexOf("+")!=-1)){
			s1=null;
		}
		return s1;
	}
	
	public String calcmulordiv(String s1){
		
		Pattern pattern1 = Pattern.compile("([0-9]+([.]([0-9]+)|([0-9]*))|e|π)(×|÷)([0-9]+([.]([0-9]+)|([0-9]*))|e|π)");//计算乘除
		Matcher matcher1 = pattern1.matcher(s1);
		boolean find=false;
		while(matcher1.find()){
			MathContext mc=new MathContext(0, RoundingMode.HALF_UP);
			s1=numinsertjudg(s1, matcher1.start(),matcher1.end());
			if(s1==null){
				break;
			}
			find=true;
			String[] sn=null;
			if(matcher1.group().indexOf("×")!=-1){
				//Log.e("", "calcmulordiv1111======>"+matcher1.group());
				sn=matcher1.group().split("×");
				
				sn=detaleorπ(sn);
				
				BigDecimal bd=new BigDecimal(sn[1]);
				BigDecimal bd1=new BigDecimal(sn[0]);
				bd=bd.multiply(bd1, mc);
				/*s1=s1.replace(matcher1.group(),bd.toPlainString()); 修*/
				s1=inc_subreplace(s1,bd.toPlainString(),matcher1.start(),(matcher1.end()-1));
			}if(matcher1.group().indexOf("÷")!=-1){
				//Log.e("", "calcmulordiv2222======>"+matcher1.group());
				sn=matcher1.group().split("÷");
				
				sn=detaleorπ(sn);
				
				BigDecimal bd=new BigDecimal(sn[0]);
				BigDecimal bd1=new BigDecimal(sn[1]);
				if(bd1.compareTo(new BigDecimal("0"))==0){
					s1=null;
				}if(bd1.compareTo(new BigDecimal("0"))!=0){
					bd=bd.divide(bd1, (demic+2), RoundingMode.HALF_UP);
					/*s1=s1.replace(matcher1.group(),bd.toPlainString()); 修*/
					s1=inc_subreplace(s1,bd.toPlainString(),matcher1.start(),(matcher1.end()-1));
				}
			}			
			break;
		}
		if(s1!=null && !find && (s1.indexOf("×")!=-1 || s1.indexOf("÷")!=-1)){
			s1=null;
		}
		return s1;
	}
	
	public String calculsqrt(String s1){
				    
			Pattern pattern1 = Pattern.compile("(√[0-9]+([.]([0-9]+)|([0-9]*))|√π|√e)");//先计算根号
			Matcher matcher1 = pattern1.matcher(s1);
			boolean find=false;boolean insertjudge=true;
			ArrayList<String> arrd1=new ArrayList<String>();
			ArrayList<String> arrd2=new ArrayList<String>();
			
			while(matcher1.find()){
				s1=numinsertjudg(s1, matcher1.start(),matcher1.end());
				if(s1==null){
					insertjudge=false;
					break;
				}
				find=true;				
				//double dou1=Double.parseDouble(matcher1.group().substring(1,matcher1.group().length()));
				BigDecimal bd1=null;
				if(matcher1.group().indexOf("e")!=-1){
					CustomValues CustomValues=new CustomValues();
					bd1=CustomValues.e;
					bd1=bd1.setScale((demic+2), RoundingMode.HALF_UP);
				}if(matcher1.group().indexOf("π")!=-1){
					CustomValues CustomValues=new CustomValues();
					bd1=CustomValues.π;
					bd1=bd1.setScale((demic+2), RoundingMode.HALF_UP);
				}if(matcher1.group().indexOf("e")==-1 && matcher1.group().indexOf("π")==-1){
					bd1=new BigDecimal(matcher1.group().substring(1,matcher1.group().length()));
				}
				if(bd1.compareTo(new BigDecimal("0"))==0){
                	s1=null;
                	insertjudge=false;
                	break;
				}
				double dou=bd1.doubleValue();
				dou=Math.sqrt(dou);String doustr=""+dou;
				BigDecimal bd2=new BigDecimal(doustr);
				if(bd2.compareTo(new BigDecimal("0"))==0){
                	s1=null;
                	insertjudge=false;
                	break;
				}
				bd1=bd1.divide(bd2, (demic+2), RoundingMode.HALF_UP);
				arrd1.add(matcher1.group());
				arrd2.add(bd1.toString());
				/*s1=s1.replace(arrd1.get(i), arrd2.get(i)); 修*/
				s1=inc_subreplace(s1,bd1.toString(),matcher1.start(),(matcher1.end()-1));
				break;
		    }		
			/*for (int i = 0; i < arrd1.size(); i++) {
				if(s1!=null && insertjudge==true){
					s1=s1.replace(arrd1.get(i), arrd2.get(i));//
				}
			}*/
			if(s1!=null && !find && s1.indexOf("√")!=-1){
			   s1=null;
			   //Log.e("", "calculsqrt======>"+null);
			}
			
		return s1;
	}
	
	public String calculpow(String s1){
		Pattern pattern1 = Pattern.compile("([0-9]+([.]([0-9]+)|([0-9]*))|e|π)[\\^][-]?([0-9]+([.]([0-9]+)|([0-9]*))|e|π)");//计算n次方
		Matcher matcher1 = pattern1.matcher(s1);
		boolean find=false;boolean insertjudge=true;
		ArrayList<String> arrd1=new ArrayList<String>();
		ArrayList<String> arrd2=new ArrayList<String>();
		
		while(matcher1.find()){

			MathContext mc=new MathContext(0, RoundingMode.HALF_UP);
			s1=numinsertjudg(s1, matcher1.start(),matcher1.end());
			if(s1==null){
				insertjudge=false;
				break;
			}
			find=true;		
			String[] sn=matcher1.group().split("\\^");
			sn=detaleorπ(sn);
			
			BigDecimal bd=new BigDecimal(sn[0]);
			/*if(bd.compareTo(new BigDecimal("0"))==0){
				s1=null;
				insertjudge=false;
				break;
			}*/			
			BigDecimal bd1=new BigDecimal(sn[1]);
			if(bd1.compareTo(new BigDecimal("0"))==-1){
				sn[0]=new BigDecimal("1").divide(bd, demic, RoundingMode.HALF_UP).toPlainString()+"";
				sn[1]=sn[1].replace("-", "");
				//Log.e("", "calculpow=====>"+sn[0]);
			}
			
			BigDecimal bd2=new BigDecimal(sn[0]);
			String[] sna=getintdou(sn[1]);
			if(sna[2].equals("-1")){
				bd2=bd2.pow(Integer.parseInt(sn[1]), mc);
			}if(sna[2].equals("1")){
				double dou1=Math.pow(Double.parseDouble(sn[0]), Double.parseDouble(sna[1]));
				String dou1str=dou1+"";
			   if(new BigDecimal(dou1str).compareTo(new BigDecimal("0"))==0){
				    s1=null;
					insertjudge=false;
					break;
			   }
			 bd2=bd2.pow(Integer.parseInt(sna[0]), mc).divide(new BigDecimal(dou1str), (demic+2), RoundingMode.HALF_UP);
			}if(sna[2].equals("0")){
				double dou1=Math.pow(Double.parseDouble(sn[0]), Double.parseDouble(sna[1]));
				String dou1str=dou1+"";
				bd2=bd2.pow(Integer.parseInt(sna[0]), mc).multiply(new BigDecimal(dou1str) , mc);
			}
			arrd1.add(matcher1.group());
			arrd2.add(bd2.toPlainString());
			//Log.e("", bd2.toString()+"<======calculpow======>"+matcher1.group());
			
			/*s1=s1.replace(arrd1.get(i), arrd2.get(i)); 修*/
			s1=inc_subreplace(s1,bd2.toPlainString(),matcher1.start(),(matcher1.end()-1));
		}
		/*for (int i = 0; i < arrd1.size(); i++) {
			if(s1!=null && insertjudge==true){
				s1=s1.replace(arrd1.get(i), arrd2.get(i));
			}
		}*/
		if(s1!=null && !find && s1.indexOf("^")!=-1){
			   s1=null;
			   //Log.e("", "calculpow======>"+null);
			}
		return s1;
	}

	public String[] detaleorπ(String[] sn){
		
		CustomValues CustomValues=new CustomValues();
		if(sn[0].indexOf("e")!=-1){
			 BigDecimal sbd=CustomValues.e;
			 sbd=sbd.setScale((demic+2), RoundingMode.HALF_UP);
			 sn[0]=sbd.toString();
		}if(sn[1].indexOf("e")!=-1){
			 BigDecimal sbd=CustomValues.e;
			 sbd=sbd.setScale((demic+2), RoundingMode.HALF_UP);
			 sn[1]=sbd.toString();
		}if(sn[0].indexOf("π")!=-1){
			BigDecimal sbd=CustomValues.π;
			sbd=sbd.setScale((demic+2), RoundingMode.HALF_UP);
			sn[0]=sbd.toString();
		}if(sn[1].indexOf("π")!=-1){
			BigDecimal sbd=CustomValues.π;
			sbd=sbd.setScale((demic+2), RoundingMode.HALF_UP);
			sn[1]=sbd.toString();
		}
		
		return sn;
	}
	
	
	public String[] removieunit(String s1){

		String s2="nohave";
		Pattern pattern1 = Pattern.compile("([0-9]+([.]([0-9]+)|([0-9]*))|e|π)[^(0|1|2|3|4|5|6|7|8|9|\\(|\\)|.|\\+|\\-|×|÷|√|\\^|π|e|,)]+");
		Matcher matcher1 = pattern1.matcher(s1);
		while (matcher1.find()) {
			s1=numinsertjudg(s1, matcher1.start(),matcher1.end());
			if (s1!=null) {
				s2="have";
				String groupStr=unityplace(matcher1.group());
				if (groupStr==null) {
					return new String[]{null,s2};
				}if (groupStr!=null) {
					s1=inc_subreplace(s1,groupStr,matcher1.start(),(matcher1.end()-1));
				}
			}
			break;
		}
		return new String[]{s1,s2};
	}
	
	public int getInd(String group) {
		
		int ind=0;
		for (int i = 0; i < group.length(); i++) {
			String indexStr=group.charAt(i)+"";
			if (indexStr.equals("0") || indexStr.equals("1") || indexStr.equals("2") || indexStr.equals("3") || indexStr.equals("4") || 
					indexStr.equals("5") || indexStr.equals("6") || indexStr.equals("7") || indexStr.equals("8") || indexStr.equals("9") || 
					indexStr.equals(".") || indexStr.equals("e") || indexStr.equals("π")) {
				ind=i+1;
			}else {
				break;
			}
		}
		
		return ind;		
	}
	
	private String unityplace(String group) {
		// TODO Auto-generated method stub
		
		/**
		 *0  长度：米
		 *1  质量：千克
		 *2  面积：平方米
		 *3  体积：立方米
		 *4  压力：帕斯卡
		 *5  流量：字节
		 * */
		String numStr="";String unityStr="";
		int ind=getInd(group);
		numStr=group.substring(0,ind);
		unityStr=group.substring(ind,group.length());
		String unitytypy="";String unitysize="";String unitySelecttypy="";String unitysizeSelet="";String unitySeletStr="";
		Cursor cursor=db.rawQuery("select * from unit_table where unitsimple=?", new String[]{unityStr}); 
		while (cursor.moveToNext()) {
			unitytypy=cursor.getString(cursor.getColumnIndexOrThrow("unittype"));
			unitysize=cursor.getString(cursor.getColumnIndexOrThrow("unitsize"));
			globe.hasUnity=true;
		}cursor.close();
		if (unitytypy=="") {
			return null;
		}
		ArrayList<String> aListStr=new ArrayList<String>();
		aListStr.add("长度");aListStr.add("质量");aListStr.add("面积");
		aListStr.add("体积");aListStr.add("压力");aListStr.add("流量");
		unitySeletStr=globe.unitseleidStr.get(aListStr.indexOf(unitytypy));
		if (unitySeletStr.indexOf("(")!=-1) {
			unitySeletStr=unitySeletStr.substring((unitySeletStr.indexOf("(")+1), unitySeletStr.indexOf(")"));
		}if (unitySeletStr.equals("")) {
			return null;
		}if (!unitySeletStr.equals("")) {
			cursor=db.rawQuery("select * from unit_table where unitsimple=?", new String[]{unitySeletStr}); 
			while (cursor.moveToNext()) {
				unitySelecttypy=cursor.getString(cursor.getColumnIndexOrThrow("unittype"));
				unitysizeSelet=cursor.getString(cursor.getColumnIndexOrThrow("unitsize"));
			}cursor.close();
		}if (!unitySelecttypy.equals(unitytypy)) {
			return null;
		}if (unitysize.equals(unitysizeSelet)) {
			group=numStr;
		}if (!unitysize.equals(unitysizeSelet)) {
			try {
				
				 BigDecimal bd1=null;
				 if (numStr.equals("e")) {
					 bd1=CustomValues.e;
				 }if (numStr.equals("π")) {
					 bd1=CustomValues.π;
				 }if (!numStr.equals("e") && !numStr.equals("π")) {
					 bd1=new BigDecimal(numStr);
				 }
				 bd1= bd1.multiply(new BigDecimal(unitysize));
				 BigDecimal bd2= new BigDecimal(unitysizeSelet);
				 bd1=bd1.divide(bd2, (globe.demic+4), RoundingMode.HALF_UP);
				 group=bd1.toPlainString();
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}			
		}
		return group;
		
		
	}

	public String[] getintdou(String s1){
		
		String[] sn=new String[3];
		sn[0]="";sn[1]="";sn[2]="0";//0代表乘以
		MathContext mc=new MathContext(0, RoundingMode.HALF_UP);
		if(s1.indexOf(".")!=-1){
	    	String s1a=s1.substring(0, s1.indexOf("."));
	    	String s1b="0"+s1.substring(s1.indexOf("."), s1.length());
	    	BigDecimal bd1=new BigDecimal(s1a);BigDecimal bd2=new BigDecimal(s1b);
	    	if(bd2.compareTo(new BigDecimal("0.5"))==1){
				bd1=bd1.add(new BigDecimal("1"), mc);
				bd2=new BigDecimal("1").subtract(bd2, mc);
				sn[2]="1";//1代表除以
				sn[0]=bd1.toString();
				sn[1]=bd2.toString();
	    	}else{
	    		sn[2]="0";//0代表乘以
				sn[0]=s1a;
				sn[1]=s1b;
	    	}
	    	//Log.e("", bd1.toString()+"<====getintdou====>"+bd2.toString());
	    }if(s1.indexOf(".")==-1){
	    	sn[0]=s1;
	    	sn[1]="";
	    	sn[2]="-1";//-1代表直接计算
	    }
		
		return sn;		
	}
	
	private String numinsertjudg(String s,int a,int b){
		
		String FrontStr="";
		String LastStr="";
		if(a>=1){
			 FrontStr=""+s.charAt(a-1);
		}if(b<=s.length()-1){
            LastStr=""+s.charAt(b);
		}
		if(!FrontStr.equals("") && !FrontStr.matches("(\\+|\\-|×|÷|\\(|√|\\^|,)")){
			s=null;
		}if(!LastStr.equals("") && !LastStr.matches("(\\+|\\-|×|÷|\\)|\\^|,)")){

			String unity="";
			for (int i = b; i < s.length(); i++) {
				String ch=s.charAt(i)+"";
				if(ch.matches("(\\+|\\-|×|÷|\\)|\\^|,)")) {
					break;
				}if (ch.matches("[(0|1|2|3|4|5|6|7|8|9|\\(||.|√||π|e)]")) {
					return null;
				}if (ch.matches("[^(0|1|2|3|4|5|6|7|8|9|\\(||.|√||π|e)]")) {
					unity=unity+ch;
				}
			}
			Cursor cursor=db.rawQuery("select * from unit_table where unitsimple=?", new String[]{unity});
			if (cursor.getCount()>0) {
				return s;
			}else {
				return null;
			}
		}
		return s;
	}
	
	public String subtimeset(String s1){
		
		subtime=subtime+1;
		String am=s1.substring(1,s1.length());
		String am1="";
		for(int i=0;i<am.length();i++){
			String sa=""+am.charAt(i);
			
			if(i==0 && sa.matches("[\\+\\-×÷\\^]")){
				s1=null;
				break;
			}if(i==0 && !sa.matches("[\\+\\-×÷\\^]")){
				am1=am1+am.charAt(i);
			}if(i!=0 && sa.equals("-")){
				String sb=""+am.charAt(i-1);
				if(sb.equals("^")){
					am1=am1+am.charAt(i);
				}if(!sb.equals("^")){
					am1=am1+"+";
				}
			}if(i!=0 && sa.equals("+")){
				am1=am1+"-";
			}if(i!=0 && !sa.matches("[\\+\\-]")){
				am1=am1+am.charAt(i);
			}
		}
		s1=am1;
		//Log.e("", "subtimeset======>"+s1);
		return s1;
	}
}
