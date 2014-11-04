package com.vanstone.common.util.security;

import org.apache.commons.lang.StringUtils;

/**
 * 36进制数
 * 
 * @author liufang
 * 
 */
public class Hex36 {
	public static final char[] HEX36_CHARS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };
	public static final char[] HEX36_CHARS_LOWER = { '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
			'v', 'w', 'x', 'y', 'z' };

	public static long hex36ToLong(String hex) {
		if (StringUtils.isBlank(hex)) {
			return 0;
		}
		char[] hexs = hex.toCharArray();
		long result = 0;
		long power = 1;
		int n = 0;
		for (int i = hexs.length - 1; i >= 0; i--) {
			for (int j = 0, len = HEX36_CHARS.length; j < len; j++) {
				if (HEX36_CHARS[j] == hexs[i]
						|| HEX36_CHARS_LOWER[j] == hexs[i]) {
					n = j;
				}
			}
			result += power * n;
			power *= HEX36_CHARS.length;
			n = 0;
		}
		return result;
	}

	public static String longToHex36(long num) {
		return longToHex36(num, 0, true);
	}

	public static String longToHex36(long num, int minLen) {
		return longToHex36(num, minLen, true);
	}

	public static String longToHex36(long num, boolean uppercase) {
		return longToHex36(num, 0, uppercase);
	}

	public static String longToHex36(long num, int minLen, boolean uppercase) {
		StringBuilder hex = new StringBuilder(minLen);
		int n;
		while (num > 0) {
			n = (int) (num % HEX36_CHARS.length);
			hex.append(uppercase ? HEX36_CHARS[n] : HEX36_CHARS_LOWER[n]);
			num /= HEX36_CHARS.length;
		}
		if (hex.length() < minLen) {
			for (int i = hex.length(); i < minLen; i++) {
				hex.append('0');
			}
		}
		return hex.reverse().toString();
	}

	public static void main(String[] args) {
		System.out.println(Hex36.longToHex36(System.currentTimeMillis()));
		System.out.println(Hex36.longToHex36(5000, 0));
		System.out.println(Hex36.longToHex36(5000, 5));
		System.out.println(Hex36.hex36ToLong("3UW"));
		System.out.println(Hex36.hex36ToLong("003UW"));
		System.out.println(Hex36.hex36ToLong("zzz"));
		System.out.println(Hex36.hex36ToLong("3.w"));
	}
}
