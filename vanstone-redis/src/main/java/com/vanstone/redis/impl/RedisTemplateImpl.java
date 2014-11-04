/**
 * 
 */
package com.vanstone.redis.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.vanstone.redis.RedisCallback;
import com.vanstone.redis.RedisCallbackWithoutResult;
import com.vanstone.redis.RedisIdDefine;
import com.vanstone.redis.RedisTemplate;
import com.vanstone.redis.conf.RedisPoolConf;
import com.vanstone.redis.error.DefaultRedisErrorHandler;
import com.vanstone.redis.error.ErrorHandler;

/**
 * @author shipeng
 */
public class RedisTemplateImpl implements RedisTemplate {
	
	/**
	 * 默认ErrorHandler处理器
	 */
	private ErrorHandler errorHandler = new DefaultRedisErrorHandler();
	
	/**
	 * RedisPoolConf Map
	 */
	private Map<String,RedisPoolConf> confs = new HashMap<String,RedisPoolConf>();
	
	private Map<String, JedisPool> jedisPoolMap = new HashMap<String, JedisPool>();
	
	private static final String PREFIX = "redis";
	
	/**
	 */
	private static Logger _LOG = LoggerFactory.getLogger(RedisTemplateImpl.class);
	
	/**
	 * redis配置文件
	 */
	private static final String CONF = "/ecointel-redis.properties";
	
	public RedisTemplateImpl() {
		InputStream is = getClass().getResourceAsStream(CONF);
		Properties properties = new Properties();
		try {
			properties.load(is);
			String strids = properties.getProperty("redis.ids");
			if (_isBlank(strids)) {
				throw new IllegalArgumentException("redis.ids NULL");
			}
			String[] ids = strids.trim().split(",");
			
			for (String id : ids) {
				String ip = properties.getProperty(PREFIX + "." + id + ".ip");
				if (_isBlank(id) || _isBlank(ip)) {
					throw new ExceptionInInitializerError("Redis Conf Initial Error,id or ip null.");
				}
				String strPort = properties.getProperty(PREFIX + "." + id + ".port");
				int port = 0;
				try {
					port = Integer.parseInt(strPort.trim());
				}catch (Exception e) {
					port = 0;
				}
				RedisPoolConf redisPoolConf = new RedisPoolConf(id.trim(), ip.trim(), port);
				String strmaxActive = properties.getProperty(PREFIX + "." + id + ".maxActive");
				if (!_isBlank(strmaxActive)) {
					try {
						redisPoolConf.setMaxActive(Integer.parseInt(strmaxActive));
					} catch (Exception e) {
					}
				}
				String strmaxIdle = properties.getProperty(PREFIX + "." + id + ".maxIdle");
				if (!_isBlank(strmaxIdle)) {
					try {
						redisPoolConf.setMaxIdle(Integer.parseInt(strmaxIdle.trim()));
					} catch (Exception e) {
					}
				}
				String strmaxWait = properties.getProperty(PREFIX + "." + id + ".maxWait");
				if (!_isBlank(strmaxWait)) {
					try {
						redisPoolConf.setMaxWait(Integer.parseInt(strmaxWait.trim()));
					} catch (Exception e) {
					}
					
				}
				String strtestOnBorrow = properties.getProperty(PREFIX + "." + id + ".testOnBorrow");
				if (!_isBlank(strtestOnBorrow)) {
					try {
						redisPoolConf.setTestOnBorrow(Boolean.parseBoolean(strtestOnBorrow.trim()));
					} catch (Exception e) {
					}
				}
				String strTimeout = properties.getProperty(PREFIX + "." + id + ".timeout");
				if (!_isBlank(strTimeout)) {
					try {
						redisPoolConf.setTimeout(Integer.parseInt(strTimeout.trim()));
					} catch (Exception e) {
					}
				}
				confs.put(id, redisPoolConf);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
		
		Set<Map.Entry<String, RedisPoolConf>> enties = confs.entrySet();
		if (enties != null && enties.size() > 0) {
			for (Map.Entry<String, RedisPoolConf> map : enties) {
				RedisPoolConf redisPoolConf = map.getValue();
				
				JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
				jedisPoolConfig.setMaxActive(redisPoolConf.getMaxActive());
				jedisPoolConfig.setMaxIdle(redisPoolConf.getMaxIdle());
				jedisPoolConfig.setTestOnBorrow(redisPoolConf.isTestOnBorrow());
				jedisPoolConfig.setMaxWait(redisPoolConf.getMaxWait());
				
				JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisPoolConf.getIp(), redisPoolConf.getPort());
				jedisPoolMap.put(redisPoolConf.getId(), jedisPool);
				if (_LOG.isInfoEnabled()) {
					_LOG.info("Redis initial -->> " + redisPoolConf.toString());
				}
			}
		}
	}
	
	/**
	 * 是否为null
	 * @param str
	 * @return
	 */
	private boolean _isBlank(String str) {
		if (str == null || str.equals("") || str.trim().equals("")) {
			return true;
		}
		return false;
	}
	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.redis.RedisTemplate#getErrorHandler()
	 */
	@Override
	public ErrorHandler getErrorHandler() {
		return this.errorHandler;
	}
	
	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.redis.RedisTemplate#getJedisPool(cn.com.ecointel.commons.redis.RedisIdDefine)
	 */
	@Override
	public JedisPool getJedisPool(RedisIdDefine redisIdDefine) {
		if (redisIdDefine == null || _isBlank(redisIdDefine.getId())) {
			throw new IllegalArgumentException("Redis ID NULL");
		}
		return jedisPoolMap.get(redisIdDefine.getId());
	}

	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.redis.RedisTemplate#flushAll(cn.com.ecointel.commons.redis.RedisIdDefine)
	 */
	@Override
	public void flushAll(RedisIdDefine redisIdDefine) {
		if (redisIdDefine == null) {
			throw new IllegalArgumentException("RedisIdDefine NULL");
		}
		this.executeInRedis(redisIdDefine, new RedisCallbackWithoutResult() {
			@Override
			public void doInRedisWithoutResult(Jedis jedis) {
				jedis.flushAll();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see cn.com.ecointel.commons.redis.RedisTemplate#executeInRedis(cn.com.ecointel.commons.redis.RedisIdDefine, cn.com.ecointel.commons.redis.RedisCallback)
	 */
	@Override
	public <T> T executeInRedis(RedisIdDefine redisIdDefine, RedisCallback<T> redisCallback) {
		JedisPool jedisPool = this.getJedisPool(redisIdDefine);
		if (jedisPool != null) {
			Jedis jedis = null;
			try {
				jedis = jedisPool.getResource();
				if (jedis != null) {
					return redisCallback.doInRedis(jedis);
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (jedis != null) {
					jedisPool.returnBrokenResource(jedis);
				}
				errorHandler.process(e);
				return null;
			} finally {
				if (jedis != null) {
					jedisPool.returnResource(jedis);
				}
			}
		}
		return null;
	}

	/**
	 * 获取配置信息
	 * @return
	 */
	@Override
	public Map<String, RedisPoolConf> getConfs() {
		return confs;
	}

}
