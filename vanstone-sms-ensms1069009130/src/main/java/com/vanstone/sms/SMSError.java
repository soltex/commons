/**
 * Copyright 2009-2013 Sagacityidea.com.
 */
package com.vanstone.sms;

/**
 * @author shipeng
 * Mail:shipeng@sagacityidea.com
 */
public class SMSError extends RuntimeException {

	private static final long serialVersionUID = -8156268838731632000L;
	
	public SMSErrorCode errorCode ;
	
	public SMSError(SMSErrorCode errorCode) {
		if(errorCode == null) {
			throw new IllegalArgumentException("ErrorCode Not Null");
		}
		this.errorCode = errorCode;
	}
	
	public static enum SMSErrorCode implements BaseEnum<String> {
		
		MT_MOBILE_COUNT_GT_50("短信下发手机号大于50");
		
		private String desc;
		
		private SMSErrorCode(String desc) {
			this.desc = desc;
		}

		@Override
		public String getCode() {
			return this.toString();
		}

		@Override
		public String getDesc() {
			return this.desc;
		}
	}
}
