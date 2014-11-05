/**
 * 
 */
package com.vanstone.notification;

/**
 * @author shipeng
 */
public enum Environment {
	
	/**生产环境*/
	Live, 
	
	/**测试环境*/
	Test;
	
	/**
	 * 获取当前环境的Boolean表达方式
	 * @return
	 */
	public boolean getBooleanValueOfEnvironment() {
		if (this.equals(Live)) {
			return true;
		}
		return false;
	}
	
	public static Environment getByStr(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		Environment[] values = Environment.values();
		for (Environment val : values) {
			if (val.toString().equalsIgnoreCase(str)) {
				return val;
			}
		}
		return null;
	}
	
}
