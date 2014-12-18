package com.xjj.anes.entity.sys;

import org.springframework.util.StringUtils;


/**
 * 菜单表
 */
public class Menu extends CoreEntity {
	/** 菜单名称 */
	private String name;
	/** 父级菜单ID标识 */
	private String parentId;
	private String uri;
	/** 排序号 */
	private Integer orderNo;
	/** 状态 */
	private String status;
	/** 备注 **/
	private String remark;
	
	//如果ParentId为空，则是父菜单
	public boolean getParent(){
		return StringUtils.isEmpty(parentId) ? true : false; 
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
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
