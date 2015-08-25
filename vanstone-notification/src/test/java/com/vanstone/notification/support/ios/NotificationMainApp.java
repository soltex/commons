/**
 * 
 */
package com.vanstone.notification.support.ios;

import com.vanstone.notification.Notification;
import com.vanstone.notification.NotificationBuilder;
import com.vanstone.notification.NotificationException;
import com.vanstone.notification.SendState;

/**
 * @author shipeng
 *
 */
public class NotificationMainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Notification notification = NotificationBuilder.createIOSNotification("890528a0b6148bf91ca4d6476f20708fab469e9d88788decfce11625d4522eeb", "呵呵呵呵，孟**", null, null);
		SendState sendState;
		try {
			sendState = notification.send();
			System.out.println(sendState.getSuccessNum());
		} catch (NotificationException e) {
			e.printStackTrace();
		}
	}

}
