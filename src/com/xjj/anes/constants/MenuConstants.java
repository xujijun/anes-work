package com.xjj.anes.constants;

/**
 * 定义有关菜单的常量
 * 
 */

public class MenuConstants {
	/** 系统管理 */
	public final static String SYS = "sys";

	public static class Sys	{
		/** 菜单管理 */
		public final static String SYS_MENU = "sys-menu";
		/** 用户管理 */
		public final static String SYS_USER = "sys-user";
		/** 角色管理 */
		public final static String SYS_ROLE = "sys-role";
		/** 字典管理 */
		public final static String SYS_DICT = "sys-dict";
		/** 登录日志 */
		public final static String SYS_LOGINLOG = "sys-loginLog";
		/** 操作日志 */
		public final static String SYS_TRAILLOG = "sys-trailLog";
	}
	
	/** 工作量管理 */
	public final static String ANES = "anes";

	public static class Anes	{
		/** 医生管理 */
		public final static String ANES_ANESTHETIST = "anes-anesthetist";
		/** 工作量管理 */
		public final static String ANES_WORK = "anes-work";
	}

}
