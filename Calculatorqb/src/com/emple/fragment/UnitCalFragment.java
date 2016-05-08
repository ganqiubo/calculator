package com.emple.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.gqb.calculator.R;

public class UnitCalFragment extends Fragment{

   private TextView fragment_unitcal_support;
   private String fontStart="<font color='#0080FF'>",fontEnd="</font>";
private TextView fragment_unitcal_waring;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		   
		View rootView = inflater.inflate(R.layout.fragment_unitcal, container, false);
		
		fragment_unitcal_support = (TextView) rootView.findViewById(R.id.fragment_unitcal_support);
		fragment_unitcal_support.setText(Html.fromHtml(fontStart+"\u3000\u3000��λ����"+fontEnd
				+"����ָ�������ܹ��������λ����ֵ,ʹ��ֵת���ɶ�Ӧ�ĵ�λ��С���м���,�����ڲ�ͬ��λ����ʱ��ת�����䵥λ����Ĭ�ϵĵ�λ���м���,"
				+ "�������յ�λΪ��ǰ��ʾ�ĵ�λ���͵�Ĭ�ϵ�λ���ð汾Ŀǰ֧��"+ fontStart+"����"+fontEnd+"��"+fontStart+"����"
				+fontEnd+"��"+fontStart+"���"+fontEnd+"��"+fontStart+"���"+fontEnd+"��"+fontStart
				+"ѹ��"+fontEnd+"��"+fontStart+"��������"+fontEnd+"�ȵĳ�����λ���㡣"));
		
		fragment_unitcal_waring=(TextView) rootView.findViewById(R.id.fragment_unitcal_waring);
		fragment_unitcal_waring.setText(Html.fromHtml("\u3000\u3000�ڴ���λ����ʱӦ��������"+fontStart+"��λ���"+fontEnd
				+"��"+fontStart+"���"+fontEnd+"�ļ��㡣�ù���Ϊ�򻯹���,ʹ�õ���λ���ʱ�õ��ĵ�λ��Ϊ"+fontStart+"��λA*��λB"
				+fontEnd+",������ʱĬ�ϵ�λ���Ϊ����һ��,��"+fontStart+"��λA"+fontEnd+"������"+fontStart+"��λB"+fontEnd+","
				+"����1m*2mʱ�õ��Ľ������2m������2m^2,��˵����ִ����ʱ�û�Ӧ����������������塣"));
		
		//"+fontStart+" "+fontEnd+"
		return rootView;
	}
	
}
