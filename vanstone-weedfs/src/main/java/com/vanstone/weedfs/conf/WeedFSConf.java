/**
 * 
 */
package com.vanstone.weedfs.conf;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * @author shipeng
 */
public class WeedFSConf {
	
	private String serverAddress;
	private int serverPort;
//	private String[] nginxAddresses;
	
	/**
	 * 服务器地址
	 */
	private static final String SERVER_ADDRESS_KEY = "weedfs.server.address";
	
	/**
	 * 服务器端口
	 */
	private static final String SERVER_PORT_KEY = "weedfs.server.port";
	
//	/**
//	 * Nginx域名地址
//	 */
//	private static final String NGINX_SERVER_ADDRESSES = "weedfs.nginx.addresses";
	
//	/**
//	 * 默认种子生成器
//	 */
//	private static final Random random = new Random(System.currentTimeMillis());
	
	/**
	 * 默认配置文件-classpath下
	 */
	private static final String WEEDFS_CONF_PROPERTIES = "/weedfs.properties";
	
	private static class WeedFSConfInstance {
		private static final WeedFSConf instance ;
		static {
			Properties properties = new Properties();
			try {
				properties.load(WeedFSConf.class.getResourceAsStream(WEEDFS_CONF_PROPERTIES));
			} catch (IOException e) {
				e.printStackTrace();
				throw new ExceptionInInitializerError(e);
			}
			if (StringUtils.isEmpty(properties.getProperty(SERVER_ADDRESS_KEY))) {
				throw new ExceptionInInitializerError("Server Address Empty !");
			}
			if (StringUtils.isEmpty(properties.getProperty(SERVER_PORT_KEY))) {
				throw new ExceptionInInitializerError("Server Port Empty");
			}
//			String[] addresses = null;
//			if (!StringUtils.isEmpty(properties.getProperty(NGINX_SERVER_ADDRESSES))) {
//				String strs = properties.getProperty(NGINX_SERVER_ADDRESSES);
//				addresses = strs.split(",");
//			}
			instance = new WeedFSConf(properties.getProperty(SERVER_ADDRESS_KEY), Integer.parseInt(properties.getProperty(SERVER_PORT_KEY)));
		}
	}
	
//	private WeedFSConf(String serverAddress,int serverPort,String... nginxServerAddresses) {
	private WeedFSConf(String serverAddress,int serverPort) {
		this.serverAddress = serverAddress;
		if (serverPort <=0) {
			serverPort = 9333;
		}else{
			this.serverPort = serverPort;
		}
//		this.nginxAddresses = nginxServerAddresses;
	}
	
	/**
	 * 获取WeedFSConf配置信息,单例对象
	 * @return
	 */
	public static WeedFSConf getWeedFSConf() {
		return WeedFSConfInstance.instance;
	}
	
	public String getServerAddress() {
		return serverAddress;
	}

	public int getServerPort() {
		return serverPort;
	}

//	public String[] getNginxAddresses() {
//		return nginxAddresses;
//	}

//	/**
//	 * 随机获取NginxAddress地址信息
//	 * 算法 ： 默认通过Random来实现，
//	 * 扩展 ： 根据Ip地址信息Hash定位address
//	 * @return
//	 */
//	public String getRandNginxAddress() {
//		if (this.nginxAddresses == null || this.nginxAddresses.length <=0) {
//			return null;
//		}else if (this.nginxAddresses != null && this.nginxAddresses.length == 1) {
//			return this.nginxAddresses[0];
//		}else {
//			return this.nginxAddresses[random.nextInt(this.nginxAddresses.length)];
//		}
//	}
	
}
