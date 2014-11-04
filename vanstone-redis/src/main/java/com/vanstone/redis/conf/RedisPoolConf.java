/**
 * 
 */
package com.vanstone.redis.conf;

/**
 * @author peng
 */
public class RedisPoolConf {

	/**
	 * ID用于外界引用
	 */
	private String id;

	/**
	 * Redis IP地址
	 */
	private String ip;

	/**
	 * Redis默认值
	 */
	private int port = 6394;

	/**
	 * 
	 */
	private int maxActive = 500;

	/**
	 * 
	 */
	private int maxIdle = 60000;

	/**
	 * 
	 */
	private int maxWait = 10000;

	/**
	 * 
	 */
	private int timeout = 1000000;

	/**
	 * 
	 */
	private boolean testOnBorrow = true;

	public RedisPoolConf(String id, String ip, int port) {
		if (id == null || id.equals("") || ip == null || ip.equals("")) {
			throw new IllegalArgumentException("id or ip NULL.");
		}
		this.ip = ip;
		this.id = id;
		if (port > 0) {
			this.port = port;
		}
	}

	public String getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public int getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	public int getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("id=" + this.id).append(",").append("ip=").append(this.ip)
				.append(",").append("port=").append(this.port).append(",")
				.append("maxActive=").append(this.maxActive).append(",")
				.append("maxIdle=").append(this.maxIdle).append(",")
				.append("maxWait=").append(this.maxWait).append(",")
				.append("timeout=").append(this.timeout).append(",")
				.append("testOnBorrow=").append(this.testOnBorrow).append(",");
		return sb.toString();
	}
}
