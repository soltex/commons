/**
 * 
 */
package com.vanstone.redis.error;

/**
 * 错误处理器
 * @author shipeng
 */
public interface ErrorHandler {
	
	/**
	 * @param e
	 */
	void process(Exception e);
	
}
