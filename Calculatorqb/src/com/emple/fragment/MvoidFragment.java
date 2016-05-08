package com.emple.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.gqb.calculator.R;

public class MvoidFragment extends Fragment{

	private TextView fragment_mvoid_stru;
	private String fontStart="<font color='#0080FF'>",fontEnd="</font>";
	private TextView fragment_mvoid_about;
	private TextView fragment_mvoid_symbol;
	private TextView fragment_mvoid_diff;
	private TextView fragment_mvoid_sample;
	
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	   
		View rootView = inflater.inflate(R.layout.fragment_mvoid, container, false);
		
		fragment_mvoid_about=(TextView) rootView.findViewById(R.id.fragment_mvoid_about);
		fragment_mvoid_about.setText(Html.fromHtml("\u3000\u3000Ϊ�������û��ԱȽϱȽ�����ļ�������"
				+ "���߶����"+fontStart+"�Ʊ��ʽ"+fontEnd+"���û����Ķ��������ļ��㹫ʽ�����øñ��ʽ��������ʵ�ַ����ͣ��׳˵ȼ��㡣"));
		
		fragment_mvoid_stru=(TextView) rootView.findViewById(R.id.fragment_mvoid_stru);
		fragment_mvoid_stru.setText(Html.fromHtml("\u3000\u3000�ñ��ʽ���ģ���Ϊ�Ĳ���:"+fontStart+"[1��N]��ƽ��"+fontEnd+","+
		fontStart+"��()������ʽ"+fontEnd+","+fontStart+"[a,b,c]����"+fontEnd+","+fontStart+"+�����"+fontEnd+","
				+ "����NΪ������ʽ������ܴ���,��()Ϊ��������,�������û����ɶ���,[a,b,c]��a����������ʼֵ,b������,c������ֵֹ,+Ϊ���ʽ֮��������ϵ��"
				+ "��ʾΪ�ۼ�,��ȻҲ�����޸�Ϊ-,��,��,�ֱ��ʾ�ۼ�,�۳�,�۳���<br/>\u3000\u3000�ñ��ʽ���Է�Ϊ"+
				fontStart+"�Զ���������ģʽ"+fontEnd+"��"+fontStart+"�ֶ���������ģʽ,"+fontEnd+"�Զ���������ģʽָ����[a,b,c]����ġƱ�﹫ʽ,������"
						+fontStart+"n"+fontEnd+"��������,"
				+ "����ʱ���ݻ����ʼֵ��ʼ,ÿ�����Ӳ���,����ֵֹ��������,�ֶ���������ģʽָ������[a,b,c]����ġƱ�﹫ʽ,������"
				+fontStart+"X"+fontEnd+"��"+fontStart+"Y"+fontEnd+"��������,����ʱ�������û��ֶ����롣"
				+ "<br/>\u3000\u3000�Ʊ�﹫ʽ������ʽΪ"+fontStart+"[1��N]��()[,,]+"+fontEnd+","
				+ "�û�����ȥ�����ַǱ������,����֧�ֵķ�������ϰ���"+fontStart+"[1��N]��()+"+fontEnd+"��"+
				fontStart+"��()+"+fontEnd+"��"+fontStart+"��()[,,]+"+fontEnd+",�û������Ʊ��ʽʱ����ͨ��ɾ����������ŵ������ɾ����ɾ������顣"));
		
		fragment_mvoid_symbol=(TextView) rootView.findViewById(R.id.fragment_mvoid_symbol);
		fragment_mvoid_symbol.setText(Html.fromHtml("\u3000\u3000�������"+fontStart+"X"+fontEnd+","
				+fontStart+"Y"+fontEnd+","+fontStart+"AVG"+fontEnd+","+fontStart+"n"+fontEnd
				+ "��ֻ���ڡƱ�﹫ʽ��ʹ��,"+fontStart+"AVG"+fontEnd+"Ϊ����������������б�����ƽ��ֵ,X,Y,AVGֻ�ܴ���"
				+ "���ֶ���������ģʽ��,nֻ�ܴ������Զ���������ģʽ��,�Զ�����ģʽ��ϰ�����"+fontStart+"[1��N]��()[,,]+"+fontEnd
				+ "��"+fontStart+"��()[,,]+"+fontEnd+",�ֶ�����ģʽ��ϰ���"+fontStart+"[1��N]��()"+fontEnd+"��"
				+ ""+fontStart+"��()+"+fontEnd));
		
		fragment_mvoid_diff=(TextView) rootView.findViewById(R.id.fragment_mvoid_diff);
		fragment_mvoid_diff.setText(Html.fromHtml("\u3000\u3000Nָ�ۼƼ���ļ��㹫ʽ����,[1��N]��������������̺�Ľ������N,"
				+ "AVGָ�����������������б���X��Y��ƽ��ֵ,AVG����Ҫ�ֶ�����,��ϵͳ�Զ�����õ���"));
		
		fragment_mvoid_sample=(TextView) rootView.findViewById(R.id.fragment_mvoid_sample);
		fragment_mvoid_sample.setText(Html.fromHtml("\u3000\u3000��͹�ʽ��"+fontStart+"��(X)+"+fontEnd+"<br/>"
				+ "\u3000\u3000ƽ��ֵ��ʽ��"+fontStart+"[1/N]��(X)+"+fontEnd+"<br/>"
				+ "\u3000\u300010�Ľ׳˹�ʽ��"+fontStart+"��(n)[1,1,10]��"+fontEnd+"<br/>"
				+ "\u3000\u3000���ʽ��"+fontStart+"[1/N]��((X-AVG)^2)+"+fontEnd+"<br/>"
				+ "\u3000\u3000���๫ʽ�ɸ����������ж���,�ݲ�֧�ֱ����Զ���ƺ���"));
		//"+fontStart+" "+fontEnd+"
		return rootView;
   }
	
}
