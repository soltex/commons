/**
 * 
 */
package com.vanstone.sms;

import java.util.Date;

/**
 * 短信接口
 * @author shipeng
 */
public interface SMS {

	/**
	 * 获取短信内容
	 * @return
	 */
	String getContent();
	
	/**
	 * 获取完成时间
	 * @return
	 */
	Date getCompleteTime();
	
	/**
	 * 发送短信
	 * @return
	 * @throws SMSException
	 */
	SendState send() throws SMSException;
}
