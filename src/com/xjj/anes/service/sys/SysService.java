package com.xjj.anes.service.sys;

public interface SysService {

	/**
	 * 系统启动时自动扫描菜单和权限
	 * @return
	 */
	public boolean txScanAnnotations();
	
	/**
	 * 创建缺省账号
	 */
	public void txCreateDefaultAccount();
}
