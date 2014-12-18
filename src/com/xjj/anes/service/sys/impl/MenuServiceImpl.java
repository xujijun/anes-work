package com.xjj.anes.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.dao.sys.MenuDao;
import com.xjj.anes.entity.sys.Menu;
import com.xjj.anes.service.sys.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuDao menuMapper;

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

	@Override
	public ResultBean update(Menu entity)
	{
		ResultBean rb = new ResultBean();
		int i = menuMapper.update(entity);
		if (i <= 0)
		{
			rb.setSuccess(false);
			rb.setMessage("更新失败");
		}
		rb.setMessage("更新成功");
		
		return rb;
	}
	
	@Override
	public ResultBean deleteMenuAndRel(String id) {
		ResultBean rb = new ResultBean();
		int i = menuMapper.deleteMenuAndRel(id);
		if (i <= 0)
		{
			rb.setSuccess(false);
			rb.setMessage("删除失败");
		}else{
			rb.setSuccess(true);
			rb.setMessage("删除成功");
		}
		return rb;
	}

}
