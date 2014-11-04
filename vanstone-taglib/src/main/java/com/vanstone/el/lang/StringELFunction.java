/**
 * 
 */
package com.vanstone.el.lang;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author shipeng
 */
public class StringELFunction {
	
	private static Log LOG = LogFactory.getLog(StringELFunction.class);
	
	/**
	 * 截取字符串
	 * @param targetstr 目标字符串
	 * @param lenght 截取长度
	 * @param defaultValue 如果targetstr为null或者""，则输出defaultValue值
	 * @return
	 */
	public static String substring(String targetstr,Integer length,String defaultValue) {
		if (targetstr == null || targetstr.equals("")) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("ecointel substring print default : " + targetstr);
			}
			return defaultValue;
		}
		if (targetstr.length() <= length) {
			if(LOG.isDebugEnabled()) {
				LOG.debug("ecointel substring print orig string : " + targetstr);
			}
			return targetstr;
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("ecointel substring print finish process :  " + targetstr.substring(length));
		}
		return targetstr.substring(0,length);
	}
	
	public static String replaceAll(String text,String regexExp, String replaceStr) {
		if (text == null || text.equals("")) {
			return text;
		}
		return text.replaceAll(regexExp, replaceStr);
	}
}
