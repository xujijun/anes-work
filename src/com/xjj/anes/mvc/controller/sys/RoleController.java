package com.xjj.anes.mvc.controller.sys;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.constants.MenuConstants;

@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + MenuConstants.SYS + "/role/")
@SysMenu(id = MenuConstants.Sys.SYS_ROLE, name = "角色管理", parent = MenuConstants.SYS, orderNo = 2, uri = "/views/sys/role/list.html")
public class RoleController extends SysBaseController {
	
	@RequestMapping(value = "search")
	@PermissionChecking(id = MenuConstants.Sys.SYS_ROLE + "-search", name = "搜索")
	public ResultBean search(){
	//public ResultBean search(HttpServletRequest request, Pager<User> pager, UserVo userVo){
		return null;
	}
}
