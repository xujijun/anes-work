package com.xjj.anes.mvc.controller.anes;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.constants.MenuConstants;
import com.xjj.anes.entity.anes.Anesthetist;
import com.xjj.anes.service.anes.AnesthetistService;
import com.xjj.anes.utils.MyUtil;

@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + MenuConstants.ANES + "/anesthetist/")
@SysMenu(id = MenuConstants.Anes.ANES_ANESTHETIST, name = "医生管理", parent = MenuConstants.ANES, orderNo = 2, uri = "/views/anes/anesthetist/list.html")
public class AnesthetistController extends AnesBaseController {
	@Resource
	AnesthetistService anesthetistService;
	
	@RequestMapping(value = "selectById")
	public ResultBean view(@RequestParam(value = "id", required = true) String id) {
		return anesthetistService.selectById(id);
	}
	
	@RequestMapping(value = "selectAllValid")
	public ResultBean selectAllValid() {
		return anesthetistService.selectAllValid();
	}
	
	@RequestMapping(value = "search")
	@PermissionChecking(id = MenuConstants.Anes.ANES_ANESTHETIST + "-search", name = "搜索")
	public ResultBean search(HttpServletRequest request, Pager<Anesthetist> pager, Anesthetist anesthetist){
		return anesthetistService.search(pager, anesthetist);
	}
	
	@RequestMapping(value = "insert")
	@PermissionChecking(id = MenuConstants.Anes.ANES_ANESTHETIST + "-insert", name = "新增")
	public ResultBean insert(HttpServletRequest request, Anesthetist anesthetist){
		anesthetist.setId(MyUtil.generateUUID());
		anesthetist.setCreateDt(new Date());
		anesthetist.setCreator(getUserCode(request));
		return anesthetistService.insert(anesthetist);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@PermissionChecking(id = MenuConstants.Anes.ANES_ANESTHETIST + "-update", name = "修改")
	public ResultBean update(HttpServletRequest request, Anesthetist anesthetist)	{
		anesthetist.setUpdateDt(new Date());
		anesthetist.setUpdater(getUserCode(request));
		return anesthetistService.update(anesthetist);
	}
	
	@RequestMapping(value = "delete")
	@PermissionChecking(id = MenuConstants.Anes.ANES_ANESTHETIST + "-delete", name = "删除")
	public ResultBean delete(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		return anesthetistService.delete(id);
	}
}
