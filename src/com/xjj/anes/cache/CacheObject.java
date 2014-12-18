package com.xjj.anes.cache;

import java.io.Serializable;
import java.util.Date;

import com.xjj.anes.utils.DateTimeUtil;

public class CacheObject implements Serializable {
	private static final long serialVersionUID = -3695513855839201605L;
	private String key;
	private Date expireDt;
	private Object object;

	public CacheObject(String k, Object obj) {
		key = k;
		object = obj;
		expireDt = DateTimeUtil.stringToDate("2100-12-30", "yyyy-MM-dd");
	}

	public CacheObject(String k, Object obj, Date dt) {
		key = k;
		object = obj;
		expireDt = dt;
	}

	public String getKey() {
		return key;
	}

	public Date getExpireDt() {
		return expireDt;
	}

	public void setExpireDt(Date value) {
		expireDt = value;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object value) {
		object = value;
	}

	public boolean isAvailable() {
		return expireDt == null || expireDt.getTime() >= new Date().getTime();
	}

	@Override
	public String toString() {
		return "CacheObject [key=" + key + ", expireDt=" + expireDt
				+ ", object=" + object + "]";
	}
}
