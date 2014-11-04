package com.vanstone.framework.business;

import org.springframework.util.Assert;

import com.vanstone.framework.context.SpringContextHolder;

/**
 * 业务service工厂类
 * @author peng.shi
 */
public final class ServiceManagerFactory {
	
	private static class ServiceManagerFactoryInstance {
		private static final ServiceManagerFactory instance = new ServiceManagerFactory();
	}
	
	private ServiceManagerFactory() {
	}
	
	public static ServiceManagerFactory getInstance() {
		return ServiceManagerFactoryInstance.instance;
	}
	
	/**
	 * @param serviceName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Object> T getService(String serviceName) {
		Assert.hasText(serviceName);
		return (T) SpringContextHolder.getBean(serviceName);
	}
}
