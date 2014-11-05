/**
 * 
 */
package com.vanstone.zk;

/**
 * ZK错误
 * @author shipeng
 */
public class ZKError extends RuntimeException {

	/***/
	private static final long serialVersionUID = -5266403806664129130L;
	
	public ZKError() {
		super();
	}
	
	public ZKError(Exception exception) {
		super(exception);
	}
	
	public ZKError(String errorMsg) {
		super(errorMsg);
	}
	
}
