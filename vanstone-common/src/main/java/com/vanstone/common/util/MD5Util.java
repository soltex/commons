package com.vanstone.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import com.vanstone.common.MyAssert;

public class MD5Util {
	
	public  static String MD5(String s) {
		if (StringUtils.isEmpty(s) || StringUtils.isBlank(s))
		{
			return null;
		}
		
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getHashCode(Object object) throws IOException{
		if(object == null) return "";
		
		String ss = null;
		ObjectOutputStream s = null;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
			try {
				s = new ObjectOutputStream(bo);
				s.writeObject(object);
				s.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(s != null) {
					s.close();
					s = null;
				}
			}
		ss = MD5(bo.toString());
		return ss;
	}
	
	/**
	 * 获取文件MD5值
	 * @param file
	 * @return
	 */
	public static String fileMD5Uppercase(File file) {
		MyAssert.notNull(file);
		if (!file.exists()) {
			throw new IllegalArgumentException();
		}
		try {
			InputStream is = new FileInputStream(file);
			String md5 = DigestUtils.md5Hex(is).toUpperCase();
			IOUtils.closeQuietly(is);
			return md5;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public static String inputStreamMD5Uppercase(InputStream is) {
		MyAssert.notNull(is);
		try {
			String md5 = DigestUtils.md5Hex(is).toUpperCase();
			IOUtils.closeQuietly(is);
			return md5;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public static void main(String[] args) {
		String str = "serviceLocator = getDefaultClassLoader().loadClass(SERVICE_LOCATOR_CLASS)serviceLocator = getDefaultClassLoader().loadClass(SERVICE_LOCATOR_CLASS)serviceLocator = getDefaultClassLoader().loadClass(SERVICE_LOCATOR_CLASS)serviceLocator = getDefaultClassLoader().loadClass(SERVICE_LOCATOR_CLASS)serviceLocator = getDefaultClassLoader().loadClass(SERVICE_LOCATOR_CLASS)serviceLocator = getDefaultClassLoader().loadClass(SERVICE_LOCATOR_CLASS)serviceLocator = getDefaultClassLoader().loadClass(SERVICE_LOCATOR_CLASS)";
		System.out.println(MD5(str));
	}
}
