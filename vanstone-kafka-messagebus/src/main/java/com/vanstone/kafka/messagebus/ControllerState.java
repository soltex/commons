/**
 * 
 */
package com.vanstone.kafka.messagebus;

/**
 * @author shipeng
 */
public enum ControllerState {
	
	/**
	 * 未启动
	 */
	Not_Run,
	/**
	 * 已启动
	 */
	Started,
	/**
	 * 终止
	 */
	Terminated,
	/**
	 * 关闭
	 */
	Shutdown;
	
}
