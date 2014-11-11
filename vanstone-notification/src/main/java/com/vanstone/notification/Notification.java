/**
 * 
 */
package com.vanstone.notification;

import java.util.Date;

/**
 * @author shipeng
 */
public interface Notification {
	
	/**
	 * 获取通知内容
	 * @return
	 */
	String getContent();
	
	/**
	 * 获取完成时间
	 * @return
	 */
	Date getCompleteTime();
	
	/**
	 * 发送通知
	 * @return
	 */
	SendState send() throws NotificationException;
	
	/**
	 * 是否为群发消息
	 * @return
	 */
	boolean isMassNotification();
	
}
