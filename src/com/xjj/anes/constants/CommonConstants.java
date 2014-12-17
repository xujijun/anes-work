package com.xjj.anes.constants;

/**
 * 定义系统通用的常量
 */
public class CommonConstants
{

	/** 初始密码 */
	public static final String INITIAL_PASSWORD = "12345678";

	public static class UriPrefix
	{
		public final static String PC = "/pc/";
		public final static String API = "/api/";
	}

	public static class Status
	{
		public final static String ACTIVE = "ON";
		public final static String INACTIVE = "OFF";
		public final static String DELETE = "DEL";
	}

	public static class Boolean
	{
		public final static String TRUE = "TRUE";
		public final static String FALSE = "FALSE";
	}

	public static class UserType
	{
		public final static String ADMIN = "ADMIN";// 系统管理员
		public final static String COMMON = "COM";// 普通用户
	}
}
