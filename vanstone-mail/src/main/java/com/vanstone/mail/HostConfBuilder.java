/**
 * 
 */
package com.vanstone.mail;

import com.vanstone.mail.impl.HostConfImpl;

/**
 * @author shipeng
 *
 */
public class HostConfBuilder {
	
	/**
	 * 邮件主机配置
	 */
	private HostConfImpl hostConf;
	
	public HostConfBuilder() {
		hostConf = new HostConfImpl();
	}
	
	/**
	 * 设定smtp
	 * @param smtp
	 * @return
	 */
	public HostConfBuilder smtp(String smtp) {
		if (smtp == null || smtp.equals("")) {
			throw new IllegalArgumentException("smtp is null");
		}
		this.hostConf.setSmtp(smtp);
		return this;
	}
	
	/**
	 * 设定smtp端口
	 * @param port
	 * @return
	 */
	public HostConfBuilder smtpPort(int port) {
		if (port < 0) {
			throw new IllegalArgumentException("smtp < 0 ");
		}
		this.hostConf.setSmtpPort(port);
		return this;
	}
	
	/**
	 * 发送方邮件地址
	 * @param fromEmailAddress
	 * @return
	 */
	public HostConfBuilder fromEmailAddress(String fromEmailAddress) {
		if (fromEmailAddress == null || fromEmailAddress.equals("")) {
			throw new IllegalArgumentException("from email address is null");
		}
		this.hostConf.setFromEmailAddress(fromEmailAddress);
		return this;
	}
	
	/**
	 * 用户名
	 * @param emailUsername
	 * @return
	 */
	public HostConfBuilder emailUsername(String emailUsername) {
		this.hostConf.setEmailUsername(emailUsername);
		return this;
	}
	
	/**
	 * 密码
	 * @param emailUserPwd
	 * @return
	 */
	public HostConfBuilder emailUserPwd(String emailUserPwd) {
		this.hostConf.setEmailUserPwd(emailUserPwd);
		return this;
	}
	
	/**
	 * 是否ssl
	 * @param securityable
	 * @return
	 */
	public HostConfBuilder securityable(boolean securityable) {
		this.hostConf.setSecurityable(securityable);
		return this;
	}
	
	/**
	 * 是否debug
	 * @param debugable
	 * @return
	 */
	public HostConfBuilder debugable(boolean debugable) {
		this.hostConf.setDebugable(debugable);
		return this;
	}
	
	/**
	 * UTF-8
	 * @param charset
	 * @return
	 */
	public HostConfBuilder charset(String charset) {
		this.hostConf.setCharset(charset);
		return this;
	}
	/**
	 * 建立邮件主机配置
	 * @return
	 * @throws HostIllegalArgumentException
	 */
	public IHostConf build() throws HostIllegalArgumentException {
		if (this.hostConf.getSmtp() == null || this.hostConf.getSmtp().equals("") || this.hostConf.getFromEmailAddress() == null || this.hostConf.getFromEmailAddress().equals("")) {
			throw new HostIllegalArgumentException("host conf smtp null or from mail address null");
		}
		return this.hostConf;
	}
	
}
