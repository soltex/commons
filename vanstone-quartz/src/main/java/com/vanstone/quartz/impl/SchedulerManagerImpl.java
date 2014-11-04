/**
 * 
 */
package com.vanstone.quartz.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerListener;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.quartz.AbstractQuartzJobAdapter;
import com.vanstone.quartz.IJobInfo;
import com.vanstone.quartz.ISchedulerManager;
import com.vanstone.quartz.SchedulerState;
import com.vanstone.quartz.VanstoneQuartzError;
import com.vanstone.quartz.VanstoneQuartzError.ErrorCode;
import com.vanstone.quartz.listener.JobListenerImpl;
import com.vanstone.quartz.listener.TriggerListenerImpl;

/**
 * @author shipeng
 */
public class SchedulerManagerImpl implements ISchedulerManager {
	
	private static Logger LOG = LoggerFactory.getLogger(SchedulerManagerImpl.class);
	
	/** Quartz Scheduler Factory*/
	private SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	/** 调度器*/
	private Scheduler scheduler;
	/** Job监听器*/
	private JobListener jobListener;
	/** Trigger监听器*/
	private TriggerListener triggerListener;
	
	/**
	 * 获取调度器
	 * @return
	 */
	@Override
	public Scheduler getScheduler() {
		return this.scheduler;
	}
	
	/**
	 * 管理器初始化
	 */
	public void init() throws SchedulerException {
		if (this.scheduler == null || this.scheduler.isShutdown()) {
			this.scheduler = schedulerFactory.getScheduler();
			this.jobListener = new JobListenerImpl();
			this.scheduler.getListenerManager().addJobListener(this.jobListener);
			this.triggerListener = new TriggerListenerImpl();
			this.scheduler.getListenerManager().addTriggerListener(this.triggerListener);
		}
	}
	
	private void _schedulerValidate() {
		try {
			if (this.scheduler == null || this.scheduler.isShutdown() || this.scheduler.isInStandbyMode()) {
				throw new IllegalArgumentException();
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			LOG.error("The Scheduler is null or shutdown or in standby");
			throw new IllegalArgumentException(e);
		}
	}
	
	public Collection<IJobInfo> getCurrentRunningJobs() {
		this._schedulerValidate();
		try {
			List<JobExecutionContext> jobExecutionContexts = this.getScheduler().getCurrentlyExecutingJobs();
			if (jobExecutionContexts == null || jobExecutionContexts.size() <= 0) {
				return null;
			}
			for (JobExecutionContext context : jobExecutionContexts) {
				context.getJobDetail();
			}
			return null;
		} catch (SchedulerException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void start() {
		try {
			if (this.scheduler != null) {
				if (!this.scheduler.isStarted() || !this.scheduler.isInStandbyMode()) {
					this.scheduler.start();
				}
			}else {
				throw new RuntimeException("Scheduler Container is null.");
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public SchedulerState getSchedulerState() {
		if (this.scheduler == null) {
			return SchedulerState.Not_Started;
		}
		try {
			if (this.scheduler.isShutdown()) {
				return SchedulerState.Shutdowned;
			}else if (this.scheduler.isStarted()) {
				return SchedulerState.Started;
			}else{
				return SchedulerState.StartBy;
			}
        } catch (SchedulerException e) {
        	throw new VanstoneQuartzError(VanstoneQuartzError.ErrorCode.Scheduler_Not_Available);
        }
	}
	
	@Override
    public IJobInfo getJobInfo(String jobkey, String jobgroup) {
		if (StringUtils.isEmpty(jobkey) || StringUtils.isBlank(jobkey)) {
			throw new IllegalArgumentException();
		}
		try {
            JobDetail jobDetail = this.getScheduler().getJobDetail(new JobKey(jobkey, jobgroup));
            if (jobDetail == null) {
            	return null;
            }
            
            return _toJobInfo(jobDetail);
        } catch (SchedulerException e) {
            e.printStackTrace();
            throw new VanstoneQuartzError(ErrorCode.Scheduler_Exception);
        }
    }
	
	@Override
    public boolean existsJob(String jobkey, String jobgroup) {
		IJobInfo jobInfo = this.getJobInfo(jobkey, jobgroup);
		if (jobInfo == null) {
			return false;
		}
		return true;
    }
	
	@Override
    public Collection<IJobInfo> getCurrentRunningJobInfos() {
		return null;
    }
	
	@Override
    public int getTotalCurrentRunningJobInfos() {
	    // TODO Auto-generated method stub
	    return 0;
    }
	
	@Override
    public void deleteJobByGroup(String group) {
		if (StringUtils.isEmpty(group)) {
			throw new IllegalArgumentException();
		}
		try {
	        Set<JobKey> jobKeies = this.scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group));
	        if (jobKeies != null && jobKeies.size() >0 ) {
	        	List<JobKey> jobs = new ArrayList<JobKey>();
	        	jobs.addAll(jobKeies);
	        	this.scheduler.deleteJobs(jobs);
	        }
        } catch (SchedulerException e) {
	        e.printStackTrace();
	        throw new VanstoneQuartzError(ErrorCode.Scheduler_Exception);
        }
	}
	
	@Override
    public void deleteJob(String jobkey, String jobgroup) {
		if (StringUtils.isEmpty(jobkey) || StringUtils.isBlank(jobkey)) {
			throw new IllegalArgumentException();
		}
		try {
	        this.getScheduler().deleteJob(new JobKey(jobkey, jobgroup));
        } catch (SchedulerException e) {
	        e.printStackTrace();
	        throw new VanstoneQuartzError(ErrorCode.Scheduler_Exception);
        }
    }
	
	@Override
    public void deleteAllJobs() {
		Set<JobKey> jobKeies;
        try {
	        jobKeies = this.getScheduler().getJobKeys(GroupMatcher.anyJobGroup());
	        if (jobKeies != null && jobKeies.size() >0) {
				for (JobKey jobKey : jobKeies) {
					this.getScheduler().deleteJob(jobKey);
					LOG.info("DELETE job : " + jobKey.getGroup() + "\t" + jobKey.getName());
				}
			}
        } catch (SchedulerException e) {
	        e.printStackTrace();
        }
    }

	@Override
    public Collection<IJobInfo> getJobs(String key, int offset, int limit) {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public int getTotalJobs(String key) {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public void parse() {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void shutdown() {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void shutdownImmediately() {
	    // TODO Auto-generated method stub
    }
	
	@Override
    public <T extends AbstractQuartzJobAdapter> void createCronJobAndStart(String jobname, String group, String description, boolean storable,
    		Map<String,Object> datamap, String expression,Class<T> jobclazz) {
		if (StringUtils.isEmpty(jobname) || StringUtils.isBlank(jobname)) {
			throw new IllegalArgumentException();
		}
		if (!this.getSchedulerState().equals(SchedulerState.Started)) {
			
		}
		
		JobBuilder jobBuilder = JobBuilder.newJob(jobclazz).withIdentity(jobname, group).storeDurably(storable);
		if (datamap != null && datamap.size() >0) {
			jobBuilder.usingJobData(new JobDataMap(datamap));
		}
		JobDetail jobDetail = jobBuilder.build();
		Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withSchedule(CronScheduleBuilder.cronSchedule(expression)).build();
		try {
	        this.getScheduler().scheduleJob(jobDetail, trigger);
	        this.getScheduler().start();
        } catch (SchedulerException e) {
        	e.printStackTrace();
        }
    }
	
	/**
	 * @param jobDetail
	 * @return
	 */
	private IJobInfo _toJobInfo(JobDetail jobDetail) {
		if (jobDetail == null) {
			throw new IllegalArgumentException();
		}
		JobInfo jobInfo = new JobInfo();
		jobInfo.setDescription(jobDetail.getDescription());
		jobInfo.setJobdetailClass(jobDetail.getJobClass().getClass().getName());
		jobInfo.setJobgroup(jobDetail.getKey().getGroup());
		jobInfo.setJobname(jobDetail.getKey().getName());
		return jobInfo;
	}
}
