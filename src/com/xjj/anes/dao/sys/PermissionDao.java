package com.xjj.anes.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xjj.anes.entity.sys.Permission;

public interface PermissionDao {
	public List<Permission> listAll();
	
	public int insert(Permission entity);

	public int update(Permission entity);
	
	//删除一条记录，及其关联表中的相关记录
	public int deletePermissionAndRel(@Param("id") String id);
}
