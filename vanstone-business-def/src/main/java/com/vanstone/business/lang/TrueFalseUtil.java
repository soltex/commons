/**
 * 
 */
package com.vanstone.business.lang;

/**
 * TRUE >0 ,DEFAULT = 1
 * FALSE <=0 DEFAULT  = 0
 * @author shipeng
 */
public class TrueFalseUtil {
	
	/**
	 * 转换为Int
	 * @param state
	 * @return
	 */
	public static int parseInt(boolean state) {
		if (state) {
			return 1;
		}
		return 0;
	}
	
	/**
	 * 转换为bool
	 * @param value
	 * @return
	 */
	public static boolean parseBool(int value) {
		if (value >0) {
			return true;
		}
		return false;
	}
	
	public static int returnTrue() {
		return 1;
	}
	
	public static int returnFalse() {
		return 0;
	}
	
}
