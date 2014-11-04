/**
 * Ecointel-R&D
 */
package com.vanstone.framework.business.services;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import redis.clients.jedis.Jedis;

import com.vanstone.business.MyAssert4Business;
import com.vanstone.business.def.AbstractBusinessObject;
import com.vanstone.business.def.BusinessObjectKeyBuilder;
import com.vanstone.business.serialize.SerializationUtil;
import com.vanstone.redis.RedisCallback;
import com.vanstone.redis.RedisCallbackWithoutResult;
import com.vanstone.redis.RedisIdDefine;
import com.vanstone.redis.RedisTemplate;

/**
 * @author shipeng
 */
public class ServiceUtil<T extends AbstractBusinessObject,V extends Serializable> {
	
	public static final Logger LOG = LoggerFactory.getLogger(ServiceUtil.class);
	
	/**
	 * 获取Ids
	 * @param ts
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<V> getIds(Collection<T> ts) {
		if (CollectionUtils.isEmpty(ts)) {
			return null;
		}
		List vs = new ArrayList();
		for (T t : ts) {
			vs.add(t.getId());
		}
		return vs;
	}
	
	/**
	 * 从redis中获取业务对象信息
	 * @param clazz
	 * @param id
	 * @return T extends <code>cn.com.ecointel.framework.business.AbstractBusinessObject</code>
	 */
	public T getObjectFromRedis(RedisTemplate redisTemplate, RedisIdDefine redisIdDefine,final Class<T> clazz,final Serializable id) {
		MyAssert4Business.notNull(redisIdDefine);
		MyAssert4Business.notNull(clazz);
		MyAssert4Business.notNull(id);
		MyAssert4Business.notNull(redisTemplate);
		T t = redisTemplate.executeInRedis(redisIdDefine, new RedisCallback<T>() {
			
			@Override
			public T doInRedis(Jedis jedis) {
				byte[] bs = jedis.get(BusinessObjectKeyBuilder.class2key(clazz, id).getBytes());
				if (bs != null && bs.length >0) {
					return SerializationUtil.bytes2Object(bs, clazz);
				}
				return null;
			}
			
		});
		return t;
	}
	
	/**
	 * 通过缓冲Key获取T
	 * @param redisTemplate
	 * @param redisIdDefine
	 * @param clazz
	 * @param key
	 * @return
	 */
	public T getObjectFromRedisByKey(RedisTemplate redisTemplate, RedisIdDefine redisIdDefine,final Class<T> clazz,final String key) {
		MyAssert4Business.notNull(redisIdDefine);
		MyAssert4Business.notNull(clazz);
		MyAssert4Business.notNull(key);
		MyAssert4Business.notNull(redisTemplate);
		T t = redisTemplate.executeInRedis(redisIdDefine, new RedisCallback<T>() {
			
			@Override
			public T doInRedis(Jedis jedis) {
				byte[] bs = jedis.get(key.getBytes());
				if (bs != null && bs.length >0) {
					return SerializationUtil.bytes2Object(bs, clazz);
				}
				return null;
			}
		});
		return t;
	}
	
	public String getRedisValue(RedisTemplate redisTemplate, RedisIdDefine redisIdDefine,final String id) {
		MyAssert4Business.notNull(redisIdDefine);
		MyAssert4Business.notNull(id);
		MyAssert4Business.notNull(redisTemplate);
		String t = redisTemplate.executeInRedis(redisIdDefine, new RedisCallback<String>() {
			@Override
			public String doInRedis(Jedis jedis) {
				byte[] bs = jedis.get(id.toString().getBytes());
				if (bs != null && bs.length >0) {
					try {
	                    return new String(bs,"utf-8");
                    } catch (UnsupportedEncodingException e) {
	                    e.printStackTrace();
                    }
				}
				return null;
			}
		});
		return t;
	}
	
	/**
	 * 通过ids获取一组业务对象数据
	 * @param redidIdDefine
	 * @param t
	 * @param ts
	 * @return
	 */
	public  Collection<T> getObjectsFromRedis(RedisTemplate redisTemplate, final RedisIdDefine redisDefineId,final Class<T> clazz,final Collection<? extends Serializable> keyCollection) {
		MyAssert4Business.notNull(redisDefineId);
		MyAssert4Business.notNull(clazz);
		MyAssert4Business.notNull(redisTemplate);
		if (CollectionUtils.isEmpty(keyCollection)) {
			return null;
		}
		final byte[][] keies = new byte[keyCollection.size()][];
		int index = 0;
		for (Serializable key : keyCollection)  {
			keies[index] = key.toString().getBytes();
			index++;
		}
		List<T> returnTs = redisTemplate.executeInRedis(redisDefineId, new RedisCallback<List<T>>() {
			@Override
			public List<T> doInRedis(Jedis jedis) {
				List<byte[]> os = jedis.mget(keies);
				if (!CollectionUtils.isEmpty(os)) {
					List<T> tt = new ArrayList<T>();
					for (byte[] o : os) {
						tt.add(SerializationUtil.bytes2Object(o, clazz));
					}
					return tt;
				}
				return null;
			}
		});
		return returnTs;
	}
	
	/**
	 * 刷新对象到Redis中
	 * @param redisTemplate
	 * @param redisIdDefine
	 * @param t
	 */
	public void refreshObjectInRedis(RedisTemplate redisTemplate, final RedisIdDefine redisIdDefine, final T t) {
		this.deleteFromRedis(redisTemplate, redisIdDefine, t);
		this.setObjectToRedis(redisTemplate, redisIdDefine, t);
		LOG.debug("Refresh Object In Redis :{}", t.getId());
	}
	
	/**
	 * 设定业务对象到缓冲中
	 * @param redisIdDefine
	 * @param t
	 */
	public  void setObjectToRedis(RedisTemplate redisTemplate, final RedisIdDefine redisIdDefine, final T t) {
		MyAssert4Business.notNull(redisIdDefine);
		MyAssert4Business.notNull(t);
		MyAssert4Business.notNull(redisTemplate);
		redisTemplate.executeInRedis(redisIdDefine, new RedisCallbackWithoutResult() {
			@Override
			public void doInRedisWithoutResult(Jedis jedis) {
				jedis.set(t.getKey().getBytes(), t.getBytes());
			}
		});
	}
	
	/**
	 * 设定对象到Redis缓冲中
	 * @param redisIdDefine
	 * @param key
	 * @param t
	 */
	public void setObjectToRedis(RedisTemplate redisTemplate, final RedisIdDefine redisIdDefine, final String key, final Serializable t) {
		MyAssert4Business.notNull(redisIdDefine);
		MyAssert4Business.hasText(key);
		MyAssert4Business.notNull(t);
		redisTemplate.executeInRedis(redisIdDefine, new RedisCallbackWithoutResult() {
			@Override
			public void doInRedisWithoutResult(Jedis jedis) {
				jedis.set(key.getBytes(), SerializationUtil.object2bytes(t));
			}
		});
	}
	
	/**
	 * 设定业务对象到缓冲中
	 * @param redisIdDefine
	 * @param expiretime单位秒，可使用<code>cn.com.ecointel.framework.cache.CacheConstants</code>
	 * @param t
	 */
	public  void setObjectToRedis(RedisTemplate redisTemplate, final RedisIdDefine redisIdDefine, final T t, final int expiretime) {
		MyAssert4Business.notNull(redisIdDefine);
		MyAssert4Business.notNull(t);
		MyAssert4Business.notNull(redisTemplate);
		redisTemplate.executeInRedis(redisIdDefine, new RedisCallbackWithoutResult() {
			@Override
			public void doInRedisWithoutResult(Jedis jedis) {
				jedis.set(t.getKey().getBytes(), t.getBytes());
				jedis.expire(t.getKey().getBytes(), expiretime);
			}
		});
	}
	
	/**
	 * 通过Key删除Redis Object
	 * @param redisTemplate
	 * @param redisIdDefine
	 * @param key
	 */
	public void deleteFromRedis(RedisTemplate redisTemplate, final RedisIdDefine redisIdDefine,final String key) {
		MyAssert4Business.notNull(redisIdDefine);
		MyAssert4Business.notNull(redisTemplate);
		MyAssert4Business.hasText(key);
		redisTemplate.executeInRedis(redisIdDefine, new RedisCallbackWithoutResult() {
			@Override
			public void doInRedisWithoutResult(Jedis jedis) {
				jedis.del(key.getBytes());
			}
		});
	}
	
	/**
	 * 通过业务对象删除
	 * @param redisTemplate
	 * @param redisIdDefine
	 * @param object
	 */
	public  void deleteFromRedis(RedisTemplate redisTemplate, final RedisIdDefine redisIdDefine,T object) {
		MyAssert4Business.notNull(redisIdDefine);
		MyAssert4Business.notNull(redisTemplate);
		MyAssert4Business.notNull(object);
		deleteFromRedis(redisTemplate, redisIdDefine, object.getKey());
	}
	
	/**
	 * 通过Id删除Redis缓冲
	 * @param redisTemplate
	 * @param redisIdDefine
	 * @param clazz
	 * @param id
	 */
	public void deleteFromRedis(RedisTemplate redisTemplate, final RedisIdDefine redisIdDefine , Class<T> clazz, Serializable id) {
		MyAssert4Business.notNull(redisIdDefine);
		MyAssert4Business.notNull(redisTemplate);
		MyAssert4Business.notNull(clazz);
		MyAssert4Business.notNull(id);
		deleteFromRedis(redisTemplate, redisIdDefine, BusinessObjectKeyBuilder.class2key(clazz, id));
	}
	
	/**
	 * 判断是否在Redis存在
	 * @param redisIdDefine
	 * @param key
	 * @return
	 */
	public boolean existsInRedis(RedisTemplate redisTemplate, final RedisIdDefine redisIdDefine, final String key) {
		MyAssert4Business.hasText(key);
		return redisTemplate.executeInRedis(redisIdDefine, new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(Jedis jedis) {
				return jedis.exists(key);
			}
		});
	}
	
}
