package com.xjj.anes.schedule;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xjj.anes.cache.CacheConstants;
import com.xjj.anes.cache.CacheManager;
import com.xjj.anes.dao.sys.DictDao;
import com.xjj.anes.entity.sys.Dict;
import com.xjj.anes.service.sys.SysService;


public class StartupTask implements Runnable{
	private Log log = LogFactory.getLog(this.getClass().getName());
	
	@Resource
	SysService sysService;
	
	@Resource
	DictDao dictDao;
	
	@Override
	public void run() {
		//SysService sysService = CoreApplicationContext.getApplicationContext().getBean(SysService.class);
		if (sysService.txScanAnnotations())	{
			sysService.txCreateDefaultAccount();
		}

		putDataToCache();
	}

	public void putDataToCache() {
/*		DictService dictService = CoreApplicationContext.getApplicationContext().getBean(DictService.class);
		dictService.putDictToCache();
		MenuService menuServie = CoreApplicationContext.getApplicationContext().getBean(MenuService.class);
		menuServie.putMenuToCache();*/
		
		putDictToCache();
	}
	
	public void putDictToCache() {
		Map<String, Map<String, String>> map = new LinkedHashMap<String, Map<String, String>>();
		List<Dict> dicts = dictDao.getUseableList();
		String clsCode = null;
		for (Dict dict : dicts) {
			clsCode = dict.getClsCode();
			if (!map.containsKey(clsCode)) {
				map.put(clsCode, new LinkedHashMap<String, String>());
			}
			map.get(clsCode).put(dict.getCode(), dict.getName());
		}
		CacheManager.getInstance().put(CacheConstants.DICT, map);
		log.info("putDictToCache......Done! " + new Date());
	}

}