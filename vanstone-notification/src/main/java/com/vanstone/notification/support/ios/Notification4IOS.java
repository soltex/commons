/**
 * 
 */
package com.vanstone.notification.support.ios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;

import org.json.JSONException;

import com.vanstone.notification.NotificationException;
import com.vanstone.notification.NotificationException.ErrorCode;
import com.vanstone.notification.SendState;
import com.vanstone.notification.conf.NotificationConf;
import com.vanstone.notification.support.AbstractNotification;

/**
 * 苹果消息通知
 * @author shipeng
 */
public class Notification4IOS extends AbstractNotification {
	
	/**声音*/
	private String sound = NotificationConf.IOS_DEFAULT_SOUND;
	/**徽章*/
	private int badge = NotificationConf.IOS_DEFAULT_BADGE;
	/**自定义参数*/
	private Map<String, Object> params = new LinkedHashMap<String, Object>();
	
	public Notification4IOS(String token, String content) {
		super(token, content);
	}
	
	/**
	 * 多Token
	 * @param tokens
	 * @param content
	 */
	public Notification4IOS(Collection<String> tokens, String content) {
		super(tokens, content);
	}
	
	public String getSound() {
		return sound;
	}
	
	public void setSound(String sound) {
		this.sound = sound;
	}

	public int getBadge() {
		return badge;
	}

	public void setBadge(int badge) {
		this.badge = badge;
	}

	/* (non-Javadoc)
	 * @see com.vanstone.notification.support.AbstractNotification#sendInternal()
	 */
	@Override
	protected SendState sendInternal() throws NotificationException {
		byte[] contentBytes = this.getContent().getBytes(NotificationConf.SYS_CHARSET);
		if (contentBytes.length > NotificationConf.IOS_MAX_CONTENT_BYTE_SIZE) {
			throw NotificationException.create(ErrorCode.Content_Char_Max_Size);
		}
		
		PushNotificationPayload payload = new PushNotificationPayload();
		try {
			//通知定义
			payload.addAlert(this.getContent());
			payload.addBadge(badge);
			payload.addSound(sound);
//			payload.addCustomAlertBody("<a href='消息体'>呵呵呵</a>");
			if (this.containParams()) {
				for (Map.Entry<String, Object> param : params.entrySet()) {
					payload.addCustomDictionary(param.getKey(), param.getValue());
				}
			}
			PushNotificationManager pushNotificationManager = new PushNotificationManager();
			pushNotificationManager.initializeConnection(new AppleNotificationServerBasicImpl(NotificationConf.getInstance().getCertification(), NotificationConf.getInstance().getCertificationPassword(), NotificationConf.getInstance().getEnvironment().getBooleanValueOfEnvironment()));
			if (!this.isMassNotification()) {
				//单体发送
				//设备定义
				Device device = new BasicDevice();
				device.setToken(this.getToken());
				//管理器定义以及消息发送
				PushedNotification pushedNotification = pushNotificationManager.sendNotification(device, payload);
				if (pushedNotification.isSuccessful()) {
					return SendState.createSingleNotificationState(true);
				}
				LOG.error("IOS Send Msg Error, Reason : {}", pushedNotification.getResponse().getMessage());
				return SendState.createSingleNotificationState(false);
			}else{
				//群发
				List<Device> devices = new ArrayList<Device>();
				for (String tempToken: this.getTokens()) {
					Device device = new BasicDevice();
					device.setToken(tempToken);
					devices.add(device);
				}
				PushedNotifications pushedNotifications = pushNotificationManager.sendNotifications(payload, devices);
				PushedNotifications failPushedNotifications = pushedNotifications.getFailedNotifications();
				PushedNotifications successPushedNotifications = pushedNotifications.getSuccessfulNotifications();
				SendState sendState = new SendState();
				if (failPushedNotifications != null && failPushedNotifications.size() >0) {
					sendState.incFailNum(failPushedNotifications.size());
				}
				if (successPushedNotifications != null && successPushedNotifications.size() >0) {
					sendState.incSuccessNum(successPushedNotifications.size());
				}
				return sendState;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return SendState.createSingleNotificationState(false);
		} catch (CommunicationException e) {
			e.printStackTrace();
			return SendState.createSingleNotificationState(false);
		} catch (KeystoreException e) {
			e.printStackTrace();
			return SendState.createSingleNotificationState(false);
		}
	}
	
	/**
	 * 添加参数
	 * @param name
	 * @param value
	 */
	public void addParam(String name, Object value) {
		if (name == null || name.equals("")) {
			throw new IllegalArgumentException();
		}
		this.params.put(name, value);
	}
	
	/**
	 * 批量添加参数
	 * @param params
	 */
	public void addParams(Map<String, Object> params) {
		if (params == null || params.size() <=0) {
			throw new IllegalArgumentException();
		}
		this.params.putAll(params);
	}
	
	/**
	 * 是否包含参数
	 * @return
	 */
	public boolean containParams() {
		return this.params.size() != 0;
	}
	
}
