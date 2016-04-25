package com.emple.utils;

import java.util.HashMap;
import java.util.Map;

import com.emple.activity.OtherActivity;
import cn.gqb.calculator.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

public class SoundPlayer {

	private Context ct;
	private SoundPool soundPool;
	private int maxSound=5;
	private static Map<String, Integer> sounds;
	
	@SuppressWarnings("deprecation")
	public SoundPlayer(Context ct) {
		this.ct = ct;
		this.soundPool = new SoundPool(maxSound, AudioManager.STREAM_MUSIC,0);
		this.sounds=new HashMap<String, Integer>();
		try {
			sounds.put("zero", soundPool.load(ct,R.raw.zero, 1));
			sounds.put("one", soundPool.load(ct,R.raw.one, 1));
			sounds.put("two", soundPool.load(ct,R.raw.two, 1));
			sounds.put("three", soundPool.load(ct,R.raw.three, 1));
			sounds.put("four", soundPool.load(ct,R.raw.four, 1));
			sounds.put("five", soundPool.load(ct,R.raw.five, 1));
			sounds.put("six", soundPool.load(ct,R.raw.six, 1));
			sounds.put("seven", soundPool.load(ct,R.raw.seven, 1));
			sounds.put("eight", soundPool.load(ct,R.raw.eight, 1));
			sounds.put("nine", soundPool.load(ct,R.raw.nine, 1));
			sounds.put("plus", soundPool.load(ct,R.raw.plus, 1));
			sounds.put("subtract", soundPool.load(ct,R.raw.subtract, 1));
			sounds.put("divide", soundPool.load(ct,R.raw.divide, 1));
			sounds.put("multiply", soundPool.load(ct,R.raw.multiply, 1));
			sounds.put("point", soundPool.load(ct,R.raw.point, 1));
			sounds.put("sqr", soundPool.load(ct,R.raw.sqr, 1));
			sounds.put("bracket", soundPool.load(ct,R.raw.bracket, 1));
			sounds.put("power", soundPool.load(ct,R.raw.power, 1));
			sounds.put("equal", soundPool.load(ct,R.raw.equal, 1));
			sounds.put("n", soundPool.load(ct,R.raw.n, 1));
			sounds.put("sin", soundPool.load(ct,R.raw.sin, 1));
			sounds.put("cos", soundPool.load(ct,R.raw.cos, 1));
			sounds.put("tan", soundPool.load(ct,R.raw.tan, 1));
			sounds.put("asin", soundPool.load(ct,R.raw.asin, 1));
			sounds.put("acos", soundPool.load(ct,R.raw.acos, 1));
			sounds.put("atan", soundPool.load(ct,R.raw.atan, 1));
			sounds.put("log", soundPool.load(ct,R.raw.log, 1));
			sounds.put("ln", soundPool.load(ct,R.raw.ln, 1));
			sounds.put("¦Ð", soundPool.load(ct,R.raw.pi, 1));
			sounds.put("e", soundPool.load(ct,R.raw.e, 1));
			sounds.put("X", soundPool.load(ct,R.raw.x, 1));
			sounds.put("Y", soundPool.load(ct,R.raw.y, 1));
			sounds.put("AVG", soundPool.load(ct,R.raw.avg, 1));
			sounds.put("·½²î", soundPool.load(ct,R.raw.variance, 1));
			sounds.put("D", soundPool.load(ct,R.raw.delete, 1));
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("", "1----<"+e);
		}
	}

	public void playSound(int Type,final String sourceId){
		if (OtherActivity.PLAYSOUND) {
			new Thread(new Runnable() {
				public void run() {
					try {
						soundPool.play(sounds.get(sourceId), 
								1, 1, 1, 0, 1f);
					} catch (Exception e) {
						// TODO: handle exception
						Log.e("", "2----<"+e);
					}
				}
			}).start();
		}
	} 
	
}
