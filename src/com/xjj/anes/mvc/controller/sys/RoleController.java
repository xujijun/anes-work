package com.xjj.anes.mvc.controller.sys;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.bean.sys.RoleVo;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.constants.MenuConstants;
import com.xjj.anes.constants.SysConstants;
import com.xjj.anes.entity.sys.Role;
import com.xjj.anes.service.sys.RoleService;
import com.xjj.anes.utils.MyUtil;

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
	
	@RequestMapping(value = "/insert")
	@PermissionChecking(id = MenuConstants.Sys.SYS_ROLE + "-insert", name = "新增")
	public ResultBean insert(HttpServletRequest request, Role role){
		role.setId(MyUtil.generateUUID());
		role.setCreator(getUserCode(request));
		role.setCreateDt(new Date());
		role.setStatus(CommonConstants.Status.ACTIVE);
		return roleService.insert(role);
	}
	
	@RequestMapping(value = "/update")
	@PermissionChecking(id = MenuConstants.Sys.SYS_ROLE + "-update", name = "修改")
	public ResultBean update(HttpServletRequest request, Role role) {
		role.setUpdater(getUserCode(request));
		role.setUpdateDt(new Date());
		return roleService.update(role);
	}
	
	@RequestMapping(value = "/delete")
	@PermissionChecking(id = MenuConstants.Sys.SYS_ROLE + "-delete", name = "删除")
	public ResultBean delete(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		return roleService.delete(id);
	}
	
	@RequestMapping(value = "/selectById")
	public ResultBean selectById(@RequestParam(value = "id", required = true) String id) {
		return roleService.selectById(id);
	}
	
	@RequestMapping(value = "/getRoleMenus")
	@PermissionChecking(id = MenuConstants.Sys.SYS_ROLE + "-getRoleMenus", name = "分配权限")
	public ResultBean getRoleMenus(HttpServletRequest request, Role role) {
		return roleService.getRoleMenus(role.getId());
	}
	
	@RequestMapping(value = "/saveRoleMenus")
	@PermissionChecking(id = MenuConstants.Sys.SYS_ROLE + "-saveRoleMenus", name = "保存权限")
	public ResultBean saveRoleMenus(HttpServletRequest request, RoleVo roleVo)	{
		ResultBean rb = null;
		if (!SysConstants.DefaultRole.superAdmin.equals(roleVo.getId())) {
			rb = roleService.saveRoleMenus(roleVo);
		} else {
			rb = new ResultBean(false);
			rb.setMessage("不能修改超级管理员角色");
		}
		return rb;
	}
}
