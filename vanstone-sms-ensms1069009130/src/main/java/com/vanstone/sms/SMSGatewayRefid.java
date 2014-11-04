/**
 * 
 */
package com.vanstone.sms;

/**
 * @author shipengpipi@126.com
 */
public enum SMSGatewayRefid{
	Unicom("联通"),Cmcc("移动"),Telecom("电信");
	
	private String desc;
	
	private SMSGatewayRefid(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public static SMSGatewayRefid getSmsGatewayRefid(String phoneNumber) {
		//移动
		String cm = "^((13[4-9])|(147)|(15[0-2,7-9])|(18[2-3,7-8]))\\d{8}$";
		//联通
		String cu = "^((13[0-2])|(145)|(152)|(15[5-6])|(186)|(185))\\d{8}$";
		//电信
		String ct = "^((133)|(153)|(18[0,9]))\\d{8}$";

		if (phoneNumber.matches(cm)) {
			return SMSGatewayRefid.Cmcc;
		} else if (phoneNumber.matches(cu)) {
			return SMSGatewayRefid.Unicom;
		} else if (phoneNumber.matches(ct)) {
			return SMSGatewayRefid.Telecom;
		} else {
			return null;
		}
	}
}
