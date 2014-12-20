package com.xjj.anes.service.sys.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.bean.common.LoginUser;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.cache.CacheConstants;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.constants.SysConstants;
import com.xjj.anes.constants.WebConstant;
import com.xjj.anes.dao.sys.MenuDao;
import com.xjj.anes.dao.sys.PermissionDao;
import com.xjj.anes.dao.sys.RoleDao;
import com.xjj.anes.dao.sys.UserDao;
import com.xjj.anes.entity.sys.Menu;
import com.xjj.anes.entity.sys.Permission;
import com.xjj.anes.entity.sys.Role;
import com.xjj.anes.entity.sys.RoleMenu;
import com.xjj.anes.entity.sys.RolePermission;
import com.xjj.anes.entity.sys.User;
import com.xjj.anes.service.common.CacheSessionService;
import com.xjj.anes.service.common.SessionService;
import com.xjj.anes.service.sys.SysService;
import com.xjj.anes.utils.AuthxUtil;
import com.xjj.anes.utils.ClassUtil;
import com.xjj.anes.utils.MyUtil;

@Service
public class SysServiceImpl implements SysService {
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Resource
	private MenuDao menuDao;
	@Resource
	private PermissionDao permissionDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private UserDao userDao;
	@Resource
	private SessionService sessionService;
	@Resource
	private CacheSessionService cacheSessionService;

	@Override
	public ResultBean txLogin(HttpServletRequest request,
			HttpServletResponse response, String code, String password,
			String client, String verificationCode, boolean rememberMe) {
		
		ResultBean rb = new ResultBean();
		
		if (StringUtils.isEmpty(code) || StringUtils.isEmpty(password))	{
			rb.setSuccess(false);
			rb.setMessage("用户名或密码不能为空。");
			return rb;
		}
		
		
		User user = userDao.getUserByCode(code);
		if (user == null) {
			rb.setSuccess(false);
			rb.setMessage("用户名不正确或用户处于暂停状态。");
			return rb;
		}
		
		if (user.getUnlockDt() != null)	{
			rb.setSuccess(false);
			rb.setMessage("用户处于锁定状态，请稍后再试。");
			return rb;
		}
		
		if (!password.equals(user.getPassword())) {
			rb.setSuccess(false);
			rb.setMessage("密码不正确。");
		}
		
		//拼装LoginUser
		String sessionId = AuthxUtil.encryptByMd5(UUID.randomUUID() + request.getSession().getId());
		LoginUser loginUser = new LoginUser();
		loginUser.setId(user.getId());
		loginUser.setCode(code);
		loginUser.setName(user.getName());
		loginUser.setLoginDt(new Date());
		loginUser.setMaxInactiveInterval(request.getSession().getMaxInactiveInterval());
		loginUser.setSessionId(sessionId);
		loginUser.setToken(CacheConstants.DefaultSessionIdName + "=" + sessionId);
		loginUser.setClient(client);
		
		user.setPermissionIdSet(userDao.getUserPermissionIds(loginUser.getId()));
		user.setMenuList(menuDao.getMenusByUser(loginUser.getId()));
		loginUser.setUser(user);
		
		//保存用户登录信息到缓存中
		cacheSessionService.txLogin(loginUser.getSessionId(), loginUser);
		
		//设置Cookie
		request.getSession(true).setAttribute(CacheConstants.DefaultSessionIdName, loginUser.getSessionId());
		sessionService.addCookie(request, response, CacheConstants.DefaultSessionIdName, loginUser.getSessionId());// web使用
		response.addHeader(CacheConstants.DefaultSessionIdName, loginUser.getSessionId());// cs使用
		
		if (rememberMe)	{
			List<String> list = new ArrayList<String>();
			list.add(code);
			list.add(password);
			sessionService.addCookie(request, response, WebConstant.RememberMeCookieName, AuthxUtil.encryptRememberMe(list));// web使用
		} else {
			sessionService.removeCookie(request, response, WebConstant.RememberMeCookieName);
		}
		
		rb.setData(loginUser);
		return rb;
	}
	
	@Override
	public ResultBean txLogout(HttpServletRequest request) {
		String sessionId = sessionService.getSessionId(request);
		if (sessionId != null) {
			LoginUser loginUser = cacheSessionService.getLoginUser(sessionId);
			if (loginUser != null) {
				cacheSessionService.deleteCacheSession(sessionId);
			}
			request.getSession().invalidate();
		}
		return new ResultBean();
	}
	
	@Override
	public boolean txScanAnnotations() {
		boolean result = true;
		String pkg = "com.xjj.anes.mvc.controller";
		String userName = "system";
		Date dtNow = new Date();
		Set<Class<?>> allClses = null;
		
		Map<String, Menu> menuMap = new HashMap<String, Menu>();
		List<Menu> menuList = (List<Menu>) menuDao.listAll();
		Set<String> menuIdSet = new HashSet<String>();
		
		for (Menu m : menuList)	{
			menuMap.put(m.getId(), m);
		}
		
		allClses = ClassUtil.getClasses(pkg);
		String menuId = null;
		SysMenu sysMenu = null;
		Menu menu = null;
		RequestMapping menuRm = null;
		
		Set<String> permissionIdSet = new HashSet<String>();
		PermissionChecking permissionChecking = null;
		Permission permission = null;
		Method[] methods = null;
		Map<String, Permission> permissionMap = new HashMap<String, Permission>();
		List<Permission> permissionList = permissionDao.listAll();
		for (Permission p : permissionList)	{
			permissionMap.put(p.getId(), p);
		}
		
		for (Class<?> cls : allClses){
			log.info("Scan Menu and Permission annotations at class:" + cls);
			
			//只扫描@Controller
			if(AnnotationUtils.findAnnotation(cls, Controller.class)==null){
				continue;
			}
			
			//开始处理Menu
			sysMenu = AnnotationUtils.findAnnotation(cls, SysMenu.class);
			permissionChecking = AnnotationUtils.findAnnotation(cls, PermissionChecking.class);
			if (sysMenu == null) {
				//如果没有Menu却有Permission，提示错误
				if (permissionChecking != null)	{
					log.error("There is Permission but no Menu annotation in " + cls.getName());
					throw new RuntimeException("There is Permission but no  Menu annotation in " + cls.getName());
				}
				continue;
			}
			
			//Test Only
			/*if(sysMenu!=null){
				System.out.println(sysMenu.name());
			}*/

			menu = new Menu();
			menuId = sysMenu.id();
			if (StringUtils.isEmpty(menuId))
			{
				throw new RuntimeException("Loss id of Menu annotation at " + cls.getName());
			}
			menu.setId(menuId);
			if (StringUtils.isEmpty(sysMenu.name()) && !StringUtils.isEmpty(sysMenu.id()))
			{
				throw new RuntimeException("Loss name of Menu annotation at " + cls.getName());
			}
			menu.setName(sysMenu.name());
			menu.setParentId(sysMenu.parent());
			menu.setOrderNo(sysMenu.orderNo());
			menu.setUri(sysMenu.uri());
			menu.setStatus(CommonConstants.Status.ACTIVE);
			if (menuMap.containsKey(menuId)) { //Menu表已经存在该Menu ID，则update一下
				//menu = menuMap.get(menuId);
				menu.setUpdater(userName);
				menu.setUpdateDt(dtNow);
				menuDao.update(menu);
			} else{ //Menu表不存在该Menu ID，则添加到Menu表中
				menu.setCreator(userName);
				menu.setCreateDt(dtNow);
				menuDao.insert(menu);
			}
			menuMap.put(menu.getId(), menu);
			menuIdSet.add(menuId);
			
			//处理Permissions
			menuRm = AnnotationUtils.findAnnotation(cls, RequestMapping.class);
			if(menuRm==null){ //如果该类没有RequestMapping，就没必要继续扫描Permission
				continue;
			}
			
			methods = cls.getDeclaredMethods();
			for (Method method : methods){
				permissionChecking = AnnotationUtils.findAnnotation(method, PermissionChecking.class);
				if(permissionChecking==null){
					continue;
				}
				//Test only
				//System.out.println(permissionChecking.name());
				
				//检查是否有重复的Permission ID
				if(permissionIdSet.contains(permissionChecking.id())) {
					result=false;
					permission = permissionMap.get(permissionChecking.id());
					throw new RuntimeException("Duplicate id[" + permissionChecking.id() + "] between method[" + method.getName() + "] of class[" + cls.getName() + "] and another method["
							+ permission.getMethod() + "] of class[" + permission.getCls() + "]");
				}
				
				if(permissionMap.containsKey(permissionChecking.id())) {
					permission = permissionMap.get(permissionChecking.id());
					permission.setMenuId(menuId);
					permission.setName(permissionChecking.name());
					permission.setCls(cls.getName());
					permission.setMethod(method.getName());
					permissionDao.update(permission);
				} else {
					permission = new Permission();
					permission.setId(permissionChecking.id());
					permission.setMenuId(menuId);
					permission.setName(permissionChecking.name());
					permission.setCls(cls.getName());
					permission.setMethod(method.getName());
					permissionDao.insert(permission);
				}
				permissionMap.put(permission.getId(), permission);
				permissionIdSet.add(permission.getId());
			}
		}
		
		//删除扫描列表中没有的Menu及其关联表
		for (String id : menuMap.keySet()) {
			if (!menuIdSet.contains(id)) {
				log.info("Delete Menu[" + id + "] " + menuMap.get(id));
				menuDao.deleteMenuAndRel(id);
			}
		}
		
		for(String id : permissionMap.keySet()){
			if(!permissionIdSet.contains(id)){
				log.info("Delete Permission[" + id + "] " + permissionMap.get(id));
				permissionDao.deletePermissionAndRel(id);
			}
		}
		
		log.info("Menus and Permissions scanned.");
		return result;
	}

	@Override
	public void txCreateDefaultAccount() {
		String creator = "system";
		Date dtNow = new Date();
		
		//获取所有Menu
		Set<String> menuIdSet = new HashSet<String>();
		List<Menu> menuList = (List<Menu>) menuDao.listAll();
		for (Menu m : menuList)	{
			menuIdSet.add(m.getId());
		}
		
		//获取所有Permission
		Set<String> permissionIdSet = new HashSet<String>();
		List<Permission> permissionList = permissionDao.listAll();
		for (Permission p : permissionList)	{
			permissionIdSet.add(p.getId());
		}
		
		String roleId = SysConstants.DefaultRole.superAdmin;
		Set<String> assignedMenuIds = null; //上次已经分配给superAdmin的MenuIds
		Set<String> assignedPermissionId = null; //上次已经分配给superAdmin的PermissionIds
		
		//超级管理员角色是否已经存在
		if(roleDao.exists(SysConstants.DefaultRole.superAdmin) >0 ){
			assignedMenuIds = roleDao.selectMenuIdsByRoleId(roleId);
			assignedPermissionId = roleDao.selectPermissionIdsByRoleId(roleId);
		}else {
			Map<String, String> defaultRoleMap = SysConstants.getDefaultRoleMap();
			for (String id : defaultRoleMap.keySet()){ //创建系统缺省角色
				Role role = new Role();
				role.setId(id);
				role.setName(defaultRoleMap.get(id));
				role.setStatus(CommonConstants.Status.ACTIVE);
				role.setCreator(creator);
				role.setCreateDt(dtNow);
				roleDao.insert(role);
			}
		}
		
		RoleMenu rm = null;
		for(String menuId : menuIdSet){
			if(assignedMenuIds==null || !assignedMenuIds.contains(menuId)){ //如果该Menu还没被分配给superAdmin
				rm = new RoleMenu();
				rm.setId(MyUtil.generateUUID());
				rm.setRoleId(roleId);
				rm.setMenuId(menuId);
				roleDao.insertRoleMenu(rm);
			}
		}
		
		RolePermission rp = null;
		for(String permissionId : permissionIdSet){
			if(assignedPermissionId==null || !assignedPermissionId.contains(permissionId)){
				rp = new RolePermission();
				rp.setId(MyUtil.generateUUID());
				rp.setRoleId(roleId);
				rp.setPermissionId(permissionId);
				roleDao.insertRolePermission(rp);
			}
		}
		
		if(userDao.exists(SysConstants.ADMINISTRATOR)<1){
			// 创建超级管理员用户
			User user = new User();
			user.setId(SysConstants.ADMINISTRATOR);
			user.setCode(SysConstants.ADMINISTRATOR);
			user.setPassword(AuthxUtil.encryptByMd5(CommonConstants.INITIAL_PASSWORD));
			user.setName("超级管理员");
			user.setStatus(CommonConstants.Status.ACTIVE);
			user.setUserType(CommonConstants.UserType.ADMIN);
			user.setRoleId(roleId);
			user.setRemark("系统自动创建");
			user.setCreator(creator);
			user.setCreateDt(dtNow);
			userDao.insert(user);
		}
		log.info("Default roles and accounts created.");
	}

	@Override
	public ResultBean getRememberMe(HttpServletRequest request) {
		ResultBean rb = new ResultBean();
		String rememberMe = sessionService.getSessionId(request, WebConstant.RememberMeCookieName);
		if (!StringUtils.isEmpty(rememberMe)) {
			rb.setData(AuthxUtil.unencryptRememberMe(rememberMe));
		} else {
			rb.setSuccess(false);
		}
		return rb;
	}


}
