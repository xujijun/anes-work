package com.xjj.anes.constants;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 定义系统管理模块的常量
 */
public class SysConstants {
	public static String ADMINISTRATOR = "administrator";

	/** 缺省角色 */
	public static class DefaultRole	{
		public final static String superAdmin = "super_admin"; //超级管理员
		public final static String systemAdmin = "system_admin"; //普通系统管理员
	}

	public static Map<String, String> getDefaultRoleMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(DefaultRole.superAdmin, "系统超级管理员");
		map.put(DefaultRole.systemAdmin, "系统管理员");
		return map;
	}

	public static class SystemSetup	{
		public final static String lockUserMinutes = "LUMS";
		public final static String lockUserMinutesForPassword = "LUMSFP";
		public final static String tryTimesForLogin = "TTFL";
		public final static String tryTimesForLoginPassword = "TTFLP";
	}

	public static class CacheRI	{
		public final static String CACHE_CAN_NOT_FIND = "缓存中找不到此值";
	}
}
