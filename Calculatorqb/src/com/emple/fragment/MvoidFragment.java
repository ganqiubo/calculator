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
		fragment_mvoid_about.setText(Html.fromHtml("\u3000\u3000为了满足用户对比较比较特殊的计算需求，"
				+ "作者定义出"+fontStart+"∑表达式"+fontEnd+"让用户灵活的定义出所需的计算公式，利用该表达式可以轻松实现方差，求和，阶乘等计算。"));
		
		fragment_mvoid_stru=(TextView) rootView.findViewById(R.id.fragment_mvoid_stru);
		fragment_mvoid_stru.setText(Html.fromHtml("\u3000\u3000该表达式组成模块分为四部分:"+fontStart+"[1÷N]求平均"+fontEnd+","+
		fontStart+"∑()计算表达式"+fontEnd+","+fontStart+"[a,b,c]区间"+fontEnd+","+fontStart+"+运算符"+fontEnd+","
				+ "其中N为计算表达式计算的总次数,∑()为计算主体,可以由用户自由定义,[a,b,c]中a代表区间起始值,b代表步长,c代表终止值,+为表达式之间的运算关系，"
				+ "表示为累加,当然也可以修改为-,×,÷,分别表示累减,累乘,累除。<br/>\u3000\u3000该表达式可以分为"+
				fontStart+"自动输入数据模式"+fontEnd+"和"+fontStart+"手动输入数据模式,"+fontEnd+"自动输入数据模式指包含[a,b,c]区间的∑表达公式,数据由"
						+fontStart+"n"+fontEnd+"变量代表,"
				+ "计算时数据会从起始值开始,每次增加步长,至终止值结束计算,手动输入数据模式指不包含[a,b,c]区间的∑表达公式,数据由"
				+fontStart+"X"+fontEnd+"或"+fontStart+"Y"+fontEnd+"变量代表,计算时数据需用户手动输入。"
				+ "<br/>\u3000\u3000∑表达公式完整公式为"+fontStart+"[1÷N]∑()[,,]+"+fontEnd+","
				+ "用户可以去掉部分非必需组块,其他支持的非完整组合包括"+fontStart+"[1÷N]∑()+"+fontEnd+"、"+
				fontStart+"∑()+"+fontEnd+"、"+fontStart+"∑()[,,]+"+fontEnd+",用户构建∑表达式时可以通过删除键带叉符号的软键盘删除可删除的组块。"));
		
		fragment_mvoid_symbol=(TextView) rootView.findViewById(R.id.fragment_mvoid_symbol);
		fragment_mvoid_symbol.setText(Html.fromHtml("\u3000\u3000特殊符号"+fontStart+"X"+fontEnd+","
				+fontStart+"Y"+fontEnd+","+fontStart+"AVG"+fontEnd+","+fontStart+"n"+fontEnd
				+ "均只能在∑表达公式中使用,"+fontStart+"AVG"+fontEnd+"为整个计算过程中所有变量的平均值,X,Y,AVG只能存在"
				+ "于手动输入数据模式中,n只能存在于自动输入数据模式中,自动输入模式组合包括："+fontStart+"[1÷N]∑()[,,]+"+fontEnd
				+ "、"+fontStart+"∑()[,,]+"+fontEnd+",手动输入模式组合包括"+fontStart+"[1÷N]∑()"+fontEnd+"、"
				+ ""+fontStart+"∑()+"+fontEnd));
		
		fragment_mvoid_diff=(TextView) rootView.findViewById(R.id.fragment_mvoid_diff);
		fragment_mvoid_diff.setText(Html.fromHtml("\u3000\u3000N指累计计算的计算公式个数,[1÷N]代表整个运算过程后的结果除以N,"
				+ "AVG指的是整个过程中所有变量X和Y的平均值,AVG不需要手动输入,由系统自动计算得到。"));
		
		fragment_mvoid_sample=(TextView) rootView.findViewById(R.id.fragment_mvoid_sample);
		fragment_mvoid_sample.setText(Html.fromHtml("\u3000\u3000求和公式："+fontStart+"∑(X)+"+fontEnd+"<br/>"
				+ "\u3000\u3000平均值公式："+fontStart+"[1/N]∑(X)+"+fontEnd+"<br/>"
				+ "\u3000\u300010的阶乘公式："+fontStart+"∑(n)[1,1,10]×"+fontEnd+"<br/>"
				+ "\u3000\u3000方差公式："+fontStart+"[1/N]∑((X-AVG)^2)+"+fontEnd+"<br/>"
				+ "\u3000\u3000更多公式可根据需求自行定义,暂不支持保存自定义∑函数"));
		//"+fontStart+" "+fontEnd+"
		return rootView;
   }
	
}
