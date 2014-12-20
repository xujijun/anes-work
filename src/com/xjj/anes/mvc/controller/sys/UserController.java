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
import com.xjj.anes.entity.sys.User;
import com.xjj.anes.service.sys.UserService;

@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + MenuConstants.SYS + "/user/")
@SysMenu(id = MenuConstants.Sys.SYS_USER, name = "用户管理", parent = MenuConstants.SYS, orderNo = 1, uri = "/views/sys/user/list.html")
public class UserController extends SysBaseController {
	@Resource
	protected UserService userService;
	
	@RequestMapping(value = "getUser")
	public ResultBean getUser(HttpServletRequest request){
		ResultBean rb = new ResultBean(true, "获取用户信息成功！");
		rb.setData(super.getLoginUser(request));
		if (rb.getData() == null) {
			rb.setSuccess(false);
			rb.setMessage("取不到对应的用户信息");
		}
		return rb;
	}
	
	@RequestMapping(value = "search")
	@PermissionChecking(id = MenuConstants.Sys.SYS_USER + "-search", name = "搜索")
	public ResultBean search(HttpServletRequest request, Pager<User> pager, User user){
		return userService.search(pager, user);
	}
	
	@RequestMapping(value = "insert")
	@PermissionChecking(id = MenuConstants.Sys.SYS_USER + "-insert", name = "新增")
	public ResultBean insert(){
	//public ResultBean search(HttpServletRequest request, Pager<User> pager, UserVo userVo){
		return null;
	}
	
	//修改密码
	@RequestMapping(value = "modifypwd")
	public ResultBean modifypwd(HttpServletRequest request, User user) {
		user.setId(getLoginUser(request).getUser().getId());
		return userService.modifypwd(user);
	}
	
	//TODO Other methods
}
