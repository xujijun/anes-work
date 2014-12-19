package com.xjj.anes.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter implements Filter {

	@Override
	public void destroy(){

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)resp;
        //HttpServletRequest request = (HttpServletRequest) req;
        //System.out.println("页面过滤加no cache头：" + request.getRequestURI());

        response.setDateHeader("Expires", -1);	//设置页面失效时间
        response.setHeader("Cache_Control", "no-cache");	//支持HTTP1.1
        response.setHeader("Pragma", "no-cache");	//支持HTTP1.0
        chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException	{

	}

}
