package com.htht.weather.springCloudWeather.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtils {
	private GsonUtils() {}  
	
	private static final GsonUtils single = new GsonUtils();  
	
	public Gson gson; 
	
	
	public static GsonUtils getInstance() {  
		return single;  
    }
	public String toJson(Object o){
		gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		String json = gson.toJson(o);
		return json;
	}
	
	public Object fromJson(String json,Class<?> clazz){
		gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		Object o = null;
		o = gson.fromJson(json, clazz);
		return o;
	}
	
	
}
