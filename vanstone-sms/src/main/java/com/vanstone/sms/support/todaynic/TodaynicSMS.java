/**
 * 
 */
package com.vanstone.sms.support.todaynic;

import java.util.Hashtable;

import com.todaynic.client.mobile.SMS;
import com.vanstone.sms.SMSException;
import com.vanstone.sms.SendState;
import com.vanstone.sms.conf.SMSConf;
import com.vanstone.sms.support.AbstractSMS;

/**
 * @author shipeng
 */
public class TodaynicSMS extends AbstractSMS {

	public TodaynicSMS(String mobile, String content) {
		super(mobile, content);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vanstone.sms.support.AbstractSMS#sendInternal()
	 */
	@Override
	public SendState sendInternal() throws SMSException {
		Hashtable<String, String> configTable = new Hashtable<String, String>();
		configTable.put("VCPSERVER", SMSConf.getInstance().getVcpserver());
		configTable.put("VCPSVPORT", SMSConf.getInstance().getVcpsvport());
		configTable.put("VCPUSERID", SMSConf.getInstance().getVcpuserid());
		configTable.put("VCPPASSWD", SMSConf.getInstance().getVcppasswd());
		SMS sms = new SMS(configTable);
		try {
			boolean isok = sms.sendSMS(getMobile(), getContent(), "0", "3");
			if (LOG.isDebugEnabled()) {
				String receiveXml = sms.getSendXml();
				String code = sms.getCode();
				String msg = sms.getMsg();
				System.out.println(isok);
				System.out.println(receiveXml);
				System.out.println(code);
				System.out.println(msg);
			}
			return SendState.createSingleNotificationState(isok);
		} catch (Exception e) {
			e.printStackTrace();
			return SendState.createSingleNotificationState(false);
		}
	}

}
