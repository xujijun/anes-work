package com.xjj.anes.service.sys.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.dao.sys.UserDao;
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

}
