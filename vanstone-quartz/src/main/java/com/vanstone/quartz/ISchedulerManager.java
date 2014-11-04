/**
 * 
 */
package com.vanstone.quartz;

import java.util.Collection;
import java.util.Map;

import org.quartz.Scheduler;

/**
 * SchedulerManager 管理器
 * @author shipeng
 */
public interface ISchedulerManager {

	/**
	 * 获取当前Scheduler状态
	 * @return
	 */
	SchedulerState getSchedulerState();
	
	/**
	 * 获取当前调度器
	 * @return
	 */
	Scheduler getScheduler();
	
	/**
	 * 获取job信息
	 * @param jobkey
	 * @param jobgroup
	 * @return
	 */
	IJobInfo getJobInfo(String jobkey,String jobgroup);
	
	/**
	 * 是否存在Job
	 * @param jobkey
	 * @param jobgroup
	 * @return
	 */
	boolean existsJob(String jobkey,String jobgroup);
	
	/**
	 * 获取当前运行Jobs
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<IJobInfo> getCurrentRunningJobInfos();
	
	/**
	 * 获取当前运行数量
	 * @return
	 */
	int getTotalCurrentRunningJobInfos();
	
	/**
	 * 删除分组下的Jobs
	 * @param group
	 */
	void deleteJobByGroup(String group);
	
	/**
	 * 删除Job
	 * @param jobkey
	 * @param jobgroup
	 */
	void deleteJob(String jobkey,String jobgroup);
	
	/**
	 * 删除全部
	 */
	void deleteAllJobs();
	
	/**
	 * 检索Job
	 * @param key
	 * @param offset
	 * @param limit
	 * @return
	 */
	Collection<IJobInfo> getJobs(String key, int offset, int limit);
	
	/**
	 * 获取jobs数量
	 * @param key
	 * @return
	 */
	int getTotalJobs(String key);
	
	/**
	 * 暂定当前Scheduler
	 */
	void parse();
	
	/**
	 * 关闭，等待任务执行完毕后关闭
	 */
	void shutdown();
	
	/**
	 * 立即关闭Scheduler
	 */
	void shutdownImmediately();
	
	<T extends AbstractQuartzJobAdapter> void createCronJobAndStart(String jobname, String group, String description, boolean storable, Map<String,Object> datamap, String expression,Class<T> jobclass);
}
