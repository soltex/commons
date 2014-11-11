/**
 * 
 */
package com.vanstone.notification;

/**
 * 通知管理器工厂
 * @author shipeng
 */
public class NotificationManagerFactory {
	
	private static class NotificationManagerInstance {
		private static final NotificationManager instance = new DefaultNotificationManagerImpl();
	}
	
	/**
	 * 获取NotificationManager
	 * @return
	 */
	public static NotificationManager getNotificationManager() {
		return NotificationManagerInstance.instance;
	}
	
}
