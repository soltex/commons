/**
 * 
 */
package com.vanstone.notification;

/**
 * 通知管理器
 * 对群发，异步进行管理
 * @author shipeng
 */
public interface NotificationManager {
	
	/**
	 * 发送通知
	 * @param notification 通知
	 * @param asyn 是否异步发送
	 * @return
	 */
	SendState send(Notification notification, boolean asyn);
	
}
