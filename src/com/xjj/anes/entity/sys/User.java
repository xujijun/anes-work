package com.xjj.anes.entity.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xjj.anes.serializer.DateTimeSerializer;

public class User extends CoreEntity {
	private String code;
	private String password;
	private String newPassword;
	private String name;
	private String status;
	private String userType;
	private String roleId;
	private String remark;
	private Date unlockDt;
	
	private List<Menu> menuList = new ArrayList<Menu>();
	private Set<String> permissionIdSet = new HashSet<String>();
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getUnlockDt() {
		return unlockDt;
	}
	public void setUnlockDt(Date unlockDt) {
		this.unlockDt = unlockDt;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	public Set<String> getPermissionIdSet() {
		return permissionIdSet;
	}
	public void setPermissionIdSet(Set<String> permissionIdSet) {
		this.permissionIdSet = permissionIdSet;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	
}
