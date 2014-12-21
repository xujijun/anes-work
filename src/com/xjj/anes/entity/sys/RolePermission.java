package com.xjj.anes.entity.sys;


/**
 * 角色权限关系表
 */
public class RolePermission {
	private String id;
	private String roleId;
	private String permissionId;
	
	public RolePermission()	{
	}

	public RolePermission(String roleId, String permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
}
