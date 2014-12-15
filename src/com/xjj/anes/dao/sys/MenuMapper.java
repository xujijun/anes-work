package com.xjj.anes.dao.sys;

import java.util.List;

import com.xjj.anes.entity.sys.Menu;

public interface MenuMapper {
	public List<Menu> listAll();
	
	public int insert(Menu entity);

}
