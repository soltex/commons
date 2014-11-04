/**
 * Copyright 2009-2013 Sagacityidea.com.
 */
package com.vanstone.sms.impl;

import java.util.Date;

import com.vanstone.sms.Assert;
import com.vanstone.sms.MD5Util;

/**
 * @author shipeng
 * Mail:shipeng@sagacityidea.com
 */
public class BaseC106SMSChannelConf {
	
	/**
	 * 发送用户名 
	 */
	private String username;
	
	/**
	 * 发送密码
	 */
	private String password;
	
	public BaseC106SMSChannelConf(String username,String password) {
		Assert.hasText(username);
		Assert.hasText(password);
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	/**
	 * 获取Unix Timestamp
	 * @return
	 */
	protected long getUnixTimestamp() {
		return Math.round(new Date().getTime() / 1000);
	}
	
	/**
	 * 请求字符串
	 * @return
	 */
	public String toRequestQuery() {
		StringBuffer sb = new StringBuffer();
		//dt
		long timestamp = getUnixTimestamp();
		//username 
		sb.append("?username=").append(this.getUsername()).append("&");
		//pwd
		sb.append("pwd=").append(MD5Util.MD5(this.getUsername() + this.getPassword() + timestamp).toLowerCase()).append("&");
		//dt
		sb.append("dt=").append(timestamp);
		return sb.toString();
	}
}
