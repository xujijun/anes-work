package com.xjj.anes.entity.anes;

import com.xjj.anes.entity.sys.CoreEntity;
import com.xjj.anes.utils.CacheUtil;

public class Anesthetist extends CoreEntity {
	private String name;
	private String sex;
	private String status;
	
	//转为中文名的字段
	public String getSexName(){
		return CacheUtil.getSexName(sex);
	}
	public String getStatusName(){
		return CacheUtil.getStatusName(status);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
