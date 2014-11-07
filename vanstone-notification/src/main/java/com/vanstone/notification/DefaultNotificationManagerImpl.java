/**
 * 
 */
package com.vanstone.notification;

import java.util.concurrent.Callable;

import com.vanstone.common.component.task.TaskManager;
import com.vanstone.common.component.task.TaskManagerFactory;

/**
 * 默认NotificationManager实现
 * @author shipeng
 */
public class DefaultNotificationManagerImpl implements NotificationManager {
	
	/* (non-Javadoc)
	 * @see com.vanstone.notification.NotificationManager#send(com.vanstone.notification.Notification, boolean)
	 */
	@Override
	public SendState send(final Notification notification, boolean asyn) {
		if (notification == null) {
			throw new IllegalArgumentException();
		}
		TaskManager taskManager = TaskManagerFactory.getTaskManager();
		return taskManager.executeTask(new Callable<SendState>() {
			@Override
			public SendState call() throws Exception {
				return notification.send();
			}
		}, asyn);
	}
}
