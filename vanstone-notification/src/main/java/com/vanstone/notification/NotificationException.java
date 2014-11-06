/**
 * 
 */
package com.vanstone.notification;


/**
 * Notification异常
 * @author shipeng
 */
public class NotificationException extends Exception {
	
	/***/
	private static final long serialVersionUID = -435543314379966001L;
	
	private ErrorCode errorCode;
	
	public NotificationException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	/**
	 * 获取当前ErrorCode
	 * @return
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}
	
	/**
	 * 创建NotificationException
	 * @param errorCode
	 * @return
	 */
	public static NotificationException create(ErrorCode errorCode) {
		return new NotificationException(errorCode);
	}
	
	public static enum ErrorCode {
		Content_Char_Max_Size("字符不能超过256个");
		private String desc;

		private ErrorCode(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
	
}
