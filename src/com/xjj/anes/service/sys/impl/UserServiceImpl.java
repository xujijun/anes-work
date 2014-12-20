package com.xjj.anes.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.dao.sys.UserDao;
import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.sys.User;
import com.xjj.anes.service.sys.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public ResultBean modifypwd(User user) {
		ResultBean resultBean = new ResultBean();
		int i = userDao.modifypwd(user);
		if (i > 0){
			resultBean.setMessage("修改密码成功");
			resultBean.setSuccess(true);
		} else {
			resultBean.setMessage("旧密码错误！");
			resultBean.setSuccess(false);
		}
		return resultBean;
	}

	@Override
	public ResultBean search(Pager<User> pager, User user) {
		ResultBean rb = new ResultBean();
		
		PagerEntity pagerEntity = new PagerEntity(user, pager.getOffset(), pager.getPageSize());
		pager.setDataSize(userDao.count(pagerEntity));
		if (pager.getDataSize() > 0) {
			if (pager.getDataSize() < pagerEntity.getOffset()) {
				pagerEntity.setOffset(0);
			}
			pager.setBeanList(userDao.search(pagerEntity));
		}
		
		rb.setData(pager);
		return rb;
	}

}
