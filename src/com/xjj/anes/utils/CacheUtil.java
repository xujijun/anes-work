package com.xjj.anes.utils;

import java.util.Map;

import com.xjj.anes.cache.CacheConstants;
import com.xjj.anes.cache.CacheManager;
import com.xjj.anes.constants.DictConstants;

public class CacheUtil {
	/**
	 * 根据clsCode获取code-name MAP
	 * @param clsCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getSecondDictMap(final String clsCode){
		final Map<String, Map<String, String>> firstMap = (Map<String, Map<String, String>>) CacheManager.getInstance().get(CacheConstants.DICT);
		final Map<String, String> secondMap = firstMap.get(clsCode);

		return secondMap;
	}
	
	/**
	 * 状态(有效/无效/已删除)
	 * 描述: 根据code获取name值
	 * @param code
	 * @return
	 */
	public static String getStatusName(final String code){
		final Map<String, String> statusMap = CacheUtil.getSecondDictMap(DictConstants.ClsCode.STATUS);
		
		return statusMap.get(code);
	}
	
	/**
	 * 用户状态(正常/停用)
	 * 描述: 根据code获取name值
	 * @param code
	 * @return
	 */
	public static String getUserStatusName(final String code){
		final Map<String, String> statusMap = CacheUtil.getSecondDictMap(DictConstants.ClsCode.USER_STATUS);
		
		return statusMap.get(code);
	}
	
	/**
	 * 获取用户类型名称
	 * 描述: 根据code获取name值
	 * @param code
	 * @return
	 */
	public static String getUserTypeName(final String code){
		final Map<String, String> statusMap = CacheUtil.getSecondDictMap(DictConstants.ClsCode.USER_TYPE);
		
		return statusMap.get(code);
	}
}
