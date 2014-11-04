/**
 * 
 */
package com.vanstone.sms.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;

import com.vanstone.commons.common.Assert;
import com.vanstone.commons.common.component.queue.GetMethod;
import com.vanstone.sms.C106ChannelCode;
import com.vanstone.sms.ConfObject;
import com.vanstone.sms.DateUtil;
import com.vanstone.sms.ISMSOf1069009130Manager;
import com.vanstone.sms.MsgUtil;

/**
 * @author shipeng
 */
public class SMSOf1069009130ManagerImpl implements ISMSOf1069009130Manager {
	
	private static Log _LOG = LogFactory.getLog(SMSOf1069009130ManagerImpl.class);
	
	/**
	 * 短信文字最大承载数量
	 */
	private static final int MAX_MSG_LETTER_COUNT = 400;
	
	@Override
	public List<C106ChannelCode> sendC106Msg(List<String> mobiles, String msg, int port) {
		if (mobiles == null || mobiles.size() <=0 || StringUtils.isEmpty(msg)) {
			throw new IllegalArgumentException();
		}
		if (port <=0) {
			throw new IllegalArgumentException("port < = 0");
		}
		String mtAddress = ConfObject.getInstance().getC106MTAddress();
		String mtUsername = ConfObject.getInstance().getC106Username();
		String mtPassword = ConfObject.getInstance().getC106Password();
		
		String[] arrays = MsgUtil.splitMsg(msg, MAX_MSG_LETTER_COUNT);
		
		List<C106ChannelCode> c106ChannelCodes = new ArrayList<C106ChannelCode>();
		
		for (String s : arrays) {
			C106MTObject mtObject = new C106MTObject(mtUsername, mtPassword, mobiles, s, port);
			GetMethod getMethod = new GetMethod(mtAddress + mtObject.toRequestQuery());
			C106ChannelCode smsChannelCode = null;
			HttpClient httpClient = new HttpClient();
			try {
				int statusCode = httpClient.executeMethod(getMethod);
				if (statusCode == HttpStatus.SC_OK) {
					String content = getMethod.getResponseBodyAsString();
					if (!StringUtils.isEmpty(content)) {
						smsChannelCode = C106ChannelCode.retrievalObject(Integer.parseInt(content));
					}else {
						smsChannelCode = C106ChannelCode.Other_Error;
					}
				}else {
					smsChannelCode = C106ChannelCode.Other_Error;
					_LOG.error("Send Message ERROR, Mobile -> " + mtObject.toRequestQuery());
				}
				c106ChannelCodes.add(smsChannelCode);
			} catch (HttpException e) {
				e.printStackTrace();
				throw new IllegalArgumentException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new IllegalArgumentException(e);
			} finally {
				if (getMethod != null) {
					getMethod.releaseConnection();
				}
				httpClient = null;
			}
		}
		return c106ChannelCodes;
	}

	@Override
	public C106ChannelCode sendC106Msg(String mobile, String msg, int port) {
		List<String> mobiles = new ArrayList<String>();
		mobiles.add(mobile);
		return this.sendC106Msg(mobiles, msg, port).get(0);
	}

	@Override
	public int getC106Inventories() {
		String inventoriesAddress = ConfObject.getInstance().getC106Inventoriesaddress();
		String mtUsername = ConfObject.getInstance().getC106Username();
		String mtPassword = ConfObject.getInstance().getC106Password();
		
		HttpClient httpClient = new HttpClient();
		
		BaseC106SMSChannelConf mtObject = new BaseC106SMSChannelConf(mtUsername, mtPassword);
		GetMethod getMethod = new GetMethod(inventoriesAddress + mtObject.toRequestQuery());
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String content = getMethod.getResponseBodyAsString();
				if (!StringUtils.isEmpty(content)) {
					return Integer.parseInt(content.trim());
				}
			}
			_LOG.error("SEND SMS -> HTTP CODE : " + statusCode);
			throw new IllegalArgumentException("http error " + statusCode);
		} catch (HttpException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
			httpClient = null;
		}
	}

	@Override
	public boolean sendHaottMsg(String mobile, String content) {
		Assert.hasText(mobile);
		Assert.hasText(content);
		String haottMTAddress = ConfObject.getInstance().getHaottMTAddress();
		Assert.hasText(haottMTAddress);
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = null;
		try {
			content = URLEncoder.encode(content, "utf-8");
			StringBuffer sb = new StringBuffer();
			sb.append("mbls=").append(mobile).append("&");
			sb.append("content=").append(content);
			haottMTAddress = haottMTAddress + "?" + sb.toString();
			getMethod = new GetMethod(haottMTAddress);
			int code = httpClient.executeMethod(getMethod);
			if (code == HttpStatus.SC_OK) {
				return true;
			}
			return false;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		} catch (HttpException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
			httpClient = null;
		}
	}

	@Override
	public boolean sendHaottMsg(List<String> mobiles, String content) {
		return false;
	}
	
	@Override
	public List<HaottMOObject> getHaottMOObjects() {
		String mtaddress = ConfObject.getInstance().getHaottMOAddress();
		Assert.hasText(mtaddress);
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = null;
		try {
			getMethod = new GetMethod(mtaddress);
			int code = httpClient.executeMethod(getMethod);
			if (code == HttpStatus.SC_OK) {
				String responseBody = new String(getMethod.getResponseBody(),"utf-8");
				if (responseBody == null || responseBody.trim().equals("-1")) {
					return null;
				}
				
				//字符串解析   18610558506,2013-11-29 14:37:19,测试<br/>
				responseBody = responseBody.trim();
				String[] strs = responseBody.split("<br/>");
				List<HaottMOObject> tts = new ArrayList<HaottMOObject>();
				for (String string : strs) {
					String[] array = string.split(",");
					String mobile = array[0];
					Date time = DateUtil.string2Date(array[1], "yyyy-MM-dd HH:mm:ss");
					String content = array[2];
					HaottMOObject mtObject = new HaottMOObject();
					mtObject.setMobile(mobile);
					mtObject.setMsg(content);
					mtObject.setMoTime(time);
					tts.add(mtObject);
				}
				return tts;
			}
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (HttpException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (httpClient != null) {
				getMethod.releaseConnection();
			}
			httpClient = null;
		}
	}

}
