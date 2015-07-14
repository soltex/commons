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
		Notification notification = new Notification4IOS("33a87ebff04afec4252dd6f3d6b45c82f27a3de3d0e6a2ec8d3fa61668569e50", "能么 ？ 收到了么 ？");
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
