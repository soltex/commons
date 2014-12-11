/**
 * 
 */
package com.vanstone.business.serialize;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author shipeng
 *
 */
public abstract class GsonCreator {
	
	/**
	 * 创建Gson
	 * @return
	 */
	public static Gson create() {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateSerializer()).disableHtmlEscaping().create();
		return gson;
	}
	
	/**
	 * 创建格式化好的Gson
	 * @return
	 */
	public static Gson createPretty() {
		Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Date.class, new DateSerializer()).disableHtmlEscaping().create();
		return gson;
	}
	
	/**
	 * 创建日期格式化形式的Json，格式为 yyyy-MM-dd hh:mm:ss
	 * @return
	 */
	public static Gson createDateStringGson() {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateStringSerializer()).disableHtmlEscaping().create();
		return gson;
	}
	
	/**
	 * 转换为格式化Json字符串
	 * @param object
	 * @return
	 */
	public static String getPrettyString(Object object) {
		Gson gson = createPretty();
		return gson.toJson(object);
	}
}
