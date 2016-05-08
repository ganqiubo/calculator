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
		fragment_unitcal_support.setText(Html.fromHtml(fontStart+"\u3000\u3000单位计算"+fontEnd
				+"功能指计算器能够计算带单位的数值,使其值转换成对应的单位大小进行计算,当存在不同单位类型时将转换成其单位类型默认的单位进行计算,"
				+ "计算最终单位为当前显示的单位类型的默认单位。该版本目前支持"+ fontStart+"长度"+fontEnd+"、"+fontStart+"质量"
				+fontEnd+"、"+fontStart+"面积"+fontEnd+"、"+fontStart+"体积"+fontEnd+"、"+fontStart
				+"压力"+fontEnd+"、"+fontStart+"数据流量"+fontEnd+"等的常见单位计算。"));
		
		fragment_unitcal_waring=(TextView) rootView.findViewById(R.id.fragment_unitcal_waring);
		fragment_unitcal_waring.setText(Html.fromHtml("\u3000\u3000在带单位计算时应尽量避免"+fontStart+"单位相乘"+fontEnd
				+"或"+fontStart+"相除"+fontEnd+"的计算。该功能为简化功能,使得当单位相乘时得到的单位不为"+fontStart+"单位A*单位B"
				+fontEnd+",而计算时默认单位会归为其中一个,即"+fontStart+"单位A"+fontEnd+"或者是"+fontStart+"单位B"+fontEnd+","
				+"例如1m*2m时得到的结果会是2m而不是2m^2,因此当出现此情况时用户应当清楚计算结果的意义。"));
		
		//"+fontStart+" "+fontEnd+"
		return rootView;
	}
	
}
