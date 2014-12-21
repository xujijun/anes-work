package com.xjj.anes.bean.sys;

/**
 * 用于接收分配权限的结果
 */
public class RoleVo {
	private String id;
	private String[] menuIds;
	private String[] permissionIds;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}

	public String[] getPermissionIds() {
		return permissionIds;
	}

	public void setPermissionIds(String[] permissionIds) {
		this.permissionIds = permissionIds;
	}

}
