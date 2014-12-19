package com.xjj.anes.service.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionService {
	
	public String getDomain();
	
	public String getSessionId(HttpServletRequest request);

	public String getSessionId(HttpServletRequest request, String cookieName);

	public String getCookies(HttpServletRequest request);

	public void addCookie(HttpServletRequest request,HttpServletResponse response, String name, String value);
	
	public void addCookie(HttpServletRequest request,HttpServletResponse response, String name, String value,int seconds);

	public Cookie findCookie(HttpServletRequest request, String name);

	public boolean removeCookie(HttpServletRequest request, HttpServletResponse response, String name);
}
