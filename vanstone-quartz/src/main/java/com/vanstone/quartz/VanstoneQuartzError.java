/**
 * 
 */
package com.vanstone.quartz;

/**
 * @author shipeng
 *
 */
public class VanstoneQuartzError extends RuntimeException {

    private static final long serialVersionUID = 4203296232670939268L;
    
    private ErrorCode errorCode;
    private String errorMsg;
    
    public VanstoneQuartzError(ErrorCode errorCode) {
    	this(errorCode, null);
    }
    
    public VanstoneQuartzError(ErrorCode errorCode,String errorMsg) {
    	if (errorCode == null) {
    		errorCode = ErrorCode.Other_Error;
    	}
    	this.errorMsg = errorMsg;
    }
    
    /**
     * 获取当前错误
     * @return
     */
    public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * 获取错误信息
	 * @return
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public static enum ErrorCode {
    	
    	/**
    	 * 当前Scheduler不可用
    	 */
    	Scheduler_Not_Available,
    	
    	Scheduler_Exception,
    	
    	Other_Error;
    	
    }
}
