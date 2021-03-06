package com.xjj.anes.entity.sys;


/**
 * 角色菜单关系表
 *
 */
public class RoleMenu {
	private String id;
	private String roleId;
	private String menuId;
	
	public RoleMenu() {
	}

	public RoleMenu(String roleId, String menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
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
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
