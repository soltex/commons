/**
 * 
 */
package com.vanstone.sms;

import org.apache.commons.lang.StringUtils;

/**
 * 短信工具类
 * 
 * @author shipeng
 */
public abstract class MsgUtil {

	/**
	 * 切分短信
	 * 
	 * @param smcontent
	 * @param maxlength
	 * @return
	 */
	public static String[] splitMsg(String smscontent, int maxlength) {
		if (StringUtils.isEmpty(smscontent)) {
			throw new IllegalArgumentException();
		}
		if (smscontent.length() <= maxlength) {
			return new String[] { smscontent };
		}
		int smscount = smscontent.length();
		int n = smscount / maxlength + 1;
		String[] strs = new String[n];
		for (int i = 0; i < n; i++) {
			if (i != n - 1) {
				String tmp = smscontent.substring(i * maxlength, (i + 1) * maxlength);
				strs[i] = tmp;
			} else {
				String tmp = smscontent.substring(i * maxlength, smscount);
				strs[i] = tmp;
			}
		}
		return strs;
	}
}
