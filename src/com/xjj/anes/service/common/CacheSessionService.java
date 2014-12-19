package com.xjj.anes.service.common;

import com.xjj.anes.bean.common.LoginUser;
import com.xjj.anes.cache.session.CacheHttpSession.CacheSessionHeader;

public interface CacheSessionService {
	public boolean txLogin(String sessionId, LoginUser loginUser);

	public boolean txLogout(String sessionId);

	public LoginUser getLoginUser(String sessionId);
	
	public boolean deleteCacheSession(String sessionId);
	
	public boolean isLogin(String sessionId);
	
	public CacheSessionHeader getCacheSessionHeader(String sessionId);

}
