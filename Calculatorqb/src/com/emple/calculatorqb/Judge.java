package com.emple.calculatorqb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class Judge {

	SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(Environment.getExternalStorageDirectory()+"/gqb/gqb.db", null);
	
	public boolean isnumberfun(String s){
				
		Pattern pattern = null;		
		pattern = Pattern.compile("-?¡Ì*[0-9]+([.]([0-9]+)|([0-9]*))"); 
		Matcher isNum = pattern.matcher(s);	

		return isNum.matches();		
	}	
	
	public boolean main1(String s) {
	
		Log.e("", "judge>>>>>>"+s);
		String s1=s;
		boolean isok=false;String s2="("+s1+")";
		int a=0;int b=0;
		Pattern pattern = null;Pattern pattern1 = null;
		pattern=Pattern.compile("\\(");pattern1=Pattern.compile("\\)");		
		Matcher matcher = pattern.matcher(s);Matcher matcher1 = pattern1.matcher(s);
		while(matcher.find()){
			a=a+1;
		}while(matcher1.find()){
			b=b+1;
		}Log.e("", a+"<<<count>>>"+b);			
		if(s2.matches(".*[\\+\\-¡Á¡Â\\^][\\+\\-¡Á¡Â\\^].*") || s2.matches(".*[\\+\\-¡Á¡Â\\^][\\)].*") || 
				s2.matches(".*[\\(][\\+¡Á¡Â\\^].*") || s2.matches(".*[¡Ì][\\-].*")){
			Log.e("", "match2222222222222");
		}if(!s2.matches(".*[\\+\\-¡Á¡Â\\^][\\+\\-¡Á¡Â\\^].*") && !s2.matches(".*[\\+\\-¡Á¡Â\\^][\\)].*") && 
				!s2.matches(".*[\\(][\\+¡Á¡Â\\^].*") && !s2.matches(".*[¡Ì][\\-].*") && !s2.matches(".*[\\-][,].*")){
			Log.e("", "match11111111111111");
			if(a==b && a!=0){			
				while(s1!=null && s1.indexOf("(")!=-1 && s1.indexOf("")!=-1){
					s1=getinc(s1);
					Log.e("", "returns>>>>>>>"+s1);
				}
				if(s1!=null){
					isok=true;
				}
			}if(a==b && a==0){
				isok=isunmarray(s1);
			}
		}
		Log.e("", "returnfinally>>>>>>>>>>"+isok);
		return isok;
	}

	public String getinc(String s1){
		int[] inc=new int[2];
		inc[0]=-1;inc[1]=-1;
		for(int i=0;i<s1.length();i++){
			String incstr=""+s1.charAt(i);
			if(incstr.equals("(")){
				inc[0]=i;
			}if(incstr.equals(")")){
				inc[1]=i;
				break;				
			}
		}		
		if(inc[1]==inc[0] && inc[1]==-1){			
			if(s1.length()>0){					
				boolean isok=isunmarray(s1);
				if(isok){
					Log.e("", "splite>>>>>>>>ttttttture");					
				}if(!isok){
					s1=null;
				}
			}if(s1!=null && s1.length()==0){
				s1=null;
			}			
		}if(inc[0]<inc[1]){						
			String s1a=s1.substring((inc[0]+1),inc[1]);
			if(s1a.length()>0){				
				if(s1a.indexOf(",")==-1){
					if(isunmarray(s1a)){
						s1=removieinc(s1,inc[0],inc[1]);
					}else{
						s1=null;
					}
				}if(s1a.indexOf(",")!=-1){
					String[] arrs=s1a.split(",");
					int isallnum=0;
					for(int i=0;i<arrs.length;i++){
						if(!isunmarray(arrs[i])){
							isallnum=1;
							break;
						}
					}
					if(isallnum==0){
						s1=removieinc(s1,inc[0],inc[1]);
					}if(isallnum==1){
						s1=null;
					}
				}	
			}if(s1a.length()==0){
				s1=null;
			}
		}if(inc[0]>inc[1]){
			s1=null;
		}
		return s1;		
	}

	public boolean isunmarray(String s){
		
		String snn1=null;
		if(s.length()>=2){
			snn1=""+s.charAt(0);
		}if(snn1!=null){
			if(snn1.equals("-")){
				s=s.replaceFirst("[\\-]", "");
				Log.e("", "replace----"+s);
			}
		}

		String[] sn1=s.split("\\+|¡Á|\\-|¡Â|\\^");
		boolean isok=true;
		Log.e("", s+"isunmarray"+sn1.length);
		if(sn1.length>0){			
			for(int i=0;i<sn1.length;i++){					
				if(!isnum1(sn1[i])){
					isok=false;							
					break;
				}
			}	
		}if(sn1.length==0){
			isok=false;
		}			
		return isok;		
	}
	
	public String removieinc(String s, int inc0, int inc1){
		
		if(inc0-1>=0){
			int incfre=inc0-1;
			 while(incfre>=0){
				String strfront=""+s.charAt((incfre));
				Log.e("", incfre+"--<removieinc----->"+strfront);
				if(strfront.matches("[¡Á¡Â¡Ì]") || strfront.equals("(")  || strfront.equals("^")  || strfront.equals("+")
						  || strfront.equals("-")){
					break;
				}
				incfre=incfre-1;
			}
			 String sub=s.substring((incfre+1), inc0);
			 if(sub.length()==0){
				 s=replaceb(s,inc0,inc1);
			 }if(sub.length()>0){
				 Cursor c=db.rawQuery("select * from fun_table where funname=?", new String[]{sub});
				 if(c.getCount()>0){
					 s=replaceb(s,(incfre+1),inc1);
				 }if(c.getCount()<=0){
					 s=null;
				 }				 
			 }	 
		}if(inc0-1<=0){
			s=replaceb(s,inc0,inc1);
		}
		return s;		
	}
	
	public String replaceb(String s, int inc0, int inc1){
		
		String sn1="";
		for(int i=0;i<s.length();i++){
			if(i<inc0 || i>inc1){
				sn1=sn1+s.charAt(i);
			}if(i==inc0){
				sn1=sn1+"b";
			}
		}		
		return sn1;		
	}
	
	public boolean isnum1(String s){
		
		boolean isok1=true;
		Pattern pattern = null;		
		pattern = Pattern.compile("-?¡Ì*[0-9]+([.]([0-9]+)|([0-9]*))"); 
		Matcher isNum = pattern.matcher(s);		
		isok1=isNum.matches();
		if(!isok1){
			if(s.equals("b") || s.equals("Y") || s.equals("X") || s.equals("AVG") || s.equals("n")){
				isok1=true;
			}
		}
		Log.e("", s+"<--isnmu111111>>>>>>"+isok1);
		
		return isok1;
	}
	
	public boolean isfun(String s1,String s2,int a) {
          		
		boolean isassace=false;
		Pattern pattern = null;
		//pattern=Pattern.compile("\\[1/N\\][¡Æ][(].*[)][\\[]-?[0-9]+([.]([0-9]+)|([0-9]*))[,]-?[0-9]+([.]([0-9]+)|([0-9]*))[,]-?[0-9]+([.]([0-9]+)|([0-9]*))[\\]][+-¡Á¡Â]");
		
		if(a==1){
			pattern=Pattern.compile("[¡Æ][(].*[)][+-¡Á¡Â]");
			//matchpatter=
		}if(a==2){
			pattern=Pattern.compile("[¡Æ][(].*[)][\\[]-?[0-9]+([.]([0-9]+)|([0-9]*))[,]-?[0-9]+([.]([0-9]+)|([0-9]*))[,]-?[0-9]+([.]([0-9]+)|([0-9]*))[\\]][+-¡Á¡Â]");
		}if(a==3){
			pattern=Pattern.compile("\\[1/N\\][¡Æ][(].*[)][+-¡Á¡Â]");
		}if(a==4){
			pattern=Pattern.compile("\\[1/N\\][¡Æ][(].*[)][\\[]-?[0-9]+([.]([0-9]+)|([0-9]*))[,]-?[0-9]+([.]([0-9]+)|([0-9]*))[,]-?[0-9]+([.]([0-9]+)|([0-9]*))[\\]][+-¡Á¡Â]");
		}if(a==5){
			pattern=Pattern.compile("[(].*[)]");
		}		
		Matcher isNum = pattern.matcher(s1);
		if(isNum.matches()/* && main1(s2)*/){
			isassace=true;			
		}
		
		return isassace;
	}
}
