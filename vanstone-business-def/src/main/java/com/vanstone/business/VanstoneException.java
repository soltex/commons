package com.vanstone.business;

/**
 * 系统异常基础实现-checked Exception.<br/>
 * 
 * 希望调用者对抛出的异常进行处理时，可以直接使用此异常或者使用此异常的子类.<br/>
 * 
 * 系统中发生的异常， 都需要重新包装为 ServiceException&ServiceRuntimeException 或者其子类
 * 
 * 实现这个异常的目的：<br/>
 * 1.统一异常的父类，便于系统中对异常做统一处理.<br/>
 * 2.复写fillInStackTrace 方法， 降低创建异常的开销.<br/>
 * <br/>
 * Error日志输出时机：<br/>
 * 重新包装原始异常时， 进行日志输出<br/>
 * <br/>
 * 例如：<br/>
 * <pre>
 * try{
 * ...
 * }catch(XxxException e){
 *     logger.error(e);
 *     //重新包装， 进行日志输出
 *     throw new ServiceRuntimeException(e);
 * }catch(ServiceException e2){
 *     //接收到ServiceException，不做重新包装， 不进行日志输出
 *     throw e2;
 * 
 * }catch(Exception e1){
 *     //重新包装， 进行日志输出
 * 	   logger.error(e1);
 * 	   throw new ServiceRuntimeException(e1);
 * }
 * </pre>
 * 
 * @author shipeng
 *
 */
public abstract class VanstoneException extends Exception {

	private static final long serialVersionUID = -6879298763723247455L;

	public VanstoneException() {
		super();
	}
	
	public VanstoneException(String message) {
		super(message);
	}

	public VanstoneException(Throwable cause) {
		super(cause);
	}

	public VanstoneException(String message, Throwable cause) {
		super(message, cause);
	}

	public Throwable fillInStackTrace() {
		return this;
	}
	
	/**
	 * 获取组装ErrorMsg,异常需自定义ErrorCode
	 * @return
	 */
	public String getErrorMessage() {
		return null;
	}
}
