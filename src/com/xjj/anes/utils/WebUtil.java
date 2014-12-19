package com.xjj.anes.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	//private static Log log = LogFactory.getLog("WebUtil");

	public static boolean isAjAxRequest(HttpServletRequest request)	{
		boolean isAjaxRequest = false;
		String requestType = request.getHeader("X-Requested-With");
		String accept = request.getHeader("Accept");
		String contentType = request.getHeader("Content-Type");
		if ((requestType != null && "XMLHttpRequest".equalsIgnoreCase(requestType))
				&& ((accept != null && accept.indexOf("application/json") != -1) || (contentType != null && contentType.indexOf("application/json") != -1)))
		{
			isAjaxRequest = true;
		}
		return isAjaxRequest;
	}

	/**
	 * 
	 * 描述: contextPath 例如: http://ipAddr:port/appName
	 * 
	 * @param request
	 * @return
	 */
	public static String getContextPath(final HttpServletRequest request) {
		// request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	/**
	 * 获得请求的IP地址
	 * @param request
	 * @return IP地址
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader(" x-forwarded-for ");
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
			ip = request.getHeader(" Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip))	{
			ip = request.getHeader(" WL-Proxy-Client-IP ");
		}
		if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip))	{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
