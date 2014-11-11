/**
 * 
 */
package com.vanstone.sms.conf;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

import com.vanstone.common.util.MD5Util;
import com.vanstone.sms.support.ensms.TimestampUtil;

/**
 * @author shipeng
 */
public class SMSConf {
	
	private static class SMSConfInstance {
		private static final SMSConf instance = new SMSConf();
		static {
			instance.init();
		}
	}
	
	/**系统编码*/
	public static final String SYS_CHARSET_STRING = "utf-8";
	
	/**系统编码*/
	public static final Charset SYS_CHARSET = Charset.forName(SYS_CHARSET_STRING);
	
	/**
	 * 获取当前实例信息
	 * @return
	 */
	public static SMSConf getInstance() {
		return SMSConfInstance.instance;
	}
	
	//===============================Global短信接口设置================================//
	public static final String SMS_CONF = "/vanstone-sms.conf";
	
	
	
	
	//===============================ENSMS短信接口设置===============================//
	/**ENSMS 1069009130下行地址*/
	public static final String ENSMS_MT_ADDR_TEMPALTE = "http://sms.ensms.com:8080/sendsms/?username=#username#&pwd=#pwd#&dt=#dt#&msg=#msg#&mobiles=#mobiles#&code=#code#";
	/**ENSMS 1069009130上行地址*/
	public static final String ENSMS_MO_ADDR = "";
	/**ENSMS 1069009130短信剩余量*/
	public static final String ENSMS_SMS_NUM_ADDR_TEMPLATE = "http://sms.ensms.com:8080/getsmsnum/?username=#username#&pwd=#pwd#&dt=#dt#";
	
	/**ENSMS用户名 ensms.username*/
	private String ensmsUsername;
	/**ENSMS密码 ensms.password*/
	private String ensmsPassword;
	
	
	public void init() {
		//初始化配置信息
		Properties properties = new Properties();
		try {
			properties.load(SMSConf.class.getResourceAsStream(SMS_CONF));
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
		this.ensmsUsername = properties.getProperty("ensms.username");
		this.ensmsPassword = properties.getProperty("ensms.password");
		
		//todaynic配置信息
		this.vcpserver=properties.getProperty("todaynic.VCPSERVER");
		this.vcpsvport=properties.getProperty("todaynic.VCPSVPORT");
		this.vcpuserid=properties.getProperty("todaynic.VCPUSERID");
		this.vcppasswd=properties.getProperty("todaynic.VCPPASSWD");
	}
	
	public String getEnsmsUsername() {
		return ensmsUsername;
	}
	public String getEnsmsPassword() {
		return ensmsPassword;
	}
	
	/**
	 * 获取当前发送下行地址信息以及短信信息
	 * @param msg
	 * @param mobiles
	 * @param code
	 * @return
	 */
	public String getMTAddr(String msg, String[] mobiles, Integer code) {
		if (msg == null || msg.equals("")) {
			throw new IllegalArgumentException();
		}
		if (mobiles == null || mobiles.length <=0 || mobiles.length > 50) {
			throw new IllegalArgumentException();
		}
		//dt
		long dt = TimestampUtil.getUnixTimestamp();
		//username 
		String username = getEnsmsUsername();
		//pwd
		String pwd = MD5Util.MD5(this.getEnsmsUsername() + this.getEnsmsPassword() + dt).toLowerCase();
		String address = StringUtils.replace(ENSMS_MT_ADDR_TEMPALTE, "#username#", username);
		address = StringUtils.replace(address, "#pwd#", pwd);
		address = StringUtils.replace(address, "#dt#", String.valueOf(dt));
		try {
			address = StringUtils.replace(address, "#msg#", URLEncoder.encode(msg, SYS_CHARSET_STRING));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<mobiles.length;i++) {
			sb.append(mobiles[i]);
			if (i != mobiles.length -1) {
				sb.append(",");
			}
		}
		address = StringUtils.replace(address, "#mobiles#", sb.toString());
		address = StringUtils.replace(address, "#code#", code == null ? "" : String.valueOf(code));
		return address;
	}
	
	public String getSMSNumAddr() {
		long dt = TimestampUtil.getUnixTimestamp();
		//username 
		String username = getEnsmsUsername();
		//pwd
		String pwd = MD5Util.MD5(this.getEnsmsUsername() + this.getEnsmsPassword() + dt).toLowerCase();
		String address = StringUtils.replace(ENSMS_SMS_NUM_ADDR_TEMPLATE, "#username#", username);
		address = StringUtils.replace(address, "#pwd#", pwd);
		address = StringUtils.replace(address, "#dt#", String.valueOf(dt));
		return address;
	}
	
	
	
	//===============================Todaynic短信接口设置===============================//
	/**VCPSERVER*/
	private String vcpserver="sms.todaynic.com";
	/**VCPSVPORT*/
	private String vcpsvport="20002";
	/**VCPUSERID*/
	private String vcpuserid="ms98726";
	/**VCPPASSWD*/
	private String vcppasswd="mtiynd";

	public String getVcpserver() {
		return vcpserver;
	}

	public String getVcpsvport() {
		return vcpsvport;
	}

	public String getVcpuserid() {
		return vcpuserid;
	}

	public String getVcppasswd() {
		return vcppasswd;
	}
}
