/**
 * 
 */
package com.vanstone.notification.support.ios;

import java.util.concurrent.TimeUnit;

import com.vanstone.notification.Notification;
import com.vanstone.notification.NotificationException;
import com.vanstone.notification.conf.NotificationConf;

/**
 * @author shipeng
 */
public class Notification4IOSTest {
	
	public static void main(String[] args) {
		System.out.println(NotificationConf.getInstance().getEnvironment().getBooleanValueOfEnvironment());
		Notification notification = new Notification4IOS("020f0b69693873be21dd81b823b65cb5a0c17a16d30f6095c90c81c94d58ce8d", "能么 ？ 收到了么 ？");
		//Notification notification = new Notification4IOS("12345", "呵呵呵呵，炎龙，看下。 你说呢，测试下看看");
		try {
			System.out.println(notification.send());
		} catch (NotificationException e) {
			e.printStackTrace();
		}
		System.out.println(notification.getCompleteTime());
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
