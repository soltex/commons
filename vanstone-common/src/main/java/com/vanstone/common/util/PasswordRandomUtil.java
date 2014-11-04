/**
 * 
 */
package com.vanstone.common.util;

import java.util.Random;

/**
 * @author shipeng
 *
 */
public class PasswordRandomUtil {
	
	private static final char[] characters_pool = new char[]{
		'a','b','c','d','e','f','g','h','i','j',
		'k','l','m','n','o','p','q','r','s','t',
		'u','v','w','x','y','z',
		'0','1','2','3','4','5','6','7','8','9','0'
	};
	
	private static final Random random = new Random();
	
	public static String random(int length) {
		int poolLength = characters_pool.length;
		if (length > poolLength) {
			length = poolLength;
		}
		StringBuffer password = new StringBuffer();
		for (int i=0;i<length;i++) {
			password.append(characters_pool[random.nextInt(characters_pool.length)]);
		}
		return password.toString();
	}
	
	public static void main(String[] args) {
		for (int i=0;i<30;i++) {
			System.out.println(random(20));
		}
	}
}
