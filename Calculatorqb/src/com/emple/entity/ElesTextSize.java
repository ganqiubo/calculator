package com.emple.entity;

import java.math.BigDecimal;

public class ElesTextSize implements Cloneable{

	private float ele_size1;
	private float ele_size2;
	private float ele_size3;
	private float ele_size4;
	private float ele1_size1;
	private float ele1_size2;
	private float ele1_size3;
	private float ele1_size4;
	
	public ElesTextSize() {
		// TODO Auto-generated constructor stub
	}
	public ElesTextSize(float ele_size1, float ele_size2, float ele_size3, float ele_size4, float ele1_size1, float ele1_size2,
			float ele1_size3, float ele1_size4) {
		this.ele_size1 = ele_size1;
		this.ele_size2 = ele_size2;
		this.ele_size3 = ele_size3;
		this.ele_size4 = ele_size4;
		this.ele1_size1 = ele1_size1;
		this.ele1_size2 = ele1_size2;
		this.ele1_size3 = ele1_size3;
		this.ele1_size4 = ele1_size4;
	}
	public float getEle_size1() {
		return ele_size1;
	}
	public void setEle_size1(float ele_size1) {
		this.ele_size1 = ele_size1;
	}
	public float getEle_size2() {
		return ele_size2;
	}
	public void setEle_size2(float ele_size2) {
		this.ele_size2 = ele_size2;
	}
	public float getEle_size3() {
		return ele_size3;
	}
	public void setEle_size3(float ele_size3) {
		this.ele_size3 = ele_size3;
	}
	public float getEle_size4() {
		return ele_size4;
	}
	public void setEle_size4(float ele_size4) {
		this.ele_size4 = ele_size4;
	}
	public float getEle1_size1() {
		return ele1_size1;
	}
	public void setEle1_size1(float ele1_size1) {
		this.ele1_size1 = ele1_size1;
	}
	public float getEle1_size2() {
		return ele1_size2;
	}
	public void setEle1_size2(float ele1_size2) {
		this.ele1_size2 = ele1_size2;
	}
	public float getEle1_size3() {
		return ele1_size3;
	}
	public void setEle1_size3(float ele1_size3) {
		this.ele1_size3 = ele1_size3;
	}
	public float getEle1_size4() {
		return ele1_size4;
	}
	public void setEle1_size4(float ele1_size4) {
		this.ele1_size4 = ele1_size4;
	}
	
	@Override
	public ElesTextSize clone() {
		// TODO Auto-generated method stub
		ElesTextSize elesTextSize=null;
		try {
			elesTextSize=(ElesTextSize) super.clone();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return elesTextSize;
	}

	public void addTinyOne(){
		this.ele_size1 = new BigDecimal((""+(this.ele_size1+0.1f))).
			setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele_size2 = new BigDecimal((""+(this.ele_size2+0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele_size3 = new BigDecimal((""+(this.ele_size3+0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele_size4 = new BigDecimal((""+(this.ele_size4+0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public void addBigOne(){
		this.ele1_size1 = new BigDecimal((""+(this.ele1_size1+0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele1_size2 = new BigDecimal((""+(this.ele1_size2+0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele1_size3 = new BigDecimal((""+(this.ele1_size3+0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele1_size4 = new BigDecimal((""+(this.ele1_size4+0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public void subTinyOne(){
		this.ele_size1 = new BigDecimal((""+(this.ele_size1-0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele_size2 = new BigDecimal((""+(this.ele_size2-0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele_size3 = new BigDecimal((""+(this.ele_size3-0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele_size4 = new BigDecimal((""+(this.ele_size4-0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	public void subBigOne(){
		this.ele1_size1 = new BigDecimal((""+(this.ele1_size1-0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele1_size2 = new BigDecimal((""+(this.ele1_size2-0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele1_size3 = new BigDecimal((""+(this.ele1_size3-0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
		this.ele1_size4 = new BigDecimal((""+(this.ele1_size4-0.1f))).
				setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
}
