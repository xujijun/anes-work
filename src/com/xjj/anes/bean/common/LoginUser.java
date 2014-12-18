package com.xjj.anes.bean.common;

import java.util.Date;

import com.xjj.anes.entity.sys.User;

public class LoginUser
{
	private String id;
	private String code;
	private String name;
	private Date loginDt;
	private String sessionId;
	private String token;
	private String loginLogId;
	private String client;
	private int maxInactiveInterval;
	private User user;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getLoginDt()
	{
		return loginDt;
	}

	public void setLoginDt(Date loginDt)
	{
		this.loginDt = loginDt;
	}

	public String getSessionId()
	{
		return sessionId;
	}

	public void setSessionId(String sessionId)
	{
		this.sessionId = sessionId;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getLoginLogId()
	{
		return loginLogId;
	}

	public void setLoginLogId(String loginLogId)
	{
		this.loginLogId = loginLogId;
	}

	public String getClient()
	{
		return client;
	}

	public void setClient(String client)
	{
		this.client = client;
	}

	public int getMaxInactiveInterval()
	{
		return maxInactiveInterval;
	}

	public void setMaxInactiveInterval(int maxInactiveInterval)
	{
		this.maxInactiveInterval = maxInactiveInterval;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}
}
