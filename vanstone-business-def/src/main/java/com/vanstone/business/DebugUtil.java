/**
 * 
 */
package com.vanstone.business;

import com.google.gson.Gson;
import com.vanstone.business.serialize.GsonCreator;

/**
 * @author shipeng
 *
 */
public class DebugUtil {
	
	/**
	 * 以Json形势打印JSON形式的结构值
	 * @param object
	 */
	public static void print(Object object) {
		if (object == null) {
			System.out.println(object);
			return;
		}
		Gson gson = GsonCreator.createPretty();
		System.out.println(gson.toJson(object));
	}
	
}
