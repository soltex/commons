/**
 * 
 */
package com.vanstone.notification;

import com.vanstone.notification.support.ios.Notification4IOS;

/**
 * 通知创建器
 * @author shipeng
 */
public class NotificationBuilder {
	
	/**
	 * 创建IOS通知
	 * @param token 一个App下的设备的Token
	 * @param content 发送内容
	 * @param sound 声音代码
	 * @param badge 徽章
	 * @return
	 */
	public static Notification createIOSNotification(String token, String content, String sound, Integer badge){
		if (token == null || token.equals("") || content == null || content.equals("")) {
			throw new IllegalArgumentException();
		}
		Notification4IOS notification4ios = new Notification4IOS(token, content);
		if (sound != null && !sound.equals("")) {
			notification4ios.setSound(sound);
		}
		if (badge != null) {
			notification4ios.setBadge(badge);
		}
		return notification4ios;
	}
	
}
