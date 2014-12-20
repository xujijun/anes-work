package com.xjj.anes.service.sys;

import com.xjj.anes.bean.common.ResultBean;
import com.xjj.anes.entity.sys.User;

public interface UserService {

	/**
	 * 修改用户密码
	 * @param user : password, newPassword
	 * @return
	 */
	ResultBean modifypwd(User user);
}
