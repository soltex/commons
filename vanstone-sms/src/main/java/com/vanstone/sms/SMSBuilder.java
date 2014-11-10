/**
 * 
 */
package com.vanstone.sms;

import java.util.Collection;

import com.vanstone.common.MyAssert;
import com.vanstone.sms.support.ensms.Ensms;
import com.vanstone.sms.support.todaynic.TodaynicSMS;

/**
 * @author shipeng
 */
public class SMSBuilder {
	
	/**
	 * 创建ENSMS对象,单体对象
	 * @param mobile
	 * @param content
	 * @param port
	 * @return
	 */
	public static SMS createENSMSEntity(String mobile, String content, Integer port) {
		MyAssert.hasText(mobile);
		MyAssert.hasText(content);
		SMS sms = new Ensms(mobile, content, port);
		return sms;
	}
	
	/**
	 * 创建ENSMS对象,群发对象
	 * @param mobiles
	 * @param content
	 * @param port
	 * @return
	 */
	public static SMS createENSMSEntity(Collection<String> mobiles, String content, Integer port) {
		MyAssert.notEmpty(mobiles);
		MyAssert.hasText(content);
		SMS sms = new Ensms(mobiles, content, port);
		return sms;
	}
	
	/**
	 * 创建Todaynic供应商短信格式
	 * @param mobile
	 * @param content
	 * @return
	 */
	public static SMS createTodaynicSMSEntity(String mobile, String content) {
		MyAssert.notNull(mobile);
		MyAssert.hasText(content);
		SMS sms = new TodaynicSMS(mobile, content);
		return sms;
	}
}
