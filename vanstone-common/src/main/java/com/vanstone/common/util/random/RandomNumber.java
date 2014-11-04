/**
 * 
 */
package com.vanstone.common.util.random;

import java.util.Random;

/**
 * @author shipengpipi@126.com
 */
public class RandomNumber {
	private static char[] NUMBER_POOL = new char[]{'0','1','2','3','4','5','6','7','8','9'};
	
	private static Random random = new Random(System.currentTimeMillis());
	
	public static String randomNumber(int length) {
		if (length <=0) {
			throw new java.lang.IllegalArgumentException();
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<length;i++) {
			int tmp = random.nextInt(NUMBER_POOL.length);
			sb.append(NUMBER_POOL[tmp]);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
		System.out.println(randomNumber(6));
	}
}
