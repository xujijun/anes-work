package com.xjj.anes.mvc.controller.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.service.sys.SysService;


@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + "authx")
public class AuthxController {
	@Resource
	protected SysService sysService;

	@RequestMapping(value = CommonConstants.UriPrefix.PC + "login")
	public ResultBean pcLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "password", required = true) String password, @RequestParam(value = "verificationCode", required = false) String verificationCode,
			@RequestParam(value = "rememberMe", required = false, defaultValue = "false") boolean rememberMe) {
		return sysService.txLogin(request, response, code, password, CommonConstants.Client.PC, verificationCode, rememberMe);
	}

	@RequestMapping(value = "/logout")
	public ResultBean logout(HttpServletRequest request, HttpServletResponse response) {
		return sysService.txLogout(request);
	}

	@RequestMapping(value = "/getRememberMe")
	public ResultBean getRememberMe(HttpServletRequest request, HttpServletResponse response) {
		return null;
		//return sysService.getRememberMe(request);
	}

}
