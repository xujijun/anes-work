package com.xjj.anes.cache.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xjj.anes.utils.AuthxUtil;

public class BaseCache implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Log log = LogFactory.getLog(this.getClass().getName());
	protected boolean firstKeyIsForceMd5 = false;

	public void setFirstKeyIsForceMd5(boolean value) {
		firstKeyIsForceMd5 = value;
	}

	public void init() {

	}

	protected String getFirstKey(String key) {
		if (firstKeyIsForceMd5) {
			key = AuthxUtil.encryptByMd5(key);
		}
		return key;
	}

	protected boolean keyIsEmpty(String key) {
		if (key == null || "".equals(key)) {
			log.error("The key [" + key + "] is empty.");
			return true;
		}
		return false;
	}
}
