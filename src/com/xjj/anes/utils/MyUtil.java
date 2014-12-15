package com.xjj.anes.utils;

import java.util.UUID;

public class MyUtil {
	/**
	 * 生成一个UUID
	 * @return 一个去除了“-”号的32位UUID
	 */
	public static String generateUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
