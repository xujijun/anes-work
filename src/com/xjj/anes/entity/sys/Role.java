package com.xjj.anes.entity.sys;

import com.xjj.anes.utils.CacheUtil;


/**
 * 角色表
 */
public class Role extends CoreEntity{
	/** 角色名称 */
	private String name;
	/** 状态 ON:有效，OFF：无效  */
	private String status;
	/** 备注 */
	private String remark;
	
	//转为中文名的字段
	public String getStatusName(){
		return CacheUtil.getStatusName(status);
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
