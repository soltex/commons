/**
 * 
 */
package com.vanstone.sms.support.ensms;

import java.util.Date;

/**
 * 时间戳工具类
 * @author shipeng
 */
public class TimestampUtil {

	/**
	 * 获取Unix时间戳
	 * @return
	 */
	public static long getUnixTimestamp() {
		return Math.round(new Date().getTime() / 1000);
	}
	
}
