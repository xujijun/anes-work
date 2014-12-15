package com.xjj.anes.service.sys.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
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

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.entity.sys.Menu;
import com.xjj.anes.service.sys.MenuService;
import com.xjj.anes.service.sys.SysService;
import com.xjj.anes.utils.ClassUtil;

@Service
public class SysServiceImpl implements SysService {
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Resource
	private MenuService menuService;
	
	@Override
	public boolean scanAnnotations() {
		// TODO Auto-generated method stub
		
		boolean result = true;
		String pkg = "com.xjj.anes.mvc.controller";
		String userName = "system";
		Date dtNow = new Date();
		Set<Class<?>> allClses = null;
		
		Map<String, Menu> menuMap = new HashMap<String, Menu>();
		@SuppressWarnings("unchecked")
		List<Menu> menuList = (List<Menu>) menuService.listAll().getData();
		
		for (Menu m : menuList)	{
			menuMap.put(m.getId(), m);
		}
		
		allClses = ClassUtil.getClasses(pkg);
		String menuId = null;
		SysMenu sysMenu = null;
		Menu menu = null;
		PermissionChecking permissionChecking = null;
		Method[] methods = null;
		
		for (Class<?> cls : allClses){
			log.info("Scan Menu and Permission annotations at class:" + cls);
			
			//只扫描@Controller
			if(AnnotationUtils.findAnnotation(cls, Controller.class)==null){
				continue;
			}
			
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
			
			//TODO to be removed
			if(sysMenu!=null){
				System.out.println(sysMenu.name());
			}
			
			menuId = sysMenu.id();
			if (menuMap.containsKey(menuId)) { //Menu表已经存在该Menu ID
				menu = menuMap.get(menuId);
			} else{ //Menu表不存在该Menu ID，则添加到Menu表中
				menu = new Menu();
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
				menu.setCreator(userName);
				menu.setCreateDt(dtNow);
				menu.setStatus(CommonConstants.Status.ACTIVE);
				menuService.insert(menu);
				menuMap.put(menu.getId(), menu);
			}
			
			methods = cls.getDeclaredMethods();
			for (Method method : methods){
				permissionChecking = AnnotationUtils.findAnnotation(method, PermissionChecking.class);
				if(permissionChecking==null){
					continue;
				}
				System.out.println(permissionChecking.name());
			}
		}
		
		return result;
	}

}
