package com.xjj.anes.service.sys.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.dao.sys.RoleDao;
import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.sys.Role;
import com.xjj.anes.service.sys.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Resource 
	RoleDao roleDao;

	@Override
	public ResultBean getRoleList() {
		ResultBean rb = new ResultBean();
		Map<String, String> roleMap = new LinkedHashMap<String, String>();
		List<Role> roleList = roleDao.selectAllValid();
		
		for(Role role : roleList){
			roleMap.put(role.getId(), role.getName());
		}
		rb.setData(roleMap);
		return rb;
	}

	@Override
	public ResultBean search(Pager<Role> pager, Role role) {
		ResultBean rb = new ResultBean();
		
		PagerEntity pagerEntity = new PagerEntity(role, pager.getOffset(), pager.getPageSize());
		pager.setDataSize(roleDao.count(pagerEntity));
		if (pager.getDataSize() > 0) {
			if (pager.getDataSize() < pagerEntity.getOffset()) {
				pagerEntity.setOffset(0);
			}
			pager.setBeanList(roleDao.search(pagerEntity));
		}
		
		rb.setData(pager);
		return rb;
	}

}
