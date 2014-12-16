package com.xjj.anes.dao.sys;

import org.apache.ibatis.annotations.Param;

import com.xjj.anes.entity.sys.Menu;

public interface PermissionMapper {
	public int insert(Menu entity);

	public int update(Menu entity);
	
	//删除一条记录，及其关联表中的相关记录
	public int deletePermissionAndRel(@Param("id") String id);
}
