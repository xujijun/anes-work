package com.xjj.anes.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.dao.sys.MenuMapper;
import com.xjj.anes.entity.sys.Menu;
import com.xjj.anes.service.sys.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuMapper menuMapper;

	@Override
	public ResultBean listAll() {
		ResultBean rb = new ResultBean();
		rb.setData(menuMapper.listAll());
		return rb;
	}

	@Override
	public ResultBean insert(Menu menu) {
		ResultBean rb = new ResultBean();
		int i = menuMapper.insert(menu);
		if (i <= 0)
		{
			rb.setSuccess(false);
			rb.setMessage("保存失败");
		}
		rb.setMessage("保存成功");
		
		return rb;
	}

}
