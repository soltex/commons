/**
 * 
 */
package com.vanstone.mail.impl;

import com.vanstone.mail.IHostConf;

/**
 * @author shipeng
 *
 */
public class HostConfImpl implements IHostConf {
	
	/**
	 * smtp地址
	 */
	private String smtp;
	/**
	 * 端口号
	 */
	private int smtpPort = 25;
	/**
	 * 发送方邮件地址
	 */
	private String fromEmailAddress;
	/**
	 * 用户名
	 */
	private String emailUsername;
	/**
	 * 密码
	 */
	private String emailUserPwd;
	/**
	 * 是否ssl
	 */
	private boolean securityable = false;
	/**
	 * 是否debug
	 */
	private boolean debugable = false;
	/**
	 * UTF-8
	 */
	private String charset = "UTF-8";
	
	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.mail.IHostConf#getSmtp()
	 */
	@Override
	public String getSmtp() {
		return this.smtp;
	}

	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.mail.IHostConf#getSmtpPort()
	 */
	@Override
	public int getSmtpPort() {
		return this.smtpPort;
	}

	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.mail.IHostConf#getFromEmailAddress()
	 */
	@Override
	public String getFromEmailAddress() {
		return this.fromEmailAddress;
	}

	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.mail.IHostConf#getEmailUsername()
	 */
	@Override
	public String getEmailUsername() {
		return this.emailUsername;
	}

	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.mail.IHostConf#getEmailUserPwd()
	 */
	@Override
	public String getEmailUserPwd() {
		return this.emailUserPwd;
	}

	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.mail.IHostConf#isSecurityable()
	 */
	@Override
	public boolean isSecurityable() {
		return this.securityable;
	}

	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.mail.IHostConf#isDebugable()
	 */
	@Override
	public boolean isDebugable() {
		return this.debugable;
	}

	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.mail.IHostConf#getCharset()
	 */
	@Override
	public String getCharset() {
		return this.charset;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}

	public void setEmailUsername(String emailUsername) {
		this.emailUsername = emailUsername;
	}

	public void setEmailUserPwd(String emailUserPwd) {
		this.emailUserPwd = emailUserPwd;
	}

	public void setSecurityable(boolean securityable) {
		this.securityable = securityable;
	}

	public void setDebugable(boolean debugable) {
		this.debugable = debugable;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}
	
}
