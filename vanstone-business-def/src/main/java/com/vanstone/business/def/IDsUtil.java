/**
 * 
 */
package com.vanstone.business.def;

import java.util.Collection;
import java.util.HashSet;

import org.apache.commons.lang.StringUtils;

/**
 * @author shipeng
 * 
 */
public class IDsUtil {
	/**
	 * @param ids
	 * @return
	 */
	public static Collection<Long> commaString2LongCollection(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return null;
		}
		String[] strs = StringUtils.split(ids, ',');
		Collection<Long> collection = new HashSet<Long>();
		for (String str : strs) {
			collection.add(Long.parseLong(str));
		}
		return collection;
	}

	/**
	 * @param ids
	 * @return
	 */
	public static Collection<Integer> commaString2IntegerCollection(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return null;
		}
		String[] strs = StringUtils.split(ids, ',');
		Collection<Integer> collection = new HashSet<Integer>();
		for (String str : strs) {
			collection.add(Integer.parseInt(str));
		}
		return collection;
	}

	/**
	 * @param ids
	 * @return
	 */
	public static Collection<String> commaString2StringCollection(String ids) {
		if (StringUtils.isEmpty(ids)) {
			return null;
		}
		String[] strs = StringUtils.split(ids, ',');
		Collection<String> collection = new HashSet<String>();
		for (String str : strs) {
			collection.add(str);
		}
		return collection;
	}
}
