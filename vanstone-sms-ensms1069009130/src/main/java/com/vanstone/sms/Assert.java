/**
 * 
 */
package com.vanstone.sms;

/**
 * @author shipeng
 *
 */
public abstract class Assert {
	
	/**
	 * @param text
	 */
	public static void hasText(String text) {
		if (text == null || text.equals("")) {
			throw new IllegalArgumentException("Text is null");
		}
	}
	
	/**
	 * @param object
	 */
	public static void notNull(Object object) {
		if (object == null) {
			throw new IllegalArgumentException("Object not null");
		}
	}
}
