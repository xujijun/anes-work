package com.xjj.anes.service.sys;

import com.xjj.anes.bean.common.ResultBean;

public interface RoleService {

	//获取角色列表，用户新增或修改的时候下拉选择
	ResultBean getRoleList();

}
