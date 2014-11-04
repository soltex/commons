/**
 * 
 */
package com.vanstone;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

/**
 * @author shipeng
 *
 */
public class HexConvertorTest {
	
	@Test
	public void testhexStrToBytes() throws UnsupportedEncodingException {
//		String str = "U+1F4AF";
//		byte[] bytes = HexConvertor.hexStrToBytes(str);
//		try {
//			System.out.println(new String(bytes, "utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		String str = new String("你好");
		byte[] bytes = str.getBytes("utf-16");
		StringBuffer sb = new StringBuffer();
		System.out.println(bytes.length);
		for (byte b : bytes) {
			int n = b;
			sb.append(Integer.toHexString(n).toUpperCase() + " ");
		}
		System.out.println(sb);
		System.out.println(str);
	}
	
}
