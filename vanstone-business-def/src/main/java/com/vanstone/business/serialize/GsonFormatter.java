/**
 * 
 */
package com.vanstone.business.serialize;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.vanstone.business.def.AbstractBusinessObject;

/**
 * @author shipeng
 */
public class GsonFormatter {
	
	/**
	 * 格式化json字符串
	 * @param json
	 * @return
	 */
	public static String formatGsonString(String json) {
		Gson gson = GsonCreator.createPretty();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(json);
		String prettyJsonString = gson.toJson(je);
		return prettyJsonString;
	}
	
	/**
	 * 格式化json字符串
	 * @param businessObject
	 * @return
	 */
	public static String formatGsonString(AbstractBusinessObject businessObject) {
		Gson gson = GsonCreator.createPretty();
		return gson.toJson(businessObject);
	}
}
