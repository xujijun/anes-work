package com.xjj.anes.mvc.controller.api;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.cache.CacheConstants;
import com.xjj.anes.cache.CacheManager;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.service.sys.RoleService;
import com.xjj.anes.service.sys.SysService;


@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + "authx")
public class AuthxController {
	@Resource
	protected SysService sysService;
	@Resource
	protected RoleService roleService;

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
		return sysService.getRememberMe(request);
	}

	/**
	 * 根据多个分类编号获取多个字典集合
	 * @param clsCodes
	 * @return {{"clsCode" : {"code1":"name1,"code2":"name2"...}}, ...}
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("getDictList")
	public ResultBean getDictList(@RequestParam(value = "clsCodes[]", required = true) String[] clsCodes)
	{
		ResultBean rb = new ResultBean();
		
		Map<String, Map<String, String>> dictCache = (Map<String, Map<String, String>>) CacheManager.getInstance().get(CacheConstants.DICT);
		Map dictMap = new LinkedHashMap<>(); //使用LinkedHashMap保证顺序

		if(dictCache != null){
			for(String  clsCode: clsCodes){
				dictMap.put(clsCode, dictCache.get(clsCode));
			}
		}else{
			rb.setMessage("缓存中拿不到字典信息！");
			rb.setSuccess(false);
		}

		rb.setData(dictMap);
		return rb;
	}
	
	/**
	 * 获取角色列表，用户新增或修改的时候下拉选择
	 * @return
	 */
	@RequestMapping(value = "getRoleList")
	public ResultBean getRoleList()	{
		return roleService.getRoleList();
	}
}
