package com.xjj.anes.cache;

import java.util.Date;

import com.xjj.anes.service.common.CoreApplicationContext;

/**
 * Cache 管理器
 * @author XJJ
 *
 */
public class CacheManager {
	private static CacheManager instance = null;
	private static ICache cache;


	private CacheManager() {
		cache = CoreApplicationContext.getApplicationContext().getBean(ICache.class);
	}

	public static synchronized CacheManager getInstance() {
		if (instance == null) {
			instance = new CacheManager();
		}
		return instance;
	}

	public void setCache(ICache value) {
		cache = value;
	}

	public boolean keyExists(String key) {
		return cache.keyExists(key);
	}

	public boolean put(String key, Object value) {
		boolean result = false;
		synchronized(cache){
			result = cache.put(key, value);
		}
		return result;
	}

	public boolean put(String key, Object value, Date expireDt) {
		boolean result = false;
		synchronized(cache){
			result = cache.put(key, value, expireDt);
		}
		return result;
	}

	public Object get(String key) {
		return cache.get(key);
	}

	public boolean delete(String key) {
		boolean result = false;
		synchronized(cache){
			result = cache.delete(key);
		}
		
		return result;
	}
	
	public boolean isAvailable(){
		return cache.isAvailable();
	}
}
