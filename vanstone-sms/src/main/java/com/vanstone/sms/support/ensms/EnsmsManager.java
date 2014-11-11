/**
 * 
 */
package com.vanstone.sms.support.ensms;

import com.vanstone.sms.SMSException;
import com.vanstone.sms.SMSManager;

/**
 * Ensms管理器
 * @author shipeng
 */
public interface EnsmsManager extends SMSManager {
	
	/**
	 * 获取短信剩余数量
	 * @return
	 */
	int getResidualSMSNum() throws SMSException;
	
}
