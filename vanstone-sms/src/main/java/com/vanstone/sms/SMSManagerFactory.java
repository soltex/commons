/**
 * 
 */
package com.vanstone.sms;

import com.vanstone.sms.support.ensms.EnsmsManager;
import com.vanstone.sms.support.ensms.EnsmsManagerImpl;

/**
 * @author shipeng
 *
 */
public class SMSManagerFactory {

	private static class SMSManagerInstance {
		private static final SMSManager DEFAULT_INSTANCE  = new DefaultSMSManager();
		private static final EnsmsManager ENSMS_MANAGER_INSTANCE = new EnsmsManagerImpl();
	}
	
	private SMSManagerFactory() {}
	
	/**
	 * 获取默认SMSManager
	 * @return
	 */
	public static SMSManager getDefaultSMSManager() {
		return SMSManagerInstance.DEFAULT_INSTANCE;
	}
	
	/**
	 * 获取ENSMSManager实例
	 * @return
	 */
	public static EnsmsManager getEnsmsManager(){
		return SMSManagerInstance.ENSMS_MANAGER_INSTANCE;
	}
	
}
