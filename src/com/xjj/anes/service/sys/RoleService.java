package com.xjj.anes.service.sys;

import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.bean.sys.RoleVo;
import com.xjj.anes.entity.sys.Role;

public interface RoleService {

	//获取角色列表，用户新增或修改的时候下拉选择
	ResultBean getRoleList();

	ResultBean search(Pager<Role> pager, Role role);

	ResultBean insert(Role role);

	ResultBean update(Role role);

	ResultBean delete(String id);

	ResultBean getRoleMenus(String id);

	ResultBean selectById(String id);

	//保存角色权限
	ResultBean saveRoleMenus(RoleVo roleVo);

}
