/**
 * 
 */
package com.vanstone.common.component.task;


/**
 * @author shipeng
 */
@Deprecated
public class TaskManagerFactory {
	
	private static class TaskManagerInstance {
		private static final TaskManager instance = new DefaultTaskManagerImpl();
	}
	
	/**
	 * 获取任务管理器
	 * @return
	 */
	public static TaskManager getTaskManager() {
		return TaskManagerInstance.instance;
	}
	
}
