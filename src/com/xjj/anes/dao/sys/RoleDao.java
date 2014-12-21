package com.xjj.anes.dao.sys;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.xjj.anes.entity.PagerEntity;
import com.xjj.anes.entity.sys.Role;
import com.xjj.anes.entity.sys.RoleMenu;
import com.xjj.anes.entity.sys.RolePermission;

public interface RoleDao {
	public Role selectById(@Param("id") String id);
	
	public int insert(Role entity);
	
	public List<Role> selectAllValid();
	
	// id是否已经存在：0：不存在；>0：存在
	public int exists(@Param("id") String id);
	
	// 根据Role Id查找Menu Ids
	public Set<String> selectMenuIdsByRoleId(@Param("roleId") String roleId);
	
	// 根据Role Id查找Permission Ids
	public Set<String> selectPermissionIdsByRoleId(@Param("roleId") String roleId);
	
	// 插入到role_menu关系表
	public int insertRoleMenu(RoleMenu entity);
	
	// 插入到role_permission关系表
	public int insertRolePermission(RolePermission entity);

	public long count(PagerEntity pagerEntity);

	public List<Role> search(PagerEntity pagerEntity);

	// 删除角色及其关联表
	public int deleteRoleAndRel(String id);

	public int update(Role role);
}
