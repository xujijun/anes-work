package com.xjj.anes.entity.sys;

import java.util.Date;

public class Menu {
	private String id;
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

	private String creator;
	private Date createDt;
	private String updater;
	private Date updateDt;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getName() {
		return name;
	}

	public String getParentId() {
		return parentId;
	}

	public String getUri() {
		return uri;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public String getStatus() {
		return status;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
