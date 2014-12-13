package com.xjj.anes.bean.common;

import java.io.Serializable;

public class ResultBean implements Serializable
{
	private static final long serialVersionUID = 3475946724048234071L;
	private boolean success = true;
	private String messageCode = "";
	private String message = "";
	private Object data = "";

	public ResultBean(boolean sucess)
	{
		this.success = sucess;
	}

	public ResultBean(boolean sucess, String message)
	{
		this.success = sucess;
		this.message = message;
	}

	public boolean getSuccess()
	{
		return success;
	}

	public void setSuccess(boolean value)
	{
		success = value;
	}

	public String getMessageCode()
	{
		return messageCode;
	}

	public void setMessageCode(String value)
	{
		messageCode = value;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String value)
	{
		message = value;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object value)
	{
		data = value;
	}

	@Override
	public String toString()
	{
		return "ResultBean [success=" + success + ", messageCode=" + messageCode + ", message=" + message + ", data=" + data + "]";
	}
}
