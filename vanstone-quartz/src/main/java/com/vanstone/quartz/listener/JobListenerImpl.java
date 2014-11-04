/**
 * 
 */
package com.vanstone.quartz.listener;

import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.quartz.QuartzConstants;

/**
 * @author shipeng
 *
 */
public class JobListenerImpl implements JobListener {

	private static Logger LOG = LoggerFactory.getLogger(JobListenerImpl.class);
	
	/* (non-Javadoc)
	 * @see org.quartz.JobListener#getName()
	 */
	@Override
	public String getName() {
		return QuartzConstants.JOB_LISTENER_NAME;
	}
	
	/* (non-Javadoc)
	 * @see org.quartz.JobListener#jobToBeExecuted(org.quartz.JobExecutionContext)
	 */
	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		if (LOG.isDebugEnabled()) {
			JobDetail jobDetail = context.getJobDetail();
			LOG.debug(jobDetail.toString());
		}
	}
	
	/* (non-Javadoc)
	 * @see org.quartz.JobListener#jobExecutionVetoed(org.quartz.JobExecutionContext)
	 */
	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		
	}

	/* (non-Javadoc)
	 * @see org.quartz.JobListener#jobWasExecuted(org.quartz.JobExecutionContext, org.quartz.JobExecutionException)
	 */
	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		
	}

}
