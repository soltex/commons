/**
 * 
 */
package com.vanstone.redis;

import redis.clients.jedis.Jedis;

/**
 * @author shipeng
 *
 */
public abstract class RedisCallbackWithoutResult implements RedisCallback<Object> {

	@Override
	final public Object doInRedis(Jedis jedis) {
		this.doInRedisWithoutResult(jedis);
		return null;
	}
	
	/**
	 * 无返回值回调
	 * @param jedis
	 */
	public abstract void doInRedisWithoutResult(Jedis jedis);
	
}
