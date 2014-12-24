package com.xjj.anes.mvc.controller.anes;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.anes.annotation.PermissionChecking;
import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.bean.anes.WorkAnalysisVo;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.constants.CommonConstants;
import com.xjj.anes.constants.MenuConstants;
import com.xjj.anes.entity.anes.Work;
import com.xjj.anes.service.anes.WorkService;
import com.xjj.anes.utils.ExcelUtil;

@RestController
@RequestMapping(value = CommonConstants.UriPrefix.API + MenuConstants.ANES + "/workAnlys/")
@SysMenu(id = MenuConstants.Anes.ANES_ANLYS, name = "工作量统计", parent = MenuConstants.ANES, orderNo = 3, uri = "/views/anes/work/workAnlys.html")
public class WorkAnalysisController extends AnesBaseController {
	@Resource
	WorkService workService;
	
	@RequestMapping(value = "anlys")
	@PermissionChecking(id = MenuConstants.Anes.ANES_ANLYS + "-anlys", name = "统计")
	public ResultBean workAnlys(Work work){
		return workService.workAnlys(work);
	}
	
	@RequestMapping(value = "exportExcel")
	@PermissionChecking(id = MenuConstants.Anes.ANES_ANLYS + "-exportExcel", name = "导出到Excel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Work work){
		ResultBean rb = workService.workAnlys(work);
		@SuppressWarnings("unchecked")
		List<WorkAnalysisVo> dataSet = (List<WorkAnalysisVo>) rb.getData();
		
		LinkedHashMap<String, String> propertyHeaderMap = new LinkedHashMap<>();
		propertyHeaderMap.put("searchDate", "时间范围");
		propertyHeaderMap.put("anesthetistName", "麻醉医生");
		propertyHeaderMap.put("optNo", "手术数量");
		propertyHeaderMap.put("pumpNo", "术后止痛泵数量");
		propertyHeaderMap.put("dezocineNo", "地佐辛数量");
		propertyHeaderMap.put("mepivacaineNo", "甲哌卡因数量");
		
		XSSFWorkbook wb = ExcelUtil.generateXlsxWorkbook("工作量统计", propertyHeaderMap, dataSet);
		ExcelUtil.exportExcel("工作量统计", wb, request, response);
	}
}
