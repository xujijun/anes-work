package com.xjj.anes.mvc.controller.sys;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.constants.MenuConstants;
import com.xjj.anes.entity.sys.User;
import com.xjj.anes.service.sys.UserService;
import com.xjj.anes.utils.MyUtil;

@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + MenuConstants.SYS + "/user/")
@SysMenu(id = MenuConstants.Sys.SYS_USER, name = "用户管理", parent = MenuConstants.SYS, orderNo = 1, uri = "/views/sys/user/list.html")
public class UserController extends SysBaseController {
	@Resource
	protected UserService userService;
	
	//登录后调用获取登录用户信息
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
	
	/**
	 * 根据id获取一个用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "select")
	public ResultBean view(@RequestParam(value = "id", required = true) String id)
	{
		return userService.select(id);
	}
	
	@RequestMapping(value = "search")
	@PermissionChecking(id = MenuConstants.Sys.SYS_USER + "-search", name = "搜索")
	public ResultBean search(HttpServletRequest request, Pager<User> pager, User user){
		return userService.search(pager, user);
	}
	
	@RequestMapping(value = "insert")
	@PermissionChecking(id = MenuConstants.Sys.SYS_USER + "-insert", name = "新增")
	public ResultBean insert(HttpServletRequest request, User user){
		User creator = getLoginUser(request).getUser();
		user.setId(MyUtil.generateUUID());
		user.setCreateDt(new Date());
		user.setCreator(creator.getName());
		return userService.insert(user);
	}
	
	/**
	 * 检查登录名是否可用 true可用； false不可用
	 * 
	 * @param code
	 * @param oldCode
	 * @return
	 */
	@RequestMapping(value = "checkCode")
	public boolean checkCode(@RequestParam(value = "code", required = true, defaultValue = "") String code){
		return userService.checkCode(code);
	}
	
	//修改密码
	@RequestMapping(value = "modifypwd")
	public ResultBean modifypwd(HttpServletRequest request, User user) {
		user.setId(getLoginUser(request).getUser().getId());
		return userService.modifypwd(user);
	}
	
	/**
	 * 修改
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@PermissionChecking(id = MenuConstants.Sys.SYS_USER + "-update", name = "修改")
	public ResultBean update(HttpServletRequest request, User user)	{
		String updater = getUserName(request);
		user.setUpdateDt(new Date());
		user.setUpdater(updater);
		return userService.update(user);
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete")
	@PermissionChecking(id = MenuConstants.Sys.SYS_USER + "-delete", name = "删除")
	public ResultBean delete(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		String deleter = getUserName(request);
		return userService.delete(id, deleter);
	}
	
	/**
	 * 重置密码
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "resetPwd")
	@PermissionChecking(id = MenuConstants.Sys.SYS_USER + "-resetPwd", name = "重置密码")
	public ResultBean resetPwd(HttpServletRequest request, @RequestParam(value = "id", required = true) String id, @RequestParam(value = "pwd", required = true) String pwd) {
		return userService.resetPwd(id, pwd);
	}
	
}
