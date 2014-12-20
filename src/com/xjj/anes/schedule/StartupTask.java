package com.xjj.anes.schedule;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xjj.anes.service.sys.SysService;


public class StartupTask implements Runnable{
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Resource
	SysService sysService;
	
	@Override
	public void run() {
		//SysService sysService = CoreApplicationContext.getApplicationContext().getBean(SysService.class);
		if (sysService.txScanAnnotations())	{
			sysService.txCreateDefaultAccount();
		}

		//putDataToCache();
	}

	public void putDataToCache() {
/*		DictService dictService = CoreApplicationContext.getApplicationContext().getBean(DictService.class);
		dictService.putDictToCache();
		MenuService menuServie = CoreApplicationContext.getApplicationContext().getBean(MenuService.class);
		menuServie.putMenuToCache();*/
		
		System.out.println("Alert! putDataToCache.......................................");
		log.info("Alert! putDataToCache........" + new Date());
	}

}