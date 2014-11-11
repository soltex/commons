/**
 * 
 */
package com.vanstone.sms.support.ensms;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.vanstone.sms.SMSException;
import com.vanstone.sms.SMSException.ErrorCode;
import com.vanstone.sms.SendState;
import com.vanstone.sms.conf.SMSConf;
import com.vanstone.sms.support.AbstractSMS;

/**
 * @author shipeng
 *
 */
public class Ensms extends AbstractSMS {

	/**默认端口号*/
	private Integer port = null;
	
	public Ensms(String mobile, String content, Integer port) {
		super(mobile, content);
		if (port != null && port <0) {
			throw new IllegalArgumentException("ENSMS Port must GT 0");
		}
		this.port = port;
	}
	
	public Ensms(Collection<String> mobiles, String content, Integer port) {
		super(mobiles, content);
		this.port = port;
	}
	
	public Integer getPort() {
		return port;
	}

	/* (non-Javadoc)
	 * @see com.vanstone.sms.support.AbstractSMS#sendInternal()
	 */
	@Override
	public SendState sendInternal() throws SMSException {
		HttpClient httpClient = new DefaultHttpClient();
		Set<String> set = new LinkedHashSet<String>();
		if(!this.isMassSMS()) {
			set.add(this.getMobile());
		}else {
			set.addAll(this.getMobiles());
		}
		String uri = SMSConf.getInstance().getMTAddr(this.getContent(), set.toArray(new String[set.size()]), this.getPort());
		HttpGet httpGet = new HttpGet(uri);
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String code = EntityUtils.toString(httpResponse.getEntity(), SMSConf.SYS_CHARSET);
				code = code.trim();
				if (code.equals("0")) {
					return SendState.createSingleNotificationState(true);
				}
				if (code.equals("-1")) {
					throw new SMSException(ErrorCode.ENSMS_1);
				}
				if (code.equals("-2")) {
					throw new SMSException(ErrorCode.ENSMS_2);
				}
				if (code.equals("-3")) {
					throw new SMSException(ErrorCode.ENSMS_3);
				}
				if (code.equals("-4")) {
					throw new SMSException(ErrorCode.ENSMS_4);
				}
				if (code.equals("-5")) {
					throw new SMSException(ErrorCode.ENSMS_5);
				}
				if (code.equals("-6")) {
					throw new SMSException(ErrorCode.ENSMS_6);
				}
				if (code.equals("-7")) {
					throw new SMSException(ErrorCode.ENSMS_7);
				}
				if (code.equals("-8")) {
					throw new SMSException(ErrorCode.ENSMS_8);
				}
				if (code.equals("-9")) {
					throw new SMSException(ErrorCode.ENSMS_9);
				}
				if (code.equals("-10")) {
					throw new SMSException(ErrorCode.ENSMS_10);
				}
				if (code.equals("-11")) {
					throw new SMSException(ErrorCode.ENSMS_11);
				}
				if (code.equals("-12")) {
					throw new SMSException(ErrorCode.ENSMS_12);
				}
				if (code.equals("-13")) {
					throw new SMSException(ErrorCode.ENSMS_13);
				}
				if (code.equals("-14")) {
					throw new SMSException(ErrorCode.ENSMS_14);
				}
				if (code.equals("-100")) {
					throw new SMSException(ErrorCode.ENSMS_100);
				}
			}
			throw new SMSException(ErrorCode.SMS_Error);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			throw new SMSException(ErrorCode.Net_Error, e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SMSException(ErrorCode.Net_Error, e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}
}
