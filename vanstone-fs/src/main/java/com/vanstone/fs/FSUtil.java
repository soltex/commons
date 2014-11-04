/**
 * 
 */
package com.vanstone.fs;

import java.util.Date;

import com.vanstone.common.util.CommonDateUtil;

/**
 * peng.shi@argylesoftware.co.uk
 */
public class FSUtil {
	/**
	 * 构建日期样式的目录结构
	 * @param date
	 * @return
	 */
	public static String buildDateStyleStructure(Date date) {
		if (date == null) {
			throw new IllegalArgumentException();
		}
		return CommonDateUtil.date2String(date, "/yyyy/MM/dd/");
	}
}
