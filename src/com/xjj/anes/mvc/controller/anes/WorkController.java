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
import com.xjj.anes.entity.anes.Work;
import com.xjj.anes.service.anes.WorkService;
import com.xjj.anes.utils.MyUtil;

@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + MenuConstants.ANES + "/work/")
@SysMenu(id = MenuConstants.Anes.ANES_WORK, name = "工作量管理", parent = MenuConstants.ANES, orderNo = 1, uri = "/views/anes/work/list.html")
public class WorkController extends AnesBaseController {
	@Resource
	WorkService workService;
	
	@RequestMapping(value = "selectById")
	public ResultBean selectById(@RequestParam(value = "id", required = true) String id) {
		return workService.selectById(id);
	}
	
	@RequestMapping(value = "search")
	@PermissionChecking(id = MenuConstants.Anes.ANES_WORK + "-search", name = "搜索")
	public ResultBean search(HttpServletRequest request, Pager<Work> pager, Work work){
		return workService.search(pager, work);
	}
	
	@RequestMapping(value = "insert")
	@PermissionChecking(id = MenuConstants.Anes.ANES_WORK + "-insert", name = "新增")
	public ResultBean insert(HttpServletRequest request, Work work){
		work.setId(MyUtil.generateUUID());
		work.setCreateDt(new Date());
		work.setCreator(getUserCode(request));
		return workService.insert(work);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@PermissionChecking(id = MenuConstants.Anes.ANES_WORK + "-update", name = "修改")
	public ResultBean update(HttpServletRequest request, Work work)	{
		work.setUpdateDt(new Date());
		work.setUpdater(getUserCode(request));
		return workService.update(work);
	}
	
	@RequestMapping(value = "delete")
	@PermissionChecking(id = MenuConstants.Anes.ANES_WORK + "-delete", name = "删除")
	public ResultBean delete(HttpServletRequest request, @RequestParam(value = "id", required = true) String id) {
		return workService.delete(id);
	}
}
