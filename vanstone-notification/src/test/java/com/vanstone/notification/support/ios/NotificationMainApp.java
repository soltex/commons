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
		Notification notification = NotificationBuilder.createIOSNotification("65b1166d1702e9b8858b0068606690b82eee34e2be319977413c7721acaa74f8", "呵呵呵呵，孟**", null, null);
		SendState sendState;
		try {
			sendState = notification.send();
			System.out.println(sendState.getSuccessNum());
		} catch (NotificationException e) {
			e.printStackTrace();
		}
	}

}
