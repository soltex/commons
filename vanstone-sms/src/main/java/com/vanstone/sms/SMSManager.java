
/**
 * 
 */
package com.vanstone.sms;

/**
 * 短息管理器
 * @author shipeng
 */
public interface SMSManager {
	
	/**
	 * 发送短信,支持异步等发送方式
	 * @param sms 短信
	 * @param asyn 是否异步
	 * @return 发送状态
	 */
	SendState send(SMS sms, boolean asyn) throws SMSException;
	
	/**
	 * 关闭SMSManager
	 */
	void close();
}
