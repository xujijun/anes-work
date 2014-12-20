package com.xjj.anes.service.sys;

import com.xjj.anes.bean.common.Pager;
import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.entity.sys.User;

public interface UserService {

	/**
	 * 修改用户密码
	 * @param user : password, newPassword
	 * @return
	 */
	ResultBean modifypwd(User user);

	//搜索用户
	ResultBean search(Pager<User> pager, User user);

	//新增用户
	ResultBean insert(User user);

	//检查用户名是否可用
	boolean checkCode(String code);

	ResultBean update(User user);

	ResultBean delete(String id, String deleter);

	//重置密码
	ResultBean resetPwd(String id, String pwd);

	ResultBean select(String id);
}
