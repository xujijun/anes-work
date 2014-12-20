package com.xjj.anes.mvc.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.constants.MenuConstants;
import com.xjj.anes.entity.sys.Role;
import com.xjj.anes.service.sys.RoleService;

@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + MenuConstants.SYS + "/role/")
@SysMenu(id = MenuConstants.Sys.SYS_ROLE, name = "角色管理", parent = MenuConstants.SYS, orderNo = 2, uri = "/views/sys/role/list.html")
public class RoleController extends SysBaseController {
	@Resource
	RoleService roleService;
	
	@RequestMapping(value = "search")
	@PermissionChecking(id = MenuConstants.Sys.SYS_ROLE + "-search", name = "搜索")
	public ResultBean search(HttpServletRequest request, Pager<Role> pager, Role role) {
		return roleService.search(pager, role);
	}
	
	
}
