package com.itaoniu.testserver.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * 基于redis的缓存操作 （疑问：保存对象时，是直接序列化缓存对象快 ，还是toJSON后缓存字符串快）
 * 
 * @author yzhu
 * 
 */
public class RedisUtils {
	private static Log log = LogFactory.getLog(RedisUtils.class);
	public static JedisPool pool;

	private static int maxActive = 1024; // 最大连接数
	private static int maxIdle = 10; // 最大空闲连接数
	private static int maxWait = 2000; // 获取连接最大等待时间
	private static boolean testOnBorrow = true; // 是否进行连接检验

	private static String redisIP = "127.0.0.1";
	private static int port = 6378;

	// 从连接池获取有效的连接
	public static Jedis getConnection() {
		if (pool == null)

			initPool();
		return pool.getResource();
	}

	// 初始化连接池
	private static void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		// config.setMaxActive(maxActive);
		config.setMaxIdle(maxIdle);
		// config.setMaxWait(maxWait);
		config.setTestOnBorrow(testOnBorrow);
		// config.setTestOnReturn(testOnReturn);是否对返回值进行检验
		try {
			pool = new JedisPool(config, redisIP, port, 1000000, "itaoniu2011");
		} catch (Exception e) {
			log.error("初始化Jedis连接池异常", e);
		}
	}

	/**
	 * 以键-值对方式缓存
	 * 
	 * @param key
	 *            键名
	 * @param value
	 *            值
	 * @param exporeSeconds
	 *            过期时间（秒）
	 */
	public static <T> void put(String key, T value, int exporeSeconds) {
		Jedis jds = getConnection();
		if(value instanceof String){
			jds.set(key, (String)value);
		
		}else{
			jds.set(key,JSON.toJSONString(value));
		}
		
		if(exporeSeconds>0){
			jds.expire(key, exporeSeconds);
		}
		pool.returnResource(jds);
	}
	


	/**
	 * 以键-值方式保存，存在则覆盖
	 * 
	 * @param key
	 * @param value
	 */
	public static <T> void put(String key, T value) {
		put(key,value,0);
	}
	
	/**
	 * 根据键获取字符串
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T  get(String key,Class<T> clazz) {
		Jedis jds = getConnection();
		String value = jds.get(key);
		pool.returnResource(jds);
		return JSON.parseObject(value, clazz);
	}
	
	/**
	 * 根据键获取字符串
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T get(String key,	TypeReference<T> type) {
		Jedis jds = getConnection();
		String value = jds.get(key);
		pool.returnResource(jds);
		return JSON.parseObject(value, type);
	}
	
	/**
	 * 根据键获取字符串
	 * 
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		Jedis jds = getConnection();
		String value = jds.get(key);
		pool.returnResource(jds);
		return value;
	}
	
	

	/**
	 * 根据keys(String)删除
	 * 
	 * @param keys
	 */
	public static void delete(String keys) {
		Jedis jds = getConnection();
		jds.del(keys);
		pool.returnResource(jds);
	}

	/**
	 * 根据keys(byte[])删除
	 * 
	 * @param keys
	 */
	public static void delete(String[] keys) {
		Jedis jds = getConnection();
		jds.del(keys);
		pool.returnResource(jds);
	}
	
	
	
	/**
	 * 更新时间
	 * @param key
	 * @param exporeSeconds
	 */
	public static void expire(String key,int exporeSeconds){
		Jedis jds = getConnection();
		if(exporeSeconds>0){
			jds.expire(key, exporeSeconds);
		}
		pool.returnResource(jds);
	}

	/**
	 * 清空所有缓存
	 */
	public static void flushAll() {
		Jedis jds = getConnection();
		jds.flushAll();
		pool.returnResource(jds);
	}
	
}
