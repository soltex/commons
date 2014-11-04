/**
 * 
 */
package com.vanstone.redis;

import redis.clients.jedis.Jedis;

/**
 * @author shipeng
 *
 */
public interface RedisCallback<T> {
	
	T doInRedis(Jedis jedis);
	
}
