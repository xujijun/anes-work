package com.xjj.anes.entity.sys;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xjj.anes.serializer.DateTimeSerializer;

public abstract class CoreEntity
{
	protected String id;
	protected String creator;
	protected Date createDt;
	protected String updater;
	protected Date updateDt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@JsonSerialize(using = DateTimeSerializer.class)
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
	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
}
