/**
 * 
 */
package com.vanstone.framework.event;

import java.util.concurrent.Executor;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.AbstractApplicationEventMulticaster;

/**
 * @author shipeng
 */
public class AsynApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
	
	private Executor taskExecutor;
	
	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.context.event.ApplicationEventMulticaster#multicastEvent
	 * (org.springframework.context.ApplicationEvent)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void multicastEvent(final ApplicationEvent event) {
		for (final ApplicationListener listener : getApplicationListeners(event)) {
			Executor executor = getTaskExecutor();
			if (executor != null) {
				executor.execute(new Runnable() {
					public void run() {
						listener.onApplicationEvent(event);
					}
				});
			} else {
				listener.onApplicationEvent(event);
			}
		}
	}

	public Executor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(Executor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
}
