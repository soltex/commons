/**
 * 
 */
package com.vanstone.sms.support;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.common.MyAssert;
import com.vanstone.sms.SMS;
import com.vanstone.sms.SMSException;
import com.vanstone.sms.SendState;

/**
 * @author shipeng
 */
public abstract class AbstractSMS implements SMS {
	
	protected static Logger LOG = LoggerFactory.getLogger(AbstractSMS.class);
	
	/**目标手机号*/
	private String mobile;
	/**目标群发手机号*/
	private Set<String> mobiles = new LinkedHashSet<String>();
	/**完成时间*/
	private Date completeTime;
	/**短信内容*/
	private String content;
	
	public AbstractSMS(String mobile, String content) {
		MyAssert.hasText(mobile);
		MyAssert.hasText(content);
		this.mobile = mobile;
		this.content = content;
	}
	
	public AbstractSMS(Collection<String> mobiles, String content) {
		MyAssert.notEmpty(mobiles);
		MyAssert.hasText(content);
		this.content = content;
		this.mobiles.addAll(mobiles);
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.sms.SMS#getContent()
	 */
	@Override
	public String getContent() {
		return this.content;
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.sms.SMS#getCompleteTime()
	 */
	@Override
	public Date getCompleteTime() {
		return this.completeTime;
	}

	/**
	 * 获取手机号
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}
	
	/**
	 * 获取手机号群发集合
	 * @return
	 */
	public Set<String> getMobiles() {
		return mobiles;
	}
	
	/**
	 * 是否为群发
	 * @return
	 */
	public boolean isMassSMS() {
		if (this.mobile != null && !this.mobile.equals("")) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.vanstone.sms.SMS#send()
	 */
	@Override
	public SendState send() throws SMSException {
		SendState sendState = this.sendInternal();
		this.completeTime = new Date();
		return sendState;
	}
	
	/**
	 * 内部发送实现
	 * @return
	 * @throws SMSException
	 */
	public abstract SendState sendInternal() throws SMSException;
}
