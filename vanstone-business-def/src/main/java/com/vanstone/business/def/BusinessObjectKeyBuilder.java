package com.vanstone.business.def;

import java.io.Serializable;

import com.vanstone.business.CacheConstants;
import com.vanstone.business.MyAssert4Business;

/**
 * @author shipeng
 */
public class BusinessObjectKeyBuilder {
	
	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String class2key(Class clazz,Serializable id) {
		MyAssert4Business.notNull(clazz);
		MyAssert4Business.notNull(id);
		return clazz.getName() + "_" + id;
	}
	
	/**
	 * @param object
	 * @return
	 */
	public static String object2Key(AbstractBusinessObject object) {
		MyAssert4Business.notNull(object);
		if (object.initialable()) {
			return object.getClass().getName() + "_" + object.getId();
		}else{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String class2MutexKey(Class clazz,Serializable id) {
		MyAssert4Business.notNull(clazz);
		MyAssert4Business.notNull(id);
		return clazz.getName() + "_" + id + "_" + CacheConstants.MUTEX_SUFFIX;
	}
	
	/**
	 * @param object
	 * @return
	 */
	public static String object2MutexKey(AbstractBusinessObject object) {
		MyAssert4Business.notNull(object);
		if (object.initialable()) {
			return object.getClass().getName() + "_" + object.getId() + "_" + CacheConstants.MUTEX_SUFFIX;
		}else{
			throw new IllegalArgumentException();
		}
	}
}
