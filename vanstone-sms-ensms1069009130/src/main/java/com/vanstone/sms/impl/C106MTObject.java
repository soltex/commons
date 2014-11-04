/**
 * Copyright 2009-2013 Sagacityidea.com.
 */
package com.vanstone.sms.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.vanstone.sms.Assert;
import com.vanstone.sms.MD5Util;
import com.vanstone.sms.SMSError;
import com.vanstone.sms.SMSError.SMSErrorCode;

/**
 * 短信下发对象封装
 * @author shipeng
 * Mail:shipeng@sagacityidea.com
 */
public class C106MTObject extends BaseC106SMSChannelConf {
	
	private List<String> mobiles = new ArrayList<String>();
	private String msg;
	private int code;
	
	public C106MTObject(String username,String password,List<String> mobiles,String msg, int code) {
		super(username,password);
		Assert.hasText(username);
		Assert.hasText(password);
		Assert.notNull(mobiles);
		if (code <0 ) {
			code = 0;
		}
		this.mobiles = mobiles;
		this.msg = msg;
		this.code = code;
	}
	
	/**
	 * 手机号
	 * @return
	 */
	public List<String> getMobiles() {
		return mobiles;
	}

	/**
	 * 短信内容
	 * @return
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 下发端口号
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param mobile
	 */
	public void addMobile(String mobile) {
		if (!mobiles.contains(mobile)) {
			mobiles.add(mobile);
		}
	}
	
	/**
	 * @param mobile
	 */
	public void removeMobile(String mobile) {
		if (!mobiles.contains(mobile)) {
			mobiles.remove(mobile);
		}
	}
	
	/**
	 * @param newmobiles
	 */
	public void addMobiles(List<String> newmobiles) {
		if (newmobiles != null && newmobiles.size() >0) {
			for (String m : newmobiles) {
				mobiles.add(m);
			}
		}
	}
	
	/**
	 * 获取逗号分隔的mobiles
	 * @return
	 */
	private String _getMobilesOfComma() {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<mobiles.size();i++) {
			sb.append(mobiles.get(i));
			if (i != mobiles.size() -1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 生成短信下发RequestQuery字符串
	 * @return
	 */
	@Override
	public String toRequestQuery() {
		if (mobiles == null || mobiles.size() <=0) {
			throw new IllegalArgumentException();
		}
		if (this.mobiles.size() >50) {
			throw new SMSError(SMSErrorCode.MT_MOBILE_COUNT_GT_50);
		}
		StringBuffer sb = new StringBuffer();
		//dt
		long timestamp = this.getUnixTimestamp();
		//username 
		sb.append("?username=").append(this.getUsername()).append("&");
		//pwd
		sb.append("pwd=").append(MD5Util.MD5(this.getUsername() + this.getPassword() + timestamp).toLowerCase()).append("&");
		//msg
		sb.append("mobiles=").append(this._getMobilesOfComma()).append("&");
		//encodeURIComponent
		try {
			sb.append("msg=").append(URLEncoder.encode(msg,"utf-8")).append("&");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//sb.append("msg=").append(ASURLEncoder.encodeURIComponent(msg,"utf-8")).append("&");
		//code
		if (code != 0) {
			sb.append("code=").append(code).append("&");
		}
		//dt
		sb.append("dt=").append(timestamp);
		return sb.toString();
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		List<String > mobiles = new ArrayList<String>();
		mobiles.add("134261733105");
		C106MTObject object = new C106MTObject("shipeng", "pipi", mobiles , "呵呵呵", 30);
		System.out.println(object.toRequestQuery());
	}
}
