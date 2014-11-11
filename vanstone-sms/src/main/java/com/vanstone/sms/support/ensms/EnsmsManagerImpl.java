/**
 * 
 */
package com.vanstone.sms.support.ensms;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.vanstone.sms.DefaultSMSManager;
import com.vanstone.sms.SMSException;
import com.vanstone.sms.SMSException.ErrorCode;
import com.vanstone.sms.conf.SMSConf;

/**
 * @author shipeng
 */
public class EnsmsManagerImpl extends DefaultSMSManager implements EnsmsManager {
	
	/* (non-Javadoc)
	 * @see com.vanstone.sms.support.ensms.EnsmsManager#getResidualSMSNum()
	 */
	@Override
	public int getResidualSMSNum() throws SMSException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(SMSConf.getInstance().getSMSNumAddr());
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String code = EntityUtils.toString(httpResponse.getEntity(), SMSConf.SYS_CHARSET);
				code = code.trim();
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
				return Integer.parseInt(code);
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
