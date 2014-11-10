/**
 * 
 */
package com.vanstone.sms;

import com.vanstone.common.MyAssert;

/**
 * @author shipeng
 * 
 */
public class SMSException extends Exception {

	/***/
	private static final long serialVersionUID = 8563817535017406676L;

	private ErrorCode errorCode;
	
	public SMSException(ErrorCode errorCode) {
		MyAssert.notNull(errorCode);
		this.errorCode = errorCode;
	}
	
	public SMSException(ErrorCode errorCode, Exception exception) {
		super(exception);
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * 短信错误代码
	 * @author shipeng
	 */
	public static enum ErrorCode {
		ENSMS_1("-1		一次发送的手机号码过多"),
		ENSMS_2("-2		登录账户错误"),
		ENSMS_3("-3	密码错误"),
		ENSMS_4("-4	余额不足    "),
		ENSMS_5("-5	超时[注意检查服务器系统时间]"),
		ENSMS_6("-6	code参数不合法"),
		ENSMS_7("-7	用成POST了，正确应该是GET"),
		ENSMS_8("-8	username参数丢失"),
		ENSMS_9("-9	pwd参数丢失"),
		ENSMS_10("-10	msg参数丢失 或者 msg为空信息 或 msg 编码不对"),
		ENSMS_11("-11	mobiles参数丢失"),
		ENSMS_12("-12	dt参数丢失"),
		ENSMS_13("-13	一次下发短信超过了400个字"),
		ENSMS_14("-14	mobiles参数不对 不是正确电话号"),
		ENSMS_100("~-100	其他错误"),
		
		
		
		
		Net_Error("网络错误"),
		SMS_Error("短信接口错误");
		
		private String desc;

		private ErrorCode(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

	}

}
