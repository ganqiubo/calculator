package com.emple.utils;

import com.emple.calculatorqb.Globe;
import com.emple.entity.ElesTextSize;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ElesTxtSizeUtil {
	
	SQLiteDatabase db;
	
	public ElesTxtSizeUtil() {
		super();
		// TODO Auto-generated constructor stub
		if (db==null) {
			db= SQLiteDatabase.openOrCreateDatabase(Globe.dbFile.getPath(), null);
		}
	}

	public ElesTextSize getTextSizes(){
		
		ElesTextSize elesTextSize=new ElesTextSize();
		try {
			Cursor c=db.rawQuery("select * from eles_size where type=?", new String[]{"modifed"});
			while(c.moveToNext()){
				elesTextSize.setEle_size1(c.getFloat(c.getColumnIndexOrThrow("ele_size1")));
				elesTextSize.setEle_size2(c.getFloat(c.getColumnIndexOrThrow("ele_size2")));
				elesTextSize.setEle_size3(c.getFloat(c.getColumnIndexOrThrow("ele_size3")));
				elesTextSize.setEle_size4(c.getFloat(c.getColumnIndexOrThrow("ele_size4")));
				
				elesTextSize.setEle1_size1(c.getFloat(c.getColumnIndexOrThrow("ele1_size1")));
				elesTextSize.setEle1_size2(c.getFloat(c.getColumnIndexOrThrow("ele1_size2")));
				elesTextSize.setEle1_size3(c.getFloat(c.getColumnIndexOrThrow("ele1_size3")));
				elesTextSize.setEle1_size4(c.getFloat(c.getColumnIndexOrThrow("ele1_size4")));
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return elesTextSize;
	}
	
	public ElesTextSize getDefaultSizes(){
		
		ElesTextSize elesTextSize=new ElesTextSize();
		try {
			Cursor c=db.rawQuery("select * from eles_size where type=?", new String[]{"default"});
			while(c.moveToNext()){
				elesTextSize.setEle_size1(c.getFloat(c.getColumnIndexOrThrow("ele_size1")));
				elesTextSize.setEle_size2(c.getFloat(c.getColumnIndexOrThrow("ele_size2")));
				elesTextSize.setEle_size3(c.getFloat(c.getColumnIndexOrThrow("ele_size3")));
				elesTextSize.setEle_size4(c.getFloat(c.getColumnIndexOrThrow("ele_size4")));
				
				elesTextSize.setEle1_size1(c.getFloat(c.getColumnIndexOrThrow("ele1_size1")));
				elesTextSize.setEle1_size2(c.getFloat(c.getColumnIndexOrThrow("ele1_size2")));
				elesTextSize.setEle1_size3(c.getFloat(c.getColumnIndexOrThrow("ele1_size3")));
				elesTextSize.setEle1_size4(c.getFloat(c.getColumnIndexOrThrow("ele1_size4")));
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return elesTextSize;
	}
	
	public boolean setTextSizes(ElesTextSize elesTextSize){
		try {
			ContentValues values=new ContentValues();
			values.put("ele_size1", elesTextSize.getEle_size1());
			values.put("ele_size2", elesTextSize.getEle_size2());
			values.put("ele_size3", elesTextSize.getEle_size3());
			values.put("ele_size4", elesTextSize.getEle_size4());
			
			values.put("ele1_size1", elesTextSize.getEle1_size1());
			values.put("ele1_size2", elesTextSize.getEle1_size2());
			values.put("ele1_size3", elesTextSize.getEle1_size3());
			values.put("ele1_size4", elesTextSize.getEle1_size4());
			if (db.update("eles_size", values, "type=?", new String[]{"modifed"})>0) {
				return true;
			}else{
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean setToDefaultSize(){
		return setTextSizes(getDefaultSizes());
	}
	
}
