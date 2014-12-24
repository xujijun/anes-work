package com.xjj.anes.mvc.controller.anes;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.xjj.anes.utils.ExcelUtil;
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
	
	@RequestMapping(value = "exportExcel")
	@PermissionChecking(id = MenuConstants.Anes.ANES_WORK + "-exportExcel", name = "导出到Excel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Pager<Work> pager, Work work){
		pager.setNeedPage(false);//不需要分页
		ResultBean rb = workService.search(pager, work);
		@SuppressWarnings("unchecked")
		Pager<Work> p = (Pager<Work>) rb.getData();
		List<Work> dataSet = p.getBeanList();
		
		LinkedHashMap<String, String> propertyHeaderMap = new LinkedHashMap<>();
		propertyHeaderMap.put("operationDt", "手术时间");
		propertyHeaderMap.put("departmentName", "科室");
		propertyHeaderMap.put("admissionNo", "住院号");
		propertyHeaderMap.put("patientName", "病人姓名");
		propertyHeaderMap.put("patientAge", "病人年龄");
		propertyHeaderMap.put("anesMethodName", "手术名称");
		propertyHeaderMap.put("anesMethod", "麻醉方式");
		propertyHeaderMap.put("anesthetistName", "麻醉医生");
		propertyHeaderMap.put("pump", "术后止痛泵");
		propertyHeaderMap.put("dezocine", "地佐辛");
		propertyHeaderMap.put("mepivacaine", "甲哌卡因");
		
		XSSFWorkbook wb = ExcelUtil.generateXlsxWorkbook("工作量", propertyHeaderMap, dataSet);
		ExcelUtil.exportExcel("工作量", wb, request, response);
	}
}
