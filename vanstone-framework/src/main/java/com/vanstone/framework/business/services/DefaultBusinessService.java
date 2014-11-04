/**
 * 
 */
package com.vanstone.framework.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author shipeng
 *
 */
public class DefaultBusinessService extends AbstractBusinessService {
	
	public static final String DEFAULT_BUSINESS_TRANSACTION_MANAGER_NAME = "jdbcTransactionManager";
	
	/** */
	private static final long serialVersionUID = 3390053409525096710L;
	
	@Override
	@Autowired
	@Qualifier(value = DEFAULT_BUSINESS_TRANSACTION_MANAGER_NAME)
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		super.setTransactionManager(transactionManager);
	}
	
}
