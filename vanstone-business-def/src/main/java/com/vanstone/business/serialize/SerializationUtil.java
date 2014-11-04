package com.vanstone.business.serialize;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.google.gson.JsonSyntaxException;
import com.vanstone.business.MyAssert4Business;

/**
 * 使用json进行序列化传输，方便网络传输以及多语种之间信息传递，如java生成json，在php中进行解析使用
 * @author shipeng
 */
public class SerializationUtil {
	
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	/**
	 * 对象转byte数组
	 * @param object
	 * @return
	 */
	public static byte[] object2bytes(Serializable object) {
		MyAssert4Business.notNull(object);
		try {
			return GsonCreator.create().toJson(object).getBytes(DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("object2bytes charset unsupport encoding");
		}
	}
	
	/**
	 * 由字节数组转对象
	 * @param bytes
	 * @param clazz
	 * @return
	 */
	public static <T extends Object> T bytes2Object(byte[] bytes,Class<T> clazz) {
		if (bytes == null || bytes.length <=0 || clazz == null) {
			return null;
		}
		try {
			return GsonCreator.create().fromJson(new String(bytes,DEFAULT_CHARSET), clazz);
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("object2bytes charset unsupport encoding");
		}
	}
	
}
