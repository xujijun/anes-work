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
}
