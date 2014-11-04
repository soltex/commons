package com.vanstone.mail;


/**
 * @author peng.shi
 */
public interface IHostConf {

	/**
	 * smtp
	 * @return
	 */
	String getSmtp();

	/**
	 * smtp端口
	 * @return
	 */
	int getSmtpPort();

	/**
	 * 获取发送地址
	 * @return
	 */
	String getFromEmailAddress();

	/**
	 * 获取邮件用户名
	 * @return
	 */
	String getEmailUsername();

	/**
	 * 获取邮件密码
	 * @return
	 */
	String getEmailUserPwd();

	/**
	 * 
	 * 是否ssl
	 * @return
	 */
	boolean isSecurityable();

	/**
	 * 是否debug
	 * @return
	 */
	boolean isDebugable();

	/**
	 * 获取字符集
	 * @return
	 */
	String getCharset();
}
