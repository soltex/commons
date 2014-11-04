/**
 * 
 */
package com.vanstone.redis;

import java.util.Map;

import redis.clients.jedis.JedisPool;

import com.vanstone.redis.conf.RedisPoolConf;
import com.vanstone.redis.error.ErrorHandler;

/**
 * @author shipeng
 *
 */
public interface RedisTemplate {
	
	/**
	 * 获取错误处理器
	 * @return
	 */
	ErrorHandler getErrorHandler();
	
	/**
	 * 获取RedisPool
	 * @param redisIdDefine
	 * @return
	 */
	JedisPool getJedisPool(RedisIdDefine redisIdDefine);
	
	/**
	 * 清理全部缓冲池数据
	 * @param redisIdDefine
	 */
	void flushAll(RedisIdDefine redisIdDefine);
	
	/**
	 * 执行回调
	 * @param redisCallback
	 */
	<T extends Object> T executeInRedis(RedisIdDefine redisIdDefine,RedisCallback<T> redisCallback);
	
	/**
	 * 获取Redis配置信息
	 * @return
	 */
	Map<String, RedisPoolConf> getConfs();
	
}
