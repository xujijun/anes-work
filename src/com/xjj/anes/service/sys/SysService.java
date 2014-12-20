package com.xjj.anes.service.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjj.anes.bean.common.ResultBean;

public interface SysService {

	public ResultBean txLogin(HttpServletRequest request, HttpServletResponse response, String code, String password, String client, String verificationCode, boolean rememberMe);

	public ResultBean txLogout(HttpServletRequest request);
	
	/**
	 * 系统启动时自动扫描菜单和权限
	 * @return
	 */
	public boolean txScanAnnotations();
	
	/**
	 * 创建缺省账号
	 */
	public void txCreateDefaultAccount();

	public ResultBean getRememberMe(HttpServletRequest request);
}
