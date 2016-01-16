package com.emple.calculatorqb;

public class MySplit {

    public String[] mysplit(String s1,String s2) {
    	
    	String[] sn=new String[2];
    	int a=s1.indexOf(s2);
    	sn[0]=s1.substring(0, a);
    	sn[1]=s1.substring((a+1), s1.length());
    	
		return sn;
	}
	
}
