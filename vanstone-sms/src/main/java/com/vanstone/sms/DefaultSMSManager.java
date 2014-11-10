/**
 * 
 */
package com.vanstone.sms;

import java.util.concurrent.Callable;

import com.vanstone.common.MyAssert;
import com.vanstone.common.component.task.TaskManager;
import com.vanstone.common.component.task.TaskManagerFactory;

/**
 * @author shipeng
 *
 */
public class DefaultSMSManager implements SMSManager {

	/* (non-Javadoc)
	 * @see com.vanstone.sms.SMSManager#send(com.vanstone.sms.SMS, boolean)
	 */
	@Override
	public SendState send(final SMS sms, boolean asyn) throws SMSException {
		MyAssert.notNull(sms);
		TaskManager taskManager = TaskManagerFactory.getTaskManager();
		return taskManager.executeTask(new Callable<SendState>() {
			@Override
			public SendState call() throws Exception {
				return sms.send();
			}
		}, asyn);
	}
	
	@Override
	public void close() {
		TaskManagerFactory.getTaskManager().close();
	}
	
}
