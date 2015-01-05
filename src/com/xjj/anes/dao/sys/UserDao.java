package com.xjj.anes.dao.sys;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.xjj.anes.dao.BaseDao;
import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.sys.User;

public interface UserDao extends BaseDao<User> {
	//public int insert(User entity);

	//public int update(User entity);
	
	//public int delete(@Param("id") String id);
	
	// id是否已经存在：0：不存在；>0：存在
	public int exists(@Param("id") String id);
	
	public User getUserByCode(@Param("code") String code);
	
	//获取用户权限
	public Set<String> getUserPermissionIds(@Param("userId") String userId);
	
	//修改密码
	public int modifypwd(User user);

	//统计
	public long count(PagerEntity pagerEntity);
	//分页搜索
	public List<User> search(PagerEntity pagerEntity);

	//code是否已经存在
	public int countCode(@Param("code") String code);

	//重置密码
	public int resetPwd(@Param("id") String id, @Param("pwd") String pwd);

	//public User select(@Param("id") String id);



}
