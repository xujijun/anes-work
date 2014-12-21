package com.xjj.anes.mvc.controller.anes;

import org.springframework.stereotype.Controller;

import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.constants.MenuConstants;
import com.xjj.anes.mvc.controller.BaseController;

/**
 * 工作量管理基类
 */
@Controller
@SysMenu(id = MenuConstants.ANES, name = "工作量管理", parent = "", orderNo = 2, uri = "")
public class AnesBaseController extends BaseController {

}
