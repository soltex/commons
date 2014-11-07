/**
 * 
 */
package com.vanstone.notification.support;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.notification.Notification;
import com.vanstone.notification.NotificationException;
import com.vanstone.notification.SendState;

/**
 * 抽象Notification
 * @author shipeng
 */
public abstract class AbstractNotification implements Notification {
	
	protected static Logger LOG = LoggerFactory.getLogger(AbstractNotification.class);
	
	/**通知内容*/
	private String content;
	/**通知发送完成时间*/
	private Date completeTime;
	/**设备Token*/
	private String token;
	/**群发Tokens*/
	private Set<String> tokens = new LinkedHashSet<String>();
	
	/**
	 * 默认构造Notification4IOS,单个Token
	 * @param token
	 * @param content
	 */
	public AbstractNotification(String token, String content) {
		if (content == null || "".equals(content)) {
			throw new IllegalArgumentException();
		}
		this.content = content;
		this.token = token;
	}
	
	/**
	 * 多Token
	 * @param tokens
	 * @param content
	 */
	public AbstractNotification(Collection<String> tokens, String content) {
		if (content == null || "".equals(content)) {
			throw new IllegalArgumentException();
		}
		this.content = content;
		if (tokens == null || tokens.size() <=0) {
			throw new IllegalArgumentException("Tokens is Empty.");
		}
		this.tokens.addAll(tokens);
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.notification.Notification#getContent()
	 */
	@Override
	public String getContent() {
		return this.content;
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.notification.Notification#getCompleteTime()
	 */
	@Override
	public Date getCompleteTime() {
		return this.completeTime;
	}
	
	public String getToken() {
		return token;
	}
	
	public Set<String> getTokens() {
		return tokens;
	}
	
	@Override
	public boolean isMassNotification() {
		if (this.getToken() == null || this.getToken().equals("")) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.notification.Notification#send()
	 */
	@Override
	public SendState send() throws NotificationException {
		SendState notificationState = this.sendInternal();
		this.completeTime = new Date();
		return notificationState;
	}
	
	/**
	 * 内部发送实现
	 * @throws NotificationException
	 * @return
	 */
	protected abstract SendState sendInternal() throws NotificationException;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
