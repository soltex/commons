package com.vanstone.dal;

import org.apache.commons.lang.StringUtils;

/**
 * @author shipengpipi@126.com
 */
public enum Sort {
	/**
	 * 升序
	 */
	ASC,
	
	/**
	 * 降序
	 */
	DESC;
	
	/**
	 * @return
	 */
	public String getCode() {
		return this.toString();
	}
	
	/**
	 * 获取SortDirection
	 * @param value
	 * @return
	 */
	public static Sort getByValue(String value) {
		if (StringUtils.isEmpty(value)) {
			return null;
		}
		if (value.equalsIgnoreCase(Sort.ASC.toString())) {
			return Sort.ASC;
		}
		if (value.equals(Sort.DESC.toString())) {
			return Sort.DESC;
		}
		return null;
	}
}
