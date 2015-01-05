package com.xjj.anes.dao;

import org.apache.ibatis.annotations.Param;
/**
 * @author XJJ
 *
 * @param <T>
 */
public interface BaseDao <T> {
	public T selectById(@Param("id") String id);
	
	public int insert(T entity);
	
	public int update(T entity);
	
	int delete(@Param("id") String id);
}
