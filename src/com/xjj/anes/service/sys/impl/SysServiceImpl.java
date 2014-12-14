package com.xjj.anes.service.sys.impl;

import java.lang.reflect.Method;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.service.sys.SysService;
import com.xjj.anes.utils.ClassUtil;

@Service
public class SysServiceImpl implements SysService {
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Override
	public boolean scanAnnotations() {
		// TODO Auto-generated method stub
		
		boolean result = true;
		String pkg = "com.xjj.anes.mvc.controller";
		Set<Class<?>> allClses = null;
		
		allClses = ClassUtil.getClasses(pkg);
		SysMenu sysMenu = null;
		PermissionChecking permissionChecking = null;
		Method[] methods = null;
		
		for (Class<?> cls : allClses){
			log.info("Scan Menu and Permission annotations at class:" + cls);
			
			if(AnnotationUtils.findAnnotation(cls, Controller.class)==null){
				continue;
			}
			
			sysMenu = AnnotationUtils.findAnnotation(cls, SysMenu.class);
			
			if(sysMenu!=null){
				System.out.println(sysMenu.name());
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
