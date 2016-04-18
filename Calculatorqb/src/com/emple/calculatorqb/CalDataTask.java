package com.emple.calculatorqb;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.R.drawable;
import android.R.integer;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

public class CalDataTask extends AsyncTask<String, Integer, String>{
	
	public Globe globe;
	public Context ct;
	public int width;
	public boolean cancer=false;
	Drawable main1tv3bg;
	
	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
		Log.e("", "onCanclled====>");
		LayoutParams hu = globe.pbCal.getLayoutParams();
		hu.width=width;
		globe.pbCal.setLayoutParams(hu);
		globe.pbCal.setVisibility(View.INVISIBLE);
		globe.pbCal1.setVisibility(View.INVISIBLE);
		globe.main1tv3.setBackgroundDrawable(main1tv3bg);
		globe.main1tv4.setText("长度");
		globe.input=1;
		Toast.makeText(ct, "操作结束", Toast.LENGTH_SHORT).show();
		super.onCancelled();
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		globe.input=-1;
		width=globe.pbCal.getLayoutParams().width;
		globe.main1tv4.setText("");
		Log.e("", "----<onPreExecute");
		main1tv3bg=globe.main1tv3.getBackground();
		globe.main1tv3.setBackgroundDrawable(null);
		globe.pbCal1.setVisibility(View.VISIBLE);
		globe.pbCal.setVisibility(View.VISIBLE);
		LayoutParams hu = globe.pbCal.getLayoutParams();
		hu.width=0;
		globe.pbCal.setLayoutParams(hu);
		super.onPreExecute();
	}
	

	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		Log.e("", "onPostExecute====>"+result);
		
		LayoutParams hu = globe.pbCal.getLayoutParams();
		hu.width=width;
		globe.pbCal.setLayoutParams(hu);
		globe.pbCal.setVisibility(View.INVISIBLE);
		globe.pbCal1.setVisibility(View.INVISIBLE);
		globe.main1tv4.setText("长度");
		globe.main1tv3.setBackgroundDrawable(main1tv3bg);
		globe.input=1;
		globe.btarray.get(27).setText("∑");
		if (result==null) {
			Toast.makeText(ct, "计算出错，终止运算！", Toast.LENGTH_SHORT).show();
		}else {
			//result
			globe.main1inputtext.inputResult(result);			
			Toast.makeText(ct, "计算完成", Toast.LENGTH_SHORT).show();
			globe.isfun1save=false;globe.isfuning=false;
		}
		
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		//Log.e("", "onProgressUpdate====>"+values[0]);
		LayoutParams hu = globe.pbCal.getLayoutParams();
		hu.width=values[0];
		globe.pbCal.setLayoutParams(hu);
		super.onProgressUpdate(values);
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
	
		String str=params[0];String type = params[1];//String copystr = params[2];
		String str1=str.substring((str.lastIndexOf("[")+1), str.lastIndexOf("]"));
    	String[] strs=str1.split(",");
    	if (strs.length==3) {
    		BigDecimal start=null;BigDecimal step=null;BigDecimal stop=null;
			try {
				start=new BigDecimal(strs[0]);
				step=new BigDecimal(strs[1]);
				stop=new BigDecimal(strs[2]);
			} catch (Exception e) {
				// TODO: handle exception
				return null;
			}if (str!=null) {
				if ((stop.subtract(start).compareTo(new BigDecimal("0")) == 1 && step .compareTo(new BigDecimal("0")) == 1) || 
						(stop.subtract(start).compareTo(new BigDecimal("0")) == -1 && step.compareTo(new BigDecimal("0")) == -1) || 
						(stop.subtract(start).compareTo(new BigDecimal("0")) == 0 && step.compareTo(new BigDecimal("0")) != 0) ) {
					String character=str.charAt((str.length()-1))+"";//运算符
					String body=str.substring((str.indexOf("∑")+1), str.lastIndexOf("["));
					int N=0;String strCal="";int run=-1;BigDecimal calbigdem=new BigDecimal("0");BigDecimal calbigdem1=new BigDecimal("0");
					int sum=stop.subtract(start).intValue();String copybody = body;
					ArrayList<Integer> aList=new ArrayList<Integer>();boolean find = true;
					while (find) {
						Pattern pattern = Pattern.compile("[\\+|×|\\-|÷|\\^√\\(]n[\\+|×|\\-|÷|\\^\\)]");
						Matcher matcher = pattern.matcher(copybody);
						while (find=matcher.find()) {
							String s = matcher.group();
							//aList.add((matcher.start() + s.indexOf("n")));
							int integer=(matcher.start() + s.indexOf("n"));
							copybody = globe.calculate.inc_subreplace(
									copybody, start.toPlainString(),
									integer, integer);
							/*copybody = globe.calculate.inc_subreplace(
									copybody, start.toPlainString(),
									(matcher.start() + s.indexOf("n")),
									(matcher.start() + s.indexOf("n")));*/
							break;
						}
					}if (start.compareTo(stop)==-1 || start.compareTo(stop)==0) {
						while (start.compareTo(stop)==-1 || start.compareTo(stop)==0) {
							if (cancer) {
								return null;
							}
							/*copybody = body;
							ArrayList<String> replaces=new ArrayList<String>();
							replaces.add(copybody.substring(0,aList.get(0)));
							
							for (int i=1;i<aList.size()-1;i++) {
								replaces.add(copybody.substring((aList.get((i))+1),aList.get(i+1)));
								
								copybody = globe.calculate.inc_subreplace(
										copybody, start.toPlainString(),
										integer, integer);
							}
							replaces.add(copybody.substring((aList.get(aList.size()-1)+1),));*/
							try {
								calbigdem1 = new BigDecimal(
										globe.calculate.calculate(copybody));
							} catch (Exception e) {
								// TODO: handle exception
								return null;
							}
							if (character.equals("+")) {							
								calbigdem=calbigdem.add(calbigdem1);
							}if (character.equals("-") && N>0) {
								if (N==0) {
									calbigdem=calbigdem1;
								}if (N>0) {
									calbigdem=calbigdem.subtract(calbigdem1);
								}
							}if (character.equals("×")) {
								if (N==0) {
									calbigdem=calbigdem1;
								}if (N>0) {
									calbigdem=calbigdem.multiply(calbigdem1);
								}
							}if (character.equals("÷")) {
								if (N==0) {
									calbigdem=calbigdem1;
								}if (N>0) {
									try {
										calbigdem=calbigdem.divide(calbigdem1, (globe.demic+2), RoundingMode.HALF_UP);
									} catch (Exception e) {
										// TODO: handle exception
										return null;
									}
								}
							}
							N=N+1;start=start.add(step);
							int pb=(int) ((N*(1.0)*Math.abs(step.intValue())/Math.abs(sum))*width);
							publishProgress(pb);
							/*N=N+1;strCal=strCal+body.replace("n", start.toPlainString())+character;
							start=start.add(step);*/
						}
						run=1;
					}if (start.compareTo(stop)==1 && run==-1) {
						while (start.compareTo(stop)==1 || start.compareTo(stop)==0) {
							if (cancer) {
								return null;
							}
							copybody = body;
							for (Integer integer : aList) {
								copybody = globe.calculate.inc_subreplace(
										copybody, start.toPlainString(),
										integer, integer);
							}
							try {
								calbigdem1 = new BigDecimal(
										globe.calculate.calculate(copybody));
							} catch (Exception e) {
								// TODO: handle exception
								return null;
							}
							if (character.equals("+")) {
								calbigdem=calbigdem.add(calbigdem1);
							}if (character.equals("-") && N>0) {
								if (N==0) {
									calbigdem=calbigdem1;
								}if (N>0) {
									calbigdem=calbigdem.subtract(calbigdem1);
								}
							}if (character.equals("×")) {
								if (N==0) {
									calbigdem=calbigdem1;
								}if (N>0) {
									calbigdem=calbigdem.multiply(calbigdem1);
								}
							}if (character.equals("÷")) {
								if (N==0) {
									calbigdem=calbigdem1;
								}if (N>0) {
									try {
										calbigdem=calbigdem.divide(calbigdem1, (globe.demic+2), RoundingMode.HALF_UP);
									} catch (Exception e) {
										// TODO: handle exception
										return null;
									}
								}
							}
							N=N+1;start=start.add(step);
							int pb=(int) ((N*(1.0)*Math.abs(step.intValue())/Math.abs(sum))*width);
							publishProgress(pb);
							/*N=N+1;strCal=strCal+body.replace("n", start.toPlainString())+character;
							start=start.add(step);*/
						}
					}if (type.equals("4")) {
						try {
							calbigdem=calbigdem.divide(new BigDecimal((""+N)), (globe.demic+2), RoundingMode.HALF_UP);
						} catch (Exception e) {
							// TODO: handle exception
							return null;
						}
					}/*if (type.equals("2")) {
						strCal="("+strCal.substring(0,(strCal.length()-1))+")";
					}*/
					Log.e("", "<===doInBackground====>"+calbigdem.toPlainString());
					str=calbigdem.toPlainString();
				}else {
					str=null;
				}
			}
		}else {
			//str=null;
		}
		return str;
	}

}
