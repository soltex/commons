/**
 * 
 */
package com.vanstone.common.component.task;

import java.util.concurrent.Callable;

/**
 * 系统任务管理器
 * @author shipeng
 */
@Deprecated
public interface TaskManager {
	
	/**当前默认线程池数量*/
	public static final int DEFAULT_SYS_TASK_THREAD_NUM = 500;
	
	/**
	 * 系统执行任务
	 * @param callable
	 * @param asyn
	 * @return
	 */
	<T extends Object> T executeTask(Callable<T> callable, boolean asyn);
	
	/**
	 * 关闭任务引擎
	 */
	void close();
	
}
