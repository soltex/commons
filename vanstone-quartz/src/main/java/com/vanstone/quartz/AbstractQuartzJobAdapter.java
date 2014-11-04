/**
 * 
 */
package com.vanstone.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author shipeng
 */
public abstract class AbstractQuartzJobAdapter implements Job {

	/**
	 * 任务执行前
	 * @param jobExecutionContext
	 * @throws JobExecutionException
	 */
	public void preExecute(JobExecutionContext jobExecutionContext) throws JobExecutionException{
		
	}
	
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		preExecute(jobExecutionContext);
		executeInQuartz(jobExecutionContext);
		finishExecute(jobExecutionContext);
	}
	
	/**
	 * 执行中
	 * @param jobExecutionContext
	 * @throws JobExecutionException
	 */
	public abstract void executeInQuartz(JobExecutionContext jobExecutionContext) throws JobExecutionException;
	
	/**
	 * 执行后
	 * @param jobExecutionContext
	 * @throws JobExecutionException
	 */
	public void finishExecute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		
	}
	
}
