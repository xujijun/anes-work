package com.xjj.anes.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xjj.anes.entity.sys.Menu;

public interface MenuDao {
	public List<Menu> listAll();
	
	public int insert(Menu entity);

	public int update(Menu entity);

	//删除一条记录，及其关联表中的相关记录
	public int deleteMenuAndRel(@Param("id") String id);
	
	public List<Menu> getMenusByUser(String userId);
}
