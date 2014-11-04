/**
 * 
 */
package com.vanstone.business.def;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

/**
 * 主要实现Bean和Map的相互转换
 * @author shipeng
 */
public abstract class BeanMapUtils {

	/**
	 * Map转Bean类
	 * @param map
	 * @param targetObject
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void tranBean2Map(Map<String,Object> map,Object targetObject) throws IllegalAccessException, InvocationTargetException {
		if (map == null || targetObject == null) {
			return;
		}
		BeanUtils.populate(targetObject, map);
	}
	
	/**
	 * Bean转Map
	 * @param sourceObject
	 * @return
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static Map<String,Object> object2Map(Object sourceObject) throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (sourceObject == null) {
			return null;
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(sourceObject.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		if (propertyDescriptors == null || propertyDescriptors.length <=0) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		for (PropertyDescriptor pd : propertyDescriptors) {
			String key = pd.getName();
			if (key.equals("class")) {
				continue;
			}
			System.out.println(key);
			Object[] args = null;
			Object value = pd.getReadMethod().invoke(sourceObject, args);
			map.put(key, value);
		}
		return map;
	}
	
}
