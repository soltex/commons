package com.vanstone.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * @author peng.shi
 */
public class MailObject extends HtmlEmail {
	
	/**
	 * 邮件配置
	 */
	private IHostConf hostConfig;

	/**
	 * @param mailType
	 */
	public MailObject(IHostConf hostConf) {
		super();
		if (hostConf == null) {
			throw new IllegalArgumentException();
		}
		
		this.hostConfig = hostConf;
		this.setSmtpPort(hostConfig.getSmtpPort());
		this.setAuthentication(hostConfig.getEmailUsername(), hostConfig.getEmailUserPwd());
		this.setSSLOnConnect(hostConf.isSecurityable());
		this.setCharset(hostConf.getCharset());
		this.setDebug(hostConf.isDebugable());
		this.setHostName(this.hostConfig.getSmtp());
		try {
			this.setFrom(hostConfig.getFromEmailAddress());
		} catch (EmailException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * 获取邮件主机设置
	 * @return
	 */
	public IHostConf getHostConfig() {
		return this.hostConfig;
	}

	/**
	 * 增加附件
	 * @param path
	 * @param name
	 * @param description
	 * @throws EmailException
	 */
	public void attach(String path, String name, String description) throws EmailException {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(path);
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		try {
			attachment.setName(name != null && !name.equals("") ? MimeUtility.encodeText(name) : null);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		attachment.setDescription(description);
		attach(attachment);
	}

}
