/**
 * 
 */
package com.vanstone.gm;

import com.vanstone.gm.impl.DefaultGMImageHanderImpl;

/**
 * @author shipeng
 */
public class GMFactory {
	
	private static class GMImageHandlerInstance {
		private static final IGMImageHandler instance = new DefaultGMImageHanderImpl();
	}
	
	/**
	 * 获取GMImage处理器
	 * @return
	 */
	public static IGMImageHandler getGMImagerHandler() {
		return GMImageHandlerInstance.instance;
	}
	
}
