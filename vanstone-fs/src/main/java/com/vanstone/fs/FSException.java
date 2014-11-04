/**
 * 
 */
package com.vanstone.fs;

/**
 * 
 * peng.shi@argylesoftware.co.uk
 */
public class FSException extends Exception {

	private static final long serialVersionUID = 3938941338378490506L;
	
	/**
	 * 非法文件类型
	 */
	public static final int ILLEGAL_FILE_TYPE = 0;
	
	/**
	 * 非法文件大小
	 */
	public static final int ILLEGAL_FILE_SIZE = 1;
	
	private int errorCode;
	
	public FSException(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return
	 */
	public int getErrorCode() {
		return errorCode;
	}
	
}
