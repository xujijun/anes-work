package com.xjj.anes.service.common.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjj.anes.bean.common.LoginUser;
import com.xjj.anes.cache.CacheConstants;
import com.xjj.anes.cache.ICache;
import com.xjj.anes.cache.session.CacheHttpSession.CacheSessionHeader;
import com.xjj.anes.service.common.CacheSessionService;
import com.xjj.anes.service.common.SessionService;

@Service
public class CacheSessionServiceImpl implements CacheSessionService {
	//private Log log = LogFactory.getLog(this.getClass().getName());

	@Resource
	private ICache cache;
	@Resource
	private SessionService sessionService;
	
	@Override
	public boolean txLogin(String sessionId, LoginUser loginUser) {
		CacheSessionHeader cacheSessionHeader = new CacheSessionHeader();
		cacheSessionHeader.setIsNew(true);
		cacheSessionHeader.setCreateTime(System.currentTimeMillis());
		cacheSessionHeader.setLastAccessTime(System.currentTimeMillis());
		cacheSessionHeader.setExpireDt(loginUser.getMaxInactiveInterval() * 1000 + cacheSessionHeader.getLastAccessTime());
		
		if (cache.isAvailable()) {
			synchronized (cache) {
				cache.put(CacheConstants.UserSessionHeaderPrefix + sessionId, cacheSessionHeader);
				cache.put(CacheConstants.LoginUser + sessionId, loginUser);
			}
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean txLogout(String sessionId) {
		if (cache.isAvailable()) {
			deleteCacheSession(sessionId);
			return true;
		}
		return false;
	}

	@Override
	public LoginUser getLoginUser(String sessionId) {
		if (cache.isAvailable() && isLogin(sessionId)) {
			return (LoginUser) cache.get(CacheConstants.LoginUser + sessionId);
		}
		return null;
	}

	@Override
	public boolean deleteCacheSession(String sessionId) {
		if (!cache.isAvailable()) {
			return false;
		}
		synchronized (cache){
			cache.delete(CacheConstants.UserSessionHeaderPrefix + sessionId);
			cache.delete(CacheConstants.LoginUser + sessionId);
		}
		return true;
	}

	@Override
	public boolean isLogin(String sessionId) {
		boolean result = false;
		if (cache.isAvailable()) {
			CacheSessionHeader csHeader = getCacheSessionHeader(sessionId);
			if (csHeader != null && csHeader.isAvalable()) {
				result = true;
			} else { //Session已经过时
				deleteCacheSession(sessionId);
			}
		}
		return result;
	}

	@Override
	public CacheSessionHeader getCacheSessionHeader(String sessionId) {
		return (CacheSessionHeader) cache.get(CacheConstants.UserSessionHeaderPrefix + sessionId);
	}
}
