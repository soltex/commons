package com.vanstone.sms;

/**
 * @author shipeng
 */
public enum C106ChannelCode implements BaseEnum<Integer> {
	
	Success("成功",0),
	
	Send_More_Mobiles("一次发送的手机号码过多",-1),
	
	Username_Error("登录账户错误",-2),
	
	Password_Error("密码错误",-3),
	
	Balance_Not_Enough("余额不足",-4),
	
	Timeout("超时[注意检查服务器系统时间]",-5),
	
	Code_Illegal("code参数不合法",-6),
	
	Http_Method_Illegal("用成POST了，正确应该是GET",-7),
	
	Username_Not_Found("username参数丢失",-8),
	
	Password_Not_Found("pwd参数丢失",-9),
	
	MSG_Illegal("msg参数丢失 或者 msg为空信息 或 msg 编码不对",-10),
	
	Mobiles_Not_Found("mobiles参数丢失",-11),
	
	Timestamp_Not_Found("dt参数丢失",-12),
	
	Message_Char_GT_400("一次下发短信超过了400个字",-13),
	
	Mobile_Illegal("mobiles参数不对 不是正确电话号",-14),
	
	Other_Error("其他错误",-100);
	
	private String desc;
	private Integer code;
	
	private C106ChannelCode(String desc,Integer code) {
		this.desc = desc;
		this.code = code;
	}
	
	public Integer getCode() {
		return this.code;
	}

	public String getDesc() {
		return this.desc;
	}
	
	/**
	 * 获取SmsCode,取代EnumUtils.getByCode方法
	 * @param code
	 * @return
	 */
	public static C106ChannelCode retrievalObject(int code) {
		if (code >0 ) {
			throw new IllegalArgumentException("Code 非法");
		}
		C106ChannelCode smsCode = EnumUtils.getByCode(code, C106ChannelCode.class);
		if (smsCode == null) {
			return C106ChannelCode.Other_Error;
		}
		return smsCode;
	}
}
