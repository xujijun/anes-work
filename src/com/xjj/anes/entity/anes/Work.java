package com.xjj.anes.entity.anes;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.xjj.anes.entity.sys.CoreEntity;
import com.xjj.anes.serializer.DateSerializer;
import com.xjj.anes.utils.CacheUtil;

public class Work extends CoreEntity {
	private Date operationDt;
	private String department;
	private String admissionNo;
	private String patientName;
	private String patientAge;
	private String operationName;
	private String anesMethod;
	private String anesthetistId;
	private int pump;
	private int dezocine;
	private int mepivacaine;
	
	//查询字段
	private Date startDt;
	private Date endDt;
	private String anesthetistName;
	
	public Date getStartDt() {
		return startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	//转为中文名的字段
	public String getDepartmentName(){
		return CacheUtil.getDepartmentName(department);
	}
	public String getAnesMethodName(){
		return CacheUtil.getAnesMethodName(anesMethod);
	}
	
	@JsonSerialize(using = DateSerializer.class)
	public Date getOperationDt() {
		return operationDt;
	}
	public void setOperationDt(Date operationDt) {
		this.operationDt = operationDt;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAdmissionNo() {
		return admissionNo;
	}
	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getAnesMethod() {
		return anesMethod;
	}
	public void setAnesMethod(String anesMethod) {
		this.anesMethod = anesMethod;
	}
	public String getAnesthetistId() {
		return anesthetistId;
	}
	public void setAnesthetistId(String anesthetistId) {
		this.anesthetistId = anesthetistId;
	}
	public int getPump() {
		return pump;
	}
	public void setPump(int pump) {
		this.pump = pump;
	}
	public int getDezocine() {
		return dezocine;
	}
	public void setDezocine(int dezocine) {
		this.dezocine = dezocine;
	}
	public int getMepivacaine() {
		return mepivacaine;
	}
	public void setMepivacaine(int mepivacaine) {
		this.mepivacaine = mepivacaine;
	}
	public String getAnesthetistName() {
		return anesthetistName;
	}
	public void setAnesthetistName(String anesthetistName) {
		this.anesthetistName = anesthetistName;
	}
	
}
