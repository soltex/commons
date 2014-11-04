/**
 * 
 */
package com.vanstone.sms;

import com.vanstone.sms.impl.SMSOf1069009130ManagerImpl;

/**
 * @author shipeng
 *
 */
public class SMSManagerFactory {
	
	private static class SMSOf1069009130Instance {
		private static final ISMSOf1069009130Manager instance = new SMSOf1069009130ManagerImpl();
	}
	
	private SMSManagerFactory() {}
	
	/**
	 * 获取ISMSOf1069009130Manager
	 * @return
	 */
	public static ISMSOf1069009130Manager getSMSOf1069009130Manager() {
		return SMSOf1069009130Instance.instance;
	}
	
}
