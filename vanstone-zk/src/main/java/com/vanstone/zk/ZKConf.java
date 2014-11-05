/**
 * 
 */
package com.vanstone.zk;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * zookeeper 配置信息
 * @author shipeng
 */
public class ZKConf {
	
	private static final String ZK_CONF_PATH = "/vanstone-zk.conf";
	
	/**UTF8编码*/
	public static final String SYS_CHARSET_STRING = "UTF-8";
	
	/**UTF8编码*/
	public static final Charset SYS_CHARSET = Charset.forName(SYS_CHARSET_STRING);
	
	private static class ZKConfInstance {
		private static final ZKConf instance = new ZKConf();
		static {
			try {
				instance.init();
			} catch (Exception e) {
				e.printStackTrace();
				throw new ExceptionInInitializerError(e);
			}
		}
	}
	
	/**ZK地址*/
	private String zk;
	/**ZK连接超时时间*/
	private int connectionTimeoutMS = 2000;
	
	private ZKConf(){}
	
	/**
	 * 获取单例
	 * @return
	 */
	public static ZKConf getInstance() {
		return ZKConfInstance.instance;
	}
	
	/**
	 * 初始化ZK配置信息
	 * @throws Exception
	 */
	private void init() throws Exception {
		Properties properties = new Properties();
		try {
			properties.load(ZKConf.class.getResourceAsStream(ZK_CONF_PATH));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.zk = properties.getProperty("zk");
		if (this.zk == null || this.zk.equals("")) {
			throw new IllegalArgumentException("zk is null.");
		}
		try {
			this.connectionTimeoutMS = Integer.parseInt(properties.getProperty("zk.connectionTimeoutMS"));
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 获取ZK地址
	 * @return
	 */
	public String getZk() {
		return zk;
	}
	
	/**
	 * 获取连接超时时间
	 * @return
	 */
	public int getConnectionTimeoutMS() {
		return connectionTimeoutMS;
	}
	
}
