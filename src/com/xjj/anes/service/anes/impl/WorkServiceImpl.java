package com.xjj.anes.service.anes.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.dao.anes.WorkDao;
import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.anes.Work;
import com.xjj.anes.service.anes.WorkService;

@Service
public class WorkServiceImpl implements WorkService {
	@Resource
	WorkDao workDao;

	@Override
	public ResultBean selectById(String id) {
		ResultBean rb = new ResultBean();
		rb.setData(workDao.selectById(id));
		return rb;
	}

	@Override
	public ResultBean search(Pager<Work> pager, Work work) {
		ResultBean rb = new ResultBean();
		
		PagerEntity pagerEntity = new PagerEntity(work, pager.getOffset(), pager.getPageSize());
		pager.setDataSize(workDao.count(pagerEntity));
		if (pager.getDataSize() > 0) {
			if (pager.getDataSize() < pagerEntity.getOffset()) {
				pagerEntity.setOffset(0);
			}
			pager.setBeanList(workDao.search(pagerEntity));
		}
		
		rb.setData(pager);
		return rb;
	}

	@Override
	public ResultBean insert(Work work) {
		ResultBean rb = new ResultBean();
		int i = workDao.insert(work);
		if (i <= 0)
		{
			rb.setSuccess(false);
			rb.setMessage("保存失败");
		}
		rb.setMessage("保存成功");
		
		return rb;
	}

	@Override
	public ResultBean update(Work work) {
		ResultBean rb = new ResultBean();
		int i = workDao.update(work);
		if (i <= 0)	{
			rb.setSuccess(false);
			rb.setMessage("修改失败");
		}
		return rb;
	}

	@Override
	public ResultBean delete(String id) {
		ResultBean rb = new ResultBean();
		
		int i = workDao.delete(id);
		if (i <= 0)	{
			rb.setSuccess(false);
			rb.setMessage("删除失败");
		}
		return rb;
	}
}
