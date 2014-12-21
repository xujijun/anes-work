package com.xjj.anes.service.anes.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.dao.anes.AnesthetistDao;
import com.xjj.anes.dao.anes.WorkDao;
import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.anes.Anesthetist;
import com.xjj.anes.service.anes.AnesthetistService;

@Service
public class AnesthetistServiceImpl implements AnesthetistService {
	@Resource
	AnesthetistDao anesthetistDao;
	@Resource
	WorkDao workDao;

	@Override
	public ResultBean selectById(String id) {
		ResultBean rb = new ResultBean();
		rb.setData(anesthetistDao.selectById(id));
		return rb;
	}

	@Override
	public ResultBean search(Pager<Anesthetist> pager, Anesthetist anesthetist) {
		ResultBean rb = new ResultBean();
		
		PagerEntity pagerEntity = new PagerEntity(anesthetist, pager.getOffset(), pager.getPageSize());
		pager.setDataSize(anesthetistDao.count(pagerEntity));
		if (pager.getDataSize() > 0) {
			if (pager.getDataSize() < pagerEntity.getOffset()) {
				pagerEntity.setOffset(0);
			}
			pager.setBeanList(anesthetistDao.search(pagerEntity));
		}
		
		rb.setData(pager);
		return rb;
	}

	@Override
	public ResultBean insert(Anesthetist anesthetist) {
		ResultBean rb = new ResultBean();
		int i = anesthetistDao.insert(anesthetist);
		if (i <= 0)
		{
			rb.setSuccess(false);
			rb.setMessage("保存失败");
		}
		rb.setMessage("保存成功");
		
		return rb;
	}

	@Override
	public ResultBean update(Anesthetist anesthetist) {
		ResultBean rb = new ResultBean();
		int i = anesthetistDao.update(anesthetist);
		if (i <= 0)	{
			rb.setSuccess(false);
			rb.setMessage("修改失败");
		}
		return rb;
	}

	@Override
	public ResultBean delete(String id) {
		ResultBean rb = new ResultBean();
		
		if(workDao.countByAnesthetistId(id)>0){
			rb.setSuccess(false);
			rb.setMessage("该医生有工作量记录，无法删除。请先删除工作量；或设置医生状态为无效。");
			return rb;
		}
		
		int i = anesthetistDao.delete(id);
		if (i <= 0)	{
			rb.setSuccess(false);
			rb.setMessage("删除失败");
		}
		return rb;
	}

	//选择 Anesthetist Map
	@Override
	public ResultBean selectAllValid() {
		ResultBean rb = new ResultBean();
		Map<String, String> anesthetistMap = new LinkedHashMap<String, String>();
		List<Anesthetist> anesthetistMapList = anesthetistDao.selectAllValid();
		
		for(Anesthetist anesthetist : anesthetistMapList){
			anesthetistMap.put(anesthetist.getId(), anesthetist.getName());
		}
		rb.setData(anesthetistMap);
		return rb;
	}
}
