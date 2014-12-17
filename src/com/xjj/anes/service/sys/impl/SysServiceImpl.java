package com.xjj.anes.service.sys.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.constants.SysConstants;
import com.xjj.anes.dao.sys.MenuMapper;
import com.xjj.anes.dao.sys.PermissionMapper;
import com.xjj.anes.dao.sys.RoleMapper;
import com.xjj.anes.dao.sys.UserMapper;
import com.xjj.anes.entity.sys.Menu;
import com.xjj.anes.entity.sys.Permission;
import com.xjj.anes.entity.sys.Role;
import com.xjj.anes.entity.sys.RoleMenu;
import com.xjj.anes.entity.sys.RolePermission;
import com.xjj.anes.entity.sys.User;
import com.xjj.anes.service.sys.SysService;
import com.xjj.anes.utils.AuthxUtil;
import com.xjj.anes.utils.ClassUtil;
import com.xjj.anes.utils.MyUtil;

@Service
public class SysServiceImpl implements SysService {
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Resource
	private MenuMapper menuMapper;
	@Resource
	private PermissionMapper permissionMapper;
	@Resource
	private RoleMapper roleMapper;
	@Resource
	private UserMapper userMapper;
	
	@Override
	public boolean txScanAnnotations() {
		boolean result = true;
		String pkg = "com.xjj.anes.mvc.controller";
		String userName = "system";
		Date dtNow = new Date();
		Set<Class<?>> allClses = null;
		
		Map<String, Menu> menuMap = new HashMap<String, Menu>();
		List<Menu> menuList = (List<Menu>) menuMapper.listAll();
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
		List<Permission> permissionList = permissionMapper.listAll();
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
				menuMapper.update(menu);
			} else{ //Menu表不存在该Menu ID，则添加到Menu表中
				menu.setCreator(userName);
				menu.setCreateDt(dtNow);
				menuMapper.insert(menu);
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
					permissionMapper.update(permission);
				} else {
					permission = new Permission();
					permission.setId(permissionChecking.id());
					permission.setMenuId(menuId);
					permission.setName(permissionChecking.name());
					permission.setCls(cls.getName());
					permission.setMethod(method.getName());
					permissionMapper.insert(permission);
				}
				permissionMap.put(permission.getId(), permission);
				permissionIdSet.add(permission.getId());
			}
		}
		
		//删除扫描列表中没有的Menu及其关联表
		for (String id : menuMap.keySet()) {
			if (!menuIdSet.contains(id)) {
				log.info("Delete Menu[" + id + "] " + menuMap.get(id));
				menuMapper.deleteMenuAndRel(id);
			}
		}
		
		for(String id : permissionMap.keySet()){
			if(!permissionIdSet.contains(id)){
				log.info("Delete Permission[" + id + "] " + permissionMap.get(id));
				permissionMapper.deletePermissionAndRel(id);
			}
		}
		
		return result;
	}

	@Override
	public void txCreateDefaultAccount() {
		String creator = "system";
		Date dtNow = new Date();
		
		//获取所有Menu
		Set<String> menuIdSet = new HashSet<String>();
		List<Menu> menuList = (List<Menu>) menuMapper.listAll();
		for (Menu m : menuList)	{
			menuIdSet.add(m.getId());
		}
		
		//获取所有Permission
		Set<String> permissionIdSet = new HashSet<String>();
		List<Permission> permissionList = permissionMapper.listAll();
		for (Permission p : permissionList)	{
			permissionIdSet.add(p.getId());
		}
		
		String roleId = SysConstants.DefaultRole.superAdmin;
		Set<String> assignedMenuIds = null; //上次已经分配给superAdmin的MenuIds
		Set<String> assignedPermissionId = null; //上次已经分配给superAdmin的PermissionIds
		
		//超级管理员角色是否已经存在
		if(roleMapper.exists(SysConstants.DefaultRole.superAdmin) >0 ){
			assignedMenuIds = roleMapper.selectMenuIdsByRoleId(roleId);
			assignedPermissionId = roleMapper.selectPermissionIdsByRoleId(roleId);
		}else {
			Map<String, String> defaultRoleMap = SysConstants.getDefaultRoleMap();
			for (String id : defaultRoleMap.keySet()){ //创建系统缺省角色
				Role role = new Role();
				role.setId(id);
				role.setName(defaultRoleMap.get(id));
				role.setStatus(CommonConstants.Status.ACTIVE);
				role.setCreator(creator);
				role.setCreateDt(dtNow);
				roleMapper.insert(role);
			}
		}
		
		RoleMenu rm = null;
		for(String menuId : menuIdSet){
			if(assignedMenuIds!=null && !assignedMenuIds.contains(menuId)){ //如果该Menu还没被分配给superAdmin
				rm = new RoleMenu();
				rm.setId(MyUtil.generateUUID());
				rm.setRoleId(roleId);
				rm.setMenuId(menuId);
				roleMapper.insertRoleMenu(rm);
			}
		}
		
		RolePermission rp = null;
		for(String permissionId : permissionIdSet){
			if(assignedPermissionId!=null && !assignedPermissionId.contains(permissionId)){
				rp = new RolePermission();
				rp.setId(MyUtil.generateUUID());
				rp.setRoleId(roleId);
				rp.setPermissionId(permissionId);
				roleMapper.insertRolePermission(rp);
			}
		}
		
		if(userMapper.equals(SysConstants.ADMINISTRATOR)){
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
			userMapper.insert(user);
		}

	}

}
