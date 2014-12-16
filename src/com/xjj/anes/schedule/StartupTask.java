package com.xjj.anes.schedule;

import javax.annotation.Resource;

import com.xjj.anes.service.sys.SysService;


public class StartupTask implements Runnable{
	@Resource
	SysService sysService;
	
	@Override
	public void run()
	{
		//SysService sysService = CoreApplicationContext.getApplicationContext().getBean(SysService.class);
		if (sysService.txScanAnnotations())
		{
			//sysService.txAutoCreateDefaultSuperAdmin();
		}

		//putDataToCache();
	}

/*	public void putDataToCache()
	{
		DictService dictService = CoreApplicationContext.getApplicationContext().getBean(DictService.class);
		dictService.putDictToCache();

		RegionService regionService = CoreApplicationContext.getApplicationContext().getBean(RegionService.class);
		regionService.putRegionToCache();

		MenuService menuServie = CoreApplicationContext.getApplicationContext().getBean(MenuService.class);
		menuServie.putMenuToCache();
	}*/

}