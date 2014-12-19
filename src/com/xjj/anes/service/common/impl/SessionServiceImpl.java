package com.xjj.anes.service.common.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.xjj.anes.cache.CacheConstants;
import com.xjj.anes.service.common.SessionService;

@Service
public class SessionServiceImpl implements SessionService {
	// private Log log = LogFactory.getLog(this.getClass().getName());
	private String domain="xjj.com";

	public void setDomain(String value) {
		domain = value;
	}

	@Override
	public String getDomain() {
		return domain;
	}

	@Override
	public String getSessionId(HttpServletRequest request) {
		return getSessionId(request, CacheConstants.DefaultSessionIdName);
	}

	@Override
	public String getSessionId(HttpServletRequest request, String cookieName) {
		String sessionId = request.getParameter(cookieName);
		if (sessionId == null || "".equals(sessionId)) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(cookieName)) {
						sessionId = cookie.getValue();
						break;
					}
				}
			}
		}
		if (sessionId == null || "".equals(sessionId)) {
			sessionId = request.getHeader(cookieName);
		}
		return sessionId;
	}

	@Override
	public String getCookies(HttpServletRequest request) {
		String cookie = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if (cookie != "") {
				cookie += ";";
			}
			cookie += c.getName() + "=" + c.getValue();
		}
		return cookie;
	}

	@Override
	public void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		addCookie(request, response, name, value, 7 * 24 * 60 * 60);
	}

	@Override
	public void addCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int seconds) {
		Cookie cookie = new Cookie(name, value);
		if (request.getRequestURL().indexOf(domain) != -1) {
			cookie.setDomain(domain);
		}
		cookie.setPath("/");
		cookie.setMaxAge(seconds);
		response.addCookie(cookie);
		if (request.getRequestURL().indexOf(domain) == -1 && CacheConstants.DefaultSessionIdName.equals(name)) {
			cookie = new Cookie(name, value);
			cookie.setDomain(domain);
			cookie.setPath("/");
			cookie.setMaxAge(seconds);
			response.addCookie(cookie);
		}
	}

	@Override
	public Cookie findCookie(HttpServletRequest request, String name) {
		if (request != null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(name)) {
						return cookie;
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean removeCookie(HttpServletRequest request,	HttpServletResponse response, String name) {
		Cookie cookie = new Cookie(name, "");
		cookie.setPath("/");
		cookie.setDomain(domain);
		cookie.setMaxAge(1);
		response.addCookie(cookie);
		return true;
	}

}
