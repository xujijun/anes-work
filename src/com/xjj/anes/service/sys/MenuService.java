package com.xjj.anes.service.sys;

import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.entity.sys.Menu;

public interface MenuService {
	
	public ResultBean listAll();
	
	public ResultBean insert(Menu menu);
}
