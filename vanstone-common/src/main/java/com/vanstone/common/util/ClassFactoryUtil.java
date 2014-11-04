package com.vanstone.common.util;

import java.lang.reflect.Constructor;

/**
 *
 */
public class ClassFactoryUtil {
	/**
	 * 获取处理类实例
	 * 
	 * @param dealerClass 字段处理类
	 * @param callingClass 调用该方法的类
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object getClassInstance(String dealerClass,
	        Class<?> callingClass) throws Exception {

		if (dealerClass == null || "".equals(dealerClass)) {
			throw new Exception("处理类不能为空");
		}

		Class<?> c = null;
		try {
			c = Thread.currentThread().getContextClassLoader()
			        .loadClass(dealerClass);
		} catch (ClassNotFoundException e) {
			try {
				c = Class.forName(dealerClass);
			} catch (ClassNotFoundException ex) {

				try {
					c = callingClass.getClassLoader().loadClass(dealerClass);
				} catch (ClassNotFoundException e2) {
					throw new Exception("找不到类:" + dealerClass, e2);
				}
			}
		}
		Object instance = null;
		try {
			Constructor<?> ct = c.getDeclaredConstructor();
			ct.setAccessible(true);
			instance = ct.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
			Exception e = new Exception(dealerClass + "是接口或者是抽象类", e1);
			throw e;
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			Exception e = new Exception(dealerClass + "找不到或者类的构造方法为private", e1);
			throw e;
		}

		return instance;
	}

	/**
	 * 获取处理类实例
	 * 
	 * @param dealerClass 字段处理类
	 * @param callingClass 调用该方法的类
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static <T> T getClassInstance(Class<T> dealerClass)
	        throws Exception {
		if (dealerClass == null || "".equals(dealerClass)) {
			throw new Exception("处理类不能为空");
		}
		T instance = null;

		try {
			Constructor<T> ct = dealerClass
			        .getDeclaredConstructor();
			ct.setAccessible(true);
			instance = ct.newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
			Exception e = new Exception(dealerClass + "是接口或者是抽象类", e1);
			throw e;
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
			Exception e = new Exception(dealerClass + "找不到或者类的构造方法为private", e1);
			throw e;
		}

		return instance;
	}
}