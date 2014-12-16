package com.xjj.anes.service.sys;

import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.entity.sys.Menu;

public interface MenuService {
	
	public ResultBean listAll();
	
	public ResultBean insert(Menu menu);
	
	public ResultBean update(Menu menu);
	
	//删除一条记录，及其关联表中的相关记录
	public ResultBean deleteMenuAndRel(String id);
}
