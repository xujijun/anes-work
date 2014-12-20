package com.xjj.anes.entity.sys;

/**
 * 字典实体
 */
public class Dict extends CoreEntity {
	/** 分类编号 */
	private String clsCode;
	/** 分类名称 */
	private String clsName;
	/** 字典编号 */
	private String code;
	/** 字典名称 */
	private String name;
	/** 状态 ON:有效，OFF：无效 */
	private String status;

	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClsCode() {
		return clsCode;
	}

	public void setClsCode(String clsCode) {
		this.clsCode = clsCode;
	}

	public String getClsName() {
		return clsName;
	}

	public void setClsName(String clsName) {
		this.clsName = clsName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
