/**
 * 
 */
package com.vanstone.notification.support.ios;

import com.vanstone.notification.Notification;
import com.vanstone.notification.conf.NotificationConf;

/**
 * @author shipeng
 */
public class Notification4IOSTest {
	
	public static void main(String[] args) {
		System.out.println(NotificationConf.getInstance().getEnvironment().getBooleanValueOfEnvironment());
		Notification notification = new Notification4IOS("f199d124b570e7ce4f9d774a43991b9e2b1d093b81957528e774d5dc126e689f", "呵呵呵呵，你说呢，测试下看看");
		System.out.println(notification.send());
		System.out.println(notification.getCompleteTime());
	}
	
}
