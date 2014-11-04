/**
 * 
 */
package com.vanstone.gm;

import com.vanstone.gm.impl.DefaultGMImageHanderImpl;

/**
 * @author shipeng
 */
public class GMHandlerFactory {
	
	private static class GMHandlerFactoryInstance {
		private static final IGMImageHandler instance = new DefaultGMImageHanderImpl();
	}
	
	/**
	 * 获取IGMImageHandler实例
	 * @return
	 */
	public static IGMImageHandler getGMImageHandler() {
		return GMHandlerFactoryInstance.instance;
	}
	
}
