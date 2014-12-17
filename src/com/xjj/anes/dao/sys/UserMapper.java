package com.xjj.anes.dao.sys;

import org.apache.ibatis.annotations.Param;

import com.xjj.anes.entity.sys.User;

public interface UserMapper {
	public int insert(User entity);

	public int update(User entity);
	
	// id是否已经存在：0：不存在；>0：存在
	public int exists(@Param("id") String id);
}
