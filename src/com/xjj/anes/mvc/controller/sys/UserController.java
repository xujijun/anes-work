package com.xjj.anes.mvc.controller.sys;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.constants.MenuConstants;

@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + MenuConstants.SYS + "/user/")
@SysMenu(id = MenuConstants.Sys.SYS_USER, name = "用户管理", parent = MenuConstants.SYS, orderNo = 1, uri = "/views/sys/user/list.html")
public class UserController extends SysBaseController {

	@RequestMapping(value = "getUser")
	public ResultBean getUser(HttpServletRequest request){
		ResultBean rb = new ResultBean(true, "获取用户信息成功！");
		/*rb.setData(super.getLoginUser(request));
		if (rb.getData() == null)
		{
			rb.setSuccess(false);
			rb.setMessage("取不到对应的用户信息");
		}*/
		return rb;
	}
	
	@RequestMapping(value = "search")
	@PermissionChecking(id = MenuConstants.Sys.SYS_USER + "-search", name = "搜索")
	public ResultBean search(){
	//public ResultBean search(HttpServletRequest request, Pager<User> pager, UserVo userVo){
		return null;
	}
	//TODO Other methods
}
