/**
 * 
 */
package com.vanstone.notification.support;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.notification.Notification;
import com.vanstone.notification.NotificationException;

/**
 * 抽象Notification
 * @author shipeng
 */
public abstract class AbstractNotification implements Notification {
	
	protected static Logger LOG = LoggerFactory.getLogger(AbstractNotification.class);
	
	private String content;
	private Date completeTime;
	
	public AbstractNotification(String content) {
		if (content == null || "".equals(content)) {
			throw new IllegalArgumentException();
		}
		this.content = content;
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
	
	/* (non-Javadoc)
	 * @see com.vanstone.notification.Notification#send()
	 */
	@Override
	public boolean send() throws NotificationException {
		boolean isok = this.sendInternal();
		this.completeTime = new Date();
		return isok;
	}
	
	/**
	 * 内部发送实现
	 * @throws NotificationException
	 * @return
	 */
	protected abstract boolean sendInternal() throws NotificationException;
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
