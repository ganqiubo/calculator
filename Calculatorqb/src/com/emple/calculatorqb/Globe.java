package com.emple.calculatorqb;

import java.io.File;
import java.util.ArrayList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Globe{
	
	public static File dbFile;
	public boolean hasUnity;
	public TextView pbCal,pbCal1,main1tv3;
	public static TextView main1tv4;
	public Button btCal;
	public RelativeLayout rln1;
	public int funtype=-1;	//�������ͣ�1-5��
	public int fununknow=0;
	public String funinputx="";
	public String funnote="";
	public String funinputy="";
	public String funcharacter="";
	public int n=-1;
	public ArrayList<String> xy=new ArrayList<String>();
	public ArrayList<Integer> funxy=new ArrayList<Integer>()
			,allfunxy=new ArrayList<Integer>();
	public String funbody1="";
	public String xory1="";
	public boolean isfun1save=false;//����������
	public boolean isfuning=false;//ִ�к�����
	public ArrayList<Button> btarray=new ArrayList<Button>();
	public float TextSize=0;
	public Calculate calculate=new Calculate();	
	public ArrayList<Integer> unitseleid=new ArrayList<Integer>();
	public ArrayList<String> unitseleidStr=new ArrayList<String>();
	public String unitshowtype="us";
	public static int demic=14;//�����С����λ��
	public String degrees="degree";//radian:���� degree:�Ƕ�
	public int input=1;//�첽���㲻�����룬1���������룬-1����������
	public CalDataTask cdTask;
	public EditText main1et1;
	public static EditText funet1;
	public Main1Inputtext main1inputtext;
	public static int statusHeight1,pwidth,pheight;
	public static LinearLayout main1ll1;
	
}
