/**
 * 
 */
package com.vanstone.quartz;

import org.quartz.SchedulerException;

import com.vanstone.quartz.impl.SchedulerManagerImpl;

/**
 * @author shipeng
 */
public class QuartzFactory {
	
	private static class SchedulerManagerInstance {
		private static final SchedulerManagerImpl instance = new SchedulerManagerImpl();
		static {
			try {
				instance.init();
			} catch (SchedulerException e) {
				e.printStackTrace();
				throw new ExceptionInInitializerError(e);
			}
		}
	}
	
	/**
	 * 获取SchedulerManager实例
	 * @return
	 */
	public static ISchedulerManager getSchedulerManager() {
		return SchedulerManagerInstance.instance;
	}
}
