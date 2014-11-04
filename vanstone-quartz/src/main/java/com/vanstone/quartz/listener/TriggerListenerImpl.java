/**
 * 
 */
package com.vanstone.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;

import com.vanstone.quartz.QuartzConstants;

/**
 * @author shipeng
 *
 */
public class TriggerListenerImpl implements TriggerListener {
	
	/* (non-Javadoc)
	 * @see org.quartz.TriggerListener#getName()
	 */
	@Override
	public String getName() {
		return QuartzConstants.TRIGGER_LISTENER_NAME;
	}
	
	/* (non-Javadoc)
	 * @see org.quartz.TriggerListener#triggerFired(org.quartz.Trigger, org.quartz.JobExecutionContext)
	 */
	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		
	}

	/* (non-Javadoc)
	 * @see org.quartz.TriggerListener#vetoJobExecution(org.quartz.Trigger, org.quartz.JobExecutionContext)
	 */
	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.quartz.TriggerListener#triggerMisfired(org.quartz.Trigger)
	 */
	@Override
	public void triggerMisfired(Trigger trigger) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.quartz.TriggerListener#triggerComplete(org.quartz.Trigger, org.quartz.JobExecutionContext, org.quartz.Trigger.CompletedExecutionInstruction)
	 */
	@Override
	public void triggerComplete(Trigger trigger, JobExecutionContext context,
			CompletedExecutionInstruction triggerInstructionCode) {
		// TODO Auto-generated method stub

	}

}
