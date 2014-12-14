package com.xjj.anes.mvc.controller.sys;

import org.springframework.stereotype.Controller;

import com.xjj.anes.annotation.SysMenu;
import com.xjj.anes.constants.MenuConstants;
import com.xjj.anes.mvc.controller.BaseController;

/**
 * 系统管理基类
 * 
 * @author 钟伟雄
 * 
 */
@Controller
@SysMenu(id = MenuConstants.SYS, name = "系统管理", parent = "", orderNo = 1, uri = "")
public class SysBaseController extends BaseController
{

}