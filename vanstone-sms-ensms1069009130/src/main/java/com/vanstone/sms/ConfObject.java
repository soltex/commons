/**
 * 
 */
package com.vanstone.sms;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * 短信工具包配置文件
 * @author shipeng
 *
 */
public class ConfObject {
	
	/**
	 * 默认配置文件
	 */
	private static final String SMS_CONF_PROPERTIES = "/sagacityidea-sms.properties";
	
	/**
	 * 106用户名
	 */
	private String c106Username;
	/**
	 * 106密码
	 */
	private String c106Password;
	/**
	 * 106下发短信地址
	 */
	private String c106MTAddress;
	/**
	 * 查询库存量地址
	 */
	private String c106Inventoriesaddress;
	/**
	 * C12114地址
	 */
	private String c12114MTAddress;
	/**
	 * haott 下行短信接口地址
	 */
	private String haottMTAddress;
	/**
	 * haott上行短信接口地址
	 */
	private String haottMOAddress;
	/**
	 * 
	 */
	private static Object lock = new Object();
	
	/**
	 * Instance
	 */
	private static ConfObject confObject;
	
	/**
	 * 默认配置文件
	 */
	private Properties properties = new Properties();
	
	private ConfObject(){}
	
	/**
	 * 获取实例
	 * @return
	 */
	public static ConfObject getInstance() {
		if (confObject == null) {
			synchronized (lock) {
				confObject = new ConfObject();
				confObject._init();
			}
		}
		return confObject;
	}
	
	/**
	 * 配置文件初始化
	 */
	private void _init() {
		InputStream is = this.getClass().getResourceAsStream(SMS_CONF_PROPERTIES);
		try {
			properties.load(is);
			this.c106MTAddress = properties.getProperty("c1069009130.mtaddress");
			if (StringUtils.isEmpty(getC106MTAddress())) {
				throw new ExceptionInInitializerError("c1069009130.mtaddress is null.");
			}
			this.c106Inventoriesaddress = properties.getProperty("c1609669130.inventoriesaddress");
			if (StringUtils.isEmpty(getC106Inventoriesaddress())) {
				throw new ExceptionInInitializerError("c1069009130.c106Inventoriesaddress is null.");
			}
			this.c106Username = properties.getProperty("c1609669130.username");
			if (StringUtils.isEmpty(getC106Username())) {
				throw new ExceptionInInitializerError("c1069009130.c106Username is null.");
			}
			this.c106Password = properties.getProperty("c1609669130.password");
			if (StringUtils.isEmpty(getC106Password())) {
				throw new ExceptionInInitializerError("c1069009130.c106Password is null.");
			}
			this.c12114MTAddress = properties.getProperty("c12114.mtaddress");
			if (!StringUtils.isEmpty(getC12114MTAddress())) {
				throw new ExceptionInInitializerError("c12114.mtaddress is null.");
			}
			this.haottMOAddress = properties.getProperty("haott.moaddress");
			this.haottMTAddress = properties.getProperty("haott.mtaddress");
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * 获取Haott下行短信接口地址
	 * @return
	 */
	public String getHaottMTAddress() {
		return haottMTAddress;
	}

	/**
	 * 获取haott下行短信接口地址
	 * @return
	 */
	public String getHaottMOAddress() {
		return haottMOAddress;
	}

	/**
	 * C106 Username
	 * @return
	 */
	public String getC106Username() {
		return c106Username;
	}

	/**
	 * C106 Password
	 * @return
	 */
	public String getC106Password() {
		return c106Password;
	}

	/**
	 * C106 MTAddress
	 * @return
	 */
	public String getC106MTAddress() {
		return c106MTAddress;
	}

	/**
	 * C106 Inventoriesaddress
	 * @return
	 */
	public String getC106Inventoriesaddress() {
		return c106Inventoriesaddress;
	}
	
	/**
	 * 获取C12114地址
	 * @return
	 */
	public String getC12114MTAddress() {
		return c12114MTAddress;
	}

	/**
	 * 重新加载
	 */
	public void reload() {
		synchronized (lock) {
			this.properties.clear();
			this._init();
		}
	}
}
