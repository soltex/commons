package com.vanstone.framework.business.services;

import org.springframework.context.ApplicationEvent;
import org.springframework.transaction.support.TransactionTemplate;

import com.vanstone.framework.context.SpringContextHolder;

/**
 * @author shipeng
 */
public abstract class AbstractBusinessService extends TransactionTemplate {
	
	private static final long serialVersionUID = -3254640629590025158L;
	
	/**
	 * 发布事件
	 * @param event
	 */
	public void publishApplicationEvent(ApplicationEvent event) {
		SpringContextHolder.getApplicationContext().publishEvent(event);
	}
}
