package com.xjj.anes.dao.sys;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.sys.User;

public interface UserDao {
	public int insert(User entity);

	public int update(User entity);
	
	// id是否已经存在：0：不存在；>0：存在
	public int exists(@Param("id") String id);
	
	public User getUserByCode(String code);
	
	//获取用户权限
	public Set<String> getUserPermissionIds(@Param("userId") String userId);
	
	//修改密码
	public int modifypwd(User user);

	//统计
	public long count(PagerEntity pagerEntity);
	//分页搜索
	public List<User> search(PagerEntity pagerEntity);

}
