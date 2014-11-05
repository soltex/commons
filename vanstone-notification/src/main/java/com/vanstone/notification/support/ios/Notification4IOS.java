/**
 * 
 */
package com.vanstone.notification.support.ios;

import java.util.LinkedHashMap;
import java.util.Map;

import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import org.json.JSONException;

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
	/**设备Token*/
	private String token;
	/**自定义参数*/
	private Map<String, Object> params = new LinkedHashMap<String, Object>();
	
	/**
	 * 默认构造Notification4IOS
	 * @param token
	 * @param content
	 */
	public Notification4IOS(String token, String content) {
		super(content);
		this.token = token;
	}
	
	public String getToken() {
		return token;
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

	public void setToken(String token) {
		this.token = token;
	}

	/* (non-Javadoc)
	 * @see com.vanstone.notification.support.AbstractNotification#sendInternal()
	 */
	@Override
	protected boolean sendInternal() {
		PushNotificationPayload payload = new PushNotificationPayload();
		try {
			
			//通知定义
			payload.addAlert(this.getContent());
			payload.addBadge(badge);
			payload.addSound(sound);
//			payload.addCustomAlertBody("<a href='消息体'>呵呵呵</a>");
//			payload.addCustomDictionary("username", "shipeng");
//			payload.addCustomDictionary("password", "shipengpassword");
			if (this.containParams()) {
				for (Map.Entry<String, Object> param : params.entrySet()) {
					payload.addCustomDictionary(param.getKey(), param.getValue());
				}
			}
			//设备定义
			Device device = new BasicDevice();
			device.setToken(token);
			
			//管理器定义以及消息发送
			PushNotificationManager pushNotificationManager = new PushNotificationManager();
			pushNotificationManager.initializeConnection(new AppleNotificationServerBasicImpl(NotificationConf.getInstance().getCertification(), NotificationConf.getInstance().getCertificationPassword(), NotificationConf.getInstance().getEnvironment().getBooleanValueOfEnvironment()));
			PushedNotification pushedNotification = pushNotificationManager.sendNotification(device, payload);
			if (pushedNotification.isSuccessful()) {
				return true;
			}
			LOG.error("IOS Send Msg Error, Reason : {}", pushedNotification.getResponse().getMessage());
			return false;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		} catch (CommunicationException e) {
			e.printStackTrace();
			return false;
		} catch (KeystoreException e) {
			e.printStackTrace();
			return false;
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
